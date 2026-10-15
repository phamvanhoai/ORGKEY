# APK member: lib/arm64-v8a/libnpitchshift.so
# Library SHA-256: 25dff5430d9ff9db374fd01ab3fc879c7215eca59ad5e181fa54ca3dbd3b5cc0
# Function: smbPitchShiftCopy; address=0xf68; bytes=180
00000f68  fd7bbea9  stp      x29, x30, [sp, #-0x20]!
00000f6c  f44f01a9  stp      x20, x19, [sp, #0x10]
00000f70  fd030091  mov      x29, sp
00000f74  02008252  mov      w2, #0x1000
00000f78  f40301aa  mov      x20, x1
00000f7c  f30300aa  mov      x19, x0
00000f80  bc030094  bl       #0x1e70
00000f84  60064091  add      x0, x19, #1, lsl #12
00000f88  81064091  add      x1, x20, #1, lsl #12
00000f8c  02008252  mov      w2, #0x1000
00000f90  b8030094  bl       #0x1e70
00000f94  600a4091  add      x0, x19, #2, lsl #12
00000f98  810a4091  add      x1, x20, #2, lsl #12
00000f9c  02008452  mov      w2, #0x2000
00000fa0  b4030094  bl       #0x1e70
00000fa4  60124091  add      x0, x19, #4, lsl #12
00000fa8  81124091  add      x1, x20, #4, lsl #12
00000fac  82008152  mov      w2, #0x804
00000fb0  b0030094  bl       #0x1e70
00000fb4  88008952  mov      w8, #0x4804
00000fb8  82008152  mov      w2, #0x804
00000fbc  6002088b  add      x0, x19, x8
00000fc0  8102088b  add      x1, x20, x8
00000fc4  ab030094  bl       #0x1e70
00000fc8  08018a52  mov      w8, #0x5008
00000fcc  02008452  mov      w2, #0x2000
00000fd0  6002088b  add      x0, x19, x8
00000fd4  8102088b  add      x1, x20, x8
00000fd8  a6030094  bl       #0x1e70
00000fdc  08018e52  mov      w8, #0x7008
00000fe0  02008252  mov      w2, #0x1000
00000fe4  6002088b  add      x0, x19, x8
00000fe8  8102088b  add      x1, x20, x8
00000fec  a1030094  bl       #0x1e70
00000ff0  08019052  mov      w8, #0x8008
00000ff4  02008252  mov      w2, #0x1000
00000ff8  6002088b  add      x0, x19, x8
00000ffc  8102088b  add      x1, x20, x8
00001000  9c030094  bl       #0x1e70
00001004  08019652  mov      w8, #0xb008
00001008  896a68f8  ldr      x9, [x20, x8]
0000100c  696a28f8  str      x9, [x19, x8]
00001010  f44f41a9  ldp      x20, x19, [sp, #0x10]
00001014  fd7bc2a8  ldp      x29, x30, [sp], #0x20
00001018  c0035fd6  ret      
