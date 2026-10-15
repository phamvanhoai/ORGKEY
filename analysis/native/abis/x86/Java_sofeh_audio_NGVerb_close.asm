# APK member: lib/x86/libngverb.so
# Library SHA-256: 8412ca981b3be45bccdf4d07aff9e3554c36594cb309f6bb73e3c87705155f48
# Function: Java_sofeh_audio_NGVerb_close; address=0x30d0; bytes=46
# Decoder mode: default; decoded_bytes=46
000030d0  55                push       ebp
000030d1  89e5              mov        ebp, esp
000030d3  53                push       ebx
000030d4  83e4f0            and        esp, 0xfffffff0
000030d7  83ec10            sub        esp, 0x10
000030da  e800000000        call       0x30df
000030df  5b                pop        ebx
000030e0  81c37d120000      add        ebx, 0x127d
000030e6  8b4510            mov        eax, dword ptr [ebp + 0x10]
000030e9  8b4d14            mov        ecx, dword ptr [ebp + 0x14]
000030ec  09c1              or         ecx, eax
000030ee  7408              je         0x30f8
000030f0  890424            mov        dword ptr [esp], eax
000030f3  e8c8000000        call       0x31c0
000030f8  8d65fc            lea        esp, [ebp - 4]
000030fb  5b                pop        ebx
000030fc  5d                pop        ebp
000030fd  c3                ret        
