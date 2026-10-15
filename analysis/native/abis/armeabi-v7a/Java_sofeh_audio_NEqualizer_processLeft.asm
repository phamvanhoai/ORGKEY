# APK member: lib/armeabi-v7a/libnequalizer.so
# Library SHA-256: 43a844f1021b0ecaefd1ef5590ed454b77eb391a5a623bbafb0e7fc971f4108a
# Function: Java_sofeh_audio_NEqualizer_processLeft; address=0x9e4; bytes=1256
# Decoder mode: thumb; decoded_bytes=1256
000009e4  52ea0300          orrs.w     r0, r2, r3
000009e8  02bf              ittt       eq
000009ea  0020              moveq      r0, #0
000009ec  0021              moveq      r1, #0
000009ee  7047              bxeq       lr
000009f0  f0b5              push       {r4, r5, r6, r7, lr}
000009f2  03af              add        r7, sp, #0xc
000009f4  2de9000f          push.w     {r8, sb, sl, fp}
000009f8  81b0              sub        sp, #4
000009fa  2ded108b          vpush      {d8, d9, d10, d11, d12, d13, d14, d15}
000009fe  86b0              sub        sp, #0x18
00000a00  d7e90201          ldrd       r0, r1, [r7, #8]
00000a04  1446              mov        r4, r2
00000a06  00f09cef          blx        #0x1940
00000a0a  94ed091a          vldr       s2, [r4, #0x24]
00000a0e  08ee100a          vmov       s16, r0
00000a12  94ed080a          vldr       s0, [r4, #0x20]
00000a16  0546              mov        r5, r0
00000a18  38ee414a          vsub.f32   s8, s16, s2
00000a1c  9fedfc9a          vldr       s18, [pc, #0x3f0]
00000a20  94ed0a2a          vldr       s4, [r4, #0x28]
00000a24  31ee091a          vadd.f32   s2, s2, s18
00000a28  94ed0b3a          vldr       s6, [r4, #0x2c]
00000a2c  d4ed120a          vldr       s1, [r4, #0x48]
00000a30  94ed0c6a          vldr       s12, [r4, #0x30]
00000a34  616b              ldr        r1, [r4, #0x34]
00000a36  94ed18da          vldr       s26, [r4, #0x60]
00000a3a  24ee004a          vmul.f32   s8, s8, s0
00000a3e  d4ed228a          vldr       s17, [r4, #0x88]
00000a42  d4ed2caa          vldr       s21, [r4, #0xb0]
00000a46  606c              ldr        r0, [r4, #0x44]
00000a48  d4ed36da          vldr       s27, [r4, #0xd8]
00000a4c  94ed40ba          vldr       s22, [r4, #0x100]
00000a50  0128              cmp        r0, #1
00000a52  84ed19da          vstr       s26, [r4, #0x64]
00000a56  c4ed238a          vstr       s17, [r4, #0x8c]
00000a5a  31ee044a          vadd.f32   s8, s2, s8
00000a5e  c4ed2daa          vstr       s21, [r4, #0xb4]
00000a62  d4f8bc60          ldr.w      r6, [r4, #0xbc]
00000a66  c4ed37da          vstr       s27, [r4, #0xdc]
00000a6a  d4ed38ca          vldr       s25, [r4, #0xe0]
00000a6e  d4f8e4a0          ldr.w      sl, [r4, #0xe4]
00000a72  84ed41ba          vstr       s22, [r4, #0x104]
00000a76  34ee421a          vsub.f32   s2, s8, s4
00000a7a  84ed094a          vstr       s8, [r4, #0x24]
00000a7e  21ee001a          vmul.f32   s2, s2, s0
00000a82  31ee025a          vadd.f32   s10, s2, s4
00000a86  35ee431a          vsub.f32   s2, s10, s6
00000a8a  84ed0a5a          vstr       s10, [r4, #0x28]
00000a8e  21ee001a          vmul.f32   s2, s2, s0
00000a92  31ee033a          vadd.f32   s6, s2, s6
00000a96  94ed131a          vldr       s2, [r4, #0x4c]
00000a9a  38ee412a          vsub.f32   s4, s16, s2
00000a9e  31ee091a          vadd.f32   s2, s2, s18
00000aa2  33ee467a          vsub.f32   s14, s6, s12
00000aa6  84ed0b3a          vstr       s6, [r4, #0x2c]
00000aaa  94ed143a          vldr       s6, [r4, #0x50]
00000aae  62ee201a          vmul.f32   s3, s4, s1
00000ab2  94ed0e2a          vldr       s4, [r4, #0x38]
00000ab6  c4e90d51          strd       r5, r1, [r4, #0x34]
00000aba  e16d              ldr        r1, [r4, #0x5c]
00000abc  84ed0f2a          vstr       s4, [r4, #0x3c]
00000ac0  27ee007a          vmul.f32   s14, s14, s0
00000ac4  c4e91751          strd       r5, r1, [r4, #0x5c]
00000ac8  e16e              ldr        r1, [r4, #0x6c]
00000aca  71ee211a          vadd.f32   s3, s2, s3
00000ace  0391              str        r1, [sp, #0xc]
00000ad0  d4f88410          ldr.w      r1, [r4, #0x84]
00000ad4  94ed100a          vldr       s0, [r4, #0x40]
00000ad8  c4e92151          strd       r5, r1, [r4, #0x84]
00000adc  37ee061a          vadd.f32   s2, s14, s12
00000ae0  d4f89410          ldr.w      r1, [r4, #0x94]
00000ae4  0191              str        r1, [sp, #4]
00000ae6  31eec34a          vsub.f32   s8, s3, s6
00000aea  d4f8ac10          ldr.w      r1, [r4, #0xac]
00000aee  c4ed131a          vstr       s3, [r4, #0x4c]
00000af2  c4e92b51          strd       r5, r1, [r4, #0xac]
00000af6  d4f8d410          ldr.w      r1, [r4, #0xd4]
00000afa  84ed0c1a          vstr       s2, [r4, #0x30]
00000afe  32ee412a          vsub.f32   s4, s4, s2
00000b02  24ee204a          vmul.f32   s8, s8, s1
00000b06  c4e93551          strd       r5, r1, [r4, #0xd4]
00000b0a  d4f8fc10          ldr.w      r1, [r4, #0xfc]
00000b0e  34ee033a          vadd.f32   s6, s8, s6
00000b12  94ed154a          vldr       s8, [r4, #0x54]
00000b16  84ed143a          vstr       s6, [r4, #0x50]
00000b1a  33ee443a          vsub.f32   s6, s6, s8
00000b1e  23ee203a          vmul.f32   s6, s6, s1
00000b22  33ee043a          vadd.f32   s6, s6, s8
00000b26  94ed164a          vldr       s8, [r4, #0x58]
00000b2a  84ed153a          vstr       s6, [r4, #0x54]
00000b2e  33ee443a          vsub.f32   s6, s6, s8
00000b32  23ee203a          vmul.f32   s6, s6, s1
00000b36  33ee04ca          vadd.f32   s24, s6, s8
00000b3a  94ed1d4a          vldr       s8, [r4, #0x74]
00000b3e  94ed1a3a          vldr       s6, [r4, #0x68]
00000b42  38ee445a          vsub.f32   s10, s16, s8
00000b46  8ded043a          vstr       s6, [sp, #0x10]
00000b4a  34ee094a          vadd.f32   s8, s8, s18
00000b4e  94ed1c3a          vldr       s6, [r4, #0x70]
00000b52  84ed16ca          vstr       s24, [r4, #0x58]
00000b56  25ee035a          vmul.f32   s10, s10, s6
00000b5a  34ee054a          vadd.f32   s8, s8, s10
00000b5e  94ed1e5a          vldr       s10, [r4, #0x78]
00000b62  84ed1d4a          vstr       s8, [r4, #0x74]
00000b66  34ee454a          vsub.f32   s8, s8, s10
00000b6a  24ee034a          vmul.f32   s8, s8, s6
00000b6e  34ee054a          vadd.f32   s8, s8, s10
00000b72  94ed1f5a          vldr       s10, [r4, #0x7c]
00000b76  84ed1e4a          vstr       s8, [r4, #0x78]
00000b7a  34ee454a          vsub.f32   s8, s8, s10
00000b7e  24ee034a          vmul.f32   s8, s8, s6
00000b82  34ee054a          vadd.f32   s8, s8, s10
00000b86  94ed205a          vldr       s10, [r4, #0x80]
00000b8a  84ed1f4a          vstr       s8, [r4, #0x7c]
00000b8e  34ee454a          vsub.f32   s8, s8, s10
00000b92  24ee033a          vmul.f32   s6, s8, s6
00000b96  94ed274a          vldr       s8, [r4, #0x9c]
00000b9a  33ee05aa          vadd.f32   s20, s6, s10
00000b9e  94ed243a          vldr       s6, [r4, #0x90]
00000ba2  38ee445a          vsub.f32   s10, s16, s8
00000ba6  8ded053a          vstr       s6, [sp, #0x14]
00000baa  34ee094a          vadd.f32   s8, s8, s18
00000bae  94ed263a          vldr       s6, [r4, #0x98]
00000bb2  84ed20aa          vstr       s20, [r4, #0x80]
00000bb6  25ee035a          vmul.f32   s10, s10, s6
00000bba  34ee054a          vadd.f32   s8, s8, s10
00000bbe  94ed285a          vldr       s10, [r4, #0xa0]
00000bc2  84ed274a          vstr       s8, [r4, #0x9c]
00000bc6  34ee454a          vsub.f32   s8, s8, s10
00000bca  24ee034a          vmul.f32   s8, s8, s6
00000bce  34ee054a          vadd.f32   s8, s8, s10
00000bd2  94ed295a          vldr       s10, [r4, #0xa4]
00000bd6  84ed284a          vstr       s8, [r4, #0xa0]
00000bda  34ee454a          vsub.f32   s8, s8, s10
00000bde  24ee034a          vmul.f32   s8, s8, s6
00000be2  34ee054a          vadd.f32   s8, s8, s10
00000be6  94ed2a5a          vldr       s10, [r4, #0xa8]
00000bea  84ed294a          vstr       s8, [r4, #0xa4]
00000bee  34ee454a          vsub.f32   s8, s8, s10
00000bf2  24ee033a          vmul.f32   s6, s8, s6
00000bf6  94ed314a          vldr       s8, [r4, #0xc4]
00000bfa  33ee05fa          vadd.f32   s30, s6, s10
00000bfe  94ed2e3a          vldr       s6, [r4, #0xb8]
00000c02  38ee445a          vsub.f32   s10, s16, s8
00000c06  8ded023a          vstr       s6, [sp, #8]
00000c0a  34ee094a          vadd.f32   s8, s8, s18
00000c0e  94ed303a          vldr       s6, [r4, #0xc0]
00000c12  84ed2afa          vstr       s30, [r4, #0xa8]
00000c16  25ee035a          vmul.f32   s10, s10, s6
00000c1a  34ee054a          vadd.f32   s8, s8, s10
00000c1e  94ed325a          vldr       s10, [r4, #0xc8]
00000c22  84ed314a          vstr       s8, [r4, #0xc4]
00000c26  34ee454a          vsub.f32   s8, s8, s10
00000c2a  24ee034a          vmul.f32   s8, s8, s6
00000c2e  34ee054a          vadd.f32   s8, s8, s10
00000c32  94ed335a          vldr       s10, [r4, #0xcc]
00000c36  84ed324a          vstr       s8, [r4, #0xc8]
00000c3a  34ee454a          vsub.f32   s8, s8, s10
00000c3e  24ee034a          vmul.f32   s8, s8, s6
00000c42  34ee054a          vadd.f32   s8, s8, s10
00000c46  94ed345a          vldr       s10, [r4, #0xd0]
00000c4a  84ed334a          vstr       s8, [r4, #0xcc]
00000c4e  34ee454a          vsub.f32   s8, s8, s10
00000c52  24ee033a          vmul.f32   s6, s8, s6
00000c56  94ed3b4a          vldr       s8, [r4, #0xec]
00000c5a  73ee05ba          vadd.f32   s23, s6, s10
00000c5e  94ed3a3a          vldr       s6, [r4, #0xe8]
00000c62  38ee445a          vsub.f32   s10, s16, s8
00000c66  34ee094a          vadd.f32   s8, s8, s18
00000c6a  c4ed34ba          vstr       s23, [r4, #0xd0]
00000c6e  25ee035a          vmul.f32   s10, s10, s6
00000c72  34ee054a          vadd.f32   s8, s8, s10
00000c76  94ed3c5a          vldr       s10, [r4, #0xf0]
00000c7a  84ed3b4a          vstr       s8, [r4, #0xec]
00000c7e  34ee454a          vsub.f32   s8, s8, s10
00000c82  24ee034a          vmul.f32   s8, s8, s6
00000c86  34ee054a          vadd.f32   s8, s8, s10
00000c8a  94ed3d5a          vldr       s10, [r4, #0xf4]
00000c8e  84ed3c4a          vstr       s8, [r4, #0xf0]
00000c92  34ee454a          vsub.f32   s8, s8, s10
00000c96  24ee034a          vmul.f32   s8, s8, s6
00000c9a  34ee054a          vadd.f32   s8, s8, s10
00000c9e  94ed3e5a          vldr       s10, [r4, #0xf8]
00000ca2  84ed3d4a          vstr       s8, [r4, #0xf4]
00000ca6  34ee454a          vsub.f32   s8, s8, s10
00000caa  24ee033a          vmul.f32   s6, s8, s6
00000cae  73ee05ea          vadd.f32   s29, s6, s10
00000cb2  c4ed3eea          vstr       s29, [r4, #0xf8]
00000cb6  08bf              it         eq
00000cb8  b0ee421a          vmoveq.f32 s2, s4
00000cbc  94ed453a          vldr       s6, [r4, #0x114]
00000cc0  21ee000a          vmul.f32   s0, s2, s0
00000cc4  94ed442a          vldr       s4, [r4, #0x110]
00000cc8  38ee434a          vsub.f32   s8, s16, s6
00000ccc  c4e93f51          strd       r5, r1, [r4, #0xfc]
00000cd0  33ee093a          vadd.f32   s6, s6, s18
00000cd4  d4f82411          ldr.w      r1, [r4, #0x124]
00000cd8  d4ed4a9a          vldr       s19, [r4, #0x128]
00000cdc  d4ed42fa          vldr       s31, [r4, #0x108]
00000ce0  d4f80cb1          ldr.w      fp, [r4, #0x10c]
00000ce4  10ee100a          vmov       r0, s0
00000ce8  c4ed4b9a          vstr       s19, [r4, #0x12c]
00000cec  c4e94951          strd       r5, r1, [r4, #0x124]
00000cf0  24ee024a          vmul.f32   s8, s8, s4
00000cf4  33ee043a          vadd.f32   s6, s6, s8
00000cf8  94ed464a          vldr       s8, [r4, #0x118]
00000cfc  84ed453a          vstr       s6, [r4, #0x114]
00000d00  33ee443a          vsub.f32   s6, s6, s8
00000d04  23ee023a          vmul.f32   s6, s6, s4
00000d08  33ee043a          vadd.f32   s6, s6, s8
00000d0c  94ed474a          vldr       s8, [r4, #0x11c]
00000d10  84ed463a          vstr       s6, [r4, #0x118]
00000d14  33ee443a          vsub.f32   s6, s6, s8
00000d18  23ee023a          vmul.f32   s6, s6, s4
00000d1c  33ee043a          vadd.f32   s6, s6, s8
00000d20  94ed484a          vldr       s8, [r4, #0x120]
00000d24  84ed473a          vstr       s6, [r4, #0x11c]
00000d28  33ee443a          vsub.f32   s6, s6, s8
00000d2c  23ee022a          vmul.f32   s4, s6, s4
00000d30  32ee04ea          vadd.f32   s28, s4, s8
00000d34  84ed48ea          vstr       s28, [r4, #0x120]
00000d38  00f0c0ed          blx        #0x18bc
00000d3c  8146              mov        sb, r0
00000d3e  0398              ldr        r0, [sp, #0xc]
00000d40  3dee4c0a          vsub.f32   s0, s26, s24
00000d44  8846              mov        r8, r1
00000d46  0128              cmp        r0, #1
00000d48  08bf              it         eq
00000d4a  b0ee40ca          vmoveq.f32 s24, s0
00000d4e  9ded040a          vldr       s0, [sp, #0x10]
00000d52  2cee000a          vmul.f32   s0, s24, s0
00000d56  10ee100a          vmov       r0, s0
00000d5a  00f0b0ed          blx        #0x18bc
00000d5e  94ed4f2a          vldr       s4, [r4, #0x13c]
00000d62  94ed4e1a          vldr       s2, [r4, #0x138]
00000d66  38ee423a          vsub.f32   s6, s16, s4
00000d6a  019a              ldr        r2, [sp, #4]
00000d6c  32ee092a          vadd.f32   s4, s4, s18
00000d70  94ed4c0a          vldr       s0, [r4, #0x130]
00000d74  012a              cmp        r2, #1
00000d76  23ee013a          vmul.f32   s6, s6, s2
00000d7a  32ee032a          vadd.f32   s4, s4, s6
00000d7e  38eeca3a          vsub.f32   s6, s17, s20
00000d82  08bf              it         eq
00000d84  b0ee43aa          vmoveq.f32 s20, s6
00000d88  94ed503a          vldr       s6, [r4, #0x140]
00000d8c  012e              cmp        r6, #1
00000d8e  9ded054a          vldr       s8, [sp, #0x14]
00000d92  d4f83431          ldr.w      r3, [r4, #0x134]
00000d96  2aee044a          vmul.f32   s8, s20, s8
00000d9a  84ed4f2a          vstr       s4, [r4, #0x13c]
00000d9e  32ee432a          vsub.f32   s4, s4, s6
00000da2  14ee102a          vmov       r2, s8
00000da6  22ee012a          vmul.f32   s4, s4, s2
00000daa  32ee032a          vadd.f32   s4, s4, s6
00000dae  94ed513a          vldr       s6, [r4, #0x144]
00000db2  84ed502a          vstr       s4, [r4, #0x140]
00000db6  32ee432a          vsub.f32   s4, s4, s6
00000dba  22ee012a          vmul.f32   s4, s4, s2
00000dbe  32ee032a          vadd.f32   s4, s4, s6
00000dc2  94ed523a          vldr       s6, [r4, #0x148]
00000dc6  84ed512a          vstr       s4, [r4, #0x144]
00000dca  32ee432a          vsub.f32   s4, s4, s6
00000dce  22ee011a          vmul.f32   s2, s4, s2
00000dd2  3aeecf2a          vsub.f32   s4, s21, s30
00000dd6  08bf              it         eq
00000dd8  b0ee42fa          vmoveq.f32 s30, s4
00000ddc  3deeeb2a          vsub.f32   s4, s27, s23
00000de0  baf1010f          cmp.w      sl, #1
00000de4  08bf              it         eq
00000de6  f0ee42ba          vmoveq.f32 s23, s4
00000dea  9ded022a          vldr       s4, [sp, #8]
00000dee  94ed544a          vldr       s8, [r4, #0x150]
00000df2  bbf1010f          cmp.w      fp, #1
00000df6  2fee022a          vmul.f32   s4, s30, s4
00000dfa  2beeac5a          vmul.f32   s10, s23, s25
00000dfe  31ee031a          vadd.f32   s2, s2, s6
00000e02  3bee6e3a          vsub.f32   s6, s22, s29
00000e06  15ee10ba          vmov       fp, s10
00000e0a  00f003b8          b.w        #0xe14
00000e0e  00bf              nop        
00000e10  0000              movs       r0, r0
00000e12  802f              cmp        r7, #0x80
00000e14  84ed521a          vstr       s2, [r4, #0x148]
00000e18  08bf              it         eq
00000e1a  f0ee43ea          vmoveq.f32 s29, s6
00000e1e  d4f84c61          ldr.w      r6, [r4, #0x14c]
00000e22  39eece3a          vsub.f32   s6, s19, s28
00000e26  84ed554a          vstr       s8, [r4, #0x154]
00000e2a  012b              cmp        r3, #1
00000e2c  08bf              it         eq
00000e2e  b0ee43ea          vmoveq.f32 s28, s6
00000e32  d4f85c31          ldr.w      r3, [r4, #0x15c]
00000e36  34ee413a          vsub.f32   s6, s8, s2
00000e3a  c4e95356          strd       r5, r6, [r4, #0x14c]
00000e3e  012b              cmp        r3, #1
00000e40  08bf              it         eq
00000e42  b0ee431a          vmoveq.f32 s2, s6
00000e46  2eeeaf3a          vmul.f32   s6, s29, s31
00000e4a  2eee000a          vmul.f32   s0, s28, s0
00000e4e  12ee105a          vmov       r5, s4
00000e52  94ed562a          vldr       s4, [r4, #0x158]
00000e56  10eb0904          adds.w     r4, r0, sb
00000e5a  1046              mov        r0, r2
00000e5c  21ee028a          vmul.f32   s16, s2, s4
00000e60  41eb0806          adc.w      r6, r1, r8
00000e64  13ee10aa          vmov       sl, s6
00000e68  10ee109a          vmov       sb, s0
00000e6c  00f026ed          blx        #0x18bc
00000e70  2418              adds       r4, r4, r0
00000e72  2846              mov        r0, r5
00000e74  18ee108a          vmov       r8, s16
00000e78  4e41              adcs       r6, r1
00000e7a  00f020ed          blx        #0x18bc
00000e7e  2418              adds       r4, r4, r0
00000e80  5846              mov        r0, fp
00000e82  46eb0105          adc.w      r5, r6, r1
00000e86  00f01aed          blx        #0x18bc
00000e8a  2418              adds       r4, r4, r0
00000e8c  5046              mov        r0, sl
00000e8e  4d41              adcs       r5, r1
00000e90  00f014ed          blx        #0x18bc
00000e94  2418              adds       r4, r4, r0
00000e96  4846              mov        r0, sb
00000e98  4d41              adcs       r5, r1
00000e9a  00f010ed          blx        #0x18bc
00000e9e  2418              adds       r4, r4, r0
00000ea0  4046              mov        r0, r8
00000ea2  4d41              adcs       r5, r1
00000ea4  00f00aed          blx        #0x18bc
00000ea8  0019              adds       r0, r0, r4
00000eaa  6941              adcs       r1, r5
00000eac  ca17              asrs       r2, r1, #0x1f
00000eae  10eb9270          adds.w     r0, r0, r2, lsr #30
00000eb2  41f10001          adc        r1, r1, #0
00000eb6  8008              lsrs       r0, r0, #2
00000eb8  40ea8170          orr.w      r0, r0, r1, lsl #30
00000ebc  8910              asrs       r1, r1, #2
00000ebe  06b0              add        sp, #0x18
00000ec0  bdec108b          vpop       {d8, d9, d10, d11, d12, d13, d14, d15}
00000ec4  01b0              add        sp, #4
00000ec6  bde8000f          pop.w      {r8, sb, sl, fp}
00000eca  f0bd              pop        {r4, r5, r6, r7, pc}
