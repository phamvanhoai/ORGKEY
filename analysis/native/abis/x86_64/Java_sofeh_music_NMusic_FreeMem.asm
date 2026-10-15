# APK member: lib/x86_64/libnmusic.so
# Library SHA-256: 792e4ca002affd7fa432bd2a8dbf94dc31b829ef32123a5954dd6e376627e35c
# Function: Java_sofeh_music_NMusic_FreeMem; address=0x35b0; bytes=25
# Decoder mode: default; decoded_bytes=25
000035b0  55                push       rbp
000035b1  4889e5            mov        rbp, rsp
000035b4  488b07            mov        rax, qword ptr [rdi]
000035b7  4889d6            mov        rsi, rdx
000035ba  ff9030070000      call       qword ptr [rax + 0x730]
000035c0  4889c7            mov        rdi, rax
000035c3  5d                pop        rbp
000035c4  e9b7010000        jmp        0x3780
