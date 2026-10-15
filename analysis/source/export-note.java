    public void ExportKeyToAudioFile(String str, int i, int i2, int i3, int i4, int[] iArr, int i5, int i6) {
        int i7;
        short[] sArr;
        int i8 = this.fOutSampleRate / 100;
        this.fOutBufferSize = i8;
        long[] jArr = new long[i8];
        long[] jArr2 = new long[i8];
        Channels channels = new Channels(this);
        channels.fType = 6;
        int i9 = this.fOutBufferSize;
        channels.size = i9;
        channels.mixer = 1;
        channels.bufL = jArr;
        channels.bufR = jArr2;
        channels.fMode = i2;
        short[] sArr2 = new short[i9 * 2];
        int i10 = 0;
        while (i10 < iArr.length) {
            int i11 = iArr[i10];
            if (i11 > 0) {
                sArr = sArr2;
                channels.OpenKey(i10, (i4 * 12) + i3, 100, false, -1, i11, i5, 100, 0, 0, 0, 0, 1000, i6, i6, false, -1, 0);
                try {
                    Channel channel = channels.fList[i10];
                    if (channel.Release > 5.0f) {
                        channel.Release = 5.0f;
                    }
                } catch (Exception unused) {
                }
            } else {
                sArr = sArr2;
            }
            i10++;
            sArr2 = sArr;
        }
        short[] sArr3 = sArr2;
        try {
            a aVar = new a(str, i, this.fOutSampleRate, this.fOutChannels, this.fOutBitsPerSample);
            int i12 = 0;
            while (!channels.isEmpty()) {
                try {
                    Arrays.fill(jArr, 0L);
                    Arrays.fill(jArr2, 0L);
                    channels.Process();
                    int i13 = 0;
                    while (true) {
                        i7 = this.fOutBufferSize;
                        if (i13 >= i7) {
                            break;
                        }
                        jArr[i13] = jArr[i13] / 200;
                        jArr2[i13] = jArr2[i13] / 200;
                        long j = jArr[i13];
                        long j2 = j * j;
                        jArr[i13] = j - (j < 0 ? j2 / (-128000) : j2 / 128000);
                        long j3 = jArr2[i13];
                        long j4 = j3 - (j3 < 0 ? (j3 * j3) / (-128000) : (j3 * j3) / 128000);
                        jArr2[i13] = j4;
                        int i14 = i13 << 1;
                        sArr3[i14] = (short) jArr[i13];
                        sArr3[i14 + 1] = (short) j4;
                        i13++;
                    }
                    aVar.c(sArr3, i7);
                    int i15 = i12 + 1;
                    if (i12 < 4) {
                        for (int i16 = 0; i16 < iArr.length; i16++) {
                            if (iArr[i16] > 0) {
                                channels.Close(i16);
                            }
                        }
                    }
                    i12 = i15;
                } catch (Exception unused2) {
                }
            }
            aVar.a();
        } catch (Exception unused3) {
        }
    }
