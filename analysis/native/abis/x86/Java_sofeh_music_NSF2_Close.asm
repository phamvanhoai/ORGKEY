# APK member: lib/x86/libnsf2.so
# Library SHA-256: 452e13279ac07ceb7c0366072a52f65dedf77b845331b27d58ba4be9865cb7a5
# Function: Java_sofeh_music_NSF2_Close; address=0x9490; bytes=46
# Decoder mode: default; decoded_bytes=46
00009490  55                push       ebp
00009491  89e5              mov        ebp, esp
00009493  53                push       ebx
00009494  83e4f0            and        esp, 0xfffffff0
00009497  83ec10            sub        esp, 0x10
0000949a  e800000000        call       0x949f
0000949f  5b                pop        ebx
000094a0  81c31d1e0000      add        ebx, 0x1e1d
000094a6  8b8388100000      mov        eax, dword ptr [ebx + 0x1088]
000094ac  85c0              test       eax, eax
000094ae  7408              je         0x94b8
000094b0  890424            mov        dword ptr [esp], eax
000094b3  e8180c0000        call       0xa0d0
000094b8  8d65fc            lea        esp, [ebp - 4]
000094bb  5b                pop        ebx
000094bc  5d                pop        ebp
000094bd  c3                ret        
