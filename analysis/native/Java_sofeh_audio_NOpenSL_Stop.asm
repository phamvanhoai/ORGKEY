# Library: libnopensl.so
# Function: Java_sofeh_audio_NOpenSL_Stop; bytes=172
00002a14  fd7bbea9  stp      x29, x30, [sp, #-0x20]!
00002a18  f30b00f9  str      x19, [sp, #0x10]
00002a1c  fd030091  mov      x29, sp
00002a20  80080090  adrp     x0, #0x112000
00002a24  00e03d91  add      x0, x0, #0xf78
00002a28  9a000094  bl       #0x2c90
00002a2c  93000090  adrp     x19, #0x12000
00002a30  68008052  mov      w8, #3
00002a34  89080090  adrp     x9, #0x112000
00002a38  686a0fb9  str      w8, [x19, #0xf68]
00002a3c  88080090  adrp     x8, #0x112000
00002a40  3ff13e39  strb     wzr, [x9, #0xfbc]
00002a44  00e147f9  ldr      x0, [x8, #0xfc0]
00002a48  a00000b4  cbz      x0, #0x2a5c
00002a4c  080040f9  ldr      x8, [x0]
00002a50  21008052  mov      w1, #1
00002a54  080140f9  ldr      x8, [x8]
00002a58  00013fd6  blr      x8
00002a5c  88080090  adrp     x8, #0x112000
00002a60  00e547f9  ldr      x0, [x8, #0xfc8]
00002a64  800000b4  cbz      x0, #0x2a74
00002a68  080040f9  ldr      x8, [x0]
00002a6c  080540f9  ldr      x8, [x8, #8]
00002a70  00013fd6  blr      x8
00002a74  88080090  adrp     x8, #0x112000
00002a78  00e947f9  ldr      x0, [x8, #0xfd0]
00002a7c  a00000b4  cbz      x0, #0x2a90
00002a80  080040f9  ldr      x8, [x0]
00002a84  21008052  mov      w1, #1
00002a88  080140f9  ldr      x8, [x8]
00002a8c  00013fd6  blr      x8
00002a90  88040090  adrp     x8, #0x92000
00002a94  00b947f9  ldr      x0, [x8, #0xf70]
00002a98  800000b4  cbz      x0, #0x2aa8
00002a9c  080040f9  ldr      x8, [x0]
00002aa0  080540f9  ldr      x8, [x8, #8]
00002aa4  00013fd6  blr      x8
00002aa8  80080090  adrp     x0, #0x112000
00002aac  00e03d91  add      x0, x0, #0xf78
00002ab0  7f6a0fb9  str      wzr, [x19, #0xf68]
00002ab4  f30b40f9  ldr      x19, [sp, #0x10]
00002ab8  fd7bc2a8  ldp      x29, x30, [sp], #0x20
00002abc  79000014  b        #0x2ca0
