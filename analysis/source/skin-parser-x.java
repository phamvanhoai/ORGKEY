    /* JADX WARN: Code duplicated, block: B:99:0x0238  */
    public final void x(int i, int i2, String str) {
        vs3 vs3Var;
        ArrayList arrayList = this.i;
        int[] iArr = this.x;
        this.H = true;
        String[] strArrSplit = str.split("\\r?\\n");
        String[] strArrSplit2 = strArrSplit[1].split(" ");
        this.a = Integer.parseInt(strArrSplit2[0]);
        this.b = Integer.parseInt(strArrSplit2[1]);
        this.c = Integer.parseInt(strArrSplit2[2]);
        int i3 = Integer.parseInt(strArrSplit2[3]);
        this.d = i3;
        this.j = Math.min(this.j, i3 - 1);
        this.e = Integer.parseInt(strArrSplit2[4]);
        this.f = Integer.parseInt(strArrSplit2[5]);
        this.g = Integer.parseInt(strArrSplit2[6]);
        this.h = strArrSplit2.length > 7 ? Integer.parseInt(strArrSplit2[7]) : 10;
        iArr[1] = strArrSplit2.length > 8 ? Integer.parseInt(strArrSplit2[8]) : -49152;
        iArr[2] = strArrSplit2.length > 9 ? Integer.parseInt(strArrSplit2[9]) : -16715536;
        iArr[0] = strArrSplit2.length > 10 ? Integer.parseInt(strArrSplit2[10]) : -13619152;
        this.y = strArrSplit2.length > 11 ? Integer.parseInt(strArrSplit2[11]) : 0;
        this.z = strArrSplit2.length > 12 ? Integer.parseInt(strArrSplit2[12]) : 1;
        iArr[3] = strArrSplit2.length > 13 ? Integer.parseInt(strArrSplit2[13]) : -6250336;
        this.l = i;
        this.m = i2;
        float f = this.a;
        this.n = i / f;
        int i4 = this.b;
        int i5 = 1;
        int i6 = this.c;
        float f2 = (this.d * i6) + i4;
        this.o = i2 / f2;
        int i7 = this.t;
        this.p = i7;
        int i8 = (i2 * i7) / i;
        this.q = i8;
        this.v = i7 / f;
        float f3 = i8 / f2;
        this.w = f3;
        this.r = (int) (i4 * f3);
        this.s = (int) (f3 * (i4 + i6));
        this.A = null;
        this.C = null;
        this.D = null;
        this.E = null;
        this.F = null;
        this.G = null;
        arrayList.clear();
        int i9 = 2;
        while (i9 < strArrSplit.length) {
            String[] strArrSplit3 = strArrSplit[i9].split(" ");
            int i10 = Integer.parseInt(strArrSplit3[0]);
            int i11 = Integer.parseInt(strArrSplit3[i5]);
            int i12 = Integer.parseInt(strArrSplit3[2]);
            int i13 = Integer.parseInt(strArrSplit3[3]);
            int i14 = Integer.parseInt(strArrSplit3[4]);
            int i15 = Integer.parseInt(strArrSplit3[5]);
            int i16 = Integer.parseInt(strArrSplit3[6]);
            int i17 = Integer.parseInt(strArrSplit3[7]);
            vs3 vs3Var2 = new vs3(i10, i11, i12, i13, i14, i15, i16, i17, Integer.parseInt(strArrSplit3[8]), strArrSplit3.length > 9 ? Integer.parseInt(strArrSplit3[9]) : i5, strArrSplit3.length > 10 ? Integer.parseInt(strArrSplit3[10]) : this.e, strArrSplit3.length > 11 ? Integer.parseInt(strArrSplit3[11]) : this.f, strArrSplit3.length > 12 ? Integer.parseInt(strArrSplit3[12]) : this.g, strArrSplit3.length > 13 ? Integer.parseInt(strArrSplit3[13]) : this.h, strArrSplit3.length > 14 ? Integer.parseInt(strArrSplit3[14]) : iArr[i5], strArrSplit3.length > 15 ? Integer.parseInt(strArrSplit3[15]) : iArr[2], strArrSplit3.length > 16 ? Integer.parseInt(strArrSplit3[16]) : iArr[0], strArrSplit3.length > 17 ? Integer.parseInt(strArrSplit3[17]) : this.y, true);
            arrayList.add(vs3Var2);
            c(vs3Var2);
            if (i10 == 30 || i10 == 31) {
                this.A = vs3Var2;
                int i18 = vs3Var2.f;
                vs3 vs3Var3 = new vs3(32, 5, 1, 5, 5, i18, i16, Math.max(i5, (i17 - i18) / 5) + i18, this.A.i, 1, this.e, this.f, this.g, this.h, 0, 0, 0, 0, false);
                this.B = vs3Var3;
                arrayList.add(vs3Var3);
            } else if (i10 == 35) {
                this.G = vs3Var2;
            } else if (i10 == 60) {
                this.C = vs3Var2;
            } else if (i10 == 70) {
                this.E = vs3Var2;
            } else if (i10 == 121 || i10 == 130) {
                this.F = vs3Var2;
            } else if (i10 == 179) {
                this.D = vs3Var2;
            } else if (i10 == 135 || i10 == 136) {
                this.F = vs3Var2;
            }
            i9++;
            i5 = 1;
        }
        if (this.c == 0 && (vs3Var = this.G) != null) {
            this.d = 1;
            int i19 = this.b;
            int i20 = vs3Var.g;
            this.c = i19 - i20;
            this.b = i20;
            this.r = (int) (this.w * i20);
            for (int i21 = 1; i21 < arrayList.size(); i21++) {
                c((vs3) arrayList.get(i21));
            }
        }
        int i22 = this.d;
        if (i22 > 1) {
            arrayList.add(new vs3(1, Math.min(i22 - 1, 2), Math.min(this.d - 1, 2), 1, Math.min(this.d - 1, 2), 0, this.b, Math.min(this.d - 1, 2), (this.d * this.c) + this.b, 1, 0, 0, 0, 0, 0, 0, 0, 0, true));
        }
    }\n