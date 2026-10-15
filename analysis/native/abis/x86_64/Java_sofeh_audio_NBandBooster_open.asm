# APK member: lib/x86_64/libnbandbooster.so
# Library SHA-256: d26716a4c20ffc01c78a57e125ea92d8c7d3caf71b1c31026ce42124b3dc20f2
# Function: Java_sofeh_audio_NBandBooster_open; address=0x840; bytes=33
# Decoder mode: default; decoded_bytes=33
00000840  4885d2            test       rdx, rdx
00000843  740d              je         0x852
00000845  55                push       rbp
00000846  4889e5            mov        rbp, rsp
00000849  4889d7            mov        rdi, rdx
0000084c  e85f020000        call       0xab0
00000851  5d                pop        rbp
00000852  bf01000000        mov        edi, 1
00000857  be58000000        mov        esi, 0x58
0000085c  e95f020000        jmp        0xac0
