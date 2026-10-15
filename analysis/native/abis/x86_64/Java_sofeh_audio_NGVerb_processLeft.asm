# APK member: lib/x86_64/libngverb.so
# Library SHA-256: 8fd68c89616a094decd0ad7f229e0d8e89656b29446477be056c19051fc5f443
# Function: Java_sofeh_audio_NGVerb_processLeft; address=0x2220; bytes=106
# Decoder mode: default; decoded_bytes=106
00002220  55                push       rbp
00002221  4889e5            mov        rbp, rsp
00002224  4883ec10          sub        rsp, 0x10
00002228  64488b042528000000  mov        rax, qword ptr fs:[0x28]
00002231  488945f8          mov        qword ptr [rbp - 8], rax
00002235  4885d2            test       rdx, rdx
00002238  7434              je         0x226e
0000223a  f3480f2ac1        cvtsi2ss   xmm0, rcx
0000223f  f30f5905a1edffff  mulss      xmm0, dword ptr [rip - 0x125f]
00002247  488d75f4          lea        rsi, [rbp - 0xc]
0000224b  488d45f0          lea        rax, [rbp - 0x10]
0000224f  4889d7            mov        rdi, rdx
00002252  4889c2            mov        rdx, rax
00002255  e836000000        call       0x2290
0000225a  f30f1045f4        movss      xmm0, dword ptr [rbp - 0xc]
0000225f  f30f5905b5edffff  mulss      xmm0, dword ptr [rip - 0x124b]
00002267  f3480f2cc0        cvttss2si  rax, xmm0
0000226c  eb02              jmp        0x2270
0000226e  31c0              xor        eax, eax
00002270  64488b0c2528000000  mov        rcx, qword ptr fs:[0x28]
00002279  483b4df8          cmp        rcx, qword ptr [rbp - 8]
0000227d  7506              jne        0x2285
0000227f  4883c410          add        rsp, 0x10
00002283  5d                pop        rbp
00002284  c3                ret        
00002285  e8b60d0000        call       0x3040
