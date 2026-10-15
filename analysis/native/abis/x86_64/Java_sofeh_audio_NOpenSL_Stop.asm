# APK member: lib/x86_64/libnopensl.so
# Library SHA-256: 1cfbadb3f31bae35f1aa6bc7a994af95d242e75b680f26a661a4b66c2a61f23f
# Function: Java_sofeh_audio_NOpenSL_Stop; address=0x2a50; bytes=136
# Decoder mode: default; decoded_bytes=136
00002a50  55                push       rbp
00002a51  4889e5            mov        rbp, rsp
00002a54  488d3d05061100    lea        rdi, [rip + 0x110605]
00002a5b  e800030000        call       0x2d60
00002a60  c705d605010003000000  mov        dword ptr [rip + 0x105d6], 3
00002a6a  c6053306110000    mov        byte ptr [rip + 0x110633], 0
00002a71  488b3d30061100    mov        rdi, qword ptr [rip + 0x110630]
00002a78  4885ff            test       rdi, rdi
00002a7b  740a              je         0x2a87
00002a7d  488b07            mov        rax, qword ptr [rdi]
00002a80  be01000000        mov        esi, 1
00002a85  ff10              call       qword ptr [rax]
00002a87  488b3d22061100    mov        rdi, qword ptr [rip + 0x110622]
00002a8e  4885ff            test       rdi, rdi
00002a91  7406              je         0x2a99
00002a93  488b07            mov        rax, qword ptr [rdi]
00002a96  ff5008            call       qword ptr [rax + 8]
00002a99  488b3d18061100    mov        rdi, qword ptr [rip + 0x110618]
00002aa0  4885ff            test       rdi, rdi
00002aa3  740a              je         0x2aaf
00002aa5  488b07            mov        rax, qword ptr [rdi]
00002aa8  be01000000        mov        esi, 1
00002aad  ff10              call       qword ptr [rax]
00002aaf  488b3d9a050900    mov        rdi, qword ptr [rip + 0x9059a]
00002ab6  4885ff            test       rdi, rdi
00002ab9  7406              je         0x2ac1
00002abb  488b07            mov        rax, qword ptr [rdi]
00002abe  ff5008            call       qword ptr [rax + 8]
00002ac1  c7057505010000000000  mov        dword ptr [rip + 0x10575], 0
00002acb  488d3d8e051100    lea        rdi, [rip + 0x11058e]
00002ad2  5d                pop        rbp
00002ad3  e998020000        jmp        0x2d70
