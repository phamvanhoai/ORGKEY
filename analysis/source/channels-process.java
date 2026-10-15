    /* JADX WARN: Code duplicated, block: B:93:0x0206  */
    /* JADX WARN: Type inference failed for: r3v10, types: [boolean] */
    /* JADX WARN: Type inference failed for: r3v16, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r3v18 */
    /* JADX WARN: Type inference failed for: r3v28 */
    /* JADX WARN: Type inference failed for: r3v30 */
    /* JADX WARN: Type inference failed for: r3v31 */
    /* JADX WARN: Type inference failed for: r3v32 */
    /* JADX WARN: Type inference failed for: r3v33 */
    /* JADX WARN: Type inference failed for: r3v37 */
    /* JADX WARN: Type inference failed for: r3v9 */
    public synchronized void Process() {
        long j;
        ?? r3;
        float f;
        double d;
        boolean z;
        WaveData waveData;
        short[] sArr;
        int i;
        int i2;
        int i3;
        int i4;
        int i5;
        float f2;
        int i6;
        int i7;
        int i8;
        char c;
        char c2;
        Channel channel;
        try {
            byte b = 1;
            this.ProcessCounter++;
            long j2 = 0;
            if (this.fFXs.l.isEmpty()) {
                this._bufL = this.bufL;
                this._bufR = this.bufR;
            } else {
                Arrays.fill(this.fMusic.bufCL, 0L);
                Arrays.fill(this.fMusic.bufCR, 0L);
                Music music = this.fMusic;
                this._bufL = music.bufCL;
                this._bufR = music.bufCR;
            }
            z01 z01Var = this.fFXs;
            int i9 = z01Var.c;
            int i10 = z01Var.b;
            if (i9 > 0) {
                this._volumeR = i10;
                this._volumeL = ((100 - i9) * i10) / 100;
            } else {
                this._volumeL = i10;
                this._volumeR = ((i9 + 100) * i10) / 100;
            }
            this._index = 0;
            while (true) {
                int i11 = this._index;
                if (i11 >= this.fListCount) {
                    break;
                }
                Channel channel2 = this.fList[i11];
                if (channel2.idx > j2) {
                    this._channel = channel2;
                    InstSec instSec = channel2.InstSec;
                    this._instSec = instSec;
                    int i12 = channel2.Pitch * channel2.PitchMove;
                    channel2.iPitch = i12 / 1000;
                    channel2.diPitch = channel2.dPitch;
                    Music music2 = this.fMusic;
                    this._pitchSpeed = music2.fPitchSpeed;
                    if (music2.fLoopTempo && instSec.Loop == 3) {
                        int i13 = (i12 * music2.fTempo) / 120000;
                        channel2.iPitch = i13;
                        double d2 = i13;
                        channel2.dPitch = d2;
                        channel2.diPitch = d2;
                        channel2.dPitchChangeValue = 0.0d;
                        this._pitchSpeed = 0;
                    }
                    byte b2 = channel2.Envelope;
                    if (b2 == 0) {
                        float f3 = channel2.Attack;
                        if (f3 >= 0.1f) {
                            float f4 = channel2.dVolumeL;
                            float f5 = f3 * this.size * 10.0f;
                            channel2.VolumeMoveL = f4 / f5;
                            channel2.VolumeMoveR = channel2.dVolumeR / f5;
                            channel2.dVolumeL = Sequence.PPQ;
                            channel2.dVolumeR = Sequence.PPQ;
                        }
                        channel2.Envelope = b;
                        b2 = b;
                    } else if (b2 == b) {
                        float f6 = channel2.Attack - 0.1f;
                        channel2.Attack = f6;
                        if (f6 < 0.1f) {
                            float f7 = channel2.Decay;
                            if (f7 > Sequence.PPQ) {
                                float f8 = channel2.dVolumeL;
                                float f9 = channel2.Sustain;
                                float f10 = f7 * this.size * 10.0f;
                                channel2.VolumeMoveL = (-(f8 - (f8 * f9))) / f10;
                                float f11 = channel2.dVolumeR;
                                channel2.VolumeMoveR = (-(f11 - (f9 * f11))) / f10;
                            } else {
                                channel2.VolumeMoveL = Sequence.PPQ;
                                channel2.VolumeMoveR = Sequence.PPQ;
                            }
                            channel2.Envelope = (byte) 2;
                            b2 = 2;
                        }
                    } else if (b2 == 2) {
                        float f12 = channel2.Decay - 0.1f;
                        channel2.Decay = f12;
                        if (f12 < 0.1f) {
                            channel2.VolumeMoveL = Sequence.PPQ;
                            channel2.VolumeMoveR = Sequence.PPQ;
                            channel2.Envelope = (byte) 3;
                            b2 = 3;
                        }
                    }
                    boolean z2 = channel2.Close;
                    if (z2 || (channel2.ReleaseAuto && !channel2.ForceLoop && b2 > 2)) {
                        if (b2 < 4) {
                            float f13 = -channel2.dVolumeL;
                            float f14 = channel2.Release * this.size * 10.0f;
                            channel2.VolumeMoveL = f13 / f14;
                            channel2.VolumeMoveR = (-channel2.dVolumeR) / f14;
                            channel2.Envelope = (byte) 4;
                            b2 = 4;
                        }
                        int i14 = channel2.PedalMode;
                        if (i14 == -1) {
                            i14 = channel2.Pedal;
                        }
                        this._pedal = i14;
                        if (i14 == 0 && z2 && b2 < 5) {
                            float f15 = channel2.Release;
                            if (f15 > 20.0f) {
                                channel2.Release = 20.0f;
                                f15 = 20.0f;
                            }
                            float f16 = -channel2.dVolumeL;
                            float f17 = f15 * this.size;
                            channel2.VolumeMoveL = f16 / f17;
                            channel2.VolumeMoveR = (-channel2.dVolumeR) / f17;
                            channel2.Envelope = (byte) 5;
                        }
                    } else {
                        b = b;
                    }
                    float f18 = channel2.dVolumeL;
                    float f19 = channel2.VolumeMoveL;
                    float f20 = this.size;
                    float f21 = (f19 * f20) + f18;
                    channel2.dVolumeL = f21;
                    channel2.dVolumeR = (channel2.VolumeMoveR * f20) + channel2.dVolumeR;
                    channel2.VolumeL = Math.round(f21);
                    Channel channel3 = this._channel;
                    channel3.VolumeR = Math.round(channel3.dVolumeR);
                    Channel channel4 = this._channel;
                    int i15 = channel4.VolumeL;
                    int i16 = this._volumeL;
                    channel4.iVolumeL = (i15 * i16) / 100;
                    int i17 = channel4.VolumeR;
                    int i18 = this._volumeR;
                    channel4.iVolumeR = (i17 * i18) / 100;
                    float f22 = (channel4.dVolumeL * i16) / 100.0f;
                    channel4.diVolumeL = f22;
                    float f23 = (channel4.dVolumeR * i18) / 100.0f;
                    channel4.diVolumeR = f23;
                    float f24 = channel4.dVolumeStarter;
                    if (f24 < 1.0f) {
                        channel4.diVolumeL = f22 * f24;
                        channel4.diVolumeR = f23 * f24;
                        channel4.dVolumeStarter = f24 + 0.05f;
                    }
                    if (channel4.EffectAllow && ((i8 = this.fType) == 0 || i8 == 2)) {
                        Effect effect = this.fEffects[0];
                        if (effect.Enable) {
                            if (!effect.getByPedal() || this._channel.Pedal != 0) {
                                Channel channel5 = this._channel;
                                if (!channel5.Close) {
                                    channel5.iKeyRepeatStart += 10;
                                }
                            }
                            if (this.fEffects[0].getStopByRelease() && this._channel.Close) {
                                j = j2;
                                c = 2;
                                c2 = 3;
                            } else if (this._channel.iKeyRepeatStart > this.fEffects[0].getStartDelay()) {
                                Channel channel6 = this._channel;
                                int i19 = channel6.iKeyRepeatCount + 1;
                                channel6.iKeyRepeatCount = i19;
                                if (i19 % ((5000 / (this.fEffects[0].getSpeed() * 10)) + 1) == 0) {
                                    Channel channel7 = this._channel;
                                    if (channel7.Close) {
                                        j = j2;
                                        c = 2;
                                        c2 = 3;
                                    } else {
                                        c2 = 3;
                                        c = 2;
                                        j = 0;
                                        Open(channel7.ID, channel7.Event, channel7.Key, channel7.BaseKey, channel7.Pitch, channel7.PitchMove, -1, channel7.iFirstVolumeL, channel7.iFirstVolumeR, (byte) channel7.Attack, (byte) channel7.Decay, (byte) channel7.Sustain, (byte) channel7.Release, true, false, channel7.PedalMode, channel7.Pedal, this._instSec, false, -1);
                                    }
                                } else {
                                    j = j2;
                                    c = 2;
                                    c2 = 3;
                                }
                            } else {
                                j = j2;
                                c = 2;
                                c2 = 3;
                            }
                        } else {
                            j = j2;
                            c = 2;
                            c2 = 3;
                        }
                        r3 = 1;
                        r3 = 1;
                        r3 = 1;
                        r3 = 1;
                        r3 = 1;
                        Effect effect2 = this.fEffects[1];
                        if (effect2.Enable || this._channel.KeyConnection) {
                            if (!effect2.getByPedal() || this._channel.Pedal != 0) {
                                Channel channel8 = this._channel;
                                if (!channel8.Close) {
                                    channel8.iKeyConnectionStart += 10;
                                }
                            }
                            if ((!this.fEffects[1].getStopByRelease() || !this._channel.Close) && this._channel.iKeyConnectionStart > this.fEffects[1].getStartDelay() && this._channel.iKeyConnectionPitch != 0) {
                                if (this.fEffects[1].getSpeed() != 0) {
                                    Channel channel9 = this._channel;
                                    if (Math.abs(channel9.iKeyConnectionPitch - channel9.iPitch) < Math.abs(this._channel.iKeyConnectionSpeed)) {
                                        channel = this._channel;
                                        channel.iKeyConnectionPitch = channel.iPitch;
                                    } else {
                                        channel = this._channel;
                                        channel.iKeyConnectionPitch += channel.iKeyConnectionSpeed;
                                    }
                                } else {
                                    channel = this._channel;
                                    channel.iKeyConnectionPitch = channel.iPitch;
                                }
                                int i20 = channel.iKeyConnectionSpeed;
                                if (i20 > 0) {
                                    int i21 = channel.iKeyConnectionPitch;
                                    int i22 = channel.iPitch;
                                    if (i21 > i22) {
                                        channel.iKeyConnectionPitch = i22;
                                    }
                                }
                                if (i20 < 0) {
                                    int i23 = channel.iKeyConnectionPitch;
                                    int i24 = channel.iPitch;
                                    if (i23 < i24) {
                                        channel.iKeyConnectionPitch = i24;
                                    }
                                }
                                if (this.fEffects[1].getSpeed() != 0) {
                                    Channel channel10 = this._channel;
                                    if (Math.abs(channel10.iKeyConnectionPitch - channel10.iPitch) < Math.abs(this._channel.iKeyConnectionSpeed)) {
                                        Channel channel11 = this._channel;
                                        channel11.iPitch = channel11.iKeyConnectionPitch;
                                    } else {
                                        Channel channel12 = this._channel;
                                        channel12.iPitch = (channel12.iKeyConnectionPitch * channel12.PitchMove) / 1000;
                                    }
                                } else {
                                    Channel channel13 = this._channel;
                                    channel13.iPitch = channel13.iKeyConnectionPitch;
                                }
                            }
                            if (this.fEffects[1].getSpeed() == 0 && !this.fEffects[c].Enable) {
                                Channel channel14 = this._channel;
                                double d3 = channel14.iPitch;
                                channel14.dPitch = d3;
                                channel14.diPitch = d3;
                            }
                        }
                        Effect effect3 = this.fEffects[c];
                        if (effect3.Enable) {
                            if (!effect3.getByPedal() || this._channel.Pedal != 0) {
                                Channel channel15 = this._channel;
                                if (!channel15.Close) {
                                    channel15.iPitchShakeStart += 10;
                                }
                            }
                            if ((!this.fEffects[c].getStopByRelease() || !this._channel.Close) && this._channel.iPitchShakeStart > this.fEffects[c].getStartDelay()) {
                                Channel channel16 = this._channel;
                                channel16.iPitchShakeCount = (this.fEffects[c].getSpeed() * 10) + channel16.iPitchShakeCount;
                                float fSin = ((float) Math.sin(this._channel.iPitchShakeCount / 1000.0f)) * this.fEffects[c].getDepth();
                                Channel channel17 = this._channel;
                                channel17.iPitch = (int) (fSin + ((channel17.iPitchShakeCount * this.fEffects[c].getGain()) / 10000) + channel17.iPitch);
                            }
                        }
                        Effect effect4 = this.fEffects[c2];
                        if (effect4.Enable) {
                            if (!effect4.getByPedal() || this._channel.Pedal != 0) {
                                Channel channel18 = this._channel;
                                if (!channel18.Close) {
                                    channel18.iVolumeShakeStart += 10;
                                }
                            }
                            if ((!this.fEffects[c2].getStopByRelease() || !this._channel.Close) && this._channel.iVolumeShakeStart > this.fEffects[c2].getStartDelay()) {
                                Channel channel19 = this._channel;
                                channel19.iVolumeShakeCount = (this.fEffects[c2].getSpeed() * 10) + channel19.iVolumeShakeCount;
                                float fSin2 = ((float) Math.sin(this._channel.iVolumeShakeCount / 1000.0f)) * this.fEffects[c2].getDepth();
                                float fSin3 = ((float) Math.sin(this._channel.iVolumeShakeCount / 1000.0f)) * this.fEffects[c2].getGain();
                                int i25 = this.mixer;
                                Channel channel20 = this._channel;
                                if (i25 == 0) {
                                    float f25 = channel20.iVolumeL;
                                    channel20.iVolumeL = (int) ((((fSin2 - fSin3) * f25) / 100.0f) + f25);
                                    float f26 = channel20.iVolumeR;
                                    channel20.iVolumeR = (int) ((((fSin2 + fSin3) * f26) / 100.0f) + f26);
                                } else {
                                    channel20.diVolumeL = (((fSin2 - fSin3) * channel20.dVolumeL) / 100.0f) + channel20.diVolumeL;
                                    channel20.diVolumeR = (((fSin2 + fSin3) * channel20.dVolumeR) / 100.0f) + channel20.diVolumeR;
                                }
                            }
                        }
                    } else {
                        j = j2;
                        r3 = b;
                    }
                    Channel channel21 = this._channel;
                    if (channel21.iPitch < r3) {
                        channel21.iPitch = r3 == true ? 1 : 0;
                    }
                    InstSec instSec2 = this._instSec;
                    if (instSec2 == null || ((sArr = (waveData = instSec2.WaveData).WaveA) == null && waveData.WaveB == null)) {
                        f = Sequence.PPQ;
                        d = 0.0d;
                    } else {
                        this._wave = sArr;
                        int i26 = waveData._PS;
                        this._ps = i26;
                        int i27 = waveData._PO;
                        this._po = i27;
                        byte b3 = instSec2.Loop;
                        boolean z3 = (b3 > r3 || (b3 == r3 && !channel21.Close) || channel21.ForceLoop) ? r3 == true ? 1 : 0 : false;
                        this._loop = z3;
                        boolean z4 = channel21.ForceLoop;
                        if (z4) {
                            this._loopStart = 0;
                            i = 0;
                        } else {
                            int i28 = instSec2.LoopStart;
                            this._loopStart = i28;
                            i = i28;
                        }
                        int i29 = z4 ? waveData.Length : instSec2.LoopEnd;
                        this._loopEnd = i29;
                        if (!z3) {
                            i29 = waveData.Length;
                        }
                        this._length = i29;
                        this._delete = false;
                        int i30 = i29 - 1;
                        this._length = i30;
                        if (sArr == null) {
                            this._delete = NMusic.ChannelProcess(channel21.Handle, this.mixer, channel21, waveData.WaveB, this._bufL, this._bufR, this.size, i30, i, z3, i26, i27);
                            f = Sequence.PPQ;
                            d = 0.0d;
                        } else {
                            int i31 = channel21.iVolumeL;
                            if (i31 < 0) {
                                channel21.iVolumeL = 0;
                                i31 = 0;
                            }
                            int i32 = channel21.iVolumeR;
                            if (i32 < 0) {
                                channel21.iVolumeR = 0;
                                i32 = 0;
                            }
                            float f27 = channel21.diVolumeL;
                            float f28 = Sequence.PPQ;
                            if (f27 < Sequence.PPQ) {
                                channel21.diVolumeL = Sequence.PPQ;
                                f27 = 0.0f;
                            }
                            float f29 = channel21.diVolumeR;
                            if (f29 < Sequence.PPQ) {
                                channel21.diVolumeR = Sequence.PPQ;
                                f29 = 0.0f;
                            }
                            int i33 = this.mixer;
                            long j3 = 8192;
                            if (i33 == 0) {
                                if (i31 > 0 || i32 > 0) {
                                    this._count = 0;
                                    while (true) {
                                        int i34 = this._count;
                                        if (i34 >= this.size || this._delete) {
                                            break;
                                        }
                                        Channel channel22 = this._channel;
                                        long j4 = channel22.Position;
                                        int i35 = (int) (j4 / j3);
                                        this._pos = i35;
                                        if (this._loop && i35 > (i7 = this._length)) {
                                            i35 = ((this._loopStart + i35) - i7) - 1;
                                            this._pos = i35;
                                            j4 = ((long) i35) * j3;
                                            channel22.Position = j4;
                                        }
                                        int i36 = this._length;
                                        if (i35 > i36) {
                                            this._pos = i36;
                                            this._delete = r3;
                                            i35 = i36;
                                        }
                                        int i37 = i35 << this._ps;
                                        this._posA = i37;
                                        long[] jArr = this._bufL;
                                        long j5 = jArr[i34];
                                        short[] sArr2 = this._wave;
                                        float f30 = f28;
                                        jArr[i34] = (((long) sArr2[i37]) * ((long) channel22.iVolumeL)) + j5;
                                        long[] jArr2 = this._bufR;
                                        jArr2[i34] = (((long) sArr2[i37 + this._po]) * ((long) channel22.iVolumeR)) + jArr2[i34];
                                        channel22.Position = j4 + ((long) channel22.iPitch);
                                        this._count = i34 + 1;
                                        j3 = j3;
                                        f28 = f30;
                                    }
                                }
                                f = f28;
                            } else {
                                f = 0.0f;
                                float f31 = 8192.0f;
                                if (i33 != r3) {
                                    float f32 = 8192.0f;
                                    boolean z5 = r3 == true ? 1 : 0;
                                    double d4 = 0.0d;
                                    if (f27 > Sequence.PPQ || f29 > Sequence.PPQ) {
                                        this._count = 0;
                                        while (true) {
                                            int i38 = this._count;
                                            if (i38 >= this.size || this._delete) {
                                                break;
                                            }
                                            Channel channel23 = this._channel;
                                            long j6 = channel23.Position;
                                            int i39 = (int) (j6 / 8192);
                                            this._pos = i39;
                                            if (this._loop) {
                                                int i40 = this._length;
                                                if (i39 > i40) {
                                                    i39 = ((this._loopStart + i39) - i40) - 1;
                                                    this._pos = i39;
                                                    j6 = ((long) i39) * 8192;
                                                    channel23.Position = j6;
                                                    if (channel23.diVolumeL <= Sequence.PPQ && channel23.diVolumeR <= Sequence.PPQ) {
                                                        this._delete = z5;
                                                    }
                                                }
                                                i2 = i39 + 1;
                                                this._pos1 = i2;
                                                i3 = i39 + 2;
                                                this._pos2 = i3;
                                                i4 = i39 + 3;
                                                this._pos3 = i4;
                                                if (i2 > i40) {
                                                    i2 = ((i2 - i40) - 1) + this._loopStart;
                                                    this._pos1 = i2;
                                                }
                                                if (i3 > i40) {
                                                    i3 = ((i3 - i40) - 1) + this._loopStart;
                                                    this._pos2 = i3;
                                                }
                                                if (i4 > i40) {
                                                    i4 = ((i4 - i40) - 1) + this._loopStart;
                                                    this._pos3 = i4;
                                                }
                                            } else {
                                                i2 = i39 + 1;
                                                this._pos1 = i2;
                                                i3 = i39 + 2;
                                                this._pos2 = i3;
                                                i4 = i39 + 3;
                                                this._pos3 = i4;
                                            }
                                            int i41 = this._length;
                                            if (i39 > i41) {
                                                this._pos = i41;
                                                this._delete = true;
                                                i39 = i41;
                                            }
                                            if (i2 > i41) {
                                                this._pos1 = i39;
                                                i2 = i39;
                                            }
                                            if (i3 > i41) {
                                                this._pos2 = i2;
                                                i3 = i2;
                                            }
                                            if (i4 > i41) {
                                                this._pos3 = i3;
                                                i4 = i3;
                                            }
                                            int i42 = this._ps;
                                            int i43 = i39 << i42;
                                            this._posA = i43;
                                            int i44 = i2 << i42;
                                            this._posA1 = i44;
                                            int i45 = i3 << i42;
                                            this._posA2 = i45;
                                            int i46 = i4 << i42;
                                            this._posA3 = i46;
                                            float f33 = (j6 / f32) - i39;
                                            this._pd = f33;
                                            float f34 = f33 * f33;
                                            this._pd2 = f34;
                                            float f35 = f34 * f33;
                                            this._pd3 = f35;
                                            float f36 = channel23.diVolumeL;
                                            if (f36 > Sequence.PPQ) {
                                                short[] sArr3 = this._wave;
                                                float f37 = sArr3[i43];
                                                this._p0 = f37;
                                                i5 = i38;
                                                float f38 = sArr3[i44];
                                                this._p1 = f38;
                                                float f39 = sArr3[i45];
                                                this._p2 = f39;
                                                float f40 = sArr3[i46];
                                                this._p3 = f40;
                                                long[] jArr3 = this._bufL;
                                                f2 = f34;
                                                jArr3[i5] = (long) ((((((((f38 * 3.0f) - f37) - (f39 * 3.0f)) + f40) * f35) + ((((f39 * 4.0f) + ((f37 * 2.0f) - (f38 * 5.0f))) - f40) * f2) + ((f39 - f37) * f33) + (f38 * 2.0f)) * 0.5f * f36) + jArr3[i5]);
                                                channel23.diVolumeL = f36 + channel23.VolumeMoveL;
                                            } else {
                                                i5 = i38;
                                                f2 = f34;
                                            }
                                            float f41 = channel23.diVolumeR;
                                            if (f41 > Sequence.PPQ) {
                                                short[] sArr4 = this._wave;
                                                int i47 = this._po;
                                                float f42 = sArr4[i43 + i47];
                                                this._p0 = f42;
                                                float f43 = sArr4[i44 + i47];
                                                this._p1 = f43;
                                                float f44 = sArr4[i45 + i47];
                                                this._p2 = f44;
                                                float f45 = sArr4[i46 + i47];
                                                this._p3 = f45;
                                                long[] jArr4 = this._bufR;
                                                jArr4[i5] = (long) ((((((((f43 * 3.0f) - f42) - (f44 * 3.0f)) + f45) * f35) + ((((4.0f * f44) + ((2.0f * f42) - (5.0f * f43))) - f45) * f2) + ((f44 - f42) * f33) + (f43 * 2.0f)) * 0.5f * f41) + jArr4[i5]);
                                                channel23.diVolumeR = f41 + channel23.VolumeMoveR;
                                            }
                                            double d5 = channel23.diPitch + channel23.dPitchChangeValue;
                                            channel23.diPitch = d5;
                                            if (d5 > d4) {
                                                channel23.Position = (long) (j6 + d5);
                                            }
                                            this._count = i5 + 1;
                                            d4 = d4;
                                            f32 = 8192.0f;
                                            z5 = true;
                                        }
                                    }
                                    d = d4;
                                } else if (f27 > Sequence.PPQ || f29 > Sequence.PPQ) {
                                    this._count = 0;
                                    ?? r4 = r3;
                                    while (true) {
                                        int i48 = this._count;
                                        if (i48 >= this.size || this._delete) {
                                            break;
                                        }
                                        Channel channel24 = this._channel;
                                        long j7 = channel24.Position;
                                        int i49 = (int) (j7 / 8192);
                                        this._pos = i49;
                                        if (this._loop) {
                                            int i50 = this._length;
                                            if (i49 > i50) {
                                                i49 = ((this._loopStart + i49) - i50) - 1;
                                                this._pos = i49;
                                                j7 = ((long) i49) * 8192;
                                                channel24.Position = j7;
                                                if (channel24.diVolumeL <= Sequence.PPQ && channel24.diVolumeR <= Sequence.PPQ) {
                                                    this._delete = r4;
                                                }
                                            }
                                            i6 = i49 + 1;
                                            this._pos1 = i6;
                                            if (i6 > i50) {
                                                i6 = ((i6 - i50) - r4) + this._loopStart;
                                                this._pos1 = i6;
                                            }
                                        } else {
                                            i6 = i49 + 1;
                                            this._pos1 = i6;
                                        }
                                        int i51 = this._length;
                                        if (i49 > i51) {
                                            this._pos = i51;
                                            this._delete = r4;
                                            i49 = i51;
                                        }
                                        if (i6 > i51) {
                                            this._pos1 = i49;
                                            i6 = i49;
                                        }
                                        int i52 = this._ps;
                                        int i53 = i49 << i52;
                                        this._posA = i53;
                                        int i54 = i6 << i52;
                                        this._posA1 = i54;
                                        float f46 = (j7 / f31) - i49;
                                        this._pd = f46;
                                        float f47 = channel24.diVolumeL;
                                        if (f47 > Sequence.PPQ) {
                                            short[] sArr5 = this._wave;
                                            float f48 = sArr5[i53];
                                            this._p0 = f48;
                                            float f49 = sArr5[i54];
                                            this._p1 = f49;
                                            long[] jArr5 = this._bufL;
                                            jArr5[i48] = (long) (((((f49 - f48) * f46) + f48) * f47) + jArr5[i48]);
                                            channel24.diVolumeL = f47 + channel24.VolumeMoveL;
                                        }
                                        float f50 = channel24.diVolumeR;
                                        if (f50 > Sequence.PPQ) {
                                            short[] sArr6 = this._wave;
                                            int i55 = this._po;
                                            float f51 = sArr6[i53 + i55];
                                            this._p0 = f51;
                                            float f52 = sArr6[i54 + i55];
                                            this._p1 = f52;
                                            long[] jArr6 = this._bufR;
                                            jArr6[i48] = (long) (((((f52 - f51) * f46) + f51) * f50) + jArr6[i48]);
                                            channel24.diVolumeR = f50 + channel24.VolumeMoveR;
                                        }
                                        double d6 = channel24.diPitch + channel24.dPitchChangeValue;
                                        channel24.diPitch = d6;
                                        if (d6 > 0.0d) {
                                            channel24.Position = (long) (j7 + d6);
                                        }
                                        this._count = i48 + 1;
                                        f31 = f31;
                                        r4 = r4;
                                    }
                                }
                            }
                            d = 0.0d;
                        }
                    }
                    if (this.mixer == 0) {
                        if (!this._delete) {
                            Channel channel25 = this._channel;
                            z = channel25.VolumeL <= 0 && channel25.VolumeR <= 0;
                        }
                        this._delete = z;
                    } else {
                        int i56 = this._pitchSpeed;
                        Channel channel26 = this._channel;
                        if (i56 == 0) {
                            channel26.dPitch = channel26.iPitch;
                            channel26.dPitchChangeValue = d;
                        } else {
                            double d7 = channel26.diPitch;
                            channel26.dPitch = d7;
                            channel26.dPitchChangeValue = (((double) channel26.iPitch) - d7) / ((double) (this.size * i56));
                        }
                        z = this._delete || (channel26.dVolumeL <= f && channel26.dVolumeR <= f);
                        this._delete = z;
                    }
                    if (z) {
                        DeleteIndex(this._index);
                    }
                } else {
                    j = j2;
                }
                this._index++;
                b = 1;
                j2 = j;
            }
            if (!this.fFXs.l.isEmpty()) {
                this._count = 0;
                while (true) {
                    int i57 = this._count;
                    if (i57 >= this.size) {
                        break;
                    }
                    z01 z01Var2 = this.fFXs;
                    Music music3 = this.fMusic;
                    z01Var2.h(music3.bufCL, music3.bufCR, i57);
                    long[] jArr7 = this.bufL;
                    int i58 = this._count;
                    long j8 = jArr7[i58];
                    Music music4 = this.fMusic;
                    jArr7[i58] = j8 + music4.bufCL[i58];
                    long[] jArr8 = this.bufR;
                    jArr8[i58] = jArr8[i58] + music4.bufCR[i58];
                    this._count = i58 + 1;
                }
            }
        } catch (Throwable th) {
            throw th;
        }
    }
