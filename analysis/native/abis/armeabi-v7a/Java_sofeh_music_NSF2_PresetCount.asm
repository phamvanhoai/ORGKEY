# APK member: lib/armeabi-v7a/libnsf2.so
# Library SHA-256: b134503a08050b5d58cdbb50dc53828aca777de7c0dac969b6dd3e110defa039
# Function: Java_sofeh_music_NSF2_PresetCount; address=0x40a0; bytes=20
# Decoder mode: thumb; decoded_bytes=20
000040a0  0348              ldr        r0, [pc, #0xc]
000040a2  7844              add        r0, pc
000040a4  0068              ldr        r0, [r0]
000040a6  08b1              cbz        r0, #0x40ac
000040a8  0069              ldr        r0, [r0, #0x10]
000040aa  7047              bx         lr
000040ac  0020              movs       r0, #0
000040ae  7047              bx         lr
000040b0  7e2e              cmp        r6, #0x7e
000040b2  0000              movs       r0, r0
