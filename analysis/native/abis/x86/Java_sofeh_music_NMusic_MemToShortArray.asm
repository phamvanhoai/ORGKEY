# APK member: lib/x86/libnmusic.so
# Library SHA-256: 051762c43873d3b1e8d0eb96eb91cd82ffbc3e9ca211006f6977da7afa2b80a2
# Function: Java_sofeh_music_NMusic_MemToShortArray; address=0x3cc0; bytes=79
# Decoder mode: default; decoded_bytes=79
00003cc0  55                push       ebp
00003cc1  89e5              mov        ebp, esp
00003cc3  53                push       ebx
00003cc4  57                push       edi
00003cc5  56                push       esi
00003cc6  83e4f0            and        esp, 0xfffffff0
00003cc9  83ec10            sub        esp, 0x10
00003ccc  e800000000        call       0x3cd1
00003cd1  5b                pop        ebx
00003cd2  81c3d3120000      add        ebx, 0x12d3
00003cd8  8b7d20            mov        edi, dword ptr [ebp + 0x20]
00003cdb  8b7508            mov        esi, dword ptr [ebp + 8]
00003cde  8b06              mov        eax, dword ptr [esi]
00003ce0  83ec08            sub        esp, 8
00003ce3  ff7510            push       dword ptr [ebp + 0x10]
00003ce6  56                push       esi
00003ce7  ff9098030000      call       dword ptr [eax + 0x398]
00003ced  83c410            add        esp, 0x10
00003cf0  8b0e              mov        ecx, dword ptr [esi]
00003cf2  83ec0c            sub        esp, 0xc
00003cf5  50                push       eax
00003cf6  57                push       edi
00003cf7  ff7518            push       dword ptr [ebp + 0x18]
00003cfa  ff7514            push       dword ptr [ebp + 0x14]
00003cfd  56                push       esi
00003cfe  ff9148030000      call       dword ptr [ecx + 0x348]
00003d04  83c420            add        esp, 0x20
00003d07  8d65f4            lea        esp, [ebp - 0xc]
00003d0a  5e                pop        esi
00003d0b  5f                pop        edi
00003d0c  5b                pop        ebx
00003d0d  5d                pop        ebp
00003d0e  c3                ret        
