public sp2 b(String str, int i2, int i3, int i4) {
        int i5;
        ArrayList arrayList = (ArrayList) this.g;
        int size = arrayList.size();
        int i6 = 0;
        while (i6 < size) {
            Object obj = arrayList.get(i6);
            i6++;
            sp2 sp2Var = (sp2) obj;
            if (sp2Var.c.equals(str.toLowerCase(Locale.UK)) && ((i5 = sp2Var.d) == -1 || i5 == i2)) {
                int i7 = sp2Var.e;
                if (i7 == -1 || i7 == i3) {
                    int i8 = sp2Var.f;
                    if (i8 == -1 || i8 == i4) {
                        return sp2Var;
                    }
                }
            }
        }
        return null;
    }
