    public final synchronized void j(al0 al0Var) {
        n01 o01Var;
        try {
            k();
            this.b = al0Var.b();
            this.c = al0Var.b();
            this.d = al0Var.b();
            this.e = al0Var.b();
            this.f = al0Var.b();
            this.g = al0Var.b();
            int iB = al0Var.b();
            this.h = iB;
            if (iB == 0) {
                this.h = 100;
            }
            this.i = al0Var.readBoolean();
            for (int i = 0; i < 18; i++) {
                al0Var.readByte();
            }
            this.j = al0Var.readBoolean();
            int iB2 = al0Var.b();
            this.k.clear();
            for (int i2 = 0; i2 < iB2; i2++) {
                int iB3 = al0Var.b();
                if (iB3 == 1) {
                    o01Var = new o01();
                } else if (iB3 == 2) {
                    o01Var = new c();
                } else if (iB3 == 4) {
                    o01Var = new t01();
                } else if (iB3 == 23) {
                    o01Var = new r01();
                } else if (iB3 == 29) {
                    o01Var = new w01();
                } else if (iB3 == 31) {
                    o01Var = new b();
                } else if (iB3 == 20) {
                    o01Var = new p01();
                } else if (iB3 != 21) {
                    switch (iB3) {
                        case 6:
                            o01Var = new s01();
                            break;
                        case 7:
                            o01Var = new d();
                            break;
                        case 8:
                            o01Var = new e();
                            break;
                        case 9:
                            o01Var = new u01((byte) 0);
                            break;
                        case 10:
                            o01Var = new u01((byte) 1);
                            break;
                        default:
                            switch (iB3) {
                                case 15:
                                    o01Var = new f();
                                    break;
                                case 16:
                                    o01Var = new x01();
                                    break;
                                case 17:
                                    o01Var = new y01();
                                    break;
                                default:
                                    o01Var = null;
                                    break;
                            }
                            break;
                    }
                } else {
                    o01Var = new q01();
                }
                if (o01Var != null) {
                    o01Var.g(al0Var);
                    a(o01Var);
                }
            }
            c();
        } catch (Throwable th) {
            throw th;
        }
    }