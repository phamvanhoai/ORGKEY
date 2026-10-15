# APK member: lib/x86/libnsf2.so
# Library SHA-256: 452e13279ac07ceb7c0366072a52f65dedf77b845331b27d58ba4be9865cb7a5
# Function: Java_sofeh_music_NSF2_PresetCount; address=0x94c0; bytes=39
# Decoder mode: default; decoded_bytes=39
000094c0  55                push       ebp
000094c1  89e5              mov        ebp, esp
000094c3  83e4fc            and        esp, 0xfffffffc
000094c6  e800000000        call       0x94cb
000094cb  58                pop        eax
000094cc  81c0f11d0000      add        eax, 0x1df1
000094d2  8b8088100000      mov        eax, dword ptr [eax + 0x1088]
000094d8  85c0              test       eax, eax
000094da  7405              je         0x94e1
000094dc  8b4010            mov        eax, dword ptr [eax + 0x10]
000094df  eb02              jmp        0x94e3
000094e1  31c0              xor        eax, eax
000094e3  89ec              mov        esp, ebp
000094e5  5d                pop        ebp
000094e6  c3                ret        
