# APK member: lib/x86_64/libnequalizer.so
# Library SHA-256: 5bb0dc8806216597eac5b39540e6e15563919ae6c029617a4d93d22790a8596c
# Function: Java_sofeh_audio_NEqualizer_band; address=0xa20; bytes=13
# Decoder mode: default; decoded_bytes=13
00000a20  4885d2            test       rdx, rdx
00000a23  7407              je         0xa2c
00000a25  4863c1            movsxd     rax, ecx
00000a28  44890482          mov        dword ptr [rdx + rax*4], r8d
00000a2c  c3                ret        
