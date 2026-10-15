public final boolean f1(int i, boolean z, boolean z2, boolean z3, boolean z4) {
        int i2;
        byte b = 0;
        if (this.T5 != 1) {
            this.U5 = "keyboard";
            this.V5 = -1;
            this.W5 = i;
            this.X5 = 0;
            if (z) {
                this.X5 = 1;
                i2 = 1;
            } else {
                i2 = 0;
            }
            if (z2) {
                i2 += 2;
                this.X5 = i2;
            }
            if (z3) {
                i2 += 4;
                this.X5 = i2;
            }
            if (z4) {
                i2 += 8;
                this.X5 = i2;
            }
            sp2 sp2VarB = this.n.b("keyboard", -1, i, i2);
            AlertDialog alertDialog = this.Y5;
            if (alertDialog != null && alertDialog.isShowing()) {
                runOnUiThread(new v92(this, i, z, z2, z3, z4, sp2VarB));
                return false;
            }
            if (sp2VarB != null) {
                runOnUiThread(new x92(this, sp2VarB, b));
                return true;
            }
        }
        return false;
    }
