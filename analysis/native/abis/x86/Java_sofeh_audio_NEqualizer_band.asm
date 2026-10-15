# APK member: lib/x86/libnequalizer.so
# Library SHA-256: 0c7dfba2ccd6a4c88a74500c5b3ecac4f88ff3e4abb681dafc117af7ee94b44b
# Function: Java_sofeh_audio_NEqualizer_band; address=0x980; bytes=29
# Decoder mode: default; decoded_bytes=29
00000980  55                push       ebp
00000981  89e5              mov        ebp, esp
00000983  83e4fc            and        esp, 0xfffffffc
00000986  8b4510            mov        eax, dword ptr [ebp + 0x10]
00000989  8b4d14            mov        ecx, dword ptr [ebp + 0x14]
0000098c  09c1              or         ecx, eax
0000098e  7409              je         0x999
00000990  8b4d1c            mov        ecx, dword ptr [ebp + 0x1c]
00000993  8b5518            mov        edx, dword ptr [ebp + 0x18]
00000996  890c90            mov        dword ptr [eax + edx*4], ecx
00000999  89ec              mov        esp, ebp
0000099b  5d                pop        ebp
0000099c  c3                ret        
