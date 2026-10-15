    @Override // defpackage.y42
    public void g(Object obj) {
        switch (this.a) {
            case 16:
                b60 b60Var = ((c23) obj).c;
                try {
                    ((Music) b60Var.g).ProcessOut(false);
                    AudioTrack audioTrack = (AudioTrack) b60Var.d;
                    Music music = (Music) b60Var.g;
                    audioTrack.write(music.fOutBuffer, 0, music.fOutBufferSize * 2);
                    Music music2 = (Music) b60Var.g;
                    a aVar = music2.fDirectRecordWriter;
                    if (aVar != null) {
                        aVar.c(music2.fOutBuffer, music2.fOutBufferSize);
                    }
                } catch (Exception unused) {
                    return;
                }
                break;
            default:
                c23 c23Var = (c23) obj;
                MainActivity mainActivity = c23Var.b;
                b60 b60Var2 = c23Var.c;
                for (int i = 2; i < 9; i++) {
                    ArrayList arrayList = (ArrayList) b60Var2.b;
                    ArrayList arrayList2 = (ArrayList) b60Var2.c;
                    int size = arrayList.size();
                    SoundPool soundPool = (SoundPool) b60Var2.a;
                    if (i >= size) {
                        arrayList.add(Integer.valueOf(soundPool.load(mainActivity, (i - 2) + R.raw.c3, 1)));
                        arrayList2.add("");
                    } else {
                        soundPool.unload(((Integer) arrayList.get(i)).intValue());
                        arrayList.set(i, Integer.valueOf(((SoundPool) b60Var2.a).load(mainActivity, (i - 2) + R.raw.c3, 1)));
                        arrayList2.set(i, "");
                    }
                }
                break;
        }
    }
