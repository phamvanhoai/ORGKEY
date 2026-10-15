# APK member: lib/x86/libnlame.so
# Library SHA-256: f3c34506fef42c63a66723f1c2244513e7809249892d190415173b1669bbf30f
# Function: Java_sofeh_audio_Lame_close; address=0x3c960; bytes=52
# Decoder mode: default; decoded_bytes=52
0003c960  55                push       ebp
0003c961  89e5              mov        ebp, esp
0003c963  53                push       ebx
0003c964  83e4f0            and        esp, 0xfffffff0
0003c967  83ec10            sub        esp, 0x10
0003c96a  e800000000        call       0x3c96f
0003c96f  5b                pop        ebx
0003c970  81c3ad200000      add        ebx, 0x20ad
0003c976  8b83fc1c0100      mov        eax, dword ptr [ebx + 0x11cfc]
0003c97c  890424            mov        dword ptr [esp], eax
0003c97f  e8dc090000        call       0x3d360
0003c984  c783fc1c010000000000  mov        dword ptr [ebx + 0x11cfc], 0
0003c98e  8d65fc            lea        esp, [ebp - 4]
0003c991  5b                pop        ebx
0003c992  5d                pop        ebp
0003c993  c3                ret        
