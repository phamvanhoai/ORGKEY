# ELF: analysis\dic-decoded.bin; .text=756 bytes; entry=0x550
00000550  31ed             xor      ebp, ebp
00000552  89e0             mov      eax, esp
00000554  83e4f0           and      esp, 0xfffffff0
00000557  83ec0c           sub      esp, 0xc
0000055a  50               push     eax
0000055b  e800000000       call     0x560
00000560  53               push     ebx
00000561  83ec18           sub      esp, 0x18
00000564  e800000000       call     0x569
00000569  5b               pop      ebx
0000056a  81c35f140000     add      ebx, 0x145f
00000570  8b442420         mov      eax, dword ptr [esp + 0x20]
00000574  8b8bf0ffffff     mov      ecx, dword ptr [ebx - 0x10]
0000057a  894c2408         mov      dword ptr [esp + 8], ecx
0000057e  8b8bf4ffffff     mov      ecx, dword ptr [ebx - 0xc]
00000584  894c240c         mov      dword ptr [esp + 0xc], ecx
00000588  8b8bf8ffffff     mov      ecx, dword ptr [ebx - 8]
0000058e  894c2410         mov      dword ptr [esp + 0x10], ecx
00000592  8d4c2408         lea      ecx, [esp + 8]
00000596  8b93fcffffff     mov      edx, dword ptr [ebx - 4]
0000059c  51               push     ecx
0000059d  52               push     edx
0000059e  6a00             push     0
000005a0  50               push     eax
000005a1  e8ba020000       call     0x860
000005a6  90               nop      
000005a7  90               nop      
000005a8  90               nop      
000005a9  90               nop      
000005aa  90               nop      
000005ab  90               nop      
000005ac  90               nop      
000005ad  90               nop      
000005ae  90               nop      
000005af  90               nop      
000005b0  53               push     ebx
000005b1  83ec08           sub      esp, 8
000005b4  e800000000       call     0x5b9
000005b9  5b               pop      ebx
000005ba  81c30f140000     add      ebx, 0x140f
000005c0  8b442410         mov      eax, dword ptr [esp + 0x10]
000005c4  85c0             test     eax, eax
000005c6  7402             je       0x5ca
000005c8  ffd0             call     eax
000005ca  83c408           add      esp, 8
000005cd  5b               pop      ebx
000005ce  c3               ret      
000005cf  90               nop      
000005d0  53               push     ebx
000005d1  83ec08           sub      esp, 8
000005d4  e800000000       call     0x5d9
000005d9  5b               pop      ebx
000005da  81c3ef130000     add      ebx, 0x13ef
000005e0  83ec04           sub      esp, 4
000005e3  8d831c100000     lea      eax, [ebx + 0x101c]
000005e9  8d8be8ebffff     lea      ecx, [ebx - 0x1418]
000005ef  50               push     eax
000005f0  ff742418         push     dword ptr [esp + 0x18]
000005f4  51               push     ecx
000005f5  e876020000       call     0x870
000005fa  83c418           add      esp, 0x18
000005fd  5b               pop      ebx
000005fe  c3               ret      
000005ff  cc               int3     
00000600  55               push     ebp
00000601  89e5             mov      ebp, esp
00000603  53               push     ebx
00000604  57               push     edi
00000605  56               push     esi
00000606  83e4f0           and      esp, 0xfffffff0
00000609  81ecc0000000     sub      esp, 0xc0
0000060f  e800000000       call     0x614
00000614  5b               pop      ebx
00000615  81c3b4130000     add      ebx, 0x13b4
0000061b  65a114000000     mov      eax, dword ptr gs:[0x14]
00000621  898424b8000000   mov      dword ptr [esp + 0xb8], eax
00000628  8d742420         lea      esi, [esp + 0x20]
0000062c  31c9             xor      ecx, ecx
0000062e  89f2             mov      edx, esi
00000630  e86b010000       call     0x7a0
00000635  b847656e75       mov      eax, 0x756e6547
0000063a  334604           xor      eax, dword ptr [esi + 4]
0000063d  b96e74656c       mov      ecx, 0x6c65746e
00000642  334e08           xor      ecx, dword ptr [esi + 8]
00000645  09c1             or       ecx, eax
00000647  b8696e6549       mov      eax, 0x49656e69
0000064c  33460c           xor      eax, dword ptr [esi + 0xc]
0000064f  09c8             or       eax, ecx
00000651  b800000000       mov      eax, 0
00000656  0f94c0           sete     al
00000659  c1e018           shl      eax, 0x18
0000065c  8944240c         mov      dword ptr [esp + 0xc], eax
00000660  31c9             xor      ecx, ecx
00000662  41               inc      ecx
00000663  89f2             mov      edx, esi
00000665  e836010000       call     0x7a0
0000066a  8b7e04           mov      edi, dword ptr [esi + 4]
0000066d  8b4608           mov      eax, dword ptr [esi + 8]
00000670  89c1             mov      ecx, eax
00000672  83e108           and      ecx, 8
00000675  c1e117           shl      ecx, 0x17
00000678  034c240c         add      ecx, dword ptr [esp + 0xc]
0000067c  c1e806           shr      eax, 6
0000067f  2500000002       and      eax, 0x2000000
00000684  09c8             or       eax, ecx
00000686  c1ef08           shr      edi, 8
00000689  81e700ff0000     and      edi, 0xff00
0000068f  09c7             or       edi, eax
00000691  b90a000000       mov      ecx, 0xa
00000696  89f2             mov      edx, esi
00000698  e803010000       call     0x7a0
0000069d  8b0e             mov      ecx, dword ptr [esi]
0000069f  8b460c           mov      eax, dword ptr [esi + 0xc]
000006a2  89442410         mov      dword ptr [esp + 0x10], eax
000006a6  89c8             mov      eax, ecx
000006a8  25000000ff       and      eax, 0xff000000
000006ad  8944241c         mov      dword ptr [esp + 0x1c], eax
000006b1  3d00000007       cmp      eax, 0x7000000
000006b6  894c2418         mov      dword ptr [esp + 0x18], ecx
000006ba  0fb6c5           movzx    eax, ch
000006bd  b900000000       mov      ecx, 0
000006c2  0f45c1           cmovne   eax, ecx
000006c5  897c240c         mov      dword ptr [esp + 0xc], edi
000006c9  09f8             or       eax, edi
000006cb  89442414         mov      dword ptr [esp + 0x14], eax
000006cf  0f57c0           xorps    xmm0, xmm0
000006d2  8d7c2470         lea      edi, [esp + 0x70]
000006d6  0f294730         movaps   xmmword ptr [edi + 0x30], xmm0
000006da  0f294720         movaps   xmmword ptr [edi + 0x20], xmm0
000006de  0f294710         movaps   xmmword ptr [edi + 0x10], xmm0
000006e2  0f2907           movaps   xmmword ptr [edi], xmm0
000006e5  c6474000         mov      byte ptr [edi + 0x40], 0
000006e9  be02000080       mov      esi, 0x80000002
000006ee  89f1             mov      ecx, esi
000006f0  8d542420         lea      edx, [esp + 0x20]
000006f4  e8a7000000       call     0x7a0
000006f9  0f28442420       movaps   xmm0, xmmword ptr [esp + 0x20]
000006fe  0f1107           movups   xmmword ptr [edi], xmm0
00000701  46               inc      esi
00000702  83c710           add      edi, 0x10
00000705  81fe05000080     cmp      esi, 0x80000005
0000070b  75e1             jne      0x6ee
0000070d  c68424b000000000 mov      byte ptr [esp + 0xb0], 0
00000715  0f57c0           xorps    xmm0, xmm0
00000718  0f29442460       movaps   xmmword ptr [esp + 0x60], xmm0
0000071d  0f29442450       movaps   xmmword ptr [esp + 0x50], xmm0
00000722  0f29442440       movaps   xmmword ptr [esp + 0x40], xmm0
00000727  0f29442430       movaps   xmmword ptr [esp + 0x30], xmm0
0000072c  31c0             xor      eax, eax
0000072e  8d8b08eaffff     lea      ecx, [ebx - 0x15f8]
00000734  8a11             mov      dl, byte ptr [ecx]
00000736  41               inc      ecx
00000737  80f259           xor      dl, 0x59
0000073a  88540430         mov      byte ptr [esp + eax + 0x30], dl
0000073e  40               inc      eax
0000073f  83f80f           cmp      eax, 0xf
00000742  75f0             jne      0x734
00000744  f64424101f       test     byte ptr [esp + 0x10], 0x1f
00000749  8b74240c         mov      esi, dword ptr [esp + 0xc]
0000074d  8b4c2414         mov      ecx, dword ptr [esp + 0x14]
00000751  0f45f1           cmovne   esi, ecx
00000754  f6442418fe       test     byte ptr [esp + 0x18], 0xfe
00000759  0f44f1           cmove    esi, ecx
0000075c  817c241c00000007 cmp      dword ptr [esp + 0x1c], 0x7000000
00000764  0f45f1           cmovne   esi, ecx
00000767  c644043000       mov      byte ptr [esp + eax + 0x30], 0
0000076c  83ec04           sub      esp, 4
0000076f  8d442474         lea      eax, [esp + 0x74]
00000773  8d4c2434         lea      ecx, [esp + 0x34]
00000777  50               push     eax
00000778  56               push     esi
00000779  51               push     ecx
0000077a  e801010000       call     0x880
0000077f  83c410           add      esp, 0x10
00000782  65a114000000     mov      eax, dword ptr gs:[0x14]
00000788  3b8424b8000000   cmp      eax, dword ptr [esp + 0xb8]
0000078f  750a             jne      0x79b
00000791  89f0             mov      eax, esi
00000793  8d65f4           lea      esp, [ebp - 0xc]
00000796  5e               pop      esi
00000797  5f               pop      edi
00000798  5b               pop      ebx
00000799  5d               pop      ebp
0000079a  c3               ret      
0000079b  e8f0000000       call     0x890
000007a0  55               push     ebp
000007a1  89e5             mov      ebp, esp
000007a3  57               push     edi
000007a4  56               push     esi
000007a5  83e4fc           and      esp, 0xfffffffc
000007a8  83ec10           sub      esp, 0x10
000007ab  89d6             mov      esi, edx
000007ad  89c8             mov      eax, ecx
000007af  8b4c240c         mov      ecx, dword ptr [esp + 0xc]
000007b3  0fb6c9           movzx    ecx, cl
000007b6  894c2408         mov      dword ptr [esp + 8], ecx
000007ba  8b4c2408         mov      ecx, dword ptr [esp + 8]
000007be  0faf4c2408       imul     ecx, dword ptr [esp + 8]
000007c3  69c9b76ddbb6     imul     ecx, ecx, 0xb6db6db7
000007c9  bab76ddbb6       mov      edx, 0xb6db6db7
000007ce  01ca             add      edx, ecx
000007d0  81fa24499224     cmp      edx, 0x24924924
000007d6  7711             ja       0x7e9
000007d8  43               inc      ebx
000007d9  2e56             push     esi
000007db  3d32303139       cmp      eax, 0x39313032
000007e0  3032             xor      byte ptr [edx], dh
000007e2  3132             xor      dword ptr [edx], esi
000007e4  3133             xor      dword ptr [ebx], esi
000007e6  3339             xor      edi, dword ptr [ecx]
000007e8  e8530fa289       call     0x89a21740
000007ed  df5b89           fistp    word ptr [ebx - 0x77]
000007f0  06               push     es
000007f1  897e04           mov      dword ptr [esi + 4], edi
000007f4  894e08           mov      dword ptr [esi + 8], ecx
000007f7  89560c           mov      dword ptr [esi + 0xc], edx
000007fa  8d65f8           lea      esp, [ebp - 8]
000007fd  5e               pop      esi
000007fe  5f               pop      edi
000007ff  5d               pop      ebp
00000800  c3               ret      
00000801  9c               pushfd   
00000802  58               pop      eax
00000803  c3               ret      
00000804  55               push     ebp
00000805  89e5             mov      ebp, esp
00000807  53               push     ebx
00000808  83e4f0           and      esp, 0xfffffff0
0000080b  83ec20           sub      esp, 0x20
0000080e  e800000000       call     0x813
00000813  5b               pop      ebx
00000814  81c3b5110000     add      ebx, 0x11b5
0000081a  e8e2ffffff       call     0x801
0000081f  0fbae008         bt       eax, 8
00000823  7210             jb       0x835
00000825  80bb2010000000   cmp      byte ptr [ebx + 0x1020], 0
0000082c  750e             jne      0x83c
0000082e  e8cdfdffff       call     0x600
00000833  eb09             jmp      0x83e
00000835  c6832010000001   mov      byte ptr [ebx + 0x1020], 1
0000083c  31c0             xor      eax, eax
0000083e  8d65fc           lea      esp, [ebp - 4]
00000841  5b               pop      ebx
00000842  5d               pop      ebp
00000843  c3               ret      
