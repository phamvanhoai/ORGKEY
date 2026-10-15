# APK member: lib/x86/libnopensl.so
# Library SHA-256: 81e6220973b454100123e9259f13717edc34b683f704a7e2f342ff6424ed7706
# Function: Java_sofeh_audio_NOpenSL_Start; address=0x1850; bytes=2713
# Decoder mode: default; decoded_bytes=2713
00001850  55                push       ebp
00001851  89e5              mov        ebp, esp
00001853  53                push       ebx
00001854  57                push       edi
00001855  56                push       esi
00001856  83e4f0            and        esp, 0xfffffff0
00001859  81ecc0000000      sub        esp, 0xc0
0000185f  e800000000        call       0x1864
00001864  5b                pop        ebx
00001865  81c338250000      add        ebx, 0x2538
0000186b  65a114000000      mov        eax, dword ptr gs:[0x14]
00001871  898424b8000000    mov        dword ptr [esp + 0xb8], eax
00001878  8b7510            mov        esi, dword ptr [ebp + 0x10]
0000187b  83ec0c            sub        esp, 0xc
0000187e  8d8368901000      lea        eax, [ebx + 0x109068]
00001884  89442418          mov        dword ptr [esp + 0x18], eax
00001888  50                push       eax
00001889  e8d2130000        call       0x2c60
0000188e  83c410            add        esp, 0x10
00001891  c7835c90000001000000  mov        dword ptr [ebx + 0x905c], 1
0000189b  85f6              test       esi, esi
0000189d  0f94c0            sete       al
000018a0  8b8b6c901000      mov        ecx, dword ptr [ebx + 0x10906c]
000018a6  b401              mov        ah, 1
000018a8  85c9              test       ecx, ecx
000018aa  743b              je         0x18e7
000018ac  388370901000      cmp        byte ptr [ebx + 0x109070], al
000018b2  7533              jne        0x18e7
000018b4  8b5514            mov        edx, dword ptr [ebp + 0x14]
000018b7  399374901000      cmp        dword ptr [ebx + 0x109074], edx
000018bd  7528              jne        0x18e7
000018bf  8b5518            mov        edx, dword ptr [ebp + 0x18]
000018c2  399378901000      cmp        dword ptr [ebx + 0x109078], edx
000018c8  751d              jne        0x18e7
000018ca  8b551c            mov        edx, dword ptr [ebp + 0x1c]
000018cd  39937c901000      cmp        dword ptr [ebx + 0x10907c], edx
000018d3  7512              jne        0x18e7
000018d5  31d2              xor        edx, edx
000018d7  807d2000          cmp        byte ptr [ebp + 0x20], 0
000018db  0f95c2            setne      dl
000018de  399380901000      cmp        dword ptr [ebx + 0x109080], edx
000018e4  0f95c4            setne      ah
000018e7  85c9              test       ecx, ecx
000018e9  0f94c2            sete       dl
000018ec  31c9              xor        ecx, ecx
000018ee  807d2000          cmp        byte ptr [ebp + 0x20], 0
000018f2  0f95c1            setne      cl
000018f5  898b80901000      mov        dword ptr [ebx + 0x109080], ecx
000018fb  888370901000      mov        byte ptr [ebx + 0x109070], al
00001901  88642410          mov        byte ptr [esp + 0x10], ah
00001905  88e0              mov        al, ah
00001907  3401              xor        al, 1
00001909  08d0              or         al, dl
0000190b  8b7d08            mov        edi, dword ptr [ebp + 8]
0000190e  8b4514            mov        eax, dword ptr [ebp + 0x14]
00001911  898374901000      mov        dword ptr [ebx + 0x109074], eax
00001917  8b4518            mov        eax, dword ptr [ebp + 0x18]
0000191a  898378901000      mov        dword ptr [ebx + 0x109078], eax
00001920  8b451c            mov        eax, dword ptr [ebp + 0x1c]
00001923  89837c901000      mov        dword ptr [ebx + 0x10907c], eax
00001929  0f85da000000      jne        0x1a09
0000192f  c7835c90000003000000  mov        dword ptr [ebx + 0x905c], 3
00001939  c6838490100000    mov        byte ptr [ebx + 0x109084], 0
00001940  8b8388901000      mov        eax, dword ptr [ebx + 0x109088]
00001946  85c0              test       eax, eax
00001948  740d              je         0x1957
0000194a  8b08              mov        ecx, dword ptr [eax]
0000194c  83ec08            sub        esp, 8
0000194f  6a01              push       1
00001951  50                push       eax
00001952  ff11              call       dword ptr [ecx]
00001954  83c410            add        esp, 0x10
00001957  8b838c901000      mov        eax, dword ptr [ebx + 0x10908c]
0000195d  85c0              test       eax, eax
0000195f  740c              je         0x196d
00001961  8b08              mov        ecx, dword ptr [eax]
00001963  83ec0c            sub        esp, 0xc
00001966  50                push       eax
00001967  ff5104            call       dword ptr [ecx + 4]
0000196a  83c410            add        esp, 0x10
0000196d  8b8390901000      mov        eax, dword ptr [ebx + 0x109090]
00001973  85c0              test       eax, eax
00001975  740d              je         0x1984
00001977  8b08              mov        ecx, dword ptr [eax]
00001979  83ec08            sub        esp, 8
0000197c  6a01              push       1
0000197e  50                push       eax
0000197f  ff11              call       dword ptr [ecx]
00001981  83c410            add        esp, 0x10
00001984  8b8364900800      mov        eax, dword ptr [ebx + 0x89064]
0000198a  85c0              test       eax, eax
0000198c  740c              je         0x199a
0000198e  8b08              mov        ecx, dword ptr [eax]
00001990  83ec0c            sub        esp, 0xc
00001993  50                push       eax
00001994  ff5104            call       dword ptr [ecx + 4]
00001997  83c410            add        esp, 0x10
0000199a  83ec0c            sub        esp, 0xc
0000199d  ff742418          push       dword ptr [esp + 0x18]
000019a1  e8ca120000        call       0x2c70
000019a6  83c410            add        esp, 0x10
000019a9  83bb9490100000    cmp        dword ptr [ebx + 0x109094], 0
000019b0  7e34              jle        0x19e6
000019b2  31ff              xor        edi, edi
000019b4  90                nop        
000019b5  90                nop        
000019b6  90                nop        
000019b7  90                nop        
000019b8  90                nop        
000019b9  90                nop        
000019ba  90                nop        
000019bb  90                nop        
000019bc  90                nop        
000019bd  90                nop        
000019be  90                nop        
000019bf  90                nop        
000019c0  83ec0c            sub        esp, 0xc
000019c3  68e8030000        push       0x3e8
000019c8  e843120000        call       0x2c10
000019cd  83c410            add        esp, 0x10
000019d0  83bb9490100000    cmp        dword ptr [ebx + 0x109094], 0
000019d7  7e0d              jle        0x19e6
000019d9  8d4701            lea        eax, [edi + 1]
000019dc  81ffe7030000      cmp        edi, 0x3e7
000019e2  89c7              mov        edi, eax
000019e4  72da              jb         0x19c0
000019e6  83ec0c            sub        esp, 0xc
000019e9  ff742418          push       dword ptr [esp + 0x18]
000019ed  e86e120000        call       0x2c60
000019f2  83c410            add        esp, 0x10
000019f5  8b7d08            mov        edi, dword ptr [ebp + 8]
000019f8  89f9              mov        ecx, edi
000019fa  e8f1080000        call       0x22f0
000019ff  c7839890100000000000  mov        dword ptr [ebx + 0x109098], 0
00001a09  8b839c901000      mov        eax, dword ptr [ebx + 0x10909c]
00001a0f  85c0              test       eax, eax
00001a11  7417              je         0x1a2a
00001a13  8b0f              mov        ecx, dword ptr [edi]
00001a15  83ec08            sub        esp, 8
00001a18  50                push       eax
00001a19  57                push       edi
00001a1a  ff5158            call       dword ptr [ecx + 0x58]
00001a1d  83c410            add        esp, 0x10
00001a20  c7839c90100000000000  mov        dword ptr [ebx + 0x10909c], 0
00001a2a  8b83a0901000      mov        eax, dword ptr [ebx + 0x1090a0]
00001a30  85c0              test       eax, eax
00001a32  7417              je         0x1a4b
00001a34  8b0f              mov        ecx, dword ptr [edi]
00001a36  83ec08            sub        esp, 8
00001a39  50                push       eax
00001a3a  57                push       edi
00001a3b  ff5158            call       dword ptr [ecx + 0x58]
00001a3e  83c410            add        esp, 0x10
00001a41  c783a090100000000000  mov        dword ptr [ebx + 0x1090a0], 0
00001a4b  c783a490100000000000  mov        dword ptr [ebx + 0x1090a4], 0
00001a55  c783a890100000000000  mov        dword ptr [ebx + 0x1090a8], 0
00001a5f  85f6              test       esi, esi
00001a61  0f8489000000      je         0x1af0
00001a67  8b07              mov        eax, dword ptr [edi]
00001a69  83ec08            sub        esp, 8
00001a6c  8d8ba8901000      lea        ecx, [ebx + 0x1090a8]
00001a72  51                push       ecx
00001a73  57                push       edi
00001a74  ff906c030000      call       dword ptr [eax + 0x36c]
00001a7a  83c410            add        esp, 0x10
00001a7d  8b07              mov        eax, dword ptr [edi]
00001a7f  83ec08            sub        esp, 8
00001a82  56                push       esi
00001a83  57                push       edi
00001a84  ff5054            call       dword ptr [eax + 0x54]
00001a87  83c410            add        esp, 0x10
00001a8a  89839c901000      mov        dword ptr [ebx + 0x10909c], eax
00001a90  8b07              mov        eax, dword ptr [edi]
00001a92  83ec08            sub        esp, 8
00001a95  56                push       esi
00001a96  57                push       edi
00001a97  ff507c            call       dword ptr [eax + 0x7c]
00001a9a  83c410            add        esp, 0x10
00001a9d  89c7              mov        edi, eax
00001a9f  8b4508            mov        eax, dword ptr [ebp + 8]
00001aa2  8b00              mov        eax, dword ptr [eax]
00001aa4  83ec08            sub        esp, 8
00001aa7  57                push       edi
00001aa8  ff7508            push       dword ptr [ebp + 8]
00001aab  ff5054            call       dword ptr [eax + 0x54]
00001aae  83c410            add        esp, 0x10
00001ab1  8983a0901000      mov        dword ptr [ebx + 0x1090a0], eax
00001ab7  8b4508            mov        eax, dword ptr [ebp + 8]
00001aba  8b00              mov        eax, dword ptr [eax]
00001abc  83ec08            sub        esp, 8
00001abf  57                push       edi
00001ac0  8b7508            mov        esi, dword ptr [ebp + 8]
00001ac3  56                push       esi
00001ac4  ff505c            call       dword ptr [eax + 0x5c]
00001ac7  83c410            add        esp, 0x10
00001aca  8b06              mov        eax, dword ptr [esi]
00001acc  8d8b44caffff      lea        ecx, [ebx - 0x35bc]
00001ad2  8d9348caffff      lea        edx, [ebx - 0x35b8]
00001ad8  51                push       ecx
00001ad9  52                push       edx
00001ada  ffb3a0901000      push       dword ptr [ebx + 0x1090a0]
00001ae0  56                push       esi
00001ae1  ff9084000000      call       dword ptr [eax + 0x84]
00001ae7  83c410            add        esp, 0x10
00001aea  8983a4901000      mov        dword ptr [ebx + 0x1090a4], eax
00001af0  c7834490000000000000  mov        dword ptr [ebx + 0x9044], 0
00001afa  c7834090000000000000  mov        dword ptr [ebx + 0x9040], 0
00001b04  c7835090000000000000  mov        dword ptr [ebx + 0x9050], 0
00001b0e  c7835490000000000000  mov        dword ptr [ebx + 0x9054], 0
00001b18  c7836090000000000000  mov        dword ptr [ebx + 0x9060], 0
00001b22  c783ac90100000000000  mov        dword ptr [ebx + 0x1090ac], 0
00001b2c  8b4518            mov        eax, dword ptr [ebp + 0x18]
00001b2f  01c0              add        eax, eax
00001b31  3d00400000        cmp        eax, 0x4000
00001b36  be00400000        mov        esi, 0x4000
00001b3b  0f4cf0            cmovl      esi, eax
00001b3e  89b34c900000      mov        dword ptr [ebx + 0x904c], esi
00001b44  c6835890000001    mov        byte ptr [ebx + 0x9058], 1
00001b4b  8b4d1c            mov        ecx, dword ptr [ebp + 0x1c]
00001b4e  83f904            cmp        ecx, 4
00001b51  b803000000        mov        eax, 3
00001b56  0f4dc1            cmovge     eax, ecx
00001b59  83f810            cmp        eax, 0x10
00001b5c  b910000000        mov        ecx, 0x10
00001b61  0f42c8            cmovb      ecx, eax
00001b64  894c2414          mov        dword ptr [esp + 0x14], ecx
00001b68  898b3c100000      mov        dword ptr [ebx + 0x103c], ecx
00001b6e  01f6              add        esi, esi
00001b70  83ec04            sub        esp, 4
00001b73  8dbb64900000      lea        edi, [ebx + 0x9064]
00001b79  56                push       esi
00001b7a  6a00              push       0
00001b7c  57                push       edi
00001b7d  e8fe100000        call       0x2c80
00001b82  83c40c            add        esp, 0xc
00001b85  8d8368900800      lea        eax, [ebx + 0x89068]
00001b8b  56                push       esi
00001b8c  6a00              push       0
00001b8e  50                push       eax
00001b8f  e8ec100000        call       0x2c80
00001b94  83c40c            add        esp, 0xc
00001b97  8d8364100100      lea        eax, [ebx + 0x11064]
00001b9d  56                push       esi
00001b9e  6a00              push       0
00001ba0  50                push       eax
00001ba1  e8da100000        call       0x2c80
00001ba6  83c40c            add        esp, 0xc
00001ba9  8d8368100900      lea        eax, [ebx + 0x91068]
00001baf  56                push       esi
00001bb0  6a00              push       0
00001bb2  50                push       eax
00001bb3  e8c8100000        call       0x2c80
00001bb8  83c40c            add        esp, 0xc
00001bbb  8d8364900100      lea        eax, [ebx + 0x19064]
00001bc1  56                push       esi
00001bc2  6a00              push       0
00001bc4  50                push       eax
00001bc5  e8b6100000        call       0x2c80
00001bca  83c40c            add        esp, 0xc
00001bcd  8d8368900900      lea        eax, [ebx + 0x99068]
00001bd3  56                push       esi
00001bd4  6a00              push       0
00001bd6  50                push       eax
00001bd7  e8a4100000        call       0x2c80
00001bdc  83c40c            add        esp, 0xc
00001bdf  8d8364100200      lea        eax, [ebx + 0x21064]
00001be5  56                push       esi
00001be6  6a00              push       0
00001be8  50                push       eax
00001be9  e892100000        call       0x2c80
00001bee  83c40c            add        esp, 0xc
00001bf1  8d8368100a00      lea        eax, [ebx + 0xa1068]
00001bf7  56                push       esi
00001bf8  6a00              push       0
00001bfa  50                push       eax
00001bfb  e880100000        call       0x2c80
00001c00  83c40c            add        esp, 0xc
00001c03  8d8364900200      lea        eax, [ebx + 0x29064]
00001c09  56                push       esi
00001c0a  6a00              push       0
00001c0c  50                push       eax
00001c0d  e86e100000        call       0x2c80
00001c12  83c40c            add        esp, 0xc
00001c15  8d8368900a00      lea        eax, [ebx + 0xa9068]
00001c1b  56                push       esi
00001c1c  6a00              push       0
00001c1e  50                push       eax
00001c1f  e85c100000        call       0x2c80
00001c24  83c40c            add        esp, 0xc
00001c27  8d8364100300      lea        eax, [ebx + 0x31064]
00001c2d  56                push       esi
00001c2e  6a00              push       0
00001c30  50                push       eax
00001c31  e84a100000        call       0x2c80
00001c36  83c40c            add        esp, 0xc
00001c39  8d8368100b00      lea        eax, [ebx + 0xb1068]
00001c3f  56                push       esi
00001c40  6a00              push       0
00001c42  50                push       eax
00001c43  e838100000        call       0x2c80
00001c48  83c40c            add        esp, 0xc
00001c4b  8d8364900300      lea        eax, [ebx + 0x39064]
00001c51  56                push       esi
00001c52  6a00              push       0
00001c54  50                push       eax
00001c55  e826100000        call       0x2c80
00001c5a  83c40c            add        esp, 0xc
00001c5d  8d8368900b00      lea        eax, [ebx + 0xb9068]
00001c63  56                push       esi
00001c64  6a00              push       0
00001c66  50                push       eax
00001c67  e814100000        call       0x2c80
00001c6c  83c40c            add        esp, 0xc
00001c6f  8d8364100400      lea        eax, [ebx + 0x41064]
00001c75  56                push       esi
00001c76  6a00              push       0
00001c78  50                push       eax
00001c79  e802100000        call       0x2c80
00001c7e  83c40c            add        esp, 0xc
00001c81  8d8368100c00      lea        eax, [ebx + 0xc1068]
00001c87  56                push       esi
00001c88  6a00              push       0
00001c8a  50                push       eax
00001c8b  e8f00f0000        call       0x2c80
00001c90  83c40c            add        esp, 0xc
00001c93  8d8364900400      lea        eax, [ebx + 0x49064]
00001c99  56                push       esi
00001c9a  6a00              push       0
00001c9c  50                push       eax
00001c9d  e8de0f0000        call       0x2c80
00001ca2  83c40c            add        esp, 0xc
00001ca5  8d8368900c00      lea        eax, [ebx + 0xc9068]
00001cab  56                push       esi
00001cac  6a00              push       0
00001cae  50                push       eax
00001caf  e8cc0f0000        call       0x2c80
00001cb4  83c40c            add        esp, 0xc
00001cb7  8d8364100500      lea        eax, [ebx + 0x51064]
00001cbd  56                push       esi
00001cbe  6a00              push       0
00001cc0  50                push       eax
00001cc1  e8ba0f0000        call       0x2c80
00001cc6  83c40c            add        esp, 0xc
00001cc9  8d8368100d00      lea        eax, [ebx + 0xd1068]
00001ccf  56                push       esi
00001cd0  6a00              push       0
00001cd2  50                push       eax
00001cd3  e8a80f0000        call       0x2c80
00001cd8  83c40c            add        esp, 0xc
00001cdb  8d8364900500      lea        eax, [ebx + 0x59064]
00001ce1  56                push       esi
00001ce2  6a00              push       0
00001ce4  50                push       eax
00001ce5  e8960f0000        call       0x2c80
00001cea  83c40c            add        esp, 0xc
00001ced  8d8368900d00      lea        eax, [ebx + 0xd9068]
00001cf3  56                push       esi
00001cf4  6a00              push       0
00001cf6  50                push       eax
00001cf7  e8840f0000        call       0x2c80
00001cfc  83c40c            add        esp, 0xc
00001cff  8d8364100600      lea        eax, [ebx + 0x61064]
00001d05  56                push       esi
00001d06  6a00              push       0
00001d08  50                push       eax
00001d09  e8720f0000        call       0x2c80
00001d0e  83c40c            add        esp, 0xc
00001d11  8d8368100e00      lea        eax, [ebx + 0xe1068]
00001d17  56                push       esi
00001d18  6a00              push       0
00001d1a  50                push       eax
00001d1b  e8600f0000        call       0x2c80
00001d20  83c40c            add        esp, 0xc
00001d23  8d8364900600      lea        eax, [ebx + 0x69064]
00001d29  56                push       esi
00001d2a  6a00              push       0
00001d2c  50                push       eax
00001d2d  e84e0f0000        call       0x2c80
00001d32  83c40c            add        esp, 0xc
00001d35  8d8368900e00      lea        eax, [ebx + 0xe9068]
00001d3b  56                push       esi
00001d3c  6a00              push       0
00001d3e  50                push       eax
00001d3f  e83c0f0000        call       0x2c80
00001d44  83c40c            add        esp, 0xc
00001d47  8d8364100700      lea        eax, [ebx + 0x71064]
00001d4d  56                push       esi
00001d4e  6a00              push       0
00001d50  50                push       eax
00001d51  e82a0f0000        call       0x2c80
00001d56  83c40c            add        esp, 0xc
00001d59  8d8368100f00      lea        eax, [ebx + 0xf1068]
00001d5f  56                push       esi
00001d60  6a00              push       0
00001d62  50                push       eax
00001d63  e8180f0000        call       0x2c80
00001d68  83c40c            add        esp, 0xc
00001d6b  8d8364900700      lea        eax, [ebx + 0x79064]
00001d71  56                push       esi
00001d72  6a00              push       0
00001d74  50                push       eax
00001d75  e8060f0000        call       0x2c80
00001d7a  83c40c            add        esp, 0xc
00001d7d  8d8368900f00      lea        eax, [ebx + 0xf9068]
00001d83  56                push       esi
00001d84  6a00              push       0
00001d86  50                push       eax
00001d87  e8f40e0000        call       0x2c80
00001d8c  83c40c            add        esp, 0xc
00001d8f  8d8364100800      lea        eax, [ebx + 0x81064]
00001d95  56                push       esi
00001d96  6a00              push       0
00001d98  50                push       eax
00001d99  e8e20e0000        call       0x2c80
00001d9e  83c40c            add        esp, 0xc
00001da1  8d8368101000      lea        eax, [ebx + 0x101068]
00001da7  56                push       esi
00001da8  6a00              push       0
00001daa  50                push       eax
00001dab  e8d00e0000        call       0x2c80
00001db0  83c410            add        esp, 0x10
00001db3  8b836c901000      mov        eax, dword ptr [ebx + 0x10906c]
00001db9  85c0              test       eax, eax
00001dbb  0f94c1            sete       cl
00001dbe  084c2410          or         byte ptr [esp + 0x10], cl
00001dc2  0f8407010000      je         0x1ecf
00001dc8  85c0              test       eax, eax
00001dca  0f84ad010000      je         0x1f7d
00001dd0  c7842498000000bd070080  mov        dword ptr [esp + 0x98], 0x800007bd
00001ddb  8b442414          mov        eax, dword ptr [esp + 0x14]
00001ddf  8984249c000000    mov        dword ptr [esp + 0x9c], eax
00001de6  c744247c02000000  mov        dword ptr [esp + 0x7c], 2
00001dee  c784248000000002000000  mov        dword ptr [esp + 0x80], 2
00001df9  694514e8030000    imul       eax, dword ptr [ebp + 0x14], 0x3e8
00001e00  89442414          mov        dword ptr [esp + 0x14], eax
00001e04  89842484000000    mov        dword ptr [esp + 0x84], eax
00001e0b  0f288334caffff    movaps     xmm0, xmmword ptr [ebx - 0x35cc]
00001e12  0f11842488000000  movups     xmmword ptr [esp + 0x88], xmm0
00001e1a  8d842498000000    lea        eax, [esp + 0x98]
00001e21  89442474          mov        dword ptr [esp + 0x74], eax
00001e25  8d44247c          lea        eax, [esp + 0x7c]
00001e29  89442478          mov        dword ptr [esp + 0x78], eax
00001e2d  c744246c04000000  mov        dword ptr [esp + 0x6c], 4
00001e35  8b83b4901000      mov        eax, dword ptr [ebx + 0x1090b4]
00001e3b  89442470          mov        dword ptr [esp + 0x70], eax
00001e3f  8d44246c          lea        eax, [esp + 0x6c]
00001e43  89442464          mov        dword ptr [esp + 0x64], eax
00001e47  c744246800000000  mov        dword ptr [esp + 0x68], 0
00001e4f  8b83e8ffffff      mov        eax, dword ptr [ebx - 0x18]
00001e55  8b00              mov        eax, dword ptr [eax]
00001e57  89442410          mov        dword ptr [esp + 0x10], eax
00001e5b  898424b0000000    mov        dword ptr [esp + 0xb0], eax
00001e62  8b83ecffffff      mov        eax, dword ptr [ebx - 0x14]
00001e68  8b38              mov        edi, dword ptr [eax]
00001e6a  89bc24b4000000    mov        dword ptr [esp + 0xb4], edi
00001e71  c78424ac00000001000000  mov        dword ptr [esp + 0xac], 1
00001e7c  c78424a800000001000000  mov        dword ptr [esp + 0xa8], 1
00001e87  8b83b0901000      mov        eax, dword ptr [ebx + 0x1090b0]
00001e8d  8b10              mov        edx, dword ptr [eax]
00001e8f  83ec04            sub        esp, 4
00001e92  8db424ac000000    lea        esi, [esp + 0xac]
00001e99  8d8b48900000      lea        ecx, [ebx + 0x9048]
00001e9f  56                push       esi
00001ea0  8db424b8000000    lea        esi, [esp + 0xb8]
00001ea7  56                push       esi
00001ea8  6a02              push       2
00001eaa  8d742474          lea        esi, [esp + 0x74]
00001eae  56                push       esi
00001eaf  8db42488000000    lea        esi, [esp + 0x88]
00001eb6  56                push       esi
00001eb7  51                push       ecx
00001eb8  50                push       eax
00001eb9  ff5208            call       dword ptr [edx + 8]
00001ebc  83c420            add        esp, 0x20
00001ebf  85c0              test       eax, eax
00001ec1  0f8464010000      je         0x202b
00001ec7  8b4d08            mov        ecx, dword ptr [ebp + 8]
00001eca  e976010000        jmp        0x2045
00001ecf  8b838c901000      mov        eax, dword ptr [ebx + 0x10908c]
00001ed5  85c0              test       eax, eax
00001ed7  0f8442010000      je         0x201f
00001edd  83bb8890100000    cmp        dword ptr [ebx + 0x109088], 0
00001ee4  0f8435010000      je         0x201f
00001eea  8b08              mov        ecx, dword ptr [eax]
00001eec  83ec0c            sub        esp, 0xc
00001eef  50                push       eax
00001ef0  ff5104            call       dword ptr [ecx + 4]
00001ef3  83c410            add        esp, 0x10
00001ef6  83bb3c10000000    cmp        dword ptr [ebx + 0x103c], 0
00001efd  7e43              jle        0x1f42
00001eff  31f6              xor        esi, esi
00001f01  90                nop        
00001f02  90                nop        
00001f03  90                nop        
00001f04  90                nop        
00001f05  90                nop        
00001f06  90                nop        
00001f07  90                nop        
00001f08  90                nop        
00001f09  90                nop        
00001f0a  90                nop        
00001f0b  90                nop        
00001f0c  90                nop        
00001f0d  90                nop        
00001f0e  90                nop        
00001f0f  90                nop        
00001f10  8b838c901000      mov        eax, dword ptr [ebx + 0x10908c]
00001f16  8b08              mov        ecx, dword ptr [eax]
00001f18  8b934c900000      mov        edx, dword ptr [ebx + 0x904c]
00001f1e  01d2              add        edx, edx
00001f20  83ec04            sub        esp, 4
00001f23  52                push       edx
00001f24  57                push       edi
00001f25  50                push       eax
00001f26  ff11              call       dword ptr [ecx]
00001f28  83c410            add        esp, 0x10
00001f2b  85c0              test       eax, eax
00001f2d  0f85ec000000      jne        0x201f
00001f33  46                inc        esi
00001f34  81c700800000      add        edi, 0x8000
00001f3a  3bb33c100000      cmp        esi, dword ptr [ebx + 0x103c]
00001f40  7cce              jl         0x1f10
00001f42  8b8388901000      mov        eax, dword ptr [ebx + 0x109088]
00001f48  8b08              mov        ecx, dword ptr [eax]
00001f4a  83ec08            sub        esp, 8
00001f4d  6a03              push       3
00001f4f  50                push       eax
00001f50  ff11              call       dword ptr [ecx]
00001f52  83c410            add        esp, 0x10
00001f55  85c0              test       eax, eax
00001f57  0f85c2000000      jne        0x201f
00001f5d  c7835c90000002000000  mov        dword ptr [ebx + 0x905c], 2
00001f67  83ec0c            sub        esp, 0xc
00001f6a  ff742418          push       dword ptr [esp + 0x18]
00001f6e  e8fd0c0000        call       0x2c70
00001f73  83c410            add        esp, 0x10
00001f76  b001              mov        al, 1
00001f78  e9de000000        jmp        0x205b
00001f7d  83ec20            sub        esp, 0x20
00001f80  0f57c0            xorps      xmm0, xmm0
00001f83  0f11442404        movups     xmmword ptr [esp + 4], xmm0
00001f88  8d836c901000      lea        eax, [ebx + 0x10906c]
00001f8e  890424            mov        dword ptr [esp], eax
00001f91  c744241400000000  mov        dword ptr [esp + 0x14], 0
00001f99  e8f20c0000        call       0x2c90
00001f9e  83c420            add        esp, 0x20
00001fa1  85c0              test       eax, eax
00001fa3  757a              jne        0x201f
00001fa5  8b836c901000      mov        eax, dword ptr [ebx + 0x10906c]
00001fab  8b08              mov        ecx, dword ptr [eax]
00001fad  83ec08            sub        esp, 8
00001fb0  6a00              push       0
00001fb2  50                push       eax
00001fb3  ff11              call       dword ptr [ecx]
00001fb5  83c410            add        esp, 0x10
00001fb8  85c0              test       eax, eax
00001fba  7563              jne        0x201f
00001fbc  8b836c901000      mov        eax, dword ptr [ebx + 0x10906c]
00001fc2  8b08              mov        ecx, dword ptr [eax]
00001fc4  8b93f0ffffff      mov        edx, dword ptr [ebx - 0x10]
00001fca  83ec04            sub        esp, 4
00001fcd  8db3b0901000      lea        esi, [ebx + 0x1090b0]
00001fd3  56                push       esi
00001fd4  ff32              push       dword ptr [edx]
00001fd6  50                push       eax
00001fd7  ff510c            call       dword ptr [ecx + 0xc]
00001fda  83c410            add        esp, 0x10
00001fdd  85c0              test       eax, eax
00001fdf  753e              jne        0x201f
00001fe1  8b83b0901000      mov        eax, dword ptr [ebx + 0x1090b0]
00001fe7  8b08              mov        ecx, dword ptr [eax]
00001fe9  83ec0c            sub        esp, 0xc
00001fec  8d93b4901000      lea        edx, [ebx + 0x1090b4]
00001ff2  6a00              push       0
00001ff4  6a00              push       0
00001ff6  6a00              push       0
00001ff8  52                push       edx
00001ff9  50                push       eax
00001ffa  ff511c            call       dword ptr [ecx + 0x1c]
00001ffd  83c420            add        esp, 0x20
00002000  85c0              test       eax, eax
00002002  751b              jne        0x201f
00002004  8b83b4901000      mov        eax, dword ptr [ebx + 0x1090b4]
0000200a  8b08              mov        ecx, dword ptr [eax]
0000200c  83ec08            sub        esp, 8
0000200f  6a00              push       0
00002011  50                push       eax
00002012  ff11              call       dword ptr [ecx]
00002014  83c410            add        esp, 0x10
00002017  85c0              test       eax, eax
00002019  0f84b6020000      je         0x22d5
0000201f  c7835c90000000000000  mov        dword ptr [ebx + 0x905c], 0
00002029  eb1f              jmp        0x204a
0000202b  8b8348900000      mov        eax, dword ptr [ebx + 0x9048]
00002031  8b08              mov        ecx, dword ptr [eax]
00002033  83ec08            sub        esp, 8
00002036  6a00              push       0
00002038  50                push       eax
00002039  ff11              call       dword ptr [ecx]
0000203b  83c410            add        esp, 0x10
0000203e  85c0              test       eax, eax
00002040  8b4d08            mov        ecx, dword ptr [ebp + 8]
00002043  7432              je         0x2077
00002045  e8a6020000        call       0x22f0
0000204a  83ec0c            sub        esp, 0xc
0000204d  ff742418          push       dword ptr [esp + 0x18]
00002051  e81a0c0000        call       0x2c70
00002056  83c410            add        esp, 0x10
00002059  31c0              xor        eax, eax
0000205b  658b0d14000000    mov        ecx, dword ptr gs:[0x14]
00002062  3b8c24b8000000    cmp        ecx, dword ptr [esp + 0xb8]
00002069  0f8575020000      jne        0x22e4
0000206f  8d65f4            lea        esp, [ebp - 0xc]
00002072  5e                pop        esi
00002073  5f                pop        edi
00002074  5b                pop        ebx
00002075  5d                pop        ebp
00002076  c3                ret        
00002077  8b8348900000      mov        eax, dword ptr [ebx + 0x9048]
0000207d  8b08              mov        ecx, dword ptr [eax]
0000207f  8b93f4ffffff      mov        edx, dword ptr [ebx - 0xc]
00002085  83ec04            sub        esp, 4
00002088  8db388901000      lea        esi, [ebx + 0x109088]
0000208e  56                push       esi
0000208f  ff32              push       dword ptr [edx]
00002091  50                push       eax
00002092  ff510c            call       dword ptr [ecx + 0xc]
00002095  83c410            add        esp, 0x10
00002098  85c0              test       eax, eax
0000209a  0f8527feffff      jne        0x1ec7
000020a0  8b8348900000      mov        eax, dword ptr [ebx + 0x9048]
000020a6  8b08              mov        ecx, dword ptr [eax]
000020a8  83ec04            sub        esp, 4
000020ab  8d938c901000      lea        edx, [ebx + 0x10908c]
000020b1  52                push       edx
000020b2  ff742418          push       dword ptr [esp + 0x18]
000020b6  50                push       eax
000020b7  ff510c            call       dword ptr [ecx + 0xc]
000020ba  83c410            add        esp, 0x10
000020bd  85c0              test       eax, eax
000020bf  0f8502feffff      jne        0x1ec7
000020c5  83bba490100000    cmp        dword ptr [ebx + 0x1090a4], 0
000020cc  8d8394e6ffff      lea        eax, [ebx - 0x196c]
000020d2  8d8b14eaffff      lea        ecx, [ebx - 0x15ec]
000020d8  0f44c1            cmove      eax, ecx
000020db  83bb9c90100000    cmp        dword ptr [ebx + 0x10909c], 0
000020e2  0f44c1            cmove      eax, ecx
000020e5  83bba890100000    cmp        dword ptr [ebx + 0x1090a8], 0
000020ec  8b938c901000      mov        edx, dword ptr [ebx + 0x10908c]
000020f2  8b32              mov        esi, dword ptr [edx]
000020f4  0f44c1            cmove      eax, ecx
000020f7  83ec04            sub        esp, 4
000020fa  6a00              push       0
000020fc  50                push       eax
000020fd  52                push       edx
000020fe  ff560c            call       dword ptr [esi + 0xc]
00002101  83c410            add        esp, 0x10
00002104  85c0              test       eax, eax
00002106  0f85bbfdffff      jne        0x1ec7
0000210c  8b8348900000      mov        eax, dword ptr [ebx + 0x9048]
00002112  8b08              mov        ecx, dword ptr [eax]
00002114  83ec04            sub        esp, 4
00002117  8d93b8901000      lea        edx, [ebx + 0x1090b8]
0000211d  52                push       edx
0000211e  57                push       edi
0000211f  50                push       eax
00002120  ff510c            call       dword ptr [ecx + 0xc]
00002123  83c410            add        esp, 0x10
00002126  85c0              test       eax, eax
00002128  0f8599fdffff      jne        0x1ec7
0000212e  83bb3c10000000    cmp        dword ptr [ebx + 0x103c], 0
00002135  7e3a              jle        0x2171
00002137  31f6              xor        esi, esi
00002139  8dbb64900000      lea        edi, [ebx + 0x9064]
0000213f  8b838c901000      mov        eax, dword ptr [ebx + 0x10908c]
00002145  8b08              mov        ecx, dword ptr [eax]
00002147  8b934c900000      mov        edx, dword ptr [ebx + 0x904c]
0000214d  01d2              add        edx, edx
0000214f  83ec04            sub        esp, 4
00002152  52                push       edx
00002153  57                push       edi
00002154  50                push       eax
00002155  ff11              call       dword ptr [ecx]
00002157  83c410            add        esp, 0x10
0000215a  85c0              test       eax, eax
0000215c  0f8565fdffff      jne        0x1ec7
00002162  46                inc        esi
00002163  81c700800000      add        edi, 0x8000
00002169  3bb33c100000      cmp        esi, dword ptr [ebx + 0x103c]
0000216f  7cce              jl         0x213f
00002171  8b8388901000      mov        eax, dword ptr [ebx + 0x109088]
00002177  8b08              mov        ecx, dword ptr [eax]
00002179  83ec08            sub        esp, 8
0000217c  6a03              push       3
0000217e  50                push       eax
0000217f  ff11              call       dword ptr [ecx]
00002181  83c410            add        esp, 0x10
00002184  85c0              test       eax, eax
00002186  0f853bfdffff      jne        0x1ec7
0000218c  807d2000          cmp        byte ptr [ebp + 0x20], 0
00002190  0f8426010000      je         0x22bc
00002196  c744245cbd070080  mov        dword ptr [esp + 0x5c], 0x800007bd
0000219e  8b833c100000      mov        eax, dword ptr [ebx + 0x103c]
000021a4  89442460          mov        dword ptr [esp + 0x60], eax
000021a8  c744244002000000  mov        dword ptr [esp + 0x40], 2
000021b0  c744244401000000  mov        dword ptr [esp + 0x44], 1
000021b8  8b442414          mov        eax, dword ptr [esp + 0x14]
000021bc  89442448          mov        dword ptr [esp + 0x48], eax
000021c0  0f288314caffff    movaps     xmm0, xmmword ptr [ebx - 0x35ec]
000021c7  0f1144244c        movups     xmmword ptr [esp + 0x4c], xmm0
000021cc  8d44245c          lea        eax, [esp + 0x5c]
000021d0  89442438          mov        dword ptr [esp + 0x38], eax
000021d4  8d442440          lea        eax, [esp + 0x40]
000021d8  8944243c          mov        dword ptr [esp + 0x3c], eax
000021dc  0f108324caffff    movups     xmm0, xmmword ptr [ebx - 0x35dc]
000021e3  0f29442420        movaps     xmmword ptr [esp + 0x20], xmm0
000021e8  8d442420          lea        eax, [esp + 0x20]
000021ec  89442418          mov        dword ptr [esp + 0x18], eax
000021f0  c744241c00000000  mov        dword ptr [esp + 0x1c], 0
000021f8  8b83f8ffffff      mov        eax, dword ptr [ebx - 8]
000021fe  8b00              mov        eax, dword ptr [eax]
00002200  89442410          mov        dword ptr [esp + 0x10], eax
00002204  898424a4000000    mov        dword ptr [esp + 0xa4], eax
0000220b  c78424a000000001000000  mov        dword ptr [esp + 0xa0], 1
00002216  8b83b0901000      mov        eax, dword ptr [ebx + 0x1090b0]
0000221c  8b10              mov        edx, dword ptr [eax]
0000221e  83ec04            sub        esp, 4
00002221  8db424a4000000    lea        esi, [esp + 0xa4]
00002228  8dbc24a8000000    lea        edi, [esp + 0xa8]
0000222f  8d8bbc901000      lea        ecx, [ebx + 0x1090bc]
00002235  56                push       esi
00002236  57                push       edi
00002237  6a01              push       1
00002239  8d742448          lea        esi, [esp + 0x48]
0000223d  56                push       esi
0000223e  8d74242c          lea        esi, [esp + 0x2c]
00002242  56                push       esi
00002243  51                push       ecx
00002244  50                push       eax
00002245  ff520c            call       dword ptr [edx + 0xc]
00002248  83c420            add        esp, 0x20
0000224b  85c0              test       eax, eax
0000224d  0f8574fcffff      jne        0x1ec7
00002253  8b83bc901000      mov        eax, dword ptr [ebx + 0x1090bc]
00002259  8b08              mov        ecx, dword ptr [eax]
0000225b  83ec08            sub        esp, 8
0000225e  6a00              push       0
00002260  50                push       eax
00002261  ff11              call       dword ptr [ecx]
00002263  83c410            add        esp, 0x10
00002266  85c0              test       eax, eax
00002268  0f8559fcffff      jne        0x1ec7
0000226e  8b83bc901000      mov        eax, dword ptr [ebx + 0x1090bc]
00002274  8b08              mov        ecx, dword ptr [eax]
00002276  8b93fcffffff      mov        edx, dword ptr [ebx - 4]
0000227c  83ec04            sub        esp, 4
0000227f  8db390901000      lea        esi, [ebx + 0x109090]
00002285  56                push       esi
00002286  ff32              push       dword ptr [edx]
00002288  50                push       eax
00002289  ff510c            call       dword ptr [ecx + 0xc]
0000228c  83c410            add        esp, 0x10
0000228f  85c0              test       eax, eax
00002291  0f8530fcffff      jne        0x1ec7
00002297  8b83bc901000      mov        eax, dword ptr [ebx + 0x1090bc]
0000229d  8b08              mov        ecx, dword ptr [eax]
0000229f  83ec04            sub        esp, 4
000022a2  8d9364900800      lea        edx, [ebx + 0x89064]
000022a8  52                push       edx
000022a9  ff742418          push       dword ptr [esp + 0x18]
000022ad  50                push       eax
000022ae  ff510c            call       dword ptr [ecx + 0xc]
000022b1  83c410            add        esp, 0x10
000022b4  85c0              test       eax, eax
000022b6  0f850bfcffff      jne        0x1ec7
000022bc  c7835c90000002000000  mov        dword ptr [ebx + 0x905c], 2
000022c6  c7839490100000000000  mov        dword ptr [ebx + 0x109094], 0
000022d0  e992fcffff        jmp        0x1f67
000022d5  8b833c100000      mov        eax, dword ptr [ebx + 0x103c]
000022db  89442414          mov        dword ptr [esp + 0x14], eax
000022df  e9ecfaffff        jmp        0x1dd0
000022e4  e847090000        call       0x2c30
