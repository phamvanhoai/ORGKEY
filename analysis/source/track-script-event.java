public boolean ScriptEvent(String str, String[] strArr, int[] iArr) {
        if (str.isEmpty()) {
            this.fMusic.fScriptTimer.b(null);
        }
        int i = this.fChannels.fMode;
        if (i < 0 || i >= Instrument.MaxModeCount) {
            return false;
        }
        InstMode instMode = this.fMusic.fInstruments.Get(this.fInstrument).fModeList[this.fChannels.fMode];
        if (str.isEmpty()) {
            il3 il3Var = instMode.Script;
            if (il3Var != null) {
                il3Var.d();
            }
            Arrays.fill(instMode.ScriptLastKeys, 0);
            str = "mode";
        }
        return instMode.ScriptEvent(str, strArr, iArr, this, this.fMusic);
    }
