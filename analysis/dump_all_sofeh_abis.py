#!/usr/bin/env python3
import hashlib
import io
import json
import sys
import zipfile
from pathlib import Path

from capstone import (
    CS_ARCH_ARM,
    CS_ARCH_ARM64,
    CS_ARCH_X86,
    CS_MODE_32,
    CS_MODE_64,
    CS_MODE_ARM,
    CS_MODE_LITTLE_ENDIAN,
    CS_MODE_THUMB,
    Cs,
)
from elftools.elf.elffile import ELFFile


ABIS = {
    "arm64-v8a": (CS_ARCH_ARM64, CS_MODE_ARM),
    "armeabi-v7a": (CS_ARCH_ARM, CS_MODE_ARM | CS_MODE_LITTLE_ENDIAN),
    "x86": (CS_ARCH_X86, CS_MODE_32),
    "x86_64": (CS_ARCH_X86, CS_MODE_64),
}


def disassemble_symbol(elf, symbol, arch, base_mode):
    address = symbol["st_value"]
    mode = base_mode
    if arch == CS_ARCH_ARM and address & 1:
        address &= ~1
        mode = CS_MODE_THUMB | CS_MODE_LITTLE_ENDIAN
    section = elf.get_section(symbol["st_shndx"])
    offset = address - section["sh_addr"]
    code = section.data()[offset : offset + symbol["st_size"]]
    decoder = Cs(arch, mode)
    instructions = list(decoder.disasm(code, address))
    decoded = sum(item.size for item in instructions)
    return address, code, instructions, decoded, "thumb" if mode & CS_MODE_THUMB else "default"


def main():
    apk = Path(sys.argv[1] if len(sys.argv) > 1 else "ORG+24_+Your+Music_2026.10.01_APKPure.apk")
    root = Path(__file__).resolve().parent
    output_root = root / "native" / "abis"
    rows = []
    with zipfile.ZipFile(apk) as archive:
        members = set(archive.namelist())
        for abi, (arch, mode) in ABIS.items():
            for member in sorted(x for x in members if x.startswith(f"lib/{abi}/") and x.endswith(".so")):
                blob = archive.read(member)
                elf = ELFFile(io.BytesIO(blob))
                symbols = elf.get_section_by_name(".dynsym")
                if symbols is None:
                    continue
                library = Path(member).name
                for symbol in symbols.iter_symbols():
                    if not symbol.name.startswith("Java_sofeh_") or symbol["st_size"] == 0:
                        continue
                    address, code, instructions, decoded, decoder_mode = disassemble_symbol(
                        elf, symbol, arch, mode
                    )
                    directory = output_root / abi
                    directory.mkdir(parents=True, exist_ok=True)
                    path = directory / f"{symbol.name}.asm"
                    lines = [
                        f"# APK member: {member}",
                        f"# Library SHA-256: {hashlib.sha256(blob).hexdigest()}",
                        f"# Function: {symbol.name}; address={address:#x}; bytes={len(code)}",
                        f"# Decoder mode: {decoder_mode}; decoded_bytes={decoded}",
                    ]
                    lines.extend(
                        f"{item.address:08x}  {item.bytes.hex():16s}  {item.mnemonic:10s} {item.op_str}"
                        for item in instructions
                    )
                    if decoded < len(code):
                        lines.extend(
                            [
                                f"# Undecoded tail: offset={decoded:#x}; bytes={len(code) - decoded}",
                                f"# Raw hex: {code[decoded:].hex()}",
                            ]
                        )
                    path.write_text("\n".join(lines) + "\n", encoding="utf-8")
                    rows.append(
                        {
                            "abi": abi,
                            "library": library,
                            "symbol": symbol.name,
                            "address": address,
                            "symbol_bytes": len(code),
                            "decoded_bytes": decoded,
                            "instruction_count": len(instructions),
                            "decoder_mode": decoder_mode,
                            "output": path.relative_to(root).as_posix(),
                        }
                    )
    rows.sort(key=lambda row: (row["abi"], row["library"], row["symbol"]))
    summary = {}
    for abi in ABIS:
        selected = [row for row in rows if row["abi"] == abi]
        summary[abi] = {
            "functions": len(selected),
            "symbol_bytes": sum(row["symbol_bytes"] for row in selected),
            "decoded_bytes": sum(row["decoded_bytes"] for row in selected),
            "instructions": sum(row["instruction_count"] for row in selected),
            "complete_functions": sum(
                row["symbol_bytes"] == row["decoded_bytes"] for row in selected
            ),
        }
    result = {"summary": summary, "functions": rows}
    (root / "native-sofeh-all-abi-disassembly.json").write_text(
        json.dumps(result, ensure_ascii=False, indent=2) + "\n", encoding="utf-8"
    )
    print(json.dumps(summary, ensure_ascii=False))


if __name__ == "__main__":
    main()
