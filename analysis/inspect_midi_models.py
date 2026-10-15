"""Parse the MIDI .Model files bundled in assets/data.zip."""

from __future__ import annotations

import argparse
import hashlib
import io
import json
import re
import zipfile
from collections import Counter
from pathlib import Path


ROOT = Path(__file__).resolve().parent.parent
DEFAULT_APK = ROOT / "ORG+24_+Your+Music_2026.10.01_APKPure.apk"
DEFAULT_OUTPUT = ROOT / "analysis" / "midi-model-inventory.json"

# tp2.<clinit>: command name to internal command code for entries used by the
# bundled files. The class contains 70 supported command names in total.
COMMAND_CODES = {"instrument": 0xF0}


def integer(value: str, default: int) -> int:
    try:
        return int(value.strip())
    except ValueError:
        return default


def parse_model(path: str, data: bytes) -> dict[str, object]:
    text = data.decode("utf-8-sig")
    name = ""
    key_count, key_start, chord_start = 61, 36, 17
    skin = ""
    operations = []
    ignored = []
    for line_number, raw in enumerate(text.splitlines(), 1):
        line = raw.strip()
        if not line or line.startswith("/"):
            continue
        parts = [part.strip() for part in re.split(r":|=|,|/", line)]
        key = parts[0].lower()
        if key == "name":
            name = parts[1] if len(parts) > 1 else ""
        elif key == "key":
            key_count = integer(parts[1], 61) if len(parts) > 1 else key_count
            key_start = integer(parts[2], 36) if len(parts) > 2 else key_start
            chord_start = integer(parts[3], 17) if len(parts) > 3 else chord_start
        elif key == "skin":
            skin = parts[1] if len(parts) > 1 else ""
        elif key in COMMAND_CODES:
            operations.append({
                "line": line_number,
                "command": parts[0],
                "command_code": COMMAND_CODES[key],
                "index": integer(parts[1], 0) if len(parts) > 1 else 0,
                "event": parts[2].lower() if len(parts) > 2 else "",
                "channel": integer(parts[3], -1) if len(parts) > 3 else -1,
                "value1": integer(parts[4], -1) if len(parts) > 4 else -1,
                "value2": integer(parts[5], -1) if len(parts) > 5 else -1,
            })
        else:
            ignored.append({"line": line_number, "text": line})
    return {
        "path": path,
        "size": len(data),
        "sha256": hashlib.sha256(data).hexdigest(),
        "name": name,
        "key": {"count": key_count, "start": key_start, "chord": chord_start,
                "end_inclusive": key_start + key_count - 1},
        "skin": skin,
        "operation_count": len(operations),
        "operations": operations,
        "ignored_lines": ignored,
    }


def main() -> None:
    parser = argparse.ArgumentParser()
    parser.add_argument("apk", nargs="?", type=Path, default=DEFAULT_APK)
    parser.add_argument("-o", "--output", type=Path, default=DEFAULT_OUTPUT)
    args = parser.parse_args()
    with zipfile.ZipFile(args.apk) as apk:
        nested = apk.read("assets/data.zip")
    with zipfile.ZipFile(io.BytesIO(nested)) as archive:
        models = [parse_model(info.filename, archive.read(info)) for info in archive.infolist()
                  if info.filename.endswith(".Model")]
    operation_shapes = Counter(
        (op["command"], op["event"], op["channel"])
        for model in models for op in model["operations"]
    )
    result = {
        "source": str(args.apk),
        "asset": "assets/data.zip",
        "model_count": len(models),
        "total_operations": sum(model["operation_count"] for model in models),
        "all_lines_recognized": all(not model["ignored_lines"] for model in models),
        "operation_shapes": [
            {"command": key[0], "event": key[1], "channel": key[2], "count": count}
            for key, count in sorted(operation_shapes.items())
        ],
        "models": models,
    }
    args.output.write_text(json.dumps(result, ensure_ascii=False, indent=2) + "\n", encoding="utf-8")
    print(f"wrote {args.output}: {len(models)} models, {result['total_operations']} operations")


if __name__ == "__main__":
    main()
