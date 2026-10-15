# APK member: lib/armeabi-v7a/libnmusic.so
# Library SHA-256: bd2a4a0e17aaa14c558d5f96ba1ac387097bd96c2bdd771c287261dd8fdcc77e
# Function: Java_sofeh_music_NMusic_FreeMem; address=0x252a; bytes=22
# Decoder mode: thumb; decoded_bytes=22
0000252a  80b5              push       {r7, lr}
0000252c  6f46              mov        r7, sp
0000252e  1146              mov        r1, r2
00002530  0268              ldr        r2, [r0]
00002532  d2f89823          ldr.w      r2, [r2, #0x398]
00002536  9047              blx        r2
00002538  bde88040          pop.w      {r7, lr}
0000253c  00f09eba          b.w        #0x2a7c
