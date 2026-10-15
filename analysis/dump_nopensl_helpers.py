#!/usr/bin/env python3
import hashlib
import io
import sys
import zipfile
from pathlib import Path

from capstone import CS_ARCH_ARM64, CS_MODE_ARM, Cs
from elftools.elf.elffile import ELFFile


APK_MEMBER = "lib/arm64-v8a/libnopensl.so"
RANGES = {
    "nopensl-teardown-2448.asm": (0x2448, 0x2574, "teardown helper"),
    "nopensl-buffer-callback-2574.asm": (0x2574, 0x28C8, "Java-assisted buffer callback"),
    "nopensl-buffer-callback-28c8.asm": (0x28C8, 0x2A14, "native buffer callback"),
}


def main() -> None:
    apk = Path(sys.argv[1] if len(sys.argv) > 1 else "ORG+24_+Your+Music_2026.10.01_APKPure.apk")
    output = Path(__file__).resolve().parent / "native"
    output.mkdir(exist_ok=True)
    with zipfile.ZipFile(apk) as archive:
        blob = archive.read(APK_MEMBER)
    elf = ELFFile(io.BytesIO(blob))
    text = elf.get_section_by_name(".text")
    disassembler = Cs(CS_ARCH_ARM64, CS_MODE_ARM)
    digest = hashlib.sha256(blob).hexdigest()
    for filename, (start, end, label) in RANGES.items():
        offset = start - text["sh_addr"]
        code = text.data()[offset : offset + end - start]
        instructions = list(disassembler.disasm(code, start))
        if sum(item.size for item in instructions) != len(code):
            raise RuntimeError(f"Incomplete disassembly for {filename}")
        lines = [
            f"# APK member: {APK_MEMBER}",
            f"# Library SHA-256: {digest}",
            f"# Internal range: {label}; address={start:#x}..{end:#x}; bytes={len(code)}",
            "# Names describe observed behavior; the ELF has no dynamic symbol for this range.",
        ]
        lines.extend(
            f"{item.address:08x}  {item.bytes.hex():8s}  {item.mnemonic:8s} {item.op_str}"
            for item in instructions
        )
        (output / filename).write_text("\n".join(lines) + "\n", encoding="utf-8")
        print(f"{filename}: {len(code)} bytes, {len(instructions)} instructions")


if __name__ == "__main__":
    main()
