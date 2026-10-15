#!/usr/bin/env python3
import io,json,zipfile,hashlib
from pathlib import Path
apk=Path(__file__).resolve().parent.parent/'ORG+24_+Your+Music_2026.10.01_APKPure.apk'
outer=zipfile.ZipFile(apk); raw=outer.read('assets/data.zip'); inner=zipfile.ZipFile(io.BytesIO(raw)); rows=[]; errors=[]
for name in sorted(n for n in inner.namelist() if n.startswith('Chord/') and n.endswith('.Chord')):
 b=inner.read(name)
 try: vals=[int(x) for x in b.decode('ascii').split()]
 except Exception as e: errors.append(f'{name}: {e}'); continue
 rows.append({'path':name,'size':len(b),'sha256':hashlib.sha256(b).hexdigest(),'mask_count':len(vals),'masks':vals})
out={'asset':'assets/data.zip!/Chord','summary':{'file_count':len(rows),'parse_error_count':len(errors),'total_masks':sum(r['mask_count'] for r in rows)},'files':rows,'errors':errors}
Path(__file__).with_name('nested-chord-inventory.json').write_text(json.dumps(out,ensure_ascii=False,indent=2)+'\n',encoding='utf-8'); print(json.dumps(out['summary'])); raise SystemExit(1 if errors else 0)
