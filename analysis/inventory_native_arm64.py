import zipfile,io,json
from pathlib import Path
from elftools.elf.elffile import ELFFile
apk=Path('ORG+24_+Your+Music_2026.10.01_APKPure.apk'); z=zipfile.ZipFile(apk); rows=[]
for n in sorted(x for x in z.namelist() if x.startswith('lib/arm64-v8a/') and x.endswith('.so')):
 b=z.read(n); e=ELFFile(io.BytesIO(b)); d=e.get_section_by_name('.dynsym'); syms=[s.name for s in d.iter_symbols() if s.name] if d else []; rows.append({'path':n,'size':len(b),'dynamic_symbol_count':len(syms),'jni_symbol_count':sum(s.startswith('Java_') for s in syms),'sample_symbols':syms[:20]})
Path('analysis/native-arm64-symbol-inventory.json').write_text(json.dumps({'file_count':len(rows),'files':rows},indent=2)+'\n'); print(json.dumps({'file_count':len(rows),'symbols':sum(r['dynamic_symbol_count'] for r in rows),'jni':sum(r['jni_symbol_count'] for r in rows)}))
