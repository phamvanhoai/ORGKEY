    @Override // defpackage.y42
    public void g(Object obj) {
        SoundPool soundPoolBuild;
        c23 c23Var = (c23) obj;
        MainActivity mainActivity = c23Var.b;
        b60 b60Var = c23Var.c;
        ArrayList arrayList = (ArrayList) b60Var.b;
        arrayList.clear();
        ArrayList arrayList2 = (ArrayList) b60Var.c;
        arrayList2.clear();
        try {
            soundPoolBuild = new SoundPool(9, 3, 0);
        } catch (Exception unused) {
            soundPoolBuild = null;
        }
        if (soundPoolBuild == null) {
            try {
                soundPoolBuild = new SoundPool.Builder().setAudioAttributes(new AudioAttributes.Builder().setUsage(14).setContentType(4).build()).build();
            } catch (Exception unused2) {
            }
        }
        b60Var.a = soundPoolBuild;
        soundPoolBuild.setOnLoadCompleteListener(new b23(c23Var));
        arrayList.add(Integer.valueOf(((SoundPool) b60Var.a).load(mainActivity, R.raw.metronome0, 1)));
        arrayList2.add("");
        arrayList.add(Integer.valueOf(((SoundPool) b60Var.a).load(mainActivity, R.raw.metronome1, 1)));
        arrayList2.add("");
    }
