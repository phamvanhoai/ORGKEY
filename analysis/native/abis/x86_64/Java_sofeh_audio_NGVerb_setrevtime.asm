# APK member: lib/x86_64/libngverb.so
# Library SHA-256: 8fd68c89616a094decd0ad7f229e0d8e89656b29446477be056c19051fc5f443
# Function: Java_sofeh_audio_NGVerb_setrevtime; address=0x2d50; bytes=242
# Decoder mode: default; decoded_bytes=242
00002d50  4885d2            test       rdx, rdx
00002d53  0f84e8000000      je         0x2e41
00002d59  55                push       rbp
00002d5a  4889e5            mov        rbp, rsp
00002d5d  4156              push       r14
00002d5f  53                push       rbx
00002d60  4883ec30          sub        rsp, 0x30
00002d64  f30f114220        movss      dword ptr [rdx + 0x20], xmm0
00002d69  f30f2a12          cvtsi2ss   xmm2, dword ptr [rdx]
00002d6d  f30f59d0          mulss      xmm2, xmm0
00002d71  f30f100d67e2ffff  movss      xmm1, dword ptr [rip - 0x1d99]
00002d79  f30f5eca          divss      xmm1, xmm2
00002d7d  f30f1005a3e2ffff  movss      xmm0, dword ptr [rip - 0x1d5d]
00002d85  4989d6            mov        r14, rdx
00002d88  e8f3010000        call       0x2f80
00002d8d  f30f1145ec        movss      dword ptr [rbp - 0x14], xmm0
00002d92  0f57c9            xorps      xmm1, xmm1
00002d95  f30f5ac8          cvtss2sd   xmm1, xmm0
00002d99  f2410f118e98000000  movsd      qword ptr [r14 + 0x98], xmm1
00002da2  498b5e38          mov        rbx, qword ptr [r14 + 0x38]
00002da6  498b4640          mov        rax, qword ptr [r14 + 0x40]
00002daa  0f1008            movups     xmm1, xmmword ptr [rax]
00002dad  0f5bc9            cvtdq2ps   xmm1, xmm1
00002db0  0f294dc0          movaps     xmmword ptr [rbp - 0x40], xmm1
00002db4  e8c7010000        call       0x2f80
00002db9  0f2945d0          movaps     xmmword ptr [rbp - 0x30], xmm0
00002dbd  f30f164dc0        movshdup   xmm1, xmmword ptr [rbp - 0x40]
00002dc2  f30f1045ec        movss      xmm0, dword ptr [rbp - 0x14]
00002dc7  e8b4010000        call       0x2f80
00002dcc  0f284dd0          movaps     xmm1, xmmword ptr [rbp - 0x30]
00002dd0  660f3a21c810      insertps   xmm1, xmm0, 0x10
00002dd6  0f294dd0          movaps     xmmword ptr [rbp - 0x30], xmm1
00002dda  0f284dc0          movaps     xmm1, xmmword ptr [rbp - 0x40]
00002dde  0f12c9            movhlps    xmm1, xmm1
00002de1  f30f1045ec        movss      xmm0, dword ptr [rbp - 0x14]
00002de6  e895010000        call       0x2f80
00002deb  0f284dd0          movaps     xmm1, xmmword ptr [rbp - 0x30]
00002def  660f3a21c820      insertps   xmm1, xmm0, 0x20
00002df5  0f294dd0          movaps     xmmword ptr [rbp - 0x30], xmm1
00002df9  0f284dc0          movaps     xmm1, xmmword ptr [rbp - 0x40]
00002dfd  0fc6c9ff          shufps     xmm1, xmm1, 0xff
00002e01  f30f1045ec        movss      xmm0, dword ptr [rbp - 0x14]
00002e06  e875010000        call       0x2f80
00002e0b  0f2855d0          movaps     xmm2, xmmword ptr [rbp - 0x30]
00002e0f  660f3a21d030      insertps   xmm2, xmm0, 0x30
00002e15  0f280d94e1ffff    movaps     xmm1, xmmword ptr [rip - 0x1e6c]
00002e1c  0f57ca            xorps      xmm1, xmm2
00002e1f  0fc21509e1ffff01  cmpltps    xmm2, xmmword ptr [rip - 0x1ef7]
00002e27  0f28c2            movaps     xmm0, xmm2
00002e2a  0f28159fe1ffff    movaps     xmm2, xmmword ptr [rip - 0x1e61]
00002e31  660f3814d1        blendvps   xmm2, xmm1, xmm0
00002e36  0f1113            movups     xmmword ptr [rbx], xmm2
00002e39  4883c430          add        rsp, 0x30
00002e3d  5b                pop        rbx
00002e3e  415e              pop        r14
00002e40  5d                pop        rbp
00002e41  c3                ret        
