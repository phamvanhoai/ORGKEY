public boolean ScriptEvent(String str, String[] strArr, int[] iArr) {
        ArrayList<Track> arrayList = this.fTracks.fTracksMain;
        int size = arrayList.size();
        boolean z = false;
        int i = 0;
        while (i < size) {
            Track track = arrayList.get(i);
            i++;
            Track track2 = track;
            if (track2.fRecord && track2.ScriptEvent(str, strArr, iArr)) {
                z = true;
            }
        }
        return z;
    }
