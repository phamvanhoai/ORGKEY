# APK member: lib/x86_64/libnflanger.so
# Library SHA-256: d2a7cec8840993d6dc944a3f0999a7fe2667decd0004b0e78fc4f98b33ea053e
# Function: Java_sofeh_audio_NFlanger_processLeft; address=0xa00; bytes=555
# Decoder mode: default; decoded_bytes=555
00000a00  4885d2            test       rdx, rdx
00000a03  0f8400010000      je         0xb09
00000a09  55                push       rbp
00000a0a  4889e5            mov        rbp, rsp
00000a0d  4157              push       r15
00000a0f  4156              push       r14
00000a11  53                push       rbx
00000a12  50                push       rax
00000a13  f3480f2ac1        cvtsi2ss   xmm0, rcx
00000a18  f30f2a0a          cvtsi2ss   xmm1, dword ptr [rdx]
00000a1c  f30f109220080000  movss      xmm2, dword ptr [rdx + 0x820]
00000a24  f30f5915d8fcffff  mulss      xmm2, dword ptr [rip - 0x328]
00000a2c  f30f59d1          mulss      xmm2, xmm1
00000a30  f30f58d0          addss      xmm2, xmm0
00000a34  f30f119228080000  movss      dword ptr [rdx + 0x828], xmm2
00000a3c  8b5a18            mov        ebx, dword ptr [rdx + 0x18]
00000a3f  69c3cdcccccc      imul       eax, ebx, 0xcccccccd
00000a45  c1c802            ror        eax, 2
00000a48  3dcccccc0c        cmp        eax, 0xccccccc
00000a4d  0f8794000000      ja         0xae7
00000a53  89d8              mov        eax, ebx
00000a55  0f57c0            xorps      xmm0, xmm0
00000a58  f3480f2ac0        cvtsi2ss   xmm0, rax
00000a5d  0f57c9            xorps      xmm1, xmm1
00000a60  f30f2a4a04        cvtsi2ss   xmm1, dword ptr [rdx + 4]
00000a65  f30f59821c080000  mulss      xmm0, dword ptr [rdx + 0x81c]
00000a6d  f30f58c8          addss      xmm1, xmm0
00000a71  0f57c0            xorps      xmm0, xmm0
00000a74  f30f5ac1          cvtss2sd   xmm0, xmm1
00000a78  4989ce            mov        r14, rcx
00000a7b  4989d7            mov        r15, rdx
00000a7e  f30f1155e4        movss      dword ptr [rbp - 0x1c], xmm2
00000a83  e8d8050000        call       0x1060
00000a88  f20f5ac0          cvtsd2ss   xmm0, xmm0
00000a8c  f30f58c0          addss      xmm0, xmm0
00000a90  f30f580564fcffff  addss      xmm0, dword ptr [rip - 0x39c]
00000a98  f30f5ac0          cvtss2sd   xmm0, xmm0
00000a9c  e8cf050000        call       0x1070
00000aa1  f30f1055e4        movss      xmm2, dword ptr [rbp - 0x1c]
00000aa6  4c89fa            mov        rdx, r15
00000aa9  4c89f1            mov        rcx, r14
00000aac  f20f5ac0          cvtsd2ss   xmm0, xmm0
00000ab0  0f57c9            xorps      xmm1, xmm1
00000ab3  f3410f2a4f08      cvtsi2ss   xmm1, dword ptr [r15 + 8]
00000ab9  f30f580537fcffff  addss      xmm0, dword ptr [rip - 0x3c9]
00000ac1  f30f590d37fcffff  mulss      xmm1, dword ptr [rip - 0x3c9]
00000ac9  f30f59c8          mulss      xmm1, xmm0
00000acd  f3410f5e8f30080000  divss      xmm1, dword ptr [r15 + 0x830]
00000ad6  f30f580d12fcffff  addss      xmm1, dword ptr [rip - 0x3ee]
00000ade  f3410f118f14080000  movss      dword ptr [r15 + 0x814], xmm1
00000ae7  ffc3              inc        ebx
00000ae9  895a18            mov        dword ptr [rdx + 0x18], ebx
00000aec  4863420c          movsxd     rax, dword ptr [rdx + 0xc]
00000af0  4885c0            test       rax, rax
00000af3  7e17              jle        0xb0c
00000af5  f30f108214080000  movss      xmm0, dword ptr [rdx + 0x814]
00000afd  83f801            cmp        eax, 1
00000b00  7511              jne        0xb13
00000b02  31f6              xor        esi, esi
00000b04  e99c000000        jmp        0xba5
00000b09  31c0              xor        eax, eax
00000b0b  c3                ret        
00000b0c  31c0              xor        eax, eax
00000b0e  e9ce000000        jmp        0xbe1
00000b13  89c7              mov        edi, eax
00000b15  81e7feffff7f      and        edi, 0x7ffffffe
00000b1b  31f6              xor        esi, esi
00000b1d  0f1f00            nop        dword ptr [rax]
00000b20  f30f104cb21c      movss      xmm1, dword ptr [rdx + rsi*4 + 0x1c]
00000b26  f30f118a18080000  movss      dword ptr [rdx + 0x818], xmm1
00000b2e  f30f59c1          mulss      xmm0, xmm1
00000b32  f30f58c2          addss      xmm0, xmm2
00000b36  f30f1144b21c      movss      dword ptr [rdx + rsi*4 + 0x1c], xmm0
00000b3c  f30f108a14080000  movss      xmm1, dword ptr [rdx + 0x814]
00000b44  f30f109218080000  movss      xmm2, dword ptr [rdx + 0x818]
00000b4c  f30f59c1          mulss      xmm0, xmm1
00000b50  f30f5cd0          subss      xmm2, xmm0
00000b54  f30f119228080000  movss      dword ptr [rdx + 0x828], xmm2
00000b5c  f30f105cb220      movss      xmm3, dword ptr [rdx + rsi*4 + 0x20]
00000b62  f30f119a18080000  movss      dword ptr [rdx + 0x818], xmm3
00000b6a  f30f59d9          mulss      xmm3, xmm1
00000b6e  f30f58da          addss      xmm3, xmm2
00000b72  f30f115cb220      movss      dword ptr [rdx + rsi*4 + 0x20], xmm3
00000b78  f30f108214080000  movss      xmm0, dword ptr [rdx + 0x814]
00000b80  f30f109218080000  movss      xmm2, dword ptr [rdx + 0x818]
00000b88  f30f59d8          mulss      xmm3, xmm0
00000b8c  f30f5cd3          subss      xmm2, xmm3
00000b90  f30f119228080000  movss      dword ptr [rdx + 0x828], xmm2
00000b98  4883c602          add        rsi, 2
00000b9c  4839f7            cmp        rdi, rsi
00000b9f  0f857bffffff      jne        0xb20
00000ba5  a801              test       al, 1
00000ba7  7438              je         0xbe1
00000ba9  f30f104cb21c      movss      xmm1, dword ptr [rdx + rsi*4 + 0x1c]
00000baf  f30f118a18080000  movss      dword ptr [rdx + 0x818], xmm1
00000bb7  f30f59c1          mulss      xmm0, xmm1
00000bbb  f30f58c2          addss      xmm0, xmm2
00000bbf  f30f1144b21c      movss      dword ptr [rdx + rsi*4 + 0x1c], xmm0
00000bc5  f30f109218080000  movss      xmm2, dword ptr [rdx + 0x818]
00000bcd  f30f598214080000  mulss      xmm0, dword ptr [rdx + 0x814]
00000bd5  f30f5cd0          subss      xmm2, xmm0
00000bd9  f30f119228080000  movss      dword ptr [rdx + 0x828], xmm2
00000be1  894214            mov        dword ptr [rdx + 0x14], eax
00000be4  f30f119220080000  movss      dword ptr [rdx + 0x820], xmm2
00000bec  48634210          movsxd     rax, dword ptr [rdx + 0x10]
00000bf0  0f57c0            xorps      xmm0, xmm0
00000bf3  f30f2ac0          cvtsi2ss   xmm0, eax
00000bf7  f30f59c2          mulss      xmm0, xmm2
00000bfb  baff000000        mov        edx, 0xff
00000c00  4829c2            sub        rdx, rax
00000c03  480fafd1          imul       rdx, rcx
00000c07  0f57c9            xorps      xmm1, xmm1
00000c0a  f3480f2aca        cvtsi2ss   xmm1, rdx
00000c0f  f30f58c8          addss      xmm1, xmm0
00000c13  f30f590dedfaffff  mulss      xmm1, dword ptr [rip - 0x513]
00000c1b  f3480f2cc1        cvttss2si  rax, xmm1
00000c20  4883c408          add        rsp, 8
00000c24  5b                pop        rbx
00000c25  415e              pop        r14
00000c27  415f              pop        r15
00000c29  5d                pop        rbp
00000c2a  c3                ret        
