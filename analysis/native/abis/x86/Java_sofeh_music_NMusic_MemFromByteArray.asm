# APK member: lib/x86/libnmusic.so
# Library SHA-256: 051762c43873d3b1e8d0eb96eb91cd82ffbc3e9ca211006f6977da7afa2b80a2
# Function: Java_sofeh_music_NMusic_MemFromByteArray; address=0x3d10; bytes=79
# Decoder mode: default; decoded_bytes=79
00003d10  55                push       ebp
00003d11  89e5              mov        ebp, esp
00003d13  53                push       ebx
00003d14  57                push       edi
00003d15  56                push       esi
00003d16  83e4f0            and        esp, 0xfffffff0
00003d19  83ec10            sub        esp, 0x10
00003d1c  e800000000        call       0x3d21
00003d21  5b                pop        ebx
00003d22  81c383120000      add        ebx, 0x1283
00003d28  8b7d20            mov        edi, dword ptr [ebp + 0x20]
00003d2b  8b7508            mov        esi, dword ptr [ebp + 8]
00003d2e  8b06              mov        eax, dword ptr [esi]
00003d30  83ec08            sub        esp, 8
00003d33  ff7510            push       dword ptr [ebp + 0x10]
00003d36  56                push       esi
00003d37  ff9098030000      call       dword ptr [eax + 0x398]
00003d3d  83c410            add        esp, 0x10
00003d40  8b0e              mov        ecx, dword ptr [esi]
00003d42  83ec0c            sub        esp, 0xc
00003d45  50                push       eax
00003d46  57                push       edi
00003d47  ff7518            push       dword ptr [ebp + 0x18]
00003d4a  ff7514            push       dword ptr [ebp + 0x14]
00003d4d  56                push       esi
00003d4e  ff9120030000      call       dword ptr [ecx + 0x320]
00003d54  83c420            add        esp, 0x20
00003d57  8d65f4            lea        esp, [ebp - 0xc]
00003d5a  5e                pop        esi
00003d5b  5f                pop        edi
00003d5c  5b                pop        ebx
00003d5d  5d                pop        ebp
00003d5e  c3                ret        
