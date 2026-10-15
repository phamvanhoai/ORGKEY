#!/usr/bin/env python3
"""Inventory ORG's text-based Skin/Part assets without extracting the APK."""

from __future__ import annotations

import hashlib
import json
import re
import sys
import zipfile
from collections import Counter
from pathlib import Path


HEADER_NAMES = [
    "logical_width", "header_height", "row_height", "row_count",
    "default_1", "default_2", "default_3", "default_4",
    "color_1", "color_2", "color_0", "flag_y", "flag_z", "color_3",
]


def parse_ints(line: str) -> list[int]:
    return [int(value) for value in line.split()]


def main() -> int:
    root = Path(__file__).resolve().parent.parent
    apk = Path(sys.argv[1]).resolve() if len(sys.argv) > 1 else next(root.glob("*.apk"))
    output = Path(sys.argv[2]).resolve() if len(sys.argv) > 2 else Path(__file__).with_name("skin-assets-inventory.json")
    files: list[dict] = []
    ids: Counter[int] = Counter()
    errors: list[str] = []

    with zipfile.ZipFile(apk) as archive:
        names = sorted(
            name for name in archive.namelist()
            if name.startswith("assets/skin/") and Path(name).suffix in {".Skin", ".Part"}
        )
        for name in names:
            raw = archive.read(name)
            try:
                text = raw.decode("utf-8")
            except UnicodeDecodeError as exc:
                errors.append(f"{name}: UTF-8: {exc}")
                continue
            lines = [line.strip() for line in text.splitlines() if line.strip()]
            if len(lines) < 2 or lines[0] != "; ORG Skin file":
                errors.append(f"{name}: missing signature/header")
                continue
            try:
                header_values = parse_ints(lines[1])
                records = [parse_ints(line) for line in lines[2:]]
            except ValueError as exc:
                errors.append(f"{name}: non-integer token: {exc}")
                continue
            if len(header_values) < 7:
                errors.append(f"{name}: header has {len(header_values)} fields")
            short_records = [index + 3 for index, record in enumerate(records) if len(record) < 9]
            if short_records:
                errors.append(f"{name}: records shorter than 9 fields at lines {short_records}")
            for record in records:
                if record:
                    ids[record[0]] += 1
            header = {
                HEADER_NAMES[index] if index < len(HEADER_NAMES) else f"extra_{index}": value
                for index, value in enumerate(header_values)
            }
            family_match = re.match(r"(.+?)(?:#(\d+))?\.(?:Skin|Part)$", Path(name).name)
            family, part = family_match.groups() if family_match else (Path(name).stem, None)
            files.append({
                "path": name,
                "kind": Path(name).suffix[1:],
                "family": family,
                "part": int(part) if part else None,
                "size": len(raw),
                "sha256": hashlib.sha256(raw).hexdigest(),
                "header_field_count": len(header_values),
                "header": header,
                "record_count": len(records),
                "record_field_counts": dict(sorted(Counter(map(len, records)).items())),
                "component_ids": dict(sorted(Counter(record[0] for record in records if record).items())),
                "coordinate_bounds": {
                    "left": [min((r[5] for r in records), default=None), max((r[5] for r in records), default=None)],
                    "top": [min((r[6] for r in records), default=None), max((r[6] for r in records), default=None)],
                    "right": [min((r[7] for r in records), default=None), max((r[7] for r in records), default=None)],
                    "bottom": [min((r[8] for r in records), default=None), max((r[8] for r in records), default=None)],
                },
            })

    families: dict[str, dict] = {}
    for item in files:
        family = families.setdefault(item["family"], {"base": None, "parts": [], "records": 0})
        if item["kind"] == "Skin":
            family["base"] = item["path"]
        else:
            family["parts"].append(item["part"])
        family["records"] += item["record_count"]
    for family in families.values():
        family["parts"].sort()

    report = {
        "apk": {"path": str(apk), "sha256": hashlib.sha256(apk.read_bytes()).hexdigest()},
        "format": {
            "signature": "; ORG Skin file",
            "header_min_fields": 7,
            "record_min_fields": 9,
            "record_layout": ["component_id", "arg1", "arg2", "arg3", "arg4", "left", "top", "right", "bottom", "optional_9_to_17"],
        },
        "summary": {
            "file_count": len(files),
            "skin_count": sum(item["kind"] == "Skin" for item in files),
            "part_count": sum(item["kind"] == "Part" for item in files),
            "family_count": len(families),
            "record_count": sum(item["record_count"] for item in files),
            "distinct_component_ids": len(ids),
            "parse_error_count": len(errors),
        },
        "families": families,
        "component_id_frequency": dict(sorted(ids.items())),
        "files": files,
        "errors": errors,
    }
    output.write_text(json.dumps(report, ensure_ascii=False, indent=2) + "\n", encoding="utf-8")
    print(json.dumps(report["summary"], ensure_ascii=False))
    if errors:
        print("\n".join(errors), file=sys.stderr)
        return 1
    return 0


if __name__ == "__main__":
    raise SystemExit(main())
