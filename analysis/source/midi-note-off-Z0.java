public final void Z0(int i, int i2, int i3) {
        StringBuilder sbP = mt4.p(i, "NoteOff: ", " Key:", i2, " Velocity:");
        sbP.append(i3);
        P0(sbP.toString());
        if (this.G5 && this.E5 && (this.X0 & 1) != 0) {
            int i4 = i2 % 128;
            if (this.n.b("NoteOn", i, i4, -1) == null) {
                o(i, i4);
            }
        }
    }
