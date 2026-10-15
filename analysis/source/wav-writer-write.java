    public final void c(short[] sArr, int i) throws IOException {
        int i2 = this.c;
        if (i2 == 1) {
            int i3 = (this.f / 8) * this.e * i;
            ByteBuffer byteBufferAllocate = ByteBuffer.allocate(i3);
            byteBufferAllocate.order(ByteOrder.LITTLE_ENDIAN);
            byteBufferAllocate.asShortBuffer().put(sArr);
            this.a.write(byteBufferAllocate.array(), 0, i3);
            return;
        }
        if (i2 != 2) {
            return;
        }
        byte[] bArr = new byte[(int) ((((double) (sArr.length * 2)) * 1.25d) + 7200.0d)];
        short[] sArr2 = new short[i];
        short[] sArr3 = new short[i];
        for (int i4 = 0; i4 < i; i4++) {
            int i5 = i4 << 1;
            sArr2[i4] = sArr[i5];
            sArr3[i4] = sArr[i5 + 1];
        }
        int iEncode = Lame.encode(sArr2, sArr3, i, bArr);
        if (iEncode > 0) {
            this.b.write(bArr, 0, iEncode);
        }
    }
