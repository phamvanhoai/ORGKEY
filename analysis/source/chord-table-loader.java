    public void ChordTable(String str) {
        String[] strArrSplit = str.split("\\r?\\n");
        this.fChordCount = Math.min(strArrSplit.length, MaxChannelCount);
        for (int i = 0; i < this.fChordCount; i++) {
            String[] strArrSplit2 = strArrSplit[i].split(" ");
            this.fChordName[i] = strArrSplit2[0];
            int i2 = 0;
            while (i2 < 12) {
                int i3 = i2 + 1;
                int length = strArrSplit2.length;
                short[][] sArr = this.fChordScale;
                if (i3 < length) {
                    sArr[i][i2] = (short) Integer.parseInt(strArrSplit2[i3]);
                } else {
                    sArr[i][i2] = -1;
                }
                i2 = i3;
            }
            for (int i4 = 0; i4 < 12; i4++) {
                int i5 = (i * 12) + i4;
                this.fChordTable[i5] = 0;
                for (int i6 = 0; i6 < 12; i6++) {
                    short s = this.fChordScale[i][i6];
                    if (s != -1) {
                        int[] iArr = this.fChordTable;
                        iArr[i5] = (1 << ((s + i4) % 12)) | iArr[i5];
                    }
                }
            }
        }
    }\n