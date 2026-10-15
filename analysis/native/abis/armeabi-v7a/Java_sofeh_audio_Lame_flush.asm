# APK member: lib/armeabi-v7a/libnlame.so
# Library SHA-256: 2546cbab2bfb92ea24bd6bac3cbb612a6a689c1bc7473807e16c9fd4bd6fec21
# Function: Java_sofeh_audio_Lame_flush; address=0x2836c; bytes=88
# Decoder mode: thumb; decoded_bytes=88
0002836c  f0b5              push       {r4, r5, r6, r7, lr}
0002836e  03af              add        r7, sp, #0xc
00028370  4df8048d          str        r8, [sp, #-0x4]!
00028374  0546              mov        r5, r0
00028376  0068              ldr        r0, [r0]
00028378  1446              mov        r4, r2
0002837a  2146              mov        r1, r4
0002837c  d0f8ac22          ldr.w      r2, [r0, #0x2ac]
00028380  2846              mov        r0, r5
00028382  9047              blx        r2
00028384  8046              mov        r8, r0
00028386  2868              ldr        r0, [r5]
00028388  2146              mov        r1, r4
0002838a  0022              movs       r2, #0
0002838c  d0f8e032          ldr.w      r3, [r0, #0x2e0]
00028390  2846              mov        r0, r5
00028392  9847              blx        r3
00028394  0646              mov        r6, r0
00028396  0a48              ldr        r0, [pc, #0x28]
00028398  3146              mov        r1, r6
0002839a  4246              mov        r2, r8
0002839c  7844              add        r0, pc
0002839e  0068              ldr        r0, [r0]
000283a0  00f09eef          blx        #0x292e0
000283a4  8046              mov        r8, r0
000283a6  2868              ldr        r0, [r5]
000283a8  2146              mov        r1, r4
000283aa  3246              mov        r2, r6
000283ac  0023              movs       r3, #0
000283ae  d0f800c3          ldr.w      ip, [r0, #0x300]
000283b2  2846              mov        r0, r5
000283b4  e047              blx        ip
000283b6  4046              mov        r0, r8
000283b8  5df8048b          ldr        r8, [sp], #4
000283bc  f0bd              pop        {r4, r5, r6, r7, pc}
000283be  00bf              nop        
000283c0  3443              orrs       r4, r6
000283c2  0100              movs       r1, r0
