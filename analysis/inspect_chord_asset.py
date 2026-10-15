#!/usr/bin/env python3
import hashlib,json,zipfile
from pathlib import Path
apk=Path(__file__).resolve().parent.parent/'ORG+24_+Your+Music_2026.10.01_APKPure.apk'
raw=zipfile.ZipFile(apk).read('assets/Chord'); text=raw.decode('utf-8-sig').replace('\r\n','\n').replace('\r','\n')
rows=[]; errors=[]
for no,line in enumerate(text.splitlines(),1):
    if not line.strip(): continue
    parts=line.split(); name=parts[0]
    try: intervals=[int(x) for x in parts[1:]]
    except ValueError as e: errors.append(f'{no}: {e}'); continue
    masks=[sum(1 << ((interval + root) % 12) for interval in intervals) for root in range(12)]
    rows.append({'line':no,'name':name,'intervals':intervals,'note_count':len(intervals),'max_interval':max(intervals,default=None),'root_masks':masks})
lookup={}
for row in rows:
    for root, mask in enumerate(row['root_masks']): lookup.setdefault(str(mask), []).append({'name':row['name'],'root':root})
collisions={mask: values for mask, values in lookup.items() if len(values)>1}
out={'asset':'assets/Chord','size':len(raw),'sha256':hashlib.sha256(raw).hexdigest(),'summary':{'chord_count':len(rows),'parse_error_count':len(errors),'min_notes':min((r['note_count'] for r in rows),default=0),'max_notes':max((r['note_count'] for r in rows),default=0),'distinct_root_masks':len(lookup),'collision_mask_count':len(collisions)},'chords':rows,'root_mask_collisions':collisions,'errors':errors}
Path(__file__).with_name('chord-asset-inventory.json').write_text(json.dumps(out,ensure_ascii=False,indent=2)+'\n',encoding='utf-8')
print(json.dumps(out['summary']))
raise SystemExit(1 if errors else 0)
