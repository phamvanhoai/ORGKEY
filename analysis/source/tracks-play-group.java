    /* JADX WARN: Code duplicated, block: B:59:0x00da  */
    private void _PlayGroup(TrackGroup trackGroup, int i, int i2, int i3, boolean z) {
        TrackGroup trackGroup2;
        int i4;
        int i5;
        this._result = true;
        this._tracks = TrackList(trackGroup.Group);
        Arrays.fill(this._inst, false);
        int i6 = this.fMusic.fChordQuality;
        if (i6 == 0) {
            boolean[] zArr = this._inst;
            zArr[7] = true;
            zArr[8] = true;
            zArr[9] = true;
        } else if (i6 == 1) {
            this._inst[8] = true;
        }
        ArrayList<Track> arrayList = this._tracks;
        int size = arrayList.size();
        int i7 = 0;
        while (i7 < size) {
            Track track = arrayList.get(i7);
            i7++;
            Track track2 = track;
            byte b = this.fMusic.fInstruments.Get(track2.fInstrument).fType;
            this._instType = b;
            byte b2 = trackGroup.Type;
            if (b2 == 0 || trackGroup.Section == 0 || z || i3 != 0 || b != 0) {
                boolean z2 = this._solo;
                if (!(z2 && track2.fSolo) && ((z2 || track2.fMute) && (z || track2.fMute || b2 != 1))) {
                    track2.fChannels.CloseAll(true);
                } else if (i2 == -1 || this.fMusic.fChordQuality == 2 || b != 0 || (i5 = track2.fInstrument) > 10 || !this._inst[i5]) {
                    if ((!this.fChordMute || b != 0) && ((!this.fDrumMute || b == 0) && track2.PlayNext(i, trackGroup, i3, this.fChordNo))) {
                        this._result = false;
                    }
                    if (this._instType == 0 && (i4 = track2.fInstrument) <= 10) {
                        this._inst[i4] = true;
                    }
                }
            }
        }
        if (this._result) {
            if (this._result) {
                GroupPlayStart(trackGroup, i);
            } else {
                GroupPlayStart(trackGroup, i);
            }
            if (trackGroup.Type == 1) {
                GroupPlayNext(i2, i);
            }
        } else {
            Music music = this.fMusic;
            if (i % music.rhythmScale <= (music.fTempo * 10) / Music.DefTempo && i2 != -1 && !this.fGroupPlayList[i2].List.isEmpty()) {
                if ((this._result || (this.fGroupPlayList[i2].List.get(0) != this.fTrackGroupChordMute && this.fGroupPlayList[i2].List.get(0) != this.fTrackGroupDrumMute)) && (trackGroup.Loop || trackGroup.tempLoop)) {
                    GroupPlayStart(trackGroup, i);
                }
                if (trackGroup.Type == 1 && i2 != -1) {
                    GroupPlayNext(i2, i);
                }
            }
        }
        Music music2 = this.fMusic;
        if (!music2.GroupFXTest || i2 < 0 || (trackGroup2 = this.fGroupPlayList[i2].Current) == null) {
            return;
        }
        try {
            music2.fFXRhythm.e(GroupFXs(trackGroup2), false, false, false, false, false, false, true, false);
        } catch (Exception unused) {
        }
    }