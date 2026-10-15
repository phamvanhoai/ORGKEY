# APK member: lib/armeabi-v7a/libnflanger.so
# Library SHA-256: 4d4fabc33a8e9d24417f86d86f0e51c250d4ce0cc77fc1056a4f23b59e289534
# Function: Java_sofeh_audio_NFlanger_processLeft; address=0x6b8; bytes=416
# Decoder mode: thumb; decoded_bytes=416
000006b8  52ea0300          orrs.w     r0, r2, r3
000006bc  02bf              ittt       eq
000006be  0020              moveq      r0, #0
000006c0  0021              moveq      r1, #0
000006c2  7047              bxeq       lr
000006c4  f0b5              push       {r4, r5, r6, r7, lr}
000006c6  03af              add        r7, sp, #0xc
000006c8  2de90007          push.w     {r8, sb, sl}
000006cc  2ded048b          vpush      {d8, d9}
000006d0  d7e90298          ldrd       sb, r8, [r7, #8]
000006d4  1446              mov        r4, r2
000006d6  4146              mov        r1, r8
000006d8  4846              mov        r0, sb
000006da  00f006ea          blx        #0xae8
000006de  04f5026a          add.w      sl, r4, #0x820
000006e2  94ed001a          vldr       s2, [r4]
000006e6  9fed590a          vldr       s0, [pc, #0x164]
000006ea  4cf6cc41          movw       r1, #0xcccc
000006ee  9aed002a          vldr       s4, [sl]
000006f2  b8eec11a          vcvt.f32.s32 s2, s2
000006f6  a669              ldr        r6, [r4, #0x18]
000006f8  04f62805          addw       r5, r4, #0x828
000006fc  22ee000a          vmul.f32   s0, s4, s0
00000700  c0f6cc41          movt       r1, #0xccc
00000704  20ee010a          vmul.f32   s0, s0, s2
00000708  01ee100a          vmov       s2, r0
0000070c  4cf6cd40          movw       r0, #0xcccd
00000710  ccf6cc40          movt       r0, #0xcccc
00000714  7043              muls       r0, r6, r0
00000716  30ee018a          vadd.f32   s16, s0, s2
0000071a  4feab000          ror.w      r0, r0, #2
0000071e  8842              cmp        r0, r1
00000720  85ed008a          vstr       s16, [r5]
00000724  45d8              bhi        #0x7b2
00000726  00ee106a          vmov       s0, r6
0000072a  04f61c00          addw       r0, r4, #0x81c
0000072e  94ed011a          vldr       s2, [r4, #4]
00000732  b8ee400a          vcvt.f32.u32 s0, s0
00000736  90ed002a          vldr       s4, [r0]
0000073a  b8eec11a          vcvt.f32.s32 s2, s2
0000073e  94ed029a          vldr       s18, [r4, #8]
00000742  22ee000a          vmul.f32   s0, s4, s0
00000746  30ee010a          vadd.f32   s0, s0, s2
0000074a  f7eec00a          vcvt.f64.f32 d16, s0
0000074e  51ec300b          vmov       r0, r1, d16
00000752  00f09eea          blx        #0xc90
00000756  41ec300b          vmov       d16, r0, r1
0000075a  b0ee001a          vmov.f32   s2, #2.000000e+00
0000075e  b7eee00b          vcvt.f32.f64 s0, d16
00000762  30ee000a          vadd.f32   s0, s0, s0
00000766  30ee010a          vadd.f32   s0, s0, s2
0000076a  f7eec00a          vcvt.f64.f32 d16, s0
0000076e  51ec300b          vmov       r0, r1, d16
00000772  00f096ea          blx        #0xca0
00000776  41ec300b          vmov       d16, r0, r1
0000077a  9fed353a          vldr       s6, [pc, #0xd4]
0000077e  b8eec90a          vcvt.f32.s32 s0, s18
00000782  04f50360          add.w      r0, r4, #0x830
00000786  bfee001a          vmov.f32   s2, #-1.000000e+00
0000078a  b7eee02b          vcvt.f32.f64 s4, d16
0000078e  20ee030a          vmul.f32   s0, s0, s6
00000792  32ee011a          vadd.f32   s2, s4, s2
00000796  20ee010a          vmul.f32   s0, s0, s2
0000079a  90ed001a          vldr       s2, [r0]
0000079e  04f61400          addw       r0, r4, #0x814
000007a2  80ee010a          vdiv.f32   s0, s0, s2
000007a6  b7ee001a          vmov.f32   s2, #1.000000e+00
000007aa  30ee010a          vadd.f32   s0, s0, s2
000007ae  80ed000a          vstr       s0, [r0]
000007b2  701c              adds       r0, r6, #1
000007b4  e668              ldr        r6, [r4, #0xc]
000007b6  a061              str        r0, [r4, #0x18]
000007b8  012e              cmp        r6, #1
000007ba  1fdb              blt        #0x7fc
000007bc  04f61401          addw       r1, r4, #0x814
000007c0  04f61800          addw       r0, r4, #0x818
000007c4  04f11c02          add.w      r2, r4, #0x1c
000007c8  3346              mov        r3, r6
000007ca  91ed000a          vldr       s0, [r1]
000007ce  92ed001a          vldr       s2, [r2]
000007d2  013b              subs       r3, #1
000007d4  21ee000a          vmul.f32   s0, s2, s0
000007d8  80ed001a          vstr       s2, [r0]
000007dc  30ee082a          vadd.f32   s4, s0, s16
000007e0  a2ec012a          vstmia     r2!, {s4}
000007e4  91ed000a          vldr       s0, [r1]
000007e8  20ee021a          vmul.f32   s2, s0, s4
000007ec  90ed002a          vldr       s4, [r0]
000007f0  32ee418a          vsub.f32   s16, s4, s2
000007f4  85ed008a          vstr       s16, [r5]
000007f8  e9d1              bne        #0x7ce
000007fa  00e0              b          #0x7fe
000007fc  0026              movs       r6, #0
000007fe  2569              ldr        r5, [r4, #0x10]
00000800  c5f1ff01          rsb.w      r1, r5, #0xff
00000804  a1fb0902          umull      r0, r2, r1, sb
00000808  01fb0822          mla        r2, r1, r8, r2
0000080c  c917              asrs       r1, r1, #0x1f
0000080e  01fb0921          mla        r1, r1, sb, r2
00000812  00f06ae9          blx        #0xae8
00000816  00ee105a          vmov       s0, r5
0000081a  8aed008a          vstr       s16, [sl]
0000081e  01ee100a          vmov       s2, r0
00000822  6661              str        r6, [r4, #0x14]
00000824  b8eec00a          vcvt.f32.s32 s0, s0
00000828  28ee000a          vmul.f32   s0, s16, s0
0000082c  30ee010a          vadd.f32   s0, s0, s2
00000830  9fed081a          vldr       s2, [pc, #0x20]
00000834  20ee010a          vmul.f32   s0, s0, s2
00000838  bdeec00a          vcvt.s32.f32 s0, s0
0000083c  10ee100a          vmov       r0, s0
00000840  c117              asrs       r1, r0, #0x1f
00000842  bdec048b          vpop       {d8, d9}
00000846  bde80007          pop.w      {r8, sb, sl}
0000084a  f0bd              pop        {r4, r5, r6, r7, pc}
0000084c  0ad7              bvc        #0x864
0000084e  233c              subs       r4, #0x23
00000850  8180              strh       r1, [r0, #4]
00000852  80bb              cbnz       r0, #0x8b6
00000854  8180              strh       r1, [r0, #4]
00000856  803b              subs       r3, #0x80
