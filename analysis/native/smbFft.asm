# APK member: lib/arm64-v8a/libnpitchshift.so
# Library SHA-256: 25dff5430d9ff9db374fd01ab3fc879c7215eca59ad5e181fa54ca3dbd3b5cc0
# Function: smbFft; address=0x15d8; bytes=504
000015d8  ffc301d1  sub      sp, sp, #0x70
000015dc  e923016d  stp      d9, d8, [sp, #0x10]
000015e0  fd7b02a9  stp      x29, x30, [sp, #0x20]
000015e4  f91b00f9  str      x25, [sp, #0x30]
000015e8  f85f04a9  stp      x24, x23, [sp, #0x40]
000015ec  f65705a9  stp      x22, x21, [sp, #0x50]
000015f0  f44f06a9  stp      x20, x19, [sp, #0x60]
000015f4  fd830091  add      x29, sp, #0x20
000015f8  35f87fd3  lsl      x21, x1, #1
000015fc  f40302aa  mov      x20, x2
00001600  3f0c00f1  cmp      x1, #3
00001604  f30300aa  mov      x19, x0
00001608  8b030054  b.lt     #0x1678
0000160c  a80a00d1  sub      x8, x21, #2
00001610  49008052  mov      w9, #2
00001614  04000014  b        #0x1624
00001618  29090091  add      x9, x9, #2
0000161c  3f0108eb  cmp      x9, x8
00001620  ca020054  b.ge     #0x1678
00001624  ea031faa  mov      x10, xzr
00001628  4b008052  mov      w11, #2
0000162c  7f0109ea  tst      x11, x9
00001630  6bf97fd3  lsl      x11, x11, #1
00001634  4a058a9a  cinc     x10, x10, ne
00001638  4af97fd3  lsl      x10, x10, #1
0000163c  7f0115eb  cmp      x11, x21
00001640  6bffff54  b.lt     #0x162c
00001644  3f010aeb  cmp      x9, x10
00001648  8afeff54  b.ge     #0x1618
0000164c  6a0a0a8b  add      x10, x19, x10, lsl #2
00001650  6b0a098b  add      x11, x19, x9, lsl #2
00001654  400140bd  ldr      s0, [x10]
00001658  610140bd  ldr      s1, [x11]
0000165c  600100bd  str      s0, [x11]
00001660  410100bd  str      s1, [x10]
00001664  400540bd  ldr      s0, [x10, #4]
00001668  610540bd  ldr      s1, [x11, #4]
0000166c  600500bd  str      s0, [x11, #4]
00001670  410500bd  str      s1, [x10, #4]
00001674  e9ffff17  b        #0x1618
00001678  2000629e  scvtf    d0, x1
0000167c  11020094  bl       #0x1ec0
00001680  e8fffff0  adrp     x8, #0
00001684  01dd44fd  ldr      d1, [x8, #0x9b8]
00001688  0018611e  fdiv     d0, d0, d1
0000168c  01106c1e  fmov     d1, #0.50000000
00001690  0028611e  fadd     d0, d0, d1
00001694  1600789e  fcvtzs   x22, d0
00001698  df0600f1  cmp      x22, #1
0000169c  ab080054  b.lt     #0x17b0
000016a0  8802629e  scvtf    d8, x20
000016a4  e8fffff0  adrp     x8, #0
000016a8  f7031faa  mov      x23, xzr
000016ac  09e544fd  ldr      d9, [x8, #0x9c8]
000016b0  59008052  mov      w25, #2
000016b4  05000014  b        #0x16c8
000016b8  f7060091  add      x23, x23, #1
000016bc  f90314aa  mov      x25, x20
000016c0  ff0216eb  cmp      x23, x22
000016c4  60070054  b.eq     #0x17b0
000016c8  28fb4193  sbfx     x8, x25, #1, #0x3e
000016cc  a0630091  add      x0, x29, #0x18
000016d0  e1230091  add      x1, sp, #8
000016d4  34fb7fd3  lsl      x20, x25, #1
000016d8  38fb4093  sbfx     x24, x25, #0, #0x3f
000016dc  0001629e  scvtf    d0, x8
000016e0  2019601e  fdiv     d0, d9, d0
000016e4  0040621e  fcvt     s0, d0
000016e8  00c0221e  fcvt     d0, s0
000016ec  f1010094  bl       #0x1eb0
000016f0  1f0700f1  cmp      x24, #1
000016f4  2bfeff54  b.lt     #0x16b8
000016f8  a00f40fd  ldr      d0, [x29, #0x18]
000016fc  02e4002f  movi     d2, #0000000000000000
00001700  03102e1e  fmov     s3, #1.00000000
00001704  29f37dd3  lsl      x9, x25, #3
00001708  0af77ed3  lsl      x10, x24, #2
0000170c  e8031faa  mov      x8, xzr
00001710  0108681e  fmul     d1, d0, d8
00001714  e00740fd  ldr      d0, [sp, #8]
00001718  eb0313aa  mov      x11, x19
0000171c  0040621e  fcvt     s0, d0
00001720  2140621e  fcvt     s1, d1
00001724  0a000014  b        #0x174c
00001728  4488211e  fnmul    s4, s2, s1
0000172c  4208201e  fmul     s2, s2, s0
00001730  08090091  add      x8, x8, #2
00001734  1f0118eb  cmp      x8, x24
00001738  6b210091  add      x11, x11, #8
0000173c  6410001f  fmadd    s4, s3, s0, s4
00001740  6208011f  fmadd    s2, s3, s1, s2
00001744  8340201e  fmov     s3, s4
00001748  8afbff54  b.ge     #0x16b8
0000174c  1f0115eb  cmp      x8, x21
00001750  cafeff54  b.ge     #0x1728
00001754  ec030baa  mov      x12, x11
00001758  ed0308aa  mov      x13, x8
0000175c  8e010a8b  add      x14, x12, x10
00001760  870140bd  ldr      s7, [x12]
00001764  ad01148b  add      x13, x13, x20
00001768  c611402d  ldp      s6, s4, [x14]
0000176c  bf0115eb  cmp      x13, x21
00001770  8588221e  fnmul    s5, s4, s2
00001774  6408241e  fmul     s4, s3, s4
00001778  c514031f  fmadd    s5, s6, s3, s5
0000177c  c410021f  fmadd    s4, s6, s2, s4
00001780  e738251e  fsub     s7, s7, s5
00001784  c70100bd  str      s7, [x14]
00001788  860540bd  ldr      s6, [x12, #4]
0000178c  c738241e  fsub     s7, s6, s4
00001790  8428261e  fadd     s4, s4, s6
00001794  c70500bd  str      s7, [x14, #4]
00001798  870140bd  ldr      s7, [x12]
0000179c  a528271e  fadd     s5, s5, s7
000017a0  8511002d  stp      s5, s4, [x12]
000017a4  8c01098b  add      x12, x12, x9
000017a8  abfdff54  b.lt     #0x175c
000017ac  dfffff17  b        #0x1728
000017b0  f44f46a9  ldp      x20, x19, [sp, #0x60]
000017b4  f91b40f9  ldr      x25, [sp, #0x30]
000017b8  f65745a9  ldp      x22, x21, [sp, #0x50]
000017bc  f85f44a9  ldp      x24, x23, [sp, #0x40]
000017c0  fd7b42a9  ldp      x29, x30, [sp, #0x20]
000017c4  e923416d  ldp      d9, d8, [sp, #0x10]
000017c8  ffc30191  add      sp, sp, #0x70
000017cc  c0035fd6  ret      
