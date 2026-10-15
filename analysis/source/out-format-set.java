    public void OutFormatSet(int i, int i2, int i3, int i4) throws Throwable {
        boolean z;
        boolean z2 = this.fOutActive;
        if (z2) {
            OutDeviceStop();
        }
        try {
            long j = this.globalReverb;
            if (j == 0 || this.fOutSampleRate != i) {
                z = z2;
                try {
                    try {
                        this.globalReverb = NGVerb.open(j, i, 41.0f, 40.0f, 7.0f, 0.8f, 0.5f, 0.5f, 0.5f, 0.5f);
                    } catch (Exception unused) {
                        this.globalReverb = 0L;
                    }
                } catch (Throwable th) {
                    th = th;
                    this.fOutSampleRate = i;
                    this.fOutBitsPerSample = (byte) i2;
                    this.fOutChannels = (byte) i3;
                    this.fOutBufferCount = i4;
                    if (z) {
                        OutDeviceStart();
                    }
                    throw th;
                }
            } else {
                z = z2;
            }
            this.fOutSampleRate = i;
            this.fOutBitsPerSample = (byte) i2;
            this.fOutChannels = (byte) i3;
            this.fOutBufferCount = i4;
            if (z) {
                OutDeviceStart();
            }
        } catch (Throwable th2) {
            th = th2;
            z = z2;
        }
    }
