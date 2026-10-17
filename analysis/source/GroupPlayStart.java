    public void GroupPlayStart(TrackGroup trackGroup, int i) {
        Music music = this.fMusic;
        int i2 = music.rhythmScale;
        int i3 = (i / i2) * i2;
        int i4 = music.fGroupPlayChangeMethod;
        int i5 = 0;
        if (i4 > 0 && i4 < 3 && trackGroup.Parent != Integer.MIN_VALUE) {
            byte b = trackGroup.Section;
            if ((b & 240) == 32 || (b & 240) == 48 || (b & 240) == 64 || (b & 240) == 96 || i4 == 1) {
                int i6 = trackGroup.Limit ? trackGroup.LimitEnd - trackGroup.LimitStart : 0;
                if (i6 > 0) {
                    i2 = i6;
                }
                i3 = (i / i2) * i2;
            }
        }
        if (trackGroup.Limit) {
            i3 -= trackGroup.LimitStart;
        }
        ArrayList<Track> arrayListTrackList = TrackList(trackGroup.Group);
        int size = arrayListTrackList.size();
        while (i5 < size) {
            Track track = arrayListTrackList.get(i5);
            i5++;
            track.Start(i3);
        }
    }