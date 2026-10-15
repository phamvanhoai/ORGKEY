    @Override // sofeh.music.MusicObject
    public void ReadFromStream(al0 al0Var, boolean z) throws IOException {
        SndParam[] sndParamArr;
        super.ReadFromStream(al0Var, z);
        al0Var.read(this.fSelected, 0, 52);
        if (this.fVersion.compareToIgnoreCase("1.4.0") >= 0) {
            for (int i = 0; i < 15; i++) {
                al0Var.readInt();
            }
            sndParamArr = null;
        } else {
            for (int i2 = 0; i2 < 3; i2++) {
                al0Var.readInt();
            }
            sndParamArr = new SndParam[16];
            for (int i3 = 0; i3 < 16; i3++) {
                SndParam sndParam = new SndParam();
                sndParamArr[i3] = sndParam;
                sndParam.Attack = al0Var.readByte();
                sndParamArr[i3].Decay = al0Var.readByte();
                sndParamArr[i3].Sustain = al0Var.readByte();
                if (this.fVersion.compareToIgnoreCase("1.3.1") < 0) {
                    SndParam sndParam2 = sndParamArr[i3];
                    sndParam2.Attack = (byte) 0;
                    sndParam2.Decay = (byte) 0;
                    sndParam2.Sustain = (byte) 100;
                }
            }
        }
        this.fVolume = al0Var.b();
        this.fPan = al0Var.b();
        this.fType = al0Var.readByte();
        this.fFamily = al0Var.readByte();
        this.fPriority = al0Var.readByte();
        byte b = al0Var.readByte();
        this.fModeCount = b;
        int i4 = 1;
        if (b > 0) {
            for (int i5 = 0; i5 < this.fModeCount; i5++) {
                if (i5 != 0 || this.fVersion.compareToIgnoreCase("1.4.1") >= 0) {
                    this.fModeList[i5].Name = al0Var.e();
                }
                this.fModeList[i5].Sound = al0Var.b();
                this.fModeList[i5].Aftertouch = al0Var.b();
                al0Var.read(this.fModeList[i5].Mix);
                if (this.fVersion.compareToIgnoreCase("1.2.7") < 0) {
                    int i6 = 1;
                    while (true) {
                        byte[] bArr = this.fModeList[i5].Mix;
                        if (i6 >= bArr.length) {
                            break;
                        }
                        if (bArr[i6] == 0) {
                            bArr[i6] = -1;
                        }
                        i6++;
                    }
                }
                this.fModeList[i5].FXs.j(al0Var);
                int i7 = 0;
                while (true) {
                    Effect[] effectArr = this.fModeList[i5].Effects;
                    if (i7 >= effectArr.length) {
                        break;
                    }
                    effectArr[i7].ReadFromStream(al0Var);
                    i7++;
                }
                if (this.fVersion.compareToIgnoreCase("1.4.3") < 0) {
                    Effect effect = this.fModeList[i5].Effects[1];
                    effect.setMode((byte) (effect.getMode() | (this.fMusic.fKeyConnectionMethod * 16)));
                }
                al0Var.read(this.fModeList[i5].MixVol);
                al0Var.read(this.fModeList[i5].MixPan);
                if (this.fVersion.compareToIgnoreCase("1.4.0") >= 0) {
                    this.fModeList[i5].ScriptText = al0Var.e();
                }
                if (this.fVersion.compareToIgnoreCase("1.4.4") >= 0) {
                    ArrayList arrayList = new ArrayList();
                    int iB = al0Var.b();
                    for (int i8 = 0; i8 < iB; i8++) {
                        arrayList.add(al0Var.e());
                    }
                    if (arrayList.size() > 0) {
                        InstMode instMode = this.fModeList[i5];
                        if (instMode.ScriptSetting == null) {
                            instMode.ScriptSetting = new ad4();
                        }
                        this.fModeList[i5].ScriptSetting.b();
                        this.fModeList[i5].ScriptSetting.a.addAll(arrayList);
                        ArrayList arrayList2 = this.fModeList[i5].ScriptSetting.b;
                        int iB2 = al0Var.b();
                        for (int i9 = 0; i9 < iB2; i9++) {
                            arrayList2.add(al0Var.e());
                        }
                    }
                }
            }
        }
        if (this.fVersion.compareToIgnoreCase("1.4.1") < 0) {
            int iCompareToIgnoreCase = this.fVersion.compareToIgnoreCase("1.4.0");
            InstSnd[] instSndArr = this.fSndList;
            if (iCompareToIgnoreCase >= 0) {
                instSndArr[0].Attack = al0Var.readByte();
                this.fSndList[0].Decay = al0Var.readByte();
                this.fSndList[0].Sustain = al0Var.readByte();
            } else {
                InstSnd instSnd = instSndArr[0];
                SndParam sndParam3 = sndParamArr[0];
                instSnd.Attack = sndParam3.Attack;
                instSnd.Decay = sndParam3.Decay;
                instSnd.Sustain = sndParam3.Sustain;
            }
            this.fSndList[0].Release = al0Var.readByte();
            this.fSndList[0].PitchMode = al0Var.readByte();
            this.fSndList[0].AutoRelease = al0Var.readBoolean();
        }
        this.fSndCount = al0Var.readByte();
        int i10 = 0;
        while (true) {
            if (i10 >= Math.max((int) this.fSndCount, 1)) {
                break;
            }
            if (i10 != 0 || this.fVersion.compareToIgnoreCase("1.4.1") >= 0) {
                this.fSndList[i10].Name = al0Var.e();
                int iCompareToIgnoreCase2 = this.fVersion.compareToIgnoreCase("1.4.0");
                InstSnd[] instSndArr2 = this.fSndList;
                if (iCompareToIgnoreCase2 >= 0) {
                    instSndArr2[i10].Attack = al0Var.readByte();
                    this.fSndList[i10].Decay = al0Var.readByte();
                    this.fSndList[i10].Sustain = al0Var.readByte();
                } else {
                    InstSnd instSnd2 = instSndArr2[i10];
                    SndParam sndParam4 = sndParamArr[i10];
                    instSnd2.Attack = sndParam4.Attack;
                    instSnd2.Decay = sndParam4.Decay;
                    instSnd2.Sustain = sndParam4.Sustain;
                }
                this.fSndList[i10].Release = al0Var.readByte();
                this.fSndList[i10].PitchMode = al0Var.readByte();
                this.fSndList[i10].AutoRelease = al0Var.readBoolean();
            }
            byte b2 = al0Var.readByte();
            byte b3 = b2 >= 0 ? b2 : (byte) 128;
            for (int i11 = 0; i11 < b3; i11++) {
                byte b4 = al0Var.readByte();
                if (b4 < 0) {
                    b4 = 0;
                }
                InstSec[] instSecArr = this.fSndList[i10].fList;
                if (instSecArr[b4] == null) {
                    instSecArr[b4] = new InstSec();
                }
                this.fSndList[i10].fList[b4].ReadFromStream(al0Var, this.fVersion, false, this.fMusic);
            }
            i10++;
        }
        byte[] bArr2 = this.fSelected;
        if (bArr2[0] == 0) {
            bArr2[0] = 1;
            for (int i12 = 0; i12 < 128; i12++) {
                if (this.fSndList[0].SectionExists(i12)) {
                    byte[] bArr3 = this.fSelected;
                    bArr3[i4] = (byte) i12;
                    i4++;
                    if (i4 >= bArr3.length) {
                        return;
                    }
                }
            }
        }
    }
