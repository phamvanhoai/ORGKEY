# APK member: lib/armeabi-v7a/libngverb.so
# Library SHA-256: f4be624ae221f556beaa4e0e3c9dd6b359e44e97d630f47aa9e74fe9a3990748
# Function: Java_sofeh_audio_NGVerb_setroomsize; address=0x19b8; bytes=400
# Decoder mode: thumb; decoded_bytes=344
000019b8  52ea0300          orrs.w     r0, r2, r3
000019bc  00f09780          beq.w      #0x1aee
000019c0  f0b5              push       {r4, r5, r6, r7, lr}
000019c2  03af              add        r7, sp, #0xc
000019c4  2de9000b          push.w     {r8, sb, fp}
000019c8  2ded088b          vpush      {d8, d9, d10, d11}
000019cc  b7ee000a          vmov.f32   s0, #1.000000e+00
000019d0  97ed021a          vldr       s2, [r7, #8]
000019d4  9046              mov        r8, r2
000019d6  b4ee401a          vcmp.f32   s2, s0
000019da  f1ee10fa          vmrs       apsr_nzcv, fpscr
000019de  06d9              bls        #0x19ee
000019e0  b4ee411a          vcmp.f32   s2, s2
000019e4  f1ee10fa          vmrs       apsr_nzcv, fpscr
000019e8  78bf              it         vc
000019ea  b0ee410a          vmovvc.f32 s0, s2
000019ee  98ed001a          vldr       s2, [r8]
000019f2  3fa0              adr        r0, #0xfc
000019f4  88ed060a          vstr       s0, [r8, #0x18]
000019f8  0026              movs       r6, #0
000019fa  b8eec11a          vcvt.f32.s32 s2, s2
000019fe  9fed4d2a          vldr       s4, [pc, #0x134]
00001a02  20f9cf8a          vld1.64    {d8, d9}, [r0]
00001a06  3ea0              adr        r0, #0xf8
00001a08  60f9cf2a          vld1.64    {d18, d19}, [r0]
00001a0c  d8f83090          ldr.w      sb, [r8, #0x30]
00001a10  d8f82c40          ldr.w      r4, [r8, #0x2c]
00001a14  20ee010a          vmul.f32   s0, s0, s2
00001a18  9fed451a          vldr       s2, [pc, #0x114]
00001a1c  9fed48aa          vldr       s20, [pc, #0x120]
00001a20  9fed48ba          vldr       s22, [pc, #0x120]
00001a24  20ee010a          vmul.f32   s0, s0, s2
00001a28  88ed090a          vstr       s0, [r8, #0x24]
00001a2c  60ee021a          vmul.f32   s3, s0, s4
00001a30  9fed412a          vldr       s4, [pc, #0x104]
00001a34  20ee021a          vmul.f32   s2, s0, s4
00001a38  9fed402a          vldr       s4, [pc, #0x100]
00001a3c  60ee020a          vmul.f32   s1, s0, s4
00001a40  40ef480d          vadd.f32   q8, q0, q4
00001a44  60efe208          vadd.i32   q8, q8, q9
00001a48  49f98f0a          vst1.32    {d16, d17}, [sb]
00001a4c  d8ed180b          vldr       d16, [r8, #0x60]
00001a50  b7eee00b          vcvt.f32.f64 s0, d16
00001a54  10ee105a          vmov       r5, s0
00001a58  102e              cmp        r6, #0x10
00001a5a  1cd0              beq        #0x1a96
00001a5c  09eb0600          add.w      r0, sb, r6
00001a60  90ed000a          vldr       s0, [r0]
00001a64  2846              mov        r0, r5
00001a66  b8eec00a          vcvt.f32.s32 s0, s0
00001a6a  10ee101a          vmov       r1, s0
00001a6e  00f0b8eb          blx        #0x21e0
00001a72  00ee100a          vmov       s0, r0
00001a76  b4ee4a0a          vcmp.f32   s0, s20
00001a7a  b1ee401a          vneg.f32   s2, s0
00001a7e  f1ee10fa          vmrs       apsr_nzcv, fpscr
00001a82  b0ee4b0a          vmov.f32   s0, s22
00001a86  48bf              it         mi
00001a88  b0ee410a          vmovmi.f32 s0, s2
00001a8c  a019              adds       r0, r4, r6
00001a8e  0436              adds       r6, #4
00001a90  80ed000a          vstr       s0, [r0]
00001a94  e0e7              b          #0x1a58
00001a96  08f12400          add.w      r0, r8, #0x24
00001a9a  2421              movs       r1, #0x24
00001a9c  d8f84c60          ldr.w      r6, [r8, #0x4c]
00001aa0  0024              movs       r4, #0
00001aa2  e0f9b10c          vld1.32    {d16[], d17[]}, [r0:0x20], r1
00001aa6  1aa1              adr        r1, #0x68
00001aa8  61f9cf2a          vld1.64    {d18, d19}, [r1]
00001aac  1ca1              adr        r1, #0x70
00001aae  d0f80090          ldr.w      sb, [r0]
00001ab2  00eff28d          vmla.f32   q4, q8, q9
00001ab6  61f9cf0a          vld1.64    {d16, d17}, [r1]
00001aba  68ef6008          vadd.i32   q8, q4, q8
00001abe  49f98f0a          vst1.32    {d16, d17}, [sb]
00001ac2  102c              cmp        r4, #0x10
00001ac4  0dd0              beq        #0x1ae2
00001ac6  09eb0400          add.w      r0, sb, r4
00001aca  90ed000a          vldr       s0, [r0]
00001ace  2846              mov        r0, r5
00001ad0  b8eec00a          vcvt.f32.s32 s0, s0
00001ad4  10ee101a          vmov       r1, s0
00001ad8  00f082eb          blx        #0x21e0
00001adc  3051              str        r0, [r6, r4]
00001ade  0434              adds       r4, #4
00001ae0  efe7              b          #0x1ac2
00001ae2  bdec088b          vpop       {d8, d9, d10, d11}
00001ae6  bde8000b          pop.w      {r8, sb, fp}
00001aea  bde8f040          pop.w      {r4, r5, r6, r7, lr}
00001aee  7047              bx         lr
00001af0  0000              movs       r0, r0
00001af2  404b              ldr        r3, [pc, #0x100]
00001af4  0000              movs       r0, r0
00001af6  404b              ldr        r3, [pc, #0x100]
00001af8  0000              movs       r0, r0
00001afa  404b              ldr        r3, [pc, #0x100]
00001afc  0000              movs       r0, r0
00001afe  404b              ldr        r3, [pc, #0x100]
00001b00  0000              movs       r0, r0
00001b02  c0b4              push       {r6, r7}
00001b04  0000              movs       r0, r0
00001b06  c0b4              push       {r6, r7}
00001b08  0000              movs       r0, r0
00001b0a  c0b4              push       {r6, r7}
00001b0c  0000              movs       r0, r0
00001b0e  c0b4              push       {r6, r7}
# Undecoded tail: offset=0x158; bytes=56
# Raw hex: 85ebd13e9a99993e52b81e3e000000000500c0b40500c0b40500c0b40500c0b404ad403b3ee8213f8104353f7d05513f8fc2753f8fc275bf
