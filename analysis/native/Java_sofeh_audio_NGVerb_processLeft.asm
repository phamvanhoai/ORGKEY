# Library: libngverb.so
# Function: Java_sofeh_audio_NGVerb_processLeft; bytes=96
000020d0  ffc300d1  sub      sp, sp, #0x30
000020d4  fd7b01a9  stp      x29, x30, [sp, #0x10]
000020d8  f31300f9  str      x19, [sp, #0x20]
000020dc  fd430091  add      x29, sp, #0x10
000020e0  53d03bd5  mrs      x19, tpidr_el0
000020e4  e00302aa  mov      x0, x2
000020e8  681640f9  ldr      x8, [x19, #0x28]
000020ec  e80700f9  str      x8, [sp, #8]
000020f0  e20000b4  cbz      x2, #0x210c
000020f4  60c4029e  scvtf    s0, x3, #0xf
000020f8  e1130091  add      x1, sp, #4
000020fc  e2030091  mov      x2, sp
00002100  0c000094  bl       #0x2130
00002104  e00740bd  ldr      s0, [sp, #4]
00002108  00c4189e  fcvtzs   x0, s0, #0xf
0000210c  681640f9  ldr      x8, [x19, #0x28]
00002110  e90740f9  ldr      x9, [sp, #8]
00002114  1f0109eb  cmp      x8, x9
00002118  a1000054  b.ne     #0x212c
0000211c  fd7b41a9  ldp      x29, x30, [sp, #0x10]
00002120  f31340f9  ldr      x19, [sp, #0x20]
00002124  ffc30091  add      sp, sp, #0x30
00002128  c0035fd6  ret      
0000212c  1d030094  bl       #0x2da0
