# APK member: lib/x86_64/libnopensl.so
# Library SHA-256: 1cfbadb3f31bae35f1aa6bc7a994af95d242e75b680f26a661a4b66c2a61f23f
# Function: Java_sofeh_audio_NOpenSL_MixWrite; address=0x1550; bytes=1318
# Decoder mode: default; decoded_bytes=1318
00001550  55                push       rbp
00001551  4889e5            mov        rbp, rsp
00001554  4157              push       r15
00001556  4156              push       r14
00001558  4155              push       r13
0000155a  4154              push       r12
0000155c  53                push       rbx
0000155d  4881ec38000100    sub        rsp, 0x10038
00001564  4889bdb0fffeff    mov        qword ptr [rbp - 0x10050], rdi
0000156b  64488b042528000000  mov        rax, qword ptr fs:[0x28]
00001574  488945d0          mov        qword ptr [rbp - 0x30], rax
00001578  488995b8fffeff    mov        qword ptr [rbp - 0x10048], rdx
0000157f  4885d2            test       rdx, rdx
00001582  0f84c8040000      je         0x1a50
00001588  4989ce            mov        r14, rcx
0000158b  4885c9            test       rcx, rcx
0000158e  0f84bc040000      je         0x1a50
00001594  48833d8c1a010000  cmp        qword ptr [rip + 0x11a8c], 0
0000159c  0f84ae040000      je         0x1a50
000015a2  833d871a010000    cmp        dword ptr [rip + 0x11a87], 0
000015a9  0f8ea1040000      jle        0x1a50
000015af  4589cd            mov        r13d, r9d
000015b2  4589c4            mov        r12d, r8d
000015b5  4c8bbdb0fffeff    mov        r15, qword ptr [rbp - 0x10050]
000015bc  498b07            mov        rax, qword ptr [r15]
000015bf  4c89ff            mov        rdi, r15
000015c2  488bb5b8fffeff    mov        rsi, qword ptr [rbp - 0x10048]
000015c9  ff9058050000      call       qword ptr [rax + 0x558]
000015cf  89c3              mov        ebx, eax
000015d1  498b07            mov        rax, qword ptr [r15]
000015d4  4c89ff            mov        rdi, r15
000015d7  4c89f6            mov        rsi, r14
000015da  ff9058050000      call       qword ptr [rax + 0x558]
000015e0  4439e3            cmp        ebx, r12d
000015e3  440f4ce3          cmovl      r12d, ebx
000015e7  4489e1            mov        ecx, r12d
000015ea  c1e91f            shr        ecx, 0x1f
000015ed  4401e1            add        ecx, r12d
000015f0  d1f9              sar        ecx, 1
000015f2  8d1c00            lea        ebx, [rax + rax]
000015f5  39c1              cmp        ecx, eax
000015f7  410f4edc          cmovle     ebx, r12d
000015fb  81fb00400000      cmp        ebx, 0x4000
00001601  41bc00400000      mov        r12d, 0x4000
00001607  440f4ce3          cmovl      r12d, ebx
0000160b  85db              test       ebx, ebx
0000160d  0f8e3d040000      jle        0x1a50
00001613  4c8bbdb0fffeff    mov        r15, qword ptr [rbp - 0x10050]
0000161a  498b07            mov        rax, qword ptr [r15]
0000161d  4c8d85d07fffff    lea        r8, [rbp - 0x8030]
00001624  4c89ff            mov        rdi, r15
00001627  488bb5b8fffeff    mov        rsi, qword ptr [rbp - 0x10048]
0000162e  31d2              xor        edx, edx
00001630  4489e1            mov        ecx, r12d
00001633  ff9050060000      call       qword ptr [rax + 0x650]
00001639  498b07            mov        rax, qword ptr [r15]
0000163c  4489a5c8fffeff    mov        dword ptr [rbp - 0x10038], r12d
00001643  41d1ec            shr        r12d, 1
00001646  4c8d85d0fffeff    lea        r8, [rbp - 0x10030]
0000164d  4c89ff            mov        rdi, r15
00001650  4c89f6            mov        rsi, r14
00001653  31d2              xor        edx, edx
00001655  4489a5ccfffeff    mov        dword ptr [rbp - 0x10034], r12d
0000165c  4489e1            mov        ecx, r12d
0000165f  ff9050060000      call       qword ptr [rax + 0x650]
00001665  83fb01            cmp        ebx, 1
00001668  0f845b010000      je         0x17c9
0000166e  8b4510            mov        eax, dword ptr [rbp + 0x10]
00001671  8b8dccfffeff      mov        ecx, dword ptr [rbp - 0x10034]
00001677  83f901            cmp        ecx, 1
0000167a  83d100            adc        ecx, 0
0000167d  83fb08            cmp        ebx, 8
00001680  7d07              jge        0x1689
00001682  31d2              xor        edx, edx
00001684  e9e7000000        jmp        0x1770
00001689  89ca              mov        edx, ecx
0000168b  81e2fc3f0000      and        edx, 0x3ffc
00001691  66410f6ec5        movd       xmm0, r13d
00001696  660f70c000        pshufd     xmm0, xmm0, 0
0000169b  660f6ec8          movd       xmm1, eax
0000169f  660f70c900        pshufd     xmm1, xmm1, 0
000016a4  8d3409            lea        esi, [rcx + rcx]
000016a7  81e6f87f0000      and        esi, 0x7ff8
000016ad  31ff              xor        edi, edi
000016af  660f6f15f9f3ffff  movdqa     xmm2, xmmword ptr [rip - 0xc07]
000016b7  660f6f1d01f4ffff  movdqa     xmm3, xmmword ptr [rip - 0xbff]
000016bf  90                nop        
000016c0  660f3823ac3dd0fffeff  pmovsxwd   xmm5, qword ptr [rbp + rdi - 0x10030]
000016ca  660f6fe0          movdqa     xmm4, xmm0
000016ce  660f3840e5        pmulld     xmm4, xmm5
000016d3  660f70f4f5        pshufd     xmm6, xmm4, 0xf5
000016d8  660f3828f2        pmuldq     xmm6, xmm2
000016dd  660f3828e2        pmuldq     xmm4, xmm2
000016e2  660f70e4f5        pshufd     xmm4, xmm4, 0xf5
000016e7  660f3a0ee6cc      pblendw    xmm4, xmm6, 0xcc
000016ed  660f6ff4          movdqa     xmm6, xmm4
000016f1  660f72d61f        psrld      xmm6, 0x1f
000016f6  660f72d405        psrld      xmm4, 5
000016fb  660ffee6          paddd      xmm4, xmm6
000016ff  660f3840e9        pmulld     xmm5, xmm1
00001704  660f70f5f5        pshufd     xmm6, xmm5, 0xf5
00001709  660f3828f2        pmuldq     xmm6, xmm2
0000170e  660f3828ea        pmuldq     xmm5, xmm2
00001713  660f70edf5        pshufd     xmm5, xmm5, 0xf5
00001718  660f3a0eeecc      pblendw    xmm5, xmm6, 0xcc
0000171e  660f6ff5          movdqa     xmm6, xmm5
00001722  660f72d61f        psrld      xmm6, 0x1f
00001727  660f72d505        psrld      xmm5, 5
0000172c  660ffeee          paddd      xmm5, xmm6
00001730  660f3800eb        pshufb     xmm5, xmm3
00001735  660f3800e3        pshufb     xmm4, xmm3
0000173a  660f61e5          punpcklwd  xmm4, xmm5
0000173e  660ffda47dd07fffff  paddw      xmm4, xmmword ptr [rbp + rdi*2 - 0x8030]
00001747  660f7fa47dd07fffff  movdqa     xmmword ptr [rbp + rdi*2 - 0x8030], xmm4
00001750  4883c708          add        rdi, 8
00001754  4839fe            cmp        rsi, rdi
00001757  0f8563ffffff      jne        0x16c0
0000175d  4839ca            cmp        rdx, rcx
00001760  7467              je         0x17c9
00001762  66666666662e0f1f840000000000  nop        word ptr cs:[rax + rax]
00001770  0fbfb455d0fffeff  movsx      esi, word ptr [rbp + rdx*2 - 0x10030]
00001778  89f7              mov        edi, esi
0000177a  410faffd          imul       edi, r13d
0000177e  4863ff            movsxd     rdi, edi
00001781  4869ff1f85eb51    imul       rdi, rdi, 0x51eb851f
00001788  4989f8            mov        r8, rdi
0000178b  49c1e83f          shr        r8, 0x3f
0000178f  48c1ef25          shr        rdi, 0x25
00001793  4401c7            add        edi, r8d
00001796  0faff0            imul       esi, eax
00001799  4863f6            movsxd     rsi, esi
0000179c  4869f61f85eb51    imul       rsi, rsi, 0x51eb851f
000017a3  4989f0            mov        r8, rsi
000017a6  49c1e83f          shr        r8, 0x3f
000017aa  48c1ee25          shr        rsi, 0x25
000017ae  6601bc95d07fffff  add        word ptr [rbp + rdx*4 - 0x8030], di
000017b6  4401c6            add        esi, r8d
000017b9  6601b495d27fffff  add        word ptr [rbp + rdx*4 - 0x802e], si
000017c1  48ffc2            inc        rdx
000017c4  4839d1            cmp        rcx, rdx
000017c7  75a7              jne        0x1770
000017c9  bb10000000        mov        ebx, 0x10
000017ce  4c89b5a0fffeff    mov        qword ptr [rbp - 0x10060], r14
000017d5  66662e0f1f840000000000  nop        word ptr cs:[rax + rax]
000017e0  8b054e180100      mov        eax, dword ptr [rip + 0x1184e]
000017e6  3b054c180100      cmp        eax, dword ptr [rip + 0x1184c]
000017ec  7512              jne        0x1800
000017ee  48833d3218010000  cmp        qword ptr [rip + 0x11832], 0
000017f6  752d              jne        0x1825
000017f8  e9e6010000        jmp        0x19e3
000017fd  0f1f00            nop        dword ptr [rax]
00001800  8b0d2e180100      mov        ecx, dword ptr [rip + 0x1182e]
00001806  803d2f18010000    cmp        byte ptr [rip + 0x1182f], 0
0000180d  ba04000000        mov        edx, 4
00001812  0f45d3            cmovne     edx, ebx
00001815  488b050c180100    mov        rax, qword ptr [rip + 0x1180c]
0000181c  39d1              cmp        ecx, edx
0000181e  7c2b              jl         0x184b
00001820  4885c0            test       rax, rax
00001823  7426              je         0x184b
00001825  8b0515180100      mov        eax, dword ptr [rip + 0x11815]
0000182b  83f802            cmp        eax, 2
0000182e  750c              jne        0x183c
00001830  bf0a000000        mov        edi, 0xa
00001835  e8d6140000        call       0x2d10
0000183a  eba4              jmp        0x17e0
0000183c  8b1df6170100      mov        ebx, dword ptr [rip + 0x117f6]
00001842  448b35fb170100    mov        r14d, dword ptr [rip + 0x117fb]
00001849  eb16              jmp        0x1861
0000184b  8b1de7170100      mov        ebx, dword ptr [rip + 0x117e7]
00001851  448b35ec170100    mov        r14d, dword ptr [rip + 0x117ec]
00001858  4885c0            test       rax, rax
0000185b  0f8497010000      je         0x19f8
00001861  4531ed            xor        r13d, r13d
00001864  448bbdc8fffeff    mov        r15d, dword ptr [rbp - 0x10038]
0000186b  0f1f440000        nop        dword ptr [rax + rax]
00001870  8b05ca170100      mov        eax, dword ptr [rip + 0x117ca]
00001876  83f802            cmp        eax, 2
00001879  0f8579010000      jne        0x19f8
0000187f  448b25aa170100    mov        r12d, dword ptr [rip + 0x117aa]
00001886  4539e6            cmp        r14d, r12d
00001889  0f8c8b000000      jl         0x191a
0000188f  ffc3              inc        ebx
00001891  803da417010000    cmp        byte ptr [rip + 0x117a4], 0
00001898  b804000000        mov        eax, 4
0000189d  41be10000000      mov        r14d, 0x10
000018a3  410f45c6          cmovne     eax, r14d
000018a7  39c3              cmp        ebx, eax
000018a9  b800000000        mov        eax, 0
000018ae  0f4dd8            cmovge     ebx, eax
000018b1  6666666666662e0f1f840000000000  nop        word ptr cs:[rax + rax]
000018c0  8b056e170100      mov        eax, dword ptr [rip + 0x1176e]
000018c6  39d8              cmp        eax, ebx
000018c8  741a              je         0x18e4
000018ca  8b0564170100      mov        eax, dword ptr [rip + 0x11764]
000018d0  803d6517010000    cmp        byte ptr [rip + 0x11765], 0
000018d7  b904000000        mov        ecx, 4
000018dc  410f45ce          cmovne     ecx, r14d
000018e0  39c8              cmp        eax, ecx
000018e2  7c2c              jl         0x1910
000018e4  48833d3c17010000  cmp        qword ptr [rip + 0x1173c], 0
000018ec  7422              je         0x1910
000018ee  8b054c170100      mov        eax, dword ptr [rip + 0x1174c]
000018f4  83f802            cmp        eax, 2
000018f7  7517              jne        0x1910
000018f9  bf0a000000        mov        edi, 0xa
000018fe  e80d140000        call       0x2d10
00001903  ebbb              jmp        0x18c0
00001905  66662e0f1f840000000000  nop        word ptr cs:[rax + rax]
00001910  4531f6            xor        r14d, r14d
00001913  448b2516170100    mov        r12d, dword ptr [rip + 0x11716]
0000191a  4529f4            sub        r12d, r14d
0000191d  4539e7            cmp        r15d, r12d
00001920  4489bdacfffeff    mov        dword ptr [rbp - 0x10054], r15d
00001927  450f4ce7          cmovl      r12d, r15d
0000192b  899dc4fffeff      mov        dword ptr [rbp - 0x1003c], ebx
00001931  4c63fb            movsxd     r15, ebx
00001934  4963c6            movsxd     rax, r14d
00001937  49c1e70f          shl        r15, 0xf
0000193b  488d0d0e170100    lea        rcx, [rip + 0x1170e]
00001942  4c01f9            add        rcx, r15
00001945  488d3c41          lea        rdi, [rcx + rax*2]
00001949  4963c5            movsxd     rax, r13d
0000194c  488db445d07fffff  lea        rsi, [rbp + rax*2 - 0x8030]
00001954  4489eb            mov        ebx, r13d
00001957  4d63ec            movsxd     r13, r12d
0000195a  4a8d146d00000000  lea        rdx, [r13*2]
00001962  e8e9130000        call       0x2d50
00001967  4183fd02          cmp        r13d, 2
0000196b  4189dd            mov        r13d, ebx
0000196e  7c4b              jl         0x19bb
00001970  48833dd816090000  cmp        qword ptr [rip + 0x916d8], 0
00001978  7441              je         0x19bb
0000197a  4489e2            mov        edx, r12d
0000197d  81e2feffff7f      and        edx, 0x7ffffffe
00001983  4489e8            mov        eax, r13d
00001986  c1e81f            shr        eax, 0x1f
00001989  4401e8            add        eax, r13d
0000198c  d1f8              sar        eax, 1
0000198e  4898              cdqe       
00001990  488d3c45d0fffeff  lea        rdi, [rax*2 - 0x10030]
00001998  4801ef            add        rdi, rbp
0000199b  4489f0            mov        eax, r14d
0000199e  c1e81f            shr        eax, 0x1f
000019a1  4401f0            add        eax, r14d
000019a4  d1f8              sar        eax, 1
000019a6  4898              cdqe       
000019a8  488d0db1160900    lea        rcx, [rip + 0x916b1]
000019af  4901cf            add        r15, rcx
000019b2  498d3447          lea        rsi, [r15 + rax*2]
000019b6  e895130000        call       0x2d50
000019bb  4501e6            add        r14d, r12d
000019be  448bbdacfffeff    mov        r15d, dword ptr [rbp - 0x10054]
000019c5  4529e7            sub        r15d, r12d
000019c8  7e28              jle        0x19f2
000019ca  4501e5            add        r13d, r12d
000019cd  48833d5316010000  cmp        qword ptr [rip + 0x11653], 0
000019d5  8b9dc4fffeff      mov        ebx, dword ptr [rbp - 0x1003c]
000019db  0f858ffeffff      jne        0x1870
000019e1  eb15              jmp        0x19f8
000019e3  8b1d4f160100      mov        ebx, dword ptr [rip + 0x1164f]
000019e9  448b3554160100    mov        r14d, dword ptr [rip + 0x11654]
000019f0  eb06              jmp        0x19f8
000019f2  8b9dc4fffeff      mov        ebx, dword ptr [rbp - 0x1003c]
000019f8  891d3a160100      mov        dword ptr [rip + 0x1163a], ebx
000019fe  4489353f160100    mov        dword ptr [rip + 0x1163f], r14d
00001a05  488b9db0fffeff    mov        rbx, qword ptr [rbp - 0x10050]
00001a0c  488b03            mov        rax, qword ptr [rbx]
00001a0f  4c8d85d07fffff    lea        r8, [rbp - 0x8030]
00001a16  4889df            mov        rdi, rbx
00001a19  488bb5b8fffeff    mov        rsi, qword ptr [rbp - 0x10048]
00001a20  31d2              xor        edx, edx
00001a22  8b8dc8fffeff      mov        ecx, dword ptr [rbp - 0x10038]
00001a28  ff9090060000      call       qword ptr [rax + 0x690]
00001a2e  488b03            mov        rax, qword ptr [rbx]
00001a31  4c8d85d0fffeff    lea        r8, [rbp - 0x10030]
00001a38  4889df            mov        rdi, rbx
00001a3b  488bb5a0fffeff    mov        rsi, qword ptr [rbp - 0x10060]
00001a42  31d2              xor        edx, edx
00001a44  8b8dccfffeff      mov        ecx, dword ptr [rbp - 0x10034]
00001a4a  ff9090060000      call       qword ptr [rax + 0x690]
00001a50  64488b042528000000  mov        rax, qword ptr fs:[0x28]
00001a59  483b45d0          cmp        rax, qword ptr [rbp - 0x30]
00001a5d  7512              jne        0x1a71
00001a5f  4881c438000100    add        rsp, 0x10038
00001a66  5b                pop        rbx
00001a67  415c              pop        r12
00001a69  415d              pop        r13
00001a6b  415e              pop        r14
00001a6d  415f              pop        r15
00001a6f  5d                pop        rbp
00001a70  c3                ret        
00001a71  e8ba120000        call       0x2d30
