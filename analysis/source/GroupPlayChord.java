    /* JADX WARN: Code duplicated, block: B:38:0x00a9 A[PHI: r9
  0x00a9: PHI (r9v6 int) = (r9v5 int), (r9v5 int), (r9v5 int), (r9v8 int), (r9v8 int) binds: [B:19:0x0060, B:23:0x0068, B:25:0x006e, B:53:0x00a9, B:36:0x00a6] A[DONT_GENERATE, DONT_INLINE]] */
    public void GroupPlayChord(int i, int i2, int i3) {
        int i4;
        int i5 = i2;
        GroupPlay[] groupPlayArr = this.fGroupPlayList;
        boolean z = true;
        int length = i >= groupPlayArr.length ? groupPlayArr.length - 1 : i;
        int i6 = length + 1;
        if (this.fGroupPlayCount < i6) {
            this.fGroupPlayCount = i6;
        }
        this.fCurrentChord = i5;
        this.fChordNo = ChordFind(i5);
        this.fChordOctaveMove = i3;
        TrackGroup trackGroup = this.fGroupPlayList[length].Current;
        if (trackGroup != null) {
            ArrayList<Track> arrayListTrackList = TrackList(trackGroup.Group);
            int size = arrayListTrackList.size();
            int i7 = 0;
            while (i7 < size) {
                Track track = arrayListTrackList.get(i7);
                i7++;
                Track track2 = track;
                if (this.fMusic.fInstruments.Get(track2.fInstrument).fType == 0) {
                    int i8 = 0;
                    while (true) {
                        Channels channels = track2.fChannels;
                        if (i8 < channels.fListCount) {
                            Channel channel = channels.fList[i8];
                            if (channel.idx > 0) {
                                if (i5 == 0) {
                                    channel.Close = z;
                                }
                                if (!channel.Close && channel.Length >= 1000) {
                                    int i9 = 0;
                                    while (true) {
                                        if (i9 < track2.fDataPos) {
                                            KeyData keyData = track2.fData.get(i9);
                                            if (keyData.Event == 2 && track2.fChannels.fList[i8].ID == keyData.ID) {
                                                int iCalcChordKey = track2.CalcChordKey(keyData.Key, this.fChordNo);
                                                Channels channels2 = track2.fChannels;
                                                Channel channel2 = channels2.fList[i8];
                                                if (channel2.Key != iCalcChordKey || this.fMusic.fFXMusic.f != 0) {
                                                    i4 = i8;
                                                    channels2.OpenKey(keyData.ID, iCalcChordKey, keyData.Touch, false, channel2.Length, track2.fInstrument, track2.fSystem, track2.KeyTouch, track2.KeyBalance, 0, 0, 0, track2.KeyPitchMove, track2.fPedalMode, track2.fPedal, true, i4, 0);
                                                    break;
                                                }
                                            } else {
                                                i9++;
                                                i8 = i8;
                                            }
                                        }
                                        i4 = i8;
                                        break;
                                        break;
                                    }
                                }
                                i4 = i8;
                                break;
                                break;
                            }
                            i4 = i8;
                            break;
                            i8 = i4 + 1;
                            i5 = i2;
                            z = true;
                        }
                    }
                }
                i5 = i2;
                z = true;
            }
            GroupPlay groupPlay = this.fGroupPlayList[length];
            TrackGroup trackGroup2 = groupPlay.Current;
            byte b = trackGroup2.Section;
            if (b > 0) {
                groupPlay.Current = GroupSection(trackGroup2, trackGroup2.Parent, b);
            }
        }
    }