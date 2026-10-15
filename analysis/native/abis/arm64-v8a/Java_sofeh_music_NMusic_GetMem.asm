# APK member: lib/arm64-v8a/libnmusic.so
# Library SHA-256: 282be49ee24cb3822cc941fb9003d9259c93cd6ea56238426f4f24b018d78156
# Function: Java_sofeh_music_NMusic_GetMem; address=0x3064; bytes=76
# Decoder mode: default; decoded_bytes=76
00003064  ffc300d1          sub        sp, sp, #0x30
00003068  fd7b01a9          stp        x29, x30, [sp, #0x10]
0000306c  f44f02a9          stp        x20, x19, [sp, #0x20]
00003070  fd430091          add        x29, sp, #0x10
00003074  080040f9          ldr        x8, [x0]
00003078  f30300aa          mov        x19, x0
0000307c  f40302aa          mov        x20, x2
00003080  009543f9          ldr        x0, [x8, #0x728]
00003084  e00700f9          str        x0, [sp, #8]
00003088  e00302aa          mov        x0, x2
0000308c  85000094          bl         #0x32a0
00003090  e10300aa          mov        x1, x0
00003094  e00313aa          mov        x0, x19
00003098  e20314aa          mov        x2, x20
0000309c  e30740f9          ldr        x3, [sp, #8]
000030a0  f44f42a9          ldp        x20, x19, [sp, #0x20]
000030a4  fd7b41a9          ldp        x29, x30, [sp, #0x10]
000030a8  ffc30091          add        sp, sp, #0x30
000030ac  60001fd6          br         x3
