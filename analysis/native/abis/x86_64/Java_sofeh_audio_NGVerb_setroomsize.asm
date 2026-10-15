# APK member: lib/x86_64/libngverb.so
# Library SHA-256: 8fd68c89616a094decd0ad7f229e0d8e89656b29446477be056c19051fc5f443
# Function: Java_sofeh_audio_NGVerb_setroomsize; address=0x2b60; bytes=482
# Decoder mode: default; decoded_bytes=482
00002b60  4885d2            test       rdx, rdx
00002b63  0f84d8010000      je         0x2d41
00002b69  55                push       rbp
00002b6a  4889e5            mov        rbp, rsp
00002b6d  4156              push       r14
00002b6f  53                push       rbx
00002b70  4883ec30          sub        rsp, 0x30
00002b74  f30f100d64e4ffff  movss      xmm1, dword ptr [rip - 0x1b9c]
00002b7c  0f28d0            movaps     xmm2, xmm0
00002b7f  f30fc2d003        cmpunordss xmm2, xmm0
00002b84  0f28da            movaps     xmm3, xmm2
00002b87  0f54d9            andps      xmm3, xmm1
00002b8a  0f55d0            andnps     xmm2, xmm0
00002b8d  0f56d3            orps       xmm2, xmm3
00002b90  f30fc2c102        cmpless    xmm0, xmm1
00002b95  0f28d8            movaps     xmm3, xmm0
00002b98  0f55da            andnps     xmm3, xmm2
00002b9b  0f54c1            andps      xmm0, xmm1
00002b9e  0f56c3            orps       xmm0, xmm3
00002ba1  f30f11421c        movss      dword ptr [rdx + 0x1c], xmm0
00002ba6  0f57c9            xorps      xmm1, xmm1
00002ba9  f30f2a0a          cvtsi2ss   xmm1, dword ptr [rdx]
00002bad  f30f59c8          mulss      xmm1, xmm0
00002bb1  488b4240          mov        rax, qword ptr [rdx + 0x40]
00002bb5  f30f590d3be4ffff  mulss      xmm1, dword ptr [rip - 0x1bc5]
00002bbd  f30f114a28        movss      dword ptr [rdx + 0x28], xmm1
00002bc2  f30f10054ee4ffff  movss      xmm0, dword ptr [rip - 0x1bb2]
00002bca  f30f59c1          mulss      xmm0, xmm1
00002bce  f30f10154ee4ffff  movss      xmm2, dword ptr [rip - 0x1bb2]
00002bd6  f30f59d1          mulss      xmm2, xmm1
00002bda  f30f101d0ee4ffff  movss      xmm3, dword ptr [rip - 0x1bf2]
00002be2  f30f59d9          mulss      xmm3, xmm1
00002be6  660f3a21c810      insertps   xmm1, xmm0, 0x10
00002bec  660f3a21ca20      insertps   xmm1, xmm2, 0x20
00002bf2  660f3a21cb30      insertps   xmm1, xmm3, 0x30
00002bf8  0f580d91e3ffff    addps      xmm1, xmmword ptr [rip - 0x1c6f]
00002bff  660ffe0d09e3ffff  paddd      xmm1, xmmword ptr [rip - 0x1cf7]
00002c07  f30f7f08          movdqu     xmmword ptr [rax], xmm1
00002c0b  f20f108298000000  movsd      xmm0, qword ptr [rdx + 0x98]
00002c13  f20f5ac0          cvtsd2ss   xmm0, xmm0
00002c17  f30f1145ec        movss      dword ptr [rbp - 0x14], xmm0
00002c1c  4c8b7238          mov        r14, qword ptr [rdx + 0x38]
00002c20  0f5bc9            cvtdq2ps   xmm1, xmm1
00002c23  0f294dd0          movaps     xmmword ptr [rbp - 0x30], xmm1
00002c27  f30f16c9          movshdup   xmm1, xmm1
00002c2b  4889d3            mov        rbx, rdx
00002c2e  e84d030000        call       0x2f80
00002c33  0f2945c0          movaps     xmmword ptr [rbp - 0x40], xmm0
00002c37  f30f1045ec        movss      xmm0, dword ptr [rbp - 0x14]
00002c3c  0f284dd0          movaps     xmm1, xmmword ptr [rbp - 0x30]
00002c40  e83b030000        call       0x2f80
00002c45  660f3a2145c010    insertps   xmm0, dword ptr [rbp - 0x40], 0x10
00002c4c  0f2945c0          movaps     xmmword ptr [rbp - 0x40], xmm0
00002c50  0f284dd0          movaps     xmm1, xmmword ptr [rbp - 0x30]
00002c54  0f12c9            movhlps    xmm1, xmm1
00002c57  f30f1045ec        movss      xmm0, dword ptr [rbp - 0x14]
00002c5c  e81f030000        call       0x2f80
00002c61  0f284dc0          movaps     xmm1, xmmword ptr [rbp - 0x40]
00002c65  660f3a21c820      insertps   xmm1, xmm0, 0x20
00002c6b  0f294dc0          movaps     xmmword ptr [rbp - 0x40], xmm1
00002c6f  0f284dd0          movaps     xmm1, xmmword ptr [rbp - 0x30]
00002c73  0fc6c9ff          shufps     xmm1, xmm1, 0xff
00002c77  f30f1045ec        movss      xmm0, dword ptr [rbp - 0x14]
00002c7c  e8ff020000        call       0x2f80
00002c81  0f2855c0          movaps     xmm2, xmmword ptr [rbp - 0x40]
00002c85  660f3a21d030      insertps   xmm2, xmm0, 0x30
00002c8b  0f280d1ee3ffff    movaps     xmm1, xmmword ptr [rip - 0x1ce2]
00002c92  0f57ca            xorps      xmm1, xmm2
00002c95  0fc21593e2ffff01  cmpltps    xmm2, xmmword ptr [rip - 0x1d6d]
00002c9d  0f28c2            movaps     xmm0, xmm2
00002ca0  0f281529e3ffff    movaps     xmm2, xmmword ptr [rip - 0x1cd7]
00002ca7  660f3814d1        blendvps   xmm2, xmm1, xmm0
00002cac  410f1116          movups     xmmword ptr [r14], xmm2
00002cb0  f30f104328        movss      xmm0, dword ptr [rbx + 0x28]
00002cb5  0fc6c000          shufps     xmm0, xmm0, 0
00002cb9  0f5905c0e2ffff    mulps      xmm0, xmmword ptr [rip - 0x1d40]
00002cc0  0f5805c9e2ffff    addps      xmm0, xmmword ptr [rip - 0x1d37]
00002cc7  660ffe0591e2ffff  paddd      xmm0, xmmword ptr [rip - 0x1d6f]
00002ccf  660f7f45d0        movdqa     xmmword ptr [rbp - 0x30], xmm0
00002cd4  488b4370          mov        rax, qword ptr [rbx + 0x70]
00002cd8  f30f7f00          movdqu     xmmword ptr [rax], xmm0
00002cdc  488b5b78          mov        rbx, qword ptr [rbx + 0x78]
00002ce0  0f5bc8            cvtdq2ps   xmm1, xmm0
00002ce3  f30f1045ec        movss      xmm0, dword ptr [rbp - 0x14]
00002ce8  e893020000        call       0x2f80
00002ced  f30f1103          movss      dword ptr [rbx], xmm0
00002cf1  660f7045d055      pshufd     xmm0, xmmword ptr [rbp - 0x30], 0x55
00002cf7  0f5bc8            cvtdq2ps   xmm1, xmm0
00002cfa  f30f1045ec        movss      xmm0, dword ptr [rbp - 0x14]
00002cff  e87c020000        call       0x2f80
00002d04  f30f114304        movss      dword ptr [rbx + 4], xmm0
00002d09  660f7045d0ee      pshufd     xmm0, xmmword ptr [rbp - 0x30], 0xee
00002d0f  0f5bc8            cvtdq2ps   xmm1, xmm0
00002d12  f30f1045ec        movss      xmm0, dword ptr [rbp - 0x14]
00002d17  e864020000        call       0x2f80
00002d1c  f30f114308        movss      dword ptr [rbx + 8], xmm0
00002d21  660f7045d0ff      pshufd     xmm0, xmmword ptr [rbp - 0x30], 0xff
00002d27  0f5bc8            cvtdq2ps   xmm1, xmm0
00002d2a  f30f1045ec        movss      xmm0, dword ptr [rbp - 0x14]
00002d2f  e84c020000        call       0x2f80
00002d34  f30f11430c        movss      dword ptr [rbx + 0xc], xmm0
00002d39  4883c430          add        rsp, 0x30
00002d3d  5b                pop        rbx
00002d3e  415e              pop        r14
00002d40  5d                pop        rbp
00002d41  c3                ret        
