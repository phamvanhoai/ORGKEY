# APK member: lib/arm64-v8a/libnsf2.so
# Library SHA-256: 8ea5a2f1833868953a9b4a52525e2544eefc6e94c98e1e9a8c344040d51139df
# Function: Java_sofeh_music_NSF2_PresetName; address=0x77d0; bytes=72
# Decoder mode: default; decoded_bytes=72
000077d0  480000b0          adrp       x8, #0x10000
000077d4  084d43f9          ldr        x8, [x8, #0x698]
000077d8  c80100b4          cbz        x8, #0x7810
000077dc  090040f9          ldr        x9, [x0]
000077e0  239d42f9          ldr        x3, [x9, #0x538]
000077e4  2201f837          tbnz       w2, #0x1f, #0x7808
000077e8  092140b9          ldr        w9, [x8, #0x20]
000077ec  3f01026b          cmp        w9, w2
000077f0  cd000054          b.le       #0x7808
000077f4  09058052          mov        w9, #0x28
000077f8  080140f9          ldr        x8, [x8]
000077fc  1f2003d5          nop        
00007800  4120a99b          umaddl     x1, w2, w9, x8
00007804  60001fd6          br         x3
00007808  e1031faa          mov        x1, xzr
0000780c  60001fd6          br         x3
00007810  e0031faa          mov        x0, xzr
00007814  c0035fd6          ret        
