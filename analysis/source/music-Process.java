    public void Process() {
        this._size = this.fOutBufferSize * 2;
        if (this.fOutActive) {
            try {
                ProcessOut(false);
                if (this.fInActive && this.fIsOpenSLRecord) {
                    ProcessIn(this.fInBufferSize);
                    NOpenSL.MixPut(this.fOutBuffer, this.fInBuffer, this._size, this.volumeLeft, this.volumeRight);
                } else {
                    NOpenSL.Put(this.fOutBuffer, this._size);
                }
                a aVar = this.fDirectRecordWriter;
                if (aVar != null) {
                    aVar.c(this.fOutBuffer, this.fOutBufferSize);
                }
            } catch (Throwable unused) {
            }
        }
    }
