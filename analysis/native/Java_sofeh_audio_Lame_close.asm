# Function: Java_sofeh_audio_Lame_close; bytes=40
00036090  fd7bbea9  stp      x29, x30, [sp, #-0x20]!
00036094  f30b00f9  str      x19, [sp, #0x10]
00036098  fd030091  mov      x29, sp
0003609c  d30000d0  adrp     x19, #0x50000
000360a0  609e42f9  ldr      x0, [x19, #0x538]
000360a4  6f020094  bl       #0x36a60
000360a8  7f9e02f9  str      xzr, [x19, #0x538]
000360ac  f30b40f9  ldr      x19, [sp, #0x10]
000360b0  fd7bc2a8  ldp      x29, x30, [sp], #0x20
000360b4  c0035fd6  ret      
