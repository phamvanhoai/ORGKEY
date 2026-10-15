    @Override // defpackage.y42
    public void g(Object obj) {
        c23 target = (c23) obj;
        b60 state = target.c;
        try {
            AudioRecord record = (AudioRecord) state.f;
            Music music = (Music) state.g;
            int count = record.read(music.fInBuffer, 0, music.fInBufferSize);
            target.a = count;
            music.ProcessIn(count);
            ((AudioTrack) state.e).write(music.fInBuffer, 0, target.a);
        } catch (Exception unused) {
        }
    }
