# APK member: lib/x86_64/libnmusic.so
# Library SHA-256: 792e4ca002affd7fa432bd2a8dbf94dc31b829ef32123a5954dd6e376627e35c
# Function: Java_sofeh_music_NMusic_ChannelOpen; address=0x2700; bytes=49
# Decoder mode: default; decoded_bytes=49
00002700  55                push       rbp
00002701  4889e5            mov        rbp, rsp
00002704  53                push       rbx
00002705  50                push       rax
00002706  bf20600100        mov        edi, 0x16020
0000270b  e850100000        call       0x3760
00002710  4889c3            mov        rbx, rax
00002713  4889c7            mov        rdi, rax
00002716  e875100000        call       0x3790
0000271b  488dbb10b00000    lea        rdi, [rbx + 0xb010]
00002722  e869100000        call       0x3790
00002727  4889d8            mov        rax, rbx
0000272a  4883c408          add        rsp, 8
0000272e  5b                pop        rbx
0000272f  5d                pop        rbp
00002730  c3                ret        
