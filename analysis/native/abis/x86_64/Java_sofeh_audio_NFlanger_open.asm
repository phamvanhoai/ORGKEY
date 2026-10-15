# APK member: lib/x86_64/libnflanger.so
# Library SHA-256: d2a7cec8840993d6dc944a3f0999a7fe2667decd0004b0e78fc4f98b33ea053e
# Function: Java_sofeh_audio_NFlanger_open; address=0x960; bytes=51
# Decoder mode: default; decoded_bytes=51
00000960  55                push       rbp
00000961  4889e5            mov        rbp, rsp
00000964  4885d2            test       rdx, rdx
00000967  7408              je         0x971
00000969  4889d7            mov        rdi, rdx
0000096c  e8cf060000        call       0x1040
00000971  bf01000000        mov        edi, 1
00000976  be34080000        mov        esi, 0x834
0000097b  e8d0060000        call       0x1050
00000980  0f280559fdffff    movaps     xmm0, xmmword ptr [rip - 0x2a7]
00000987  0f1100            movups     xmmword ptr [rax], xmm0
0000098a  c7401080000000    mov        dword ptr [rax + 0x10], 0x80
00000991  5d                pop        rbp
00000992  c3                ret        
