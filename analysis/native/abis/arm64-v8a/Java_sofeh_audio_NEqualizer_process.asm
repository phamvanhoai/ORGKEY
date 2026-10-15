# APK member: lib/arm64-v8a/libnequalizer.so
# Library SHA-256: 8ea1d1bb9a47bdb7f785ccbca268dd7b2758ab07e14e89c818f91f4d0a1581cb
# Function: Java_sofeh_audio_NEqualizer_process; address=0x1538; bytes=1068
# Decoder mode: default; decoded_bytes=1068
00001538  222100b4          cbz        x2, #0x195c
0000153c  ff0302d1          sub        sp, sp, #0x80
00001540  ef3b046d          stp        d15, d14, [sp, #0x40]
00001544  ed33056d          stp        d13, d12, [sp, #0x50]
00001548  eb2b066d          stp        d11, d10, [sp, #0x60]
0000154c  e923076d          stp        d9, d8, [sp, #0x70]
00001550  683c0013          sxth       w8, w3
00001554  400c492d          ldp        s0, s3, [x2, #0x48]
00001558  412440bd          ldr        s1, [x2, #0x24]
0000155c  57484e2d          ldp        s23, s18, [x2, #0x70]
00001560  1401221e          scvtf      s20, w8
00001564  08f0a552          mov        w8, #0x2f800000
00001568  499440b9          ldr        w9, [x2, #0x94]
0000156c  1101271e          fmov       s17, w8
00001570  5a60582d          ldp        s26, s24, [x2, #0xc0]
00001574  597c5d2d          ldp        s25, s31, [x2, #0xe8]
00001578  484440b9          ldr        w8, [x2, #0x44]
0000157c  4a24542d          ldp        s10, s9, [x2, #0xa0]
00001580  2528311e          fadd       s5, s1, s17
00001584  6628311e          fadd       s6, s3, s17
00001588  813a211e          fsub       s1, s20, s1
0000158c  873a231e          fsub       s7, s20, s3
00001590  432040bd          ldr        s3, [x2, #0x20]
00001594  532a311e          fadd       s19, s18, s17
00001598  9b3a321e          fsub       s27, s20, s18
0000159c  082b311e          fadd       s8, s24, s17
000015a0  983a381e          fsub       s24, s20, s24
000015a4  7040201e          fmov       s16, s3
000015a8  4e34592d          ldp        s14, s13, [x2, #0xc8]
000015ac  2214031f          fmadd      s2, s1, s3, s5
000015b0  4130452d          ldp        s1, s12, [x2, #0x28]
000015b4  e318001f          fmadd      s3, s7, s0, s6
000015b8  0640201e          fmov       s6, s0
000015bc  1f050071          cmp        w8, #1
000015c0  40144a2d          ldp        s0, s5, [x2, #0x50]
000015c4  486c40b9          ldr        w8, [x2, #0x6c]
000015c8  4758532d          ldp        s7, s22, [x2, #0x98]
000015cc  5538211e          fsub       s21, s2, s1
000015d0  e38b062d          stp        s3, s2, [sp, #0x34]
000015d4  6238201e          fsub       s2, s3, s0
000015d8  0342201e          fmov       s3, s16
000015dc  dc2a311e          fadd       s28, s22, s17
000015e0  9e3a361e          fsub       s30, s20, s22
000015e4  bd06101f          fmadd      s29, s21, s16, s1
000015e8  953a3f1e          fsub       s21, s20, s31
000015ec  704f171f          fmadd      s16, s27, s23, s19
000015f0  5200061f          fmadd      s18, s2, s6, s0
000015f4  e02b311e          fadd       s0, s31, s17
000015f8  02231a1f          fmadd      s2, s24, s26, s8
000015fc  c173071f          fmadd      s1, s30, s7, s28
00001600  5b7c4f2d          ldp        s27, s31, [x2, #0x78]
00001604  581441bd          ldr        s24, [x2, #0x114]
00001608  d340201e          fmov       s19, s6
0000160c  bc3b2c1e          fsub       s28, s29, s12
00001610  b602191f          fmadd      s22, s21, s25, s0
00001614  153a3b1e          fsub       s21, s16, s27
00001618  5e3a251e          fsub       s30, s18, s5
0000161c  e11b00bd          str        s1, [sp, #0x18]
00001620  2b382a1e          fsub       s11, s1, s10
00001624  41382e1e          fsub       s1, s2, s14
00001628  f2c3012d          stp        s18, s16, [sp, #0xc]
0000162c  102b311e          fadd       s16, s24, s17
00001630  983a381e          fsub       s24, s20, s24
00001634  8833031f          fmadd      s8, s28, s3, s12
00001638  403c5e2d          ldp        s0, s15, [x2, #0xf0]
0000163c  ac6e171f          fmadd      s12, s21, s23, s27
00001640  de17061f          fmadd      s30, s30, s6, s5
00001644  5c1041bd          ldr        s28, [x2, #0x110]
00001648  3b381a1f          fmadd      s27, s1, s26, s14
0000164c  4404462d          ldp        s4, s1, [x2, #0x30]
00001650  6b29071f          fmadd      s11, s11, s7, s10
00001654  c53a201e          fsub       s5, s22, s0
00001658  e22300bd          str        s2, [sp, #0x20]
0000165c  02431c1f          fmadd      s2, s24, s28, s16
00001660  f687052d          stp        s22, s1, [sp, #0x2c]
00001664  f640201e          fmov       s22, s7
00001668  4a044b2d          ldp        s10, s1, [x2, #0x58]
0000166c  471841bd          ldr        s7, [x2, #0x118]
00001670  7240201e          fmov       s18, s3
00001674  0639241e          fsub       s6, s8, s4
00001678  b000191f          fmadd      s16, s5, s25, s0
0000167c  453c41bd          ldr        s5, [x2, #0x13c]
00001680  6e39291e          fsub       s14, s11, s9
00001684  e13f00bd          str        s1, [sp, #0x3c]
00001688  c13b2a1e          fsub       s1, s30, s10
0000168c  83393f1e          fsub       s3, s12, s31
00001690  e21f00bd          str        s2, [sp, #0x1c]
00001694  4238271e          fsub       s2, s2, s7
00001698  b128311e          fadd       s17, s5, s17
0000169c  853a251e          fsub       s5, s20, s5
000016a0  e03740bd          ldr        s0, [sp, #0x34]
000016a4  d210121f          fmadd      s18, s6, s18, s4
000016a8  ce25161f          fmadd      s14, s14, s22, s9
000016ac  f01700bd          str        s16, [sp, #0x14]
000016b0  3328131f          fmadd      s19, s1, s19, s10
000016b4  613b2d1e          fsub       s1, s27, s13
000016b8  4a3841bd          ldr        s10, [x2, #0x138]
000016bc  757c171f          fmadd      s21, s3, s23, s31
000016c0  5f1c1c1f          fmadd      s31, s2, s28, s7
000016c4  404c00bd          str        s0, [x2, #0x4c]
000016c8  4718552d          ldp        s7, s6, [x2, #0xa8]
000016cc  003a2f1e          fsub       s0, s16, s15
000016d0  b8440a1f          fmadd      s24, s5, s10, s17
000016d4  e33b40bd          ldr        s3, [sp, #0x38]
000016d8  424041bd          ldr        s2, [x2, #0x140]
000016dc  29341a1f          fmadd      s9, s1, s26, s13
000016e0  4d14502d          ldp        s13, s5, [x2, #0x80]
000016e4  41105a2d          ldp        s1, s4, [x2, #0xd0]
000016e8  d139271e          fsub       s17, s14, s7
000016ec  43f4042d          stp        s3, s29, [x2, #0x24]
000016f0  1d3c191f          fmadd      s29, s0, s25, s15
000016f4  403840bd          ldr        s0, [x2, #0x38]
000016f8  af3a2d1e          fsub       s15, s21, s13
000016fc  103b221e          fsub       s16, s24, s2
00001700  5ecc0a2d          stp        s30, s19, [x2, #0x54]
00001704  e497042d          stp        s4, s5, [sp, #0x24]
00001708  43105f2d          ldp        s3, s4, [x2, #0xf8]
0000170c  311e161f          fmadd      s17, s17, s22, s7
00001710  e70f40bd          ldr        s7, [sp, #0xc]
00001714  403c00bd          str        s0, [x2, #0x3c]
00001718  2539211e          fsub       s5, s9, s1
0000171c  48c8052d          stp        s8, s18, [x2, #0x2c]
00001720  e693062d          stp        s6, s4, [sp, #0x34]
00001724  441c41bd          ldr        s4, [x2, #0x11c]
00001728  a63b231e          fsub       s6, s29, s3
0000172c  ed35171f          fmadd      s13, s15, s23, s13
00001730  475000bd          str        s7, [x2, #0x50]
00001734  0738321e          fsub       s7, s0, s18
00001738  ef3b241e          fsub       s15, s31, s4
0000173c  e01340bd          ldr        s0, [sp, #0x10]
00001740  4ec4142d          stp        s14, s17, [x2, #0xa4]
00001744  ba041a1f          fmadd      s26, s5, s26, s1
00001748  e11b40bd          ldr        s1, [sp, #0x18]
0000174c  5f1801bd          str        s31, [x2, #0x118]
00001750  40b00e2d          stp        s0, s12, [x2, #0x74]
00001754  c00c191f          fmadd      s0, s6, s25, s3
00001758  190a0a1f          fmadd      s25, s16, s10, s2
0000175c  e20c321e          fcsel      s2, s7, s18, eq
00001760  434040bd          ldr        s3, [x2, #0x40]
00001764  41ac132d          stp        s1, s11, [x2, #0x9c]
00001768  f6111c1f          fmadd      s22, s15, s28, s4
0000176c  442041bd          ldr        s4, [x2, #0x120]
00001770  416040bd          ldr        s1, [x2, #0x60]
00001774  464441bd          ldr        s6, [x2, #0x144]
00001778  1f050071          cmp        w8, #1
0000177c  522441bd          ldr        s18, [x2, #0x124]
00001780  4208231e          fmul       s2, s2, s3
00001784  438840bd          ldr        s3, [x2, #0x88]
00001788  416400bd          str        s1, [x2, #0x64]
0000178c  2138331e          fsub       s1, s1, s19
00001790  40f800bd          str        s0, [x2, #0xf8]
00001794  c53a241e          fsub       s5, s22, s4
00001798  438c00bd          str        s3, [x2, #0x8c]
0000179c  63382d1e          fsub       s3, s3, s13
000017a0  55b40f2d          stp        s21, s13, [x2, #0x7c]
000017a4  f53340bd          ldr        s21, [sp, #0x30]
000017a8  4800389e          fcvtzs     x8, s2
000017ac  223b261e          fsub       s2, s25, s6
000017b0  49e8192d          stp        s9, s26, [x2, #0xcc]
000017b4  210c331e          fcsel      s1, s1, s19, eq
000017b8  3f050071          cmp        w9, #1
000017bc  49bc40b9          ldr        w9, [x2, #0xbc]
000017c0  b7101c1f          fmadd      s23, s5, s28, s4
000017c4  44b040bd          ldr        s4, [x2, #0xb0]
000017c8  630c2d1e          fcsel      s3, s3, s13, eq
000017cc  456840bd          ldr        s5, [x2, #0x68]
000017d0  3f050071          cmp        w9, #1
000017d4  49e440b9          ldr        w9, [x2, #0xe4]
000017d8  9038311e          fsub       s16, s4, s17
000017dc  47180a1f          fmadd      s7, s2, s10, s6
000017e0  e62340bd          ldr        s6, [sp, #0x20]
000017e4  44b400bd          str        s4, [x2, #0xb4]
000017e8  44d840bd          ldr        s4, [x2, #0xd8]
000017ec  429040bd          ldr        s2, [x2, #0x90]
000017f0  46ec182d          stp        s6, s27, [x2, #0xc4]
000017f4  464841bd          ldr        s6, [x2, #0x148]
000017f8  2108251e          fmul       s1, s1, s5
000017fc  44dc00bd          str        s4, [x2, #0xdc]
00001800  84383a1e          fsub       s4, s4, s26
00001804  45b840bd          ldr        s5, [x2, #0xb8]
00001808  100e311e          fcsel      s16, s16, s17, eq
0000180c  f138261e          fsub       s17, s7, s6
00001810  6208221e          fmul       s2, s3, s2
00001814  e32f40bd          ldr        s3, [sp, #0x2c]
00001818  3f050071          cmp        w9, #1
0000181c  490c41b9          ldr        w9, [x2, #0x10c]
00001820  e923476d          ldp        d9, d8, [sp, #0x70]
00001824  561c01bd          str        s22, [x2, #0x11c]
00001828  43ec00bd          str        s3, [x2, #0xec]
0000182c  030a251e          fmul       s3, s16, s5
00001830  e51740bd          ldr        s5, [sp, #0x14]
00001834  840c3a1e          fcsel      s4, s4, s26, eq
00001838  331a0a1f          fmadd      s19, s17, s10, s6
0000183c  46e040bd          ldr        s6, [x2, #0xe0]
00001840  45741e2d          stp        s5, s29, [x2, #0xf0]
00001844  450041bd          ldr        s5, [x2, #0x100]
00001848  502841bd          ldr        s16, [x2, #0x128]
0000184c  515041bd          ldr        s17, [x2, #0x150]
00001850  3f050071          cmp        w9, #1
00001854  493441b9          ldr        w9, [x2, #0x134]
00001858  450401bd          str        s5, [x2, #0x104]
0000185c  a538201e          fsub       s5, s5, s0
00001860  8408261e          fmul       s4, s4, s6
00001864  e61f40bd          ldr        s6, [sp, #0x1c]
00001868  502c01bd          str        s16, [x2, #0x12c]
0000186c  4a00389e          fcvtzs     x10, s2
00001870  522801bd          str        s18, [x2, #0x128]
00001874  523041bd          ldr        s18, [x2, #0x130]
00001878  6b00389e          fcvtzs     x11, s3
0000187c  461401bd          str        s6, [x2, #0x114]
00001880  063a371e          fsub       s6, s16, s23
00001884  500841bd          ldr        s16, [x2, #0x108]
00001888  a00c201e          fcsel      s0, s5, s0, eq
0000188c  253a331e          fsub       s5, s17, s19
00001890  3f050071          cmp        w9, #1
00001894  495c41b9          ldr        w9, [x2, #0x15c]
00001898  8c00389e          fcvtzs     x12, s4
0000189c  572001bd          str        s23, [x2, #0x120]
000018a0  eb2b466d          ldp        d11, d10, [sp, #0x60]
000018a4  54d4062d          stp        s20, s21, [x2, #0x34]
000018a8  c60c371e          fcsel      s6, s6, s23, eq
000018ac  3f050071          cmp        w9, #1
000018b0  0008301e          fmul       s0, s0, s16
000018b4  a50c331e          fcsel      s5, s5, s19, eq
000018b8  505841bd          ldr        s16, [x2, #0x158]
000018bc  2900389e          fcvtzs     x9, s1
000018c0  e13f40bd          ldr        s1, [sp, #0x3c]
000018c4  ed33456d          ldp        d13, d12, [sp, #0x50]
000018c8  c608321e          fmul       s6, s6, s18
000018cc  ef3b446d          ldp        d15, d14, [sp, #0x40]
000018d0  54840b2d          stp        s20, s1, [x2, #0x5c]
000018d4  a108301e          fmul       s1, s5, s16
000018d8  0d00389e          fcvtzs     x13, s0
000018dc  e02b40bd          ldr        s0, [sp, #0x28]
000018e0  2801080b          add        w8, w9, w8
000018e4  49010b0b          add        w9, w10, w11
000018e8  0801090b          add        w8, w8, w9
000018ec  583c01bd          str        s24, [x2, #0x13c]
000018f0  ce00389e          fcvtzs     x14, s6
000018f4  5480102d          stp        s20, s0, [x2, #0x84]
000018f8  e03740bd          ldr        s0, [sp, #0x34]
000018fc  2f00389e          fcvtzs     x15, s1
00001900  8a010d0b          add        w10, w12, w13
00001904  542401bd          str        s20, [x2, #0x124]
00001908  5480152d          stp        s20, s0, [x2, #0xac]
0000190c  e02740bd          ldr        s0, [sp, #0x24]
00001910  594001bd          str        s25, [x2, #0x140]
00001914  49010e0b          add        w9, w10, w14
00001918  54801a2d          stp        s20, s0, [x2, #0xd4]
0000191c  e03b40bd          ldr        s0, [sp, #0x38]
00001920  0801090b          add        w8, w8, w9
00001924  474401bd          str        s7, [x2, #0x144]
00001928  08010f0b          add        w8, w8, w15
0000192c  54801f2d          stp        s20, s0, [x2, #0xfc]
00001930  404c41bd          ldr        s0, [x2, #0x14c]
00001934  093d0013          sxth       w9, w8
00001938  534801bd          str        s19, [x2, #0x148]
0000193c  515401bd          str        s17, [x2, #0x154]
00001940  29791d53          ubfx       w9, w9, #0x1d, #2
00001944  405001bd          str        s0, [x2, #0x150]
00001948  544c01bd          str        s20, [x2, #0x14c]
0000194c  0801090b          add        w8, w8, w9
00001950  003d0213          sbfx       w0, w8, #2, #0xe
00001954  ff030291          add        sp, sp, #0x80
00001958  c0035fd6          ret        
0000195c  e0031f2a          mov        w0, wzr
00001960  c0035fd6          ret        
