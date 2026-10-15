# APK member: lib/x86_64/libngverb.so
# Library SHA-256: 8fd68c89616a094decd0ad7f229e0d8e89656b29446477be056c19051fc5f443
# Function: Java_sofeh_audio_NGVerb_processStereoLeft; address=0x29a0; bytes=243
# Decoder mode: default; decoded_bytes=243
000029a0  4885d2            test       rdx, rdx
000029a3  0f84e7000000      je         0x2a90
000029a9  55                push       rbp
000029aa  4889e5            mov        rbp, rsp
000029ad  4157              push       r15
000029af  4156              push       r14
000029b1  4155              push       r13
000029b3  4154              push       r12
000029b5  53                push       rbx
000029b6  50                push       rax
000029b7  f3480f2ac9        cvtsi2ss   xmm1, rcx
000029bc  f30f101524e6ffff  movss      xmm2, dword ptr [rip - 0x19dc]
000029c4  f30f59ca          mulss      xmm1, xmm2
000029c8  4c8b3d29480000    mov        r15, qword ptr [rip + 0x4829]
000029cf  f3490f2ac0        cvtsi2ss   xmm0, r8
000029d4  f30f59c2          mulss      xmm0, xmm2
000029d8  0f57d2            xorps      xmm2, xmm2
000029db  f3410f2ad1        cvtsi2ss   xmm2, r9d
000029e0  f3410f110f        movss      dword ptr [r15], xmm1
000029e5  4c8b2514480000    mov        r12, qword ptr [rip + 0x4814]
000029ec  f30f5e152ce6ffff  divss      xmm2, dword ptr [rip - 0x19d4]
000029f4  f3410f110424      movss      dword ptr [r12], xmm0
000029fa  4c8b2d07480000    mov        r13, qword ptr [rip + 0x4807]
00002a01  f3410f115500      movss      dword ptr [r13], xmm2
00002a07  f30f58c1          addss      xmm0, xmm1
00002a0b  488b1dfe470000    mov        rbx, qword ptr [rip + 0x47fe]
00002a12  4c8b35ff470000    mov        r14, qword ptr [rip + 0x47ff]
00002a19  4889d7            mov        rdi, rdx
00002a1c  4889de            mov        rsi, rbx
00002a1f  4c89f2            mov        rdx, r14
00002a22  e869f8ffff        call       0x2290
00002a27  f3410f104500      movss      xmm0, dword ptr [r13]
00002a2d  f30f100dabe5ffff  movss      xmm1, dword ptr [rip - 0x1a55]
00002a35  f30f5cc8          subss      xmm1, xmm0
00002a39  f3410f1016        movss      xmm2, dword ptr [r14]
00002a3e  f30f59d0          mulss      xmm2, xmm0
00002a42  f3410f101c24      movss      xmm3, dword ptr [r12]
00002a48  f30f59d9          mulss      xmm3, xmm1
00002a4c  f30f58da          addss      xmm3, xmm2
00002a50  f30f1015c4e5ffff  movss      xmm2, dword ptr [rip - 0x1a3c]
00002a58  f30f59da          mulss      xmm3, xmm2
00002a5c  f3480f2cc3        cvttss2si  rax, xmm3
00002a61  488b0db8470000    mov        rcx, qword ptr [rip + 0x47b8]
00002a68  488901            mov        qword ptr [rcx], rax
00002a6b  f30f5903          mulss      xmm0, dword ptr [rbx]
00002a6f  f3410f590f        mulss      xmm1, dword ptr [r15]
00002a74  f30f58c8          addss      xmm1, xmm0
00002a78  f30f59ca          mulss      xmm1, xmm2
00002a7c  f3480f2cc1        cvttss2si  rax, xmm1
00002a81  4883c408          add        rsp, 8
00002a85  5b                pop        rbx
00002a86  415c              pop        r12
00002a88  415d              pop        r13
00002a8a  415e              pop        r14
00002a8c  415f              pop        r15
00002a8e  5d                pop        rbp
00002a8f  c3                ret        
00002a90  31c0              xor        eax, eax
00002a92  c3                ret        
