# APK member: lib/x86_64/libnpitchshift.so
# Library SHA-256: 2b07803806c26b8fb41dfd381a8c34b4f05d5cd476aabd180d5113a56462a43a
# Function: Java_sofeh_audio_NPitchShift_process; address=0x20d0; bytes=53
# Decoder mode: default; decoded_bytes=53
000020d0  4885d2            test       rdx, rdx
000020d3  742d              je         0x2102
000020d5  55                push       rbp
000020d6  4889e5            mov        rbp, rsp
000020d9  4889d7            mov        rdi, rdx
000020dc  f30f2ac1          cvtsi2ss   xmm0, ecx
000020e0  f30f5e0504e9ffff  divss      xmm0, dword ptr [rip - 0x16fc]
000020e8  f3410f2ac8        cvtsi2ss   xmm1, r8d
000020ed  490fbfc9          movsx      rcx, r9w
000020f1  be00040000        mov        esi, 0x400
000020f6  ba04000000        mov        edx, 4
000020fb  e8f0000000        call       0x21f0
00002100  5d                pop        rbp
00002101  c3                ret        
00002102  31c0              xor        eax, eax
00002104  c3                ret        
