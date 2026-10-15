    public final void l(int i, boolean z) {
        jp.kshoji.blemidi.device.MidiOutputDevice midiOutputDevice;
        MidiOutputDevice midiOutputDevice2;
        int i2;
        int i3 = this.T1;
        if (i3 == 3) {
            this.o.RecordMusic((short) 51, 1000, this.d5, -1);
        } else if (i3 == 4 && (i2 = this.e3) != -1) {
            this.o.RecordMusic((short) 2, 1000, i2, 100);
        }
        if (i < 0 || i >= 128) {
            return;
        }
        if (z && (this.X0 & 2) != 0) {
            if (this.J5 != null && (midiOutputDevice2 = this.K5) != null && this.F5) {
                midiOutputDevice2.sendMidiNoteOff(0, 0, i, 64);
            }
            if (this.M5 != null && (midiOutputDevice = this.O5) != null && this.F5) {
                midiOutputDevice.sendMidiNoteOff(0, i, 64);
            }
        }
        int iRecordRhythmMode = this.o.RecordRhythmMode();
        Music music = this.o;
        if (iRecordRhythmMode == 0) {
            music.RecordMusic((short) 1, -1, i, -1);
        } else {
            music.RecordRhythmTrack(Integer.MIN_VALUE, this.B4, (short) 1, i, i, 100);
        }
    }
