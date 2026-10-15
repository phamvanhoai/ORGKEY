# APK member: lib/x86/libngverb.so
# Library SHA-256: 8412ca981b3be45bccdf4d07aff9e3554c36594cb309f6bb73e3c87705155f48
# Function: Java_sofeh_audio_NGVerb_processStereoRight; address=0x2ac0; bytes=33
# Decoder mode: default; decoded_bytes=33
00002ac0  55                push       ebp
00002ac1  89e5              mov        ebp, esp
00002ac3  83e4fc            and        esp, 0xfffffffc
00002ac6  e800000000        call       0x2acb
00002acb  58                pop        eax
00002acc  81c091180000      add        eax, 0x1891
00002ad2  8b88fcffffff      mov        ecx, dword ptr [eax - 4]
00002ad8  8b01              mov        eax, dword ptr [ecx]
00002ada  8b5104            mov        edx, dword ptr [ecx + 4]
00002add  89ec              mov        esp, ebp
00002adf  5d                pop        ebp
00002ae0  c3                ret        
