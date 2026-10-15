    public void InDeviceStart(int i, int i2) {
        if (this.fInActive) {
            return;
        }
        if (this.fIsOpenSL && this.fIsOpenSLRecord) {
            this.fPlatform.d();
            try {
                NOpenSL.RecordStart();
            } catch (Exception unused) {
            }
            z01 z01Var = this.fFXWaveMic;
            z01Var.a = this.fOutSampleRate;
            z01Var.c();
            this.fFXWaveMic.d(this.fFXWave);
            this.fInChannels = (byte) i2;
            this.fInSampleRate = this.fOutSampleRate;
            int i3 = this.fOutBufferSize;
            this.fInBufferSize = i3;
            short[] sArr = new short[i3];
            this.fInBuffer = sArr;
            Arrays.fill(sArr, (short) 0);
            setInAudioTrackVolume();
            this.fInActive = true;
            return;
        }
        // Nhánh AudioRecord fallback tiếp tục trong bằng chứng REA gốc.
    }
