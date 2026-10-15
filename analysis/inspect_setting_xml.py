"""Read Android binary XML string pool and typed attributes without executing APK."""
import json
from pathlib import Path
import struct
import sys
import zipfile

def pool(data, offset):
    _, header, size, count, styles, flags, start, style_start = struct.unpack_from('<HHIIIIII', data, offset)
    def length(position, wide=False):
        if wide:
            first = struct.unpack_from('<H', data, position)[0]
            return (((first & 32767) << 16) | struct.unpack_from('<H', data, position+2)[0], position+4) if first & 32768 else (first, position+2)
        first = data[position]
        return (((first & 127) << 8) | data[position+1], position+2) if first & 128 else (first, position+1)
    values = []
    for index in range(count):
        pos = offset + start + struct.unpack_from('<I', data, offset + header + 4*index)[0]
        if flags & 256:
            _, pos = length(pos)
            size, pos = length(pos)
            values.append(data[pos:pos+size].decode('utf-8'))
        else:
            size, pos = length(pos, True)
            values.append(data[pos:pos+size*2].decode('utf-16le'))
    return values

with zipfile.ZipFile(sys.argv[1]) as archive:
    data = archive.read('res/tN.xml')
offset = 8
strings = []
rows = []
while offset < len(data):
    kind, header, size = struct.unpack_from('<HHI', data, offset)
    if size < 8 or offset+size > len(data):
        raise ValueError('Invalid chunk size')
    if kind == 1:
        strings = pool(data, offset)
    elif kind == 0x102:
        ext = offset + header
        namespace, name, attr_start, attr_size, count = struct.unpack_from('<IIHHH', data, ext)
        attributes = {}
        for index in range(count):
            pos = ext + attr_start + index*attr_size
            ns, key, raw, value_size, zero, dtype, value = struct.unpack_from('<IIIHBBI', data, pos)
            attributes[strings[key]] = (strings[raw] if raw != 0xffffffff else
                                       strings[value] if dtype == 3 else
                                       {'type': hex(dtype), 'value': hex(value)})
        rows.append({'tag': strings[name], 'attributes': attributes})
    offset += size
output = Path(__file__).with_name('audio-preferences-xml.json')
output.write_text(json.dumps(rows, ensure_ascii=False, indent=2), encoding='utf-8')
for row in rows:
    if 'output_' in str(row):
        print(json.dumps(row, ensure_ascii=False, indent=2))

# Resolve ordinary, non-sparse resource entries referenced by this preference XML.
with zipfile.ZipFile(sys.argv[1]) as archive:
    data = archive.read('resources.arsc')
global_strings = pool(data, 12)
resolved = {}
def value_at(pos):
    _, _, dtype, value = struct.unpack_from('<HBBI', data, pos)
    return global_strings[value] if dtype == 3 else {'type': hex(dtype), 'value': hex(value)}
offset = 12
while offset < len(data):
    kind, header, size = struct.unpack_from('<HHI', data, offset)
    if kind == 0x200:
        package_id = struct.unpack_from('<I', data, offset+8)[0]
        child = offset + header
        while child < offset + size:
            ckind, cheader, csize = struct.unpack_from('<HHI', data, child)
            if ckind == 0x201:
                type_id, flags, _, count, start = struct.unpack_from('<BBHII', data, child+8)
                if flags:
                    raise ValueError('Sparse/offset16 resource table needs explicit support')
                config = data[child+20:child+cheader]
                if not any(config[4:]):  # default configuration only
                    for index in range(count):
                        relative = struct.unpack_from('<I', data, child+cheader+4*index)[0]
                        if relative == 0xffffffff:
                            continue
                        pos = child+start+relative
                        entry_size, entry_flags, key = struct.unpack_from('<HHI', data, pos)
                        resource_id = (package_id << 24) | (type_id << 16) | index
                        if entry_flags & 1:
                            _, map_count = struct.unpack_from('<II', data, pos+8)
                            value = [value_at(pos+entry_size+12*j+4) for j in range(map_count)]
                        else:
                            value = value_at(pos+entry_size)
                        resolved[hex(resource_id)] = value
            if csize < 8:
                raise ValueError('Invalid resource child')
            child += csize
    offset += size
def resolve(value, depth=0):
    if depth > 10:
        return value
    if isinstance(value, dict) and value.get('type') == '0x1':
        target = resolved.get(value['value'], value)
        return resolve(target, depth+1) if target is not value else value
    if isinstance(value, list):
        return [resolve(item, depth+1) for item in value]
    return value
preferences = [dict(tag=row['tag'], attributes={key: resolve(value) for key, value in row['attributes'].items()})
               for row in rows if row['attributes'].get('key', '').startswith('output_')]
Path(__file__).with_name('audio-preferences-resolved.json').write_text(
    json.dumps(preferences, ensure_ascii=False, indent=2), encoding='utf-8')
print(json.dumps(preferences, ensure_ascii=False, indent=2))
