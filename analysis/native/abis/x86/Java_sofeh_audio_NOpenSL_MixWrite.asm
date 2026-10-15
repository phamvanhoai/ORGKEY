# APK member: lib/x86/libnopensl.so
# Library SHA-256: 81e6220973b454100123e9259f13717edc34b683f704a7e2f342ff6424ed7706
# Function: Java_sofeh_audio_NOpenSL_MixWrite; address=0x1300; bytes=1355
# Decoder mode: default; decoded_bytes=1355
00001300  55                push       ebp
00001301  89e5              mov        ebp, esp
00001303  53                push       ebx
00001304  57                push       edi
00001305  56                push       esi
00001306  83e4f0            and        esp, 0xfffffff0
00001309  81ec60000100      sub        esp, 0x10060
0000130f  e800000000        call       0x1314
00001314  5b                pop        ebx
00001315  81c3882a0000      add        ebx, 0x2a88
0000131b  65a114000000      mov        eax, dword ptr gs:[0x14]
00001321  89842458000100    mov        dword ptr [esp + 0x10058], eax
00001328  837d1000          cmp        dword ptr [ebp + 0x10], 0
0000132c  0f84fd040000      je         0x182f
00001332  837d1400          cmp        dword ptr [ebp + 0x14], 0
00001336  0f84f3040000      je         0x182f
0000133c  83bb4890000000    cmp        dword ptr [ebx + 0x9048], 0
00001343  0f84e6040000      je         0x182f
00001349  83bb4c90000000    cmp        dword ptr [ebx + 0x904c], 0
00001350  0f8ed9040000      jle        0x182f
00001356  8b7d18            mov        edi, dword ptr [ebp + 0x18]
00001359  8b4d08            mov        ecx, dword ptr [ebp + 8]
0000135c  8b01              mov        eax, dword ptr [ecx]
0000135e  83ec08            sub        esp, 8
00001361  ff7510            push       dword ptr [ebp + 0x10]
00001364  51                push       ecx
00001365  ff90ac020000      call       dword ptr [eax + 0x2ac]
0000136b  83c410            add        esp, 0x10
0000136e  89c6              mov        esi, eax
00001370  8b4d08            mov        ecx, dword ptr [ebp + 8]
00001373  8b01              mov        eax, dword ptr [ecx]
00001375  83ec08            sub        esp, 8
00001378  ff7514            push       dword ptr [ebp + 0x14]
0000137b  51                push       ecx
0000137c  ff90ac020000      call       dword ptr [eax + 0x2ac]
00001382  83c410            add        esp, 0x10
00001385  39fe              cmp        esi, edi
00001387  0f4cfe            cmovl      edi, esi
0000138a  89f9              mov        ecx, edi
0000138c  c1e91f            shr        ecx, 0x1f
0000138f  01f9              add        ecx, edi
00001391  d1f9              sar        ecx, 1
00001393  8d3400            lea        esi, [eax + eax]
00001396  39c1              cmp        ecx, eax
00001398  0f4ef7            cmovle     esi, edi
0000139b  81fe00400000      cmp        esi, 0x4000
000013a1  b800400000        mov        eax, 0x4000
000013a6  0f4cc6            cmovl      eax, esi
000013a9  85f6              test       esi, esi
000013ab  0f8e7e040000      jle        0x182f
000013b1  89c7              mov        edi, eax
000013b3  8b5508            mov        edx, dword ptr [ebp + 8]
000013b6  8b02              mov        eax, dword ptr [edx]
000013b8  83ec0c            sub        esp, 0xc
000013bb  8d8c2464800000    lea        ecx, [esp + 0x8064]
000013c2  51                push       ecx
000013c3  57                push       edi
000013c4  6a00              push       0
000013c6  ff7510            push       dword ptr [ebp + 0x10]
000013c9  52                push       edx
000013ca  ff9028030000      call       dword ptr [eax + 0x328]
000013d0  83c420            add        esp, 0x20
000013d3  8b5508            mov        edx, dword ptr [ebp + 8]
000013d6  8b02              mov        eax, dword ptr [edx]
000013d8  897c240c          mov        dword ptr [esp + 0xc], edi
000013dc  d1ef              shr        edi, 1
000013de  83ec0c            sub        esp, 0xc
000013e1  8d4c2464          lea        ecx, [esp + 0x64]
000013e5  51                push       ecx
000013e6  897c2418          mov        dword ptr [esp + 0x18], edi
000013ea  57                push       edi
000013eb  6a00              push       0
000013ed  ff7514            push       dword ptr [ebp + 0x14]
000013f0  52                push       edx
000013f1  ff9028030000      call       dword ptr [eax + 0x328]
000013f7  83c420            add        esp, 0x20
000013fa  83fe01            cmp        esi, 1
000013fd  0f84d7010000      je         0x15da
00001403  8b7d1c            mov        edi, dword ptr [ebp + 0x1c]
00001406  8b442408          mov        eax, dword ptr [esp + 8]
0000140a  83f801            cmp        eax, 1
0000140d  83d000            adc        eax, 0
00001410  31c9              xor        ecx, ecx
00001412  83fe08            cmp        esi, 8
00001415  89442404          mov        dword ptr [esp + 4], eax
00001419  0f8c61010000      jl         0x1580
0000141f  89c1              mov        ecx, eax
00001421  81e1fc3f0000      and        ecx, 0x3ffc
00001427  89ce              mov        esi, ecx
00001429  660f6ec7          movd       xmm0, edi
0000142d  660f70c800        pshufd     xmm1, xmm0, 0
00001432  8b4d20            mov        ecx, dword ptr [ebp + 0x20]
00001435  660f6ec1          movd       xmm0, ecx
00001439  660f70c000        pshufd     xmm0, xmm0, 0
0000143e  01c0              add        eax, eax
00001440  25f87f0000        and        eax, 0x7ff8
00001445  31c9              xor        ecx, ecx
00001447  660f7f4c2410      movdqa     xmmword ptr [esp + 0x10], xmm1
0000144d  660f70c9f5        pshufd     xmm1, xmm1, 0xf5
00001452  660f7f4c2420      movdqa     xmmword ptr [esp + 0x20], xmm1
00001458  660f6f9bf4c9ffff  movdqa     xmm3, xmmword ptr [ebx - 0x360c]
00001460  660f7f442430      movdqa     xmmword ptr [esp + 0x30], xmm0
00001466  660f70c0f5        pshufd     xmm0, xmm0, 0xf5
0000146b  660f7f442440      movdqa     xmmword ptr [esp + 0x40], xmm0
00001471  660f6fab04caffff  movdqa     xmm5, xmmword ptr [ebx - 0x35fc]
00001479  90                nop        
0000147a  90                nop        
0000147b  90                nop        
0000147c  90                nop        
0000147d  90                nop        
0000147e  90                nop        
0000147f  90                nop        
00001480  f30f7e440c58      movq       xmm0, qword ptr [esp + ecx + 0x58]
00001486  660f61f8          punpcklwd  xmm7, xmm0
0000148a  660f72e710        psrad      xmm7, 0x10
0000148f  660f6f742410      movdqa     xmm6, xmmword ptr [esp + 0x10]
00001495  660ff4f7          pmuludq    xmm6, xmm7
00001499  660f70cee8        pshufd     xmm1, xmm6, 0xe8
0000149e  660f70c7f5        pshufd     xmm0, xmm7, 0xf5
000014a3  660f6f542420      movdqa     xmm2, xmmword ptr [esp + 0x20]
000014a9  660ff4d0          pmuludq    xmm2, xmm0
000014ad  660f70e2e8        pshufd     xmm4, xmm2, 0xe8
000014b2  660f62cc          punpckldq  xmm1, xmm4
000014b6  660ff4f3          pmuludq    xmm6, xmm3
000014ba  660f70f6ed        pshufd     xmm6, xmm6, 0xed
000014bf  660ff4d3          pmuludq    xmm2, xmm3
000014c3  660f70d2ed        pshufd     xmm2, xmm2, 0xed
000014c8  660f62f2          punpckldq  xmm6, xmm2
000014cc  660fefd2          pxor       xmm2, xmm2
000014d0  660f66d1          pcmpgtd    xmm2, xmm1
000014d4  660fdbd3          pand       xmm2, xmm3
000014d8  660ffaf2          psubd      xmm6, xmm2
000014dc  660f6fce          movdqa     xmm1, xmm6
000014e0  660f72d11f        psrld      xmm1, 0x1f
000014e5  660f72d605        psrld      xmm6, 5
000014ea  660ffef1          paddd      xmm6, xmm1
000014ee  660ff47c2430      pmuludq    xmm7, xmmword ptr [esp + 0x30]
000014f4  660f70cfe8        pshufd     xmm1, xmm7, 0xe8
000014f9  660ff4442440      pmuludq    xmm0, xmmword ptr [esp + 0x40]
000014ff  660f70d0e8        pshufd     xmm2, xmm0, 0xe8
00001504  660f62ca          punpckldq  xmm1, xmm2
00001508  660ff4fb          pmuludq    xmm7, xmm3
0000150c  660f70ffed        pshufd     xmm7, xmm7, 0xed
00001511  660ff4c3          pmuludq    xmm0, xmm3
00001515  660f70c0ed        pshufd     xmm0, xmm0, 0xed
0000151a  660f62f8          punpckldq  xmm7, xmm0
0000151e  660fefc0          pxor       xmm0, xmm0
00001522  660f66c1          pcmpgtd    xmm0, xmm1
00001526  660fdbc3          pand       xmm0, xmm3
0000152a  660ffaf8          psubd      xmm7, xmm0
0000152e  660f6fc7          movdqa     xmm0, xmm7
00001532  660f72d01f        psrld      xmm0, 0x1f
00001537  660f72d705        psrld      xmm7, 5
0000153c  660ffef8          paddd      xmm7, xmm0
00001540  660f3800fd        pshufb     xmm7, xmm5
00001545  660f3800f5        pshufb     xmm6, xmm5
0000154a  660f61f7          punpcklwd  xmm6, xmm7
0000154e  f30f6f844c58800000  movdqu     xmm0, xmmword ptr [esp + ecx*2 + 0x8058]
00001557  660ffdf0          paddw      xmm6, xmm0
0000155b  f30f7fb44c58800000  movdqu     xmmword ptr [esp + ecx*2 + 0x8058], xmm6
00001564  83c108            add        ecx, 8
00001567  39c8              cmp        eax, ecx
00001569  0f8511ffffff      jne        0x1480
0000156f  89f1              mov        ecx, esi
00001571  39742404          cmp        dword ptr [esp + 4], esi
00001575  7463              je         0x15da
00001577  90                nop        
00001578  90                nop        
00001579  90                nop        
0000157a  90                nop        
0000157b  90                nop        
0000157c  90                nop        
0000157d  90                nop        
0000157e  90                nop        
0000157f  90                nop        
00001580  894c2410          mov        dword ptr [esp + 0x10], ecx
00001584  0fbf744c58        movsx      esi, word ptr [esp + ecx*2 + 0x58]
00001589  89f0              mov        eax, esi
0000158b  0fafc7            imul       eax, edi
0000158e  b91f85eb51        mov        ecx, 0x51eb851f
00001593  f7e9              imul       ecx
00001595  89d1              mov        ecx, edx
00001597  89d7              mov        edi, edx
00001599  c1ef1f            shr        edi, 0x1f
0000159c  c1e905            shr        ecx, 5
0000159f  0faf7520          imul       esi, dword ptr [ebp + 0x20]
000015a3  89f0              mov        eax, esi
000015a5  ba1f85eb51        mov        edx, 0x51eb851f
000015aa  f7ea              imul       edx
000015ac  01f9              add        ecx, edi
000015ae  8b7d1c            mov        edi, dword ptr [ebp + 0x1c]
000015b1  8b442410          mov        eax, dword ptr [esp + 0x10]
000015b5  66018c8458800000  add        word ptr [esp + eax*4 + 0x8058], cx
000015bd  8b4c2410          mov        ecx, dword ptr [esp + 0x10]
000015c1  89d0              mov        eax, edx
000015c3  c1e81f            shr        eax, 0x1f
000015c6  c1ea05            shr        edx, 5
000015c9  01c2              add        edx, eax
000015cb  6601948c5a800000  add        word ptr [esp + ecx*4 + 0x805a], dx
000015d3  41                inc        ecx
000015d4  394c2404          cmp        dword ptr [esp + 4], ecx
000015d8  75a6              jne        0x1580
000015da  be10000000        mov        esi, 0x10
000015df  90                nop        
000015e0  8b8350900000      mov        eax, dword ptr [ebx + 0x9050]
000015e6  3b8354900000      cmp        eax, dword ptr [ebx + 0x9054]
000015ec  7512              jne        0x1600
000015ee  83bb4890000000    cmp        dword ptr [ebx + 0x9048], 0
000015f5  752c              jne        0x1623
000015f7  e9d2010000        jmp        0x17ce
000015fc  90                nop        
000015fd  90                nop        
000015fe  90                nop        
000015ff  90                nop        
00001600  8b8b50900000      mov        ecx, dword ptr [ebx + 0x9050]
00001606  80bb5890000000    cmp        byte ptr [ebx + 0x9058], 0
0000160d  ba04000000        mov        edx, 4
00001612  0f45d6            cmovne     edx, esi
00001615  8b8348900000      mov        eax, dword ptr [ebx + 0x9048]
0000161b  39d1              cmp        ecx, edx
0000161d  7c2f              jl         0x164e
0000161f  85c0              test       eax, eax
00001621  742b              je         0x164e
00001623  8b835c900000      mov        eax, dword ptr [ebx + 0x905c]
00001629  83f802            cmp        eax, 2
0000162c  750f              jne        0x163d
0000162e  83ec0c            sub        esp, 0xc
00001631  6a0a              push       0xa
00001633  e8d8150000        call       0x2c10
00001638  83c410            add        esp, 0x10
0000163b  eba3              jmp        0x15e0
0000163d  8bbb54900000      mov        edi, dword ptr [ebx + 0x9054]
00001643  8b9360900000      mov        edx, dword ptr [ebx + 0x9060]
00001649  8b7508            mov        esi, dword ptr [ebp + 8]
0000164c  eb17              jmp        0x1665
0000164e  8bbb54900000      mov        edi, dword ptr [ebx + 0x9054]
00001654  8b9360900000      mov        edx, dword ptr [ebx + 0x9060]
0000165a  85c0              test       eax, eax
0000165c  8b7508            mov        esi, dword ptr [ebp + 8]
0000165f  0f8481010000      je         0x17e6
00001665  c744241000000000  mov        dword ptr [esp + 0x10], 0
0000166d  8b4c240c          mov        ecx, dword ptr [esp + 0xc]
00001671  90                nop        
00001672  90                nop        
00001673  90                nop        
00001674  90                nop        
00001675  90                nop        
00001676  90                nop        
00001677  90                nop        
00001678  90                nop        
00001679  90                nop        
0000167a  90                nop        
0000167b  90                nop        
0000167c  90                nop        
0000167d  90                nop        
0000167e  90                nop        
0000167f  90                nop        
00001680  8b835c900000      mov        eax, dword ptr [ebx + 0x905c]
00001686  83f802            cmp        eax, 2
00001689  0f8557010000      jne        0x17e6
0000168f  8bb34c900000      mov        esi, dword ptr [ebx + 0x904c]
00001695  39f2              cmp        edx, esi
00001697  894c2404          mov        dword ptr [esp + 4], ecx
0000169b  0f8c7b000000      jl         0x171c
000016a1  47                inc        edi
000016a2  80bb5890000000    cmp        byte ptr [ebx + 0x9058], 0
000016a9  b804000000        mov        eax, 4
000016ae  be10000000        mov        esi, 0x10
000016b3  0f45c6            cmovne     eax, esi
000016b6  39c7              cmp        edi, eax
000016b8  b800000000        mov        eax, 0
000016bd  0f4df8            cmovge     edi, eax
000016c0  8b8350900000      mov        eax, dword ptr [ebx + 0x9050]
000016c6  39f8              cmp        eax, edi
000016c8  7419              je         0x16e3
000016ca  8b8350900000      mov        eax, dword ptr [ebx + 0x9050]
000016d0  80bb5890000000    cmp        byte ptr [ebx + 0x9058], 0
000016d7  b904000000        mov        ecx, 4
000016dc  0f45ce            cmovne     ecx, esi
000016df  39c8              cmp        eax, ecx
000016e1  7c2d              jl         0x1710
000016e3  83bb4890000000    cmp        dword ptr [ebx + 0x9048], 0
000016ea  7424              je         0x1710
000016ec  8b835c900000      mov        eax, dword ptr [ebx + 0x905c]
000016f2  83f802            cmp        eax, 2
000016f5  7519              jne        0x1710
000016f7  83ec0c            sub        esp, 0xc
000016fa  6a0a              push       0xa
000016fc  e80f150000        call       0x2c10
00001701  83c410            add        esp, 0x10
00001704  ebba              jmp        0x16c0
00001706  90                nop        
00001707  90                nop        
00001708  90                nop        
00001709  90                nop        
0000170a  90                nop        
0000170b  90                nop        
0000170c  90                nop        
0000170d  90                nop        
0000170e  90                nop        
0000170f  90                nop        
00001710  31d2              xor        edx, edx
00001712  8bb34c900000      mov        esi, dword ptr [ebx + 0x904c]
00001718  8b4c2404          mov        ecx, dword ptr [esp + 4]
0000171c  29d6              sub        esi, edx
0000171e  39f1              cmp        ecx, esi
00001720  0f4cf1            cmovl      esi, ecx
00001723  897c2420          mov        dword ptr [esp + 0x20], edi
00001727  c1e70f            shl        edi, 0xf
0000172a  8d043b            lea        eax, [ebx + edi]
0000172d  0564900000        add        eax, 0x9064
00001732  89542430          mov        dword ptr [esp + 0x30], edx
00001736  8d0450            lea        eax, [eax + edx*2]
00001739  8b4c2410          mov        ecx, dword ptr [esp + 0x10]
0000173d  8d0c4c            lea        ecx, [esp + ecx*2]
00001740  81c158800000      add        ecx, 0x8058
00001746  8d1436            lea        edx, [esi + esi]
00001749  83ec04            sub        esp, 4
0000174c  52                push       edx
0000174d  51                push       ecx
0000174e  50                push       eax
0000174f  e8fc140000        call       0x2c50
00001754  83c410            add        esp, 0x10
00001757  83fe02            cmp        esi, 2
0000175a  7c4a              jl         0x17a6
0000175c  83bb6490080000    cmp        dword ptr [ebx + 0x89064], 0
00001763  7441              je         0x17a6
00001765  89f0              mov        eax, esi
00001767  25feffff7f        and        eax, 0x7ffffffe
0000176c  8b542430          mov        edx, dword ptr [esp + 0x30]
00001770  89d1              mov        ecx, edx
00001772  c1e91f            shr        ecx, 0x1f
00001775  01d1              add        ecx, edx
00001777  83e1fe            and        ecx, 0xfffffffe
0000177a  8d143b            lea        edx, [ebx + edi]
0000177d  81c268900800      add        edx, 0x89068
00001783  01ca              add        edx, ecx
00001785  8b7c2410          mov        edi, dword ptr [esp + 0x10]
00001789  89f9              mov        ecx, edi
0000178b  c1e91f            shr        ecx, 0x1f
0000178e  01f9              add        ecx, edi
00001790  83e1fe            and        ecx, 0xfffffffe
00001793  01e1              add        ecx, esp
00001795  83c158            add        ecx, 0x58
00001798  83ec04            sub        esp, 4
0000179b  50                push       eax
0000179c  52                push       edx
0000179d  51                push       ecx
0000179e  e8ad140000        call       0x2c50
000017a3  83c410            add        esp, 0x10
000017a6  8b542430          mov        edx, dword ptr [esp + 0x30]
000017aa  01f2              add        edx, esi
000017ac  8b4c2404          mov        ecx, dword ptr [esp + 4]
000017b0  29f1              sub        ecx, esi
000017b2  7e2b              jle        0x17df
000017b4  01742410          add        dword ptr [esp + 0x10], esi
000017b8  83bb4890000000    cmp        dword ptr [ebx + 0x9048], 0
000017bf  8b7508            mov        esi, dword ptr [ebp + 8]
000017c2  8b7c2420          mov        edi, dword ptr [esp + 0x20]
000017c6  0f85b4feffff      jne        0x1680
000017cc  eb18              jmp        0x17e6
000017ce  8bbb54900000      mov        edi, dword ptr [ebx + 0x9054]
000017d4  8b9360900000      mov        edx, dword ptr [ebx + 0x9060]
000017da  8b7508            mov        esi, dword ptr [ebp + 8]
000017dd  eb07              jmp        0x17e6
000017df  8b7508            mov        esi, dword ptr [ebp + 8]
000017e2  8b7c2420          mov        edi, dword ptr [esp + 0x20]
000017e6  89bb54900000      mov        dword ptr [ebx + 0x9054], edi
000017ec  899360900000      mov        dword ptr [ebx + 0x9060], edx
000017f2  8b06              mov        eax, dword ptr [esi]
000017f4  83ec0c            sub        esp, 0xc
000017f7  8d8c2464800000    lea        ecx, [esp + 0x8064]
000017fe  51                push       ecx
000017ff  ff74241c          push       dword ptr [esp + 0x1c]
00001803  6a00              push       0
00001805  ff7510            push       dword ptr [ebp + 0x10]
00001808  56                push       esi
00001809  ff9048030000      call       dword ptr [eax + 0x348]
0000180f  83c420            add        esp, 0x20
00001812  8b06              mov        eax, dword ptr [esi]
00001814  83ec0c            sub        esp, 0xc
00001817  8d4c2464          lea        ecx, [esp + 0x64]
0000181b  51                push       ecx
0000181c  ff742418          push       dword ptr [esp + 0x18]
00001820  6a00              push       0
00001822  ff7514            push       dword ptr [ebp + 0x14]
00001825  56                push       esi
00001826  ff9048030000      call       dword ptr [eax + 0x348]
0000182c  83c420            add        esp, 0x20
0000182f  65a114000000      mov        eax, dword ptr gs:[0x14]
00001835  3b842458000100    cmp        eax, dword ptr [esp + 0x10058]
0000183c  7508              jne        0x1846
0000183e  8d65f4            lea        esp, [ebp - 0xc]
00001841  5e                pop        esi
00001842  5f                pop        edi
00001843  5b                pop        ebx
00001844  5d                pop        ebp
00001845  c3                ret        
00001846  e8e5130000        call       0x2c30
