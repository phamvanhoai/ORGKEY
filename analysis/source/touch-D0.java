    public final int D0(float f, float f2) {
        int i = this.X1;
        if (i == 0) {
            return E0(f, f2, this.c0, this.a0, 0, this.K0, this.c2);
        }
        if (i != 1) {
            return -1;
        }
        int iE0 = E0(f, f2, this.c0 / 2, this.b0, 0, this.K0, this.d2);
        if (iE0 != -1) {
            return iE0;
        }
        int i2 = this.c0;
        return E0(f, f2, i2 / 2, this.b0, i2 / 2, this.K0, this.c2);
    }
