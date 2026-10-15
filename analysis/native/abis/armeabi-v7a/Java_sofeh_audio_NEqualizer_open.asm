# APK member: lib/armeabi-v7a/libnequalizer.so
# Library SHA-256: 43a844f1021b0ecaefd1ef5590ed454b77eb391a5a623bbafb0e7fc971f4108a
# Function: Java_sofeh_audio_NEqualizer_open; address=0x66c; bytes=44
# Decoder mode: thumb; decoded_bytes=44
0000066c  80b5              push       {r7, lr}
0000066e  6f46              mov        r7, sp
00000670  52ea0300          orrs.w     r0, r2, r3
00000674  1cbf              itt        ne
00000676  1046              movne      r0, r2
00000678  01f022ea          blxne      #0x1ac0
0000067c  0120              movs       r0, #1
0000067e  4ff42871          mov.w      r1, #0x2a0
00000682  01f026ea          blx        #0x1ad0
00000686  c6ef5400          vmov.i32   q8, #0x64
0000068a  0146              mov        r1, r0
0000068c  41f98d0a          vst1.32    {d16, d17}, [r1]!
00000690  41f98f0a          vst1.32    {d16, d17}, [r1]
00000694  0021              movs       r1, #0
00000696  80bd              pop        {r7, pc}
