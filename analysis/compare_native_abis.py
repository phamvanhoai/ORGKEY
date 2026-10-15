import zipfile,io,json
from pathlib import Path
from elftools.elf.elffile import ELFFile
z=zipfile.ZipFile('ORG+24_+Your+Music_2026.10.01_APKPure.apk'); result={}
for abi in ['arm64-v8a','armeabi-v7a','x86','x86_64']:
 result[abi]={}
 for n in sorted(x for x in z.namelist() if x.startswith('lib/'+abi+'/') and x.endswith('.so')):
  e=ELFFile(io.BytesIO(z.read(n))); d=e.get_section_by_name('.dynsym'); names=sorted(s.name for s in d.iter_symbols() if s.name.startswith('Java_sofeh_')) if d else []
  if names: result[abi][n.split('/')[-1]]=names
Path('analysis/native-abi-jni-comparison.json').write_text(json.dumps(result,indent=2)+'\n');
base=result['arm64-v8a']; print({a:{'libs':len(result[a]),'jni':sum(map(len,result[a].values())),'matches_arm64':result[a]==base} for a in result})
