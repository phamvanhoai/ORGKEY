public sp2 a(int i2, int i3, boolean z) {
        ArrayList arrayList = (ArrayList) this.g;
        int size = arrayList.size();
        int i4 = 0;
        while (i4 < size) {
            Object obj = arrayList.get(i4);
            i4++;
            sp2 sp2Var = (sp2) obj;
            if (i2 == sp2Var.a && i3 == sp2Var.b && ((z && sp2Var.c.equals("keyboard")) || (!z && !sp2Var.c.equals("keyboard")))) {
                return sp2Var;
            }
        }
        return null;
    }
