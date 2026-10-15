# APK member: lib/armeabi-v7a/libnopensl.so
# Library SHA-256: 84027a5e2c443984c6607df964e90cb12f7218ac735e77863d230b3995694b1f
# Function: Java_sofeh_audio_NOpenSL_Write; address=0x9d4; bytes=384
# Decoder mode: thumb; decoded_bytes=384
000009d4  f0b5              push       {r4, r5, r6, r7, lr}
000009d6  03af              add        r7, sp, #0xc
000009d8  2de9000f          push.w     {r8, sb, sl, fp}
000009dc  adf5004d          sub.w      sp, sp, #0x8000
000009e0  85b0              sub        sp, #0x14
000009e2  8046              mov        r8, r0
000009e4  5748              ldr        r0, [pc, #0x15c]
000009e6  002a              cmp        r2, #0
000009e8  7844              add        r0, pc
000009ea  0068              ldr        r0, [r0]
000009ec  0068              ldr        r0, [r0]
000009ee  47f8240c          str        r0, [r7, #-0x24]
000009f2  00f09780          beq.w      #0xb24
000009f6  544d              ldr        r5, [pc, #0x150]
000009f8  7d44              add        r5, pc
000009fa  e868              ldr        r0, [r5, #0xc]
000009fc  0028              cmp        r0, #0
000009fe  00f09180          beq.w      #0xb24
00000a02  2869              ldr        r0, [r5, #0x10]
00000a04  0128              cmp        r0, #1
00000a06  c0f28d80          blt.w      #0xb24
00000a0a  d8f80000          ldr.w      r0, [r8]
00000a0e  9146              mov        sb, r2
00000a10  4946              mov        r1, sb
00000a12  1e46              mov        r6, r3
00000a14  d0f8ac22          ldr.w      r2, [r0, #0x2ac]
00000a18  4046              mov        r0, r8
00000a1a  9047              blx        r2
00000a1c  b042              cmp        r0, r6
00000a1e  b8bf              it         lt
00000a20  0646              movlt      r6, r0
00000a22  b6f5804f          cmp.w      r6, #0x4000
00000a26  b346              mov        fp, r6
00000a28  a8bf              it         ge
00000a2a  4ff4804b          movge.w    fp, #0x4000
00000a2e  012e              cmp        r6, #1
00000a30  78db              blt        #0xb24
00000a32  d8f80000          ldr.w      r0, [r8]
00000a36  4946              mov        r1, sb
00000a38  0022              movs       r2, #0
00000a3a  5b46              mov        r3, fp
00000a3c  d0f82843          ldr.w      r4, [r0, #0x328]
00000a40  03a8              add        r0, sp, #0xc
00000a42  0090              str        r0, [sp]
00000a44  4046              mov        r0, r8
00000a46  a047              blx        r4
00000a48  6869              ldr        r0, [r5, #0x14]
00000a4a  a969              ldr        r1, [r5, #0x18]
00000a4c  8842              cmp        r0, r1
00000a4e  02d1              bne        #0xa56
00000a50  ee68              ldr        r6, [r5, #0xc]
00000a52  5eb9              cbnz       r6, #0xa6c
00000a54  11e0              b          #0xa7a
00000a56  2878              ldrb       r0, [r5]
00000a58  0028              cmp        r0, #0
00000a5a  4ff00400          mov.w      r0, #4
00000a5e  18bf              it         ne
00000a60  1020              movne      r0, #0x10
00000a62  ee68              ldr        r6, [r5, #0xc]
00000a64  6969              ldr        r1, [r5, #0x14]
00000a66  8142              cmp        r1, r0
00000a68  08db              blt        #0xa7c
00000a6a  3eb1              cbz        r6, #0xa7c
00000a6c  e869              ldr        r0, [r5, #0x1c]
00000a6e  0228              cmp        r0, #2
00000a70  04d1              bne        #0xa7c
00000a72  0a20              movs       r0, #0xa
00000a74  01f07ce8          blx        #0x1b70
00000a78  e6e7              b          #0xa48
00000a7a  0026              movs       r6, #0
00000a7c  ac69              ldr        r4, [r5, #0x18]
00000a7e  4ff00009          mov.w      sb, #0
00000a82  d5f82080          ldr.w      r8, [r5, #0x20]
00000a86  3148              ldr        r0, [pc, #0xc4]
00000a88  7844              add        r0, pc
00000a8a  0290              str        r0, [sp, #8]
00000a8c  bbf1010f          cmp.w      fp, #1
00000a90  45db              blt        #0xb1e
00000a92  002e              cmp        r6, #0
00000a94  43d0              beq        #0xb1e
00000a96  e869              ldr        r0, [r5, #0x1c]
00000a98  0228              cmp        r0, #2
00000a9a  40d1              bne        #0xb1e
00000a9c  2a69              ldr        r2, [r5, #0x10]
00000a9e  9045              cmp        r8, r2
00000aa0  27db              blt        #0xaf2
00000aa2  2878              ldrb       r0, [r5]
00000aa4  0028              cmp        r0, #0
00000aa6  4ff00400          mov.w      r0, #4
00000aaa  18bf              it         ne
00000aac  1020              movne      r0, #0x10
00000aae  0134              adds       r4, #1
00000ab0  8442              cmp        r4, r0
00000ab2  4ff00000          mov.w      r0, #0
00000ab6  a8bf              it         ge
00000ab8  0446              movge      r4, r0
00000aba  6869              ldr        r0, [r5, #0x14]
00000abc  a042              cmp        r0, r4
00000abe  02d1              bne        #0xac6
00000ac0  ee68              ldr        r6, [r5, #0xc]
00000ac2  5eb9              cbnz       r6, #0xadc
00000ac4  11e0              b          #0xaea
00000ac6  2878              ldrb       r0, [r5]
00000ac8  0028              cmp        r0, #0
00000aca  4ff00400          mov.w      r0, #4
00000ace  18bf              it         ne
00000ad0  1020              movne      r0, #0x10
00000ad2  ee68              ldr        r6, [r5, #0xc]
00000ad4  6969              ldr        r1, [r5, #0x14]
00000ad6  8142              cmp        r1, r0
00000ad8  08db              blt        #0xaec
00000ada  3eb1              cbz        r6, #0xaec
00000adc  e869              ldr        r0, [r5, #0x1c]
00000ade  0228              cmp        r0, #2
00000ae0  04d1              bne        #0xaec
00000ae2  0a20              movs       r0, #0xa
00000ae4  01f044e8          blx        #0x1b70
00000ae8  e7e7              b          #0xaba
00000aea  0026              movs       r6, #0
00000aec  2a69              ldr        r2, [r5, #0x10]
00000aee  4ff00008          mov.w      r8, #0
00000af2  03a8              add        r0, sp, #0xc
00000af4  a2eb080a          sub.w      sl, r2, r8
00000af8  00eb4901          add.w      r1, r0, sb, lsl #1
00000afc  0298              ldr        r0, [sp, #8]
00000afe  d345              cmp        fp, sl
00000b00  b8bf              it         lt
00000b02  da46              movlt      sl, fp
00000b04  00ebc430          add.w      r0, r0, r4, lsl #15
00000b08  4fea4a02          lsl.w      r2, sl, #1
00000b0c  00eb4800          add.w      r0, r0, r8, lsl #1
00000b10  01f036e8          blx        #0x1b80
00000b14  abeb0a0b          sub.w      fp, fp, sl
00000b18  d144              add        sb, sl
00000b1a  d044              add        r8, sl
00000b1c  b6e7              b          #0xa8c
00000b1e  ac61              str        r4, [r5, #0x18]
00000b20  c5f82080          str.w      r8, [r5, #0x20]
00000b24  57f8240c          ldr        r0, [r7, #-0x24]
00000b28  0949              ldr        r1, [pc, #0x24]
00000b2a  7944              add        r1, pc
00000b2c  0968              ldr        r1, [r1]
00000b2e  0968              ldr        r1, [r1]
00000b30  8142              cmp        r1, r0
00000b32  01bf              itttt      eq
00000b34  0df5004d          addeq.w    sp, sp, #0x8000
00000b38  05b0              addeq      sp, #0x14
00000b3a  bde8000f          popeq.w    {r8, sb, sl, fp}
00000b3e  f0bd              popeq      {r4, r5, r6, r7, pc}
00000b40  01f026e8          blx        #0x1b90
00000b44  f822              movs       r2, #0xf8
00000b46  0000              movs       r0, r0
00000b48  44b3              cbz        r4, #0xb9c
00000b4a  1000              movs       r0, r2
00000b4c  b4b2              uxth       r4, r6
00000b4e  0000              movs       r0, r0
00000b50  b621              movs       r1, #0xb6
00000b52  0000              movs       r0, r0
