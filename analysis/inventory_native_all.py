#!/usr/bin/env python3
import csv
import hashlib
import io
import json
import sys
import zipfile
from pathlib import Path

from elftools.elf.elffile import ELFFile


SOFEH = {
    "libnbandbooster.so", "libnequalizer.so", "libnflanger.so", "libngverb.so",
    "libnlame.so", "libnmusic.so", "libnopensl.so", "libnpitchshift.so", "libnsf2.so",
}
PANGLE = {
    "libbuffer_pgl.so", "libfile_lock_pgl.so", "libpglarmor.so", "libnms.so",
    "libapminsighta.so", "libapminsightb.so", "libtt_ugen_layout.so",
}


def owner(name):
    if name in SOFEH:
        return "ORG/Sofeh audio"
    if name == "libdatastore_shared_counter.so":
        return "AndroidX DataStore"
    if name == "libapplovin-native-crash-reporter.so":
        return "AppLovin"
    if name in PANGLE:
        return "Pangle/ByteDance"
    return "unclassified"


def main():
    apk = Path(sys.argv[1] if len(sys.argv) > 1 else "ORG+24_+Your+Music_2026.10.01_APKPure.apk")
    root = Path(__file__).resolve().parent
    rows = []
    with zipfile.ZipFile(apk) as archive:
        for member in sorted(x for x in archive.namelist() if x.startswith("lib/") and x.endswith(".so")):
            raw = archive.read(member)
            elf = ELFFile(io.BytesIO(raw))
            dynsym = elf.get_section_by_name(".dynsym")
            symbols = list(dynsym.iter_symbols()) if dynsym else []
            dynamic = elf.get_section_by_name(".dynamic")
            needed = []
            soname = None
            if dynamic:
                for tag in dynamic.iter_tags():
                    if tag.entry.d_tag == "DT_NEEDED":
                        needed.append(tag.needed)
                    elif tag.entry.d_tag == "DT_SONAME":
                        soname = tag.soname
            jni = sorted(s.name for s in symbols if s.name.startswith("Java_"))
            imports = sorted(s.name for s in symbols if s.name and s["st_shndx"] == "SHN_UNDEF")
            exports = sorted(s.name for s in symbols if s.name and s["st_shndx"] != "SHN_UNDEF")
            parts = member.split("/")
            name = parts[-1]
            rows.append({
                "member": member,
                "abi": parts[1],
                "library": name,
                "owner": owner(name),
                "file_bytes": len(raw),
                "sha256": hashlib.sha256(raw).hexdigest(),
                "elf_class": elf.elfclass,
                "machine": elf["e_machine"],
                "soname": soname,
                "dynamic_symbols": len(symbols),
                "defined_symbols": len(exports),
                "undefined_symbols": len(imports),
                "jni_count": len(jni),
                "jni_symbols": jni,
                "needed": needed,
                "imports": imports,
                "exports": exports,
            })
    by_owner = {}
    by_abi = {}
    for row in rows:
        by_owner[row["owner"]] = by_owner.get(row["owner"], 0) + 1
        by_abi[row["abi"]] = by_abi.get(row["abi"], 0) + 1
    result = {
        "apk": apk.name,
        "summary": {"libraries": len(rows), "by_abi": by_abi, "by_owner": by_owner},
        "libraries": rows,
    }
    (root / "native-all-library-inventory.json").write_text(
        json.dumps(result, ensure_ascii=False, indent=2) + "\n", encoding="utf-8"
    )
    with (root / "native-all-library-summary.csv").open("w", newline="", encoding="utf-8") as handle:
        writer = csv.writer(handle)
        writer.writerow(["abi", "library", "owner", "file_bytes", "sha256", "elf_class", "machine",
                         "dynamic_symbols", "defined_symbols", "undefined_symbols", "jni_count", "needed"])
        for row in rows:
            writer.writerow([row[k] for k in ("abi", "library", "owner", "file_bytes", "sha256", "elf_class",
                                               "machine", "dynamic_symbols", "defined_symbols", "undefined_symbols",
                                               "jni_count")] + [";".join(row["needed"])])
    print(json.dumps(result["summary"], ensure_ascii=False))


if __name__ == "__main__":
    main()
