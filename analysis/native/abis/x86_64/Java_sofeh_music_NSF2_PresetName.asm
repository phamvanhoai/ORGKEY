# APK member: lib/x86_64/libnsf2.so
# Library SHA-256: b145c2ce82b69909417b8510a31da8408b7f0696fd62ab62f1ed49c7c76ec3b8
# Function: Java_sofeh_music_NSF2_PresetName; address=0x8aa0; bytes=53
# Decoder mode: default; decoded_bytes=53
00008aa0  488b0db98e0000    mov        rcx, qword ptr [rip + 0x8eb9]
00008aa7  4885c9            test       rcx, rcx
00008aaa  7426              je         0x8ad2
00008aac  488b07            mov        rax, qword ptr [rdi]
00008aaf  488b8038050000    mov        rax, qword ptr [rax + 0x538]
00008ab6  85d2              test       edx, edx
00008ab8  7814              js         0x8ace
00008aba  395120            cmp        dword ptr [rcx + 0x20], edx
00008abd  7e0f              jle        0x8ace
00008abf  89d2              mov        edx, edx
00008ac1  488d3492          lea        rsi, [rdx + rdx*4]
00008ac5  48c1e603          shl        rsi, 3
00008ac9  480331            add        rsi, qword ptr [rcx]
00008acc  ffe0              jmp        rax
00008ace  31f6              xor        esi, esi
00008ad0  ffe0              jmp        rax
00008ad2  31c0              xor        eax, eax
00008ad4  c3                ret        
