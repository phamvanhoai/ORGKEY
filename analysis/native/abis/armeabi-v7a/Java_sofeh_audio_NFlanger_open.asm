# APK member: lib/armeabi-v7a/libnflanger.so
# Library SHA-256: 4d4fabc33a8e9d24417f86d86f0e51c250d4ce0cc77fc1056a4f23b59e289534
# Function: Java_sofeh_audio_NFlanger_open; address=0x610; bytes=64
# Decoder mode: thumb; decoded_bytes=64
00000610  80b5              push       {r7, lr}
00000612  6f46              mov        r7, sp
00000614  52ea0300          orrs.w     r0, r2, r3
00000618  1cbf              itt        ne
0000061a  1046              movne      r0, r2
0000061c  00f028eb          blxne      #0xc70
00000620  0120              movs       r0, #1
00000622  40f63401          movw       r1, #0x834
00000626  00f02ceb          blx        #0xc80
0000062a  05a1              adr        r1, #0x14
0000062c  8022              movs       r2, #0x80
0000062e  61f9ef0a          vld1.64    {d16, d17}, [r1:0x80]
00000632  0146              mov        r1, r0
00000634  41f98d0a          vst1.32    {d16, d17}, [r1]!
00000638  0a60              str        r2, [r1]
0000063a  0021              movs       r1, #0
0000063c  80bd              pop        {r7, pc}
0000063e  00bf              nop        
00000640  2d00              movs       r5, r5
00000642  0000              movs       r0, r0
00000644  0a00              movs       r2, r1
00000646  0000              movs       r0, r0
00000648  8000              lsls       r0, r0, #2
0000064a  0000              movs       r0, r0
0000064c  0200              movs       r2, r0
0000064e  0000              movs       r0, r0
