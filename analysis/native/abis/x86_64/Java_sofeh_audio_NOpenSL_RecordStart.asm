# APK member: lib/x86_64/libnopensl.so
# Library SHA-256: 1cfbadb3f31bae35f1aa6bc7a994af95d242e75b680f26a661a4b66c2a61f23f
# Function: Java_sofeh_audio_NOpenSL_RecordStart; address=0x2af0; bytes=425
# Decoder mode: default; decoded_bytes=425
00002af0  488b0dc1051100    mov        rcx, qword ptr [rip + 0x1105c1]
00002af7  31c0              xor        eax, eax
00002af9  4885c9            test       rcx, rcx
00002afc  0f8496010000      je         0x2c98
00002b02  48833d4605090000  cmp        qword ptr [rip + 0x90546], 0
00002b0a  0f8488010000      je         0x2c98
00002b10  55                push       rbp
00002b11  4889e5            mov        rbp, rsp
00002b14  4157              push       r15
00002b16  4156              push       r14
00002b18  4155              push       r13
00002b1a  4154              push       r12
00002b1c  53                push       rbx
00002b1d  50                push       rax
00002b1e  48894dd0          mov        qword ptr [rbp - 0x30], rcx
00002b22  4c633507050100    movsxd     r14, dword ptr [rip + 0x10507]
00002b29  4d01f6            add        r14, r14
00002b2c  803d0905010000    cmp        byte ptr [rip + 0x10509], 0
00002b33  b810000000        mov        eax, 0x10
00002b38  bb04000000        mov        ebx, 4
00002b3d  480f45d8          cmovne     rbx, rax
00002b41  0f84b8000000      je         0x2bff
00002b47  4189dc            mov        r12d, ebx
00002b4a  4183e4f0          and        r12d, 0xfffffff0
00002b4e  4c8d3d0b850c00    lea        r15, [rip + 0xc850b]
00002b55  4531ed            xor        r13d, r13d
00002b58  0f1f840000000000  nop        dword ptr [rax + rax]
00002b60  498dbf0080fcff    lea        rdi, [r15 - 0x38000]
00002b67  31f6              xor        esi, esi
00002b69  4c89f2            mov        rdx, r14
00002b6c  e80f020000        call       0x2d80
00002b71  498dbf0000fdff    lea        rdi, [r15 - 0x30000]
00002b78  31f6              xor        esi, esi
00002b7a  4c89f2            mov        rdx, r14
00002b7d  e8fe010000        call       0x2d80
00002b82  498dbf0080fdff    lea        rdi, [r15 - 0x28000]
00002b89  31f6              xor        esi, esi
00002b8b  4c89f2            mov        rdx, r14
00002b8e  e8ed010000        call       0x2d80
00002b93  498dbf0000feff    lea        rdi, [r15 - 0x20000]
00002b9a  31f6              xor        esi, esi
00002b9c  4c89f2            mov        rdx, r14
00002b9f  e8dc010000        call       0x2d80
00002ba4  498dbf0080feff    lea        rdi, [r15 - 0x18000]
00002bab  31f6              xor        esi, esi
00002bad  4c89f2            mov        rdx, r14
00002bb0  e8cb010000        call       0x2d80
00002bb5  498dbf0000ffff    lea        rdi, [r15 - 0x10000]
00002bbc  31f6              xor        esi, esi
00002bbe  4c89f2            mov        rdx, r14
00002bc1  e8ba010000        call       0x2d80
00002bc6  498dbf0080ffff    lea        rdi, [r15 - 0x8000]
00002bcd  31f6              xor        esi, esi
00002bcf  4c89f2            mov        rdx, r14
00002bd2  e8a9010000        call       0x2d80
00002bd7  4c89ff            mov        rdi, r15
00002bda  31f6              xor        esi, esi
00002bdc  4c89f2            mov        rdx, r14
00002bdf  e89c010000        call       0x2d80
00002be4  4983c508          add        r13, 8
00002be8  4981c700000400    add        r15, 0x40000
00002bef  4d39ec            cmp        r12, r13
00002bf2  0f8568ffffff      jne        0x2b60
00002bf8  f6c304            test       bl, 4
00002bfb  750a              jne        0x2c07
00002bfd  eb3b              jmp        0x2c3a
00002bff  4531ed            xor        r13d, r13d
00002c02  f6c304            test       bl, 4
00002c05  7433              je         0x2c3a
00002c07  49c1e50f          shl        r13, 0xf
00002c0b  4c8d3d4e040900    lea        r15, [rip + 0x9044e]
00002c12  4d01ef            add        r15, r13
00002c15  83e304            and        ebx, 4
00002c18  c1e30f            shl        ebx, 0xf
00002c1b  4531e4            xor        r12d, r12d
00002c1e  6690              nop        
00002c20  4b8d3c27          lea        rdi, [r15 + r12]
00002c24  31f6              xor        esi, esi
00002c26  4c89f2            mov        rdx, r14
00002c29  e852010000        call       0x2d80
00002c2e  4981c400800000    add        r12, 0x8000
00002c35  4c39e3            cmp        rbx, r12
00002c38  75e6              jne        0x2c20
00002c3a  488b7dd0          mov        rdi, qword ptr [rbp - 0x30]
00002c3e  488b07            mov        rax, qword ptr [rdi]
00002c41  be03000000        mov        esi, 3
00002c46  ff10              call       qword ptr [rax]
00002c48  85c0              test       eax, eax
00002c4a  488d642408        lea        rsp, [rsp + 8]
00002c4f  5b                pop        rbx
00002c50  415c              pop        r12
00002c52  415d              pop        r13
00002c54  415e              pop        r14
00002c56  415f              pop        r15
00002c58  5d                pop        rbp
00002c59  7403              je         0x2c5e
00002c5b  31c0              xor        eax, eax
00002c5d  c3                ret        
00002c5e  8b05d0030100      mov        eax, dword ptr [rip + 0x103d0]
00002c64  ffc8              dec        eax
00002c66  890584041100      mov        dword ptr [rip + 0x110484], eax
00002c6c  833d7d04110000    cmp        dword ptr [rip + 0x11047d], 0
00002c73  791a              jns        0x2c8f
00002c75  803dc003010000    cmp        byte ptr [rip + 0x103c0], 0
00002c7c  b80f000000        mov        eax, 0xf
00002c81  b903000000        mov        ecx, 3
00002c86  0f45c8            cmovne     ecx, eax
00002c89  890d61041100      mov        dword ptr [rip + 0x110461], ecx
00002c8f  c6050e04110001    mov        byte ptr [rip + 0x11040e], 1
00002c96  b001              mov        al, 1
00002c98  c3                ret        
