"""Inventory a local JADX export without treating Java output as original source."""
from pathlib import Path
import hashlib
import json
import zipfile

root = Path(__file__).resolve().parent
apk = next(root.parent.glob('*.apk'))
export = root / 'decoded-original'
sources = sorted((export / 'sources').rglob('*.java'))
issues = []
app_sources = []
for path in sources:
    relative = path.relative_to(export).as_posix()
    app = relative.startswith(('sources/sofeh/', 'sources/com/sofeh/'))
    if not app:
        continue
    body = path.read_text(encoding='utf-8', errors='replace')
    errors = [(number, line.strip()[:400]) for number, line in enumerate(body.splitlines(), 1)
              if 'Method not decompiled:' in line or 'JADX ERROR:' in line]
    if app:
        app_sources.append(relative)
    if errors:
        issues.append({'file': relative, 'app_namespace': app, 'markers': errors})
with zipfile.ZipFile(apk) as archive:
    entries = [{'path': item.filename, 'bytes': item.file_size,
                'sha256': hashlib.sha256(archive.read(item)).hexdigest()}
               for item in archive.infolist() if not item.is_dir()]
report = {
    'apk': apk.name, 'sha256': hashlib.sha256(apk.read_bytes()).hexdigest(),
    'java_files': len(sources), 'app_namespace_java_files': len(app_sources),
    'resource_files': sum(p.is_file() for p in (export / 'resources').rglob('*')),
    'marker_scan_scope': 'sofeh namespaces only; third-party libraries are inventoried but not marker-scanned',
    'files_with_error_markers': len(issues),
    'app_files_with_error_markers': sum(item['app_namespace'] for item in issues),
    'caveats': ['Exported Java is decompiler output, not original source.',
                '655 errors were reported by this JADX run; marker counts measure a different thing.',
                'Native .so files are retained as binaries; Java export is not native source.',
                'No equivalence to the rebuilt ORGX project is implied.'],
    'app_sources': app_sources, 'issues': issues, 'archive_entries': entries,
}
(root / 'DECODED-APK-INVENTORY.json').write_text(json.dumps(report, ensure_ascii=False, indent=2), encoding='utf-8')
print(json.dumps({k: v for k, v in report.items() if k not in ('app_sources', 'issues', 'archive_entries')}, indent=2))
