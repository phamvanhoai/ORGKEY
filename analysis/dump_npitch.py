import zipfile,io,pathlib,hashlib
from elftools.elf.elffile import ELFFile
from capstone import *
z=zipfile.ZipFile('ORG+24_+Your+Music_2026.10.01_APKPure.apk'); b=z.read('lib/arm64-v8a/libnpitchshift.so'); e=ELFFile(io.BytesIO(b)); sy=e.get_section_by_name('.dynsym'); d=Cs(CS_ARCH_ARM64,CS_MODE_ARM); out=pathlib.Path('analysis/native'); out.mkdir(exist_ok=True)
for n in ['smbPitchShift','Java_sofeh_audio_NPitchShift_open','Java_sofeh_audio_NPitchShift_process','Java_sofeh_audio_NPitchShift_processLeft','Java_sofeh_audio_NPitchShift_processRight','smbPitchShiftSample','smbFft','smbPitchShiftNew','smbPitchShiftFlush','smbPitchShiftCopy','Java_sofeh_audio_NPitchShift_close']:
 s=next(x for x in sy.iter_symbols() if x.name==n); sec=e.get_section(s['st_shndx']); off=s['st_value']-sec['sh_addr']; code=sec.data()[off:off+s['st_size']]; lines=['# APK member: lib/arm64-v8a/libnpitchshift.so',f'# Library SHA-256: {hashlib.sha256(b).hexdigest()}',f'# Function: {n}; address={s["st_value"]:#x}; bytes={len(code)}']; ins=list(d.disasm(code,s['st_value'])); lines += [f'{i.address:08x}  {i.bytes.hex():8s}  {i.mnemonic:8s} {i.op_str}' for i in ins]; (out/(n+'.asm')).write_text('\n'.join(lines)+'\n'); print(n,len(code),len(ins))


