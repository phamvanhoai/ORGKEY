# Library: libnmusic.so
# Function: Java_sofeh_music_NMusic_MemFromByteArray; bytes=92
00003188  fd7bbda9  stp      x29, x30, [sp, #-0x30]!
0000318c  f65701a9  stp      x22, x21, [sp, #0x10]
00003190  f44f02a9  stp      x20, x19, [sp, #0x20]
00003194  fd030091  mov      x29, sp
00003198  080040f9  ldr      x8, [x0]
0000319c  e10302aa  mov      x1, x2
000031a0  f30305aa  mov      x19, x5
000031a4  f40304aa  mov      x20, x4
000031a8  f50303aa  mov      x21, x3
000031ac  f60300aa  mov      x22, x0
000031b0  089943f9  ldr      x8, [x8, #0x730]
000031b4  00013fd6  blr      x8
000031b8  c80240f9  ldr      x8, [x22]
000031bc  e40300aa  mov      x4, x0
000031c0  e00316aa  mov      x0, x22
000031c4  e10315aa  mov      x1, x21
000031c8  e203142a  mov      w2, w20
000031cc  052143f9  ldr      x5, [x8, #0x640]
000031d0  e303132a  mov      w3, w19
000031d4  f44f42a9  ldp      x20, x19, [sp, #0x20]
000031d8  f65741a9  ldp      x22, x21, [sp, #0x10]
000031dc  fd7bc3a8  ldp      x29, x30, [sp], #0x30
000031e0  a0001fd6  br       x5
