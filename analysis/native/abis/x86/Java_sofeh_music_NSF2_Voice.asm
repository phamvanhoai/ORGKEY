# APK member: lib/x86/libnsf2.so
# Library SHA-256: 452e13279ac07ceb7c0366072a52f65dedf77b845331b27d58ba4be9865cb7a5
# Function: Java_sofeh_music_NSF2_Voice; address=0x9590; bytes=948
# Decoder mode: default; decoded_bytes=948
00009590  55                push       ebp
00009591  89e5              mov        ebp, esp
00009593  53                push       ebx
00009594  57                push       edi
00009595  56                push       esi
00009596  83e4f0            and        esp, 0xfffffff0
00009599  81ec90080000      sub        esp, 0x890
0000959f  e800000000        call       0x95a4
000095a4  59                pop        ecx
000095a5  81c1181d0000      add        ecx, 0x1d18
000095ab  8b7d14            mov        edi, dword ptr [ebp + 0x14]
000095ae  65a114000000      mov        eax, dword ptr gs:[0x14]
000095b4  89842488080000    mov        dword ptr [esp + 0x888], eax
000095bb  894c2404          mov        dword ptr [esp + 4], ecx
000095bf  8b8188100000      mov        eax, dword ptr [ecx + 0x1088]
000095c5  85c0              test       eax, eax
000095c7  0f94c1            sete       cl
000095ca  8d57e7            lea        edx, [edi - 0x19]
000095cd  83fa47            cmp        edx, 0x47
000095d0  0f93c2            setae      dl
000095d3  31f6              xor        esi, esi
000095d5  08ca              or         dl, cl
000095d7  0f8545030000      jne        0x9922
000095dd  83ec0c            sub        esp, 0xc
000095e0  8b5c2410          mov        ebx, dword ptr [esp + 0x10]
000095e4  50                push       eax
000095e5  e8f60a0000        call       0xa0e0
000095ea  83c410            add        esp, 0x10
000095ed  8b8388100000      mov        eax, dword ptr [ebx + 0x1088]
000095f3  c7402000000000    mov        dword ptr [eax + 0x20], 0
000095fa  f30f1083045fffff  movss      xmm0, dword ptr [ebx - 0xa0fc]
00009602  0f134024          movlps     qword ptr [eax + 0x24], xmm0
00009606  680000803f        push       0x3f800000
0000960b  57                push       edi
0000960c  ff7510            push       dword ptr [ebp + 0x10]
0000960f  50                push       eax
00009610  e80b0b0000        call       0xa120
00009615  83c410            add        esp, 0x10
00009618  8b8388100000      mov        eax, dword ptr [ebx + 0x1088]
0000961e  31c9              xor        ecx, ecx
00009620  31d2              xor        edx, edx
00009622  89442424          mov        dword ptr [esp + 0x24], eax
00009626  83782002          cmp        dword ptr [eax + 0x20], 2
0000962a  0f95c1            setne      cl
0000962d  894c2420          mov        dword ptr [esp + 0x20], ecx
00009631  0f94c2            sete       dl
00009634  c1e208            shl        edx, 8
00009637  81c200010000      add        edx, 0x100
0000963d  8954241c          mov        dword ptr [esp + 0x1c], edx
00009641  b900ee0200        mov        ecx, 0x2ee00
00009646  8db38c100000      lea        esi, [ebx + 0x108c]
0000964c  f30f1093c05effff  movss      xmm2, dword ptr [ebx - 0xa140]
00009654  f30f109ba05effff  movss      xmm3, dword ptr [ebx - 0xa160]
0000965c  f30f10a3d05effff  movss      xmm4, dword ptr [ebx - 0xa130]
00009664  0f2883945dffff    movaps     xmm0, xmmword ptr [ebx - 0xa26c]
0000966b  0f29442470        movaps     xmmword ptr [esp + 0x70], xmm0
00009670  0f2883645dffff    movaps     xmm0, xmmword ptr [ebx - 0xa29c]
00009677  0f29442460        movaps     xmmword ptr [esp + 0x60], xmm0
0000967c  660f6fb3a45dffff  movdqa     xmm6, xmmword ptr [ebx - 0xa25c]
00009684  660f6f83045effff  movdqa     xmm0, xmmword ptr [ebx - 0xa1fc]
0000968c  660f7f442440      movdqa     xmmword ptr [esp + 0x40], xmm0
00009692  660f6fab345dffff  movdqa     xmm5, xmmword ptr [ebx - 0xa2cc]
0000969a  8d942488000000    lea        edx, [esp + 0x88]
000096a1  f30f11542410      movss      dword ptr [esp + 0x10], xmm2
000096a7  f30f115c240c      movss      dword ptr [esp + 0xc], xmm3
000096ad  f30f11642408      movss      dword ptr [esp + 8], xmm4
000096b3  660f7f742450      movdqa     xmmword ptr [esp + 0x50], xmm6
000096b9  660f7f6c2430      movdqa     xmmword ptr [esp + 0x30], xmm5
000096bf  eb24              jmp        0x96e5
000096c1  90                nop        
000096c2  90                nop        
000096c3  90                nop        
000096c4  90                nop        
000096c5  90                nop        
000096c6  90                nop        
000096c7  90                nop        
000096c8  90                nop        
000096c9  90                nop        
000096ca  90                nop        
000096cb  90                nop        
000096cc  90                nop        
000096cd  90                nop        
000096ce  90                nop        
000096cf  90                nop        
000096d0  8b4c242c          mov        ecx, dword ptr [esp + 0x2c]
000096d4  2b4c2428          sub        ecx, dword ptr [esp + 0x28]
000096d8  8d942488000000    lea        edx, [esp + 0x88]
000096df  0f8eb8010000      jle        0x989d
000096e5  8b44241c          mov        eax, dword ptr [esp + 0x1c]
000096e9  39c1              cmp        ecx, eax
000096eb  894c242c          mov        dword ptr [esp + 0x2c], ecx
000096ef  0f4cc1            cmovl      eax, ecx
000096f2  89c7              mov        edi, eax
000096f4  8b4c2420          mov        ecx, dword ptr [esp + 0x20]
000096f8  d3e7              shl        edi, cl
000096fa  89742414          mov        dword ptr [esp + 0x14], esi
000096fe  8d347e            lea        esi, [esi + edi*2]
00009701  8b5c2404          mov        ebx, dword ptr [esp + 4]
00009705  6a00              push       0
00009707  8944242c          mov        dword ptr [esp + 0x2c], eax
0000970b  50                push       eax
0000970c  52                push       edx
0000970d  ff742430          push       dword ptr [esp + 0x30]
00009711  e85a0a0000        call       0xa170
00009716  83c410            add        esp, 0x10
00009719  4f                dec        edi
0000971a  81e7ffffff7f      and        edi, 0x7fffffff
00009720  83ff07            cmp        edi, 7
00009723  733b              jae        0x9760
00009725  8b442414          mov        eax, dword ptr [esp + 0x14]
00009729  8d8c2488000000    lea        ecx, [esp + 0x88]
00009730  f30f10542410      movss      xmm2, dword ptr [esp + 0x10]
00009736  f30f105c240c      movss      xmm3, dword ptr [esp + 0xc]
0000973c  f30f10642408      movss      xmm4, dword ptr [esp + 8]
00009742  bfff7f0000        mov        edi, 0x7fff
00009747  bb00800000        mov        ebx, 0x8000
0000974c  e91f010000        jmp        0x9870
00009751  90                nop        
00009752  90                nop        
00009753  90                nop        
00009754  90                nop        
00009755  90                nop        
00009756  90                nop        
00009757  90                nop        
00009758  90                nop        
00009759  90                nop        
0000975a  90                nop        
0000975b  90                nop        
0000975c  90                nop        
0000975d  90                nop        
0000975e  90                nop        
0000975f  90                nop        
00009760  47                inc        edi
00009761  897c2418          mov        dword ptr [esp + 0x18], edi
00009765  89fa              mov        edx, edi
00009767  83e2f8            and        edx, 0xfffffff8
0000976a  8b5c2414          mov        ebx, dword ptr [esp + 0x14]
0000976e  8d0453            lea        eax, [ebx + edx*2]
00009771  8d0c94            lea        ecx, [esp + edx*4]
00009774  81c188000000      add        ecx, 0x88
0000977a  31ff              xor        edi, edi
0000977c  90                nop        
0000977d  90                nop        
0000977e  90                nop        
0000977f  90                nop        
00009780  0f10a4bc88000000  movups     xmm4, xmmword ptr [esp + edi*4 + 0x88]
00009788  0f109cbc98000000  movups     xmm3, xmmword ptr [esp + edi*4 + 0x98]
00009790  0f28c4            movaps     xmm0, xmm4
00009793  0f28cb            movaps     xmm1, xmm3
00009796  0f287c2460        movaps     xmm7, xmmword ptr [esp + 0x60]
0000979b  0f28d7            movaps     xmm2, xmm7
0000979e  0fc2d401          cmpltps    xmm2, xmm4
000097a2  660f6bd2          packssdw   xmm2, xmm2
000097a6  0f28742450        movaps     xmm6, xmmword ptr [esp + 0x50]
000097ab  0f59e6            mulps      xmm4, xmm6
000097ae  f30f5be4          cvttps2dq  xmm4, xmm4
000097b2  660f6be4          packssdw   xmm4, xmm4
000097b6  660f6fea          movdqa     xmm5, xmm2
000097ba  660fdfec          pandn      xmm5, xmm4
000097be  0f28e7            movaps     xmm4, xmm7
000097c1  0fc2e301          cmpltps    xmm4, xmm3
000097c5  660f6be4          packssdw   xmm4, xmm4
000097c9  0f59de            mulps      xmm3, xmm6
000097cc  f30f5bdb          cvttps2dq  xmm3, xmm3
000097d0  660f6bdb          packssdw   xmm3, xmm3
000097d4  660f6f742440      movdqa     xmm6, xmmword ptr [esp + 0x40]
000097da  660fdbd6          pand       xmm2, xmm6
000097de  660febd5          por        xmm2, xmm5
000097e2  660f6fec          movdqa     xmm5, xmm4
000097e6  660fdfeb          pandn      xmm5, xmm3
000097ea  0f287c2470        movaps     xmm7, xmmword ptr [esp + 0x70]
000097ef  0fc2c701          cmpltps    xmm0, xmm7
000097f3  660f6bc0          packssdw   xmm0, xmm0
000097f7  660fdbe6          pand       xmm4, xmm6
000097fb  660febe5          por        xmm4, xmm5
000097ff  660f6fd8          movdqa     xmm3, xmm0
00009803  660fdfda          pandn      xmm3, xmm2
00009807  0fc2cf01          cmpltps    xmm1, xmm7
0000980b  660f6bc9          packssdw   xmm1, xmm1
0000980f  660f6f6c2430      movdqa     xmm5, xmmword ptr [esp + 0x30]
00009815  660fdbc5          pand       xmm0, xmm5
00009819  660febc3          por        xmm0, xmm3
0000981d  660f6fd1          movdqa     xmm2, xmm1
00009821  660fdfd4          pandn      xmm2, xmm4
00009825  660fdbcd          pand       xmm1, xmm5
00009829  660febca          por        xmm1, xmm2
0000982d  660f6cc1          punpcklqdq xmm0, xmm1
00009831  f30f7f047b        movdqu     xmmword ptr [ebx + edi*2], xmm0
00009836  83c708            add        edi, 8
00009839  39fa              cmp        edx, edi
0000983b  0f853fffffff      jne        0x9780
00009841  39542418          cmp        dword ptr [esp + 0x18], edx
00009845  f30f10542410      movss      xmm2, dword ptr [esp + 0x10]
0000984b  f30f105c240c      movss      xmm3, dword ptr [esp + 0xc]
00009851  f30f10642408      movss      xmm4, dword ptr [esp + 8]
00009857  bfff7f0000        mov        edi, 0x7fff
0000985c  bb00800000        mov        ebx, 0x8000
00009861  0f8469feffff      je         0x96d0
00009867  90                nop        
00009868  90                nop        
00009869  90                nop        
0000986a  90                nop        
0000986b  90                nop        
0000986c  90                nop        
0000986d  90                nop        
0000986e  90                nop        
0000986f  90                nop        
00009870  f30f1001          movss      xmm0, dword ptr [ecx]
00009874  83c104            add        ecx, 4
00009877  0f2ec2            ucomiss    xmm0, xmm2
0000987a  0f28c8            movaps     xmm1, xmm0
0000987d  f30f59cb          mulss      xmm1, xmm3
00009881  f30f2cd1          cvttss2si  edx, xmm1
00009885  0f47d7            cmova      edx, edi
00009888  0f2ee0            ucomiss    xmm4, xmm0
0000988b  0f47d3            cmova      edx, ebx
0000988e  668910            mov        word ptr [eax], dx
00009891  83c002            add        eax, 2
00009894  39f0              cmp        eax, esi
00009896  75d8              jne        0x9870
00009898  e933feffff        jmp        0x96d0
0000989d  83ec04            sub        esp, 4
000098a0  8b5c2408          mov        ebx, dword ptr [esp + 8]
000098a4  ff7514            push       dword ptr [ebp + 0x14]
000098a7  ff7510            push       dword ptr [ebp + 0x10]
000098aa  ffb388100000      push       dword ptr [ebx + 0x1088]
000098b0  e87b080000        call       0xa130
000098b5  83c410            add        esp, 0x10
000098b8  b800ee0200        mov        eax, 0x2ee00
000098bd  8d8b8ac80b00      lea        ecx, [ebx + 0xbc88a]
000098c3  90                nop        
000098c4  90                nop        
000098c5  90                nop        
000098c6  90                nop        
000098c7  90                nop        
000098c8  90                nop        
000098c9  90                nop        
000098ca  90                nop        
000098cb  90                nop        
000098cc  90                nop        
000098cd  90                nop        
000098ce  90                nop        
000098cf  90                nop        
000098d0  85c0              test       eax, eax
000098d2  7426              je         0x98fa
000098d4  668379fe00        cmp        word ptr [ecx - 2], 0
000098d9  751f              jne        0x98fa
000098db  66833900          cmp        word ptr [ecx], 0
000098df  7519              jne        0x98fa
000098e1  8d70ff            lea        esi, [eax - 1]
000098e4  668379fa00        cmp        word ptr [ecx - 6], 0
000098e9  7511              jne        0x98fc
000098eb  83c0fe            add        eax, -2
000098ee  668379fc00        cmp        word ptr [ecx - 4], 0
000098f3  8d49f8            lea        ecx, [ecx - 8]
000098f6  74d8              je         0x98d0
000098f8  eb02              jmp        0x98fc
000098fa  89c6              mov        esi, eax
000098fc  8b7d08            mov        edi, dword ptr [ebp + 8]
000098ff  8b07              mov        eax, dword ptr [edi]
00009901  8d0c36            lea        ecx, [esi + esi]
00009904  83ec0c            sub        esp, 0xc
00009907  8b5c2410          mov        ebx, dword ptr [esp + 0x10]
0000990b  8d938c100000      lea        edx, [ebx + 0x108c]
00009911  52                push       edx
00009912  51                push       ecx
00009913  6a00              push       0
00009915  ff7518            push       dword ptr [ebp + 0x18]
00009918  57                push       edi
00009919  ff9048030000      call       dword ptr [eax + 0x348]
0000991f  83c420            add        esp, 0x20
00009922  65a114000000      mov        eax, dword ptr gs:[0x14]
00009928  3b842488080000    cmp        eax, dword ptr [esp + 0x888]
0000992f  750a              jne        0x993b
00009931  89f0              mov        eax, esi
00009933  8d65f4            lea        esp, [ebp - 0xc]
00009936  5e                pop        esi
00009937  5f                pop        edi
00009938  5b                pop        ebx
00009939  5d                pop        ebp
0000993a  c3                ret        
0000993b  8b5c2404          mov        ebx, dword ptr [esp + 4]
0000993f  e8fc060000        call       0xa040
