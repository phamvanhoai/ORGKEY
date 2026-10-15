# Function: Java_sofeh_audio_NGVerb_processStereoLeft; bytes=212
00002740  620600b4  cbz      x2, #0x280c
00002744  fd7bbca9  stp      x29, x30, [sp, #-0x40]!
00002748  f70b00f9  str      x23, [sp, #0x10]
0000274c  f65702a9  stp      x22, x21, [sp, #0x20]
00002750  f44f03a9  stp      x20, x19, [sp, #0x30]
00002754  fd030091  mov      x29, sp
00002758  a000221e  scvtf    s0, w5
0000275c  0859a852  mov      w8, #0x42c80000
00002760  62c4029e  scvtf    s2, x3, #0xf
00002764  0101271e  fmov     s1, w8
00002768  35000090  adrp     x21, #0x6000
0000276c  83c4029e  scvtf    s3, x4, #0xf
00002770  b5ae47f9  ldr      x21, [x21, #0xf58]
00002774  36000090  adrp     x22, #0x6000
00002778  37000090  adrp     x23, #0x6000
0000277c  d6b247f9  ldr      x22, [x22, #0xf60]
00002780  33000090  adrp     x19, #0x6000
00002784  34000090  adrp     x20, #0x6000
00002788  0118211e  fdiv     s1, s0, s1
0000278c  a20200bd  str      s2, [x21]
00002790  e00302aa  mov      x0, x2
00002794  f7b647f9  ldr      x23, [x23, #0xf68]
00002798  4028231e  fadd     s0, s2, s3
0000279c  c30200bd  str      s3, [x22]
000027a0  e10200bd  str      s1, [x23]
000027a4  73ba47f9  ldr      x19, [x19, #0xf70]
000027a8  94be47f9  ldr      x20, [x20, #0xf78]
000027ac  e10313aa  mov      x1, x19
000027b0  e20314aa  mov      x2, x20
000027b4  5ffeff97  bl       #0x2130
000027b8  00102e1e  fmov     s0, #1.00000000
000027bc  e10240bd  ldr      s1, [x23]
000027c0  820240bd  ldr      s2, [x20]
000027c4  630240bd  ldr      s3, [x19]
000027c8  a40240bd  ldr      s4, [x21]
000027cc  29000090  adrp     x9, #0x6000
000027d0  2208221e  fmul     s2, s1, s2
000027d4  0038211e  fsub     s0, s0, s1
000027d8  2108231e  fmul     s1, s1, s3
000027dc  c30240bd  ldr      s3, [x22]
000027e0  29c147f9  ldr      x9, [x9, #0xf80]
000027e4  6208001f  fmadd    s2, s3, s0, s2
000027e8  8004001f  fmadd    s0, s4, s0, s1
000027ec  48c4189e  fcvtzs   x8, s2, #0xf
000027f0  00c4189e  fcvtzs   x0, s0, #0xf
000027f4  280100f9  str      x8, [x9]
000027f8  f44f43a9  ldp      x20, x19, [sp, #0x30]
000027fc  f70b40f9  ldr      x23, [sp, #0x10]
00002800  f65742a9  ldp      x22, x21, [sp, #0x20]
00002804  fd7bc4a8  ldp      x29, x30, [sp], #0x40
00002808  c0035fd6  ret      
0000280c  e0031faa  mov      x0, xzr
00002810  c0035fd6  ret      
