# APK member: lib/x86/libnpitchshift.so
# Library SHA-256: 4b9d17ea2c347096a6285494e18c00ebbd50a630c337141ab53c3b787fc2b7fb
# Function: Java_sofeh_audio_NPitchShift_process; address=0x2460; bytes=104
# Decoder mode: default; decoded_bytes=104
00002460  55                push       ebp
00002461  89e5              mov        ebp, esp
00002463  53                push       ebx
00002464  83e4f0            and        esp, 0xfffffff0
00002467  83ec30            sub        esp, 0x30
0000246a  e800000000        call       0x246f
0000246f  5b                pop        ebx
00002470  81c345120000      add        ebx, 0x1245
00002476  8b4510            mov        eax, dword ptr [ebp + 0x10]
00002479  8b4d14            mov        ecx, dword ptr [ebp + 0x14]
0000247c  09c1              or         ecx, eax
0000247e  7440              je         0x24c0
00002480  f30f2a4518        cvtsi2ss   xmm0, dword ptr [ebp + 0x18]
00002485  f30f5e8398d0ffff  divss      xmm0, dword ptr [ebx - 0x2f68]
0000248d  f30f2a4d1c        cvtsi2ss   xmm1, dword ptr [ebp + 0x1c]
00002492  0fbf4d20          movsx      ecx, word ptr [ebp + 0x20]
00002496  894c2414          mov        dword ptr [esp + 0x14], ecx
0000249a  f30f114c2410      movss      dword ptr [esp + 0x10], xmm1
000024a0  890424            mov        dword ptr [esp], eax
000024a3  f30f11442404      movss      dword ptr [esp + 4], xmm0
000024a9  c744240c04000000  mov        dword ptr [esp + 0xc], 4
000024b1  c744240800040000  mov        dword ptr [esp + 8], 0x400
000024b9  e812010000        call       0x25d0
000024be  eb02              jmp        0x24c2
000024c0  31c0              xor        eax, eax
000024c2  8d65fc            lea        esp, [ebp - 4]
000024c5  5b                pop        ebx
000024c6  5d                pop        ebp
000024c7  c3                ret        
