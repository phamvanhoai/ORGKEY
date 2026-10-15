# APK member: lib/armeabi-v7a/libnopensl.so
# Library SHA-256: 84027a5e2c443984c6607df964e90cb12f7218ac735e77863d230b3995694b1f
# Function: Java_sofeh_audio_NOpenSL_MixWrite; address=0xcfc; bytes=640
# Decoder mode: thumb; decoded_bytes=616
00000cfc  f0b5              push       {r4, r5, r6, r7, lr}
00000cfe  03af              add        r7, sp, #0xc
00000d00  2de9000f          push.w     {r8, sb, sl, fp}
00000d04  adf5803d          sub.w      sp, sp, #0x10000
00000d08  89b0              sub        sp, #0x24
00000d0a  0546              mov        r5, r0
00000d0c  9648              ldr        r0, [pc, #0x258]
00000d0e  002a              cmp        r2, #0
00000d10  7844              add        r0, pc
00000d12  0068              ldr        r0, [r0]
00000d14  0068              ldr        r0, [r0]
00000d16  47f8240c          str        r0, [r7, #-0x24]
00000d1a  00f01381          beq.w      #0xf44
00000d1e  9b46              mov        fp, r3
00000d20  002b              cmp        r3, #0
00000d22  00f00f81          beq.w      #0xf44
00000d26  9149              ldr        r1, [pc, #0x244]
00000d28  7944              add        r1, pc
00000d2a  c868              ldr        r0, [r1, #0xc]
00000d2c  0028              cmp        r0, #0
00000d2e  00f00981          beq.w      #0xf44
00000d32  0869              ldr        r0, [r1, #0x10]
00000d34  0128              cmp        r0, #1
00000d36  c0f20581          blt.w      #0xf44
00000d3a  2868              ldr        r0, [r5]
00000d3c  1446              mov        r4, r2
00000d3e  8946              mov        sb, r1
00000d40  2146              mov        r1, r4
00000d42  be68              ldr        r6, [r7, #8]
00000d44  d0f8ac22          ldr.w      r2, [r0, #0x2ac]
00000d48  2846              mov        r0, r5
00000d4a  9047              blx        r2
00000d4c  8046              mov        r8, r0
00000d4e  2868              ldr        r0, [r5]
00000d50  5946              mov        r1, fp
00000d52  d0f8ac22          ldr.w      r2, [r0, #0x2ac]
00000d56  2846              mov        r0, r5
00000d58  9047              blx        r2
00000d5a  b045              cmp        r8, r6
00000d5c  b8bf              it         lt
00000d5e  4646              movlt      r6, r8
00000d60  06ebd671          add.w      r1, r6, r6, lsr #31
00000d64  b0eb610f          cmp.w      r0, r1, asr #1
00000d68  b8bf              it         lt
00000d6a  4600              lsllt      r6, r0, #1
00000d6c  b6f5804f          cmp.w      r6, #0x4000
00000d70  3046              mov        r0, r6
00000d72  a8bf              it         ge
00000d74  4ff48040          movge.w    r0, #0x4000
00000d78  012e              cmp        r6, #1
00000d7a  c0f2e380          blt.w      #0xf44
00000d7e  0646              mov        r6, r0
00000d80  2868              ldr        r0, [r5]
00000d82  cdf81490          str.w      sb, [sp, #0x14]
00000d86  2146              mov        r1, r4
00000d88  0022              movs       r2, #0
00000d8a  3346              mov        r3, r6
00000d8c  d0f828c3          ldr.w      ip, [r0, #0x328]
00000d90  07a8              add        r0, sp, #0x1c
00000d92  00f50040          add.w      r0, r0, #0x8000
00000d96  0090              str        r0, [sp]
00000d98  2846              mov        r0, r5
00000d9a  d7e90398          ldrd       sb, r8, [r7, #0xc]
00000d9e  0024              movs       r4, #0
00000da0  0291              str        r1, [sp, #8]
00000da2  e047              blx        ip
00000da4  2868              ldr        r0, [r5]
00000da6  7308              lsrs       r3, r6, #1
00000da8  5946              mov        r1, fp
00000daa  0022              movs       r2, #0
00000dac  d0f828c3          ldr.w      ip, [r0, #0x328]
00000db0  07a8              add        r0, sp, #0x1c
00000db2  0090              str        r0, [sp]
00000db4  2846              mov        r0, r5
00000db6  0496              str        r6, [sp, #0x10]
00000db8  0395              str        r5, [sp, #0xc]
00000dba  0693              str        r3, [sp, #0x18]
00000dbc  e047              blx        ip
00000dbe  0698              ldr        r0, [sp, #0x18]
00000dc0  a042              cmp        r0, r4
00000dc2  1cd0              beq        #0xdfe
00000dc4  07a8              add        r0, sp, #0x1c
00000dc6  6421              movs       r1, #0x64
00000dc8  00f50045          add.w      r5, r0, #0x8000
00000dcc  35f824a0          ldrh.w     sl, [r5, r4, lsl #2]
00000dd0  07a8              add        r0, sp, #0x1c
00000dd2  30f91460          ldrsh.w    r6, [r0, r4, lsl #1]
00000dd6  06fb09f0          mul        r0, r6, sb
00000dda  00f096ed          blx        #0x1908
00000dde  5044              add        r0, sl
00000de0  25f82400          strh.w     r0, [r5, r4, lsl #2]
00000de4  06fb08f0          mul        r0, r6, r8
00000de8  05eb8405          add.w      r5, r5, r4, lsl #2
00000dec  6421              movs       r1, #0x64
00000dee  b5f802a0          ldrh.w     sl, [r5, #2]
00000df2  00f08aed          blx        #0x1908
00000df6  5044              add        r0, sl
00000df8  6880              strh       r0, [r5, #2]
00000dfa  0134              adds       r4, #1
00000dfc  dfe7              b          #0xdbe
00000dfe  059c              ldr        r4, [sp, #0x14]
00000e00  6069              ldr        r0, [r4, #0x14]
00000e02  a169              ldr        r1, [r4, #0x18]
00000e04  8842              cmp        r0, r1
00000e06  02d1              bne        #0xe0e
00000e08  e068              ldr        r0, [r4, #0xc]
00000e0a  50b9              cbnz       r0, #0xe22
00000e0c  10e0              b          #0xe30
00000e0e  2078              ldrb       r0, [r4]
00000e10  0421              movs       r1, #4
00000e12  0028              cmp        r0, #0
00000e14  18bf              it         ne
00000e16  1021              movne      r1, #0x10
00000e18  e068              ldr        r0, [r4, #0xc]
00000e1a  6269              ldr        r2, [r4, #0x14]
00000e1c  8a42              cmp        r2, r1
00000e1e  08db              blt        #0xe32
00000e20  38b1              cbz        r0, #0xe32
00000e22  e169              ldr        r1, [r4, #0x1c]
00000e24  0229              cmp        r1, #2
00000e26  04d1              bne        #0xe32
00000e28  0a20              movs       r0, #0xa
00000e2a  00f0a2ee          blx        #0x1b70
00000e2e  e7e7              b          #0xe00
00000e30  0020              movs       r0, #0
00000e32  cdf804b0          str.w      fp, [sp, #4]
00000e36  4ff0000b          mov.w      fp, #0
00000e3a  d4f81890          ldr.w      sb, [r4, #0x18]
00000e3e  d4f82080          ldr.w      r8, [r4, #0x20]
00000e42  4b4d              ldr        r5, [pc, #0x12c]
00000e44  474e              ldr        r6, [pc, #0x11c]
00000e46  ddf810a0          ldr.w      sl, [sp, #0x10]
00000e4a  7d44              add        r5, pc
00000e4c  baf1010f          cmp.w      sl, #1
00000e50  5ddb              blt        #0xf0e
00000e52  0028              cmp        r0, #0
00000e54  5bd0              beq        #0xf0e
00000e56  e069              ldr        r0, [r4, #0x1c]
00000e58  0228              cmp        r0, #2
00000e5a  58d1              bne        #0xf0e
00000e5c  2269              ldr        r2, [r4, #0x10]
00000e5e  9045              cmp        r8, r2
00000e60  25db              blt        #0xeae
00000e62  2078              ldrb       r0, [r4]
00000e64  09f10109          add.w      sb, sb, #1
00000e68  0028              cmp        r0, #0
00000e6a  4ff00400          mov.w      r0, #4
00000e6e  18bf              it         ne
00000e70  1020              movne      r0, #0x10
00000e72  8145              cmp        sb, r0
00000e74  4ff00000          mov.w      r0, #0
00000e78  a8bf              it         ge
00000e7a  8146              movge      sb, r0
00000e7c  6069              ldr        r0, [r4, #0x14]
00000e7e  4845              cmp        r0, sb
00000e80  01d1              bne        #0xe86
00000e82  e068              ldr        r0, [r4, #0xc]
00000e84  08e0              b          #0xe98
00000e86  2078              ldrb       r0, [r4]
00000e88  0421              movs       r1, #4
00000e8a  0028              cmp        r0, #0
00000e8c  18bf              it         ne
00000e8e  1021              movne      r1, #0x10
00000e90  e068              ldr        r0, [r4, #0xc]
00000e92  6269              ldr        r2, [r4, #0x14]
00000e94  8a42              cmp        r2, r1
00000e96  07db              blt        #0xea8
00000e98  30b1              cbz        r0, #0xea8
00000e9a  e069              ldr        r0, [r4, #0x1c]
00000e9c  0228              cmp        r0, #2
00000e9e  03d1              bne        #0xea8
00000ea0  0a20              movs       r0, #0xa
00000ea2  00f066ee          blx        #0x1b70
00000ea6  e9e7              b          #0xe7c
00000ea8  2269              ldr        r2, [r4, #0x10]
00000eaa  4ff00008          mov.w      r8, #0
00000eae  07a8              add        r0, sp, #0x1c
00000eb0  a2eb0804          sub.w      r4, r2, r8
00000eb4  00f50040          add.w      r0, r0, #0x8000
00000eb8  a245              cmp        sl, r4
00000eba  00eb4b01          add.w      r1, r0, fp, lsl #1
00000ebe  05ebc930          add.w      r0, r5, sb, lsl #15
00000ec2  00eb4800          add.w      r0, r0, r8, lsl #1
00000ec6  b8bf              it         lt
00000ec8  5446              movlt      r4, sl
00000eca  6200              lsls       r2, r4, #1
00000ecc  00f070ee          blx        #0x1bb0
00000ed0  022c              cmp        r4, #2
00000ed2  15db              blt        #0xf00
00000ed4  0598              ldr        r0, [sp, #0x14]
00000ed6  406a              ldr        r0, [r0, #0x24]
00000ed8  90b1              cbz        r0, #0xf00
00000eda  0bebdb70          add.w      r0, fp, fp, lsr #31
00000ede  07a9              add        r1, sp, #0x1c
00000ee0  20f00100          bic        r0, r0, #1
00000ee4  08ebd873          add.w      r3, r8, r8, lsr #31
00000ee8  0844              add        r0, r1
00000eea  2249              ldr        r1, [pc, #0x88]
00000eec  23f00103          bic        r3, r3, #1
00000ef0  04ea0602          and.w      r2, r4, r6
00000ef4  7944              add        r1, pc
00000ef6  01ebc931          add.w      r1, r1, sb, lsl #15
00000efa  1944              add        r1, r3
00000efc  00f058ee          blx        #0x1bb0
00000f00  aaeb040a          sub.w      sl, sl, r4
00000f04  a344              add        fp, r4
00000f06  a044              add        r8, r4
00000f08  059c              ldr        r4, [sp, #0x14]
00000f0a  e068              ldr        r0, [r4, #0xc]
00000f0c  9ee7              b          #0xe4c
00000f0e  c4f81890          str.w      sb, [r4, #0x18]
00000f12  0022              movs       r2, #0
00000f14  c4f82080          str.w      r8, [r4, #0x20]
00000f18  039e              ldr        r6, [sp, #0xc]
00000f1a  3068              ldr        r0, [r6]
00000f1c  d0f84843          ldr.w      r4, [r0, #0x348]
00000f20  07a8              add        r0, sp, #0x1c
00000f22  0299              ldr        r1, [sp, #8]
00000f24  00f50040          add.w      r0, r0, #0x8000
00000f28  049b              ldr        r3, [sp, #0x10]
00000f2a  0090              str        r0, [sp]
00000f2c  3046              mov        r0, r6
00000f2e  a047              blx        r4
00000f30  3068              ldr        r0, [r6]
00000f32  0022              movs       r2, #0
00000f34  d0f84843          ldr.w      r4, [r0, #0x348]
00000f38  07a8              add        r0, sp, #0x1c
00000f3a  0199              ldr        r1, [sp, #4]
00000f3c  069b              ldr        r3, [sp, #0x18]
00000f3e  0090              str        r0, [sp]
00000f40  3046              mov        r0, r6
00000f42  a047              blx        r4
00000f44  57f8240c          ldr        r0, [r7, #-0x24]
00000f48  0b49              ldr        r1, [pc, #0x2c]
00000f4a  7944              add        r1, pc
00000f4c  0968              ldr        r1, [r1]
00000f4e  0968              ldr        r1, [r1]
00000f50  8142              cmp        r1, r0
00000f52  01bf              itttt      eq
00000f54  0df5803d          addeq.w    sp, sp, #0x10000
00000f58  09b0              addeq      sp, #0x24
00000f5a  bde8000f          popeq.w    {r8, sb, sl, fp}
00000f5e  f0bd              popeq      {r4, r5, r6, r7, pc}
00000f60  00f016ee          blx        #0x1b90
# Undecoded tail: offset=0x268; bytes=24
# Raw hex: feffff7fd01f000014b01000f2ae000048ae0800961d0000
