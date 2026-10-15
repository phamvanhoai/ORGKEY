# APK member: lib/x86/libnbandbooster.so
# Library SHA-256: 159fe53c87c1728336b9ffd53d5f471b91d6cddbc89effb302ce8435a0f64a40
# Function: Java_sofeh_audio_NBandBooster_close; address=0x9c0; bytes=46
# Decoder mode: default; decoded_bytes=46
000009c0  55                push       ebp
000009c1  89e5              mov        ebp, esp
000009c3  53                push       ebx
000009c4  83e4f0            and        esp, 0xfffffff0
000009c7  83ec10            sub        esp, 0x10
000009ca  e800000000        call       0x9cf
000009cf  5b                pop        ebx
000009d0  81c365110000      add        ebx, 0x1165
000009d6  8b4510            mov        eax, dword ptr [ebp + 0x10]
000009d9  8b4d14            mov        ecx, dword ptr [ebp + 0x14]
000009dc  09c1              or         ecx, eax
000009de  7408              je         0x9e8
000009e0  890424            mov        dword ptr [esp], eax
000009e3  e848000000        call       0xa30
000009e8  8d65fc            lea        esp, [ebp - 4]
000009eb  5b                pop        ebx
000009ec  5d                pop        ebp
000009ed  c3                ret        
