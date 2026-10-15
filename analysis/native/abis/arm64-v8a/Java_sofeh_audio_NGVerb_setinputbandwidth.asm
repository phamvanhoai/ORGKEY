# APK member: lib/arm64-v8a/libngverb.so
# Library SHA-256: f7967cfd6f4c70e596e7fa92e524bebcca0029509265993c59ff91bba9a05620
# Function: Java_sofeh_audio_NGVerb_setinputbandwidth; address=0x2bd4; bytes=28
# Decoder mode: default; decoded_bytes=28
00002bd4  c20000b4          cbz        x2, #0x2bec
00002bd8  01102e1e          fmov       s1, #1.00000000
00002bdc  480840f9          ldr        x8, [x2, #0x10]
00002be0  400400bd          str        s0, [x2, #4]
00002be4  2138201e          fsub       s1, s1, s0
00002be8  010100bd          str        s1, [x8]
00002bec  c0035fd6          ret        
