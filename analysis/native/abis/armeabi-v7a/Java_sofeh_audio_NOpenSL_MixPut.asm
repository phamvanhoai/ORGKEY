# APK member: lib/armeabi-v7a/libnopensl.so
# Library SHA-256: 84027a5e2c443984c6607df964e90cb12f7218ac735e77863d230b3995694b1f
# Function: Java_sofeh_audio_NOpenSL_MixPut; address=0xb54; bytes=424
# Decoder mode: thumb; decoded_bytes=424
00000b54  f0b5              push       {r4, r5, r6, r7, lr}
00000b56  03af              add        r7, sp, #0xc
00000b58  2de9000f          push.w     {r8, sb, sl, fp}
00000b5c  adf5803d          sub.w      sp, sp, #0x10000
00000b60  87b0              sub        sp, #0x1c
00000b62  0446              mov        r4, r0
00000b64  6048              ldr        r0, [pc, #0x180]
00000b66  002a              cmp        r2, #0
00000b68  7844              add        r0, pc
00000b6a  0068              ldr        r0, [r0]
00000b6c  0068              ldr        r0, [r0]
00000b6e  47f8240c          str        r0, [r7, #-0x24]
00000b72  00f0a980          beq.w      #0xcc8
00000b76  9b46              mov        fp, r3
00000b78  002b              cmp        r3, #0
00000b7a  00f0a580          beq.w      #0xcc8
00000b7e  2068              ldr        r0, [r4]
00000b80  1646              mov        r6, r2
00000b82  3146              mov        r1, r6
00000b84  bd68              ldr        r5, [r7, #8]
00000b86  d0f8ac22          ldr.w      r2, [r0, #0x2ac]
00000b8a  2046              mov        r0, r4
00000b8c  9047              blx        r2
00000b8e  8046              mov        r8, r0
00000b90  2068              ldr        r0, [r4]
00000b92  5946              mov        r1, fp
00000b94  d0f8ac22          ldr.w      r2, [r0, #0x2ac]
00000b98  2046              mov        r0, r4
00000b9a  9047              blx        r2
00000b9c  a845              cmp        r8, r5
00000b9e  b8bf              it         lt
00000ba0  4546              movlt      r5, r8
00000ba2  05ebd571          add.w      r1, r5, r5, lsr #31
00000ba6  b0eb610f          cmp.w      r0, r1, asr #1
00000baa  b8bf              it         lt
00000bac  4500              lsllt      r5, r0, #1
00000bae  b5f5804f          cmp.w      r5, #0x4000
00000bb2  2946              mov        r1, r5
00000bb4  a8bf              it         ge
00000bb6  4ff48041          movge.w    r1, #0x4000
00000bba  012d              cmp        r5, #1
00000bbc  c0f28480          blt.w      #0xcc8
00000bc0  2068              ldr        r0, [r4]
00000bc2  0d46              mov        r5, r1
00000bc4  3146              mov        r1, r6
00000bc6  0022              movs       r2, #0
00000bc8  2b46              mov        r3, r5
00000bca  4ff0000a          mov.w      sl, #0
00000bce  d0f828c3          ldr.w      ip, [r0, #0x328]
00000bd2  05a8              add        r0, sp, #0x14
00000bd4  00f50049          add.w      sb, r0, #0x8000
00000bd8  2046              mov        r0, r4
00000bda  cdf80090          str.w      sb, [sp]
00000bde  0496              str        r6, [sp, #0x10]
00000be0  e047              blx        ip
00000be2  2068              ldr        r0, [r4]
00000be4  4fea5508          lsr.w      r8, r5, #1
00000be8  5946              mov        r1, fp
00000bea  0022              movs       r2, #0
00000bec  4346              mov        r3, r8
00000bee  d0f828c3          ldr.w      ip, [r0, #0x328]
00000bf2  05a8              add        r0, sp, #0x14
00000bf4  cde90005          strd       r0, r5, [sp]
00000bf8  2046              mov        r0, r4
00000bfa  0394              str        r4, [sp, #0xc]
00000bfc  cdf808b0          str.w      fp, [sp, #8]
00000c00  e047              blx        ip
00000c02  d045              cmp        r8, sl
00000c04  19d0              beq        #0xc3a
00000c06  39f82ab0          ldrh.w     fp, [sb, sl, lsl #2]
00000c0a  05a8              add        r0, sp, #0x14
00000c0c  6421              movs       r1, #0x64
00000c0e  30f91a60          ldrsh.w    r6, [r0, sl, lsl #1]
00000c12  f868              ldr        r0, [r7, #0xc]
00000c14  7043              muls       r0, r6, r0
00000c16  00f078ee          blx        #0x1908
00000c1a  5844              add        r0, fp
00000c1c  29f82a00          strh.w     r0, [sb, sl, lsl #2]
00000c20  09eb8a05          add.w      r5, sb, sl, lsl #2
00000c24  3869              ldr        r0, [r7, #0x10]
00000c26  6421              movs       r1, #0x64
00000c28  6c88              ldrh       r4, [r5, #2]
00000c2a  7043              muls       r0, r6, r0
00000c2c  00f06cee          blx        #0x1908
00000c30  2044              add        r0, r4
00000c32  6880              strh       r0, [r5, #2]
00000c34  0af1010a          add.w      sl, sl, #1
00000c38  e3e7              b          #0xc02
00000c3a  019d              ldr        r5, [sp, #4]
00000c3c  4ff40043          mov.w      r3, #0x8000
00000c40  2a48              ldr        r0, [pc, #0xa8]
00000c42  05a9              add        r1, sp, #0x14
00000c44  01f50049          add.w      sb, r1, #0x8000
00000c48  6a00              lsls       r2, r5, #1
00000c4a  7844              add        r0, pc
00000c4c  4946              mov        r1, sb
00000c4e  00f0a8ef          blx        #0x1ba0
00000c52  2748              ldr        r0, [pc, #0x9c]
00000c54  7844              add        r0, pc
00000c56  8168              ldr        r1, [r0, #8]
00000c58  4560              str        r5, [r0, #4]
00000c5a  a942              cmp        r1, r5
00000c5c  a4bf              itt        ge
00000c5e  0021              movge      r1, #0
00000c60  8160              strge      r1, [r0, #8]
00000c62  416a              ldr        r1, [r0, #0x24]
00000c64  dde90246          ldrd       r4, r6, [sp, #8]
00000c68  21b3              cbz        r1, #0xcb4
00000c6a  4169              ldr        r1, [r0, #0x14]
00000c6c  0029              cmp        r1, #0
00000c6e  21d4              bmi        #0xcb4
00000c70  0178              ldrb       r1, [r0]
00000c72  0422              movs       r2, #4
00000c74  0029              cmp        r1, #0
00000c76  18bf              it         ne
00000c78  1022              movne      r2, #0x10
00000c7a  4169              ldr        r1, [r0, #0x14]
00000c7c  9142              cmp        r1, r2
00000c7e  19da              bge        #0xcb4
00000c80  47f6fe71          movw       r1, #0x7ffe
00000c84  05ea0102          and.w      r2, r5, r1
00000c88  4069              ldr        r0, [r0, #0x14]
00000c8a  0df1140a          add.w      sl, sp, #0x14
00000c8e  1949              ldr        r1, [pc, #0x64]
00000c90  4ff40043          mov.w      r3, #0x8000
00000c94  7944              add        r1, pc
00000c96  01ebc031          add.w      r1, r1, r0, lsl #15
00000c9a  5046              mov        r0, sl
00000c9c  00f080ef          blx        #0x1ba0
00000ca0  3068              ldr        r0, [r6]
00000ca2  2146              mov        r1, r4
00000ca4  0022              movs       r2, #0
00000ca6  4346              mov        r3, r8
00000ca8  d0f848c3          ldr.w      ip, [r0, #0x348]
00000cac  3046              mov        r0, r6
00000cae  cdf800a0          str.w      sl, [sp]
00000cb2  e047              blx        ip
00000cb4  3068              ldr        r0, [r6]
00000cb6  0022              movs       r2, #0
00000cb8  0499              ldr        r1, [sp, #0x10]
00000cba  2b46              mov        r3, r5
00000cbc  d0f84843          ldr.w      r4, [r0, #0x348]
00000cc0  3046              mov        r0, r6
00000cc2  cdf80090          str.w      sb, [sp]
00000cc6  a047              blx        r4
00000cc8  57f8240c          ldr        r0, [r7, #-0x24]
00000ccc  0a49              ldr        r1, [pc, #0x28]
00000cce  7944              add        r1, pc
00000cd0  0968              ldr        r1, [r1]
00000cd2  0968              ldr        r1, [r1]
00000cd4  8142              cmp        r1, r0
00000cd6  01bf              itttt      eq
00000cd8  0df5803d          addeq.w    sp, sp, #0x10000
00000cdc  07b0              addeq      sp, #0x1c
00000cde  bde8000f          popeq.w    {r8, sb, sl, fp}
00000ce2  f0bd              popeq      {r4, r5, r6, r7, pc}
00000ce4  00f054ef          blx        #0x1b90
00000ce8  7821              movs       r1, #0x78
00000cea  0000              movs       r0, r0
00000cec  f230              adds       r0, #0xf2
00000cee  0000              movs       r0, r0
00000cf0  e8b0              sub        sp, #0x1a0
00000cf2  1000              movs       r0, r2
00000cf4  a8b0              sub        sp, #0xa0
00000cf6  0800              movs       r0, r1
00000cf8  1220              movs       r0, #0x12
00000cfa  0000              movs       r0, r0
