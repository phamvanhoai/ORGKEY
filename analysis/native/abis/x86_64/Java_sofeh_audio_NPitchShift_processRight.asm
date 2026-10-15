# APK member: lib/x86_64/libnpitchshift.so
# Library SHA-256: 2b07803806c26b8fb41dfd381a8c34b4f05d5cd476aabd180d5113a56462a43a
# Function: Java_sofeh_audio_NPitchShift_processRight; address=0x2090; bytes=53
# Decoder mode: default; decoded_bytes=53
00002090  4885d2            test       rdx, rdx
00002093  742d              je         0x20c2
00002095  4889d7            mov        rdi, rdx
00002098  f30f2ac1          cvtsi2ss   xmm0, ecx
0000209c  4881c710b00000    add        rdi, 0xb010
000020a3  f3410f2ac8        cvtsi2ss   xmm1, r8d
000020a8  f30f5e053ce9ffff  divss      xmm0, dword ptr [rip - 0x16c4]
000020b0  be00040000        mov        esi, 0x400
000020b5  ba04000000        mov        edx, 4
000020ba  4c89c9            mov        rcx, r9
000020bd  e92e010000        jmp        0x21f0
000020c2  31c0              xor        eax, eax
000020c4  c3                ret        
