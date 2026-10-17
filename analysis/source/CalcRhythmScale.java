    public int CalcRhythmScale(TrackGroup trackGroup) {
        Music music = this.fMusic;
        int i = music.fGroupPlayChangeMethod;
        if (trackGroup != null && trackGroup.Parent == Integer.MIN_VALUE) {
            i = 0;
        }
        int i2 = 1000;
        if (trackGroup != null) {
            int i3 = trackGroup.MeterX;
            int i4 = trackGroup.MeterY;
            if (i3 == 0 || i4 == 0) {
                i3 = music.fMetronomeX;
                i4 = music.fMetronomeY;
            }
            int i5 = (i3 * 2000) / i4;
            if (trackGroup.Limit) {
                int i6 = trackGroup.LimitEnd;
                int i7 = trackGroup.LimitStart;
                if (i6 - i7 != 0 && (i6 - i7) % i5 == 0) {
                    i2 = i5;
                }
            }
        }
        if (i != 0) {
            if (i == 1) {
                while (i2 > 500) {
                    i2 /= 2;
                }
                return i2;
            }
            if (i != 2) {
                if (i != 3) {
                    return -1;
                }
                if (trackGroup != null && trackGroup.Limit) {
                    int i8 = trackGroup.LimitEnd;
                    int i9 = trackGroup.LimitStart;
                    if (i8 - i9 > 0) {
                        return i8 - i9;
                    }
                }
            }
        } else if (trackGroup != null) {
            if ((trackGroup.Section >> 4) == 6) {
                return -1;
            }
            while (i2 >= 2000) {
                i2 /= 2;
            }
            if (!trackGroup.Limit || i2 <= 10 || (trackGroup.LimitEnd - trackGroup.LimitStart) % i2 == 0) {
                return i2;
            }
            return 10;
        }
        return i2;
    }