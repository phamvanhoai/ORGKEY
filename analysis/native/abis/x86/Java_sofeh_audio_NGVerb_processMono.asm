# APK member: lib/x86/libngverb.so
# Library SHA-256: 8412ca981b3be45bccdf4d07aff9e3554c36594cb309f6bb73e3c87705155f48
# Function: Java_sofeh_audio_NGVerb_processMono; address=0x2af0; bytes=234
# Decoder mode: default; decoded_bytes=234
00002af0  55                push       ebp
00002af1  89e5              mov        ebp, esp
00002af3  53                push       ebx
00002af4  57                push       edi
00002af5  56                push       esi
00002af6  83e4f0            and        esp, 0xfffffff0
00002af9  83ec30            sub        esp, 0x30
00002afc  e800000000        call       0x2b01
00002b01  5b                pop        ebx
00002b02  81c35b180000      add        ebx, 0x185b
00002b08  8b4d10            mov        ecx, dword ptr [ebp + 0x10]
00002b0b  8b4514            mov        eax, dword ptr [ebp + 0x14]
00002b0e  09c8              or         eax, ecx
00002b10  0f84b8000000      je         0x2bce
00002b16  8b4520            mov        eax, dword ptr [ebp + 0x20]
00002b19  f30f104518        movss      xmm0, dword ptr [ebp + 0x18]
00002b1e  f30f104d1c        movss      xmm1, dword ptr [ebp + 0x1c]
00002b23  0f14c1            unpcklps   xmm0, xmm1
00002b26  0f13442420        movlps     qword ptr [esp + 0x20], xmm0
00002b2b  df6c2420          fild       qword ptr [esp + 0x20]
00002b2f  d95c241c          fstp       dword ptr [esp + 0x1c]
00002b33  f30f1044241c      movss      xmm0, dword ptr [esp + 0x1c]
00002b39  f30f59835cc8ffff  mulss      xmm0, dword ptr [ebx - 0x37a4]
00002b41  8b93e8ffffff      mov        edx, dword ptr [ebx - 0x18]
00002b47  89542418          mov        dword ptr [esp + 0x18], edx
00002b4b  f30f1102          movss      dword ptr [edx], xmm0
00002b4f  0f57c9            xorps      xmm1, xmm1
00002b52  f30f2ac8          cvtsi2ss   xmm1, eax
00002b56  f30f5e8b88c8ffff  divss      xmm1, dword ptr [ebx - 0x3778]
00002b5e  8bbbf0ffffff      mov        edi, dword ptr [ebx - 0x10]
00002b64  f30f110f          movss      dword ptr [edi], xmm1
00002b68  8b83f4ffffff      mov        eax, dword ptr [ebx - 0xc]
00002b6e  89442414          mov        dword ptr [esp + 0x14], eax
00002b72  890424            mov        dword ptr [esp], eax
00002b75  8bb3f8ffffff      mov        esi, dword ptr [ebx - 8]
00002b7b  89f2              mov        edx, esi
00002b7d  e8bef6ffff        call       0x2240
00002b82  f30f1007          movss      xmm0, dword ptr [edi]
00002b86  f30f108b54c8ffff  movss      xmm1, dword ptr [ebx - 0x37ac]
00002b8e  f30f1016          movss      xmm2, dword ptr [esi]
00002b92  8b442414          mov        eax, dword ptr [esp + 0x14]
00002b96  f30f5810          addss      xmm2, dword ptr [eax]
00002b9a  f30f5cc8          subss      xmm1, xmm0
00002b9e  f30f59d0          mulss      xmm2, xmm0
00002ba2  8b442418          mov        eax, dword ptr [esp + 0x18]
00002ba6  f30f5908          mulss      xmm1, dword ptr [eax]
00002baa  f30f58ca          addss      xmm1, xmm2
00002bae  f30f598b84c8ffff  mulss      xmm1, dword ptr [ebx - 0x377c]
00002bb6  f30f114c2408      movss      dword ptr [esp + 8], xmm1
00002bbc  d9442408          fld        dword ptr [esp + 8]
00002bc0  dd4c2408          fisttp     qword ptr [esp + 8]
00002bc4  8b442408          mov        eax, dword ptr [esp + 8]
00002bc8  8b54240c          mov        edx, dword ptr [esp + 0xc]
00002bcc  eb04              jmp        0x2bd2
00002bce  31c0              xor        eax, eax
00002bd0  31d2              xor        edx, edx
00002bd2  8d65f4            lea        esp, [ebp - 0xc]
00002bd5  5e                pop        esi
00002bd6  5f                pop        edi
00002bd7  5b                pop        ebx
00002bd8  5d                pop        ebp
00002bd9  c3                ret        
