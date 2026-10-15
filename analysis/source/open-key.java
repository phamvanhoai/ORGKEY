    /* JADX WARN: Code duplicated, block: B:150:0x0268  */
    /* JADX WARN: Code duplicated, block: B:151:0x026b  */
    /* JADX WARN: Code duplicated, block: B:154:0x029e  */
    /* JADX WARN: Code duplicated, block: B:155:0x02a0  */
    /* JADX WARN: Code duplicated, block: B:158:0x02d4  */
    /* JADX WARN: Code duplicated, block: B:181:0x03a7  */
    /* JADX WARN: Code duplicated, block: B:184:0x03b4 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:185:0x03b6  */
    /* JADX WARN: Code duplicated, block: B:186:0x03b9  */
    /* JADX WARN: Code duplicated, block: B:189:0x03be  */
    /* JADX WARN: Code duplicated, block: B:190:0x03c0 A[Catch: Exception -> 0x0459, TryCatch #3 {Exception -> 0x0459, blocks: (B:187:0x03ba, B:190:0x03c0, B:192:0x03c4, B:193:0x03cc, B:197:0x03ee, B:199:0x0429, B:202:0x0436, B:204:0x0442, B:205:0x0450), top: B:216:0x03ba }] */
    /* JADX WARN: Code duplicated, block: B:195:0x03ea  */
    /* JADX WARN: Code duplicated, block: B:196:0x03ed  */
    /* JADX WARN: Code duplicated, block: B:199:0x0429 A[Catch: Exception -> 0x0459, TryCatch #3 {Exception -> 0x0459, blocks: (B:187:0x03ba, B:190:0x03c0, B:192:0x03c4, B:193:0x03cc, B:197:0x03ee, B:199:0x0429, B:202:0x0436, B:204:0x0442, B:205:0x0450), top: B:216:0x03ba }] */
    /* JADX WARN: Code duplicated, block: B:204:0x0442 A[Catch: Exception -> 0x0459, TryCatch #3 {Exception -> 0x0459, blocks: (B:187:0x03ba, B:190:0x03c0, B:192:0x03c4, B:193:0x03cc, B:197:0x03ee, B:199:0x0429, B:202:0x0436, B:204:0x0442, B:205:0x0450), top: B:216:0x03ba }] */
    /* JADX WARN: Code duplicated, block: B:238:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:239:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:240:? A[ADDED_TO_REGION, RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:69:0x0131  */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r19v0 */
    /* JADX WARN: Type inference failed for: r19v1 */
    /* JADX WARN: Type inference failed for: r19v3 */
    public void OpenKey(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10, int i11, int i12, int i13, int i14, int i15, int i16, int i17, int i18, int i19, int i20, int i21, int i22, boolean z, int i23, int i24) {
        int iMin;
        int i25;
        int i26;
        int i27;
        int i28;
        int i29;
        int i30;
        int i31;
        int i32;
        int speed;
        boolean z2;
        int i33;
        int i34;
        boolean z3;
        int i35;
        boolean z4;
        Instrument instrument;
        int i36;
        int i37;
        Instrument instrument2;
        int i38;
        Channel channelLastChannel;
        boolean z5;
        boolean z6;
        int i39;
        int i40;
        int i41;
        int i42;
        Effect effect;
        Effect[] effectArr;
        Effect effect2;
        int i43;
        int i44;
        int i45 = i8;
        Instrument instrumentGet = this.fMusic.fInstruments.Get(i6);
        int i46 = i9 + ((short) (i4 >> 16));
        int i47 = i4 & Settings.DEFAULT_INITIAL_WINDOW_SIZE;
        int i48 = this.fType;
        Music music = this.fMusic;
        if (i48 == 0) {
            iMin = (Math.min(100, music.fFXMusic.c + 100) * i47) / 100;
        } else {
            int iMin2 = (Math.min(100, 100 - music.fFXMusic.c) * (i47 * music.fFXRhythm.b)) / 10000;
            byte b = instrumentGet.fType;
            Music music2 = this.fMusic;
            iMin = b == 0 ? (Math.min(100, 100 - music2.fFXRhythm.c) * iMin2) / 100 : (Math.min(100, music2.fFXRhythm.c + 100) * iMin2) / 100;
        }
        int i49 = this.fType;
        if (i49 >= 6) {
            i25 = i3;
            i26 = 0;
            i27 = 0;
        } else {
            z01 z01Var = this.fFXs;
            int i50 = z01Var.f;
            Music music3 = this.fMusic;
            z01 z01Var2 = music3.fFXMusic;
            int i51 = i50 + z01Var2.f + i10;
            int i52 = z01Var.g + this.tempOctaveMove + i11 + (i49 == 0 ? z01Var2.g : music3.fFXRhythm.g);
            if (instrumentGet.fType == 0) {
                i25 = (i52 * 12) + i51 + i3;
                i27 = z01Var.d + z01Var2.d + i12;
                i26 = i51;
            } else {
                i25 = i3;
                i26 = i51;
                i27 = i12;
            }
        }
        if (i25 >= 0) {
            if (i25 >= 128) {
                i28 = -1;
            } else {
                InstSnd instSnd = instrumentGet.fSndList[i];
                byte b2 = instSnd.PitchMode;
                if (b2 == 0) {
                    i28 = -1;
                    int iAbs = 127;
                    int i53 = 0;
                    for (int i54 = 128; i53 < i54; i54 = 128) {
                        if (instrumentGet.fSndList[i].SectionExists(i53)) {
                            int i55 = i25 - i53;
                            if (Math.abs(i55) < iAbs) {
                                iAbs = Math.abs(i55);
                                i28 = i53;
                            }
                        }
                        i53++;
                    }
                } else if (b2 == 1) {
                    int i56 = 0;
                    while (true) {
                        if (i56 >= 128) {
                            i56 = -1;
                            break;
                        } else if (instrumentGet.fSndList[i].SectionExists(i56)) {
                            break;
                        } else {
                            i56++;
                        }
                    }
                    for (int i57 = 127; i57 >= 0; i57--) {
                        if (instrumentGet.fSndList[i].SectionExists(i57)) {
                            i56 = (i57 - i56) + 1;
                            break;
                        }
                    }
                    if (i56 > 0) {
                        int i58 = 0;
                        while (true) {
                            if (i58 > 128 / i56) {
                                i44 = -1;
                                break;
                            }
                            if (instrumentGet.fSndList[i].SectionExists(i25)) {
                                i44 = i25;
                                break;
                            }
                            i25 += i56;
                            if (i25 >= 128) {
                                while (true) {
                                    int i59 = i25 - i56;
                                    if (i59 >= 0) {
                                        i25 = i59;
                                    }
                                }
                            }
                            i58++;
                        }
                        i28 = i44;
                    } else {
                        i28 = -1;
                    }
                } else if (b2 == 2 && instSnd.SectionExists(i25)) {
                    i28 = i25;
                } else {
                    i28 = -1;
                }
            }
        } else {
            i28 = -1;
        }
        if (i28 != -1) {
            InstSec instSec = instrumentGet.fSndList[i].fList[i28];
            if (i45 < 0) {
                i45 = -i45;
            }
            int i60 = instrumentGet.fVolume;
            if (i60 != 0 && (i43 = instSec.Volume) != 0) {
                i45 = ((i45 * i60) * i43) / 10000;
            }
            int i61 = i46 + instrumentGet.fPan + instSec.Pan;
            int i62 = i45 * iMin;
            int iMin3 = (Math.min(100, 100 - i61) * i62) / 10000;
            int iMin4 = (Math.min(100, i61 + 100) * i62) / 10000;
            int i63 = i16 < 0 ? instrumentGet.fSndList[i].Attack : i16;
            int i64 = i17 < 0 ? instrumentGet.fSndList[i].Decay : i17;
            int i65 = i18 < 0 ? instrumentGet.fSndList[i].Sustain : i18;
            int i66 = (((i19 < 0 ? instrumentGet.fSndList[i].Release : i19) * this.fFXs.h) * this.fMusic.fFXMusic.h) / 10000;
            int i67 = i66 > 100 ? 100 : i66;
            ?? r19 = i20 < 0 ? instrumentGet.fSndList[i].AutoRelease : i20;
            try {
                if (this.fEffects[1].getMode() <= 15 || i21 >= 0 || (effectArr = this.fEffects) == null || (effect2 = effectArr[1]) == null || !effect2.Enable) {
                    i29 = i63;
                    i30 = i64;
                    i31 = i65;
                    i32 = i7;
                } else {
                    i29 = i63;
                    i30 = i64;
                    i31 = i65;
                    try {
                        i32 = i7;
                        if (_Connection(i, i2, i25, instrumentGet, i7, i26, i27, i15)) {
                            return;
                        }
                    } catch (Exception unused) {
                        instrumentGet = instrumentGet;
                        i32 = i7;
                        speed = i22;
                        z2 = false;
                        i33 = -1;
                        i34 = speed;
                        int i68 = instSec.Tune + i27;
                        double[] dArr = this.fMusic.fSystems.Get(i32).fTable.Table;
                        if (instrumentGet.fType == 0) {
                            z3 = true;
                        } else {
                            z3 = false;
                        }
                        int i69 = i28;
                        int i70 = i25;
                        i35 = i26;
                        int iRound = (int) Math.round((Music.KeyToPitchEx(i70, i69, i35, i68, dArr, z3, 8192.0d) * ((double) instSec.WaveData.SampleRate)) / ((double) this.fMusic.fOutSampleRate));
                        byte b3 = (byte) i29;
                        byte b4 = (byte) i30;
                        byte b5 = (byte) i31;
                        byte b6 = (byte) i67;
                        if (r19 != 0) {
                            z4 = true;
                        } else {
                            z4 = false;
                        }
                        instrument = instrumentGet;
                        i36 = i27;
                        Open(i2, 2, i70, i69, iRound, i13, i5, iMin3, iMin4, b3, b4, b5, b6, z4, this.fFXs.i, i14, i15, instSec, z, i23);
                        if (i24 > 2) {
                            i37 = i7;
                            instrument2 = instrument;
                            i38 = i36;
                        } else {
                            i37 = i7;
                            instrument2 = instrument;
                            i38 = i36;
                        }
                        channelLastChannel = lastChannel();
                        if (channelLastChannel != null) {
                            if (i33 >= 0) {
                                z5 = true;
                            } else {
                                z5 = false;
                            }
                            try {
                                channelLastChannel.KeyConnection = z5;
                                if (z5) {
                                    if (z2) {
                                        channelLastChannel.Position = ((long) instSec.LoopStart) * 8192;
                                    }
                                    channelLastChannel.ID = i2;
                                    channelLastChannel.Close = false;
                                    channelLastChannel.Pedal = i15;
                                    int i71 = channelLastChannel.BaseKey;
                                    int i72 = instSec.Tune + i38;
                                    double[] dArr2 = this.fMusic.fSystems.Get(i37).fTable.Table;
                                    if (instrument2.fType == 0) {
                                        z6 = true;
                                    } else {
                                        z6 = false;
                                    }
                                    int iRound2 = (int) Math.round((Music.KeyToPitchEx(i33, i71, i35, i72, dArr2, z6, 8192.0d) * ((double) instSec.WaveData.SampleRate)) / ((double) this.fMusic.fOutSampleRate));
                                    channelLastChannel.iKeyConnectionPitch = iRound2;
                                    channelLastChannel.iPitch = iRound2;
                                    double d = iRound2;
                                    channelLastChannel.dPitch = d;
                                    channelLastChannel.diPitch = d;
                                    channelLastChannel.iKeyConnectionSpeed = (((channelLastChannel.Pitch - iRound2) * i34) * 10) / 8192;
                                    if (i34 != 0) {
                                        return;
                                    }
                                    channelLastChannel.Position = 0L;
                                    if (instrument2.fSndList[i].AutoRelease) {
                                        int i73 = channelLastChannel.iFirstVolumeL;
                                        channelLastChannel.VolumeL = i73;
                                        int i74 = channelLastChannel.iFirstVolumeR;
                                        channelLastChannel.VolumeR = i74;
                                        channelLastChannel.dVolumeL = i73;
                                        channelLastChannel.dVolumeR = i74;
                                    }
                                    int i75 = channelLastChannel.Pitch;
                                    channelLastChannel.iPitch = i75;
                                    double d2 = i75;
                                    channelLastChannel.dPitch = d2;
                                    channelLastChannel.diPitch = d2;
                                }
                            } catch (Exception unused2) {
                                return;
                            }
                        }
                    }
                }
                try {
                    Channel channelLastChannel2 = lastChannel();
                    Effect[] effectArr2 = this.fEffects;
                    if (effectArr2 != null && (effect = effectArr2[1]) != null && effect.Enable && this.fList != null && channelLastChannel2 != null && ((!effect.getStopByRelease() || !channelLastChannel2.Close) && (!this.fEffects[1].getByPedal() || i15 != 0))) {
                        i42 = i21 < 0 ? channelLastChannel2.Key : i21;
                        speed = i22 <= 0 ? this.fEffects[1].getSpeed() : i22;
                        try {
                            boolean z7 = this.fEffects[1].getMode() > 1;
                            i33 = i42;
                            z2 = z7;
                        } catch (Exception unused3) {
                            z2 = false;
                            i33 = -1;
                        }
                        i34 = speed;
                        int i610 = instSec.Tune + i27;
                        double[] dArr3 = this.fMusic.fSystems.Get(i32).fTable.Table;
                        if (instrumentGet.fType == 0) {
                            z3 = true;
                        } else {
                            z3 = false;
                        }
                        int i611 = i28;
                        int i76 = i25;
                        i35 = i26;
                        int iRound3 = (int) Math.round((Music.KeyToPitchEx(i76, i611, i35, i610, dArr3, z3, 8192.0d) * ((double) instSec.WaveData.SampleRate)) / ((double) this.fMusic.fOutSampleRate));
                        byte b7 = (byte) i29;
                        byte b8 = (byte) i30;
                        byte b9 = (byte) i31;
                        byte b10 = (byte) i67;
                        if (r19 != 0) {
                            z4 = true;
                        } else {
                            z4 = false;
                        }
                        instrument = instrumentGet;
                        i36 = i27;
                        Open(i2, 2, i76, i611, iRound3, i13, i5, iMin3, iMin4, b7, b8, b9, b10, z4, this.fFXs.i, i14, i15, instSec, z, i23);
                        if (i24 > 2 || (i39 = i76 / 12) < 3 || i39 > 9) {
                            i37 = i7;
                            instrument2 = instrument;
                            i38 = i36;
                        } else {
                            Channel channelLastChannel3 = lastChannel();
                            if (i24 == 4 && channelLastChannel3 != null) {
                                channelLastChannel3.dVolumeStarter = Sequence.PPQ;
                            }
                            i38 = i36;
                            float f = (((i38 / 8192.0f) + 1.0f) * i13) / 1000.0f;
                            z01 z01Var3 = this.fFXs;
                            int i77 = z01Var3.c;
                            int i78 = z01Var3.b;
                            if (i77 > 0) {
                                i40 = ((100 - i77) * (iMin3 * i78)) / 100;
                                i41 = iMin4 * i78;
                            } else {
                                i40 = iMin3 * i78;
                                i41 = ((i77 + 100) * (iMin4 * i78)) / 100;
                            }
                            Music music4 = this.fMusic;
                            x13 x13Var = music4.fPlatform;
                            int i79 = music4.fFXMusic.b;
                            float f2 = (i40 * i79) / 2000000.0f;
                            float f3 = (i41 * i79) / 2000000.0f;
                            int i80 = i76 % 12;
                            int i81 = i35 % 12;
                            i37 = i7;
                            instrument2 = instrument;
                            float fKeyToPitchEx = (float) Music.KeyToPitchEx(i80, 0, i81, 0, music4.fSystems.Get(i37).fTable.Table, instrument2.fType == 0, f);
                            ew ewVar = x13Var.a;
                            ewVar.getClass();
                            ArrayList arrayList = new ArrayList(ewVar.b);
                            int size = arrayList.size();
                            int i82 = 0;
                            while (i82 < size) {
                                Object obj = arrayList.get(i82);
                                i82++;
                                int i83 = i39 - 1;
                                b60 b60Var = ((c23) obj).c;
                                ArrayList arrayList2 = (ArrayList) b60Var.b;
                                if (i83 < arrayList2.size()) {
                                    ((SoundPool) b60Var.a).play(((Integer) arrayList2.get(i83)).intValue(), f2, f3, 0, 0, fKeyToPitchEx);
                                }
                            }
                        }
                        channelLastChannel = lastChannel();
                        if (channelLastChannel != null) {
                            if (i33 >= 0) {
                                z5 = true;
                            } else {
                                z5 = false;
                            }
                            channelLastChannel.KeyConnection = z5;
                            if (z5) {
                                if (z2 && instSec.Loop > 0) {
                                    channelLastChannel.Position = ((long) instSec.LoopStart) * 8192;
                                }
                                channelLastChannel.ID = i2;
                                channelLastChannel.Close = false;
                                channelLastChannel.Pedal = i15;
                                int i710 = channelLastChannel.BaseKey;
                                int i711 = instSec.Tune + i38;
                                double[] dArr4 = this.fMusic.fSystems.Get(i37).fTable.Table;
                                if (instrument2.fType == 0) {
                                    z6 = true;
                                } else {
                                    z6 = false;
                                }
                                int iRound4 = (int) Math.round((Music.KeyToPitchEx(i33, i710, i35, i711, dArr4, z6, 8192.0d) * ((double) instSec.WaveData.SampleRate)) / ((double) this.fMusic.fOutSampleRate));
                                channelLastChannel.iKeyConnectionPitch = iRound4;
                                channelLastChannel.iPitch = iRound4;
                                double d3 = iRound4;
                                channelLastChannel.dPitch = d3;
                                channelLastChannel.diPitch = d3;
                                channelLastChannel.iKeyConnectionSpeed = (((channelLastChannel.Pitch - iRound4) * i34) * 10) / 8192;
                                if (i34 != 0 && this.fEffects[1].getMode() == 1) {
                                    channelLastChannel.Position = 0L;
                                    if (instrument2.fSndList[i].AutoRelease) {
                                        int i712 = channelLastChannel.iFirstVolumeL;
                                        channelLastChannel.VolumeL = i712;
                                        int i713 = channelLastChannel.iFirstVolumeR;
                                        channelLastChannel.VolumeR = i713;
                                        channelLastChannel.dVolumeL = i712;
                                        channelLastChannel.dVolumeR = i713;
                                    }
                                    int i714 = channelLastChannel.Pitch;
                                    channelLastChannel.iPitch = i714;
                                    double d4 = i714;
                                    channelLastChannel.dPitch = d4;
                                    channelLastChannel.diPitch = d4;
                                }
                                return;
                            }
                        }
                    }
                    i42 = i21;
                    speed = i22;
                    i33 = i42;
                    z2 = z7;
                } catch (Exception unused4) {
                    speed = i22;
                    z2 = false;
                    i33 = -1;
                    i34 = speed;
                    int i612 = instSec.Tune + i27;
                    double[] dArr5 = this.fMusic.fSystems.Get(i32).fTable.Table;
                    if (instrumentGet.fType == 0) {
                        z3 = true;
                    } else {
                        z3 = false;
                    }
                    int i613 = i28;
                    int i715 = i25;
                    i35 = i26;
                    int iRound5 = (int) Math.round((Music.KeyToPitchEx(i715, i613, i35, i612, dArr5, z3, 8192.0d) * ((double) instSec.WaveData.SampleRate)) / ((double) this.fMusic.fOutSampleRate));
                    byte b11 = (byte) i29;
                    byte b12 = (byte) i30;
                    byte b13 = (byte) i31;
                    byte b14 = (byte) i67;
                    if (r19 != 0) {
                        z4 = true;
                    } else {
                        z4 = false;
                    }
                    instrument = instrumentGet;
                    i36 = i27;
                    Open(i2, 2, i715, i613, iRound5, i13, i5, iMin3, iMin4, b11, b12, b13, b14, z4, this.fFXs.i, i14, i15, instSec, z, i23);
                    if (i24 > 2) {
                        i37 = i7;
                        instrument2 = instrument;
                        i38 = i36;
                    } else {
                        i37 = i7;
                        instrument2 = instrument;
                        i38 = i36;
                    }
                    channelLastChannel = lastChannel();
                    if (channelLastChannel != null) {
                        if (i33 >= 0) {
                            z5 = true;
                        } else {
                            z5 = false;
                        }
                        channelLastChannel.KeyConnection = z5;
                        if (z5) {
                            if (z2) {
                                channelLastChannel.Position = ((long) instSec.LoopStart) * 8192;
                            }
                            channelLastChannel.ID = i2;
                            channelLastChannel.Close = false;
                            channelLastChannel.Pedal = i15;
                            int i716 = channelLastChannel.BaseKey;
                            int i717 = instSec.Tune + i38;
                            double[] dArr6 = this.fMusic.fSystems.Get(i37).fTable.Table;
                            if (instrument2.fType == 0) {
                                z6 = true;
                            } else {
                                z6 = false;
                            }
                            int iRound6 = (int) Math.round((Music.KeyToPitchEx(i33, i716, i35, i717, dArr6, z6, 8192.0d) * ((double) instSec.WaveData.SampleRate)) / ((double) this.fMusic.fOutSampleRate));
                            channelLastChannel.iKeyConnectionPitch = iRound6;
                            channelLastChannel.iPitch = iRound6;
                            double d5 = iRound6;
                            channelLastChannel.dPitch = d5;
                            channelLastChannel.diPitch = d5;
                            channelLastChannel.iKeyConnectionSpeed = (((channelLastChannel.Pitch - iRound6) * i34) * 10) / 8192;
                            if (i34 != 0) {
                                return;
                            }
                            channelLastChannel.Position = 0L;
                            if (instrument2.fSndList[i].AutoRelease) {
                                int i718 = channelLastChannel.iFirstVolumeL;
                                channelLastChannel.VolumeL = i718;
                                int i719 = channelLastChannel.iFirstVolumeR;
                                channelLastChannel.VolumeR = i719;
                                channelLastChannel.dVolumeL = i718;
                                channelLastChannel.dVolumeR = i719;
                            }
                            int i7110 = channelLastChannel.Pitch;
                            channelLastChannel.iPitch = i7110;
                            double d6 = i7110;
                            channelLastChannel.dPitch = d6;
                            channelLastChannel.diPitch = d6;
                        }
                    }
                }
            } catch (Exception unused5) {
                i29 = i63;
                i30 = i64;
                instrumentGet = instrumentGet;
                i31 = i65;
                i32 = i7;
            }
            i34 = speed;
            int i614 = instSec.Tune + i27;
            double[] dArr7 = this.fMusic.fSystems.Get(i32).fTable.Table;
            if (instrumentGet.fType == 0) {
                z3 = true;
            } else {
                z3 = false;
            }
            int i615 = i28;
            int i7111 = i25;
            i35 = i26;
            int iRound7 = (int) Math.round((Music.KeyToPitchEx(i7111, i615, i35, i614, dArr7, z3, 8192.0d) * ((double) instSec.WaveData.SampleRate)) / ((double) this.fMusic.fOutSampleRate));
            byte b15 = (byte) i29;
            byte b16 = (byte) i30;
            byte b17 = (byte) i31;
            byte b18 = (byte) i67;
            if (r19 != 0) {
                z4 = true;
            } else {
                z4 = false;
            }
            instrument = instrumentGet;
            i36 = i27;
            Open(i2, 2, i7111, i615, iRound7, i13, i5, iMin3, iMin4, b15, b16, b17, b18, z4, this.fFXs.i, i14, i15, instSec, z, i23);
            if (i24 > 2) {
                i37 = i7;
                instrument2 = instrument;
                i38 = i36;
            } else {
                i37 = i7;
                instrument2 = instrument;
                i38 = i36;
            }
            channelLastChannel = lastChannel();
            if (channelLastChannel != null) {
                if (i33 >= 0) {
                    z5 = true;
                } else {
                    z5 = false;
                }
                channelLastChannel.KeyConnection = z5;
                if (z5) {
                    if (z2) {
                        channelLastChannel.Position = ((long) instSec.LoopStart) * 8192;
                    }
                    channelLastChannel.ID = i2;
                    channelLastChannel.Close = false;
                    channelLastChannel.Pedal = i15;
                    int i7112 = channelLastChannel.BaseKey;
                    int i7113 = instSec.Tune + i38;
                    double[] dArr8 = this.fMusic.fSystems.Get(i37).fTable.Table;
                    if (instrument2.fType == 0) {
                        z6 = true;
                    } else {
                        z6 = false;
                    }
                    int iRound8 = (int) Math.round((Music.KeyToPitchEx(i33, i7112, i35, i7113, dArr8, z6, 8192.0d) * ((double) instSec.WaveData.SampleRate)) / ((double) this.fMusic.fOutSampleRate));
                    channelLastChannel.iKeyConnectionPitch = iRound8;
                    channelLastChannel.iPitch = iRound8;
                    double d7 = iRound8;
                    channelLastChannel.dPitch = d7;
                    channelLastChannel.diPitch = d7;
                    channelLastChannel.iKeyConnectionSpeed = (((channelLastChannel.Pitch - iRound8) * i34) * 10) / 8192;
                    if (i34 != 0) {
                        return;
                    }
                    channelLastChannel.Position = 0L;
                    if (instrument2.fSndList[i].AutoRelease) {
                        int i7114 = channelLastChannel.iFirstVolumeL;
                        channelLastChannel.VolumeL = i7114;
                        int i7115 = channelLastChannel.iFirstVolumeR;
                        channelLastChannel.VolumeR = i7115;
                        channelLastChannel.dVolumeL = i7114;
                        channelLastChannel.dVolumeR = i7115;
                    }
                    int i7116 = channelLastChannel.Pitch;
                    channelLastChannel.iPitch = i7116;
                    double d8 = i7116;
                    channelLastChannel.dPitch = d8;
                    channelLastChannel.diPitch = d8;
                }
            }
        }
    }
