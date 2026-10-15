# APK member: lib/x86/libngverb.so
# Library SHA-256: 8412ca981b3be45bccdf4d07aff9e3554c36594cb309f6bb73e3c87705155f48
# Function: Java_sofeh_audio_NGVerb_processRight; address=0x28b0; bytes=165
# Decoder mode: default; decoded_bytes=165
000028b0  55                push       ebp
000028b1  89e5              mov        ebp, esp
000028b3  53                push       ebx
000028b4  83e4f0            and        esp, 0xfffffff0
000028b7  83ec40            sub        esp, 0x40
000028ba  e800000000        call       0x28bf
000028bf  5b                pop        ebx
000028c0  81c39d1a0000      add        ebx, 0x1a9d
000028c6  8b4514            mov        eax, dword ptr [ebp + 0x14]
000028c9  8b4d10            mov        ecx, dword ptr [ebp + 0x10]
000028cc  658b1514000000    mov        edx, dword ptr gs:[0x14]
000028d3  89542430          mov        dword ptr [esp + 0x30], edx
000028d7  09c8              or         eax, ecx
000028d9  745e              je         0x2939
000028db  f30f104518        movss      xmm0, dword ptr [ebp + 0x18]
000028e0  f30f104d1c        movss      xmm1, dword ptr [ebp + 0x1c]
000028e5  0f14c1            unpcklps   xmm0, xmm1
000028e8  0f13442420        movlps     qword ptr [esp + 0x20], xmm0
000028ed  df6c2420          fild       qword ptr [esp + 0x20]
000028f1  d95c241c          fstp       dword ptr [esp + 0x1c]
000028f5  f30f1044241c      movss      xmm0, dword ptr [esp + 0x1c]
000028fb  f30f59835cc8ffff  mulss      xmm0, dword ptr [ebx - 0x37a4]
00002903  8d44242c          lea        eax, [esp + 0x2c]
00002907  890424            mov        dword ptr [esp], eax
0000290a  8d542428          lea        edx, [esp + 0x28]
0000290e  e82df9ffff        call       0x2240
00002913  f30f1044242c      movss      xmm0, dword ptr [esp + 0x2c]
00002919  f30f598384c8ffff  mulss      xmm0, dword ptr [ebx - 0x377c]
00002921  f30f11442410      movss      dword ptr [esp + 0x10], xmm0
00002927  d9442410          fld        dword ptr [esp + 0x10]
0000292b  dd4c2410          fisttp     qword ptr [esp + 0x10]
0000292f  8b442410          mov        eax, dword ptr [esp + 0x10]
00002933  8b542414          mov        edx, dword ptr [esp + 0x14]
00002937  eb04              jmp        0x293d
00002939  31c0              xor        eax, eax
0000293b  31d2              xor        edx, edx
0000293d  658b0d14000000    mov        ecx, dword ptr gs:[0x14]
00002944  3b4c2430          cmp        ecx, dword ptr [esp + 0x30]
00002948  7506              jne        0x2950
0000294a  8d65fc            lea        esp, [ebp - 4]
0000294d  5b                pop        ebx
0000294e  5d                pop        ebp
0000294f  c3                ret        
00002950  e80b090000        call       0x3260
