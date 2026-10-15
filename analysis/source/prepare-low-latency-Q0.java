    public final void Q0() {
        int i = 0;
        if (this.R1 != 4) {
            this.o.lowLatencyInstruments(null, 0, this.f4);
            return;
        }
        int[] iArr = new int[4];
        iArr[0] = 0;
        iArr[1] = 0;
        iArr[2] = 0;
        iArr[3] = 0;
        int i2 = 0;
        for (int i3 = 0; i3 < this.o.TrackCount(-1); i3++) {
            if (this.o.Track(i3).fGroup == 0 && this.o.Track(i3).fType == 1 && this.o.Track(i3).fRecord) {
                iArr[i2] = this.o.Track(i3).fInstrument;
                i2++;
            }
        }
        for (int i4 = 1; i4 <= 3; i4++) {
            if (this.o.ModeState(i4) > 0) {
                i = i4;
                break;
            }
        }
        this.o.lowLatencyInstruments(iArr, i, this.f4);
    }
