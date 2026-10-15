# APK member: lib/x86/libnbandbooster.so
# Library SHA-256: 159fe53c87c1728336b9ffd53d5f471b91d6cddbc89effb302ce8435a0f64a40
# Function: Java_sofeh_audio_NBandBooster_open; address=0x730; bytes=68
# Decoder mode: default; decoded_bytes=68
00000730  55                push       ebp
00000731  89e5              mov        ebp, esp
00000733  53                push       ebx
00000734  83e4f0            and        esp, 0xfffffff0
00000737  83ec20            sub        esp, 0x20
0000073a  e800000000        call       0x73f
0000073f  5b                pop        ebx
00000740  81c3f5130000      add        ebx, 0x13f5
00000746  8b4510            mov        eax, dword ptr [ebp + 0x10]
00000749  8b4d14            mov        ecx, dword ptr [ebp + 0x14]
0000074c  09c1              or         ecx, eax
0000074e  7408              je         0x758
00000750  890424            mov        dword ptr [esp], eax
00000753  e8d8020000        call       0xa30
00000758  c744240458000000  mov        dword ptr [esp + 4], 0x58
00000760  c7042401000000    mov        dword ptr [esp], 1
00000767  e8d4020000        call       0xa40
0000076c  31d2              xor        edx, edx
0000076e  8d65fc            lea        esp, [ebp - 4]
00000771  5b                pop        ebx
00000772  5d                pop        ebp
00000773  c3                ret        
