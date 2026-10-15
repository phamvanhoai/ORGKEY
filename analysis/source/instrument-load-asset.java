    public boolean InstrumentLoadFromAsset(int i, InputStream inputStream, boolean z) {
        try {
            return this.fInstruments.Get(i).LoadFromAsset(inputStream, z);
        } catch (Exception unused) {
            return false;
        }
    }
