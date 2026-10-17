                    public final void y1(int i, boolean z) {
                        TrackGroup trackGroupGroupSection;
                        TrackGroup trackGroupGroupSection2;
                        int i2 = this.e2;
                        if (this.o.RecordRhythmMode() > 0) {
                            if (this.o.RecordRhythmMode() == 3) {
                                for (int i3 = 0; i3 < Music.RhythmTrackName.length; i3++) {
                                    Track trackRhythmTrack = this.o.RhythmTrack(Integer.MIN_VALUE, this.B4, i3);
                                    if (trackRhythmTrack != null && trackRhythmTrack.fRecord) {
                                        this.o.RecordRhythmSet(Integer.MIN_VALUE, this.B4, i3);
                                    }
                                }
                                this.B4 = i;
                                return;
                            }
                            TrackGroup trackGroupRhythmSection = this.o.RhythmSection(Integer.MIN_VALUE, i);
                            if (this.o.RecordRhythmMode() > 3 && trackGroupRhythmSection != null && trackGroupRhythmSection.LimitEnd == 0) {
                                this.g2 = i;
                                this.B4 = i;
                                return;
                            } else {
                                this.o.GroupPlayTimeReset();
                                this.B4 = i;
                                i2 = Integer.MIN_VALUE;
                            }
                        }
                        if (i2 != -1) {
                            if (x1(i2, i) == 0) {
                                i = 32;
                            }
                            TrackGroup trackGroupGroupSection3 = this.o.GroupSection(i2, i);
                            if (trackGroupGroupSection3 == null) {
                                trackGroupGroupSection3 = this.o.GroupSection(0, i);
                            }
                            if (trackGroupGroupSection3 == null) {
                                return;
                            }
                            if (this.q1 && this.j2 != 0 && this.o.GroupPlayIsLastGroup(0, trackGroupGroupSection3)) {
                                this.o.RecordRhythm(TrackEvent.RhythmTempLoop, trackGroupGroupSection3.Group, -1);
                                return;
                            }
                            int i4 = i >> 4;
                            if (i4 == 6) {
                                this.o.RecordRhythm(TrackEvent.Rhythm, trackGroupGroupSection3.Group, (i & 15) + 1);
                            } else {
                                int i5 = this.j2;
                                if (i5 != 0) {
                                    this.g2 = i;
                                }
                                if (i5 != 0 && this.i2 && z && i4 == 2) {
                                    int i6 = (i & 15) + 48;
                                    trackGroupGroupSection = this.o.GroupSection(i2, i6);
                                    if (trackGroupGroupSection == null) {
                                        trackGroupGroupSection = this.o.GroupSection(0, i6);
                                    }
                                    if (trackGroupGroupSection != null) {
                                        this.o.RecordRhythm(TrackEvent.Rhythm, trackGroupGroupSection.Group, 0);
                                        this.o.RecordRhythm(TrackEvent.RhythmJump, trackGroupGroupSection3.Group, 0);
                                    }
                                } else {
                                    trackGroupGroupSection = null;
                                }
                                if (trackGroupGroupSection == null) {
                                    this.o.RecordRhythm(TrackEvent.Rhythm, trackGroupGroupSection3.Group, 0);
                                }
                            }
                            int i7 = this.g2;
                            if (i7 == 0 || i7 == i || (trackGroupGroupSection2 = this.o.GroupSection(i2, i7)) == null) {
                                return;
                            }
                            this.o.RecordRhythm(TrackEvent.RhythmJump, trackGroupGroupSection2.Group, 0);
                        }
                    }