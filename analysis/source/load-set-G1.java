    public final boolean G1(String str, boolean z) {
        int i = this.U1 == 0 ? 1 : 4;
        int i2 = 0;
        for (int i3 = 1; i3 < this.o.TrackCount(-1); i3++) {
            if (this.o.Track(i3).fGroup == 0 && this.o.Track(i3).fType == 1) {
                if (this.o.Track(i3).fRecord) {
                    i2++;
                }
                this.o.Track(i3).fPedalMode = -1;
            }
        }
        if (str.isEmpty()) {
            i2--;
            z = true;
        }
        if (i2 <= 1) {
            z = true;
        }
        if (z) {
            for (int i4 = 1; i4 < this.o.TrackCount(-1); i4++) {
                if (this.o.Track(i4).fGroup == 0 && this.o.Track(i4).fType == 1 && this.o.Track(i4).fRecord) {
                    if (i2 < i) {
                        break;
                    }
                    i2--;
                    this.o.TrackRecordSet(i4, false);
                }
            }
        }
        if (!str.isEmpty()) {
            Music music = this.o;
            music.TrackRecordSet(music.Track(0, str), z);
        }
        H1(false);
        Q0();
        return z;
    }
