# APK member: lib/arm64-v8a/libnmusic.so
# Library SHA-256: 282be49ee24cb3822cc941fb9003d9259c93cd6ea56238426f4f24b018d78156
# Function: Java_sofeh_music_NMusic_ChannelCopy; address=0x2448; bytes=68
# Decoder mode: default; decoded_bytes=68
00002448  020200b4          cbz        x2, #0x2488
0000244c  e30100b4          cbz        x3, #0x2488
00002450  fd7bbea9          stp        x29, x30, [sp, #-0x20]!
00002454  f44f01a9          stp        x20, x19, [sp, #0x10]
00002458  fd030091          mov        x29, sp
0000245c  e00302aa          mov        x0, x2
00002460  e10303aa          mov        x1, x3
00002464  f30302aa          mov        x19, x2
00002468  f40303aa          mov        x20, x3
0000246c  9d030094          bl         #0x32e0
00002470  08029652          mov        w8, #0xb010
00002474  6002088b          add        x0, x19, x8
00002478  8102088b          add        x1, x20, x8
0000247c  f44f41a9          ldp        x20, x19, [sp, #0x10]
00002480  fd7bc2a8          ldp        x29, x30, [sp], #0x20
00002484  97030014          b          #0x32e0
00002488  c0035fd6          ret        
