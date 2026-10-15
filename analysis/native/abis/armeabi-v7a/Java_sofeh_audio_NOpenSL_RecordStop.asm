# APK member: lib/armeabi-v7a/libnopensl.so
# Library SHA-256: 84027a5e2c443984c6607df964e90cb12f7218ac735e77863d230b3995694b1f
# Function: Java_sofeh_audio_NOpenSL_RecordStop; address=0x18e8; bytes=28
# Decoder mode: thumb; decoded_bytes=28
000018e8  0548              ldr        r0, [pc, #0x14]
000018ea  0021              movs       r1, #0
000018ec  7844              add        r0, pc
000018ee  8170              strb       r1, [r0, #2]
000018f0  806c              ldr        r0, [r0, #0x48]
000018f2  18b1              cbz        r0, #0x18fc
000018f4  0168              ldr        r1, [r0]
000018f6  0a68              ldr        r2, [r1]
000018f8  0121              movs       r1, #1
000018fa  1047              bx         r2
000018fc  7047              bx         lr
000018fe  00bf              nop        
00001900  50a4              adr        r4, #0x140
00001902  1000              movs       r0, r2
