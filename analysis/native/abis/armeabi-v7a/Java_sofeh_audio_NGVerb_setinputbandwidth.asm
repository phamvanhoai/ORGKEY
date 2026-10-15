# APK member: lib/armeabi-v7a/libngverb.so
# Library SHA-256: f4be624ae221f556beaa4e0e3c9dd6b359e44e97d630f47aa9e74fe9a3990748
# Function: Java_sofeh_audio_NGVerb_setinputbandwidth; address=0x1c18; bytes=32
# Decoder mode: thumb; decoded_bytes=32
00001c18  52ea0300          orrs.w     r0, r2, r3
00001c1c  1fbf              itttt      ne
00001c1e  9ded000a          vldrne     s0, [sp]
00001c22  82ed010a          vstrne     s0, [r2, #4]
00001c26  b7ee001a          vmovne.f32 s2, #1.000000e+00
00001c2a  31ee400a          vsubne.f32 s0, s2, s0
00001c2e  1cbf              itt        ne
00001c30  1069              ldrne      r0, [r2, #0x10]
00001c32  80ed000a          vstrne     s0, [r0]
00001c36  7047              bx         lr
