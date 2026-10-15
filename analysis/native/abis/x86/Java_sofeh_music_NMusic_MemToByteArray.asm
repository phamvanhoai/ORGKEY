# APK member: lib/x86/libnmusic.so
# Library SHA-256: 051762c43873d3b1e8d0eb96eb91cd82ffbc3e9ca211006f6977da7afa2b80a2
# Function: Java_sofeh_music_NMusic_MemToByteArray; address=0x3c70; bytes=79
# Decoder mode: default; decoded_bytes=79
00003c70  55                push       ebp
00003c71  89e5              mov        ebp, esp
00003c73  53                push       ebx
00003c74  57                push       edi
00003c75  56                push       esi
00003c76  83e4f0            and        esp, 0xfffffff0
00003c79  83ec10            sub        esp, 0x10
00003c7c  e800000000        call       0x3c81
00003c81  5b                pop        ebx
00003c82  81c323130000      add        ebx, 0x1323
00003c88  8b7d20            mov        edi, dword ptr [ebp + 0x20]
00003c8b  8b7508            mov        esi, dword ptr [ebp + 8]
00003c8e  8b06              mov        eax, dword ptr [esi]
00003c90  83ec08            sub        esp, 8
00003c93  ff7510            push       dword ptr [ebp + 0x10]
00003c96  56                push       esi
00003c97  ff9098030000      call       dword ptr [eax + 0x398]
00003c9d  83c410            add        esp, 0x10
00003ca0  8b0e              mov        ecx, dword ptr [esi]
00003ca2  83ec0c            sub        esp, 0xc
00003ca5  50                push       eax
00003ca6  57                push       edi
00003ca7  ff7518            push       dword ptr [ebp + 0x18]
00003caa  ff7514            push       dword ptr [ebp + 0x14]
00003cad  56                push       esi
00003cae  ff9140030000      call       dword ptr [ecx + 0x340]
00003cb4  83c420            add        esp, 0x20
00003cb7  8d65f4            lea        esp, [ebp - 0xc]
00003cba  5e                pop        esi
00003cbb  5f                pop        edi
00003cbc  5b                pop        ebx
00003cbd  5d                pop        ebp
00003cbe  c3                ret        
