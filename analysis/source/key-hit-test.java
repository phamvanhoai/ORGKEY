    public final int E0(float f, float f2, float f3, float f4, int i, int i2, int i3) {
        int i4 = (i2 == 0 || i2 == 5) ? -1 : 0;
        for (int i5 = -1; i5 <= (this.J0 * 12) / 7; i5++) {
            int i6 = i5 + i2;
            int i7 = i6 % 12;
            if (i7 == 1 || i7 == 3 || i7 == 6 || i7 == 8 || i7 == 10) {
                float f5 = this.Y;
                float fRound = (i4 * f5) - Math.round(this.S[i7] * f5);
                if (f > fRound && f <= fRound + this.Z) {
                    float f6 = i;
                    if (f2 <= f6 + f4 && f2 >= f6) {
                        return Math.min(127, Math.max(0, (i3 * 12) + i6));
                    }
                }
            } else {
                i4++;
            }
        }
        if (!this.P0 || f2 > i + f4) {
            int i8 = 0;
            for (int i9 = 0; i9 <= (this.J0 * 12) / 7; i9++) {
                int i10 = i9 + i2;
                int i11 = i10 % 12;
                if (i11 != 1 && i11 != 3 && i11 != 6 && i11 != 8 && i11 != 10) {
                    float f7 = this.Y;
                    float f8 = i8 * f7;
                    if (f > f8 && f <= f8 + f7) {
                        float f9 = i;
                        if (f2 <= f9 + f3 && f2 >= f9) {
                            return Math.min(127, Math.max(0, (i3 * 12) + i10));
                        }
                    }
                    i8++;
                }
            }
        }
        return -1;
    }
