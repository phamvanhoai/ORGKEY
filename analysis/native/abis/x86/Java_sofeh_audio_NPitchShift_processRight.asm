# APK member: lib/x86/libnpitchshift.so
# Library SHA-256: 4b9d17ea2c347096a6285494e18c00ebbd50a630c337141ab53c3b787fc2b7fb
# Function: Java_sofeh_audio_NPitchShift_processRight; address=0x23e0; bytes=115
# Decoder mode: default; decoded_bytes=115
000023e0  55                push       ebp
000023e1  89e5              mov        ebp, esp
000023e3  53                push       ebx
000023e4  83e4f0            and        esp, 0xfffffff0
000023e7  83ec30            sub        esp, 0x30
000023ea  e800000000        call       0x23ef
000023ef  5b                pop        ebx
000023f0  81c3c5120000      add        ebx, 0x12c5
000023f6  8b4510            mov        eax, dword ptr [ebp + 0x10]
000023f9  8b4d14            mov        ecx, dword ptr [ebp + 0x14]
000023fc  09c1              or         ecx, eax
000023fe  7449              je         0x2449
00002400  f30f2a4518        cvtsi2ss   xmm0, dword ptr [ebp + 0x18]
00002405  8b4d20            mov        ecx, dword ptr [ebp + 0x20]
00002408  f30f5e8398d0ffff  divss      xmm0, dword ptr [ebx - 0x2f68]
00002410  050cb00000        add        eax, 0xb00c
00002415  f30f2a4d1c        cvtsi2ss   xmm1, dword ptr [ebp + 0x1c]
0000241a  894c2414          mov        dword ptr [esp + 0x14], ecx
0000241e  f30f114c2410      movss      dword ptr [esp + 0x10], xmm1
00002424  890424            mov        dword ptr [esp], eax
00002427  f30f11442404      movss      dword ptr [esp + 4], xmm0
0000242d  c744240c04000000  mov        dword ptr [esp + 0xc], 4
00002435  c744240800040000  mov        dword ptr [esp + 8], 0x400
0000243d  e88e010000        call       0x25d0
00002442  89c2              mov        edx, eax
00002444  c1fa1f            sar        edx, 0x1f
00002447  eb04              jmp        0x244d
00002449  31c0              xor        eax, eax
0000244b  31d2              xor        edx, edx
0000244d  8d65fc            lea        esp, [ebp - 4]
00002450  5b                pop        ebx
00002451  5d                pop        ebp
00002452  c3                ret        
