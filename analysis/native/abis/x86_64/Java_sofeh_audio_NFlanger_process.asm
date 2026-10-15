# APK member: lib/x86_64/libnflanger.so
# Library SHA-256: d2a7cec8840993d6dc944a3f0999a7fe2667decd0004b0e78fc4f98b33ea053e
# Function: Java_sofeh_audio_NFlanger_process; address=0xdc0; bytes=550
# Decoder mode: default; decoded_bytes=550
00000dc0  4885d2            test       rdx, rdx
00000dc3  0f8401010000      je         0xeca
00000dc9  55                push       rbp
00000dca  4889e5            mov        rbp, rsp
00000dcd  4157              push       r15
00000dcf  4156              push       r14
00000dd1  53                push       rbx
00000dd2  50                push       rax
00000dd3  0fbfd9            movsx      ebx, cx
00000dd6  f30f2ac3          cvtsi2ss   xmm0, ebx
00000dda  f30f2a0a          cvtsi2ss   xmm1, dword ptr [rdx]
00000dde  f30f109220080000  movss      xmm2, dword ptr [rdx + 0x820]
00000de6  f30f591516f9ffff  mulss      xmm2, dword ptr [rip - 0x6ea]
00000dee  f30f59d1          mulss      xmm2, xmm1
00000df2  f30f58d0          addss      xmm2, xmm0
00000df6  f30f119228080000  movss      dword ptr [rdx + 0x828], xmm2
00000dfe  448b7218          mov        r14d, dword ptr [rdx + 0x18]
00000e02  4169c6cdcccccc    imul       eax, r14d, 0xcccccccd
00000e09  c1c802            ror        eax, 2
00000e0c  3dcccccc0c        cmp        eax, 0xccccccc
00000e11  0f878f000000      ja         0xea6
00000e17  4489f0            mov        eax, r14d
00000e1a  0f57c0            xorps      xmm0, xmm0
00000e1d  f3480f2ac0        cvtsi2ss   xmm0, rax
00000e22  0f57c9            xorps      xmm1, xmm1
00000e25  f30f2a4a04        cvtsi2ss   xmm1, dword ptr [rdx + 4]
00000e2a  f30f59821c080000  mulss      xmm0, dword ptr [rdx + 0x81c]
00000e32  f30f58c8          addss      xmm1, xmm0
00000e36  0f57c0            xorps      xmm0, xmm0
00000e39  f30f5ac1          cvtss2sd   xmm0, xmm1
00000e3d  4989d7            mov        r15, rdx
00000e40  f30f1155e4        movss      dword ptr [rbp - 0x1c], xmm2
00000e45  e816020000        call       0x1060
00000e4a  f20f5ac0          cvtsd2ss   xmm0, xmm0
00000e4e  f30f58c0          addss      xmm0, xmm0
00000e52  f30f5805a2f8ffff  addss      xmm0, dword ptr [rip - 0x75e]
00000e5a  f30f5ac0          cvtss2sd   xmm0, xmm0
00000e5e  e80d020000        call       0x1070
00000e63  f30f1055e4        movss      xmm2, dword ptr [rbp - 0x1c]
00000e68  4c89fa            mov        rdx, r15
00000e6b  f20f5ac0          cvtsd2ss   xmm0, xmm0
00000e6f  0f57c9            xorps      xmm1, xmm1
00000e72  f3410f2a4f08      cvtsi2ss   xmm1, dword ptr [r15 + 8]
00000e78  f30f580578f8ffff  addss      xmm0, dword ptr [rip - 0x788]
00000e80  f30f590d78f8ffff  mulss      xmm1, dword ptr [rip - 0x788]
00000e88  f30f59c8          mulss      xmm1, xmm0
00000e8c  f3410f5e8f30080000  divss      xmm1, dword ptr [r15 + 0x830]
00000e95  f30f580d53f8ffff  addss      xmm1, dword ptr [rip - 0x7ad]
00000e9d  f3410f118f14080000  movss      dword ptr [r15 + 0x814], xmm1
00000ea6  41ffc6            inc        r14d
00000ea9  44897218          mov        dword ptr [rdx + 0x18], r14d
00000ead  4863420c          movsxd     rax, dword ptr [rdx + 0xc]
00000eb1  4885c0            test       rax, rax
00000eb4  7e17              jle        0xecd
00000eb6  f30f108214080000  movss      xmm0, dword ptr [rdx + 0x814]
00000ebe  83f801            cmp        eax, 1
00000ec1  7511              jne        0xed4
00000ec3  31c9              xor        ecx, ecx
00000ec5  e99b000000        jmp        0xf65
00000eca  31c0              xor        eax, eax
00000ecc  c3                ret        
00000ecd  31c0              xor        eax, eax
00000ecf  e9cd000000        jmp        0xfa1
00000ed4  89c6              mov        esi, eax
00000ed6  81e6feffff7f      and        esi, 0x7ffffffe
00000edc  31c9              xor        ecx, ecx
00000ede  6690              nop        
00000ee0  f30f104c8a1c      movss      xmm1, dword ptr [rdx + rcx*4 + 0x1c]
00000ee6  f30f118a18080000  movss      dword ptr [rdx + 0x818], xmm1
00000eee  f30f59c1          mulss      xmm0, xmm1
00000ef2  f30f58c2          addss      xmm0, xmm2
00000ef6  f30f11448a1c      movss      dword ptr [rdx + rcx*4 + 0x1c], xmm0
00000efc  f30f108a14080000  movss      xmm1, dword ptr [rdx + 0x814]
00000f04  f30f109218080000  movss      xmm2, dword ptr [rdx + 0x818]
00000f0c  f30f59c1          mulss      xmm0, xmm1
00000f10  f30f5cd0          subss      xmm2, xmm0
00000f14  f30f119228080000  movss      dword ptr [rdx + 0x828], xmm2
00000f1c  f30f105c8a20      movss      xmm3, dword ptr [rdx + rcx*4 + 0x20]
00000f22  f30f119a18080000  movss      dword ptr [rdx + 0x818], xmm3
00000f2a  f30f59d9          mulss      xmm3, xmm1
00000f2e  f30f58da          addss      xmm3, xmm2
00000f32  f30f115c8a20      movss      dword ptr [rdx + rcx*4 + 0x20], xmm3
00000f38  f30f108214080000  movss      xmm0, dword ptr [rdx + 0x814]
00000f40  f30f109218080000  movss      xmm2, dword ptr [rdx + 0x818]
00000f48  f30f59d8          mulss      xmm3, xmm0
00000f4c  f30f5cd3          subss      xmm2, xmm3
00000f50  f30f119228080000  movss      dword ptr [rdx + 0x828], xmm2
00000f58  4883c102          add        rcx, 2
00000f5c  4839ce            cmp        rsi, rcx
00000f5f  0f857bffffff      jne        0xee0
00000f65  a801              test       al, 1
00000f67  7438              je         0xfa1
00000f69  f30f104c8a1c      movss      xmm1, dword ptr [rdx + rcx*4 + 0x1c]
00000f6f  f30f118a18080000  movss      dword ptr [rdx + 0x818], xmm1
00000f77  f30f59c1          mulss      xmm0, xmm1
00000f7b  f30f58c2          addss      xmm0, xmm2
00000f7f  f30f11448a1c      movss      dword ptr [rdx + rcx*4 + 0x1c], xmm0
00000f85  f30f109218080000  movss      xmm2, dword ptr [rdx + 0x818]
00000f8d  f30f598214080000  mulss      xmm0, dword ptr [rdx + 0x814]
00000f95  f30f5cd0          subss      xmm2, xmm0
00000f99  f30f119228080000  movss      dword ptr [rdx + 0x828], xmm2
00000fa1  894214            mov        dword ptr [rdx + 0x14], eax
00000fa4  f30f119220080000  movss      dword ptr [rdx + 0x820], xmm2
00000fac  8b4210            mov        eax, dword ptr [rdx + 0x10]
00000faf  0f57c0            xorps      xmm0, xmm0
00000fb2  f30f2ac0          cvtsi2ss   xmm0, eax
00000fb6  f30f59c2          mulss      xmm0, xmm2
00000fba  b9ff000000        mov        ecx, 0xff
00000fbf  29c1              sub        ecx, eax
00000fc1  0fafcb            imul       ecx, ebx
00000fc4  0f57c9            xorps      xmm1, xmm1
00000fc7  f30f2ac9          cvtsi2ss   xmm1, ecx
00000fcb  f30f58c8          addss      xmm1, xmm0
00000fcf  f30f590d31f7ffff  mulss      xmm1, dword ptr [rip - 0x8cf]
00000fd7  f30f2cc1          cvttss2si  eax, xmm1
00000fdb  4883c408          add        rsp, 8
00000fdf  5b                pop        rbx
00000fe0  415e              pop        r14
00000fe2  415f              pop        r15
00000fe4  5d                pop        rbp
00000fe5  c3                ret        
