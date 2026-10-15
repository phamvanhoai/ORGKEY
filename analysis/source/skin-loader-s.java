    public final void s(MainActivity mainActivity, String str, int i, boolean z, boolean z2) {
        int iMin;
        Bitmap bitmapCreateScaledBitmap;
        try {
            Bitmap bitmapDecodeResource = BitmapFactory.decodeResource(this.J, i);
            int i2 = this.t;
            this.l = i2;
            if (i2 == bitmapDecodeResource.getWidth()) {
                this.l -= 2;
            }
            int i3 = this.u;
            if (z) {
                iMin = i3 - 2;
                this.m = iMin;
            } else {
                iMin = Math.min(i3 - 2, (bitmapDecodeResource.getHeight() * this.l) / bitmapDecodeResource.getWidth());
                this.m = iMin;
            }
            int i4 = this.l;
            if (i4 > 2048) {
                this.m = (iMin * 2048) / i4;
                this.l = 2048;
            }
            this.v0 = bitmapDecodeResource.getWidth();
            Bitmap bitmap = this.P;
            if (bitmap != null) {
                bitmap.recycle();
            }
            if (this.l == bitmapDecodeResource.getHeight() && this.m == bitmapDecodeResource.getHeight()) {
                bitmapCreateScaledBitmap = bitmapDecodeResource.copy(Bitmap.Config.ARGB_8888, true);
                this.P = bitmapCreateScaledBitmap;
            } else {
                bitmapCreateScaledBitmap = Bitmap.createScaledBitmap(bitmapDecodeResource, this.l, this.m, true);
                this.P = bitmapCreateScaledBitmap;
            }
            if (bitmapDecodeResource != bitmapCreateScaledBitmap) {
                bitmapDecodeResource.recycle();
            }
            w(xs1.d(mainActivity, str));
            if (this.m0 == null) {
                this.m0 = new int[]{i};
            }
            this.n0 = str;
            this.o0 = false;
            if (z2) {
                Arrays.fill(this.h0, "");
                A("");
            }
        } catch (Exception | OutOfMemoryError unused) {
        }
    }\n