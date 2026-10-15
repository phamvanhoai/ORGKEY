# APK member: lib/armeabi-v7a/libnmusic.so
# Library SHA-256: bd2a4a0e17aaa14c558d5f96ba1ac387097bd96c2bdd771c287261dd8fdcc77e
# Function: Java_sofeh_music_NMusic_ChannelOpen; address=0x1a18; bytes=36
# Decoder mode: thumb; decoded_bytes=36
00001a18  d0b5              push       {r4, r6, r7, lr}
00001a1a  02af              add        r7, sp, #8
00001a1c  0648              ldr        r0, [pc, #0x18]
00001a1e  01f070e8          blx        #0x2b00
00001a22  0446              mov        r4, r0
00001a24  01f08ce8          blx        #0x2b40
00001a28  4bf20c00          movw       r0, #0xb00c
00001a2c  2044              add        r0, r4
00001a2e  01f088e8          blx        #0x2b40
00001a32  2046              mov        r0, r4
00001a34  0021              movs       r1, #0
00001a36  d0bd              pop        {r4, r6, r7, pc}
00001a38  1860              str        r0, [r3]
00001a3a  0100              movs       r1, r0
