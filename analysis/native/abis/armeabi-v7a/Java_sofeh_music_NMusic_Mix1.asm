# APK member: lib/armeabi-v7a/libnmusic.so
# Library SHA-256: bd2a4a0e17aaa14c558d5f96ba1ac387097bd96c2bdd771c287261dd8fdcc77e
# Function: Java_sofeh_music_NMusic_Mix1; address=0x1930; bytes=76
# Decoder mode: thumb; decoded_bytes=76
00001930  b0b5              push       {r4, r5, r7, lr}
00001932  02af              add        r7, sp, #8
00001934  d7e90401          ldrd       r0, r1, [r7, #0x10]
00001938  1c46              mov        r4, r3
0000193a  1546              mov        r5, r2
0000193c  01f00ce8          blx        #0x2958
00001940  97ed061a          vldr       s2, [r7, #0x18]
00001944  00ee100a          vmov       s0, r0
00001948  9fed0b2a          vldr       s4, [pc, #0x2c]
0000194c  b8eec11a          vcvt.f32.s32 s2, s2
00001950  10ee021a          vnmls.f32  s2, s0, s4
00001954  97ed022a          vldr       s4, [r7, #8]
00001958  00ee104a          vmov       s0, r4
0000195c  32ee402a          vsub.f32   s4, s4, s0
00001960  01ee020a          vmla.f32   s0, s2, s4
00001964  01ee105a          vmov       s2, r5
00001968  20ee010a          vmul.f32   s0, s0, s2
0000196c  bdeec00a          vcvt.s32.f32 s0, s0
00001970  10ee100a          vmov       r0, s0
00001974  b0bd              pop        {r4, r5, r7, pc}
00001976  00bf              nop        
00001978  0000              movs       r0, r0
0000197a  0039              subs       r1, #0
