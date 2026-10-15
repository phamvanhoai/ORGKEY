# APK member: lib/arm64-v8a/libnmusic.so
# Library SHA-256: 282be49ee24cb3822cc941fb9003d9259c93cd6ea56238426f4f24b018d78156
# Function: Java_sofeh_music_NMusic_MemToByteArray; address=0x30d0; bytes=92
# Decoder mode: default; decoded_bytes=92
000030d0  fd7bbda9          stp        x29, x30, [sp, #-0x30]!
000030d4  f65701a9          stp        x22, x21, [sp, #0x10]
000030d8  f44f02a9          stp        x20, x19, [sp, #0x20]
000030dc  fd030091          mov        x29, sp
000030e0  080040f9          ldr        x8, [x0]
000030e4  e10302aa          mov        x1, x2
000030e8  f30305aa          mov        x19, x5
000030ec  f40304aa          mov        x20, x4
000030f0  f50303aa          mov        x21, x3
000030f4  f60300aa          mov        x22, x0
000030f8  089943f9          ldr        x8, [x8, #0x730]
000030fc  00013fd6          blr        x8
00003100  c80240f9          ldr        x8, [x22]
00003104  e40300aa          mov        x4, x0
00003108  e00316aa          mov        x0, x22
0000310c  e10315aa          mov        x1, x21
00003110  e203142a          mov        w2, w20
00003114  054143f9          ldr        x5, [x8, #0x680]
00003118  e303132a          mov        w3, w19
0000311c  f44f42a9          ldp        x20, x19, [sp, #0x20]
00003120  f65741a9          ldp        x22, x21, [sp, #0x10]
00003124  fd7bc3a8          ldp        x29, x30, [sp], #0x30
00003128  a0001fd6          br         x5
