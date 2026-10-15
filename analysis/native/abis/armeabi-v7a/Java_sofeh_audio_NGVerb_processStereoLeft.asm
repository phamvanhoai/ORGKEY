# APK member: lib/armeabi-v7a/libngverb.so
# Library SHA-256: f4be624ae221f556beaa4e0e3c9dd6b359e44e97d630f47aa9e74fe9a3990748
# Function: Java_sofeh_audio_NGVerb_processStereoLeft; address=0x17d4; bytes=276
# Decoder mode: thumb; decoded_bytes=276
000017d4  52ea0300          orrs.w     r0, r2, r3
000017d8  02bf              ittt       eq
000017da  0020              moveq      r0, #0
000017dc  0021              moveq      r1, #0
000017de  7047              bxeq       lr
000017e0  f0b5              push       {r4, r5, r6, r7, lr}
000017e2  03af              add        r7, sp, #0xc
000017e4  2de90007          push.w     {r8, sb, sl}
000017e8  2ded068b          vpush      {d8, d9, d10}
000017ec  1446              mov        r4, r2
000017ee  07f11002          add.w      r2, r7, #0x10
000017f2  9fed341a          vldr       s2, [pc, #0xd0]
000017f6  07ca              ldm        r2, {r0, r1, r2}
000017f8  00ee102a          vmov       s0, r2
000017fc  b8eec00a          vcvt.f32.s32 s0, s0
00001800  334a              ldr        r2, [pc, #0xcc]
00001802  d7e90265          ldrd       r6, r5, [r7, #8]
00001806  7a44              add        r2, pc
00001808  d2f80080          ldr.w      r8, [r2]
0000180c  80ee010a          vdiv.f32   s0, s0, s2
00001810  88ed000a          vstr       s0, [r8]
00001814  00f0b0ea          blx        #0x1d78
00001818  00ee100a          vmov       s0, r0
0000181c  9fed2a8a          vldr       s16, [pc, #0xa8]
00001820  2c48              ldr        r0, [pc, #0xb0]
00001822  2946              mov        r1, r5
00001824  20ee089a          vmul.f32   s18, s0, s16
00001828  7844              add        r0, pc
0000182a  d0f800a0          ldr.w      sl, [r0]
0000182e  3046              mov        r0, r6
00001830  8aed009a          vstr       s18, [sl]
00001834  00f0a0ea          blx        #0x1d78
00001838  00ee100a          vmov       s0, r0
0000183c  2648              ldr        r0, [pc, #0x98]
0000183e  20ee080a          vmul.f32   s0, s0, s16
00001842  7844              add        r0, pc
00001844  d0f80090          ldr.w      sb, [r0]
00001848  89ed000a          vstr       s0, [sb]
0000184c  30ee090a          vadd.f32   s0, s0, s18
00001850  2248              ldr        r0, [pc, #0x88]
00001852  7844              add        r0, pc
00001854  0568              ldr        r5, [r0]
00001856  2248              ldr        r0, [pc, #0x88]
00001858  7844              add        r0, pc
0000185a  2946              mov        r1, r5
0000185c  0668              ldr        r6, [r0]
0000185e  2046              mov        r0, r4
00001860  3246              mov        r2, r6
00001862  fff75ffe          bl         #0x1524
00001866  b7ee000a          vmov.f32   s0, #1.000000e+00
0000186a  98ed008a          vldr       s16, [r8]
0000186e  9aed001a          vldr       s2, [sl]
00001872  9fed16aa          vldr       s20, [pc, #0x58]
00001876  30ee489a          vsub.f32   s18, s0, s16
0000187a  96ed000a          vldr       s0, [r6]
0000187e  28ee000a          vmul.f32   s0, s16, s0
00001882  01ee090a          vmla.f32   s0, s2, s18
00001886  20ee0a0a          vmul.f32   s0, s0, s20
0000188a  10ee100a          vmov       r0, s0
0000188e  00f032ea          blx        #0x1cf4
00001892  144a              ldr        r2, [pc, #0x50]
00001894  95ed000a          vldr       s0, [r5]
00001898  99ed001a          vldr       s2, [sb]
0000189c  7a44              add        r2, pc
0000189e  28ee000a          vmul.f32   s0, s16, s0
000018a2  1268              ldr        r2, [r2]
000018a4  c2e90001          strd       r0, r1, [r2]
000018a8  01ee090a          vmla.f32   s0, s2, s18
000018ac  20ee0a0a          vmul.f32   s0, s0, s20
000018b0  10ee100a          vmov       r0, s0
000018b4  00f01eea          blx        #0x1cf4
000018b8  bdec068b          vpop       {d8, d9, d10}
000018bc  bde80007          pop.w      {r8, sb, sl}
000018c0  f0bd              pop        {r4, r5, r6, r7, pc}
000018c2  00bf              nop        
000018c4  0000              movs       r0, r0
000018c6  c842              cmn        r0, r1
000018c8  0000              movs       r0, r0
000018ca  0038              subs       r0, #0
000018cc  0000              movs       r0, r0
000018ce  0047              bx         r0
000018d0  8a1b              subs       r2, r1, r6
000018d2  0000              movs       r0, r0
000018d4  6c1b              subs       r4, r5, r5
000018d6  0000              movs       r0, r0
000018d8  561b              subs       r6, r2, r5
000018da  0000              movs       r0, r0
000018dc  4a1b              subs       r2, r1, r5
000018de  0000              movs       r0, r0
000018e0  481b              subs       r0, r1, r5
000018e2  0000              movs       r0, r0
000018e4  081b              subs       r0, r1, r4
000018e6  0000              movs       r0, r0
