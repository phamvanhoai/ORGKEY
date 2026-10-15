# APK member: lib/arm64-v8a/libnmusic.so
# Library SHA-256: 282be49ee24cb3822cc941fb9003d9259c93cd6ea56238426f4f24b018d78156
# Function: Java_sofeh_music_NMusic_FreeMem; address=0x30b0; bytes=32
# Decoder mode: default; decoded_bytes=32
000030b0  fd7bbfa9          stp        x29, x30, [sp, #-0x10]!
000030b4  fd030091          mov        x29, sp
000030b8  080040f9          ldr        x8, [x0]
000030bc  e10302aa          mov        x1, x2
000030c0  089943f9          ldr        x8, [x8, #0x730]
000030c4  00013fd6          blr        x8
000030c8  fd7bc1a8          ldp        x29, x30, [sp], #0x10
000030cc  7d000014          b          #0x32c0
