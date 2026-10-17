    public void RecordRhythm(short s, int i, int i2) {
        short s2;
        int i3;
        int i4;
        ArrayList<Track> arrayList = this.fTracksMain;
        int size = arrayList.size();
        int i5 = 0;
        while (i5 < size) {
            Track track = arrayList.get(i5);
            i5++;
            Track track2 = track;
            if (track2.fRecord && track2.fType == 3) {
                s2 = s;
                i3 = i;
                i4 = i2;
                track2.Play(s2, -1, i3, i4, -1, 0, 0);
                if (this.fMusic.fPlayMode == 4) {
                    track2.RecordRhythm(this.fTimeNow1, s2, b44.e(1, Integer.MAX_VALUE), i3, i4, 0);
                    s2 = s2;
                    i3 = i3;
                    i4 = i4;
                }
            } else {
                s2 = s;
                i3 = i;
                i4 = i2;
            }
            s = s2;
            i = i3;
            i2 = i4;
        }
    }