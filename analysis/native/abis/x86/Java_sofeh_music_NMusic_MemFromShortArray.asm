# APK member: lib/x86/libnmusic.so
# Library SHA-256: 051762c43873d3b1e8d0eb96eb91cd82ffbc3e9ca211006f6977da7afa2b80a2
# Function: Java_sofeh_music_NMusic_MemFromShortArray; address=0x3d60; bytes=79
# Decoder mode: default; decoded_bytes=79
00003d60  55                push       ebp
00003d61  89e5              mov        ebp, esp
00003d63  53                push       ebx
00003d64  57                push       edi
00003d65  56                push       esi
00003d66  83e4f0            and        esp, 0xfffffff0
00003d69  83ec10            sub        esp, 0x10
00003d6c  e800000000        call       0x3d71
00003d71  5b                pop        ebx
00003d72  81c333120000      add        ebx, 0x1233
00003d78  8b7d20            mov        edi, dword ptr [ebp + 0x20]
00003d7b  8b7508            mov        esi, dword ptr [ebp + 8]
00003d7e  8b06              mov        eax, dword ptr [esi]
00003d80  83ec08            sub        esp, 8
00003d83  ff7510            push       dword ptr [ebp + 0x10]
00003d86  56                push       esi
00003d87  ff9098030000      call       dword ptr [eax + 0x398]
00003d8d  83c410            add        esp, 0x10
00003d90  8b0e              mov        ecx, dword ptr [esi]
00003d92  83ec0c            sub        esp, 0xc
00003d95  50                push       eax
00003d96  57                push       edi
00003d97  ff7518            push       dword ptr [ebp + 0x18]
00003d9a  ff7514            push       dword ptr [ebp + 0x14]
00003d9d  56                push       esi
00003d9e  ff9128030000      call       dword ptr [ecx + 0x328]
00003da4  83c420            add        esp, 0x20
00003da7  8d65f4            lea        esp, [ebp - 0xc]
00003daa  5e                pop        esi
00003dab  5f                pop        edi
00003dac  5b                pop        ebx
00003dad  5d                pop        ebp
00003dae  c3                ret        
