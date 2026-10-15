# APK member: lib/x86/libngverb.so
# Library SHA-256: 8412ca981b3be45bccdf4d07aff9e3554c36594cb309f6bb73e3c87705155f48
# Function: Java_sofeh_audio_NGVerb_setdamping; address=0x2fe0; bytes=60
# Decoder mode: default; decoded_bytes=60
00002fe0  55                push       ebp
00002fe1  89e5              mov        ebp, esp
00002fe3  83e4fc            and        esp, 0xfffffffc
00002fe6  8b4510            mov        eax, dword ptr [ebp + 0x10]
00002fe9  8b4d14            mov        ecx, dword ptr [ebp + 0x14]
00002fec  09c1              or         ecx, eax
00002fee  7428              je         0x3018
00002ff0  f30f104518        movss      xmm0, dword ptr [ebp + 0x18]
00002ff5  f30f114038        movss      dword ptr [eax + 0x38], xmm0
00002ffa  8b4034            mov        eax, dword ptr [eax + 0x34]
00002ffd  8b08              mov        ecx, dword ptr [eax]
00002fff  f30f1101          movss      dword ptr [ecx], xmm0
00003003  8b4804            mov        ecx, dword ptr [eax + 4]
00003006  f30f1101          movss      dword ptr [ecx], xmm0
0000300a  8b4808            mov        ecx, dword ptr [eax + 8]
0000300d  f30f1101          movss      dword ptr [ecx], xmm0
00003011  8b400c            mov        eax, dword ptr [eax + 0xc]
00003014  f30f1100          movss      dword ptr [eax], xmm0
00003018  89ec              mov        esp, ebp
0000301a  5d                pop        ebp
0000301b  c3                ret        
