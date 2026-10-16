"""Build a deterministic MIDI integration fixture using only Python's standard library."""
from pathlib import Path
import struct

root=Path(__file__).resolve().parent.parent
events=[]
def add(tick,*data):events.append((tick,bytes(data)))
def vlq(value):
    result=[value&127]
    while value>>7:
        value>>=7;result.insert(0,(value&127)|128)
    return bytes(result)
add(0,0xff,0x51,3,7,0xa1,0x20) # 120 BPM
for channel,program in [(0,0),(1,73),(2,32)]:add(0,0xc0|channel,program)
add(0,0xb0,64,127)
for note in [60,64,67]:
    add(0,0x90,note,70);add(240,0x80,note,0)
add(960,0xb0,64,0)
for tick,note in [(0,72),(480,74),(960,76),(1440,79),(1920,76),(2400,74)]:
    add(tick,0x91,note,80);add(tick+360,0x81,note,0)
for tick in range(0,2880,480):
    add(tick,0x92,36 if tick<1440 else 43,75);add(tick+360,0x82,36 if tick<1440 else 43,0)
    drum=36 if tick%960==0 else 38
    add(tick,0x99,drum,100);add(tick+120,0x89,drum,0)
for tick in range(0,2880,240):
    add(tick,0x99,42,45);add(tick+100,0x89,42,0)
add(1440,0xff,0x51,3,0x0b,0x71,0xb0) # 80 BPM
add(2880,0xb0,123,0);add(2880,0xb1,123,0);add(2880,0xb2,123,0)
add(2880,0xff,0x2f,0)
track=bytearray();previous=0
for tick,data in sorted(events,key=lambda item:item[0]):
    track+=vlq(tick-previous)+data;previous=tick
output=root/'analysis/previews/ORGX-midi-integration.mid'
output.parent.mkdir(parents=True,exist_ok=True)
output.write_bytes(b'MThd'+struct.pack('>IHHH',6,0,1,480)+b'MTrk'+struct.pack('>I',len(track))+track)
print(f'{output.name}: {len(events)} events, 120 -> 80 BPM, 3.75 seconds')
