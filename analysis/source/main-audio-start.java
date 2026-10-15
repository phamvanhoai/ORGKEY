    public final void r() {
        b44.g(50);
        Music music = this.o;
        byte b = 1;
        if (music != null && this.q4) {
            music.OutDeviceStart();
            Music music2 = this.o;
            if (music2.fOutActive && music2.fIsOpenSL && W0() && !this.G5) {
                this.G5 = true;
            }
        }
        runOnUiThread(new t82(this, b));
    }
