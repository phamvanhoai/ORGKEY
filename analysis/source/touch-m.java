    /* JADX WARN: Code duplicated, block: B:13:0x0022  */
    /* JADX WARN: Code duplicated, block: B:15:0x0026  */
    /* JADX WARN: Code duplicated, block: B:16:0x0029  */
    public final void m(int i, int i2, boolean z, boolean z2) {
        int i3;
        jp.kshoji.blemidi.device.MidiOutputDevice midiOutputDevice;
        MidiOutputDevice midiOutputDevice2;
        int i4;
        int i5;
        if (i >= 0 && i < 128) {
            this.d5 = i2;
            if (this.W1) {
                this.W1 = false;
                if (W0()) {
                    tp2 tp2Var = this.n;
                    if (tp2Var.b != 0) {
                        i4 = tp2Var.d;
                    } else if (this.X1 == 1) {
                        i4 = 17;
                    } else {
                        i4 = 11;
                    }
                } else if (this.X1 == 1) {
                    i4 = 17;
                } else {
                    i4 = 11;
                }
                int i6 = this.X1 == 1 ? this.d2 : this.c2;
                int i7 = this.o.fChordScan;
                if (i7 == 1 || i7 == 2) {
                    i5 = i - (((i6 * 12) + this.K0) + i4);
                    this.V1 = i5;
                } else {
                    i5 = i - (((i6 * 12) + this.K0) - 1);
                    this.V1 = i5;
                }
                if (i5 < 0) {
                    this.V1 = 0;
                }
                H1(false);
                Y();
                this.m0.putString("split_count", Integer.toString(this.V1));
                this.m0.commit();
            }
            int iRecordRhythmMode = this.o.RecordRhythmMode();
            Music music = this.o;
            if (iRecordRhythmMode != 0) {
                int iRecordRhythmMode2 = music.RecordRhythmMode();
                int i8 = this.g2;
                if (i8 != 0 && i8 != this.f2 && !this.o.GroupPlayIsEmpty()) {
                    int i9 = this.g2;
                    this.f2 = i9;
                    this.B4 = i9;
                    this.o.RecordRhythmStart();
                }
                if (this.o.RecordRhythmTrack(Integer.MIN_VALUE, this.B4, (short) 2, i, i, i2) || iRecordRhythmMode2 != this.o.RecordRhythmMode()) {
                    Y();
                }
            } else if (z) {
                music.RecordMusic((short) 4, -1, i, i2);
            } else {
                music.RecordMusic((short) 2, -1, i, i2);
            }
            if (z2 && (this.X0 & 2) != 0) {
                if (this.J5 != null && (midiOutputDevice2 = this.K5) != null && this.F5) {
                    midiOutputDevice2.sendMidiNoteOn(0, 0, i, i2);
                }
                if (this.M5 != null && (midiOutputDevice = this.O5) != null && this.F5) {
                    midiOutputDevice.sendMidiNoteOn(0, i, i2);
                }
            }
        }
        int i10 = this.T1;
        if (i10 == 3) {
            this.o.RecordMusic((short) 51, 1000, this.d5 / 10, -1);
        } else if (i10 == 4 && (i3 = this.e3) != -1) {
            this.o.RecordMusic((short) 1, 1000, i3, 0);
        }
    }
