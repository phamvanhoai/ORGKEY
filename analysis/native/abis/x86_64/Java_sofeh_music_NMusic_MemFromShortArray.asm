# APK member: lib/x86_64/libnmusic.so
# Library SHA-256: 792e4ca002affd7fa432bd2a8dbf94dc31b829ef32123a5954dd6e376627e35c
# Function: Java_sofeh_music_NMusic_MemFromShortArray; address=0x36c0; bytes=71
# Decoder mode: default; decoded_bytes=71
000036c0  55                push       rbp
000036c1  4889e5            mov        rbp, rsp
000036c4  4157              push       r15
000036c6  4156              push       r14
000036c8  4154              push       r12
000036ca  53                push       rbx
000036cb  4c89cb            mov        rbx, r9
000036ce  4d89c6            mov        r14, r8
000036d1  4989cf            mov        r15, rcx
000036d4  4989fc            mov        r12, rdi
000036d7  488b07            mov        rax, qword ptr [rdi]
000036da  4889d6            mov        rsi, rdx
000036dd  ff9030070000      call       qword ptr [rax + 0x730]
000036e3  498b0c24          mov        rcx, qword ptr [r12]
000036e7  4c8b8950060000    mov        r9, qword ptr [rcx + 0x650]
000036ee  4c89e7            mov        rdi, r12
000036f1  4c89fe            mov        rsi, r15
000036f4  4489f2            mov        edx, r14d
000036f7  89d9              mov        ecx, ebx
000036f9  4989c0            mov        r8, rax
000036fc  5b                pop        rbx
000036fd  415c              pop        r12
000036ff  415e              pop        r14
00003701  415f              pop        r15
00003703  5d                pop        rbp
00003704  41ffe1            jmp        r9
