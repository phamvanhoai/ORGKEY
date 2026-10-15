# APK member: lib/x86_64/libnflanger.so
# Library SHA-256: d2a7cec8840993d6dc944a3f0999a7fe2667decd0004b0e78fc4f98b33ea053e
# Function: Java_sofeh_audio_NFlanger_processRight; address=0xc30; bytes=387
# Decoder mode: default; decoded_bytes=387
00000c30  31c0              xor        eax, eax
00000c32  4885d2            test       rdx, rdx
00000c35  0f8477010000      je         0xdb2
00000c3b  f3480f2ac9        cvtsi2ss   xmm1, rcx
00000c40  f30f2a12          cvtsi2ss   xmm2, dword ptr [rdx]
00000c44  f30f108224080000  movss      xmm0, dword ptr [rdx + 0x824]
00000c4c  f30f5905b0faffff  mulss      xmm0, dword ptr [rip - 0x550]
00000c54  f30f59c2          mulss      xmm0, xmm2
00000c58  f30f58c1          addss      xmm0, xmm1
00000c5c  f30f11822c080000  movss      dword ptr [rdx + 0x82c], xmm0
00000c64  4863420c          movsxd     rax, dword ptr [rdx + 0xc]
00000c68  4885c0            test       rax, rax
00000c6b  7e14              jle        0xc81
00000c6d  f30f108a14080000  movss      xmm1, dword ptr [rdx + 0x814]
00000c75  83f801            cmp        eax, 1
00000c78  750e              jne        0xc88
00000c7a  31f6              xor        esi, esi
00000c7c  e9b0000000        jmp        0xd31
00000c81  31c0              xor        eax, eax
00000c83  e9eb000000        jmp        0xd73
00000c88  89c7              mov        edi, eax
00000c8a  81e7feffff7f      and        edi, 0x7ffffffe
00000c90  31f6              xor        esi, esi
00000c92  66666666662e0f1f840000000000  nop        word ptr cs:[rax + rax]
00000ca0  f30f1094b218040000  movss      xmm2, dword ptr [rdx + rsi*4 + 0x418]
00000ca9  f30f119218080000  movss      dword ptr [rdx + 0x818], xmm2
00000cb1  f30f59ca          mulss      xmm1, xmm2
00000cb5  f30f58c8          addss      xmm1, xmm0
00000cb9  f30f118cb218040000  movss      dword ptr [rdx + rsi*4 + 0x418], xmm1
00000cc2  f30f108214080000  movss      xmm0, dword ptr [rdx + 0x814]
00000cca  f30f109218080000  movss      xmm2, dword ptr [rdx + 0x818]
00000cd2  f30f59c8          mulss      xmm1, xmm0
00000cd6  f30f5cd1          subss      xmm2, xmm1
00000cda  f30f11922c080000  movss      dword ptr [rdx + 0x82c], xmm2
00000ce2  f30f109cb21c040000  movss      xmm3, dword ptr [rdx + rsi*4 + 0x41c]
00000ceb  f30f119a18080000  movss      dword ptr [rdx + 0x818], xmm3
00000cf3  f30f59d8          mulss      xmm3, xmm0
00000cf7  f30f58da          addss      xmm3, xmm2
00000cfb  f30f119cb21c040000  movss      dword ptr [rdx + rsi*4 + 0x41c], xmm3
00000d04  f30f108a14080000  movss      xmm1, dword ptr [rdx + 0x814]
00000d0c  f30f108218080000  movss      xmm0, dword ptr [rdx + 0x818]
00000d14  f30f59d9          mulss      xmm3, xmm1
00000d18  f30f5cc3          subss      xmm0, xmm3
00000d1c  f30f11822c080000  movss      dword ptr [rdx + 0x82c], xmm0
00000d24  4883c602          add        rsi, 2
00000d28  4839f7            cmp        rdi, rsi
00000d2b  0f856fffffff      jne        0xca0
00000d31  a801              test       al, 1
00000d33  743e              je         0xd73
00000d35  f30f1094b218040000  movss      xmm2, dword ptr [rdx + rsi*4 + 0x418]
00000d3e  f30f119218080000  movss      dword ptr [rdx + 0x818], xmm2
00000d46  f30f59ca          mulss      xmm1, xmm2
00000d4a  f30f58c8          addss      xmm1, xmm0
00000d4e  f30f118cb218040000  movss      dword ptr [rdx + rsi*4 + 0x418], xmm1
00000d57  f30f108218080000  movss      xmm0, dword ptr [rdx + 0x818]
00000d5f  f30f598a14080000  mulss      xmm1, dword ptr [rdx + 0x814]
00000d67  f30f5cc1          subss      xmm0, xmm1
00000d6b  f30f11822c080000  movss      dword ptr [rdx + 0x82c], xmm0
00000d73  894214            mov        dword ptr [rdx + 0x14], eax
00000d76  f30f118224080000  movss      dword ptr [rdx + 0x824], xmm0
00000d7e  48634210          movsxd     rax, dword ptr [rdx + 0x10]
00000d82  0f57c9            xorps      xmm1, xmm1
00000d85  f30f2ac8          cvtsi2ss   xmm1, eax
00000d89  f30f59c8          mulss      xmm1, xmm0
00000d8d  baff000000        mov        edx, 0xff
00000d92  4829c2            sub        rdx, rax
00000d95  480fafd1          imul       rdx, rcx
00000d99  0f57c0            xorps      xmm0, xmm0
00000d9c  f3480f2ac2        cvtsi2ss   xmm0, rdx
00000da1  f30f58c1          addss      xmm0, xmm1
00000da5  f30f59055bf9ffff  mulss      xmm0, dword ptr [rip - 0x6a5]
00000dad  f3480f2cc0        cvttss2si  rax, xmm0
00000db2  c3                ret        
