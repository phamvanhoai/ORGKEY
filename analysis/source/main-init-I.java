    /* JADX WARN: Code duplicated, block: B:101:0x0305  */
    /* JADX WARN: Code duplicated, block: B:104:0x030f  */
    /* JADX WARN: Code duplicated, block: B:106:0x0312  */
    /* JADX WARN: Code duplicated, block: B:109:0x031b  */
    /* JADX WARN: Code duplicated, block: B:112:0x0324  */
    /* JADX WARN: Code duplicated, block: B:113:0x032e  */
    /* JADX WARN: Code duplicated, block: B:116:0x0341  */
    /* JADX WARN: Code duplicated, block: B:118:0x0351  */
    /* JADX WARN: Code duplicated, block: B:124:0x039e  */
    /* JADX WARN: Code duplicated, block: B:127:0x03db  */
    /* JADX WARN: Code duplicated, block: B:130:0x03e2  */
    /* JADX WARN: Code duplicated, block: B:133:0x03e9  */
    /* JADX WARN: Code duplicated, block: B:136:0x03f0  */
    /* JADX WARN: Code duplicated, block: B:139:0x03f7  */
    /* JADX WARN: Code duplicated, block: B:142:0x03fe  */
    /* JADX WARN: Code duplicated, block: B:89:0x0240  */
    /* JADX WARN: Code duplicated, block: B:92:0x0287  */
    /* JADX WARN: Code duplicated, block: B:95:0x02ef  */
    /* JADX WARN: Code duplicated, block: B:98:0x02fb  */
    public final void I() {
        boolean z;
        us3 us3Var;
        us3 us3Var2;
        Bitmap bitmapCreateBitmap;
        Bitmap bitmapCreateBitmap2;
        Bitmap bitmapCreateBitmap3;
        Bitmap bitmapCreateBitmap4;
        Bitmap bitmapCreateBitmap5;
        Bitmap bitmapCreateBitmap6;
        int width;
        int height;
        int i;
        int i2;
        int i3;
        int i4;
        int i5;
        int i6;
        int i7;
        int iMax;
        int iMax2;
        int i8;
        tp2 tp2Var;
        int i9;
        int i10;
        O0();
        us3 us3Var3 = this.m;
        us3Var3.k = Integer.parseInt(this.k0.getString("tab_mode", Integer.toString(us3Var3.k)));
        if (this.W < 1.6d) {
            this.m.k = 1;
            this.X1 = 1;
        }
        if (this.u0) {
            this.R = new TextToSpeech(this, new rb2(this));
        }
        if (!this.c5 || (i10 = this.U0) <= 0) {
            z = false;
        } else {
            double d = this.V;
            if (d <= 9.0d || d >= 24.0d) {
                this.m.t(this, "skin/skin_m5.Skin", new int[]{R.drawable.skin_m5, R.drawable.skin_m5_1, R.drawable.skin_m5_2, R.drawable.skin_m5_3, R.drawable.skin_m5_4}, i10 == 2);
            } else {
                this.m.t(this, "skin/skin_m9.Skin", new int[]{R.drawable.skin_m9, R.drawable.skin_m9_1, R.drawable.skin_m9_2, R.drawable.skin_m9_3, R.drawable.skin_m9_4}, i10 == 2);
            }
            this.J0 = 12;
            this.K0 = 0;
            this.X1 = 1;
            this.c2 = 5;
            z = true;
        }
        if (W0()) {
            if (this.Y0 > 0) {
                if (this.m.r(this, this.V3 + File.separator + ((String) this.n.f), this.Y0 == 2, null, this.k0.getString("skin_pincode", "")) != 0) {
                    double d2 = this.V;
                    if (d2 <= 9.0d || d2 >= 24.0d) {
                        this.m.t(this, "skin/skin_m5.Skin", new int[]{R.drawable.skin_m5, R.drawable.skin_m5_1, R.drawable.skin_m5_2, R.drawable.skin_m5_3, R.drawable.skin_m5_4}, this.Y0 == 2);
                    } else {
                        this.m.t(this, "skin/skin_m9.Skin", new int[]{R.drawable.skin_m9, R.drawable.skin_m9_1, R.drawable.skin_m9_2, R.drawable.skin_m9_3, R.drawable.skin_m9_4}, this.Y0 == 2);
                    }
                }
                z = true;
            }
            if ((this.Y0 > 0 || !this.a1) && (i9 = (tp2Var = this.n).b) > 0) {
                this.J0 = iy1.i(i9, 7, 12, 1);
                int i11 = tp2Var.c;
                this.K0 = i11 % 12;
                this.c2 = i11 / 12;
                this.X1 = 0;
            }
        }
        if (!z && this.m.r(this, this.k0.getString("skin_file", ""), false, null, this.k0.getString("skin_pincode", "")) != 0) {
            double d3 = this.V;
            if (d3 <= 9.0d || d3 >= 24.0d) {
                us3 us3Var4 = this.m;
                if (d3 > 5.300000190734863d) {
                    us3Var4.t(this, "skin/skin_5.Skin", new int[]{R.drawable.skin_5, R.drawable.skin_5_1, R.drawable.skin_5_2, R.drawable.skin_5_3, R.drawable.skin_5_4}, false);
                } else {
                    us3Var4.t(this, "skin/skin_4.Skin", new int[]{R.drawable.skin_4, R.drawable.skin_4_1, R.drawable.skin_4_2, R.drawable.skin_4_3, R.drawable.skin_4_4}, false);
                }
            } else {
                this.m.t(this, "skin/skin_9.Skin", new int[]{R.drawable.skin_9, 0, R.drawable.skin_9_2, R.drawable.skin_9_3, R.drawable.skin_9_4}, false);
            }
        }
        int i12 = 0;
        while (true) {
            us3Var = this.m;
            String[] strArr = us3Var.h0;
            if (i12 >= strArr.length) {
                break;
            }
            strArr[i12] = this.k0.getString("skin_file" + i12, "");
            i12++;
        }
        us3Var.A("");
        this.J.setVisibility(8);
        us3 us3Var5 = this.m;
        int i13 = us3Var5.k;
        if (i13 == 0) {
            ViewGroup.LayoutParams layoutParams = this.I.getLayoutParams();
            us3Var2 = this.m;
            layoutParams.height = us3Var2.r;
        } else {
            if (i13 != 1) {
                if (i13 == 2) {
                    ViewGroup.LayoutParams layoutParams2 = this.I.getLayoutParams();
                    us3Var2 = this.m;
                    layoutParams2.height = us3Var2.q;
                }
                us3Var5.j = Math.min(r1(us3Var5.j, "tab_index"), this.m.d - 1);
                if (this.W4) {
                    this.m0.commit();
                }
                this.W4 = false;
                this.I.setVisibility(8);
                this.I.setVisibility(0);
                ImageView imageView = this.I;
                imageView.setScrollY(((imageView.getLayoutParams().height - this.m.q) + 1) / 2);
                this.H.getLayoutParams().height = this.U - this.I.getLayoutParams().height;
                this.c0 = this.U - this.m.r;
                if (this.G != null) {
                    this.y.recycle();
                    this.z.recycle();
                    this.A.recycle();
                    this.B.recycle();
                    this.C.recycle();
                    this.D.recycle();
                    this.E.recycle();
                    this.F.recycle();
                    this.G.recycle();
                }
                bitmapCreateBitmap = Bitmap.createBitmap(this.m.b0);
                bitmapCreateBitmap2 = Bitmap.createBitmap(this.m.c0);
                bitmapCreateBitmap3 = Bitmap.createBitmap(this.m.d0);
                bitmapCreateBitmap4 = Bitmap.createBitmap(this.m.e0);
                bitmapCreateBitmap5 = Bitmap.createBitmap(this.m.f0);
                bitmapCreateBitmap6 = Bitmap.createBitmap(this.m.g0);
                width = this.T / this.J0;
                if (width > bitmapCreateBitmap.getWidth()) {
                    width = bitmapCreateBitmap.getWidth();
                }
                height = this.c0;
                if (height > bitmapCreateBitmap.getHeight()) {
                    height = bitmapCreateBitmap.getHeight();
                }
                if (this.T > 1000) {
                    int i14 = this.N0;
                    width /= i14;
                    height /= i14;
                }
                i = (width / 2) * 2;
                i2 = (height / 2) * 2;
                if (i < 2) {
                    i = 2;
                }
                if (i2 < 2) {
                    i2 = 2;
                }
                i3 = this.J0;
                i4 = i * i3;
                if (i4 > 2048) {
                    i2 = (i2 * 2048) / i4;
                    i = 2048 / i3;
                }
                if (this.N0 > 2) {
                    this.G = Bitmap.createBitmap(i3 * i, i2, Bitmap.Config.RGB_565);
                } else {
                    this.G = Bitmap.createBitmap(i3 * i, i2, Bitmap.Config.ARGB_8888);
                }
                i5 = this.c0;
                i6 = this.U / 3;
                i7 = this.Q0;
                if (i5 < i6) {
                    iMax = Math.max(i7 * 2, i2);
                    iMax2 = Math.max(this.Q0 * 2, i2 / 2);
                } else {
                    iMax = Math.max(i7 * 2, ((bitmapCreateBitmap3.getHeight() * i2) * 2) / bitmapCreateBitmap.getHeight());
                    iMax2 = Math.max(this.Q0 * 2, ((bitmapCreateBitmap5.getHeight() * i2) * 2) / bitmapCreateBitmap.getHeight());
                }
                this.y = Bitmap.createScaledBitmap(bitmapCreateBitmap, i, i2, true);
                this.z = Bitmap.createScaledBitmap(bitmapCreateBitmap2, i, i2, true);
                i8 = this.Q0;
                if (i8 <= 2 || this.c0 < this.U / 3) {
                    Bitmap bitmapCreateScaledBitmap = Bitmap.createScaledBitmap(bitmapCreateBitmap5, i, iMax / i8, true);
                    this.A = bitmapCreateScaledBitmap;
                    this.B = Bitmap.createScaledBitmap(bitmapCreateBitmap6, i, bitmapCreateScaledBitmap.getHeight(), true);
                } else {
                    Bitmap bitmapCreateScaledBitmap2 = Bitmap.createScaledBitmap(bitmapCreateBitmap3, i, iMax / i8, true);
                    this.A = bitmapCreateScaledBitmap2;
                    this.B = Bitmap.createScaledBitmap(bitmapCreateBitmap4, i, bitmapCreateScaledBitmap2.getHeight(), true);
                }
                int i15 = i2 / 2;
                this.C = Bitmap.createScaledBitmap(bitmapCreateBitmap, i, Math.max(2, i15), true);
                this.D = Bitmap.createScaledBitmap(bitmapCreateBitmap2, i, Math.max(2, i15), true);
                Bitmap bitmapCreateScaledBitmap3 = Bitmap.createScaledBitmap(bitmapCreateBitmap5, i, iMax2 / this.Q0, true);
                this.E = bitmapCreateScaledBitmap3;
                this.F = Bitmap.createScaledBitmap(bitmapCreateBitmap6, i, bitmapCreateScaledBitmap3.getHeight(), true);
                if (bitmapCreateBitmap != this.y) {
                    bitmapCreateBitmap.recycle();
                }
                if (bitmapCreateBitmap2 != this.z) {
                    bitmapCreateBitmap2.recycle();
                }
                if (bitmapCreateBitmap3 != this.A) {
                    bitmapCreateBitmap3.recycle();
                }
                if (bitmapCreateBitmap4 != this.B) {
                    bitmapCreateBitmap4.recycle();
                }
                if (bitmapCreateBitmap5 != this.E) {
                    bitmapCreateBitmap5.recycle();
                }
                if (bitmapCreateBitmap6 != this.F) {
                    bitmapCreateBitmap6.recycle();
                }
                Canvas canvas = new Canvas();
                this.O = canvas;
                canvas.setBitmap(this.G);
                this.H.setImageBitmap(this.G);
                this.c0 = this.U - this.I.getLayoutParams().height;
                this.Y = this.T / this.J0;
                this.Z = (((this.A.getWidth() * this.Y) / this.y.getWidth()) * 2.0f) / 3.0f;
                this.a0 = (this.A.getHeight() * this.c0) / this.y.getHeight();
                this.b0 = (this.E.getHeight() * this.c0) / this.y.getHeight();
                q(-1);
            }
            ViewGroup.LayoutParams layoutParams3 = this.I.getLayoutParams();
            us3Var2 = this.m;
            layoutParams3.height = us3Var2.s;
        }
        us3Var5 = us3Var2;
        us3Var5.j = Math.min(r1(us3Var5.j, "tab_index"), this.m.d - 1);
        if (this.W4) {
            this.m0.commit();
        }
        this.W4 = false;
        this.I.setVisibility(8);
        this.I.setVisibility(0);
        ImageView imageView2 = this.I;
        imageView2.setScrollY(((imageView2.getLayoutParams().height - this.m.q) + 1) / 2);
        this.H.getLayoutParams().height = this.U - this.I.getLayoutParams().height;
        this.c0 = this.U - this.m.r;
        if (this.G != null) {
            this.y.recycle();
            this.z.recycle();
            this.A.recycle();
            this.B.recycle();
            this.C.recycle();
            this.D.recycle();
            this.E.recycle();
            this.F.recycle();
            this.G.recycle();
        }
        bitmapCreateBitmap = Bitmap.createBitmap(this.m.b0);
        bitmapCreateBitmap2 = Bitmap.createBitmap(this.m.c0);
        bitmapCreateBitmap3 = Bitmap.createBitmap(this.m.d0);
        bitmapCreateBitmap4 = Bitmap.createBitmap(this.m.e0);
        bitmapCreateBitmap5 = Bitmap.createBitmap(this.m.f0);
        bitmapCreateBitmap6 = Bitmap.createBitmap(this.m.g0);
        width = this.T / this.J0;
        if (width > bitmapCreateBitmap.getWidth()) {
            width = bitmapCreateBitmap.getWidth();
        }
        height = this.c0;
        if (height > bitmapCreateBitmap.getHeight()) {
            height = bitmapCreateBitmap.getHeight();
        }
        if (this.T > 1000) {
            int i16 = this.N0;
            width /= i16;
            height /= i16;
        }
        i = (width / 2) * 2;
        i2 = (height / 2) * 2;
        if (i < 2) {
            i = 2;
        }
        if (i2 < 2) {
            i2 = 2;
        }
        i3 = this.J0;
        i4 = i * i3;
        if (i4 > 2048) {
            i2 = (i2 * 2048) / i4;
            i = 2048 / i3;
        }
        if (this.N0 > 2) {
            this.G = Bitmap.createBitmap(i3 * i, i2, Bitmap.Config.RGB_565);
        } else {
            this.G = Bitmap.createBitmap(i3 * i, i2, Bitmap.Config.ARGB_8888);
        }
        i5 = this.c0;
        i6 = this.U / 3;
        i7 = this.Q0;
        if (i5 < i6) {
            iMax = Math.max(i7 * 2, i2);
            iMax2 = Math.max(this.Q0 * 2, i2 / 2);
        } else {
            iMax = Math.max(i7 * 2, ((bitmapCreateBitmap3.getHeight() * i2) * 2) / bitmapCreateBitmap.getHeight());
            iMax2 = Math.max(this.Q0 * 2, ((bitmapCreateBitmap5.getHeight() * i2) * 2) / bitmapCreateBitmap.getHeight());
        }
        this.y = Bitmap.createScaledBitmap(bitmapCreateBitmap, i, i2, true);
        this.z = Bitmap.createScaledBitmap(bitmapCreateBitmap2, i, i2, true);
        i8 = this.Q0;
        if (i8 <= 2) {
            Bitmap bitmapCreateScaledBitmap4 = Bitmap.createScaledBitmap(bitmapCreateBitmap5, i, iMax / i8, true);
            this.A = bitmapCreateScaledBitmap4;
            this.B = Bitmap.createScaledBitmap(bitmapCreateBitmap6, i, bitmapCreateScaledBitmap4.getHeight(), true);
        } else {
            Bitmap bitmapCreateScaledBitmap5 = Bitmap.createScaledBitmap(bitmapCreateBitmap5, i, iMax / i8, true);
            this.A = bitmapCreateScaledBitmap5;
            this.B = Bitmap.createScaledBitmap(bitmapCreateBitmap6, i, bitmapCreateScaledBitmap5.getHeight(), true);
        }
        int i17 = i2 / 2;
        this.C = Bitmap.createScaledBitmap(bitmapCreateBitmap, i, Math.max(2, i17), true);
        this.D = Bitmap.createScaledBitmap(bitmapCreateBitmap2, i, Math.max(2, i17), true);
        Bitmap bitmapCreateScaledBitmap6 = Bitmap.createScaledBitmap(bitmapCreateBitmap5, i, iMax2 / this.Q0, true);
        this.E = bitmapCreateScaledBitmap6;
        this.F = Bitmap.createScaledBitmap(bitmapCreateBitmap6, i, bitmapCreateScaledBitmap6.getHeight(), true);
        if (bitmapCreateBitmap != this.y) {
            bitmapCreateBitmap.recycle();
        }
        if (bitmapCreateBitmap2 != this.z) {
            bitmapCreateBitmap2.recycle();
        }
        if (bitmapCreateBitmap3 != this.A) {
            bitmapCreateBitmap3.recycle();
        }
        if (bitmapCreateBitmap4 != this.B) {
            bitmapCreateBitmap4.recycle();
        }
        if (bitmapCreateBitmap5 != this.E) {
            bitmapCreateBitmap5.recycle();
        }
        if (bitmapCreateBitmap6 != this.F) {
            bitmapCreateBitmap6.recycle();
        }
        Canvas canvas2 = new Canvas();
        this.O = canvas2;
        canvas2.setBitmap(this.G);
        this.H.setImageBitmap(this.G);
        this.c0 = this.U - this.I.getLayoutParams().height;
        this.Y = this.T / this.J0;
        this.Z = (((this.A.getWidth() * this.Y) / this.y.getWidth()) * 2.0f) / 3.0f;
        this.a0 = (this.A.getHeight() * this.c0) / this.y.getHeight();
        this.b0 = (this.E.getHeight() * this.c0) / this.y.getHeight();
        q(-1);
    }
