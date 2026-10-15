    @Override // defpackage.y42
    public void g(Object obj) {
        int i = this.a;
        String str = this.b;
        b60 b60Var = ((c23) obj).c;
        int i2 = i + 2;
        try {
            ArrayList arrayList = (ArrayList) b60Var.b;
            ArrayList arrayList2 = (ArrayList) b60Var.c;
            int size = arrayList.size();
            SoundPool soundPool = (SoundPool) b60Var.a;
            if (i2 >= size) {
                arrayList.add(Integer.valueOf(soundPool.load(str, 1)));
                arrayList2.add(str);
            } else {
                soundPool.unload(((Integer) arrayList.get(i2)).intValue());
                arrayList.set(i2, Integer.valueOf(((SoundPool) b60Var.a).load(str, 1)));
                arrayList2.set(i2, str);
            }
        } catch (Exception unused) {
        }
    }
