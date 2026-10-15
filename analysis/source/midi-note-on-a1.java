public final void a1(int i, int i2, int i3) {
        int i4;
        int i5;
        int i6;
        StringBuilder sbP = mt4.p(i, "NoteOn: ", " Key:", i2, " Velocity:");
        sbP.append(i3);
        P0(sbP.toString());
        if (this.G5 && this.E5 && (this.X0 & 1) != 0) {
            int i7 = i2 % 128;
            if (i3 <= 0 || i3 >= 128) {
                if (this.n.b("NoteOn", i, i7, -1) == null) {
                    o(i, i7);
                    return;
                }
                return;
            }
            if (this.o.fKeySet[i7] || g1("NoteOn", i, i7, -1)) {
                return;
            }
            if (i3 <= 0) {
                o(i, i7);
            } else {
                if (!this.Z0 || i >= 8) {
                    i4 = i7;
                } else {
                    i4 = (i * 12) + i7;
                    if (i4 >= 128) {
                        i4 = 127;
                    }
                }
                this.I5[i4] = true;
                int i8 = this.V0;
                int iMin = i8 == 0 ? 127 : Math.min(127, (i8 * i3) / 100);
                int i9 = this.W0;
                m(i4, iMin, i9 != 0 && i3 >= 127 - (i9 * 4), false);
                if (this.L0) {
                    this.h3 |= 8;
                }
            }
            if (this.a1) {
                if (i7 < this.b3 || i7 >= this.c3) {
                    while (i7 < this.b3 && (i6 = this.c2) > 0) {
                        this.c2 = i6 - 1;
                        H1(true);
                    }
                    while (i7 >= this.c3 && (i5 = this.c2) < 9) {
                        this.c2 = i5 + 1;
                        H1(true);
                    }
                    this.h3 |= 1;
                }
            }
        }
    }
