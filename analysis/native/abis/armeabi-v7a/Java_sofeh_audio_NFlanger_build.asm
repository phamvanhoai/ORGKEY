# APK member: lib/armeabi-v7a/libnflanger.so
# Library SHA-256: 4d4fabc33a8e9d24417f86d86f0e51c250d4ce0cc77fc1056a4f23b59e289534
# Function: Java_sofeh_audio_NFlanger_build; address=0x650; bytes=104
# Decoder mode: thumb; decoded_bytes=104
00000650  52ea0300          orrs.w     r0, r2, r3
00000654  08bf              it         eq
00000656  7047              bxeq       lr
00000658  b0b5              push       {r4, r5, r7, lr}
0000065a  02af              add        r7, sp, #8
0000065c  d7e906c1          ldrd       ip, r1, [r7, #0x18]
00000660  00ee101a          vmov       s0, r1
00000664  386a              ldr        r0, [r7, #0x20]
00000666  9fed131a          vldr       s2, [pc, #0x4c]
0000066a  b8eec00a          vcvt.f32.s32 s0, s0
0000066e  d7e904e3          ldrd       lr, r3, [r7, #0x10]
00000672  02ee100a          vmov       s4, r0
00000676  46f28140          movw       r0, #0x6481
0000067a  c4f25620          movt       r0, #0x4256
0000067e  d7e90254          ldrd       r5, r4, [r7, #8]
00000682  b8eec22a          vcvt.f32.s32 s4, s4
00000686  c2f83008          str.w      r0, [r2, #0x830]
0000068a  02f61c00          addw       r0, r2, #0x81c
0000068e  ff2b              cmp        r3, #0xff
00000690  a8bf              it         ge
00000692  ff23              movge      r3, #0xff
00000694  c2e90054          strd       r5, r4, [r2]
00000698  c2e902e3          strd       lr, r3, [r2, #8]
0000069c  20ee010a          vmul.f32   s0, s0, s2
000006a0  c2f810c0          str.w      ip, [r2, #0x10]
000006a4  80ee020a          vdiv.f32   s0, s0, s4
000006a8  80ed000a          vstr       s0, [r0]
000006ac  bde8b040          pop.w      {r4, r5, r7, lr}
000006b0  7047              bx         lr
000006b2  00bf              nop        
000006b4  7cd9              bls        #0x7b0
000006b6  203e              subs       r6, #0x20
