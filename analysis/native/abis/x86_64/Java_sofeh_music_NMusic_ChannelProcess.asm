# APK member: lib/x86_64/libnmusic.so
# Library SHA-256: 792e4ca002affd7fa432bd2a8dbf94dc31b829ef32123a5954dd6e376627e35c
# Function: Java_sofeh_music_NMusic_ChannelProcess; address=0x27d0; bytes=3295
# Decoder mode: default; decoded_bytes=3295
000027d0  55                push       rbp
000027d1  4889e5            mov        rbp, rsp
000027d4  4157              push       r15
000027d6  4156              push       r14
000027d8  4155              push       r13
000027da  4154              push       r12
000027dc  53                push       rbx
000027dd  4881ec98000000    sub        rsp, 0x98
000027e4  4d89cd            mov        r13, r9
000027e7  4d89c7            mov        r15, r8
000027ea  894dc0            mov        dword ptr [rbp - 0x40], ecx
000027ed  48899558ffffff    mov        qword ptr [rbp - 0xa8], rdx
000027f4  4889fb            mov        rbx, rdi
000027f7  4c8b6518          mov        r12, qword ptr [rbp + 0x18]
000027fb  488b07            mov        rax, qword ptr [rdi]
000027fe  4c89c6            mov        rsi, r8
00002801  ff90f8000000      call       qword ptr [rax + 0xf8]
00002807  4889c6            mov        rsi, rax
0000280a  488b03            mov        rax, qword ptr [rbx]
0000280d  488d159ce5ffff    lea        rdx, [rip - 0x1a64]
00002814  488d0df1e5ffff    lea        rcx, [rip - 0x1a0f]
0000281b  4889df            mov        rdi, rbx
0000281e  4889b578ffffff    mov        qword ptr [rbp - 0x88], rsi
00002825  ff90f0020000      call       qword ptr [rax + 0x2f0]
0000282b  4889c2            mov        rdx, rax
0000282e  488b03            mov        rax, qword ptr [rbx]
00002831  4889df            mov        rdi, rbx
00002834  4c89fe            mov        rsi, r15
00002837  48899540ffffff    mov        qword ptr [rbp - 0xc0], rdx
0000283e  ff9028030000      call       qword ptr [rax + 0x328]
00002844  4989c6            mov        r14, rax
00002847  488b03            mov        rax, qword ptr [rbx]
0000284a  4889df            mov        rdi, rbx
0000284d  4c89ee            mov        rsi, r13
00002850  ff9030070000      call       qword ptr [rax + 0x730]
00002856  48894590          mov        qword ptr [rbp - 0x70], rax
0000285a  488b03            mov        rax, qword ptr [rbx]
0000285d  4531ed            xor        r13d, r13d
00002860  4889df            mov        rdi, rbx
00002863  488b7510          mov        rsi, qword ptr [rbp + 0x10]
00002867  31d2              xor        edx, edx
00002869  ff90e0050000      call       qword ptr [rax + 0x5e0]
0000286f  48894580          mov        qword ptr [rbp - 0x80], rax
00002873  488b03            mov        rax, qword ptr [rbx]
00002876  4889df            mov        rdi, rbx
00002879  4c89e6            mov        rsi, r12
0000287c  31d2              xor        edx, edx
0000287e  ff90e0050000      call       qword ptr [rax + 0x5e0]
00002884  48894588          mov        qword ptr [rbp - 0x78], rax
00002888  488b03            mov        rax, qword ptr [rbx]
0000288b  488b80f0020000    mov        rax, qword ptr [rax + 0x2f0]
00002892  837dc000          cmp        dword ptr [rbp - 0x40], 0
00002896  4c89bd60ffffff    mov        qword ptr [rbp - 0xa0], r15
0000289d  0f8488010000      je         0x2a2b
000028a3  488d1527e5ffff    lea        rdx, [rip - 0x1ad9]
000028aa  4c8d2d3be5ffff    lea        r13, [rip - 0x1ac5]
000028b1  4889df            mov        rdi, rbx
000028b4  4c8ba578ffffff    mov        r12, qword ptr [rbp - 0x88]
000028bb  4c89e6            mov        rsi, r12
000028be  4c89e9            mov        rcx, r13
000028c1  ffd0              call       rax
000028c3  4889c2            mov        rdx, rax
000028c6  488b03            mov        rax, qword ptr [rbx]
000028c9  4889df            mov        rdi, rbx
000028cc  4c89fe            mov        rsi, r15
000028cf  48899570ffffff    mov        qword ptr [rbp - 0x90], rdx
000028d6  ff9038030000      call       qword ptr [rax + 0x338]
000028dc  f20f1145a8        movsd      qword ptr [rbp - 0x58], xmm0
000028e1  488b03            mov        rax, qword ptr [rbx]
000028e4  488d150fe5ffff    lea        rdx, [rip - 0x1af1]
000028eb  4889df            mov        rdi, rbx
000028ee  4c89e6            mov        rsi, r12
000028f1  4c89e9            mov        rcx, r13
000028f4  ff90f0020000      call       qword ptr [rax + 0x2f0]
000028fa  488b0b            mov        rcx, qword ptr [rbx]
000028fd  4889df            mov        rdi, rbx
00002900  4c89fe            mov        rsi, r15
00002903  4889c2            mov        rdx, rax
00002906  ff9138030000      call       qword ptr [rcx + 0x338]
0000290c  f20f1145b8        movsd      qword ptr [rbp - 0x48], xmm0
00002911  488b03            mov        rax, qword ptr [rbx]
00002914  488d15d3e4ffff    lea        rdx, [rip - 0x1b2d]
0000291b  4c8d2dd6e4ffff    lea        r13, [rip - 0x1b2a]
00002922  4889df            mov        rdi, rbx
00002925  4c89e6            mov        rsi, r12
00002928  4c89e9            mov        rcx, r13
0000292b  ff90f0020000      call       qword ptr [rax + 0x2f0]
00002931  4889c2            mov        rdx, rax
00002934  488b03            mov        rax, qword ptr [rbx]
00002937  4889df            mov        rdi, rbx
0000293a  4c89fe            mov        rsi, r15
0000293d  48899548ffffff    mov        qword ptr [rbp - 0xb8], rdx
00002944  ff9030030000      call       qword ptr [rax + 0x330]
0000294a  f30f118568ffffff  movss      dword ptr [rbp - 0x98], xmm0
00002952  488b03            mov        rax, qword ptr [rbx]
00002955  488d157de4ffff    lea        rdx, [rip - 0x1b83]
0000295c  4889df            mov        rdi, rbx
0000295f  4c89e6            mov        rsi, r12
00002962  4c89e9            mov        rcx, r13
00002965  ff90f0020000      call       qword ptr [rax + 0x2f0]
0000296b  4889c2            mov        rdx, rax
0000296e  488b03            mov        rax, qword ptr [rbx]
00002971  4889df            mov        rdi, rbx
00002974  4c89fe            mov        rsi, r15
00002977  48899550ffffff    mov        qword ptr [rbp - 0xb0], rdx
0000297e  ff9030030000      call       qword ptr [rax + 0x330]
00002984  f30f11856cffffff  movss      dword ptr [rbp - 0x94], xmm0
0000298c  488b03            mov        rax, qword ptr [rbx]
0000298f  488d1523e4ffff    lea        rdx, [rip - 0x1bdd]
00002996  4889df            mov        rdi, rbx
00002999  4c89e6            mov        rsi, r12
0000299c  4c89e9            mov        rcx, r13
0000299f  ff90f0020000      call       qword ptr [rax + 0x2f0]
000029a5  488b0b            mov        rcx, qword ptr [rbx]
000029a8  4889df            mov        rdi, rbx
000029ab  4c89fe            mov        rsi, r15
000029ae  4889c2            mov        rdx, rax
000029b1  ff9130030000      call       qword ptr [rcx + 0x330]
000029b7  f30f1145b0        movss      dword ptr [rbp - 0x50], xmm0
000029bc  488b03            mov        rax, qword ptr [rbx]
000029bf  488d15ffe3ffff    lea        rdx, [rip - 0x1c01]
000029c6  4889df            mov        rdi, rbx
000029c9  4c89e6            mov        rsi, r12
000029cc  4c89e9            mov        rcx, r13
000029cf  ff90f0020000      call       qword ptr [rax + 0x2f0]
000029d5  488b0b            mov        rcx, qword ptr [rbx]
000029d8  4889df            mov        rdi, rbx
000029db  4c89fe            mov        rsi, r15
000029de  4889c2            mov        rdx, rax
000029e1  ff9130030000      call       qword ptr [rcx + 0x330]
000029e7  0f57c9            xorps      xmm1, xmm1
000029ea  0f57ed            xorps      xmm5, xmm5
000029ed  f30f5fad68ffffff  maxss      xmm5, dword ptr [rbp - 0x98]
000029f5  450f57ed          xorps      xmm13, xmm13
000029f9  f3440f5fad6cffffff  maxss      xmm13, dword ptr [rbp - 0x94]
00002a02  0f2ee9            ucomiss    xmm5, xmm1
00002a05  0f8704020000      ja         0x2c0f
00002a0b  440f2ee9          ucomiss    xmm13, xmm1
00002a0f  0f87fa010000      ja         0x2c0f
00002a15  0f296dc0          movaps     xmmword ptr [rbp - 0x40], xmm5
00002a19  4531ed            xor        r13d, r13d
00002a1c  4c8b7d10          mov        r15, qword ptr [rbp + 0x10]
00002a20  f2440f1065a8      movsd      xmm12, qword ptr [rbp - 0x58]
00002a26  e9cc090000        jmp        0x33f7
00002a2b  488d15dce3ffff    lea        rdx, [rip - 0x1c24]
00002a32  488d0de5e3ffff    lea        rcx, [rip - 0x1c1b]
00002a39  4889df            mov        rdi, rbx
00002a3c  4c8ba578ffffff    mov        r12, qword ptr [rbp - 0x88]
00002a43  4c89e6            mov        rsi, r12
00002a46  ffd0              call       rax
00002a48  488b0b            mov        rcx, qword ptr [rbx]
00002a4b  4889df            mov        rdi, rbx
00002a4e  4c89fe            mov        rsi, r15
00002a51  4889c2            mov        rdx, rax
00002a54  ff9120030000      call       qword ptr [rcx + 0x320]
00002a5a  898570ffffff      mov        dword ptr [rbp - 0x90], eax
00002a60  488b03            mov        rax, qword ptr [rbx]
00002a63  488d1579e3ffff    lea        rdx, [rip - 0x1c87]
00002a6a  4889df            mov        rdi, rbx
00002a6d  4c89e6            mov        rsi, r12
00002a70  488d0da7e3ffff    lea        rcx, [rip - 0x1c59]
00002a77  ff90f0020000      call       qword ptr [rax + 0x2f0]
00002a7d  4889c2            mov        rdx, rax
00002a80  488b03            mov        rax, qword ptr [rbx]
00002a83  4889df            mov        rdi, rbx
00002a86  4c89fe            mov        rsi, r15
00002a89  488955b0          mov        qword ptr [rbp - 0x50], rdx
00002a8d  ff9020030000      call       qword ptr [rax + 0x320]
00002a93  8945c0            mov        dword ptr [rbp - 0x40], eax
00002a96  488b03            mov        rax, qword ptr [rbx]
00002a99  488d1575e3ffff    lea        rdx, [rip - 0x1c8b]
00002aa0  4889df            mov        rdi, rbx
00002aa3  4c89e6            mov        rsi, r12
00002aa6  488d0d71e3ffff    lea        rcx, [rip - 0x1c8f]
00002aad  ff90f0020000      call       qword ptr [rax + 0x2f0]
00002ab3  4889c2            mov        rdx, rax
00002ab6  488b03            mov        rax, qword ptr [rbx]
00002ab9  48895da8          mov        qword ptr [rbp - 0x58], rbx
00002abd  4889df            mov        rdi, rbx
00002ac0  4c89fe            mov        rsi, r15
00002ac3  488955b8          mov        qword ptr [rbp - 0x48], rdx
00002ac7  ff9020030000      call       qword ptr [rax + 0x320]
00002acd  448b45c0          mov        r8d, dword ptr [rbp - 0x40]
00002ad1  4585c0            test       r8d, r8d
00002ad4  450f4ec5          cmovle     r8d, r13d
00002ad8  0f9ec2            setle      dl
00002adb  85c0              test       eax, eax
00002add  410f4ec5          cmovle     eax, r13d
00002ae1  0f9ec1            setle      cl
00002ae4  20d1              and        cl, dl
00002ae6  8b5520            mov        edx, dword ptr [rbp + 0x20]
00002ae9  85d2              test       edx, edx
00002aeb  400f9ec6          setle      sil
00002aef  4008ce            or         sil, cl
00002af2  448945c0          mov        dword ptr [rbp - 0x40], r8d
00002af6  898578ffffff      mov        dword ptr [rbp - 0x88], eax
00002afc  0f85ca000000      jne        0x2bcc
00002b02  89d1              mov        ecx, edx
00002b04  448b6528          mov        r12d, dword ptr [rbp + 0x28]
00002b08  4489e3            mov        ebx, r12d
00002b0b  f7d3              not        ebx
00002b0d  035d30            add        ebx, dword ptr [rbp + 0x30]
00002b10  48639570ffffff    movsxd     rdx, dword ptr [rbp - 0x90]
00002b17  89ce              mov        esi, ecx
00002b19  bf01000000        mov        edi, 1
00002b1e  4531ed            xor        r13d, r13d
00002b21  4d63c8            movsxd     r9, r8d
00002b24  4c63d0            movsxd     r10, eax
00002b27  b801000000        mov        eax, 1
00002b2c  0f1f4000          nop        dword ptr [rax]
00002b30  807d3800          cmp        byte ptr [rbp + 0x38], 0
00002b34  0f95c1            setne      cl
00002b37  4d8d9eff1f0000    lea        r11, [r14 + 0x1fff]
00002b3e  4d85f6            test       r14, r14
00002b41  4d0f49de          cmovns     r11, r14
00002b45  49c1eb0d          shr        r11, 0xd
00002b49  4539e3            cmp        r11d, r12d
00002b4c  410f9fc4          setg       r12b
00002b50  468d3c1b          lea        r15d, [rbx + r11]
00002b54  4d63ff            movsxd     r15, r15d
00002b57  4d89f8            mov        r8, r15
00002b5a  49c1e00d          shl        r8, 0xd
00002b5e  4484e1            test       cl, r12b
00002b61  448b6528          mov        r12d, dword ptr [rbp + 0x28]
00002b65  450f44fb          cmove      r15d, r11d
00002b69  4d0f45f0          cmovne     r14, r8
00002b6d  4539e7            cmp        r15d, r12d
00002b70  450f4dfc          cmovge     r15d, r12d
00002b74  450fb6ed          movzx      r13d, r13b
00002b78  440f4fe8          cmovg      r13d, eax
00002b7c  8b4d40            mov        ecx, dword ptr [rbp + 0x40]
00002b7f  41d3e7            shl        r15d, cl
00002b82  4963cf            movsxd     rcx, r15d
00002b85  4c8b7d90          mov        r15, qword ptr [rbp - 0x70]
00002b89  4d0fbf044f        movsx      r8, word ptr [r15 + rcx*2]
00002b8e  4d0fafc1          imul       r8, r9
00002b92  4c8b5d80          mov        r11, qword ptr [rbp - 0x80]
00002b96  4d0144fbf8        add        qword ptr [r11 + rdi*8 - 8], r8
00002b9b  034d48            add        ecx, dword ptr [rbp + 0x48]
00002b9e  4863c9            movsxd     rcx, ecx
00002ba1  490fbf0c4f        movsx      rcx, word ptr [r15 + rcx*2]
00002ba6  490fafca          imul       rcx, r10
00002baa  4c8b4588          mov        r8, qword ptr [rbp - 0x78]
00002bae  49014cf8f8        add        qword ptr [r8 + rdi*8 - 8], rcx
00002bb3  4901d6            add        r14, rdx
00002bb6  4839f7            cmp        rdi, rsi
00002bb9  7311              jae        0x2bcc
00002bbb  4489e9            mov        ecx, r13d
00002bbe  80e101            and        cl, 1
00002bc1  48ffc7            inc        rdi
00002bc4  84c9              test       cl, cl
00002bc6  0f8464ffffff      je         0x2b30
00002bcc  488b5da8          mov        rbx, qword ptr [rbp - 0x58]
00002bd0  488b03            mov        rax, qword ptr [rbx]
00002bd3  4889df            mov        rdi, rbx
00002bd6  4c8ba560ffffff    mov        r12, qword ptr [rbp - 0xa0]
00002bdd  4c89e6            mov        rsi, r12
00002be0  488b55b0          mov        rdx, qword ptr [rbp - 0x50]
00002be4  8b4dc0            mov        ecx, dword ptr [rbp - 0x40]
00002be7  ff9068030000      call       qword ptr [rax + 0x368]
00002bed  488b03            mov        rax, qword ptr [rbx]
00002bf0  4889df            mov        rdi, rbx
00002bf3  4c89e6            mov        rsi, r12
00002bf6  488b55b8          mov        rdx, qword ptr [rbp - 0x48]
00002bfa  8b8d78ffffff      mov        ecx, dword ptr [rbp - 0x88]
00002c00  ff9068030000      call       qword ptr [rax + 0x368]
00002c06  4c8b7d10          mov        r15, qword ptr [rbp + 0x10]
00002c0a  e943080000        jmp        0x3452
00002c0f  837dc001          cmp        dword ptr [rbp - 0x40], 1
00002c13  0f850d020000      jne        0x2e26
00002c19  0f296dc0          movaps     xmmword ptr [rbp - 0x40], xmm5
00002c1d  31c0              xor        eax, eax
00002c1f  8b4d20            mov        ecx, dword ptr [rbp + 0x20]
00002c22  85c9              test       ecx, ecx
00002c24  448b6528          mov        r12d, dword ptr [rbp + 0x28]
00002c28  f2440f1065a8      movsd      xmm12, qword ptr [rbp - 0x58]
00002c2e  f2440f1045b8      movsd      xmm8, qword ptr [rbp - 0x48]
00002c34  0f8ed8050000      jle        0x3212
00002c3a  4489e2            mov        edx, r12d
00002c3d  f7d2              not        edx
00002c3f  035530            add        edx, dword ptr [rbp + 0x30]
00002c42  89ce              mov        esi, ecx
00002c44  bf01000000        mov        edi, 1
00002c49  41b801000000      mov        r8d, 1
00002c4f  f30f100d15e1ffff  movss      xmm1, dword ptr [rip - 0x1eeb]
00002c57  0f57d2            xorps      xmm2, xmm2
00002c5a  660f57db          xorpd      xmm3, xmm3
00002c5e  4531ed            xor        r13d, r13d
00002c61  4c8b7d10          mov        r15, qword ptr [rbp + 0x10]
00002c65  66662e0f1f840000000000  nop        word ptr cs:[rax + rax]
00002c70  4d8d8eff1f0000    lea        r9, [r14 + 0x1fff]
00002c77  4d85f6            test       r14, r14
00002c7a  4d0f49ce          cmovns     r9, r14
00002c7e  49c1e90d          shr        r9, 0xd
00002c82  807d3800          cmp        byte ptr [rbp + 0x38], 0
00002c86  7458              je         0x2ce0
00002c88  4539e1            cmp        r9d, r12d
00002c8b  440f284dc0        movaps     xmm9, xmmword ptr [rbp - 0x40]
00002c90  7e2c              jle        0x2cbe
00002c92  4101d1            add        r9d, edx
00002c95  4d63f1            movsxd     r14, r9d
00002c98  49c1e60d          shl        r14, 0xd
00002c9c  410f28e5          movaps     xmm4, xmm13
00002ca0  0fc2e202          cmpleps    xmm4, xmm2
00002ca4  410f28e9          movaps     xmm5, xmm9
00002ca8  0fc2ea02          cmpleps    xmm5, xmm2
00002cac  0f54ec            andps      xmm5, xmm4
00002caf  660f7ee9          movd       ecx, xmm5
00002cb3  450fb6ed          movzx      r13d, r13b
00002cb7  f6c101            test       cl, 1
00002cba  450f45e8          cmovne     r13d, r8d
00002cbe  4539e1            cmp        r9d, r12d
00002cc1  89d1              mov        ecx, edx
00002cc3  0f4cc8            cmovl      ecx, eax
00002cc6  458d1c09          lea        r11d, [r9 + rcx]
00002cca  41ffc3            inc        r11d
00002ccd  4d89f2            mov        r10, r14
00002cd0  eb1a              jmp        0x2cec
00002cd2  66666666662e0f1f840000000000  nop        word ptr cs:[rax + rax]
00002ce0  458d5901          lea        r11d, [r9 + 1]
00002ce4  4d89f2            mov        r10, r14
00002ce7  440f284dc0        movaps     xmm9, xmmword ptr [rbp - 0x40]
00002cec  4539e1            cmp        r9d, r12d
00002cef  450f4dcc          cmovge     r9d, r12d
00002cf3  450fb6ed          movzx      r13d, r13b
00002cf7  450f4fe8          cmovg      r13d, r8d
00002cfb  4539e3            cmp        r11d, r12d
00002cfe  450f4fd9          cmovg      r11d, r9d
00002d02  0f57ed            xorps      xmm5, xmm5
00002d05  f3410f2ae9        cvtsi2ss   xmm5, r9d
00002d0a  8b4d40            mov        ecx, dword ptr [rbp + 0x40]
00002d0d  41d3e1            shl        r9d, cl
00002d10  41d3e3            shl        r11d, cl
00002d13  0f57e4            xorps      xmm4, xmm4
00002d16  f3490f2ae2        cvtsi2ss   xmm4, r10
00002d1b  f30f59e1          mulss      xmm4, xmm1
00002d1f  f30f5ce5          subss      xmm4, xmm5
00002d23  440f2eca          ucomiss    xmm9, xmm2
00002d27  7655              jbe        0x2d7e
00002d29  4963c9            movsxd     rcx, r9d
00002d2c  4c8b7590          mov        r14, qword ptr [rbp - 0x70]
00002d30  410fbf0c4e        movsx      ecx, word ptr [r14 + rcx*2]
00002d35  0f57ed            xorps      xmm5, xmm5
00002d38  f30f2ae9          cvtsi2ss   xmm5, ecx
00002d3c  4963cb            movsxd     rcx, r11d
00002d3f  410fbf0c4e        movsx      ecx, word ptr [r14 + rcx*2]
00002d44  0f57f6            xorps      xmm6, xmm6
00002d47  f30f2af1          cvtsi2ss   xmm6, ecx
00002d4b  f30f5cf5          subss      xmm6, xmm5
00002d4f  4c8b7580          mov        r14, qword ptr [rbp - 0x80]
00002d53  0f57ff            xorps      xmm7, xmm7
00002d56  f3490f2a7cfef8    cvtsi2ss   xmm7, qword ptr [r14 + rdi*8 - 8]
00002d5d  f30f59f4          mulss      xmm6, xmm4
00002d61  f30f58f5          addss      xmm6, xmm5
00002d65  f3410f59f1        mulss      xmm6, xmm9
00002d6a  f30f58f7          addss      xmm6, xmm7
00002d6e  f3480f2cce        cvttss2si  rcx, xmm6
00002d73  49894cfef8        mov        qword ptr [r14 + rdi*8 - 8], rcx
00002d78  f3440f584db0      addss      xmm9, dword ptr [rbp - 0x50]
00002d7e  440f2eea          ucomiss    xmm13, xmm2
00002d82  765e              jbe        0x2de2
00002d84  448b7548          mov        r14d, dword ptr [rbp + 0x48]
00002d88  4501f1            add        r9d, r14d
00002d8b  4963c9            movsxd     rcx, r9d
00002d8e  4c8b4d90          mov        r9, qword ptr [rbp - 0x70]
00002d92  410fbf0c49        movsx      ecx, word ptr [r9 + rcx*2]
00002d97  0f57ed            xorps      xmm5, xmm5
00002d9a  f30f2ae9          cvtsi2ss   xmm5, ecx
00002d9e  4501f3            add        r11d, r14d
00002da1  4963cb            movsxd     rcx, r11d
00002da4  410fbf0c49        movsx      ecx, word ptr [r9 + rcx*2]
00002da9  0f57f6            xorps      xmm6, xmm6
00002dac  f30f2af1          cvtsi2ss   xmm6, ecx
00002db0  f30f5cf5          subss      xmm6, xmm5
00002db4  4c8b4d88          mov        r9, qword ptr [rbp - 0x78]
00002db8  0f57ff            xorps      xmm7, xmm7
00002dbb  f3490f2a7cf9f8    cvtsi2ss   xmm7, qword ptr [r9 + rdi*8 - 8]
00002dc2  f30f59e6          mulss      xmm4, xmm6
00002dc6  f30f58e5          addss      xmm4, xmm5
00002dca  f3410f59e5        mulss      xmm4, xmm13
00002dcf  f30f58e7          addss      xmm4, xmm7
00002dd3  f3480f2ccc        cvttss2si  rcx, xmm4
00002dd8  49894cf9f8        mov        qword ptr [r9 + rdi*8 - 8], rcx
00002ddd  f3440f58e8        addss      xmm13, xmm0
00002de2  440f294dc0        movaps     xmmword ptr [rbp - 0x40], xmm9
00002de7  f2450f58e0        addsd      xmm12, xmm8
00002dec  66440f2ee3        ucomisd    xmm12, xmm3
00002df1  0f57e4            xorps      xmm4, xmm4
00002df4  f2490f2ae2        cvtsi2sd   xmm4, r10
00002df9  f2410f58e4        addsd      xmm4, xmm12
00002dfe  f24c0f2cf4        cvttsd2si  r14, xmm4
00002e03  4d0f46f2          cmovbe     r14, r10
00002e07  4839f7            cmp        rdi, rsi
00002e0a  0f83e7050000      jae        0x33f7
00002e10  4489e9            mov        ecx, r13d
00002e13  80e101            and        cl, 1
00002e16  48ffc7            inc        rdi
00002e19  84c9              test       cl, cl
00002e1b  0f844ffeffff      je         0x2c70
00002e21  e9d1050000        jmp        0x33f7
00002e26  8b45c0            mov        eax, dword ptr [rbp - 0x40]
00002e29  83e0fe            and        eax, 0xfffffffe
00002e2c  83f802            cmp        eax, 2
00002e2f  448b6528          mov        r12d, dword ptr [rbp + 0x28]
00002e33  f2440f1065a8      movsd      xmm12, qword ptr [rbp - 0x58]
00002e39  f20f1065b8        movsd      xmm4, qword ptr [rbp - 0x48]
00002e3e  8b4d20            mov        ecx, dword ptr [rbp + 0x20]
00002e41  0f85d3030000      jne        0x321a
00002e47  0f296dc0          movaps     xmmword ptr [rbp - 0x40], xmm5
00002e4b  85c9              test       ecx, ecx
00002e4d  0f8ebf030000      jle        0x3212
00002e53  4489e2            mov        edx, r12d
00002e56  f7d2              not        edx
00002e58  035530            add        edx, dword ptr [rbp + 0x30]
00002e5b  89ce              mov        esi, ecx
00002e5d  bf01000000        mov        edi, 1
00002e62  0f57d2            xorps      xmm2, xmm2
00002e65  f30f103513dfffff  movss      xmm6, dword ptr [rip - 0x20ed]
00002e6d  f30f103d03dfffff  movss      xmm7, dword ptr [rip - 0x20fd]
00002e75  66450f57c0        xorpd      xmm8, xmm8
00002e7a  4531ed            xor        r13d, r13d
00002e7d  4c8b7d10          mov        r15, qword ptr [rbp + 0x10]
00002e81  6666666666662e0f1f840000000000  nop        word ptr cs:[rax + rax]
00002e90  4d8d96ff1f0000    lea        r10, [r14 + 0x1fff]
00002e97  4d85f6            test       r14, r14
00002e9a  4d0f49d6          cmovns     r10, r14
00002e9e  49c1ea0d          shr        r10, 0xd
00002ea2  807d3800          cmp        byte ptr [rbp + 0x38], 0
00002ea6  0f8494000000      je         0x2f40
00002eac  4539e2            cmp        r10d, r12d
00002eaf  0f286dc0          movaps     xmm5, xmmword ptr [rbp - 0x40]
00002eb3  7e34              jle        0x2ee9
00002eb5  4101d2            add        r10d, edx
00002eb8  4d63f2            movsxd     r14, r10d
00002ebb  49c1e60d          shl        r14, 0xd
00002ebf  450f28cd          movaps     xmm9, xmm13
00002ec3  440fc2ca02        cmpleps    xmm9, xmm2
00002ec8  440f28d5          movaps     xmm10, xmm5
00002ecc  440fc2d202        cmpleps    xmm10, xmm2
00002ed1  450f54d1          andps      xmm10, xmm9
00002ed5  66440f7ed0        movd       eax, xmm10
00002eda  450fb6ed          movzx      r13d, r13b
00002ede  a801              test       al, 1
00002ee0  b801000000        mov        eax, 1
00002ee5  440f45e8          cmovne     r13d, eax
00002ee9  418d4202          lea        eax, [r10 + 2]
00002eed  418d4a03          lea        ecx, [r10 + 3]
00002ef1  4539e2            cmp        r10d, r12d
00002ef4  4189d1            mov        r9d, edx
00002ef7  41b800000000      mov        r8d, 0
00002efd  450f4cc8          cmovl      r9d, r8d
00002f01  4439e0            cmp        eax, r12d
00002f04  b800000000        mov        eax, 0
00002f09  0f4fc2            cmovg      eax, edx
00002f0c  478d040a          lea        r8d, [r10 + r9]
00002f10  41ffc0            inc        r8d
00002f13  458d1c02          lea        r11d, [r10 + rax]
00002f17  4183c302          add        r11d, 2
00002f1b  4439e1            cmp        ecx, r12d
00002f1e  b800000000        mov        eax, 0
00002f23  0f4fc2            cmovg      eax, edx
00002f26  4401d0            add        eax, r10d
00002f29  83c003            add        eax, 3
00002f2c  4d89f1            mov        r9, r14
00002f2f  eb22              jmp        0x2f53
00002f31  6666666666662e0f1f840000000000  nop        word ptr cs:[rax + rax]
00002f40  458d4201          lea        r8d, [r10 + 1]
00002f44  458d5a02          lea        r11d, [r10 + 2]
00002f48  418d4203          lea        eax, [r10 + 3]
00002f4c  4d89f1            mov        r9, r14
00002f4f  0f286dc0          movaps     xmm5, xmmword ptr [rbp - 0x40]
00002f53  4539e2            cmp        r10d, r12d
00002f56  450f4dd4          cmovge     r10d, r12d
00002f5a  450fb6ed          movzx      r13d, r13b
00002f5e  b901000000        mov        ecx, 1
00002f63  440f4fe9          cmovg      r13d, ecx
00002f67  4539e0            cmp        r8d, r12d
00002f6a  450f4fc2          cmovg      r8d, r10d
00002f6e  4539e3            cmp        r11d, r12d
00002f71  450f4fd8          cmovg      r11d, r8d
00002f75  4439e0            cmp        eax, r12d
00002f78  410f4fc3          cmovg      eax, r11d
00002f7c  450f57c9          xorps      xmm9, xmm9
00002f80  f3450f2aca        cvtsi2ss   xmm9, r10d
00002f85  8b4d40            mov        ecx, dword ptr [rbp + 0x40]
00002f88  41d3e2            shl        r10d, cl
00002f8b  41d3e0            shl        r8d, cl
00002f8e  41d3e3            shl        r11d, cl
00002f91  d3e0              shl        eax, cl
00002f93  450f57db          xorps      xmm11, xmm11
00002f97  f34d0f2ad9        cvtsi2ss   xmm11, r9
00002f9c  f3440f591dc7ddffff  mulss      xmm11, dword ptr [rip - 0x2239]
00002fa5  f3450f5cd9        subss      xmm11, xmm9
00002faa  450f28d3          movaps     xmm10, xmm11
00002fae  f3450f59d3        mulss      xmm10, xmm11
00002fb3  450f28cb          movaps     xmm9, xmm11
00002fb7  f3450f59ca        mulss      xmm9, xmm10
00002fbc  66410f28e4        movapd     xmm4, xmm12
00002fc1  0f2eea            ucomiss    xmm5, xmm2
00002fc4  0f86eb000000      jbe        0x30b5
00002fca  4963ca            movsxd     rcx, r10d
00002fcd  4c8b7590          mov        r14, qword ptr [rbp - 0x70]
00002fd1  410fbf0c4e        movsx      ecx, word ptr [r14 + rcx*2]
00002fd6  f3440f2af9        cvtsi2ss   xmm15, ecx
00002fdb  4963c8            movsxd     rcx, r8d
00002fde  410fbf0c4e        movsx      ecx, word ptr [r14 + rcx*2]
00002fe3  410f28ed          movaps     xmm5, xmm13
00002fe7  f3440f2ae9        cvtsi2ss   xmm13, ecx
00002fec  4963cb            movsxd     rcx, r11d
00002fef  410fbf0c4e        movsx      ecx, word ptr [r14 + rcx*2]
00002ff4  450f57e4          xorps      xmm12, xmm12
00002ff8  f3440f2ae1        cvtsi2ss   xmm12, ecx
00002ffd  4863c8            movsxd     rcx, eax
00003000  410fbf0c4e        movsx      ecx, word ptr [r14 + rcx*2]
00003005  f3440f2af1        cvtsi2ss   xmm14, ecx
0000300a  410f28cc          movaps     xmm1, xmm12
0000300e  f3410f5ccf        subss      xmm1, xmm15
00003013  f3410f59cb        mulss      xmm1, xmm11
00003018  410f28dd          movaps     xmm3, xmm13
0000301c  f3410f58dd        addss      xmm3, xmm13
00003021  f30f58d9          addss      xmm3, xmm1
00003025  410f28cd          movaps     xmm1, xmm13
00003029  f30f590d3fddffff  mulss      xmm1, dword ptr [rip - 0x22c1]
00003031  f3440f592d4eddffff  mulss      xmm13, dword ptr [rip - 0x22b2]
0000303a  f3450f5cef        subss      xmm13, xmm15
0000303f  f3450f58ff        addss      xmm15, xmm15
00003044  f3440f58f9        addss      xmm15, xmm1
00003049  410f28cc          movaps     xmm1, xmm12
0000304d  f30f590d27ddffff  mulss      xmm1, dword ptr [rip - 0x22d9]
00003055  f3410f58cf        addss      xmm1, xmm15
0000305a  f3410f5cce        subss      xmm1, xmm14
0000305f  f3410f59ca        mulss      xmm1, xmm10
00003064  f30f58cb          addss      xmm1, xmm3
00003068  f3440f59e6        mulss      xmm12, xmm6
0000306d  f3450f58e5        addss      xmm12, xmm13
00003072  440f28ed          movaps     xmm13, xmm5
00003076  f3450f58e6        addss      xmm12, xmm14
0000307b  0f286dc0          movaps     xmm5, xmmword ptr [rbp - 0x40]
0000307f  f3450f59e1        mulss      xmm12, xmm9
00003084  4c8b7580          mov        r14, qword ptr [rbp - 0x80]
00003088  0f57db            xorps      xmm3, xmm3
0000308b  f3490f2a5cfef8    cvtsi2ss   xmm3, qword ptr [r14 + rdi*8 - 8]
00003092  f3440f58e1        addss      xmm12, xmm1
00003097  f3440f59e7        mulss      xmm12, xmm7
0000309c  f3440f59e5        mulss      xmm12, xmm5
000030a1  f3440f58e3        addss      xmm12, xmm3
000030a6  f3490f2ccc        cvttss2si  rcx, xmm12
000030ab  49894cfef8        mov        qword ptr [r14 + rdi*8 - 8], rcx
000030b0  f30f586db0        addss      xmm5, dword ptr [rbp - 0x50]
000030b5  440f2eea          ucomiss    xmm13, xmm2
000030b9  0f296dc0          movaps     xmmword ptr [rbp - 0x40], xmm5
000030bd  0f8603010000      jbe        0x31c6
000030c3  448b7548          mov        r14d, dword ptr [rbp + 0x48]
000030c7  4501f2            add        r10d, r14d
000030ca  4963ca            movsxd     rcx, r10d
000030cd  4c8b5590          mov        r10, qword ptr [rbp - 0x70]
000030d1  410fbf0c4a        movsx      ecx, word ptr [r10 + rcx*2]
000030d6  450f57f6          xorps      xmm14, xmm14
000030da  f3440f2af1        cvtsi2ss   xmm14, ecx
000030df  4501f0            add        r8d, r14d
000030e2  4963c8            movsxd     rcx, r8d
000030e5  410fbf0c4a        movsx      ecx, word ptr [r10 + rcx*2]
000030ea  410f28ed          movaps     xmm5, xmm13
000030ee  450f57ed          xorps      xmm13, xmm13
000030f2  f3440f2ae9        cvtsi2ss   xmm13, ecx
000030f7  4501f3            add        r11d, r14d
000030fa  4963cb            movsxd     rcx, r11d
000030fd  410fbf0c4a        movsx      ecx, word ptr [r10 + rcx*2]
00003102  450f57e4          xorps      xmm12, xmm12
00003106  f3440f2ae1        cvtsi2ss   xmm12, ecx
0000310b  4401f0            add        eax, r14d
0000310e  4898              cdqe       
00003110  410fbf0442        movsx      eax, word ptr [r10 + rax*2]
00003115  450f57ff          xorps      xmm15, xmm15
00003119  f3440f2af8        cvtsi2ss   xmm15, eax
0000311e  410f28cc          movaps     xmm1, xmm12
00003122  f3410f5cce        subss      xmm1, xmm14
00003127  f3440f59d9        mulss      xmm11, xmm1
0000312c  410f28cd          movaps     xmm1, xmm13
00003130  f3410f58cd        addss      xmm1, xmm13
00003135  f3410f58cb        addss      xmm1, xmm11
0000313a  410f28dd          movaps     xmm3, xmm13
0000313e  f30f591d2adcffff  mulss      xmm3, dword ptr [rip - 0x23d6]
00003146  f3440f592d39dcffff  mulss      xmm13, dword ptr [rip - 0x23c7]
0000314f  f3450f5cee        subss      xmm13, xmm14
00003154  f3450f58f6        addss      xmm14, xmm14
00003159  f3440f58f3        addss      xmm14, xmm3
0000315e  410f28dc          movaps     xmm3, xmm12
00003162  f30f591d12dcffff  mulss      xmm3, dword ptr [rip - 0x23ee]
0000316a  f3410f58de        addss      xmm3, xmm14
0000316f  f3410f5cdf        subss      xmm3, xmm15
00003174  f3410f59da        mulss      xmm3, xmm10
00003179  f30f58d9          addss      xmm3, xmm1
0000317d  f3440f59e6        mulss      xmm12, xmm6
00003182  f3450f58e5        addss      xmm12, xmm13
00003187  440f28ed          movaps     xmm13, xmm5
0000318b  f3450f58e7        addss      xmm12, xmm15
00003190  f3450f59e1        mulss      xmm12, xmm9
00003195  488b4d88          mov        rcx, qword ptr [rbp - 0x78]
00003199  0f57c9            xorps      xmm1, xmm1
0000319c  f3480f2a4cf9f8    cvtsi2ss   xmm1, qword ptr [rcx + rdi*8 - 8]
000031a3  f3440f58e3        addss      xmm12, xmm3
000031a8  f3440f59e7        mulss      xmm12, xmm7
000031ad  f3440f59e5        mulss      xmm12, xmm5
000031b2  f3440f58e1        addss      xmm12, xmm1
000031b7  f3490f2cc4        cvttss2si  rax, xmm12
000031bc  488944f9f8        mov        qword ptr [rcx + rdi*8 - 8], rax
000031c1  f3440f58e8        addss      xmm13, xmm0
000031c6  448b6528          mov        r12d, dword ptr [rbp + 0x28]
000031ca  66440f28e4        movapd     xmm12, xmm4
000031cf  f20f104db8        movsd      xmm1, qword ptr [rbp - 0x48]
000031d4  f2440f58e1        addsd      xmm12, xmm1
000031d9  66450f2ee0        ucomisd    xmm12, xmm8
000031de  0f57c9            xorps      xmm1, xmm1
000031e1  f2490f2ac9        cvtsi2sd   xmm1, r9
000031e6  f2410f58cc        addsd      xmm1, xmm12
000031eb  f24c0f2cf1        cvttsd2si  r14, xmm1
000031f0  4d0f46f1          cmovbe     r14, r9
000031f4  4839f7            cmp        rdi, rsi
000031f7  0f83fa010000      jae        0x33f7
000031fd  4489e8            mov        eax, r13d
00003200  2401              and        al, 1
00003202  48ffc7            inc        rdi
00003205  84c0              test       al, al
00003207  0f8483fcffff      je         0x2e90
0000320d  e9e5010000        jmp        0x33f7
00003212  4531ed            xor        r13d, r13d
00003215  e9d9010000        jmp        0x33f3
0000321a  85c9              test       ecx, ecx
0000321c  0f8e14010000      jle        0x3336
00003222  48634530          movsxd     rax, dword ptr [rbp + 0x30]
00003226  48c1e00d          shl        rax, 0xd
0000322a  89ca              mov        edx, ecx
0000322c  be01000000        mov        esi, 1
00003231  31ff              xor        edi, edi
00003233  41b801000000      mov        r8d, 1
00003239  4c8d0d30880000    lea        r9, [rip + 0x8830]
00003240  4c8d1529080100    lea        r10, [rip + 0x10829]
00003247  4531ed            xor        r13d, r13d
0000324a  660f1f440000      nop        word ptr [rax + rax]
00003250  4d8d9eff1f0000    lea        r11, [r14 + 0x1fff]
00003257  4d85f6            test       r14, r14
0000325a  4d0f49de          cmovns     r11, r14
0000325e  49c1eb0d          shr        r11, 0xd
00003262  410f28d5          movaps     xmm2, xmm13
00003266  0fc2d102          cmpleps    xmm2, xmm1
0000326a  0f28dd            movaps     xmm3, xmm5
0000326d  0fc2d902          cmpleps    xmm3, xmm1
00003271  0f54da            andps      xmm3, xmm2
00003274  660f7ed9          movd       ecx, xmm3
00003278  f6c101            test       cl, 1
0000327b  410fb6cd          movzx      ecx, r13b
0000327f  4189cd            mov        r13d, ecx
00003282  450f45e8          cmovne     r13d, r8d
00003286  807d3800          cmp        byte ptr [rbp + 0x38], 0
0000328a  450f44e8          cmove      r13d, r8d
0000328e  410f94c7          sete       r15b
00003292  4539e3            cmp        r11d, r12d
00003295  450f4ddc          cmovge     r11d, r12d
00003299  410f9ec4          setle      r12b
0000329d  440f4ee9          cmovle     r13d, ecx
000032a1  4508fc            or         r12b, r15b
000032a4  7509              jne        0x32af
000032a6  4989c6            mov        r14, rax
000032a9  8b4d30            mov        ecx, dword ptr [rbp + 0x30]
000032ac  4189cb            mov        r11d, ecx
000032af  8b4d40            mov        ecx, dword ptr [rbp + 0x40]
000032b2  41d3e3            shl        r11d, cl
000032b5  0f2ee9            ucomiss    xmm5, xmm1
000032b8  7625              jbe        0x32df
000032ba  4963cb            movsxd     rcx, r11d
000032bd  4c8b7d90          mov        r15, qword ptr [rbp - 0x70]
000032c1  410fbf0c4f        movsx      ecx, word ptr [r15 + rcx*2]
000032c6  0f57d2            xorps      xmm2, xmm2
000032c9  f30f2ad1          cvtsi2ss   xmm2, ecx
000032cd  f30f59d5          mulss      xmm2, xmm5
000032d1  f3480f2cca        cvttss2si  rcx, xmm2
000032d6  4a890c0f          mov        qword ptr [rdi + r9], rcx
000032da  f30f586db0        addss      xmm5, dword ptr [rbp - 0x50]
000032df  448b6528          mov        r12d, dword ptr [rbp + 0x28]
000032e3  440f2ee9          ucomiss    xmm13, xmm1
000032e7  762a              jbe        0x3313
000032e9  44035d48          add        r11d, dword ptr [rbp + 0x48]
000032ed  4963cb            movsxd     rcx, r11d
000032f0  4c8b5d90          mov        r11, qword ptr [rbp - 0x70]
000032f4  410fbf0c4b        movsx      ecx, word ptr [r11 + rcx*2]
000032f9  0f57d2            xorps      xmm2, xmm2
000032fc  f30f2ad1          cvtsi2ss   xmm2, ecx
00003300  f3410f59d5        mulss      xmm2, xmm13
00003305  f3480f2cca        cvttss2si  rcx, xmm2
0000330a  4a890c17          mov        qword ptr [rdi + r10], rcx
0000330e  f3440f58e8        addss      xmm13, xmm0
00003313  4981c600200000    add        r14, 0x2000
0000331a  4839d6            cmp        rsi, rdx
0000331d  731a              jae        0x3339
0000331f  4489e9            mov        ecx, r13d
00003322  80e101            and        cl, 1
00003325  4883c708          add        rdi, 8
00003329  48ffc6            inc        rsi
0000332c  84c9              test       cl, cl
0000332e  0f841cffffff      je         0x3250
00003334  eb03              jmp        0x3339
00003336  4531ed            xor        r13d, r13d
00003339  0f296dc0          movaps     xmmword ptr [rbp - 0x40], xmm5
0000333d  488b8558ffffff    mov        rax, qword ptr [rbp - 0xa8]
00003344  4885c0            test       rax, rax
00003347  448b6520          mov        r12d, dword ptr [rbp + 0x20]
0000334b  0f8491000000      je         0x33e2
00003351  f20f100507daffff  movsd      xmm0, qword ptr [rip - 0x25f9]
00003359  f2410f59c4        mulsd      xmm0, xmm12
0000335e  f20f5ac0          cvtsd2ss   xmm0, xmm0
00003362  f30f1145b0        movss      dword ptr [rbp - 0x50], xmm0
00003367  4d63fc            movsxd     r15, r12d
0000336a  4c8d05ff860000    lea        r8, [rip + 0x86ff]
00003371  f30f100d0bdaffff  movss      xmm1, dword ptr [rip - 0x25f5]
00003379  ba00040000        mov        edx, 0x400
0000337e  b904000000        mov        ecx, 4
00003383  4989c4            mov        r12, rax
00003386  4889c7            mov        rdi, rax
00003389  4c89fe            mov        rsi, r15
0000338c  4c8b4d80          mov        r9, qword ptr [rbp - 0x80]
00003390  440f296d90        movaps     xmmword ptr [rbp - 0x70], xmm13
00003395  e826040000        call       0x37c0
0000339a  4981c410b00000    add        r12, 0xb010
000033a1  4c8d05c8060100    lea        r8, [rip + 0x106c8]
000033a8  ba00040000        mov        edx, 0x400
000033ad  b904000000        mov        ecx, 4
000033b2  4c89e7            mov        rdi, r12
000033b5  f30f1045b0        movss      xmm0, dword ptr [rbp - 0x50]
000033ba  4c89fe            mov        rsi, r15
000033bd  f30f100dbfd9ffff  movss      xmm1, dword ptr [rip - 0x2641]
000033c5  4c8b4d88          mov        r9, qword ptr [rbp - 0x78]
000033c9  e8f2030000        call       0x37c0
000033ce  448b6520          mov        r12d, dword ptr [rbp + 0x20]
000033d2  440f286d90        movaps     xmm13, xmmword ptr [rbp - 0x70]
000033d7  f20f1065b8        movsd      xmm4, qword ptr [rbp - 0x48]
000033dc  f2440f1065a8      movsd      xmm12, qword ptr [rbp - 0x58]
000033e2  0f57c0            xorps      xmm0, xmm0
000033e5  f2410f2ac4        cvtsi2sd   xmm0, r12d
000033ea  f20f59e0          mulsd      xmm4, xmm0
000033ee  f2440f58e4        addsd      xmm12, xmm4
000033f3  4c8b7d10          mov        r15, qword ptr [rbp + 0x10]
000033f7  440f296d90        movaps     xmmword ptr [rbp - 0x70], xmm13
000033fc  488b03            mov        rax, qword ptr [rbx]
000033ff  4889df            mov        rdi, rbx
00003402  4c8ba560ffffff    mov        r12, qword ptr [rbp - 0xa0]
00003409  4c89e6            mov        rsi, r12
0000340c  488b9570ffffff    mov        rdx, qword ptr [rbp - 0x90]
00003413  66410f28c4        movapd     xmm0, xmm12
00003418  ff9080030000      call       qword ptr [rax + 0x380]
0000341e  488b03            mov        rax, qword ptr [rbx]
00003421  4889df            mov        rdi, rbx
00003424  4c89e6            mov        rsi, r12
00003427  488b9548ffffff    mov        rdx, qword ptr [rbp - 0xb8]
0000342e  0f2845c0          movaps     xmm0, xmmword ptr [rbp - 0x40]
00003432  ff9078030000      call       qword ptr [rax + 0x378]
00003438  488b03            mov        rax, qword ptr [rbx]
0000343b  4889df            mov        rdi, rbx
0000343e  4c89e6            mov        rsi, r12
00003441  488b9550ffffff    mov        rdx, qword ptr [rbp - 0xb0]
00003448  0f284590          movaps     xmm0, xmmword ptr [rbp - 0x70]
0000344c  ff9078030000      call       qword ptr [rax + 0x378]
00003452  488b03            mov        rax, qword ptr [rbx]
00003455  4889df            mov        rdi, rbx
00003458  4c89e6            mov        rsi, r12
0000345b  488b9540ffffff    mov        rdx, qword ptr [rbp - 0xc0]
00003462  4c89f1            mov        rcx, r14
00003465  ff9070030000      call       qword ptr [rax + 0x370]
0000346b  488b03            mov        rax, qword ptr [rbx]
0000346e  4889df            mov        rdi, rbx
00003471  4c89fe            mov        rsi, r15
00003474  488b5580          mov        rdx, qword ptr [rbp - 0x80]
00003478  31c9              xor        ecx, ecx
0000347a  ff9020060000      call       qword ptr [rax + 0x620]
00003480  488b03            mov        rax, qword ptr [rbx]
00003483  4889df            mov        rdi, rbx
00003486  488b7518          mov        rsi, qword ptr [rbp + 0x18]
0000348a  488b5588          mov        rdx, qword ptr [rbp - 0x78]
0000348e  31c9              xor        ecx, ecx
00003490  ff9020060000      call       qword ptr [rax + 0x620]
00003496  4180e501          and        r13b, 1
0000349a  4489e8            mov        eax, r13d
0000349d  4881c498000000    add        rsp, 0x98
000034a4  5b                pop        rbx
000034a5  415c              pop        r12
000034a7  415d              pop        r13
000034a9  415e              pop        r14
000034ab  415f              pop        r15
000034ad  5d                pop        rbp
000034ae  c3                ret        
