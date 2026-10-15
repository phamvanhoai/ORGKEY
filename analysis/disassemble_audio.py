"""Extract static ARM64 function disassembly; never load or execute APK code."""
from pathlib import Path
import hashlib
import io
import re
import sys
import zipfile

from capstone import Cs, CS_ARCH_ARM64, CS_MODE_ARM
from elftools.elf.elffile import ELFFile

destination = Path(__file__).with_name('native')
destination.mkdir(exist_ok=True)
targets = {
    'libnmusic.so': ['Java_sofeh_music_NMusic_ChannelProcess'],
    'libnopensl.so': ['Java_sofeh_audio_NOpenSL_Put', 'Java_sofeh_audio_NOpenSL_MixPut',
                     'Java_sofeh_audio_NOpenSL_Start', 'Java_sofeh_audio_NOpenSL_Write'],
}
with zipfile.ZipFile(sys.argv[1]) as archive:
    for library, names in targets.items():
        member = 'lib/arm64-v8a/' + library
        data = archive.read(member)
        elf = ELFFile(io.BytesIO(data))
        dynsym = elf.get_section_by_name('.dynsym')
        symbols = {symbol.name: symbol for symbol in dynsym.iter_symbols()}
        address_names = {symbol['st_value']: symbol.name for symbol in symbols.values()
                         if symbol['st_value'] and symbol.name}
        decoder = Cs(CS_ARCH_ARM64, CS_MODE_ARM)
        imports = {}
        for relocation_section in elf.iter_sections():
            if relocation_section['sh_type'] not in ('SHT_RELA', 'SHT_REL'):
                continue
            table = elf.get_section(relocation_section['sh_link'])
            for relocation in relocation_section.iter_relocations():
                if relocation['r_info_sym']:
                    imports[relocation['r_offset']] = table.get_symbol(relocation['r_info_sym']).name
        plt = elf.get_section_by_name('.plt')
        if plt:
            instructions = list(decoder.disasm(plt.data(), plt['sh_addr']))
            for index, instruction in enumerate(instructions[:-1]):
                page = re.fullmatch(r'x16, #(0x[0-9a-f]+)', instruction.op_str)
                load = re.fullmatch(r'x17, \[x16(?:, #(0x[0-9a-f]+))?\]', instructions[index + 1].op_str)
                if instruction.mnemonic == 'adrp' and page and instructions[index + 1].mnemonic == 'ldr' and load:
                    got = int(page[1], 0) + (int(load[1], 0) if load[1] else 0)
                    if got in imports:
                        address_names[instruction.address] = imports[got] + '@plt'
        (destination / (library + '-plt.txt')).write_text(
            '\n'.join(f'{address:08x} {label}' for address, label in sorted(address_names.items())
                      if label.endswith('@plt')) + '\n', encoding='utf-8')
        strings = []
        for section in elf.iter_sections():
            if section.name in ('.rodata', '.dynstr'):
                for match in re.finditer(rb'[\x20-\x7e]{2,}', section.data()):
                    strings.append(f'{section["sh_addr"] + match.start():08x}  '
                                   + match.group().decode('ascii'))
        (destination / (library + '-strings.txt')).write_text('\n'.join(strings) + '\n', encoding='utf-8')
        if library == 'libnopensl.so':
            text_section = elf.get_section_by_name('.text')
            full_text = []
            for instruction in decoder.disasm(text_section.data(), text_section['sh_addr']):
                note = ''
                if instruction.mnemonic in ('bl', 'b') and instruction.op_str.startswith('#'):
                    label = address_names.get(int(instruction.op_str[1:], 0))
                    note = ' ; ' + label if label else ''
                full_text.append(f'{instruction.address:08x}  {instruction.bytes.hex():8s}  '
                                 f'{instruction.mnemonic:8s} {instruction.op_str}{note}')
            (destination / (library + '-text.asm')).write_text('\n'.join(full_text) + '\n', encoding='utf-8')
        for name in names:
            symbol = symbols[name]
            section = elf.get_section(symbol['st_shndx'])
            start = symbol['st_value'] - section['sh_addr']
            code = section.data()[start:start + symbol['st_size']]
            if len(code) != symbol['st_size']:
                raise ValueError('Incomplete function bytes: ' + name)
            lines = [f'# APK member: {member}', f'# Library SHA-256: {hashlib.sha256(data).hexdigest()}',
                     f'# Function: {name}; address={symbol["st_value"]:#x}; bytes={len(code)}',
                     '# ELF virtual addresses, not runtime absolute addresses. Indirect calls unresolved.']
            decoded_bytes = 0
            for instruction in decoder.disasm(code, symbol['st_value']):
                note = ''
                if instruction.mnemonic in ('bl', 'b') and instruction.op_str.startswith('#'):
                    target = int(instruction.op_str[1:], 0)
                    if target in address_names:
                        note = ' ; ' + address_names[target]
                lines.append(f'{instruction.address:08x}  {instruction.bytes.hex():8s}  '
                             f'{instruction.mnemonic:8s} {instruction.op_str}{note}')
                decoded_bytes += instruction.size
            if decoded_bytes != len(code):
                raise ValueError('Disassembly did not cover entire function: ' + name)
            path = destination / (name + '.asm')
            path.write_text('\n'.join(lines) + '\n', encoding='utf-8')
            print(f'{name}: {decoded_bytes // 4} instructions, {decoded_bytes} bytes')
