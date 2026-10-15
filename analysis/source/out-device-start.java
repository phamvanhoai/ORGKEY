    /* JADX WARN: Code duplicated, block: B:69:0x0105  */
    public void OutDeviceStart() {
        boolean zStart;
        if (this.fOutActive) {
            return;
        }
        this.fTracks.ProcessListClear();
        x13 x13Var = this.fPlatform;
        int i = this.fOutSampleRate;
        ew ewVar = x13Var.a;
        ewVar.getClass();
        ArrayList arrayList = new ArrayList(ewVar.b);
        int size = arrayList.size();
        int i2 = 0;
        while (i2 < size) {
            Object obj = arrayList.get(i2);
            i2++;
            ((c23) obj).getClass();
            x13Var.d = AudioTrack.getMinBufferSize(i, 12, 2);
        }
        int i3 = x13Var.d;
        this.fIsOpenSL = false;
        int i4 = this.fLowLatencyMethod;
        if (i4 == 1 || i4 == 2) {
            boolean z = (this.fPlatform.b.isEmpty() || this.fPlatform.c.isEmpty()) ? false : true;
            this.fIsOpenSL = z;
            if (z) {
                String str = this.fPlatform.b;
                int i5 = this.fOutSampleRate;
                String str2 = b44.a;
                try {
                    i5 = Integer.parseInt(str);
                } catch (NumberFormatException unused) {
                }
                this.fOutSampleRate = i5;
                int i6 = i3 / 4;
                try {
                    i6 = Integer.parseInt(this.fPlatform.c);
                } catch (NumberFormatException unused2) {
                }
                i3 = i6 * 4;
            }
        }
        OutDeviceRebuild(true);
        this.fOutActive = true;
        this.fOutThread = new Thread() { // from class: sofeh.music.Music.2
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                Music music;
                b44.h();
                Thread.currentThread().setPriority(10);
                Music music2 = Music.this;
                int i7 = music2.fOutBufferSize * 2;
                if (!music2.fIsOpenSL) {
                    while (true) {
                        music = Music.this;
                        if (!music.fOutActive) {
                            break;
                        } else {
                            music.fPlatform.a.i(new zd1(Music.DefBitsPerSample));
                        }
                    }
                } else {
                    while (true) {
                        music = Music.this;
                        if (!music.fOutActive) {
                            break;
                        }
                        try {
                            music.ProcessOut(false);
                            Music music3 = Music.this;
                            if (music3.fInActive && music3.fIsOpenSLRecord) {
                                music3.ProcessIn(music3.fInBufferSize);
                                Music music4 = Music.this;
                                NOpenSL.MixWrite(music4.fOutBuffer, music4.fInBuffer, i7, music4.volumeLeft, Music.this.volumeRight);
                            } else {
                                NOpenSL.Write(music3.fOutBuffer, i7);
                            }
                            Music music5 = Music.this;
                            a aVar = music5.fDirectRecordWriter;
                            if (aVar != null) {
                                aVar.c(music5.fOutBuffer, music5.fOutBufferSize);
                            }
                        } catch (Exception unused3) {
                        }
                    }
                }
                music.fNow = 0L;
                Thread.currentThread().setPriority(5);
                Music.this.fPlatform.f();
            }
        };
        this.fPlatform.f();
        int i7 = i3 / 4;
        int i8 = this.fOutBufferCount + 2;
        int i9 = i7;
        while (i9 >= 960) {
            i9 /= 2;
        }
        if (this.fOutBufferCount == 0) {
            i8 = i9 < 512 ? 5 : 4;
            if (NMusic.SysBits() == 32) {
                i8++;
            }
        }
        while (i9 * i8 < i7) {
            i8++;
        }
        if (i8 < 3) {
            i8 = 3;
        }
        int i10 = (i8 >= 4 || i9 > 150) ? i8 : 4;
        if (i10 > 8) {
            i10 = 8;
        }
        if (i9 < 64) {
            i9 = 64;
        }
        if (i9 > 4096) {
            i9 = 4096;
        }
        if (this.fIsOpenSL) {
            try {
                zStart = NOpenSL.Start(this.fLowLatencyMethod == 1 ? this : null, this.fOutSampleRate, i9, i10, this.fIsOpenSLRecord);
            } catch (Exception unused3) {
                zStart = false;
            }
            if (zStart) {
                StringBuilder sb = new StringBuilder();
                ep4.w(sb, this.fOutSampleRate, " Hz, ", i9, " x ");
                sb.append(i10);
                sb.append(" (");
                sb.append(i7);
                sb.append(" fpb)");
                this.fOpenSL = sb.toString();
            } else {
                this.fIsOpenSL = false;
                this.fOpenSL = "OFF";
                this.fPlatform.a.i(new fx2(this.fOutSampleRate, i3, this.fMusic));
            }
        } else {
            this.fIsOpenSL = false;
            this.fOpenSL = "OFF";
            this.fPlatform.a.i(new fx2(this.fOutSampleRate, i3, this.fMusic));
        }
        try {
            if (this.fPlatform.e()) {
                if (this.fLowLatencyMethod == 1 && this.fIsOpenSL) {
                    return;
                }
                this.fOutThread.start();
            }
        } catch (Exception unused4) {
        }
    }
