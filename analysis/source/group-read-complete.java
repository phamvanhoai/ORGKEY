    public void ReadFromStream(al0 al0Var, String str) throws IOException {
        int i;
        this.Group = al0Var.b();
        this.Parent = al0Var.b();
        this.Name = al0Var.a();
        this.Section = al0Var.readByte();
        this.Type = al0Var.readByte();
        this.Loop = al0Var.readBoolean();
        this.Limit = al0Var.readBoolean();
        this.LimitStart = al0Var.b();
        this.LimitEnd = al0Var.b();
        this.JumpGroup = al0Var.b();
        this.Color = al0Var.b();
        this.ChordType = al0Var.readByte();
        this.ChordBase = al0Var.readByte();
        this.Tempo = al0Var.c();
        if (str.compareToIgnoreCase("1.3.0") < 0) {
            this.Tempo = (short) ((this.Tempo * 120) / 100);
        }
        this.ShortCutKeyDevice = al0Var.b();
        this.ShortCutKeyMIDI = al0Var.readByte();
        this.Reserved = al0Var.readByte();
        this.MeterX = al0Var.readByte();
        this.MeterY = al0Var.readByte();
        if (str.compareToIgnoreCase("1.4.8") < 0) {
            byte b = this.MeterY;
            this.MeterX = (byte) (b >> 4);
            this.MeterY = (byte) (b & 15);
        }
        if (str.equalsIgnoreCase("1.4.8")) {
            this.ChordVars = 0L;
        }
        if (str.compareToIgnoreCase("1.4.9") >= 0) {
            this.ChordVars = Long.reverseBytes(al0Var.readLong());
        }
        if (str.compareToIgnoreCase("1.4.6") >= 0) {
            for (int i2 = 0; i2 < 4; i2++) {
                this.STSIndex[i2] = al0Var.b();
            }
        }
        if (str.compareToIgnoreCase("1.4.2") >= 0) {
            this.fxs.j(al0Var);
        }
        if (this.Limit && (i = this.LimitStart) >= this.LimitEnd) {
            this.LimitEnd = i + 1000;
        }
        if (this.Section == 0) {
            switch (this.ShortCutKeyDevice) {
                case 113:
                    this.Section = (byte) 16;
                    this.Color = 255;
                    break;
                case ASSET_WRITE_ERROR_VALUE:
                    this.Section = (byte) 17;
                    this.Color = 255;
                    break;
                case INVALID_INDEX_URL_VALUE:
                    this.Section = (byte) 32;
                    this.Color = 16711680;
                    break;
                case GZIP_ENCODE_ERROR_VALUE:
                    this.Section = (byte) 33;
                    this.Color = 16711680;
                    break;
                case ASSET_FAILED_STATUS_CODE_VALUE:
                    this.Section = (byte) 34;
                    this.Color = 16711680;
                    break;
                case PROTOBUF_SERIALIZATION_ERROR_VALUE:
                    this.Section = (byte) 35;
                    this.Color = 16711680;
                    break;
                case JSON_ENCODE_ERROR_VALUE:
                    this.Section = (byte) 48;
                    this.Color = 32768;
                    break;
                case Music.DefTempo /* 120 */:
                    this.Section = (byte) 49;
                    this.Color = 32768;
                    break;
                case TPAT_ERROR_VALUE:
                    this.Section = (byte) 80;
                    this.Color = 32896;
                    break;
                case INVALID_ADS_ENDPOINT_VALUE:
                    this.Section = (byte) 81;
                    this.Color = 32896;
                    break;
                case INVALID_RI_ENDPOINT_VALUE:
                    this.Section = (byte) 64;
                    this.Color = 8421376;
                    break;
            }
        }
    }