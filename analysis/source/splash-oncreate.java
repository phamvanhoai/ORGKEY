    @Override
    public final void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_splash);
        this.b = (ProgressBar) findViewById(R.id.progress);
        this.a = new Handler(Looper.getMainLooper());
        fu3 showProgress = new fu3((Object) this, (byte) 0);
        this.a.postDelayed(showProgress, 1000L);
        this.d = new Thread(new fb2(this, showProgress, false, (byte) 14));
        this.d.start();
    }
