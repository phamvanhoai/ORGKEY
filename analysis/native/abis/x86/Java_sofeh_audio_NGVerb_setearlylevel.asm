# APK member: lib/x86/libngverb.so
# Library SHA-256: 8412ca981b3be45bccdf4d07aff9e3554c36594cb309f6bb73e3c87705155f48
# Function: Java_sofeh_audio_NGVerb_setearlylevel; address=0x3060; bytes=30
# Decoder mode: default; decoded_bytes=30
00003060  55                push       ebp
00003061  89e5              mov        ebp, esp
00003063  83e4fc            and        esp, 0xfffffffc
00003066  8b4510            mov        eax, dword ptr [ebp + 0x10]
00003069  8b4d14            mov        ecx, dword ptr [ebp + 0x14]
0000306c  09c1              or         ecx, eax
0000306e  740a              je         0x307a
00003070  f30f104518        movss      xmm0, dword ptr [ebp + 0x18]
00003075  f30f11400c        movss      dword ptr [eax + 0xc], xmm0
0000307a  89ec              mov        esp, ebp
0000307c  5d                pop        ebp
0000307d  c3                ret        
