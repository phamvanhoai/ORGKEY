# APK member: lib/armeabi-v7a/libnopensl.so
# Library SHA-256: 84027a5e2c443984c6607df964e90cb12f7218ac735e77863d230b3995694b1f
# Function: Java_sofeh_audio_NOpenSL_Stop; address=0x17f0; bytes=88
# Decoder mode: thumb; decoded_bytes=88
000017f0  b0b5              push       {r4, r5, r7, lr}
000017f2  02af              add        r7, sp, #8
000017f4  134d              ldr        r5, [pc, #0x4c]
000017f6  7d44              add        r5, pc
000017f8  05f12804          add.w      r4, r5, #0x28
000017fc  2046              mov        r0, r4
000017fe  00f0e0e9          blx        #0x1bc0
00001802  0320              movs       r0, #3
00001804  e861              str        r0, [r5, #0x1c]
00001806  0020              movs       r0, #0
00001808  a870              strb       r0, [r5, #2]
0000180a  286c              ldr        r0, [r5, #0x40]
0000180c  18b1              cbz        r0, #0x1816
0000180e  0168              ldr        r1, [r0]
00001810  0a68              ldr        r2, [r1]
00001812  0121              movs       r1, #1
00001814  9047              blx        r2
00001816  686c              ldr        r0, [r5, #0x44]
00001818  10b1              cbz        r0, #0x1820
0000181a  0168              ldr        r1, [r0]
0000181c  4968              ldr        r1, [r1, #4]
0000181e  8847              blx        r1
00001820  a86c              ldr        r0, [r5, #0x48]
00001822  18b1              cbz        r0, #0x182c
00001824  0168              ldr        r1, [r0]
00001826  0a68              ldr        r2, [r1]
00001828  0121              movs       r1, #1
0000182a  9047              blx        r2
0000182c  686a              ldr        r0, [r5, #0x24]
0000182e  10b1              cbz        r0, #0x1836
00001830  0168              ldr        r1, [r0]
00001832  4968              ldr        r1, [r1, #4]
00001834  8847              blx        r1
00001836  0020              movs       r0, #0
00001838  e861              str        r0, [r5, #0x1c]
0000183a  2046              mov        r0, r4
0000183c  bde8b040          pop.w      {r4, r5, r7, lr}
00001840  00f062b9          b.w        #0x1b08
00001844  46a5              adr        r5, #0x118
00001846  1000              movs       r0, r2
