# APK member: lib/armeabi-v7a/libnlame.so
# Library SHA-256: 2546cbab2bfb92ea24bd6bac3cbb612a6a689c1bc7473807e16c9fd4bd6fec21
# Function: Java_sofeh_audio_Lame_init; address=0x28260; bytes=100
# Decoder mode: thumb; decoded_bytes=100
00028260  f0b5              push       {r4, r5, r6, r7, lr}
00028262  03af              add        r7, sp, #0xc
00028264  2de90007          push.w     {r8, sb, sl}
00028268  154e              ldr        r6, [pc, #0x54]
0002826a  1c46              mov        r4, r3
0002826c  d7f81080          ldr.w      r8, [r7, #0x10]
00028270  1546              mov        r5, r2
00028272  7e44              add        r6, pc
00028274  d7e902a9          ldrd       sl, sb, [r7, #8]
00028278  3068              ldr        r0, [r6]
0002827a  18b1              cbz        r0, #0x28284
0002827c  01f038e8          blx        #0x292f0
00028280  0020              movs       r0, #0
00028282  3060              str        r0, [r6]
00028284  01f03ce8          blx        #0x29300
00028288  2946              mov        r1, r5
0002828a  3060              str        r0, [r6]
0002828c  01f060e8          blx        #0x29350
00028290  3068              ldr        r0, [r6]
00028292  2146              mov        r1, r4
00028294  01f064e8          blx        #0x29360
00028298  3068              ldr        r0, [r6]
0002829a  5146              mov        r1, sl
0002829c  01f068e8          blx        #0x29370
000282a0  3068              ldr        r0, [r6]
000282a2  4946              mov        r1, sb
000282a4  00f09cec          blx        #0x28be0
000282a8  3068              ldr        r0, [r6]
000282aa  4146              mov        r1, r8
000282ac  01f068e8          blx        #0x29380
000282b0  3068              ldr        r0, [r6]
000282b2  bde80007          pop.w      {r8, sb, sl}
000282b6  bde8f040          pop.w      {r4, r5, r6, r7, lr}
000282ba  00f003bb          b.w        #0x288c4
000282be  00bf              nop        
000282c0  5e44              add        r6, fp
000282c2  0100              movs       r1, r0
