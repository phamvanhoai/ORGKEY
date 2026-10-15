# APK member: lib/x86/libngverb.so
# Library SHA-256: 8412ca981b3be45bccdf4d07aff9e3554c36594cb309f6bb73e3c87705155f48
# Function: Java_sofeh_audio_NGVerb_settaillevel; address=0x3080; bytes=30
# Decoder mode: default; decoded_bytes=30
00003080  55                push       ebp
00003081  89e5              mov        ebp, esp
00003083  83e4fc            and        esp, 0xfffffffc
00003086  8b4510            mov        eax, dword ptr [ebp + 0x10]
00003089  8b4d14            mov        ecx, dword ptr [ebp + 0x14]
0000308c  09c1              or         ecx, eax
0000308e  740a              je         0x309a
00003090  f30f104518        movss      xmm0, dword ptr [ebp + 0x18]
00003095  f30f114008        movss      dword ptr [eax + 8], xmm0
0000309a  89ec              mov        esp, ebp
0000309c  5d                pop        ebp
0000309d  c3                ret        
