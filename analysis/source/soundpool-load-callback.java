    @Override // android.media.SoundPool.OnLoadCompleteListener
    public final void onLoadComplete(SoundPool soundPool, int i, int i2) {
        b60 b60Var = this.a.c;
        int iIndexOf = ((ArrayList) b60Var.b).indexOf(Integer.valueOf(i));
        if (iIndexOf >= 0) {
            if (!((String) ((ArrayList) b60Var.c).get(iIndexOf)).isEmpty()) {
                bp3.e(((String) ((ArrayList) b60Var.c).get(iIndexOf)) + ".wav");
            }
            ((ArrayList) b60Var.c).set(iIndexOf, ".");
        }
    }
