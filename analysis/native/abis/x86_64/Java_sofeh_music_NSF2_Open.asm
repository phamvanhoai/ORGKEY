# APK member: lib/x86_64/libnsf2.so
# Library SHA-256: b145c2ce82b69909417b8510a31da8408b7f0696fd62ab62f1ed49c7c76ec3b8
# Function: Java_sofeh_music_NSF2_Open; address=0x89c0; bytes=145
# Decoder mode: default; decoded_bytes=145
000089c0  55                push       rbp
000089c1  4889e5            mov        rbp, rsp
000089c4  4156              push       r14
000089c6  53                push       rbx
000089c7  4883ec20          sub        rsp, 0x20
000089cb  64488b042528000000  mov        rax, qword ptr fs:[0x28]
000089d4  488945e8          mov        qword ptr [rbp - 0x18], rax
000089d8  488b07            mov        rax, qword ptr [rdi]
000089db  31db              xor        ebx, ebx
000089dd  4889d6            mov        rsi, rdx
000089e0  31d2              xor        edx, edx
000089e2  ff9048050000      call       qword ptr [rax + 0x548]
000089e8  488b0d994c0000    mov        rcx, qword ptr [rip + 0x4c99]
000089ef  48894de0          mov        qword ptr [rbp - 0x20], rcx
000089f3  0f10057e4c0000    movups     xmm0, xmmword ptr [rip + 0x4c7e]
000089fa  0f2945d0          movaps     xmmword ptr [rbp - 0x30], xmm0
000089fe  488d35d38fffff    lea        rsi, [rip - 0x702d]
00008a05  4889c7            mov        rdi, rax
00008a08  e8a30a0000        call       0x94b0
00008a0d  4885c0            test       rax, rax
00008a10  741b              je         0x8a2d
00008a12  488945d0          mov        qword ptr [rbp - 0x30], rax
00008a16  488d7dd0          lea        rdi, [rbp - 0x30]
00008a1a  4989c6            mov        r14, rax
00008a1d  e89e0a0000        call       0x94c0
00008a22  4889c3            mov        rbx, rax
00008a25  4c89f7            mov        rdi, r14
00008a28  e8a30a0000        call       0x94d0
00008a2d  48891d2c8f0000    mov        qword ptr [rip + 0x8f2c], rbx
00008a34  64488b042528000000  mov        rax, qword ptr fs:[0x28]
00008a3d  483b45e8          cmp        rax, qword ptr [rbp - 0x18]
00008a41  7509              jne        0x8a4c
00008a43  4883c420          add        rsp, 0x20
00008a47  5b                pop        rbx
00008a48  415e              pop        r14
00008a4a  5d                pop        rbp
00008a4b  c3                ret        
00008a4c  e88f0a0000        call       0x94e0
