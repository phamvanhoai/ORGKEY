# APK member: lib/x86_64/libnmusic.so
# Library SHA-256: 792e4ca002affd7fa432bd2a8dbf94dc31b829ef32123a5954dd6e376627e35c
# Function: Java_sofeh_music_NMusic_GetMem; address=0x3570; bytes=58
# Decoder mode: default; decoded_bytes=58
00003570  55                push       rbp
00003571  4889e5            mov        rbp, rsp
00003574  4157              push       r15
00003576  4156              push       r14
00003578  53                push       rbx
00003579  50                push       rax
0000357a  4889d3            mov        rbx, rdx
0000357d  4989fe            mov        r14, rdi
00003580  488b07            mov        rax, qword ptr [rdi]
00003583  4c8bb828070000    mov        r15, qword ptr [rax + 0x728]
0000358a  4889d7            mov        rdi, rdx
0000358d  e8ce010000        call       0x3760
00003592  4c89f7            mov        rdi, r14
00003595  4889c6            mov        rsi, rax
00003598  4889da            mov        rdx, rbx
0000359b  4c89f8            mov        rax, r15
0000359e  4883c408          add        rsp, 8
000035a2  5b                pop        rbx
000035a3  415e              pop        r14
000035a5  415f              pop        r15
000035a7  5d                pop        rbp
000035a8  ffe0              jmp        rax
