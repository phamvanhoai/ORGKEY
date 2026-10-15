"""Parse bundled Music Studio templates from the inspected ORG 24 APK.

The parser supports the bundled formats 1.2.4 and 1.4.6 and validates every
boundary through EOF. It does not decode PCM samples or DSP semantics; it
records their offsets and sizes.
"""

from __future__ import annotations

import argparse
from collections import Counter
import json
import struct
import zipfile
from pathlib import Path


ROOT = Path(__file__).resolve().parent.parent
DEFAULT_APK = ROOT / "ORG+24_+Your+Music_2026.10.01_APKPure.apk"
DEFAULT_ASSET = "assets/Default.Template"

# Extra bytes read after the common 4-byte type + 4-byte n01 payload.
FX_EXTRA = {
    1: 8,
    2: 17,
    4: 8,
    6: 8,
    8: 28,
    9: 4,
    10: 4,
    15: 4,
    16: 4,
    17: 4,
    20: 8,
    21: 24,
    23: 9,
    29: 8,
    31: 4,
}


class Reader:
    def __init__(self, data: bytes):
        self.data = data
        self.offset = 0
        self.version = ""

    def take(self, size: int) -> bytes:
        if size < 0 or self.offset + size > len(self.data):
            raise ValueError(f"invalid read offset={self.offset} size={size}")
        result = self.data[self.offset : self.offset + size]
        self.offset += size
        return result

    def u8(self) -> int:
        return self.take(1)[0]

    def boolean(self) -> bool:
        return self.u8() != 0

    def i16(self) -> int:
        return struct.unpack("<h", self.take(2))[0]

    def i32(self) -> int:
        return struct.unpack("<i", self.take(4))[0]

    def i64(self) -> int:
        return struct.unpack("<q", self.take(8))[0]

    def long_string(self) -> str:
        size = self.i32()
        if not 0 <= size <= 1_000_000:
            raise ValueError(f"invalid string size {size} at {self.offset - 4}")
        return self.take(size).decode("utf-8", "replace")

    def fixed_string(self) -> str:
        raw = self.take(32)[1:]
        return raw.split(b"\0", 1)[0].decode("utf-8", "replace")

    def common_object(self) -> dict[str, object]:
        start = self.offset
        metadata = self.long_string()
        parts = metadata.split("\r")
        return {
            "offset": start,
            "name": parts[0] if parts else "",
            "version": parts[1] if len(parts) > 1 else "",
            "description": parts[2] if len(parts) > 2 else "",
            "group": self.i32(),
        }

    def fx(self) -> dict[str, object]:
        start = self.offset
        values = list(struct.unpack("<7i", self.take(28)))
        enabled = self.boolean()
        self.take(18)
        flag = self.boolean()
        count = self.i32()
        if not 0 <= count <= 1_000:
            raise ValueError(f"invalid FX count {count} at {self.offset - 4}")
        modules = []
        for _ in range(count):
            module_start = self.offset
            module_type = self.i32()
            self.take(4)  # n01: enabled + byte[2] + flag
            if module_type == 7:
                band_count = self.i32()
                if not 0 <= band_count <= 128:
                    raise ValueError(f"invalid equalizer band count {band_count}")
                self.take(4 * band_count)
                detail = {"band_count": band_count}
            else:
                if module_type not in FX_EXTRA:
                    raise ValueError(f"unknown FX type {module_type} at {module_start}")
                self.take(FX_EXTRA[module_type])
                detail = {}
            modules.append(
                {
                    "offset": module_start,
                    "type": module_type,
                    "bytes": self.offset - module_start,
                    **detail,
                }
            )
        return {
            "offset": start,
            "values": values,
            "enabled": enabled,
            "flag": flag,
            "modules": modules,
            "bytes": self.offset - start,
        }

    def effects(self) -> None:
        self.take(8 * 40)

    def section(self) -> dict[str, object]:
        start = self.offset
        name = self.fixed_string()
        legacy_rate = struct.unpack("<H", self.take(2))[0]
        bits = self.u8()
        channels = self.u8()
        sample_rate = self.i32() or legacy_rate or 48_000
        stored_wave_index = self.i32()
        wave_index = stored_wave_index if self.version >= "1.2.7" else 0
        length = self.i32()
        self.i32()  # reserved
        volume, pan, tune, sample_start = struct.unpack("<4i", self.take(16))
        loop = self.u8()
        self.take(3)
        loop_start, loop_end = struct.unpack("<2i", self.take(8))
        self.take(16)
        pcm_offset = None
        pcm_bytes = 0
        effective_channels = channels if channels in (1, 2) else 2
        if length > 0 and wave_index == 0:
            pcm_offset = self.offset
            pcm_bytes = length * effective_channels * 2
            self.take(pcm_bytes)
        return {
            "offset": start,
            "name": name,
            "sample_rate": sample_rate,
            "source_bits": bits,
            "channels": effective_channels,
            "wave_index": wave_index,
            "stored_wave_index": stored_wave_index,
            "length": length,
            "volume": volume,
            "pan": pan,
            "tune": tune,
            "start": sample_start,
            "loop": loop,
            "loop_start": loop_start,
            "loop_end": loop_end,
            "pcm_offset": pcm_offset,
            "pcm_bytes": pcm_bytes,
            "bytes": self.offset - start,
        }

    def instrument(self) -> dict[str, object]:
        item = self.common_object()
        version = str(item["version"])
        if version not in ("1.2.4", "1.4.2", "1.4.6"):
            raise ValueError(f"unexpected instrument version {version}")
        self.version = version
        self.take(52)
        if version >= "1.4.0":
            self.take(15 * 4)
            legacy_ads = None
        else:
            self.take(3 * 4)
            legacy_ads = [tuple(self.take(3)) for _ in range(16)]
        volume, pan = self.i32(), self.i32()
        instrument_type, family, priority, mode_count = self.take(4)
        modes = []
        for mode_index in range(mode_count):
            mode_start = self.offset
            name = (
                self.long_string()
                if mode_index != 0 or version >= "1.4.1"
                else "Default mode"
            )
            sound, aftertouch = self.i32(), self.i32()
            mix = list(self.take(8))
            fx = self.fx()
            self.effects()
            mix_volume = list(self.take(8))
            mix_pan = list(self.take(8))
            script = self.long_string() if version >= "1.4.0" else ""
            setting_count = self.i32() if version >= "1.4.4" else 0
            settings = [self.long_string() for _ in range(setting_count)]
            setting_values = []
            if setting_count:
                value_count = self.i32()
                setting_values = [self.long_string() for _ in range(value_count)]
            modes.append(
                {
                    "offset": mode_start,
                    "name": name,
                    "sound": sound,
                    "aftertouch": aftertouch,
                    "mix": mix,
                    "mix_volume": mix_volume,
                    "mix_pan": mix_pan,
                    "script_bytes": len(script.encode("utf-8")),
                    "settings": settings,
                    "setting_values": setting_values,
                    "fx": fx,
                    "bytes": self.offset - mode_start,
                }
            )
        first_sound_legacy = None
        if version < "1.4.1":
            if version >= "1.4.0":
                attack, decay, sustain = self.take(3)
            else:
                assert legacy_ads is not None
                attack, decay, sustain = legacy_ads[0]
            release, pitch_mode, auto_release = self.take(3)
            first_sound_legacy = (
                attack,
                decay,
                sustain,
                release,
                pitch_mode,
                auto_release,
            )
        sound_count = self.u8()
        sounds = []
        for sound_index in range(max(sound_count, 1)):
            sound_start = self.offset
            if sound_index != 0 or version >= "1.4.1":
                name = self.long_string()
                if version >= "1.4.0":
                    attack, decay, sustain = self.take(3)
                else:
                    assert legacy_ads is not None
                    attack, decay, sustain = legacy_ads[sound_index]
                release, pitch_mode, auto_release = self.take(3)
            else:
                name = "Primary"
                assert first_sound_legacy is not None
                attack, decay, sustain, release, pitch_mode, auto_release = first_sound_legacy
            section_count = self.u8()
            sections = []
            for _ in range(section_count):
                note = self.u8()
                sections.append({"note": note, **self.section()})
            sounds.append(
                {
                    "offset": sound_start,
                    "name": name,
                    "attack": attack,
                    "decay": decay,
                    "sustain": sustain,
                    "release": release,
                    "pitch_mode": pitch_mode,
                    "auto_release": bool(auto_release),
                    "sections": sections,
                    "bytes": self.offset - sound_start,
                }
            )
        return {
            **item,
            "volume": volume,
            "pan": pan,
            "type": instrument_type,
            "family": family,
            "priority": priority,
            "modes": modes,
            "sounds": sounds,
            "bytes": self.offset - int(item["offset"]),
        }

    def system(self) -> dict[str, object]:
        item = self.common_object()
        start_key = self.u8()
        selected_keys = self.i32()
        self.take(28)
        tuning = list(struct.unpack("<24d", self.take(24 * 8)))
        return {
            **item,
            "start_key": start_key,
            "selected_keys": selected_keys,
            "tuning": tuning,
            "bytes": self.offset - int(item["offset"]),
        }

    def sts(self) -> dict[str, object]:
        start = self.offset
        record = self.boolean()
        name = self.take(self.u8()).decode("utf-8", "replace")
        midi_out = list(self.take(2))
        instrument, system = self.i32(), self.i32()
        pedal, mode, data1 = self.i16(), self.i16(), self.i16()
        data2, chord_type = self.u8(), self.u8()
        chord = [self.i16() for _ in range(12)] if chord_type else []
        fx = self.fx()
        self.effects()
        return {
            "offset": start,
            "record": record,
            "name": name,
            "midi_out": midi_out,
            "instrument": instrument,
            "system": system,
            "pedal": pedal,
            "mode": mode,
            "data1": data1,
            "data2": data2,
            "chord_type": chord_type,
            "chord": chord,
            "fx": fx,
            "bytes": self.offset - start,
        }

    def channels(self) -> dict[str, object]:
        start = self.offset
        fx = self.fx()
        self.effects()
        return {"offset": start, "fx": fx, "bytes": self.offset - start}

    def event(self) -> dict[str, object]:
        start = self.offset
        event_type, tune = self.i16(), self.i16()
        event_id, key, touch, time, length = struct.unpack("<5i", self.take(20))
        section = self.section() if event_type == 101 else None
        return {
            "offset": start,
            "event": event_type,
            "tune": tune,
            "id": event_id,
            "key": key,
            "touch": touch,
            "time": time,
            "length": length,
            "section": section,
            "bytes": self.offset - start,
        }

    def track(self) -> dict[str, object]:
        item = self.common_object()
        track_type, kind = self.u8(), self.u8()
        midi_out = self.i16()
        system, instrument = self.i32(), self.i32()
        midi_instrument = self.u8()
        solo, mute, record = self.boolean(), self.boolean(), self.boolean()
        midi_in = self.i16()
        bank0, bank1, chord_root, chord_type = self.take(4)
        self.take(2)
        color = self.i32()
        self.take(25 * 4)
        stored_sts_count = self.i32()
        sts_count = 13 if self.version < "1.2.9" else stored_sts_count
        enabled = (
            [self.boolean() for _ in range(sts_count)]
            if item["group"] == 0 or self.version < "1.2.8"
            else []
        )
        sts = [self.sts() for value in enabled if value]
        self.take(7)
        pedal_mode, pedal, key_from, key_to = struct.unpack("<4i", self.take(16))
        channels = self.channels()
        event_count = self.i32()
        events = [self.event() for _ in range(event_count)]
        return {
            **item,
            "type": track_type,
            "kind": kind,
            "midi_out": midi_out,
            "system": system,
            "instrument": instrument,
            "midi_instrument": midi_instrument,
            "solo": solo,
            "mute": mute,
            "record": record,
            "midi_in": midi_in,
            "midi_bank": [bank0, bank1],
            "chord_root": chord_root,
            "chord_type": chord_type,
            "color": color,
            "sts_count": sts_count,
            "stored_sts_count": stored_sts_count,
            "sts_enabled": sum(enabled),
            "sts": sts,
            "pedal_mode": pedal_mode,
            "pedal": pedal,
            "key_from": key_from,
            "key_to": key_to,
            "channels": channels,
            "events": events,
            "bytes": self.offset - int(item["offset"]),
        }

    def track_group(self) -> dict[str, object]:
        start = self.offset
        group, parent = self.i32(), self.i32()
        name = self.fixed_string()
        section, group_type = self.u8(), self.u8()
        loop, limit = self.boolean(), self.boolean()
        limit_start, limit_end, jump_group, color = struct.unpack("<4i", self.take(16))
        chord_type, chord_base = self.u8(), self.u8()
        tempo = self.i16()
        shortcut_device = self.i32()
        shortcut_midi, reserved, meter_x, meter_y = self.take(4)
        sts_indexes = (
            list(struct.unpack("<4i", self.take(16))) if self.version >= "1.4.6" else []
        )
        fx = self.fx() if self.version >= "1.4.2" else None
        return {
            "offset": start,
            "group": group,
            "parent": parent,
            "name": name,
            "section": section,
            "type": group_type,
            "loop": loop,
            "limit": limit,
            "limit_start": limit_start,
            "limit_end": limit_end,
            "jump_group": jump_group,
            "color": color,
            "chord_type": chord_type,
            "chord_base": chord_base,
            "tempo": tempo,
            "shortcut_device": shortcut_device,
            "shortcut_midi": shortcut_midi,
            "reserved": reserved,
            "meter": [meter_x, meter_y],
            "sts_indexes": sts_indexes,
            "fx": fx,
            "bytes": self.offset - start,
        }


def parse(data: bytes, asset_name: str = DEFAULT_ASSET) -> dict[str, object]:
    reader = Reader(data)
    signature = reader.take(32).split(b"\0", 1)[0].decode("ascii")
    music = reader.common_object()
    if signature != "Music Studio Music" or music["version"] not in ("1.2.4", "1.4.6"):
        raise ValueError(f"unsupported template: {signature!r}, {music['version']!r}")
    reader.version = str(music["version"])

    album = reader.long_string()
    info_type = reader.long_string()
    copyright_text = reader.long_string()
    password_size = reader.i32()
    if password_size:
        raise ValueError("password-protected template is outside this parser's scope")
    info_reserved = reader.i16()
    stereo_mode, tempo, record_counter = reader.i32(), reader.i32(), reader.i32()
    tempo_lock = reader.boolean()
    reader.take(115)

    wave_slot_count, wave_count = reader.i32(), reader.i32()
    waves = []
    for _ in range(wave_count):
        wave_index, byte_count = reader.i32(), reader.i32()
        wave_offset = reader.offset
        reader.take(byte_count)
        waves.append({"index": wave_index, "offset": wave_offset, "bytes": byte_count})

    music_fx = [reader.fx(), reader.fx(), reader.fx()]

    instruments_header = reader.common_object()
    instrument_count = reader.i32()
    instruments = [reader.instrument() for _ in range(instrument_count)]

    systems_header = reader.common_object()
    system_count = reader.i32()
    systems = [reader.system() for _ in range(system_count)]

    tracks_header = reader.common_object()
    track_count = reader.i32()
    tracks = [reader.track() for _ in range(track_count)]
    group_count = reader.i32()
    groups = [reader.track_group() for _ in range(group_count)]

    if reader.offset != len(data):
        raise ValueError(f"parser stopped at {reader.offset}, file size is {len(data)}")

    event_count = sum(len(track["events"]) for track in tracks)
    embedded_sections = sum(
        event["section"] is not None for track in tracks for event in track["events"]
    )
    event_types = Counter(
        event["event"] for track in tracks for event in track["events"]
    )
    return {
        "asset": asset_name,
        "size": len(data),
        "parsed_to_eof": True,
        "signature": signature,
        "music": music,
        "info": {
            "album": album,
            "type": info_type,
            "copyright": copyright_text,
            "reserved": info_reserved,
            "stereo_mode": stereo_mode,
            "tempo": tempo,
            "record_counter": record_counter,
            "tempo_lock": tempo_lock,
        },
        "summary": {
            "wave_slots": wave_slot_count,
            "waves": wave_count,
            "wave_bytes": sum(wave["bytes"] for wave in waves),
            "instruments": instrument_count,
            "systems": system_count,
            "tracks": track_count,
            "events": event_count,
            "event_types": {str(key): value for key, value in sorted(event_types.items())},
            "embedded_sample_events": embedded_sections,
            "track_groups": group_count,
        },
        "waves": waves,
        "music_fx": music_fx,
        "instruments_header": instruments_header,
        "instruments": instruments,
        "systems_header": systems_header,
        "systems": systems,
        "tracks_header": tracks_header,
        "tracks": tracks,
        "track_groups": groups,
    }


def parse_asset(data: bytes, asset_name: str) -> dict[str, object]:
    signature = data[:32].split(b"\0", 1)[0].decode("ascii", "replace")
    if signature == "Music Studio Music":
        return parse(data, asset_name)
    if signature != "Music Studio Instrument":
        raise ValueError(f"unsupported asset signature {signature!r}")
    reader = Reader(data)
    reader.take(32)
    instrument = reader.instrument()
    if reader.offset != len(data):
        raise ValueError(f"parser stopped at {reader.offset}, file size is {len(data)}")
    sections = [
        section
        for sound in instrument["sounds"]
        for section in sound["sections"]
    ]
    return {
        "asset": asset_name,
        "size": len(data),
        "parsed_to_eof": True,
        "signature": signature,
        "summary": {
            "modes": len(instrument["modes"]),
            "sounds": len(instrument["sounds"]),
            "sections": len(sections),
            "inline_pcm_sections": sum(section["pcm_bytes"] > 0 for section in sections),
            "pcm_bytes": sum(section["pcm_bytes"] for section in sections),
        },
        "instrument": instrument,
    }


def main() -> None:
    parser = argparse.ArgumentParser()
    parser.add_argument("--apk", type=Path, default=DEFAULT_APK)
    parser.add_argument("--asset", default=DEFAULT_ASSET)
    parser.add_argument("--output", type=Path)
    parser.add_argument("--compact", action="store_true")
    args = parser.parse_args()
    with zipfile.ZipFile(args.apk) as archive:
        result = parse_asset(archive.read(args.asset), args.asset)
    if args.compact and result["signature"] == "Music Studio Music":
        result = {
            "asset": result["asset"],
            "size": result["size"],
            "parsed_to_eof": result["parsed_to_eof"],
            "signature": result["signature"],
            "music": result["music"],
            "info": result["info"],
            "summary": result["summary"],
            "music_fx": result["music_fx"],
            "instruments": [
                {
                    "offset": value["offset"],
                    "bytes": value["bytes"],
                    "name": value["name"],
                    "group": value["group"],
                    "type": value["type"],
                    "family": value["family"],
                    "modes": [mode["name"] for mode in value["modes"]],
                    "sounds": [sound["name"] for sound in value["sounds"]],
                    "sections": sum(len(sound["sections"]) for sound in value["sounds"]),
                }
                for value in result["instruments"]
            ],
            "systems": [
                {
                    "offset": value["offset"],
                    "bytes": value["bytes"],
                    "name": value["name"],
                    "start_key": value["start_key"],
                    "selected_keys": value["selected_keys"],
                    "tuning": value["tuning"],
                }
                for value in result["systems"]
            ],
            "tracks": [
                {
                    "offset": value["offset"],
                    "bytes": value["bytes"],
                    "name": value["name"],
                    "group": value["group"],
                    "type": value["type"],
                    "kind": value["kind"],
                    "system": value["system"],
                    "instrument": value["instrument"],
                    "sts_count": value["sts_count"],
                    "sts_enabled": value["sts_enabled"],
                    "events": len(value["events"]),
                }
                for value in result["tracks"]
            ],
            "track_groups": [
                {
                    "offset": value["offset"],
                    "bytes": value["bytes"],
                    "group": value["group"],
                    "parent": value["parent"],
                    "name": value["name"],
                    "section": value["section"],
                    "type": value["type"],
                    "tempo": value["tempo"],
                    "meter": value["meter"],
                }
                for value in result["track_groups"]
            ],
        }
    text = json.dumps(result, ensure_ascii=False, indent=2)
    if args.output:
        args.output.write_text(text + "\n", encoding="utf-8")
    else:
        print(text)


if __name__ == "__main__":
    main()
