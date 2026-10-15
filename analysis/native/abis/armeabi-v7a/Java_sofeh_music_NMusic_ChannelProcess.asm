# APK member: lib/armeabi-v7a/libnmusic.so
# Library SHA-256: bd2a4a0e17aaa14c558d5f96ba1ac387097bd96c2bdd771c287261dd8fdcc77e
# Function: Java_sofeh_music_NMusic_ChannelProcess; address=0x1aa0; bytes=2544
# Decoder mode: thumb; decoded_bytes=2544
00001aa0  f0b5              push       {r4, r5, r6, r7, lr}
00001aa2  03af              add        r7, sp, #0xc
00001aa4  2de9800f          push.w     {r7, r8, sb, sl, fp}
00001aa8  2ded108b          vpush      {d8, d9, d10, d11, d12, d13, d14, d15}
00001aac  94b0              sub        sp, #0x50
00001aae  0646              mov        r6, r0
00001ab0  0068              ldr        r0, [r0]
00001ab2  d7f80c80          ldr.w      r8, [r7, #0xc]
00001ab6  1292              str        r2, [sp, #0x48]
00001ab8  c26f              ldr        r2, [r0, #0x7c]
00001aba  3046              mov        r0, r6
00001abc  4146              mov        r1, r8
00001abe  0f93              str        r3, [sp, #0x3c]
00001ac0  9047              blx        r2
00001ac2  8146              mov        sb, r0
00001ac4  3068              ldr        r0, [r6]
00001ac6  4946              mov        r1, sb
00001ac8  d0f87841          ldr.w      r4, [r0, #0x178]
00001acc  3046              mov        r0, r6
00001ace  904a              ldr        r2, [pc, #0x240]
00001ad0  904b              ldr        r3, [pc, #0x240]
00001ad2  7a44              add        r2, pc
00001ad4  7b44              add        r3, pc
00001ad6  a047              blx        r4
00001ad8  0246              mov        r2, r0
00001ada  3068              ldr        r0, [r6]
00001adc  4146              mov        r1, r8
00001ade  0892              str        r2, [sp, #0x20]
00001ae0  d0f89431          ldr.w      r3, [r0, #0x194]
00001ae4  3046              mov        r0, r6
00001ae6  9847              blx        r3
00001ae8  8346              mov        fp, r0
00001aea  3068              ldr        r0, [r6]
00001aec  1091              str        r1, [sp, #0x40]
00001aee  3969              ldr        r1, [r7, #0x10]
00001af0  d0f89823          ldr.w      r2, [r0, #0x398]
00001af4  3046              mov        r0, r6
00001af6  9047              blx        r2
00001af8  1190              str        r0, [sp, #0x44]
00001afa  0022              movs       r2, #0
00001afc  3068              ldr        r0, [r6]
00001afe  7969              ldr        r1, [r7, #0x14]
00001b00  d0f8f032          ldr.w      r3, [r0, #0x2f0]
00001b04  3046              mov        r0, r6
00001b06  9847              blx        r3
00001b08  0b90              str        r0, [sp, #0x2c]
00001b0a  0022              movs       r2, #0
00001b0c  3068              ldr        r0, [r6]
00001b0e  b969              ldr        r1, [r7, #0x18]
00001b10  d0f8f032          ldr.w      r3, [r0, #0x2f0]
00001b14  3046              mov        r0, r6
00001b16  9847              blx        r3
00001b18  0a90              str        r0, [sp, #0x28]
00001b1a  3068              ldr        r0, [r6]
00001b1c  fc69              ldr        r4, [r7, #0x1c]
00001b1e  0996              str        r6, [sp, #0x24]
00001b20  d0f87851          ldr.w      r5, [r0, #0x178]
00001b24  b868              ldr        r0, [r7, #8]
00001b26  0028              cmp        r0, #0
00001b28  00f00a82          beq.w      #0x1f40
00001b2c  7a4a              ldr        r2, [pc, #0x1e8]
00001b2e  3046              mov        r0, r6
00001b30  7a4c              ldr        r4, [pc, #0x1e8]
00001b32  4946              mov        r1, sb
00001b34  7a44              add        r2, pc
00001b36  7c44              add        r4, pc
00001b38  2346              mov        r3, r4
00001b3a  a847              blx        r5
00001b3c  0246              mov        r2, r0
00001b3e  3068              ldr        r0, [r6]
00001b40  4146              mov        r1, r8
00001b42  0792              str        r2, [sp, #0x1c]
00001b44  d0f89c31          ldr.w      r3, [r0, #0x19c]
00001b48  3046              mov        r0, r6
00001b4a  9847              blx        r3
00001b4c  1390              str        r0, [sp, #0x4c]
00001b4e  8a46              mov        sl, r1
00001b50  3068              ldr        r0, [r6]
00001b52  4946              mov        r1, sb
00001b54  2346              mov        r3, r4
00001b56  d0f87851          ldr.w      r5, [r0, #0x178]
00001b5a  3046              mov        r0, r6
00001b5c  704a              ldr        r2, [pc, #0x1c0]
00001b5e  7a44              add        r2, pc
00001b60  a847              blx        r5
00001b62  0246              mov        r2, r0
00001b64  3068              ldr        r0, [r6]
00001b66  4146              mov        r1, r8
00001b68  d0f89c31          ldr.w      r3, [r0, #0x19c]
00001b6c  3046              mov        r0, r6
00001b6e  9847              blx        r3
00001b70  cde90d10          strd       r1, r0, [sp, #0x34]
00001b74  4946              mov        r1, sb
00001b76  3068              ldr        r0, [r6]
00001b78  d0f87851          ldr.w      r5, [r0, #0x178]
00001b7c  3046              mov        r0, r6
00001b7e  694a              ldr        r2, [pc, #0x1a4]
00001b80  694c              ldr        r4, [pc, #0x1a4]
00001b82  7a44              add        r2, pc
00001b84  7c44              add        r4, pc
00001b86  2346              mov        r3, r4
00001b88  a847              blx        r5
00001b8a  0246              mov        r2, r0
00001b8c  3068              ldr        r0, [r6]
00001b8e  4146              mov        r1, r8
00001b90  0692              str        r2, [sp, #0x18]
00001b92  d0f89831          ldr.w      r3, [r0, #0x198]
00001b96  3046              mov        r0, r6
00001b98  9847              blx        r3
00001b9a  08ee100a          vmov       s16, r0
00001b9e  3068              ldr        r0, [r6]
00001ba0  4946              mov        r1, sb
00001ba2  2346              mov        r3, r4
00001ba4  d0f87851          ldr.w      r5, [r0, #0x178]
00001ba8  3046              mov        r0, r6
00001baa  604a              ldr        r2, [pc, #0x180]
00001bac  7a44              add        r2, pc
00001bae  a847              blx        r5
00001bb0  0246              mov        r2, r0
00001bb2  3068              ldr        r0, [r6]
00001bb4  4146              mov        r1, r8
00001bb6  0592              str        r2, [sp, #0x14]
00001bb8  d0f89831          ldr.w      r3, [r0, #0x198]
00001bbc  3046              mov        r0, r6
00001bbe  9847              blx        r3
00001bc0  09ee100a          vmov       s18, r0
00001bc4  9fed5a0a          vldr       s0, [pc, #0x168]
00001bc8  b5ee408a          vcmp.f32   s16, #0
00001bcc  4946              mov        r1, sb
00001bce  f1ee10fa          vmrs       apsr_nzcv, fpscr
00001bd2  b5ee409a          vcmp.f32   s18, #0
00001bd6  48bf              it         mi
00001bd8  b0ee408a          vmovmi.f32 s16, s0
00001bdc  f1ee10fa          vmrs       apsr_nzcv, fpscr
00001be0  48bf              it         mi
00001be2  b0ee409a          vmovmi.f32 s18, s0
00001be6  1398              ldr        r0, [sp, #0x4c]
00001be8  2346              mov        r3, r4
00001bea  4aec1a0b          vmov       d10, r0, sl
00001bee  3068              ldr        r0, [r6]
00001bf0  d0f87851          ldr.w      r5, [r0, #0x178]
00001bf4  3046              mov        r0, r6
00001bf6  4f4a              ldr        r2, [pc, #0x13c]
00001bf8  7a44              add        r2, pc
00001bfa  a847              blx        r5
00001bfc  0246              mov        r2, r0
00001bfe  3068              ldr        r0, [r6]
00001c00  4146              mov        r1, r8
00001c02  d0f89831          ldr.w      r3, [r0, #0x198]
00001c06  3046              mov        r0, r6
00001c08  9847              blx        r3
00001c0a  8246              mov        sl, r0
00001c0c  3068              ldr        r0, [r6]
00001c0e  4946              mov        r1, sb
00001c10  2346              mov        r3, r4
00001c12  d0f87851          ldr.w      r5, [r0, #0x178]
00001c16  3046              mov        r0, r6
00001c18  474a              ldr        r2, [pc, #0x11c]
00001c1a  7a44              add        r2, pc
00001c1c  a847              blx        r5
00001c1e  0246              mov        r2, r0
00001c20  3068              ldr        r0, [r6]
00001c22  4146              mov        r1, r8
00001c24  d0f89831          ldr.w      r3, [r0, #0x198]
00001c28  3046              mov        r0, r6
00001c2a  9847              blx        r3
00001c2c  b5ee408a          vcmp.f32   s16, #0
00001c30  c146              mov        sb, r8
00001c32  f1ee10fa          vmrs       apsr_nzcv, fpscr
00001c36  dcbf              itt        le
00001c38  b5ee409a          vcmple.f32 s18, #0
00001c3c  f1ee10fa          vmrsle     apsr_nzcv, fpscr
00001c40  40f30d83          ble.w      #0x225e
00001c44  dde90d21          ldrd       r2, r1, [sp, #0x34]
00001c48  0dee10aa          vmov       s26, sl
00001c4c  ddf84080          ldr.w      r8, [sp, #0x40]
00001c50  08ee900a          vmov       s17, r0
00001c54  42ec1b1b          vmov       d11, r1, r2
00001c58  b968              ldr        r1, [r7, #8]
00001c5a  881e              subs       r0, r1, #2
00001c5c  0228              cmp        r0, #2
00001c5e  80f02282          bhs.w      #0x20a6
00001c62  396b              ldr        r1, [r7, #0x30]
00001c64  f0ee089a          vmov.f32   s19, #3.000000e+00
00001c68  1198              ldr        r0, [sp, #0x44]
00001c6a  f8ee08ca          vmov.f32   s25, #-3.000000e+00
00001c6e  f6ee00da          vmov.f32   s27, #5.000000e-01
00001c72  9fed32fa          vldr       s30, [pc, #0xc8]
00001c76  00eb4100          add.w      r0, r0, r1, lsl #1
00001c7a  0c90              str        r0, [sp, #0x30]
00001c7c  386a              ldr        r0, [r7, #0x20]
00001c7e  796a              ldr        r1, [r7, #0x24]
00001c80  c043              mvns       r0, r0
00001c82  0844              add        r0, r1
00001c84  0d90              str        r0, [sp, #0x34]
00001c86  dde90a40          ldrd       r4, r0, [sp, #0x28]
00001c8a  0021              movs       r1, #0
00001c8c  1290              str        r0, [sp, #0x48]
00001c8e  0020              movs       r0, #0
00001c90  1390              str        r0, [sp, #0x4c]
00001c92  139a              ldr        r2, [sp, #0x4c]
00001c94  f869              ldr        r0, [r7, #0x1c]
00001c96  02f00106          and        r6, r2, #1
00001c9a  8142              cmp        r1, r0
00001c9c  80f2ac83          bge.w      #0x23f8
00001ca0  002e              cmp        r6, #0
00001ca2  40f0a983          bne.w      #0x23f8
00001ca6  4feae870          asr.w      r0, r8, #0x1f
00001caa  1091              str        r1, [sp, #0x40]
00001cac  1bebd040          adds.w     r0, fp, r0, lsr #19
00001cb0  48f10001          adc        r1, r8, #0
00001cb4  400b              lsrs       r0, r0, #0xd
00001cb6  4246              mov        r2, r8
00001cb8  40eac148          orr.w      r8, r0, r1, lsl #19
00001cbc  b86a              ldr        r0, [r7, #0x28]
00001cbe  0f94              str        r4, [sp, #0x3c]
00001cc0  d8b1              cbz        r0, #0x1cfa
00001cc2  3e6a              ldr        r6, [r7, #0x20]
00001cc4  b045              cmp        r8, r6
00001cc6  3bdd              ble        #0x1d40
00001cc8  0d9b              ldr        r3, [sp, #0x34]
00001cca  b5ee409a          vcmp.f32   s18, #0
00001cce  139a              ldr        r2, [sp, #0x4c]
00001cd0  9844              add        r8, r3
00001cd2  4feae870          asr.w      r0, r8, #0x1f
00001cd6  4fea483a          lsl.w      sl, r8, #0xd
00001cda  4003              lsls       r0, r0, #0xd
00001cdc  40ead841          orr.w      r1, r0, r8, lsr #19
00001ce0  f1ee10fa          vmrs       apsr_nzcv, fpscr
00001ce4  1046              mov        r0, r2
00001ce6  b5ee408a          vcmp.f32   s16, #0
00001cea  98bf              it         ls
00001cec  0120              movls      r0, #1
00001cee  f1ee10fa          vmrs       apsr_nzcv, fpscr
00001cf2  98bf              it         ls
00001cf4  0246              movls      r2, r0
00001cf6  1392              str        r2, [sp, #0x4c]
00001cf8  25e0              b          #0x1d46
00001cfa  3e6a              ldr        r6, [r7, #0x20]
00001cfc  08f10305          add.w      r5, r8, #3
00001d00  08f10204          add.w      r4, r8, #2
00001d04  08f10109          add.w      sb, r8, #1
00001d08  da46              mov        sl, fp
00001d0a  1146              mov        r1, r2
00001d0c  2be0              b          #0x1d66
00001d0e  00bf              nop        
00001d10  52f1ffff          bl         #0x154d12
00001d14  acf1ffff          bl         #0x1aed16
00001d18  11f1ffff          bl         #0x113d1a
00001d1c  2af1ffff          bl         #0x12cd1e
00001d20  10f1ffff          bl         #0x112d22
00001d24  e0f0ffff          bl         #0xe2d26
00001d28  e8f0ffff          bl         #0xead2a
00001d2c  a1f0ffff          bl         #0xa3d2e
00001d30  0000              movs       r0, r0
00001d32  0000              movs       r0, r0
00001d34  35f0ffff          bl         #0x37d36
00001d38  1ff0ffff          bl         #0x21d3a
00001d3c  0000              movs       r0, r0
00001d3e  0039              subs       r1, #0
00001d40  da46              mov        sl, fp
00001d42  1146              mov        r1, r2
00001d44  0d9b              ldr        r3, [sp, #0x34]
00001d46  08f10305          add.w      r5, r8, #3
00001d4a  08f10204          add.w      r4, r8, #2
00001d4e  b542              cmp        r5, r6
00001d50  c8bf              it         gt
00001d52  1d44              addgt      r5, r3
00001d54  b442              cmp        r4, r6
00001d56  4046              mov        r0, r8
00001d58  c8bf              it         gt
00001d5a  1c44              addgt      r4, r3
00001d5c  b045              cmp        r8, r6
00001d5e  a8bf              it         ge
00001d60  1844              addge      r0, r3
00001d62  00f10109          add.w      sb, r0, #1
00001d66  5046              mov        r0, sl
00001d68  0e91              str        r1, [sp, #0x38]
00001d6a  00f0f6ed          blx        #0x2958
00001d6e  00ee100a          vmov       s0, r0
00001d72  3046              mov        r0, r6
00001d74  b045              cmp        r8, r6
00001d76  b8bf              it         lt
00001d78  4046              movlt      r0, r8
00001d7a  01ee100a          vmov       s2, r0
00001d7e  b045              cmp        r8, r6
00001d80  1399              ldr        r1, [sp, #0x4c]
00001d82  3246              mov        r2, r6
00001d84  b8eec1ca          vcvt.f32.s32 s24, s2
00001d88  c8bf              it         gt
00001d8a  0121              movgt      r1, #1
00001d8c  1391              str        r1, [sp, #0x4c]
00001d8e  9145              cmp        sb, r2
00001d90  f96a              ldr        r1, [r7, #0x2c]
00001d92  b5ee408a          vcmp.f32   s16, #0
00001d96  00fa01f6          lsl.w      r6, r0, r1
00001d9a  d8bf              it         le
00001d9c  4846              movle      r0, sb
00001d9e  10ee0fca          vnmls.f32  s24, s0, s30
00001da2  9442              cmp        r4, r2
00001da4  00fa01f8          lsl.w      r8, r0, r1
00001da8  d8bf              it         le
00001daa  2046              movle      r0, r4
00001dac  9542              cmp        r5, r2
00001dae  00fa01f4          lsl.w      r4, r0, r1
00001db2  d8bf              it         le
00001db4  2846              movle      r0, r5
00001db6  00fa01fb          lsl.w      fp, r0, r1
00001dba  f1ee10fa          vmrs       apsr_nzcv, fpscr
00001dbe  6cee0cfa          vmul.f32   s31, s24, s24
00001dc2  6cee2fea          vmul.f32   s29, s24, s31
00001dc6  4bdd              ble        #0x1e60
00001dc8  1199              ldr        r1, [sp, #0x44]
00001dca  b9ee045a          vmov.f32   s10, #-5.000000e+00
00001dce  129d              ldr        r5, [sp, #0x48]
00001dd0  b0ee4dea          vmov.f32   s28, s26
00001dd4  31f91600          ldrsh.w    r0, [r1, r6, lsl #1]
00001dd8  00ee100a          vmov       s0, r0
00001ddc  31f91400          ldrsh.w    r0, [r1, r4, lsl #1]
00001de0  b8eec00a          vcvt.f32.s32 s0, s0
00001de4  01ee100a          vmov       s2, r0
00001de8  31f91800          ldrsh.w    r0, [r1, r8, lsl #1]
00001dec  b8eec11a          vcvt.f32.s32 s2, s2
00001df0  03ee100a          vmov       s6, r0
00001df4  31f91b00          ldrsh.w    r0, [r1, fp, lsl #1]
00001df8  b8eec33a          vcvt.f32.s32 s6, s6
00001dfc  31ee402a          vsub.f32   s4, s2, s0
00001e00  33ee034a          vadd.f32   s8, s6, s6
00001e04  0cee024a          vmla.f32   s8, s24, s4
00001e08  30ee002a          vadd.f32   s4, s0, s0
00001e0c  13ee290a          vnmls.f32  s0, s6, s19
00001e10  03ee052a          vmla.f32   s4, s6, s10
00001e14  b1ee005a          vmov.f32   s10, #4.000000e+00
00001e18  01ee2c0a          vmla.f32   s0, s2, s25
00001e1c  01ee052a          vmla.f32   s4, s2, s10
00001e20  05ee100a          vmov       s10, r0
00001e24  d5e90001          ldrd       r0, r1, [r5]
00001e28  b8eec55a          vcvt.f32.s32 s10, s10
00001e2c  32ee452a          vsub.f32   s4, s4, s10
00001e30  30ee050a          vadd.f32   s0, s0, s10
00001e34  02ee2f4a          vmla.f32   s8, s4, s31
00001e38  00ee2e4a          vmla.f32   s8, s0, s29
00001e3c  24ee2dda          vmul.f32   s26, s8, s27
00001e40  00f08aed          blx        #0x2958
00001e44  00ee100a          vmov       s0, r0
00001e48  0dee080a          vmla.f32   s0, s26, s16
00001e4c  b0ee4eda          vmov.f32   s26, s28
00001e50  10ee100a          vmov       r0, s0
00001e54  00f0f6ec          blx        #0x2844
00001e58  3eee088a          vadd.f32   s16, s28, s16
00001e5c  c5e90001          strd       r0, r1, [r5]
00001e60  d7f80c90          ldr.w      sb, [r7, #0xc]
00001e64  b5ee409a          vcmp.f32   s18, #0
00001e68  f1ee10fa          vmrs       apsr_nzcv, fpscr
00001e6c  48dd              ble        #0x1f00
00001e6e  0c99              ldr        r1, [sp, #0x30]
00001e70  b9ee045a          vmov.f32   s10, #-5.000000e+00
00001e74  31f91600          ldrsh.w    r0, [r1, r6, lsl #1]
00001e78  00ee100a          vmov       s0, r0
00001e7c  31f91400          ldrsh.w    r0, [r1, r4, lsl #1]
00001e80  0f9c              ldr        r4, [sp, #0x3c]
00001e82  b8eec00a          vcvt.f32.s32 s0, s0
00001e86  01ee100a          vmov       s2, r0
00001e8a  31f91800          ldrsh.w    r0, [r1, r8, lsl #1]
00001e8e  b8eec11a          vcvt.f32.s32 s2, s2
00001e92  03ee100a          vmov       s6, r0
00001e96  31f91b00          ldrsh.w    r0, [r1, fp, lsl #1]
00001e9a  b8eec33a          vcvt.f32.s32 s6, s6
00001e9e  31ee402a          vsub.f32   s4, s2, s0
00001ea2  33ee034a          vadd.f32   s8, s6, s6
00001ea6  0cee024a          vmla.f32   s8, s24, s4
00001eaa  30ee002a          vadd.f32   s4, s0, s0
00001eae  13ee290a          vnmls.f32  s0, s6, s19
00001eb2  03ee052a          vmla.f32   s4, s6, s10
00001eb6  b1ee005a          vmov.f32   s10, #4.000000e+00
00001eba  01ee2c0a          vmla.f32   s0, s2, s25
00001ebe  01ee052a          vmla.f32   s4, s2, s10
00001ec2  05ee100a          vmov       s10, r0
00001ec6  d4e90001          ldrd       r0, r1, [r4]
00001eca  b8eec55a          vcvt.f32.s32 s10, s10
00001ece  32ee452a          vsub.f32   s4, s4, s10
00001ed2  30ee050a          vadd.f32   s0, s0, s10
00001ed6  02ee2f4a          vmla.f32   s8, s4, s31
00001eda  00ee2e4a          vmla.f32   s8, s0, s29
00001ede  24ee2dca          vmul.f32   s24, s8, s27
00001ee2  00f03aed          blx        #0x2958
00001ee6  00ee100a          vmov       s0, r0
00001eea  0cee090a          vmla.f32   s0, s24, s18
00001eee  10ee100a          vmov       r0, s0
00001ef2  00f0a8ec          blx        #0x2844
00001ef6  38ee899a          vadd.f32   s18, s17, s18
00001efa  c4e90001          strd       r0, r1, [r4]
00001efe  00e0              b          #0x1f02
00001f00  0f9c              ldr        r4, [sp, #0x3c]
00001f02  0e99              ldr        r1, [sp, #0x38]
00001f04  5046              mov        r0, sl
00001f06  3bee0aab          vadd.f64   d10, d11, d10
00001f0a  0d46              mov        r5, r1
00001f0c  00f004ed          blx        #0x2918
00001f10  41ec300b          vmov       d16, r0, r1
00001f14  7aee200b          vadd.f64   d16, d10, d16
00001f18  51ec300b          vmov       r0, r1, d16
00001f1c  00f07aec          blx        #0x2814
00001f20  b5ee40ab          vcmp.f64   d10, #0
00001f24  8346              mov        fp, r0
00001f26  8846              mov        r8, r1
00001f28  f1ee10fa          vmrs       apsr_nzcv, fpscr
00001f2c  dcbf              itt        le
00001f2e  a846              movle      r8, r5
00001f30  d346              movle      fp, sl
00001f32  1298              ldr        r0, [sp, #0x48]
00001f34  0834              adds       r4, #8
00001f36  1099              ldr        r1, [sp, #0x40]
00001f38  0830              adds       r0, #8
00001f3a  1290              str        r0, [sp, #0x48]
00001f3c  0131              adds       r1, #1
00001f3e  a8e6              b          #0x1c92
00001f40  f24a              ldr        r2, [pc, #0x3c8]
00001f42  3046              mov        r0, r6
00001f44  f24b              ldr        r3, [pc, #0x3c8]
00001f46  4946              mov        r1, sb
00001f48  7a44              add        r2, pc
00001f4a  7b44              add        r3, pc
00001f4c  9a46              mov        sl, r3
00001f4e  a847              blx        r5
00001f50  0246              mov        r2, r0
00001f52  3068              ldr        r0, [r6]
00001f54  4146              mov        r1, r8
00001f56  d0f89031          ldr.w      r3, [r0, #0x190]
00001f5a  3046              mov        r0, r6
00001f5c  9847              blx        r3
00001f5e  0d90              str        r0, [sp, #0x34]
00001f60  4946              mov        r1, sb
00001f62  3068              ldr        r0, [r6]
00001f64  5346              mov        r3, sl
00001f66  d0f87851          ldr.w      r5, [r0, #0x178]
00001f6a  3046              mov        r0, r6
00001f6c  e94a              ldr        r2, [pc, #0x3a4]
00001f6e  7a44              add        r2, pc
00001f70  a847              blx        r5
00001f72  0246              mov        r2, r0
00001f74  3068              ldr        r0, [r6]
00001f76  4146              mov        r1, r8
00001f78  0792              str        r2, [sp, #0x1c]
00001f7a  d0f89031          ldr.w      r3, [r0, #0x190]
00001f7e  3046              mov        r0, r6
00001f80  9847              blx        r3
00001f82  8046              mov        r8, r0
00001f84  3068              ldr        r0, [r6]
00001f86  4946              mov        r1, sb
00001f88  5346              mov        r3, sl
00001f8a  d0f87851          ldr.w      r5, [r0, #0x178]
00001f8e  3046              mov        r0, r6
00001f90  e14a              ldr        r2, [pc, #0x384]
00001f92  7a44              add        r2, pc
00001f94  a847              blx        r5
00001f96  0246              mov        r2, r0
00001f98  3068              ldr        r0, [r6]
00001f9a  d7f80c90          ldr.w      sb, [r7, #0xc]
00001f9e  0692              str        r2, [sp, #0x18]
00001fa0  d0f89031          ldr.w      r3, [r0, #0x190]
00001fa4  3046              mov        r0, r6
00001fa6  4946              mov        r1, sb
00001fa8  9847              blx        r3
00001faa  0022              movs       r2, #0
00001fac  28eae871          bic.w      r1, r8, r8, asr #31
00001fb0  b8f1000f          cmp.w      r8, #0
00001fb4  0e91              str        r1, [sp, #0x38]
00001fb6  20eae071          bic.w      r1, r0, r0, asr #31
00001fba  0f91              str        r1, [sp, #0x3c]
00001fbc  d8bf              it         le
00001fbe  0128              cmple      r0, #1
00001fc0  c0f25181          blt.w      #0x2266
00001fc4  386a              ldr        r0, [r7, #0x20]
00001fc6  796a              ldr        r1, [r7, #0x24]
00001fc8  dde90ae8          ldrd       lr, r8, [sp, #0x28]
00001fcc  c043              mvns       r0, r0
00001fce  0844              add        r0, r1
00001fd0  0021              movs       r1, #0
00001fd2  0c90              str        r0, [sp, #0x30]
00001fd4  01f00106          and        r6, r1, #1
00001fd8  a242              cmp        r2, r4
00001fda  80f23d81          bge.w      #0x2258
00001fde  002e              cmp        r6, #0
00001fe0  40f03a81          bne.w      #0x2258
00001fe4  ddf840c0          ldr.w      ip, [sp, #0x40]
00001fe8  1391              str        r1, [sp, #0x4c]
00001fea  0c98              ldr        r0, [sp, #0x30]
00001fec  4feaec71          asr.w      r1, ip, #0x1f
00001ff0  d7f82090          ldr.w      sb, [r7, #0x20]
00001ff4  1bebd141          adds.w     r1, fp, r1, lsr #19
00001ff8  4cf10005          adc        r5, ip, #0
00001ffc  490b              lsrs       r1, r1, #0xd
00001ffe  bc6a              ldr        r4, [r7, #0x28]
00002000  41eac546          orr.w      r6, r1, r5, lsl #19
00002004  0021              movs       r1, #0
00002006  8519              adds       r5, r0, r6
00002008  4e45              cmp        r6, sb
0000200a  c8bf              it         gt
0000200c  0121              movgt      r1, #1
0000200e  002c              cmp        r4, #0
00002010  18bf              it         ne
00002012  0124              movne      r4, #1
00002014  14ea0100          ands.w     r0, r4, r1
00002018  4c46              mov        r4, sb
0000201a  1290              str        r0, [sp, #0x48]
0000201c  18bf              it         ne
0000201e  2e46              movne      r6, r5
00002020  4e45              cmp        r6, sb
00002022  b8bf              it         lt
00002024  3446              movlt      r4, r6
00002026  f86a              ldr        r0, [r7, #0x2c]
00002028  5946              mov        r1, fp
0000202a  ddf844b0          ldr.w      fp, [sp, #0x44]
0000202e  8440              lsls       r4, r0
00002030  0e98              ldr        r0, [sp, #0x38]
00002032  3bf914a0          ldrsh.w    sl, [fp, r4, lsl #1]
00002036  00fb0af0          mul        r0, r0, sl
0000203a  9246              mov        sl, r2
0000203c  d8e90032          ldrd       r3, r2, [r8]
00002040  1b18              adds       r3, r3, r0
00002042  42ebe070          adc.w      r0, r2, r0, asr #31
00002046  e8e80230          strd       r3, r0, [r8], #8
0000204a  0beb4400          add.w      r0, fp, r4, lsl #1
0000204e  8b46              mov        fp, r1
00002050  396b              ldr        r1, [r7, #0x30]
00002052  def80020          ldr.w      r2, [lr]
00002056  fc69              ldr        r4, [r7, #0x1c]
00002058  30f91100          ldrsh.w    r0, [r0, r1, lsl #1]
0000205c  0f99              ldr        r1, [sp, #0x3c]
0000205e  4843              muls       r0, r1, r0
00002060  1399              ldr        r1, [sp, #0x4c]
00002062  1218              adds       r2, r2, r0
00002064  cef80020          str.w      r2, [lr]
00002068  def80420          ldr.w      r2, [lr, #4]
0000206c  42ebe070          adc.w      r0, r2, r0, asr #31
00002070  cef80400          str.w      r0, [lr, #4]
00002074  4e45              cmp        r6, sb
00002076  c8bf              it         gt
00002078  0121              movgt      r1, #1
0000207a  e817              asrs       r0, r5, #0x1f
0000207c  129e              ldr        r6, [sp, #0x48]
0000207e  4003              lsls       r0, r0, #0xd
00002080  5246              mov        r2, sl
00002082  002e              cmp        r6, #0
00002084  1cbf              itt        ne
00002086  40ead54c          orrne.w    ip, r0, r5, lsr #19
0000208a  4fea453b          lslne.w    fp, r5, #0xd
0000208e  0d98              ldr        r0, [sp, #0x34]
00002090  0ef1080e          add.w      lr, lr, #8
00002094  0af10102          add.w      r2, sl, #1
00002098  1beb000b          adds.w     fp, fp, r0
0000209c  4cebe07c          adc.w      ip, ip, r0, asr #31
000020a0  cdf840c0          str.w      ip, [sp, #0x40]
000020a4  96e7              b          #0x1fd4
000020a6  0129              cmp        r1, #1
000020a8  40f0f280          bne.w      #0x2290
000020ac  396b              ldr        r1, [r7, #0x30]
000020ae  0023              movs       r3, #0
000020b0  1198              ldr        r0, [sp, #0x44]
000020b2  dde90a54          ldrd       r5, r4, [sp, #0x28]
000020b6  00eb4100          add.w      r0, r0, r1, lsl #1
000020ba  0e90              str        r0, [sp, #0x38]
000020bc  386a              ldr        r0, [r7, #0x20]
000020be  796a              ldr        r1, [r7, #0x24]
000020c0  c043              mvns       r0, r0
000020c2  9fedefea          vldr       s28, [pc, #0x3bc]
000020c6  0844              add        r0, r1
000020c8  0021              movs       r1, #0
000020ca  0f90              str        r0, [sp, #0x3c]
000020cc  f869              ldr        r0, [r7, #0x1c]
000020ce  03f00106          and        r6, r3, #1
000020d2  8142              cmp        r1, r0
000020d4  80f25981          bge.w      #0x238a
000020d8  002e              cmp        r6, #0
000020da  40f05681          bne.w      #0x238a
000020de  4feae870          asr.w      r0, r8, #0x1f
000020e2  1391              str        r1, [sp, #0x4c]
000020e4  1bebd040          adds.w     r0, fp, r0, lsr #19
000020e8  48f10001          adc        r1, r8, #0
000020ec  400b              lsrs       r0, r0, #0xd
000020ee  1295              str        r5, [sp, #0x48]
000020f0  40eac149          orr.w      sb, r0, r1, lsl #19
000020f4  b86a              ldr        r0, [r7, #0x28]
000020f6  c8b1              cbz        r0, #0x212c
000020f8  396a              ldr        r1, [r7, #0x20]
000020fa  8945              cmp        sb, r1
000020fc  1cdd              ble        #0x2138
000020fe  0f9a              ldr        r2, [sp, #0x3c]
00002100  b5ee409a          vcmp.f32   s18, #0
00002104  9144              add        sb, r2
00002106  4feae970          asr.w      r0, sb, #0x1f
0000210a  4fea493a          lsl.w      sl, sb, #0xd
0000210e  4003              lsls       r0, r0, #0xd
00002110  40ead948          orr.w      r8, r0, sb, lsr #19
00002114  f1ee10fa          vmrs       apsr_nzcv, fpscr
00002118  1846              mov        r0, r3
0000211a  b5ee408a          vcmp.f32   s16, #0
0000211e  98bf              it         ls
00002120  0120              movls      r0, #1
00002122  f1ee10fa          vmrs       apsr_nzcv, fpscr
00002126  98bf              it         ls
00002128  0346              movls      r3, r0
0000212a  07e0              b          #0x213c
0000212c  da46              mov        sl, fp
0000212e  d7f820b0          ldr.w      fp, [r7, #0x20]
00002132  09f10106          add.w      r6, sb, #1
00002136  07e0              b          #0x2148
00002138  da46              mov        sl, fp
0000213a  0f9a              ldr        r2, [sp, #0x3c]
0000213c  8945              cmp        sb, r1
0000213e  4846              mov        r0, sb
00002140  a8bf              it         ge
00002142  1044              addge      r0, r2
00002144  8b46              mov        fp, r1
00002146  461c              adds       r6, r0, #1
00002148  5046              mov        r0, sl
0000214a  4146              mov        r1, r8
0000214c  d945              cmp        sb, fp
0000214e  c8bf              it         gt
00002150  0123              movgt      r3, #1
00002152  1093              str        r3, [sp, #0x40]
00002154  00f000ec          blx        #0x2958
00002158  d945              cmp        sb, fp
0000215a  a8bf              it         ge
0000215c  d946              movge      sb, fp
0000215e  01ee109a          vmov       s2, sb
00002162  5e45              cmp        r6, fp
00002164  00ee100a          vmov       s0, r0
00002168  f86a              ldr        r0, [r7, #0x2c]
0000216a  b8eec1ca          vcvt.f32.s32 s24, s2
0000216e  b5ee408a          vcmp.f32   s16, #0
00002172  09fa00f5          lsl.w      r5, sb, r0
00002176  d8bf              it         le
00002178  b146              movle      sb, r6
0000217a  09fa00f6          lsl.w      r6, sb, r0
0000217e  f1ee10fa          vmrs       apsr_nzcv, fpscr
00002182  10ee0eca          vnmls.f32  s24, s0, s28
00002186  20dd              ble        #0x21ca
00002188  1199              ldr        r1, [sp, #0x44]
0000218a  31f91500          ldrsh.w    r0, [r1, r5, lsl #1]
0000218e  00ee100a          vmov       s0, r0
00002192  31f91600          ldrsh.w    r0, [r1, r6, lsl #1]
00002196  b8eec0fa          vcvt.f32.s32 s30, s0
0000219a  00ee100a          vmov       s0, r0
0000219e  d4e90001          ldrd       r0, r1, [r4]
000021a2  b8eec00a          vcvt.f32.s32 s0, s0
000021a6  30ee4f0a          vsub.f32   s0, s0, s30
000021aa  0cee00fa          vmla.f32   s30, s24, s0
000021ae  00f0d4eb          blx        #0x2958
000021b2  00ee100a          vmov       s0, r0
000021b6  0fee080a          vmla.f32   s0, s30, s16
000021ba  10ee100a          vmov       r0, s0
000021be  00f042eb          blx        #0x2844
000021c2  3dee088a          vadd.f32   s16, s26, s16
000021c6  c4e90001          strd       r0, r1, [r4]
000021ca  b5ee409a          vcmp.f32   s18, #0
000021ce  f1ee10fa          vmrs       apsr_nzcv, fpscr
000021d2  22dd              ble        #0x221a
000021d4  0e99              ldr        r1, [sp, #0x38]
000021d6  31f91500          ldrsh.w    r0, [r1, r5, lsl #1]
000021da  129d              ldr        r5, [sp, #0x48]
000021dc  00ee100a          vmov       s0, r0
000021e0  31f91600          ldrsh.w    r0, [r1, r6, lsl #1]
000021e4  b8eec0fa          vcvt.f32.s32 s30, s0
000021e8  00ee100a          vmov       s0, r0
000021ec  d5e90001          ldrd       r0, r1, [r5]
000021f0  b8eec00a          vcvt.f32.s32 s0, s0
000021f4  30ee4f0a          vsub.f32   s0, s0, s30
000021f8  0cee00fa          vmla.f32   s30, s24, s0
000021fc  00f0aceb          blx        #0x2958
00002200  00ee100a          vmov       s0, r0
00002204  0fee090a          vmla.f32   s0, s30, s18
00002208  10ee100a          vmov       r0, s0
0000220c  00f01aeb          blx        #0x2844
00002210  38ee899a          vadd.f32   s18, s17, s18
00002214  c5e90001          strd       r0, r1, [r5]
00002218  00e0              b          #0x221c
0000221a  129d              ldr        r5, [sp, #0x48]
0000221c  5046              mov        r0, sl
0000221e  4146              mov        r1, r8
00002220  3bee0aab          vadd.f64   d10, d11, d10
00002224  00f078eb          blx        #0x2918
00002228  41ec300b          vmov       d16, r0, r1
0000222c  7aee200b          vadd.f64   d16, d10, d16
00002230  51ec300b          vmov       r0, r1, d16
00002234  00f0eeea          blx        #0x2814
00002238  b5ee40ab          vcmp.f64   d10, #0
0000223c  8346              mov        fp, r0
0000223e  f1ee10fa          vmrs       apsr_nzcv, fpscr
00002242  d8bf              it         le
00002244  4146              movle      r1, r8
00002246  d8bf              it         le
00002248  d346              movle      fp, sl
0000224a  8846              mov        r8, r1
0000224c  1399              ldr        r1, [sp, #0x4c]
0000224e  0835              adds       r5, #8
00002250  0834              adds       r4, #8
00002252  109b              ldr        r3, [sp, #0x40]
00002254  0131              adds       r1, #1
00002256  39e7              b          #0x20cc
00002258  d7f80c90          ldr.w      sb, [r7, #0xc]
0000225c  04e0              b          #0x2268
0000225e  0026              movs       r6, #0
00002260  ddf84080          ldr.w      r8, [sp, #0x40]
00002264  c8e0              b          #0x23f8
00002266  0026              movs       r6, #0
00002268  099d              ldr        r5, [sp, #0x24]
0000226a  4946              mov        r1, sb
0000226c  079a              ldr        r2, [sp, #0x1c]
0000226e  0e9b              ldr        r3, [sp, #0x38]
00002270  2868              ldr        r0, [r5]
00002272  d0f8b441          ldr.w      r4, [r0, #0x1b4]
00002276  2846              mov        r0, r5
00002278  a047              blx        r4
0000227a  2868              ldr        r0, [r5]
0000227c  4946              mov        r1, sb
0000227e  069a              ldr        r2, [sp, #0x18]
00002280  0f9b              ldr        r3, [sp, #0x3c]
00002282  d0f8b441          ldr.w      r4, [r0, #0x1b4]
00002286  2846              mov        r0, r5
00002288  a047              blx        r4
0000228a  ddf84080          ldr.w      r8, [sp, #0x40]
0000228e  cfe0              b          #0x2430
00002290  396b              ldr        r1, [r7, #0x30]
00002292  0026              movs       r6, #0
00002294  1198              ldr        r0, [sp, #0x44]
00002296  0023              movs       r3, #0
00002298  d7f81ca0          ldr.w      sl, [r7, #0x1c]
0000229c  00eb4100          add.w      r0, r0, r1, lsl #1
000022a0  1390              str        r0, [sp, #0x4c]
000022a2  796a              ldr        r1, [r7, #0x24]
000022a4  784a              ldr        r2, [pc, #0x1e0]
000022a6  794c              ldr        r4, [pc, #0x1e4]
000022a8  c817              asrs       r0, r1, #0x1f
000022aa  4003              lsls       r0, r0, #0xd
000022ac  40ead14e          orr.w      lr, r0, r1, lsr #19
000022b0  7a44              add        r2, pc
000022b2  7c44              add        r4, pc
000022b4  4fea413c          lsl.w      ip, r1, #0xd
000022b8  5345              cmp        r3, sl
000022ba  69da              bge        #0x2390
000022bc  f507              lsls       r5, r6, #0x1f
000022be  67d1              bne        #0x2390
000022c0  4feae875          asr.w      r5, r8, #0x1f
000022c4  396a              ldr        r1, [r7, #0x20]
000022c6  1bebd545          adds.w     r5, fp, r5, lsr #19
000022ca  48f10006          adc        r6, r8, #0
000022ce  6d0b              lsrs       r5, r5, #0xd
000022d0  45eac645          orr.w      r5, r5, r6, lsl #19
000022d4  0026              movs       r6, #0
000022d6  8d42              cmp        r5, r1
000022d8  c8bf              it         gt
000022da  0126              movgt      r6, #1
000022dc  a8bf              it         ge
000022de  0d46              movge      r5, r1
000022e0  1fdd              ble        #0x2322
000022e2  b96a              ldr        r1, [r7, #0x28]
000022e4  e9b1              cbz        r1, #0x2322
000022e6  0026              movs       r6, #0
000022e8  b5ee408a          vcmp.f32   s16, #0
000022ec  f1ee10fa          vmrs       apsr_nzcv, fpscr
000022f0  14d8              bhi        #0x231c
000022f2  7d6a              ldr        r5, [r7, #0x24]
000022f4  b5ee409a          vcmp.f32   s18, #0
000022f8  e346              mov        fp, ip
000022fa  f046              mov        r8, lr
000022fc  f1ee10fa          vmrs       apsr_nzcv, fpscr
00002300  9ebf              ittt       ls
00002302  0126              movls      r6, #1
00002304  e346              movls      fp, ip
00002306  f046              movls      r8, lr
00002308  0be0              b          #0x2322
0000230a  00bf              nop        
0000230c  3aedffff          ldc        p15, c15, [sl, #-0x3fc]!
00002310  48edffff          stcl       p15, c15, [r8, #-0x3fc]
00002314  e9ecffff          stcl       p15, c15, [sb], #0x3fc
00002318  f7ecffff          ldcl       p15, c15, [r7], #0x3fc
0000231c  e346              mov        fp, ip
0000231e  f046              mov        r8, lr
00002320  7d6a              ldr        r5, [r7, #0x24]
00002322  f96a              ldr        r1, [r7, #0x2c]
00002324  b5ee408a          vcmp.f32   s16, #0
00002328  8d40              lsls       r5, r1
0000232a  f1ee10fa          vmrs       apsr_nzcv, fpscr
0000232e  10dd              ble        #0x2352
00002330  1199              ldr        r1, [sp, #0x44]
00002332  31f91510          ldrsh.w    r1, [r1, r5, lsl #1]
00002336  00ee101a          vmov       s0, r1
0000233a  b8eec00a          vcvt.f32.s32 s0, s0
0000233e  28ee000a          vmul.f32   s0, s16, s0
00002342  3dee088a          vadd.f32   s16, s26, s16
00002346  bdeec00a          vcvt.s32.f32 s0, s0
0000234a  10ee101a          vmov       r1, s0
0000234e  42f82310          str.w      r1, [r2, r3, lsl #2]
00002352  b5ee409a          vcmp.f32   s18, #0
00002356  f1ee10fa          vmrs       apsr_nzcv, fpscr
0000235a  10dd              ble        #0x237e
0000235c  1398              ldr        r0, [sp, #0x4c]
0000235e  30f91500          ldrsh.w    r0, [r0, r5, lsl #1]
00002362  00ee100a          vmov       s0, r0
00002366  b8eec00a          vcvt.f32.s32 s0, s0
0000236a  29ee000a          vmul.f32   s0, s18, s0
0000236e  38ee899a          vadd.f32   s18, s17, s18
00002372  bdeec00a          vcvt.s32.f32 s0, s0
00002376  10ee100a          vmov       r0, s0
0000237a  44f82300          str.w      r0, [r4, r3, lsl #2]
0000237e  1bf5005b          adds.w     fp, fp, #0x2000
00002382  48f10008          adc        r8, r8, #0
00002386  0133              adds       r3, #1
00002388  96e7              b          #0x22b8
0000238a  d7f80c90          ldr.w      sb, [r7, #0xc]
0000238e  33e0              b          #0x23f8
00002390  129d              ldr        r5, [sp, #0x48]
00002392  0f99              ldr        r1, [sp, #0x3c]
00002394  2943              orrs       r1, r5
00002396  29d0              beq        #0x23ec
00002398  dfed370b          vldr       d16, [pc, #0xdc]
0000239c  2846              mov        r0, r5
0000239e  cdf84080          str.w      r8, [sp, #0x40]
000023a2  4ff48063          mov.w      r3, #0x400
000023a6  6aee200b          vmul.f64   d16, d10, d16
000023aa  0b99              ldr        r1, [sp, #0x2c]
000023ac  cde90221          strd       r2, r1, [sp, #8]
000023b0  5246              mov        r2, sl
000023b2  3449              ldr        r1, [pc, #0xd0]
000023b4  0191              str        r1, [sp, #4]
000023b6  0421              movs       r1, #4
000023b8  0091              str        r1, [sp]
000023ba  b7eee00b          vcvt.f32.f64 s0, d16
000023be  10ee108a          vmov       r8, s0
000023c2  4146              mov        r1, r8
000023c4  00f0d4eb          blx        #0x2b70
000023c8  0420              movs       r0, #4
000023ca  4146              mov        r1, r8
000023cc  0090              str        r0, [sp]
000023ce  5246              mov        r2, sl
000023d0  2c48              ldr        r0, [pc, #0xb0]
000023d2  4ff48063          mov.w      r3, #0x400
000023d6  cde90104          strd       r0, r4, [sp, #4]
000023da  0a98              ldr        r0, [sp, #0x28]
000023dc  0390              str        r0, [sp, #0xc]
000023de  4bf20c00          movw       r0, #0xb00c
000023e2  2844              add        r0, r5
000023e4  ddf84080          ldr.w      r8, [sp, #0x40]
000023e8  00f0c2eb          blx        #0x2b70
000023ec  00ee10aa          vmov       s0, sl
000023f0  f8eec00b          vcvt.f64.s32 d16, s0
000023f4  0bee20ab          vmla.f64   d10, d11, d16
000023f8  099d              ldr        r5, [sp, #0x24]
000023fa  4946              mov        r1, sb
000023fc  079a              ldr        r2, [sp, #0x1c]
000023fe  2868              ldr        r0, [r5]
00002400  d0f8c031          ldr.w      r3, [r0, #0x1c0]
00002404  2846              mov        r0, r5
00002406  8ded00ab          vstr       d10, [sp]
0000240a  9847              blx        r3
0000240c  18ee103a          vmov       r3, s16
00002410  2868              ldr        r0, [r5]
00002412  069a              ldr        r2, [sp, #0x18]
00002414  4946              mov        r1, sb
00002416  d0f8bc41          ldr.w      r4, [r0, #0x1bc]
0000241a  2846              mov        r0, r5
0000241c  a047              blx        r4
0000241e  19ee103a          vmov       r3, s18
00002422  2868              ldr        r0, [r5]
00002424  059a              ldr        r2, [sp, #0x14]
00002426  4946              mov        r1, sb
00002428  d0f8bc41          ldr.w      r4, [r0, #0x1bc]
0000242c  2846              mov        r0, r5
0000242e  a047              blx        r4
00002430  2868              ldr        r0, [r5]
00002432  4946              mov        r1, sb
00002434  089a              ldr        r2, [sp, #0x20]
00002436  d0f8b831          ldr.w      r3, [r0, #0x1b8]
0000243a  2846              mov        r0, r5
0000243c  cde900b8          strd       fp, r8, [sp]
00002440  9847              blx        r3
00002442  2868              ldr        r0, [r5]
00002444  0023              movs       r3, #0
00002446  7969              ldr        r1, [r7, #0x14]
00002448  0b9a              ldr        r2, [sp, #0x2c]
0000244a  d0f81043          ldr.w      r4, [r0, #0x310]
0000244e  2846              mov        r0, r5
00002450  a047              blx        r4
00002452  2868              ldr        r0, [r5]
00002454  0023              movs       r3, #0
00002456  b969              ldr        r1, [r7, #0x18]
00002458  0a9a              ldr        r2, [sp, #0x28]
0000245a  d0f81043          ldr.w      r4, [r0, #0x310]
0000245e  2846              mov        r0, r5
00002460  a047              blx        r4
00002462  06f00100          and        r0, r6, #1
00002466  14b0              add        sp, #0x50
00002468  bdec108b          vpop       {d8, d9, d10, d11, d12, d13, d14, d15}
0000246c  01b0              add        sp, #4
0000246e  bde8000f          pop.w      {r8, sb, sl, fp}
00002472  f0bd              pop        {r4, r5, r6, r7, pc}
00002474  00bf              nop        
00002476  00bf              nop        
00002478  0000              movs       r0, r0
0000247a  0000              movs       r0, r0
0000247c  0000              movs       r0, r0
0000247e  203f              subs       r7, #0x20
00002480  0000              movs       r0, r0
00002482  0039              subs       r1, #0
00002484  0080              strh       r0, [r0]
00002486  3b47              bx         r7
00002488  502a              cmp        r2, #0x50
0000248a  0000              movs       r0, r0
0000248c  4e6a              ldr        r6, [r1, #0x24]
0000248e  0000              movs       r0, r0
