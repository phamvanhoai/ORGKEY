# APK member: lib/armeabi-v7a/libnequalizer.so
# Library SHA-256: 43a844f1021b0ecaefd1ef5590ed454b77eb391a5a623bbafb0e7fc971f4108a
# Function: Java_sofeh_audio_NEqualizer_build; address=0x6f0; bytes=756
# Decoder mode: thumb; decoded_bytes=736
000006f0  52ea0300          orrs.w     r0, r2, r3
000006f4  08bf              it         eq
000006f6  7047              bxeq       lr
000006f8  f0b5              push       {r4, r5, r6, r7, lr}
000006fa  03af              add        r7, sp, #0xc
000006fc  2de9000f          push.w     {r8, sb, sl, fp}
00000700  81b0              sub        sp, #4
00000702  2ded108b          vpush      {d8, d9, d10, d11, d12, d13, d14, d15}
00000706  82b0              sub        sp, #8
00000708  b868              ldr        r0, [r7, #8]
0000070a  1446              mov        r4, r2
0000070c  dfeda41b          vldr       d17, [pc, #0x290]
00000710  00ee100a          vmov       s0, r0
00000714  b8eec08a          vcvt.f32.s32 s16, s0
00000718  9feda90a          vldr       s0, [pc, #0x2a4]
0000071c  80ee080a          vdiv.f32   s0, s0, s16
00000720  f7eec00a          vcvt.f64.f32 d16, s0
00000724  60eea10b          vmul.f64   d16, d16, d17
00000728  51ec300b          vmov       r0, r1, d16
0000072c  01f0d8e9          blx        #0x1ae0
00000730  9feda40a          vldr       s0, [pc, #0x290]
00000734  0546              mov        r5, r0
00000736  dfed9c1b          vldr       d17, [pc, #0x270]
0000073a  0e46              mov        r6, r1
0000073c  80ee080a          vdiv.f32   s0, s0, s16
00000740  f7eec00a          vcvt.f64.f32 d16, s0
00000744  60eea10b          vmul.f64   d16, d16, d17
00000748  51ec300b          vmov       r0, r1, d16
0000074c  01f0c8e9          blx        #0x1ae0
00000750  9fed9d0a          vldr       s0, [pc, #0x274]
00000754  9fed96ab          vldr       d10, [pc, #0x258]
00000758  80ee080a          vdiv.f32   s0, s0, s16
0000075c  f7eec00a          vcvt.f64.f32 d16, s0
00000760  60ee8a0b          vmul.f64   d16, d16, d10
00000764  9fed991a          vldr       s2, [pc, #0x264]
00000768  53ec302b          vmov       r2, r3, d16
0000076c  41ec300b          vmov       d16, r0, r1
00000770  81ee081a          vdiv.f32   s2, s2, s16
00000774  1046              mov        r0, r2
00000776  1946              mov        r1, r3
00000778  b7eec1ba          vcvt.f64.f32 d11, s2
0000077c  30eea09b          vadd.f64   d9, d16, d16
00000780  01f0aee9          blx        #0x1ae0
00000784  6bee0a0b          vmul.f64   d16, d11, d10
00000788  8046              mov        r8, r0
0000078a  8946              mov        sb, r1
0000078c  9fed900a          vldr       s0, [pc, #0x240]
00000790  51ec300b          vmov       r0, r1, d16
00000794  9fed8f1a          vldr       s2, [pc, #0x23c]
00000798  46ec1c5b          vmov       d12, r5, r6
0000079c  80ee080a          vdiv.f32   s0, s0, s16
000007a0  81ee08aa          vdiv.f32   s20, s2, s16
000007a4  b7eec0ba          vcvt.f64.f32 d11, s0
000007a8  01f09ae9          blx        #0x1ae0
000007ac  9fed8a0a          vldr       s0, [pc, #0x228]
000007b0  0546              mov        r5, r0
000007b2  9fed81db          vldr       d13, [pc, #0x204]
000007b6  0e46              mov        r6, r1
000007b8  80ee080a          vdiv.f32   s0, s0, s16
000007bc  f7eec00a          vcvt.f64.f32 d16, s0
000007c0  60ee8d0b          vmul.f64   d16, d16, d13
000007c4  51ec300b          vmov       r0, r1, d16
000007c8  01f08ae9          blx        #0x1ae0
000007cc  f7eeca0a          vcvt.f64.f32 d16, s20
000007d0  8246              mov        sl, r0
000007d2  8b46              mov        fp, r1
000007d4  60ee8d0b          vmul.f64   d16, d16, d13
000007d8  9fed800a          vldr       s0, [pc, #0x200]
000007dc  51ec300b          vmov       r0, r1, d16
000007e0  46ec305b          vmov       d16, r5, r6
000007e4  80ee080a          vdiv.f32   s0, s0, s16
000007e8  2bee0dab          vmul.f64   d10, d11, d13
000007ec  3cee0cbb          vadd.f64   d11, d12, d12
000007f0  b7eec08a          vcvt.f64.f32 d8, s0
000007f4  30eea0cb          vadd.f64   d12, d16, d16
000007f8  01f072e9          blx        #0x1ae0
000007fc  0246              mov        r2, r0
000007fe  0b46              mov        r3, r1
00000800  51ec1a0b          vmov       r0, r1, d10
00000804  94ed000a          vldr       s0, [r4]
00000808  94ed011a          vldr       s2, [r4, #4]
0000080c  4bec30ab          vmov       d16, sl, fp
00000810  94ed022a          vldr       s4, [r4, #8]
00000814  b8eec00a          vcvt.f32.s32 s0, s0
00000818  b8eec11a          vcvt.f32.s32 s2, s2
0000081c  0125              movs       r5, #1
0000081e  b8eec22a          vcvt.f32.s32 s4, s4
00000822  6564              str        r5, [r4, #0x44]
00000824  68ee0d1b          vmul.f64   d17, d8, d13
00000828  c4f88451          str.w      r5, [r4, #0x184]
0000082c  e566              str        r5, [r4, #0x6c]
0000082e  c4f8ac51          str.w      r5, [r4, #0x1ac]
00000832  c4f89450          str.w      r5, [r4, #0x94]
00000836  c4f8d451          str.w      r5, [r4, #0x1d4]
0000083a  9fed698a          vldr       s16, [pc, #0x1a4]
0000083e  43ec322b          vmov       d18, r2, r3
00000842  70eea00b          vadd.f64   d16, d16, d16
00000846  20ee080a          vmul.f32   s0, s0, s16
0000084a  cded000b          vstr       d16, [sp]
0000084e  21ee081a          vmul.f32   s2, s2, s16
00000852  22ee08fa          vmul.f32   s30, s4, s16
00000856  49ec338b          vmov       d19, r8, sb
0000085a  b7eecbab          vcvt.f32.f64 s20, d11
0000085e  b7eeccbb          vcvt.f32.f64 s22, d12
00000862  b7eec99b          vcvt.f32.f64 s18, d9
00000866  33eea3cb          vadd.f64   d12, d19, d19
0000086a  32eea2db          vadd.f64   d13, d18, d18
0000086e  58ec316b          vmov       r6, r8, d17
00000872  84ed100a          vstr       s0, [r4, #0x40]
00000876  84ed600a          vstr       s0, [r4, #0x180]
0000087a  84ed1a1a          vstr       s2, [r4, #0x68]
0000087e  84ed6a1a          vstr       s2, [r4, #0x1a8]
00000882  84ed24fa          vstr       s30, [r4, #0x90]
00000886  01f02ce9          blx        #0x1ae0
0000088a  41ec1e0b          vmov       d14, r0, r1
0000088e  3046              mov        r0, r6
00000890  4146              mov        r1, r8
00000892  84ed08aa          vstr       s20, [r4, #0x20]
00000896  84ed58aa          vstr       s20, [r4, #0x160]
0000089a  84ed129a          vstr       s18, [r4, #0x48]
0000089e  84ed629a          vstr       s18, [r4, #0x188]
000008a2  84ed1cba          vstr       s22, [r4, #0x70]
000008a6  84ed6cba          vstr       s22, [r4, #0x1b0]
000008aa  01f01ae9          blx        #0x1ae0
000008ae  94ed030a          vldr       s0, [r4, #0xc]
000008b2  41ec300b          vmov       d16, r0, r1
000008b6  94ed041a          vldr       s2, [r4, #0x10]
000008ba  b8eec00a          vcvt.f32.s32 s0, s0
000008be  dded001b          vldr       d17, [sp]
000008c2  b8eec11a          vcvt.f32.s32 s2, s2
000008c6  94ed052a          vldr       s4, [r4, #0x14]
000008ca  b7eee15b          vcvt.f32.f64 s10, d17
000008ce  c4f8bc50          str.w      r5, [r4, #0xbc]
000008d2  c4f8fc51          str.w      r5, [r4, #0x1fc]
000008d6  c4f8e450          str.w      r5, [r4, #0xe4]
000008da  c4f82452          str.w      r5, [r4, #0x224]
000008de  c4f80c51          str.w      r5, [r4, #0x10c]
000008e2  c4f84c52          str.w      r5, [r4, #0x24c]
000008e6  c4f83451          str.w      r5, [r4, #0x134]
000008ea  20ee080a          vmul.f32   s0, s0, s16
000008ee  84ed74fa          vstr       s30, [r4, #0x1d0]
000008f2  21ee081a          vmul.f32   s2, s2, s16
000008f6  84ed3a5a          vstr       s10, [r4, #0xe8]
000008fa  7eee0e1b          vadd.f64   d17, d14, d14
000008fe  c4f87452          str.w      r5, [r4, #0x274]
00000902  c4f85c51          str.w      r5, [r4, #0x15c]
00000906  c4f89c52          str.w      r5, [r4, #0x29c]
0000090a  70eea00b          vadd.f64   d16, d16, d16
0000090e  84ed2e0a          vstr       s0, [r4, #0xb8]
00000912  b8eec22a          vcvt.f32.s32 s4, s4
00000916  84ed7e0a          vstr       s0, [r4, #0x1f8]
0000091a  94ed060a          vldr       s0, [r4, #0x18]
0000091e  84ed381a          vstr       s2, [r4, #0xe0]
00000922  84ed881a          vstr       s2, [r4, #0x220]
00000926  b8eec00a          vcvt.f32.s32 s0, s0
0000092a  94ed071a          vldr       s2, [r4, #0x1c]
0000092e  b7eecc3b          vcvt.f32.f64 s6, d12
00000932  b7eecd4b          vcvt.f32.f64 s8, d13
00000936  b8eec11a          vcvt.f32.s32 s2, s2
0000093a  84ed263a          vstr       s6, [r4, #0x98]
0000093e  22ee082a          vmul.f32   s4, s4, s16
00000942  84ed763a          vstr       s6, [r4, #0x1d8]
00000946  b7eee13b          vcvt.f32.f64 s6, d17
0000094a  20ee080a          vmul.f32   s0, s0, s16
0000094e  84ed304a          vstr       s8, [r4, #0xc0]
00000952  21ee081a          vmul.f32   s2, s2, s16
00000956  84ed804a          vstr       s8, [r4, #0x200]
0000095a  b7eee04b          vcvt.f32.f64 s8, d16
0000095e  84ed8a5a          vstr       s10, [r4, #0x228]
00000962  84ed422a          vstr       s4, [r4, #0x108]
00000966  84ed922a          vstr       s4, [r4, #0x248]
0000096a  84ed443a          vstr       s6, [r4, #0x110]
0000096e  84ed943a          vstr       s6, [r4, #0x250]
00000972  84ed4e4a          vstr       s8, [r4, #0x138]
00000976  84ed9e4a          vstr       s8, [r4, #0x278]
0000097a  84ed4c0a          vstr       s0, [r4, #0x130]
0000097e  84ed9c0a          vstr       s0, [r4, #0x270]
00000982  84ed561a          vstr       s2, [r4, #0x158]
00000986  84eda61a          vstr       s2, [r4, #0x298]
0000098a  02b0              add        sp, #8
0000098c  bdec108b          vpop       {d8, d9, d10, d11, d12, d13, d14, d15}
00000990  01b0              add        sp, #4
00000992  bde8000f          pop.w      {r8, sb, sl, fp}
00000996  bde8f040          pop.w      {r4, r5, r6, r7, lr}
0000099a  7047              bx         lr
0000099c  00bf              nop        
0000099e  00bf              nop        
000009a0  182d              cmp        r5, #0x18
000009a2  4454              strb       r4, [r0, r1]
000009a4  fb21              movs       r1, #0xfb
000009a6  d93f              subs       r7, #0xd9
000009a8  182d              cmp        r5, #0x18
000009aa  4454              strb       r4, [r0, r1]
000009ac  fb21              movs       r1, #0xfb
000009ae  e93f              subs       r7, #0xe9
000009b0  182d              cmp        r5, #0x18
000009b2  4454              strb       r4, [r0, r1]
000009b4  fb21              movs       r1, #0xfb
000009b6  f93f              subs       r7, #0xf9
000009b8  182d              cmp        r5, #0x18
000009ba  4454              strb       r4, [r0, r1]
000009bc  fb21              movs       r1, #0xfb
000009be  0940              ands       r1, r1
000009c0  0040              ands       r0, r0
000009c2  9c44              add        ip, r3
000009c4  0040              ands       r0, r0
000009c6  1c45              cmp        r4, r3
000009c8  0040              ands       r0, r0
000009ca  9c45              cmp        ip, r3
000009cc  0060              str        r0, [r0]
000009ce  6a45              cmp        r2, sp
# Undecoded tail: offset=0x2e0; bytes=20
# Raw hex: 00b808460050c3450060ea4500401c460ad7233c
