import zipfile,io,pathlib
from elftools.elf.elffile import ELFFile
from capstone import *
z=zipfile.ZipFile('ORG+24_+Your+Music_2026.10.01_APKPure.apk'); e=ELFFile(io.BytesIO(z.read('lib/arm64-v8a/libnpitchshift.so'))); sec=e.get_section_by_name('.text'); d=Cs(CS_ARCH_ARM64,CS_MODE_ARM); start=0x1e50; end=0x1ed0; off=start-sec['sh_addr']; code=sec.data()[off:end-sec['sh_addr']]; ins=list(d.disasm(code,start)); pathlib.Path('analysis/native/npitchshift-helpers.asm').write_text('\n'.join(f'{i.address:08x}  {i.bytes.hex():8s}  {i.mnemonic:8s} {i.op_str}' for i in ins)+'\n'); print(len(code),len(ins))
