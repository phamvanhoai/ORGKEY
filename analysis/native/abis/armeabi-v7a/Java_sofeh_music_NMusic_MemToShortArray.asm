# APK member: lib/armeabi-v7a/libnmusic.so
# Library SHA-256: bd2a4a0e17aaa14c558d5f96ba1ac387097bd96c2bdd771c287261dd8fdcc77e
# Function: Java_sofeh_music_NMusic_MemToShortArray; address=0x2574; bytes=52
# Decoder mode: thumb; decoded_bytes=52
00002574  f0b5              push       {r4, r5, r6, r7, lr}
00002576  03af              add        r7, sp, #0xc
00002578  4df804bd          str        fp, [sp, #-0x4]!
0000257c  82b0              sub        sp, #8
0000257e  0546              mov        r5, r0
00002580  0068              ldr        r0, [r0]
00002582  1146              mov        r1, r2
00002584  1c46              mov        r4, r3
00002586  d0f89823          ldr.w      r2, [r0, #0x398]
0000258a  2846              mov        r0, r5
0000258c  9047              blx        r2
0000258e  2968              ldr        r1, [r5]
00002590  ba68              ldr        r2, [r7, #8]
00002592  3b69              ldr        r3, [r7, #0x10]
00002594  d1f84863          ldr.w      r6, [r1, #0x348]
00002598  2146              mov        r1, r4
0000259a  0090              str        r0, [sp]
0000259c  2846              mov        r0, r5
0000259e  b047              blx        r6
000025a0  02b0              add        sp, #8
000025a2  5df804bb          ldr        fp, [sp], #4
000025a6  f0bd              pop        {r4, r5, r6, r7, pc}
