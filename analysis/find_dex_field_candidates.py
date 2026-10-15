"""Locate candidate DEX field references; code-unit scan is not instruction decoding."""
import json
from pathlib import Path
import struct
import sys
import zipfile

rows = []
with zipfile.ZipFile(sys.argv[1]) as archive:
    for entry in archive.namelist():
        if not (entry.startswith('classes') and entry.endswith('.dex')):
            continue
        data = archive.read(entry)
        def u32(offset):
            return struct.unpack_from('<I', data, offset)[0]
        def leb(offset):
            value = shift = 0
            while True:
                byte = data[offset]
                offset += 1
                value |= (byte & 127) << shift
                if byte < 128:
                    return value, offset
                shift += 7
        strings = []
        for index in range(u32(56)):
            offset = u32(u32(60) + index * 4)
            _, offset = leb(offset)
            strings.append(data[offset:data.index(0, offset)].decode('utf-8', errors='replace'))
        types = [strings[u32(u32(68) + index * 4)] for index in range(u32(64))]
        fields = []
        for index in range(u32(80)):
            owner, kind, name = struct.unpack_from('<HHI', data, u32(84) + index * 8)
            fields.append((types[owner], strings[name]))
        wanted = {index for index, field in enumerate(fields)
                  if field == ('Lsofeh/music/Music;', 'fLowLatencyMethod')}
        methods = []
        for index in range(u32(88)):
            owner, proto, name = struct.unpack_from('<HHI', data, u32(92) + index * 8)
            methods.append((types[owner], strings[name], proto))
        for index in range(u32(96)):
            offset = u32(u32(100) + index * 32 + 24)
            if not offset:
                continue
            counts = []
            for _ in range(4):
                count, offset = leb(offset)
                counts.append(count)
            for _ in range(counts[0] + counts[1]):
                _, offset = leb(offset)
                _, offset = leb(offset)
            for count in counts[2:]:
                method_index = 0
                for _ in range(count):
                    delta, offset = leb(offset)
                    method_index += delta
                    _, offset = leb(offset)
                    code, offset = leb(offset)
                    if not code:
                        continue
                    units = struct.unpack_from('<' + 'H' * u32(code + 12), data, code + 16)
                    for position in range(len(units) - 1):
                        opcode = units[position] & 255
                        if opcode in (0x52, 0x59, 0x60, 0x67) and units[position + 1] in wanted:
                            owner, name, proto = methods[method_index]
                            rows.append(dict(dex=entry, owner=owner, method=name, proto_index=proto,
                                             code_unit=position, opcode=hex(opcode),
                                             status='candidate_requires_instruction_verification'))
output = Path(__file__).with_name('latency-field-candidates.json')
output.write_text(json.dumps(rows, indent=2), encoding='utf-8')
print(json.dumps(rows, indent=2))
