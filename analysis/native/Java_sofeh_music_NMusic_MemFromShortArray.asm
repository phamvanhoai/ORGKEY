# Library: libnmusic.so
# Function: Java_sofeh_music_NMusic_MemFromShortArray; bytes=92
000031e4  fd7bbda9  stp      x29, x30, [sp, #-0x30]!
000031e8  f65701a9  stp      x22, x21, [sp, #0x10]
000031ec  f44f02a9  stp      x20, x19, [sp, #0x20]
000031f0  fd030091  mov      x29, sp
000031f4  080040f9  ldr      x8, [x0]
000031f8  e10302aa  mov      x1, x2
000031fc  f30305aa  mov      x19, x5
00003200  f40304aa  mov      x20, x4
00003204  f50303aa  mov      x21, x3
00003208  f60300aa  mov      x22, x0
0000320c  089943f9  ldr      x8, [x8, #0x730]
00003210  00013fd6  blr      x8
00003214  c80240f9  ldr      x8, [x22]
00003218  e40300aa  mov      x4, x0
0000321c  e00316aa  mov      x0, x22
00003220  e10315aa  mov      x1, x21
00003224  e203142a  mov      w2, w20
00003228  052943f9  ldr      x5, [x8, #0x650]
0000322c  e303132a  mov      w3, w19
00003230  f44f42a9  ldp      x20, x19, [sp, #0x20]
00003234  f65741a9  ldp      x22, x21, [sp, #0x10]
00003238  fd7bc3a8  ldp      x29, x30, [sp], #0x30
0000323c  a0001fd6  br       x5
