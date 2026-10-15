# APK member: lib/armeabi-v7a/libnmusic.so
# Library SHA-256: bd2a4a0e17aaa14c558d5f96ba1ac387097bd96c2bdd771c287261dd8fdcc77e
# Function: Java_sofeh_music_NMusic_Mix2; address=0x197c; bytes=156
# Decoder mode: thumb; decoded_bytes=156
0000197c  b0b5              push       {r4, r5, r7, lr}
0000197e  02af              add        r7, sp, #8
00001980  d7e90601          ldrd       r0, r1, [r7, #0x18]
00001984  1d46              mov        r5, r3
00001986  1446              mov        r4, r2
00001988  00f0e6ef          blx        #0x2958
0000198c  97ed081a          vldr       s2, [r7, #0x20]
00001990  00ee100a          vmov       s0, r0
00001994  9fed1f2a          vldr       s4, [pc, #0x7c]
00001998  b9ee046a          vmov.f32   s12, #-5.000000e+00
0000199c  b8eec11a          vcvt.f32.s32 s2, s2
000019a0  97ed024a          vldr       s8, [r7, #8]
000019a4  34ee045a          vadd.f32   s10, s8, s8
000019a8  10ee021a          vnmls.f32  s2, s0, s4
000019ac  97ed032a          vldr       s4, [r7, #0xc]
000019b0  00ee105a          vmov       s0, r5
000019b4  32ee403a          vsub.f32   s6, s4, s0
000019b8  21ee017a          vmul.f32   s14, s2, s2
000019bc  03ee015a          vmla.f32   s10, s6, s2
000019c0  30ee003a          vadd.f32   s6, s0, s0
000019c4  21ee071a          vmul.f32   s2, s2, s14
000019c8  04ee063a          vmla.f32   s6, s8, s12
000019cc  b1ee006a          vmov.f32   s12, #4.000000e+00
000019d0  02ee063a          vmla.f32   s6, s4, s12
000019d4  97ed046a          vldr       s12, [r7, #0x10]
000019d8  33ee463a          vsub.f32   s6, s6, s12
000019dc  03ee075a          vmla.f32   s10, s6, s14
000019e0  b0ee083a          vmov.f32   s6, #3.000000e+00
000019e4  14ee030a          vnmls.f32  s0, s8, s6
000019e8  b8ee083a          vmov.f32   s6, #-3.000000e+00
000019ec  02ee030a          vmla.f32   s0, s4, s6
000019f0  30ee060a          vadd.f32   s0, s0, s12
000019f4  00ee015a          vmla.f32   s10, s0, s2
000019f8  b6ee000a          vmov.f32   s0, #5.000000e-01
000019fc  01ee104a          vmov       s2, r4
00001a00  25ee000a          vmul.f32   s0, s10, s0
00001a04  20ee010a          vmul.f32   s0, s0, s2
00001a08  bdeec00a          vcvt.s32.f32 s0, s0
00001a0c  10ee100a          vmov       r0, s0
00001a10  b0bd              pop        {r4, r5, r7, pc}
00001a12  00bf              nop        
00001a14  0000              movs       r0, r0
00001a16  0039              subs       r1, #0
