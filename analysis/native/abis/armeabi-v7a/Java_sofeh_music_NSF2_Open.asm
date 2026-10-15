# APK member: lib/armeabi-v7a/libnsf2.so
# Library SHA-256: b134503a08050b5d58cdbb50dc53828aca777de7c0dac969b6dd3e110defa039
# Function: Java_sofeh_music_NSF2_Open; address=0x406c; bytes=32
# Decoder mode: thumb; decoded_bytes=32
0000406c  80b5              push       {r7, lr}
0000406e  6f46              mov        r7, sp
00004070  1146              mov        r1, r2
00004072  0268              ldr        r2, [r0]
00004074  d2f8a432          ldr.w      r3, [r2, #0x2a4]
00004078  0022              movs       r2, #0
0000407a  9847              blx        r3
0000407c  00f060ed          blx        #0x4b40
00004080  0149              ldr        r1, [pc, #4]
00004082  7944              add        r1, pc
00004084  0860              str        r0, [r1]
00004086  80bd              pop        {r7, pc}
00004088  9e2e              cmp        r6, #0x9e
0000408a  0000              movs       r0, r0
