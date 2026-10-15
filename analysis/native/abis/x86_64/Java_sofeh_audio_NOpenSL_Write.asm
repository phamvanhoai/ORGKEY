# APK member: lib/x86_64/libnopensl.so
# Library SHA-256: 1cfbadb3f31bae35f1aa6bc7a994af95d242e75b680f26a661a4b66c2a61f23f
# Function: Java_sofeh_audio_NOpenSL_Write; address=0xf30; bytes=694
# Decoder mode: default; decoded_bytes=694
00000f30  55                push       rbp
00000f31  4889e5            mov        rbp, rsp
00000f34  4157              push       r15
00000f36  4156              push       r14
00000f38  4155              push       r13
00000f3a  4154              push       r12
00000f3c  53                push       rbx
00000f3d  4881ec18800000    sub        rsp, 0x8018
00000f44  64488b042528000000  mov        rax, qword ptr fs:[0x28]
00000f4d  488945d0          mov        qword ptr [rbp - 0x30], rax
00000f51  4885d2            test       rdx, rdx
00000f54  0f8466020000      je         0x11c0
00000f5a  48833dc620010000  cmp        qword ptr [rip + 0x120c6], 0
00000f62  0f8458020000      je         0x11c0
00000f68  833dc120010000    cmp        dword ptr [rip + 0x120c1], 0
00000f6f  0f8e4b020000      jle        0x11c0
00000f75  4189cc            mov        r12d, ecx
00000f78  4989d6            mov        r14, rdx
00000f7b  4989ff            mov        r15, rdi
00000f7e  488b07            mov        rax, qword ptr [rdi]
00000f81  4889d6            mov        rsi, rdx
00000f84  ff9058050000      call       qword ptr [rax + 0x558]
00000f8a  4439e0            cmp        eax, r12d
00000f8d  440f4ce0          cmovl      r12d, eax
00000f91  4181fc00400000    cmp        r12d, 0x4000
00000f98  b800400000        mov        eax, 0x4000
00000f9d  410f4cc4          cmovl      eax, r12d
00000fa1  8985c87fffff      mov        dword ptr [rbp - 0x8038], eax
00000fa7  4585e4            test       r12d, r12d
00000faa  0f8e10020000      jle        0x11c0
00000fb0  498b07            mov        rax, qword ptr [r15]
00000fb3  4c8d85d07fffff    lea        r8, [rbp - 0x8030]
00000fba  4c89ff            mov        rdi, r15
00000fbd  4c89f6            mov        rsi, r14
00000fc0  31d2              xor        edx, edx
00000fc2  8b8dc87fffff      mov        ecx, dword ptr [rbp - 0x8038]
00000fc8  ff9050060000      call       qword ptr [rax + 0x650]
00000fce  bb10000000        mov        ebx, 0x10
00000fd3  666666662e0f1f840000000000  nop        word ptr cs:[rax + rax]
00000fe0  8b054e200100      mov        eax, dword ptr [rip + 0x1204e]
00000fe6  3b054c200100      cmp        eax, dword ptr [rip + 0x1204c]
00000fec  7512              jne        0x1000
00000fee  4c8b3d33200100    mov        r15, qword ptr [rip + 0x12033]
00000ff5  4d85ff            test       r15, r15
00000ff8  752b              jne        0x1025
00000ffa  e99c010000        jmp        0x119b
00000fff  90                nop        
00001000  8b052e200100      mov        eax, dword ptr [rip + 0x1202e]
00001006  803d2f20010000    cmp        byte ptr [rip + 0x1202f], 0
0000100d  b904000000        mov        ecx, 4
00001012  0f45cb            cmovne     ecx, ebx
00001015  4c8b3d0c200100    mov        r15, qword ptr [rip + 0x1200c]
0000101c  39c8              cmp        eax, ecx
0000101e  7c2c              jl         0x104c
00001020  4d85ff            test       r15, r15
00001023  7427              je         0x104c
00001025  8b0515200100      mov        eax, dword ptr [rip + 0x12015]
0000102b  83f802            cmp        eax, 2
0000102e  750c              jne        0x103c
00001030  bf0a000000        mov        edi, 0xa
00001035  e8d61c0000        call       0x2d10
0000103a  eba4              jmp        0xfe0
0000103c  448b2df51f0100    mov        r13d, dword ptr [rip + 0x11ff5]
00001043  448b25fa1f0100    mov        r12d, dword ptr [rip + 0x11ffa]
0000104a  eb17              jmp        0x1063
0000104c  448b2de51f0100    mov        r13d, dword ptr [rip + 0x11fe5]
00001053  448b25ea1f0100    mov        r12d, dword ptr [rip + 0x11fea]
0000105a  4d85ff            test       r15, r15
0000105d  0f844f010000      je         0x11b2
00001063  4531f6            xor        r14d, r14d
00001066  662e0f1f840000000000  nop        word ptr cs:[rax + rax]
00001070  8b05ca1f0100      mov        eax, dword ptr [rip + 0x11fca]
00001076  83f802            cmp        eax, 2
00001079  0f8533010000      jne        0x11b2
0000107f  8b1dab1f0100      mov        ebx, dword ptr [rip + 0x11fab]
00001085  4139dc            cmp        r12d, ebx
00001088  0f8ca1000000      jl         0x112f
0000108e  4489b5cc7fffff    mov        dword ptr [rbp - 0x8034], r14d
00001095  41ffc5            inc        r13d
00001098  803d9d1f010000    cmp        byte ptr [rip + 0x11f9d], 0
0000109f  b804000000        mov        eax, 4
000010a4  bb10000000        mov        ebx, 0x10
000010a9  0f45c3            cmovne     eax, ebx
000010ac  4139c5            cmp        r13d, eax
000010af  b800000000        mov        eax, 0
000010b4  440f4de8          cmovge     r13d, eax
000010b8  0f1f840000000000  nop        dword ptr [rax + rax]
000010c0  8b056e1f0100      mov        eax, dword ptr [rip + 0x11f6e]
000010c6  4439e8            cmp        eax, r13d
000010c9  7515              jne        0x10e0
000010cb  4c8b3d561f0100    mov        r15, qword ptr [rip + 0x11f56]
000010d2  4d85ff            test       r15, r15
000010d5  752e              jne        0x1105
000010d7  eb43              jmp        0x111c
000010d9  0f1f8000000000    nop        dword ptr [rax]
000010e0  8b054e1f0100      mov        eax, dword ptr [rip + 0x11f4e]
000010e6  803d4f1f010000    cmp        byte ptr [rip + 0x11f4f], 0
000010ed  b904000000        mov        ecx, 4
000010f2  0f45cb            cmovne     ecx, ebx
000010f5  4c8b3d2c1f0100    mov        r15, qword ptr [rip + 0x11f2c]
000010fc  39c8              cmp        eax, ecx
000010fe  7c1f              jl         0x111f
00001100  4d85ff            test       r15, r15
00001103  741a              je         0x111f
00001105  8b05351f0100      mov        eax, dword ptr [rip + 0x11f35]
0000110b  83f802            cmp        eax, 2
0000110e  750f              jne        0x111f
00001110  bf0a000000        mov        edi, 0xa
00001115  e8f61b0000        call       0x2d10
0000111a  eba4              jmp        0x10c0
0000111c  4531ff            xor        r15d, r15d
0000111f  4531e4            xor        r12d, r12d
00001122  8b1d081f0100      mov        ebx, dword ptr [rip + 0x11f08]
00001128  448bb5cc7fffff    mov        r14d, dword ptr [rbp - 0x8034]
0000112f  4429e3            sub        ebx, r12d
00001132  8b85c87fffff      mov        eax, dword ptr [rbp - 0x8038]
00001138  39d8              cmp        eax, ebx
0000113a  0f4cd8            cmovl      ebx, eax
0000113d  4489adcc7fffff    mov        dword ptr [rbp - 0x8034], r13d
00001144  4963c5            movsxd     rax, r13d
00001147  4d63e4            movsxd     r12, r12d
0000114a  48c1e00f          shl        rax, 0xf
0000114e  488d0dfb1e0100    lea        rcx, [rip + 0x11efb]
00001155  4801c8            add        rax, rcx
00001158  4a8d3c60          lea        rdi, [rax + r12*2]
0000115c  4963c6            movsxd     rax, r14d
0000115f  488d3445d07fffff  lea        rsi, [rax*2 - 0x8030]
00001167  4801ee            add        rsi, rbp
0000116a  4c63eb            movsxd     r13, ebx
0000116d  4a8d146d00000000  lea        rdx, [r13*2]
00001175  e8a61b0000        call       0x2d20
0000117a  4501ec            add        r12d, r13d
0000117d  4429adc87fffff    sub        dword ptr [rbp - 0x8038], r13d
00001184  7e25              jle        0x11ab
00001186  4101de            add        r14d, ebx
00001189  4d85ff            test       r15, r15
0000118c  448badcc7fffff    mov        r13d, dword ptr [rbp - 0x8034]
00001193  0f85d7feffff      jne        0x1070
00001199  eb17              jmp        0x11b2
0000119b  448b2d961e0100    mov        r13d, dword ptr [rip + 0x11e96]
000011a2  448b259b1e0100    mov        r12d, dword ptr [rip + 0x11e9b]
000011a9  eb07              jmp        0x11b2
000011ab  448badcc7fffff    mov        r13d, dword ptr [rbp - 0x8034]
000011b2  44892d7f1e0100    mov        dword ptr [rip + 0x11e7f], r13d
000011b9  448925841e0100    mov        dword ptr [rip + 0x11e84], r12d
000011c0  64488b042528000000  mov        rax, qword ptr fs:[0x28]
000011c9  483b45d0          cmp        rax, qword ptr [rbp - 0x30]
000011cd  7512              jne        0x11e1
000011cf  4881c418800000    add        rsp, 0x8018
000011d6  5b                pop        rbx
000011d7  415c              pop        r12
000011d9  415d              pop        r13
000011db  415e              pop        r14
000011dd  415f              pop        r15
000011df  5d                pop        rbp
000011e0  c3                ret        
000011e1  e84a1b0000        call       0x2d30
