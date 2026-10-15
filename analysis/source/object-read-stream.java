    public void ReadFromStream(al0 al0Var, boolean z) throws IOException {
        Music music;
        if (!z) {
            Clear(false);
        }
        String strE = al0Var.e();
        this.fName = strE;
        String[] strArrSplit = strE.split("\r", -1);
        if (strArrSplit.length > 0) {
            this.fName = strArrSplit[0];
        }
        if (strArrSplit.length > 1) {
            this.fVersion = strArrSplit[1];
        } else {
            this.fVersion = "";
        }
        if (strArrSplit.length > 2) {
            this.fDescription = strArrSplit[2];
        }
        this.fGroup = al0Var.b();
        if (this.fVersion.compareTo("1.4.9") <= 0 || (music = this.fMusic) == null || !music.MsgVersion) {
            return;
        }
        this.fPlatform.c(1, "", "This file was created by a newer version,\nYou may need to update the app.");
        this.fMusic.MsgVersion = false;
    }
