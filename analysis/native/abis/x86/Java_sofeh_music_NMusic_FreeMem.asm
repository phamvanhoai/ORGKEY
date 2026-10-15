# APK member: lib/x86/libnmusic.so
# Library SHA-256: 051762c43873d3b1e8d0eb96eb91cd82ffbc3e9ca211006f6977da7afa2b80a2
# Function: Java_sofeh_music_NMusic_FreeMem; address=0x3c30; bytes=57
# Decoder mode: default; decoded_bytes=57
00003c30  55                push       ebp
00003c31  89e5              mov        ebp, esp
00003c33  53                push       ebx
00003c34  83e4f0            and        esp, 0xfffffff0
00003c37  83ec20            sub        esp, 0x20
00003c3a  e800000000        call       0x3c3f
00003c3f  5b                pop        ebx
00003c40  81c365130000      add        ebx, 0x1365
00003c46  8b4510            mov        eax, dword ptr [ebp + 0x10]
00003c49  8b4d08            mov        ecx, dword ptr [ebp + 8]
00003c4c  8b11              mov        edx, dword ptr [ecx]
00003c4e  89442404          mov        dword ptr [esp + 4], eax
00003c52  890c24            mov        dword ptr [esp], ecx
00003c55  ff9298030000      call       dword ptr [edx + 0x398]
00003c5b  890424            mov        dword ptr [esp], eax
00003c5e  e8bd010000        call       0x3e20
00003c63  8d65fc            lea        esp, [ebp - 4]
00003c66  5b                pop        ebx
00003c67  5d                pop        ebp
00003c68  c3                ret        
