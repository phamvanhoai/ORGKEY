# APK member: lib/x86_64/libnmusic.so
# Library SHA-256: 792e4ca002affd7fa432bd2a8dbf94dc31b829ef32123a5954dd6e376627e35c
# Function: Java_sofeh_music_NMusic_ChannelCopy; address=0x2780; bytes=74
# Decoder mode: default; decoded_bytes=74
00002780  55                push       rbp
00002781  4889e5            mov        rbp, rsp
00002784  4156              push       r14
00002786  53                push       rbx
00002787  4889d3            mov        rbx, rdx
0000278a  4885d2            test       rdx, rdx
0000278d  0f94c0            sete       al
00002790  4885c9            test       rcx, rcx
00002793  0f94c2            sete       dl
00002796  08c2              or         dl, al
00002798  752b              jne        0x27c5
0000279a  4889df            mov        rdi, rbx
0000279d  4889ce            mov        rsi, rcx
000027a0  4989ce            mov        r14, rcx
000027a3  e8f80f0000        call       0x37a0
000027a8  4881c310b00000    add        rbx, 0xb010
000027af  4981c610b00000    add        r14, 0xb010
000027b6  4889df            mov        rdi, rbx
000027b9  4c89f6            mov        rsi, r14
000027bc  5b                pop        rbx
000027bd  415e              pop        r14
000027bf  5d                pop        rbp
000027c0  e9db0f0000        jmp        0x37a0
000027c5  5b                pop        rbx
000027c6  415e              pop        r14
000027c8  5d                pop        rbp
000027c9  c3                ret        
