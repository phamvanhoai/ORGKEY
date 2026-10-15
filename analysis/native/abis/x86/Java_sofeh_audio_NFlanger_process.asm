# APK member: lib/x86/libnflanger.so
# Library SHA-256: 358a7d5d8ff8336f0dff81448c92ca52cbb50537d20bc26689308d57c3842482
# Function: Java_sofeh_audio_NFlanger_process; address=0xd80; bytes=607
# Decoder mode: default; decoded_bytes=607
00000d80  55                push       ebp
00000d81  89e5              mov        ebp, esp
00000d83  53                push       ebx
00000d84  57                push       edi
00000d85  56                push       esi
00000d86  83e4f0            and        esp, 0xfffffff0
00000d89  83ec20            sub        esp, 0x20
00000d8c  e800000000        call       0xd91
00000d91  5b                pop        ebx
00000d92  81c3d3130000      add        ebx, 0x13d3
00000d98  8b7d10            mov        edi, dword ptr [ebp + 0x10]
00000d9b  8b4514            mov        eax, dword ptr [ebp + 0x14]
00000d9e  09f8              or         eax, edi
00000da0  0f8414010000      je         0xeba
00000da6  0fbf4518          movsx      eax, word ptr [ebp + 0x18]
00000daa  89442410          mov        dword ptr [esp + 0x10], eax
00000dae  f30f2ac0          cvtsi2ss   xmm0, eax
00000db2  f30f2a0f          cvtsi2ss   xmm1, dword ptr [edi]
00000db6  f30f109720080000  movss      xmm2, dword ptr [edi + 0x820]
00000dbe  f30f5993c0e3ffff  mulss      xmm2, dword ptr [ebx - 0x1c40]
00000dc6  f30f59d1          mulss      xmm2, xmm1
00000dca  f30f58d0          addss      xmm2, xmm0
00000dce  f30f119728080000  movss      dword ptr [edi + 0x828], xmm2
00000dd6  8b7718            mov        esi, dword ptr [edi + 0x18]
00000dd9  69c6cdcccccc      imul       eax, esi, 0xcccccccd
00000ddf  c1c802            ror        eax, 2
00000de2  3dcccccc0c        cmp        eax, 0xccccccc
00000de7  0f87ae000000      ja         0xe9b
00000ded  f30f7e83cce3ffff  movq       xmm0, qword ptr [ebx - 0x1c34]
00000df5  660f6ece          movd       xmm1, esi
00000df9  660febc8          por        xmm1, xmm0
00000dfd  f20f5cc8          subsd      xmm1, xmm0
00000e01  0f57c0            xorps      xmm0, xmm0
00000e04  f20f5ac1          cvtsd2ss   xmm0, xmm1
00000e08  0f57c9            xorps      xmm1, xmm1
00000e0b  f30f2a4f04        cvtsi2ss   xmm1, dword ptr [edi + 4]
00000e10  f30f59871c080000  mulss      xmm0, dword ptr [edi + 0x81c]
00000e18  f30f58c8          addss      xmm1, xmm0
00000e1c  0f57c0            xorps      xmm0, xmm0
00000e1f  f30f5ac1          cvtss2sd   xmm0, xmm1
00000e23  f20f110424        movsd      qword ptr [esp], xmm0
00000e28  f30f1154240c      movss      dword ptr [esp + 0xc], xmm2
00000e2e  e83d020000        call       0x1070
00000e33  d95c2414          fstp       dword ptr [esp + 0x14]
00000e37  f30f10442414      movss      xmm0, dword ptr [esp + 0x14]
00000e3d  f30f58c0          addss      xmm0, xmm0
00000e41  f30f5883b8e3ffff  addss      xmm0, dword ptr [ebx - 0x1c48]
00000e49  f30f5ac0          cvtss2sd   xmm0, xmm0
00000e4d  f20f110424        movsd      qword ptr [esp], xmm0
00000e52  e829020000        call       0x1080
00000e57  f30f1054240c      movss      xmm2, dword ptr [esp + 0xc]
00000e5d  d95c2418          fstp       dword ptr [esp + 0x18]
00000e61  f30f10442418      movss      xmm0, dword ptr [esp + 0x18]
00000e67  0f57c9            xorps      xmm1, xmm1
00000e6a  f30f2a4f08        cvtsi2ss   xmm1, dword ptr [edi + 8]
00000e6f  f30f5883b4e3ffff  addss      xmm0, dword ptr [ebx - 0x1c4c]
00000e77  f30f598bbce3ffff  mulss      xmm1, dword ptr [ebx - 0x1c44]
00000e7f  f30f59c8          mulss      xmm1, xmm0
00000e83  f30f5e8f30080000  divss      xmm1, dword ptr [edi + 0x830]
00000e8b  f30f588bace3ffff  addss      xmm1, dword ptr [ebx - 0x1c54]
00000e93  f30f118f14080000  movss      dword ptr [edi + 0x814], xmm1
00000e9b  46                inc        esi
00000e9c  897718            mov        dword ptr [edi + 0x18], esi
00000e9f  8b470c            mov        eax, dword ptr [edi + 0xc]
00000ea2  85c0              test       eax, eax
00000ea4  7e1b              jle        0xec1
00000ea6  f30f108714080000  movss      xmm0, dword ptr [edi + 0x814]
00000eae  83f801            cmp        eax, 1
00000eb1  7515              jne        0xec8
00000eb3  31c9              xor        ecx, ecx
00000eb5  e9a5000000        jmp        0xf5f
00000eba  31c0              xor        eax, eax
00000ebc  e916010000        jmp        0xfd7
00000ec1  31c0              xor        eax, eax
00000ec3  e9d3000000        jmp        0xf9b
00000ec8  89c2              mov        edx, eax
00000eca  81e2feffff7f      and        edx, 0x7ffffffe
00000ed0  31c9              xor        ecx, ecx
00000ed2  90                nop        
00000ed3  90                nop        
00000ed4  90                nop        
00000ed5  90                nop        
00000ed6  90                nop        
00000ed7  90                nop        
00000ed8  90                nop        
00000ed9  90                nop        
00000eda  90                nop        
00000edb  90                nop        
00000edc  90                nop        
00000edd  90                nop        
00000ede  90                nop        
00000edf  90                nop        
00000ee0  f30f104c8f1c      movss      xmm1, dword ptr [edi + ecx*4 + 0x1c]
00000ee6  f30f118f18080000  movss      dword ptr [edi + 0x818], xmm1
00000eee  f30f59c1          mulss      xmm0, xmm1
00000ef2  f30f58c2          addss      xmm0, xmm2
00000ef6  f30f11448f1c      movss      dword ptr [edi + ecx*4 + 0x1c], xmm0
00000efc  f30f108f14080000  movss      xmm1, dword ptr [edi + 0x814]
00000f04  f30f109718080000  movss      xmm2, dword ptr [edi + 0x818]
00000f0c  f30f59c1          mulss      xmm0, xmm1
00000f10  f30f5cd0          subss      xmm2, xmm0
00000f14  f30f119728080000  movss      dword ptr [edi + 0x828], xmm2
00000f1c  f30f105c8f20      movss      xmm3, dword ptr [edi + ecx*4 + 0x20]
00000f22  f30f119f18080000  movss      dword ptr [edi + 0x818], xmm3
00000f2a  f30f59d9          mulss      xmm3, xmm1
00000f2e  f30f58da          addss      xmm3, xmm2
00000f32  f30f115c8f20      movss      dword ptr [edi + ecx*4 + 0x20], xmm3
00000f38  f30f108714080000  movss      xmm0, dword ptr [edi + 0x814]
00000f40  f30f109718080000  movss      xmm2, dword ptr [edi + 0x818]
00000f48  f30f59d8          mulss      xmm3, xmm0
00000f4c  f30f5cd3          subss      xmm2, xmm3
00000f50  f30f119728080000  movss      dword ptr [edi + 0x828], xmm2
00000f58  83c102            add        ecx, 2
00000f5b  39ca              cmp        edx, ecx
00000f5d  7581              jne        0xee0
00000f5f  a801              test       al, 1
00000f61  7438              je         0xf9b
00000f63  f30f104c8f1c      movss      xmm1, dword ptr [edi + ecx*4 + 0x1c]
00000f69  f30f118f18080000  movss      dword ptr [edi + 0x818], xmm1
00000f71  f30f59c1          mulss      xmm0, xmm1
00000f75  f30f58c2          addss      xmm0, xmm2
00000f79  f30f11448f1c      movss      dword ptr [edi + ecx*4 + 0x1c], xmm0
00000f7f  f30f109718080000  movss      xmm2, dword ptr [edi + 0x818]
00000f87  f30f598714080000  mulss      xmm0, dword ptr [edi + 0x814]
00000f8f  f30f5cd0          subss      xmm2, xmm0
00000f93  f30f119728080000  movss      dword ptr [edi + 0x828], xmm2
00000f9b  894714            mov        dword ptr [edi + 0x14], eax
00000f9e  f30f119720080000  movss      dword ptr [edi + 0x820], xmm2
00000fa6  8b4710            mov        eax, dword ptr [edi + 0x10]
00000fa9  0f57c0            xorps      xmm0, xmm0
00000fac  f30f2ac0          cvtsi2ss   xmm0, eax
00000fb0  f30f59c2          mulss      xmm0, xmm2
00000fb4  b9ff000000        mov        ecx, 0xff
00000fb9  29c1              sub        ecx, eax
00000fbb  0faf4c2410        imul       ecx, dword ptr [esp + 0x10]
00000fc0  0f57c9            xorps      xmm1, xmm1
00000fc3  f30f2ac9          cvtsi2ss   xmm1, ecx
00000fc7  f30f58c8          addss      xmm1, xmm0
00000fcb  f30f598bc4e3ffff  mulss      xmm1, dword ptr [ebx - 0x1c3c]
00000fd3  f30f2cc1          cvttss2si  eax, xmm1
00000fd7  8d65f4            lea        esp, [ebp - 0xc]
00000fda  5e                pop        esi
00000fdb  5f                pop        edi
00000fdc  5b                pop        ebx
00000fdd  5d                pop        ebp
00000fde  c3                ret        
