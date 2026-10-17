    public void GroupPlayAdd(int i, TrackGroup trackGroup) {
        GroupPlay[] groupPlayArr = this.fGroupPlayList;
        if (i >= groupPlayArr.length) {
            i = groupPlayArr.length - 1;
        }
        if (trackGroup != null && trackGroup.Section == 0) {
            i = 0;
            while (true) {
                GroupPlay groupPlay = this.fGroupPlayList[i];
                if ((groupPlay.Current == null && groupPlay.List.size() <= 0) || i >= this.fGroupPlayList.length - 1) {
                    break;
                } else {
                    i++;
                }
            }
        }
        int i2 = i + 1;
        if (this.fGroupPlayCount < i2) {
            this.fGroupPlayCount = i2;
        }
        int size = this.fGroupPlayList[i].List.size();
        GroupPlay[] groupPlayArr2 = this.fGroupPlayList;
        TrackGroup trackGroup2 = size == 0 ? groupPlayArr2[i].Current : groupPlayArr2[i].List.get(size - 1);
        if ((trackGroup2 == null || trackGroup2 != trackGroup || !trackGroup2.Loop) && size <= 8) {
            this.fGroupPlayList[i].List.add(trackGroup);
        }
        if (trackGroup != null) {
            trackGroup.tempLoop = false;
        }
    }