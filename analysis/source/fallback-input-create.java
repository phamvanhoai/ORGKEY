    @Override // defpackage.y42
    public void g(Object obj) {
        Music music = (Music) this.e;
        int sampleRate = this.a;
        int inputSource = this.b;
        int bufferBytes = this.d;
        b60 state = ((c23) obj).c;
        state.g = music;
        int channels = this.c;
        try {
            state.f = new AudioRecord(inputSource, sampleRate, channels == 1 ? 16 : 12, 2, bufferBytes);
        } catch (Exception unused) {
            state.f = null;
        }
        int outputMask = channels == 1 ? 4 : 12;
        state.e = null;
        // APK tiếp tục thử các constructor AudioTrack mới rồi cũ.
        try {
            AudioRecord record = (AudioRecord) state.f;
            if (record != null) record.startRecording();
            AudioTrack monitor = (AudioTrack) state.e;
            if (monitor != null) monitor.play();
        } catch (Exception unused) {
        }
    }
