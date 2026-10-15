# APK member: lib/x86/libnopensl.so
# Library SHA-256: 81e6220973b454100123e9259f13717edc34b683f704a7e2f342ff6424ed7706
# Function: Java_sofeh_audio_NOpenSL_Put; address=0xc00; bytes=139
# Decoder mode: default; decoded_bytes=139
00000c00  55                push       ebp
00000c01  89e5              mov        ebp, esp
00000c03  53                push       ebx
00000c04  57                push       edi
00000c05  56                push       esi
00000c06  83e4f0            and        esp, 0xfffffff0
00000c09  83ec10            sub        esp, 0x10
00000c0c  e800000000        call       0xc11
00000c11  5b                pop        ebx
00000c12  81c38b310000      add        ebx, 0x318b
00000c18  8b7510            mov        esi, dword ptr [ebp + 0x10]
00000c1b  85f6              test       esi, esi
00000c1d  7464              je         0xc83
00000c1f  8b7d14            mov        edi, dword ptr [ebp + 0x14]
00000c22  8b4d08            mov        ecx, dword ptr [ebp + 8]
00000c25  8b01              mov        eax, dword ptr [ecx]
00000c27  83ec08            sub        esp, 8
00000c2a  56                push       esi
00000c2b  51                push       ecx
00000c2c  ff90ac020000      call       dword ptr [eax + 0x2ac]
00000c32  83c410            add        esp, 0x10
00000c35  39f8              cmp        eax, edi
00000c37  0f4cf8            cmovl      edi, eax
00000c3a  85ff              test       edi, edi
00000c3c  7e45              jle        0xc83
00000c3e  81ff00400000      cmp        edi, 0x4000
00000c44  89f2              mov        edx, esi
00000c46  be00400000        mov        esi, 0x4000
00000c4b  0f42f7            cmovb      esi, edi
00000c4e  8b7d08            mov        edi, dword ptr [ebp + 8]
00000c51  8b07              mov        eax, dword ptr [edi]
00000c53  83ec0c            sub        esp, 0xc
00000c56  8d8b40100000      lea        ecx, [ebx + 0x1040]
00000c5c  51                push       ecx
00000c5d  56                push       esi
00000c5e  6a00              push       0
00000c60  52                push       edx
00000c61  57                push       edi
00000c62  ff9028030000      call       dword ptr [eax + 0x328]
00000c68  83c420            add        esp, 0x20
00000c6b  89b340900000      mov        dword ptr [ebx + 0x9040], esi
00000c71  39b344900000      cmp        dword ptr [ebx + 0x9044], esi
00000c77  7c0a              jl         0xc83
00000c79  c7834490000000000000  mov        dword ptr [ebx + 0x9044], 0
00000c83  8d65f4            lea        esp, [ebp - 0xc]
00000c86  5e                pop        esi
00000c87  5f                pop        edi
00000c88  5b                pop        ebx
00000c89  5d                pop        ebp
00000c8a  c3                ret        
