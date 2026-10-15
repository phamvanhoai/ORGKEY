# APK member: lib/armeabi-v7a/libnsf2.so
# Library SHA-256: b134503a08050b5d58cdbb50dc53828aca777de7c0dac969b6dd3e110defa039
# Function: Java_sofeh_music_NSF2_PresetName; address=0x40b4; bytes=52
# Decoder mode: thumb; decoded_bytes=52
000040b4  0b49              ldr        r1, [pc, #0x2c]
000040b6  7944              add        r1, pc
000040b8  0968              ldr        r1, [r1]
000040ba  89b1              cbz        r1, #0x40e0
000040bc  d0b5              push       {r4, r6, r7, lr}
000040be  02af              add        r7, sp, #8
000040c0  0368              ldr        r3, [r0]
000040c2  002a              cmp        r2, #0
000040c4  d3f89c32          ldr.w      r3, [r3, #0x29c]
000040c8  06d4              bmi        #0x40d8
000040ca  0c69              ldr        r4, [r1, #0x10]
000040cc  9442              cmp        r4, r2
000040ce  03dd              ble        #0x40d8
000040d0  0968              ldr        r1, [r1]
000040d2  01eb4211          add.w      r1, r1, r2, lsl #5
000040d6  00e0              b          #0x40da
000040d8  0021              movs       r1, #0
000040da  bde8d040          pop.w      {r4, r6, r7, lr}
000040de  1847              bx         r3
000040e0  0020              movs       r0, #0
000040e2  7047              bx         lr
000040e4  6a2e              cmp        r6, #0x6a
000040e6  0000              movs       r0, r0
