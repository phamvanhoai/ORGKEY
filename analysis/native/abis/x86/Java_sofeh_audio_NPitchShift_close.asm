# APK member: lib/x86/libnpitchshift.so
# Library SHA-256: 4b9d17ea2c347096a6285494e18c00ebbd50a630c337141ab53c3b787fc2b7fb
# Function: Java_sofeh_audio_NPitchShift_close; address=0x24d0; bytes=46
# Decoder mode: default; decoded_bytes=46
000024d0  55                push       ebp
000024d1  89e5              mov        ebp, esp
000024d3  53                push       ebx
000024d4  83e4f0            and        esp, 0xfffffff0
000024d7  83ec10            sub        esp, 0x10
000024da  e800000000        call       0x24df
000024df  5b                pop        ebx
000024e0  81c3d5110000      add        ebx, 0x11d5
000024e6  8b4510            mov        eax, dword ptr [ebp + 0x10]
000024e9  8b4d14            mov        ecx, dword ptr [ebp + 0x14]
000024ec  09c1              or         ecx, eax
000024ee  7408              je         0x24f8
000024f0  890424            mov        dword ptr [esp], eax
000024f3  e868000000        call       0x2560
000024f8  8d65fc            lea        esp, [ebp - 4]
000024fb  5b                pop        ebx
000024fc  5d                pop        ebp
000024fd  c3                ret        
