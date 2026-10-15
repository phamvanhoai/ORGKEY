# APK member: lib/x86/libnpitchshift.so
# Library SHA-256: 4b9d17ea2c347096a6285494e18c00ebbd50a630c337141ab53c3b787fc2b7fb
# Function: Java_sofeh_audio_NPitchShift_open; address=0x22f0; bytes=127
# Decoder mode: default; decoded_bytes=127
000022f0  55                push       ebp
000022f1  89e5              mov        ebp, esp
000022f3  53                push       ebx
000022f4  57                push       edi
000022f5  56                push       esi
000022f6  83e4f0            and        esp, 0xfffffff0
000022f9  83ec10            sub        esp, 0x10
000022fc  e800000000        call       0x2301
00002301  5b                pop        ebx
00002302  81c3b3130000      add        ebx, 0x13b3
00002308  8b4510            mov        eax, dword ptr [ebp + 0x10]
0000230b  8b4d14            mov        ecx, dword ptr [ebp + 0x14]
0000230e  09c1              or         ecx, eax
00002310  740c              je         0x231e
00002312  83ec0c            sub        esp, 0xc
00002315  50                push       eax
00002316  e845020000        call       0x2560
0000231b  83c410            add        esp, 0x10
0000231e  83ec0c            sub        esp, 0xc
00002321  6818600100        push       0x16018
00002326  e815020000        call       0x2540
0000232b  83c410            add        esp, 0x10
0000232e  89c6              mov        esi, eax
00002330  8db808b00000      lea        edi, [eax + 0xb008]
00002336  c7801460010000000000  mov        dword ptr [eax + 0x16014], 0
00002340  83ec04            sub        esp, 4
00002343  6808900000        push       0x9008
00002348  6a00              push       0
0000234a  50                push       eax
0000234b  e800020000        call       0x2550
00002350  83c40c            add        esp, 0xc
00002353  680c900000        push       0x900c
00002358  6a00              push       0
0000235a  57                push       edi
0000235b  e8f0010000        call       0x2550
00002360  83c410            add        esp, 0x10
00002363  89f0              mov        eax, esi
00002365  31d2              xor        edx, edx
00002367  8d65f4            lea        esp, [ebp - 0xc]
0000236a  5e                pop        esi
0000236b  5f                pop        edi
0000236c  5b                pop        ebx
0000236d  5d                pop        ebp
0000236e  c3                ret        
