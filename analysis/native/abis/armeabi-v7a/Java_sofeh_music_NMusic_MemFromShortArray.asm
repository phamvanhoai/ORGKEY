# APK member: lib/armeabi-v7a/libnmusic.so
# Library SHA-256: bd2a4a0e17aaa14c558d5f96ba1ac387097bd96c2bdd771c287261dd8fdcc77e
# Function: Java_sofeh_music_NMusic_MemFromShortArray; address=0x25dc; bytes=52
# Decoder mode: thumb; decoded_bytes=52
000025dc  f0b5              push       {r4, r5, r6, r7, lr}
000025de  03af              add        r7, sp, #0xc
000025e0  4df804bd          str        fp, [sp, #-0x4]!
000025e4  82b0              sub        sp, #8
000025e6  0546              mov        r5, r0
000025e8  0068              ldr        r0, [r0]
000025ea  1146              mov        r1, r2
000025ec  1c46              mov        r4, r3
000025ee  d0f89823          ldr.w      r2, [r0, #0x398]
000025f2  2846              mov        r0, r5
000025f4  9047              blx        r2
000025f6  2968              ldr        r1, [r5]
000025f8  ba68              ldr        r2, [r7, #8]
000025fa  3b69              ldr        r3, [r7, #0x10]
000025fc  d1f82863          ldr.w      r6, [r1, #0x328]
00002600  2146              mov        r1, r4
00002602  0090              str        r0, [sp]
00002604  2846              mov        r0, r5
00002606  b047              blx        r6
00002608  02b0              add        sp, #8
0000260a  5df804bb          ldr        fp, [sp], #4
0000260e  f0bd              pop        {r4, r5, r6, r7, pc}
