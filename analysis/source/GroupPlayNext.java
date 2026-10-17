    public void GroupPlayNext(int i, int i2) {
        short s;
        byte b;
        GroupPlay[] groupPlayArr = this.fGroupPlayList;
        if (i >= groupPlayArr.length) {
            i = groupPlayArr.length - 1;
        }
        int i3 = i + 1;
        if (this.fGroupPlayCount < i3) {
            this.fGroupPlayCount = i3;
        }
        Music music = this.fMusic;
        if (i2 % music.rhythmScale <= (music.fTempo * 10) / Music.DefTempo) {
            GroupPlay groupPlay = groupPlayArr[i];
            TrackGroup trackGroup = groupPlay.Current;
            TrackGroup trackGroup2 = null;
            if (groupPlay.List.isEmpty()) {
                TrackGroup trackGroup3 = groupPlay.Current;
                if (trackGroup3 != null && !trackGroup3.Loop && !trackGroup3.tempLoop) {
                    TrackGroup trackGroup4 = groupPlay.Jump;
                    if (trackGroup4 != null) {
                        groupPlay.Current = trackGroup4;
                        groupPlay.Jump = null;
                    } else {
                        int i4 = trackGroup3.JumpGroup;
                        if (i4 < 0) {
                            groupPlay.Current = groupPlay.LastLoop;
                        } else if (i4 > 0) {
                            groupPlay.Current = Group(i4);
                        } else {
                            groupPlay.Current = null;
                        }
                    }
                }
            } else {
                if (groupPlay.List.get(0) == this.fTrackGroupChordMute || groupPlay.List.get(0) == this.fTrackGroupDrumMute) {
                    if (groupPlay.List.get(0) == this.fTrackGroupChordMute) {
                        this.fChordMute = !this.fChordMute;
                    }
                    if (groupPlay.List.get(0) == this.fTrackGroupDrumMute) {
                        this.fDrumMute = !this.fDrumMute;
                    }
                    groupPlay.List.remove(0);
                    return;
                }
                groupPlay.Current = groupPlay.List.get(0);
                groupPlay.List.remove(0);
            }
            TrackGroup trackGroupGroupSection = groupPlay.Current;
            if (trackGroupGroupSection != null && (b = trackGroupGroupSection.Section) > 0) {
                trackGroupGroupSection = GroupSection(trackGroupGroupSection, trackGroupGroupSection.Parent, b);
                groupPlay.Current = trackGroupGroupSection;
            }
            if (trackGroupGroupSection == null || trackGroupGroupSection.Group >= 0) {
                trackGroup2 = trackGroupGroupSection;
            } else {
                groupPlay.Current = null;
            }
            if (trackGroup2 != null && trackGroup2.Loop) {
                groupPlay.LastLoop = trackGroup2;
            }
            if (trackGroup2 != null) {
                int iCalcRhythmScale = CalcRhythmScale(trackGroup2);
                if (iCalcRhythmScale != -1 && iCalcRhythmScale <= 10) {
                    this.fMusic.rhythmScale = iCalcRhythmScale;
                }
                GroupPlayStart(groupPlay.Current, i2);
                if (iCalcRhythmScale != -1) {
                    this.fMusic.rhythmScale = iCalcRhythmScale;
                }
            }
            if (trackGroup != null && trackGroup != groupPlay.Current && TrackList(trackGroup.Group) != null) {
                ArrayList<Track> arrayListTrackList = TrackList(trackGroup.Group);
                int size = arrayListTrackList.size();
                int i5 = 0;
                while (i5 < size) {
                    Track track = arrayListTrackList.get(i5);
                    i5++;
                    track.fChannels.CloseAll(false);
                }
            }
        }
        TrackGroup trackGroup5 = this.fGroupPlayList[i].Current;
        if (trackGroup5 != null) {
            Music music2 = this.fMusic;
            if (!music2.fTempoLock && (s = trackGroup5.Tempo) > 0 && (trackGroup5.Section >> 4) != 6) {
                music2.fTempo = s;
            }
            ArrayList<Track> arrayListTrackList2 = TrackList(trackGroup5.Group);
            int size2 = arrayListTrackList2.size();
            int i6 = 0;
            while (i6 < size2) {
                Track track2 = arrayListTrackList2.get(i6);
                i6++;
                Track track3 = track2;
                if (track3.fKind == 1 && this.fMusic.fInstruments.Get(track3.fInstrument).fType == 0) {
                    this.fMusic.fInstruments.Get(track3.fInstrument).SetType((byte) 2);
                }
                if (track3.fKind > 1 && this.fMusic.fInstruments.Get(track3.fInstrument).fType == 2) {
                    this.fMusic.fInstruments.Get(track3.fInstrument).SetType((byte) 0);
                }
            }
            this.fMusic.fFXRhythm.e(GroupFXs(this.fGroupPlayList[i].Current), false, false, false, false, false, false, true, false);
        }
    }