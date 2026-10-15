# APK member: lib/x86/libnmusic.so
# Library SHA-256: 051762c43873d3b1e8d0eb96eb91cd82ffbc3e9ca211006f6977da7afa2b80a2
# Function: Java_sofeh_music_NMusic_GetMem; address=0x3be0; bytes=71
# Decoder mode: default; decoded_bytes=71
00003be0  55                push       ebp
00003be1  89e5              mov        ebp, esp
00003be3  53                push       ebx
00003be4  57                push       edi
00003be5  56                push       esi
00003be6  83e4f0            and        esp, 0xfffffff0
00003be9  83ec10            sub        esp, 0x10
00003bec  e800000000        call       0x3bf1
00003bf1  5b                pop        ebx
00003bf2  81c3b3130000      add        ebx, 0x13b3
00003bf8  8b7d10            mov        edi, dword ptr [ebp + 0x10]
00003bfb  8b4508            mov        eax, dword ptr [ebp + 8]
00003bfe  8b00              mov        eax, dword ptr [eax]
00003c00  8bb094030000      mov        esi, dword ptr [eax + 0x394]
00003c06  83ec0c            sub        esp, 0xc
00003c09  57                push       edi
00003c0a  e8f1010000        call       0x3e00
00003c0f  83c410            add        esp, 0x10
00003c12  ff7514            push       dword ptr [ebp + 0x14]
00003c15  57                push       edi
00003c16  50                push       eax
00003c17  ff7508            push       dword ptr [ebp + 8]
00003c1a  ffd6              call       esi
00003c1c  83c410            add        esp, 0x10
00003c1f  8d65f4            lea        esp, [ebp - 0xc]
00003c22  5e                pop        esi
00003c23  5f                pop        edi
00003c24  5b                pop        ebx
00003c25  5d                pop        ebp
00003c26  c3                ret        
