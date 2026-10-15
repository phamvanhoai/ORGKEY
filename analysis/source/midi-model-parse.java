public void c(String str) {
        int i2;
        int i3;
        int i4;
        int i5;
        int i6;
        int i7;
        ArrayList arrayList = (ArrayList) this.g;
        arrayList.clear();
        for (String str2 : str.split("\\r?\\n")) {
            String strTrim = str2.trim();
            if (!strTrim.isEmpty() && strTrim.charAt(0) != '/') {
                String[] strArrSplit = strTrim.split(":|=|,|/");
                String lowerCase = strArrSplit[0].trim().toLowerCase(Locale.UK);
                if (lowerCase.equals("name")) {
                    if (strArrSplit.length > 1) {
                        this.e = strArrSplit[1].trim();
                    }
                } else if (lowerCase.equals("key")) {
                    if (strArrSplit.length > 1) {
                        String strTrim2 = strArrSplit[1].trim();
                        String str3 = b44.a;
                        try {
                            i7 = Integer.parseInt(strTrim2);
                        } catch (NumberFormatException unused) {
                            i7 = 61;
                        }
                        this.b = i7;
                    }
                    if (strArrSplit.length > 2) {
                        String strTrim3 = strArrSplit[2].trim();
                        String str4 = b44.a;
                        try {
                            i6 = Integer.parseInt(strTrim3);
                        } catch (NumberFormatException unused2) {
                            i6 = 36;
                        }
                        this.c = i6;
                    }
                    if (strArrSplit.length > 3) {
                        String strTrim4 = strArrSplit[3].trim();
                        String str5 = b44.a;
                        try {
                            i5 = Integer.parseInt(strTrim4);
                        } catch (NumberFormatException unused3) {
                            i5 = 17;
                        }
                        this.d = i5;
                    }
                } else if (!lowerCase.equals("skin")) {
                    for (int i8 = 0; i8 < 70; i8++) {
                        if (lowerCase.equals(i[i8].toLowerCase(Locale.UK))) {
                            sp2 sp2Var = new sp2();
                            sp2Var.a = j[i8];
                            if (strArrSplit.length > 1) {
                                String strTrim5 = strArrSplit[1].trim();
                                String str6 = b44.a;
                                try {
                                    i4 = Integer.parseInt(strTrim5);
                                } catch (NumberFormatException unused4) {
                                    i4 = 0;
                                }
                                sp2Var.b = i4;
                            }
                            if (strArrSplit.length > 2) {
                                sp2Var.c = strArrSplit[2].trim().toLowerCase(Locale.UK);
                            }
                            int i9 = -1;
                            if (strArrSplit.length > 2) {
                                String strTrim6 = strArrSplit[3].trim();
                                String str7 = b44.a;
                                try {
                                    i3 = Integer.parseInt(strTrim6);
                                } catch (NumberFormatException unused5) {
                                    i3 = -1;
                                }
                                sp2Var.d = i3;
                            }
                            if (strArrSplit.length > 4) {
                                String strTrim7 = strArrSplit[4].trim();
                                String str8 = b44.a;
                                try {
                                    i2 = Integer.parseInt(strTrim7);
                                } catch (NumberFormatException unused6) {
                                    i2 = -1;
                                }
                                sp2Var.e = i2;
                            }
                            if (strArrSplit.length > 5) {
                                String strTrim8 = strArrSplit[5].trim();
                                String str9 = b44.a;
                                try {
                                    i9 = Integer.parseInt(strTrim8);
                                } catch (NumberFormatException unused7) {
                                }
                                sp2Var.f = i9;
                            }
                            arrayList.add(sp2Var);
                            break;
                        }
                    }
                } else if (strArrSplit.length > 1) {
                    this.f = strArrSplit[1].trim();
                }
            }
        }
    }
