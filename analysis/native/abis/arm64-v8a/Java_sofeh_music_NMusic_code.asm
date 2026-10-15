# APK member: lib/arm64-v8a/libnmusic.so
# Library SHA-256: 282be49ee24cb3822cc941fb9003d9259c93cd6ea56238426f4f24b018d78156
# Function: Java_sofeh_music_NMusic_code; address=0x2f8c; bytes=216
# Decoder mode: default; decoded_bytes=216
00002f8c  fd7bbca9          stp        x29, x30, [sp, #-0x40]!
00002f90  f70b00f9          str        x23, [sp, #0x10]
00002f94  f65702a9          stp        x22, x21, [sp, #0x20]
00002f98  f44f03a9          stp        x20, x19, [sp, #0x30]
00002f9c  fd030091          mov        x29, sp
00002fa0  080040f9          ldr        x8, [x0]
00002fa4  f50302aa          mov        x21, x2
00002fa8  e10302aa          mov        x1, x2
00002fac  e2031faa          mov        x2, xzr
00002fb0  f303032a          mov        w19, w3
00002fb4  f40300aa          mov        x20, x0
00002fb8  08a542f9          ldr        x8, [x8, #0x548]
00002fbc  00013fd6          blr        x8
00002fc0  08004039          ldrb       w8, [x0]
00002fc4  f60300aa          mov        x22, x0
00002fc8  68010034          cbz        w8, #0x2ff4
00002fcc  e00316aa          mov        x0, x22
00002fd0  e4000094          bl         #0x3360
00002fd4  e8031faa          mov        x8, xzr
00002fd8  f7031f2a          mov        w23, wzr
00002fdc  c96a6838          ldrb       w9, [x22, x8]
00002fe0  08050091          add        x8, x8, #1
00002fe4  1f0008eb          cmp        x0, x8
00002fe8  175d091b          madd       w23, w8, w9, w23
00002fec  88ffff54          b.hi       #0x2fdc
00002ff0  02000014          b          #0x2ff8
00002ff4  f7031f2a          mov        w23, wzr
00002ff8  880240f9          ldr        x8, [x20]
00002ffc  e00314aa          mov        x0, x20
00003000  e10315aa          mov        x1, x21
00003004  e20316aa          mov        x2, x22
00003008  08a942f9          ldr        x8, [x8, #0x550]
0000300c  00013fd6          blr        x8
00003010  e8ffffb0          adrp       x8, #0
00003014  08813591          add        x8, x8, #0xd60
00003018  e96e1c53          lsl        w9, w23, #4
0000301c  08d973b8          ldr        w8, [x8, w19, sxtw #2]
00003020  200dc81a          sdiv       w0, w9, w8
00003024  09e28452          mov        w9, #0x2710
00003028  1f00096b          cmp        w0, w9
0000302c  2b010054          b.lt       #0x3050
00003030  e9e18452          mov        w9, #0x270f
00003034  ff020071          cmp        w23, #0
00003038  eaa6971a          cinc       w10, w23, lt
0000303c  577d0113          asr        w23, w10, #1
00003040  ea6e1c53          lsl        w10, w23, #4
00003044  400dc81a          sdiv       w0, w10, w8
00003048  1f00096b          cmp        w0, w9
0000304c  4cffff54          b.gt       #0x3034
00003050  f44f43a9          ldp        x20, x19, [sp, #0x30]
00003054  f70b40f9          ldr        x23, [sp, #0x10]
00003058  f65742a9          ldp        x22, x21, [sp, #0x20]
0000305c  fd7bc4a8          ldp        x29, x30, [sp], #0x40
00003060  c0035fd6          ret        
