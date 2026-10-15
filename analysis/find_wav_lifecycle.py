"""Find exact DEX instruction references for WAV lifecycle research."""
import hashlib
import json
from pathlib import Path
import sys
import zipfile
from loguru import logger
logger.remove()
from androguard.core.dex import DEX

destination = Path(__file__).with_name('dex-lifecycle')
destination.mkdir(exist_ok=True)
matches = []
with zipfile.ZipFile(sys.argv[1]) as archive:
    for entry in ('classes.dex', 'classes2.dex', 'classes3.dex'):
        data = archive.read(entry)
        dex = DEX(data)
        for cls in dex.get_classes():
            for method in cls.get_methods():
                if not method.get_code():
                    continue
                decoded = [(address, instruction.get_name(), instruction.get_output(), instruction.get_raw().hex())
                           for address, instruction in method.get_instructions_idx()]
                hits = [dict(address=hex(address), opcode=opcode, operands=operands)
                        for address, opcode, operands, raw in decoded
                        if ((cls.get_name() == 'Lb23;' and method.get_name() == 'onLoadComplete') or
                            'Lsofeh/android/MainActivity;->f4 ' in operands or
                            'Lsofeh/android/MainActivity;->Q0(' in operands or
                            ('Landroid/media/SoundPool;->' in operands and
                             any(term in operands for term in ('setOnLoadCompleteListener', '<init>', 'release('))) or
                            'Landroid/media/SoundPool$Builder;->build(' in operands or
                            (cls.get_name() in ('Lb60;', 'Lr03;') and
                             any(term in operands for term in ('->delete(', '->unload(', '->load(', '->release('))))]
                if hits:
                    name = cls.get_name() + '->' + method.get_name() + method.get_descriptor()
                    matches.append(dict(dex=entry, method=name, hits=hits))
                    filename = f'method-{len(matches):03d}.dexasm'
                    lines = [f'# {entry} SHA-256: {hashlib.sha256(data).hexdigest()}', '# ' + name,
                             '# Addresses in bytes; branch deltas in code units.']
                    lines += [f'{address:06x} {raw:24s} {opcode:24s} {operands}'
                              for address, opcode, operands, raw in decoded]
                    (destination / filename).write_text('\n'.join(lines)+'\n', encoding='utf-8')
                    matches[-1]['file'] = filename
(destination / 'index.json').write_text(json.dumps(matches, indent=2), encoding='utf-8')
print(json.dumps(matches, indent=2))
