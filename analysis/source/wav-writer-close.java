    public final void a() throws IOException {
        int i = this.c;
        if (i == 1) {
            if (this.a != null) {
                b();
                this.a.close();
                this.a = null;
                return;
            }
            return;
        }
        if (i == 2 && this.b != null) {
            byte[] bArr = new byte[7200];
            int iFlush = Lame.flush(bArr);
            if (iFlush > 0) {
                this.b.write(bArr, 0, iFlush);
            }
            this.b.close();
            this.b = null;
        }
    }
