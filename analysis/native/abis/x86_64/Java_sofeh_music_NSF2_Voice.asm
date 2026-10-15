# APK member: lib/x86_64/libnsf2.so
# Library SHA-256: b145c2ce82b69909417b8510a31da8408b7f0696fd62ab62f1ed49c7c76ec3b8
# Function: Java_sofeh_music_NSF2_Voice; address=0x8b20; bytes=870
# Decoder mode: default; decoded_bytes=870
00008b20  55                push       rbp
00008b21  4889e5            mov        rbp, rsp
00008b24  4157              push       r15
00008b26  4156              push       r14
00008b28  4155              push       r13
00008b2a  4154              push       r12
00008b2c  53                push       rbx
00008b2d  4881ec48080000    sub        rsp, 0x848
00008b34  4189ce            mov        r14d, ecx
00008b37  4189d7            mov        r15d, edx
00008b3a  64488b042528000000  mov        rax, qword ptr fs:[0x28]
00008b43  488945d0          mov        qword ptr [rbp - 0x30], rax
00008b47  488b05128e0000    mov        rax, qword ptr [rip + 0x8e12]
00008b4e  4885c0            test       rax, rax
00008b51  0f94c1            sete       cl
00008b54  418d56e7          lea        edx, [r14 - 0x19]
00008b58  83fa47            cmp        edx, 0x47
00008b5b  0f93c2            setae      dl
00008b5e  31db              xor        ebx, ebx
00008b60  08ca              or         dl, cl
00008b62  0f85f6020000      jne        0x8e5e
00008b68  4c8985a8f7ffff    mov        qword ptr [rbp - 0x858], r8
00008b6f  4889bda0f7ffff    mov        qword ptr [rbp - 0x860], rdi
00008b76  4889c7            mov        rdi, rax
00008b79  e8020a0000        call       0x9580
00008b7e  488b3ddb8d0000    mov        rdi, qword ptr [rip + 0x8ddb]
00008b85  c7473000000000    mov        dword ptr [rdi + 0x30], 0
00008b8c  f30f1005f08bffff  movss      xmm0, dword ptr [rip - 0x7410]
00008b94  0f134734          movlps     qword ptr [rdi + 0x34], xmm0
00008b98  660f6e05848bffff  movd       xmm0, dword ptr [rip - 0x747c]
00008ba0  4489bdc8f7ffff    mov        dword ptr [rbp - 0x838], r15d
00008ba7  4489fe            mov        esi, r15d
00008baa  4c89b5b0f7ffff    mov        qword ptr [rbp - 0x850], r14
00008bb1  4489f2            mov        edx, r14d
00008bb4  e8070a0000        call       0x95c0
00008bb9  488b05a08d0000    mov        rax, qword ptr [rip + 0x8da0]
00008bc0  31c9              xor        ecx, ecx
00008bc2  48898598f7ffff    mov        qword ptr [rbp - 0x868], rax
00008bc9  83783002          cmp        dword ptr [rax + 0x30], 2
00008bcd  0f9585cff7ffff    setne      byte ptr [rbp - 0x831]
00008bd4  0f94c1            sete       cl
00008bd7  c1e108            shl        ecx, 8
00008bda  81c100010000      add        ecx, 0x100
00008be0  898dc4f7ffff      mov        dword ptr [rbp - 0x83c], ecx
00008be6  b900ee0200        mov        ecx, 0x2ee00
00008beb  4c8d3d7e8d0000    lea        r15, [rip + 0x8d7e]
00008bf2  4c8dadd0f7ffff    lea        r13, [rbp - 0x830]
00008bf9  41bc00800000      mov        r12d, 0x8000
00008bff  eb24              jmp        0x8c25
00008c01  6666666666662e0f1f840000000000  nop        word ptr cs:[rax + rax]
00008c10  4989df            mov        r15, rbx
00008c13  8b8dbcf7ffff      mov        ecx, dword ptr [rbp - 0x844]
00008c19  2b8dc0f7ffff      sub        ecx, dword ptr [rbp - 0x840]
00008c1f  0f8ebc010000      jle        0x8de1
00008c25  8b85c4f7ffff      mov        eax, dword ptr [rbp - 0x83c]
00008c2b  39c1              cmp        ecx, eax
00008c2d  89c2              mov        edx, eax
00008c2f  898dbcf7ffff      mov        dword ptr [rbp - 0x844], ecx
00008c35  0f4cd1            cmovl      edx, ecx
00008c38  4189d6            mov        r14d, edx
00008c3b  0fb68dcff7ffff    movzx      ecx, byte ptr [rbp - 0x831]
00008c42  41d3e6            shl        r14d, cl
00008c45  4b8d1c77          lea        rbx, [r15 + r14*2]
00008c49  488bbd98f7ffff    mov        rdi, qword ptr [rbp - 0x868]
00008c50  4c89ee            mov        rsi, r13
00008c53  8995c0f7ffff      mov        dword ptr [rbp - 0x840], edx
00008c59  31c9              xor        ecx, ecx
00008c5b  e8b0090000        call       0x9610
00008c60  48b8ffffffffffffff7f  movabs     rax, 0x7fffffffffffffff
00008c6a  4901c6            add        r14, rax
00008c6d  4921c6            and        r14, rax
00008c70  4983fe07          cmp        r14, 7
00008c74  732a              jae        0x8ca0
00008c76  4c89f8            mov        rax, r15
00008c79  4c89e9            mov        rcx, r13
00008c7c  f30f1015bc8affff  movss      xmm2, dword ptr [rip - 0x7544]
00008c84  f30f101d908affff  movss      xmm3, dword ptr [rip - 0x7570]
00008c8c  beff7f0000        mov        esi, 0x7fff
00008c91  f30f1025b78affff  movss      xmm4, dword ptr [rip - 0x7549]
00008c99  e912010000        jmp        0x8db0
00008c9e  6690              nop        
00008ca0  49ffc6            inc        r14
00008ca3  4c89f2            mov        rdx, r14
00008ca6  4883e2f8          and        rdx, 0xfffffffffffffff8
00008caa  498d0457          lea        rax, [r15 + rdx*2]
00008cae  488d0c95d0f7ffff  lea        rcx, [rdx*4 - 0x830]
00008cb6  4801e9            add        rcx, rbp
00008cb9  31f6              xor        esi, esi
00008cbb  0f28352e89ffff    movaps     xmm6, xmmword ptr [rip - 0x76d2]
00008cc2  0f283d0789ffff    movaps     xmm7, xmmword ptr [rip - 0x76f9]
00008cc9  440f28052f89ffff  movaps     xmm8, xmmword ptr [rip - 0x76d1]
00008cd1  66440f6f0db689ffff  movdqa     xmm9, xmmword ptr [rip - 0x764a]
00008cda  66440f6f15ad88ffff  movdqa     xmm10, xmmword ptr [rip - 0x7753]
00008ce3  666666662e0f1f840000000000  nop        word ptr cs:[rax + rax]
00008cf0  0f28a4b5d0f7ffff  movaps     xmm4, xmmword ptr [rbp + rsi*4 - 0x830]
00008cf8  0f28acb5e0f7ffff  movaps     xmm5, xmmword ptr [rbp + rsi*4 - 0x820]
00008d00  0f28cc            movaps     xmm1, xmm4
00008d03  0fc2ce01          cmpltps    xmm1, xmm6
00008d07  660f6bc9          packssdw   xmm1, xmm1
00008d0b  0f28d5            movaps     xmm2, xmm5
00008d0e  0f28c7            movaps     xmm0, xmm7
00008d11  0fc2c401          cmpltps    xmm0, xmm4
00008d15  660f6bc0          packssdw   xmm0, xmm0
00008d19  0f28df            movaps     xmm3, xmm7
00008d1c  0fc2dd01          cmpltps    xmm3, xmm5
00008d20  660f6bdb          packssdw   xmm3, xmm3
00008d24  410f59e0          mulps      xmm4, xmm8
00008d28  410f59e8          mulps      xmm5, xmm8
00008d2c  f30f5be4          cvttps2dq  xmm4, xmm4
00008d30  660f6be4          packssdw   xmm4, xmm4
00008d34  f30f5bed          cvttps2dq  xmm5, xmm5
00008d38  66410f3810e1      pblendvb   xmm4, xmm9, xmm0
00008d3e  660f6bed          packssdw   xmm5, xmm5
00008d42  660f6fc3          movdqa     xmm0, xmm3
00008d46  66410f3810e9      pblendvb   xmm5, xmm9, xmm0
00008d4c  0fc2d601          cmpltps    xmm2, xmm6
00008d50  660f6fc1          movdqa     xmm0, xmm1
00008d54  66410f3810e2      pblendvb   xmm4, xmm10, xmm0
00008d5a  660f6bd2          packssdw   xmm2, xmm2
00008d5e  660f6fc2          movdqa     xmm0, xmm2
00008d62  66410f3810ea      pblendvb   xmm5, xmm10, xmm0
00008d68  660f6ce5          punpcklqdq xmm4, xmm5
00008d6c  f3410f7f2477      movdqu     xmmword ptr [r15 + rsi*2], xmm4
00008d72  4883c608          add        rsi, 8
00008d76  4839f2            cmp        rdx, rsi
00008d79  0f8571ffffff      jne        0x8cf0
00008d7f  4939d6            cmp        r14, rdx
00008d82  f30f1015b689ffff  movss      xmm2, dword ptr [rip - 0x764a]
00008d8a  f30f101d8a89ffff  movss      xmm3, dword ptr [rip - 0x7676]
00008d92  beff7f0000        mov        esi, 0x7fff
00008d97  f30f1025b189ffff  movss      xmm4, dword ptr [rip - 0x764f]
00008d9f  0f846bfeffff      je         0x8c10
00008da5  66662e0f1f840000000000  nop        word ptr cs:[rax + rax]
00008db0  f30f1001          movss      xmm0, dword ptr [rcx]
00008db4  4883c104          add        rcx, 4
00008db8  0f2ec2            ucomiss    xmm0, xmm2
00008dbb  0f28c8            movaps     xmm1, xmm0
00008dbe  f30f59cb          mulss      xmm1, xmm3
00008dc2  f30f2cd1          cvttss2si  edx, xmm1
00008dc6  0f47d6            cmova      edx, esi
00008dc9  0f2ee0            ucomiss    xmm4, xmm0
00008dcc  410f47d4          cmova      edx, r12d
00008dd0  668910            mov        word ptr [rax], dx
00008dd3  4883c002          add        rax, 2
00008dd7  4839d8            cmp        rax, rbx
00008dda  75d4              jne        0x8db0
00008ddc  e92ffeffff        jmp        0x8c10
00008de1  488b3d788b0000    mov        rdi, qword ptr [rip + 0x8b78]
00008de8  8bb5c8f7ffff      mov        esi, dword ptr [rbp - 0x838]
00008dee  488b95b0f7ffff    mov        rdx, qword ptr [rbp - 0x850]
00008df5  e8d6070000        call       0x95d0
00008dfa  b800ee0200        mov        eax, 0x2ee00
00008dff  488d0d6a8b0000    lea        rcx, [rip + 0x8b6a]
00008e06  488bbda0f7ffff    mov        rdi, qword ptr [rbp - 0x860]
00008e0d  0f1f00            nop        dword ptr [rax]
00008e10  4885c0            test       rax, rax
00008e13  742a              je         0x8e3f
00008e15  66837c81fc00      cmp        word ptr [rcx + rax*4 - 4], 0
00008e1b  7522              jne        0x8e3f
00008e1d  66837c81fe00      cmp        word ptr [rcx + rax*4 - 2], 0
00008e23  751a              jne        0x8e3f
00008e25  488d58ff          lea        rbx, [rax - 1]
00008e29  66837c81f800      cmp        word ptr [rcx + rax*4 - 8], 0
00008e2f  7511              jne        0x8e42
00008e31  66837c81fa00      cmp        word ptr [rcx + rax*4 - 6], 0
00008e37  488d40fe          lea        rax, [rax - 2]
00008e3b  74d3              je         0x8e10
00008e3d  eb03              jmp        0x8e42
00008e3f  4889c3            mov        rbx, rax
00008e42  488b07            mov        rax, qword ptr [rdi]
00008e45  8d0c1b            lea        ecx, [rbx + rbx]
00008e48  4c8d05218b0000    lea        r8, [rip + 0x8b21]
00008e4f  488bb5a8f7ffff    mov        rsi, qword ptr [rbp - 0x858]
00008e56  31d2              xor        edx, edx
00008e58  ff9090060000      call       qword ptr [rax + 0x690]
00008e5e  64488b042528000000  mov        rax, qword ptr fs:[0x28]
00008e67  483b45d0          cmp        rax, qword ptr [rbp - 0x30]
00008e6b  7514              jne        0x8e81
00008e6d  89d8              mov        eax, ebx
00008e6f  4881c448080000    add        rsp, 0x848
00008e76  5b                pop        rbx
00008e77  415c              pop        r12
00008e79  415d              pop        r13
00008e7b  415e              pop        r14
00008e7d  415f              pop        r15
00008e7f  5d                pop        rbp
00008e80  c3                ret        
00008e81  e85a060000        call       0x94e0
