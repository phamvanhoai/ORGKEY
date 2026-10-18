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
    event_match = re.search(r'(?:it|event)\.type !in listOf\(([^)]+)\)', support)
    if not fx_match or not event_match:
        raise RuntimeError('Support declarations changed; update inventory extractor')
    numbers = lambda text: sorted(int(v.strip()) for v in text.split(','))
    return {
        'meaning': 'Declared implementation coverage, not verified APK equivalence',
        'set_layouts': re.findall(r'"([0-9.]+)"', re.search(r'require\(musicVersion in listOf\(([^)]+)\)', parser)[1]),
        'implemented_fx_types': numbers(fx_match[1]),
        'accepted_track_event_types': sorted(set(numbers(event_match[1]) + [11, 12])),
        'conditionally_supported_track_events': {
            '51': 'Nonnegative KeyTouch, low 16 bits; no script/smoothing',
            '52': 'KeyBalance -100 through 100; sampled voices',
            '53': 'Positive KeyPitchMove /1000; no script/smoothing',
            '65': 'Slots 2/3 parameters 0 through 6; retained but expression bypassed on rhythm channel type 1'},
        'expression_effects': {
            '2': 'Pitch Shake: keyboard mode/STS and allowed channel types 0/2; rhythm groups bypass; mixer quantization and timing need original-app validation',
            '3': 'Volume Shake: keyboard mode/STS and allowed channel types 0/2; rhythm groups bypass; timing needs original-app validation'},
        'native_reuse': {'29': 'libngverb.so; original binary, algorithm source not reconstructed',
                         '15': 'libnpitchshift.so; original binary, device sine frequency checks passed'},
        'missing_fx_types': [],
        'sts_chord_input': 'ChordSingles, ChordGet octave shortcuts, ChordFind subset/order and dual lookup implemented; dual preferences/UI and original-app comparison missing',
        'known_missing_clusters': ['STS ChordDuals preferences/UI, change masks and complete snapshot routing',
                                   'original Aftertouch switching conditions and timing verification',
                                   'DNC articulation/connection',
                                   'instrument/mode script runtime',
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
