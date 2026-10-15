# APK member: lib/armeabi-v7a/libnpitchshift.so
# Library SHA-256: a256c79829a2f84e4ab770889138f15e5541e0c39859a13df5d7082bfdb693c3
# Function: Java_sofeh_audio_NPitchShift_process; address=0x158c; bytes=80
# Decoder mode: thumb; decoded_bytes=80
0000158c  52ea0300          orrs.w     r0, r2, r3
00001590  1ed0              beq        #0x15d0
00001592  e0b5              push       {r5, r6, r7, lr}
00001594  02af              add        r7, sp, #8
00001596  d7e90231          ldrd       r3, r1, [r7, #8]
0000159a  00ee101a          vmov       s0, r1
0000159e  9fed0e1a          vldr       s2, [pc, #0x38]
000015a2  3869              ldr        r0, [r7, #0x10]
000015a4  b8eec00a          vcvt.f32.s32 s0, s0
000015a8  0190              str        r0, [sp, #4]
000015aa  1046              mov        r0, r2
000015ac  4ff48062          mov.w      r2, #0x400
000015b0  8ded000a          vstr       s0, [sp]
000015b4  00ee103a          vmov       s0, r3
000015b8  0423              movs       r3, #4
000015ba  b8eec00a          vcvt.f32.s32 s0, s0
000015be  80ee010a          vdiv.f32   s0, s0, s2
000015c2  10ee101a          vmov       r1, s0
000015c6  00f074ea          blx        #0x1ab0
000015ca  bde88c40          pop.w      {r2, r3, r7, lr}
000015ce  00e0              b          #0x15d2
000015d0  0020              movs       r0, #0
000015d2  00b2              sxth       r0, r0
000015d4  7047              bx         lr
000015d6  00bf              nop        
000015d8  0000              movs       r0, r0
000015da  c842              cmn        r0, r1
