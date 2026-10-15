# APK member: lib/arm64-v8a/libnpitchshift.so
# Library SHA-256: 25dff5430d9ff9db374fd01ab3fc879c7215eca59ad5e181fa54ca3dbd3b5cc0
# Function: smbPitchShiftSample; address=0x17d0; bytes=1216
000017d0  ffc303d1  sub      sp, sp, #0xf0
000017d4  ef3b056d  stp      d15, d14, [sp, #0x50]
000017d8  ed33066d  stp      d13, d12, [sp, #0x60]
000017dc  eb2b076d  stp      d11, d10, [sp, #0x70]
000017e0  e923086d  stp      d9, d8, [sp, #0x80]
000017e4  fd7b09a9  stp      x29, x30, [sp, #0x90]
000017e8  fc6f0aa9  stp      x28, x27, [sp, #0xa0]
000017ec  fa670ba9  stp      x26, x25, [sp, #0xb0]
000017f0  f85f0ca9  stp      x24, x23, [sp, #0xc0]
000017f4  f6570da9  stp      x22, x21, [sp, #0xd0]
000017f8  f44f0ea9  stp      x20, x19, [sp, #0xe0]
000017fc  fd430291  add      x29, sp, #0x90
00001800  2b0cc29a  sdiv     x11, x1, x2
00001804  08019652  mov      w8, #0xb008
00001808  3f0000f1  cmp      x1, #0
0000180c  096868f8  ldr      x9, [x0, x8]
00001810  62c4029e  scvtf    s2, x3, #0xf
00001814  35a4819a  cinc     x21, x1, lt
00001818  0d044091  add      x13, x0, #1, lsl #12
0000181c  3f0100f1  cmp      x9, #0
00001820  2c000bcb  sub      x12, x1, x11
00001824  8901899a  csel     x9, x12, x9, eq
00001828  027829bc  str      s2, [x0, x9, lsl #2]
0000182c  2a010ccb  sub      x10, x9, x12
00001830  29050091  add      x9, x9, #1
00001834  a2796abc  ldr      s2, [x13, x10, lsl #2]
00001838  3f0101eb  cmp      x9, x1
0000183c  096828f8  str      x9, [x0, x8]
00001840  ab200054  b.lt     #0x1c54
00001844  e03300bd  str      s0, [sp, #0x30]
00001848  6001629e  scvtf    d0, x11
0000184c  e9fffff0  adrp     x9, #0
00001850  2ae144fd  ldr      d10, [x9, #0x9c0]
00001854  29c0221e  fcvt     d9, s1
00001858  2b00629e  scvtf    d11, x1
0000185c  09019252  mov      w9, #0x9008
00001860  0a018e52  mov      w10, #0x7008
00001864  f30301aa  mov      x19, x1
00001868  f90300aa  mov      x25, x0
0000186c  3f0400f1  cmp      x1, #1
00001870  1800098b  add      x24, x0, x9
00001874  0d086a1e  fmul     d13, d0, d10
00001878  14000a8b  add      x20, x0, x10
0000187c  e21300f9  str      x2, [sp, #0x20]
00001880  ed0300f9  str      x13, [sp]
00001884  e21f00bd  str      s2, [sp, #0x1c]
00001888  eb0b00f9  str      x11, [sp, #0x10]
0000188c  ec0700f9  str      x12, [sp, #8]
00001890  0c6828f8  str      x12, [x0, x8]
00001894  8b020054  b.lt     #0x18e4
00001898  0c106c1e  fmov     d12, #0.50000000
0000189c  0e107c1e  fmov     d14, #-0.50000000
000018a0  88008452  mov      w8, #0x2004
000018a4  f6031faa  mov      x22, xzr
000018a8  3703088b  add      x23, x25, x8
000018ac  c002629e  scvtf    d0, x22
000018b0  00086a1e  fmul     d0, d0, d10
000018b4  00186b1e  fdiv     d0, d0, d11
000018b8  72010094  bl       #0x1e80
000018bc  217b76bc  ldr      s1, [x25, x22, lsl #2]
000018c0  00304e1f  fmadd    d0, d0, d14, d12
000018c4  d6060091  add      x22, x22, #1
000018c8  7f0216eb  cmp      x19, x22
000018cc  21c0221e  fcvt     d1, s1
000018d0  0008611e  fmul     d0, d0, d1
000018d4  0040621e  fcvt     s0, d0
000018d8  e0c21fbc  stur     s0, [x23, #-4]
000018dc  ff8600b8  str      wzr, [x23], #8
000018e0  61feff54  b.ne     #0x18ac
000018e4  370b4091  add      x23, x25, #2, lsl #12
000018e8  e10313aa  mov      x1, x19
000018ec  02008092  mov      x2, #-1
000018f0  e00317aa  mov      x0, x23
000018f4  2c196b1e  fdiv     d12, d9, d11
000018f8  b5fe4193  asr      x21, x21, #1
000018fc  f91700f9  str      x25, [sp, #0x28]
00001900  ad196b1e  fdiv     d13, d13, d11
00001904  63010094  bl       #0x1e90
00001908  7f0600b1  cmn      x19, #1
0000190c  4b060054  b.lt     #0x19d4
00001910  e82f42a9  ldp      x8, x11, [sp, #0x20]
00001914  e9fffff0  adrp     x9, #0
00001918  8a008452  mov      w10, #0x2004
0000191c  29d944fd  ldr      d9, [x9, #0x9b0]
00001920  f9031faa  mov      x25, xzr
00001924  bb060091  add      x27, x21, #1
00001928  f60314aa  mov      x22, x20
0000192c  0e01629e  scvtf    d14, x8
00001930  e8fffff0  adrp     x8, #0
00001934  7a114091  add      x26, x11, #4, lsl #12
00001938  0fe544fd  ldr      d15, [x8, #0x9c8]
0000193c  7c010a8b  add      x28, x11, x10
00001940  81837f2d  ldp      s1, s0, [x28, #-4]
00001944  00c0221e  fcvt     d0, s0
00001948  21c0221e  fcvt     d1, s1
0000194c  0208601e  fmul     d2, d0, d0
00001950  2208411f  fmadd    d2, d1, d1, d2
00001954  42c0611e  fsqrt    d2, d2
00001958  4828621e  fadd     d8, d2, d2
0000195c  51010094  bl       #0x1ea0
00001960  28f77ed3  lsl      x8, x25, #2
00001964  2203629e  scvtf    d2, x25
00001968  39070091  add      x25, x25, #1
0000196c  9c230091  add      x28, x28, #8
00001970  416b68bc  ldr      s1, [x26, x8]
00001974  21c0221e  fcvt     d1, s1
00001978  0138611e  fsub     d1, d0, d1
0000197c  0040621e  fcvt     s0, d0
00001980  406b28bc  str      s0, [x26, x8]
00001984  41844d1f  fmsub    d1, d2, d13, d1
00001988  23186f1e  fdiv     d3, d1, d15
0000198c  6900789e  fcvtzs   x9, d3
00001990  2a014092  and      x10, x9, #1
00001994  3f0100f1  cmp      x9, #0
00001998  4a01098b  add      x10, x10, x9
0000199c  29f97f92  and      x9, x9, #0xfffffffffffffffe
000019a0  29b18a9a  csel     x9, x9, x10, lt
000019a4  7f0319eb  cmp      x27, x25
000019a8  2301629e  scvtf    d3, x9
000019ac  6104491f  fmadd    d1, d3, d9, d1
000019b0  21086e1e  fmul     d1, d1, d14
000019b4  21186a1e  fdiv     d1, d1, d10
000019b8  8109611e  fmul     d1, d12, d1
000019bc  41044c1f  fmadd    d1, d2, d12, d1
000019c0  0241621e  fcvt     s2, d8
000019c4  2140621e  fcvt     s1, d1
000019c8  c20210bd  str      s2, [x22, #0x1000]
000019cc  c14600bc  str      s1, [x22], #4
000019d0  81fbff54  b.ne     #0x1940
000019d4  fc1740f9  ldr      x28, [sp, #0x28]
000019d8  08019452  mov      w8, #0xa008
000019dc  79f67ed3  lsl      x25, x19, #2
000019e0  e1031f2a  mov      w1, wzr
000019e4  9a03088b  add      x26, x28, x8
000019e8  e20319aa  mov      x2, x25
000019ec  e0031aaa  mov      x0, x26
000019f0  18010094  bl       #0x1e50
000019f4  e00318aa  mov      x0, x24
000019f8  e1031f2a  mov      w1, wzr
000019fc  e20319aa  mov      x2, x25
00001a00  14010094  bl       #0x1e50
00001a04  7f0600b1  cmn      x19, #1
00001a08  ab070054  b.lt     #0x1afc
00001a0c  88008952  mov      w8, #0x4804
00001a10  e23340bd  ldr      s2, [sp, #0x30]
00001a14  9b03088b  add      x27, x28, x8
00001a18  08008092  mov      x8, #-1
00001a1c  04000014  b        #0x1a2c
00001a20  bf0208eb  cmp      x21, x8
00001a24  94120091  add      x20, x20, #4
00001a28  00020054  b.eq     #0x1a68
00001a2c  08050091  add      x8, x8, #1
00001a30  0001229e  scvtf    s0, x8
00001a34  0008221e  fmul     s0, s0, s2
00001a38  0900389e  fcvtzs   x9, s0
00001a3c  bf0209eb  cmp      x21, x9
00001a40  0bffff54  b.lt     #0x1a20
00001a44  29f57ed3  lsl      x9, x9, #2
00001a48  800250bd  ldr      s0, [x20, #0x1000]
00001a4c  416b69bc  ldr      s1, [x26, x9]
00001a50  0028211e  fadd     s0, s0, s1
00001a54  406b29bc  str      s0, [x26, x9]
00001a58  800240bd  ldr      s0, [x20]
00001a5c  0008221e  fmul     s0, s0, s2
00001a60  006b29bc  str      s0, [x24, x9]
00001a64  efffff17  b        #0x1a20
00001a68  7f0600b1  cmn      x19, #1
00001a6c  8b040054  b.lt     #0x1afc
00001a70  e81340f9  ldr      x8, [sp, #0x20]
00001a74  16008092  mov      x22, #-1
00001a78  f4230191  add      x20, sp, #0x48
00001a7c  fa0317aa  mov      x26, x23
00001a80  0801629e  scvtf    d8, x8
00001a84  000340bd  ldr      s0, [x24]
00001a88  d6060091  add      x22, x22, #1
00001a8c  620340bd  ldr      s2, [x27]
00001a90  c102629e  scvtf    d1, x22
00001a94  e0230191  add      x0, sp, #0x48
00001a98  e1030191  add      x1, sp, #0x40
00001a9c  00c0221e  fcvt     d0, s0
00001aa0  20804c1f  fmsub    d0, d1, d12, d0
00001aa4  00186c1e  fdiv     d0, d0, d12
00001aa8  00086a1e  fmul     d0, d0, d10
00001aac  0018681e  fdiv     d0, d0, d8
00001ab0  20004d1f  fmadd    d0, d1, d13, d0
00001ab4  41c0221e  fcvt     d1, s2
00001ab8  0028611e  fadd     d0, d0, d1
00001abc  0140621e  fcvt     s1, d0
00001ac0  000350bd  ldr      s0, [x24, #0x1000]
00001ac4  00c0221e  fcvt     d0, s0
00001ac8  614700bc  str      s1, [x27], #4
00001acc  e00f803d  str      q0, [sp, #0x30]
00001ad0  20c0221e  fcvt     d0, s1
00001ad4  f7000094  bl       #0x1eb0
00001ad8  e02340fd  ldr      d0, [sp, #0x40]
00001adc  e10fc03d  ldr      q1, [sp, #0x30]
00001ae0  bf0216eb  cmp      x21, x22
00001ae4  18130091  add      x24, x24, #4
00001ae8  8086404d  ld1      {v0.d}[1], [x20]
00001aec  0090c14f  fmul     v0.2d, v0.2d, v1.d[0]
00001af0  0068610e  fcvtn    v0.2s, v0.2d
00001af4  408700fc  str      d0, [x26], #8
00001af8  61fcff54  b.ne     #0x1a84
00001afc  680a0091  add      x8, x19, #2
00001b00  1f0513eb  cmp      x8, x19, lsl #1
00001b04  ea000054  b.ge     #0x1b20
00001b08  8803198b  add      x8, x28, x25
00001b0c  09018452  mov      w9, #0x2008
00001b10  222300d1  sub      x2, x25, #8
00001b14  0001098b  add      x0, x8, x9
00001b18  e1031f2a  mov      w1, wzr
00001b1c  cd000094  bl       #0x1e50
00001b20  e00317aa  mov      x0, x23
00001b24  e10313aa  mov      x1, x19
00001b28  22008052  mov      w2, #1
00001b2c  d9000094  bl       #0x1e90
00001b30  7f0600f1  cmp      x19, #1
00001b34  8b030054  b.lt     #0x1ba4
00001b38  e81340f9  ldr      x8, [sp, #0x20]
00001b3c  09106c1e  fmov     d9, #0.50000000
00001b40  0c107c1e  fmov     d12, #-0.50000000
00001b44  f4031faa  mov      x20, xzr
00001b48  a87e089b  mul      x8, x21, x8
00001b4c  0801629e  scvtf    d8, x8
00001b50  08018a52  mov      w8, #0x5008
00001b54  9503088b  add      x21, x28, x8
00001b58  8002629e  scvtf    d0, x20
00001b5c  00086a1e  fmul     d0, d0, d10
00001b60  00186b1e  fdiv     d0, d0, d11
00001b64  c7000094  bl       #0x1e80
00001b68  00244c1f  fmadd    d0, d0, d12, d9
00001b6c  e18640bc  ldr      s1, [x23], #8
00001b70  88f67ed3  lsl      x8, x20, #2
00001b74  94060091  add      x20, x20, #1
00001b78  21c0221e  fcvt     d1, s1
00001b7c  7f0214eb  cmp      x19, x20
00001b80  0028601e  fadd     d0, d0, d0
00001b84  0008611e  fmul     d0, d0, d1
00001b88  a16a68bc  ldr      s1, [x21, x8]
00001b8c  21c0221e  fcvt     d1, s1
00001b90  0018681e  fdiv     d0, d0, d8
00001b94  0028611e  fadd     d0, d0, d1
00001b98  0040621e  fcvt     s0, d0
00001b9c  a06a28bc  str      s0, [x21, x8]
00001ba0  c1fdff54  b.ne     #0x1b58
00001ba4  f40b40f9  ldr      x20, [sp, #0x10]
00001ba8  9f0600f1  cmp      x20, #1
00001bac  cb000054  b.lt     #0x1bc4
00001bb0  82f67ed3  lsl      x2, x20, #2
00001bb4  08018a52  mov      w8, #0x5008
00001bb8  e00340f9  ldr      x0, [sp]
00001bbc  8103088b  add      x1, x28, x8
00001bc0  ac000094  bl       #0x1e70
00001bc4  08018a52  mov      w8, #0x5008
00001bc8  e20319aa  mov      x2, x25
00001bcc  8003088b  add      x0, x28, x8
00001bd0  0108148b  add      x1, x0, x20, lsl #2
00001bd4  a7000094  bl       #0x1e70
00001bd8  ed0740f9  ldr      x13, [sp, #8]
00001bdc  e21f40bd  ldr      s2, [sp, #0x1c]
00001be0  bf0500f1  cmp      x13, #1
00001be4  8b030054  b.lt     #0x1c54
00001be8  bf2100f1  cmp      x13, #8
00001bec  23020054  b.lo     #0x1c30
00001bf0  e80b14cb  neg      x8, x20, lsl #2
00001bf4  1f8100f1  cmp      x8, #0x20
00001bf8  e8031faa  mov      x8, xzr
00001bfc  c3010054  b.lo     #0x1c34
00001c00  a8ed7d92  and      x8, x13, #0x7ffffffffffffff8
00001c04  89f67ed3  lsl      x9, x20, #2
00001c08  eb031caa  mov      x11, x28
00001c0c  ea0308aa  mov      x10, x8
00001c10  6c01098b  add      x12, x11, x9
00001c14  4a2100f1  subs     x10, x10, #8
00001c18  800540ad  ldp      q0, q1, [x12]
00001c1c  600581ac  stp      q0, q1, [x11], #0x20
00001c20  81ffff54  b.ne     #0x1c10
00001c24  bf0108eb  cmp      x13, x8
00001c28  61000054  b.ne     #0x1c34
00001c2c  0a000014  b        #0x1c54
00001c30  e8031faa  mov      x8, xzr
00001c34  0a01148b  add      x10, x8, x20
00001c38  880b088b  add      x8, x28, x8, lsl #2
00001c3c  890b0a8b  add      x9, x28, x10, lsl #2
00001c40  4a0113cb  sub      x10, x10, x19
00001c44  204540bc  ldr      s0, [x9], #4
00001c48  4a0500b1  adds     x10, x10, #1
00001c4c  004500bc  str      s0, [x8], #4
00001c50  a3ffff54  b.lo     #0x1c44
00001c54  00e4002f  movi     d0, #0000000000000000
00001c58  4028201e  fadd     s0, s2, s0
00001c5c  00c4189e  fcvtzs   x0, s0, #0xf
00001c60  f44f4ea9  ldp      x20, x19, [sp, #0xe0]
00001c64  f6574da9  ldp      x22, x21, [sp, #0xd0]
00001c68  f85f4ca9  ldp      x24, x23, [sp, #0xc0]
00001c6c  fa674ba9  ldp      x26, x25, [sp, #0xb0]
00001c70  fc6f4aa9  ldp      x28, x27, [sp, #0xa0]
00001c74  fd7b49a9  ldp      x29, x30, [sp, #0x90]
00001c78  e923486d  ldp      d9, d8, [sp, #0x80]
00001c7c  eb2b476d  ldp      d11, d10, [sp, #0x70]
00001c80  ed33466d  ldp      d13, d12, [sp, #0x60]
00001c84  ef3b456d  ldp      d15, d14, [sp, #0x50]
00001c88  ffc30391  add      sp, sp, #0xf0
00001c8c  c0035fd6  ret      
