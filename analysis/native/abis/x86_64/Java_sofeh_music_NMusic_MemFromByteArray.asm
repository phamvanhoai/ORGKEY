# APK member: lib/x86_64/libnmusic.so
# Library SHA-256: 792e4ca002affd7fa432bd2a8dbf94dc31b829ef32123a5954dd6e376627e35c
# Function: Java_sofeh_music_NMusic_MemFromByteArray; address=0x3670; bytes=71
# Decoder mode: default; decoded_bytes=71
00003670  55                push       rbp
00003671  4889e5            mov        rbp, rsp
00003674  4157              push       r15
00003676  4156              push       r14
00003678  4154              push       r12
0000367a  53                push       rbx
0000367b  4c89cb            mov        rbx, r9
0000367e  4d89c6            mov        r14, r8
00003681  4989cf            mov        r15, rcx
00003684  4989fc            mov        r12, rdi
00003687  488b07            mov        rax, qword ptr [rdi]
0000368a  4889d6            mov        rsi, rdx
0000368d  ff9030070000      call       qword ptr [rax + 0x730]
00003693  498b0c24          mov        rcx, qword ptr [r12]
00003697  4c8b8940060000    mov        r9, qword ptr [rcx + 0x640]
0000369e  4c89e7            mov        rdi, r12
000036a1  4c89fe            mov        rsi, r15
000036a4  4489f2            mov        edx, r14d
000036a7  89d9              mov        ecx, ebx
000036a9  4989c0            mov        r8, rax
000036ac  5b                pop        rbx
000036ad  415c              pop        r12
000036af  415e              pop        r14
000036b1  415f              pop        r15
000036b3  5d                pop        rbp
000036b4  41ffe1            jmp        r9
