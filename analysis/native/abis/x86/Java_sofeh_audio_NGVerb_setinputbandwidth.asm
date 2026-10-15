# APK member: lib/x86/libngverb.so
# Library SHA-256: 8412ca981b3be45bccdf4d07aff9e3554c36594cb309f6bb73e3c87705155f48
# Function: Java_sofeh_audio_NGVerb_setinputbandwidth; address=0x3020; bytes=61
# Decoder mode: default; decoded_bytes=61
00003020  55                push       ebp
00003021  89e5              mov        ebp, esp
00003023  83e4fc            and        esp, 0xfffffffc
00003026  e800000000        call       0x302b
0000302b  58                pop        eax
0000302c  81c031130000      add        eax, 0x1331
00003032  8b4d10            mov        ecx, dword ptr [ebp + 0x10]
00003035  8b5514            mov        edx, dword ptr [ebp + 0x14]
00003038  09ca              or         edx, ecx
0000303a  741d              je         0x3059
0000303c  f30f104518        movss      xmm0, dword ptr [ebp + 0x18]
00003041  f30f114104        movss      dword ptr [ecx + 4], xmm0
00003046  8b4910            mov        ecx, dword ptr [ecx + 0x10]
00003049  f30f108854c8ffff  movss      xmm1, dword ptr [eax - 0x37ac]
00003051  f30f5cc8          subss      xmm1, xmm0
00003055  f30f1109          movss      dword ptr [ecx], xmm1
00003059  89ec              mov        esp, ebp
0000305b  5d                pop        ebp
0000305c  c3                ret        
