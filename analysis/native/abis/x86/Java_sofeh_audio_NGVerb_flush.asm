# APK member: lib/x86/libngverb.so
# Library SHA-256: 8412ca981b3be45bccdf4d07aff9e3554c36594cb309f6bb73e3c87705155f48
# Function: Java_sofeh_audio_NGVerb_flush; address=0x30a0; bytes=46
# Decoder mode: default; decoded_bytes=46
000030a0  55                push       ebp
000030a1  89e5              mov        ebp, esp
000030a3  53                push       ebx
000030a4  83e4f0            and        esp, 0xfffffff0
000030a7  83ec10            sub        esp, 0x10
000030aa  e800000000        call       0x30af
000030af  5b                pop        ebx
000030b0  81c3ad120000      add        ebx, 0x12ad
000030b6  8b4510            mov        eax, dword ptr [ebp + 0x10]
000030b9  8b4d14            mov        ecx, dword ptr [ebp + 0x14]
000030bc  09c1              or         ecx, eax
000030be  7408              je         0x30c8
000030c0  890424            mov        dword ptr [esp], eax
000030c3  e848010000        call       0x3210
000030c8  8d65fc            lea        esp, [ebp - 4]
000030cb  5b                pop        ebx
000030cc  5d                pop        ebp
000030cd  c3                ret        
