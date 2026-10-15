# APK member: lib/x86_64/libnmusic.so
# Library SHA-256: 792e4ca002affd7fa432bd2a8dbf94dc31b829ef32123a5954dd6e376627e35c
# Function: Java_sofeh_music_NMusic_MemToShortArray; address=0x3620; bytes=71
# Decoder mode: default; decoded_bytes=71
00003620  55                push       rbp
00003621  4889e5            mov        rbp, rsp
00003624  4157              push       r15
00003626  4156              push       r14
00003628  4154              push       r12
0000362a  53                push       rbx
0000362b  4c89cb            mov        rbx, r9
0000362e  4d89c6            mov        r14, r8
00003631  4989cf            mov        r15, rcx
00003634  4989fc            mov        r12, rdi
00003637  488b07            mov        rax, qword ptr [rdi]
0000363a  4889d6            mov        rsi, rdx
0000363d  ff9030070000      call       qword ptr [rax + 0x730]
00003643  498b0c24          mov        rcx, qword ptr [r12]
00003647  4c8b8990060000    mov        r9, qword ptr [rcx + 0x690]
0000364e  4c89e7            mov        rdi, r12
00003651  4c89fe            mov        rsi, r15
00003654  4489f2            mov        edx, r14d
00003657  89d9              mov        ecx, ebx
00003659  4989c0            mov        r8, rax
0000365c  5b                pop        rbx
0000365d  415c              pop        r12
0000365f  415e              pop        r14
00003661  415f              pop        r15
00003663  5d                pop        rbp
00003664  41ffe1            jmp        r9
