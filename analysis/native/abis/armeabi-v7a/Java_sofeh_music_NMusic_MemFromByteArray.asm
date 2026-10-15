# APK member: lib/armeabi-v7a/libnmusic.so
# Library SHA-256: bd2a4a0e17aaa14c558d5f96ba1ac387097bd96c2bdd771c287261dd8fdcc77e
# Function: Java_sofeh_music_NMusic_MemFromByteArray; address=0x25a8; bytes=52
# Decoder mode: thumb; decoded_bytes=52
000025a8  f0b5              push       {r4, r5, r6, r7, lr}
000025aa  03af              add        r7, sp, #0xc
000025ac  4df804bd          str        fp, [sp, #-0x4]!
000025b0  82b0              sub        sp, #8
000025b2  0546              mov        r5, r0
000025b4  0068              ldr        r0, [r0]
000025b6  1146              mov        r1, r2
000025b8  1c46              mov        r4, r3
000025ba  d0f89823          ldr.w      r2, [r0, #0x398]
000025be  2846              mov        r0, r5
000025c0  9047              blx        r2
000025c2  2968              ldr        r1, [r5]
000025c4  ba68              ldr        r2, [r7, #8]
000025c6  3b69              ldr        r3, [r7, #0x10]
000025c8  d1f82063          ldr.w      r6, [r1, #0x320]
000025cc  2146              mov        r1, r4
000025ce  0090              str        r0, [sp]
000025d0  2846              mov        r0, r5
000025d2  b047              blx        r6
000025d4  02b0              add        sp, #8
000025d6  5df804bb          ldr        fp, [sp], #4
000025da  f0bd              pop        {r4, r5, r6, r7, pc}
