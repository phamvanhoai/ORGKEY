    public void OpenKey(int i, int i2, int i3, boolean z, int i4, int i5, int i6, int i7, int i8, int i9, int i10, int i11, int i12, int i13, int i14, boolean z2, int i15, int i16) {
        int i17;
        if (this.fMusic.fInstruments.Get(i5).fModeCount == 0) {
            OpenKey(0, i, i2, i3, i4, i5, i6, i7, i8, i9, i10, i11, i12, i13, i14, -1, -1, -1, -1, -1, -1, 0, z2, i15, i16);
            return;
        }
        if (this.fMode < 0) {
            ModeSet(i5, 0);
        }
        Instrument instrumentGet = this.fMusic.fInstruments.Get(i5);
        InstMode instMode = instrumentGet.fModeList[this.fMode];
        int i18 = instMode.Sound;
        if (z && (i17 = instMode.Aftertouch) >= 0) {
            i18 = i17;
        }
        if (i18 >= 0) {
            OpenKey(i18, i, i2, i3, i4, i5, i6, i7, i8, i9, i10, i11, i12, i13, i14, -1, -1, -1, -1, -1, -1, 0, z2, i15, i16);
        }
        int i19 = 0;
        while (true) {
            instMode = instMode;
            byte[] bArr = instMode.Mix;
            if (i19 >= bArr.length) {
                return;
            }
            byte b = bArr[i19];
            if (b >= 0) {
                Instrument instrument = instrumentGet;
                if (b < instrument.fSndCount) {
                    instrumentGet = instrument;
                    OpenKey(b, i, i2, i3, i4, i5, i6, ((instMode.MixVol[i19] + 100) * i7) / 100, i8 + instMode.MixPan[i19], i9, i10, i11, i12, i13, i14, -1, -1, -1, -1, -1, -1, 0, z2, i15, 0);
                } else {
                    instrumentGet = instrument;
                }
            }
            i19++;
        }
    }
