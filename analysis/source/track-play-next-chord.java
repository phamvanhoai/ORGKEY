    /* JADX WARN: Code duplicated, block: B:28:0x0051  */
    public synchronized boolean PlayNext(int i, TrackGroup trackGroup, int i2, int i3) {
        int iCalcChordKey;
        short s;
        short s2;
        boolean z = true;
        try {
            this._result = true;
            if (this.KeyData == null || this.fDataPos == 0) {
                if (this.fDataPos < this.fData.size()) {
                    this.KeyData = this.fData.get(this.fDataPos);
                    this.fDataPos++;
                    this._result = true;
                } else {
                    this.KeyData = null;
                    this._result = false;
                }
            }
            while (true) {
                KeyData keyData = this.KeyData;
                if (keyData != null && i - this.fTimeStart >= keyData.Time) {
                    if (i2 == 0 || trackGroup.Section <= 0 || !((s2 = keyData.Event) == 2 || s2 == 4)) {
                        iCalcChordKey = this.KeyData.Key;
                        this._key = iCalcChordKey;
                    } else if (this.fMusic.fInstruments.Get(this.fInstrument).fType == 0) {
                        iCalcChordKey = CalcChordKey(this.KeyData.Key, i3);
                        this._key = iCalcChordKey;
                    } else {
                        iCalcChordKey = this.KeyData.Key;
                        this._key = iCalcChordKey;
                    }
                    KeyData keyData2 = this.KeyData;
                    int i4 = keyData2.Length;
                    if ((i4 <= 0 || i - this.fTimeStart <= keyData2.Time + i4 + 10 || ((s = keyData2.Event) != 2 && s != 4 && s != 101)) && !keyData2.IsNew) {
                        Play(keyData2.Event, keyData2.ID, iCalcChordKey, keyData2.Touch, keyData2.Time, i4, keyData2.Tune, 0, keyData2.instSec);
                    }
                    if (this.fDataPos >= this.fData.size()) {
                        KeyData keyData3 = this.KeyData;
                        this._result = keyData3 != null && keyData3.Event != 1 && this.fChannels.Exists(keyData3.ID, false) && this.KeyData.Length >= 0;
                        this.KeyData = null;
                        break;
                    }
                    this.KeyData = this.fData.get(this.fDataPos);
                    this.fDataPos++;
                    this._result = true;
                } else {
                    break;
                }
            }
            if (trackGroup.Limit) {
                if (i - this.fTimeStart >= trackGroup.LimitEnd) {
                    z = false;
                }
                this._result = z;
                if (!z) {
                    this.fChannels.CloseAll(false);
                }
            }
        } catch (Throwable th) {
            throw th;
        }
        return this._result;
    }