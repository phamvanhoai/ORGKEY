# APK member: lib/x86_64/libnmusic.so
# Library SHA-256: 792e4ca002affd7fa432bd2a8dbf94dc31b829ef32123a5954dd6e376627e35c
# Function: Java_sofeh_music_NMusic_ChannelReset; address=0x2750; bytes=44
# Decoder mode: default; decoded_bytes=44
00002750  4885d2            test       rdx, rdx
00002753  7426              je         0x277b
00002755  55                push       rbp
00002756  4889e5            mov        rbp, rsp
00002759  53                push       rbx
0000275a  50                push       rax
0000275b  4889d3            mov        rbx, rdx
0000275e  4889d7            mov        rdi, rdx
00002761  e82a100000        call       0x3790
00002766  4881c310b00000    add        rbx, 0xb010
0000276d  4889df            mov        rdi, rbx
00002770  4883c408          add        rsp, 8
00002774  5b                pop        rbx
00002775  5d                pop        rbp
00002776  e915100000        jmp        0x3790
0000277b  c3                ret        
