    @Override // sofeh.music.MusicObject
    public void ReadFromStream(al0 al0Var, boolean z) throws IOException {
        super.ReadFromStream(al0Var, z);
        this.fType = al0Var.readByte();
        this.fKind = al0Var.readByte();
        this.fMIDIChOut = al0Var.c();
        this.fSystem = al0Var.b();
        InstrumentChange(al0Var.b());
        this.fMIDIInst = al0Var.readByte();
        this.fSolo = al0Var.readBoolean();
        this.fMute = al0Var.readBoolean();
        this.fRecord = al0Var.readBoolean();
        this.fMIDIChIn = al0Var.c();
        this.fMIDIBank0 = al0Var.readByte();
        this.fMIDIBank1 = al0Var.readByte();
        this.fChordRoot = al0Var.readByte();
        this.fChordType = al0Var.readByte();
        al0Var.readByte();
        al0Var.readByte();
        this.fColor = al0Var.b();
        for (int i = 0; i < 25; i++) {
            al0Var.readInt();
        }
        this.fSTSCount = al0Var.b();
        if (this.fVersion.compareToIgnoreCase("1.2.9") < 0) {
            this.fSTSCount = 13;
        }
        if (this.fGroup == 0 || this.fVersion.compareToIgnoreCase("1.2.8") < 0) {
            for (int i2 = 0; i2 < this.fSTSCount; i2++) {
                STS(i2).Enable = al0Var.readBoolean();
            }
            for (int i3 = 0; i3 < this.fSTSCount; i3++) {
                if (STS(i3).Enable) {
                    STS(i3).ReadFromStream(al0Var, this.fVersion);
                }
            }
        }
        for (int i4 = 0; i4 < 7; i4++) {
            al0Var.readByte();
        }
        this.fPedalMode = al0Var.b();
        this.fPedal = al0Var.b();
        this.fKeyAreaFromKey = al0Var.b();
        this.fKeyAreaToKey = al0Var.b();
        this.fChannels.ReadFromStream(al0Var, this.fVersion);
        int iB = al0Var.b();
        for (int i5 = 0; i5 < iB; i5++) {
            KeyData keyData = new KeyData();
            keyData.ReadFromStream(al0Var, this.fVersion, this.fMusic);
            if (this.fGroup == 0) {
                Tracks tracks = this.fMusic.fTracks;
                int i6 = tracks.fEndTime;
                int i7 = keyData.Time;
                int i8 = keyData.Length;
                if (i6 < (i7 + i8) - 1) {
                    tracks.fEndTime = (i7 + i8) - 1;
                }
            }
            this.fData.add(keyData);
        }
    }