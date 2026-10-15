# APK member: lib/arm64-v8a/libnopensl.so
# Library SHA-256: dc900f9de170ec71471d8b5d2ccecc14d0bbb8c1d2346bd6e149467c888b38cf
# Internal range: teardown helper; address=0x2448..0x2574; bytes=300
# Names describe observed behavior; the ELF has no dynamic symbol for this range.
00002448  fd7bbda9  stp      x29, x30, [sp, #-0x30]!
0000244c  f50b00f9  str      x21, [sp, #0x10]
00002450  f44f02a9  stp      x20, x19, [sp, #0x20]
00002454  fd030091  mov      x29, sp
00002458  94000090  adrp     x20, #0x12000
0000245c  f30300aa  mov      x19, x0
00002460  88aa47f9  ldr      x8, [x20, #0xf50]
00002464  880100b4  cbz      x8, #0x2494
00002468  090140f9  ldr      x9, [x8]
0000246c  e00308aa  mov      x0, x8
00002470  291940f9  ldr      x9, [x9, #0x30]
00002474  20013fd6  blr      x9
00002478  88080090  adrp     x8, #0x112000
0000247c  89080090  adrp     x9, #0x112000
00002480  9faa07f9  str      xzr, [x20, #0xf50]
00002484  1fe107f9  str      xzr, [x8, #0xfc0]
00002488  880800b0  adrp     x8, #0x113000
0000248c  3fe507f9  str      xzr, [x9, #0xfc8]
00002490  1f1100f9  str      xzr, [x8, #0x20]
00002494  940800b0  adrp     x20, #0x113000
00002498  801640f9  ldr      x0, [x20, #0x28]
0000249c  200100b4  cbz      x0, #0x24c0
000024a0  080040f9  ldr      x8, [x0]
000024a4  081940f9  ldr      x8, [x8, #0x30]
000024a8  00013fd6  blr      x8
000024ac  88080090  adrp     x8, #0x112000
000024b0  89040090  adrp     x9, #0x92000
000024b4  9f1600f9  str      xzr, [x20, #0x28]
000024b8  1fe907f9  str      xzr, [x8, #0xfd0]
000024bc  3fb907f9  str      xzr, [x9, #0xf70]
000024c0  940800b0  adrp     x20, #0x113000
000024c4  800e40f9  ldr      x0, [x20, #0x18]
000024c8  a00000b4  cbz      x0, #0x24dc
000024cc  080040f9  ldr      x8, [x0]
000024d0  081940f9  ldr      x8, [x8, #0x30]
000024d4  00013fd6  blr      x8
000024d8  9f0e00f9  str      xzr, [x20, #0x18]
000024dc  94080090  adrp     x20, #0x112000
000024e0  80d247f9  ldr      x0, [x20, #0xfa0]
000024e4  e00000b4  cbz      x0, #0x2500
000024e8  080040f9  ldr      x8, [x0]
000024ec  081940f9  ldr      x8, [x8, #0x30]
000024f0  00013fd6  blr      x8
000024f4  880800b0  adrp     x8, #0x113000
000024f8  9fd207f9  str      xzr, [x20, #0xfa0]
000024fc  1f0900f9  str      xzr, [x8, #0x10]
00002500  95080090  adrp     x21, #0x112000
00002504  94080090  adrp     x20, #0x112000
00002508  b30100b4  cbz      x19, #0x253c
0000250c  a1f647f9  ldr      x1, [x21, #0xfe8]
00002510  a10000b4  cbz      x1, #0x2524
00002514  680240f9  ldr      x8, [x19]
00002518  e00313aa  mov      x0, x19
0000251c  085940f9  ldr      x8, [x8, #0xb0]
00002520  00013fd6  blr      x8
00002524  81fa47f9  ldr      x1, [x20, #0xff0]
00002528  a10000b4  cbz      x1, #0x253c
0000252c  680240f9  ldr      x8, [x19]
00002530  e00313aa  mov      x0, x19
00002534  085940f9  ldr      x8, [x8, #0xb0]
00002538  00013fd6  blr      x8
0000253c  880800b0  adrp     x8, #0x113000
00002540  bff607f9  str      xzr, [x21, #0xfe8]
00002544  1f0100f9  str      xzr, [x8]
00002548  88080090  adrp     x8, #0x112000
0000254c  1ffd07f9  str      xzr, [x8, #0xff8]
00002550  88080090  adrp     x8, #0x112000
00002554  9ffa07f9  str      xzr, [x20, #0xff0]
00002558  1fd90fb9  str      wzr, [x8, #0xfd8]
0000255c  88000090  adrp     x8, #0x12000
00002560  1f690fb9  str      wzr, [x8, #0xf68]
00002564  f44f42a9  ldp      x20, x19, [sp, #0x20]
00002568  f50b40f9  ldr      x21, [sp, #0x10]
0000256c  fd7bc3a8  ldp      x29, x30, [sp], #0x30
00002570  c0035fd6  ret      
