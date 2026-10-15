# APK member: lib/armeabi-v7a/libnbandbooster.so
# Library SHA-256: 785733136117fa69ab2b7908597ad4dc045b883de2775b3018ca8d1de04333cc
# Function: Java_sofeh_audio_NBandBooster_process; address=0x634; bytes=328
# Decoder mode: thumb; decoded_bytes=328
00000634  52ea0300          orrs.w     r0, r2, r3
00000638  02bf              ittt       eq
0000063a  0020              moveq      r0, #0
0000063c  0021              moveq      r1, #0
0000063e  7047              bxeq       lr
00000640  f0b5              push       {r4, r5, r6, r7, lr}
00000642  03af              add        r7, sp, #0xc
00000644  4df804bd          str        fp, [sp, #-0x4]!
00000648  2ded068b          vpush      {d8, d9, d10}
0000064c  d7e90265          ldrd       r6, r5, [r7, #8]
00000650  1446              mov        r4, r2
00000652  2946              mov        r1, r5
00000654  3046              mov        r0, r6
00000656  00f0dce8          blx        #0x810
0000065a  04ee100a          vmov       s8, r0
0000065e  94ed011a          vldr       s2, [r4, #4]
00000662  94ed065a          vldr       s10, [r4, #0x18]
00000666  34ee416a          vsub.f32   s12, s8, s2
0000066a  94ed000a          vldr       s0, [r4]
0000066e  34ee454a          vsub.f32   s8, s8, s10
00000672  9fed417a          vldr       s14, [pc, #0x104]
00000676  d4ed050a          vldr       s1, [r4, #0x14]
0000067a  31ee071a          vadd.f32   s2, s2, s14
0000067e  94ed022a          vldr       s4, [r4, #8]
00000682  35ee075a          vadd.f32   s10, s10, s14
00000686  94ed033a          vldr       s6, [r4, #0xc]
0000068a  d4e91401          ldrd       r0, r1, [r4, #0x50]
0000068e  20ee066a          vmul.f32   s12, s0, s12
00000692  20ee844a          vmul.f32   s8, s1, s8
00000696  31ee061a          vadd.f32   s2, s2, s12
0000069a  35ee044a          vadd.f32   s8, s10, s8
0000069e  94ed075a          vldr       s10, [r4, #0x1c]
000006a2  31ee426a          vsub.f32   s12, s2, s4
000006a6  84ed011a          vstr       s2, [r4, #4]
000006aa  34ee457a          vsub.f32   s14, s8, s10
000006ae  94ed0a1a          vldr       s2, [r4, #0x28]
000006b2  84ed064a          vstr       s8, [r4, #0x18]
000006b6  26ee006a          vmul.f32   s12, s12, s0
000006ba  27ee207a          vmul.f32   s14, s14, s1
000006be  36ee022a          vadd.f32   s4, s12, s4
000006c2  94ed086a          vldr       s12, [r4, #0x20]
000006c6  37ee055a          vadd.f32   s10, s14, s10
000006ca  32ee437a          vsub.f32   s14, s4, s6
000006ce  84ed022a          vstr       s4, [r4, #8]
000006d2  75ee461a          vsub.f32   s3, s10, s12
000006d6  84ed075a          vstr       s10, [r4, #0x1c]
000006da  27ee007a          vmul.f32   s14, s14, s0
000006de  61eea01a          vmul.f32   s3, s3, s1
000006e2  37ee033a          vadd.f32   s6, s14, s6
000006e6  94ed047a          vldr       s14, [r4, #0x10]
000006ea  31ee866a          vadd.f32   s12, s3, s12
000006ee  d4ed091a          vldr       s3, [r4, #0x24]
000006f2  73ee472a          vsub.f32   s5, s6, s14
000006f6  84ed033a          vstr       s6, [r4, #0xc]
000006fa  76ee613a          vsub.f32   s7, s12, s3
000006fe  84ed086a          vstr       s12, [r4, #0x20]
00000702  22ee800a          vmul.f32   s0, s5, s0
00000706  63eea00a          vmul.f32   s1, s7, s1
0000070a  30ee070a          vadd.f32   s0, s0, s14
0000070e  30eea18a          vadd.f32   s16, s1, s3
00000712  21ee009a          vmul.f32   s18, s2, s0
00000716  84ed040a          vstr       s0, [r4, #0x10]
0000071a  38ee400a          vsub.f32   s0, s16, s0
0000071e  94ed0b1a          vldr       s2, [r4, #0x2c]
00000722  84ed098a          vstr       s16, [r4, #0x24]
00000726  84ed0d9a          vstr       s18, [r4, #0x34]
0000072a  20ee01aa          vmul.f32   s20, s0, s2
0000072e  00f070e8          blx        #0x810
00000732  00ee100a          vmov       s0, r0
00000736  94ed0c1a          vldr       s2, [r4, #0x30]
0000073a  3aee092a          vadd.f32   s4, s20, s18
0000073e  04f14001          add.w      r1, r4, #0x40
00000742  30ee480a          vsub.f32   s0, s0, s16
00000746  0822              movs       r2, #8
00000748  61f9c20a          vld1.64    {d16, d17}, [r1], r2
0000074c  84ed0eaa          vstr       s20, [r4, #0x38]
00000750  c4e91065          strd       r6, r5, [r4, #0x40]
00000754  41f9cf0a          vst1.64    {d16, d17}, [r1]
00000758  21ee000a          vmul.f32   s0, s2, s0
0000075c  32ee001a          vadd.f32   s2, s4, s0
00000760  84ed0f0a          vstr       s0, [r4, #0x3c]
00000764  11ee100a          vmov       r0, s2
00000768  00f010e8          blx        #0x78c
0000076c  bdec068b          vpop       {d8, d9, d10}
00000770  5df804bb          ldr        fp, [sp], #4
00000774  f0bd              pop        {r4, r5, r6, r7, pc}
00000776  00bf              nop        
00000778  0000              movs       r0, r0
0000077a  802f              cmp        r7, #0x80
