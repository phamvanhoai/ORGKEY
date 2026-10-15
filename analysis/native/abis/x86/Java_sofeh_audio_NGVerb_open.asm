# APK member: lib/x86/libngverb.so
# Library SHA-256: 8412ca981b3be45bccdf4d07aff9e3554c36594cb309f6bb73e3c87705155f48
# Function: Java_sofeh_audio_NGVerb_open; address=0x20d0; bytes=189
# Decoder mode: default; decoded_bytes=189
000020d0  55                push       ebp
000020d1  89e5              mov        ebp, esp
000020d3  53                push       ebx
000020d4  56                push       esi
000020d5  83e4f0            and        esp, 0xfffffff0
000020d8  83ec30            sub        esp, 0x30
000020db  e800000000        call       0x20e0
000020e0  5b                pop        ebx
000020e1  81c37c220000      add        ebx, 0x227c
000020e7  8b4510            mov        eax, dword ptr [ebp + 0x10]
000020ea  f30f107d38        movss      xmm7, dword ptr [ebp + 0x38]
000020ef  f30f107534        movss      xmm6, dword ptr [ebp + 0x34]
000020f4  f30f106d30        movss      xmm5, dword ptr [ebp + 0x30]
000020f9  f30f10652c        movss      xmm4, dword ptr [ebp + 0x2c]
000020fe  f30f105d28        movss      xmm3, dword ptr [ebp + 0x28]
00002103  f30f105524        movss      xmm2, dword ptr [ebp + 0x24]
00002108  f30f104d20        movss      xmm1, dword ptr [ebp + 0x20]
0000210d  f30f10451c        movss      xmm0, dword ptr [ebp + 0x1c]
00002112  8b7518            mov        esi, dword ptr [ebp + 0x18]
00002115  8b4d14            mov        ecx, dword ptr [ebp + 0x14]
00002118  09c1              or         ecx, eax
0000211a  7430              je         0x214c
0000211c  890424            mov        dword ptr [esp], eax
0000211f  e89c100000        call       0x31c0
00002124  f30f107d38        movss      xmm7, dword ptr [ebp + 0x38]
00002129  f30f107534        movss      xmm6, dword ptr [ebp + 0x34]
0000212e  f30f106d30        movss      xmm5, dword ptr [ebp + 0x30]
00002133  f30f10652c        movss      xmm4, dword ptr [ebp + 0x2c]
00002138  f30f105d28        movss      xmm3, dword ptr [ebp + 0x28]
0000213d  f30f105524        movss      xmm2, dword ptr [ebp + 0x24]
00002142  f30f104d20        movss      xmm1, dword ptr [ebp + 0x20]
00002147  f30f10451c        movss      xmm0, dword ptr [ebp + 0x1c]
0000214c  f30f117c2420      movss      dword ptr [esp + 0x20], xmm7
00002152  f30f1174241c      movss      dword ptr [esp + 0x1c], xmm6
00002158  f30f116c2418      movss      dword ptr [esp + 0x18], xmm5
0000215e  f30f11642414      movss      dword ptr [esp + 0x14], xmm4
00002164  f30f115c2410      movss      dword ptr [esp + 0x10], xmm3
0000216a  f30f1154240c      movss      dword ptr [esp + 0xc], xmm2
00002170  f30f114c2408      movss      dword ptr [esp + 8], xmm1
00002176  f30f11442404      movss      dword ptr [esp + 4], xmm0
0000217c  893424            mov        dword ptr [esp], esi
0000217f  e8bc0f0000        call       0x3140
00002184  31d2              xor        edx, edx
00002186  8d65f8            lea        esp, [ebp - 8]
00002189  5e                pop        esi
0000218a  5b                pop        ebx
0000218b  5d                pop        ebp
0000218c  c3                ret        
