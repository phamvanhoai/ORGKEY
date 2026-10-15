# APK member: lib/arm64-v8a/libnopensl.so
# Library SHA-256: dc900f9de170ec71471d8b5d2ccecc14d0bbb8c1d2346bd6e149467c888b38cf
# Function: Java_sofeh_audio_NOpenSL_RecordStop; address=0x2bc0; bytes=40
# Decoder mode: default; decoded_bytes=40
00002bc0  88080090          adrp       x8, #0x112000
00002bc4  1ff13e39          strb       wzr, [x8, #0xfbc]
00002bc8  88080090          adrp       x8, #0x112000
00002bcc  00e947f9          ldr        x0, [x8, #0xfd0]
00002bd0  a00000b4          cbz        x0, #0x2be4
00002bd4  080040f9          ldr        x8, [x0]
00002bd8  21008052          mov        w1, #1
00002bdc  020140f9          ldr        x2, [x8]
00002be0  40001fd6          br         x2
00002be4  c0035fd6          ret        
