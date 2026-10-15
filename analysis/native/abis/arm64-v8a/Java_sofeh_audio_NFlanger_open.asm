# APK member: lib/arm64-v8a/libnflanger.so
# Library SHA-256: 0367d94dbc3607e1d98b5787c916e98cbc88d73a8aaa5f5653a4955aa92158b2
# Function: Java_sofeh_audio_NFlanger_open; address=0x948; bytes=60
# Decoder mode: default; decoded_bytes=60
00000948  fd7bbfa9          stp        x29, x30, [sp, #-0x10]!
0000094c  fd030091          mov        x29, sp
00000950  620000b4          cbz        x2, #0x95c
00000954  e00302aa          mov        x0, x2
00000958  32010094          bl         #0xe20
0000095c  20008052          mov        w0, #1
00000960  81068152          mov        w1, #0x834
00000964  33010094          bl         #0xe30
00000968  08000090          adrp       x8, #0
0000096c  00b9c13d          ldr        q0, [x8, #0x6e0]
00000970  08108052          mov        w8, #0x80
00000974  0000803d          str        q0, [x0]
00000978  081000b9          str        w8, [x0, #0x10]
0000097c  fd7bc1a8          ldp        x29, x30, [sp], #0x10
00000980  c0035fd6          ret        
