# Function: Java_sofeh_audio_NEqualizer_processLeft; bytes=968
00000d4c  021e00b4  cbz      x2, #0x110c
00000d50  ff8301d1  sub      sp, sp, #0x60
00000d54  ef3b026d  stp      d15, d14, [sp, #0x20]
00000d58  ed33036d  stp      d13, d12, [sp, #0x30]
00000d5c  eb2b046d  stp      d11, d10, [sp, #0x40]
00000d60  e923056d  stp      d9, d8, [sp, #0x50]
00000d64  6700229e  scvtf    s7, x3
00000d68  08f0a552  mov      w8, #0x2f800000
00000d6c  5eec40bd  ldr      s30, [x2, #0xec]
00000d70  5008442d  ldp      s16, s2, [x2, #0x20]
00000d74  1901271e  fmov     s25, w8
00000d78  400c492d  ldp      s0, s3, [x2, #0x48]
00000d7c  49a440bd  ldr      s9, [x2, #0xa4]
00000d80  5d184e2d  ldp      s29, s6, [x2, #0x70]
00000d84  484440b9  ldr      w8, [x2, #0x44]
00000d88  4428391e  fadd     s4, s2, s25
00000d8c  e238221e  fsub     s2, s7, s2
00000d90  cb2b391e  fadd     s11, s30, s25
00000d94  6528391e  fadd     s5, s3, s25
00000d98  e338231e  fsub     s3, s7, s3
00000d9c  ec383e1e  fsub     s12, s7, s30
00000da0  5154452d  ldp      s17, s21, [x2, #0x28]
00000da4  f338261e  fsub     s19, s7, s6
00000da8  58fc532d  ldp      s24, s31, [x2, #0x9c]
00000dac  5e1441bd  ldr      s30, [x2, #0x114]
00000db0  4110101f  fmadd    s1, s2, s16, s4
00000db4  57704f2d  ldp      s23, s28, [x2, #0x78]
00000db8  6214001f  fmadd    s2, s3, s0, s5
00000dbc  520c4a2d  ldp      s18, s3, [x2, #0x50]
00000dc0  c528391e  fadd     s5, s6, s25
00000dc4  fa38381e  fsub     s26, s7, s24
00000dc8  c62b391e  fadd     s6, s30, s25
00000dcc  fe383e1e  fsub     s30, s7, s30
00000dd0  1f050071  cmp      w8, #1
00000dd4  486c40b9  ldr      w8, [x2, #0x6c]
00000dd8  3438311e  fsub     s20, s1, s17
00000ddc  49bc40b9  ldr      w9, [x2, #0xbc]
00000de0  5638321e  fsub     s22, s2, s18
00000de4  e18b022d  stp      s1, s2, [sp, #0x14]
00000de8  61161d1f  fmadd    s1, s19, s29, s5
00000dec  456c582d  ldp      s5, s27, [x2, #0xc0]
00000df0  9346101f  fmadd    s19, s20, s16, s17
00000df4  142b391e  fadd     s20, s24, s25
00000df8  519840bd  ldr      s17, [x2, #0x98]
00000dfc  c24a001f  fmadd    s2, s22, s0, s18
00000e00  783a351e  fsub     s24, s19, s21
00000e04  5453111f  fmadd    s20, s26, s17, s20
00000e08  e207012d  stp      s2, s1, [sp, #8]
00000e0c  2138371e  fsub     s1, s1, s23
00000e10  5638231e  fsub     s22, s2, s3
00000e14  1a57101f  fmadd    s26, s24, s16, s21
00000e18  f5383b1e  fsub     s21, s7, s27
00000e1c  8a3a3f1e  fsub     s10, s20, s31
00000e20  325c1d1f  fmadd    s18, s1, s29, s23
00000e24  d80e001f  fmadd    s24, s22, s0, s3
00000e28  772b391e  fadd     s23, s27, s25
00000e2c  4e044b2d  ldp      s14, s1, [x2, #0x58]
00000e30  56e840bd  ldr      s22, [x2, #0xe8]
00000e34  4d6c462d  ldp      s13, s27, [x2, #0x30]
00000e38  473400bd  str      s7, [x2, #0x34]
00000e3c  4f3a3c1e  fsub     s15, s18, s28
00000e40  083b2e1e  fsub     s8, s24, s14
00000e44  b55e051f  fmadd    s21, s21, s5, s23
00000e48  972d161f  fmadd    s23, s12, s22, s11
00000e4c  4b7d111f  fmadd    s11, s10, s17, s31
00000e50  423b2d1e  fsub     s2, s26, s13
00000e54  5f30592d  ldp      s31, s12, [x2, #0xc8]
00000e58  e11f00bd  str      s1, [sp, #0x1c]
00000e5c  ef711d1f  fmadd    s15, s15, s29, s28
00000e60  1c39001f  fmadd    s28, s8, s0, s14
00000e64  4e00502d  ldp      s14, s0, [x2, #0x80]
00000e68  a13a3f1e  fsub     s1, s21, s31
00000e6c  4a34101f  fmadd    s10, s2, s16, s13
00000e70  6839291e  fsub     s8, s11, s9
00000e74  501041bd  ldr      s16, [x2, #0x110]
00000e78  4d3c41bd  ldr      s13, [x2, #0x13c]
00000e7c  54ac132d  stp      s20, s11, [x2, #0x9c]
00000e80  4b6040bd  ldr      s11, [x2, #0x60]
00000e84  e01300bd  str      s0, [sp, #0x10]
00000e88  40105e2d  ldp      s0, s4, [x2, #0xf0]
00000e8c  3f7c051f  fmadd    s31, s1, s5, s31
00000e90  e11740bd  ldr      s1, [sp, #0x14]
00000e94  e3392e1e  fsub     s3, s15, s14
00000e98  c61b101f  fmadd    s6, s30, s16, s6
00000e9c  b929391e  fadd     s25, s13, s25
00000ea0  ed382d1e  fsub     s13, s7, s13
00000ea4  e23a201e  fsub     s2, s23, s0
00000ea8  41cc042d  stp      s1, s19, [x2, #0x24]
00000eac  0925111f  fmadd    s9, s8, s17, s9
00000eb0  411841bd  ldr      s1, [x2, #0x118]
00000eb4  5e3841bd  ldr      s30, [x2, #0x138]
00000eb8  5aa8052d  stp      s26, s10, [x2, #0x2c]
00000ebc  6e381d1f  fmadd    s14, s3, s29, s14
00000ec0  e31b40bd  ldr      s3, [sp, #0x18]
00000ec4  e83b2c1e  fsub     s8, s31, s12
00000ec8  b9651e1f  fmadd    s25, s13, s30, s25
00000ecc  5d3840bd  ldr      s29, [x2, #0x38]
00000ed0  5a1c41bd  ldr      s26, [x2, #0x11c]
00000ed4  5300161f  fmadd    s19, s2, s22, s0
00000ed8  e203412d  ldp      s2, s0, [sp, #8]
00000edc  4388092d  stp      s3, s2, [x2, #0x4c]
00000ee0  c338211e  fsub     s3, s6, s1
00000ee4  5b74072d  stp      s27, s29, [x2, #0x38]
00000ee8  b43b2a1e  fsub     s20, s29, s10
00000eec  0831051f  fmadd    s8, s8, s5, s12
00000ef0  58f00a2d  stp      s24, s28, [x2, #0x54]
00000ef4  40c80e2d  stp      s0, s18, [x2, #0x74]
00000ef8  4200552d  ldp      s2, s0, [x2, #0xa8]
00000efc  7d3a241e  fsub     s29, s19, s4
00000f00  7204101f  fmadd    s18, s3, s16, s1
00000f04  940e2a1e  fcsel    s20, s20, s10, eq
00000f08  434040bd  ldr      s3, [x2, #0x40]
00000f0c  4b6400bd  str      s11, [x2, #0x64]
00000f10  6a393c1e  fsub     s10, s11, s28
00000f14  2d39221e  fsub     s13, s9, s2
00000f18  55fc182d  stp      s21, s31, [x2, #0xc4]
00000f1c  1f050071  cmp      w8, #1
00000f20  830a231e  fmul     s3, s20, s3
00000f24  489440b9  ldr      w8, [x2, #0x94]
00000f28  57cc1d2d  stp      s23, s19, [x2, #0xec]
00000f2c  5b3a3a1e  fsub     s27, s18, s26
00000f30  461401bd  str      s6, [x2, #0x114]
00000f34  46e040bd  ldr      s6, [x2, #0xe0]
00000f38  5c0d3c1e  fcsel    s28, s10, s28, eq
00000f3c  4ab040bd  ldr      s10, [x2, #0xb0]
00000f40  1f050071  cmp      w8, #1
00000f44  ac09111f  fmadd    s12, s13, s17, s2
00000f48  42045a2d  ldp      s2, s1, [x2, #0xd0]
00000f4c  b113161f  fmadd    s17, s29, s22, s4
00000f50  5d4041bd  ldr      s29, [x2, #0x140]
00000f54  6800389e  fcvtzs   x8, s3
00000f58  7a6b101f  fmadd    s26, s27, s16, s26
00000f5c  5b8840bd  ldr      s27, [x2, #0x88]
00000f60  521801bd  str      s18, [x2, #0x118]
00000f64  0439221e  fsub     s4, s8, s2
00000f68  383b3d1e  fsub     s24, s25, s29
00000f6c  520041bd  ldr      s18, [x2, #0x100]
00000f70  5b8c00bd  str      s27, [x2, #0x8c]
00000f74  7b3b2e1e  fsub     s27, s27, s14
00000f78  5f6840bd  ldr      s31, [x2, #0x68]
00000f7c  520401bd  str      s18, [x2, #0x104]
00000f80  57b840bd  ldr      s23, [x2, #0xb8]
00000f84  9c0b3f1e  fmul     s28, s28, s31
00000f88  4fb80f2d  stp      s15, s14, [x2, #0x7c]
00000f8c  8408051f  fmadd    s4, s4, s5, s2
00000f90  54085f2d  ldp      s20, s2, [x2, #0xf8]
00000f94  05771e1f  fmadd    s5, s24, s30, s29
00000f98  582041bd  ldr      s24, [x2, #0x120]
00000f9c  5d4441bd  ldr      s29, [x2, #0x144]
00000fa0  630f2e1e  fcsel    s3, s27, s14, eq
00000fa4  5b392c1e  fsub     s27, s10, s12
00000fa8  3f050071  cmp      w9, #1
00000fac  2b3a341e  fsub     s11, s17, s20
00000fb0  49e440b9  ldr      w9, [x2, #0xe4]
00000fb4  4ab400bd  str      s10, [x2, #0xb4]
00000fb8  4890192d  stp      s8, s4, [x2, #0xcc]
00000fbc  ef3b426d  ldp      d15, d14, [sp, #0x20]
00000fc0  b5383d1e  fsub     s21, s5, s29
00000fc4  49b0142d  stp      s9, s12, [x2, #0xa4]
00000fc8  7b0f2c1e  fcsel    s27, s27, s12, eq
00000fcc  3f050071  cmp      w9, #1
00000fd0  490c41b9  ldr      w9, [x2, #0x10c]
00000fd4  7451161f  fmadd    s20, s11, s22, s20
00000fd8  563b381e  fsub     s22, s26, s24
00000fdc  4780152d  stp      s7, s0, [x2, #0xac]
00000fe0  404c41bd  ldr      s0, [x2, #0x14c]
00000fe4  e923456d  ldp      d9, d8, [sp, #0x50]
00000fe8  eb2b446d  ldp      d11, d10, [sp, #0x40]
00000fec  5a1c01bd  str      s26, [x2, #0x11c]
00000ff0  ed33436d  ldp      d13, d12, [sp, #0x30]
00000ff4  593c01bd  str      s25, [x2, #0x13c]
00000ff8  d662101f  fmadd    s22, s22, s16, s24
00000ffc  58d840bd  ldr      s24, [x2, #0xd8]
00001000  b0761e1f  fmadd    s16, s21, s30, s29
00001004  523a341e  fsub     s18, s18, s20
00001008  51d01e2d  stp      s17, s20, [x2, #0xf4]
0000100c  153b241e  fsub     s21, s24, s4
00001010  58dc00bd  str      s24, [x2, #0xdc]
00001014  584841bd  ldr      s24, [x2, #0x148]
00001018  47841a2d  stp      s7, s1, [x2, #0xd4]
0000101c  133a381e  fsub     s19, s16, s24
00001020  562001bd  str      s22, [x2, #0x120]
00001024  47881f2d  stp      s7, s2, [x2, #0xfc]
00001028  a40e241e  fcsel    s4, s21, s4, eq
0000102c  3f050071  cmp      w9, #1
00001030  493441b9  ldr      w9, [x2, #0x134]
00001034  510e341e  fcsel    s17, s18, s20, eq
00001038  559040bd  ldr      s21, [x2, #0x90]
0000103c  540841bd  ldr      s20, [x2, #0x108]
00001040  73621e1f  fmadd    s19, s19, s30, s24
00001044  585041bd  ldr      s24, [x2, #0x150]
00001048  3f050071  cmp      w9, #1
0000104c  8408261e  fmul     s4, s4, s6
00001050  462841bd  ldr      s6, [x2, #0x128]
00001054  495c41b9  ldr      w9, [x2, #0x15c]
00001058  6308351e  fmul     s3, s3, s21
0000105c  750b371e  fmul     s21, s27, s23
00001060  572441bd  ldr      s23, [x2, #0x124]
00001064  462c01bd  str      s6, [x2, #0x12c]
00001068  c638361e  fsub     s6, s6, s22
0000106c  310a341e  fmul     s17, s17, s20
00001070  123b331e  fsub     s18, s24, s19
00001074  545841bd  ldr      s20, [x2, #0x158]
00001078  572801bd  str      s23, [x2, #0x128]
0000107c  8c00389e  fcvtzs   x12, s4
00001080  f71f40bd  ldr      s23, [sp, #0x1c]
00001084  472401bd  str      s7, [x2, #0x124]
00001088  6a00389e  fcvtzs   x10, s3
0000108c  ab02389e  fcvtzs   x11, s21
00001090  454001bd  str      s5, [x2, #0x140]
00001094  c60c361e  fcsel    s6, s6, s22, eq
00001098  563041bd  ldr      s22, [x2, #0x130]
0000109c  3f050071  cmp      w9, #1
000010a0  520e331e  fcsel    s18, s18, s19, eq
000010a4  8903389e  fcvtzs   x9, s28
000010a8  2d02389e  fcvtzs   x13, s17
000010ac  47dc0b2d  stp      s7, s23, [x2, #0x5c]
000010b0  c608361e  fmul     s6, s6, s22
000010b4  f61340bd  ldr      s22, [sp, #0x10]
000010b8  504401bd  str      s16, [x2, #0x144]
000010bc  430a341e  fmul     s3, s18, s20
000010c0  534801bd  str      s19, [x2, #0x148]
000010c4  2801088b  add      x8, x9, x8
000010c8  49010b8b  add      x9, x10, x11
000010cc  8a010d8b  add      x10, x12, x13
000010d0  0801098b  add      x8, x8, x9
000010d4  47d8102d  stp      s7, s22, [x2, #0x84]
000010d8  ce00389e  fcvtzs   x14, s6
000010dc  585401bd  str      s24, [x2, #0x154]
000010e0  6f00389e  fcvtzs   x15, s3
000010e4  405001bd  str      s0, [x2, #0x150]
000010e8  474c01bd  str      s7, [x2, #0x14c]
000010ec  49010e8b  add      x9, x10, x14
000010f0  0801098b  add      x8, x8, x9
000010f4  08010fab  adds     x8, x8, x15
000010f8  090d0091  add      x9, x8, #3
000010fc  28b1889a  csel     x8, x9, x8, lt
00001100  00fd4293  asr      x0, x8, #2
00001104  ff830191  add      sp, sp, #0x60
00001108  c0035fd6  ret      
0000110c  e0031faa  mov      x0, xzr
00001110  c0035fd6  ret      
