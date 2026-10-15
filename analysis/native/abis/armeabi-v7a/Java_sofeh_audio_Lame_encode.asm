# APK member: lib/armeabi-v7a/libnlame.so
# Library SHA-256: 2546cbab2bfb92ea24bd6bac3cbb612a6a689c1bc7473807e16c9fd4bd6fec21
# Function: Java_sofeh_audio_Lame_encode; address=0x282c4; bytes=168
# Decoder mode: thumb; decoded_bytes=168
000282c4  f0b5              push       {r4, r5, r6, r7, lr}
000282c6  03af              add        r7, sp, #0xc
000282c8  2de9000f          push.w     {r8, sb, sl, fp}
000282cc  83b0              sub        sp, #0xc
000282ce  0446              mov        r4, r0
000282d0  0068              ldr        r0, [r0]
000282d2  1d46              mov        r5, r3
000282d4  0293              str        r3, [sp, #8]
000282d6  9246              mov        sl, r2
000282d8  1146              mov        r1, r2
000282da  d0f8e832          ldr.w      r3, [r0, #0x2e8]
000282de  2046              mov        r0, r4
000282e0  0022              movs       r2, #0
000282e2  9847              blx        r3
000282e4  8346              mov        fp, r0
000282e6  2068              ldr        r0, [r4]
000282e8  2946              mov        r1, r5
000282ea  0022              movs       r2, #0
000282ec  d0f8e832          ldr.w      r3, [r0, #0x2e8]
000282f0  2046              mov        r0, r4
000282f2  9847              blx        r3
000282f4  8146              mov        sb, r0
000282f6  2068              ldr        r0, [r4]
000282f8  fd68              ldr        r5, [r7, #0xc]
000282fa  d0f8ac22          ldr.w      r2, [r0, #0x2ac]
000282fe  2046              mov        r0, r4
00028300  2946              mov        r1, r5
00028302  9047              blx        r2
00028304  0646              mov        r6, r0
00028306  2068              ldr        r0, [r4]
00028308  2946              mov        r1, r5
0002830a  0022              movs       r2, #0
0002830c  d0f8e032          ldr.w      r3, [r0, #0x2e0]
00028310  2046              mov        r0, r4
00028312  9847              blx        r3
00028314  0546              mov        r5, r0
00028316  1448              ldr        r0, [pc, #0x50]
00028318  bb68              ldr        r3, [r7, #8]
0002831a  5946              mov        r1, fp
0002831c  7844              add        r0, pc
0002831e  4a46              mov        r2, sb
00028320  cde90056          strd       r5, r6, [sp]
00028324  0068              ldr        r0, [r0]
00028326  00f0d4ef          blx        #0x292d0
0002832a  8046              mov        r8, r0
0002832c  2068              ldr        r0, [r4]
0002832e  5146              mov        r1, sl
00028330  5a46              mov        r2, fp
00028332  0023              movs       r3, #0
00028334  d0f80863          ldr.w      r6, [r0, #0x308]
00028338  2046              mov        r0, r4
0002833a  b047              blx        r6
0002833c  2068              ldr        r0, [r4]
0002833e  4a46              mov        r2, sb
00028340  0299              ldr        r1, [sp, #8]
00028342  0023              movs       r3, #0
00028344  d0f80863          ldr.w      r6, [r0, #0x308]
00028348  2046              mov        r0, r4
0002834a  b047              blx        r6
0002834c  2068              ldr        r0, [r4]
0002834e  2a46              mov        r2, r5
00028350  f968              ldr        r1, [r7, #0xc]
00028352  0023              movs       r3, #0
00028354  d0f80063          ldr.w      r6, [r0, #0x300]
00028358  2046              mov        r0, r4
0002835a  b047              blx        r6
0002835c  4046              mov        r0, r8
0002835e  03b0              add        sp, #0xc
00028360  bde8000f          pop.w      {r8, sb, sl, fp}
00028364  f0bd              pop        {r4, r5, r6, r7, pc}
00028366  00bf              nop        
00028368  b443              bics       r4, r6
0002836a  0100              movs       r1, r0
