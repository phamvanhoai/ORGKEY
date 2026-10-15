# APK member: lib/x86_64/libngverb.so
# Library SHA-256: 8fd68c89616a094decd0ad7f229e0d8e89656b29446477be056c19051fc5f443
# Function: Java_sofeh_audio_NGVerb_processRight; address=0x2930; bytes=106
# Decoder mode: default; decoded_bytes=106
00002930  55                push       rbp
00002931  4889e5            mov        rbp, rsp
00002934  4883ec10          sub        rsp, 0x10
00002938  64488b042528000000  mov        rax, qword ptr fs:[0x28]
00002941  488945f8          mov        qword ptr [rbp - 8], rax
00002945  4885d2            test       rdx, rdx
00002948  7434              je         0x297e
0000294a  f3480f2ac1        cvtsi2ss   xmm0, rcx
0000294f  f30f590591e6ffff  mulss      xmm0, dword ptr [rip - 0x196f]
00002957  488d75f0          lea        rsi, [rbp - 0x10]
0000295b  488d45f4          lea        rax, [rbp - 0xc]
0000295f  4889d7            mov        rdi, rdx
00002962  4889c2            mov        rdx, rax
00002965  e826f9ffff        call       0x2290
0000296a  f30f1045f4        movss      xmm0, dword ptr [rbp - 0xc]
0000296f  f30f5905a5e6ffff  mulss      xmm0, dword ptr [rip - 0x195b]
00002977  f3480f2cc0        cvttss2si  rax, xmm0
0000297c  eb02              jmp        0x2980
0000297e  31c0              xor        eax, eax
00002980  64488b0c2528000000  mov        rcx, qword ptr fs:[0x28]
00002989  483b4df8          cmp        rcx, qword ptr [rbp - 8]
0000298d  7506              jne        0x2995
0000298f  4883c410          add        rsp, 0x10
00002993  5d                pop        rbp
00002994  c3                ret        
00002995  e8a6060000        call       0x3040
