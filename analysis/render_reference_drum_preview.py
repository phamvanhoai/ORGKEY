"""Render one compatible reference drum loop using ORGX's current release approximation."""
from pathlib import Path
import argparse
import json
import struct
import wave

root=Path(__file__).resolve().parent.parent
cli=argparse.ArgumentParser()
cli.add_argument('--output',type=Path,default=root/'analysis/previews/reference-drums.wav')
cli.add_argument('--style-index',type=int,default=0)
cli.add_argument('--report-only',action='store_true')
args=cli.parse_args()
tracks=json.loads((root/'orgx/app/src/main/assets/reference-drum-tracks.json').read_text())['tracks']
compatible=[t for t in tracks if not t['mute'] and t['loop'] and t['section'] in range(32,36)
    and t['limit_end']>t['limit_start'] and sum(e['event']==2 for e in t['events'])>=16
    and all(34<=e['key']<=82 for e in t['events'] if e['event']==2)]
if not 0<=args.style_index<len(compatible):cli.error(f'--style-index must be 0..{len(compatible)-1}')
track=compatible[args.style_index]
rate=48000
sounds={}
for note in {e['key'] for e in track['events'] if e['event']==2}:
    with wave.open(str(root/f'orgx/app/src/main/res/raw/orgx_drum_{note}.wav'),'rb') as source:
        assert source.getsampwidth()==2 and source.getnchannels()==1
        data=source.readframes(source.getnframes())
        sounds[note]=(struct.unpack('<'+'h'*(len(data)//2),data),source.getframerate())
events=sorted(track['events'],key=lambda e:e['time'])
frames=round((track['limit_end']-track['limit_start'])*120/track['tempo']/1000*rate)
voices={};cursor=0;clipped=0;peak=0;output=bytearray()
for frame in range(frames):
    time=frame*1000*track['tempo']//(rate*120)+track['limit_start']
    while cursor<len(events) and events[cursor]['time']<=time:
        event=events[cursor];cursor+=1
        if event['event']==2:
            if event['length']>0 and time-event['time']>event['length']+10:continue
            voices[event['id']]=[event['key'],event['touch']/100,0,-1]
        elif event['id'] in voices:voices[event['id']][3]=0
    left=right=0.0
    for identity,voice in list(voices.items()):
        note,gain,age,release=voice;pcm,source_rate=sounds[note]
        position=age*source_rate/rate;index=int(position)
        if index>=len(pcm):del voices[identity];continue
        fraction=position-index
        value=pcm[index]*(1-fraction)+(pcm[index+1] if index+1<len(pcm) else 0)*fraction
        gain*=max(0,1-release/(rate/100)) if release>=0 else 1
        pan=-50 if note%2 else 50
        left+=value*gain*min(100,100-pan)/100
        right+=value*gain*min(100,100+pan)/100
        voice[2]+=1
        if release>=0:
            voice[3]+=1
            if voice[3]>=rate/100:del voices[identity]
    for value in (left,right):
        value=round(value*0.55*0.65*track.get('orgx_output_gain',1.0))
        peak=max(peak,abs(value));clipped+=abs(value)>32767
        if not args.report_only:output.extend(struct.pack('<h',max(-32768,min(32767,value))))
args.output.parent.mkdir(parents=True,exist_ok=True)
if not args.report_only:
    with wave.open(str(args.output),'wb') as out:
        out.setnchannels(2);out.setsampwidth(2);out.setframerate(rate);out.writeframes(output)
report={'style':track['display_name'],'tempo':track['tempo'],'frames':frames,'seconds':frames/rate,
    'orgx_output_gain':track.get('orgx_output_gain',1.0),
    'peak_before_clipping':peak,'clipped_samples':clipped,'release':'ORGX approximation, 10 ms',
    'source':'Reference events and General Drumkit PCM; not a device recording'}
args.output.with_suffix('.json').write_text(json.dumps(report,indent=2),encoding='utf-8')
print(json.dumps(report))
