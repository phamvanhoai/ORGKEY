    @Override // defpackage.y42
    public void g(Object obj) {
        Music music = (Music) this.c;
        int i = this.a;
        int i2 = this.b;
        b60 b60Var = ((c23) obj).c;
        b60Var.g = music;
        b60Var.d = null;
        try {
            if (Build.VERSION.SDK_INT >= 26) {
                try {
                    AudioTrack audioTrack = new AudioTrack(new AudioAttributes.Builder().setUsage(1).setContentType(2).build(), new AudioFormat.Builder().setSampleRate(i).setEncoding(2).setChannelMask(12).build(), i2, 1, 0);
                    i2 = i2;
                    b60Var.d = audioTrack;
                } catch (Exception unused) {
                    i2 = i2;
                    b60Var.d = null;
                }
            }
        } catch (Exception unused2) {
        }
        if (((AudioTrack) b60Var.d) == null) {
            try {
                if (Build.VERSION.SDK_INT >= 26) {
                    b60Var.d = new AudioTrack.Builder().setAudioAttributes(new AudioAttributes.Builder().setUsage(1).setContentType(2).build()).setAudioFormat(new AudioFormat.Builder().setEncoding(2).setSampleRate(i).setChannelMask(12).build()).setBufferSizeInBytes(i2).build();
                }
            } catch (Exception unused3) {
                b60Var.d = null;
            }
        }
        if (((AudioTrack) b60Var.d) == null) {
            try {
                b60Var.d = new AudioTrack(3, i, 12, 2, i2, 1);
            } catch (Exception unused4) {
                b60Var.d = null;
            }
        }
        try {
            AudioTrack audioTrack2 = (AudioTrack) b60Var.d;
            if (audioTrack2 != null) {
                audioTrack2.play();
            }
        } catch (Exception unused5) {
        }
    }
