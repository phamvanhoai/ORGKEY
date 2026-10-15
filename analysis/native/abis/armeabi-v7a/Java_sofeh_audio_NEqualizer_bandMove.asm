# APK member: lib/armeabi-v7a/libnequalizer.so
# Library SHA-256: 43a844f1021b0ecaefd1ef5590ed454b77eb391a5a623bbafb0e7fc971f4108a
# Function: Java_sofeh_audio_NEqualizer_bandMove; address=0x6a8; bytes=72
# Decoder mode: thumb; decoded_bytes=72
000006a8  52ea0300          orrs.w     r0, r2, r3
000006ac  08bf              it         eq
000006ae  7047              bxeq       lr
000006b0  9ded000a          vldr       s0, [sp]
000006b4  9fed0d1a          vldr       s2, [pc, #0x34]
000006b8  30ee012a          vadd.f32   s4, s0, s2
000006bc  31ee400a          vsub.f32   s0, s2, s0
000006c0  bdeec21a          vcvt.s32.f32 s2, s4
000006c4  bdeec00a          vcvt.s32.f32 s0, s0
000006c8  82ed041a          vstr       s2, [r2, #0x10]
000006cc  82ed000a          vstr       s0, [r2]
000006d0  82ed010a          vstr       s0, [r2, #4]
000006d4  82ed020a          vstr       s0, [r2, #8]
000006d8  82ed030a          vstr       s0, [r2, #0xc]
000006dc  82ed051a          vstr       s2, [r2, #0x14]
000006e0  82ed061a          vstr       s2, [r2, #0x18]
000006e4  82ed071a          vstr       s2, [r2, #0x1c]
000006e8  7047              bx         lr
000006ea  00bf              nop        
000006ec  0000              movs       r0, r0
000006ee  c842              cmn        r0, r1
