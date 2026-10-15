# APK member: lib/armeabi-v7a/libnopensl.so
# Library SHA-256: 84027a5e2c443984c6607df964e90cb12f7218ac735e77863d230b3995694b1f
# Function: Java_sofeh_audio_NOpenSL_RecordStart; address=0x185c; bytes=140
# Decoder mode: thumb; decoded_bytes=140
0000185c  f0b5              push       {r4, r5, r6, r7, lr}
0000185e  03af              add        r7, sp, #0xc
00001860  2de9000b          push.w     {r8, sb, fp}
00001864  1e49              ldr        r1, [pc, #0x78]
00001866  7944              add        r1, pc
00001868  d1f84880          ldr.w      r8, [r1, #0x48]
0000186c  b8f1000f          cmp.w      r8, #0
00001870  1cbf              itt        ne
00001872  486a              ldrne      r0, [r1, #0x24]
00001874  0028              cmpne      r0, #0
00001876  19d0              beq        #0x18ac
00001878  0878              ldrb       r0, [r1]
0000187a  0424              movs       r4, #4
0000187c  8946              mov        sb, r1
0000187e  0028              cmp        r0, #0
00001880  18bf              it         ne
00001882  1024              movne      r4, #0x10
00001884  0869              ldr        r0, [r1, #0x10]
00001886  174e              ldr        r6, [pc, #0x5c]
00001888  4500              lsls       r5, r0, #1
0000188a  7e44              add        r6, pc
0000188c  3cb1              cbz        r4, #0x189e
0000188e  3046              mov        r0, r6
00001890  2946              mov        r1, r5
00001892  00f0a6e9          blx        #0x1be0
00001896  06f50046          add.w      r6, r6, #0x8000
0000189a  013c              subs       r4, #1
0000189c  f6e7              b          #0x188c
0000189e  d8f80000          ldr.w      r0, [r8]
000018a2  0321              movs       r1, #3
000018a4  0268              ldr        r2, [r0]
000018a6  4046              mov        r0, r8
000018a8  9047              blx        r2
000018aa  18b1              cbz        r0, #0x18b4
000018ac  0020              movs       r0, #0
000018ae  bde8000b          pop.w      {r8, sb, fp}
000018b2  f0bd              pop        {r4, r5, r6, r7, pc}
000018b4  d9f81400          ldr.w      r0, [sb, #0x14]
000018b8  0138              subs       r0, #1
000018ba  c9f86400          str.w      r0, [sb, #0x64]
000018be  d9f86400          ldr.w      r0, [sb, #0x64]
000018c2  b0f1ff3f          cmp.w      r0, #-1
000018c6  07dc              bgt        #0x18d8
000018c8  99f80000          ldrb.w     r0, [sb]
000018cc  0321              movs       r1, #3
000018ce  0028              cmp        r0, #0
000018d0  18bf              it         ne
000018d2  0f21              movne      r1, #0xf
000018d4  c9f86410          str.w      r1, [sb, #0x64]
000018d8  0120              movs       r0, #1
000018da  89f80200          strb.w     r0, [sb, #2]
000018de  e6e7              b          #0x18ae
000018e0  d6a4              adr        r4, #0x358
000018e2  1000              movs       r0, r2
000018e4  b2a4              adr        r4, #0x2c8
000018e6  0800              movs       r0, r1
