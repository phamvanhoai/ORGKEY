# APK member: lib/arm64-v8a/libnlame.so
# Library SHA-256: 8fdbc059e6be58988d86df4e875f5c17f5ba3b93153afe7ef9c20bbfd409c9f9
# Function: Java_sofeh_audio_Lame_init; address=0x35e34; bytes=148
# Decoder mode: default; decoded_bytes=148
00035e34  fd7bbca9          stp        x29, x30, [sp, #-0x40]!
00035e38  f85f01a9          stp        x24, x23, [sp, #0x10]
00035e3c  f65702a9          stp        x22, x21, [sp, #0x20]
00035e40  f44f03a9          stp        x20, x19, [sp, #0x30]
00035e44  fd030091          mov        x29, sp
00035e48  d80000f0          adrp       x24, #0x50000
00035e4c  f303062a          mov        w19, w6
00035e50  f403052a          mov        w20, w5
00035e54  009f42f9          ldr        x0, [x24, #0x538]
00035e58  f503042a          mov        w21, w4
00035e5c  f603032a          mov        w22, w3
00035e60  f703022a          mov        w23, w2
00035e64  600000b4          cbz        x0, #0x35e70
00035e68  fe020094          bl         #0x36a60
00035e6c  1f9f02f9          str        xzr, [x24, #0x538]
00035e70  00030094          bl         #0x36a70
00035e74  e103172a          mov        w1, w23
00035e78  009f02f9          str        x0, [x24, #0x538]
00035e7c  0d030094          bl         #0x36ab0
00035e80  009f42f9          ldr        x0, [x24, #0x538]
00035e84  e103162a          mov        w1, w22
00035e88  0e030094          bl         #0x36ac0
00035e8c  009f42f9          ldr        x0, [x24, #0x538]
00035e90  e103152a          mov        w1, w21
00035e94  0f030094          bl         #0x36ad0
00035e98  009f42f9          ldr        x0, [x24, #0x538]
00035e9c  e103142a          mov        w1, w20
00035ea0  4c010094          bl         #0x363d0
00035ea4  009f42f9          ldr        x0, [x24, #0x538]
00035ea8  e103132a          mov        w1, w19
00035eac  0d030094          bl         #0x36ae0
00035eb0  009f42f9          ldr        x0, [x24, #0x538]
00035eb4  f44f43a9          ldp        x20, x19, [sp, #0x30]
00035eb8  f65742a9          ldp        x22, x21, [sp, #0x20]
00035ebc  f85f41a9          ldp        x24, x23, [sp, #0x10]
00035ec0  fd7bc4a8          ldp        x29, x30, [sp], #0x40
00035ec4  cb020014          b          #0x369f0
