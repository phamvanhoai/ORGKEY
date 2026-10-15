    public final String e() {
        int iB = b();
        if (iB <= 0) {
            return "";
        }
        byte[] bArr = new byte[iB];
        return new String(bArr, 0, read(bArr, 0, iB));
    }
