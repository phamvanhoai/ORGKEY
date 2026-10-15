# APK member: lib/x86/libnequalizer.so
# Library SHA-256: 0c7dfba2ccd6a4c88a74500c5b3ecac4f88ff3e4abb681dafc117af7ee94b44b
# Function: Java_sofeh_audio_NEqualizer_bandMove; address=0x9a0; bytes=91
# Decoder mode: default; decoded_bytes=91
000009a0  55                push       ebp
000009a1  89e5              mov        ebp, esp
000009a3  83e4fc            and        esp, 0xfffffffc
000009a6  e800000000        call       0x9ab
000009ab  59                pop        ecx
000009ac  81c1492e0000      add        ecx, 0x2e49
000009b2  8b4510            mov        eax, dword ptr [ebp + 0x10]
000009b5  8b5514            mov        edx, dword ptr [ebp + 0x14]
000009b8  09c2              or         edx, eax
000009ba  743b              je         0x9f7
000009bc  f30f104518        movss      xmm0, dword ptr [ebp + 0x18]
000009c1  f30f1089bccdffff  movss      xmm1, dword ptr [ecx - 0x3244]
000009c9  0f28d1            movaps     xmm2, xmm1
000009cc  f30f5cd0          subss      xmm2, xmm0
000009d0  f30f2cca          cvttss2si  ecx, xmm2
000009d4  f30f58c1          addss      xmm0, xmm1
000009d8  f30f2cd0          cvttss2si  edx, xmm0
000009dc  660f6ec1          movd       xmm0, ecx
000009e0  660f70c000        pshufd     xmm0, xmm0, 0
000009e5  f30f7f00          movdqu     xmmword ptr [eax], xmm0
000009e9  660f6ec2          movd       xmm0, edx
000009ed  660f70c000        pshufd     xmm0, xmm0, 0
000009f2  f30f7f4010        movdqu     xmmword ptr [eax + 0x10], xmm0
000009f7  89ec              mov        esp, ebp
000009f9  5d                pop        ebp
000009fa  c3                ret        
