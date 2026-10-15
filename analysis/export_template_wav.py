"""Export one instrument section from a bundled Template as PCM WAV."""

from __future__ import annotations

import argparse
import json
import re
import struct
import wave
import zipfile
from pathlib import Path

from parse_default_template import DEFAULT_APK, parse_asset


def safe_name(value: str) -> str:
    value = re.sub(r"[^A-Za-z0-9._-]+", "_", value.strip())
    return value.strip("._") or "section"


def pcm_for_section(
    template: bytes, parsed: dict[str, object], section: dict[str, object]
) -> bytes:
    pcm_bytes = int(section["length"]) * int(section["channels"]) * 2
    wave_index = int(section["wave_index"])
    if wave_index:
        stored_index = wave_index - 1
        matches = [wave for wave in parsed["waves"] if wave["index"] == stored_index]
        if len(matches) != 1:
            raise ValueError(f"WaveIndex {wave_index} maps to {len(matches)} entries")
        source = matches[0]
        if int(source["bytes"]) != pcm_bytes:
            raise ValueError(
                f"wave size mismatch: metadata={pcm_bytes}, stored={source['bytes']}"
            )
        offset = int(source["offset"])
    else:
        if int(section["pcm_bytes"]) != pcm_bytes or section["pcm_offset"] is None:
            raise ValueError("section has no extractable inline PCM")
        offset = int(section["pcm_offset"])
    return template[offset : offset + pcm_bytes]


def main() -> None:
    cli = argparse.ArgumentParser()
    cli.add_argument("--apk", type=Path, default=DEFAULT_APK)
    cli.add_argument("--asset", default="assets/Default.Template")
    cli.add_argument("--instrument", type=int, required=True, help="zero-based index")
    cli.add_argument("--sound", type=int, default=0, help="zero-based index")
    cli.add_argument("--note", type=int, required=True, help="stored section note index")
    cli.add_argument("--output", type=Path)
    cli.add_argument("--metadata", type=Path)
    args = cli.parse_args()

    with zipfile.ZipFile(args.apk) as archive:
        template = archive.read(args.asset)
    parsed = parse_asset(template, args.asset)
    if parsed["signature"] == "Music Studio Instrument":
        if args.instrument != 0:
            raise ValueError("standalone Instrument asset only has instrument index 0")
        instrument = parsed["instrument"]
    else:
        instrument = parsed["instruments"][args.instrument]
    sound = instrument["sounds"][args.sound]
    matches = [section for section in sound["sections"] if section["note"] == args.note]
    if len(matches) != 1:
        raise ValueError(f"note {args.note} matched {len(matches)} sections")
    section = matches[0]
    pcm = pcm_for_section(template, parsed, section)

    output = args.output
    if output is None:
        output = Path(
            f"{safe_name(str(instrument['name']))}-{args.sound}-{args.note}-"
            f"{safe_name(str(section['name']))}.wav"
        )
    output.parent.mkdir(parents=True, exist_ok=True)
    with wave.open(str(output), "wb") as wav:
        wav.setnchannels(int(section["channels"]))
        wav.setsampwidth(2)
        wav.setframerate(int(section["sample_rate"]))
        wav.writeframes(pcm)

    metadata = {
        "source_asset": args.asset,
        "instrument_index": args.instrument,
        "instrument": instrument["name"],
        "sound_index": args.sound,
        "sound": sound["name"],
        "note": args.note,
        "section": section["name"],
        "sample_rate": section["sample_rate"],
        "channels": section["channels"],
        "samples_per_channel": section["length"],
        "pcm_bytes": len(pcm),
        "wave_index": section["wave_index"],
        "loop": section["loop"],
        "loop_start": section["loop_start"],
        "loop_end": section["loop_end"],
        "output": str(output),
    }
    if args.metadata:
        args.metadata.parent.mkdir(parents=True, exist_ok=True)
        args.metadata.write_text(
            json.dumps(metadata, ensure_ascii=False, indent=2) + "\n", encoding="utf-8"
        )
    print(json.dumps(metadata, ensure_ascii=False))


if __name__ == "__main__":
    main()
