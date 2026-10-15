# APK member: lib/arm64-v8a/libnpitchshift.so
# Library SHA-256: 25dff5430d9ff9db374fd01ab3fc879c7215eca59ad5e181fa54ca3dbd3b5cc0
# Function: Java_sofeh_audio_NPitchShift_process; address=0x1d98; bytes=68
# Decoder mode: default; decoded_bytes=68
00001d98  e20100b4          cbz        x2, #0x1dd4
00001d9c  fd7bbfa9          stp        x29, x30, [sp, #-0x10]!
00001da0  fd030091          mov        x29, sp
00001da4  6000221e          scvtf      s0, w3
00001da8  0859a852          mov        w8, #0x42c80000
00001dac  a33c4093          sxth       x3, w5
00001db0  0101271e          fmov       s1, w8
00001db4  e00302aa          mov        x0, x2
00001db8  01808052          mov        w1, #0x400
00001dbc  82008052          mov        w2, #4
00001dc0  0018211e          fdiv       s0, s0, s1
00001dc4  8100221e          scvtf      s1, w4
00001dc8  42000094          bl         #0x1ed0
00001dcc  fd7bc1a8          ldp        x29, x30, [sp], #0x10
00001dd0  c0035fd6          ret        
00001dd4  e0031f2a          mov        w0, wzr
00001dd8  c0035fd6          ret        
