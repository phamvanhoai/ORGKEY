# APK member: lib/armeabi-v7a/libnmusic.so
# Library SHA-256: bd2a4a0e17aaa14c558d5f96ba1ac387097bd96c2bdd771c287261dd8fdcc77e
# Function: Java_sofeh_music_NMusic_MemToByteArray; address=0x2540; bytes=52
# Decoder mode: thumb; decoded_bytes=52
00002540  f0b5              push       {r4, r5, r6, r7, lr}
00002542  03af              add        r7, sp, #0xc
00002544  4df804bd          str        fp, [sp, #-0x4]!
00002548  82b0              sub        sp, #8
0000254a  0546              mov        r5, r0
0000254c  0068              ldr        r0, [r0]
0000254e  1146              mov        r1, r2
00002550  1c46              mov        r4, r3
00002552  d0f89823          ldr.w      r2, [r0, #0x398]
00002556  2846              mov        r0, r5
00002558  9047              blx        r2
0000255a  2968              ldr        r1, [r5]
0000255c  ba68              ldr        r2, [r7, #8]
0000255e  3b69              ldr        r3, [r7, #0x10]
00002560  d1f84063          ldr.w      r6, [r1, #0x340]
00002564  2146              mov        r1, r4
00002566  0090              str        r0, [sp]
00002568  2846              mov        r0, r5
0000256a  b047              blx        r6
0000256c  02b0              add        sp, #8
0000256e  5df804bb          ldr        fp, [sp], #4
00002572  f0bd              pop        {r4, r5, r6, r7, pc}
