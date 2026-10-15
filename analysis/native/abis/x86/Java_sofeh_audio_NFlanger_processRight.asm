# APK member: lib/x86/libnflanger.so
# Library SHA-256: 358a7d5d8ff8336f0dff81448c92ca52cbb50537d20bc26689308d57c3842482
# Function: Java_sofeh_audio_NFlanger_processRight; address=0xba0; bytes=477
# Decoder mode: default; decoded_bytes=477
00000ba0  55                push       ebp
00000ba1  89e5              mov        ebp, esp
00000ba3  53                push       ebx
00000ba4  57                push       edi
00000ba5  56                push       esi
00000ba6  83e4f8            and        esp, 0xfffffff8
00000ba9  83ec20            sub        esp, 0x20
00000bac  e800000000        call       0xbb1
00000bb1  5e                pop        esi
00000bb2  81c6b3150000      add        esi, 0x15b3
00000bb8  8b4510            mov        eax, dword ptr [ebp + 0x10]
00000bbb  8b4d14            mov        ecx, dword ptr [ebp + 0x14]
00000bbe  09c1              or         ecx, eax
00000bc0  7462              je         0xc24
00000bc2  8b7d1c            mov        edi, dword ptr [ebp + 0x1c]
00000bc5  660f6e4518        movd       xmm0, dword ptr [ebp + 0x18]
00000bca  660f6ecf          movd       xmm1, edi
00000bce  660f62c1          punpckldq  xmm0, xmm1
00000bd2  660fd6442410      movq       qword ptr [esp + 0x10], xmm0
00000bd8  df6c2410          fild       qword ptr [esp + 0x10]
00000bdc  d95c2404          fstp       dword ptr [esp + 4]
00000be0  0f57c9            xorps      xmm1, xmm1
00000be3  f30f2a08          cvtsi2ss   xmm1, dword ptr [eax]
00000be7  f30f108024080000  movss      xmm0, dword ptr [eax + 0x824]
00000bef  f30f5986c0e3ffff  mulss      xmm0, dword ptr [esi - 0x1c40]
00000bf7  f30f59c1          mulss      xmm0, xmm1
00000bfb  f30f58442404      addss      xmm0, dword ptr [esp + 4]
00000c01  f30f11802c080000  movss      dword ptr [eax + 0x82c], xmm0
00000c09  8b580c            mov        ebx, dword ptr [eax + 0xc]
00000c0c  85db              test       ebx, ebx
00000c0e  7e1d              jle        0xc2d
00000c10  f30f108814080000  movss      xmm1, dword ptr [eax + 0x814]
00000c18  83fb01            cmp        ebx, 1
00000c1b  7517              jne        0xc34
00000c1d  31d2              xor        edx, edx
00000c1f  e9ab000000        jmp        0xccf
00000c24  31c0              xor        eax, eax
00000c26  31d2              xor        edx, edx
00000c28  e948010000        jmp        0xd75
00000c2d  31db              xor        ebx, ebx
00000c2f  e9de000000        jmp        0xd12
00000c34  89d9              mov        ecx, ebx
00000c36  81e1feffff7f      and        ecx, 0x7ffffffe
00000c3c  31d2              xor        edx, edx
00000c3e  90                nop        
00000c3f  90                nop        
00000c40  f30f10949018040000  movss      xmm2, dword ptr [eax + edx*4 + 0x418]
00000c49  f30f119018080000  movss      dword ptr [eax + 0x818], xmm2
00000c51  f30f59ca          mulss      xmm1, xmm2
00000c55  f30f58c8          addss      xmm1, xmm0
00000c59  f30f118c9018040000  movss      dword ptr [eax + edx*4 + 0x418], xmm1
00000c62  f30f108014080000  movss      xmm0, dword ptr [eax + 0x814]
00000c6a  f30f109018080000  movss      xmm2, dword ptr [eax + 0x818]
00000c72  f30f59c8          mulss      xmm1, xmm0
00000c76  f30f5cd1          subss      xmm2, xmm1
00000c7a  f30f11902c080000  movss      dword ptr [eax + 0x82c], xmm2
00000c82  f30f109c901c040000  movss      xmm3, dword ptr [eax + edx*4 + 0x41c]
00000c8b  f30f119818080000  movss      dword ptr [eax + 0x818], xmm3
00000c93  f30f59d8          mulss      xmm3, xmm0
00000c97  f30f58da          addss      xmm3, xmm2
00000c9b  f30f119c901c040000  movss      dword ptr [eax + edx*4 + 0x41c], xmm3
00000ca4  f30f108814080000  movss      xmm1, dword ptr [eax + 0x814]
00000cac  f30f108018080000  movss      xmm0, dword ptr [eax + 0x818]
00000cb4  f30f59d9          mulss      xmm3, xmm1
00000cb8  f30f5cc3          subss      xmm0, xmm3
00000cbc  f30f11802c080000  movss      dword ptr [eax + 0x82c], xmm0
00000cc4  83c202            add        edx, 2
00000cc7  39d1              cmp        ecx, edx
00000cc9  0f8571ffffff      jne        0xc40
00000ccf  f6c301            test       bl, 1
00000cd2  743e              je         0xd12
00000cd4  f30f10949018040000  movss      xmm2, dword ptr [eax + edx*4 + 0x418]
00000cdd  f30f119018080000  movss      dword ptr [eax + 0x818], xmm2
00000ce5  f30f59ca          mulss      xmm1, xmm2
00000ce9  f30f58c8          addss      xmm1, xmm0
00000ced  f30f118c9018040000  movss      dword ptr [eax + edx*4 + 0x418], xmm1
00000cf6  f30f108018080000  movss      xmm0, dword ptr [eax + 0x818]
00000cfe  f30f598814080000  mulss      xmm1, dword ptr [eax + 0x814]
00000d06  f30f5cc1          subss      xmm0, xmm1
00000d0a  f30f11802c080000  movss      dword ptr [eax + 0x82c], xmm0
00000d12  895814            mov        dword ptr [eax + 0x14], ebx
00000d15  f30f118024080000  movss      dword ptr [eax + 0x824], xmm0
00000d1d  8b4010            mov        eax, dword ptr [eax + 0x10]
00000d20  0f57c9            xorps      xmm1, xmm1
00000d23  f30f2ac8          cvtsi2ss   xmm1, eax
00000d27  b9ff000000        mov        ecx, 0xff
00000d2c  29c1              sub        ecx, eax
00000d2e  89c8              mov        eax, ecx
00000d30  8b5d18            mov        ebx, dword ptr [ebp + 0x18]
00000d33  f7e3              mul        ebx
00000d35  0faff9            imul       edi, ecx
00000d38  01fa              add        edx, edi
00000d3a  c1f91f            sar        ecx, 0x1f
00000d3d  0fafcb            imul       ecx, ebx
00000d40  01d1              add        ecx, edx
00000d42  660f6ed1          movd       xmm2, ecx
00000d46  f30f59c8          mulss      xmm1, xmm0
00000d4a  660f6ec0          movd       xmm0, eax
00000d4e  660f62c2          punpckldq  xmm0, xmm2
00000d52  660fd6442408      movq       qword ptr [esp + 8], xmm0
00000d58  df6c2408          fild       qword ptr [esp + 8]
00000d5c  d91c24            fstp       dword ptr [esp]
00000d5f  f30f580c24        addss      xmm1, dword ptr [esp]
00000d64  f30f598ec4e3ffff  mulss      xmm1, dword ptr [esi - 0x1c3c]
00000d6c  f30f2cc1          cvttss2si  eax, xmm1
00000d70  89c2              mov        edx, eax
00000d72  c1fa1f            sar        edx, 0x1f
00000d75  8d65f4            lea        esp, [ebp - 0xc]
00000d78  5e                pop        esi
00000d79  5f                pop        edi
00000d7a  5b                pop        ebx
00000d7b  5d                pop        ebp
00000d7c  c3                ret        
