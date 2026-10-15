"""Read the common header used by bundled Music Studio files.

This intentionally stops after MusicObject.ReadFromStream's common fields.  It
does not try to deserialize the version-dependent Music/Instrument payload.
"""

from __future__ import annotations

import json
import struct
import zipfile
from pathlib import Path


ROOT = Path(__file__).resolve().parent.parent
APK = ROOT / "ORG+24_+Your+Music_2026.10.01_APKPure.apk"
TARGETS = (
    "assets/Default.Template",
    "assets/empty.Template",
    "assets/Constant.Instrument",
    "assets/Sound.Instrument",
)


def parse_common_header(data: bytes, path: str) -> dict[str, object]:
    if len(data) < 40:
        raise ValueError(f"{path}: file is too short")

    signature_raw = data[:32]
    expected = b"Music Studio "
    if not signature_raw.startswith(expected):
        raise ValueError(f"{path}: unexpected signature")

    string_length = struct.unpack_from("<i", data, 32)[0]
    if string_length < 0 or 36 + string_length + 4 > len(data):
        raise ValueError(f"{path}: invalid metadata length {string_length}")

    metadata_raw = data[36 : 36 + string_length]
    # Java uses the platform default charset here. These bundled values are ASCII.
    metadata = metadata_raw.decode("ascii")
    parts = metadata.split("\r")
    group_offset = 36 + string_length
    group = struct.unpack_from("<i", data, group_offset)[0]

    return {
        "path": path,
        "size": len(data),
        "signature_prefix": signature_raw.split(b"\0", 1)[0].decode("ascii"),
        "metadata_length": string_length,
        "metadata_hex": metadata_raw.hex(),
        "name": parts[0] if parts else "",
        "version": parts[1] if len(parts) > 1 else "",
        "description": parts[2] if len(parts) > 2 else "",
        "group": group,
        "payload_offset": group_offset + 4,
    }


def main() -> None:
    results = []
    with zipfile.ZipFile(APK) as archive:
        for target in TARGETS:
            results.append(parse_common_header(archive.read(target), target))
    print(json.dumps(results, indent=2, ensure_ascii=False))


if __name__ == "__main__":
    main()
