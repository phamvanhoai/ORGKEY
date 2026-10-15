# Library: libnmusic.so
# Function: Java_sofeh_music_NMusic_ChannelReset; bytes=52
00002414  820100b4  cbz      x2, #0x2444
00002418  fd7bbea9  stp      x29, x30, [sp, #-0x20]!
0000241c  f30b00f9  str      x19, [sp, #0x10]
00002420  fd030091  mov      x29, sp
00002424  e00302aa  mov      x0, x2
00002428  f30302aa  mov      x19, x2
0000242c  a9030094  bl       #0x32d0
00002430  08029652  mov      w8, #0xb010
00002434  6002088b  add      x0, x19, x8
00002438  f30b40f9  ldr      x19, [sp, #0x10]
0000243c  fd7bc2a8  ldp      x29, x30, [sp], #0x20
00002440  a4030014  b        #0x32d0
00002444  c0035fd6  ret      
