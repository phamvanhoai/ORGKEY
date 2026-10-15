# APK member: lib/armeabi-v7a/libnequalizer.so
# Library SHA-256: 43a844f1021b0ecaefd1ef5590ed454b77eb391a5a623bbafb0e7fc971f4108a
# Function: Java_sofeh_audio_NEqualizer_processRight; address=0xecc; bytes=1264
# Decoder mode: thumb; decoded_bytes=1264
00000ecc  52ea0300          orrs.w     r0, r2, r3
00000ed0  02bf              ittt       eq
00000ed2  0020              moveq      r0, #0
00000ed4  0021              moveq      r1, #0
00000ed6  7047              bxeq       lr
00000ed8  f0b5              push       {r4, r5, r6, r7, lr}
00000eda  03af              add        r7, sp, #0xc
00000edc  2de9000f          push.w     {r8, sb, sl, fp}
00000ee0  81b0              sub        sp, #4
00000ee2  2ded108b          vpush      {d8, d9, d10, d11, d12, d13, d14, d15}
00000ee6  86b0              sub        sp, #0x18
00000ee8  d7e90201          ldrd       r0, r1, [r7, #8]
00000eec  1446              mov        r4, r2
00000eee  00f028ed          blx        #0x1940
00000ef2  94ed591a          vldr       s2, [r4, #0x164]
00000ef6  08ee100a          vmov       s16, r0
00000efa  94ed580a          vldr       s0, [r4, #0x160]
00000efe  0546              mov        r5, r0
00000f00  38ee414a          vsub.f32   s8, s16, s2
00000f04  9fedfc9a          vldr       s18, [pc, #0x3f0]
00000f08  94ed5a2a          vldr       s4, [r4, #0x168]
00000f0c  31ee091a          vadd.f32   s2, s2, s18
00000f10  94ed5b3a          vldr       s6, [r4, #0x16c]
00000f14  d4ed620a          vldr       s1, [r4, #0x188]
00000f18  94ed5c6a          vldr       s12, [r4, #0x170]
00000f1c  d4f87411          ldr.w      r1, [r4, #0x174]
00000f20  94ed68da          vldr       s26, [r4, #0x1a0]
00000f24  24ee004a          vmul.f32   s8, s8, s0
00000f28  d4ed728a          vldr       s17, [r4, #0x1c8]
00000f2c  d4ed7caa          vldr       s21, [r4, #0x1f0]
00000f30  d4f88401          ldr.w      r0, [r4, #0x184]
00000f34  d4ed86da          vldr       s27, [r4, #0x218]
00000f38  94ed90ba          vldr       s22, [r4, #0x240]
00000f3c  0128              cmp        r0, #1
00000f3e  84ed69da          vstr       s26, [r4, #0x1a4]
00000f42  c4ed738a          vstr       s17, [r4, #0x1cc]
00000f46  31ee044a          vadd.f32   s8, s2, s8
00000f4a  c4ed7daa          vstr       s21, [r4, #0x1f4]
00000f4e  d4f8fc61          ldr.w      r6, [r4, #0x1fc]
00000f52  c4ed87da          vstr       s27, [r4, #0x21c]
00000f56  d4ed88ca          vldr       s25, [r4, #0x220]
00000f5a  d4f824a2          ldr.w      sl, [r4, #0x224]
00000f5e  84ed91ba          vstr       s22, [r4, #0x244]
00000f62  34ee421a          vsub.f32   s2, s8, s4
00000f66  84ed594a          vstr       s8, [r4, #0x164]
00000f6a  21ee001a          vmul.f32   s2, s2, s0
00000f6e  31ee025a          vadd.f32   s10, s2, s4
00000f72  35ee431a          vsub.f32   s2, s10, s6
00000f76  84ed5a5a          vstr       s10, [r4, #0x168]
00000f7a  21ee001a          vmul.f32   s2, s2, s0
00000f7e  31ee033a          vadd.f32   s6, s2, s6
00000f82  94ed631a          vldr       s2, [r4, #0x18c]
00000f86  38ee412a          vsub.f32   s4, s16, s2
00000f8a  31ee091a          vadd.f32   s2, s2, s18
00000f8e  33ee467a          vsub.f32   s14, s6, s12
00000f92  84ed5b3a          vstr       s6, [r4, #0x16c]
00000f96  94ed643a          vldr       s6, [r4, #0x190]
00000f9a  62ee201a          vmul.f32   s3, s4, s1
00000f9e  94ed5e2a          vldr       s4, [r4, #0x178]
00000fa2  c4e95d51          strd       r5, r1, [r4, #0x174]
00000fa6  d4f89c11          ldr.w      r1, [r4, #0x19c]
00000faa  84ed5f2a          vstr       s4, [r4, #0x17c]
00000fae  27ee007a          vmul.f32   s14, s14, s0
00000fb2  c4e96751          strd       r5, r1, [r4, #0x19c]
00000fb6  d4f8ac11          ldr.w      r1, [r4, #0x1ac]
00000fba  71ee211a          vadd.f32   s3, s2, s3
00000fbe  0391              str        r1, [sp, #0xc]
00000fc0  d4f8c411          ldr.w      r1, [r4, #0x1c4]
00000fc4  94ed600a          vldr       s0, [r4, #0x180]
00000fc8  c4e97151          strd       r5, r1, [r4, #0x1c4]
00000fcc  37ee061a          vadd.f32   s2, s14, s12
00000fd0  d4f8d411          ldr.w      r1, [r4, #0x1d4]
00000fd4  0191              str        r1, [sp, #4]
00000fd6  31eec34a          vsub.f32   s8, s3, s6
00000fda  d4f8ec11          ldr.w      r1, [r4, #0x1ec]
00000fde  c4ed631a          vstr       s3, [r4, #0x18c]
00000fe2  c4e97b51          strd       r5, r1, [r4, #0x1ec]
00000fe6  d4f81412          ldr.w      r1, [r4, #0x214]
00000fea  84ed5c1a          vstr       s2, [r4, #0x170]
00000fee  32ee412a          vsub.f32   s4, s4, s2
00000ff2  24ee204a          vmul.f32   s8, s8, s1
00000ff6  c4e98551          strd       r5, r1, [r4, #0x214]
00000ffa  d4f83c12          ldr.w      r1, [r4, #0x23c]
00000ffe  34ee033a          vadd.f32   s6, s8, s6
00001002  94ed654a          vldr       s8, [r4, #0x194]
00001006  84ed643a          vstr       s6, [r4, #0x190]
0000100a  33ee443a          vsub.f32   s6, s6, s8
0000100e  23ee203a          vmul.f32   s6, s6, s1
00001012  33ee043a          vadd.f32   s6, s6, s8
00001016  94ed664a          vldr       s8, [r4, #0x198]
0000101a  84ed653a          vstr       s6, [r4, #0x194]
0000101e  33ee443a          vsub.f32   s6, s6, s8
00001022  23ee203a          vmul.f32   s6, s6, s1
00001026  33ee04ca          vadd.f32   s24, s6, s8
0000102a  94ed6d4a          vldr       s8, [r4, #0x1b4]
0000102e  94ed6a3a          vldr       s6, [r4, #0x1a8]
00001032  38ee445a          vsub.f32   s10, s16, s8
00001036  8ded043a          vstr       s6, [sp, #0x10]
0000103a  34ee094a          vadd.f32   s8, s8, s18
0000103e  94ed6c3a          vldr       s6, [r4, #0x1b0]
00001042  84ed66ca          vstr       s24, [r4, #0x198]
00001046  25ee035a          vmul.f32   s10, s10, s6
0000104a  34ee054a          vadd.f32   s8, s8, s10
0000104e  94ed6e5a          vldr       s10, [r4, #0x1b8]
00001052  84ed6d4a          vstr       s8, [r4, #0x1b4]
00001056  34ee454a          vsub.f32   s8, s8, s10
0000105a  24ee034a          vmul.f32   s8, s8, s6
0000105e  34ee054a          vadd.f32   s8, s8, s10
00001062  94ed6f5a          vldr       s10, [r4, #0x1bc]
00001066  84ed6e4a          vstr       s8, [r4, #0x1b8]
0000106a  34ee454a          vsub.f32   s8, s8, s10
0000106e  24ee034a          vmul.f32   s8, s8, s6
00001072  34ee054a          vadd.f32   s8, s8, s10
00001076  94ed705a          vldr       s10, [r4, #0x1c0]
0000107a  84ed6f4a          vstr       s8, [r4, #0x1bc]
0000107e  34ee454a          vsub.f32   s8, s8, s10
00001082  24ee033a          vmul.f32   s6, s8, s6
00001086  94ed774a          vldr       s8, [r4, #0x1dc]
0000108a  33ee05aa          vadd.f32   s20, s6, s10
0000108e  94ed743a          vldr       s6, [r4, #0x1d0]
00001092  38ee445a          vsub.f32   s10, s16, s8
00001096  8ded053a          vstr       s6, [sp, #0x14]
0000109a  34ee094a          vadd.f32   s8, s8, s18
0000109e  94ed763a          vldr       s6, [r4, #0x1d8]
000010a2  84ed70aa          vstr       s20, [r4, #0x1c0]
000010a6  25ee035a          vmul.f32   s10, s10, s6
000010aa  34ee054a          vadd.f32   s8, s8, s10
000010ae  94ed785a          vldr       s10, [r4, #0x1e0]
000010b2  84ed774a          vstr       s8, [r4, #0x1dc]
000010b6  34ee454a          vsub.f32   s8, s8, s10
000010ba  24ee034a          vmul.f32   s8, s8, s6
000010be  34ee054a          vadd.f32   s8, s8, s10
000010c2  94ed795a          vldr       s10, [r4, #0x1e4]
000010c6  84ed784a          vstr       s8, [r4, #0x1e0]
000010ca  34ee454a          vsub.f32   s8, s8, s10
000010ce  24ee034a          vmul.f32   s8, s8, s6
000010d2  34ee054a          vadd.f32   s8, s8, s10
000010d6  94ed7a5a          vldr       s10, [r4, #0x1e8]
000010da  84ed794a          vstr       s8, [r4, #0x1e4]
000010de  34ee454a          vsub.f32   s8, s8, s10
000010e2  24ee033a          vmul.f32   s6, s8, s6
000010e6  94ed814a          vldr       s8, [r4, #0x204]
000010ea  33ee05fa          vadd.f32   s30, s6, s10
000010ee  94ed7e3a          vldr       s6, [r4, #0x1f8]
000010f2  38ee445a          vsub.f32   s10, s16, s8
000010f6  8ded023a          vstr       s6, [sp, #8]
000010fa  34ee094a          vadd.f32   s8, s8, s18
000010fe  94ed803a          vldr       s6, [r4, #0x200]
00001102  84ed7afa          vstr       s30, [r4, #0x1e8]
00001106  25ee035a          vmul.f32   s10, s10, s6
0000110a  34ee054a          vadd.f32   s8, s8, s10
0000110e  94ed825a          vldr       s10, [r4, #0x208]
00001112  84ed814a          vstr       s8, [r4, #0x204]
00001116  34ee454a          vsub.f32   s8, s8, s10
0000111a  24ee034a          vmul.f32   s8, s8, s6
0000111e  34ee054a          vadd.f32   s8, s8, s10
00001122  94ed835a          vldr       s10, [r4, #0x20c]
00001126  84ed824a          vstr       s8, [r4, #0x208]
0000112a  34ee454a          vsub.f32   s8, s8, s10
0000112e  24ee034a          vmul.f32   s8, s8, s6
00001132  34ee054a          vadd.f32   s8, s8, s10
00001136  94ed845a          vldr       s10, [r4, #0x210]
0000113a  84ed834a          vstr       s8, [r4, #0x20c]
0000113e  34ee454a          vsub.f32   s8, s8, s10
00001142  24ee033a          vmul.f32   s6, s8, s6
00001146  94ed8b4a          vldr       s8, [r4, #0x22c]
0000114a  73ee05ba          vadd.f32   s23, s6, s10
0000114e  94ed8a3a          vldr       s6, [r4, #0x228]
00001152  38ee445a          vsub.f32   s10, s16, s8
00001156  34ee094a          vadd.f32   s8, s8, s18
0000115a  c4ed84ba          vstr       s23, [r4, #0x210]
0000115e  25ee035a          vmul.f32   s10, s10, s6
00001162  34ee054a          vadd.f32   s8, s8, s10
00001166  94ed8c5a          vldr       s10, [r4, #0x230]
0000116a  84ed8b4a          vstr       s8, [r4, #0x22c]
0000116e  34ee454a          vsub.f32   s8, s8, s10
00001172  24ee034a          vmul.f32   s8, s8, s6
00001176  34ee054a          vadd.f32   s8, s8, s10
0000117a  94ed8d5a          vldr       s10, [r4, #0x234]
0000117e  84ed8c4a          vstr       s8, [r4, #0x230]
00001182  34ee454a          vsub.f32   s8, s8, s10
00001186  24ee034a          vmul.f32   s8, s8, s6
0000118a  34ee054a          vadd.f32   s8, s8, s10
0000118e  94ed8e5a          vldr       s10, [r4, #0x238]
00001192  84ed8d4a          vstr       s8, [r4, #0x234]
00001196  34ee454a          vsub.f32   s8, s8, s10
0000119a  24ee033a          vmul.f32   s6, s8, s6
0000119e  73ee05ea          vadd.f32   s29, s6, s10
000011a2  c4ed8eea          vstr       s29, [r4, #0x238]
000011a6  08bf              it         eq
000011a8  b0ee421a          vmoveq.f32 s2, s4
000011ac  94ed953a          vldr       s6, [r4, #0x254]
000011b0  21ee000a          vmul.f32   s0, s2, s0
000011b4  94ed942a          vldr       s4, [r4, #0x250]
000011b8  38ee434a          vsub.f32   s8, s16, s6
000011bc  c4e98f51          strd       r5, r1, [r4, #0x23c]
000011c0  33ee093a          vadd.f32   s6, s6, s18
000011c4  d4f86412          ldr.w      r1, [r4, #0x264]
000011c8  d4ed9a9a          vldr       s19, [r4, #0x268]
000011cc  d4ed92fa          vldr       s31, [r4, #0x248]
000011d0  d4f84cb2          ldr.w      fp, [r4, #0x24c]
000011d4  10ee100a          vmov       r0, s0
000011d8  c4ed9b9a          vstr       s19, [r4, #0x26c]
000011dc  c4e99951          strd       r5, r1, [r4, #0x264]
000011e0  24ee024a          vmul.f32   s8, s8, s4
000011e4  33ee043a          vadd.f32   s6, s6, s8
000011e8  94ed964a          vldr       s8, [r4, #0x258]
000011ec  84ed953a          vstr       s6, [r4, #0x254]
000011f0  33ee443a          vsub.f32   s6, s6, s8
000011f4  23ee023a          vmul.f32   s6, s6, s4
000011f8  33ee043a          vadd.f32   s6, s6, s8
000011fc  94ed974a          vldr       s8, [r4, #0x25c]
00001200  84ed963a          vstr       s6, [r4, #0x258]
00001204  33ee443a          vsub.f32   s6, s6, s8
00001208  23ee023a          vmul.f32   s6, s6, s4
0000120c  33ee043a          vadd.f32   s6, s6, s8
00001210  94ed984a          vldr       s8, [r4, #0x260]
00001214  84ed973a          vstr       s6, [r4, #0x25c]
00001218  33ee443a          vsub.f32   s6, s6, s8
0000121c  23ee022a          vmul.f32   s4, s6, s4
00001220  32ee04ea          vadd.f32   s28, s4, s8
00001224  84ed98ea          vstr       s28, [r4, #0x260]
00001228  00f048eb          blx        #0x18bc
0000122c  8146              mov        sb, r0
0000122e  0398              ldr        r0, [sp, #0xc]
00001230  3dee4c0a          vsub.f32   s0, s26, s24
00001234  8846              mov        r8, r1
00001236  0128              cmp        r0, #1
00001238  08bf              it         eq
0000123a  b0ee40ca          vmoveq.f32 s24, s0
0000123e  9ded040a          vldr       s0, [sp, #0x10]
00001242  2cee000a          vmul.f32   s0, s24, s0
00001246  10ee100a          vmov       r0, s0
0000124a  00f038eb          blx        #0x18bc
0000124e  94ed9f2a          vldr       s4, [r4, #0x27c]
00001252  94ed9e1a          vldr       s2, [r4, #0x278]
00001256  38ee423a          vsub.f32   s6, s16, s4
0000125a  019a              ldr        r2, [sp, #4]
0000125c  32ee092a          vadd.f32   s4, s4, s18
00001260  94ed9c0a          vldr       s0, [r4, #0x270]
00001264  012a              cmp        r2, #1
00001266  23ee013a          vmul.f32   s6, s6, s2
0000126a  32ee032a          vadd.f32   s4, s4, s6
0000126e  38eeca3a          vsub.f32   s6, s17, s20
00001272  08bf              it         eq
00001274  b0ee43aa          vmoveq.f32 s20, s6
00001278  94eda03a          vldr       s6, [r4, #0x280]
0000127c  012e              cmp        r6, #1
0000127e  9ded054a          vldr       s8, [sp, #0x14]
00001282  d4f87432          ldr.w      r3, [r4, #0x274]
00001286  2aee044a          vmul.f32   s8, s20, s8
0000128a  84ed9f2a          vstr       s4, [r4, #0x27c]
0000128e  32ee432a          vsub.f32   s4, s4, s6
00001292  14ee102a          vmov       r2, s8
00001296  22ee012a          vmul.f32   s4, s4, s2
0000129a  32ee032a          vadd.f32   s4, s4, s6
0000129e  94eda13a          vldr       s6, [r4, #0x284]
000012a2  84eda02a          vstr       s4, [r4, #0x280]
000012a6  32ee432a          vsub.f32   s4, s4, s6
000012aa  22ee012a          vmul.f32   s4, s4, s2
000012ae  32ee032a          vadd.f32   s4, s4, s6
000012b2  94eda23a          vldr       s6, [r4, #0x288]
000012b6  84eda12a          vstr       s4, [r4, #0x284]
000012ba  32ee432a          vsub.f32   s4, s4, s6
000012be  22ee011a          vmul.f32   s2, s4, s2
000012c2  3aeecf2a          vsub.f32   s4, s21, s30
000012c6  08bf              it         eq
000012c8  b0ee42fa          vmoveq.f32 s30, s4
000012cc  3deeeb2a          vsub.f32   s4, s27, s23
000012d0  baf1010f          cmp.w      sl, #1
000012d4  08bf              it         eq
000012d6  f0ee42ba          vmoveq.f32 s23, s4
000012da  9ded022a          vldr       s4, [sp, #8]
000012de  94eda44a          vldr       s8, [r4, #0x290]
000012e2  bbf1010f          cmp.w      fp, #1
000012e6  2fee022a          vmul.f32   s4, s30, s4
000012ea  2beeac5a          vmul.f32   s10, s23, s25
000012ee  31ee031a          vadd.f32   s2, s2, s6
000012f2  00f003b8          b.w        #0x12fc
000012f6  00bf              nop        
000012f8  0000              movs       r0, r0
000012fa  802f              cmp        r7, #0x80
000012fc  3bee6e3a          vsub.f32   s6, s22, s29
00001300  15ee10ba          vmov       fp, s10
00001304  84eda21a          vstr       s2, [r4, #0x288]
00001308  08bf              it         eq
0000130a  f0ee43ea          vmoveq.f32 s29, s6
0000130e  d4f88c62          ldr.w      r6, [r4, #0x28c]
00001312  39eece3a          vsub.f32   s6, s19, s28
00001316  84eda54a          vstr       s8, [r4, #0x294]
0000131a  012b              cmp        r3, #1
0000131c  08bf              it         eq
0000131e  b0ee43ea          vmoveq.f32 s28, s6
00001322  d4f89c32          ldr.w      r3, [r4, #0x29c]
00001326  34ee413a          vsub.f32   s6, s8, s2
0000132a  c4e9a356          strd       r5, r6, [r4, #0x28c]
0000132e  012b              cmp        r3, #1
00001330  08bf              it         eq
00001332  b0ee431a          vmoveq.f32 s2, s6
00001336  2eeeaf3a          vmul.f32   s6, s29, s31
0000133a  2eee000a          vmul.f32   s0, s28, s0
0000133e  12ee105a          vmov       r5, s4
00001342  94eda62a          vldr       s4, [r4, #0x298]
00001346  10eb0904          adds.w     r4, r0, sb
0000134a  1046              mov        r0, r2
0000134c  21ee028a          vmul.f32   s16, s2, s4
00001350  41eb0806          adc.w      r6, r1, r8
00001354  13ee10aa          vmov       sl, s6
00001358  10ee109a          vmov       sb, s0
0000135c  00f0aeea          blx        #0x18bc
00001360  2418              adds       r4, r4, r0
00001362  2846              mov        r0, r5
00001364  18ee108a          vmov       r8, s16
00001368  4e41              adcs       r6, r1
0000136a  00f0a8ea          blx        #0x18bc
0000136e  2418              adds       r4, r4, r0
00001370  5846              mov        r0, fp
00001372  46eb0105          adc.w      r5, r6, r1
00001376  00f0a2ea          blx        #0x18bc
0000137a  2418              adds       r4, r4, r0
0000137c  5046              mov        r0, sl
0000137e  4d41              adcs       r5, r1
00001380  00f09cea          blx        #0x18bc
00001384  2418              adds       r4, r4, r0
00001386  4846              mov        r0, sb
00001388  4d41              adcs       r5, r1
0000138a  00f098ea          blx        #0x18bc
0000138e  2418              adds       r4, r4, r0
00001390  4046              mov        r0, r8
00001392  4d41              adcs       r5, r1
00001394  00f092ea          blx        #0x18bc
00001398  0019              adds       r0, r0, r4
0000139a  6941              adcs       r1, r5
0000139c  ca17              asrs       r2, r1, #0x1f
0000139e  10eb9270          adds.w     r0, r0, r2, lsr #30
000013a2  41f10001          adc        r1, r1, #0
000013a6  8008              lsrs       r0, r0, #2
000013a8  40ea8170          orr.w      r0, r0, r1, lsl #30
000013ac  8910              asrs       r1, r1, #2
000013ae  06b0              add        sp, #0x18
000013b0  bdec108b          vpop       {d8, d9, d10, d11, d12, d13, d14, d15}
000013b4  01b0              add        sp, #4
000013b6  bde8000f          pop.w      {r8, sb, sl, fp}
000013ba  f0bd              pop        {r4, r5, r6, r7, pc}
