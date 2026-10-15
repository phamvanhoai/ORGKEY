    public final void O0() throws Throwable {
        int i;
        this.S1 = r1(this.S1, "shortcut_mode");
        this.T1 = r1(this.T1, "mini_kbd_mode");
        this.U1 = r1(this.U1, "split_mode");
        this.V1 = r1(this.V1, "split_count");
        this.X1 = r1(this.X1, "keyboard_mode");
        this.Y1 = t1("style_kbdset", this.Y1);
        this.S2 = r1(this.S2, "tot_mode");
        this.T2 = r1(this.T2, "system_mode");
        this.U2 = r1(this.U2, "slider_mode");
        int i2 = 0;
        while (true) {
            int[] iArr = Music.ChordSingles;
            if (i2 >= iArr.length) {
                break;
            }
            iArr[i2] = r1(Music.ChordSingles[i2], ep4.j(i2, "user_chord_single"));
            i2++;
        }
        String[] strArrSplit = s1("user_chord_dual", "").split(" ");
        for (int i3 = 0; i3 < strArrSplit.length; i3++) {
            String str = strArrSplit[i3];
            String str2 = b44.a;
            try {
                i = Integer.parseInt(str);
            } catch (NumberFormatException unused) {
                i = -1;
            }
            if (i != -1) {
                Music.ChordDuals[i] = r1(Music.ChordDuals[i], "user_chord_dual" + strArrSplit[i3]);
            }
        }
        if (this.o0.isEmpty()) {
            this.o0 = getResources().getConfiguration().getLocales().get(0).getLanguage();
        }
        String strS1 = s1("locale", this.o0);
        this.o0 = strS1;
        ys1.g(this, strS1);
        this.p0 = t1("auto_save", this.p0);
        this.q0 = r1(this.q0, "stop_onchange");
        this.r0 = t1("rotation", this.r0);
        this.s0 = t1("orientation", this.s0);
        this.t0 = t1("screen_on", this.t0);
        this.u0 = t1("talkback", this.u0);
        try {
            ComponentName componentName = new ComponentName(this, (Class<?>) v());
            if (t1("safe_mode", false)) {
                getPackageManager().setComponentEnabledSetting(componentName, 1, 1);
            } else {
                getPackageManager().setComponentEnabledSetting(componentName, 2, 1);
            }
        } catch (Exception unused2) {
        }
        this.v0 = t1("hk_back_btn", this.v0);
        this.w0 = t1("hk_menu_btn", this.w0);
        this.x0 = t1("hk_immersive", this.x0);
        this.y0 = t1("path_filedialog", this.y0);
        this.z0 = r1(this.z0, "vibrate");
        this.A0 = r1(this.A0, "vibrate_time");
        this.B0 = r1(this.B0, "monitor_contrast");
        this.C0 = r1(this.C0, "monitor_timeout");
        this.D0 = t1("monitor_colored", this.D0);
        this.E0 = r1(this.E0, "monitor_tabs");
        this.F0 = t1("monitor_drum", this.F0);
        this.G0 = t1("monitor_chord", this.G0);
        this.H0 = r1(this.H0, "light_mode");
        this.I0 = r1(this.I0, "light_speed");
        this.J0 = r1(this.J0, "key_count");
        this.K0 = r1(this.K0, "key_start");
        this.L0 = t1("key_show", this.L0);
        this.M0 = r1(this.M0, "key_text");
        this.N0 = r1(this.N0, "key_quality");
        this.O0 = t1("touch_response", this.O0);
        this.P0 = t1("black_area", this.P0);
        this.Q0 = r1(this.Q0, "black_size");
        this.R0 = t1("lower_as_upper", this.R0);
        this.S0 = r1(this.S0, "shake_pitch");
        this.T0 = r1(this.T0, "drag_pitch");
        this.U0 = r1(this.U0, "pc_skin_mode");
        this.V0 = r1(this.V0, "midi_sensitivity");
        this.W0 = r1(this.W0, "midi_aftertouch_sens");
        this.X0 = r1(this.X0, "midi_direction");
        this.Y0 = r1(this.Y0, "midi_skin_mode");
        this.Z0 = t1("midi_channel_octave", this.Z0);
        this.a1 = t1("midi_live_octave", this.a1);
        this.b1 = r1(this.b1, "key_connection");
        this.c1 = r1(this.c1, "fade_time");
        this.d1 = r1(this.d1, "fade_delay");
        this.e1 = r1(this.e1, "pitchbend_range");
        this.f1 = t1("pitchbend_lock", this.f1);
        this.g1 = r1(this.g1, "pitchspeed");
        this.h1 = r1(this.h1, "ribbon_range");
        this.i1 = r1(this.i1, "metronome_volume");
        this.j1 = r1(this.j1, "metronome_level");
        this.k1 = r1(this.k1, "metronome_x");
        this.l1 = r1(this.l1, "metronome_y");
        this.m1 = t1("rhythm_loop_tempo", this.m1);
        this.n1 = t1("rhythm_loop_multi", this.n1);
        this.o1 = r1(this.o1, "rhythm_loop_scale");
        this.p1 = r1(this.p1, "rhythm_change");
        this.q1 = t1("rhythm_loop_double", this.q1);
        this.r1 = t1("rhythm_auto_tempolock", this.r1);
        this.s1 = r1(this.s1, "chords_type");
        this.t1 = r1(this.t1, "chord_synchro");
        this.u1 = r1(this.u1, "chord_synchro_sound");
        this.v1 = t1("chord_synchro_stop_pads", this.v1);
        this.w1 = r1(this.w1, "chord_ctrl");
        this.x1 = r1(this.x1, "chord_qual");
        this.y1 = t1("chord_inst", this.y1);
        this.z1 = r1(this.z1, "mic_src");
        this.A1 = r1(this.A1, "mic_channels");
        this.B1 = r1(this.B1, "recorder_quality");
        this.C1 = r1(this.C1, "recorder_channels");
        this.D1 = r1(this.D1, "recorder_bitrate");
        this.E1 = r1(this.E1, "recorder_samplerate");
        this.F1 = r1(this.F1, "mp3_quality");
        this.G1 = r1(this.G1, "mp3_channels");
        this.H1 = r1(this.H1, "mp3_bitrate");
        this.I1 = r1(this.I1, "mp3_samplerate");
        this.J1 = r1(this.J1, "output_music");
        this.K1 = r1(this.K1, "output_wave");
        this.L1 = r1(this.L1, "output_rhythm");
        this.M1 = r1(this.M1, "max_music_channel");
        this.N1 = r1(this.N1, "max_rhythm_channel");
        this.O1 = r1(this.O1, "global_reverb");
        this.P1 = r1(this.P1, "output_samplerate");
        this.Q1 = r1(this.Q1, "output_buf");
        this.R1 = r1(this.R1, "output_low_latency");
        if (this.o == null) {
            Music music = new Music(this.s4, new sb2(this));
            this.o = music;
            music.ChordTable(xs1.d(this, "Chord"));
        }
        Music music2 = this.o;
        music2.fIsOpenSLRecord = false;
        if (!music2.fOutActive) {
            music2.OutFormatSet(this.P1, 16, 2, this.Q1);
        }
        Music music3 = this.o;
        music3.fPitchSpeed = this.g1;
        music3.fMetronomeVolume = this.i1;
        music3.fMetronomeLevel = this.j1;
        music3.fMetronomeX = this.k1;
        music3.fMetronomeY = this.l1;
        music3.fLoopTempo = this.m1;
        int i4 = this.s1;
        music3.fChordType = i4;
        if (i4 > 2) {
            music3.fChordType = 1;
        }
        music3.fChordControl = this.w1;
        music3.fChordQuality = this.x1;
        music3.fGroupPlayChangeMethod = this.p1;
        music3.fKeyConnectionMethod = this.b1;
        music3.fMixerMusic = this.J1;
        music3.fMixerWave = this.K1;
        music3.fMixerRhythm = this.L1;
        music3.fMaxMusicChannel = this.M1;
        music3.fMaxRhythmChannel = Math.min(this.N1, 64);
        Music music4 = this.o;
        music4.fGlobalReverbLevel = this.O1;
        music4.fLowLatencyMethod = this.R1;
        music4.fChordSyncStart = t1("sync_start", music4.fChordSyncStart);
        Music music5 = this.o;
        music5.fChordSyncStop = t1("sync_stop", music5.fChordSyncStop);
        Music music6 = this.o;
        music6.fChordScan = r1(music6.fChordScan, "chord_scan");
        int iR1 = r1(b44.a(this.o.fSTSChanges), "sts_changes");
        boolean[] zArr = this.o.fSTSChanges;
        for (int i5 = 0; i5 < zArr.length; i5++) {
            zArr[i5] = ((1 << i5) & iR1) != 0;
        }
        r31 r31Var = this.v3;
        boolean z = this.y0;
        r31Var.e = z;
        this.w3.e = z;
        this.x3.e = z;
        this.y3.e = z;
        this.z3.e = z;
        this.A3.e = z;
        this.B3.e = z;
        this.C3.e = z;
        this.D3.e = z;
        this.E3.e = z;
        this.F3.e = z;
        this.G3.e = z;
        this.H3.e = z;
        this.I3.e = z;
        this.J3.e = z;
        this.K3.e = z;
        this.L3.e = true;
        this.M3.e = true;
        this.N3.e = z;
        this.O3.e = z;
        this.P3.e = z;
        this.Q3.e = z;
        this.R3.e = z;
        this.S3.e = z;
        this.T3.e = z;
        this.U3.e = z;
        if (this.W4) {
            this.m0.commit();
        }
        this.W4 = false;
    }
