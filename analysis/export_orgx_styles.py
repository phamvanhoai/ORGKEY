"""Preserve reference General Drumkit tracks without guessing timing units."""
from pathlib import Path
import json
from parse_default_template import parse_asset

root=Path(__file__).resolve().parent.parent
parsed=parse_asset((root/'orgx/app/src/main/assets/Default.Template').read_bytes(),'assets/Default.Template')
groups={g['group']:g for g in parsed['track_groups']}
report_path=root/'analysis/previews/all-style-report.json'
preview_reports=json.loads(report_path.read_text()) if report_path.exists() else []
headroom={r['style']:min(1.0,30000/max(1,r['peak_before_clipping'])) for r in preview_reports}
tracks=[]
for track in parsed['tracks']:
    if track['instrument']!=9 or not track['events']:
        continue
    group=groups.get(track['group'])
    if group is None:
        raise ValueError(f'Missing track group {track["group"]}')
    events=[]
    for event in track['events']:
        assert event['event'] in (1,2) and 0<=event['key']<=127
        events.append({k:event[k] for k in ('event','id','key','touch','time','length','tune')})
    parent=groups.get(group['parent'])
    style_name=parent['name'] if parent else str(group['parent'])
    display_name=style_name+' / '+group['name']
    tracks.append({'name':track['name'],'display_name':display_name,
        'orgx_output_gain':headroom.get(display_name,1.0),'group':track['group'],'mute':track['mute'],
        'group_name':group['name'],'parent':group['parent'],'section':group['section'],
        'tempo':group['tempo'],'loop':group['loop'],'limit':group['limit'],
        'limit_start':group['limit_start'],'limit_end':group['limit_end'],'events':events})
output={'format':'ORGX reference drum tracks','version':1,
    'source':'Default.Template','timing':'reference units; not converted to seconds',
    'tracks':tracks}
path=root/'orgx/app/src/main/assets/reference-drum-tracks.json'
path.write_text(json.dumps(output,ensure_ascii=False,separators=(',',':')),encoding='utf-8')
loaded=json.loads(path.read_text(encoding='utf-8'))
assert loaded==output
print(f'Exported {len(tracks)} drum tracks, {sum(len(t["events"]) for t in tracks)} events; round-trip verified')
