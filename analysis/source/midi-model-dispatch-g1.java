public final boolean g1(String str, int i, int i2, int i3) {
        byte b = 2;
        if (this.T5 != 2) {
            this.U5 = str;
            this.V5 = i;
            this.W5 = i2;
            this.X5 = i3;
            sp2 sp2VarB = this.n.b(str, i, i2, i3);
            AlertDialog alertDialog = this.Y5;
            byte b2 = 1;
            if (alertDialog != null && alertDialog.isShowing()) {
                runOnUiThread(new x92(this, sp2VarB, b2));
                return false;
            }
            if (sp2VarB == null && str.equalsIgnoreCase("program")) {
                u0(240, i2 + 1);
                return true;
            }
            if (sp2VarB != null) {
                runOnUiThread(new x92(this, sp2VarB, b));
                return true;
            }
        }
        return false;
    }
