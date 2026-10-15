# APK member: lib/arm64-v8a/libnpitchshift.so
# Library SHA-256: 25dff5430d9ff9db374fd01ab3fc879c7215eca59ad5e181fa54ca3dbd3b5cc0
# Function: Java_sofeh_audio_NPitchShift_open; address=0x1cc8; bytes=100
# Decoder mode: default; decoded_bytes=100
00001cc8  fd7bbea9          stp        x29, x30, [sp, #-0x20]!
00001ccc  f44f01a9          stp        x20, x19, [sp, #0x10]
00001cd0  fd030091          mov        x29, sp
00001cd4  620000b4          cbz        x2, #0x1ce0
00001cd8  e00302aa          mov        x0, x2
00001cdc  61000094          bl         #0x1e60
00001ce0  00048c52          mov        w0, #0x6020
00001ce4  2000a072          movk       w0, #1, lsl #16
00001ce8  56000094          bl         #0x1e40
00001cec  08038c52          mov        w8, #0x6018
00001cf0  e1031f2a          mov        w1, wzr
00001cf4  02019252          mov        w2, #0x9008
00001cf8  2800a072          movk       w8, #1, lsl #16
00001cfc  f30300aa          mov        x19, x0
00001d00  14019652          mov        w20, #0xb008
00001d04  1f6828f8          str        xzr, [x0, x8]
00001d08  52000094          bl         #0x1e50
00001d0c  6002148b          add        x0, x19, x20
00001d10  e1031f2a          mov        w1, wzr
00001d14  02029252          mov        w2, #0x9010
00001d18  4e000094          bl         #0x1e50
00001d1c  e00313aa          mov        x0, x19
00001d20  f44f41a9          ldp        x20, x19, [sp, #0x10]
00001d24  fd7bc2a8          ldp        x29, x30, [sp], #0x20
00001d28  c0035fd6          ret        
