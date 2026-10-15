# APK member: lib/armeabi-v7a/libnequalizer.so
# Library SHA-256: 43a844f1021b0ecaefd1ef5590ed454b77eb391a5a623bbafb0e7fc971f4108a
# Function: Java_sofeh_audio_NEqualizer_process; address=0x13bc; bytes=1266
# Decoder mode: thumb; decoded_bytes=1266
000013bc  52ea0300          orrs.w     r0, r2, r3
000013c0  04bf              itt        eq
000013c2  0020              moveq      r0, #0
000013c4  7047              bxeq       lr
000013c6  f0b5              push       {r4, r5, r6, r7, lr}
000013c8  03af              add        r7, sp, #0xc
000013ca  2de9000f          push.w     {r8, sb, sl, fp}
000013ce  81b0              sub        sp, #4
000013d0  2ded108b          vpush      {d8, d9, d10, d11, d12, d13, d14, d15}
000013d4  86b0              sub        sp, #0x18
000013d6  b868              ldr        r0, [r7, #8]
000013d8  1646              mov        r6, r2
000013da  92ed092a          vldr       s4, [r2, #0x24]
000013de  92ed081a          vldr       s2, [r2, #0x20]
000013e2  00ee100a          vmov       s0, r0
000013e6  92ed0a3a          vldr       s6, [r2, #0x28]
000013ea  92ed0b4a          vldr       s8, [r2, #0x2c]
000013ee  b8eec08a          vcvt.f32.s32 s16, s0
000013f2  9fedfd0a          vldr       s0, [pc, #0x3f4]
000013f6  d2ed121a          vldr       s3, [r2, #0x48]
000013fa  92ed0c7a          vldr       s14, [r2, #0x30]
000013fe  516b              ldr        r1, [r2, #0x34]
00001400  506c              ldr        r0, [r2, #0x44]
00001402  92ed22fa          vldr       s30, [r2, #0x88]
00001406  d2ed2caa          vldr       s21, [r2, #0xb0]
0000140a  0128              cmp        r0, #1
0000140c  38ee425a          vsub.f32   s10, s16, s4
00001410  d2ed36da          vldr       s27, [r2, #0xd8]
00001414  32ee002a          vadd.f32   s4, s4, s0
00001418  d2ed40fa          vldr       s31, [r2, #0x100]
0000141c  82ed0d8a          vstr       s16, [r2, #0x34]
00001420  82ed23fa          vstr       s30, [r2, #0x8c]
00001424  d2f894a0          ldr.w      sl, [r2, #0x94]
00001428  c2ed2daa          vstr       s21, [r2, #0xb4]
0000142c  d2f8bcb0          ldr.w      fp, [r2, #0xbc]
00001430  25ee015a          vmul.f32   s10, s10, s2
00001434  c2ed37da          vstr       s27, [r2, #0xdc]
00001438  d2f8e440          ldr.w      r4, [r2, #0xe4]
0000143c  c2ed41fa          vstr       s31, [r2, #0x104]
00001440  32ee055a          vadd.f32   s10, s4, s10
00001444  35ee432a          vsub.f32   s4, s10, s6
00001448  82ed095a          vstr       s10, [r2, #0x24]
0000144c  22ee012a          vmul.f32   s4, s4, s2
00001450  32ee036a          vadd.f32   s12, s4, s6
00001454  36ee442a          vsub.f32   s4, s12, s8
00001458  82ed0a6a          vstr       s12, [r2, #0x28]
0000145c  22ee012a          vmul.f32   s4, s4, s2
00001460  32ee044a          vadd.f32   s8, s4, s8
00001464  92ed132a          vldr       s4, [r2, #0x4c]
00001468  38ee423a          vsub.f32   s6, s16, s4
0000146c  32ee002a          vadd.f32   s4, s4, s0
00001470  74ee470a          vsub.f32   s1, s8, s14
00001474  82ed0b4a          vstr       s8, [r2, #0x2c]
00001478  92ed144a          vldr       s8, [r2, #0x50]
0000147c  63ee212a          vmul.f32   s5, s6, s3
00001480  92ed0e3a          vldr       s6, [r2, #0x38]
00001484  9163              str        r1, [r2, #0x38]
00001486  d16d              ldr        r1, [r2, #0x5c]
00001488  82ed0f3a          vstr       s6, [r2, #0x3c]
0000148c  82ed178a          vstr       s16, [r2, #0x5c]
00001490  60ee810a          vmul.f32   s1, s1, s2
00001494  92ed101a          vldr       s2, [r2, #0x40]
00001498  72ee222a          vadd.f32   s5, s4, s5
0000149c  30ee872a          vadd.f32   s4, s1, s14
000014a0  32eec45a          vsub.f32   s10, s5, s8
000014a4  c2ed132a          vstr       s5, [r2, #0x4c]
000014a8  82ed0c2a          vstr       s4, [r2, #0x30]
000014ac  33ee423a          vsub.f32   s6, s6, s4
000014b0  25ee215a          vmul.f32   s10, s10, s3
000014b4  35ee044a          vadd.f32   s8, s10, s8
000014b8  92ed155a          vldr       s10, [r2, #0x54]
000014bc  82ed144a          vstr       s8, [r2, #0x50]
000014c0  34ee454a          vsub.f32   s8, s8, s10
000014c4  24ee214a          vmul.f32   s8, s8, s3
000014c8  34ee054a          vadd.f32   s8, s8, s10
000014cc  92ed165a          vldr       s10, [r2, #0x58]
000014d0  82ed154a          vstr       s8, [r2, #0x54]
000014d4  34ee454a          vsub.f32   s8, s8, s10
000014d8  24ee214a          vmul.f32   s8, s8, s3
000014dc  34ee059a          vadd.f32   s18, s8, s10
000014e0  92ed1d5a          vldr       s10, [r2, #0x74]
000014e4  92ed184a          vldr       s8, [r2, #0x60]
000014e8  38ee456a          vsub.f32   s12, s16, s10
000014ec  1166              str        r1, [r2, #0x60]
000014ee  8ded044a          vstr       s8, [sp, #0x10]
000014f2  35ee005a          vadd.f32   s10, s10, s0
000014f6  82ed194a          vstr       s8, [r2, #0x64]
000014fa  92ed1a4a          vldr       s8, [r2, #0x68]
000014fe  d16e              ldr        r1, [r2, #0x6c]
00001500  8ded054a          vstr       s8, [sp, #0x14]
00001504  92ed1c4a          vldr       s8, [r2, #0x70]
00001508  0291              str        r1, [sp, #8]
0000150a  26ee046a          vmul.f32   s12, s12, s8
0000150e  d2f88410          ldr.w      r1, [r2, #0x84]
00001512  c2f88810          str.w      r1, [r2, #0x88]
00001516  d2f8ac10          ldr.w      r1, [r2, #0xac]
0000151a  c2f8b010          str.w      r1, [r2, #0xb0]
0000151e  d2f8d410          ldr.w      r1, [r2, #0xd4]
00001522  c2f8d810          str.w      r1, [r2, #0xd8]
00001526  82ed169a          vstr       s18, [r2, #0x58]
0000152a  35ee065a          vadd.f32   s10, s10, s12
0000152e  92ed1e6a          vldr       s12, [r2, #0x78]
00001532  82ed218a          vstr       s16, [r2, #0x84]
00001536  82ed2b8a          vstr       s16, [r2, #0xac]
0000153a  82ed358a          vstr       s16, [r2, #0xd4]
0000153e  d2f8fc10          ldr.w      r1, [r2, #0xfc]
00001542  82ed1d5a          vstr       s10, [r2, #0x74]
00001546  35ee465a          vsub.f32   s10, s10, s12
0000154a  25ee045a          vmul.f32   s10, s10, s8
0000154e  35ee065a          vadd.f32   s10, s10, s12
00001552  92ed1f6a          vldr       s12, [r2, #0x7c]
00001556  82ed1e5a          vstr       s10, [r2, #0x78]
0000155a  35ee465a          vsub.f32   s10, s10, s12
0000155e  25ee045a          vmul.f32   s10, s10, s8
00001562  35ee065a          vadd.f32   s10, s10, s12
00001566  92ed206a          vldr       s12, [r2, #0x80]
0000156a  82ed1f5a          vstr       s10, [r2, #0x7c]
0000156e  35ee465a          vsub.f32   s10, s10, s12
00001572  25ee044a          vmul.f32   s8, s10, s8
00001576  92ed275a          vldr       s10, [r2, #0x9c]
0000157a  34ee06aa          vadd.f32   s20, s8, s12
0000157e  92ed244a          vldr       s8, [r2, #0x90]
00001582  38ee456a          vsub.f32   s12, s16, s10
00001586  8ded034a          vstr       s8, [sp, #0xc]
0000158a  35ee005a          vadd.f32   s10, s10, s0
0000158e  92ed264a          vldr       s8, [r2, #0x98]
00001592  82ed20aa          vstr       s20, [r2, #0x80]
00001596  26ee046a          vmul.f32   s12, s12, s8
0000159a  35ee065a          vadd.f32   s10, s10, s12
0000159e  92ed286a          vldr       s12, [r2, #0xa0]
000015a2  82ed275a          vstr       s10, [r2, #0x9c]
000015a6  35ee465a          vsub.f32   s10, s10, s12
000015aa  25ee045a          vmul.f32   s10, s10, s8
000015ae  35ee065a          vadd.f32   s10, s10, s12
000015b2  92ed296a          vldr       s12, [r2, #0xa4]
000015b6  82ed285a          vstr       s10, [r2, #0xa0]
000015ba  35ee465a          vsub.f32   s10, s10, s12
000015be  25ee045a          vmul.f32   s10, s10, s8
000015c2  35ee065a          vadd.f32   s10, s10, s12
000015c6  92ed2a6a          vldr       s12, [r2, #0xa8]
000015ca  82ed295a          vstr       s10, [r2, #0xa4]
000015ce  35ee465a          vsub.f32   s10, s10, s12
000015d2  25ee044a          vmul.f32   s8, s10, s8
000015d6  92ed315a          vldr       s10, [r2, #0xc4]
000015da  34ee06ea          vadd.f32   s28, s8, s12
000015de  92ed2e4a          vldr       s8, [r2, #0xb8]
000015e2  38ee456a          vsub.f32   s12, s16, s10
000015e6  8ded014a          vstr       s8, [sp, #4]
000015ea  35ee005a          vadd.f32   s10, s10, s0
000015ee  92ed304a          vldr       s8, [r2, #0xc0]
000015f2  82ed2aea          vstr       s28, [r2, #0xa8]
000015f6  26ee046a          vmul.f32   s12, s12, s8
000015fa  35ee065a          vadd.f32   s10, s10, s12
000015fe  92ed326a          vldr       s12, [r2, #0xc8]
00001602  82ed315a          vstr       s10, [r2, #0xc4]
00001606  35ee465a          vsub.f32   s10, s10, s12
0000160a  25ee045a          vmul.f32   s10, s10, s8
0000160e  35ee065a          vadd.f32   s10, s10, s12
00001612  92ed336a          vldr       s12, [r2, #0xcc]
00001616  82ed325a          vstr       s10, [r2, #0xc8]
0000161a  35ee465a          vsub.f32   s10, s10, s12
0000161e  25ee045a          vmul.f32   s10, s10, s8
00001622  35ee065a          vadd.f32   s10, s10, s12
00001626  92ed346a          vldr       s12, [r2, #0xd0]
0000162a  82ed335a          vstr       s10, [r2, #0xcc]
0000162e  35ee465a          vsub.f32   s10, s10, s12
00001632  25ee044a          vmul.f32   s8, s10, s8
00001636  92ed3b5a          vldr       s10, [r2, #0xec]
0000163a  74ee069a          vadd.f32   s19, s8, s12
0000163e  92ed384a          vldr       s8, [r2, #0xe0]
00001642  38ee456a          vsub.f32   s12, s16, s10
00001646  8ded004a          vstr       s8, [sp]
0000164a  35ee005a          vadd.f32   s10, s10, s0
0000164e  92ed3a4a          vldr       s8, [r2, #0xe8]
00001652  c2ed349a          vstr       s19, [r2, #0xd0]
00001656  26ee046a          vmul.f32   s12, s12, s8
0000165a  35ee065a          vadd.f32   s10, s10, s12
0000165e  92ed3c6a          vldr       s12, [r2, #0xf0]
00001662  82ed3b5a          vstr       s10, [r2, #0xec]
00001666  35ee465a          vsub.f32   s10, s10, s12
0000166a  25ee045a          vmul.f32   s10, s10, s8
0000166e  35ee065a          vadd.f32   s10, s10, s12
00001672  92ed3d6a          vldr       s12, [r2, #0xf4]
00001676  82ed3c5a          vstr       s10, [r2, #0xf0]
0000167a  35ee465a          vsub.f32   s10, s10, s12
0000167e  25ee045a          vmul.f32   s10, s10, s8
00001682  35ee065a          vadd.f32   s10, s10, s12
00001686  92ed3e6a          vldr       s12, [r2, #0xf8]
0000168a  82ed3d5a          vstr       s10, [r2, #0xf4]
0000168e  35ee465a          vsub.f32   s10, s10, s12
00001692  25ee044a          vmul.f32   s8, s10, s8
00001696  74ee06ca          vadd.f32   s25, s8, s12
0000169a  c2ed3eca          vstr       s25, [r2, #0xf8]
0000169e  08bf              it         eq
000016a0  b0ee432a          vmoveq.f32 s4, s6
000016a4  92ed454a          vldr       s8, [r2, #0x114]
000016a8  22ee011a          vmul.f32   s2, s4, s2
000016ac  92ed443a          vldr       s6, [r2, #0x110]
000016b0  38ee445a          vsub.f32   s10, s16, s8
000016b4  d2ed4eba          vldr       s23, [r2, #0x138]
000016b8  34ee004a          vadd.f32   s8, s8, s0
000016bc  d2ed4a8a          vldr       s17, [r2, #0x128]
000016c0  c2f80011          str.w      r1, [r2, #0x100]
000016c4  d2f82411          ldr.w      r1, [r2, #0x124]
000016c8  82ed3f8a          vstr       s16, [r2, #0xfc]
000016cc  11ee100a          vmov       r0, s2
000016d0  92ed4f1a          vldr       s2, [r2, #0x13c]
000016d4  d2ed42ea          vldr       s29, [r2, #0x108]
000016d8  25ee035a          vmul.f32   s10, s10, s6
000016dc  d2f80c51          ldr.w      r5, [r2, #0x10c]
000016e0  31ee000a          vadd.f32   s0, s2, s0
000016e4  c2ed4b8a          vstr       s17, [r2, #0x12c]
000016e8  38ee411a          vsub.f32   s2, s16, s2
000016ec  c2f82811          str.w      r1, [r2, #0x128]
000016f0  82ed498a          vstr       s16, [r2, #0x124]
000016f4  92ed4cda          vldr       s26, [r2, #0x130]
000016f8  d2f83491          ldr.w      sb, [r2, #0x134]
000016fc  34ee054a          vadd.f32   s8, s8, s10
00001700  92ed465a          vldr       s10, [r2, #0x118]
00001704  21ee2b1a          vmul.f32   s2, s2, s23
00001708  82ed454a          vstr       s8, [r2, #0x114]
0000170c  34ee454a          vsub.f32   s8, s8, s10
00001710  30ee010a          vadd.f32   s0, s0, s2
00001714  92ed501a          vldr       s2, [r2, #0x140]
00001718  24ee034a          vmul.f32   s8, s8, s6
0000171c  82ed4f0a          vstr       s0, [r2, #0x13c]
00001720  30ee410a          vsub.f32   s0, s0, s2
00001724  34ee054a          vadd.f32   s8, s8, s10
00001728  92ed475a          vldr       s10, [r2, #0x11c]
0000172c  20ee2b0a          vmul.f32   s0, s0, s23
00001730  82ed464a          vstr       s8, [r2, #0x118]
00001734  34ee454a          vsub.f32   s8, s8, s10
00001738  30ee010a          vadd.f32   s0, s0, s2
0000173c  92ed511a          vldr       s2, [r2, #0x144]
00001740  24ee034a          vmul.f32   s8, s8, s6
00001744  82ed500a          vstr       s0, [r2, #0x140]
00001748  30ee410a          vsub.f32   s0, s0, s2
0000174c  34ee054a          vadd.f32   s8, s8, s10
00001750  92ed485a          vldr       s10, [r2, #0x120]
00001754  20ee2b0a          vmul.f32   s0, s0, s23
00001758  82ed474a          vstr       s8, [r2, #0x11c]
0000175c  34ee454a          vsub.f32   s8, s8, s10
00001760  30ee01ba          vadd.f32   s22, s0, s2
00001764  24ee033a          vmul.f32   s6, s8, s6
00001768  82ed51ba          vstr       s22, [r2, #0x144]
0000176c  33ee05ca          vadd.f32   s24, s6, s10
00001770  82ed48ca          vstr       s24, [r2, #0x120]
00001774  00f0a2e8          blx        #0x18bc
00001778  9ded040a          vldr       s0, [sp, #0x10]
0000177c  8046              mov        r8, r0
0000177e  0298              ldr        r0, [sp, #8]
00001780  30ee490a          vsub.f32   s0, s0, s18
00001784  0128              cmp        r0, #1
00001786  08bf              it         eq
00001788  b0ee409a          vmoveq.f32 s18, s0
0000178c  9ded050a          vldr       s0, [sp, #0x14]
00001790  29ee000a          vmul.f32   s0, s18, s0
00001794  10ee100a          vmov       r0, s0
00001798  00f090e8          blx        #0x18bc
0000179c  96ed520a          vldr       s0, [r6, #0x148]
000017a0  baf1010f          cmp.w      sl, #1
000017a4  3fee4a2a          vsub.f32   s4, s30, s20
000017a8  08bf              it         eq
000017aa  b0ee42aa          vmoveq.f32 s20, s4
000017ae  3bee401a          vsub.f32   s2, s22, s0
000017b2  bbf1010f          cmp.w      fp, #1
000017b6  3deee92a          vsub.f32   s4, s27, s19
000017ba  21ee2b1a          vmul.f32   s2, s2, s23
000017be  31ee000a          vadd.f32   s0, s2, s0
000017c2  3aeece1a          vsub.f32   s2, s21, s28
000017c6  08bf              it         eq
000017c8  b0ee41ea          vmoveq.f32 s28, s2
000017cc  012c              cmp        r4, #1
000017ce  08bf              it         eq
000017d0  f0ee429a          vmoveq.f32 s19, s4
000017d4  9ded033a          vldr       s6, [sp, #0xc]
000017d8  3feeec1a          vsub.f32   s2, s31, s25
000017dc  96ed542a          vldr       s4, [r6, #0x150]
000017e0  012d              cmp        r5, #1
000017e2  00f003b8          b.w        #0x17ec
000017e6  00bf              nop        
000017e8  0000              movs       r0, r0
000017ea  802f              cmp        r7, #0x80
000017ec  2aee033a          vmul.f32   s6, s20, s6
000017f0  86ed520a          vstr       s0, [r6, #0x148]
000017f4  08bf              it         eq
000017f6  f0ee41ca          vmoveq.f32 s25, s2
000017fa  d6f84c11          ldr.w      r1, [r6, #0x14c]
000017fe  38eecc1a          vsub.f32   s2, s17, s24
00001802  86ed552a          vstr       s4, [r6, #0x154]
00001806  b9f1010f          cmp.w      sb, #1
0000180a  08bf              it         eq
0000180c  b0ee41ca          vmoveq.f32 s24, s2
00001810  c6f85011          str.w      r1, [r6, #0x150]
00001814  d6f85c11          ldr.w      r1, [r6, #0x15c]
00001818  32ee402a          vsub.f32   s4, s4, s0
0000181c  9ded011a          vldr       s2, [sp, #4]
00001820  2ceeae4a          vmul.f32   s8, s25, s29
00001824  0129              cmp        r1, #1
00001826  13ee101a          vmov       r1, s6
0000182a  86ed538a          vstr       s16, [r6, #0x14c]
0000182e  08bf              it         eq
00001830  b0ee420a          vmoveq.f32 s0, s4
00001834  9ded002a          vldr       s4, [sp]
00001838  2eee011a          vmul.f32   s2, s28, s2
0000183c  96ed565a          vldr       s10, [r6, #0x158]
00001840  2cee0d3a          vmul.f32   s6, s24, s26
00001844  29ee822a          vmul.f32   s4, s19, s4
00001848  00eb0806          add.w      r6, r0, r8
0000184c  20ee050a          vmul.f32   s0, s0, s10
00001850  14ee10ba          vmov       fp, s8
00001854  11ee104a          vmov       r4, s2
00001858  13ee10aa          vmov       sl, s6
0000185c  12ee105a          vmov       r5, s4
00001860  10ee109a          vmov       sb, s0
00001864  0846              mov        r0, r1
00001866  00f02ae8          blx        #0x18bc
0000186a  0644              add        r6, r0
0000186c  2046              mov        r0, r4
0000186e  00f026e8          blx        #0x18bc
00001872  3418              adds       r4, r6, r0
00001874  2846              mov        r0, r5
00001876  00f022e8          blx        #0x18bc
0000187a  0444              add        r4, r0
0000187c  5846              mov        r0, fp
0000187e  00f01ee8          blx        #0x18bc
00001882  0444              add        r4, r0
00001884  5046              mov        r0, sl
00001886  00f01ae8          blx        #0x18bc
0000188a  0444              add        r4, r0
0000188c  4846              mov        r0, sb
0000188e  00f016e8          blx        #0x18bc
00001892  2044              add        r0, r4
00001894  01b2              sxth       r1, r0
00001896  c1f34171          ubfx       r1, r1, #0x1d, #2
0000189a  0844              add        r0, r1
0000189c  00b2              sxth       r0, r0
0000189e  8010              asrs       r0, r0, #2
000018a0  06b0              add        sp, #0x18
000018a2  bdec108b          vpop       {d8, d9, d10, d11, d12, d13, d14, d15}
000018a6  01b0              add        sp, #4
000018a8  bde8000f          pop.w      {r8, sb, sl, fp}
000018ac  f0bd              pop        {r4, r5, r6, r7, pc}
