# Function: Java_sofeh_audio_NEqualizer_processRight; bytes=1060
00001114  e22000b4  cbz      x2, #0x1530
00001118  ef3bbc6d  stp      d15, d14, [sp, #-0x40]!
0000111c  ed33016d  stp      d13, d12, [sp, #0x10]
00001120  eb2b026d  stp      d11, d10, [sp, #0x20]
00001124  e923036d  stp      d9, d8, [sp, #0x30]
00001128  6000229e  scvtf    s0, x3
0000112c  08f0a552  mov      w8, #0x2f800000
00001130  446441bd  ldr      s4, [x2, #0x164]
00001134  0501271e  fmov     s5, w8
00001138  468c41bd  ldr      s6, [x2, #0x18c]
0000113c  416041bd  ldr      s1, [x2, #0x160]
00001140  54b441bd  ldr      s20, [x2, #0x1b4]
00001144  438841bd  ldr      s3, [x2, #0x188]
00001148  566841bd  ldr      s22, [x2, #0x168]
0000114c  5cdc41bd  ldr      s28, [x2, #0x1dc]
00001150  589041bd  ldr      s24, [x2, #0x190]
00001154  577441bd  ldr      s23, [x2, #0x174]
00001158  8728251e  fadd     s7, s4, s5
0000115c  1138241e  fsub     s17, s0, s4
00001160  d228251e  fadd     s18, s6, s5
00001164  1338261e  fsub     s19, s0, s6
00001168  9e2a251e  fadd     s30, s20, s5
0000116c  1438341e  fsub     s20, s0, s20
00001170  46b041bd  ldr      s6, [x2, #0x1b0]
00001174  8a2b251e  fadd     s10, s28, s5
00001178  0b383c1e  fsub     s11, s0, s28
0000117c  5d9c41bd  ldr      s29, [x2, #0x19c]
00001180  5bb841bd  ldr      s27, [x2, #0x1b8]
00001184  4d0442bd  ldr      s13, [x2, #0x204]
00001188  3a1e011f  fmadd    s26, s17, s1, s7
0000118c  427841bd  ldr      s2, [x2, #0x178]
00001190  44a041bd  ldr      s4, [x2, #0x1a0]
00001194  794a031f  fmadd    s25, s19, s3, s18
00001198  9e7a061f  fmadd    s30, s20, s6, s30
0000119c  53d841bd  ldr      s19, [x2, #0x1d8]
000011a0  577801bd  str      s23, [x2, #0x178]
000011a4  b729251e  fadd     s23, s13, s5
000011a8  49c441bd  ldr      s9, [x2, #0x1c4]
000011ac  6a29131f  fmadd    s10, s11, s19, s10
000011b0  0b382d1e  fsub     s11, s0, s13
000011b4  5da001bd  str      s29, [x2, #0x1a0]
000011b8  4c3b361e  fsub     s12, s26, s22
000011bc  506c41bd  ldr      s16, [x2, #0x16c]
000011c0  520042bd  ldr      s18, [x2, #0x200]
000011c4  2e3b381e  fsub     s14, s25, s24
000011c8  dd3b3b1e  fsub     s29, s30, s27
000011cc  47c841bd  ldr      s7, [x2, #0x1c8]
000011d0  5ce041bd  ldr      s28, [x2, #0x1e0]
000011d4  48ec41bd  ldr      s8, [x2, #0x1ec]
000011d8  49c801bd  str      s9, [x2, #0x1c8]
000011dc  559441bd  ldr      s21, [x2, #0x194]
000011e0  51f041bd  ldr      s17, [x2, #0x1f0]
000011e4  5f0842bd  ldr      s31, [x2, #0x208]
000011e8  8c59011f  fmadd    s12, s12, s1, s22
000011ec  562c42bd  ldr      s22, [x2, #0x22c]
000011f0  48f001bd  str      s8, [x2, #0x1f0]
000011f4  c961031f  fmadd    s9, s14, s3, s24
000011f8  785d121f  fmadd    s24, s11, s18, s23
000011fc  48393c1e  fsub     s8, s10, s28
00001200  bb6f061f  fmadd    s27, s29, s6, s27
00001204  ce2a251e  fadd     s14, s22, s5
00001208  0f38361e  fsub     s15, s0, s22
0000120c  5a6401bd  str      s26, [x2, #0x164]
00001210  542842bd  ldr      s20, [x2, #0x228]
00001214  4d5442bd  ldr      s13, [x2, #0x254]
00001218  9d39301e  fsub     s29, s12, s16
0000121c  598c01bd  str      s25, [x2, #0x18c]
00001220  57bc41bd  ldr      s23, [x2, #0x1bc]
00001224  3a39351e  fsub     s26, s9, s21
00001228  193b3f1e  fsub     s25, s24, s31
0000122c  0871131f  fmadd    s8, s8, s19, s28
00001230  5eb401bd  str      s30, [x2, #0x1b4]
00001234  5e7041bd  ldr      s30, [x2, #0x170]
00001238  ab29251e  fadd     s11, s13, s5
0000123c  0d382d1e  fsub     s13, s0, s13
00001240  fc39141f  fmadd    s28, s15, s20, s14
00001244  4ee441bd  ldr      s14, [x2, #0x1e4]
00001248  bd43011f  fmadd    s29, s29, s1, s16
0000124c  6f3b371e  fsub     s15, s27, s23
00001250  565042bd  ldr      s22, [x2, #0x250]
00001254  5a57031f  fmadd    s26, s26, s3, s21
00001258  357f121f  fmadd    s21, s25, s18, s31
0000125c  4adc01bd  str      s10, [x2, #0x1dc]
00001260  0a392e1e  fsub     s10, s8, s14
00001264  599841bd  ldr      s25, [x2, #0x198]
00001268  499001bd  str      s9, [x2, #0x190]
0000126c  b02d161f  fmadd    s16, s13, s22, s11
00001270  497c42bd  ldr      s9, [x2, #0x27c]
00001274  5bb801bd  str      s27, [x2, #0x1b8]
00001278  bf3b3e1e  fsub     s31, s29, s30
0000127c  f75d061f  fmadd    s23, s15, s6, s23
00001280  4c6801bd  str      s12, [x2, #0x168]
00001284  5b3b391e  fsub     s27, s26, s25
00001288  48e001bd  str      s8, [x2, #0x1e0]
0000128c  2b29251e  fadd     s11, s9, s5
00001290  4839131f  fmadd    s8, s10, s19, s14
00001294  4ac041bd  ldr      s10, [x2, #0x1c0]
00001298  0938291e  fsub     s9, s0, s9
0000129c  5d6c01bd  str      s29, [x2, #0x16c]
000012a0  5d3042bd  ldr      s29, [x2, #0x230]
000012a4  4c5842bd  ldr      s12, [x2, #0x258]
000012a8  fe7b011f  fmadd    s30, s31, s1, s30
000012ac  ff3a2a1e  fsub     s31, s23, s10
000012b0  457842bd  ldr      s5, [x2, #0x278]
000012b4  7b67031f  fmadd    s27, s27, s3, s25
000012b8  4de841bd  ldr      s13, [x2, #0x1e8]
000012bc  833b3d1e  fsub     s3, s28, s29
000012c0  57bc01bd  str      s23, [x2, #0x1bc]
000012c4  173a2c1e  fsub     s23, s16, s12
000012c8  212d051f  fmadd    s1, s9, s5, s11
000012cc  427c01bd  str      s2, [x2, #0x17c]
000012d0  488441b9  ldr      w8, [x2, #0x184]
000012d4  49d441b9  ldr      w9, [x2, #0x1d4]
000012d8  42383e1e  fsub     s2, s2, s30
000012dc  5a9401bd  str      s26, [x2, #0x194]
000012e0  1a392d1e  fsub     s26, s8, s13
000012e4  e62b061f  fmadd    s6, s31, s6, s10
000012e8  5f0c42bd  ldr      s31, [x2, #0x20c]
000012ec  7974141f  fmadd    s25, s3, s20, s29
000012f0  e332161f  fmadd    s3, s23, s22, s12
000012f4  1f050071  cmp      w8, #1
000012f8  578042bd  ldr      s23, [x2, #0x280]
000012fc  bd3a3f1e  fsub     s29, s21, s31
00001300  5e7001bd  str      s30, [x2, #0x170]
00001304  48ac41b9  ldr      w8, [x2, #0x1ac]
00001308  420c3e1e  fcsel    s2, s2, s30, eq
0000130c  5337131f  fmadd    s19, s26, s19, s13
00001310  5a8041bd  ldr      s26, [x2, #0x180]
00001314  3e38371e  fsub     s30, s1, s23
00001318  44a401bd  str      s4, [x2, #0x1a4]
0000131c  84383b1e  fsub     s4, s4, s27
00001320  48e401bd  str      s8, [x2, #0x1e4]
00001324  483442bd  ldr      s8, [x2, #0x234]
00001328  1f050071  cmp      w8, #1
0000132c  5a083a1e  fmul     s26, s2, s26
00001330  bd7f121f  fmadd    s29, s29, s18, s31
00001334  5fa841bd  ldr      s31, [x2, #0x1a8]
00001338  47cc01bd  str      s7, [x2, #0x1cc]
0000133c  e738261e  fsub     s7, s7, s6
00001340  c25f051f  fmadd    s2, s30, s5, s23
00001344  373b281e  fsub     s23, s25, s8
00001348  840c3b1e  fcsel    s4, s4, s27, eq
0000134c  5b9801bd  str      s27, [x2, #0x198]
00001350  5b1042bd  ldr      s27, [x2, #0x210]
00001354  3f050071  cmp      w9, #1
00001358  4803389e  fcvtzs   x8, s26
0000135c  5a5c42bd  ldr      s26, [x2, #0x25c]
00001360  580402bd  str      s24, [x2, #0x204]
00001364  84083f1e  fmul     s4, s4, s31
00001368  be3b3b1e  fsub     s30, s29, s27
0000136c  588442bd  ldr      s24, [x2, #0x284]
00001370  f722141f  fmadd    s23, s23, s20, s8
00001374  7f383a1e  fsub     s31, s3, s26
00001378  46c001bd  str      s6, [x2, #0x1c0]
0000137c  51f401bd  str      s17, [x2, #0x1f4]
00001380  e70c261e  fcsel    s7, s7, s6, eq
00001384  4638381e  fsub     s6, s2, s24
00001388  550802bd  str      s21, [x2, #0x208]
0000138c  313a331e  fsub     s17, s17, s19
00001390  553842bd  ldr      s21, [x2, #0x238]
00001394  49fc41b9  ldr      w9, [x2, #0x1fc]
00001398  d26f121f  fmadd    s18, s30, s18, s27
0000139c  53e801bd  str      s19, [x2, #0x1e8]
000013a0  fa6b161f  fmadd    s26, s31, s22, s26
000013a4  fe3a351e  fsub     s30, s23, s21
000013a8  5d0c02bd  str      s29, [x2, #0x20c]
000013ac  3f050071  cmp      w9, #1
000013b0  5d1842bd  ldr      s29, [x2, #0x218]
000013b4  c660051f  fmadd    s6, s6, s5, s24
000013b8  581442bd  ldr      s24, [x2, #0x214]
000013bc  310e331e  fcsel    s17, s17, s19, eq
000013c0  536042bd  ldr      s19, [x2, #0x260]
000013c4  5d1c02bd  str      s29, [x2, #0x21c]
000013c8  492442b9  ldr      w9, [x2, #0x224]
000013cc  5bd041bd  ldr      s27, [x2, #0x1d0]
000013d0  581802bd  str      s24, [x2, #0x218]
000013d4  b83b321e  fsub     s24, s29, s18
000013d8  5d8842bd  ldr      s29, [x2, #0x288]
000013dc  d457141f  fmadd    s20, s30, s20, s21
000013e0  553b331e  fsub     s21, s26, s19
000013e4  5c2c02bd  str      s28, [x2, #0x22c]
000013e8  dc383d1e  fsub     s28, s6, s29
000013ec  3f050071  cmp      w9, #1
000013f0  521002bd  str      s18, [x2, #0x210]
000013f4  494c42b9  ldr      w9, [x2, #0x24c]
000013f8  505402bd  str      s16, [x2, #0x254]
000013fc  5ef841bd  ldr      s30, [x2, #0x1f8]
00001400  120f321e  fcsel    s18, s24, s18, eq
00001404  586842bd  ldr      s24, [x2, #0x268]
00001408  435802bd  str      s3, [x2, #0x258]
0000140c  b34e161f  fmadd    s19, s21, s22, s19
00001410  554042bd  ldr      s21, [x2, #0x240]
00001414  562042bd  ldr      s22, [x2, #0x220]
00001418  8577051f  fmadd    s5, s28, s5, s29
0000141c  3f050071  cmp      w9, #1
00001420  497442b9  ldr      w9, [x2, #0x274]
00001424  554402bd  str      s21, [x2, #0x244]
00001428  b53a341e  fsub     s21, s21, s20
0000142c  520a361e  fmul     s18, s18, s22
00001430  569042bd  ldr      s22, [x2, #0x290]
00001434  573402bd  str      s23, [x2, #0x234]
00001438  573c42bd  ldr      s23, [x2, #0x23c]
0000143c  103b331e  fsub     s16, s24, s19
00001440  543802bd  str      s20, [x2, #0x238]
00001444  e7083b1e  fmul     s7, s7, s27
00001448  c33a251e  fsub     s3, s22, s5
0000144c  574002bd  str      s23, [x2, #0x240]
00001450  577042bd  ldr      s23, [x2, #0x270]
00001454  b40e341e  fcsel    s20, s21, s20, eq
00001458  3f050071  cmp      w9, #1
0000145c  499c42b9  ldr      w9, [x2, #0x29c]
00001460  554842bd  ldr      s21, [x2, #0x248]
00001464  536002bd  str      s19, [x2, #0x260]
00001468  310a3e1e  fmul     s17, s17, s30
0000146c  100e331e  fcsel    s16, s16, s19, eq
00001470  536442bd  ldr      s19, [x2, #0x264]
00001474  3f050071  cmp      w9, #1
00001478  940a351e  fmul     s20, s20, s21
0000147c  630c251e  fcsel    s3, s3, s5, eq
00001480  417c02bd  str      s1, [x2, #0x27c]
00001484  536802bd  str      s19, [x2, #0x268]
00001488  539842bd  ldr      s19, [x2, #0x298]
0000148c  8900389e  fcvtzs   x9, s4
00001490  100a371e  fmul     s16, s16, s23
00001494  ea00389e  fcvtzs   x10, s7
00001498  2b02389e  fcvtzs   x11, s17
0000149c  6108331e  fmul     s1, s3, s19
000014a0  4c02389e  fcvtzs   x12, s18
000014a4  593002bd  str      s25, [x2, #0x230]
000014a8  8d02389e  fcvtzs   x13, s20
000014ac  e923436d  ldp      d9, d8, [sp, #0x30]
000014b0  2801088b  add      x8, x9, x8
000014b4  eb2b426d  ldp      d11, d10, [sp, #0x20]
000014b8  0e02389e  fcvtzs   x14, s16
000014bc  49010b8b  add      x9, x10, x11
000014c0  5a5c02bd  str      s26, [x2, #0x25c]
000014c4  2f00389e  fcvtzs   x15, s1
000014c8  0801098b  add      x8, x8, x9
000014cc  418c42bd  ldr      s1, [x2, #0x28c]
000014d0  8a010d8b  add      x10, x12, x13
000014d4  ed33416d  ldp      d13, d12, [sp, #0x10]
000014d8  586c02bd  str      s24, [x2, #0x26c]
000014dc  49010e8b  add      x9, x10, x14
000014e0  407401bd  str      s0, [x2, #0x174]
000014e4  0801098b  add      x8, x8, x9
000014e8  409c01bd  str      s0, [x2, #0x19c]
000014ec  08010fab  adds     x8, x8, x15
000014f0  40c401bd  str      s0, [x2, #0x1c4]
000014f4  090d0091  add      x9, x8, #3
000014f8  40ec01bd  str      s0, [x2, #0x1ec]
000014fc  28b1889a  csel     x8, x9, x8, lt
00001500  401402bd  str      s0, [x2, #0x214]
00001504  403c02bd  str      s0, [x2, #0x23c]
00001508  00fd4293  asr      x0, x8, #2
0000150c  406402bd  str      s0, [x2, #0x264]
00001510  428002bd  str      s2, [x2, #0x280]
00001514  468402bd  str      s6, [x2, #0x284]
00001518  458802bd  str      s5, [x2, #0x288]
0000151c  569402bd  str      s22, [x2, #0x294]
00001520  419002bd  str      s1, [x2, #0x290]
00001524  408c02bd  str      s0, [x2, #0x28c]
00001528  ef3bc46c  ldp      d15, d14, [sp], #0x40
0000152c  c0035fd6  ret      
00001530  e0031faa  mov      x0, xzr
00001534  c0035fd6  ret      
