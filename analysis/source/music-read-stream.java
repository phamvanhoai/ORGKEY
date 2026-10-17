    @Override // sofeh.music.MusicObject
    public void ReadFromStream(al0 al0Var, boolean z) throws IOException {
        int iB;
        this.WaveListIsEnable = true;
        super.ReadFromStream(al0Var, z);
        this.fInfoAlbum = al0Var.e();
        this.fInfoType = al0Var.e();
        this.fInfoCopyright = al0Var.e();
        int iB2 = al0Var.b();
        byte[] bArr = new byte[iB2];
        byte[] bArr2 = new byte[16];
        if (iB2 <= 0 || this.fVersion.compareToIgnoreCase("1.3.2") < 0) {
            iB = 1;
        } else {
            iB = al0Var.b() ^ Sdk.SDKError.Reason.AD_RESPONSE_EMPTY_VALUE;
            al0Var.read(bArr2, 0, iB);
        }
        al0Var.read(bArr, 0, iB2);
        if (iB2 > 0 && this.fVersion.compareToIgnoreCase("1.3.2") >= 0) {
            for (int i = 0; i < iB2; i++) {
                bArr[i] = (byte) (bArr[i] ^ bArr2[i % iB]);
            }
        }
        for (int i2 = 0; i2 < iB2; i2++) {
            byte b = bArr[i2];
            if (b != 13) {
                bArr[i2] = (byte) ((b & 255) ^ 194);
            }
        }
        String[] strArrSplit = new String(bArr).split("\r", -1);
        String str = strArrSplit[0];
        this.fInfoPassword = str;
        this.fPasswordOk = str.isEmpty();
        this.fInfoUser = new String[strArrSplit.length - 1];
        for (int i3 = 1; i3 < strArrSplit.length; i3++) {
            this.fInfoUser[i3 - 1] = strArrSplit[i3];
        }
        this.fInfoReserved = al0Var.c();
        this.fStereoMode = al0Var.b();
        this.fTempo = al0Var.b();
        if (this.fVersion.compareToIgnoreCase("1.3.0") < 0) {
            this.fTempo = (this.fTempo * 120) / 100;
        }
        this.fRecordCounter = al0Var.b();
        this.fTempoLock = al0Var.readBoolean();
        byte[] bArr3 = this.fReserved;
        al0Var.read(bArr3, 0, bArr3.length);
        int iB3 = al0Var.b();
        if (iB2 > 0 && this.fVersion.compareToIgnoreCase("1.3.2") >= 0) {
            int i4 = bArr[0];
            if (i4 < 0) {
                i4 += MaxChannelCount;
            }
            iB3 ^= i4;
        }
        int iB4 = al0Var.b();
        if (iB2 > 0 && this.fVersion.compareToIgnoreCase("1.3.2") >= 0) {
            int i5 = bArr2[0];
            if (i5 < 0) {
                i5 += MaxChannelCount;
            }
            iB4 ^= i5;
        }
        WaveListClear();
        if (this.fVersion.compareToIgnoreCase("1.2.7") >= 0 && iB3 > 0) {
            int[] iArr = new int[iB3];
            this.WaveListLength = iArr;
            Arrays.fill(iArr, 0);
            if (this.nativeMem) {
                ByteBuffer[] byteBufferArr = new ByteBuffer[iB3];
                this.WaveBList = byteBufferArr;
                Arrays.fill(byteBufferArr, (Object) null);
            } else {
                short[][] sArr = new short[iB3][];
                this.WaveAList = sArr;
                Arrays.fill(sArr, (Object) null);
            }
            for (int i6 = 0; i6 < iB4; i6++) {
                int iB5 = al0Var.b();
                int iB6 = al0Var.b();
                this.WaveListLength[iB5] = iB6;
                ByteBuffer byteBufferAllocate = ByteBuffer.allocate(iB6);
                byteBufferAllocate.order(ByteOrder.LITTLE_ENDIAN);
                al0Var.read(byteBufferAllocate.array(), 0, iB6);
                if (this.nativeMem) {
                    this.WaveBList[iB5] = NMusic.GetMemSafe(iB6);
                    this.WaveBList[iB5].put(byteBufferAllocate);
                } else {
                    this.WaveAList[iB5] = new short[iB6 / 2];
                    byteBufferAllocate.asShortBuffer().get(this.WaveAList[iB5]);
                }
                byteBufferAllocate.clear();
            }
        }
        if (this.fVersion.compareToIgnoreCase("1.2.1") < 0) {
            Channels channels = new Channels(this);
            channels.ReadFromStream(al0Var, this.fVersion);
            z01 z01Var = this.fFXMusic;
            z01 z01Var2 = channels.fFXs;
            z01Var.b = z01Var2.b;
            z01Var.c = z01Var2.c;
            z01Var.d = z01Var2.d;
            z01Var.f = z01Var2.f;
            z01Var.g = z01Var2.g;
        } else {
            this.fFXMusic.j(al0Var);
            this.fFXWave.j(al0Var);
            this.fFXRhythm.j(al0Var);
        }
        this.fInstruments.ReadFromStream(al0Var, z);
        this.fSystems.ReadFromStream(al0Var, z);
        this.fTracks.ReadFromStream(al0Var, z);
        if (this.fVersion.compareToIgnoreCase("1.4.2") < 0) {
            this.fTracks.Group(0).fxs.d(this.fFXRhythm);
        }
        if (this.fVersion.compareToIgnoreCase("1.4.7") >= 0) {
            String[] strArrSplit2 = al0Var.e().split(";");
            String[] strArr = InstrumentFamily.NameListDefault;
            String[] strArr2 = InstrumentFamily.NameList;
            System.arraycopy(strArr, 1, strArr2, 1, strArr2.length - 1);
            int i7 = 0;
            while (i7 < strArrSplit2.length) {
                int i8 = i7 + 1;
                String[] strArr3 = InstrumentFamily.NameList;
                if (i8 < strArr3.length && !strArrSplit2[i7].isEmpty()) {
                    strArr3[i8] = strArrSplit2[i7];
                }
                i7 = i8;
            }
        }
        this.WaveListIsEnable = false;
    }