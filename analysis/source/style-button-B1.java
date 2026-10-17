    public final int B1(int i, boolean z) {
        if (z && this.j2 != 0) {
            this.o.fChordSyncStop = false;
        }
        for (int i2 = 1; i2 < this.o.GroupCount(); i2++) {
            if (this.o.Group(i2).Type == 0 && this.o.Group(i2).Group == i) {
                this.e2 = i;
                break;
            }
        }
        if (x1(this.e2, this.f2) == 0) {
            this.f2 = 32;
        }
        if (this.y1) {
            int i3 = 1;
            while (true) {
                if (i3 >= this.o.GroupCount()) {
                    i3 = 0;
                    break;
                }
                if (this.o.Group(i3).Parent == this.e2 && this.o.Group(i3).Section == 32) {
                    break;
                }
                i3++;
            }
            if (i3 > 0) {
                for (int i4 = 5; i4 < 12; i4++) {
                    Music music = this.o;
                    String[] strArr = this.s5;
                    Track Track = music.Track(i3, strArr[i4]);
                    if (Track != null && (i4 < 11 || this.o.InstrumentType(Track.fInstrument) == 1)) {
                        this.o.Track(0, strArr[i4]).InstrumentChange(Track.fInstrument);
                    }
                }
            }
        }
        int i5 = 0;
        for (int i6 = 1; i6 < this.o.GroupCount() && this.o.Group(i6).Group != this.e2; i6++) {
            if (this.o.Group(i6).Type == 0) {
                i5++;
            }
        }
        if (z && this.j2 != 0) {
            int i7 = this.f2;
            int i8 = i7 >> 4;
            if (i8 != 3 && i8 != 4) {
                y1(i7, false);
                return i5;
            }
            int i9 = (i7 & 15) + 32;
            this.f2 = i9;
            TrackGroup trackGroupGroupSection = this.o.GroupSection(this.e2, i9);
            if (trackGroupGroupSection == null) {
                this.f2 = 32;
                trackGroupGroupSection = this.o.GroupSection(this.e2, 32);
            }
            if (trackGroupGroupSection != null) {
                this.o.RecordRhythm(TrackEvent.RhythmJump, trackGroupGroupSection.Group, 0);
            }
        }
        return i5;
    }