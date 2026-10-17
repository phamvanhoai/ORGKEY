    /* JADX WARN: Code duplicated, block: B:21:0x0042  */
    /* JADX WARN: Code duplicated, block: B:27:0x0053 A[PHI: r7
  0x0053: PHI (r7v9 int) = (r7v8 int), (r7v13 int) binds: [B:31:0x0060, B:25:0x0050] A[DONT_GENERATE, DONT_INLINE]] */
    public int CalcChordKey(int i, int i2) {
        int i3;
        int i4 = (i2 % 12) + (i - (this.fChordRoot % 12));
        int i5 = i % 12;
        for (int i6 = 0; i6 < 12; i6++) {
            short[][] sArr = this.fMusic.fChordScale;
            short s = sArr[this.fChordType][i6];
            short s2 = sArr[i2 / 12][i6];
            if (i5 == s && s2 != -1) {
                i4 -= s - s2;
                break;
            }
        }
        Music music = this.fMusic;
        int i7 = music.fChordControl;
        if (i7 != 1) {
            if (i7 != 2) {
                if (i7 != 3) {
                    if (i7 == 4 && i4 - i > 6) {
                        i4 -= 12;
                    }
                } else if (music.fTracks.fChordOctaveMove % 2 == 0) {
                    i3 = i4 + 12;
                    if (i3 - i <= 16) {
                        i4 = i3;
                    }
                }
            } else if (music.fTracks.fChordOctaveMove % 2 == 1) {
                i3 = i4 + 12;
                if (i3 - i <= 16) {
                    i4 = i3;
                }
            }
        } else if (i4 - i > 8) {
            i4 -= 12;
        }
        while (i4 < 21) {
            i4 += 12;
        }
        while (i4 > 108) {
            i4 -= 12;
        }
        return i4;
    }