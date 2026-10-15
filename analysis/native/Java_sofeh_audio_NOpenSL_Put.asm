# APK member: lib/arm64-v8a/libnopensl.so
# Library SHA-256: dc900f9de170ec71471d8b5d2ccecc14d0bbb8c1d2346bd6e149467c888b38cf
# Function: Java_sofeh_audio_NOpenSL_Put; address=0xf28; bytes=156
# ELF virtual addresses, not runtime absolute addresses. Indirect calls unresolved.
00000f28  c20400b4  cbz      x2, #0xfc0
00000f2c  fd7bbda9  stp      x29, x30, [sp, #-0x30]!
00000f30  f50b00f9  str      x21, [sp, #0x10]
00000f34  f44f02a9  stp      x20, x19, [sp, #0x20]
00000f38  fd030091  mov      x29, sp
00000f3c  080040f9  ldr      x8, [x0]
00000f40  e10302aa  mov      x1, x2
00000f44  f503032a  mov      w21, w3
00000f48  f30302aa  mov      x19, x2
00000f4c  f40300aa  mov      x20, x0
00000f50  08ad42f9  ldr      x8, [x8, #0x558]
00000f54  00013fd6  blr      x8
00000f58  1f00156b  cmp      w0, w21
00000f5c  08b0951a  csel     w8, w0, w21, lt
00000f60  1f050071  cmp      w8, #1
00000f64  8b020054  b.lt     #0xfb4
00000f68  890240f9  ldr      x9, [x20]
00000f6c  1f114071  cmp      w8, #4, lsl #12
00000f70  0a008852  mov      w10, #0x4000
00000f74  15318a1a  csel     w21, w8, w10, lo
00000f78  1f2003d5  nop      
00000f7c  64fe0410  adr      x4, #0xaf48
00000f80  292943f9  ldr      x9, [x9, #0x650]
00000f84  e00314aa  mov      x0, x20
00000f88  e10313aa  mov      x1, x19
00000f8c  e2031f2a  mov      w2, wzr
00000f90  e303152a  mov      w3, w21
00000f94  20013fd6  blr      x9
00000f98  880000d0  adrp     x8, #0x12000
00000f9c  8a0000d0  adrp     x10, #0x12000
00000fa0  094d4fb9  ldr      w9, [x8, #0xf4c]
00000fa4  55490fb9  str      w21, [x10, #0xf48]
00000fa8  3f01156b  cmp      w9, w21
00000fac  4b000054  b.lt     #0xfb4
00000fb0  1f4d0fb9  str      wzr, [x8, #0xf4c]
00000fb4  f44f42a9  ldp      x20, x19, [sp, #0x20]
00000fb8  f50b40f9  ldr      x21, [sp, #0x10]
00000fbc  fd7bc3a8  ldp      x29, x30, [sp], #0x30
00000fc0  c0035fd6  ret      
