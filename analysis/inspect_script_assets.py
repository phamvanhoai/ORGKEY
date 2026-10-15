"""Statically inventory the ORG 24 script examples in assets/data.zip."""

from __future__ import annotations

import argparse
import hashlib
import io
import json
import re
import zipfile
from collections import Counter
from pathlib import Path, PurePosixPath


ROOT = Path(__file__).resolve().parent.parent
DEFAULT_APK = ROOT / "ORG+24_+Your+Music_2026.10.01_APKPure.apk"
DEFAULT_OUTPUT = ROOT / "analysis" / "script-assets-inventory.json"

HANDLER_RE = re.compile(r"\bon\s*\(\s*([A-Za-z_][A-Za-z0-9_]*)\s*\)")
CALL_RE = re.compile(r"\b(play|stop|timer|setting|Playing|abs|Max)\s*\(", re.IGNORECASE)
PARAM_RE = re.compile(r"\b([A-Za-z_][A-Za-z0-9_]*)\s*:")
EFFECT_RE = re.compile(r"\beffect\s*\[\s*(\d+)\s*\]\.([A-Za-z_][A-Za-z0-9_]*)", re.IGNORECASE)
SETTING_RE = re.compile(r"\bsetting\s*\((.*?)\)\s*;", re.IGNORECASE | re.DOTALL)


def strip_comments(text: str) -> str:
    text = re.sub(r"/\*.*?\*/", "", text, flags=re.DOTALL)
    return re.sub(r"//[^\r\n]*", "", text)


def brace_balance(text: str) -> int:
    clean = re.sub(r'"(?:\\.|[^"\\])*"', '""', strip_comments(text))
    return clean.count("{") - clean.count("}")


def parse_setting(body: str) -> dict[str, object]:
    parts = [part.strip() for part in body.replace("\r", " ").replace("\n", " ").split(",")]
    result: dict[str, object] = {"type": parts[0] if parts else ""}
    for part in parts[1:]:
        if ":" not in part:
            continue
        key, value = (item.strip() for item in part.split(":", 1))
        if value.startswith('"') and value.endswith('"'):
            parsed: object = value[1:-1]
        elif value.lower() in ("true", "false"):
            parsed = value.lower() == "true"
        else:
            try:
                parsed = int(value)
            except ValueError:
                parsed = value
        result[key] = parsed
    return result


def inspect_script(path: str, data: bytes) -> dict[str, object]:
    text = data.decode("utf-8-sig")
    clean = strip_comments(text)
    handlers = HANDLER_RE.findall(clean)
    calls = [name.lower() for name in CALL_RE.findall(clean)]
    effects = sorted({f"{slot}.{prop}" for slot, prop in EFFECT_RE.findall(clean)})
    settings = [parse_setting(match) for match in SETTING_RE.findall(clean)]
    play_parameters = sorted({
        param.lower()
        for call in re.findall(r"\bplay\s*\((.*?)\)\s*;", clean, re.IGNORECASE | re.DOTALL)
        for param in PARAM_RE.findall(call)
    })
    return {
        "path": path,
        "category": PurePosixPath(path).parts[1] if len(PurePosixPath(path).parts) > 2 else "root",
        "name": PurePosixPath(path).stem,
        "size": len(data),
        "lines": len(text.splitlines()),
        "sha256": hashlib.sha256(data).hexdigest(),
        "empty": not bool(clean.strip()),
        "brace_balance": brace_balance(text),
        "handlers": handlers,
        "has_main": bool(re.search(r"\bmain\s*\(", clean)),
        "calls": dict(sorted(Counter(calls).items())),
        "play_parameters": play_parameters,
        "effect_properties": effects,
        "settings": settings,
        "returns_true": bool(re.search(r"\breturn\s*\(\s*true\s*\)", clean, re.IGNORECASE)),
        "uses_exit": bool(re.search(r"\bexit\s*;", clean, re.IGNORECASE)),
    }


def main() -> None:
    parser = argparse.ArgumentParser()
    parser.add_argument("apk", nargs="?", type=Path, default=DEFAULT_APK)
    parser.add_argument("-o", "--output", type=Path, default=DEFAULT_OUTPUT)
    args = parser.parse_args()

    with zipfile.ZipFile(args.apk) as apk:
        nested = apk.read("assets/data.zip")
    with zipfile.ZipFile(io.BytesIO(nested)) as archive:
        scripts = [
            inspect_script(info.filename, archive.read(info))
            for info in archive.infolist()
            if info.filename.endswith(".Script")
        ]

    handler_counts = Counter(handler for item in scripts for handler in item["handlers"])
    call_counts = Counter()
    for item in scripts:
        call_counts.update(item["calls"])
    result = {
        "source": str(args.apk),
        "asset": "assets/data.zip",
        "script_count": len(scripts),
        "empty_count": sum(bool(item["empty"]) for item in scripts),
        "balanced_braces": sum(item["brace_balance"] == 0 for item in scripts),
        "category_counts": dict(sorted(Counter(item["category"] for item in scripts).items())),
        "handler_counts": dict(sorted(handler_counts.items())),
        "call_counts": dict(sorted(call_counts.items())),
        "setting_count": sum(len(item["settings"]) for item in scripts),
        "scripts": scripts,
    }
    args.output.write_text(json.dumps(result, ensure_ascii=False, indent=2) + "\n", encoding="utf-8")
    print(f"wrote {args.output}: {len(scripts)} scripts, {result['setting_count']} settings")


if __name__ == "__main__":
    main()
