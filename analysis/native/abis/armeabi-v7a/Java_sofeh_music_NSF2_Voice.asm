# APK member: lib/armeabi-v7a/libnsf2.so
# Library SHA-256: b134503a08050b5d58cdbb50dc53828aca777de7c0dac969b6dd3e110defa039
# Function: Java_sofeh_music_NSF2_Voice; address=0x40fc; bytes=176
# Decoder mode: thumb; decoded_bytes=160
000040fc  f0b5              push       {r4, r5, r6, r7, lr}
000040fe  03af              add        r7, sp, #0xc
00004100  2de9800f          push.w     {r7, r8, sb, sl, fp}
00004104  8146              mov        sb, r0
00004106  a3f11900          sub.w      r0, r3, #0x19
0000410a  0026              movs       r6, #0
0000410c  4628              cmp        r0, #0x46
0000410e  3dd8              bhi        #0x418c
00004110  244c              ldr        r4, [pc, #0x90]
00004112  7c44              add        r4, pc
00004114  2068              ldr        r0, [r4]
00004116  c8b3              cbz        r0, #0x418c
00004118  9b46              mov        fp, r3
0000411a  9246              mov        sl, r2
0000411c  d7f80880          ldr.w      r8, [r7, #8]
00004120  00f086ed          blx        #0x4c30
00004124  2068              ldr        r0, [r4]
00004126  0021              movs       r1, #0
00004128  1a4a              ldr        r2, [pc, #0x68]
0000412a  4ff07e53          mov.w      r3, #0x3f800000
0000412e  8162              str        r1, [r0, #0x28]
00004130  c0e90812          strd       r1, r2, [r0, #0x20]
00004134  5146              mov        r1, sl
00004136  5a46              mov        r2, fp
00004138  00f09aed          blx        #0x4c70
0000413c  2068              ldr        r0, [r4]
0000413e  0023              movs       r3, #0
00004140  194e              ldr        r6, [pc, #0x64]
00004142  154d              ldr        r5, [pc, #0x54]
00004144  7e44              add        r6, pc
00004146  3146              mov        r1, r6
00004148  2a46              mov        r2, r5
0000414a  00f0baed          blx        #0x4cc0
0000414e  2068              ldr        r0, [r4]
00004150  5146              mov        r1, sl
00004152  5a46              mov        r2, fp
00004154  00f09ced          blx        #0x4c90
00004158  1048              ldr        r0, [pc, #0x40]
0000415a  b446              mov        ip, r6
0000415c  1049              ldr        r1, [pc, #0x40]
0000415e  3044              add        r0, r6
00004160  2e46              mov        r6, r5
00004162  0b46              mov        r3, r1
00004164  41b1              cbz        r1, #0x4178
00004166  0188              ldrh       r1, [r0]
00004168  31b9              cbnz       r1, #0x4178
0000416a  021f              subs       r2, r0, #4
0000416c  4088              ldrh       r0, [r0, #2]
0000416e  751e              subs       r5, r6, #1
00004170  991e              subs       r1, r3, #2
00004172  0028              cmp        r0, #0
00004174  1046              mov        r0, r2
00004176  f3d0              beq        #0x4160
00004178  d9f80000          ldr.w      r0, [sb]
0000417c  4146              mov        r1, r8
0000417e  0022              movs       r2, #0
00004180  d0f84843          ldr.w      r4, [r0, #0x348]
00004184  4846              mov        r0, sb
00004186  cdf800c0          str.w      ip, [sp]
0000418a  a047              blx        r4
0000418c  3046              mov        r0, r6
0000418e  bde8080f          pop.w      {r3, r8, sb, sl, fp}
00004192  f0bd              pop        {r4, r5, r6, r7, pc}
00004194  0080              strh       r0, [r0]
00004196  3b47              bx         r7
00004198  00ee0200          cdp        p0, #0, c0, c0, c2, #0
# Undecoded tail: offset=0xa0; bytes=16
# Raw hex: fcb70b0000dc05000e2e0000e02d0000
