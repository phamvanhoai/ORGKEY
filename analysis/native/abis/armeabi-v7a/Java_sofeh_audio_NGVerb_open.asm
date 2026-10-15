# APK member: lib/armeabi-v7a/libngverb.so
# Library SHA-256: f4be624ae221f556beaa4e0e3c9dd6b359e44e97d630f47aa9e74fe9a3990748
# Function: Java_sofeh_audio_NGVerb_open; address=0x144a; bytes=100
# Decoder mode: thumb; decoded_bytes=100
0000144a  d0b5              push       {r4, r6, r7, lr}
0000144c  02af              add        r7, sp, #8
0000144e  2ded165b          vpush      {d5, d6, d7, d8, d9, d10, d11, d12, d13, d14, d15}
00001452  52ea0300          orrs.w     r0, r2, r3
00001456  97ed0a8a          vldr       s16, [r7, #0x28]
0000145a  97ed09aa          vldr       s20, [r7, #0x24]
0000145e  97ed08ca          vldr       s24, [r7, #0x20]
00001462  97ed07ea          vldr       s28, [r7, #0x1c]
00001466  97ed06fa          vldr       s30, [r7, #0x18]
0000146a  97ed059a          vldr       s18, [r7, #0x14]
0000146e  97ed04ba          vldr       s22, [r7, #0x10]
00001472  97ed03da          vldr       s26, [r7, #0xc]
00001476  bc68              ldr        r4, [r7, #8]
00001478  1cbf              itt        ne
0000147a  1046              movne      r0, r2
0000147c  00f0b8ee          blxne      #0x21f0
00001480  1dee101a          vmov       r1, s26
00001484  2046              mov        r0, r4
00001486  1bee102a          vmov       r2, s22
0000148a  8ded00fa          vstr       s30, [sp]
0000148e  19ee103a          vmov       r3, s18
00001492  8ded01ea          vstr       s28, [sp, #4]
00001496  8ded02ca          vstr       s24, [sp, #8]
0000149a  8ded03aa          vstr       s20, [sp, #0xc]
0000149e  8ded048a          vstr       s16, [sp, #0x10]
000014a2  00f06eee          blx        #0x2180
000014a6  0021              movs       r1, #0
000014a8  bdec165b          vpop       {d5, d6, d7, d8, d9, d10, d11, d12, d13, d14, d15}
000014ac  d0bd              pop        {r4, r6, r7, pc}
