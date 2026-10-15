# APK member: lib/x86/libnflanger.so
# Library SHA-256: 358a7d5d8ff8336f0dff81448c92ca52cbb50537d20bc26689308d57c3842482
# Function: Java_sofeh_audio_NFlanger_processLeft; address=0x900; bytes=667
# Decoder mode: default; decoded_bytes=667
00000900  55                push       ebp
00000901  89e5              mov        ebp, esp
00000903  53                push       ebx
00000904  57                push       edi
00000905  56                push       esi
00000906  83e4f0            and        esp, 0xfffffff0
00000909  83ec40            sub        esp, 0x40
0000090c  e800000000        call       0x911
00000911  5b                pop        ebx
00000912  81c353180000      add        ebx, 0x1853
00000918  8b7510            mov        esi, dword ptr [ebp + 0x10]
0000091b  8b4514            mov        eax, dword ptr [ebp + 0x14]
0000091e  09f0              or         eax, esi
00000920  0f8427010000      je         0xa4d
00000926  f30f104518        movss      xmm0, dword ptr [ebp + 0x18]
0000092b  f30f104d1c        movss      xmm1, dword ptr [ebp + 0x1c]
00000930  0f14c1            unpcklps   xmm0, xmm1
00000933  0f13442430        movlps     qword ptr [esp + 0x30], xmm0
00000938  df6c2430          fild       qword ptr [esp + 0x30]
0000093c  d95c2424          fstp       dword ptr [esp + 0x24]
00000940  0f57c0            xorps      xmm0, xmm0
00000943  f30f2a06          cvtsi2ss   xmm0, dword ptr [esi]
00000947  f30f109620080000  movss      xmm2, dword ptr [esi + 0x820]
0000094f  f30f5993c0e3ffff  mulss      xmm2, dword ptr [ebx - 0x1c40]
00000957  f30f59d0          mulss      xmm2, xmm0
0000095b  f30f58542424      addss      xmm2, dword ptr [esp + 0x24]
00000961  f30f119628080000  movss      dword ptr [esi + 0x828], xmm2
00000969  8b7e18            mov        edi, dword ptr [esi + 0x18]
0000096c  69c7cdcccccc      imul       eax, edi, 0xcccccccd
00000972  c1c802            ror        eax, 2
00000975  3dcccccc0c        cmp        eax, 0xccccccc
0000097a  0f87ae000000      ja         0xa2e
00000980  f30f7e83cce3ffff  movq       xmm0, qword ptr [ebx - 0x1c34]
00000988  660f6ecf          movd       xmm1, edi
0000098c  660febc8          por        xmm1, xmm0
00000990  f20f5cc8          subsd      xmm1, xmm0
00000994  0f57c0            xorps      xmm0, xmm0
00000997  f20f5ac1          cvtsd2ss   xmm0, xmm1
0000099b  0f57c9            xorps      xmm1, xmm1
0000099e  f30f2a4e04        cvtsi2ss   xmm1, dword ptr [esi + 4]
000009a3  f30f59861c080000  mulss      xmm0, dword ptr [esi + 0x81c]
000009ab  f30f58c8          addss      xmm1, xmm0
000009af  0f57c0            xorps      xmm0, xmm0
000009b2  f30f5ac1          cvtss2sd   xmm0, xmm1
000009b6  f20f110424        movsd      qword ptr [esp], xmm0
000009bb  f30f11542414      movss      dword ptr [esp + 0x14], xmm2
000009c1  e8aa060000        call       0x1070
000009c6  d95c241c          fstp       dword ptr [esp + 0x1c]
000009ca  f30f1044241c      movss      xmm0, dword ptr [esp + 0x1c]
000009d0  f30f58c0          addss      xmm0, xmm0
000009d4  f30f5883b8e3ffff  addss      xmm0, dword ptr [ebx - 0x1c48]
000009dc  f30f5ac0          cvtss2sd   xmm0, xmm0
000009e0  f20f110424        movsd      qword ptr [esp], xmm0
000009e5  e896060000        call       0x1080
000009ea  f30f10542414      movss      xmm2, dword ptr [esp + 0x14]
000009f0  d95c2420          fstp       dword ptr [esp + 0x20]
000009f4  f30f10442420      movss      xmm0, dword ptr [esp + 0x20]
000009fa  0f57c9            xorps      xmm1, xmm1
000009fd  f30f2a4e08        cvtsi2ss   xmm1, dword ptr [esi + 8]
00000a02  f30f5883b4e3ffff  addss      xmm0, dword ptr [ebx - 0x1c4c]
00000a0a  f30f598bbce3ffff  mulss      xmm1, dword ptr [ebx - 0x1c44]
00000a12  f30f59c8          mulss      xmm1, xmm0
00000a16  f30f5e8e30080000  divss      xmm1, dword ptr [esi + 0x830]
00000a1e  f30f588bace3ffff  addss      xmm1, dword ptr [ebx - 0x1c54]
00000a26  f30f118e14080000  movss      dword ptr [esi + 0x814], xmm1
00000a2e  47                inc        edi
00000a2f  897e18            mov        dword ptr [esi + 0x18], edi
00000a32  8b460c            mov        eax, dword ptr [esi + 0xc]
00000a35  85c0              test       eax, eax
00000a37  7e1d              jle        0xa56
00000a39  f30f108614080000  movss      xmm0, dword ptr [esi + 0x814]
00000a41  83f801            cmp        eax, 1
00000a44  7517              jne        0xa5d
00000a46  31c9              xor        ecx, ecx
00000a48  e9a2000000        jmp        0xaef
00000a4d  31c0              xor        eax, eax
00000a4f  31d2              xor        edx, edx
00000a51  e93d010000        jmp        0xb93
00000a56  31c0              xor        eax, eax
00000a58  e9ce000000        jmp        0xb2b
00000a5d  89c2              mov        edx, eax
00000a5f  81e2feffff7f      and        edx, 0x7ffffffe
00000a65  31c9              xor        ecx, ecx
00000a67  90                nop        
00000a68  90                nop        
00000a69  90                nop        
00000a6a  90                nop        
00000a6b  90                nop        
00000a6c  90                nop        
00000a6d  90                nop        
00000a6e  90                nop        
00000a6f  90                nop        
00000a70  f30f104c8e1c      movss      xmm1, dword ptr [esi + ecx*4 + 0x1c]
00000a76  f30f118e18080000  movss      dword ptr [esi + 0x818], xmm1
00000a7e  f30f59c1          mulss      xmm0, xmm1
00000a82  f30f58c2          addss      xmm0, xmm2
00000a86  f30f11448e1c      movss      dword ptr [esi + ecx*4 + 0x1c], xmm0
00000a8c  f30f108e14080000  movss      xmm1, dword ptr [esi + 0x814]
00000a94  f30f109618080000  movss      xmm2, dword ptr [esi + 0x818]
00000a9c  f30f59c1          mulss      xmm0, xmm1
00000aa0  f30f5cd0          subss      xmm2, xmm0
00000aa4  f30f119628080000  movss      dword ptr [esi + 0x828], xmm2
00000aac  f30f105c8e20      movss      xmm3, dword ptr [esi + ecx*4 + 0x20]
00000ab2  f30f119e18080000  movss      dword ptr [esi + 0x818], xmm3
00000aba  f30f59d9          mulss      xmm3, xmm1
00000abe  f30f58da          addss      xmm3, xmm2
00000ac2  f30f115c8e20      movss      dword ptr [esi + ecx*4 + 0x20], xmm3
00000ac8  f30f108614080000  movss      xmm0, dword ptr [esi + 0x814]
00000ad0  f30f109618080000  movss      xmm2, dword ptr [esi + 0x818]
00000ad8  f30f59d8          mulss      xmm3, xmm0
00000adc  f30f5cd3          subss      xmm2, xmm3
00000ae0  f30f119628080000  movss      dword ptr [esi + 0x828], xmm2
00000ae8  83c102            add        ecx, 2
00000aeb  39ca              cmp        edx, ecx
00000aed  7581              jne        0xa70
00000aef  a801              test       al, 1
00000af1  7438              je         0xb2b
00000af3  f30f104c8e1c      movss      xmm1, dword ptr [esi + ecx*4 + 0x1c]
00000af9  f30f118e18080000  movss      dword ptr [esi + 0x818], xmm1
00000b01  f30f59c1          mulss      xmm0, xmm1
00000b05  f30f58c2          addss      xmm0, xmm2
00000b09  f30f11448e1c      movss      dword ptr [esi + ecx*4 + 0x1c], xmm0
00000b0f  f30f109618080000  movss      xmm2, dword ptr [esi + 0x818]
00000b17  f30f598614080000  mulss      xmm0, dword ptr [esi + 0x814]
00000b1f  f30f5cd0          subss      xmm2, xmm0
00000b23  f30f119628080000  movss      dword ptr [esi + 0x828], xmm2
00000b2b  894614            mov        dword ptr [esi + 0x14], eax
00000b2e  f30f119620080000  movss      dword ptr [esi + 0x820], xmm2
00000b36  8b4610            mov        eax, dword ptr [esi + 0x10]
00000b39  0f57c0            xorps      xmm0, xmm0
00000b3c  f30f2ac0          cvtsi2ss   xmm0, eax
00000b40  b9ff000000        mov        ecx, 0xff
00000b45  29c1              sub        ecx, eax
00000b47  89c8              mov        eax, ecx
00000b49  8b7518            mov        esi, dword ptr [ebp + 0x18]
00000b4c  f7e6              mul        esi
00000b4e  8b7d1c            mov        edi, dword ptr [ebp + 0x1c]
00000b51  0faff9            imul       edi, ecx
00000b54  01fa              add        edx, edi
00000b56  c1f91f            sar        ecx, 0x1f
00000b59  0fafce            imul       ecx, esi
00000b5c  01d1              add        ecx, edx
00000b5e  660f6ec9          movd       xmm1, ecx
00000b62  f30f59c2          mulss      xmm0, xmm2
00000b66  660f6ed0          movd       xmm2, eax
00000b6a  660f62d1          punpckldq  xmm2, xmm1
00000b6e  660fd6542428      movq       qword ptr [esp + 0x28], xmm2
00000b74  df6c2428          fild       qword ptr [esp + 0x28]
00000b78  d95c2418          fstp       dword ptr [esp + 0x18]
00000b7c  f30f58442418      addss      xmm0, dword ptr [esp + 0x18]
00000b82  f30f5983c4e3ffff  mulss      xmm0, dword ptr [ebx - 0x1c3c]
00000b8a  f30f2cc0          cvttss2si  eax, xmm0
00000b8e  89c2              mov        edx, eax
00000b90  c1fa1f            sar        edx, 0x1f
00000b93  8d65f4            lea        esp, [ebp - 0xc]
00000b96  5e                pop        esi
00000b97  5f                pop        edi
00000b98  5b                pop        ebx
00000b99  5d                pop        ebp
00000b9a  c3                ret        
