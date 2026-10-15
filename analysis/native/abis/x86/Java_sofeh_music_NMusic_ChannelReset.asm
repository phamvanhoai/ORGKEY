# APK member: lib/x86/libnmusic.so
# Library SHA-256: 051762c43873d3b1e8d0eb96eb91cd82ffbc3e9ca211006f6977da7afa2b80a2
# Function: Java_sofeh_music_NMusic_ChannelReset; address=0x2a30; bytes=62
# Decoder mode: default; decoded_bytes=62
00002a30  55                push       ebp
00002a31  89e5              mov        ebp, esp
00002a33  53                push       ebx
00002a34  56                push       esi
00002a35  83e4f0            and        esp, 0xfffffff0
00002a38  83ec10            sub        esp, 0x10
00002a3b  e800000000        call       0x2a40
00002a40  5b                pop        ebx
00002a41  81c364250000      add        ebx, 0x2564
00002a47  8b7510            mov        esi, dword ptr [ebp + 0x10]
00002a4a  8b4514            mov        eax, dword ptr [ebp + 0x14]
00002a4d  09f0              or         eax, esi
00002a4f  7416              je         0x2a67
00002a51  893424            mov        dword ptr [esp], esi
00002a54  e8d7130000        call       0x3e30
00002a59  81c60cb00000      add        esi, 0xb00c
00002a5f  893424            mov        dword ptr [esp], esi
00002a62  e8c9130000        call       0x3e30
00002a67  8d65f8            lea        esp, [ebp - 8]
00002a6a  5e                pop        esi
00002a6b  5b                pop        ebx
00002a6c  5d                pop        ebp
00002a6d  c3                ret        
