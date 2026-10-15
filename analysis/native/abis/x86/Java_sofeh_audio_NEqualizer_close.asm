# APK member: lib/x86/libnequalizer.so
# Library SHA-256: 0c7dfba2ccd6a4c88a74500c5b3ecac4f88ff3e4abb681dafc117af7ee94b44b
# Function: Java_sofeh_audio_NEqualizer_close; address=0x2680; bytes=46
# Decoder mode: default; decoded_bytes=46
00002680  55                push       ebp
00002681  89e5              mov        ebp, esp
00002683  53                push       ebx
00002684  83e4f0            and        esp, 0xfffffff0
00002687  83ec10            sub        esp, 0x10
0000268a  e800000000        call       0x268f
0000268f  5b                pop        ebx
00002690  81c365110000      add        ebx, 0x1165
00002696  8b4510            mov        eax, dword ptr [ebp + 0x10]
00002699  8b4d14            mov        ecx, dword ptr [ebp + 0x14]
0000269c  09c1              or         ecx, eax
0000269e  7408              je         0x26a8
000026a0  890424            mov        dword ptr [esp], eax
000026a3  e848000000        call       0x26f0
000026a8  8d65fc            lea        esp, [ebp - 4]
000026ab  5b                pop        ebx
000026ac  5d                pop        ebp
000026ad  c3                ret        
