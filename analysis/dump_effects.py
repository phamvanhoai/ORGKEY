import zipfile,io,pathlib,hashlib
from elftools.elf.elffile import ELFFile
from capstone import *
z=zipfile.ZipFile('ORG+24_+Your+Music_2026.10.01_APKPure.apk'); d=Cs(CS_ARCH_ARM64,CS_MODE_ARM); out=pathlib.Path('analysis/native'); out.mkdir(exist_ok=True)
targets={'libnequalizer.so':['Java_sofeh_audio_NEqualizer_process','Java_sofeh_audio_NEqualizer_processLeft','Java_sofeh_audio_NEqualizer_processRight','Java_sofeh_audio_NEqualizer_build'],'libngverb.so':['Java_sofeh_audio_NGVerb_processStereoLeft','Java_sofeh_audio_NGVerb_processMono','Java_sofeh_audio_NGVerb_setroomsize','Java_sofeh_audio_NGVerb_setrevtime'],'libnflanger.so':['Java_sofeh_audio_NFlanger_process','Java_sofeh_audio_NFlanger_processLeft','Java_sofeh_audio_NFlanger_processRight','Java_sofeh_audio_NFlanger_build'],'libnbandbooster.so':['Java_sofeh_audio_NBandBooster_process','Java_sofeh_audio_NBandBooster_build'],'libnsf2.so':['Java_sofeh_music_NSF2_Voice','Java_sofeh_music_NSF2_Open','Java_sofeh_music_NSF2_PresetName'],'libnlame.so':['Java_sofeh_audio_Lame_encode','Java_sofeh_audio_Lame_init','Java_sofeh_audio_Lame_flush','Java_sofeh_audio_Lame_close']}
for lib,names in targets.items():
 b=z.read('lib/arm64-v8a/'+lib); e=ELFFile(io.BytesIO(b)); sy=e.get_section_by_name('.dynsym')
 for n in names:
  s=next(x for x in sy.iter_symbols() if x.name==n); sec=e.get_section(s['st_shndx']); off=s['st_value']-sec['sh_addr']; code=sec.data()[off:off+s['st_size']]; ins=list(d.disasm(code,s['st_value'])); lines=[f'# Function: {n}; bytes={len(code)}']+[f'{i.address:08x}  {i.bytes.hex():8s}  {i.mnemonic:8s} {i.op_str}' for i in ins]; (out/(n+'.asm')).write_text('\n'.join(lines)+'\n'); print(n,len(code),len(ins))


