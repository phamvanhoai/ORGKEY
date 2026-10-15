# APK member: lib/x86_64/libnopensl.so
# Library SHA-256: 1cfbadb3f31bae35f1aa6bc7a994af95d242e75b680f26a661a4b66c2a61f23f
# Function: Java_sofeh_audio_NOpenSL_Put; address=0xeb0; bytes=128
# Decoder mode: default; decoded_bytes=128
00000eb0  4885d2            test       rdx, rdx
00000eb3  747a              je         0xf2f
00000eb5  55                push       rbp
00000eb6  4889e5            mov        rbp, rsp
00000eb9  4157              push       r15
00000ebb  4156              push       r14
00000ebd  4154              push       r12
00000ebf  53                push       rbx
00000ec0  4189cf            mov        r15d, ecx
00000ec3  4889d3            mov        rbx, rdx
00000ec6  4989fe            mov        r14, rdi
00000ec9  488b07            mov        rax, qword ptr [rdi]
00000ecc  4889d6            mov        rsi, rdx
00000ecf  ff9058050000      call       qword ptr [rax + 0x558]
00000ed5  4439f8            cmp        eax, r15d
00000ed8  440f4cf8          cmovl      r15d, eax
00000edc  4585ff            test       r15d, r15d
00000edf  7e46              jle        0xf27
00000ee1  4181ff00400000    cmp        r15d, 0x4000
00000ee8  41bc00400000      mov        r12d, 0x4000
00000eee  450f42e7          cmovb      r12d, r15d
00000ef2  498b06            mov        rax, qword ptr [r14]
00000ef5  4c8d0524a10000    lea        r8, [rip + 0xa124]
00000efc  4c89f7            mov        rdi, r14
00000eff  4889de            mov        rsi, rbx
00000f02  31d2              xor        edx, edx
00000f04  4489e1            mov        ecx, r12d
00000f07  ff9050060000      call       qword ptr [rax + 0x650]
00000f0d  4489250c210100    mov        dword ptr [rip + 0x1210c], r12d
00000f14  44392509210100    cmp        dword ptr [rip + 0x12109], r12d
00000f1b  7c0a              jl         0xf27
00000f1d  c705fd20010000000000  mov        dword ptr [rip + 0x120fd], 0
00000f27  5b                pop        rbx
00000f28  415c              pop        r12
00000f2a  415e              pop        r14
00000f2c  415f              pop        r15
00000f2e  5d                pop        rbp
00000f2f  c3                ret        
