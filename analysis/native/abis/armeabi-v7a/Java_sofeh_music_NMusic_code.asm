# APK member: lib/armeabi-v7a/libnmusic.so
# Library SHA-256: bd2a4a0e17aaa14c558d5f96ba1ac387097bd96c2bdd771c287261dd8fdcc77e
# Function: Java_sofeh_music_NMusic_code; address=0x2490; bytes=108
# Decoder mode: thumb; decoded_bytes=106
00002490  f0b5              push       {r4, r5, r6, r7, lr}
00002492  03af              add        r7, sp, #0xc
00002494  2de9000b          push.w     {r8, sb, fp}
00002498  0646              mov        r6, r0
0000249a  0068              ldr        r0, [r0]
0000249c  9846              mov        r8, r3
0000249e  9146              mov        sb, r2
000024a0  1146              mov        r1, r2
000024a2  0022              movs       r2, #0
000024a4  d0f8a432          ldr.w      r3, [r0, #0x2a4]
000024a8  3046              mov        r0, r6
000024aa  0025              movs       r5, #0
000024ac  9847              blx        r3
000024ae  0446              mov        r4, r0
000024b0  00f08eeb          blx        #0x2bd0
000024b4  0021              movs       r1, #0
000024b6  8142              cmp        r1, r0
000024b8  04d2              bhs        #0x24c4
000024ba  625c              ldrb       r2, [r4, r1]
000024bc  0131              adds       r1, #1
000024be  01fb0255          mla        r5, r1, r2, r5
000024c2  f8e7              b          #0x24b6
000024c4  3068              ldr        r0, [r6]
000024c6  4946              mov        r1, sb
000024c8  2246              mov        r2, r4
000024ca  d0f8a832          ldr.w      r3, [r0, #0x2a8]
000024ce  3046              mov        r0, r6
000024d0  9847              blx        r3
000024d2  0948              ldr        r0, [pc, #0x24]
000024d4  42f21076          movw       r6, #0x2710
000024d8  7844              add        r0, pc
000024da  50f82840          ldr.w      r4, [r0, r8, lsl #2]
000024de  2801              lsls       r0, r5, #4
000024e0  2146              mov        r1, r4
000024e2  00f09ae8          blx        #0x2618
000024e6  b042              cmp        r0, r6
000024e8  03db              blt        #0x24f2
000024ea  05ebd570          add.w      r0, r5, r5, lsr #31
000024ee  4510              asrs       r5, r0, #1
000024f0  f5e7              b          #0x24de
000024f2  bde8000b          pop.w      {r8, sb, fp}
000024f6  f0bd              pop        {r4, r5, r6, r7, pc}
000024f8  bce7              b          #0x2474
# Undecoded tail: offset=0x6a; bytes=2
# Raw hex: ffff
