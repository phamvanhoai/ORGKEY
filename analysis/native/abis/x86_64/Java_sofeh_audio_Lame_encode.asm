# APK member: lib/x86_64/libnlame.so
# Library SHA-256: c8c23e1ac39d47f5c16086af1b35f9b73085cced6666201a650c2d70b5da1d47
# Function: Java_sofeh_audio_Lame_encode; address=0x3bdf0; bytes=225
# Decoder mode: default; decoded_bytes=225
0003bdf0  55                push       rbp
0003bdf1  4889e5            mov        rbp, rsp
0003bdf4  4157              push       r15
0003bdf6  4156              push       r14
0003bdf8  4155              push       r13
0003bdfa  4154              push       r12
0003bdfc  53                push       rbx
0003bdfd  4883ec28          sub        rsp, 0x28
0003be01  4d89cc            mov        r12, r9
0003be04  4c894dc8          mov        qword ptr [rbp - 0x38], r9
0003be08  448945d4          mov        dword ptr [rbp - 0x2c], r8d
0003be0c  4889cb            mov        rbx, rcx
0003be0f  48894db8          mov        qword ptr [rbp - 0x48], rcx
0003be13  488955c0          mov        qword ptr [rbp - 0x40], rdx
0003be17  4989fe            mov        r14, rdi
0003be1a  488b07            mov        rax, qword ptr [rdi]
0003be1d  4889d6            mov        rsi, rdx
0003be20  31d2              xor        edx, edx
0003be22  ff90d0050000      call       qword ptr [rax + 0x5d0]
0003be28  4989c5            mov        r13, rax
0003be2b  498b06            mov        rax, qword ptr [r14]
0003be2e  4c89f7            mov        rdi, r14
0003be31  4889de            mov        rsi, rbx
0003be34  31d2              xor        edx, edx
0003be36  ff90d0050000      call       qword ptr [rax + 0x5d0]
0003be3c  4989c7            mov        r15, rax
0003be3f  498b06            mov        rax, qword ptr [r14]
0003be42  4c89f7            mov        rdi, r14
0003be45  4c89e6            mov        rsi, r12
0003be48  ff9058050000      call       qword ptr [rax + 0x558]
0003be4e  89c3              mov        ebx, eax
0003be50  498b06            mov        rax, qword ptr [r14]
0003be53  4c89f7            mov        rdi, r14
0003be56  4c89e6            mov        rsi, r12
0003be59  31d2              xor        edx, edx
0003be5b  ff90c0050000      call       qword ptr [rax + 0x5c0]
0003be61  4989c4            mov        r12, rax
0003be64  488b3d8da50100    mov        rdi, qword ptr [rip + 0x1a58d]
0003be6b  4c89ee            mov        rsi, r13
0003be6e  4c89fa            mov        rdx, r15
0003be71  8b4dd4            mov        ecx, dword ptr [rbp - 0x2c]
0003be74  4989c0            mov        r8, rax
0003be77  4189d9            mov        r9d, ebx
0003be7a  e8610a0000        call       0x3c8e0
0003be7f  89c3              mov        ebx, eax
0003be81  498b06            mov        rax, qword ptr [r14]
0003be84  4c89f7            mov        rdi, r14
0003be87  488b75c0          mov        rsi, qword ptr [rbp - 0x40]
0003be8b  4c89ea            mov        rdx, r13
0003be8e  31c9              xor        ecx, ecx
0003be90  ff9010060000      call       qword ptr [rax + 0x610]
0003be96  498b06            mov        rax, qword ptr [r14]
0003be99  4c89f7            mov        rdi, r14
0003be9c  488b75b8          mov        rsi, qword ptr [rbp - 0x48]
0003bea0  4c89fa            mov        rdx, r15
0003bea3  31c9              xor        ecx, ecx
0003bea5  ff9010060000      call       qword ptr [rax + 0x610]
0003beab  498b06            mov        rax, qword ptr [r14]
0003beae  4c89f7            mov        rdi, r14
0003beb1  488b75c8          mov        rsi, qword ptr [rbp - 0x38]
0003beb5  4c89e2            mov        rdx, r12
0003beb8  31c9              xor        ecx, ecx
0003beba  ff9000060000      call       qword ptr [rax + 0x600]
0003bec0  89d8              mov        eax, ebx
0003bec2  4883c428          add        rsp, 0x28
0003bec6  5b                pop        rbx
0003bec7  415c              pop        r12
0003bec9  415d              pop        r13
0003becb  415e              pop        r14
0003becd  415f              pop        r15
0003becf  5d                pop        rbp
0003bed0  c3                ret        
