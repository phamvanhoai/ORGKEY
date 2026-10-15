# APK member: lib/x86_64/libnpitchshift.so
# Library SHA-256: 2b07803806c26b8fb41dfd381a8c34b4f05d5cd476aabd180d5113a56462a43a
# Function: Java_sofeh_audio_NPitchShift_processLeft; address=0x2060; bytes=46
# Decoder mode: default; decoded_bytes=46
00002060  4885d2            test       rdx, rdx
00002063  7426              je         0x208b
00002065  4889d7            mov        rdi, rdx
00002068  f30f2ac1          cvtsi2ss   xmm0, ecx
0000206c  f3410f2ac8        cvtsi2ss   xmm1, r8d
00002071  f30f5e0573e9ffff  divss      xmm0, dword ptr [rip - 0x168d]
00002079  be00040000        mov        esi, 0x400
0000207e  ba04000000        mov        edx, 4
00002083  4c89c9            mov        rcx, r9
00002086  e965010000        jmp        0x21f0
0000208b  31c0              xor        eax, eax
0000208d  c3                ret        
