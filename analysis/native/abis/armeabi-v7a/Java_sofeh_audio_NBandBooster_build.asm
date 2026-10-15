# APK member: lib/armeabi-v7a/libnbandbooster.so
# Library SHA-256: 785733136117fa69ab2b7908597ad4dc045b883de2775b3018ca8d1de04333cc
# Function: Java_sofeh_audio_NBandBooster_build; address=0x560; bytes=212
# Decoder mode: thumb; decoded_bytes=212
00000560  52ea0300          orrs.w     r0, r2, r3
00000564  08bf              it         eq
00000566  7047              bxeq       lr
00000568  f0b5              push       {r4, r5, r6, r7, lr}
0000056a  03af              add        r7, sp, #0xc
0000056c  2de90007          push.w     {r8, sb, sl}
00000570  2ded028b          vpush      {d8}
00000574  7869              ldr        r0, [r7, #0x14]
00000576  07f1080a          add.w      sl, r7, #8
0000057a  1446              mov        r4, r2
0000057c  9ae80007          ldm.w      sl, {r8, sb, sl}
00000580  00ee100a          vmov       s0, r0
00000584  b8eec08a          vcvt.f32.s32 s16, s0
00000588  9fed270a          vldr       s0, [pc, #0x9c]
0000058c  80ee080a          vdiv.f32   s0, s0, s16
00000590  f7eec00a          vcvt.f64.f32 d16, s0
00000594  51ec300b          vmov       r0, r1, d16
00000598  00f00aea          blx        #0x9b0
0000059c  9fed230a          vldr       s0, [pc, #0x8c]
000005a0  0546              mov        r5, r0
000005a2  0e46              mov        r6, r1
000005a4  80ee080a          vdiv.f32   s0, s0, s16
000005a8  f7eec00a          vcvt.f64.f32 d16, s0
000005ac  51ec300b          vmov       r0, r1, d16
000005b0  00f0fee9          blx        #0x9b0
000005b4  00ee10aa          vmov       s0, sl
000005b8  9fed1d3a          vldr       s6, [pc, #0x74]
000005bc  01ee109a          vmov       s2, sb
000005c0  02ee108a          vmov       s4, r8
000005c4  b8eec00a          vcvt.f32.s32 s0, s0
000005c8  b8eec11a          vcvt.f32.s32 s2, s2
000005cc  b8eec22a          vcvt.f32.s32 s4, s4
000005d0  46ec315b          vmov       d17, r5, r6
000005d4  41ec300b          vmov       d16, r0, r1
000005d8  b7ee004a          vmov.f32   s8, #1.000000e+00
000005dc  b7eee15b          vcvt.f32.f64 s10, d17
000005e0  20ee030a          vmul.f32   s0, s0, s6
000005e4  21ee031a          vmul.f32   s2, s2, s6
000005e8  22ee032a          vmul.f32   s4, s4, s6
000005ec  b7eee03b          vcvt.f32.f64 s6, d16
000005f0  30ee040a          vadd.f32   s0, s0, s8
000005f4  31ee041a          vadd.f32   s2, s2, s8
000005f8  32ee042a          vadd.f32   s4, s4, s8
000005fc  33ee033a          vadd.f32   s6, s6, s6
00000600  35ee054a          vadd.f32   s8, s10, s10
00000604  84ed0c0a          vstr       s0, [r4, #0x30]
00000608  84ed0b1a          vstr       s2, [r4, #0x2c]
0000060c  84ed0a2a          vstr       s4, [r4, #0x28]
00000610  84ed053a          vstr       s6, [r4, #0x14]
00000614  84ed004a          vstr       s8, [r4]
00000618  bdec028b          vpop       {d8}
0000061c  bde80007          pop.w      {r8, sb, sl}
00000620  bde8f040          pop.w      {r4, r5, r6, r7, lr}
00000624  7047              bx         lr
00000626  00bf              nop        
00000628  a0c9              ldm        r1!, {r5, r7}
0000062a  2c45              cmp        r4, r5
0000062c  db6f              ldr        r3, [r3, #0x7c]
0000062e  7546              mov        r5, lr
00000630  0ad7              bvc        #0x648
00000632  233c              subs       r4, #0x23
