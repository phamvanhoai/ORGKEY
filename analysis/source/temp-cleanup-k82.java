    @Override // java.lang.Runnable
    public final void run() {
        byte b = this.a;
        File file = this.b;
        switch (b) {
            case 0:
                ExecutorService executorService = MainActivity.s6;
                bp3.p(file, true, System.currentTimeMillis() - 86400000);
                break;
            default:
                b.b(file);
                break;
        }
    }
