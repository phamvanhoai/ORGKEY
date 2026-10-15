#!/usr/bin/env python3
"""Inventory the UTF-8 menu/help catalogs shipped in assets/menu."""
import hashlib, json, zipfile
from pathlib import Path

APK = Path(__file__).resolve().parent.parent / 'ORG+24_+Your+Music_2026.10.01_APKPure.apk'
OUT = Path(__file__).with_name('menu-assets-inventory.json')

def main():
    rows=[]; errors=[]
    with zipfile.ZipFile(APK) as z:
        for name in sorted(n for n in z.namelist() if n.startswith('assets/menu/') and n.endswith('.txt')):
            raw=z.read(name)
            try: text=raw.decode('utf-8-sig')
            except UnicodeDecodeError as e: errors.append(f'{name}: {e}'); continue
            lines=text.replace('\r\n','\n').replace('\r','\n').split('\n')
            rows.append({'path':name,'size':len(raw),'sha256':hashlib.sha256(raw).hexdigest(),'line_count':len([x for x in lines if x]),'first_line':next((x for x in lines if x),''),'lines':lines})
    result={'summary':{'file_count':len(rows),'parse_error_count':len(errors),'total_bytes':sum(r['size'] for r in rows)},'files':rows,'errors':errors}
    OUT.write_text(json.dumps(result,ensure_ascii=False,indent=2)+'\n',encoding='utf-8')
    print(json.dumps(result['summary'],ensure_ascii=False))
    return 1 if errors else 0
if __name__=='__main__': raise SystemExit(main())
