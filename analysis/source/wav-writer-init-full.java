    public a(String str, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) throws IOException {
        this.c = i;
        this.d = i2;
        this.e = i3;
        this.f = i4;
        bp3.e(str);
        if (i == 1) {
            this.a = new cl0(str, "rw");
            b();
        } else {
            if (i != 2) {
                return;
            }
            this.b = new bl0(new BufferedOutputStream(new FileOutputStream(str), 131072));
            Lame.init(i2, i6, i8, i7, i5);
        }
    }
