    @Override
    public final void onCreate(Bundle bundle) {
        try {
            ((MainApplication) getApplication()).c = Boolean.TRUE;
        } catch (Exception unused) {
        }
        super.onCreate(bundle);
        setContentView(R.layout.activity_safe);
        new Handler().postDelayed(new r92(this, (byte) 29), 750L);
    }
