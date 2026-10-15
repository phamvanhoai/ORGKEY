"""Inventory and validate the nested assets/data.zip bundled with ORG 24."""

from __future__ import annotations

import argparse
import hashlib
import io
import json
import re
import struct
import wave
import zipfile
from collections import Counter
from pathlib import Path, PurePosixPath


ROOT = Path(__file__).resolve().parent.parent
DEFAULT_APK = ROOT / "ORG+24_+Your+Music_2026.10.01_APKPure.apk"
DEFAULT_OUTPUT = ROOT / "analysis" / "data-zip-inventory.json"


def rotate12(value: int, amount: int) -> int:
    amount %= 12
    return ((value << amount) | (value >> (12 - amount))) & 0xFFF


def parse_system(path: str, data: bytes) -> dict[str, object]:
    if len(data) < 265:
        raise ValueError(f"{path}: truncated System")
    signature = data[:32].split(b"\0", 1)[0].decode("ascii")
    if signature != "Music Studio System":
        raise ValueError(f"{path}: unexpected signature {signature!r}")
    offset = 32
    metadata_size = struct.unpack_from("<i", data, offset)[0]
    offset += 4
    metadata = data[offset : offset + metadata_size].decode("utf-8")
    offset += metadata_size
    parts = metadata.split("\r")
    group = struct.unpack_from("<i", data, offset)[0]
    offset += 4
    start_key = data[offset]
    offset += 1
    selected_keys = struct.unpack_from("<i", data, offset)[0]
    offset += 4
    reserved = data[offset : offset + 28]
    offset += 28
    tuning = list(struct.unpack_from("<24d", data, offset))
    offset += 24 * 8
    if offset != len(data):
        raise ValueError(f"{path}: parser stopped at {offset}/{len(data)}")
    return {
        "path": path,
        "size": len(data),
        "name": parts[0] if parts else "",
        "version": parts[1] if len(parts) > 1 else "",
        "description": parts[2] if len(parts) > 2 else "",
        "group": group,
        "start_key": start_key,
        "selected_keys_mask": selected_keys,
        "selected_key_indices": [i for i in range(24) if selected_keys & (1 << i)],
        "reserved_nonzero": sum(value != 0 for value in reserved),
        "tuning": tuning,
        "nonzero_tuning": {str(i): value for i, value in enumerate(tuning) if value != 0},
        "parsed_bytes": offset,
        "eof": True,
    }


def parse_chord(path: str, data: bytes) -> dict[str, object]:
    values = [int(item) for item in data.decode("ascii").splitlines() if item]
    if len(values) != 12 or any(not 0 <= value <= 0xFFF for value in values):
        raise ValueError(f"{path}: expected twelve 12-bit masks")
    expected = [rotate12(values[0], i) for i in range(12)]
    return {
        "path": path,
        "name": PurePosixPath(path).stem,
        "size": len(data),
        "masks": values,
        "root_intervals": [i for i in range(12) if values[0] & (1 << i)],
        "all_transpositions_are_rotations": values == expected,
        "rotation_mismatches": [i for i, pair in enumerate(zip(values, expected)) if pair[0] != pair[1]],
    }


def parse_model(path: str, data: bytes) -> dict[str, object]:
    text = data.decode("utf-8-sig")
    fields: dict[str, object] = {}
    for raw in text.splitlines():
        line = raw.strip()
        if not line or line.startswith("//") or ":" not in line:
            continue
        key, value = (part.strip() for part in line.split(":", 1))
        values = [part.strip() for part in value.split(",")]
        fields[key] = values if len(values) > 1 else value
    return {"path": path, "size": len(data), "fields": fields}


def parse_png(path: str, data: bytes) -> dict[str, object]:
    if data[:8] != b"\x89PNG\r\n\x1a\n" or data[12:16] != b"IHDR":
        raise ValueError(f"{path}: invalid PNG")
    width, height, depth, color = struct.unpack_from(">IIBB", data, 16)
    return {"path": path, "size": len(data), "width": width, "height": height,
            "bit_depth": depth, "color_type": color}


def parse_wav(path: str, data: bytes) -> dict[str, object]:
    with wave.open(io.BytesIO(data), "rb") as wav:
        return {"path": path, "size": len(data), "channels": wav.getnchannels(),
                "sample_width_bits": wav.getsampwidth() * 8, "sample_rate": wav.getframerate(),
                "frames": wav.getnframes(), "duration_seconds": wav.getnframes() / wav.getframerate()}


def main() -> None:
    parser = argparse.ArgumentParser()
    parser.add_argument("apk", nargs="?", type=Path, default=DEFAULT_APK)
    parser.add_argument("-o", "--output", type=Path, default=DEFAULT_OUTPUT)
    args = parser.parse_args()

    with zipfile.ZipFile(args.apk) as apk:
        nested = apk.read("assets/data.zip")
    with zipfile.ZipFile(io.BytesIO(nested)) as archive:
        entries = [(info, archive.read(info)) for info in archive.infolist() if not info.is_dir()]

    chords, systems, models, pngs, wavs, texts, others = [], [], [], [], [], [], []
    suffixes = Counter()
    for info, data in entries:
        path = info.filename
        suffix = PurePosixPath(path).suffix or "(none)"
        suffixes[suffix] += 1
        digest = hashlib.sha256(data).hexdigest()
        if suffix == ".Chord":
            item = parse_chord(path, data)
            item["sha256"] = digest
            chords.append(item)
        elif suffix == ".System":
            item = parse_system(path, data)
            item["sha256"] = digest
            systems.append(item)
        elif suffix == ".Model":
            item = parse_model(path, data)
            item["sha256"] = digest
            models.append(item)
        elif suffix == ".png":
            item = parse_png(path, data)
            item["sha256"] = digest
            pngs.append(item)
        elif suffix == ".wav":
            item = parse_wav(path, data)
            item["sha256"] = digest
            wavs.append(item)
        elif suffix in (".Script", ".Skin", ".en", ".nomedia"):
            text = data.decode("utf-8-sig", "replace")
            texts.append({"path": path, "type": suffix, "size": len(data),
                          "lines": len(text.splitlines()), "sha256": digest})
        else:
            others.append({"path": path, "size": len(data), "sha256": digest})

    result = {
        "source": str(args.apk),
        "asset": "assets/data.zip",
        "zip_size": len(nested),
        "zip_sha256": hashlib.sha256(nested).hexdigest(),
        "file_count": len(entries),
        "suffix_counts": dict(sorted(suffixes.items())),
        "validation": {
            "systems_at_eof": sum(bool(item["eof"]) for item in systems),
            "chords_with_12_masks": len(chords),
            "strict_rotation_chords": sum(bool(item["all_transpositions_are_rotations"]) for item in chords),
        },
        "chords": chords,
        "systems": systems,
        "models": models,
        "pngs": pngs,
        "wavs": wavs,
        "text_assets": texts,
        "other_files": others,
    }
    args.output.parent.mkdir(parents=True, exist_ok=True)
    args.output.write_text(json.dumps(result, ensure_ascii=False, indent=2) + "\n", encoding="utf-8")
    print(f"wrote {args.output}: {len(entries)} files, {len(chords)} chords, {len(systems)} systems")


if __name__ == "__main__":
    main()
