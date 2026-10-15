# APK member: lib/x86/libnmusic.so
# Library SHA-256: 051762c43873d3b1e8d0eb96eb91cd82ffbc3e9ca211006f6977da7afa2b80a2
# Function: Java_sofeh_music_NMusic_ChannelClose; address=0x2a00; bytes=46
# Decoder mode: default; decoded_bytes=46
00002a00  55                push       ebp
00002a01  89e5              mov        ebp, esp
00002a03  53                push       ebx
00002a04  83e4f0            and        esp, 0xfffffff0
00002a07  83ec10            sub        esp, 0x10
00002a0a  e800000000        call       0x2a0f
00002a0f  5b                pop        ebx
00002a10  81c395250000      add        ebx, 0x2595
00002a16  8b4510            mov        eax, dword ptr [ebp + 0x10]
00002a19  8b4d14            mov        ecx, dword ptr [ebp + 0x14]
00002a1c  09c1              or         ecx, eax
00002a1e  7408              je         0x2a28
00002a20  890424            mov        dword ptr [esp], eax
00002a23  e8f8130000        call       0x3e20
00002a28  8d65fc            lea        esp, [ebp - 4]
00002a2b  5b                pop        ebx
00002a2c  5d                pop        ebp
00002a2d  c3                ret        
