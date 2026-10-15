# APK member: lib/armeabi-v7a/libnflanger.so
# Library SHA-256: 4d4fabc33a8e9d24417f86d86f0e51c250d4ce0cc77fc1056a4f23b59e289534
# Function: Java_sofeh_audio_NFlanger_processRight; address=0x858; bytes=240
# Decoder mode: thumb; decoded_bytes=240
00000858  52ea0300          orrs.w     r0, r2, r3
0000085c  02bf              ittt       eq
0000085e  0020              moveq      r0, #0
00000860  0021              moveq      r1, #0
00000862  7047              bxeq       lr
00000864  f0b5              push       {r4, r5, r6, r7, lr}
00000866  03af              add        r7, sp, #0xc
00000868  2de90007          push.w     {r8, sb, sl}
0000086c  2ded028b          vpush      {d8}
00000870  d7e90298          ldrd       sb, r8, [r7, #8]
00000874  1446              mov        r4, r2
00000876  4146              mov        r1, r8
00000878  4846              mov        r0, sb
0000087a  00f036e9          blx        #0xae8
0000087e  04f6240a          addw       sl, r4, #0x824
00000882  94ed001a          vldr       s2, [r4]
00000886  9fed2e0a          vldr       s0, [pc, #0xb8]
0000088a  9aed002a          vldr       s4, [sl]
0000088e  b8eec11a          vcvt.f32.s32 s2, s2
00000892  e668              ldr        r6, [r4, #0xc]
00000894  22ee000a          vmul.f32   s0, s4, s0
00000898  012e              cmp        r6, #1
0000089a  20ee010a          vmul.f32   s0, s0, s2
0000089e  01ee100a          vmov       s2, r0
000008a2  04f62c00          addw       r0, r4, #0x82c
000008a6  30ee018a          vadd.f32   s16, s0, s2
000008aa  80ed008a          vstr       s16, [r0]
000008ae  1fdb              blt        #0x8f0
000008b0  04f61402          addw       r2, r4, #0x814
000008b4  04f61801          addw       r1, r4, #0x818
000008b8  04f58363          add.w      r3, r4, #0x418
000008bc  3546              mov        r5, r6
000008be  92ed000a          vldr       s0, [r2]
000008c2  93ed001a          vldr       s2, [r3]
000008c6  013d              subs       r5, #1
000008c8  21ee000a          vmul.f32   s0, s2, s0
000008cc  81ed001a          vstr       s2, [r1]
000008d0  30ee082a          vadd.f32   s4, s0, s16
000008d4  a3ec012a          vstmia     r3!, {s4}
000008d8  92ed000a          vldr       s0, [r2]
000008dc  20ee021a          vmul.f32   s2, s0, s4
000008e0  91ed002a          vldr       s4, [r1]
000008e4  32ee418a          vsub.f32   s16, s4, s2
000008e8  80ed008a          vstr       s16, [r0]
000008ec  e9d1              bne        #0x8c2
000008ee  00e0              b          #0x8f2
000008f0  0026              movs       r6, #0
000008f2  2569              ldr        r5, [r4, #0x10]
000008f4  c5f1ff01          rsb.w      r1, r5, #0xff
000008f8  a1fb0902          umull      r0, r2, r1, sb
000008fc  01fb0822          mla        r2, r1, r8, r2
00000900  c917              asrs       r1, r1, #0x1f
00000902  01fb0921          mla        r1, r1, sb, r2
00000906  00f0f0e8          blx        #0xae8
0000090a  00ee105a          vmov       s0, r5
0000090e  8aed008a          vstr       s16, [sl]
00000912  01ee100a          vmov       s2, r0
00000916  6661              str        r6, [r4, #0x14]
00000918  b8eec00a          vcvt.f32.s32 s0, s0
0000091c  28ee000a          vmul.f32   s0, s16, s0
00000920  30ee010a          vadd.f32   s0, s0, s2
00000924  9fed071a          vldr       s2, [pc, #0x1c]
00000928  20ee010a          vmul.f32   s0, s0, s2
0000092c  bdeec00a          vcvt.s32.f32 s0, s0
00000930  10ee100a          vmov       r0, s0
00000934  c117              asrs       r1, r0, #0x1f
00000936  bdec028b          vpop       {d8}
0000093a  bde80007          pop.w      {r8, sb, sl}
0000093e  f0bd              pop        {r4, r5, r6, r7, pc}
00000940  0ad7              bvc        #0x958
00000942  233c              subs       r4, #0x23
00000944  8180              strh       r1, [r0, #4]
00000946  803b              subs       r3, #0x80
