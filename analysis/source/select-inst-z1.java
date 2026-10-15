                    public final void z1(int i) {
                        int i2 = this.e2;
                        if (i2 != -1) {
                            String strGroupName = this.o.GroupName(i2);
                            int i3 = i >> 4;
                            String strZ = Music.RhythmSection[i3];
                            if (i3 != 4) {
                                StringBuilder sbY = iy1.y(strZ, " ");
                                sbY.append((i & 15) + 1);
                                strZ = sbY.toString();
                            }
                            if (x1(this.e2, i) == 2) {
                                strZ = ep4.z("Default ", strZ);
                            }
                            if (i <= 0) {
                                K1("Style", strGroupName, ep4.z("does not have ", strZ), false);
                                return;
                            }
                            K1("Style", strGroupName, strZ, false);
                            if (this.Y1 && i3 == 2) {
                                int i4 = (i & 15) + 1;
                                this.y4 = i4;
                                this.x4 = i4;
                                if (i4 <= 4 && this.o.GroupOf(this.e2).Group != 0 && this.o.GroupOf(this.e2).STSIndex[this.y4 - 1] > 0) {
                                    this.x4 = this.o.GroupOf(this.e2).STSIndex[this.y4 - 1];
                                }
                                this.o.STSPut(this.x4);
                                H1(false);
                                Q0();
                                Y();
                                a0();
                            }
                            if (i3 == 6) {
                                int i5 = (i & 15) + 1;
                                if ((this.o.GroupPlayState() & (1 << i5)) == 0) {
                                    y1(i, true);
                                    return;
                                } else {
                                    D1(i5);
                                    return;
                                }
                            }
                            if (this.j2 != 0) {
                                y1(i, true);
                                return;
                            }
                            if (x1(this.e2, i) != 0) {
                                int i6 = this.f2;
                                if ((i6 >> 4) == 2 && i3 == 1) {
                                    this.g2 = i6;
                                } else {
                                    this.g2 = 0;
                                }
                                this.f2 = i;
                            }
                        }
                    }
