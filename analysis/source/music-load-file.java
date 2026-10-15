    @Override // sofeh.music.MusicObject
    public boolean LoadFromFile(String str, boolean z) throws IOException {
        if (str.isEmpty()) {
            str = this.fFileName;
        } else {
            this.fFileName = str;
        }
        if (!new File(str).exists()) {
            return false;
        }
        boolean z2 = this.fOutActive;
        if (z2) {
            OutDeviceStop();
        }
        boolean zLoadFromFile = super.LoadFromFile(str, z);
        if (this.fInstruments.Count(-1) == 0 || this.fSystems.Count(-1) == 0 || this.fTracks.Count(-1) == 0) {
            zLoadFromFile = false;
        }
        if (z2) {
            OutDeviceStart();
        }
        this.fModified = false;
        return zLoadFromFile;
    }
