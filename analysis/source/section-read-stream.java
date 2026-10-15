    public void ReadFromStream(al0 al0Var, String str, boolean z, Music music) throws IOException {
        int i;
        int[] iArr;
        Clear();
        this.Name = al0Var.a();
        int iC = al0Var.c() & 65535;
        this.WaveData.BitsPerSample = al0Var.readByte();
        this.WaveData.Channels = al0Var.readByte();
        this.WaveData.SampleRate = al0Var.b();
        WaveData waveData = this.WaveData;
        if (waveData.SampleRate == 0) {
            waveData.SampleRate = iC;
        }
        waveData.WaveIndex = al0Var.b();
        if (str.compareToIgnoreCase("1.2.7") < 0 || music == null || !music.WaveListIsEnable) {
            this.WaveData.WaveIndex = 0;
        }
        this.WaveData.Length = al0Var.b();
        al0Var.b();
        WaveData waveData2 = this.WaveData;
        if (waveData2.SampleRate == 0) {
            waveData2.SampleRate = 48000;
        }
        waveData2.BitsPerSample = (byte) 16;
        byte b = waveData2.Channels;
        if (b < 1 || b > 2) {
            waveData2.Channels = (byte) 2;
        }
        if (str.compareToIgnoreCase("1.10") < 0) {
            this.WaveData.Channels = (byte) 2;
        }
        this.Volume = al0Var.b();
        this.Pan = al0Var.b();
        this.Tune = al0Var.b();
        this.Start = al0Var.b();
        this.Loop = al0Var.readByte();
        al0Var.readByte();
        al0Var.readByte();
        al0Var.readByte();
        this.LoopStart = al0Var.b();
        int iB = al0Var.b();
        this.LoopEnd = iB;
        int iMin = Math.min(this.WaveData.Length - 1, iB);
        this.LoopEnd = iMin;
        this.LoopStart = Math.min(iMin, this.LoopStart);
        for (int i2 = 0; i2 < 4; i2++) {
            al0Var.readInt();
        }
        WaveData waveData3 = this.WaveData;
        if (waveData3.Channels == 1) {
            waveData3._PS = 0;
            waveData3._PO = 0;
        } else {
            waveData3._PS = 1;
            waveData3._PO = 1;
        }
        if (waveData3.Length > 0) {
            if (music != null && (i = waveData3.WaveIndex) != 0 && (iArr = music.WaveListLength) != null && (music.WaveAList != null || music.WaveBList != null)) {
                int i3 = i - 1;
                if (i3 >= iArr.length) {
                    waveData3.Length = 0;
                    waveData3.WaveA = null;
                    waveData3.WaveB = null;
                    return;
                }
                waveData3.ByteLengthSet(iArr[i3]);
                boolean z2 = music.nativeMem;
                WaveData waveData4 = this.WaveData;
                if (z2) {
                    waveData4.WaveB = music.WaveBList[waveData4.WaveIndex - 1];
                    return;
                } else {
                    waveData4.WaveA = music.WaveAList[waveData4.WaveIndex - 1];
                    return;
                }
            }
            waveData3.WaveIndex = 0;
            if (z) {
                al0Var.skipBytes(waveData3.ByteLength());
                WaveData waveData5 = this.WaveData;
                waveData5.Length = 0;
                waveData5.WaveA = null;
                waveData5.WaveB = null;
                return;
            }
            ByteBuffer byteBufferAllocate = ByteBuffer.allocate(waveData3.ByteLength());
            byteBufferAllocate.order(ByteOrder.LITTLE_ENDIAN);
            al0Var.read(byteBufferAllocate.array(), 0, this.WaveData.ByteLength());
            boolean z3 = music.nativeMem;
            WaveData waveData6 = this.WaveData;
            if (z3) {
                waveData6.WaveB = NMusic.GetMemSafe(waveData6.ByteLength());
                this.WaveData.WaveB.put(byteBufferAllocate);
            } else {
                waveData6.WaveA = new short[waveData6.ByteLength() / 2];
                if (this.WaveData.WaveA != null) {
                    byteBufferAllocate.asShortBuffer().get(this.WaveData.WaveA);
                }
            }
            byteBufferAllocate.clear();
        }
    }
