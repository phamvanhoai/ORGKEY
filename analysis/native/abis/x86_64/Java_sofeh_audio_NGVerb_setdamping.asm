# APK member: lib/x86_64/libngverb.so
# Library SHA-256: 8fd68c89616a094decd0ad7f229e0d8e89656b29446477be056c19051fc5f443
# Function: Java_sofeh_audio_NGVerb_setdamping; address=0x2e50; bytes=46
# Decoder mode: default; decoded_bytes=46
00002e50  4885d2            test       rdx, rdx
00002e53  7428              je         0x2e7d
00002e55  f30f114250        movss      dword ptr [rdx + 0x50], xmm0
00002e5a  488b4248          mov        rax, qword ptr [rdx + 0x48]
00002e5e  488b08            mov        rcx, qword ptr [rax]
00002e61  f30f1101          movss      dword ptr [rcx], xmm0
00002e65  488b4808          mov        rcx, qword ptr [rax + 8]
00002e69  f30f1101          movss      dword ptr [rcx], xmm0
00002e6d  488b4810          mov        rcx, qword ptr [rax + 0x10]
00002e71  f30f1101          movss      dword ptr [rcx], xmm0
00002e75  488b4018          mov        rax, qword ptr [rax + 0x18]
00002e79  f30f1100          movss      dword ptr [rax], xmm0
00002e7d  c3                ret        
