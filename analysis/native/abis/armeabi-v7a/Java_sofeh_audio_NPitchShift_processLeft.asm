# APK member: lib/armeabi-v7a/libnpitchshift.so
# Library SHA-256: a256c79829a2f84e4ab770889138f15e5541e0c39859a13df5d7082bfdb693c3
# Function: Java_sofeh_audio_NPitchShift_processLeft; address=0x14f0; bytes=76
# Decoder mode: thumb; decoded_bytes=76
000014f0  52ea0300          orrs.w     r0, r2, r3
000014f4  02bf              ittt       eq
000014f6  0020              moveq      r0, #0
000014f8  0021              moveq      r1, #0
000014fa  7047              bxeq       lr
000014fc  e0b5              push       {r5, r6, r7, lr}
000014fe  02af              add        r7, sp, #8
00001500  d7e90231          ldrd       r3, r1, [r7, #8]
00001504  00ee101a          vmov       s0, r1
00001508  9fed0b1a          vldr       s2, [pc, #0x2c]
0000150c  3869              ldr        r0, [r7, #0x10]
0000150e  b8eec00a          vcvt.f32.s32 s0, s0
00001512  0190              str        r0, [sp, #4]
00001514  1046              mov        r0, r2
00001516  4ff48062          mov.w      r2, #0x400
0000151a  8ded000a          vstr       s0, [sp]
0000151e  00ee103a          vmov       s0, r3
00001522  0423              movs       r3, #4
00001524  b8eec00a          vcvt.f32.s32 s0, s0
00001528  80ee010a          vdiv.f32   s0, s0, s2
0000152c  10ee101a          vmov       r1, s0
00001530  00f0beea          blx        #0x1ab0
00001534  c117              asrs       r1, r0, #0x1f
00001536  8cbd              pop        {r2, r3, r7, pc}
00001538  0000              movs       r0, r0
0000153a  c842              cmn        r0, r1
