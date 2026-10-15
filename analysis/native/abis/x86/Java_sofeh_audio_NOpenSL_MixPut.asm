# APK member: lib/x86/libnopensl.so
# Library SHA-256: 81e6220973b454100123e9259f13717edc34b683f704a7e2f342ff6424ed7706
# Function: Java_sofeh_audio_NOpenSL_MixPut; address=0xf50; bytes=944
# Decoder mode: default; decoded_bytes=944
00000f50  55                push       ebp
00000f51  89e5              mov        ebp, esp
00000f53  53                push       ebx
00000f54  57                push       edi
00000f55  56                push       esi
00000f56  83e4f0            and        esp, 0xfffffff0
00000f59  81ec60000100      sub        esp, 0x10060
00000f5f  e800000000        call       0xf64
00000f64  5b                pop        ebx
00000f65  81c3382e0000      add        ebx, 0x2e38
00000f6b  8b5510            mov        edx, dword ptr [ebp + 0x10]
00000f6e  65a114000000      mov        eax, dword ptr gs:[0x14]
00000f74  89842458000100    mov        dword ptr [esp + 0x10058], eax
00000f7b  85d2              test       edx, edx
00000f7d  0f94c0            sete       al
00000f80  837d1400          cmp        dword ptr [ebp + 0x14], 0
00000f84  0f94c1            sete       cl
00000f87  08c1              or         cl, al
00000f89  0f8555030000      jne        0x12e4
00000f8f  891c24            mov        dword ptr [esp], ebx
00000f92  8b7d18            mov        edi, dword ptr [ebp + 0x18]
00000f95  8b4d08            mov        ecx, dword ptr [ebp + 8]
00000f98  8b01              mov        eax, dword ptr [ecx]
00000f9a  83ec08            sub        esp, 8
00000f9d  8b5c2408          mov        ebx, dword ptr [esp + 8]
00000fa1  52                push       edx
00000fa2  51                push       ecx
00000fa3  ff90ac020000      call       dword ptr [eax + 0x2ac]
00000fa9  83c410            add        esp, 0x10
00000fac  89c6              mov        esi, eax
00000fae  8b4d08            mov        ecx, dword ptr [ebp + 8]
00000fb1  8b01              mov        eax, dword ptr [ecx]
00000fb3  83ec08            sub        esp, 8
00000fb6  8b5c2408          mov        ebx, dword ptr [esp + 8]
00000fba  ff7514            push       dword ptr [ebp + 0x14]
00000fbd  51                push       ecx
00000fbe  ff90ac020000      call       dword ptr [eax + 0x2ac]
00000fc4  83c410            add        esp, 0x10
00000fc7  39fe              cmp        esi, edi
00000fc9  0f4cfe            cmovl      edi, esi
00000fcc  89f9              mov        ecx, edi
00000fce  c1e91f            shr        ecx, 0x1f
00000fd1  01f9              add        ecx, edi
00000fd3  d1f9              sar        ecx, 1
00000fd5  8d3400            lea        esi, [eax + eax]
00000fd8  39c1              cmp        ecx, eax
00000fda  0f4ef7            cmovle     esi, edi
00000fdd  8b1c24            mov        ebx, dword ptr [esp]
00000fe0  81fe00400000      cmp        esi, 0x4000
00000fe6  b900400000        mov        ecx, 0x4000
00000feb  0f4cce            cmovl      ecx, esi
00000fee  85f6              test       esi, esi
00000ff0  0f8eee020000      jle        0x12e4
00000ff6  8b5508            mov        edx, dword ptr [ebp + 8]
00000ff9  8b02              mov        eax, dword ptr [edx]
00000ffb  83ec0c            sub        esp, 0xc
00000ffe  8dbc2464800000    lea        edi, [esp + 0x8064]
00001005  57                push       edi
00001006  51                push       ecx
00001007  6a00              push       0
00001009  ff7510            push       dword ptr [ebp + 0x10]
0000100c  52                push       edx
0000100d  89cf              mov        edi, ecx
0000100f  ff9028030000      call       dword ptr [eax + 0x328]
00001015  83c420            add        esp, 0x20
00001018  8b5508            mov        edx, dword ptr [ebp + 8]
0000101b  8b02              mov        eax, dword ptr [edx]
0000101d  897c2404          mov        dword ptr [esp + 4], edi
00001021  d1ef              shr        edi, 1
00001023  83ec0c            sub        esp, 0xc
00001026  8d4c2464          lea        ecx, [esp + 0x64]
0000102a  51                push       ecx
0000102b  897c2418          mov        dword ptr [esp + 0x18], edi
0000102f  57                push       edi
00001030  6a00              push       0
00001032  ff7514            push       dword ptr [ebp + 0x14]
00001035  52                push       edx
00001036  ff9028030000      call       dword ptr [eax + 0x328]
0000103c  83c420            add        esp, 0x20
0000103f  83fe01            cmp        esi, 1
00001042  0f84b7010000      je         0x11ff
00001048  8b5d1c            mov        ebx, dword ptr [ebp + 0x1c]
0000104b  8b442408          mov        eax, dword ptr [esp + 8]
0000104f  83f801            cmp        eax, 1
00001052  83d000            adc        eax, 0
00001055  31ff              xor        edi, edi
00001057  83fe08            cmp        esi, 8
0000105a  8944240c          mov        dword ptr [esp + 0xc], eax
0000105e  0f8c51010000      jl         0x11b5
00001064  89c7              mov        edi, eax
00001066  81e7fc3f0000      and        edi, 0x3ffc
0000106c  660f6ec3          movd       xmm0, ebx
00001070  660f70c800        pshufd     xmm1, xmm0, 0
00001075  8b4d20            mov        ecx, dword ptr [ebp + 0x20]
00001078  660f6ec1          movd       xmm0, ecx
0000107c  660f70c000        pshufd     xmm0, xmm0, 0
00001081  01c0              add        eax, eax
00001083  25f87f0000        and        eax, 0x7ff8
00001088  31c9              xor        ecx, ecx
0000108a  660f7f4c2440      movdqa     xmmword ptr [esp + 0x40], xmm1
00001090  660f70c9f5        pshufd     xmm1, xmm1, 0xf5
00001095  660f7f4c2420      movdqa     xmmword ptr [esp + 0x20], xmm1
0000109b  8b1424            mov        edx, dword ptr [esp]
0000109e  660f6f9af4c9ffff  movdqa     xmm3, xmmword ptr [edx - 0x360c]
000010a6  660f7f442430      movdqa     xmmword ptr [esp + 0x30], xmm0
000010ac  660f70c0f5        pshufd     xmm0, xmm0, 0xf5
000010b1  660f7f442410      movdqa     xmmword ptr [esp + 0x10], xmm0
000010b7  660f6faa04caffff  movdqa     xmm5, xmmword ptr [edx - 0x35fc]
000010bf  90                nop        
000010c0  f30f7e440c58      movq       xmm0, qword ptr [esp + ecx + 0x58]
000010c6  660f61f8          punpcklwd  xmm7, xmm0
000010ca  660f72e710        psrad      xmm7, 0x10
000010cf  660f6f742440      movdqa     xmm6, xmmword ptr [esp + 0x40]
000010d5  660ff4f7          pmuludq    xmm6, xmm7
000010d9  660f70cee8        pshufd     xmm1, xmm6, 0xe8
000010de  660f70c7f5        pshufd     xmm0, xmm7, 0xf5
000010e3  660f6f542420      movdqa     xmm2, xmmword ptr [esp + 0x20]
000010e9  660ff4d0          pmuludq    xmm2, xmm0
000010ed  660f70e2e8        pshufd     xmm4, xmm2, 0xe8
000010f2  660f62cc          punpckldq  xmm1, xmm4
000010f6  660ff4f3          pmuludq    xmm6, xmm3
000010fa  660f70f6ed        pshufd     xmm6, xmm6, 0xed
000010ff  660ff4d3          pmuludq    xmm2, xmm3
00001103  660f70d2ed        pshufd     xmm2, xmm2, 0xed
00001108  660f62f2          punpckldq  xmm6, xmm2
0000110c  660fefd2          pxor       xmm2, xmm2
00001110  660f66d1          pcmpgtd    xmm2, xmm1
00001114  660fdbd3          pand       xmm2, xmm3
00001118  660ffaf2          psubd      xmm6, xmm2
0000111c  660f6fce          movdqa     xmm1, xmm6
00001120  660f72d11f        psrld      xmm1, 0x1f
00001125  660f72d605        psrld      xmm6, 5
0000112a  660ffef1          paddd      xmm6, xmm1
0000112e  660ff47c2430      pmuludq    xmm7, xmmword ptr [esp + 0x30]
00001134  660f70cfe8        pshufd     xmm1, xmm7, 0xe8
00001139  660ff4442410      pmuludq    xmm0, xmmword ptr [esp + 0x10]
0000113f  660f70d0e8        pshufd     xmm2, xmm0, 0xe8
00001144  660f62ca          punpckldq  xmm1, xmm2
00001148  660ff4fb          pmuludq    xmm7, xmm3
0000114c  660f70ffed        pshufd     xmm7, xmm7, 0xed
00001151  660ff4c3          pmuludq    xmm0, xmm3
00001155  660f70c0ed        pshufd     xmm0, xmm0, 0xed
0000115a  660f62f8          punpckldq  xmm7, xmm0
0000115e  660fefc0          pxor       xmm0, xmm0
00001162  660f66c1          pcmpgtd    xmm0, xmm1
00001166  660fdbc3          pand       xmm0, xmm3
0000116a  660ffaf8          psubd      xmm7, xmm0
0000116e  660f6fc7          movdqa     xmm0, xmm7
00001172  660f72d01f        psrld      xmm0, 0x1f
00001177  660f72d705        psrld      xmm7, 5
0000117c  660ffef8          paddd      xmm7, xmm0
00001180  660f3800fd        pshufb     xmm7, xmm5
00001185  660f3800f5        pshufb     xmm6, xmm5
0000118a  660f61f7          punpcklwd  xmm6, xmm7
0000118e  f30f6f844c58800000  movdqu     xmm0, xmmword ptr [esp + ecx*2 + 0x8058]
00001197  660ffdf0          paddw      xmm6, xmm0
0000119b  f30f7fb44c58800000  movdqu     xmmword ptr [esp + ecx*2 + 0x8058], xmm6
000011a4  83c108            add        ecx, 8
000011a7  39c8              cmp        eax, ecx
000011a9  0f8511ffffff      jne        0x10c0
000011af  397c240c          cmp        dword ptr [esp + 0xc], edi
000011b3  744a              je         0x11ff
000011b5  0fbf747c58        movsx      esi, word ptr [esp + edi*2 + 0x58]
000011ba  89f0              mov        eax, esi
000011bc  0fafc3            imul       eax, ebx
000011bf  b91f85eb51        mov        ecx, 0x51eb851f
000011c4  f7e9              imul       ecx
000011c6  89d1              mov        ecx, edx
000011c8  89d3              mov        ebx, edx
000011ca  c1eb1f            shr        ebx, 0x1f
000011cd  c1e905            shr        ecx, 5
000011d0  0faf7520          imul       esi, dword ptr [ebp + 0x20]
000011d4  89f0              mov        eax, esi
000011d6  ba1f85eb51        mov        edx, 0x51eb851f
000011db  f7ea              imul       edx
000011dd  01d9              add        ecx, ebx
000011df  8b5d1c            mov        ebx, dword ptr [ebp + 0x1c]
000011e2  66018cbc58800000  add        word ptr [esp + edi*4 + 0x8058], cx
000011ea  89d0              mov        eax, edx
000011ec  c1e81f            shr        eax, 0x1f
000011ef  c1ea05            shr        edx, 5
000011f2  01c2              add        edx, eax
000011f4  660194bc5a800000  add        word ptr [esp + edi*4 + 0x805a], dx
000011fc  47                inc        edi
000011fd  ebb0              jmp        0x11af
000011ff  8b742404          mov        esi, dword ptr [esp + 4]
00001203  8d0436            lea        eax, [esi + esi]
00001206  8b1c24            mov        ebx, dword ptr [esp]
00001209  8d8b40100000      lea        ecx, [ebx + 0x1040]
0000120f  6800800000        push       0x8000
00001214  50                push       eax
00001215  8d842460800000    lea        eax, [esp + 0x8060]
0000121c  50                push       eax
0000121d  51                push       ecx
0000121e  e81d1a0000        call       0x2c40
00001223  89f1              mov        ecx, esi
00001225  83c410            add        esp, 0x10
00001228  89b340900000      mov        dword ptr [ebx + 0x9040], esi
0000122e  39b344900000      cmp        dword ptr [ebx + 0x9044], esi
00001234  7c0a              jl         0x1240
00001236  c7834490000000000000  mov        dword ptr [ebx + 0x9044], 0
00001240  83bb6490080000    cmp        dword ptr [ebx + 0x89064], 0
00001247  8b7514            mov        esi, dword ptr [ebp + 0x14]
0000124a  8b7d08            mov        edi, dword ptr [ebp + 8]
0000124d  7478              je         0x12c7
0000124f  83bb5090000000    cmp        dword ptr [ebx + 0x9050], 0
00001256  786f              js         0x12c7
00001258  8b8350900000      mov        eax, dword ptr [ebx + 0x9050]
0000125e  80bb5890000000    cmp        byte ptr [ebx + 0x9058], 0
00001265  b910000000        mov        ecx, 0x10
0000126a  ba04000000        mov        edx, 4
0000126f  0f45d1            cmovne     edx, ecx
00001272  8b4c2404          mov        ecx, dword ptr [esp + 4]
00001276  39d0              cmp        eax, edx
00001278  7d4d              jge        0x12c7
0000127a  8b8350900000      mov        eax, dword ptr [ebx + 0x9050]
00001280  c1e00f            shl        eax, 0xf
00001283  01d8              add        eax, ebx
00001285  0568900800        add        eax, 0x89068
0000128a  8b4c2404          mov        ecx, dword ptr [esp + 4]
0000128e  81e1fe7f0000      and        ecx, 0x7ffe
00001294  6800800000        push       0x8000
00001299  51                push       ecx
0000129a  50                push       eax
0000129b  8d442464          lea        eax, [esp + 0x64]
0000129f  50                push       eax
000012a0  e89b190000        call       0x2c40
000012a5  83c410            add        esp, 0x10
000012a8  8b07              mov        eax, dword ptr [edi]
000012aa  83ec0c            sub        esp, 0xc
000012ad  8d4c2464          lea        ecx, [esp + 0x64]
000012b1  51                push       ecx
000012b2  ff742418          push       dword ptr [esp + 0x18]
000012b6  6a00              push       0
000012b8  56                push       esi
000012b9  57                push       edi
000012ba  ff9048030000      call       dword ptr [eax + 0x348]
000012c0  8b4c2424          mov        ecx, dword ptr [esp + 0x24]
000012c4  83c420            add        esp, 0x20
000012c7  8b07              mov        eax, dword ptr [edi]
000012c9  83ec0c            sub        esp, 0xc
000012cc  8d942464800000    lea        edx, [esp + 0x8064]
000012d3  52                push       edx
000012d4  51                push       ecx
000012d5  6a00              push       0
000012d7  ff7510            push       dword ptr [ebp + 0x10]
000012da  57                push       edi
000012db  ff9048030000      call       dword ptr [eax + 0x348]
000012e1  83c420            add        esp, 0x20
000012e4  65a114000000      mov        eax, dword ptr gs:[0x14]
000012ea  3b842458000100    cmp        eax, dword ptr [esp + 0x10058]
000012f1  7508              jne        0x12fb
000012f3  8d65f4            lea        esp, [ebp - 0xc]
000012f6  5e                pop        esi
000012f7  5f                pop        edi
000012f8  5b                pop        ebx
000012f9  5d                pop        ebp
000012fa  c3                ret        
000012fb  e830190000        call       0x2c30
