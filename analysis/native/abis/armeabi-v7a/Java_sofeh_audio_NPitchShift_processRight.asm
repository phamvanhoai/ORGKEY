# APK member: lib/armeabi-v7a/libnpitchshift.so
# Library SHA-256: a256c79829a2f84e4ab770889138f15e5541e0c39859a13df5d7082bfdb693c3
# Function: Java_sofeh_audio_NPitchShift_processRight; address=0x153c; bytes=80
# Decoder mode: thumb; decoded_bytes=80
0000153c  52ea0300          orrs.w     r0, r2, r3
00001540  02bf              ittt       eq
00001542  0020              moveq      r0, #0
00001544  0021              moveq      r1, #0
00001546  7047              bxeq       lr
00001548  e0b5              push       {r5, r6, r7, lr}
0000154a  02af              add        r7, sp, #8
0000154c  d7e90231          ldrd       r3, r1, [r7, #8]
00001550  00ee101a          vmov       s0, r1
00001554  9fed0c1a          vldr       s2, [pc, #0x30]
00001558  3869              ldr        r0, [r7, #0x10]
0000155a  b8eec00a          vcvt.f32.s32 s0, s0
0000155e  0190              str        r0, [sp, #4]
00001560  4bf20c00          movw       r0, #0xb00c
00001564  1044              add        r0, r2
00001566  4ff48062          mov.w      r2, #0x400
0000156a  8ded000a          vstr       s0, [sp]
0000156e  00ee103a          vmov       s0, r3
00001572  0423              movs       r3, #4
00001574  b8eec00a          vcvt.f32.s32 s0, s0
00001578  80ee010a          vdiv.f32   s0, s0, s2
0000157c  10ee101a          vmov       r1, s0
00001580  00f096ea          blx        #0x1ab0
00001584  c117              asrs       r1, r0, #0x1f
00001586  8cbd              pop        {r2, r3, r7, pc}
00001588  0000              movs       r0, r0
0000158a  c842              cmn        r0, r1
