"""Read ARM64 ELF dynamic symbols from the supplied APK without executing code."""
import csv
import hashlib
from pathlib import Path
import struct
import sys
import zipfile

apk = Path(sys.argv[1])
output = Path(__file__).with_name('native-dynamic-symbols.csv')
rows = []
with zipfile.ZipFile(apk) as archive:
    for name in ('lib/arm64-v8a/libnmusic.so', 'lib/arm64-v8a/libnopensl.so'):
        data = archive.read(name)
        if data[:6] != b'\x7fELF\x02\x01':
            raise ValueError(f'Expected little-endian ELF64: {name}')
        header = struct.unpack_from('<16sHHIQQQIHHHHHH', data)
        section_offset, section_size, section_count = header[6], header[11], header[12]
        sections = [struct.unpack_from('<IIQQQQIIQQ', data, section_offset + i * section_size)
                    for i in range(section_count)]
        digest = hashlib.sha256(data).hexdigest()
        for section in sections:
            if section[1] != 11:  # SHT_DYNSYM
                continue
            strings = sections[section[6]]
            table = data[strings[4]:strings[4] + strings[5]]
            for offset in range(section[4], section[4] + section[5], section[9]):
                symbol, info, visibility, index, value, size = struct.unpack_from('<IBBHQQ', data, offset)
                end = table.find(b'\0', symbol)
                label = table[symbol:end].decode('utf-8', errors='replace')
                if label.startswith(('Java_', 'SL_IID_')) or label == 'slCreateEngine':
                    rows.append(dict(library=name, sha256=digest, symbol=label,
                                     defined=index != 0, type=info & 15, binding=info >> 4,
                                     address=hex(value), size=size))
with output.open('w', encoding='utf-8', newline='') as target:
    writer = csv.DictWriter(target, fieldnames=['library', 'sha256', 'symbol', 'defined',
                                               'type', 'binding', 'address', 'size'])
    writer.writeheader()
    writer.writerows(rows)
print(f'{len(rows)} selected dynamic symbols saved to {output}')
