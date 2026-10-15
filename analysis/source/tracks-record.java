    public void RecordMusic(short s, int i, int i2, int i3) {
        if (s > 210) {
            RecordTrack(Get(0), s, i, i2, i3);
            return;
        }
        if (s != 1) {
            if (s == 2 || s == 4) {
                this.fMusic.fKeySet[i2] = true;
            }
        } else if (i2 >= 0) {
            this.fMusic.fKeySet[i2] = false;
        }
        ArrayList<Track> arrayList = this.fTracksMain;
        int size = arrayList.size();
        int i4 = 0;
        while (i4 < size) {
            Track track = arrayList.get(i4);
            i4++;
            Track track2 = track;
            if (s == 1 || (track2.fRecord && ((s != 2 && s != 4) || (i2 >= track2.fKeyAreaFromKey && i2 <= track2.fKeyAreaToKey)))) {
                byte b = track2.fType;
                if (b == 0 || b == 1) {
                    RecordTrack(track2, s, i, i2, i3);
                } else if (b == 3) {
                    if (track2.fKind == -1) {
                        RecordTrack(track2, s, i, i2, i3);
                    }
                    if (s != 1) {
                        if (s == 2 || s == 4) {
                            this.fMusic.fChordKeySet[i2] = true;
                        }
                    } else if (i2 >= 0) {
                        this.fMusic.fChordKeySet[i2] = false;
                    }
                    int iChordGet = ChordGet();
                    if (iChordGet == 0 || ChordFind(iChordGet) < 0) {
                        if (!this.fMusic.fChordMemory) {
                            RecordChordStop();
                        }
                    } else if (s == 2 || s == 4) {
                        RecordChord(iChordGet);
                    }
                }
            }
        }
    }
