# APK member: lib/x86/libnmusic.so
# Library SHA-256: 051762c43873d3b1e8d0eb96eb91cd82ffbc3e9ca211006f6977da7afa2b80a2
# Function: Java_sofeh_music_NMusic_ChannelCopy; address=0x2a70; bytes=96
# Decoder mode: default; decoded_bytes=96
00002a70  55                push       ebp
00002a71  89e5              mov        ebp, esp
00002a73  53                push       ebx
00002a74  57                push       edi
00002a75  56                push       esi
00002a76  83e4f0            and        esp, 0xfffffff0
00002a79  83ec10            sub        esp, 0x10
00002a7c  e800000000        call       0x2a81
00002a81  5b                pop        ebx
00002a82  81c323250000      add        ebx, 0x2523
00002a88  8b7d18            mov        edi, dword ptr [ebp + 0x18]
00002a8b  8b7510            mov        esi, dword ptr [ebp + 0x10]
00002a8e  8b4514            mov        eax, dword ptr [ebp + 0x14]
00002a91  09f0              or         eax, esi
00002a93  0f94c0            sete       al
00002a96  8b4d1c            mov        ecx, dword ptr [ebp + 0x1c]
00002a99  09f9              or         ecx, edi
00002a9b  0f94c1            sete       cl
00002a9e  08c1              or         cl, al
00002aa0  7526              jne        0x2ac8
00002aa2  83ec08            sub        esp, 8
00002aa5  57                push       edi
00002aa6  56                push       esi
00002aa7  e894130000        call       0x3e40
00002aac  83c410            add        esp, 0x10
00002aaf  81c60cb00000      add        esi, 0xb00c
00002ab5  81c70cb00000      add        edi, 0xb00c
00002abb  83ec08            sub        esp, 8
00002abe  57                push       edi
00002abf  56                push       esi
00002ac0  e87b130000        call       0x3e40
00002ac5  83c410            add        esp, 0x10
00002ac8  8d65f4            lea        esp, [ebp - 0xc]
00002acb  5e                pop        esi
00002acc  5f                pop        edi
00002acd  5b                pop        ebx
00002ace  5d                pop        ebp
00002acf  c3                ret        
