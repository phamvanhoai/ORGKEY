# APK member: lib/armeabi-v7a/libnmusic.so
# Library SHA-256: bd2a4a0e17aaa14c558d5f96ba1ac387097bd96c2bdd771c287261dd8fdcc77e
# Function: Java_sofeh_music_NMusic_ChannelReset; address=0x1a4a; bytes=34
# Decoder mode: thumb; decoded_bytes=34
00001a4a  52ea0300          orrs.w     r0, r2, r3
00001a4e  08bf              it         eq
00001a50  7047              bxeq       lr
00001a52  d0b5              push       {r4, r6, r7, lr}
00001a54  02af              add        r7, sp, #8
00001a56  1046              mov        r0, r2
00001a58  1446              mov        r4, r2
00001a5a  01f072e8          blx        #0x2b40
00001a5e  4bf20c00          movw       r0, #0xb00c
00001a62  2044              add        r0, r4
00001a64  bde8d040          pop.w      {r4, r6, r7, lr}
00001a68  01f014b8          b.w        #0x2a94
