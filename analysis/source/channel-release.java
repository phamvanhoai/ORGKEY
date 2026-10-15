    public void Close(int i, boolean z) {
        for (int i2 = 0; i2 < this.fListCount; i2++) {
            Channel channel = this.fList[i2];
            if (channel.idx > 0 && channel.ID == i) {
                if (z) {
                    channel.PedalMode = 0;
                }
                channel.Close = true;
            }
        }
    }
