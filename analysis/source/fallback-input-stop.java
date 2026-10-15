    public final void d() {
        for (Object obj : new ArrayList(this.a.b)) {
            b60 state = ((c23) obj).c;
            AudioRecord record = (AudioRecord) state.f;
            if (record != null) {
                try {
                    record.stop();
                    ((AudioRecord) state.f).release();
                } catch (Exception unused) {
                }
                state.f = null;
            }
            AudioTrack monitor = (AudioTrack) state.e;
            if (monitor != null) {
                try {
                    monitor.stop();
                    ((AudioTrack) state.e).release();
                } catch (Exception unused) {
                }
                state.e = null;
            }
        }
    }
