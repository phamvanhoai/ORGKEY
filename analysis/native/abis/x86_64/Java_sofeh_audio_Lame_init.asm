# APK member: lib/x86_64/libnlame.so
# Library SHA-256: c8c23e1ac39d47f5c16086af1b35f9b73085cced6666201a650c2d70b5da1d47
# Function: Java_sofeh_audio_Lame_init; address=0x3bd40; bytes=165
# Decoder mode: default; decoded_bytes=165
0003bd40  55                push       rbp
0003bd41  4889e5            mov        rbp, rsp
0003bd44  4157              push       r15
0003bd46  4156              push       r14
0003bd48  4155              push       r13
0003bd4a  4154              push       r12
0003bd4c  53                push       rbx
0003bd4d  50                push       rax
0003bd4e  4589ce            mov        r14d, r9d
0003bd51  4589c7            mov        r15d, r8d
0003bd54  4189cc            mov        r12d, ecx
0003bd57  4189d5            mov        r13d, edx
0003bd5a  8b5d10            mov        ebx, dword ptr [rbp + 0x10]
0003bd5d  488b3d94a60100    mov        rdi, qword ptr [rip + 0x1a694]
0003bd64  4885ff            test       rdi, rdi
0003bd67  7410              je         0x3bd79
0003bd69  e8920b0000        call       0x3c900
0003bd6e  48c7057fa6010000000000  mov        qword ptr [rip + 0x1a67f], 0
0003bd79  e8920b0000        call       0x3c910
0003bd7e  48890573a60100    mov        qword ptr [rip + 0x1a673], rax
0003bd85  4889c7            mov        rdi, rax
0003bd88  4489ee            mov        esi, r13d
0003bd8b  e8c00b0000        call       0x3c950
0003bd90  488b3d61a60100    mov        rdi, qword ptr [rip + 0x1a661]
0003bd97  4489e6            mov        esi, r12d
0003bd9a  e8c10b0000        call       0x3c960
0003bd9f  488b3d52a60100    mov        rdi, qword ptr [rip + 0x1a652]
0003bda6  4489fe            mov        esi, r15d
0003bda9  e8c20b0000        call       0x3c970
0003bdae  488b3d43a60100    mov        rdi, qword ptr [rip + 0x1a643]
0003bdb5  4489f6            mov        esi, r14d
0003bdb8  e8b3040000        call       0x3c270
0003bdbd  488b3d34a60100    mov        rdi, qword ptr [rip + 0x1a634]
0003bdc4  89de              mov        esi, ebx
0003bdc6  e8b50b0000        call       0x3c980
0003bdcb  488b3d26a60100    mov        rdi, qword ptr [rip + 0x1a626]
0003bdd2  4883c408          add        rsp, 8
0003bdd6  5b                pop        rbx
0003bdd7  415c              pop        r12
0003bdd9  415d              pop        r13
0003bddb  415e              pop        r14
0003bddd  415f              pop        r15
0003bddf  5d                pop        rbp
0003bde0  e9ab0a0000        jmp        0x3c890
