# APK member: lib/x86/libnopensl.so
# Library SHA-256: 81e6220973b454100123e9259f13717edc34b683f704a7e2f342ff6424ed7706
# Function: Java_sofeh_audio_NOpenSL_Write; address=0xc90; bytes=691
# Decoder mode: default; decoded_bytes=691
00000c90  55                push       ebp
00000c91  89e5              mov        ebp, esp
00000c93  53                push       ebx
00000c94  57                push       edi
00000c95  56                push       esi
00000c96  83e4f0            and        esp, 0xfffffff0
00000c99  81ec20800000      sub        esp, 0x8020
00000c9f  e800000000        call       0xca4
00000ca4  5b                pop        ebx
00000ca5  81c3f8300000      add        ebx, 0x30f8
00000cab  8b7510            mov        esi, dword ptr [ebp + 0x10]
00000cae  65a114000000      mov        eax, dword ptr gs:[0x14]
00000cb4  89842418800000    mov        dword ptr [esp + 0x8018], eax
00000cbb  85f6              test       esi, esi
00000cbd  0f8464020000      je         0xf27
00000cc3  83bb4890000000    cmp        dword ptr [ebx + 0x9048], 0
00000cca  0f8457020000      je         0xf27
00000cd0  83bb4c90000000    cmp        dword ptr [ebx + 0x904c], 0
00000cd7  0f8e4a020000      jle        0xf27
00000cdd  8b7d14            mov        edi, dword ptr [ebp + 0x14]
00000ce0  8b4d08            mov        ecx, dword ptr [ebp + 8]
00000ce3  8b01              mov        eax, dword ptr [ecx]
00000ce5  83ec08            sub        esp, 8
00000ce8  56                push       esi
00000ce9  51                push       ecx
00000cea  ff90ac020000      call       dword ptr [eax + 0x2ac]
00000cf0  83c410            add        esp, 0x10
00000cf3  39f8              cmp        eax, edi
00000cf5  0f4cf8            cmovl      edi, eax
00000cf8  81ff00400000      cmp        edi, 0x4000
00000cfe  b800400000        mov        eax, 0x4000
00000d03  0f4cc7            cmovl      eax, edi
00000d06  85ff              test       edi, edi
00000d08  0f8e19020000      jle        0xf27
00000d0e  89c2              mov        edx, eax
00000d10  8b7d08            mov        edi, dword ptr [ebp + 8]
00000d13  8b07              mov        eax, dword ptr [edi]
00000d15  83ec0c            sub        esp, 0xc
00000d18  8d4c2424          lea        ecx, [esp + 0x24]
00000d1c  51                push       ecx
00000d1d  89542420          mov        dword ptr [esp + 0x20], edx
00000d21  52                push       edx
00000d22  6a00              push       0
00000d24  56                push       esi
00000d25  57                push       edi
00000d26  ff9028030000      call       dword ptr [eax + 0x328]
00000d2c  83c420            add        esp, 0x20
00000d2f  bf10000000        mov        edi, 0x10
00000d34  90                nop        
00000d35  90                nop        
00000d36  90                nop        
00000d37  90                nop        
00000d38  90                nop        
00000d39  90                nop        
00000d3a  90                nop        
00000d3b  90                nop        
00000d3c  90                nop        
00000d3d  90                nop        
00000d3e  90                nop        
00000d3f  90                nop        
00000d40  8b8350900000      mov        eax, dword ptr [ebx + 0x9050]
00000d46  3b8354900000      cmp        eax, dword ptr [ebx + 0x9054]
00000d4c  7512              jne        0xd60
00000d4e  8bb348900000      mov        esi, dword ptr [ebx + 0x9048]
00000d54  85f6              test       esi, esi
00000d56  752b              jne        0xd83
00000d58  e9a4010000        jmp        0xf01
00000d5d  90                nop        
00000d5e  90                nop        
00000d5f  90                nop        
00000d60  8b8350900000      mov        eax, dword ptr [ebx + 0x9050]
00000d66  80bb5890000000    cmp        byte ptr [ebx + 0x9058], 0
00000d6d  b904000000        mov        ecx, 4
00000d72  0f45cf            cmovne     ecx, edi
00000d75  8bb348900000      mov        esi, dword ptr [ebx + 0x9048]
00000d7b  39c8              cmp        eax, ecx
00000d7d  7c30              jl         0xdaf
00000d7f  85f6              test       esi, esi
00000d81  742c              je         0xdaf
00000d83  8b835c900000      mov        eax, dword ptr [ebx + 0x905c]
00000d89  83f802            cmp        eax, 2
00000d8c  750f              jne        0xd9d
00000d8e  83ec0c            sub        esp, 0xc
00000d91  6a0a              push       0xa
00000d93  e8781e0000        call       0x2c10
00000d98  83c410            add        esp, 0x10
00000d9b  eba3              jmp        0xd40
00000d9d  8b8b54900000      mov        ecx, dword ptr [ebx + 0x9054]
00000da3  8b8360900000      mov        eax, dword ptr [ebx + 0x9060]
00000da9  89442408          mov        dword ptr [esp + 8], eax
00000dad  eb18              jmp        0xdc7
00000daf  8b8b54900000      mov        ecx, dword ptr [ebx + 0x9054]
00000db5  8b8360900000      mov        eax, dword ptr [ebx + 0x9060]
00000dbb  89442408          mov        dword ptr [esp + 8], eax
00000dbf  85f6              test       esi, esi
00000dc1  0f8450010000      je         0xf17
00000dc7  31d2              xor        edx, edx
00000dc9  90                nop        
00000dca  90                nop        
00000dcb  90                nop        
00000dcc  90                nop        
00000dcd  90                nop        
00000dce  90                nop        
00000dcf  90                nop        
00000dd0  8b835c900000      mov        eax, dword ptr [ebx + 0x905c]
00000dd6  83f802            cmp        eax, 2
00000dd9  0f8538010000      jne        0xf17
00000ddf  894c240c          mov        dword ptr [esp + 0xc], ecx
00000de3  8bbb4c900000      mov        edi, dword ptr [ebx + 0x904c]
00000de9  8b4c2408          mov        ecx, dword ptr [esp + 8]
00000ded  39f9              cmp        ecx, edi
00000def  89542414          mov        dword ptr [esp + 0x14], edx
00000df3  7d0b              jge        0xe00
00000df5  8b442410          mov        eax, dword ptr [esp + 0x10]
00000df9  e9aa000000        jmp        0xea8
00000dfe  90                nop        
00000dff  90                nop        
00000e00  8b7c240c          mov        edi, dword ptr [esp + 0xc]
00000e04  47                inc        edi
00000e05  80bb5890000000    cmp        byte ptr [ebx + 0x9058], 0
00000e0c  b804000000        mov        eax, 4
00000e11  b910000000        mov        ecx, 0x10
00000e16  0f45c1            cmovne     eax, ecx
00000e19  39c7              cmp        edi, eax
00000e1b  b800000000        mov        eax, 0
00000e20  0f4df8            cmovge     edi, eax
00000e23  90                nop        
00000e24  90                nop        
00000e25  90                nop        
00000e26  90                nop        
00000e27  90                nop        
00000e28  90                nop        
00000e29  90                nop        
00000e2a  90                nop        
00000e2b  90                nop        
00000e2c  90                nop        
00000e2d  90                nop        
00000e2e  90                nop        
00000e2f  90                nop        
00000e30  8b8350900000      mov        eax, dword ptr [ebx + 0x9050]
00000e36  39f8              cmp        eax, edi
00000e38  7516              jne        0xe50
00000e3a  8bb348900000      mov        esi, dword ptr [ebx + 0x9048]
00000e40  85f6              test       esi, esi
00000e42  7534              jne        0xe78
00000e44  eb4c              jmp        0xe92
00000e46  90                nop        
00000e47  90                nop        
00000e48  90                nop        
00000e49  90                nop        
00000e4a  90                nop        
00000e4b  90                nop        
00000e4c  90                nop        
00000e4d  90                nop        
00000e4e  90                nop        
00000e4f  90                nop        
00000e50  8b8350900000      mov        eax, dword ptr [ebx + 0x9050]
00000e56  80bb5890000000    cmp        byte ptr [ebx + 0x9058], 0
00000e5d  b904000000        mov        ecx, 4
00000e62  ba10000000        mov        edx, 0x10
00000e67  0f45ca            cmovne     ecx, edx
00000e6a  8bb348900000      mov        esi, dword ptr [ebx + 0x9048]
00000e70  39c8              cmp        eax, ecx
00000e72  7c20              jl         0xe94
00000e74  85f6              test       esi, esi
00000e76  741c              je         0xe94
00000e78  8b835c900000      mov        eax, dword ptr [ebx + 0x905c]
00000e7e  83f802            cmp        eax, 2
00000e81  7511              jne        0xe94
00000e83  83ec0c            sub        esp, 0xc
00000e86  6a0a              push       0xa
00000e88  e8831d0000        call       0x2c10
00000e8d  83c410            add        esp, 0x10
00000e90  eb9e              jmp        0xe30
00000e92  31f6              xor        esi, esi
00000e94  897c240c          mov        dword ptr [esp + 0xc], edi
00000e98  31c9              xor        ecx, ecx
00000e9a  8bbb4c900000      mov        edi, dword ptr [ebx + 0x904c]
00000ea0  8b442410          mov        eax, dword ptr [esp + 0x10]
00000ea4  8b542414          mov        edx, dword ptr [esp + 0x14]
00000ea8  894c2408          mov        dword ptr [esp + 8], ecx
00000eac  29cf              sub        edi, ecx
00000eae  39f8              cmp        eax, edi
00000eb0  0f4cf8            cmovl      edi, eax
00000eb3  8b44240c          mov        eax, dword ptr [esp + 0xc]
00000eb7  c1e00f            shl        eax, 0xf
00000eba  01d8              add        eax, ebx
00000ebc  0564900000        add        eax, 0x9064
00000ec1  8d0448            lea        eax, [eax + ecx*2]
00000ec4  8d0c54            lea        ecx, [esp + edx*2]
00000ec7  83c118            add        ecx, 0x18
00000eca  8d143f            lea        edx, [edi + edi]
00000ecd  83ec04            sub        esp, 4
00000ed0  52                push       edx
00000ed1  51                push       ecx
00000ed2  50                push       eax
00000ed3  e8481d0000        call       0x2c20
00000ed8  8b442420          mov        eax, dword ptr [esp + 0x20]
00000edc  83c410            add        esp, 0x10
00000edf  017c2408          add        dword ptr [esp + 8], edi
00000ee3  29f8              sub        eax, edi
00000ee5  7e2c              jle        0xf13
00000ee7  89442410          mov        dword ptr [esp + 0x10], eax
00000eeb  8b442414          mov        eax, dword ptr [esp + 0x14]
00000eef  01f8              add        eax, edi
00000ef1  85f6              test       esi, esi
00000ef3  8b4c240c          mov        ecx, dword ptr [esp + 0xc]
00000ef7  89c2              mov        edx, eax
00000ef9  0f85d1feffff      jne        0xdd0
00000eff  eb16              jmp        0xf17
00000f01  8b8b54900000      mov        ecx, dword ptr [ebx + 0x9054]
00000f07  8b8360900000      mov        eax, dword ptr [ebx + 0x9060]
00000f0d  89442408          mov        dword ptr [esp + 8], eax
00000f11  eb04              jmp        0xf17
00000f13  8b4c240c          mov        ecx, dword ptr [esp + 0xc]
00000f17  898b54900000      mov        dword ptr [ebx + 0x9054], ecx
00000f1d  8b442408          mov        eax, dword ptr [esp + 8]
00000f21  898360900000      mov        dword ptr [ebx + 0x9060], eax
00000f27  65a114000000      mov        eax, dword ptr gs:[0x14]
00000f2d  3b842418800000    cmp        eax, dword ptr [esp + 0x8018]
00000f34  7508              jne        0xf3e
00000f36  8d65f4            lea        esp, [ebp - 0xc]
00000f39  5e                pop        esi
00000f3a  5f                pop        edi
00000f3b  5b                pop        ebx
00000f3c  5d                pop        ebp
00000f3d  c3                ret        
00000f3e  e8ed1c0000        call       0x2c30
