# APK member: lib/x86/libnlame.so
# Library SHA-256: f3c34506fef42c63a66723f1c2244513e7809249892d190415173b1669bbf30f
# Function: Java_sofeh_audio_Lame_flush; address=0x3c8e0; bytes=126
# Decoder mode: default; decoded_bytes=126
0003c8e0  55                push       ebp
0003c8e1  89e5              mov        ebp, esp
0003c8e3  53                push       ebx
0003c8e4  57                push       edi
0003c8e5  56                push       esi
0003c8e6  83e4f0            and        esp, 0xfffffff0
0003c8e9  83ec10            sub        esp, 0x10
0003c8ec  e800000000        call       0x3c8f1
0003c8f1  5b                pop        ebx
0003c8f2  81c32b210000      add        ebx, 0x212b
0003c8f8  8b7510            mov        esi, dword ptr [ebp + 0x10]
0003c8fb  8b7d08            mov        edi, dword ptr [ebp + 8]
0003c8fe  8b07              mov        eax, dword ptr [edi]
0003c900  83ec08            sub        esp, 8
0003c903  56                push       esi
0003c904  57                push       edi
0003c905  ff90ac020000      call       dword ptr [eax + 0x2ac]
0003c90b  83c410            add        esp, 0x10
0003c90e  89442408          mov        dword ptr [esp + 8], eax
0003c912  8b07              mov        eax, dword ptr [edi]
0003c914  83ec04            sub        esp, 4
0003c917  6a00              push       0
0003c919  56                push       esi
0003c91a  57                push       edi
0003c91b  ff90e0020000      call       dword ptr [eax + 0x2e0]
0003c921  83c410            add        esp, 0x10
0003c924  89c6              mov        esi, eax
0003c926  83ec04            sub        esp, 4
0003c929  ff74240c          push       dword ptr [esp + 0xc]
0003c92d  50                push       eax
0003c92e  ffb3fc1c0100      push       dword ptr [ebx + 0x11cfc]
0003c934  e8170a0000        call       0x3d350
0003c939  83c410            add        esp, 0x10
0003c93c  89442408          mov        dword ptr [esp + 8], eax
0003c940  8b07              mov        eax, dword ptr [edi]
0003c942  6a00              push       0
0003c944  56                push       esi
0003c945  ff7510            push       dword ptr [ebp + 0x10]
0003c948  57                push       edi
0003c949  ff9000030000      call       dword ptr [eax + 0x300]
0003c94f  83c410            add        esp, 0x10
0003c952  8b442408          mov        eax, dword ptr [esp + 8]
0003c956  8d65f4            lea        esp, [ebp - 0xc]
0003c959  5e                pop        esi
0003c95a  5f                pop        edi
0003c95b  5b                pop        ebx
0003c95c  5d                pop        ebp
0003c95d  c3                ret        
