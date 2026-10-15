# APK member: lib/arm64-v8a/libnpitchshift.so
# Library SHA-256: 25dff5430d9ff9db374fd01ab3fc879c7215eca59ad5e181fa54ca3dbd3b5cc0
# Function: smbPitchShiftFlush; address=0xeb8; bytes=176
00000eb8  fd7bbea9  stp      x29, x30, [sp, #-0x20]!
00000ebc  f30b00f9  str      x19, [sp, #0x10]
00000ec0  fd030091  mov      x29, sp
00000ec4  e1031f2a  mov      w1, wzr
00000ec8  02008252  mov      w2, #0x1000
00000ecc  f30300aa  mov      x19, x0
00000ed0  e0030094  bl       #0x1e50
00000ed4  60064091  add      x0, x19, #1, lsl #12
00000ed8  e1031f2a  mov      w1, wzr
00000edc  02008252  mov      w2, #0x1000
00000ee0  dc030094  bl       #0x1e50
00000ee4  600a4091  add      x0, x19, #2, lsl #12
00000ee8  e1031f2a  mov      w1, wzr
00000eec  02008452  mov      w2, #0x2000
00000ef0  d8030094  bl       #0x1e50
00000ef4  60124091  add      x0, x19, #4, lsl #12
00000ef8  e1031f2a  mov      w1, wzr
00000efc  82008152  mov      w2, #0x804
00000f00  d4030094  bl       #0x1e50
00000f04  88008952  mov      w8, #0x4804
00000f08  e1031f2a  mov      w1, wzr
00000f0c  82008152  mov      w2, #0x804
00000f10  6002088b  add      x0, x19, x8
00000f14  cf030094  bl       #0x1e50
00000f18  08018a52  mov      w8, #0x5008
00000f1c  e1031f2a  mov      w1, wzr
00000f20  02008452  mov      w2, #0x2000
00000f24  6002088b  add      x0, x19, x8
00000f28  ca030094  bl       #0x1e50
00000f2c  08018e52  mov      w8, #0x7008
00000f30  e1031f2a  mov      w1, wzr
00000f34  02008252  mov      w2, #0x1000
00000f38  6002088b  add      x0, x19, x8
00000f3c  c5030094  bl       #0x1e50
00000f40  08019052  mov      w8, #0x8008
00000f44  e1031f2a  mov      w1, wzr
00000f48  02008252  mov      w2, #0x1000
00000f4c  6002088b  add      x0, x19, x8
00000f50  c0030094  bl       #0x1e50
00000f54  08019652  mov      w8, #0xb008
00000f58  7f6a28f8  str      xzr, [x19, x8]
00000f5c  f30b40f9  ldr      x19, [sp, #0x10]
00000f60  fd7bc2a8  ldp      x29, x30, [sp], #0x20
00000f64  c0035fd6  ret      
