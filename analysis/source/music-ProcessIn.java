    public void ProcessIn(int i) {
        if (this.fFXWaveMic.e != 0) {
            for (int i2 = 0; i2 < i; i2++) {
                z01 z01Var = this.fFXWaveMic;
                z01Var.i(this.fInBuffer, i2, z01Var.e + 100);
            }
        }
        if (this.fMusic.fPlayMode == 4) {
            this.fTracks.RecordWave(TrackEvent.Wave, this.fInBuffer, i, 100);
        }
        if (!this.fFXWaveMic.l.isEmpty()) {
            for (int i3 = 0; i3 < i; i3++) {
                z01 z01Var2 = this.fFXWaveMic;
                short[] sArr = this.fInBuffer;
                ArrayList arrayList = z01Var2.l;
                int size = arrayList.size();
                for (int i4 = 0; i4 < size; i4++) {
                    ((n01) arrayList.get(i4)).f(sArr, i3);
                }
            }
        }
        if (this.fFadeVolume != MaxFade) {
            for (int i5 = 0; i5 < i; i5++) {
                this.fFXWaveMic.i(this.fInBuffer, i5, (int) ((this.fFadeVolume * 100) / MaxFade));
            }
        }
    }
