    public void ProcessOut(boolean z) {
        this.fNow += 10;
        this.fTracks.PlayNext();
        Arrays.fill(this.bufML, 0L);
        Arrays.fill(this.bufMR, 0L);
        Arrays.fill(this.bufWL, 0L);
        Arrays.fill(this.bufWR, 0L);
        Arrays.fill(this.bufRL, 0L);
        Arrays.fill(this.bufRR, 0L);
        this._isWave = false;
        this._count = 0;
        while (this._count < this.fTracks.fProcessList.size()) {
            this._count2 = 0;
            Tracks tracks = this.fTracks;
            this._trackList = tracks.TrackList(tracks.fProcessList.get(this._count).Group);
            while (this._count2 < this._trackList.size()) {
                Track track = this._trackList.get(this._count2);
                this._track = track;
                if (!track.fChannels.isEmpty() || !this._track.fChannels.fFXs.l.isEmpty()) {
                    Channels channels = this._track.fChannels;
                    if (channels.fType == 2 && !channels.isEmpty() && !this.fFXWave.l.isEmpty()) {
                        this._isWave = true;
                    }
                    this._track.fChannels.Process();
                }
                this._count2++;
            }
            this._count++;
        }
        this._volume = this.fFXMusic.b;
        this._volumeDiv = 20000L;
        if (this.fFadeValue != 0) {
            this._volumeDiv = 20000000000000L;
        }
        this._count = 0;
        boolean z2 = true;
        while (this._count < this.fOutBufferSize) {
            if (z2) {
                try {
                    if (!this.fFXMusic.l.isEmpty()) {
                        this.fFXMusic.h(this.bufML, this.bufMR, this._count);
                    }
                    if (this._isWave) {
                        this.fFXWave.h(this.bufWL, this.bufWR, this._count);
                    }
                    if (!this.fFXRhythm.l.isEmpty()) {
                        this.fFXRhythm.h(this.bufRL, this.bufRR, this._count);
                    }
                } catch (Exception unused) {
                    z2 = false;
                }
            }
            if (z) {
                long[] jArr = this.bufWL;
                int i = this._count;
                long j = jArr[i];
                long j2 = this.fInBuffer[i] * 500;
                jArr[i] = j + j2;
                long[] jArr2 = this.bufWR;
                jArr2[i] = jArr2[i] + j2;
            }
            long j3 = this.fFadeValue;
            if (j3 != 0) {
                if (j3 < 0 || this.fFadeDelay <= 0) {
                    long j4 = this.fFadeVolume + j3;
                    this.fFadeVolume = j4;
                    if (j4 < 0) {
                        this.fFadeVolume = 0L;
                        j4 = 0;
                    }
                    if (j4 > MaxFade) {
                        this.fFadeVolume = MaxFade;
                    }
                }
                this._volume = this.fFadeVolume * ((long) this.fFXMusic.b);
            }
            long[] jArr3 = this.bufML;
            int i2 = this._count;
            long j5 = jArr3[i2] + this.bufWL[i2] + this.bufRL[i2];
            long j6 = this._volume;
            long j7 = this._volumeDiv;
            long j8 = (j5 * j6) / j7;
            this._l = j8;
            long jProcessStereoRight = (((this.bufMR[i2] + this.bufWR[i2]) + this.bufRR[i2]) * j6) / j7;
            this._r = jProcessStereoRight;
            int i3 = this.fGlobalReverbLevel;
            if (i3 != 0) {
                long j9 = this.globalReverb;
                if (j9 != 0) {
                    this._l = NGVerb.processStereoLeft(j9, j8, jProcessStereoRight, i3);
                    jProcessStereoRight = NGVerb.processStereoRight();
                    this._r = jProcessStereoRight;
                } else {
                    jProcessStereoRight = jProcessStereoRight;
                }
            }
            long j10 = this._l;
            long j11 = j10 * j10;
            long j12 = j10 - (j10 < 0 ? j11 / (-128000) : j11 / 128000);
            this._l = j12;
            long j13 = jProcessStereoRight - (jProcessStereoRight < 0 ? (jProcessStereoRight * jProcessStereoRight) / (-128000) : (jProcessStereoRight * jProcessStereoRight) / 128000);
            this._r = j13;
            int i4 = this._count;
            int i5 = i4 << 1;
            this._count2 = i5;
            short[] sArr = this.fOutBuffer;
            sArr[i5] = (short) j12;
            sArr[i5 + 1] = (short) j13;
            this._count = i4 + 1;
        }
        long j14 = this.fFadeValue;
        if (j14 != 0) {
            if (j14 >= 0) {
                this.fFadeDelay -= 10;
                if (this.fFadeVolume == MaxFade) {
                    this.fFadeValue = 0L;
                    return;
                }
                return;
            }
            if (this.fFadeVolume == 0) {
                int i6 = this.fFadeDelay;
                if (i6 > 0) {
                    this.fFadeDelay = i6 - 10;
                    return;
                }
                Cut();
                Stop(0);
                this.fFadeValue = 0L;
                this.fFadeVolume = MaxFade;
            }
        }
    }
