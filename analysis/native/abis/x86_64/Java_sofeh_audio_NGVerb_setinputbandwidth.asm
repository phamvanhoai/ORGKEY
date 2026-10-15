# APK member: lib/x86_64/libngverb.so
# Library SHA-256: 8fd68c89616a094decd0ad7f229e0d8e89656b29446477be056c19051fc5f443
# Function: Java_sofeh_audio_NGVerb_setinputbandwidth; address=0x2e80; bytes=31
# Decoder mode: default; decoded_bytes=31
00002e80  4885d2            test       rdx, rdx
00002e83  7419              je         0x2e9e
00002e85  f30f114204        movss      dword ptr [rdx + 4], xmm0
00002e8a  488b4210          mov        rax, qword ptr [rdx + 0x10]
00002e8e  f30f100d4ae1ffff  movss      xmm1, dword ptr [rip - 0x1eb6]
00002e96  f30f5cc8          subss      xmm1, xmm0
00002e9a  f30f1108          movss      dword ptr [rax], xmm1
00002e9e  c3                ret        
