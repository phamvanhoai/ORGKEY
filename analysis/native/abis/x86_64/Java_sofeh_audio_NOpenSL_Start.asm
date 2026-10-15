# APK member: lib/x86_64/libnopensl.so
# Library SHA-256: 1cfbadb3f31bae35f1aa6bc7a994af95d242e75b680f26a661a4b66c2a61f23f
# Function: Java_sofeh_audio_NOpenSL_Start; address=0x1a80; bytes=2598
# Decoder mode: default; decoded_bytes=2598
00001a80  55                push       rbp
00001a81  4889e5            mov        rbp, rsp
00001a84  4157              push       r15
00001a86  4156              push       r14
00001a88  4155              push       r13
00001a8a  4154              push       r12
00001a8c  53                push       rbx
00001a8d  4881ecf8000000    sub        rsp, 0xf8
00001a94  4589cf            mov        r15d, r9d
00001a97  4589c4            mov        r12d, r8d
00001a9a  898de8feffff      mov        dword ptr [rbp - 0x118], ecx
00001aa0  4989d5            mov        r13, rdx
00001aa3  4889fb            mov        rbx, rdi
00001aa6  440fb67510        movzx      r14d, byte ptr [rbp + 0x10]
00001aab  64488b042528000000  mov        rax, qword ptr fs:[0x28]
00001ab4  488945d0          mov        qword ptr [rbp - 0x30], rax
00001ab8  488d3da1151100    lea        rdi, [rip + 0x1115a1]
00001abf  e89c120000        call       0x2d60
00001ac4  c7057215010001000000  mov        dword ptr [rip + 0x11572], 1
00001ace  4d85ed            test       r13, r13
00001ad1  0f94c0            sete       al
00001ad4  488b0dad151100    mov        rcx, qword ptr [rip + 0x1115ad]
00001adb  40b601            mov        sil, 1
00001ade  4885c9            test       rcx, rcx
00001ae1  743a              je         0x1b1d
00001ae3  3805a7151100      cmp        byte ptr [rip + 0x1115a7], al
00001ae9  7532              jne        0x1b1d
00001aeb  8b95e8feffff      mov        edx, dword ptr [rbp - 0x118]
00001af1  39159d151100      cmp        dword ptr [rip + 0x11159d], edx
00001af7  7524              jne        0x1b1d
00001af9  44392598151100    cmp        dword ptr [rip + 0x111598], r12d
00001b00  751b              jne        0x1b1d
00001b02  44393d93151100    cmp        dword ptr [rip + 0x111593], r15d
00001b09  7512              jne        0x1b1d
00001b0b  31d2              xor        edx, edx
00001b0d  4584f6            test       r14b, r14b
00001b10  0f95c2            setne      dl
00001b13  391587151100      cmp        dword ptr [rip + 0x111587], edx
00001b19  400f95c6          setne      sil
00001b1d  4885c9            test       rcx, rcx
00001b20  0f94c1            sete       cl
00001b23  31d2              xor        edx, edx
00001b25  4584f6            test       r14b, r14b
00001b28  0f95c2            setne      dl
00001b2b  88055f151100      mov        byte ptr [rip + 0x11155f], al
00001b31  8b85e8feffff      mov        eax, dword ptr [rbp - 0x118]
00001b37  890557151100      mov        dword ptr [rip + 0x111557], eax
00001b3d  44892554151100    mov        dword ptr [rip + 0x111554], r12d
00001b44  44893d51151100    mov        dword ptr [rip + 0x111551], r15d
00001b4b  89154f151100      mov        dword ptr [rip + 0x11154f], edx
00001b51  4088b5effeffff    mov        byte ptr [rbp - 0x111], sil
00001b58  89f0              mov        eax, esi
00001b5a  3401              xor        al, 1
00001b5c  08c8              or         al, cl
00001b5e  0f85be000000      jne        0x1c22
00001b64  c705d214010003000000  mov        dword ptr [rip + 0x114d2], 3
00001b6e  c6052f15110000    mov        byte ptr [rip + 0x11152f], 0
00001b75  488b3d2c151100    mov        rdi, qword ptr [rip + 0x11152c]
00001b7c  4885ff            test       rdi, rdi
00001b7f  740a              je         0x1b8b
00001b81  488b07            mov        rax, qword ptr [rdi]
00001b84  be01000000        mov        esi, 1
00001b89  ff10              call       qword ptr [rax]
00001b8b  488b3d1e151100    mov        rdi, qword ptr [rip + 0x11151e]
00001b92  4885ff            test       rdi, rdi
00001b95  7406              je         0x1b9d
00001b97  488b07            mov        rax, qword ptr [rdi]
00001b9a  ff5008            call       qword ptr [rax + 8]
00001b9d  488b3d14151100    mov        rdi, qword ptr [rip + 0x111514]
00001ba4  4885ff            test       rdi, rdi
00001ba7  740a              je         0x1bb3
00001ba9  488b07            mov        rax, qword ptr [rdi]
00001bac  be01000000        mov        esi, 1
00001bb1  ff10              call       qword ptr [rax]
00001bb3  488b3d96140900    mov        rdi, qword ptr [rip + 0x91496]
00001bba  4885ff            test       rdi, rdi
00001bbd  7406              je         0x1bc5
00001bbf  488b07            mov        rax, qword ptr [rdi]
00001bc2  ff5008            call       qword ptr [rax + 8]
00001bc5  488d3d94141100    lea        rdi, [rip + 0x111494]
00001bcc  e89f110000        call       0x2d70
00001bd1  833de814110000    cmp        dword ptr [rip + 0x1114e8], 0
00001bd8  7e29              jle        0x1c03
00001bda  4531f6            xor        r14d, r14d
00001bdd  0f1f00            nop        dword ptr [rax]
00001be0  bfe8030000        mov        edi, 0x3e8
00001be5  e826110000        call       0x2d10
00001bea  833dcf14110000    cmp        dword ptr [rip + 0x1114cf], 0
00001bf1  7e10              jle        0x1c03
00001bf3  418d4601          lea        eax, [r14 + 1]
00001bf7  4181fee7030000    cmp        r14d, 0x3e7
00001bfe  4189c6            mov        r14d, eax
00001c01  72dd              jb         0x1be0
00001c03  488d3d56141100    lea        rdi, [rip + 0x111456]
00001c0a  e851110000        call       0x2d60
00001c0f  4889df            mov        rdi, rbx
00001c12  e899080000        call       0x24b0
00001c17  48c705a614110000000000  mov        qword ptr [rip + 0x1114a6], 0
00001c22  488b35a7141100    mov        rsi, qword ptr [rip + 0x1114a7]
00001c29  4885f6            test       rsi, rsi
00001c2c  7417              je         0x1c45
00001c2e  488b03            mov        rax, qword ptr [rbx]
00001c31  4889df            mov        rdi, rbx
00001c34  ff90b0000000      call       qword ptr [rax + 0xb0]
00001c3a  48c7058b14110000000000  mov        qword ptr [rip + 0x11148b], 0
00001c45  488b358c141100    mov        rsi, qword ptr [rip + 0x11148c]
00001c4c  4885f6            test       rsi, rsi
00001c4f  7417              je         0x1c68
00001c51  488b03            mov        rax, qword ptr [rbx]
00001c54  4889df            mov        rdi, rbx
00001c57  ff90b0000000      call       qword ptr [rax + 0xb0]
00001c5d  48c7057014110000000000  mov        qword ptr [rip + 0x111470], 0
00001c68  48c7056d14110000000000  mov        qword ptr [rip + 0x11146d], 0
00001c73  48c7056a14110000000000  mov        qword ptr [rip + 0x11146a], 0
00001c7e  4d85ed            test       r13, r13
00001c81  0f8488000000      je         0x1d0f
00001c87  488b03            mov        rax, qword ptr [rbx]
00001c8a  488d3557141100    lea        rsi, [rip + 0x111457]
00001c91  4889df            mov        rdi, rbx
00001c94  ff90d8060000      call       qword ptr [rax + 0x6d8]
00001c9a  488b03            mov        rax, qword ptr [rbx]
00001c9d  4889df            mov        rdi, rbx
00001ca0  4c89ee            mov        rsi, r13
00001ca3  ff90a8000000      call       qword ptr [rax + 0xa8]
00001ca9  48890520141100    mov        qword ptr [rip + 0x111420], rax
00001cb0  488b03            mov        rax, qword ptr [rbx]
00001cb3  4889df            mov        rdi, rbx
00001cb6  4c89ee            mov        rsi, r13
00001cb9  ff90f8000000      call       qword ptr [rax + 0xf8]
00001cbf  4989c5            mov        r13, rax
00001cc2  488b03            mov        rax, qword ptr [rbx]
00001cc5  4889df            mov        rdi, rbx
00001cc8  4c89ee            mov        rsi, r13
00001ccb  ff90a8000000      call       qword ptr [rax + 0xa8]
00001cd1  48890500141100    mov        qword ptr [rip + 0x111400], rax
00001cd8  488b03            mov        rax, qword ptr [rbx]
00001cdb  4889df            mov        rdi, rbx
00001cde  4c89ee            mov        rsi, r13
00001ce1  ff90b8000000      call       qword ptr [rax + 0xb8]
00001ce7  488b03            mov        rax, qword ptr [rbx]
00001cea  488b35e7131100    mov        rsi, qword ptr [rip + 0x1113e7]
00001cf1  488d15fcedffff    lea        rdx, [rip - 0x1204]
00001cf8  488d0df1edffff    lea        rcx, [rip - 0x120f]
00001cff  4889df            mov        rdi, rbx
00001d02  ff9008010000      call       qword ptr [rax + 0x108]
00001d08  488905d1131100    mov        qword ptr [rip + 0x1113d1], rax
00001d0f  c7050b13010000000000  mov        dword ptr [rip + 0x1130b], 0
00001d19  c705fd12010000000000  mov        dword ptr [rip + 0x112fd], 0
00001d23  c7050713010000000000  mov        dword ptr [rip + 0x11307], 0
00001d2d  c7050113010000000000  mov        dword ptr [rip + 0x11301], 0
00001d37  c7050313010000000000  mov        dword ptr [rip + 0x11303], 0
00001d41  c705a513110000000000  mov        dword ptr [rip + 0x1113a5], 0
00001d4b  4501e4            add        r12d, r12d
00001d4e  4181fc00400000    cmp        r12d, 0x4000
00001d55  b800400000        mov        eax, 0x4000
00001d5a  410f4cc4          cmovl      eax, r12d
00001d5e  8905cc120100      mov        dword ptr [rip + 0x112cc], eax
00001d64  4183ff04          cmp        r15d, 4
00001d68  b903000000        mov        ecx, 3
00001d6d  410f4dcf          cmovge     ecx, r15d
00001d71  83f910            cmp        ecx, 0x10
00001d74  41bd10000000      mov        r13d, 0x10
00001d7a  440f42e9          cmovb      r13d, ecx
00001d7e  c605b712010001    mov        byte ptr [rip + 0x112b7], 1
00001d85  44892d84920000    mov        dword ptr [rip + 0x9284], r13d
00001d8c  4c63e0            movsxd     r12, eax
00001d8f  4d01e4            add        r12, r12
00001d92  4c8d3db7120100    lea        r15, [rip + 0x112b7]
00001d99  4c89ff            mov        rdi, r15
00001d9c  31f6              xor        esi, esi
00001d9e  4c89e2            mov        rdx, r12
00001da1  e8da0f0000        call       0x2d80
00001da6  488d3db3120900    lea        rdi, [rip + 0x912b3]
00001dad  31f6              xor        esi, esi
00001daf  4c89e2            mov        rdx, r12
00001db2  e8c90f0000        call       0x2d80
00001db7  488d3d92920100    lea        rdi, [rip + 0x19292]
00001dbe  31f6              xor        esi, esi
00001dc0  4c89e2            mov        rdx, r12
00001dc3  e8b80f0000        call       0x2d80
00001dc8  488d3d91920900    lea        rdi, [rip + 0x99291]
00001dcf  31f6              xor        esi, esi
00001dd1  4c89e2            mov        rdx, r12
00001dd4  e8a70f0000        call       0x2d80
00001dd9  488d3d70120200    lea        rdi, [rip + 0x21270]
00001de0  31f6              xor        esi, esi
00001de2  4c89e2            mov        rdx, r12
00001de5  e8960f0000        call       0x2d80
00001dea  488d3d6f120a00    lea        rdi, [rip + 0xa126f]
00001df1  31f6              xor        esi, esi
00001df3  4c89e2            mov        rdx, r12
00001df6  e8850f0000        call       0x2d80
00001dfb  488d3d4e920200    lea        rdi, [rip + 0x2924e]
00001e02  31f6              xor        esi, esi
00001e04  4c89e2            mov        rdx, r12
00001e07  e8740f0000        call       0x2d80
00001e0c  488d3d4d920a00    lea        rdi, [rip + 0xa924d]
00001e13  31f6              xor        esi, esi
00001e15  4c89e2            mov        rdx, r12
00001e18  e8630f0000        call       0x2d80
00001e1d  488d3d2c120300    lea        rdi, [rip + 0x3122c]
00001e24  31f6              xor        esi, esi
00001e26  4c89e2            mov        rdx, r12
00001e29  e8520f0000        call       0x2d80
00001e2e  488d3d2b120b00    lea        rdi, [rip + 0xb122b]
00001e35  31f6              xor        esi, esi
00001e37  4c89e2            mov        rdx, r12
00001e3a  e8410f0000        call       0x2d80
00001e3f  488d3d0a920300    lea        rdi, [rip + 0x3920a]
00001e46  31f6              xor        esi, esi
00001e48  4c89e2            mov        rdx, r12
00001e4b  e8300f0000        call       0x2d80
00001e50  488d3d09920b00    lea        rdi, [rip + 0xb9209]
00001e57  31f6              xor        esi, esi
00001e59  4c89e2            mov        rdx, r12
00001e5c  e81f0f0000        call       0x2d80
00001e61  488d3de8110400    lea        rdi, [rip + 0x411e8]
00001e68  31f6              xor        esi, esi
00001e6a  4c89e2            mov        rdx, r12
00001e6d  e80e0f0000        call       0x2d80
00001e72  488d3de7110c00    lea        rdi, [rip + 0xc11e7]
00001e79  31f6              xor        esi, esi
00001e7b  4c89e2            mov        rdx, r12
00001e7e  e8fd0e0000        call       0x2d80
00001e83  488d3dc6910400    lea        rdi, [rip + 0x491c6]
00001e8a  31f6              xor        esi, esi
00001e8c  4c89e2            mov        rdx, r12
00001e8f  e8ec0e0000        call       0x2d80
00001e94  488d3dc5910c00    lea        rdi, [rip + 0xc91c5]
00001e9b  31f6              xor        esi, esi
00001e9d  4c89e2            mov        rdx, r12
00001ea0  e8db0e0000        call       0x2d80
00001ea5  488d3da4110500    lea        rdi, [rip + 0x511a4]
00001eac  31f6              xor        esi, esi
00001eae  4c89e2            mov        rdx, r12
00001eb1  e8ca0e0000        call       0x2d80
00001eb6  488d3da3110d00    lea        rdi, [rip + 0xd11a3]
00001ebd  31f6              xor        esi, esi
00001ebf  4c89e2            mov        rdx, r12
00001ec2  e8b90e0000        call       0x2d80
00001ec7  488d3d82910500    lea        rdi, [rip + 0x59182]
00001ece  31f6              xor        esi, esi
00001ed0  4c89e2            mov        rdx, r12
00001ed3  e8a80e0000        call       0x2d80
00001ed8  488d3d81910d00    lea        rdi, [rip + 0xd9181]
00001edf  31f6              xor        esi, esi
00001ee1  4c89e2            mov        rdx, r12
00001ee4  e8970e0000        call       0x2d80
00001ee9  488d3d60110600    lea        rdi, [rip + 0x61160]
00001ef0  31f6              xor        esi, esi
00001ef2  4c89e2            mov        rdx, r12
00001ef5  e8860e0000        call       0x2d80
00001efa  488d3d5f110e00    lea        rdi, [rip + 0xe115f]
00001f01  31f6              xor        esi, esi
00001f03  4c89e2            mov        rdx, r12
00001f06  e8750e0000        call       0x2d80
00001f0b  488d3d3e910600    lea        rdi, [rip + 0x6913e]
00001f12  31f6              xor        esi, esi
00001f14  4c89e2            mov        rdx, r12
00001f17  e8640e0000        call       0x2d80
00001f1c  488d3d3d910e00    lea        rdi, [rip + 0xe913d]
00001f23  31f6              xor        esi, esi
00001f25  4c89e2            mov        rdx, r12
00001f28  e8530e0000        call       0x2d80
00001f2d  488d3d1c110700    lea        rdi, [rip + 0x7111c]
00001f34  31f6              xor        esi, esi
00001f36  4c89e2            mov        rdx, r12
00001f39  e8420e0000        call       0x2d80
00001f3e  488d3d1b110f00    lea        rdi, [rip + 0xf111b]
00001f45  31f6              xor        esi, esi
00001f47  4c89e2            mov        rdx, r12
00001f4a  e8310e0000        call       0x2d80
00001f4f  488d3dfa900700    lea        rdi, [rip + 0x790fa]
00001f56  31f6              xor        esi, esi
00001f58  4c89e2            mov        rdx, r12
00001f5b  e8200e0000        call       0x2d80
00001f60  488d3df9900f00    lea        rdi, [rip + 0xf90f9]
00001f67  31f6              xor        esi, esi
00001f69  4c89e2            mov        rdx, r12
00001f6c  e80f0e0000        call       0x2d80
00001f71  488d3dd8100800    lea        rdi, [rip + 0x810d8]
00001f78  31f6              xor        esi, esi
00001f7a  4c89e2            mov        rdx, r12
00001f7d  e8fe0d0000        call       0x2d80
00001f82  488d3dd7101000    lea        rdi, [rip + 0x1010d7]
00001f89  31f6              xor        esi, esi
00001f8b  4c89e2            mov        rdx, r12
00001f8e  e8ed0d0000        call       0x2d80
00001f93  488d3db6900800    lea        rdi, [rip + 0x890b6]
00001f9a  31f6              xor        esi, esi
00001f9c  4c89e2            mov        rdx, r12
00001f9f  e8dc0d0000        call       0x2d80
00001fa4  488d3db5901000    lea        rdi, [rip + 0x1090b5]
00001fab  31f6              xor        esi, esi
00001fad  4c89e2            mov        rdx, r12
00001fb0  e8cb0d0000        call       0x2d80
00001fb5  488b05cc101100    mov        rax, qword ptr [rip + 0x1110cc]
00001fbc  4885c0            test       rax, rax
00001fbf  0f94c1            sete       cl
00001fc2  088deffeffff      or         byte ptr [rbp - 0x111], cl
00001fc8  0f840b020000      je         0x21d9
00001fce  4885c0            test       rax, rax
00001fd1  0f84a4020000      je         0x227b
00001fd7  c745a4bd070080    mov        dword ptr [rbp - 0x5c], 0x800007bd
00001fde  44896da8          mov        dword ptr [rbp - 0x58], r13d
00001fe2  48b80200000002000000  movabs     rax, 0x200000002
00001fec  48894588          mov        qword ptr [rbp - 0x78], rax
00001ff0  4469a5e8feffffe8030000  imul       r12d, dword ptr [rbp - 0x118], 0x3e8
00001ffb  44896590          mov        dword ptr [rbp - 0x70], r12d
00001fff  0f2805daeaffff    movaps     xmm0, xmmword ptr [rip - 0x1526]
00002006  0f114594          movups     xmmword ptr [rbp - 0x6c], xmm0
0000200a  488d45a4          lea        rax, [rbp - 0x5c]
0000200e  48898578ffffff    mov        qword ptr [rbp - 0x88], rax
00002015  488d4588          lea        rax, [rbp - 0x78]
00002019  48894580          mov        qword ptr [rbp - 0x80], rax
0000201d  c78568ffffff04000000  mov        dword ptr [rbp - 0x98], 4
00002027  488b05d2101100    mov        rax, qword ptr [rip + 0x1110d2]
0000202e  48898570ffffff    mov        qword ptr [rbp - 0x90], rax
00002035  488d8568ffffff    lea        rax, [rbp - 0x98]
0000203c  48898558ffffff    mov        qword ptr [rbp - 0xa8], rax
00002043  48c78560ffffff00000000  mov        qword ptr [rbp - 0xa0], 0
0000204e  488b05134f0000    mov        rax, qword ptr [rip + 0x4f13]
00002055  4c8b38            mov        r15, qword ptr [rax]
00002058  4c897dc0          mov        qword ptr [rbp - 0x40], r15
0000205c  488b050d4f0000    mov        rax, qword ptr [rip + 0x4f0d]
00002063  4c8b30            mov        r14, qword ptr [rax]
00002066  4c8975c8          mov        qword ptr [rbp - 0x38], r14
0000206a  48b80100000001000000  movabs     rax, 0x100000001
00002074  488945b8          mov        qword ptr [rbp - 0x48], rax
00002078  488b3d79101100    mov        rdi, qword ptr [rip + 0x111079]
0000207f  488b07            mov        rax, qword ptr [rdi]
00002082  488d4db8          lea        rcx, [rbp - 0x48]
00002086  48890c24          mov        qword ptr [rsp], rcx
0000208a  488d35970f0100    lea        rsi, [rip + 0x10f97]
00002091  488d9578ffffff    lea        rdx, [rbp - 0x88]
00002098  488d8d58ffffff    lea        rcx, [rbp - 0xa8]
0000209f  4c8d4dc0          lea        r9, [rbp - 0x40]
000020a3  41b802000000      mov        r8d, 2
000020a9  ff5010            call       qword ptr [rax + 0x10]
000020ac  85c0              test       eax, eax
000020ae  0f8518010000      jne        0x21cc
000020b4  488b3d6d0f0100    mov        rdi, qword ptr [rip + 0x10f6d]
000020bb  488b07            mov        rax, qword ptr [rdi]
000020be  31f6              xor        esi, esi
000020c0  ff10              call       qword ptr [rax]
000020c2  85c0              test       eax, eax
000020c4  0f8502010000      jne        0x21cc
000020ca  488b3d570f0100    mov        rdi, qword ptr [rip + 0x10f57]
000020d1  488b07            mov        rax, qword ptr [rdi]
000020d4  488b0d9d4e0000    mov        rcx, qword ptr [rip + 0x4e9d]
000020db  488b31            mov        rsi, qword ptr [rcx]
000020de  488d15c30f1100    lea        rdx, [rip + 0x110fc3]
000020e5  ff5018            call       qword ptr [rax + 0x18]
000020e8  85c0              test       eax, eax
000020ea  0f85dc000000      jne        0x21cc
000020f0  488b3d310f0100    mov        rdi, qword ptr [rip + 0x10f31]
000020f7  488b07            mov        rax, qword ptr [rdi]
000020fa  488d15af0f1100    lea        rdx, [rip + 0x110faf]
00002101  4c89fe            mov        rsi, r15
00002104  ff5018            call       qword ptr [rax + 0x18]
00002107  85c0              test       eax, eax
00002109  0f85bd000000      jne        0x21cc
0000210f  48833dc90f110000  cmp        qword ptr [rip + 0x110fc9], 0
00002117  488d35d2040000    lea        rsi, [rip + 0x4d2]
0000211e  488d051b080000    lea        rax, [rip + 0x81b]
00002125  480f44f0          cmove      rsi, rax
00002129  48833d9f0f110000  cmp        qword ptr [rip + 0x110f9f], 0
00002131  480f44f0          cmove      rsi, rax
00002135  48833dab0f110000  cmp        qword ptr [rip + 0x110fab], 0
0000213d  488b3d6c0f1100    mov        rdi, qword ptr [rip + 0x110f6c]
00002144  488b0f            mov        rcx, qword ptr [rdi]
00002147  480f44f0          cmove      rsi, rax
0000214b  31d2              xor        edx, edx
0000214d  ff5118            call       qword ptr [rcx + 0x18]
00002150  85c0              test       eax, eax
00002152  7578              jne        0x21cc
00002154  488b3dcd0e0100    mov        rdi, qword ptr [rip + 0x10ecd]
0000215b  488b07            mov        rax, qword ptr [rdi]
0000215e  488d15a30f1100    lea        rdx, [rip + 0x110fa3]
00002165  4c89f6            mov        rsi, r14
00002168  ff5018            call       qword ptr [rax + 0x18]
0000216b  85c0              test       eax, eax
0000216d  755d              jne        0x21cc
0000216f  833d9a8e000000    cmp        dword ptr [rip + 0x8e9a], 0
00002176  7e3b              jle        0x21b3
00002178  4c8d35d10e0100    lea        r14, [rip + 0x10ed1]
0000217f  4531ff            xor        r15d, r15d
00002182  488b3d270f1100    mov        rdi, qword ptr [rip + 0x110f27]
00002189  488b07            mov        rax, qword ptr [rdi]
0000218c  8b159e0e0100      mov        edx, dword ptr [rip + 0x10e9e]
00002192  01d2              add        edx, edx
00002194  4c89f6            mov        rsi, r14
00002197  ff10              call       qword ptr [rax]
00002199  85c0              test       eax, eax
0000219b  752f              jne        0x21cc
0000219d  49ffc7            inc        r15
000021a0  486305698e0000    movsxd     rax, dword ptr [rip + 0x8e69]
000021a7  4981c600800000    add        r14, 0x8000
000021ae  4939c7            cmp        r15, rax
000021b1  7ccf              jl         0x2182
000021b3  488b3dee0e1100    mov        rdi, qword ptr [rip + 0x110eee]
000021ba  488b07            mov        rax, qword ptr [rdi]
000021bd  be03000000        mov        esi, 3
000021c2  ff10              call       qword ptr [rax]
000021c4  85c0              test       eax, eax
000021c6  0f8479010000      je         0x2345
000021cc  4889df            mov        rdi, rbx
000021cf  e8dc020000        call       0x24b0
000021d4  e92d010000        jmp        0x2306
000021d9  488b3dd00e1100    mov        rdi, qword ptr [rip + 0x110ed0]
000021e0  4885ff            test       rdi, rdi
000021e3  0f8413010000      je         0x22fc
000021e9  48833db70e110000  cmp        qword ptr [rip + 0x110eb7], 0
000021f1  0f8405010000      je         0x22fc
000021f7  488b07            mov        rax, qword ptr [rdi]
000021fa  ff5008            call       qword ptr [rax + 8]
000021fd  833d0c8e000000    cmp        dword ptr [rip + 0x8e0c], 0
00002204  7e3f              jle        0x2245
00002206  31db              xor        ebx, ebx
00002208  0f1f840000000000  nop        dword ptr [rax + rax]
00002210  488b3d990e1100    mov        rdi, qword ptr [rip + 0x110e99]
00002217  488b07            mov        rax, qword ptr [rdi]
0000221a  8b15100e0100      mov        edx, dword ptr [rip + 0x10e10]
00002220  01d2              add        edx, edx
00002222  4c89fe            mov        rsi, r15
00002225  ff10              call       qword ptr [rax]
00002227  85c0              test       eax, eax
00002229  0f85cd000000      jne        0x22fc
0000222f  48ffc3            inc        rbx
00002232  486305d78d0000    movsxd     rax, dword ptr [rip + 0x8dd7]
00002239  4981c700800000    add        r15, 0x8000
00002240  4839c3            cmp        rbx, rax
00002243  7ccb              jl         0x2210
00002245  488b3d5c0e1100    mov        rdi, qword ptr [rip + 0x110e5c]
0000224c  488b07            mov        rax, qword ptr [rdi]
0000224f  be03000000        mov        esi, 3
00002254  ff10              call       qword ptr [rax]
00002256  85c0              test       eax, eax
00002258  0f859e000000      jne        0x22fc
0000225e  c705d80d010002000000  mov        dword ptr [rip + 0x10dd8], 2
00002268  488d3df10d1100    lea        rdi, [rip + 0x110df1]
0000226f  e8fc0a0000        call       0x2d70
00002274  b001              mov        al, 1
00002276  e999000000        jmp        0x2314
0000227b  488d3d060e1100    lea        rdi, [rip + 0x110e06]
00002282  31f6              xor        esi, esi
00002284  31d2              xor        edx, edx
00002286  31c9              xor        ecx, ecx
00002288  4531c0            xor        r8d, r8d
0000228b  4531c9            xor        r9d, r9d
0000228e  e8fd0a0000        call       0x2d90
00002293  85c0              test       eax, eax
00002295  7565              jne        0x22fc
00002297  488b3dea0d1100    mov        rdi, qword ptr [rip + 0x110dea]
0000229e  488b07            mov        rax, qword ptr [rdi]
000022a1  31f6              xor        esi, esi
000022a3  ff10              call       qword ptr [rax]
000022a5  85c0              test       eax, eax
000022a7  7553              jne        0x22fc
000022a9  488b3dd80d1100    mov        rdi, qword ptr [rip + 0x110dd8]
000022b0  488b07            mov        rax, qword ptr [rdi]
000022b3  488b0dc64c0000    mov        rcx, qword ptr [rip + 0x4cc6]
000022ba  488b31            mov        rsi, qword ptr [rcx]
000022bd  488d15340e1100    lea        rdx, [rip + 0x110e34]
000022c4  ff5018            call       qword ptr [rax + 0x18]
000022c7  85c0              test       eax, eax
000022c9  7531              jne        0x22fc
000022cb  488b3d260e1100    mov        rdi, qword ptr [rip + 0x110e26]
000022d2  488b07            mov        rax, qword ptr [rdi]
000022d5  488d35240e1100    lea        rsi, [rip + 0x110e24]
000022dc  31d2              xor        edx, edx
000022de  31c9              xor        ecx, ecx
000022e0  4531c0            xor        r8d, r8d
000022e3  ff5038            call       qword ptr [rax + 0x38]
000022e6  85c0              test       eax, eax
000022e8  7512              jne        0x22fc
000022ea  488b3d0f0e1100    mov        rdi, qword ptr [rip + 0x110e0f]
000022f1  488b07            mov        rax, qword ptr [rdi]
000022f4  31f6              xor        esi, esi
000022f6  ff10              call       qword ptr [rax]
000022f8  85c0              test       eax, eax
000022fa  743d              je         0x2339
000022fc  c7053a0d010000000000  mov        dword ptr [rip + 0x10d3a], 0
00002306  488d3d530d1100    lea        rdi, [rip + 0x110d53]
0000230d  e85e0a0000        call       0x2d70
00002312  31c0              xor        eax, eax
00002314  64488b0c2528000000  mov        rcx, qword ptr fs:[0x28]
0000231d  483b4dd0          cmp        rcx, qword ptr [rbp - 0x30]
00002321  0f857a010000      jne        0x24a1
00002327  4881c4f8000000    add        rsp, 0xf8
0000232e  5b                pop        rbx
0000232f  415c              pop        r12
00002331  415d              pop        r13
00002333  415e              pop        r14
00002335  415f              pop        r15
00002337  5d                pop        rbp
00002338  c3                ret        
00002339  448b2dd08c0000    mov        r13d, dword ptr [rip + 0x8cd0]
00002340  e992fcffff        jmp        0x1fd7
00002345  807d1000          cmp        byte ptr [rbp + 0x10], 0
00002349  0f8439010000      je         0x2488
0000234f  c78550ffffffbd070080  mov        dword ptr [rbp - 0xb0], 0x800007bd
00002359  8b05b18c0000      mov        eax, dword ptr [rip + 0x8cb1]
0000235f  898554ffffff      mov        dword ptr [rbp - 0xac], eax
00002365  48b80200000001000000  movabs     rax, 0x100000002
0000236f  48898534ffffff    mov        qword ptr [rbp - 0xcc], rax
00002376  4489a53cffffff    mov        dword ptr [rbp - 0xc4], r12d
0000237d  0f28054ce7ffff    movaps     xmm0, xmmword ptr [rip - 0x18b4]
00002384  0f118540ffffff    movups     xmmword ptr [rbp - 0xc0], xmm0
0000238b  488d8550ffffff    lea        rax, [rbp - 0xb0]
00002392  48898520ffffff    mov        qword ptr [rbp - 0xe0], rax
00002399  488d8534ffffff    lea        rax, [rbp - 0xcc]
000023a0  48898528ffffff    mov        qword ptr [rbp - 0xd8], rax
000023a7  488b0562e7ffff    mov        rax, qword ptr [rip - 0x189e]
000023ae  48898510ffffff    mov        qword ptr [rbp - 0xf0], rax
000023b5  0f100544e7ffff    movups     xmm0, xmmword ptr [rip - 0x18bc]
000023bc  0f298500ffffff    movaps     xmmword ptr [rbp - 0x100], xmm0
000023c3  488d8500ffffff    lea        rax, [rbp - 0x100]
000023ca  488985f0feffff    mov        qword ptr [rbp - 0x110], rax
000023d1  48c785f8feffff00000000  mov        qword ptr [rbp - 0x108], 0
000023dc  488b05a54b0000    mov        rax, qword ptr [rip + 0x4ba5]
000023e3  4c8b30            mov        r14, qword ptr [rax]
000023e6  4c8975b0          mov        qword ptr [rbp - 0x50], r14
000023ea  c745ac01000000    mov        dword ptr [rbp - 0x54], 1
000023f1  488b3d000d1100    mov        rdi, qword ptr [rip + 0x110d00]
000023f8  488b07            mov        rax, qword ptr [rdi]
000023fb  488d4dac          lea        rcx, [rbp - 0x54]
000023ff  48890c24          mov        qword ptr [rsp], rcx
00002403  488d35060d1100    lea        rsi, [rip + 0x110d06]
0000240a  488d95f0feffff    lea        rdx, [rbp - 0x110]
00002411  488d8d20ffffff    lea        rcx, [rbp - 0xe0]
00002418  4c8d4db0          lea        r9, [rbp - 0x50]
0000241c  41b801000000      mov        r8d, 1
00002422  ff5018            call       qword ptr [rax + 0x18]
00002425  85c0              test       eax, eax
00002427  0f859ffdffff      jne        0x21cc
0000242d  488b3ddc0c1100    mov        rdi, qword ptr [rip + 0x110cdc]
00002434  488b07            mov        rax, qword ptr [rdi]
00002437  31f6              xor        esi, esi
00002439  ff10              call       qword ptr [rax]
0000243b  85c0              test       eax, eax
0000243d  0f8589fdffff      jne        0x21cc
00002443  488b3dc60c1100    mov        rdi, qword ptr [rip + 0x110cc6]
0000244a  488b07            mov        rax, qword ptr [rdi]
0000244d  488b0d3c4b0000    mov        rcx, qword ptr [rip + 0x4b3c]
00002454  488b31            mov        rsi, qword ptr [rcx]
00002457  488d155a0c1100    lea        rdx, [rip + 0x110c5a]
0000245e  ff5018            call       qword ptr [rax + 0x18]
00002461  85c0              test       eax, eax
00002463  0f8563fdffff      jne        0x21cc
00002469  488b3da00c1100    mov        rdi, qword ptr [rip + 0x110ca0]
00002470  488b07            mov        rax, qword ptr [rdi]
00002473  488d15d60b0900    lea        rdx, [rip + 0x90bd6]
0000247a  4c89f6            mov        rsi, r14
0000247d  ff5018            call       qword ptr [rax + 0x18]
00002480  85c0              test       eax, eax
00002482  0f8544fdffff      jne        0x21cc
00002488  c705ae0b010002000000  mov        dword ptr [rip + 0x10bae], 2
00002492  c705240c110000000000  mov        dword ptr [rip + 0x110c24], 0
0000249c  e9c7fdffff        jmp        0x2268
000024a1  e88a080000        call       0x2d30
