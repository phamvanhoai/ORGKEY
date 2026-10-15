# APK member: lib/x86_64/libnequalizer.so
# Library SHA-256: 5bb0dc8806216597eac5b39540e6e15563919ae6c029617a4d93d22790a8596c
# Function: Java_sofeh_audio_NEqualizer_open; address=0x9f0; bytes=48
# Decoder mode: default; decoded_bytes=48
000009f0  55                push       rbp
000009f1  4889e5            mov        rbp, rsp
000009f4  4885d2            test       rdx, rdx
000009f7  7408              je         0xa01
000009f9  4889d7            mov        rdi, rdx
000009fc  e86f150000        call       0x1f70
00000a01  bf01000000        mov        edi, 1
00000a06  bea0020000        mov        esi, 0x2a0
00000a0b  e870150000        call       0x1f80
00000a10  0f280529fdffff    movaps     xmm0, xmmword ptr [rip - 0x2d7]
00000a17  0f1100            movups     xmmword ptr [rax], xmm0
00000a1a  0f114010          movups     xmmword ptr [rax + 0x10], xmm0
00000a1e  5d                pop        rbp
00000a1f  c3                ret        
