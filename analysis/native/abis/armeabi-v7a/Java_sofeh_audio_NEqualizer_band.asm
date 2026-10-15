# APK member: lib/armeabi-v7a/libnequalizer.so
# Library SHA-256: 43a844f1021b0ecaefd1ef5590ed454b77eb391a5a623bbafb0e7fc971f4108a
# Function: Java_sofeh_audio_NEqualizer_band; address=0x698; bytes=16
# Decoder mode: thumb; decoded_bytes=16
00000698  52ea0300          orrs.w     r0, r2, r3
0000069c  1cbf              itt        ne
0000069e  dde90010          ldrdne     r1, r0, [sp]
000006a2  42f82100          strne.w    r0, [r2, r1, lsl #2]
000006a6  7047              bx         lr
