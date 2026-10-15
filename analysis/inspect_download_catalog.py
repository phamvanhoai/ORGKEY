"""Parse the bundled download.en content catalog from assets/data.zip."""

from __future__ import annotations

import argparse
import hashlib
import io
import json
import zipfile
from collections import Counter
from datetime import datetime, timezone
from pathlib import Path


ROOT = Path(__file__).resolve().parent.parent
DEFAULT_APK = ROOT / "ORG+24_+Your+Music_2026.10.01_APKPure.apk"
DEFAULT_OUTPUT = ROOT / "analysis" / "download-catalog-inventory.json"


def is_author(line: str) -> bool:
    return line.strip().lower().startswith("by ")


def timestamp(value: str) -> dict[str, object]:
    raw = value.strip()
    try:
        millis = int(raw)
        iso = datetime.fromtimestamp(millis / 1000, timezone.utc).date().isoformat()
        return {"raw": raw, "milliseconds": millis, "utc_date": iso}
    except (ValueError, OSError, OverflowError):
        return {"raw": raw, "milliseconds": None, "utc_date": None}


def parse_catalog(data: bytes) -> dict[str, object]:
    lines = data.decode("utf-8-sig").splitlines()
    author_starts = [i for i, line in enumerate(lines) if is_author(line)]
    groups = []
    errors = []
    for group_index, start in enumerate(author_starts):
        end = author_starts[group_index + 1] if group_index + 1 < len(author_starts) else len(lines)
        block = lines[start:end]
        if len(block) < 6 or not block[5].startswith(("=", "-")):
            errors.append({"line": start + 1, "reason": "invalid author header/separator"})
            continue
        items = []
        body = block[6:]
        for offset in range(0, len(body), 6):
            chunk = body[offset : offset + 6]
            if len(chunk) != 6 or not chunk[5].startswith("-"):
                errors.append({"line": start + 7 + offset, "reason": "invalid item record", "data": chunk})
                continue
            items.append({
                "title": chunk[0].strip(),
                "description": chunk[1].strip(),
                "archive": chunk[2].strip(),
                "preview": chunk[3].strip(),
                "timestamp": timestamp(chunk[4]),
            })
        groups.append({
            "author": block[0].strip(),
            "email": block[1].strip(),
            "channel": block[2].strip(),
            "website": block[3].strip(),
            "flag": block[4].strip(),
            "item_count": len(items),
            "items": items,
        })
    all_items = [item for group in groups for item in group["items"]]
    archives = [item["archive"] for item in all_items]
    extension_counts = Counter(Path(name).suffix.lower() or "(none)" for name in archives)
    archive_counts = Counter(archives)
    return {
        "size": len(data),
        "sha256": hashlib.sha256(data).hexdigest(),
        "line_count": len(lines),
        "group_count": len(groups),
        "item_count": len(all_items),
        "preview_count": sum(item["preview"] != "-" for item in all_items),
        "valid_timestamp_count": sum(item["timestamp"]["utc_date"] is not None for item in all_items),
        "extension_counts": dict(sorted(extension_counts.items())),
        "duplicate_archives": {name: count for name, count in archive_counts.items() if count > 1},
        "parse_errors": errors,
        "groups": groups,
    }


def main() -> None:
    parser = argparse.ArgumentParser()
    parser.add_argument("apk", nargs="?", type=Path, default=DEFAULT_APK)
    parser.add_argument("-o", "--output", type=Path, default=DEFAULT_OUTPUT)
    args = parser.parse_args()
    with zipfile.ZipFile(args.apk) as apk:
        nested = apk.read("assets/data.zip")
    with zipfile.ZipFile(io.BytesIO(nested)) as archive:
        data = archive.read("download.en")
    result = {"source": str(args.apk), "asset": "assets/data.zip!/download.en", **parse_catalog(data)}
    args.output.write_text(json.dumps(result, ensure_ascii=False, indent=2) + "\n", encoding="utf-8")
    print(f"wrote {args.output}: {result['group_count']} groups, {result['item_count']} items")


if __name__ == "__main__":
    main()
