# APK member: lib/x86/libnmusic.so
# Library SHA-256: 051762c43873d3b1e8d0eb96eb91cd82ffbc3e9ca211006f6977da7afa2b80a2
# Function: Java_sofeh_music_NMusic_code; address=0x3b20; bytes=192
# Decoder mode: default; decoded_bytes=192
00003b20  55                push       ebp
00003b21  89e5              mov        ebp, esp
00003b23  53                push       ebx
00003b24  57                push       edi
00003b25  56                push       esi
00003b26  83e4f0            and        esp, 0xfffffff0
00003b29  83ec10            sub        esp, 0x10
00003b2c  e800000000        call       0x3b31
00003b31  5b                pop        ebx
00003b32  81c373140000      add        ebx, 0x1473
00003b38  8b4d08            mov        ecx, dword ptr [ebp + 8]
00003b3b  8b01              mov        eax, dword ptr [ecx]
00003b3d  83ec04            sub        esp, 4
00003b40  6a00              push       0
00003b42  ff7510            push       dword ptr [ebp + 0x10]
00003b45  51                push       ecx
00003b46  ff90a4020000      call       dword ptr [eax + 0x2a4]
00003b4c  83c410            add        esp, 0x10
00003b4f  89c2              mov        edx, eax
00003b51  803800            cmp        byte ptr [eax], 0
00003b54  742a              je         0x3b80
00003b56  83ec0c            sub        esp, 0xc
00003b59  31f6              xor        esi, esi
00003b5b  52                push       edx
00003b5c  89d7              mov        edi, edx
00003b5e  e85d030000        call       0x3ec0
00003b63  89fa              mov        edx, edi
00003b65  83c410            add        esp, 0x10
00003b68  31ff              xor        edi, edi
00003b6a  90                nop        
00003b6b  90                nop        
00003b6c  90                nop        
00003b6d  90                nop        
00003b6e  90                nop        
00003b6f  90                nop        
00003b70  0fbe0c32          movsx      ecx, byte ptr [edx + esi]
00003b74  46                inc        esi
00003b75  0fafce            imul       ecx, esi
00003b78  01cf              add        edi, ecx
00003b7a  39c6              cmp        esi, eax
00003b7c  72f2              jb         0x3b70
00003b7e  eb02              jmp        0x3b82
00003b80  31ff              xor        edi, edi
00003b82  8b4d08            mov        ecx, dword ptr [ebp + 8]
00003b85  8b01              mov        eax, dword ptr [ecx]
00003b87  83ec04            sub        esp, 4
00003b8a  52                push       edx
00003b8b  ff7510            push       dword ptr [ebp + 0x10]
00003b8e  51                push       ecx
00003b8f  ff90a8020000      call       dword ptr [eax + 0x2a8]
00003b95  83c410            add        esp, 0x10
00003b98  8b4514            mov        eax, dword ptr [ebp + 0x14]
00003b9b  8b8c83b4baffff    mov        ecx, dword ptr [ebx + eax*4 - 0x454c]
00003ba2  89f8              mov        eax, edi
00003ba4  c1e004            shl        eax, 4
00003ba7  99                cdq        
00003ba8  f7f9              idiv       ecx
00003baa  3d10270000        cmp        eax, 0x2710
00003baf  7c27              jl         0x3bd8
00003bb1  89fe              mov        esi, edi
00003bb3  90                nop        
00003bb4  90                nop        
00003bb5  90                nop        
00003bb6  90                nop        
00003bb7  90                nop        
00003bb8  90                nop        
00003bb9  90                nop        
00003bba  90                nop        
00003bbb  90                nop        
00003bbc  90                nop        
00003bbd  90                nop        
00003bbe  90                nop        
00003bbf  90                nop        
00003bc0  c1ee1f            shr        esi, 0x1f
00003bc3  01fe              add        esi, edi
00003bc5  d1fe              sar        esi, 1
00003bc7  89f0              mov        eax, esi
00003bc9  c1e004            shl        eax, 4
00003bcc  99                cdq        
00003bcd  f7f9              idiv       ecx
00003bcf  89f7              mov        edi, esi
00003bd1  3d0f270000        cmp        eax, 0x270f
00003bd6  7fe8              jg         0x3bc0
00003bd8  8d65f4            lea        esp, [ebp - 0xc]
00003bdb  5e                pop        esi
00003bdc  5f                pop        edi
00003bdd  5b                pop        ebx
00003bde  5d                pop        ebp
00003bdf  c3                ret        
