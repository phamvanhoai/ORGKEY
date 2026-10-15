    public void lowLatencyInstruments(final int[] iArr, final int i, final String str) {
        int i2 = this.fLowLatencyMethod;
        if (i2 == 3) {
            this.fPlatform.a.i(new zd1((byte) 17));
        } else {
            if (i2 != 4) {
                return;
            }
            new Thread() { // from class: sofeh.music.Music.1
                @Override // java.lang.Thread, java.lang.Runnable
                public void run() {
                    for (int i3 = 3; i3 < 10; i3++) {
                        String string = Integer.toString(b44.e(0, Integer.MAX_VALUE));
                        Music music = Music.this;
                        StringBuilder sb = new StringBuilder();
                        sb.append(str);
                        String str2 = File.separator;
                        music.ExportKeyToAudioFile(ep4.o(sb, str2, string, ".wav"), 1, i, 0, i3, iArr, 0, 1);
                        try {
                            x13 x13Var = Music.this.fPlatform;
                            String str3 = str + str2 + string + ".wav";
                            ew ewVar = x13Var.a;
                            r03 r03Var = new r03();
                            r03Var.a = i3 - 3;
                            r03Var.b = str3;
                            ewVar.i(r03Var);
                        } catch (Exception unused) {
                        }
                    }
                }
            }.start();
        }
    }
