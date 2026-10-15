# APK member: lib/x86/libnbandbooster.so
# Library SHA-256: 159fe53c87c1728336b9ffd53d5f471b91d6cddbc89effb302ce8435a0f64a40
# Function: Java_sofeh_audio_NBandBooster_process; address=0x860; bytes=351
# Decoder mode: default; decoded_bytes=351
00000860  55                push       ebp
00000861  89e5              mov        ebp, esp
00000863  56                push       esi
00000864  83e4f0            and        esp, 0xfffffff0
00000867  83ec30            sub        esp, 0x30
0000086a  e800000000        call       0x86f
0000086f  5e                pop        esi
00000870  81c6c5120000      add        esi, 0x12c5
00000876  8b4510            mov        eax, dword ptr [ebp + 0x10]
00000879  8b4d14            mov        ecx, dword ptr [ebp + 0x14]
0000087c  09c1              or         ecx, eax
0000087e  0f8431010000      je         0x9b5
00000884  8b551c            mov        edx, dword ptr [ebp + 0x1c]
00000887  8b4d18            mov        ecx, dword ptr [ebp + 0x18]
0000088a  660f6ec1          movd       xmm0, ecx
0000088e  660f6eca          movd       xmm1, edx
00000892  660f62c1          punpckldq  xmm0, xmm1
00000896  660fd6442418      movq       qword ptr [esp + 0x18], xmm0
0000089c  df6c2418          fild       qword ptr [esp + 0x18]
000008a0  d95c2410          fstp       dword ptr [esp + 0x10]
000008a4  0f284c2410        movaps     xmm1, xmmword ptr [esp + 0x10]
000008a9  0fc6c900          shufps     xmm1, xmm1, 0
000008ad  f30f105818        movss      xmm3, dword ptr [eax + 0x18]
000008b2  f30f1010          movss      xmm2, dword ptr [eax]
000008b6  f30f104004        movss      xmm0, dword ptr [eax + 4]
000008bb  0f14c3            unpcklps   xmm0, xmm3
000008be  0f5cc8            subps      xmm1, xmm0
000008c1  f30f105814        movss      xmm3, dword ptr [eax + 0x14]
000008c6  0f14d3            unpcklps   xmm2, xmm3
000008c9  0f58865ce9ffff    addps      xmm0, xmmword ptr [esi - 0x16a4]
000008d0  0f59ca            mulps      xmm1, xmm2
000008d3  0f58c1            addps      xmm0, xmm1
000008d6  df6850            fild       qword ptr [eax + 0x50]
000008d9  d95c2414          fstp       dword ptr [esp + 0x14]
000008dd  f30f10481c        movss      xmm1, dword ptr [eax + 0x1c]
000008e2  f30f105808        movss      xmm3, dword ptr [eax + 8]
000008e7  0f14d9            unpcklps   xmm3, xmm1
000008ea  0f28c8            movaps     xmm1, xmm0
000008ed  0f5ccb            subps      xmm1, xmm3
000008f0  0f59ca            mulps      xmm1, xmm2
000008f3  0f58cb            addps      xmm1, xmm3
000008f6  0f28d8            movaps     xmm3, xmm0
000008f9  0f16d9            movlhps    xmm3, xmm1
000008fc  f30f106020        movss      xmm4, dword ptr [eax + 0x20]
00000901  f30f10700c        movss      xmm6, dword ptr [eax + 0xc]
00000906  0f14f4            unpcklps   xmm6, xmm4
00000909  0f28e9            movaps     xmm5, xmm1
0000090c  0f5cee            subps      xmm5, xmm6
0000090f  0f59ea            mulps      xmm5, xmm2
00000912  0f58ee            addps      xmm5, xmm6
00000915  f30f106024        movss      xmm4, dword ptr [eax + 0x24]
0000091a  f30f107010        movss      xmm6, dword ptr [eax + 0x10]
0000091f  0f14f4            unpcklps   xmm6, xmm4
00000922  0f28e5            movaps     xmm4, xmm5
00000925  0f5ce6            subps      xmm4, xmm6
00000928  0f59e2            mulps      xmm4, xmm2
0000092b  0f58e6            addps      xmm4, xmm6
0000092e  0f28d4            movaps     xmm2, xmm4
00000931  0fc6d511          shufps     xmm2, xmm5, 0x11
00000935  0fc6d5e2          shufps     xmm2, xmm5, 0xe2
00000939  0f16ec            movlhps    xmm5, xmm4
0000093c  0fc6dd88          shufps     xmm3, xmm5, 0x88
00000940  0f115804          movups     xmmword ptr [eax + 4], xmm3
00000944  0fc6c811          shufps     xmm1, xmm0, 0x11
00000948  0fc6c8e2          shufps     xmm1, xmm0, 0xe2
0000094c  0f134818          movlps     qword ptr [eax + 0x18], xmm1
00000950  0f135020          movlps     qword ptr [eax + 0x20], xmm2
00000954  f30f10442414      movss      xmm0, dword ptr [esp + 0x14]
0000095a  0fc6c4d4          shufps     xmm0, xmm4, 0xd4
0000095e  0fc6c4f2          shufps     xmm0, xmm4, 0xf2
00000962  0f5cc4            subps      xmm0, xmm4
00000965  f30f104828        movss      xmm1, dword ptr [eax + 0x28]
0000096a  f30f59cc          mulss      xmm1, xmm4
0000096e  f30f114834        movss      dword ptr [eax + 0x34], xmm1
00000973  f20f10502c        movsd      xmm2, qword ptr [eax + 0x2c]
00000978  0f59d0            mulps      xmm2, xmm0
0000097b  0f135038          movlps     qword ptr [eax + 0x38], xmm2
0000097f  0f104040          movups     xmm0, xmmword ptr [eax + 0x40]
00000983  0f114048          movups     xmmword ptr [eax + 0x48], xmm0
00000987  895044            mov        dword ptr [eax + 0x44], edx
0000098a  894840            mov        dword ptr [eax + 0x40], ecx
0000098d  f30f58ca          addss      xmm1, xmm2
00000991  f30f16c2          movshdup   xmm0, xmm2
00000995  f30f58c1          addss      xmm0, xmm1
00000999  f30f11442408      movss      dword ptr [esp + 8], xmm0
0000099f  d9442408          fld        dword ptr [esp + 8]
000009a3  dd4c2408          fisttp     qword ptr [esp + 8]
000009a7  8b442408          mov        eax, dword ptr [esp + 8]
000009ab  8b54240c          mov        edx, dword ptr [esp + 0xc]
000009af  8d65fc            lea        esp, [ebp - 4]
000009b2  5e                pop        esi
000009b3  5d                pop        ebp
000009b4  c3                ret        
000009b5  31c0              xor        eax, eax
000009b7  31d2              xor        edx, edx
000009b9  8d65fc            lea        esp, [ebp - 4]
000009bc  5e                pop        esi
000009bd  5d                pop        ebp
000009be  c3                ret        
