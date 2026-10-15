# APK member: lib/x86/libnflanger.so
# Library SHA-256: 358a7d5d8ff8336f0dff81448c92ca52cbb50537d20bc26689308d57c3842482
# Function: Java_sofeh_audio_NFlanger_close; address=0xfe0; bytes=46
# Decoder mode: default; decoded_bytes=46
00000fe0  55                push       ebp
00000fe1  89e5              mov        ebp, esp
00000fe3  53                push       ebx
00000fe4  83e4f0            and        esp, 0xfffffff0
00000fe7  83ec10            sub        esp, 0x10
00000fea  e800000000        call       0xfef
00000fef  5b                pop        ebx
00000ff0  81c375110000      add        ebx, 0x1175
00000ff6  8b4510            mov        eax, dword ptr [ebp + 0x10]
00000ff9  8b4d14            mov        ecx, dword ptr [ebp + 0x14]
00000ffc  09c1              or         ecx, eax
00000ffe  7408              je         0x1008
00001000  890424            mov        dword ptr [esp], eax
00001003  e848000000        call       0x1050
00001008  8d65fc            lea        esp, [ebp - 4]
0000100b  5b                pop        ebx
0000100c  5d                pop        ebp
0000100d  c3                ret        
