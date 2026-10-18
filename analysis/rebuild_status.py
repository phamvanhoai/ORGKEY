"""Generate a reproducible implementation inventory; never a parity percentage."""
import json
import re
from pathlib import Path

ROOT = Path(__file__).resolve().parents[1]
SOURCE = ROOT / 'orgx/app/src/main/java/com/hovait/orgx'

def inventory():
    fx = (SOURCE / 'ImportedFxChain.kt').read_text(encoding='utf-8')
    support = (SOURCE / 'ImportedStyleSupport.kt').read_text(encoding='utf-8')
    parser = (SOURCE / 'ImportedSetInstruments.kt').read_text(encoding='utf-8')
    fx_match = re.search(r'it\.enabled && it\.type in listOf\(([^)]+)\)', fx)
    event_match = re.search(r'it\.type !in listOf\(([^)]+)\)', support)
    if not fx_match or not event_match:
        raise RuntimeError('Support declarations changed; update inventory extractor')
    numbers = lambda text: sorted(int(v.strip()) for v in text.split(','))
    return {
        'meaning': 'Declared implementation coverage, not verified APK equivalence',
        'set_layouts': re.findall(r'"([0-9.]+)"', re.search(r'require\(musicVersion in listOf\(([^)]+)\)', parser)[1]),
        'implemented_fx_types': numbers(fx_match[1]),
        'accepted_track_event_types': sorted(set(numbers(event_match[1]) + [11, 12])),
        'native_reuse': {'29': 'libngverb.so; original binary, algorithm source not reconstructed'},
        'missing_fx_types': [15, 31],
        'known_missing_clusters': ['DNC articulation/connection', 'aftertouch sound switching',
                                   'instrument/mode script runtime', 'STS application',
                                   'SET control events outside the accepted list',
                                   'embedded wave events (101)'],
        'requires_original_app_comparison': ['UI and button flows', 'style timing and chord mapping',
                                               'PCM gain, quantization and timbre',
                                               'native lifecycle under long sessions'],
    }

if __name__ == '__main__':
    target = ROOT / 'analysis/REBUILD-STATUS.json'
    target.write_text(json.dumps(inventory(), ensure_ascii=False, indent=2) + '\n', encoding='utf-8')
    print(target)
