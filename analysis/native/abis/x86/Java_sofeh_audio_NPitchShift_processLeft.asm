# APK member: lib/x86/libnpitchshift.so
# Library SHA-256: 4b9d17ea2c347096a6285494e18c00ebbd50a630c337141ab53c3b787fc2b7fb
# Function: Java_sofeh_audio_NPitchShift_processLeft; address=0x2370; bytes=110
# Decoder mode: default; decoded_bytes=110
00002370  55                push       ebp
00002371  89e5              mov        ebp, esp
00002373  53                push       ebx
00002374  83e4f0            and        esp, 0xfffffff0
00002377  83ec30            sub        esp, 0x30
0000237a  e800000000        call       0x237f
0000237f  5b                pop        ebx
00002380  81c335130000      add        ebx, 0x1335
00002386  8b4510            mov        eax, dword ptr [ebp + 0x10]
00002389  8b4d14            mov        ecx, dword ptr [ebp + 0x14]
0000238c  09c1              or         ecx, eax
0000238e  7444              je         0x23d4
00002390  f30f2a4518        cvtsi2ss   xmm0, dword ptr [ebp + 0x18]
00002395  f30f5e8398d0ffff  divss      xmm0, dword ptr [ebx - 0x2f68]
0000239d  8b4d20            mov        ecx, dword ptr [ebp + 0x20]
000023a0  f30f2a4d1c        cvtsi2ss   xmm1, dword ptr [ebp + 0x1c]
000023a5  894c2414          mov        dword ptr [esp + 0x14], ecx
000023a9  f30f114c2410      movss      dword ptr [esp + 0x10], xmm1
000023af  890424            mov        dword ptr [esp], eax
000023b2  f30f11442404      movss      dword ptr [esp + 4], xmm0
000023b8  c744240c04000000  mov        dword ptr [esp + 0xc], 4
000023c0  c744240800040000  mov        dword ptr [esp + 8], 0x400
000023c8  e803020000        call       0x25d0
000023cd  89c2              mov        edx, eax
000023cf  c1fa1f            sar        edx, 0x1f
000023d2  eb04              jmp        0x23d8
000023d4  31c0              xor        eax, eax
000023d6  31d2              xor        edx, edx
000023d8  8d65fc            lea        esp, [ebp - 4]
000023db  5b                pop        ebx
000023dc  5d                pop        ebp
000023dd  c3                ret        
