# APK member: lib/armeabi-v7a/libnbandbooster.so
# Library SHA-256: 785733136117fa69ab2b7908597ad4dc045b883de2775b3018ca8d1de04333cc
# Function: Java_sofeh_audio_NBandBooster_open; address=0x544; bytes=28
# Decoder mode: thumb; decoded_bytes=28
00000544  80b5              push       {r7, lr}
00000546  6f46              mov        r7, sp
00000548  52ea0300          orrs.w     r0, r2, r3
0000054c  1cbf              itt        ne
0000054e  1046              movne      r0, r2
00000550  00f01eea          blxne      #0x990
00000554  0120              movs       r0, #1
00000556  5821              movs       r1, #0x58
00000558  00f022ea          blx        #0x9a0
0000055c  0021              movs       r1, #0
0000055e  80bd              pop        {r7, pc}
