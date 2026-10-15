    /* JADX WARN: Code duplicated, block: B:66:0x0118  */
    @Override // android.app.Activity, android.view.Window.Callback
    public final boolean dispatchTouchEvent(MotionEvent motionEvent) {
        Window window;
        int i;
        short s;
        int i2;
        int i3;
        int i4;
        double d;
        try {
            if (!N0() && ((window = getWindow()) == null || !window.superDispatchTouchEvent(motionEvent))) {
                int i5 = -1;
                int action = (!this.q4 || this.N) ? -1 : motionEvent.getAction() & 255;
                int action2 = (motionEvent.getAction() & 65280) >> 8;
                int pointerCount = motionEvent.getPointerCount();
                int i6 = pointerCount > 20 ? 20 : pointerCount;
                int measuredHeight = this.I.getMeasuredHeight();
                int i7 = 1000;
                switch (action) {
                    case 0:
                    case 2:
                    case 4:
                    case 5:
                        short s2 = 53;
                        this.e0 = Arrays.copyOf(this.I5, this.o.fKeySet.length);
                        if (action != 2) {
                            float x = motionEvent.getX(action2);
                            float y = motionEvent.getY(action2);
                            if (y <= measuredHeight) {
                                i = 2;
                                S(0, x, y, measuredHeight, action2, motionEvent.getPointerId(action2));
                            } else {
                                i = 2;
                            }
                        } else {
                            i = 2;
                        }
                        int i8 = 0;
                        while (true) {
                            int[] iArr = this.f0;
                            float[] fArr = this.h0;
                            float[] fArr2 = this.g0;
                            if (i8 < i6) {
                                float x2 = motionEvent.getX(i8);
                                float y2 = motionEvent.getY(i8);
                                float f = measuredHeight;
                                if (y2 > f) {
                                    float f2 = y2 - f;
                                    int iD0 = D0(x2, f2);
                                    if (iD0 != -1) {
                                        fArr2[iD0] = x2;
                                        fArr[iD0] = f2;
                                        this.e0[iD0] = true;
                                        if ((this.O0 || this.n2) && (this.n2 || !this.m2)) {
                                            iArr[iD0] = F0(fArr2[iD0], f2);
                                        } else {
                                            iArr[iD0] = 100;
                                        }
                                    }
                                } else if (action == i) {
                                    S(2, x2, y2, measuredHeight, i8, motionEvent.getPointerId(i8));
                                }
                                i8++;
                            } else {
                                boolean[] zArr = this.o.fKeySet;
                                this.d0 = Arrays.copyOf(zArr, zArr.length);
                                boolean z = this.z0 == i;
                                int i9 = 0;
                                while (true) {
                                    if (i9 < 128) {
                                        if (!this.e0[i9]) {
                                            Music music = this.o;
                                            if (music.fKeySet[i9]) {
                                                if (this.k2 || this.m2) {
                                                    music.RecordMusic((short) 53, -1, 1000, -1);
                                                }
                                                if (this.l2 || this.n2) {
                                                    this.o.RecordMusic((short) 51, -1, 100, -1);
                                                    this.o.RecordMusic((short) 52, -1, 0, -1);
                                                }
                                                l(i9, true);
                                            }
                                        }
                                        i9++;
                                    } else {
                                        int i10 = 0;
                                        for (int i11 = 128; i10 < i11; i11 = 128) {
                                            if (this.e0[i10]) {
                                                boolean z2 = this.o.fKeySet[i10];
                                                float[] fArr3 = this.j0;
                                                float[] fArr4 = this.i0;
                                                if (z2) {
                                                    if (Math.abs(fArr[i10] - fArr3[i10]) < this.Y / 2.0f) {
                                                        if (this.k2 && this.p2 == 0) {
                                                            float fAbs = Math.abs(fArr2[i10] - fArr4[i10]);
                                                            float f3 = this.Y;
                                                            float f4 = f3 / 12.0f;
                                                            d = 2.0d;
                                                            Music music2 = this.o;
                                                            if (fAbs > f4) {
                                                                int iRound = (int) Math.round(Music.KeyToPitch((((((double) (fArr2[i10] - fArr4[i10])) * ((double) this.S0)) * 2.0d) / ((double) f3)) + 12.0d, 500.0d));
                                                                i4 = -1;
                                                                music2.RecordMusic((short) 53, -1, iRound, -1);
                                                                i3 = 1000;
                                                            } else {
                                                                i4 = i5;
                                                                i3 = i7;
                                                                music2.RecordMusic(s2, i4, i3, i4);
                                                            }
                                                        } else {
                                                            i4 = i5;
                                                            i3 = i7;
                                                            d = 2.0d;
                                                        }
                                                        if (this.l2) {
                                                            this.o.RecordMusic((short) 51, i4, -iArr[i10], i4);
                                                            this.o.RecordMusic((short) 52, i4, Math.round(((fArr2[i10] - fArr4[i10]) * 100.0f) / this.Y), i4);
                                                        }
                                                    } else {
                                                        i4 = i5;
                                                        i3 = i7;
                                                        d = 2.0d;
                                                    }
                                                    if (this.n2) {
                                                        this.o.RecordMusic((short) 51, i4, -iArr[i10], i4);
                                                    }
                                                    if (this.m2 && this.p2 == 0) {
                                                        i2 = -1;
                                                        s = 53;
                                                        this.o.RecordMusic((short) 53, -1, (int) Math.round(Music.KeyToPitch((((((double) (fArr[i10] - fArr3[i10])) * ((double) this.T0)) * d) / ((double) this.c0)) + 12.0d, 500.0d)), -1);
                                                    } else {
                                                        i2 = -1;
                                                        s = 53;
                                                    }
                                                } else {
                                                    s = s2;
                                                    i2 = i5;
                                                    i3 = i7;
                                                    m(i10, iArr[i10], this.d3 && this.X1 == 0 && F0(fArr2[i10], fArr[i10]) >= 80, true);
                                                    if (z) {
                                                        ys1.n(this.t4, this.A0);
                                                        z = false;
                                                    }
                                                    fArr4[i10] = fArr2[i10];
                                                    fArr3[i10] = fArr[i10];
                                                }
                                            } else {
                                                s = s2;
                                                i2 = i5;
                                                i3 = i7;
                                            }
                                            i10++;
                                            i7 = i3;
                                            i5 = i2;
                                            s2 = s;
                                        }
                                        if (this.L0 && !Arrays.equals(this.d0, this.o.fKeySet)) {
                                            a0();
                                            return true;
                                        }
                                    }
                                }
                            }
                        }
                        break;
                    case 1:
                    case 3:
                    case 6:
                        float x3 = motionEvent.getX(action2);
                        float y3 = motionEvent.getY(action2);
                        S(1, x3, y3, measuredHeight, action2, motionEvent.getPointerId(action2));
                        if (this.Z2 != 0) {
                            boolean z3 = false;
                            for (int i12 = 0; i12 < i6; i12++) {
                                if (motionEvent.getY(i12) <= measuredHeight) {
                                    z3 = true;
                                }
                            }
                            if (!z3) {
                                this.t3.removeCallbacks(this.u3);
                                this.Z2 = 0;
                            }
                        }
                        float f5 = measuredHeight;
                        if (y3 > f5) {
                            boolean[] zArr2 = this.o.fKeySet;
                            this.d0 = Arrays.copyOf(zArr2, zArr2.length);
                            int iD1 = D0(x3, y3 - f5);
                            if (iD1 != -1) {
                                Music music3 = this.o;
                                if (music3.fKeySet[iD1]) {
                                    if (this.k2 || this.m2) {
                                        music3.RecordMusic((short) 53, -1, 1000, -1);
                                    }
                                    if (this.l2 || this.n2) {
                                        this.o.RecordMusic((short) 51, -1, 100, -1);
                                        this.o.RecordMusic((short) 52, -1, 0, -1);
                                    }
                                    l(iD1, true);
                                }
                            }
                            if (this.L0 && !Arrays.equals(this.d0, this.o.fKeySet)) {
                                a0();
                                return true;
                            }
                        }
                        break;
                }
            }
            return true;
        } catch (Throwable unused) {
            return false;
        }
    }
