    public void GroupPlayJump(int i, TrackGroup trackGroup) {
        this.fGroupPlayList[i].Jump = trackGroup;
        if (trackGroup != null) {
            trackGroup.tempLoop = false;
        }
    }