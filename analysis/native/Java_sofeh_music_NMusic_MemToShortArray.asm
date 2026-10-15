# Library: libnmusic.so
# Function: Java_sofeh_music_NMusic_MemToShortArray; bytes=92
0000312c  fd7bbda9  stp      x29, x30, [sp, #-0x30]!
00003130  f65701a9  stp      x22, x21, [sp, #0x10]
00003134  f44f02a9  stp      x20, x19, [sp, #0x20]
00003138  fd030091  mov      x29, sp
0000313c  080040f9  ldr      x8, [x0]
00003140  e10302aa  mov      x1, x2
00003144  f30305aa  mov      x19, x5
00003148  f40304aa  mov      x20, x4
0000314c  f50303aa  mov      x21, x3
00003150  f60300aa  mov      x22, x0
00003154  089943f9  ldr      x8, [x8, #0x730]
00003158  00013fd6  blr      x8
0000315c  c80240f9  ldr      x8, [x22]
00003160  e40300aa  mov      x4, x0
00003164  e00316aa  mov      x0, x22
00003168  e10315aa  mov      x1, x21
0000316c  e203142a  mov      w2, w20
00003170  054943f9  ldr      x5, [x8, #0x690]
00003174  e303132a  mov      w3, w19
00003178  f44f42a9  ldp      x20, x19, [sp, #0x20]
0000317c  f65741a9  ldp      x22, x21, [sp, #0x10]
00003180  fd7bc3a8  ldp      x29, x30, [sp], #0x30
00003184  a0001fd6  br       x5
