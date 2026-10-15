# APK member: lib/x86/libnequalizer.so
# Library SHA-256: 0c7dfba2ccd6a4c88a74500c5b3ecac4f88ff3e4abb681dafc117af7ee94b44b
# Function: Java_sofeh_audio_NEqualizer_process; address=0x1eb0; bytes=2000
# Decoder mode: default; decoded_bytes=2000
00001eb0  55                push       ebp
00001eb1  89e5              mov        ebp, esp
00001eb3  83e4f0            and        esp, 0xfffffff0
00001eb6  81ecc0010000      sub        esp, 0x1c0
00001ebc  e800000000        call       0x1ec1
00001ec1  59                pop        ecx
00001ec2  81c133190000      add        ecx, 0x1933
00001ec8  8b4510            mov        eax, dword ptr [ebp + 0x10]
00001ecb  8b5514            mov        edx, dword ptr [ebp + 0x14]
00001ece  09c2              or         edx, eax
00001ed0  0f84a4070000      je         0x267a
00001ed6  0fbf5518          movsx      edx, word ptr [ebp + 0x18]
00001eda  f30f2af2          cvtsi2ss   xmm6, edx
00001ede  f20f104034        movsd      xmm0, qword ptr [eax + 0x34]
00001ee3  0f298424a0010000  movaps     xmmword ptr [esp + 0x1a0], xmm0
00001eeb  f20f114038        movsd      qword ptr [eax + 0x38], xmm0
00001ef0  f30f117034        movss      dword ptr [eax + 0x34], xmm6
00001ef5  f20f10405c        movsd      xmm0, qword ptr [eax + 0x5c]
00001efa  0f29842490010000  movaps     xmmword ptr [esp + 0x190], xmm0
00001f02  f20f114060        movsd      qword ptr [eax + 0x60], xmm0
00001f07  f30f11705c        movss      dword ptr [eax + 0x5c], xmm6
00001f0c  f30f104820        movss      xmm1, dword ptr [eax + 0x20]
00001f11  f30f107824        movss      xmm7, dword ptr [eax + 0x24]
00001f16  f30f10404c        movss      xmm0, dword ptr [eax + 0x4c]
00001f1b  0f14f8            unpcklps   xmm7, xmm0
00001f1e  f30f104048        movss      xmm0, dword ptr [eax + 0x48]
00001f23  0f14c8            unpcklps   xmm1, xmm0
00001f26  0f294c2450        movaps     xmmword ptr [esp + 0x50], xmm1
00001f2b  f30f104828        movss      xmm1, dword ptr [eax + 0x28]
00001f30  f30f104050        movss      xmm0, dword ptr [eax + 0x50]
00001f35  0f14c8            unpcklps   xmm1, xmm0
00001f38  0f294c2440        movaps     xmmword ptr [esp + 0x40], xmm1
00001f3d  f30f10482c        movss      xmm1, dword ptr [eax + 0x2c]
00001f42  f30f104054        movss      xmm0, dword ptr [eax + 0x54]
00001f47  0f14c8            unpcklps   xmm1, xmm0
00001f4a  0f298c24e0000000  movaps     xmmword ptr [esp + 0xe0], xmm1
00001f52  f30f104830        movss      xmm1, dword ptr [eax + 0x30]
00001f57  f30f104058        movss      xmm0, dword ptr [eax + 0x58]
00001f5c  0f14c8            unpcklps   xmm1, xmm0
00001f5f  0f298c24f0000000  movaps     xmmword ptr [esp + 0xf0], xmm1
00001f67  f30f104840        movss      xmm1, dword ptr [eax + 0x40]
00001f6c  f30f104068        movss      xmm0, dword ptr [eax + 0x68]
00001f71  0f14c8            unpcklps   xmm1, xmm0
00001f74  0f298c2400010000  movaps     xmmword ptr [esp + 0x100], xmm1
00001f7c  f30f104844        movss      xmm1, dword ptr [eax + 0x44]
00001f81  f30f10406c        movss      xmm0, dword ptr [eax + 0x6c]
00001f86  0f14c8            unpcklps   xmm1, xmm0
00001f89  0f298c24a0000000  movaps     xmmword ptr [esp + 0xa0], xmm1
00001f91  f20f10a884000000  movsd      xmm5, qword ptr [eax + 0x84]
00001f99  0f29ac2480010000  movaps     xmmword ptr [esp + 0x180], xmm5
00001fa1  f30f106074        movss      xmm4, dword ptr [eax + 0x74]
00001fa6  f30f105870        movss      xmm3, dword ptr [eax + 0x70]
00001fab  f30f105078        movss      xmm2, dword ptr [eax + 0x78]
00001fb0  f30f10487c        movss      xmm1, dword ptr [eax + 0x7c]
00001fb5  f30f108080000000  movss      xmm0, dword ptr [eax + 0x80]
00001fbd  f20f11a888000000  movsd      qword ptr [eax + 0x88], xmm5
00001fc5  f30f11b084000000  movss      dword ptr [eax + 0x84], xmm6
00001fcd  f30f10a89c000000  movss      xmm5, dword ptr [eax + 0x9c]
00001fd5  0f14e5            unpcklps   xmm4, xmm5
00001fd8  0f16fc            movlhps    xmm7, xmm4
00001fdb  f30f10a098000000  movss      xmm4, dword ptr [eax + 0x98]
00001fe3  0f14dc            unpcklps   xmm3, xmm4
00001fe6  0f28642450        movaps     xmm4, xmmword ptr [esp + 0x50]
00001feb  0f16e3            movlhps    xmm4, xmm3
00001fee  0f29642450        movaps     xmmword ptr [esp + 0x50], xmm4
00001ff3  f30f1098a0000000  movss      xmm3, dword ptr [eax + 0xa0]
00001ffb  0f14d3            unpcklps   xmm2, xmm3
00001ffe  0f285c2440        movaps     xmm3, xmmword ptr [esp + 0x40]
00002003  0f16da            movlhps    xmm3, xmm2
00002006  0f295c2440        movaps     xmmword ptr [esp + 0x40], xmm3
0000200b  f30f1090a4000000  movss      xmm2, dword ptr [eax + 0xa4]
00002013  0f14ca            unpcklps   xmm1, xmm2
00002016  0f289424e0000000  movaps     xmm2, xmmword ptr [esp + 0xe0]
0000201e  0f16d1            movlhps    xmm2, xmm1
00002021  0f299424e0000000  movaps     xmmword ptr [esp + 0xe0], xmm2
00002029  f30f1088a8000000  movss      xmm1, dword ptr [eax + 0xa8]
00002031  0f14c1            unpcklps   xmm0, xmm1
00002034  0f288c24f0000000  movaps     xmm1, xmmword ptr [esp + 0xf0]
0000203c  0f16c8            movlhps    xmm1, xmm0
0000203f  0f298c24f0000000  movaps     xmmword ptr [esp + 0xf0], xmm1
00002047  f20f1090ac000000  movsd      xmm2, qword ptr [eax + 0xac]
0000204f  0f29942470010000  movaps     xmmword ptr [esp + 0x170], xmm2
00002057  f30f108890000000  movss      xmm1, dword ptr [eax + 0x90]
0000205f  f30f108094000000  movss      xmm0, dword ptr [eax + 0x94]
00002067  f20f1190b0000000  movsd      qword ptr [eax + 0xb0], xmm2
0000206f  f30f11b0ac000000  movss      dword ptr [eax + 0xac], xmm6
00002077  f30f1090b8000000  movss      xmm2, dword ptr [eax + 0xb8]
0000207f  0f14ca            unpcklps   xmm1, xmm2
00002082  0f28942400010000  movaps     xmm2, xmmword ptr [esp + 0x100]
0000208a  0f16d1            movlhps    xmm2, xmm1
0000208d  0f29942400010000  movaps     xmmword ptr [esp + 0x100], xmm2
00002095  f30f1088bc000000  movss      xmm1, dword ptr [eax + 0xbc]
0000209d  0f14c1            unpcklps   xmm0, xmm1
000020a0  0f288c24a0000000  movaps     xmm1, xmmword ptr [esp + 0xa0]
000020a8  0f16c8            movlhps    xmm1, xmm0
000020ab  0f298c24a0000000  movaps     xmmword ptr [esp + 0xa0], xmm1
000020b3  f20f1080d4000000  movsd      xmm0, qword ptr [eax + 0xd4]
000020bb  0f29842460010000  movaps     xmmword ptr [esp + 0x160], xmm0
000020c3  f30f10a0c4000000  movss      xmm4, dword ptr [eax + 0xc4]
000020cb  f30f1090c0000000  movss      xmm2, dword ptr [eax + 0xc0]
000020d3  f30f1088c8000000  movss      xmm1, dword ptr [eax + 0xc8]
000020db  f30f10a8cc000000  movss      xmm5, dword ptr [eax + 0xcc]
000020e3  f30f1098d0000000  movss      xmm3, dword ptr [eax + 0xd0]
000020eb  f20f1180d8000000  movsd      qword ptr [eax + 0xd8], xmm0
000020f3  f30f11b0d4000000  movss      dword ptr [eax + 0xd4], xmm6
000020fb  f30f1080ec000000  movss      xmm0, dword ptr [eax + 0xec]
00002103  0f14e0            unpcklps   xmm4, xmm0
00002106  f30f1080e8000000  movss      xmm0, dword ptr [eax + 0xe8]
0000210e  0f14d0            unpcklps   xmm2, xmm0
00002111  f30f1080f0000000  movss      xmm0, dword ptr [eax + 0xf0]
00002119  0f14c8            unpcklps   xmm1, xmm0
0000211c  0f294c2460        movaps     xmmword ptr [esp + 0x60], xmm1
00002121  f30f1080f4000000  movss      xmm0, dword ptr [eax + 0xf4]
00002129  0f14e8            unpcklps   xmm5, xmm0
0000212c  0f29ac24b0000000  movaps     xmmword ptr [esp + 0xb0], xmm5
00002134  f30f1080f8000000  movss      xmm0, dword ptr [eax + 0xf8]
0000213c  0f14d8            unpcklps   xmm3, xmm0
0000213f  0f299c24c0000000  movaps     xmmword ptr [esp + 0xc0], xmm3
00002147  f20f1080fc000000  movsd      xmm0, qword ptr [eax + 0xfc]
0000214f  0f29842450010000  movaps     xmmword ptr [esp + 0x150], xmm0
00002157  f30f1098e0000000  movss      xmm3, dword ptr [eax + 0xe0]
0000215f  f30f1088e4000000  movss      xmm1, dword ptr [eax + 0xe4]
00002167  f20f118000010000  movsd      qword ptr [eax + 0x100], xmm0
0000216f  f30f11b0fc000000  movss      dword ptr [eax + 0xfc], xmm6
00002177  f30f108008010000  movss      xmm0, dword ptr [eax + 0x108]
0000217f  0f14d8            unpcklps   xmm3, xmm0
00002182  0f299c24d0000000  movaps     xmmword ptr [esp + 0xd0], xmm3
0000218a  f30f10800c010000  movss      xmm0, dword ptr [eax + 0x10c]
00002192  0f14c8            unpcklps   xmm1, xmm0
00002195  0f298c2490000000  movaps     xmmword ptr [esp + 0x90], xmm1
0000219d  f20f109824010000  movsd      xmm3, qword ptr [eax + 0x124]
000021a5  0f299c2440010000  movaps     xmmword ptr [esp + 0x140], xmm3
000021ad  f30f10a814010000  movss      xmm5, dword ptr [eax + 0x114]
000021b5  f30f108810010000  movss      xmm1, dword ptr [eax + 0x110]
000021bd  f30f108018010000  movss      xmm0, dword ptr [eax + 0x118]
000021c5  0f29842420010000  movaps     xmmword ptr [esp + 0x120], xmm0
000021cd  f30f10801c010000  movss      xmm0, dword ptr [eax + 0x11c]
000021d5  0f29442470        movaps     xmmword ptr [esp + 0x70], xmm0
000021da  f30f108020010000  movss      xmm0, dword ptr [eax + 0x120]
000021e2  0f29842480000000  movaps     xmmword ptr [esp + 0x80], xmm0
000021ea  f20f119828010000  movsd      qword ptr [eax + 0x128], xmm3
000021f2  f30f11b024010000  movss      dword ptr [eax + 0x124], xmm6
000021fa  0f29b42430010000  movaps     xmmword ptr [esp + 0x130], xmm6
00002202  f30f10983c010000  movss      xmm3, dword ptr [eax + 0x13c]
0000220a  0f14eb            unpcklps   xmm5, xmm3
0000220d  0f16e5            movlhps    xmm4, xmm5
00002210  f30f109838010000  movss      xmm3, dword ptr [eax + 0x138]
00002218  0f14cb            unpcklps   xmm1, xmm3
0000221b  0f16d1            movlhps    xmm2, xmm1
0000221e  0fc6f600          shufps     xmm6, xmm6, 0
00002222  0f28de            movaps     xmm3, xmm6
00002225  0f5cf7            subps      xmm6, xmm7
00002228  0f59742450        mulps      xmm6, xmmword ptr [esp + 0x50]
0000222d  0f28a98ccdffff    movaps     xmm5, xmmword ptr [ecx - 0x3274]
00002234  0f58fd            addps      xmm7, xmm5
00002237  0f58fe            addps      xmm7, xmm6
0000223a  0f5cdc            subps      xmm3, xmm4
0000223d  0f58e5            addps      xmm4, xmm5
00002240  0f28ea            movaps     xmm5, xmm2
00002243  0f29942410010000  movaps     xmmword ptr [esp + 0x110], xmm2
0000224b  0f59da            mulps      xmm3, xmm2
0000224e  0f58e3            addps      xmm4, xmm3
00002251  f30f108840010000  movss      xmm1, dword ptr [eax + 0x140]
00002259  0f28842420010000  movaps     xmm0, xmmword ptr [esp + 0x120]
00002261  0f14c1            unpcklps   xmm0, xmm1
00002264  0f28742460        movaps     xmm6, xmmword ptr [esp + 0x60]
00002269  0f16f0            movlhps    xmm6, xmm0
0000226c  f30f108030010000  movss      xmm0, dword ptr [eax + 0x130]
00002274  0f29442460        movaps     xmmword ptr [esp + 0x60], xmm0
00002279  660f6e9034010000  movd       xmm2, dword ptr [eax + 0x134]
00002281  f30f117824        movss      dword ptr [eax + 0x24], xmm7
00002286  f30f16df          movshdup   xmm3, xmm7
0000228a  f30f11584c        movss      dword ptr [eax + 0x4c], xmm3
0000228f  0f28df            movaps     xmm3, xmm7
00002292  660f15df          unpckhpd   xmm3, xmm7
00002296  f30f115874        movss      dword ptr [eax + 0x74], xmm3
0000229b  0f28df            movaps     xmm3, xmm7
0000229e  0fc6dfff          shufps     xmm3, xmm7, 0xff
000022a2  f30f11989c000000  movss      dword ptr [eax + 0x9c], xmm3
000022aa  f30f11a0c4000000  movss      dword ptr [eax + 0xc4], xmm4
000022b2  f30f16dc          movshdup   xmm3, xmm4
000022b6  f30f1198ec000000  movss      dword ptr [eax + 0xec], xmm3
000022be  0f28dc            movaps     xmm3, xmm4
000022c1  660f15dc          unpckhpd   xmm3, xmm4
000022c5  f30f119814010000  movss      dword ptr [eax + 0x114], xmm3
000022cd  0f28dc            movaps     xmm3, xmm4
000022d0  0fc6dcff          shufps     xmm3, xmm4, 0xff
000022d4  f30f11983c010000  movss      dword ptr [eax + 0x13c], xmm3
000022dc  f30f109844010000  movss      xmm3, dword ptr [eax + 0x144]
000022e4  0f28442470        movaps     xmm0, xmmword ptr [esp + 0x70]
000022e9  0f14c3            unpcklps   xmm0, xmm3
000022ec  0f288c24b0000000  movaps     xmm1, xmmword ptr [esp + 0xb0]
000022f4  0f16c8            movlhps    xmm1, xmm0
000022f7  0f298c24b0000000  movaps     xmmword ptr [esp + 0xb0], xmm1
000022ff  f30f109848010000  movss      xmm3, dword ptr [eax + 0x148]
00002307  0f28842480000000  movaps     xmm0, xmmword ptr [esp + 0x80]
0000230f  0f14c3            unpcklps   xmm0, xmm3
00002312  0f288c24c0000000  movaps     xmm1, xmmword ptr [esp + 0xc0]
0000231a  0f16c8            movlhps    xmm1, xmm0
0000231d  0f298c24c0000000  movaps     xmmword ptr [esp + 0xc0], xmm1
00002325  f20f10804c010000  movsd      xmm0, qword ptr [eax + 0x14c]
0000232d  0f29442470        movaps     xmmword ptr [esp + 0x70], xmm0
00002332  f20f118050010000  movsd      qword ptr [eax + 0x150], xmm0
0000233a  0f28842430010000  movaps     xmm0, xmmword ptr [esp + 0x130]
00002342  f30f11804c010000  movss      dword ptr [eax + 0x14c], xmm0
0000234a  0f28ce            movaps     xmm1, xmm6
0000234d  0f5ce6            subps      xmm4, xmm6
00002350  0f59e5            mulps      xmm4, xmm5
00002353  0f58e6            addps      xmm4, xmm6
00002356  0f29a42480000000  movaps     xmmword ptr [esp + 0x80], xmm4
0000235e  0f28f7            movaps     xmm6, xmm7
00002361  0f285c2440        movaps     xmm3, xmmword ptr [esp + 0x40]
00002366  0f5cf3            subps      xmm6, xmm3
00002369  0f59742450        mulps      xmm6, xmmword ptr [esp + 0x50]
0000236e  0f58f3            addps      xmm6, xmm3
00002371  0f29742440        movaps     xmmword ptr [esp + 0x40], xmm6
00002376  660f6e985c010000  movd       xmm3, dword ptr [eax + 0x15c]
0000237e  660f6fc2          movdqa     xmm0, xmm2
00002382  660f62c3          punpckldq  xmm0, xmm3
00002386  660f6f942490000000  movdqa     xmm2, xmmword ptr [esp + 0x90]
0000238f  660f6cd0          punpcklqdq xmm2, xmm0
00002393  f30f108058010000  movss      xmm0, dword ptr [eax + 0x158]
0000239b  0f284c2460        movaps     xmm1, xmmword ptr [esp + 0x60]
000023a0  0f14c8            unpcklps   xmm1, xmm0
000023a3  0f288424d0000000  movaps     xmm0, xmmword ptr [esp + 0xd0]
000023ab  0f16c1            movlhps    xmm0, xmm1
000023ae  0f298424d0000000  movaps     xmmword ptr [esp + 0xd0], xmm0
000023b6  0f288424b0000000  movaps     xmm0, xmmword ptr [esp + 0xb0]
000023be  0f5ce0            subps      xmm4, xmm0
000023c1  0f28bc2410010000  movaps     xmm7, xmmword ptr [esp + 0x110]
000023c9  0f59e7            mulps      xmm4, xmm7
000023cc  0f58e0            addps      xmm4, xmm0
000023cf  0f288424e0000000  movaps     xmm0, xmmword ptr [esp + 0xe0]
000023d7  0f5cf0            subps      xmm6, xmm0
000023da  0f285c2450        movaps     xmm3, xmmword ptr [esp + 0x50]
000023df  0f59f3            mulps      xmm6, xmm3
000023e2  0f58f0            addps      xmm6, xmm0
000023e5  0f28c6            movaps     xmm0, xmm6
000023e8  0f288c24f0000000  movaps     xmm1, xmmword ptr [esp + 0xf0]
000023f0  0f5cc1            subps      xmm0, xmm1
000023f3  0f59c3            mulps      xmm0, xmm3
000023f6  0f28ec            movaps     xmm5, xmm4
000023f9  0f289c24c0000000  movaps     xmm3, xmmword ptr [esp + 0xc0]
00002401  0f5ceb            subps      xmm5, xmm3
00002404  0f59ef            mulps      xmm5, xmm7
00002407  0f58eb            addps      xmm5, xmm3
0000240a  0f58c1            addps      xmm0, xmm1
0000240d  0f284c2440        movaps     xmm1, xmmword ptr [esp + 0x40]
00002412  f30f114828        movss      dword ptr [eax + 0x28], xmm1
00002417  f30f11702c        movss      dword ptr [eax + 0x2c], xmm6
0000241c  f30f114030        movss      dword ptr [eax + 0x30], xmm0
00002421  0f28de            movaps     xmm3, xmm6
00002424  0fc6d911          shufps     xmm3, xmm1, 0x11
00002428  0fc6d9e2          shufps     xmm3, xmm1, 0xe2
0000242c  0f135850          movlps     qword ptr [eax + 0x50], xmm3
00002430  f30f16d8          movshdup   xmm3, xmm0
00002434  f30f115858        movss      dword ptr [eax + 0x58], xmm3
00002439  660f6f997ccdffff  movdqa     xmm3, xmmword ptr [ecx - 0x3284]
00002441  660f76d3          pcmpeqd    xmm2, xmm3
00002445  660f7f942490000000  movdqa     xmmword ptr [esp + 0x90], xmm2
0000244e  660f6fbc24a0000000  movdqa     xmm7, xmmword ptr [esp + 0xa0]
00002457  660f76fb          pcmpeqd    xmm7, xmm3
0000245b  0f289c2490010000  movaps     xmm3, xmmword ptr [esp + 0x190]
00002463  0fc69c24a001000055  shufps     xmm3, xmmword ptr [esp + 0x1a0], 0x55
0000246c  0f288c2470010000  movaps     xmm1, xmmword ptr [esp + 0x170]
00002474  0fc68c248001000055  shufps     xmm1, xmmword ptr [esp + 0x180], 0x55
0000247d  0fc6d922          shufps     xmm3, xmm1, 0x22
00002481  0f288c2450010000  movaps     xmm1, xmmword ptr [esp + 0x150]
00002489  0fc68c246001000055  shufps     xmm1, xmmword ptr [esp + 0x160], 0x55
00002492  0f28542470        movaps     xmm2, xmmword ptr [esp + 0x70]
00002497  0fc694244001000055  shufps     xmm2, xmmword ptr [esp + 0x140], 0x55
000024a0  0fc6ca22          shufps     xmm1, xmm2, 0x22
000024a4  0f5cd8            subps      xmm3, xmm0
000024a7  0f54df            andps      xmm3, xmm7
000024aa  0f55f8            andnps     xmm7, xmm0
000024ad  0f56fb            orps       xmm7, xmm3
000024b0  0f59bc2400010000  mulps      xmm7, xmmword ptr [esp + 0x100]
000024b8  0f5ccd            subps      xmm1, xmm5
000024bb  0f28942490000000  movaps     xmm2, xmmword ptr [esp + 0x90]
000024c3  0f54ca            andps      xmm1, xmm2
000024c6  0f55d5            andnps     xmm2, xmm5
000024c9  0f56d1            orps       xmm2, xmm1
000024cc  0f599424d0000000  mulps      xmm2, xmmword ptr [esp + 0xd0]
000024d4  f30f113c24        movss      dword ptr [esp], xmm7
000024d9  f30f16df          movshdup   xmm3, xmm7
000024dd  f30f115c2408      movss      dword ptr [esp + 8], xmm3
000024e3  f30f11542420      movss      dword ptr [esp + 0x20], xmm2
000024e9  f30f16da          movshdup   xmm3, xmm2
000024ed  0f28ca            movaps     xmm1, xmm2
000024f0  f30f115c2428      movss      dword ptr [esp + 0x28], xmm3
000024f6  0f28df            movaps     xmm3, xmm7
000024f9  0fc6dfff          shufps     xmm3, xmm7, 0xff
000024fd  f30f115c2418      movss      dword ptr [esp + 0x18], xmm3
00002503  0f12ff            movhlps    xmm7, xmm7
00002506  f30f117c2410      movss      dword ptr [esp + 0x10], xmm7
0000250c  0f28da            movaps     xmm3, xmm2
0000250f  0fc6daff          shufps     xmm3, xmm2, 0xff
00002513  f30f115c2438      movss      dword ptr [esp + 0x38], xmm3
00002519  660f15ca          unpckhpd   xmm1, xmm2
0000251d  f30f114c2430      movss      dword ptr [esp + 0x30], xmm1
00002523  d90424            fld        dword ptr [esp]
00002526  dd0c24            fisttp     qword ptr [esp]
00002529  d9442408          fld        dword ptr [esp + 8]
0000252d  dd4c2408          fisttp     qword ptr [esp + 8]
00002531  d9442420          fld        dword ptr [esp + 0x20]
00002535  dd4c2420          fisttp     qword ptr [esp + 0x20]
00002539  d9442428          fld        dword ptr [esp + 0x28]
0000253d  dd4c2428          fisttp     qword ptr [esp + 0x28]
00002541  d9442418          fld        dword ptr [esp + 0x18]
00002545  dd4c2418          fisttp     qword ptr [esp + 0x18]
00002549  d9442410          fld        dword ptr [esp + 0x10]
0000254d  dd4c2410          fisttp     qword ptr [esp + 0x10]
00002551  d9442438          fld        dword ptr [esp + 0x38]
00002555  dd4c2438          fisttp     qword ptr [esp + 0x38]
00002559  d9442430          fld        dword ptr [esp + 0x30]
0000255d  dd4c2430          fisttp     qword ptr [esp + 0x30]
00002561  f30f7e1424        movq       xmm2, qword ptr [esp]
00002566  f30f7e5c2408      movq       xmm3, qword ptr [esp + 8]
0000256c  660f6cd3          punpcklqdq xmm2, xmm3
00002570  f30f7e7c2420      movq       xmm7, qword ptr [esp + 0x20]
00002576  f30f7e5c2428      movq       xmm3, qword ptr [esp + 0x28]
0000257c  660f6cfb          punpcklqdq xmm7, xmm3
00002580  660fd4fa          paddq      xmm7, xmm2
00002584  f30f7e542418      movq       xmm2, qword ptr [esp + 0x18]
0000258a  f30f7e5c2410      movq       xmm3, qword ptr [esp + 0x10]
00002590  660f6cda          punpcklqdq xmm3, xmm2
00002594  f30f7e4c2438      movq       xmm1, qword ptr [esp + 0x38]
0000259a  f30f7e542430      movq       xmm2, qword ptr [esp + 0x30]
000025a0  660f6cd1          punpcklqdq xmm2, xmm1
000025a4  660fd4d3          paddq      xmm2, xmm3
000025a8  660fd4d7          paddq      xmm2, xmm7
000025ac  0f284c2440        movaps     xmm1, xmmword ptr [esp + 0x40]
000025b1  0f28d9            movaps     xmm3, xmm1
000025b4  0f15de            unpckhps   xmm3, xmm6
000025b7  0f135878          movlps     qword ptr [eax + 0x78], xmm3
000025bb  0f28d8            movaps     xmm3, xmm0
000025be  660f15d8          unpckhpd   xmm3, xmm0
000025c2  f30f119880000000  movss      dword ptr [eax + 0x80], xmm3
000025ca  0fc6f1ff          shufps     xmm6, xmm1, 0xff
000025ce  0fc6f1e2          shufps     xmm6, xmm1, 0xe2
000025d2  0f13b0a0000000    movlps     qword ptr [eax + 0xa0], xmm6
000025d9  0fc6c0ff          shufps     xmm0, xmm0, 0xff
000025dd  f30f1180a8000000  movss      dword ptr [eax + 0xa8], xmm0
000025e5  0f288c2480000000  movaps     xmm1, xmmword ptr [esp + 0x80]
000025ed  f30f1188c8000000  movss      dword ptr [eax + 0xc8], xmm1
000025f5  f30f11a0cc000000  movss      dword ptr [eax + 0xcc], xmm4
000025fd  f30f11a8d0000000  movss      dword ptr [eax + 0xd0], xmm5
00002605  0f28c4            movaps     xmm0, xmm4
00002608  0fc6c111          shufps     xmm0, xmm1, 0x11
0000260c  0fc6c1e2          shufps     xmm0, xmm1, 0xe2
00002610  0f1380f0000000    movlps     qword ptr [eax + 0xf0], xmm0
00002617  f30f16c5          movshdup   xmm0, xmm5
0000261b  f30f1180f8000000  movss      dword ptr [eax + 0xf8], xmm0
00002623  0f28c1            movaps     xmm0, xmm1
00002626  0f15c4            unpckhps   xmm0, xmm4
00002629  0f138018010000    movlps     qword ptr [eax + 0x118], xmm0
00002630  0f28c5            movaps     xmm0, xmm5
00002633  660f15c5          unpckhpd   xmm0, xmm5
00002637  f30f118020010000  movss      dword ptr [eax + 0x120], xmm0
0000263f  0fc6e1ff          shufps     xmm4, xmm1, 0xff
00002643  0fc6e1e2          shufps     xmm4, xmm1, 0xe2
00002647  0f13a040010000    movlps     qword ptr [eax + 0x140], xmm4
0000264e  0fc6edff          shufps     xmm5, xmm5, 0xff
00002652  f30f11a848010000  movss      dword ptr [eax + 0x148], xmm5
0000265a  660f70c2ee        pshufd     xmm0, xmm2, 0xee
0000265f  660fd4c2          paddq      xmm0, xmm2
00002663  660f7ec0          movd       eax, xmm0
00002667  8d4803            lea        ecx, [eax + 3]
0000266a  6685c0            test       ax, ax
0000266d  0f49c8            cmovns     ecx, eax
00002670  0fbfc1            movsx      eax, cx
00002673  c1e802            shr        eax, 2
00002676  89ec              mov        esp, ebp
00002678  5d                pop        ebp
00002679  c3                ret        
0000267a  31c0              xor        eax, eax
0000267c  89ec              mov        esp, ebp
0000267e  5d                pop        ebp
0000267f  c3                ret        
