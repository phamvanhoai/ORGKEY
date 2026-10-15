# APK member: lib/x86/libnflanger.so
# Library SHA-256: 358a7d5d8ff8336f0dff81448c92ca52cbb50537d20bc26689308d57c3842482
# Function: Java_sofeh_audio_NFlanger_open; address=0x820; bytes=85
# Decoder mode: default; decoded_bytes=85
00000820  55                push       ebp
00000821  89e5              mov        ebp, esp
00000823  53                push       ebx
00000824  83e4f0            and        esp, 0xfffffff0
00000827  83ec20            sub        esp, 0x20
0000082a  e800000000        call       0x82f
0000082f  5b                pop        ebx
00000830  81c335190000      add        ebx, 0x1935
00000836  8b4510            mov        eax, dword ptr [ebp + 0x10]
00000839  8b4d14            mov        ecx, dword ptr [ebp + 0x14]
0000083c  09c1              or         ecx, eax
0000083e  7408              je         0x848
00000840  890424            mov        dword ptr [esp], eax
00000843  e808080000        call       0x1050
00000848  c744240434080000  mov        dword ptr [esp + 4], 0x834
00000850  c7042401000000    mov        dword ptr [esp], 1
00000857  e804080000        call       0x1060
0000085c  0f28839ce3ffff    movaps     xmm0, xmmword ptr [ebx - 0x1c64]
00000863  0f1100            movups     xmmword ptr [eax], xmm0
00000866  c7401080000000    mov        dword ptr [eax + 0x10], 0x80
0000086d  31d2              xor        edx, edx
0000086f  8d65fc            lea        esp, [ebp - 4]
00000872  5b                pop        ebx
00000873  5d                pop        ebp
00000874  c3                ret        
