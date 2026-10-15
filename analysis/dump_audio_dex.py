"""Decode selected DEX methods at instruction boundaries; never execute APK."""
import hashlib
from pathlib import Path
import sys
import zipfile
from loguru import logger
logger.remove()
from androguard.core.dex import DEX

targets = {
    'Lb23;': {'onLoadComplete'},
    'Lsofeh/android/MainActivity;': {'<init>', 'O0', 'Q0', 'onCreate', 'onDestroy'},
    'Lsofeh/music/Music;': {'<init>', 'lowLatencyInstruments', 'ExportKeyToAudioFile'},
    'Lsofeh/music/Channels;': {'OpenKey'},
}
output = Path(__file__).with_name('dex')
output.mkdir(exist_ok=True)
with zipfile.ZipFile(sys.argv[1]) as archive:
    data = archive.read('classes3.dex')
dex = DEX(data)
count = 0
for cls in dex.get_classes():
    if cls.get_name() not in targets:
        continue
    for method in cls.get_methods():
        if method.get_name() not in targets[cls.get_name()] or not method.get_code():
            continue
        descriptor = method.get_descriptor()
        code = method.get_code()
        lines = [f'# classes3.dex SHA-256: {hashlib.sha256(data).hexdigest()}',
                 f'# {cls.get_name()}->{method.get_name()}{descriptor}',
                 f'# registers={code.get_registers_size()} incoming_words={code.get_ins_size()}',
                 '# Addresses are BYTES; branch deltas printed by decoder are CODE UNITS (2 bytes).']
        for address, instruction in method.get_instructions_idx():
            lines.append(f'{address:06x}  {instruction.get_raw().hex():24s} '
                         f'{instruction.get_name():24s} {instruction.get_output()}')
        name = cls.get_name().split('/')[-1].rstrip(';') + '-' + method.get_name().replace('<', '').replace('>', '')
        path = output / (name + f'-{count}.dexasm')
        path.write_text('\n'.join(lines) + '\n', encoding='utf-8')
        print(f'{path.name}: {descriptor}')
        count += 1
