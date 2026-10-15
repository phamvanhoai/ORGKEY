# APK member: lib/x86/libnequalizer.so
# Library SHA-256: 0c7dfba2ccd6a4c88a74500c5b3ecac4f88ff3e4abb681dafc117af7ee94b44b
# Function: Java_sofeh_audio_NEqualizer_processRight; address=0x1630; bytes=2166
# Decoder mode: default; decoded_bytes=2166
00001630  55                push       ebp
00001631  89e5              mov        ebp, esp
00001633  56                push       esi
00001634  83e4f0            and        esp, 0xfffffff0
00001637  81ecd0010000      sub        esp, 0x1d0
0000163d  e800000000        call       0x1642
00001642  58                pop        eax
00001643  81c0b2210000      add        eax, 0x21b2
00001649  8b4d10            mov        ecx, dword ptr [ebp + 0x10]
0000164c  8b5514            mov        edx, dword ptr [ebp + 0x14]
0000164f  09ca              or         edx, ecx
00001651  0f8445080000      je         0x1e9c
00001657  f30f104518        movss      xmm0, dword ptr [ebp + 0x18]
0000165c  f30f104d1c        movss      xmm1, dword ptr [ebp + 0x1c]
00001661  0f14c1            unpcklps   xmm0, xmm1
00001664  0f13842428010000  movlps     qword ptr [esp + 0x128], xmm0
0000166c  dfac2428010000    fild       qword ptr [esp + 0x128]
00001673  d95c244c          fstp       dword ptr [esp + 0x4c]
00001677  f30f107c244c      movss      xmm7, dword ptr [esp + 0x4c]
0000167d  f20f108174010000  movsd      xmm0, qword ptr [ecx + 0x174]
00001685  0f298424b0010000  movaps     xmmword ptr [esp + 0x1b0], xmm0
0000168d  f20f118178010000  movsd      qword ptr [ecx + 0x178], xmm0
00001695  f30f11b974010000  movss      dword ptr [ecx + 0x174], xmm7
0000169d  f20f10819c010000  movsd      xmm0, qword ptr [ecx + 0x19c]
000016a5  0f298424a0010000  movaps     xmmword ptr [esp + 0x1a0], xmm0
000016ad  f20f1181a0010000  movsd      qword ptr [ecx + 0x1a0], xmm0
000016b5  f30f11b99c010000  movss      dword ptr [ecx + 0x19c], xmm7
000016bd  f30f108960010000  movss      xmm1, dword ptr [ecx + 0x160]
000016c5  f30f10b164010000  movss      xmm6, dword ptr [ecx + 0x164]
000016cd  f30f10818c010000  movss      xmm0, dword ptr [ecx + 0x18c]
000016d5  0f14f0            unpcklps   xmm6, xmm0
000016d8  f30f108188010000  movss      xmm0, dword ptr [ecx + 0x188]
000016e0  0f14c8            unpcklps   xmm1, xmm0
000016e3  0f294c2450        movaps     xmmword ptr [esp + 0x50], xmm1
000016e8  f30f108968010000  movss      xmm1, dword ptr [ecx + 0x168]
000016f0  f30f108190010000  movss      xmm0, dword ptr [ecx + 0x190]
000016f8  0f14c8            unpcklps   xmm1, xmm0
000016fb  0f294c2460        movaps     xmmword ptr [esp + 0x60], xmm1
00001700  f30f10896c010000  movss      xmm1, dword ptr [ecx + 0x16c]
00001708  f30f108194010000  movss      xmm0, dword ptr [ecx + 0x194]
00001710  0f14c8            unpcklps   xmm1, xmm0
00001713  0f298c24f0000000  movaps     xmmword ptr [esp + 0xf0], xmm1
0000171b  f30f108970010000  movss      xmm1, dword ptr [ecx + 0x170]
00001723  f30f108198010000  movss      xmm0, dword ptr [ecx + 0x198]
0000172b  0f14c8            unpcklps   xmm1, xmm0
0000172e  0f298c2400010000  movaps     xmmword ptr [esp + 0x100], xmm1
00001736  f30f108980010000  movss      xmm1, dword ptr [ecx + 0x180]
0000173e  f30f1081a8010000  movss      xmm0, dword ptr [ecx + 0x1a8]
00001746  0f14c8            unpcklps   xmm1, xmm0
00001749  0f298c2410010000  movaps     xmmword ptr [esp + 0x110], xmm1
00001751  f30f108984010000  movss      xmm1, dword ptr [ecx + 0x184]
00001759  f30f1081ac010000  movss      xmm0, dword ptr [ecx + 0x1ac]
00001761  0f14c8            unpcklps   xmm1, xmm0
00001764  0f298c24b0000000  movaps     xmmword ptr [esp + 0xb0], xmm1
0000176c  f20f10a9c4010000  movsd      xmm5, qword ptr [ecx + 0x1c4]
00001774  0f29ac2490010000  movaps     xmmword ptr [esp + 0x190], xmm5
0000177c  f30f10a1b4010000  movss      xmm4, dword ptr [ecx + 0x1b4]
00001784  f30f1099b0010000  movss      xmm3, dword ptr [ecx + 0x1b0]
0000178c  f30f1091b8010000  movss      xmm2, dword ptr [ecx + 0x1b8]
00001794  f30f1089bc010000  movss      xmm1, dword ptr [ecx + 0x1bc]
0000179c  f30f1081c0010000  movss      xmm0, dword ptr [ecx + 0x1c0]
000017a4  f20f11a9c8010000  movsd      qword ptr [ecx + 0x1c8], xmm5
000017ac  f30f11b9c4010000  movss      dword ptr [ecx + 0x1c4], xmm7
000017b4  f30f10a9dc010000  movss      xmm5, dword ptr [ecx + 0x1dc]
000017bc  0f14e5            unpcklps   xmm4, xmm5
000017bf  0f16f4            movlhps    xmm6, xmm4
000017c2  f30f10a1d8010000  movss      xmm4, dword ptr [ecx + 0x1d8]
000017ca  0f14dc            unpcklps   xmm3, xmm4
000017cd  0f28642450        movaps     xmm4, xmmword ptr [esp + 0x50]
000017d2  0f16e3            movlhps    xmm4, xmm3
000017d5  0f29642450        movaps     xmmword ptr [esp + 0x50], xmm4
000017da  f30f1099e0010000  movss      xmm3, dword ptr [ecx + 0x1e0]
000017e2  0f14d3            unpcklps   xmm2, xmm3
000017e5  0f285c2460        movaps     xmm3, xmmword ptr [esp + 0x60]
000017ea  0f16da            movlhps    xmm3, xmm2
000017ed  0f295c2460        movaps     xmmword ptr [esp + 0x60], xmm3
000017f2  f30f1091e4010000  movss      xmm2, dword ptr [ecx + 0x1e4]
000017fa  0f14ca            unpcklps   xmm1, xmm2
000017fd  0f289424f0000000  movaps     xmm2, xmmword ptr [esp + 0xf0]
00001805  0f16d1            movlhps    xmm2, xmm1
00001808  0f299424f0000000  movaps     xmmword ptr [esp + 0xf0], xmm2
00001810  f30f1089e8010000  movss      xmm1, dword ptr [ecx + 0x1e8]
00001818  0f14c1            unpcklps   xmm0, xmm1
0000181b  0f288c2400010000  movaps     xmm1, xmmword ptr [esp + 0x100]
00001823  0f16c8            movlhps    xmm1, xmm0
00001826  0f298c2400010000  movaps     xmmword ptr [esp + 0x100], xmm1
0000182e  f20f1091ec010000  movsd      xmm2, qword ptr [ecx + 0x1ec]
00001836  0f29942480010000  movaps     xmmword ptr [esp + 0x180], xmm2
0000183e  f30f1089d0010000  movss      xmm1, dword ptr [ecx + 0x1d0]
00001846  f30f1081d4010000  movss      xmm0, dword ptr [ecx + 0x1d4]
0000184e  f20f1191f0010000  movsd      qword ptr [ecx + 0x1f0], xmm2
00001856  0f28df            movaps     xmm3, xmm7
00001859  0f297c2470        movaps     xmmword ptr [esp + 0x70], xmm7
0000185e  f30f11b9ec010000  movss      dword ptr [ecx + 0x1ec], xmm7
00001866  f30f1091f8010000  movss      xmm2, dword ptr [ecx + 0x1f8]
0000186e  0f14ca            unpcklps   xmm1, xmm2
00001871  0f28942410010000  movaps     xmm2, xmmword ptr [esp + 0x110]
00001879  0f16d1            movlhps    xmm2, xmm1
0000187c  0f29942410010000  movaps     xmmword ptr [esp + 0x110], xmm2
00001884  f30f1089fc010000  movss      xmm1, dword ptr [ecx + 0x1fc]
0000188c  0f14c1            unpcklps   xmm0, xmm1
0000188f  0f288c24b0000000  movaps     xmm1, xmmword ptr [esp + 0xb0]
00001897  0f16c8            movlhps    xmm1, xmm0
0000189a  0f298c24b0000000  movaps     xmmword ptr [esp + 0xb0], xmm1
000018a2  f20f108114020000  movsd      xmm0, qword ptr [ecx + 0x214]
000018aa  0f29842470010000  movaps     xmmword ptr [esp + 0x170], xmm0
000018b2  f30f10b904020000  movss      xmm7, dword ptr [ecx + 0x204]
000018ba  f30f109100020000  movss      xmm2, dword ptr [ecx + 0x200]
000018c2  f30f108908020000  movss      xmm1, dword ptr [ecx + 0x208]
000018ca  f30f10a90c020000  movss      xmm5, dword ptr [ecx + 0x20c]
000018d2  f30f10a110020000  movss      xmm4, dword ptr [ecx + 0x210]
000018da  f20f118118020000  movsd      qword ptr [ecx + 0x218], xmm0
000018e2  f30f119914020000  movss      dword ptr [ecx + 0x214], xmm3
000018ea  f30f10812c020000  movss      xmm0, dword ptr [ecx + 0x22c]
000018f2  0f14f8            unpcklps   xmm7, xmm0
000018f5  f30f108128020000  movss      xmm0, dword ptr [ecx + 0x228]
000018fd  0f14d0            unpcklps   xmm2, xmm0
00001900  0f29942480000000  movaps     xmmword ptr [esp + 0x80], xmm2
00001908  f30f108130020000  movss      xmm0, dword ptr [ecx + 0x230]
00001910  0f14c8            unpcklps   xmm1, xmm0
00001913  0f298c2490000000  movaps     xmmword ptr [esp + 0x90], xmm1
0000191b  f30f108134020000  movss      xmm0, dword ptr [ecx + 0x234]
00001923  0f14e8            unpcklps   xmm5, xmm0
00001926  0f29ac24c0000000  movaps     xmmword ptr [esp + 0xc0], xmm5
0000192e  f30f108138020000  movss      xmm0, dword ptr [ecx + 0x238]
00001936  0f14e0            unpcklps   xmm4, xmm0
00001939  0f29a424d0000000  movaps     xmmword ptr [esp + 0xd0], xmm4
00001941  f20f10813c020000  movsd      xmm0, qword ptr [ecx + 0x23c]
00001949  0f29842460010000  movaps     xmmword ptr [esp + 0x160], xmm0
00001951  f30f109920020000  movss      xmm3, dword ptr [ecx + 0x220]
00001959  660f6e9124020000  movd       xmm2, dword ptr [ecx + 0x224]
00001961  f20f118140020000  movsd      qword ptr [ecx + 0x240], xmm0
00001969  0f284c2470        movaps     xmm1, xmmword ptr [esp + 0x70]
0000196e  f30f11893c020000  movss      dword ptr [ecx + 0x23c], xmm1
00001976  f30f108148020000  movss      xmm0, dword ptr [ecx + 0x248]
0000197e  0f14d8            unpcklps   xmm3, xmm0
00001981  0f299c24e0000000  movaps     xmmword ptr [esp + 0xe0], xmm3
00001989  660f6e814c020000  movd       xmm0, dword ptr [ecx + 0x24c]
00001991  660f62d0          punpckldq  xmm2, xmm0
00001995  f20f108164020000  movsd      xmm0, qword ptr [ecx + 0x264]
0000199d  0f29842450010000  movaps     xmmword ptr [esp + 0x150], xmm0
000019a5  f30f10a954020000  movss      xmm5, dword ptr [ecx + 0x254]
000019ad  f30f109950020000  movss      xmm3, dword ptr [ecx + 0x250]
000019b5  f30f10a158020000  movss      xmm4, dword ptr [ecx + 0x258]
000019bd  0f29a42430010000  movaps     xmmword ptr [esp + 0x130], xmm4
000019c5  f30f10a15c020000  movss      xmm4, dword ptr [ecx + 0x25c]
000019cd  0f29a42440010000  movaps     xmmword ptr [esp + 0x140], xmm4
000019d5  f30f10a160020000  movss      xmm4, dword ptr [ecx + 0x260]
000019dd  0f29a424a0000000  movaps     xmmword ptr [esp + 0xa0], xmm4
000019e5  f20f118168020000  movsd      qword ptr [ecx + 0x268], xmm0
000019ed  f30f118964020000  movss      dword ptr [ecx + 0x264], xmm1
000019f5  f30f10a17c020000  movss      xmm4, dword ptr [ecx + 0x27c]
000019fd  0f14ec            unpcklps   xmm5, xmm4
00001a00  0f16fd            movlhps    xmm7, xmm5
00001a03  f30f10a178020000  movss      xmm4, dword ptr [ecx + 0x278]
00001a0b  0f14dc            unpcklps   xmm3, xmm4
00001a0e  0f28842480000000  movaps     xmm0, xmmword ptr [esp + 0x80]
00001a16  0f16c3            movlhps    xmm0, xmm3
00001a19  0f29842480000000  movaps     xmmword ptr [esp + 0x80], xmm0
00001a21  0f28d9            movaps     xmm3, xmm1
00001a24  0fc6d900          shufps     xmm3, xmm1, 0
00001a28  0f28e3            movaps     xmm4, xmm3
00001a2b  0f5cde            subps      xmm3, xmm6
00001a2e  0f595c2450        mulps      xmm3, xmmword ptr [esp + 0x50]
00001a33  0f28a88ccdffff    movaps     xmm5, xmmword ptr [eax - 0x3274]
00001a3a  0f58f5            addps      xmm6, xmm5
00001a3d  0f58f3            addps      xmm6, xmm3
00001a40  0f5ce7            subps      xmm4, xmm7
00001a43  0f58fd            addps      xmm7, xmm5
00001a46  0f59e0            mulps      xmm4, xmm0
00001a49  0f58fc            addps      xmm7, xmm4
00001a4c  0f28cf            movaps     xmm1, xmm7
00001a4f  f30f109980020000  movss      xmm3, dword ptr [ecx + 0x280]
00001a57  0f28842430010000  movaps     xmm0, xmmword ptr [esp + 0x130]
00001a5f  0f14c3            unpcklps   xmm0, xmm3
00001a62  0f28bc2490000000  movaps     xmm7, xmmword ptr [esp + 0x90]
00001a6a  0f16f8            movlhps    xmm7, xmm0
00001a6d  f30f108170020000  movss      xmm0, dword ptr [ecx + 0x270]
00001a75  0f29842490000000  movaps     xmmword ptr [esp + 0x90], xmm0
00001a7d  660f6ea974020000  movd       xmm5, dword ptr [ecx + 0x274]
00001a85  f30f11b164010000  movss      dword ptr [ecx + 0x164], xmm6
00001a8d  f30f16e6          movshdup   xmm4, xmm6
00001a91  f30f11a18c010000  movss      dword ptr [ecx + 0x18c], xmm4
00001a99  0f28e6            movaps     xmm4, xmm6
00001a9c  660f15e6          unpckhpd   xmm4, xmm6
00001aa0  f30f11a1b4010000  movss      dword ptr [ecx + 0x1b4], xmm4
00001aa8  0f28e6            movaps     xmm4, xmm6
00001aab  0fc6e6ff          shufps     xmm4, xmm6, 0xff
00001aaf  f30f11a1dc010000  movss      dword ptr [ecx + 0x1dc], xmm4
00001ab7  f30f118904020000  movss      dword ptr [ecx + 0x204], xmm1
00001abf  f30f16e1          movshdup   xmm4, xmm1
00001ac3  f30f11a12c020000  movss      dword ptr [ecx + 0x22c], xmm4
00001acb  0f28e1            movaps     xmm4, xmm1
00001ace  660f15e1          unpckhpd   xmm4, xmm1
00001ad2  f30f11a154020000  movss      dword ptr [ecx + 0x254], xmm4
00001ada  0f28e1            movaps     xmm4, xmm1
00001add  0fc6e1ff          shufps     xmm4, xmm1, 0xff
00001ae1  f30f11a17c020000  movss      dword ptr [ecx + 0x27c], xmm4
00001ae9  f30f10a184020000  movss      xmm4, dword ptr [ecx + 0x284]
00001af1  0f28842440010000  movaps     xmm0, xmmword ptr [esp + 0x140]
00001af9  0f14c4            unpcklps   xmm0, xmm4
00001afc  0f289c24c0000000  movaps     xmm3, xmmword ptr [esp + 0xc0]
00001b04  0f16d8            movlhps    xmm3, xmm0
00001b07  0f299c24c0000000  movaps     xmmword ptr [esp + 0xc0], xmm3
00001b0f  f30f10a188020000  movss      xmm4, dword ptr [ecx + 0x288]
00001b17  0f288424a0000000  movaps     xmm0, xmmword ptr [esp + 0xa0]
00001b1f  0f14c4            unpcklps   xmm0, xmm4
00001b22  0f289c24d0000000  movaps     xmm3, xmmword ptr [esp + 0xd0]
00001b2a  0f16d8            movlhps    xmm3, xmm0
00001b2d  0f299c24d0000000  movaps     xmmword ptr [esp + 0xd0], xmm3
00001b35  f20f10818c020000  movsd      xmm0, qword ptr [ecx + 0x28c]
00001b3d  0f298424a0000000  movaps     xmmword ptr [esp + 0xa0], xmm0
00001b45  f20f118190020000  movsd      qword ptr [ecx + 0x290], xmm0
00001b4d  0f28442470        movaps     xmm0, xmmword ptr [esp + 0x70]
00001b52  f30f11818c020000  movss      dword ptr [ecx + 0x28c], xmm0
00001b5a  0f28df            movaps     xmm3, xmm7
00001b5d  0f5ccf            subps      xmm1, xmm7
00001b60  0f28bc2480000000  movaps     xmm7, xmmword ptr [esp + 0x80]
00001b68  0f59cf            mulps      xmm1, xmm7
00001b6b  0f58cb            addps      xmm1, xmm3
00001b6e  0f294c2470        movaps     xmmword ptr [esp + 0x70], xmm1
00001b73  0f28642460        movaps     xmm4, xmmword ptr [esp + 0x60]
00001b78  0f5cf4            subps      xmm6, xmm4
00001b7b  0f285c2450        movaps     xmm3, xmmword ptr [esp + 0x50]
00001b80  0f59f3            mulps      xmm6, xmm3
00001b83  0f58f4            addps      xmm6, xmm4
00001b86  660f6ea19c020000  movd       xmm4, dword ptr [ecx + 0x29c]
00001b8e  660f62ec          punpckldq  xmm5, xmm4
00001b92  660f6cd5          punpcklqdq xmm2, xmm5
00001b96  f30f108198020000  movss      xmm0, dword ptr [ecx + 0x298]
00001b9e  0f28a42490000000  movaps     xmm4, xmmword ptr [esp + 0x90]
00001ba6  0f14e0            unpcklps   xmm4, xmm0
00001ba9  0f288424e0000000  movaps     xmm0, xmmword ptr [esp + 0xe0]
00001bb1  0f16c4            movlhps    xmm0, xmm4
00001bb4  0f298424e0000000  movaps     xmmword ptr [esp + 0xe0], xmm0
00001bbc  0f28e9            movaps     xmm5, xmm1
00001bbf  0f288424c0000000  movaps     xmm0, xmmword ptr [esp + 0xc0]
00001bc7  0f5ce8            subps      xmm5, xmm0
00001bca  0f59ef            mulps      xmm5, xmm7
00001bcd  0f58e8            addps      xmm5, xmm0
00001bd0  0f28fe            movaps     xmm7, xmm6
00001bd3  0f288424f0000000  movaps     xmm0, xmmword ptr [esp + 0xf0]
00001bdb  0f5cf0            subps      xmm6, xmm0
00001bde  0f59f3            mulps      xmm6, xmm3
00001be1  0f58f0            addps      xmm6, xmm0
00001be4  0f28c6            movaps     xmm0, xmm6
00001be7  0f288c2400010000  movaps     xmm1, xmmword ptr [esp + 0x100]
00001bef  0f5cc1            subps      xmm0, xmm1
00001bf2  0f59c3            mulps      xmm0, xmm3
00001bf5  0f28e5            movaps     xmm4, xmm5
00001bf8  0f289c24d0000000  movaps     xmm3, xmmword ptr [esp + 0xd0]
00001c00  0f5ce3            subps      xmm4, xmm3
00001c03  0f59a42480000000  mulps      xmm4, xmmword ptr [esp + 0x80]
00001c0b  0f58e3            addps      xmm4, xmm3
00001c0e  0f29642450        movaps     xmmword ptr [esp + 0x50], xmm4
00001c13  0f58c1            addps      xmm0, xmm1
00001c16  0f297c2460        movaps     xmmword ptr [esp + 0x60], xmm7
00001c1b  f30f11b968010000  movss      dword ptr [ecx + 0x168], xmm7
00001c23  f30f11b16c010000  movss      dword ptr [ecx + 0x16c], xmm6
00001c2b  f30f118170010000  movss      dword ptr [ecx + 0x170], xmm0
00001c33  0f28de            movaps     xmm3, xmm6
00001c36  0fc6df11          shufps     xmm3, xmm7, 0x11
00001c3a  0fc6dfe2          shufps     xmm3, xmm7, 0xe2
00001c3e  0f139990010000    movlps     qword ptr [ecx + 0x190], xmm3
00001c45  f30f16d8          movshdup   xmm3, xmm0
00001c49  f30f119998010000  movss      dword ptr [ecx + 0x198], xmm3
00001c51  660f6f987ccdffff  movdqa     xmm3, xmmword ptr [eax - 0x3284]
00001c59  660f76d3          pcmpeqd    xmm2, xmm3
00001c5d  660f6fbc24b0000000  movdqa     xmm7, xmmword ptr [esp + 0xb0]
00001c66  660f76fb          pcmpeqd    xmm7, xmm3
00001c6a  0f289c24a0010000  movaps     xmm3, xmmword ptr [esp + 0x1a0]
00001c72  0fc69c24b001000055  shufps     xmm3, xmmword ptr [esp + 0x1b0], 0x55
00001c7b  0f288c2480010000  movaps     xmm1, xmmword ptr [esp + 0x180]
00001c83  0fc68c249001000055  shufps     xmm1, xmmword ptr [esp + 0x190], 0x55
00001c8c  0fc6d922          shufps     xmm3, xmm1, 0x22
00001c90  0f288c2460010000  movaps     xmm1, xmmword ptr [esp + 0x160]
00001c98  0fc68c247001000055  shufps     xmm1, xmmword ptr [esp + 0x170], 0x55
00001ca1  0f28a424a0000000  movaps     xmm4, xmmword ptr [esp + 0xa0]
00001ca9  0fc6a4245001000055  shufps     xmm4, xmmword ptr [esp + 0x150], 0x55
00001cb2  0fc6cc22          shufps     xmm1, xmm4, 0x22
00001cb6  0f5cd8            subps      xmm3, xmm0
00001cb9  0f54df            andps      xmm3, xmm7
00001cbc  0f55f8            andnps     xmm7, xmm0
00001cbf  0f56fb            orps       xmm7, xmm3
00001cc2  0f59bc2410010000  mulps      xmm7, xmmword ptr [esp + 0x110]
00001cca  0f28642450        movaps     xmm4, xmmword ptr [esp + 0x50]
00001ccf  0f5ccc            subps      xmm1, xmm4
00001cd2  0f54ca            andps      xmm1, xmm2
00001cd5  0f55d4            andnps     xmm2, xmm4
00001cd8  0f56d1            orps       xmm2, xmm1
00001cdb  0f599424e0000000  mulps      xmm2, xmmword ptr [esp + 0xe0]
00001ce3  f30f117c2428      movss      dword ptr [esp + 0x28], xmm7
00001ce9  f30f16df          movshdup   xmm3, xmm7
00001ced  f30f115c2430      movss      dword ptr [esp + 0x30], xmm3
00001cf3  f30f11542408      movss      dword ptr [esp + 8], xmm2
00001cf9  f30f16da          movshdup   xmm3, xmm2
00001cfd  f30f115c2410      movss      dword ptr [esp + 0x10], xmm3
00001d03  0f28df            movaps     xmm3, xmm7
00001d06  0fc6dfff          shufps     xmm3, xmm7, 0xff
00001d0a  f30f115c2440      movss      dword ptr [esp + 0x40], xmm3
00001d10  0f12ff            movhlps    xmm7, xmm7
00001d13  f30f117c2438      movss      dword ptr [esp + 0x38], xmm7
00001d19  0f28da            movaps     xmm3, xmm2
00001d1c  0fc6daff          shufps     xmm3, xmm2, 0xff
00001d20  f30f115c2420      movss      dword ptr [esp + 0x20], xmm3
00001d26  0f12d2            movhlps    xmm2, xmm2
00001d29  f30f11542418      movss      dword ptr [esp + 0x18], xmm2
00001d2f  d9442428          fld        dword ptr [esp + 0x28]
00001d33  dd4c2428          fisttp     qword ptr [esp + 0x28]
00001d37  d9442430          fld        dword ptr [esp + 0x30]
00001d3b  dd4c2430          fisttp     qword ptr [esp + 0x30]
00001d3f  d9442408          fld        dword ptr [esp + 8]
00001d43  dd4c2408          fisttp     qword ptr [esp + 8]
00001d47  d9442410          fld        dword ptr [esp + 0x10]
00001d4b  dd4c2410          fisttp     qword ptr [esp + 0x10]
00001d4f  d9442440          fld        dword ptr [esp + 0x40]
00001d53  dd4c2440          fisttp     qword ptr [esp + 0x40]
00001d57  d9442438          fld        dword ptr [esp + 0x38]
00001d5b  dd4c2438          fisttp     qword ptr [esp + 0x38]
00001d5f  d9442420          fld        dword ptr [esp + 0x20]
00001d63  dd4c2420          fisttp     qword ptr [esp + 0x20]
00001d67  d9442418          fld        dword ptr [esp + 0x18]
00001d6b  dd4c2418          fisttp     qword ptr [esp + 0x18]
00001d6f  f30f7e4c2428      movq       xmm1, qword ptr [esp + 0x28]
00001d75  f30f7e5c2430      movq       xmm3, qword ptr [esp + 0x30]
00001d7b  660f6ccb          punpcklqdq xmm1, xmm3
00001d7f  f30f7e7c2408      movq       xmm7, qword ptr [esp + 8]
00001d85  f30f7e5c2410      movq       xmm3, qword ptr [esp + 0x10]
00001d8b  660f6cfb          punpcklqdq xmm7, xmm3
00001d8f  660fd4f9          paddq      xmm7, xmm1
00001d93  f30f7e4c2440      movq       xmm1, qword ptr [esp + 0x40]
00001d99  f30f7e5c2438      movq       xmm3, qword ptr [esp + 0x38]
00001d9f  660f6cd9          punpcklqdq xmm3, xmm1
00001da3  f30f7e542420      movq       xmm2, qword ptr [esp + 0x20]
00001da9  f30f7e4c2418      movq       xmm1, qword ptr [esp + 0x18]
00001daf  660f6cca          punpcklqdq xmm1, xmm2
00001db3  660fd4cb          paddq      xmm1, xmm3
00001db7  660fd4cf          paddq      xmm1, xmm7
00001dbb  0f28542460        movaps     xmm2, xmmword ptr [esp + 0x60]
00001dc0  0f28da            movaps     xmm3, xmm2
00001dc3  0f15de            unpckhps   xmm3, xmm6
00001dc6  0f1399b8010000    movlps     qword ptr [ecx + 0x1b8], xmm3
00001dcd  0f28d8            movaps     xmm3, xmm0
00001dd0  660f15d8          unpckhpd   xmm3, xmm0
00001dd4  f30f1199c0010000  movss      dword ptr [ecx + 0x1c0], xmm3
00001ddc  0fc6f2ff          shufps     xmm6, xmm2, 0xff
00001de0  0fc6f2e2          shufps     xmm6, xmm2, 0xe2
00001de4  0f13b1e0010000    movlps     qword ptr [ecx + 0x1e0], xmm6
00001deb  0fc6c0ff          shufps     xmm0, xmm0, 0xff
00001def  f30f1181e8010000  movss      dword ptr [ecx + 0x1e8], xmm0
00001df7  0f28542470        movaps     xmm2, xmmword ptr [esp + 0x70]
00001dfc  f30f119108020000  movss      dword ptr [ecx + 0x208], xmm2
00001e04  f30f11a90c020000  movss      dword ptr [ecx + 0x20c], xmm5
00001e0c  0f28dc            movaps     xmm3, xmm4
00001e0f  f30f11a110020000  movss      dword ptr [ecx + 0x210], xmm4
00001e17  0f28c5            movaps     xmm0, xmm5
00001e1a  0fc6c211          shufps     xmm0, xmm2, 0x11
00001e1e  0fc6c2e2          shufps     xmm0, xmm2, 0xe2
00001e22  0f138130020000    movlps     qword ptr [ecx + 0x230], xmm0
00001e29  f30f16c4          movshdup   xmm0, xmm4
00001e2d  f30f118138020000  movss      dword ptr [ecx + 0x238], xmm0
00001e35  0f28c2            movaps     xmm0, xmm2
00001e38  0f15c5            unpckhps   xmm0, xmm5
00001e3b  0f138158020000    movlps     qword ptr [ecx + 0x258], xmm0
00001e42  0f28c4            movaps     xmm0, xmm4
00001e45  660f15c4          unpckhpd   xmm0, xmm4
00001e49  f30f118160020000  movss      dword ptr [ecx + 0x260], xmm0
00001e51  0fc6eaff          shufps     xmm5, xmm2, 0xff
00001e55  0fc6eae2          shufps     xmm5, xmm2, 0xe2
00001e59  0f13a980020000    movlps     qword ptr [ecx + 0x280], xmm5
00001e60  660f70c1ee        pshufd     xmm0, xmm1, 0xee
00001e65  660fd4c1          paddq      xmm0, xmm1
00001e69  660f7ec6          movd       esi, xmm0
00001e6d  660f70c055        pshufd     xmm0, xmm0, 0x55
00001e72  660f7ec2          movd       edx, xmm0
00001e76  89d0              mov        eax, edx
00001e78  c1f81f            sar        eax, 0x1f
00001e7b  c1e81e            shr        eax, 0x1e
00001e7e  01f0              add        eax, esi
00001e80  0fc6dcff          shufps     xmm3, xmm4, 0xff
00001e84  83d200            adc        edx, 0
00001e87  f30f119988020000  movss      dword ptr [ecx + 0x288], xmm3
00001e8f  0facd002          shrd       eax, edx, 2
00001e93  c1fa02            sar        edx, 2
00001e96  8d65fc            lea        esp, [ebp - 4]
00001e99  5e                pop        esi
00001e9a  5d                pop        ebp
00001e9b  c3                ret        
00001e9c  31c0              xor        eax, eax
00001e9e  31d2              xor        edx, edx
00001ea0  8d65fc            lea        esp, [ebp - 4]
00001ea3  5e                pop        esi
00001ea4  5d                pop        ebp
00001ea5  c3                ret        
