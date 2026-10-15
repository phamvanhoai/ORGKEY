#!/usr/bin/env python3
import hashlib,json
from pathlib import Path

root=Path(__file__).resolve().parent
include={'.md','.py','.json','.csv','.asm','.txt','.java','.xml','.bin'}
rows=[]
for path in sorted(root.rglob('*')):
    if not path.is_file() or path.suffix.lower() not in include or '__pycache__' in path.parts:
        continue
    if path.name=='analysis-manifest.json':
        continue
    data=path.read_bytes()
    rows.append({'path':path.relative_to(root).as_posix(),'size':len(data),'sha256':hashlib.sha256(data).hexdigest()})
summary={
    'file_count':len(rows),
    'total_bytes':sum(x['size'] for x in rows),
    'by_extension':{},
}
for row in rows:
    ext=Path(row['path']).suffix.lower() or '(none)'
    summary['by_extension'][ext]=summary['by_extension'].get(ext,0)+1
out={'summary':summary,'files':rows}
(root/'analysis-manifest.json').write_text(json.dumps(out,ensure_ascii=False,indent=2)+'\n',encoding='utf-8')
print(json.dumps(summary,ensure_ascii=False))
