import zipfile,io,pathlib
from elftools.elf.elffile import ELFFile
from capstone import *
z=zipfile.ZipFile('ORG+24_+Your+Music_2026.10.01_APKPure.apk'); out=pathlib.Path('analysis/native'); d=Cs(CS_ARCH_ARM64,CS_MODE_ARM); count=0
libs=['libnbandbooster.so','libnequalizer.so','libnflanger.so','libngverb.so','libnlame.so','libnmusic.so','libnopensl.so','libnpitchshift.so','libnsf2.so']
for lib in libs:
 b=z.read('lib/arm64-v8a/'+lib); e=ELFFile(io.BytesIO(b)); sy=e.get_section_by_name('.dynsym')
 for s in sy.iter_symbols():
  if not s.name.startswith('Java_sofeh_'): continue
  path=out/(s.name+'.asm')
  if path.exists(): continue
  sec=e.get_section(s['st_shndx']); off=s['st_value']-sec['sh_addr']; code=sec.data()[off:off+s['st_size']]; ins=list(d.disasm(code,s['st_value'])); path.write_text('\n'.join([f'# Library: {lib}',f'# Function: {s.name}; bytes={len(code)}']+[f'{i.address:08x}  {i.bytes.hex():8s}  {i.mnemonic:8s} {i.op_str}' for i in ins])+'\n'); print(lib,s.name,len(ins)); count+=1
print('created',count)
