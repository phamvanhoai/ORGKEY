# APK member: lib/x86/libnopensl.so
# Library SHA-256: 81e6220973b454100123e9259f13717edc34b683f704a7e2f342ff6424ed7706
# Function: Java_sofeh_audio_NOpenSL_RecordStop; address=0x2b90; bytes=58
# Decoder mode: default; decoded_bytes=58
00002b90  55                push       ebp
00002b91  89e5              mov        ebp, esp
00002b93  53                push       ebx
00002b94  83e4f0            and        esp, 0xfffffff0
00002b97  83ec10            sub        esp, 0x10
00002b9a  e800000000        call       0x2b9f
00002b9f  5b                pop        ebx
00002ba0  81c3fd110000      add        ebx, 0x11fd
00002ba6  c6838490100000    mov        byte ptr [ebx + 0x109084], 0
00002bad  8b8390901000      mov        eax, dword ptr [ebx + 0x109090]
00002bb3  85c0              test       eax, eax
00002bb5  740d              je         0x2bc4
00002bb7  8b08              mov        ecx, dword ptr [eax]
00002bb9  83ec08            sub        esp, 8
00002bbc  6a01              push       1
00002bbe  50                push       eax
00002bbf  ff11              call       dword ptr [ecx]
00002bc1  83c410            add        esp, 0x10
00002bc4  8d65fc            lea        esp, [ebp - 4]
00002bc7  5b                pop        ebx
00002bc8  5d                pop        ebp
00002bc9  c3                ret        
