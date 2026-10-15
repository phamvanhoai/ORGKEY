    public void Open(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, byte b, byte b2, byte b3, byte b4, boolean z, boolean z2, int i10, int i11, InstSec instSec, boolean z3, int i12) {
        int i13;
        int i14 = this.fType;
        Music music = this.fMusic;
        int i15 = i14 == 0 ? music.fMaxMusicChannel : music.fMaxRhythmChannel;
        Channel[] channelArr = this.fList;
        if (channelArr == null || channelArr.length < i15 + 1) {
            this.fListCount = 0;
            this.fList = new Channel[i15 + 1];
            int i16 = 0;
            while (true) {
                Channel[] channelArr2 = this.fList;
                if (i16 >= channelArr2.length) {
                    break;
                }
                channelArr2[i16] = new Channel();
                Channel channel = this.fList[i16];
                channel.idx = 0L;
                if (this.mixer == 3 && channel.Handle == 0) {
                    channel.Handle = NMusic.ChannelOpen();
                }
                i16++;
            }
        }
        this.fListCount = i15;
        if (i12 == -1) {
            if (isEmpty()) {
                this.fListCounter = 0L;
            }
            long j = Long.MAX_VALUE;
            int i17 = i12;
            long j2 = 0;
            i13 = 0;
            while (true) {
                if (i13 >= this.fListCount) {
                    i13 = i17;
                    break;
                }
                long j3 = this.fList[i13].idx;
                if (j3 == j2) {
                    break;
                }
                if (j > j3) {
                    i17 = i13;
                    j = j3;
                }
                i13++;
                j2 = 0;
            }
            this.fListCounter++;
        } else {
            i13 = i12;
        }
        Channel channel2 = this.fList[i13];
        if (i12 != -1) {
            channel2.Key = i3;
            channel2.BaseKey = i4;
            channel2.Length = i7;
            channel2.PitchMove = i6;
            channel2.Pitch = i5;
            channel2.dPitch = ((double) (i5 * i6)) / 1000.0d;
            channel2.iFirstVolumeL = i8;
            channel2.iFirstVolumeR = i9;
            channel2.PedalMode = i10;
            channel2.Pedal = i11;
            channel2.InstSec = instSec;
            return;
        }
        NMusic.ChannelReset(channel2.Handle);
        channel2.ID = i;
        channel2.Event = i2;
        channel2.Key = i3;
        channel2.BaseKey = i4;
        channel2.Length = i7;
        channel2.PitchMove = i6;
        channel2.Pitch = i5;
        channel2.iPitch = 0;
        channel2.dPitch = ((double) (i5 * i6)) / 1000.0d;
        channel2.diPitch = 0.0d;
        channel2.dPitchChangeValue = 0.0d;
        channel2.VolumeL = i8;
        channel2.VolumeR = i9;
        channel2.iFirstVolumeL = i8;
        channel2.iFirstVolumeR = i9;
        channel2.iVolumeL = 0;
        channel2.iVolumeR = 0;
        channel2.dVolumeL = i8;
        channel2.dVolumeR = i9;
        channel2.diVolumeL = Sequence.PPQ;
        channel2.diVolumeR = Sequence.PPQ;
        channel2.dVolumeStarter = 1.0f;
        channel2.iKeyRepeatStart = 0;
        channel2.iKeyRepeatCount = 0;
        channel2.iKeyConnectionStart = 0;
        channel2.iKeyConnectionPitch = 0;
        channel2.iKeyConnectionSpeed = 0;
        channel2.iPitchShakeStart = 0;
        channel2.iPitchShakeCount = 0;
        channel2.iVolumeShakeStart = 0;
        channel2.iVolumeShakeCount = 0;
        channel2.Envelope = (byte) 0;
        channel2.VolumeMoveL = Sequence.PPQ;
        channel2.VolumeMoveR = Sequence.PPQ;
        channel2.Attack = b;
        channel2.Decay = b2;
        channel2.Sustain = b3 / 100.0f;
        float f = b4;
        channel2.Release = f;
        if (f == Sequence.PPQ) {
            channel2.Release = 0.1f;
        }
        channel2.ReleaseAuto = z;
        channel2.ForceLoop = z2;
        channel2.PedalMode = i10;
        channel2.Pedal = i11;
        channel2.InstSec = instSec;
        channel2.Close = false;
        channel2.Position = 0L;
        channel2.EffectAllow = z3;
        channel2.idx = this.fListCounter;
    }
