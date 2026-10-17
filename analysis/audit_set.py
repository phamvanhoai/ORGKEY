"""Read-only structural audit of SET layouts currently accepted by ORGX.

Does not expose protected metadata or execute DNC/script text. Reports skipped
regions explicitly; consuming bytes is not evidence of playback support.
"""
import argparse
import collections
import hashlib
import json
import struct
from pathlib import Path


class Audit:
    def __init__(self, path):
        self.path = path
        self.f = path.open('rb')
        self.size = path.stat().st_size
        self.regions = []
        self.context = 'header'
        self.fx_types = collections.Counter()
        self.fx_blocks = 0

    def raw(self, n, role='decoded'):
        start = self.f.tell()
        if n < 0 or start+n > self.size:
            raise ValueError(f'Truncated {self.context} at {start}, length {n}')
        self.regions.append((start, n, role, self.context))
        if role in ('skipped', 'pcm', 'protected'):
            self.f.seek(n, 1)
            return b''
        return self.f.read(n)

    def skip(self, n): self.raw(n, 'skipped')
    def integer(self): return struct.unpack('<i', self.raw(4))[0]
    def byte(self): return self.raw(1)[0]
    def short(self): return struct.unpack('<h', self.raw(2))[0]
    def count(self, limit=2000000):
        n = self.integer()
        if not 0 <= n <= limit: raise ValueError(f'Invalid count {n} in {self.context}')
        return n
    def text(self): return self.raw(self.count(1000000)).decode('utf-8', errors='replace')
    def common(self):
        metadata = self.text().split('\r')
        return metadata, self.integer()
    def fx(self):
        start = self.f.tell()
        self.skip(48)
        modules = self.count(1000)
        extra = {1:8,2:17,4:8,6:8,8:28,9:4,10:4,15:4,16:4,17:4,20:8,21:24,23:9,29:8,31:4}
        for _ in range(modules):
            kind = self.integer(); self.fx_types[kind] += 1; self.skip(4)
            if kind == 7: self.skip(self.count(128)*4)
            elif kind in extra: self.skip(extra[kind])
            else: raise ValueError(f'Unmapped FX module {kind} at {self.f.tell()}')
        self.fx_blocks += 1
        return {'offset':start, 'bytes':self.f.tell()-start, 'modules':modules}

    def parse(self):
        signature = self.raw(32).split(b'\0')[0].decode('ascii')
        if signature.lower() != 'music studio music': raise ValueError('Unsupported signature')
        meta, _ = self.common(); version = meta[1]
        if version not in ('1.2.8', '1.4.6'): raise ValueError('Layout not audited: '+version)
        for _ in range(3): self.text()  # Do not include free-text metadata in report.
        self.raw(self.count(1000000), 'protected')
        self.skip(2); header_values=[self.integer() for _ in range(3)];self.skip(116)
        self.context='wave-bank'
        slots=self.count(100000); waves={}
        for _ in range(self.count(slots)):
            index=self.integer(); length=self.count(2**31-1)
            if index in waves or not 0 <= index < slots: raise ValueError('Invalid wave index')
            waves[index]={'offset':self.f.tell(),'bytes':length};self.raw(length,'pcm')
        self.context='global-fx';global_fx=[self.fx() for _ in range(3)]
        self.context='instrument-collection';self.common()
        instruments=[];used_waves=collections.Counter();inline_pcm=0
        for number in range(self.count(10000)):
            self.context=f'instrument/{number}';start=self.f.tell();metadata,group=self.common();v=metadata[1]
            if v not in ('1.2.8','1.4.2','1.4.6'):raise ValueError('Unknown instrument '+v)
            legacy=v=='1.2.8';self.skip(52)
            if legacy:
                self.skip(12);ads=[[self.byte() for _ in range(3)] for _ in range(16)]
            else:self.skip(60)
            volume=self.integer();pan=self.integer();kind=self.byte();family=self.byte();priority=self.byte()
            modes=[]
            for mode in range(self.byte()):
                mode_start=self.f.tell()
                if not legacy or mode!=0:self.text()
                self.skip(16);mode_fx=self.fx();self.skip(336)
                if not legacy:self.text()  # Script contents deliberately not exported.
                if v=='1.4.6':
                    settings=self.count(10000)
                    for _ in range(settings):self.text()
                    if settings:
                        for _ in range(self.count(10000)):self.text()
                modes.append({'offset':mode_start,'bytes':self.f.tell()-mode_start,'fx':mode_fx})
            first=ads[0]+[self.byte() for _ in range(3)] if legacy else []
            sounds=[]
            for sound in range(max(1,self.byte())):
                name='Primary' if legacy and sound==0 else self.text()
                env=first if legacy and sound==0 else (ads[sound]+[self.byte() for _ in range(3)] if legacy else [self.byte() for _ in range(6)])
                sections=[]
                for _ in range(self.byte()):
                    section_start=self.f.tell();note=self.byte();self.skip(32)
                    legacy_rate=self.short() & 65535;self.byte();channels=self.byte();channels=channels if channels in (1,2) else 2
                    rate=self.integer() or legacy_rate or 48000
                    wave=self.integer();frames=self.count(2**31-1);self.integer()
                    gain=self.integer();balance=self.integer();tune=self.integer();sample_start=self.integer()
                    loop=self.byte();self.skip(3);loop_start=self.integer();loop_end=self.integer();self.skip(16)
                    length=frames*channels*2
                    if wave==0:self.raw(length,'pcm');inline_pcm+=length
                    else:
                        if wave-1 not in waves or waves[wave-1]['bytes']!=length:raise ValueError('Wave reference mismatch')
                        used_waves[wave-1]+=1
                    sections.append(dict(offset=section_start,note=note,rate=rate,channels=channels,frames=frames,wave=wave,
                        volume=gain,pan=balance,tune=tune,start=sample_start,loop=loop,loop_start=loop_start,loop_end=loop_end))
                sounds.append({'name':name,'envelope':env,'sections':sections})
            instruments.append(dict(index=number,name=metadata[0],version=v,group=group,offset=start,bytes=self.f.tell()-start,
                volume=volume,pan=pan,type=kind,family=family,priority=priority,modes=modes,sounds=sounds))
        self.context='system-collection';self.common();systems=[]
        for number in range(self.count(10000)):
            self.context=f'system/{number}';start=self.f.tell();metadata,group=self.common();self.skip(225)
            systems.append(dict(index=number,name=metadata[0],group=group,offset=start,bytes=self.f.tell()-start))
        self.context='track-collection';self.common();tracks=[]
        def sts():
            start=self.f.tell();self.skip(1);self.skip(self.byte());self.skip(16);self.skip(1)
            if self.byte():self.skip(24)
            self.fx();self.skip(320)
            return {'offset':start,'bytes':self.f.tell()-start}
        for number in range(self.count(100000)):
            self.context=f'track/{number}';start=self.f.tell();metadata,group=self.common()
            track_type=self.byte();kind=self.byte();midi_out=self.short();system=self.integer();instrument=self.integer()
            midi_inst=self.byte();solo=self.byte();mute=self.byte();record=self.byte();midi_in=self.short()
            bank0=self.byte();bank1=self.byte();root=self.byte();quality=self.byte();self.skip(106)
            stored=self.count(10000);number_sts=13 if version<'1.2.9' else stored;settings=[]
            if group==0 or version<'1.2.8':
                flags=[self.byte() for _ in range(number_sts)]
                settings=[sts() for flag in flags if flag]
            self.skip(23);track_fx=self.fx();self.skip(320)
            event_types=collections.Counter();tuned=0;notes=[];events=self.count()
            for _ in range(events):
                event=self.short();tune=self.short();event_id=self.integer();note=self.integer();touch=self.integer();time=self.integer();length=self.integer()
                if event==101:raise ValueError('Embedded wave event needs separate layout audit')
                event_types[event]+=1;tuned+=tune!=0
                if event==2:notes.append(note)
            tracks.append(dict(index=number,name=metadata[0],group=group,instrument=instrument,system=system,type=track_type,kind=kind,
                midi_out=midi_out,midi_in=midi_in,midi_instrument=midi_inst,solo=solo,mute=mute,record=record,bank0=bank0,bank1=bank1,
                chord_root=root,chord_type=quality,sts=settings,fx=track_fx,events=events,event_types=dict(event_types),tuned_events=tuned,
                note_range=[min(notes),max(notes)] if notes else None,offset=start,bytes=self.f.tell()-start))
        self.context='style-groups';groups=[]
        for _ in range(self.count(100000)):
            start=self.f.tell();group=self.integer();parent=self.integer();raw=self.raw(32)
            name=raw[1:].split(b'\0')[0].decode('utf-8',errors='replace')
            section=self.byte();kind=self.byte();loop=self.byte();limit=self.byte()
            begin=self.integer();end=self.integer();jump=self.integer();color=self.integer();chord_type=self.byte();base=self.byte();tempo=self.short()
            shortcut=self.integer();midi=self.byte();reserved=self.byte();mx=self.byte();my=self.byte()
            if version<'1.4.8':mx,my=my>>4,my&15
            sts_index=[self.integer() for _ in range(4)] if version>='1.4.6' else []
            fx=self.fx() if version>='1.4.2' else None
            groups.append(dict(id=group,parent=parent,name=name,section=section,type=kind,loop=loop,limit=limit,start=begin,end=end,jump=jump,
                color=color,chord_type=chord_type,chord_base=base,tempo=tempo*120//100 if version<'1.3.0' else tempo,
                meter=[mx,my],shortcut=shortcut,midi_shortcut=midi,sts_index=sts_index,fx=fx,offset=start,bytes=self.f.tell()-start))
        parsed_end=self.f.tell();trailing=self.size-parsed_end
        if trailing:self.raw(trailing,'unparsed-tail')
        by_role=collections.Counter();by_context=collections.Counter()
        expected=0
        for offset,length,role,context in self.regions:
            if offset!=expected:raise ValueError('Non-contiguous byte accounting')
            expected+=length;by_role[role]+=length;by_context[context]+=length
        assert expected==self.size
        return dict(version=version,bytes=self.size,header_values=header_values,parsed_end=parsed_end,trailing_bytes=trailing,
            byte_accounting=dict(by_role),bytes_by_context=dict(by_context),wave_slots=slots,wave_count=len(waves),
            unimplemented_regions=[dict(offset=o,bytes=n,role=r,context=c) for o,n,r,c in self.regions if r in ('skipped','unparsed-tail') and n],
            bank_pcm_bytes=sum(w['bytes'] for w in waves.values()),inline_pcm_bytes=inline_pcm,
            referenced_bank_waves=len(used_waves),unreferenced_bank_waves=sorted(set(waves)-set(used_waves)),
            global_fx=global_fx,fx_blocks=self.fx_blocks,fx_module_types=dict(self.fx_types),
            instruments=instruments,systems=systems,tracks=tracks,groups=groups,
            caveats=['Decoded means structurally inspected, not used by ORGX playback.',
                'Skipped bytes remain in the unchanged SET; their payload is not decoded here.',
                'Reserved bytes cannot be assumed active or unused without original reader evidence.',
                'This audit validates the accepted layouts, not all possible SET formats.'])


def digest(path):
    h=hashlib.sha256()
    with path.open('rb') as f:
        for block in iter(lambda:f.read(1024*1024),b''):h.update(block)
    return h.hexdigest()


def main():
    parser=argparse.ArgumentParser();parser.add_argument('file',type=Path);parser.add_argument('--output',type=Path,required=True)
    args=parser.parse_args();before=digest(args.file);audit=Audit(args.file)
    try:report=audit.parse()
    finally:audit.f.close()
    after=digest(args.file)
    if before!=after:raise RuntimeError('Input file changed during audit')
    report['sha256_before']=before;report['sha256_after']=after;report['input_unchanged']=True
    args.output.parent.mkdir(parents=True,exist_ok=True)
    args.output.write_text(json.dumps(report,ensure_ascii=False,indent=2)+'\n',encoding='utf-8')
    print(json.dumps({k:report[k] for k in ['bytes','version','parsed_end','trailing_bytes','byte_accounting','wave_count','referenced_bank_waves','input_unchanged']}))
    print(json.dumps({k:len(report[k]) for k in ['instruments','systems','tracks','groups']}))


if __name__=='__main__':main()
