# APK member: lib/armeabi-v7a/libnmusic.so
# Library SHA-256: bd2a4a0e17aaa14c558d5f96ba1ac387097bd96c2bdd771c287261dd8fdcc77e
# Function: Java_sofeh_music_NMusic_ChannelCopy; address=0x1a6c; bytes=46
# Decoder mode: thumb; decoded_bytes=46
00001a6c  b0b5              push       {r4, r5, r7, lr}
00001a6e  02af              add        r7, sp, #8
00001a70  52ea0300          orrs.w     r0, r2, r3
00001a74  10d0              beq        #0x1a98
00001a76  d7e90250          ldrd       r5, r0, [r7, #8]
00001a7a  2843              orrs       r0, r5
00001a7c  0cd0              beq        #0x1a98
00001a7e  1046              mov        r0, r2
00001a80  2946              mov        r1, r5
00001a82  1446              mov        r4, r2
00001a84  01f064e8          blx        #0x2b50
00001a88  4bf20c01          movw       r1, #0xb00c
00001a8c  6018              adds       r0, r4, r1
00001a8e  2944              add        r1, r5
00001a90  bde8b040          pop.w      {r4, r5, r7, lr}
00001a94  01f004b8          b.w        #0x2aa0
00001a98  b0bd              pop        {r4, r5, r7, pc}
