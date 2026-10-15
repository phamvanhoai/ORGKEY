# Library: libngverb.so
# Function: Java_sofeh_audio_NGVerb_processRight; bytes=96
000026e0  ffc300d1  sub      sp, sp, #0x30
000026e4  fd7b01a9  stp      x29, x30, [sp, #0x10]
000026e8  f31300f9  str      x19, [sp, #0x20]
000026ec  fd430091  add      x29, sp, #0x10
000026f0  53d03bd5  mrs      x19, tpidr_el0
000026f4  e00302aa  mov      x0, x2
000026f8  681640f9  ldr      x8, [x19, #0x28]
000026fc  e80700f9  str      x8, [sp, #8]
00002700  e20000b4  cbz      x2, #0x271c
00002704  60c4029e  scvtf    s0, x3, #0xf
00002708  e1030091  mov      x1, sp
0000270c  e2130091  add      x2, sp, #4
00002710  88feff97  bl       #0x2130
00002714  e00740bd  ldr      s0, [sp, #4]
00002718  00c4189e  fcvtzs   x0, s0, #0xf
0000271c  681640f9  ldr      x8, [x19, #0x28]
00002720  e90740f9  ldr      x9, [sp, #8]
00002724  1f0109eb  cmp      x8, x9
00002728  a1000054  b.ne     #0x273c
0000272c  fd7b41a9  ldp      x29, x30, [sp, #0x10]
00002730  f31340f9  ldr      x19, [sp, #0x20]
00002734  ffc30091  add      sp, sp, #0x30
00002738  c0035fd6  ret      
0000273c  99010094  bl       #0x2da0
