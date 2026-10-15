#!/usr/bin/env python3
import json
import math
import re
import struct
from pathlib import Path

root = Path(__file__).resolve().parent
inventory = json.loads((root / "native-sofeh-all-abi-disassembly.json").read_text(encoding="utf-8"))
classes = {
    "Java_sofeh_audio_NEqualizer_build": "floating-point-constant-pool",
    "Java_sofeh_audio_NGVerb_setroomsize": "floating-point-constant-pool",
    "Java_sofeh_music_NMusic_code": "padding-sentinel",
    "Java_sofeh_audio_NOpenSL_MixWrite": "integer-or-address-literal-pool",
    "Java_sofeh_music_NSF2_Voice": "integer-or-address-literal-pool",
}
rows = []
for item in inventory["functions"]:
    if item["abi"] != "armeabi-v7a" or item["decoded_bytes"] == item["symbol_bytes"]:
        continue
    asm = (root / item["output"]).read_text(encoding="utf-8")
    raw = bytes.fromhex(re.search(r"^# Raw hex: ([0-9a-f]+)$", asm, re.MULTILINE).group(1))
    words = [int.from_bytes(raw[i:i + 4], "little") for i in range(0, len(raw) - 3, 4)]
    floats = [struct.unpack("<f", raw[i:i + 4])[0] for i in range(0, len(raw) - 3, 4)]
    floats = [value if math.isfinite(value) else None for value in floats]
    rows.append({
        "symbol": item["symbol"],
        "tail_offset": item["decoded_bytes"],
        "tail_address": item["address"] + item["decoded_bytes"],
        "byte_count": len(raw),
        "classification": classes[item["symbol"]],
        "raw_hex": raw.hex(),
        "little_endian_u32": words,
        "little_endian_float32": floats,
    })

result = {"abi": "armeabi-v7a", "total_tail_bytes": sum(x["byte_count"] for x in rows), "regions": rows}
(root / "arm32-undecoded-tail-analysis.json").write_text(
    json.dumps(result, ensure_ascii=False, indent=2, allow_nan=False) + "\n", encoding="utf-8"
)
print(json.dumps({"regions": len(rows), "total_tail_bytes": result["total_tail_bytes"]}))
