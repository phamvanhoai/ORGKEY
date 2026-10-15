# APK member: lib/armeabi-v7a/libnbandbooster.so
# Library SHA-256: 785733136117fa69ab2b7908597ad4dc045b883de2775b3018ca8d1de04333cc
# Function: Java_sofeh_audio_NBandBooster_close; address=0x77c; bytes=14
# Decoder mode: thumb; decoded_bytes=14
0000077c  52ea0300          orrs.w     r0, r2, r3
00000780  1cbf              itt        ne
00000782  1046              movne      r0, r2
00000784  00f0d4b8          bne.w      #0x930
00000788  7047              bx         lr
