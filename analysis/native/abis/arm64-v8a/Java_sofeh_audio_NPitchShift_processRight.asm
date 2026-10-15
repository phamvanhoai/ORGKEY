# APK member: lib/arm64-v8a/libnpitchshift.so
# Library SHA-256: 25dff5430d9ff9db374fd01ab3fc879c7215eca59ad5e181fa54ca3dbd3b5cc0
# Function: Java_sofeh_audio_NPitchShift_processRight; address=0x1d60; bytes=56
# Decoder mode: default; decoded_bytes=56
00001d60  820100b4          cbz        x2, #0x1d90
00001d64  6000221e          scvtf      s0, w3
00001d68  0859a852          mov        w8, #0x42c80000
00001d6c  01808052          mov        w1, #0x400
00001d70  0101271e          fmov       s1, w8
00001d74  08029652          mov        w8, #0xb010
00001d78  e30305aa          mov        x3, x5
00001d7c  4000088b          add        x0, x2, x8
00001d80  82008052          mov        w2, #4
00001d84  0018211e          fdiv       s0, s0, s1
00001d88  8100221e          scvtf      s1, w4
00001d8c  51000014          b          #0x1ed0
00001d90  e0031faa          mov        x0, xzr
00001d94  c0035fd6          ret        
