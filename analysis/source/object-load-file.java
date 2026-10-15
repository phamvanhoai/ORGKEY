    public boolean LoadFromFile(String str, boolean z) throws IOException {
        boolean z2 = false;
        try {
            al0 al0Var = new al0(new BufferedInputStream(new FileInputStream(str), 131072));
            try {
                byte[] bArr = new byte[32];
                al0Var.read(bArr);
                String str2 = "Music Studio " + Signature();
                if (new String(bArr, 0, str2.length()).equalsIgnoreCase(str2)) {
                    try {
                        b44.h();
                        Music music = this.fMusic;
                        if (music != null) {
                            music.MsgVersion = true;
                        }
                        ReadFromStream(al0Var, z);
                        z2 = true;
                    } catch (Exception unused) {
                        Error("Error", "Error loading " + str + System.lineSeparator() + System.lineSeparator() + "The file is corrupted or too large for available memory.");
                    } catch (OutOfMemoryError unused2) {
                        Error("Error", "Error loading " + str + System.lineSeparator() + System.lineSeparator() + "The file is corrupted or too large for available memory.");
                    }
                } else {
                    Error("Error", "Wrong file format for " + Signature() + "!");
                }
                al0Var.close();
            } catch (Throwable th) {
                al0Var.close();
                throw th;
            }
        } catch (Exception unused3) {
        }
        return z2;
    }
