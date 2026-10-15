# APK member: lib/armeabi-v7a/libnsf2.so
# Library SHA-256: b134503a08050b5d58cdbb50dc53828aca777de7c0dac969b6dd3e110defa039
# Function: Java_sofeh_music_NSF2_Close; address=0x408c; bytes=20
# Decoder mode: thumb; decoded_bytes=20
0000408c  0348              ldr        r0, [pc, #0xc]
0000408e  7844              add        r0, pc
00004090  0068              ldr        r0, [r0]
00004092  08b1              cbz        r0, #0x4098
00004094  00f024bd          b.w        #0x4ae0
00004098  7047              bx         lr
0000409a  00bf              nop        
0000409c  922e              cmp        r6, #0x92
0000409e  0000              movs       r0, r0
