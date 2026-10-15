# APK member: lib/arm64-v8a/libnequalizer.so
# Library SHA-256: 8ea1d1bb9a47bdb7f785ccbca268dd7b2758ab07e14e89c818f91f4d0a1581cb
# Function: Java_sofeh_audio_NEqualizer_bandMove; address=0xa74; bytes=44
# Decoder mode: default; decoded_bytes=44
00000a74  420100b4          cbz        x2, #0xa9c
00000a78  0859a852          mov        w8, #0x42c80000
00000a7c  0101271e          fmov       s1, w8
00000a80  2238201e          fsub       s2, s1, s0
00000a84  0028211e          fadd       s0, s0, s1
00000a88  4800381e          fcvtzs     w8, s2
00000a8c  0900381e          fcvtzs     w9, s0
00000a90  000d044e          dup        v0.4s, w8
00000a94  210d044e          dup        v1.4s, w9
00000a98  400400ad          stp        q0, q1, [x2]
00000a9c  c0035fd6          ret        
