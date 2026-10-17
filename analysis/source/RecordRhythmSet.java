    public boolean RecordRhythmSet(int i, int i2, int i3) {
        Music music = this.fMusic;
        if (music.fPlayMode != 0) {
            music.Stop(0);
        }
        TrackGroup trackGroupRhythmSection = RhythmSection(i, i2);
        Track track = null;
        boolean z = true;
        for (int i4 = 0; i4 < Music.RhythmTrackName.length; i4++) {
            Track trackRhythmTrack = RhythmTrack(i, i2, i4);
            if (trackRhythmTrack != null) {
                if (i3 == i4) {
                    track = trackRhythmTrack;
                } else if (this.RecordRhythmMode < 2 || !this.fMusic.LiveStyleMix) {
                    trackRhythmTrack.fRecord = false;
                }
                if (!trackRhythmTrack.IsEmpty()) {
                    z = false;
                }
            }
        }
        if (track != null) {
            int i5 = this.RecordRhythmMode;
            if (i5 == 0 || i5 == 1) {
                if (GroupPlayIsEmpty()) {
                    this.RecordRhythmMode = 2;
                } else {
                    this.RecordRhythmMode = 3;
                }
                track.fRecord = true;
            } else if (i5 == 2) {
                if (track.fRecord && !this.fMusic.LiveStyleFree) {
                    this.RecordRhythmMode = 3;
                    this.RhythmLastTimeNow = this.fTimeNow2;
                }
                track.fRecord = true;
            } else if (i5 == 3) {
                if (z) {
                    this.RecordRhythmMode = 1;
                } else {
                    this.RecordRhythmMode = 4;
                    trackGroupRhythmSection.LimitEnd = Math.max(0, this.fTimeNow2 - this.RhythmLastTimeNow);
                    if (GroupPlayIsEmpty()) {
                        RecordRhythm(TrackEvent.Rhythm, trackGroupRhythmSection.Group, 0);
                    }
                }
                track.fRecord = true;
            } else if (i5 == 4) {
                if (z) {
                    this.RecordRhythmMode = 2;
                    track.fRecord = true;
                    this.RhythmLastTimeNow = this.fTimeNow2;
                } else {
                    boolean z2 = track.fRecord;
                    track.fRecord = !z2;
                    if (!z2 && GroupPlayIsEmpty()) {
                        RecordRhythm(TrackEvent.Rhythm, trackGroupRhythmSection.Group, 0);
                    }
                }
            }
            trackGroupRhythmSection.ChordBase = (byte) 0;
            this.fCurrentChord = 0;
            this.fChordNo = -1;
        }
        return !z;
    }