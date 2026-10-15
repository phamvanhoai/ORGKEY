public boolean ScriptEvent(String str, String[] strArr, int[] iArr, Track track, Music music) {
        try {
            if (!this.ScriptText.isEmpty()) {
                if (this.ScriptSetting == null) {
                    this.ScriptSetting = new ad4();
                }
                if (this.Script == null) {
                    il3 il3Var = new il3();
                    this.Script = il3Var;
                    il3Var.b(this.ScriptText, this.ScriptCallback, music.fScriptTimer, music.fPlatform);
                    this.Script.d();
                }
                ad4 ad4Var = this.ScriptParam;
                if (ad4Var == null) {
                    this.ScriptParam = new ad4();
                } else {
                    ad4Var.b();
                }
                for (int i = 0; i < strArr.length; i++) {
                    this.ScriptParam.e(iArr[i], strArr[i]);
                }
                double[] dArr = new double[1];
                if (this.Script.c(str, this.ScriptParam, dArr, track) && dArr[0] != 0.0d) {
                    return true;
                }
            }
        } catch (Exception unused) {
        }
        return false;
    }
