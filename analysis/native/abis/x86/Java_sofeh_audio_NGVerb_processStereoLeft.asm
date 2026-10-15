# APK member: lib/x86/libngverb.so
# Library SHA-256: 8412ca981b3be45bccdf4d07aff9e3554c36594cb309f6bb73e3c87705155f48
# Function: Java_sofeh_audio_NGVerb_processStereoLeft; address=0x2960; bytes=349
# Decoder mode: default; decoded_bytes=349
00002960  55                push       ebp
00002961  89e5              mov        ebp, esp
00002963  53                push       ebx
00002964  57                push       edi
00002965  56                push       esi
00002966  83e4f0            and        esp, 0xfffffff0
00002969  83ec50            sub        esp, 0x50
0000296c  e800000000        call       0x2971
00002971  5b                pop        ebx
00002972  81c3eb190000      add        ebx, 0x19eb
00002978  8b4d10            mov        ecx, dword ptr [ebp + 0x10]
0000297b  8b4514            mov        eax, dword ptr [ebp + 0x14]
0000297e  09c8              or         eax, ecx
00002980  0f842b010000      je         0x2ab1
00002986  8b4524            mov        eax, dword ptr [ebp + 0x24]
00002989  8b5520            mov        edx, dword ptr [ebp + 0x20]
0000298c  f30f104518        movss      xmm0, dword ptr [ebp + 0x18]
00002991  f30f104d1c        movss      xmm1, dword ptr [ebp + 0x1c]
00002996  0f14c1            unpcklps   xmm0, xmm1
00002999  0f13442440        movlps     qword ptr [esp + 0x40], xmm0
0000299e  df6c2440          fild       qword ptr [esp + 0x40]
000029a2  d95c2434          fstp       dword ptr [esp + 0x34]
000029a6  f30f10835cc8ffff  movss      xmm0, dword ptr [ebx - 0x37a4]
000029ae  f30f104c2434      movss      xmm1, dword ptr [esp + 0x34]
000029b4  f30f59c8          mulss      xmm1, xmm0
000029b8  8bb3e8ffffff      mov        esi, dword ptr [ebx - 0x18]
000029be  8974242c          mov        dword ptr [esp + 0x2c], esi
000029c2  f30f110e          movss      dword ptr [esi], xmm1
000029c6  660f6ed2          movd       xmm2, edx
000029ca  660f6ed8          movd       xmm3, eax
000029ce  660f62d3          punpckldq  xmm2, xmm3
000029d2  660fd6542438      movq       qword ptr [esp + 0x38], xmm2
000029d8  df6c2438          fild       qword ptr [esp + 0x38]
000029dc  d95c2430          fstp       dword ptr [esp + 0x30]
000029e0  f30f59442430      mulss      xmm0, dword ptr [esp + 0x30]
000029e6  8b4528            mov        eax, dword ptr [ebp + 0x28]
000029e9  0f57d2            xorps      xmm2, xmm2
000029ec  f30f2ad0          cvtsi2ss   xmm2, eax
000029f0  8b83ecffffff      mov        eax, dword ptr [ebx - 0x14]
000029f6  89442424          mov        dword ptr [esp + 0x24], eax
000029fa  f30f5e9388c8ffff  divss      xmm2, dword ptr [ebx - 0x3778]
00002a02  f30f1100          movss      dword ptr [eax], xmm0
00002a06  8bb3f0ffffff      mov        esi, dword ptr [ebx - 0x10]
00002a0c  f30f1116          movss      dword ptr [esi], xmm2
00002a10  f30f58c1          addss      xmm0, xmm1
00002a14  8bbbf4ffffff      mov        edi, dword ptr [ebx - 0xc]
00002a1a  893c24            mov        dword ptr [esp], edi
00002a1d  8b93f8ffffff      mov        edx, dword ptr [ebx - 8]
00002a23  89542428          mov        dword ptr [esp + 0x28], edx
00002a27  e814f8ffff        call       0x2240
00002a2c  f30f100e          movss      xmm1, dword ptr [esi]
00002a30  f30f108354c8ffff  movss      xmm0, dword ptr [ebx - 0x37ac]
00002a38  f30f5cc1          subss      xmm0, xmm1
00002a3c  f30f1017          movss      xmm2, dword ptr [edi]
00002a40  f30f59d1          mulss      xmm2, xmm1
00002a44  8b442424          mov        eax, dword ptr [esp + 0x24]
00002a48  f30f1018          movss      xmm3, dword ptr [eax]
00002a4c  f30f59d8          mulss      xmm3, xmm0
00002a50  f30f58da          addss      xmm3, xmm2
00002a54  f30f109384c8ffff  movss      xmm2, dword ptr [ebx - 0x377c]
00002a5c  f30f59da          mulss      xmm3, xmm2
00002a60  f30f115c2418      movss      dword ptr [esp + 0x18], xmm3
00002a66  d9442418          fld        dword ptr [esp + 0x18]
00002a6a  dd4c2418          fisttp     qword ptr [esp + 0x18]
00002a6e  8b442418          mov        eax, dword ptr [esp + 0x18]
00002a72  8b4c241c          mov        ecx, dword ptr [esp + 0x1c]
00002a76  8b93fcffffff      mov        edx, dword ptr [ebx - 4]
00002a7c  894a04            mov        dword ptr [edx + 4], ecx
00002a7f  8b4c2428          mov        ecx, dword ptr [esp + 0x28]
00002a83  f30f5909          mulss      xmm1, dword ptr [ecx]
00002a87  8b4c242c          mov        ecx, dword ptr [esp + 0x2c]
00002a8b  f30f5901          mulss      xmm0, dword ptr [ecx]
00002a8f  8902              mov        dword ptr [edx], eax
00002a91  f30f58c1          addss      xmm0, xmm1
00002a95  f30f59c2          mulss      xmm0, xmm2
00002a99  f30f11442410      movss      dword ptr [esp + 0x10], xmm0
00002a9f  d9442410          fld        dword ptr [esp + 0x10]
00002aa3  dd4c2410          fisttp     qword ptr [esp + 0x10]
00002aa7  8b442410          mov        eax, dword ptr [esp + 0x10]
00002aab  8b542414          mov        edx, dword ptr [esp + 0x14]
00002aaf  eb04              jmp        0x2ab5
00002ab1  31c0              xor        eax, eax
00002ab3  31d2              xor        edx, edx
00002ab5  8d65f4            lea        esp, [ebp - 0xc]
00002ab8  5e                pop        esi
00002ab9  5f                pop        edi
00002aba  5b                pop        ebx
00002abb  5d                pop        ebp
00002abc  c3                ret        
