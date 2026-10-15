# APK member: lib/arm64-v8a/libnopensl.so
# Library SHA-256: dc900f9de170ec71471d8b5d2ccecc14d0bbb8c1d2346bd6e149467c888b38cf
# Internal range: Java-assisted buffer callback; address=0x2574..0x28c8; bytes=852
# Names describe observed behavior; the ELF has no dynamic symbol for this range.
00002574  fd7bbaa9  stp      x29, x30, [sp, #-0x60]!
00002578  fc6f01a9  stp      x28, x27, [sp, #0x10]
0000257c  fa6702a9  stp      x26, x25, [sp, #0x20]
00002580  f85f03a9  stp      x24, x23, [sp, #0x30]
00002584  f65704a9  stp      x22, x21, [sp, #0x40]
00002588  f44f05a9  stp      x20, x19, [sp, #0x50]
0000258c  fd030091  mov      x29, sp
00002590  96000090  adrp     x22, #0x12000
00002594  88080090  adrp     x8, #0x112000
00002598  c96a4fb9  ldr      w9, [x22, #0xf68]
0000259c  0ae547f9  ldr      x10, [x8, #0xfc8]
000025a0  88000090  adrp     x8, #0x12000
000025a4  08594fb9  ldr      w8, [x8, #0xf58]
000025a8  3f090071  cmp      w9, #2
000025ac  440940fa  ccmp     x10, #0, #4, eq
000025b0  0819417a  ccmp     w8, #1, #8, ne
000025b4  cb170054  b.lt     #0x28ac
000025b8  8a080090  adrp     x10, #0x112000
000025bc  49d94fb9  ldr      w9, [x10, #0xfd8]
000025c0  29050011  add      w9, w9, #1
000025c4  49d90fb9  str      w9, [x10, #0xfd8]
000025c8  89000090  adrp     x9, #0x12000
000025cc  385d4fb9  ldr      w24, [x9, #0xf5c]
000025d0  89000090  adrp     x9, #0x12000
000025d4  1801f837  tbnz     w24, #0x1f, #0x25f4
000025d8  29917d39  ldrb     w9, [x9, #0xf64]
000025dc  0a028052  mov      w10, #0x10
000025e0  3f010071  cmp      w9, #0
000025e4  89008052  mov      w9, #4
000025e8  4911891a  csel     w9, w10, w9, ne
000025ec  1f03096b  cmp      w24, w9
000025f0  43000054  b.lo     #0x25f8
000025f4  f8031f2a  mov      w24, wzr
000025f8  89000090  adrp     x9, #0x12000
000025fc  fb031f2a  mov      w27, wzr
00002600  95000090  adrp     x21, #0x12000
00002604  33494fb9  ldr      w19, [x9, #0xf48]
00002608  7f060071  cmp      w19, #1
0000260c  cb020054  b.lt     #0x2664
00002610  b44e4fb9  ldr      w20, [x21, #0xf4c]
00002614  9f060071  cmp      w20, #1
00002618  6b020054  b.lt     #0x2664
0000261c  6902144b  sub      w9, w19, w20
00002620  297da90a  bic      w9, w9, w9, asr #31
00002624  3f01086b  cmp      w9, w8
00002628  3b31881a  csel     w27, w9, w8, lo
0000262c  db010034  cbz      w27, #0x2664
00002630  1f2003d5  nop      
00002634  e8490810  adr      x8, #0x12f70
00002638  e903182a  mov      w9, w24
0000263c  1f2003d5  nop      
00002640  4a480410  adr      x10, #0xaf48
00002644  003d098b  add      x0, x8, x9, lsl #15
00002648  4105148b  add      x1, x10, x20, lsl #1
0000264c  627b1f53  lsl      w2, w27, #1
00002650  80010094  bl       #0x2c50
00002654  6803140b  add      w8, w27, w20
00002658  1f01136b  cmp      w8, w19
0000265c  08319f1a  csel     w8, w8, wzr, lo
00002660  a84e0fb9  str      w8, [x21, #0xf4c]
00002664  94080090  adrp     x20, #0x112000
00002668  88f247f9  ldr      x8, [x20, #0xfe0]
0000266c  e80200b5  cbnz     x8, #0x26c8
00002670  930800b0  adrp     x19, #0x113000
00002674  600240f9  ldr      x0, [x19]
00002678  800200b4  cbz      x0, #0x26c8
0000267c  080040f9  ldr      x8, [x0]
00002680  c2008052  mov      w2, #6
00002684  81080090  adrp     x1, #0x112000
00002688  21803f91  add      x1, x1, #0xfe0
0000268c  2200a072  movk     w2, #1, lsl #16
00002690  081940f9  ldr      x8, [x8, #0x30]
00002694  00013fd6  blr      x8
00002698  80010034  cbz      w0, #0x26c8
0000269c  1f080031  cmn      w0, #2
000026a0  21010054  b.ne     #0x26c4
000026a4  600240f9  ldr      x0, [x19]
000026a8  81080090  adrp     x1, #0x112000
000026ac  21803f91  add      x1, x1, #0xfe0
000026b0  e2031faa  mov      x2, xzr
000026b4  080040f9  ldr      x8, [x0]
000026b8  081140f9  ldr      x8, [x8, #0x20]
000026bc  00013fd6  blr      x8
000026c0  40000034  cbz      w0, #0x26c8
000026c4  9ff207f9  str      xzr, [x20, #0xfe0]
000026c8  89000090  adrp     x9, #0x12000
000026cc  88f247f9  ldr      x8, [x20, #0xfe0]
000026d0  37594fb9  ldr      w23, [x9, #0xf58]
000026d4  680600b4  cbz      x8, #0x27a0
000026d8  99080090  adrp     x25, #0x112000
000026dc  28f747f9  ldr      x8, [x25, #0xfe8]
000026e0  080600b4  cbz      x8, #0x27a0
000026e4  9c080090  adrp     x28, #0x112000
000026e8  88ff47f9  ldr      x8, [x28, #0xff8]
000026ec  a80500b4  cbz      x8, #0x27a0
000026f0  7f03176b  cmp      w27, w23
000026f4  6a050054  b.ge     #0x27a0
000026f8  c86a4fb9  ldr      w8, [x22, #0xf68]
000026fc  1f090071  cmp      w8, #2
00002700  01050054  b.ne     #0x27a0
00002704  e803182a  mov      w8, w24
00002708  1f2003d5  nop      
0000270c  29430810  adr      x9, #0x12f70
00002710  353d088b  add      x21, x9, x8, lsl #15
00002714  80f247f9  ldr      x0, [x20, #0xfe0]
00002718  21f747f9  ldr      x1, [x25, #0xfe8]
0000271c  82ff47f9  ldr      x2, [x28, #0xff8]
00002720  080040f9  ldr      x8, [x0]
00002724  08f540f9  ldr      x8, [x8, #0x1e8]
00002728  00013fd6  blr      x8
0000272c  88000090  adrp     x8, #0x12000
00002730  1a494fb9  ldr      w26, [x8, #0xf48]
00002734  88000090  adrp     x8, #0x12000
00002738  17594fb9  ldr      w23, [x8, #0xf58]
0000273c  5f070071  cmp      w26, #1
00002740  0b030054  b.lt     #0x27a0
00002744  e8021b4b  sub      w8, w23, w27
00002748  1f050071  cmp      w8, #1
0000274c  ab020054  b.lt     #0x27a0
00002750  5f03086b  cmp      w26, w8
00002754  a0463b8b  add      x0, x21, w27, uxtw #1
00002758  1f2003d5  nop      
0000275c  613f0410  adr      x1, #0xaf48
00002760  5333881a  csel     w19, w26, w8, lo
00002764  627a1f53  lsl      w2, w19, #1
00002768  3a010094  bl       #0x2c50
0000276c  8a000090  adrp     x10, #0x12000
00002770  7b021b0b  add      w27, w19, w27
00002774  484d4fb9  ldr      w8, [x10, #0xf4c]
00002778  0801130b  add      w8, w8, w19
0000277c  1f011a6b  cmp      w8, w26
00002780  e9b39a1a  csel     w9, wzr, w26, lt
00002784  7f03176b  cmp      w27, w23
00002788  0801094b  sub      w8, w8, w9
0000278c  484d0fb9  str      w8, [x10, #0xf4c]
00002790  8a000054  b.ge     #0x27a0
00002794  c86a4fb9  ldr      w8, [x22, #0xf68]
00002798  1f090071  cmp      w8, #2
0000279c  c0fbff54  b.eq     #0x2714
000027a0  e8021b6b  subs     w8, w23, w27
000027a4  2d010054  b.le     #0x27c8
000027a8  e903182a  mov      w9, w24
000027ac  1f2003d5  nop      
000027b0  0a3e0810  adr      x10, #0x12f70
000027b4  493d098b  add      x9, x10, x9, lsl #15
000027b8  027d7f93  sbfiz    x2, x8, #1, #0x20
000027bc  e1031f2a  mov      w1, wzr
000027c0  20c53b8b  add      x0, x9, w27, sxtw #1
000027c4  3b010094  bl       #0x2cb0
000027c8  c86a4fb9  ldr      w8, [x22, #0xf68]
000027cc  94080090  adrp     x20, #0x112000
000027d0  95000090  adrp     x21, #0x12000
000027d4  1f090071  cmp      w8, #2
000027d8  41060054  b.ne     #0x28a0
000027dc  88080090  adrp     x8, #0x112000
000027e0  00e547f9  ldr      x0, [x8, #0xfc8]
000027e4  e00500b4  cbz      x0, #0x28a0
000027e8  080040f9  ldr      x8, [x0]
000027ec  e903182a  mov      w9, w24
000027f0  1f2003d5  nop      
000027f4  ea3b0810  adr      x10, #0x12f70
000027f8  e27a1f53  lsl      w2, w23, #1
000027fc  080140f9  ldr      x8, [x8]
00002800  413d098b  add      x1, x10, x9, lsl #15
00002804  00013fd6  blr      x8
00002808  a8927d39  ldrb     w8, [x21, #0xf64]
0000280c  09028052  mov      w9, #0x10
00002810  0a070011  add      w10, w24, #1
00002814  1f010071  cmp      w8, #0
00002818  88008052  mov      w8, #4
0000281c  2811881a  csel     w8, w9, w8, ne
00002820  89000090  adrp     x9, #0x12000
00002824  5f01086b  cmp      w10, w8
00002828  e8a7981a  csinc    w8, wzr, w24, ge
0000282c  285d0fb9  str      w8, [x9, #0xf5c]
00002830  88080090  adrp     x8, #0x112000
00002834  08f17e39  ldrb     w8, [x8, #0xfbc]
00002838  48030034  cbz      w8, #0x28a0
0000283c  88040090  adrp     x8, #0x92000
00002840  00b947f9  ldr      x0, [x8, #0xf70]
00002844  e00200b4  cbz      x0, #0x28a0
00002848  080040f9  ldr      x8, [x0]
0000284c  930800b0  adrp     x19, #0x113000
00002850  1f2003d5  nop      
00002854  2a394810  adr      x10, #0x92f78
00002858  080140f9  ldr      x8, [x8]
0000285c  690a80b9  ldrsw    x9, [x19, #8]
00002860  413d098b  add      x1, x10, x9, lsl #15
00002864  89000090  adrp     x9, #0x12000
00002868  22594fb9  ldr      w2, [x9, #0xf58]
0000286c  00013fd6  blr      x8
00002870  680a40b9  ldr      w8, [x19, #8]
00002874  0a028052  mov      w10, #0x10
00002878  08050011  add      w8, w8, #1
0000287c  680a00b9  str      w8, [x19, #8]
00002880  680a40b9  ldr      w8, [x19, #8]
00002884  a9927d39  ldrb     w9, [x21, #0xf64]
00002888  3f010071  cmp      w9, #0
0000288c  89008052  mov      w9, #4
00002890  4911891a  csel     w9, w10, w9, ne
00002894  1f01096b  cmp      w8, w9
00002898  4b000054  b.lt     #0x28a0
0000289c  7f0a00b9  str      wzr, [x19, #8]
000028a0  88da4fb9  ldr      w8, [x20, #0xfd8]
000028a4  08050051  sub      w8, w8, #1
000028a8  88da0fb9  str      w8, [x20, #0xfd8]
000028ac  f44f45a9  ldp      x20, x19, [sp, #0x50]
000028b0  f65744a9  ldp      x22, x21, [sp, #0x40]
000028b4  f85f43a9  ldp      x24, x23, [sp, #0x30]
000028b8  fa6742a9  ldp      x26, x25, [sp, #0x20]
000028bc  fc6f41a9  ldp      x28, x27, [sp, #0x10]
000028c0  fd7bc6a8  ldp      x29, x30, [sp], #0x60
000028c4  c0035fd6  ret      
