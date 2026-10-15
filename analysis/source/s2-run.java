    /* JADX INFO: Infinite loop detected, blocks: 8, insns: 0 */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v21, types: [android.view.View, android.view.ViewGroup, androidx.drawerlayout.widget.DrawerLayout] */
    /* JADX WARN: Type inference failed for: r11v0, types: [boolean, byte] */
    /* JADX WARN: Type inference failed for: r11v1, types: [int] */
    /* JADX WARN: Type inference failed for: r11v9 */
    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$PrimitiveArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    @Override // java.lang.Runnable
    public final void run() throws EOFException {
        View viewE;
        int width;
        Music music;
        int i;
        Music music2;
        int i2;
        byte b = this.a;
        byte b2 = 2;
        ?? r11 = 0;
        byte b3 = 1;
        Object obj = this.b;
        switch (b) {
            case 0:
                rx2 rx2Var = (rx2) obj;
                try {
                    y03.c();
                    try {
                        yo1 yo1Var = y03.a;
                        yo1Var.getClass();
                        in2 in2Var = rx2Var.a;
                        if (!in2Var.isClosed()) {
                            in2Var.m += 2;
                            in2Var.a();
                            break;
                        }
                        yo1Var.getClass();
                        return;
                    } catch (Throwable th) {
                        try {
                            y03.a.getClass();
                            throw th;
                        } catch (Throwable th2) {
                            th.addSuppressed(th2);
                            throw th;
                        }
                    }
                } catch (Throwable th3) {
                    rx2Var.d(th3);
                    return;
                }
            case 1:
                fp3 fp3Var = (fp3) obj;
                while (true) {
                    try {
                        fp3Var.A((b5) ((ReferenceQueue) fp3Var.d).remove());
                    } catch (InterruptedException unused) {
                        Thread.currentThread().interrupt();
                    }
                }
                break;
            case 2:
                dh dhVar = (dh) obj;
                vx2 vx2Var = dhVar.d;
                try {
                    fh fhVar = dhVar.h;
                    if (fhVar != null) {
                        oq oqVar = dhVar.b;
                        long j = oqVar.b;
                        if (j > 0) {
                            fhVar.v(oqVar, j);
                        }
                    }
                } catch (IOException e) {
                    vx2Var.m(e);
                }
                try {
                    fh fhVar2 = dhVar.h;
                    if (fhVar2 != null) {
                        fhVar2.close();
                    }
                } catch (IOException e2) {
                    vx2Var.m(e2);
                }
                try {
                    Socket socket = dhVar.i;
                    if (socket != null) {
                        socket.close();
                        return;
                    }
                    return;
                } catch (IOException e3) {
                    vx2Var.m(e3);
                    return;
                }
            case 3:
                w42 w42Var = (w42) obj;
                fq0 fq0Var = w42Var.c;
                kk kkVar = w42Var.a;
                if (w42Var.n) {
                    if (w42Var.l) {
                        w42Var.l = false;
                        long jCurrentAnimationTimeMillis = AnimationUtils.currentAnimationTimeMillis();
                        kkVar.e = jCurrentAnimationTimeMillis;
                        kkVar.g = -1L;
                        kkVar.f = jCurrentAnimationTimeMillis;
                        kkVar.h = 0.5f;
                    }
                    if ((kkVar.g > 0 && AnimationUtils.currentAnimationTimeMillis() > kkVar.g + ((long) kkVar.i)) || !w42Var.e()) {
                        w42Var.n = false;
                        return;
                    }
                    if (w42Var.m) {
                        w42Var.m = false;
                        long jUptimeMillis = SystemClock.uptimeMillis();
                        MotionEvent motionEventObtain = MotionEvent.obtain(jUptimeMillis, jUptimeMillis, 3, Sequence.PPQ, Sequence.PPQ, 0);
                        fq0Var.onTouchEvent(motionEventObtain);
                        motionEventObtain.recycle();
                    }
                    if (kkVar.f == 0) {
                        hr4.z("Cannot compute scroll delta before calling start()");
                        return;
                    }
                    long jCurrentAnimationTimeMillis2 = AnimationUtils.currentAnimationTimeMillis();
                    float fA = kkVar.a(jCurrentAnimationTimeMillis2);
                    long j2 = jCurrentAnimationTimeMillis2 - kkVar.f;
                    kkVar.f = jCurrentAnimationTimeMillis2;
                    w42Var.p.scrollListBy((int) (j2 * ((fA * 4.0f) + ((-4.0f) * fA * fA)) * kkVar.d));
                    WeakHashMap weakHashMap = lf4.a;
                    fq0Var.postOnAnimation(this);
                    return;
                }
                return;
            case 4:
                uh uhVar = (uh) obj;
                uhVar.b = false;
                BottomSheetBehavior bottomSheetBehavior = (BottomSheetBehavior) uhVar.e;
                nf4 nf4Var = bottomSheetBehavior.Q;
                if (nf4Var != null && nf4Var.h()) {
                    uhVar.b(uhVar.c);
                    return;
                } else {
                    if (bottomSheetBehavior.P == 2) {
                        bottomSheetBehavior.N(uhVar.c);
                        return;
                    }
                    return;
                }
            case 5:
                ((Carousel) obj).p.setProgress(Sequence.PPQ);
                throw null;
            case 6:
                ((nk0) obj).a.t();
                return;
            case 7:
                ((zl0) obj).q.onDismiss(null);
                return;
            case 8:
                b bVar = (b) obj;
                ?? r0 = bVar.j;
                int i3 = bVar.h.o;
                byte b4 = bVar.g;
                boolean z = b4 == 3;
                if (z) {
                    viewE = r0.e(3);
                    width = (viewE != null ? -viewE.getWidth() : 0) + i3;
                } else {
                    viewE = r0.e(5);
                    width = r0.getWidth() - i3;
                }
                if (viewE != null) {
                    if (((!z || viewE.getLeft() >= width) && (z || viewE.getLeft() <= width)) || r0.g(viewE) != 0) {
                        return;
                    }
                    DrawerLayout.LayoutParams layoutParams = (DrawerLayout.LayoutParams) viewE.getLayoutParams();
                    bVar.h.t(viewE, width, viewE.getTop());
                    layoutParams.c = true;
                    r0.invalidate();
                    View viewE2 = r0.e(b4 == 3 ? 5 : 3);
                    if (viewE2 != null) {
                        r0.c(viewE2, true);
                    }
                    if (r0.q) {
                        return;
                    }
                    long jUptimeMillis2 = SystemClock.uptimeMillis();
                    MotionEvent motionEventObtain2 = MotionEvent.obtain(jUptimeMillis2, jUptimeMillis2, 3, Sequence.PPQ, Sequence.PPQ, 0);
                    int childCount = r0.getChildCount();
                    while (r11 < childCount) {
                        r0.getChildAt(r11).dispatchTouchEvent(motionEventObtain2);
                        r11++;
                    }
                    motionEventObtain2.recycle();
                    r0.q = true;
                    return;
                }
                return;
            case 9:
                fq0 fq0Var2 = (fq0) obj;
                fq0Var2.l = null;
                fq0Var2.drawableStateChanged();
                return;
            case 10:
                t11 t11Var = (t11) obj;
                ValueAnimator valueAnimator = t11Var.z;
                byte b5 = t11Var.A;
                if (b5 == 1) {
                    valueAnimator.cancel();
                } else if (b5 != 2) {
                    return;
                }
                t11Var.A = (byte) 3;
                valueAnimator.setFloatValues(((Float) valueAnimator.getAnimatedValue()).floatValue(), Sequence.PPQ);
                valueAnimator.setDuration(500L);
                valueAnimator.start();
                return;
            case 11:
                r31 r31Var = (r31) ((s2) obj).b;
                r31Var.s.getListView().setSelection(r31Var.g[r31Var.o]);
                return;
            case 12:
                ((r31) obj).i.runOnUiThread(new s2(this, (byte) 11));
                return;
            case 13:
                i51 i51Var = (i51) obj;
                k51 k51Var = i51Var.f;
                n21 n21Var = k51Var.f;
                oo ooVar = i51Var.c;
                Activity activity = i51Var.d;
                oo ooVar2 = n21Var.a;
                if (ooVar2 == null ? false : ooVar2.d().isShown()) {
                    fp5.s("Fiam already active. Cannot show new Fiam.");
                } else if (activity.isFinishing() || activity.isDestroyed()) {
                    fp5.s("Activity is finishing or does not have valid window token. Cannot show FIAM.");
                } else {
                    dp1 dp1VarA = ooVar.a();
                    WindowManager.LayoutParams layoutParams2 = new WindowManager.LayoutParams(dp1VarA.g.intValue(), dp1VarA.h.intValue(), BackupConstant.SCENE_CLOSED_WITHOUT_SHOW, dp1VarA.e.intValue(), -3);
                    Rect rectA = n21.a(activity);
                    if ((dp1VarA.f.intValue() & 48) == 48) {
                        layoutParams2.y = rectA.top;
                    }
                    layoutParams2.dimAmount = 0.3f;
                    layoutParams2.gravity = dp1VarA.f.intValue();
                    layoutParams2.windowAnimations = 0;
                    WindowManager windowManager = (WindowManager) activity.getSystemService("window");
                    windowManager.addView(ooVar.d(), layoutParams2);
                    Rect rectA2 = n21.a(activity);
                    fp5.r("Inset (top, bottom)", rectA2.top, rectA2.bottom);
                    fp5.r("Inset (left, right)", rectA2.left, rectA2.right);
                    if (ooVar instanceof gm) {
                        z20 z20Var = new z20((Object) ooVar, (byte) 27);
                        ((gm) ooVar).e.setOnTouchListener(dp1VarA.g.intValue() == -1 ? new tz3(((gm) ooVar).e, z20Var) : new m21(((gm) ooVar).e, z20Var, layoutParams2, windowManager, ooVar));
                    }
                    n21Var.a = ooVar;
                }
                if (ooVar.a().j.booleanValue()) {
                    j21 j21Var = k51Var.i;
                    Application application = k51Var.h;
                    ViewGroup viewGroupD = ooVar.d();
                    j21Var.getClass();
                    viewGroupD.setAlpha(Sequence.PPQ);
                    viewGroupD.measure(-2, -2);
                    Point point = new Point(0, viewGroupD.getMeasuredHeight() * (-1));
                    viewGroupD.animate().translationX(point.x).translationY(point.y).setDuration(1L).setListener(new pp0(viewGroupD, application, b2));
                    return;
                }
                return;
            case 14:
                wb1 wb1Var = (wb1) obj;
                if (wb1Var.i == null || wb1Var.i != null) {
                    return;
                }
                fp3 fp3Var2 = new fp3((byte) 15, (boolean) r11);
                Object obj2 = wb1.o;
                fp3Var2.b = obj2;
                fp3Var2.c = obj2;
                fp3Var2.d = obj2;
                fp3Var2.e = null;
                wb1Var.i = fp3Var2;
                return;
            case 15:
                ((c54) obj).m();
                throw null;
            case 16:
                ts1 ts1Var = (ts1) ((hg5) obj).c;
                re2 re2Var = ts1Var.r;
                ts1Var.q = null;
                ts1Var.r = null;
                re2Var.c(nw3.m.h("InternalSubchannel closed transport due to address change"));
                return;
            case 17:
                wu1 wu1Var = (wu1) obj;
                wu1Var.m = wu1Var.p;
                wu1Var.p = null;
                wu1Var.c("fireOpenConnection", new mu1(wu1Var, new nu1(wu1Var, b3), b2));
                return;
            case 18:
                z53 z53Var = (z53) ((d62) obj);
                for (qj3 qj3Var : z53Var.t) {
                    qj3Var.l(true);
                    z20 z20Var2 = qj3Var.h;
                    if (z20Var2 != null) {
                        z20Var2.x(qj3Var.e);
                        qj3Var.h = null;
                        qj3Var.g = null;
                    }
                }
                gq1 gq1Var = z53Var.m;
                j01 j01Var = (j01) gq1Var.b;
                if (j01Var != null) {
                    j01Var.release();
                    gq1Var.b = null;
                }
                gq1Var.c = null;
                return;
            case 19:
                t82 t82Var = (t82) ((s2) obj).b;
                if (t82Var.b.N0() || (music = t82Var.b.o) == null) {
                    return;
                }
                if (music.fOutActive && (((i = music.fLowLatencyMethod) == 1 || i == 2) && !music.fIsOpenSL)) {
                    music.OutDeviceStop();
                    b44.g(50);
                    b44.g(50);
                    b44.g(50);
                    t82Var.b.o.OutDeviceStart();
                }
                if (t82Var.b.W0()) {
                    MainActivity mainActivity = t82Var.b;
                    if (mainActivity.G5) {
                        return;
                    }
                    mainActivity.G5 = true;
                    return;
                }
                return;
            case 20:
                MainActivity mainActivity2 = ((t82) obj).b;
                if (mainActivity2.N0() || (music2 = mainActivity2.o) == null || !mainActivity2.q4) {
                    return;
                }
                if (music2.fOutActive && (((i2 = music2.fLowLatencyMethod) == 1 || i2 == 2) && !music2.fIsOpenSL)) {
                    music2.OutDeviceStop();
                    b44.g(50);
                    b44.g(50);
                    b44.g(50);
                    mainActivity2.o.OutDeviceStart();
                }
                Music music3 = mainActivity2.o;
                if (music3.fOutActive && music3.fIsOpenSL && mainActivity2.W0() && !mainActivity2.G5) {
                    mainActivity2.G5 = true;
                }
                new Handler().postDelayed(new s2(this, (byte) 19), 1000L);
                return;
            case 21:
                ((u82) obj).d.Y();
                return;
            case 22:
                v82 v82Var = (v82) ((m) obj).b;
                w82 w82Var = v82Var.b;
                w82Var.f.k4 = w82Var.b;
                Intent intent = new Intent("android.intent.action.CREATE_DOCUMENT");
                intent.setType("*/*");
                intent.putExtra("android.intent.extra.TITLE", bp3.i(v82Var.b.f.k4, true));
                v82Var.b.f.startActivityForResult(intent, 20009);
                return;
            case 23:
                u82 u82Var = (u82) obj;
                MainActivity mainActivity3 = u82Var.d;
                if (mainActivity3.N0()) {
                    return;
                }
                mainActivity3.W(u82Var.b, "", "", false);
                return;
            case 24:
                com.sofeh.android.musicstudio3.MainActivity mainActivity4 = (com.sofeh.android.musicstudio3.MainActivity) obj;
                mainActivity4.M6.preload(mainActivity4);
                return;
            case 25:
                y82 y82Var = (y82) obj;
                s82 s82Var = (s82) y82Var.e;
                s82Var.b.L1(5, false);
                s82Var.b.z0();
                AlertDialog alertDialog = s82Var.b.u4;
                if (alertDialog == null || !alertDialog.isShowing()) {
                    return;
                }
                s82Var.b.u4.cancel();
                MainActivity mainActivity5 = s82Var.b;
                mainActivity5.u4 = null;
                mainActivity5.R(y82Var.c == 0);
                return;
            case 26:
                y82 y82Var2 = (y82) obj;
                s82 s82Var2 = (s82) y82Var2.e;
                s82Var2.b.L1(5, false);
                MainActivity mainActivity6 = s82Var2.b;
                Music music4 = mainActivity6.o;
                String[] strArr = mainActivity6.s5;
                int i4 = y82Var2.c;
                music4.RecordTrack(music4.Track(0, strArr[i4]), (short) 62, s82Var2.b.o.InstrumentCount() - 1);
                MainActivity mainActivity7 = s82Var2.b;
                mainActivity7.G1(mainActivity7.s5[i4], true);
                s82Var2.b.z0();
                AlertDialog alertDialog2 = s82Var2.b.v4;
                if (alertDialog2 == null || !alertDialog2.isShowing()) {
                    return;
                }
                s82Var2.b.v4.cancel();
                MainActivity mainActivity8 = s82Var2.b;
                mainActivity8.v4 = null;
                mainActivity8.J(i4);
                return;
            case 27:
                k92 k92Var = ((j92) obj).b;
                if (k92Var.d.N0()) {
                    return;
                }
                try {
                    String str = ((MainApplication) k92Var.d.getApplication()).b;
                    if (str.hashCode() == 1845676362 && str.equals("newdata")) {
                        k92Var.d.V("", true);
                    }
                    ((MainApplication) k92Var.d.getApplication()).b = "";
                    return;
                } catch (Throwable unused2) {
                    return;
                }
            default:
                k92 k92Var2 = ((j92) obj).b;
                if (k92Var2.d.N0()) {
                    return;
                }
                MainActivity mainActivity9 = k92Var2.d;
                if (mainActivity9.l4) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(mainActivity9.r4);
                    builder.setItems(new String[]{mainActivity9.getString(R.string.dialog_open_original), mainActivity9.getString(R.string.dialog_open_set), mainActivity9.getString(R.string.dialog_empty)}, new x82(mainActivity9, r11));
                    mainActivity9.z(R.layout.ttl_null, "", 0, builder, 1, false, false, false, 0);
                    mainActivity9.u4.setCancelable(false);
                    k92Var2.d.l4 = false;
                } else {
                    mainActivity9.N1("menu", true);
                }
                new la2(k92Var2.d, b3).start();
                return;
        }
    }
