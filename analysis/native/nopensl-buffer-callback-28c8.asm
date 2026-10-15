# APK member: lib/arm64-v8a/libnopensl.so
# Library SHA-256: dc900f9de170ec71471d8b5d2ccecc14d0bbb8c1d2346bd6e149467c888b38cf
# Internal range: native buffer callback; address=0x28c8..0x2a14; bytes=332
# Names describe observed behavior; the ELF has no dynamic symbol for this range.
000028c8  fd7bbca9  stp      x29, x30, [sp, #-0x40]!
000028cc  f70b00f9  str      x23, [sp, #0x10]
000028d0  f65702a9  stp      x22, x21, [sp, #0x20]
000028d4  f44f03a9  stp      x20, x19, [sp, #0x30]
000028d8  fd030091  mov      x29, sp
000028dc  88000090  adrp     x8, #0x12000
000028e0  94000090  adrp     x20, #0x12000
000028e4  09694fb9  ldr      w9, [x8, #0xf68]
000028e8  88080090  adrp     x8, #0x112000
000028ec  00e547f9  ldr      x0, [x8, #0xfc8]
000028f0  885a4fb9  ldr      w8, [x20, #0xf58]
000028f4  3f090071  cmp      w9, #2
000028f8  040840fa  ccmp     x0, #0, #4, eq
000028fc  0819417a  ccmp     w8, #1, #8, ne
00002900  0b080054  b.lt     #0x2a00
00002904  93080090  adrp     x19, #0x112000
00002908  96000090  adrp     x22, #0x12000
0000290c  95000090  adrp     x21, #0x12000
00002910  69da4fb9  ldr      w9, [x19, #0xfd8]
00002914  29050011  add      w9, w9, #1
00002918  69da0fb9  str      w9, [x19, #0xfd8]
0000291c  d75e4fb9  ldr      w23, [x22, #0xf5c]
00002920  1701f837  tbnz     w23, #0x1f, #0x2940
00002924  a9927d39  ldrb     w9, [x21, #0xf64]
00002928  0a028052  mov      w10, #0x10
0000292c  3f010071  cmp      w9, #0
00002930  89008052  mov      w9, #4
00002934  4911891a  csel     w9, w10, w9, ne
00002938  ff02096b  cmp      w23, w9
0000293c  43000054  b.lo     #0x2944
00002940  f7031f2a  mov      w23, wzr
00002944  090040f9  ldr      x9, [x0]
00002948  ea03172a  mov      w10, w23
0000294c  1f2003d5  nop      
00002950  0b310810  adr      x11, #0x12f70
00002954  02791f53  lsl      w2, w8, #1
00002958  290140f9  ldr      x9, [x9]
0000295c  613d0a8b  add      x1, x11, x10, lsl #15
00002960  20013fd6  blr      x9
00002964  a8927d39  ldrb     w8, [x21, #0xf64]
00002968  09028052  mov      w9, #0x10
0000296c  ea060011  add      w10, w23, #1
00002970  1f010071  cmp      w8, #0
00002974  88008052  mov      w8, #4
00002978  2811881a  csel     w8, w9, w8, ne
0000297c  5f01086b  cmp      w10, w8
00002980  e827971a  csinc    w8, wzr, w23, hs
00002984  c85e0fb9  str      w8, [x22, #0xf5c]
00002988  88080090  adrp     x8, #0x112000
0000298c  08f17e39  ldrb     w8, [x8, #0xfbc]
00002990  28030034  cbz      w8, #0x29f4
00002994  88040090  adrp     x8, #0x92000
00002998  00b947f9  ldr      x0, [x8, #0xf70]
0000299c  c00200b4  cbz      x0, #0x29f4
000029a0  080040f9  ldr      x8, [x0]
000029a4  960800b0  adrp     x22, #0x113000
000029a8  1f2003d5  nop      
000029ac  6a2e4810  adr      x10, #0x92f78
000029b0  080140f9  ldr      x8, [x8]
000029b4  c90a80b9  ldrsw    x9, [x22, #8]
000029b8  825a4fb9  ldr      w2, [x20, #0xf58]
000029bc  413d098b  add      x1, x10, x9, lsl #15
000029c0  00013fd6  blr      x8
000029c4  c80a40b9  ldr      w8, [x22, #8]
000029c8  0a028052  mov      w10, #0x10
000029cc  08050011  add      w8, w8, #1
000029d0  c80a00b9  str      w8, [x22, #8]
000029d4  c80a40b9  ldr      w8, [x22, #8]
000029d8  a9927d39  ldrb     w9, [x21, #0xf64]
000029dc  3f010071  cmp      w9, #0
000029e0  89008052  mov      w9, #4
000029e4  4911891a  csel     w9, w10, w9, ne
000029e8  1f01096b  cmp      w8, w9
000029ec  4b000054  b.lt     #0x29f4
000029f0  df0a00b9  str      wzr, [x22, #8]
000029f4  68da4fb9  ldr      w8, [x19, #0xfd8]
000029f8  08050051  sub      w8, w8, #1
000029fc  68da0fb9  str      w8, [x19, #0xfd8]
00002a00  f44f43a9  ldp      x20, x19, [sp, #0x30]
00002a04  f70b40f9  ldr      x23, [sp, #0x10]
00002a08  f65742a9  ldp      x22, x21, [sp, #0x20]
00002a0c  fd7bc4a8  ldp      x29, x30, [sp], #0x40
00002a10  c0035fd6  ret      
