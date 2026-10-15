    /* JADX WARN: Multi-variable type inference failed */
    @Override // android.app.Activity
    public void onCreate(Bundle bundle) {
        tl4 tl4Var;
        super.onCreate(bundle);
        boolean z = false;
        Object[] objArr = 0;
        try {
            j30.O(getWindow(), false);
        } catch (Throwable unused) {
        }
        setContentView(R.layout.activity_main);
        this.r4 = this;
        byte b = 28;
        try {
            this.j = getPackageManager().getPackageInfo(getPackageName(), 0).versionName;
            if (Build.VERSION.SDK_INT >= 28) {
                this.l = Math.toIntExact(getPackageManager().getPackageInfo(getPackageName(), 0).getLongVersionCode());
            } else {
                this.l = getPackageManager().getPackageInfo(getPackageName(), 0).versionCode;
            }
        } catch (Exception unused2) {
        }
        try {
            this.t4 = (Vibrator) getSystemService("vibrator");
        } catch (Exception unused3) {
            this.t4 = null;
        }
        this.k0 = PreferenceManager.getDefaultSharedPreferences(this);
        this.l0 = getPreferences(0);
        this.m0 = this.k0.edit();
        this.n0 = this.l0.edit();
        try {
            getWindow().getDecorView().postDelayed(new i5(this, b), 200L);
        } catch (Throwable unused4) {
        }
        b60 b60Var = new b60();
        b60Var.b = new ArrayList();
        b60Var.c = new ArrayList();
        b60Var.d = null;
        b60Var.e = null;
        b60Var.f = null;
        b60Var.g = null;
        x13 x13Var = new x13();
        ew ewVar = new ew((byte) 6, z);
        ewVar.b = new ArrayList();
        x13Var.a = ewVar;
        x13Var.b = "";
        x13Var.c = "";
        x13Var.d = 0;
        x13Var.e = 0;
        x13Var.f = "";
        x13Var.g = true;
        try {
            AudioManager audioManager = (AudioManager) getSystemService("audio");
            x13Var.b = audioManager.getProperty("android.media.property.OUTPUT_SAMPLE_RATE");
            x13Var.c = audioManager.getProperty("android.media.property.OUTPUT_FRAMES_PER_BUFFER");
        } catch (Exception unused5) {
        }
        x13Var.a.b.add(new c23(b60Var, this));
        this.s4 = x13Var;
        String strA = xs1.a(this);
        this.W3 = strA;
        String str = File.separator;
        if (strA.endsWith(str)) {
            this.W3 = zy4.i(1, 0, this.W3);
        }
        this.V3 = ep4.o(new StringBuilder(), this.W3, str, "ORG");
        if (ys1.j(this, 0) == 0) {
            String path = Environment.getExternalStorageDirectory().getPath();
            this.V3 = path;
            if (path.endsWith(str)) {
                this.V3 = zy4.i(1, 0, this.V3);
            }
            this.V3 = ep4.o(new StringBuilder(), this.V3, str, "ORG");
            File file = new File(this.k0.getString("root_folder", ""));
            if (file.exists() && file.isDirectory()) {
                this.V3 = this.k0.getString("root_folder", "");
            }
        }
        this.Y3 = ep4.o(new StringBuilder(), this.V3, str, "Instrument");
        this.Z3 = ep4.o(new StringBuilder(), this.V3, str, "System");
        this.a4 = ep4.o(new StringBuilder(), this.V3, str, "Style");
        this.b4 = ep4.o(new StringBuilder(), this.V3, str, "Pad");
        this.c4 = ep4.o(new StringBuilder(), this.V3, str, "Skin");
        this.d4 = ep4.o(new StringBuilder(), this.V3, str, "Model");
        this.e4 = ep4.o(new StringBuilder(), this.V3, str, "Chord");
        this.X3 = ep4.o(new StringBuilder(), this.V3, str, "Import");
        this.f4 = ep4.o(new StringBuilder(), this.W3, str, ".temp");
        this.g4 = ep4.o(new StringBuilder(), this.V3, str, "Script");
        this.h4 = ep4.o(new StringBuilder(), this.V3, str, "KeyboardSet");
        bp3.n(new File(this.W3));
        File file2 = new File(this.V3);
        bp3.n(file2);
        File file3 = new File(this.Y3);
        bp3.n(file3);
        File file4 = new File(this.Z3);
        bp3.n(file4);
        File file5 = new File(this.a4);
        bp3.n(file5);
        File file6 = new File(this.b4);
        bp3.n(file6);
        File file7 = new File(this.c4);
        bp3.n(file7);
        File file8 = new File(this.d4);
        bp3.n(file8);
        File file9 = new File(this.e4);
        bp3.n(file9);
        bp3.n(new File(this.X3));
        File file10 = new File(this.f4);
        bp3.n(file10);
        File file11 = new File(this.g4);
        bp3.n(file11);
        File file12 = new File(this.h4);
        bp3.n(file12);
        s6.execute(new k82(file10, objArr == true ? 1 : 0));
        this.i4 = ep4.o(new StringBuilder(), this.V3, str, "Default.Template");
        r31 r31Var = new r31(this, file2, getString(R.string.dialog_open_music), ".Music", -28381, false);
        this.v3 = r31Var;
        r31Var.c(new s82(this, (byte) 22));
        r31 r31Var2 = new r31(this, file2, getString(R.string.dialog_open_set), ".Set;.Template;.Music", -28381, true);
        this.w3 = r31Var2;
        r31Var2.c(new s82(this, (byte) 23));
        r31 r31Var3 = new r31(this, file2, getString(R.string.dialog_download_extract), ".zip", -28381, false);
        this.x3 = r31Var3;
        r31Var3.c(new s82(this, (byte) 24));
        r31 r31Var4 = new r31(this, file2, getString(R.string.dialog_wave_track), ".wav;.ogg;.mp3;.aac;.amr", -28381, false);
        this.y3 = r31Var4;
        r31Var4.c(new s82(this, (byte) 0));
        r31 r31Var5 = new r31(this, file3, getString(R.string.dialog_instrument), ".Instrument;.wav;.ogg;.mp3;.aac;.amr", -10837505, true);
        this.z3 = r31Var5;
        r31Var5.c(new s82(this, (byte) 1));
        r31 r31Var6 = new r31(this, file3, getString(R.string.dialog_instrument), ".Instrument;.sf2;.wav;.ogg;.mp3;.aac;.amr", -10837505, true);
        this.A3 = r31Var6;
        r31Var6.c(new s82(this, (byte) 2));
        r31 r31Var7 = new r31(this, file4, getString(R.string.dialog_system), ".System", -8345066, true);
        this.B3 = r31Var7;
        r31Var7.c(new s82(this, (byte) 3));
        r31 r31Var8 = new r31(this, file4, getString(R.string.dialog_system), ".System", -8345066, true);
        this.C3 = r31Var8;
        r31Var8.c(new s82(this, (byte) 4));
        r31 r31Var9 = new r31(this, file5, getString(R.string.dialog_style), ".Style;.Rhythm;.Pad;.wav;.ogg;.mp3;.aac;.amr", -44462, true);
        this.D3 = r31Var9;
        r31Var9.c(new s82(this, (byte) 5));
        r31 r31Var10 = new r31(this, file6, getString(R.string.dialog_loop), ".Pad;.wav;.ogg;.mp3;.aac;.amr", -44462, true);
        this.E3 = r31Var10;
        r31Var10.c(new s82(this, (byte) 6));
        r31 r31Var11 = new r31(this, file6, getString(R.string.dialog_style_section), ".Pad;.wav;.ogg;.mp3;.aac;.amr", -44462, true);
        this.G3 = r31Var11;
        r31Var11.c(new s82(this, (byte) 7));
        r31 r31Var12 = new r31(this, file5, getString(R.string.dialog_style_section), ".Section", -44462, false);
        this.F3 = r31Var12;
        r31Var12.c(new s82(this, (byte) 8));
        r31 r31Var13 = new r31(this, file6, getString(R.string.dialog_sound), ".Pad;.wav;.ogg;.mp3;.aac;.amr", -44896, true);
        this.H3 = r31Var13;
        r31Var13.c(new s82(this, (byte) 9));
        r31 r31Var14 = new r31(this, file2, getString(R.string.dialog_track), ".Track", -10837505, false);
        this.I3 = r31Var14;
        r31Var14.c(new s82(this, (byte) 10));
        r31 r31Var15 = new r31(this, file7, getString(R.string.dialog_skin), ".Skin;.Skinz", -28381, true, true);
        this.J3 = r31Var15;
        r31Var15.c(new s82(this, (byte) 11));
        r31 r31Var16 = new r31(this, file2, "Audio Player", ".wav;.ogg;.mp3;.aac;.amr", -28381, false);
        this.K3 = r31Var16;
        r31Var16.c(new s82(this, (byte) 12));
        r31 r31Var17 = new r31(this, file2, getString(R.string.dialog_select_folder));
        this.L3 = r31Var17;
        r31Var17.c(new s82(this, (byte) 13));
        r31 r31Var18 = new r31(this, file2, getString(R.string.dialog_move_root));
        this.M3 = r31Var18;
        r31Var18.c(new s82(this, (byte) 14));
        r31 r31Var19 = new r31(this, file8, "MIDI Model", ".Model", -28381, false);
        this.N3 = r31Var19;
        r31Var19.c(new s82(this, (byte) 15));
        r31 r31Var20 = new r31(this, file9, "Chord", ".Chord", -44462, false);
        this.O3 = r31Var20;
        r31Var20.c(new s82(this, (byte) 16));
        this.P3 = new r31(this, file5, getString(R.string.dialog_style), ".Style;.Rhythm", -44462, false);
        this.P3.c(new s82(this, (byte) 17));
        this.Q3 = new r31(this, file2, getString(R.string.dialog_backup), "", -28381, false, true);
        this.Q3.c(new s82(this, (byte) 18));
        this.R3 = new r31(this, file11, "Script", ".Script", -8345066, false, false);
        this.R3.c(new s82(this, (byte) 19));
        this.S3 = new r31(this, file2, "MIDI file", ".mid;.midi", -44462, false, false);
        this.S3.c(new mb2());
        this.T3 = new r31(this, file2, "FX file", ".Filter", -28381, false, false);
        this.T3.c(new s82(this, (byte) 20));
        this.U3 = new r31(this, file12, "Keyboard Set", ".KeyboardSet", -10837505, false, false);
        this.U3.c(new s82(this, (byte) 21));
        this.t3 = new Handler();
        this.u3 = new t82(this, (byte) 6);
        this.P = new Paint();
        this.Q = new Matrix();
        rl4.a.getClass();
        ql4 ql4Var = ql4.a;
        sl4 sl4Var = ql4.b;
        sl4Var.getClass();
        int i = Build.VERSION.SDK_INT;
        if (i >= 34) {
            tl4Var = ti1.m;
        } else {
            tl4Var = i >= 30 ? qs0.m : c14.n;
        }
        iq iqVar = tl4Var.g(this, sl4Var.b).a;
        Rect rect = new Rect(iqVar.a, iqVar.b, iqVar.c, iqVar.d);
        this.T = Math.max(rect.width(), rect.height());
        this.U = Math.min(rect.width(), rect.height());
        this.W = this.T / this.U;
        this.X = ((float) Math.max(this.T, this.U)) / ((float) Math.min(this.T, this.U)) < 1.6f;
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        this.V = Math.sqrt(Math.pow(this.U / displayMetrics.ydpi, 2.0d) + Math.pow(this.T / displayMetrics.xdpi, 2.0d));
        int i2 = this.T;
        int i3 = this.U;
        us3 us3Var = new us3();
        us3Var.a = 15;
        us3Var.b = 6;
        us3Var.c = 2;
        us3Var.d = 1;
        us3Var.e = 50;
        us3Var.f = 80;
        us3Var.g = 15;
        us3Var.h = 10;
        us3Var.i = new ArrayList();
        us3Var.j = 0;
        us3Var.k = 0;
        us3Var.x = new int[]{-13619152, -49152, -16715536, -6250336};
        us3Var.y = 0;
        us3Var.z = 1;
        us3Var.H = false;
        us3Var.t = i2;
        us3Var.u = i3;
        if (i3 <= 2) {
            us3Var.u = i2 / 2;
        }
        us3Var.M = new float[]{1.0f, Sequence.PPQ, Sequence.PPQ, Sequence.PPQ, Sequence.PPQ, Sequence.PPQ, 1.0f, Sequence.PPQ, Sequence.PPQ, Sequence.PPQ, Sequence.PPQ, Sequence.PPQ, 1.0f, Sequence.PPQ, Sequence.PPQ, Sequence.PPQ, Sequence.PPQ, Sequence.PPQ, 1.0f, Sequence.PPQ};
        us3Var.N = new Paint[]{new Paint(), new Paint(), new Paint()};
        us3Var.O = null;
        us3Var.P = null;
        us3Var.Q = null;
        us3Var.R = null;
        us3Var.S = null;
        us3Var.T = null;
        us3Var.U = null;
        us3Var.V = null;
        us3Var.W = null;
        us3Var.X = null;
        us3Var.Y = null;
        us3Var.Z = null;
        us3Var.a0 = null;
        us3Var.b0 = null;
        us3Var.c0 = null;
        us3Var.d0 = null;
        us3Var.e0 = null;
        us3Var.f0 = null;
        us3Var.g0 = null;
        us3Var.h0 = new String[]{"", "", "", "", "", "", "", "", "", "", "", "", ""};
        us3Var.i0 = new int[]{R.drawable.icm_save, R.drawable.icm_open, R.drawable.icm_info, R.drawable.icm_mp3, R.drawable.icm_filter, R.drawable.icm_more, R.drawable.icm_record, R.drawable.icm_play, R.drawable.icm_first, R.drawable.icm_end, R.drawable.icm_ff, R.drawable.icm_rew, R.drawable.icm_pause, R.drawable.icm_stop, R.drawable.icm_midi0, R.drawable.icm_midi1, R.drawable.icm_mic0, R.drawable.icm_mic1, R.drawable.icm_metronome0, R.drawable.icm_metronome1, R.drawable.icm_metronome2, R.drawable.icm_setting, R.drawable.icm_skin, R.drawable.icm_menu, R.drawable.icm_exit, R.drawable.icm_back, R.drawable.icm_default, R.drawable.icm_cancel, R.drawable.icm_prev, R.drawable.icm_next, R.drawable.icm_begin, R.drawable.icm_plus, R.drawable.icm_minus, R.drawable.icm_inst0, R.drawable.icm_inst1, R.drawable.icm_inst2, R.drawable.icm_inst3, R.drawable.icm_inst4, R.drawable.icm_inst5, R.drawable.icm_inst6, R.drawable.icm_1, R.drawable.icm_2, R.drawable.icm_back_save};
        us3Var.j0 = new Bitmap[43];
        us3Var.k0 = new int[]{R.drawable.ins_none, R.drawable.ins_drum, R.drawable.ins_choir, R.drawable.ins_piano, R.drawable.ins_violin, R.drawable.ins_guitar, R.drawable.ins_brass, R.drawable.ins_organ, R.drawable.ins_synth};
        us3Var.l0 = new Bitmap[9];
        us3Var.o0 = false;
        us3Var.p0 = "";
        us3Var.w0 = new Rect();
        us3Var.x0 = new Rect();
        us3Var.K = this;
        us3Var.J = getResources();
        us3Var.I = null;
        us3Var.L = new Paint();
        this.m = us3Var;
        tp2 tp2Var = new tp2();
        tp2Var.e = "Default 61 Key";
        tp2Var.b = 61;
        tp2Var.c = 36;
        tp2Var.d = 17;
        tp2Var.f = "";
        tp2Var.g = new ArrayList();
        this.n = tp2Var;
        this.I = (ImageView) findViewById(R.id.imgControl);
        this.H = (ImageView) findViewById(R.id.imgKeyboard);
        ImageView imageView = (ImageView) findViewById(R.id.imgSize);
        this.J = imageView;
        this.K = this.T / 20;
        imageView.getLayoutParams().width = this.K;
        this.J.getLayoutParams().height = this.K;
        this.J.setVisibility(8);
        this.J.setOnTouchListener(new g51(this, (byte) 2));
        I();
        H1(true);
        Y();
        a0();
        y0();
        try {
            View decorView = getWindow().getDecorView();
            this.T4 = decorView;
            nb2 nb2Var = new nb2(this, (byte) 0);
            this.V4 = nb2Var;
            decorView.addOnLayoutChangeListener(nb2Var);
            this.U4 = new e5(this, (byte) 5);
            this.T4.getViewTreeObserver().addOnGlobalLayoutListener(this.U4);
        } catch (Exception unused6) {
        }
        String[] strArrSplit = xs1.e(this, "menu/pm", this.o0).split("\\r?\\n");
        String str2 = strArrSplit[0];
        String str3 = b44.a;
        str2.replaceAll("\\|", str3);
        this.z4 = strArrSplit[1].replaceAll("\\|", str3);
        strArrSplit[2].replaceAll("\\|", str3);
    }
