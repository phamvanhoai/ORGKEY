# APK member: lib/armeabi-v7a/libngverb.so
# Library SHA-256: f4be624ae221f556beaa4e0e3c9dd6b359e44e97d630f47aa9e74fe9a3990748
# Function: Java_sofeh_audio_NGVerb_processMono; address=0x18f8; bytes=192
# Decoder mode: thumb; decoded_bytes=192
000018f8  52ea0300          orrs.w     r0, r2, r3
000018fc  02bf              ittt       eq
000018fe  0020              moveq      r0, #0
00001900  0021              moveq      r1, #0
00001902  7047              bxeq       lr
00001904  f0b5              push       {r4, r5, r6, r7, lr}
00001906  03af              add        r7, sp, #0xc
00001908  2de9000b          push.w     {r8, sb, fp}
0000190c  1446              mov        r4, r2
0000190e  07f10802          add.w      r2, r7, #8
00001912  9fed221a          vldr       s2, [pc, #0x88]
00001916  07ca              ldm        r2, {r0, r1, r2}
00001918  00ee102a          vmov       s0, r2
0000191c  b8eec00a          vcvt.f32.s32 s0, s0
00001920  214a              ldr        r2, [pc, #0x84]
00001922  7a44              add        r2, pc
00001924  d2f80080          ldr.w      r8, [r2]
00001928  80ee010a          vdiv.f32   s0, s0, s2
0000192c  88ed000a          vstr       s0, [r8]
00001930  00f022ea          blx        #0x1d78
00001934  00ee100a          vmov       s0, r0
00001938  9fed191a          vldr       s2, [pc, #0x64]
0000193c  1b48              ldr        r0, [pc, #0x6c]
0000193e  20ee010a          vmul.f32   s0, s0, s2
00001942  7844              add        r0, pc
00001944  d0f80090          ldr.w      sb, [r0]
00001948  89ed000a          vstr       s0, [sb]
0000194c  1848              ldr        r0, [pc, #0x60]
0000194e  7844              add        r0, pc
00001950  0568              ldr        r5, [r0]
00001952  1848              ldr        r0, [pc, #0x60]
00001954  7844              add        r0, pc
00001956  2946              mov        r1, r5
00001958  0668              ldr        r6, [r0]
0000195a  2046              mov        r0, r4
0000195c  3246              mov        r2, r6
0000195e  fff7e1fd          bl         #0x1524
00001962  96ed002a          vldr       s4, [r6]
00001966  b7ee001a          vmov.f32   s2, #1.000000e+00
0000196a  95ed003a          vldr       s6, [r5]
0000196e  98ed000a          vldr       s0, [r8]
00001972  33ee022a          vadd.f32   s4, s6, s4
00001976  31ee401a          vsub.f32   s2, s2, s0
0000197a  20ee020a          vmul.f32   s0, s0, s4
0000197e  99ed002a          vldr       s4, [sb]
00001982  02ee010a          vmla.f32   s0, s4, s2
00001986  9fed071a          vldr       s2, [pc, #0x1c]
0000198a  20ee010a          vmul.f32   s0, s0, s2
0000198e  10ee100a          vmov       r0, s0
00001992  00f0b0e9          blx        #0x1cf4
00001996  bde8000b          pop.w      {r8, sb, fp}
0000199a  f0bd              pop        {r4, r5, r6, r7, pc}
0000199c  0000              movs       r0, r0
0000199e  c842              cmn        r0, r1
000019a0  0000              movs       r0, r0
000019a2  0038              subs       r0, #0
000019a4  0000              movs       r0, r0
000019a6  0047              bx         r0
000019a8  6e1a              subs       r6, r5, r1
000019aa  0000              movs       r0, r0
000019ac  561a              subs       r6, r2, r1
000019ae  0000              movs       r0, r0
000019b0  4e1a              subs       r6, r1, r1
000019b2  0000              movs       r0, r0
000019b4  4c1a              subs       r4, r1, r1
000019b6  0000              movs       r0, r0
