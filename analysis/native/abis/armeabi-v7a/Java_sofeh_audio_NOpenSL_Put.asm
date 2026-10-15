# APK member: lib/armeabi-v7a/libnopensl.so
# Library SHA-256: 84027a5e2c443984c6607df964e90cb12f7218ac735e77863d230b3995694b1f
# Function: Java_sofeh_audio_NOpenSL_Put; address=0x964; bytes=112
# Decoder mode: thumb; decoded_bytes=112
00000964  8ab3              cbz        r2, #0x9ca
00000966  f0b5              push       {r4, r5, r6, r7, lr}
00000968  03af              add        r7, sp, #0xc
0000096a  4df8048d          str        r8, [sp, #-0x4]!
0000096e  82b0              sub        sp, #8
00000970  0646              mov        r6, r0
00000972  0068              ldr        r0, [r0]
00000974  1546              mov        r5, r2
00000976  1c46              mov        r4, r3
00000978  2946              mov        r1, r5
0000097a  d0f8ac22          ldr.w      r2, [r0, #0x2ac]
0000097e  3046              mov        r0, r6
00000980  9047              blx        r2
00000982  a042              cmp        r0, r4
00000984  b8bf              it         lt
00000986  0446              movlt      r4, r0
00000988  012c              cmp        r4, #1
0000098a  19db              blt        #0x9c0
0000098c  3068              ldr        r0, [r6]
0000098e  b4f5804f          cmp.w      r4, #0x4000
00000992  2946              mov        r1, r5
00000994  4ff00008          mov.w      r8, #0
00000998  d0f828c3          ldr.w      ip, [r0, #0x328]
0000099c  0b48              ldr        r0, [pc, #0x2c]
0000099e  7844              add        r0, pc
000009a0  0090              str        r0, [sp]
000009a2  28bf              it         hs
000009a4  4ff48044          movhs.w    r4, #0x4000
000009a8  3046              mov        r0, r6
000009aa  0022              movs       r2, #0
000009ac  2346              mov        r3, r4
000009ae  e047              blx        ip
000009b0  0748              ldr        r0, [pc, #0x1c]
000009b2  7844              add        r0, pc
000009b4  8168              ldr        r1, [r0, #8]
000009b6  4460              str        r4, [r0, #4]
000009b8  a142              cmp        r1, r4
000009ba  a8bf              it         ge
000009bc  c0f80880          strge.w    r8, [r0, #8]
000009c0  02b0              add        sp, #8
000009c2  5df8048b          ldr        r8, [sp], #4
000009c6  bde8f040          pop.w      {r4, r5, r6, r7, lr}
000009ca  7047              bx         lr
000009cc  9e33              adds       r3, #0x9e
000009ce  0000              movs       r0, r0
000009d0  8ab3              cbz        r2, #0xa36
000009d2  1000              movs       r0, r2
