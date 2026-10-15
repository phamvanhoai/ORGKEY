# APK member: lib/x86/libnequalizer.so
# Library SHA-256: 0c7dfba2ccd6a4c88a74500c5b3ecac4f88ff3e4abb681dafc117af7ee94b44b
# Function: Java_sofeh_audio_NEqualizer_open; address=0x920; bytes=82
# Decoder mode: default; decoded_bytes=82
00000920  55                push       ebp
00000921  89e5              mov        ebp, esp
00000923  53                push       ebx
00000924  83e4f0            and        esp, 0xfffffff0
00000927  83ec20            sub        esp, 0x20
0000092a  e800000000        call       0x92f
0000092f  5b                pop        ebx
00000930  81c3c52e0000      add        ebx, 0x2ec5
00000936  8b4510            mov        eax, dword ptr [ebp + 0x10]
00000939  8b4d14            mov        ecx, dword ptr [ebp + 0x14]
0000093c  09c1              or         ecx, eax
0000093e  7408              je         0x948
00000940  890424            mov        dword ptr [esp], eax
00000943  e8a81d0000        call       0x26f0
00000948  c7442404a0020000  mov        dword ptr [esp + 4], 0x2a0
00000950  c7042401000000    mov        dword ptr [esp], 1
00000957  e8a41d0000        call       0x2700
0000095c  0f28836ccdffff    movaps     xmm0, xmmword ptr [ebx - 0x3294]
00000963  0f1100            movups     xmmword ptr [eax], xmm0
00000966  0f114010          movups     xmmword ptr [eax + 0x10], xmm0
0000096a  31d2              xor        edx, edx
0000096c  8d65fc            lea        esp, [ebp - 4]
0000096f  5b                pop        ebx
00000970  5d                pop        ebp
00000971  c3                ret        
