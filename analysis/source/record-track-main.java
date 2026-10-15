    public int RecordTrack(Track track, short s, int i, int i2, int i3) {
        if (i < 0) {
            if (i2 < 0 || !(s == 2 || s == 4 || s == 1)) {
                i = b44.e(1, Integer.MAX_VALUE);
            } else {
                int[] iArr = track.fKeyRandoms;
                if (iArr == null) {
                    iArr = new int[128];
                    track.fKeyRandoms = iArr;
                }
                if (s != 1) {
                    iArr[i2] = b44.e(1, Settings.DEFAULT_INITIAL_WINDOW_SIZE);
                }
                i = (track.fKeyRandoms[i2] << 16) + (this.fMusic.fRecordCounter << 8) + i2;
            }
            if (s == 51 || s == 52 || s == 53) {
                i3 = this.fMusic.fRecordCounter;
            }
        }
        int i4 = i;
        int i5 = i3;
        track.Play(s, i4, i2, i5, -1, 0, track != Get(0) ? this.fMusic.fLowLatencyMethod : 0);
        if (this.fMusic.fPlayMode != 4) {
            return i4;
        }
        track.RecordMusic(this.fTimeNow1, s, i4, i2, i5);
        return i4;
    }
