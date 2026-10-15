# APK member: lib/x86/libnequalizer.so
# Library SHA-256: 0c7dfba2ccd6a4c88a74500c5b3ecac4f88ff3e4abb681dafc117af7ee94b44b
# Function: Java_sofeh_audio_NEqualizer_processLeft; address=0xe10; bytes=2067
# Decoder mode: default; decoded_bytes=2067
00000e10  55                push       ebp
00000e11  89e5              mov        ebp, esp
00000e13  56                push       esi
00000e14  83e4f0            and        esp, 0xfffffff0
00000e17  81ecd0010000      sub        esp, 0x1d0
00000e1d  e800000000        call       0xe22
00000e22  58                pop        eax
00000e23  81c0d2290000      add        eax, 0x29d2
00000e29  8b4d10            mov        ecx, dword ptr [ebp + 0x10]
00000e2c  8b5514            mov        edx, dword ptr [ebp + 0x14]
00000e2f  09ca              or         edx, ecx
00000e31  0f84e2070000      je         0x1619
00000e37  f30f104518        movss      xmm0, dword ptr [ebp + 0x18]
00000e3c  f30f104d1c        movss      xmm1, dword ptr [ebp + 0x1c]
00000e41  0f14c1            unpcklps   xmm0, xmm1
00000e44  0f13842428010000  movlps     qword ptr [esp + 0x128], xmm0
00000e4c  dfac2428010000    fild       qword ptr [esp + 0x128]
00000e53  d95c244c          fstp       dword ptr [esp + 0x4c]
00000e57  f30f107c244c      movss      xmm7, dword ptr [esp + 0x4c]
00000e5d  f20f104134        movsd      xmm0, qword ptr [ecx + 0x34]
00000e62  0f298424b0010000  movaps     xmmword ptr [esp + 0x1b0], xmm0
00000e6a  f20f114138        movsd      qword ptr [ecx + 0x38], xmm0
00000e6f  f30f117934        movss      dword ptr [ecx + 0x34], xmm7
00000e74  f20f10415c        movsd      xmm0, qword ptr [ecx + 0x5c]
00000e79  0f298424a0010000  movaps     xmmword ptr [esp + 0x1a0], xmm0
00000e81  f20f114160        movsd      qword ptr [ecx + 0x60], xmm0
00000e86  f30f11795c        movss      dword ptr [ecx + 0x5c], xmm7
00000e8b  f30f104920        movss      xmm1, dword ptr [ecx + 0x20]
00000e90  f30f107124        movss      xmm6, dword ptr [ecx + 0x24]
00000e95  f30f10414c        movss      xmm0, dword ptr [ecx + 0x4c]
00000e9a  0f14f0            unpcklps   xmm6, xmm0
00000e9d  f30f104148        movss      xmm0, dword ptr [ecx + 0x48]
00000ea2  0f14c8            unpcklps   xmm1, xmm0
00000ea5  0f294c2450        movaps     xmmword ptr [esp + 0x50], xmm1
00000eaa  f30f104928        movss      xmm1, dword ptr [ecx + 0x28]
00000eaf  f30f104150        movss      xmm0, dword ptr [ecx + 0x50]
00000eb4  0f14c8            unpcklps   xmm1, xmm0
00000eb7  0f294c2460        movaps     xmmword ptr [esp + 0x60], xmm1
00000ebc  f30f10492c        movss      xmm1, dword ptr [ecx + 0x2c]
00000ec1  f30f104154        movss      xmm0, dword ptr [ecx + 0x54]
00000ec6  0f14c8            unpcklps   xmm1, xmm0
00000ec9  0f298c24f0000000  movaps     xmmword ptr [esp + 0xf0], xmm1
00000ed1  f30f104930        movss      xmm1, dword ptr [ecx + 0x30]
00000ed6  f30f104158        movss      xmm0, dword ptr [ecx + 0x58]
00000edb  0f14c8            unpcklps   xmm1, xmm0
00000ede  0f298c2400010000  movaps     xmmword ptr [esp + 0x100], xmm1
00000ee6  f30f104940        movss      xmm1, dword ptr [ecx + 0x40]
00000eeb  f30f104168        movss      xmm0, dword ptr [ecx + 0x68]
00000ef0  0f14c8            unpcklps   xmm1, xmm0
00000ef3  0f298c2410010000  movaps     xmmword ptr [esp + 0x110], xmm1
00000efb  f30f104944        movss      xmm1, dword ptr [ecx + 0x44]
00000f00  f30f10416c        movss      xmm0, dword ptr [ecx + 0x6c]
00000f05  0f14c8            unpcklps   xmm1, xmm0
00000f08  0f298c24b0000000  movaps     xmmword ptr [esp + 0xb0], xmm1
00000f10  f20f10a984000000  movsd      xmm5, qword ptr [ecx + 0x84]
00000f18  0f29ac2490010000  movaps     xmmword ptr [esp + 0x190], xmm5
00000f20  f30f106174        movss      xmm4, dword ptr [ecx + 0x74]
00000f25  f30f105970        movss      xmm3, dword ptr [ecx + 0x70]
00000f2a  f30f105178        movss      xmm2, dword ptr [ecx + 0x78]
00000f2f  f30f10497c        movss      xmm1, dword ptr [ecx + 0x7c]
00000f34  f30f108180000000  movss      xmm0, dword ptr [ecx + 0x80]
00000f3c  f20f11a988000000  movsd      qword ptr [ecx + 0x88], xmm5
00000f44  f30f11b984000000  movss      dword ptr [ecx + 0x84], xmm7
00000f4c  f30f10a99c000000  movss      xmm5, dword ptr [ecx + 0x9c]
00000f54  0f14e5            unpcklps   xmm4, xmm5
00000f57  0f16f4            movlhps    xmm6, xmm4
00000f5a  f30f10a198000000  movss      xmm4, dword ptr [ecx + 0x98]
00000f62  0f14dc            unpcklps   xmm3, xmm4
00000f65  0f28642450        movaps     xmm4, xmmword ptr [esp + 0x50]
00000f6a  0f16e3            movlhps    xmm4, xmm3
00000f6d  0f29642450        movaps     xmmword ptr [esp + 0x50], xmm4
00000f72  f30f1099a0000000  movss      xmm3, dword ptr [ecx + 0xa0]
00000f7a  0f14d3            unpcklps   xmm2, xmm3
00000f7d  0f285c2460        movaps     xmm3, xmmword ptr [esp + 0x60]
00000f82  0f16da            movlhps    xmm3, xmm2
00000f85  0f295c2460        movaps     xmmword ptr [esp + 0x60], xmm3
00000f8a  f30f1091a4000000  movss      xmm2, dword ptr [ecx + 0xa4]
00000f92  0f14ca            unpcklps   xmm1, xmm2
00000f95  0f289424f0000000  movaps     xmm2, xmmword ptr [esp + 0xf0]
00000f9d  0f16d1            movlhps    xmm2, xmm1
00000fa0  0f299424f0000000  movaps     xmmword ptr [esp + 0xf0], xmm2
00000fa8  f30f1089a8000000  movss      xmm1, dword ptr [ecx + 0xa8]
00000fb0  0f14c1            unpcklps   xmm0, xmm1
00000fb3  0f288c2400010000  movaps     xmm1, xmmword ptr [esp + 0x100]
00000fbb  0f16c8            movlhps    xmm1, xmm0
00000fbe  0f298c2400010000  movaps     xmmword ptr [esp + 0x100], xmm1
00000fc6  f20f1091ac000000  movsd      xmm2, qword ptr [ecx + 0xac]
00000fce  0f29942480010000  movaps     xmmword ptr [esp + 0x180], xmm2
00000fd6  f30f108990000000  movss      xmm1, dword ptr [ecx + 0x90]
00000fde  f30f108194000000  movss      xmm0, dword ptr [ecx + 0x94]
00000fe6  f20f1191b0000000  movsd      qword ptr [ecx + 0xb0], xmm2
00000fee  0f28df            movaps     xmm3, xmm7
00000ff1  0f297c2470        movaps     xmmword ptr [esp + 0x70], xmm7
00000ff6  f30f11b9ac000000  movss      dword ptr [ecx + 0xac], xmm7
00000ffe  f30f1091b8000000  movss      xmm2, dword ptr [ecx + 0xb8]
00001006  0f14ca            unpcklps   xmm1, xmm2
00001009  0f28942410010000  movaps     xmm2, xmmword ptr [esp + 0x110]
00001011  0f16d1            movlhps    xmm2, xmm1
00001014  0f29942410010000  movaps     xmmword ptr [esp + 0x110], xmm2
0000101c  f30f1089bc000000  movss      xmm1, dword ptr [ecx + 0xbc]
00001024  0f14c1            unpcklps   xmm0, xmm1
00001027  0f288c24b0000000  movaps     xmm1, xmmword ptr [esp + 0xb0]
0000102f  0f16c8            movlhps    xmm1, xmm0
00001032  0f298c24b0000000  movaps     xmmword ptr [esp + 0xb0], xmm1
0000103a  f20f1081d4000000  movsd      xmm0, qword ptr [ecx + 0xd4]
00001042  0f29842470010000  movaps     xmmword ptr [esp + 0x170], xmm0
0000104a  f30f10b9c4000000  movss      xmm7, dword ptr [ecx + 0xc4]
00001052  f30f1091c0000000  movss      xmm2, dword ptr [ecx + 0xc0]
0000105a  f30f1089c8000000  movss      xmm1, dword ptr [ecx + 0xc8]
00001062  f30f10a9cc000000  movss      xmm5, dword ptr [ecx + 0xcc]
0000106a  f30f10a1d0000000  movss      xmm4, dword ptr [ecx + 0xd0]
00001072  f20f1181d8000000  movsd      qword ptr [ecx + 0xd8], xmm0
0000107a  f30f1199d4000000  movss      dword ptr [ecx + 0xd4], xmm3
00001082  f30f1081ec000000  movss      xmm0, dword ptr [ecx + 0xec]
0000108a  0f14f8            unpcklps   xmm7, xmm0
0000108d  f30f1081e8000000  movss      xmm0, dword ptr [ecx + 0xe8]
00001095  0f14d0            unpcklps   xmm2, xmm0
00001098  0f29942480000000  movaps     xmmword ptr [esp + 0x80], xmm2
000010a0  f30f1081f0000000  movss      xmm0, dword ptr [ecx + 0xf0]
000010a8  0f14c8            unpcklps   xmm1, xmm0
000010ab  0f298c2490000000  movaps     xmmword ptr [esp + 0x90], xmm1
000010b3  f30f1081f4000000  movss      xmm0, dword ptr [ecx + 0xf4]
000010bb  0f14e8            unpcklps   xmm5, xmm0
000010be  0f29ac24c0000000  movaps     xmmword ptr [esp + 0xc0], xmm5
000010c6  f30f1081f8000000  movss      xmm0, dword ptr [ecx + 0xf8]
000010ce  0f14e0            unpcklps   xmm4, xmm0
000010d1  0f29a424d0000000  movaps     xmmword ptr [esp + 0xd0], xmm4
000010d9  f20f1081fc000000  movsd      xmm0, qword ptr [ecx + 0xfc]
000010e1  0f29842460010000  movaps     xmmword ptr [esp + 0x160], xmm0
000010e9  f30f1099e0000000  movss      xmm3, dword ptr [ecx + 0xe0]
000010f1  660f6e91e4000000  movd       xmm2, dword ptr [ecx + 0xe4]
000010f9  f20f118100010000  movsd      qword ptr [ecx + 0x100], xmm0
00001101  0f284c2470        movaps     xmm1, xmmword ptr [esp + 0x70]
00001106  f30f1189fc000000  movss      dword ptr [ecx + 0xfc], xmm1
0000110e  f30f108108010000  movss      xmm0, dword ptr [ecx + 0x108]
00001116  0f14d8            unpcklps   xmm3, xmm0
00001119  0f299c24e0000000  movaps     xmmword ptr [esp + 0xe0], xmm3
00001121  660f6e810c010000  movd       xmm0, dword ptr [ecx + 0x10c]
00001129  660f62d0          punpckldq  xmm2, xmm0
0000112d  f20f108124010000  movsd      xmm0, qword ptr [ecx + 0x124]
00001135  0f29842450010000  movaps     xmmword ptr [esp + 0x150], xmm0
0000113d  f30f10a914010000  movss      xmm5, dword ptr [ecx + 0x114]
00001145  f30f109910010000  movss      xmm3, dword ptr [ecx + 0x110]
0000114d  f30f10a118010000  movss      xmm4, dword ptr [ecx + 0x118]
00001155  0f29a42430010000  movaps     xmmword ptr [esp + 0x130], xmm4
0000115d  f30f10a11c010000  movss      xmm4, dword ptr [ecx + 0x11c]
00001165  0f29a42440010000  movaps     xmmword ptr [esp + 0x140], xmm4
0000116d  f30f10a120010000  movss      xmm4, dword ptr [ecx + 0x120]
00001175  0f29a424a0000000  movaps     xmmword ptr [esp + 0xa0], xmm4
0000117d  f20f118128010000  movsd      qword ptr [ecx + 0x128], xmm0
00001185  f30f118924010000  movss      dword ptr [ecx + 0x124], xmm1
0000118d  f30f10a13c010000  movss      xmm4, dword ptr [ecx + 0x13c]
00001195  0f14ec            unpcklps   xmm5, xmm4
00001198  0f16fd            movlhps    xmm7, xmm5
0000119b  f30f10a138010000  movss      xmm4, dword ptr [ecx + 0x138]
000011a3  0f14dc            unpcklps   xmm3, xmm4
000011a6  0f28842480000000  movaps     xmm0, xmmword ptr [esp + 0x80]
000011ae  0f16c3            movlhps    xmm0, xmm3
000011b1  0f29842480000000  movaps     xmmword ptr [esp + 0x80], xmm0
000011b9  0f28d9            movaps     xmm3, xmm1
000011bc  0fc6d900          shufps     xmm3, xmm1, 0
000011c0  0f28e3            movaps     xmm4, xmm3
000011c3  0f5cde            subps      xmm3, xmm6
000011c6  0f595c2450        mulps      xmm3, xmmword ptr [esp + 0x50]
000011cb  0f28a88ccdffff    movaps     xmm5, xmmword ptr [eax - 0x3274]
000011d2  0f58f5            addps      xmm6, xmm5
000011d5  0f58f3            addps      xmm6, xmm3
000011d8  0f5ce7            subps      xmm4, xmm7
000011db  0f58fd            addps      xmm7, xmm5
000011de  0f59e0            mulps      xmm4, xmm0
000011e1  0f58fc            addps      xmm7, xmm4
000011e4  0f28cf            movaps     xmm1, xmm7
000011e7  f30f109940010000  movss      xmm3, dword ptr [ecx + 0x140]
000011ef  0f28842430010000  movaps     xmm0, xmmword ptr [esp + 0x130]
000011f7  0f14c3            unpcklps   xmm0, xmm3
000011fa  0f28bc2490000000  movaps     xmm7, xmmword ptr [esp + 0x90]
00001202  0f16f8            movlhps    xmm7, xmm0
00001205  f30f108130010000  movss      xmm0, dword ptr [ecx + 0x130]
0000120d  0f29842490000000  movaps     xmmword ptr [esp + 0x90], xmm0
00001215  660f6ea934010000  movd       xmm5, dword ptr [ecx + 0x134]
0000121d  f30f117124        movss      dword ptr [ecx + 0x24], xmm6
00001222  f30f16e6          movshdup   xmm4, xmm6
00001226  f30f11614c        movss      dword ptr [ecx + 0x4c], xmm4
0000122b  0f28e6            movaps     xmm4, xmm6
0000122e  660f15e6          unpckhpd   xmm4, xmm6
00001232  f30f116174        movss      dword ptr [ecx + 0x74], xmm4
00001237  0f28e6            movaps     xmm4, xmm6
0000123a  0fc6e6ff          shufps     xmm4, xmm6, 0xff
0000123e  f30f11a19c000000  movss      dword ptr [ecx + 0x9c], xmm4
00001246  f30f1189c4000000  movss      dword ptr [ecx + 0xc4], xmm1
0000124e  f30f16e1          movshdup   xmm4, xmm1
00001252  f30f11a1ec000000  movss      dword ptr [ecx + 0xec], xmm4
0000125a  0f28e1            movaps     xmm4, xmm1
0000125d  660f15e1          unpckhpd   xmm4, xmm1
00001261  f30f11a114010000  movss      dword ptr [ecx + 0x114], xmm4
00001269  0f28e1            movaps     xmm4, xmm1
0000126c  0fc6e1ff          shufps     xmm4, xmm1, 0xff
00001270  f30f11a13c010000  movss      dword ptr [ecx + 0x13c], xmm4
00001278  f30f10a144010000  movss      xmm4, dword ptr [ecx + 0x144]
00001280  0f28842440010000  movaps     xmm0, xmmword ptr [esp + 0x140]
00001288  0f14c4            unpcklps   xmm0, xmm4
0000128b  0f289c24c0000000  movaps     xmm3, xmmword ptr [esp + 0xc0]
00001293  0f16d8            movlhps    xmm3, xmm0
00001296  0f299c24c0000000  movaps     xmmword ptr [esp + 0xc0], xmm3
0000129e  f30f10a148010000  movss      xmm4, dword ptr [ecx + 0x148]
000012a6  0f288424a0000000  movaps     xmm0, xmmword ptr [esp + 0xa0]
000012ae  0f14c4            unpcklps   xmm0, xmm4
000012b1  0f289c24d0000000  movaps     xmm3, xmmword ptr [esp + 0xd0]
000012b9  0f16d8            movlhps    xmm3, xmm0
000012bc  0f299c24d0000000  movaps     xmmword ptr [esp + 0xd0], xmm3
000012c4  f20f10814c010000  movsd      xmm0, qword ptr [ecx + 0x14c]
000012cc  0f298424a0000000  movaps     xmmword ptr [esp + 0xa0], xmm0
000012d4  f20f118150010000  movsd      qword ptr [ecx + 0x150], xmm0
000012dc  0f28442470        movaps     xmm0, xmmword ptr [esp + 0x70]
000012e1  f30f11814c010000  movss      dword ptr [ecx + 0x14c], xmm0
000012e9  0f28df            movaps     xmm3, xmm7
000012ec  0f5ccf            subps      xmm1, xmm7
000012ef  0f28bc2480000000  movaps     xmm7, xmmword ptr [esp + 0x80]
000012f7  0f59cf            mulps      xmm1, xmm7
000012fa  0f58cb            addps      xmm1, xmm3
000012fd  0f294c2470        movaps     xmmword ptr [esp + 0x70], xmm1
00001302  0f28642460        movaps     xmm4, xmmword ptr [esp + 0x60]
00001307  0f5cf4            subps      xmm6, xmm4
0000130a  0f285c2450        movaps     xmm3, xmmword ptr [esp + 0x50]
0000130f  0f59f3            mulps      xmm6, xmm3
00001312  0f58f4            addps      xmm6, xmm4
00001315  660f6ea15c010000  movd       xmm4, dword ptr [ecx + 0x15c]
0000131d  660f62ec          punpckldq  xmm5, xmm4
00001321  660f6cd5          punpcklqdq xmm2, xmm5
00001325  f30f108158010000  movss      xmm0, dword ptr [ecx + 0x158]
0000132d  0f28a42490000000  movaps     xmm4, xmmword ptr [esp + 0x90]
00001335  0f14e0            unpcklps   xmm4, xmm0
00001338  0f288424e0000000  movaps     xmm0, xmmword ptr [esp + 0xe0]
00001340  0f16c4            movlhps    xmm0, xmm4
00001343  0f298424e0000000  movaps     xmmword ptr [esp + 0xe0], xmm0
0000134b  0f28e9            movaps     xmm5, xmm1
0000134e  0f288424c0000000  movaps     xmm0, xmmword ptr [esp + 0xc0]
00001356  0f5ce8            subps      xmm5, xmm0
00001359  0f59ef            mulps      xmm5, xmm7
0000135c  0f58e8            addps      xmm5, xmm0
0000135f  0f28fe            movaps     xmm7, xmm6
00001362  0f288424f0000000  movaps     xmm0, xmmword ptr [esp + 0xf0]
0000136a  0f5cf0            subps      xmm6, xmm0
0000136d  0f59f3            mulps      xmm6, xmm3
00001370  0f58f0            addps      xmm6, xmm0
00001373  0f28c6            movaps     xmm0, xmm6
00001376  0f288c2400010000  movaps     xmm1, xmmword ptr [esp + 0x100]
0000137e  0f5cc1            subps      xmm0, xmm1
00001381  0f59c3            mulps      xmm0, xmm3
00001384  0f28e5            movaps     xmm4, xmm5
00001387  0f289c24d0000000  movaps     xmm3, xmmword ptr [esp + 0xd0]
0000138f  0f5ce3            subps      xmm4, xmm3
00001392  0f59a42480000000  mulps      xmm4, xmmword ptr [esp + 0x80]
0000139a  0f58e3            addps      xmm4, xmm3
0000139d  0f29642450        movaps     xmmword ptr [esp + 0x50], xmm4
000013a2  0f58c1            addps      xmm0, xmm1
000013a5  0f297c2460        movaps     xmmword ptr [esp + 0x60], xmm7
000013aa  f30f117928        movss      dword ptr [ecx + 0x28], xmm7
000013af  f30f11712c        movss      dword ptr [ecx + 0x2c], xmm6
000013b4  f30f114130        movss      dword ptr [ecx + 0x30], xmm0
000013b9  0f28de            movaps     xmm3, xmm6
000013bc  0fc6df11          shufps     xmm3, xmm7, 0x11
000013c0  0fc6dfe2          shufps     xmm3, xmm7, 0xe2
000013c4  0f135950          movlps     qword ptr [ecx + 0x50], xmm3
000013c8  f30f16d8          movshdup   xmm3, xmm0
000013cc  f30f115958        movss      dword ptr [ecx + 0x58], xmm3
000013d1  660f6f987ccdffff  movdqa     xmm3, xmmword ptr [eax - 0x3284]
000013d9  660f76d3          pcmpeqd    xmm2, xmm3
000013dd  660f6fbc24b0000000  movdqa     xmm7, xmmword ptr [esp + 0xb0]
000013e6  660f76fb          pcmpeqd    xmm7, xmm3
000013ea  0f289c24a0010000  movaps     xmm3, xmmword ptr [esp + 0x1a0]
000013f2  0fc69c24b001000055  shufps     xmm3, xmmword ptr [esp + 0x1b0], 0x55
000013fb  0f288c2480010000  movaps     xmm1, xmmword ptr [esp + 0x180]
00001403  0fc68c249001000055  shufps     xmm1, xmmword ptr [esp + 0x190], 0x55
0000140c  0fc6d922          shufps     xmm3, xmm1, 0x22
00001410  0f288c2460010000  movaps     xmm1, xmmword ptr [esp + 0x160]
00001418  0fc68c247001000055  shufps     xmm1, xmmword ptr [esp + 0x170], 0x55
00001421  0f28a424a0000000  movaps     xmm4, xmmword ptr [esp + 0xa0]
00001429  0fc6a4245001000055  shufps     xmm4, xmmword ptr [esp + 0x150], 0x55
00001432  0fc6cc22          shufps     xmm1, xmm4, 0x22
00001436  0f5cd8            subps      xmm3, xmm0
00001439  0f54df            andps      xmm3, xmm7
0000143c  0f55f8            andnps     xmm7, xmm0
0000143f  0f56fb            orps       xmm7, xmm3
00001442  0f59bc2410010000  mulps      xmm7, xmmword ptr [esp + 0x110]
0000144a  0f28642450        movaps     xmm4, xmmword ptr [esp + 0x50]
0000144f  0f5ccc            subps      xmm1, xmm4
00001452  0f54ca            andps      xmm1, xmm2
00001455  0f55d4            andnps     xmm2, xmm4
00001458  0f56d1            orps       xmm2, xmm1
0000145b  0f599424e0000000  mulps      xmm2, xmmword ptr [esp + 0xe0]
00001463  f30f117c2428      movss      dword ptr [esp + 0x28], xmm7
00001469  f30f16df          movshdup   xmm3, xmm7
0000146d  f30f115c2430      movss      dword ptr [esp + 0x30], xmm3
00001473  f30f11542408      movss      dword ptr [esp + 8], xmm2
00001479  f30f16da          movshdup   xmm3, xmm2
0000147d  f30f115c2410      movss      dword ptr [esp + 0x10], xmm3
00001483  0f28df            movaps     xmm3, xmm7
00001486  0fc6dfff          shufps     xmm3, xmm7, 0xff
0000148a  f30f115c2440      movss      dword ptr [esp + 0x40], xmm3
00001490  0f12ff            movhlps    xmm7, xmm7
00001493  f30f117c2438      movss      dword ptr [esp + 0x38], xmm7
00001499  0f28da            movaps     xmm3, xmm2
0000149c  0fc6daff          shufps     xmm3, xmm2, 0xff
000014a0  f30f115c2420      movss      dword ptr [esp + 0x20], xmm3
000014a6  0f12d2            movhlps    xmm2, xmm2
000014a9  f30f11542418      movss      dword ptr [esp + 0x18], xmm2
000014af  d9442428          fld        dword ptr [esp + 0x28]
000014b3  dd4c2428          fisttp     qword ptr [esp + 0x28]
000014b7  d9442430          fld        dword ptr [esp + 0x30]
000014bb  dd4c2430          fisttp     qword ptr [esp + 0x30]
000014bf  d9442408          fld        dword ptr [esp + 8]
000014c3  dd4c2408          fisttp     qword ptr [esp + 8]
000014c7  d9442410          fld        dword ptr [esp + 0x10]
000014cb  dd4c2410          fisttp     qword ptr [esp + 0x10]
000014cf  d9442440          fld        dword ptr [esp + 0x40]
000014d3  dd4c2440          fisttp     qword ptr [esp + 0x40]
000014d7  d9442438          fld        dword ptr [esp + 0x38]
000014db  dd4c2438          fisttp     qword ptr [esp + 0x38]
000014df  d9442420          fld        dword ptr [esp + 0x20]
000014e3  dd4c2420          fisttp     qword ptr [esp + 0x20]
000014e7  d9442418          fld        dword ptr [esp + 0x18]
000014eb  dd4c2418          fisttp     qword ptr [esp + 0x18]
000014ef  f30f7e4c2428      movq       xmm1, qword ptr [esp + 0x28]
000014f5  f30f7e5c2430      movq       xmm3, qword ptr [esp + 0x30]
000014fb  660f6ccb          punpcklqdq xmm1, xmm3
000014ff  f30f7e7c2408      movq       xmm7, qword ptr [esp + 8]
00001505  f30f7e5c2410      movq       xmm3, qword ptr [esp + 0x10]
0000150b  660f6cfb          punpcklqdq xmm7, xmm3
0000150f  660fd4f9          paddq      xmm7, xmm1
00001513  f30f7e4c2440      movq       xmm1, qword ptr [esp + 0x40]
00001519  f30f7e5c2438      movq       xmm3, qword ptr [esp + 0x38]
0000151f  660f6cd9          punpcklqdq xmm3, xmm1
00001523  f30f7e542420      movq       xmm2, qword ptr [esp + 0x20]
00001529  f30f7e4c2418      movq       xmm1, qword ptr [esp + 0x18]
0000152f  660f6cca          punpcklqdq xmm1, xmm2
00001533  660fd4cb          paddq      xmm1, xmm3
00001537  660fd4cf          paddq      xmm1, xmm7
0000153b  0f28542460        movaps     xmm2, xmmword ptr [esp + 0x60]
00001540  0f28da            movaps     xmm3, xmm2
00001543  0f15de            unpckhps   xmm3, xmm6
00001546  0f135978          movlps     qword ptr [ecx + 0x78], xmm3
0000154a  0f28d8            movaps     xmm3, xmm0
0000154d  660f15d8          unpckhpd   xmm3, xmm0
00001551  f30f119980000000  movss      dword ptr [ecx + 0x80], xmm3
00001559  0fc6f2ff          shufps     xmm6, xmm2, 0xff
0000155d  0fc6f2e2          shufps     xmm6, xmm2, 0xe2
00001561  0f13b1a0000000    movlps     qword ptr [ecx + 0xa0], xmm6
00001568  0fc6c0ff          shufps     xmm0, xmm0, 0xff
0000156c  f30f1181a8000000  movss      dword ptr [ecx + 0xa8], xmm0
00001574  0f28542470        movaps     xmm2, xmmword ptr [esp + 0x70]
00001579  f30f1191c8000000  movss      dword ptr [ecx + 0xc8], xmm2
00001581  f30f11a9cc000000  movss      dword ptr [ecx + 0xcc], xmm5
00001589  0f28dc            movaps     xmm3, xmm4
0000158c  f30f11a1d0000000  movss      dword ptr [ecx + 0xd0], xmm4
00001594  0f28c5            movaps     xmm0, xmm5
00001597  0fc6c211          shufps     xmm0, xmm2, 0x11
0000159b  0fc6c2e2          shufps     xmm0, xmm2, 0xe2
0000159f  0f1381f0000000    movlps     qword ptr [ecx + 0xf0], xmm0
000015a6  f30f16c4          movshdup   xmm0, xmm4
000015aa  f30f1181f8000000  movss      dword ptr [ecx + 0xf8], xmm0
000015b2  0f28c2            movaps     xmm0, xmm2
000015b5  0f15c5            unpckhps   xmm0, xmm5
000015b8  0f138118010000    movlps     qword ptr [ecx + 0x118], xmm0
000015bf  0f28c4            movaps     xmm0, xmm4
000015c2  660f15c4          unpckhpd   xmm0, xmm4
000015c6  f30f118120010000  movss      dword ptr [ecx + 0x120], xmm0
000015ce  0fc6eaff          shufps     xmm5, xmm2, 0xff
000015d2  0fc6eae2          shufps     xmm5, xmm2, 0xe2
000015d6  0f13a940010000    movlps     qword ptr [ecx + 0x140], xmm5
000015dd  660f70c1ee        pshufd     xmm0, xmm1, 0xee
000015e2  660fd4c1          paddq      xmm0, xmm1
000015e6  660f7ec6          movd       esi, xmm0
000015ea  660f70c055        pshufd     xmm0, xmm0, 0x55
000015ef  660f7ec2          movd       edx, xmm0
000015f3  89d0              mov        eax, edx
000015f5  c1f81f            sar        eax, 0x1f
000015f8  c1e81e            shr        eax, 0x1e
000015fb  01f0              add        eax, esi
000015fd  0fc6dcff          shufps     xmm3, xmm4, 0xff
00001601  83d200            adc        edx, 0
00001604  f30f119948010000  movss      dword ptr [ecx + 0x148], xmm3
0000160c  0facd002          shrd       eax, edx, 2
00001610  c1fa02            sar        edx, 2
00001613  8d65fc            lea        esp, [ebp - 4]
00001616  5e                pop        esi
00001617  5d                pop        ebp
00001618  c3                ret        
00001619  31c0              xor        eax, eax
0000161b  31d2              xor        edx, edx
0000161d  8d65fc            lea        esp, [ebp - 4]
00001620  5e                pop        esi
00001621  5d                pop        ebp
00001622  c3                ret        
