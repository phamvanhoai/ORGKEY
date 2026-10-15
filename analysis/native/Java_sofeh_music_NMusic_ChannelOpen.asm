# Library: libnmusic.so
# Function: Java_sofeh_music_NMusic_ChannelOpen; bytes=60
000023c8  fd7bbea9  stp      x29, x30, [sp, #-0x20]!
000023cc  f30b00f9  str      x19, [sp, #0x10]
000023d0  fd030091  mov      x29, sp
000023d4  00048c52  mov      w0, #0x6020
000023d8  2000a072  movk     w0, #1, lsl #16
000023dc  b1030094  bl       #0x32a0
000023e0  f30300aa  mov      x19, x0
000023e4  bb030094  bl       #0x32d0
000023e8  08029652  mov      w8, #0xb010
000023ec  6002088b  add      x0, x19, x8
000023f0  b8030094  bl       #0x32d0
000023f4  e00313aa  mov      x0, x19
000023f8  f30b40f9  ldr      x19, [sp, #0x10]
000023fc  fd7bc2a8  ldp      x29, x30, [sp], #0x20
00002400  c0035fd6  ret      
