# APK member: lib/armeabi-v7a/libnlame.so
# Library SHA-256: 2546cbab2bfb92ea24bd6bac3cbb612a6a689c1bc7473807e16c9fd4bd6fec21
# Function: Java_sofeh_audio_Lame_close; address=0x283c4; bytes=24
# Decoder mode: thumb; decoded_bytes=24
000283c4  d0b5              push       {r4, r6, r7, lr}
000283c6  02af              add        r7, sp, #8
000283c8  034c              ldr        r4, [pc, #0xc]
000283ca  7c44              add        r4, pc
000283cc  2068              ldr        r0, [r4]
000283ce  00f090ef          blx        #0x292f0
000283d2  0020              movs       r0, #0
000283d4  2060              str        r0, [r4]
000283d6  d0bd              pop        {r4, r6, r7, pc}
000283d8  0643              orrs       r6, r0
000283da  0100              movs       r1, r0
