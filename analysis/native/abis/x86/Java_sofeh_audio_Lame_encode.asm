# APK member: lib/x86/libnlame.so
# Library SHA-256: f3c34506fef42c63a66723f1c2244513e7809249892d190415173b1669bbf30f
# Function: Java_sofeh_audio_Lame_encode; address=0x3c7f0; bytes=230
# Decoder mode: default; decoded_bytes=230
0003c7f0  55                push       ebp
0003c7f1  89e5              mov        ebp, esp
0003c7f3  53                push       ebx
0003c7f4  57                push       edi
0003c7f5  56                push       esi
0003c7f6  83e4f0            and        esp, 0xfffffff0
0003c7f9  83ec20            sub        esp, 0x20
0003c7fc  e800000000        call       0x3c801
0003c801  5b                pop        ebx
0003c802  81c31b220000      add        ebx, 0x221b
0003c808  8b7508            mov        esi, dword ptr [ebp + 8]
0003c80b  8b06              mov        eax, dword ptr [esi]
0003c80d  83ec04            sub        esp, 4
0003c810  6a00              push       0
0003c812  ff7510            push       dword ptr [ebp + 0x10]
0003c815  56                push       esi
0003c816  ff90e8020000      call       dword ptr [eax + 0x2e8]
0003c81c  83c410            add        esp, 0x10
0003c81f  89442418          mov        dword ptr [esp + 0x18], eax
0003c823  8b06              mov        eax, dword ptr [esi]
0003c825  83ec04            sub        esp, 4
0003c828  6a00              push       0
0003c82a  ff7514            push       dword ptr [ebp + 0x14]
0003c82d  56                push       esi
0003c82e  ff90e8020000      call       dword ptr [eax + 0x2e8]
0003c834  83c410            add        esp, 0x10
0003c837  89c7              mov        edi, eax
0003c839  89442410          mov        dword ptr [esp + 0x10], eax
0003c83d  8b06              mov        eax, dword ptr [esi]
0003c83f  83ec08            sub        esp, 8
0003c842  ff751c            push       dword ptr [ebp + 0x1c]
0003c845  56                push       esi
0003c846  ff90ac020000      call       dword ptr [eax + 0x2ac]
0003c84c  83c410            add        esp, 0x10
0003c84f  8944240c          mov        dword ptr [esp + 0xc], eax
0003c853  8b06              mov        eax, dword ptr [esi]
0003c855  83ec04            sub        esp, 4
0003c858  6a00              push       0
0003c85a  ff751c            push       dword ptr [ebp + 0x1c]
0003c85d  56                push       esi
0003c85e  ff90e0020000      call       dword ptr [eax + 0x2e0]
0003c864  83c410            add        esp, 0x10
0003c867  89442414          mov        dword ptr [esp + 0x14], eax
0003c86b  83ec08            sub        esp, 8
0003c86e  ff742414          push       dword ptr [esp + 0x14]
0003c872  50                push       eax
0003c873  ff7518            push       dword ptr [ebp + 0x18]
0003c876  57                push       edi
0003c877  8b7c2430          mov        edi, dword ptr [esp + 0x30]
0003c87b  57                push       edi
0003c87c  ffb3fc1c0100      push       dword ptr [ebx + 0x11cfc]
0003c882  e8b90a0000        call       0x3d340
0003c887  83c420            add        esp, 0x20
0003c88a  8944240c          mov        dword ptr [esp + 0xc], eax
0003c88e  8b06              mov        eax, dword ptr [esi]
0003c890  6a00              push       0
0003c892  57                push       edi
0003c893  ff7510            push       dword ptr [ebp + 0x10]
0003c896  56                push       esi
0003c897  ff9008030000      call       dword ptr [eax + 0x308]
0003c89d  83c410            add        esp, 0x10
0003c8a0  8b06              mov        eax, dword ptr [esi]
0003c8a2  6a00              push       0
0003c8a4  ff742414          push       dword ptr [esp + 0x14]
0003c8a8  ff7514            push       dword ptr [ebp + 0x14]
0003c8ab  56                push       esi
0003c8ac  ff9008030000      call       dword ptr [eax + 0x308]
0003c8b2  83c410            add        esp, 0x10
0003c8b5  8b06              mov        eax, dword ptr [esi]
0003c8b7  6a00              push       0
0003c8b9  ff742418          push       dword ptr [esp + 0x18]
0003c8bd  ff751c            push       dword ptr [ebp + 0x1c]
0003c8c0  56                push       esi
0003c8c1  ff9000030000      call       dword ptr [eax + 0x300]
0003c8c7  83c410            add        esp, 0x10
0003c8ca  8b44240c          mov        eax, dword ptr [esp + 0xc]
0003c8ce  8d65f4            lea        esp, [ebp - 0xc]
0003c8d1  5e                pop        esi
0003c8d2  5f                pop        edi
0003c8d3  5b                pop        ebx
0003c8d4  5d                pop        ebp
0003c8d5  c3                ret        
