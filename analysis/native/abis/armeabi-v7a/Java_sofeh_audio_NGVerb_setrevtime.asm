# APK member: lib/armeabi-v7a/libngverb.so
# Library SHA-256: f4be624ae221f556beaa4e0e3c9dd6b359e44e97d630f47aa9e74fe9a3990748
# Function: Java_sofeh_audio_NGVerb_setrevtime; address=0x1b48; bytes=172
# Decoder mode: thumb; decoded_bytes=172
00001b48  52ea0300          orrs.w     r0, r2, r3
00001b4c  4bd0              beq        #0x1be6
00001b4e  f0b5              push       {r4, r5, r6, r7, lr}
00001b50  03af              add        r7, sp, #0xc
00001b52  4df8048d          str        r8, [sp, #-0x4]!
00001b56  2ded048b          vpush      {d8, d9}
00001b5a  92ed001a          vldr       s2, [r2]
00001b5e  1546              mov        r5, r2
00001b60  97ed020a          vldr       s0, [r7, #8]
00001b64  b8eec11a          vcvt.f32.s32 s2, s2
00001b68  1f48              ldr        r0, [pc, #0x7c]
00001b6a  82ed070a          vstr       s0, [r2, #0x1c]
00001b6e  21ee000a          vmul.f32   s0, s2, s0
00001b72  b7ee001a          vmov.f32   s2, #1.000000e+00
00001b76  81ee000a          vdiv.f32   s0, s2, s0
00001b7a  10ee101a          vmov       r1, s0
00001b7e  00f030eb          blx        #0x21e0
00001b82  00ee100a          vmov       s0, r0
00001b86  9fed198a          vldr       s16, [pc, #0x64]
00001b8a  9fed199a          vldr       s18, [pc, #0x64]
00001b8e  8046              mov        r8, r0
00001b90  f7eec00a          vcvt.f64.f32 d16, s0
00001b94  0024              movs       r4, #0
00001b96  c5ed180b          vstr       d16, [r5, #0x60]
00001b9a  d5e90b65          ldrd       r6, r5, [r5, #0x2c]
00001b9e  102c              cmp        r4, #0x10
00001ba0  1bd0              beq        #0x1bda
00001ba2  2819              adds       r0, r5, r4
00001ba4  90ed000a          vldr       s0, [r0]
00001ba8  4046              mov        r0, r8
00001baa  b8eec00a          vcvt.f32.s32 s0, s0
00001bae  10ee101a          vmov       r1, s0
00001bb2  00f016eb          blx        #0x21e0
00001bb6  00ee100a          vmov       s0, r0
00001bba  b4ee480a          vcmp.f32   s0, s16
00001bbe  b1ee401a          vneg.f32   s2, s0
00001bc2  f1ee10fa          vmrs       apsr_nzcv, fpscr
00001bc6  b0ee490a          vmov.f32   s0, s18
00001bca  48bf              it         mi
00001bcc  b0ee410a          vmovmi.f32 s0, s2
00001bd0  3019              adds       r0, r6, r4
00001bd2  0434              adds       r4, #4
00001bd4  80ed000a          vstr       s0, [r0]
00001bd8  e1e7              b          #0x1b9e
00001bda  bdec048b          vpop       {d8, d9}
00001bde  5df8048b          ldr        r8, [sp], #4
00001be2  bde8f040          pop.w      {r4, r5, r6, r7, lr}
00001be6  7047              bx         lr
00001be8  6f12              asrs       r7, r5, #9
00001bea  833a              subs       r2, #0x83
00001bec  8fc2              stm        r2!, {r0, r1, r2, r3, r7}
00001bee  753f              subs       r7, #0x75
00001bf0  8fc2              stm        r2!, {r0, r1, r2, r3, r7}
00001bf2  75bf              itete      vc
