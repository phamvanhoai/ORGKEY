# APK member: lib/armeabi-v7a/libnmusic.so
# Library SHA-256: bd2a4a0e17aaa14c558d5f96ba1ac387097bd96c2bdd771c287261dd8fdcc77e
# Function: Java_sofeh_music_NMusic_GetMem; address=0x24fc; bytes=46
# Decoder mode: thumb; decoded_bytes=46
000024fc  f0b5              push       {r4, r5, r6, r7, lr}
000024fe  03af              add        r7, sp, #0xc
00002500  4df8048d          str        r8, [sp, #-0x4]!
00002504  0646              mov        r6, r0
00002506  0068              ldr        r0, [r0]
00002508  9846              mov        r8, r3
0000250a  1546              mov        r5, r2
0000250c  d0f89443          ldr.w      r4, [r0, #0x394]
00002510  1046              mov        r0, r2
00002512  00f0f6ea          blx        #0x2b00
00002516  0146              mov        r1, r0
00002518  3046              mov        r0, r6
0000251a  2a46              mov        r2, r5
0000251c  4346              mov        r3, r8
0000251e  a446              mov        ip, r4
00002520  5df8048b          ldr        r8, [sp], #4
00002524  bde8f040          pop.w      {r4, r5, r6, r7, lr}
00002528  6047              bx         ip
