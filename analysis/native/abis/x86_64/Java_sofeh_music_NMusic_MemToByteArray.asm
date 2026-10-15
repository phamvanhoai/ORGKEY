# APK member: lib/x86_64/libnmusic.so
# Library SHA-256: 792e4ca002affd7fa432bd2a8dbf94dc31b829ef32123a5954dd6e376627e35c
# Function: Java_sofeh_music_NMusic_MemToByteArray; address=0x35d0; bytes=71
# Decoder mode: default; decoded_bytes=71
000035d0  55                push       rbp
000035d1  4889e5            mov        rbp, rsp
000035d4  4157              push       r15
000035d6  4156              push       r14
000035d8  4154              push       r12
000035da  53                push       rbx
000035db  4c89cb            mov        rbx, r9
000035de  4d89c6            mov        r14, r8
000035e1  4989cf            mov        r15, rcx
000035e4  4989fc            mov        r12, rdi
000035e7  488b07            mov        rax, qword ptr [rdi]
000035ea  4889d6            mov        rsi, rdx
000035ed  ff9030070000      call       qword ptr [rax + 0x730]
000035f3  498b0c24          mov        rcx, qword ptr [r12]
000035f7  4c8b8980060000    mov        r9, qword ptr [rcx + 0x680]
000035fe  4c89e7            mov        rdi, r12
00003601  4c89fe            mov        rsi, r15
00003604  4489f2            mov        edx, r14d
00003607  89d9              mov        ecx, ebx
00003609  4989c0            mov        r8, rax
0000360c  5b                pop        rbx
0000360d  415c              pop        r12
0000360f  415e              pop        r14
00003611  415f              pop        r15
00003613  5d                pop        rbp
00003614  41ffe1            jmp        r9
