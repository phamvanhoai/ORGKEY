# APK member: lib/x86_64/libngverb.so
# Library SHA-256: 8fd68c89616a094decd0ad7f229e0d8e89656b29446477be056c19051fc5f443
# Function: Java_sofeh_audio_NGVerb_processMono; address=0x2ab0; bytes=164
# Decoder mode: default; decoded_bytes=164
00002ab0  4885d2            test       rdx, rdx
00002ab3  0f8498000000      je         0x2b51
00002ab9  55                push       rbp
00002aba  4889e5            mov        rbp, rsp
00002abd  4157              push       r15
00002abf  4156              push       r14
00002ac1  4154              push       r12
00002ac3  53                push       rbx
00002ac4  f3480f2ac1        cvtsi2ss   xmm0, rcx
00002ac9  f30f590517e5ffff  mulss      xmm0, dword ptr [rip - 0x1ae9]
00002ad1  4c8b3d20470000    mov        r15, qword ptr [rip + 0x4720]
00002ad8  f3410f1107        movss      dword ptr [r15], xmm0
00002add  f3410f2ac8        cvtsi2ss   xmm1, r8d
00002ae2  f30f5e0d36e5ffff  divss      xmm1, dword ptr [rip - 0x1aca]
00002aea  4c8b2517470000    mov        r12, qword ptr [rip + 0x4717]
00002af1  f3410f110c24      movss      dword ptr [r12], xmm1
00002af7  488b1d12470000    mov        rbx, qword ptr [rip + 0x4712]
00002afe  4c8b3513470000    mov        r14, qword ptr [rip + 0x4713]
00002b05  4889d7            mov        rdi, rdx
00002b08  4889de            mov        rsi, rbx
00002b0b  4c89f2            mov        rdx, r14
00002b0e  e87df7ffff        call       0x2290
00002b13  f3410f100424      movss      xmm0, dword ptr [r12]
00002b19  f30f100dbfe4ffff  movss      xmm1, dword ptr [rip - 0x1b41]
00002b21  f30f5cc8          subss      xmm1, xmm0
00002b25  f30f1013          movss      xmm2, dword ptr [rbx]
00002b29  f3410f5816        addss      xmm2, dword ptr [r14]
00002b2e  f30f59d0          mulss      xmm2, xmm0
00002b32  f3410f590f        mulss      xmm1, dword ptr [r15]
00002b37  f30f58ca          addss      xmm1, xmm2
00002b3b  f30f590dd9e4ffff  mulss      xmm1, dword ptr [rip - 0x1b27]
00002b43  f3480f2cc1        cvttss2si  rax, xmm1
00002b48  5b                pop        rbx
00002b49  415c              pop        r12
00002b4b  415e              pop        r14
00002b4d  415f              pop        r15
00002b4f  5d                pop        rbp
00002b50  c3                ret        
00002b51  31c0              xor        eax, eax
00002b53  c3                ret        
