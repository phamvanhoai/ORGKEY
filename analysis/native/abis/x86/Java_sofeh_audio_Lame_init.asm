# APK member: lib/x86/libnlame.so
# Library SHA-256: f3c34506fef42c63a66723f1c2244513e7809249892d190415173b1669bbf30f
# Function: Java_sofeh_audio_Lame_init; address=0x3c730; bytes=184
# Decoder mode: default; decoded_bytes=184
0003c730  55                push       ebp
0003c731  89e5              mov        ebp, esp
0003c733  53                push       ebx
0003c734  57                push       edi
0003c735  56                push       esi
0003c736  83e4f0            and        esp, 0xfffffff0
0003c739  83ec10            sub        esp, 0x10
0003c73c  e800000000        call       0x3c741
0003c741  5b                pop        ebx
0003c742  81c3db220000      add        ebx, 0x22db
0003c748  8b7d14            mov        edi, dword ptr [ebp + 0x14]
0003c74b  8b7510            mov        esi, dword ptr [ebp + 0x10]
0003c74e  8b83fc1c0100      mov        eax, dword ptr [ebx + 0x11cfc]
0003c754  85c0              test       eax, eax
0003c756  7412              je         0x3c76a
0003c758  890424            mov        dword ptr [esp], eax
0003c75b  e8000c0000        call       0x3d360
0003c760  c783fc1c010000000000  mov        dword ptr [ebx + 0x11cfc], 0
0003c76a  e8010c0000        call       0x3d370
0003c76f  8983fc1c0100      mov        dword ptr [ebx + 0x11cfc], eax
0003c775  89742404          mov        dword ptr [esp + 4], esi
0003c779  890424            mov        dword ptr [esp], eax
0003c77c  e82f0c0000        call       0x3d3b0
0003c781  8b83fc1c0100      mov        eax, dword ptr [ebx + 0x11cfc]
0003c787  897c2404          mov        dword ptr [esp + 4], edi
0003c78b  890424            mov        dword ptr [esp], eax
0003c78e  e82d0c0000        call       0x3d3c0
0003c793  8b83fc1c0100      mov        eax, dword ptr [ebx + 0x11cfc]
0003c799  8b4d18            mov        ecx, dword ptr [ebp + 0x18]
0003c79c  894c2404          mov        dword ptr [esp + 4], ecx
0003c7a0  890424            mov        dword ptr [esp], eax
0003c7a3  e8280c0000        call       0x3d3d0
0003c7a8  8b83fc1c0100      mov        eax, dword ptr [ebx + 0x11cfc]
0003c7ae  8b4d1c            mov        ecx, dword ptr [ebp + 0x1c]
0003c7b1  894c2404          mov        dword ptr [esp + 4], ecx
0003c7b5  890424            mov        dword ptr [esp], eax
0003c7b8  e8e3040000        call       0x3cca0
0003c7bd  8b83fc1c0100      mov        eax, dword ptr [ebx + 0x11cfc]
0003c7c3  8b4d20            mov        ecx, dword ptr [ebp + 0x20]
0003c7c6  894c2404          mov        dword ptr [esp + 4], ecx
0003c7ca  890424            mov        dword ptr [esp], eax
0003c7cd  e80e0c0000        call       0x3d3e0
0003c7d2  8b83fc1c0100      mov        eax, dword ptr [ebx + 0x11cfc]
0003c7d8  890424            mov        dword ptr [esp], eax
0003c7db  e8100b0000        call       0x3d2f0
0003c7e0  8d65f4            lea        esp, [ebp - 0xc]
0003c7e3  5e                pop        esi
0003c7e4  5f                pop        edi
0003c7e5  5b                pop        ebx
0003c7e6  5d                pop        ebp
0003c7e7  c3                ret        
