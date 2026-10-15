# APK member: lib/x86_64/libnopensl.so
# Library SHA-256: 1cfbadb3f31bae35f1aa6bc7a994af95d242e75b680f26a661a4b66c2a61f23f
# Function: Java_sofeh_audio_NOpenSL_RecordStop; address=0x2ca0; bytes=33
# Decoder mode: default; decoded_bytes=33
00002ca0  c605fd03110000    mov        byte ptr [rip + 0x1103fd], 0
00002ca7  488b3d0a041100    mov        rdi, qword ptr [rip + 0x11040a]
00002cae  4885ff            test       rdi, rdi
00002cb1  740d              je         0x2cc0
00002cb3  488b07            mov        rax, qword ptr [rdi]
00002cb6  488b00            mov        rax, qword ptr [rax]
00002cb9  be01000000        mov        esi, 1
00002cbe  ffe0              jmp        rax
00002cc0  c3                ret        
