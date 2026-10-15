# APK member: lib/x86_64/libnlame.so
# Library SHA-256: c8c23e1ac39d47f5c16086af1b35f9b73085cced6666201a650c2d70b5da1d47
# Function: Java_sofeh_audio_Lame_flush; address=0x3bee0; bytes=105
# Decoder mode: default; decoded_bytes=105
0003bee0  55                push       rbp
0003bee1  4889e5            mov        rbp, rsp
0003bee4  4157              push       r15
0003bee6  4156              push       r14
0003bee8  4154              push       r12
0003beea  53                push       rbx
0003beeb  4889d3            mov        rbx, rdx
0003beee  4989fe            mov        r14, rdi
0003bef1  488b07            mov        rax, qword ptr [rdi]
0003bef4  4889d6            mov        rsi, rdx
0003bef7  ff9058050000      call       qword ptr [rax + 0x558]
0003befd  4189c7            mov        r15d, eax
0003bf00  498b06            mov        rax, qword ptr [r14]
0003bf03  4c89f7            mov        rdi, r14
0003bf06  4889de            mov        rsi, rbx
0003bf09  31d2              xor        edx, edx
0003bf0b  ff90c0050000      call       qword ptr [rax + 0x5c0]
0003bf11  4989c4            mov        r12, rax
0003bf14  488b3ddda40100    mov        rdi, qword ptr [rip + 0x1a4dd]
0003bf1b  4889c6            mov        rsi, rax
0003bf1e  4489fa            mov        edx, r15d
0003bf21  e8ca090000        call       0x3c8f0
0003bf26  4189c7            mov        r15d, eax
0003bf29  498b06            mov        rax, qword ptr [r14]
0003bf2c  4c89f7            mov        rdi, r14
0003bf2f  4889de            mov        rsi, rbx
0003bf32  4c89e2            mov        rdx, r12
0003bf35  31c9              xor        ecx, ecx
0003bf37  ff9000060000      call       qword ptr [rax + 0x600]
0003bf3d  4489f8            mov        eax, r15d
0003bf40  5b                pop        rbx
0003bf41  415c              pop        r12
0003bf43  415e              pop        r14
0003bf45  415f              pop        r15
0003bf47  5d                pop        rbp
0003bf48  c3                ret        
