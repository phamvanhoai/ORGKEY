# APK member: lib/armeabi-v7a/libnpitchshift.so
# Library SHA-256: a256c79829a2f84e4ab770889138f15e5541e0c39859a13df5d7082bfdb693c3
# Function: Java_sofeh_audio_NPitchShift_open; address=0x14d8; bytes=24
# Decoder mode: thumb; decoded_bytes=24
000014d8  80b5              push       {r7, lr}
000014da  6f46              mov        r7, sp
000014dc  52ea0300          orrs.w     r0, r2, r3
000014e0  1cbf              itt        ne
000014e2  1046              movne      r0, r2
000014e4  00f09cea          blxne      #0x1a20
000014e8  00f0a2ea          blx        #0x1a30
000014ec  0021              movs       r1, #0
000014ee  80bd              pop        {r7, pc}
