public void d(String str) {
        StringBuilder sb = new StringBuilder("// Name of Model");
        String str2 = b44.a;
        sb.append(str2);
        StringBuilder sbQ = iy1.q(iy1.n(iy1.n(ep4.o(iy1.q(sb.toString(), "Name: "), (String) this.e, str2, str2), "// Key: Count, Start, Chord (0 for no change)", str2), "// Start and Chord are Zero based", str2), "Key: ");
        sbQ.append(this.b);
        sbQ.append(", ");
        sbQ.append(this.c);
        sbQ.append(", ");
        sbQ.append(this.d);
        sbQ.append(str2);
        sbQ.append(str2);
        String strN = iy1.n(iy1.n(iy1.n(ep4.o(iy1.q(iy1.n(iy1.n(sbQ.toString(), "// Skin of this Model (null for default)", str2), "// only Android/iOS version", str2), "Skin: "), (String) this.f, str2, str2), "// Operation List", str2), "// Command: Index = Event: Channel, Value1 [, Value2]", str2), "//---------------------------------------------------", str2);
        ArrayList arrayList = (ArrayList) this.g;
        int size = arrayList.size();
        int i2 = 0;
        while (i2 < size) {
            Object obj = arrayList.get(i2);
            i2++;
            sp2 sp2Var = (sp2) obj;
            int i3 = sp2Var.a;
            int i4 = 0;
            while (true) {
                if (i4 >= 70) {
                    i4 = -1;
                    break;
                } else if (i3 == j[i4]) {
                    break;
                } else {
                    i4++;
                }
            }
            if (i4 >= 0) {
                StringBuilder sb2 = new StringBuilder(strN + i[i4] + ": " + sp2Var.b + " = " + sp2Var.c + ": " + sp2Var.d + ", " + sp2Var.e + ", " + sp2Var.f);
                sb2.append(b44.a);
                strN = sb2.toString();
            }
        }
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(str);
            fileOutputStream.write(strN.getBytes());
            fileOutputStream.close();
        } catch (Exception unused) {
        }
    }
