# APK member: lib/x86/libnsf2.so
# Library SHA-256: 452e13279ac07ceb7c0366072a52f65dedf77b845331b27d58ba4be9865cb7a5
# Function: Java_sofeh_music_NSF2_PresetName; address=0x94f0; bytes=88
# Decoder mode: default; decoded_bytes=88
000094f0  55                push       ebp
000094f1  89e5              mov        ebp, esp
000094f3  53                push       ebx
000094f4  57                push       edi
000094f5  56                push       esi
000094f6  83e4f0            and        esp, 0xfffffff0
000094f9  83ec10            sub        esp, 0x10
000094fc  e800000000        call       0x9501
00009501  5b                pop        ebx
00009502  81c3bb1d0000      add        ebx, 0x1dbb
00009508  8b9388100000      mov        edx, dword ptr [ebx + 0x1088]
0000950e  85d2              test       edx, edx
00009510  742c              je         0x953e
00009512  8b7510            mov        esi, dword ptr [ebp + 0x10]
00009515  8b4508            mov        eax, dword ptr [ebp + 8]
00009518  8b08              mov        ecx, dword ptr [eax]
0000951a  8b899c020000      mov        ecx, dword ptr [ecx + 0x29c]
00009520  31ff              xor        edi, edi
00009522  85f6              test       esi, esi
00009524  780c              js         0x9532
00009526  397210            cmp        dword ptr [edx + 0x10], esi
00009529  7e07              jle        0x9532
0000952b  c1e605            shl        esi, 5
0000952e  0332              add        esi, dword ptr [edx]
00009530  89f7              mov        edi, esi
00009532  83ec08            sub        esp, 8
00009535  57                push       edi
00009536  50                push       eax
00009537  ffd1              call       ecx
00009539  83c410            add        esp, 0x10
0000953c  eb02              jmp        0x9540
0000953e  31c0              xor        eax, eax
00009540  8d65f4            lea        esp, [ebp - 0xc]
00009543  5e                pop        esi
00009544  5f                pop        edi
00009545  5b                pop        ebx
00009546  5d                pop        ebp
00009547  c3                ret        
