    public boolean LoadFromAsset(String str, InputStream inputStream, boolean z) throws IOException {
        if (!str.isEmpty()) {
            this.fFileName = str;
        }
        boolean z2 = this.fOutActive;
        if (z2) {
            OutDeviceStop();
        }
        boolean zLoadFromAsset = super.LoadFromAsset(inputStream, z);
        if (z2) {
            OutDeviceStart();
        }
        this.fModified = false;
        return zLoadFromAsset;
    }
