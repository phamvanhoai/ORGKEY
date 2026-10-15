    /* JADX WARN: Code duplicated, block: B:110:0x01b8  */
    /* JADX WARN: Code duplicated, block: B:112:0x01bc  */
    /* JADX WARN: Code duplicated, block: B:113:0x01bf  */
    /* JADX WARN: Code duplicated, block: B:124:0x01fa  */
    /* JADX WARN: Code duplicated, block: B:126:0x01fe  */
    /* JADX WARN: Code duplicated, block: B:127:0x0201  */
    /* JADX WARN: Code duplicated, block: B:137:0x0240  */
    /* JADX WARN: Code duplicated, block: B:148:0x025d  */
    /* JADX WARN: Code duplicated, block: B:171:0x02c0  */
    /* JADX WARN: Code duplicated, block: B:180:0x02c7 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:183:0x013e A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:189:0x0255 A[EDGE_INSN: B:189:0x0255->B:146:0x0255 BREAK  A[LOOP:3: B:135:0x0236->B:145:0x0252], SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:33:0x008c  */
    /* JADX WARN: Code duplicated, block: B:35:0x0090  */
    /* JADX WARN: Code duplicated, block: B:36:0x0093  */
    /* JADX WARN: Code duplicated, block: B:43:0x00b9  */
    /* JADX WARN: Code duplicated, block: B:45:0x00bd  */
    /* JADX WARN: Code duplicated, block: B:46:0x00c0  */
    /* JADX WARN: Code duplicated, block: B:53:0x00df  */
    /* JADX WARN: Code duplicated, block: B:55:0x00e7  */
    /* JADX WARN: Code duplicated, block: B:76:0x013c  */
    /* JADX WARN: Code duplicated, block: B:78:0x0143  */
    public final void H1(boolean z) {
        Music music;
        int i;
        int i2;
        Track track;
        int i3;
        int iTrackCount;
        Music music2;
        int iTrackCount2;
        int i4;
        Track Track;
        int i5;
        Track Track2;
        int i6;
        int i7;
        int i8;
        int i9;
        int i10;
        Music music3;
        int iTrackCount3;
        Track Track3;
        int i11;
        int i12;
        if (this.R0) {
            if (z) {
                this.d2 = (this.c2 - ((this.J0 - 1) / 7)) - 1;
            }
            int i13 = 0;
            for (int i14 = 0; i14 < this.o.TrackCount(-1); i14++) {
                if (this.o.Track(i14).fGroup == 0 && this.o.Track(i14).fType == 1 && this.o.Track(i14).fRecord) {
                    i13++;
                }
            }
            int i15 = this.X1 == 1 ? this.d2 : this.c2;
            int i16 = this.K0;
            int i17 = (i15 * 12) + i16;
            this.b3 = i17;
            int i18 = (this.c2 * 12) + iy1.i(this.J0, 12, 7, i16);
            this.c3 = i18;
            int i19 = (i18 - i17) + 1;
            Music music4 = this.o;
            int i20 = music4.fChordScan;
            if (i20 != 0) {
                if (i20 == 1) {
                    if (W0()) {
                        tp2 tp2Var = this.n;
                        if (tp2Var.b != 0) {
                            i11 = tp2Var.c + tp2Var.d + 1;
                        } else if (this.X1 == 1) {
                            i11 = i17 + 18;
                        } else {
                            i11 = i17 + 12;
                        }
                    } else if (this.X1 == 1) {
                        i11 = i17 + 18;
                    } else {
                        i11 = i17 + 12;
                    }
                    i19 = (i18 - i11) + 1;
                    this.o.TrackTypeKeyAreaSet(3, 0, i11 - 1);
                    i9 = i11;
                    i7 = i9;
                    i8 = 127;
                } else if (i20 == 2) {
                    if (W0()) {
                        tp2 tp2Var2 = this.n;
                        if (tp2Var2.b != 0) {
                            i12 = tp2Var2.c + tp2Var2.d + 1;
                        } else if (this.X1 == 1) {
                            i12 = i18 - 18;
                        } else {
                            i12 = i18 - 12;
                        }
                    } else if (this.X1 == 1) {
                        i12 = i18 - 18;
                    } else {
                        i12 = i18 - 12;
                    }
                    int i21 = i12 - 1;
                    this.o.TrackTypeKeyAreaSet(3, i12, 127);
                    i7 = i17;
                    i19 = (i12 - i17) + 1;
                    i9 = 0;
                    i8 = i21;
                } else if (i20 == 3) {
                    music4.TrackTypeKeyAreaSet(3, 0, 127);
                }
                i10 = this.U1;
                music3 = this.o;
                if (i10 == 1) {
                    music3.TrackTypeKeyAreaSet(1, i9, i8);
                    return;
                }
                iTrackCount3 = music3.TrackCount(0) - 1;
                int i22 = 0;
                while (iTrackCount3 >= 0) {
                    Track3 = this.o.Track(0, iTrackCount3);
                    if (Track3 == null && Track3.fType == 1 && this.o.Track(iTrackCount3).fRecord) {
                        int i23 = this.V1;
                        if (i23 <= 0) {
                            i23 = i19 / i13;
                        }
                        Music music5 = this.o;
                        int i24 = this.c2;
                        if (i22 == 0) {
                            music5.TrackKeyAreaSet(Track3, i9, (i7 + i23) - 1, i24, i7);
                        } else {
                            if (i22 >= i13 - 1) {
                                music5.TrackKeyAreaSet(Track3, (i23 * i22) + i7, i8, i24, i7);
                            } else {
                                music5.TrackKeyAreaSet(Track3, (i23 * i22) + i7, (((i22 + 1) * i23) + i7) - 1, i24, i7);
                            }
                            i22++;
                            if (i22 >= i13) {
                                return;
                            }
                        }
                        i22++;
                        if (i22 >= i13) {
                            return;
                        }
                    } else {
                        i8 = i8;
                    }
                    iTrackCount3--;
                    i8 = i8;
                }
                return;
            }
            music4.TrackTypeKeyAreaSet(3, 0, -1);
            i7 = i17;
            i9 = 0;
            i8 = 127;
            i10 = this.U1;
            music3 = this.o;
            if (i10 == 1) {
                music3.TrackTypeKeyAreaSet(1, i9, i8);
                return;
            }
            iTrackCount3 = music3.TrackCount(0) - 1;
            int i25 = 0;
            while (iTrackCount3 >= 0) {
                Track3 = this.o.Track(0, iTrackCount3);
                if (Track3 == null) {
                    i8 = i8;
                } else {
                    i8 = i8;
                }
                iTrackCount3--;
                i8 = i8;
            }
            return;
        }
        if (z) {
            this.d2 = (this.c2 - ((this.J0 - 1) / 7)) - 1;
        }
        int i26 = this.X1 == 1 ? this.d2 : this.c2;
        int i27 = this.K0;
        int i28 = (i26 * 12) + i27;
        this.b3 = i28;
        int i29 = (this.c2 * 12) + iy1.i(this.J0, 12, 7, i27);
        this.c3 = i29;
        int i30 = (i29 - i28) + 1;
        int i31 = 0;
        int i32 = 1;
        while (true) {
            int iTrackCount4 = this.o.TrackCount(0);
            music = this.o;
            if (i32 >= iTrackCount4) {
                break;
            }
            Track Track4 = music.Track(0, i32);
            if (Track4 != null && Track4.fType == 1 && Track4.fRecord) {
                i31++;
            }
            i32++;
        }
        int i33 = music.fChordScan;
        if (i33 != 0) {
            String[] strArr = this.s5;
            if (i33 == 1) {
                if (W0()) {
                    tp2 tp2Var3 = this.n;
                    if (tp2Var3.b != 0) {
                        i6 = tp2Var3.c + tp2Var3.d + 1;
                    } else if (this.X1 == 1) {
                        i6 = i28 + 18;
                    } else {
                        i6 = i28 + 12;
                    }
                } else if (this.X1 == 1) {
                    i6 = i28 + 18;
                } else {
                    i6 = i28 + 12;
                }
                i30 = (i29 - i6) + 1;
                int i34 = i6 - 1;
                this.o.TrackTypeKeyAreaSet(3, 0, i34);
                if (this.U1 == 0 || i31 <= 1) {
                    i2 = i6;
                    i = i2;
                    track = null;
                } else {
                    Track Track5 = this.o.Track(0, strArr[4]);
                    this.o.TrackKeyAreaSet(Track5, 0, i34, -1, -1);
                    i2 = i6;
                    i = i2;
                    track = Track5;
                }
            } else if (i33 == 2) {
                if (W0()) {
                    tp2 tp2Var4 = this.n;
                    if (tp2Var4.b != 0) {
                        i28 = tp2Var4.c + tp2Var4.d + 1;
                    } else if (this.X1 == 1) {
                        i28 += 18;
                    } else {
                        i28 += 12;
                    }
                } else if (this.X1 == 1) {
                    i28 += 18;
                } else {
                    i28 += 12;
                }
                i30 = (i29 - i28) + 1;
                this.o.TrackTypeKeyAreaSet(3, i28, 127);
                if (this.U1 != 0) {
                    Track Track6 = this.o.Track(0, strArr[4]);
                    this.o.TrackKeyAreaSet(Track6, 0, i28 - 1, -1, -1);
                    i2 = i28;
                    i = i2;
                    track = Track6;
                }
            } else if (i33 == 3) {
                music.TrackTypeKeyAreaSet(3, 0, 127);
            }
            int i35 = 0;
            i3 = 1;
            while (true) {
                iTrackCount = this.o.TrackCount(0);
                music2 = this.o;
                if (i3 < iTrackCount) {
                    break;
                }
                Track2 = music2.Track(0, i3);
                if (Track2 == null && Track2 != track && Track2.fType == 1 && Track2.fRecord) {
                    i35++;
                }
                i3++;
            }
            i4 = 0;
            for (iTrackCount2 = music2.TrackCount(0) - 1; iTrackCount2 >= 0; iTrackCount2--) {
                Track = this.o.Track(0, iTrackCount2);
                if (Track == null && Track != track && Track.fType == 1) {
                    if (this.U1 == 1 && Track.fRecord && i35 > 1) {
                        int i36 = this.V1;
                        if (i36 <= 0) {
                            i36 = i30 / i35;
                        }
                        Music music6 = this.o;
                        int i37 = this.c2;
                        if (i4 == 0) {
                            music6.TrackKeyAreaSet(Track, i2, (i36 + i) - 1, i37, i);
                            i5 = i4;
                        } else if (i4 >= i35 - 1) {
                            i5 = i4;
                            music6.TrackKeyAreaSet(Track, (i36 * i4) + i, 127, i37, i);
                        } else {
                            i5 = i4;
                            music6.TrackKeyAreaSet(Track, (i36 * i5) + i, (((i5 + 1) * i36) + i) - 1, i37, i);
                        }
                        i4 = i5 + 1;
                    } else {
                        this.o.TrackKeyAreaSet(Track, i2, 127, -1, -1);
                    }
                }
            }
        }
        music.TrackTypeKeyAreaSet(3, 0, -1);
        i = i28;
        i2 = 0;
        track = null;
        int i38 = 0;
        i3 = 1;
        while (true) {
            iTrackCount = this.o.TrackCount(0);
            music2 = this.o;
            if (i3 < iTrackCount) {
                break;
                break;
            } else {
                Track2 = music2.Track(0, i3);
                if (Track2 == null) {
                }
                i3++;
            }
        }
        i4 = 0;
        while (iTrackCount2 >= 0) {
            Track = this.o.Track(0, iTrackCount2);
            i4 = Track == null ? i4 : i4;
        }
    }
