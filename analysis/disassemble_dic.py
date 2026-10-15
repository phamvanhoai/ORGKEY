from pathlib import Path
from elftools.elf.elffile import ELFFile
from capstone import Cs,CS_ARCH_X86,CS_MODE_32
p=Path('analysis/dic-decoded.bin'); e=ELFFile(p.open('rb')); sec=e.get_section_by_name('.text'); d=Cs(CS_ARCH_X86,CS_MODE_32); ins=list(d.disasm(sec.data(),sec['sh_addr'])); out=Path('analysis/native/dic-x86-text.asm'); out.write_text('\n'.join([f'# ELF: {p}; .text={sec["sh_size"]} bytes; entry={e.header["e_entry"]:#x}']+[f'{i.address:08x}  {i.bytes.hex():16s} {i.mnemonic:8s} {i.op_str}' for i in ins])+'\n'); print(sec['sh_size'],len(ins),hex(sec['sh_addr']),hex(e.header['e_entry']))
