# APK member: lib/arm64-v8a/libnpitchshift.so
# Library SHA-256: 25dff5430d9ff9db374fd01ab3fc879c7215eca59ad5e181fa54ca3dbd3b5cc0
# Function: smbPitchShift; address=0x101c; bytes=1468
0000101c  ffc306d1  sub      sp, sp, #0x1b0
00001020  ef3b116d  stp      d15, d14, [sp, #0x110]
00001024  ed33126d  stp      d13, d12, [sp, #0x120]
00001028  eb2b136d  stp      d11, d10, [sp, #0x130]
0000102c  e923146d  stp      d9, d8, [sp, #0x140]
00001030  fd7b15a9  stp      x29, x30, [sp, #0x150]
00001034  fc6f16a9  stp      x28, x27, [sp, #0x160]
00001038  fa6717a9  stp      x26, x25, [sp, #0x170]
0000103c  f85f18a9  stp      x24, x23, [sp, #0x180]
00001040  f65719a9  stp      x22, x21, [sp, #0x190]
00001044  f44f1aa9  stp      x20, x19, [sp, #0x1a0]
00001048  fd430591  add      x29, sp, #0x150
0000104c  4a0cc39a  sdiv     x10, x2, x3
00001050  0e40201e  fmov     s14, s0
00001054  0d019652  mov      w13, #0xb008
00001058  09686df8  ldr      x9, [x0, x13]
0000105c  5f0000f1  cmp      x2, #0
00001060  f50302aa  mov      x21, x2
00001064  48a4829a  cinc     x8, x2, lt
00001068  4e000acb  sub      x14, x2, x10
0000106c  aa8317f8  stur     x10, [x29, #-0x88]
00001070  490000b5  cbnz     x9, #0x1078
00001074  0e682df8  str      x14, [x0, x13]
00001078  3f0400f1  cmp      x1, #1
0000107c  6b290054  b.lt     #0x15a8
00001080  20c0221e  fcvt     d0, s1
00001084  a902629e  scvtf    d9, x21
00001088  ac8357f8  ldur     x12, [x29, #-0x88]
0000108c  e9fffff0  adrp     x9, #0
00001090  1cfd4193  asr      x28, x8, #1
00001094  08019252  mov      w8, #0x9008
00001098  2be144fd  ldr      d11, [x9, #0x9c0]
0000109c  09018e52  mov      w9, #0x7008
000010a0  8a008952  mov      w10, #0x4804
000010a4  0800088b  add      x8, x0, x8
000010a8  0900098b  add      x9, x0, x9
000010ac  94f57ed3  lsl      x20, x12, #2
000010b0  0a18691e  fdiv     d10, d0, d9
000010b4  8001629e  scvtf    d0, x12
000010b8  a80317f8  stur     x8, [x29, #-0x90]
000010bc  887f039b  mul      x8, x28, x3
000010c0  e92b00f9  str      x9, [sp, #0x50]
000010c4  09000a8b  add      x9, x0, x10
000010c8  e90700f9  str      x9, [sp, #8]
000010cc  09019452  mov      w9, #0xa008
000010d0  aa0a0091  add      x10, x21, #2
000010d4  0900098b  add      x9, x0, x9
000010d8  b0f67ed3  lsl      x16, x21, #2
000010dc  df2100f1  cmp      x14, #8
000010e0  00086b1e  fmul     d0, d0, d11
000010e4  a98316f8  stur     x9, [x29, #-0x98]
000010e8  a9fa7fd3  lsl      x9, x21, #1
000010ec  0b048052  mov      w11, #0x20
000010f0  6d00629e  scvtf    d13, x3
000010f4  0f106c1e  fmov     d15, #0.50000000
000010f8  e92b07a9  stp      x9, x10, [sp, #0x70]
000010fc  09018452  mov      w9, #0x2008
00001100  ea0314cb  neg      x10, x20
00001104  40214bfa  ccmp     x10, x11, #0, hs
00001108  08107c1e  fmov     d8, #-0.50000000
0000110c  ef031faa  mov      x15, xzr
00001110  13044091  add      x19, x0, #1, lsl #12
00001114  1a084091  add      x26, x0, #2, lsl #12
00001118  19104091  add      x25, x0, #4, lsl #12
0000111c  98070091  add      x24, x28, #1
00001120  f6ff8392  mov      x22, #-0x2000
00001124  b08318f8  stur     x16, [x29, #-0x78]
00001128  e4170aa9  stp      x4, x5, [sp, #0xa0]
0000112c  e10309a9  stp      x1, x0, [sp, #0x90]
00001130  ee3f00bd  str      s14, [sp, #0x3c]
00001134  f33b08a9  stp      x19, x14, [sp, #0x80]
00001138  f43700f9  str      x20, [sp, #0x68]
0000113c  0218691e  fdiv     d2, d0, d9
00001140  0001629e  scvtf    d0, x8
00001144  08018a52  mov      w8, #0x5008
00001148  1700088b  add      x23, x0, x8
0000114c  0800098b  add      x8, x0, x9
00001150  c9ed7d92  and      x9, x14, #0x7ffffffffffffff8
00001154  e81b00f9  str      x8, [sp, #0x30]
00001158  082200d1  sub      x8, x16, #8
0000115c  e81700f9  str      x8, [sp, #0x28]
00001160  e8279f1a  cset     w8, lo
00001164  e84f00b9  str      w8, [sp, #0x4c]
00001168  88008452  mov      w8, #0x2004
0000116c  0800088b  add      x8, x0, x8
00001170  e92300f9  str      x9, [sp, #0x40]
00001174  e9fffff0  adrp     x9, #0
00001178  a80316f8  stur     x8, [x29, #-0xa0]
0000117c  e8fffff0  adrp     x8, #0
00001180  24d944fd  ldr      d4, [x9, #0x9b0]
00001184  03e544fd  ldr      d3, [x8, #0x9c8]
00001188  880115cb  sub      x8, x12, x21
0000118c  e03300fd  str      d0, [sp, #0x60]
00001190  e80b00f9  str      x8, [sp, #0x10]
00001194  e48f016d  stp      d4, d3, [sp, #0x18]
00001198  e22f00fd  str      d2, [sp, #0x58]
0000119c  04000014  b        #0x11ac
000011a0  ef050091  add      x15, x15, #1
000011a4  ff0101eb  cmp      x15, x1
000011a8  00200054  b.eq     #0x15a8
000011ac  e8f17dd3  lsl      x8, x15, #3
000011b0  0a686df8  ldr      x10, [x0, x13]
000011b4  896868f8  ldr      x9, [x4, x8]
000011b8  2001229e  scvtf    s0, x9
000011bc  a96868f8  ldr      x9, [x5, x8]
000011c0  2101229e  scvtf    s1, x9
000011c4  49010ecb  sub      x9, x10, x14
000011c8  00782abc  str      s0, [x0, x10, lsl #2]
000011cc  607a69bc  ldr      s0, [x19, x9, lsl #2]
000011d0  0028211e  fadd     s0, s0, s1
000011d4  0900389e  fcvtzs   x9, s0
000011d8  a96828f8  str      x9, [x5, x8]
000011dc  08686df8  ldr      x8, [x0, x13]
000011e0  08050091  add      x8, x8, #1
000011e4  08682df8  str      x8, [x0, x13]
000011e8  1f0115eb  cmp      x8, x21
000011ec  abfdff54  b.lt     #0x11a0
000011f0  bf0600f1  cmp      x21, #1
000011f4  af0318f8  stur     x15, [x29, #-0x80]
000011f8  0e682df8  str      x14, [x0, x13]
000011fc  4b020054  b.lt     #0x1244
00001200  b40356f8  ldur     x20, [x29, #-0xa0]
00001204  f3031faa  mov      x19, xzr
00001208  6002629e  scvtf    d0, x19
0000120c  00086b1e  fmul     d0, d0, d11
00001210  0018691e  fdiv     d0, d0, d9
00001214  1b030094  bl       #0x1e80
00001218  480b138b  add      x8, x26, x19, lsl #2
0000121c  003c481f  fmadd    d0, d0, d8, d15
00001220  73060091  add      x19, x19, #1
00001224  bf0213eb  cmp      x21, x19
00001228  016976bc  ldr      s1, [x8, x22]
0000122c  21c0221e  fcvt     d1, s1
00001230  0008611e  fmul     d0, d0, d1
00001234  0040621e  fcvt     s0, d0
00001238  80c21fbc  stur     s0, [x20, #-4]
0000123c  9f8600b8  str      wzr, [x20], #8
00001240  41feff54  b.ne     #0x1208
00001244  e0031aaa  mov      x0, x26
00001248  e10315aa  mov      x1, x21
0000124c  02008092  mov      x2, #-1
00001250  10030094  bl       #0x1e90
00001254  bf0600b1  cmn      x21, #1
00001258  6a010054  b.ge     #0x1284
0000125c  b38358f8  ldur     x19, [x29, #-0x78]
00001260  a08356f8  ldur     x0, [x29, #-0x98]
00001264  e1031f2a  mov      w1, wzr
00001268  e20313aa  mov      x2, x19
0000126c  f9020094  bl       #0x1e50
00001270  a00357f8  ldur     x0, [x29, #-0x90]
00001274  e1031f2a  mov      w1, wzr
00001278  e20313aa  mov      x2, x19
0000127c  f5020094  bl       #0x1e50
00001280  78000014  b        #0x1460
00001284  ae41601e  fmov     d14, d13
00001288  e8bf416d  ldp      d8, d15, [sp, #0x18]
0000128c  bb0356f8  ldur     x27, [x29, #-0xa0]
00001290  f42b40f9  ldr      x20, [sp, #0x50]
00001294  f6031faa  mov      x22, xzr
00001298  ed2f40fd  ldr      d13, [sp, #0x58]
0000129c  61837f2d  ldp      s1, s0, [x27, #-4]
000012a0  00c0221e  fcvt     d0, s0
000012a4  21c0221e  fcvt     d1, s1
000012a8  0208601e  fmul     d2, d0, d0
000012ac  2208411f  fmadd    d2, d1, d1, d2
000012b0  42c0611e  fsqrt    d2, d2
000012b4  4c28621e  fadd     d12, d2, d2
000012b8  fa020094  bl       #0x1ea0
000012bc  c8f67ed3  lsl      x8, x22, #2
000012c0  c202629e  scvtf    d2, x22
000012c4  d6060091  add      x22, x22, #1
000012c8  7b230091  add      x27, x27, #8
000012cc  216b68bc  ldr      s1, [x25, x8]
000012d0  21c0221e  fcvt     d1, s1
000012d4  0138611e  fsub     d1, d0, d1
000012d8  0040621e  fcvt     s0, d0
000012dc  206b28bc  str      s0, [x25, x8]
000012e0  41844d1f  fmsub    d1, d2, d13, d1
000012e4  23186f1e  fdiv     d3, d1, d15
000012e8  6900789e  fcvtzs   x9, d3
000012ec  2a014092  and      x10, x9, #1
000012f0  3f0100f1  cmp      x9, #0
000012f4  4a01098b  add      x10, x10, x9
000012f8  29f97f92  and      x9, x9, #0xfffffffffffffffe
000012fc  29b18a9a  csel     x9, x9, x10, lt
00001300  1f0316eb  cmp      x24, x22
00001304  2301629e  scvtf    d3, x9
00001308  6104481f  fmadd    d1, d3, d8, d1
0000130c  21086e1e  fmul     d1, d1, d14
00001310  21186b1e  fdiv     d1, d1, d11
00001314  4109611e  fmul     d1, d10, d1
00001318  41044a1f  fmadd    d1, d2, d10, d1
0000131c  8241621e  fcvt     s2, d12
00001320  2140621e  fcvt     s1, d1
00001324  820210bd  str      s2, [x20, #0x1000]
00001328  814600bc  str      s1, [x20], #4
0000132c  81fbff54  b.ne     #0x129c
00001330  b38358f8  ldur     x19, [x29, #-0x78]
00001334  a08356f8  ldur     x0, [x29, #-0x98]
00001338  e1031f2a  mov      w1, wzr
0000133c  e20313aa  mov      x2, x19
00001340  c4020094  bl       #0x1e50
00001344  a00357f8  ldur     x0, [x29, #-0x90]
00001348  e1031f2a  mov      w1, wzr
0000134c  e20313aa  mov      x2, x19
00001350  c0020094  bl       #0x1e50
00001354  cd41601e  fmov     d13, d14
00001358  0f106c1e  fmov     d15, #0.50000000
0000135c  e03f40bd  ldr      s0, [sp, #0x3c]
00001360  08107c1e  fmov     d8, #-0.50000000
00001364  bf0600b1  cmn      x21, #1
00001368  cb070054  b.lt     #0x1460
0000136c  0e40201e  fmov     s14, s0
00001370  acaf76a9  ldp      x12, x11, [x29, #-0x98]
00001374  e92b40f9  ldr      x9, [sp, #0x50]
00001378  08008092  mov      x8, #-1
0000137c  04000014  b        #0x138c
00001380  9f0308eb  cmp      x28, x8
00001384  29110091  add      x9, x9, #4
00001388  00020054  b.eq     #0x13c8
0000138c  08050091  add      x8, x8, #1
00001390  0001229e  scvtf    s0, x8
00001394  00082e1e  fmul     s0, s0, s14
00001398  0a00389e  fcvtzs   x10, s0
0000139c  9f030aeb  cmp      x28, x10
000013a0  0bffff54  b.lt     #0x1380
000013a4  4af57ed3  lsl      x10, x10, #2
000013a8  200150bd  ldr      s0, [x9, #0x1000]
000013ac  81696abc  ldr      s1, [x12, x10]
000013b0  0028211e  fadd     s0, s0, s1
000013b4  80692abc  str      s0, [x12, x10]
000013b8  200140bd  ldr      s0, [x9]
000013bc  00082e1e  fmul     s0, s0, s14
000013c0  60692abc  str      s0, [x11, x10]
000013c4  efffff17  b        #0x1380
000013c8  ec2f40fd  ldr      d12, [sp, #0x58]
000013cc  bf0600b1  cmn      x21, #1
000013d0  8b040054  b.lt     #0x1460
000013d4  bb0357f8  ldur     x27, [x29, #-0x90]
000013d8  f30740f9  ldr      x19, [sp, #8]
000013dc  14008092  mov      x20, #-1
000013e0  f6031aaa  mov      x22, x26
000013e4  600340bd  ldr      s0, [x27]
000013e8  94060091  add      x20, x20, #1
000013ec  620240bd  ldr      s2, [x19]
000013f0  8102629e  scvtf    d1, x20
000013f4  a04301d1  sub      x0, x29, #0x50
000013f8  a16301d1  sub      x1, x29, #0x58
000013fc  00c0221e  fcvt     d0, s0
00001400  20804a1f  fmsub    d0, d1, d10, d0
00001404  00186a1e  fdiv     d0, d0, d10
00001408  00086b1e  fmul     d0, d0, d11
0000140c  00186d1e  fdiv     d0, d0, d13
00001410  20004c1f  fmadd    d0, d1, d12, d0
00001414  41c0221e  fcvt     d1, s2
00001418  0028611e  fadd     d0, d0, d1
0000141c  0140621e  fcvt     s1, d0
00001420  600350bd  ldr      s0, [x27, #0x1000]
00001424  00c0221e  fcvt     d0, s0
00001428  614600bc  str      s1, [x19], #4
0000142c  a003993c  stur     q0, [x29, #-0x70]
00001430  20c0221e  fcvt     d0, s1
00001434  9f020094  bl       #0x1eb0
00001438  a0835afc  ldur     d0, [x29, #-0x58]
0000143c  a84301d1  sub      x8, x29, #0x50
00001440  a103d93c  ldur     q1, [x29, #-0x70]
00001444  9f0314eb  cmp      x28, x20
00001448  7b130091  add      x27, x27, #4
0000144c  0085404d  ld1      {v0.d}[1], [x8]
00001450  0090c14f  fmul     v0.2d, v0.2d, v1.d[0]
00001454  0068610e  fcvtn    v0.2s, v0.2d
00001458  c08600fc  str      d0, [x22], #8
0000145c  41fcff54  b.ne     #0x13e4
00001460  e92347a9  ldp      x9, x8, [sp, #0x70]
00001464  1f0109eb  cmp      x8, x9
00001468  ca000054  b.ge     #0x1480
0000146c  e2a742a9  ldp      x2, x9, [sp, #0x28]
00001470  e1031f2a  mov      w1, wzr
00001474  a88358f8  ldur     x8, [x29, #-0x78]
00001478  2001088b  add      x0, x9, x8
0000147c  75020094  bl       #0x1e50
00001480  e0031aaa  mov      x0, x26
00001484  e10315aa  mov      x1, x21
00001488  22008052  mov      w2, #1
0000148c  81020094  bl       #0x1e90
00001490  ec3340fd  ldr      d12, [sp, #0x60]
00001494  bf0600f1  cmp      x21, #1
00001498  cb020054  b.lt     #0x14f0
0000149c  f3031faa  mov      x19, xzr
000014a0  f4031aaa  mov      x20, x26
000014a4  6002629e  scvtf    d0, x19
000014a8  00086b1e  fmul     d0, d0, d11
000014ac  0018691e  fdiv     d0, d0, d9
000014b0  74020094  bl       #0x1e80
000014b4  003c481f  fmadd    d0, d0, d8, d15
000014b8  818640bc  ldr      s1, [x20], #8
000014bc  68f67ed3  lsl      x8, x19, #2
000014c0  73060091  add      x19, x19, #1
000014c4  21c0221e  fcvt     d1, s1
000014c8  bf0213eb  cmp      x21, x19
000014cc  0028601e  fadd     d0, d0, d0
000014d0  0008611e  fmul     d0, d0, d1
000014d4  e16a68bc  ldr      s1, [x23, x8]
000014d8  21c0221e  fcvt     d1, s1
000014dc  00186c1e  fdiv     d0, d0, d12
000014e0  0028611e  fadd     d0, d0, d1
000014e4  0040621e  fcvt     s0, d0
000014e8  e06a28bc  str      s0, [x23, x8]
000014ec  c1fdff54  b.ne     #0x14a4
000014f0  a88357f8  ldur     x8, [x29, #-0x88]
000014f4  f34340f9  ldr      x19, [sp, #0x80]
000014f8  f6ff8392  mov      x22, #-0x2000
000014fc  f43740f9  ldr      x20, [sp, #0x68]
00001500  1f0500f1  cmp      x8, #1
00001504  ab000054  b.lt     #0x1518
00001508  e00313aa  mov      x0, x19
0000150c  e10317aa  mov      x1, x23
00001510  e20314aa  mov      x2, x20
00001514  57020094  bl       #0x1e70
00001518  a28358f8  ldur     x2, [x29, #-0x78]
0000151c  e102148b  add      x1, x23, x20
00001520  e00317aa  mov      x0, x23
00001524  53020094  bl       #0x1e70
00001528  ee8748a9  ldp      x14, x1, [sp, #0x88]
0000152c  0d019652  mov      w13, #0xb008
00001530  e4174aa9  ldp      x4, x5, [sp, #0xa0]
00001534  e04f40f9  ldr      x0, [sp, #0x98]
00001538  af0358f8  ldur     x15, [x29, #-0x80]
0000153c  df0500f1  cmp      x14, #1
00001540  0be3ff54  b.lt     #0x11a0
00001544  e84f40b9  ldr      w8, [sp, #0x4c]
00001548  68000036  tbz      w8, #0, #0x1554
0000154c  ea031faa  mov      x10, xzr
00001550  0b000014  b        #0x157c
00001554  e82340f9  ldr      x8, [sp, #0x40]
00001558  e90300aa  mov      x9, x0
0000155c  2a01148b  add      x10, x9, x20
00001560  082100f1  subs     x8, x8, #8
00001564  400540ad  ldp      q0, q1, [x10]
00001568  200581ac  stp      q0, q1, [x9], #0x20
0000156c  81ffff54  b.ne     #0x155c
00001570  ea2340f9  ldr      x10, [sp, #0x40]
00001574  df010aeb  cmp      x14, x10
00001578  40e1ff54  b.eq     #0x11a0
0000157c  a88357f8  ldur     x8, [x29, #-0x88]
00001580  eb0b40f9  ldr      x11, [sp, #0x10]
00001584  09010a8b  add      x9, x8, x10
00001588  08080a8b  add      x8, x0, x10, lsl #2
0000158c  6a010a8b  add      x10, x11, x10
00001590  0908098b  add      x9, x0, x9, lsl #2
00001594  204540bc  ldr      s0, [x9], #4
00001598  4a0500b1  adds     x10, x10, #1
0000159c  004500bc  str      s0, [x8], #4
000015a0  a3ffff54  b.lo     #0x1594
000015a4  fffeff17  b        #0x11a0
000015a8  f44f5aa9  ldp      x20, x19, [sp, #0x1a0]
000015ac  f65759a9  ldp      x22, x21, [sp, #0x190]
000015b0  f85f58a9  ldp      x24, x23, [sp, #0x180]
000015b4  fa6757a9  ldp      x26, x25, [sp, #0x170]
000015b8  fc6f56a9  ldp      x28, x27, [sp, #0x160]
000015bc  fd7b55a9  ldp      x29, x30, [sp, #0x150]
000015c0  e923546d  ldp      d9, d8, [sp, #0x140]
000015c4  eb2b536d  ldp      d11, d10, [sp, #0x130]
000015c8  ed33526d  ldp      d13, d12, [sp, #0x120]
000015cc  ef3b516d  ldp      d15, d14, [sp, #0x110]
000015d0  ffc30691  add      sp, sp, #0x1b0
000015d4  c0035fd6  ret      
