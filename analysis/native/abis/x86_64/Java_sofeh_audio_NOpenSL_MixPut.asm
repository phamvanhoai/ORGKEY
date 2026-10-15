# APK member: lib/x86_64/libnopensl.so
# Library SHA-256: 1cfbadb3f31bae35f1aa6bc7a994af95d242e75b680f26a661a4b66c2a61f23f
# Function: Java_sofeh_audio_NOpenSL_MixPut; address=0x11f0; bytes=856
# Decoder mode: default; decoded_bytes=856
000011f0  55                push       rbp
000011f1  4889e5            mov        rbp, rsp
000011f4  4157              push       r15
000011f6  4156              push       r14
000011f8  4155              push       r13
000011fa  4154              push       r12
000011fc  53                push       rbx
000011fd  4881ec28000100    sub        rsp, 0x10028
00001204  4889cb            mov        rbx, rcx
00001207  64488b042528000000  mov        rax, qword ptr fs:[0x28]
00001210  488945d0          mov        qword ptr [rbp - 0x30], rax
00001214  4885d2            test       rdx, rdx
00001217  0f94c0            sete       al
0000121a  4885c9            test       rcx, rcx
0000121d  0f94c1            sete       cl
00001220  08c1              or         cl, al
00001222  0f85fa020000      jne        0x1522
00001228  4589cd            mov        r13d, r9d
0000122b  4589c4            mov        r12d, r8d
0000122e  4989fe            mov        r14, rdi
00001231  488b07            mov        rax, qword ptr [rdi]
00001234  4889d6            mov        rsi, rdx
00001237  488995c0fffeff    mov        qword ptr [rbp - 0x10040], rdx
0000123e  ff9058050000      call       qword ptr [rax + 0x558]
00001244  4189c7            mov        r15d, eax
00001247  498b06            mov        rax, qword ptr [r14]
0000124a  4c89f7            mov        rdi, r14
0000124d  4889de            mov        rsi, rbx
00001250  ff9058050000      call       qword ptr [rax + 0x558]
00001256  488bb5c0fffeff    mov        rsi, qword ptr [rbp - 0x10040]
0000125d  4539e7            cmp        r15d, r12d
00001260  450f4ce7          cmovl      r12d, r15d
00001264  4489e1            mov        ecx, r12d
00001267  c1e91f            shr        ecx, 0x1f
0000126a  4401e1            add        ecx, r12d
0000126d  d1f9              sar        ecx, 1
0000126f  448d3c00          lea        r15d, [rax + rax]
00001273  39c1              cmp        ecx, eax
00001275  450f4efc          cmovle     r15d, r12d
00001279  4181ff00400000    cmp        r15d, 0x4000
00001280  41bc00400000      mov        r12d, 0x4000
00001286  450f4ce7          cmovl      r12d, r15d
0000128a  4585ff            test       r15d, r15d
0000128d  0f8e8f020000      jle        0x1522
00001293  498b06            mov        rax, qword ptr [r14]
00001296  4c8d85d07fffff    lea        r8, [rbp - 0x8030]
0000129d  4c89f7            mov        rdi, r14
000012a0  31d2              xor        edx, edx
000012a2  4489e1            mov        ecx, r12d
000012a5  ff9050060000      call       qword ptr [rax + 0x650]
000012ab  498b06            mov        rax, qword ptr [r14]
000012ae  4489e1            mov        ecx, r12d
000012b1  d1e9              shr        ecx, 1
000012b3  4c8d85d0fffeff    lea        r8, [rbp - 0x10030]
000012ba  4c89f7            mov        rdi, r14
000012bd  48899db8fffeff    mov        qword ptr [rbp - 0x10048], rbx
000012c4  4889de            mov        rsi, rbx
000012c7  31d2              xor        edx, edx
000012c9  898dccfffeff      mov        dword ptr [rbp - 0x10034], ecx
000012cf  ff9050060000      call       qword ptr [rax + 0x650]
000012d5  4183ff01          cmp        r15d, 1
000012d9  0f846a010000      je         0x1449
000012df  8b4510            mov        eax, dword ptr [rbp + 0x10]
000012e2  8b8dccfffeff      mov        ecx, dword ptr [rbp - 0x10034]
000012e8  83f901            cmp        ecx, 1
000012eb  83d100            adc        ecx, 0
000012ee  4183ff08          cmp        r15d, 8
000012f2  7d07              jge        0x12fb
000012f4  31d2              xor        edx, edx
000012f6  e9f5000000        jmp        0x13f0
000012fb  89ca              mov        edx, ecx
000012fd  81e2fc3f0000      and        edx, 0x3ffc
00001303  66410f6ec5        movd       xmm0, r13d
00001308  660f70c000        pshufd     xmm0, xmm0, 0
0000130d  660f6ec8          movd       xmm1, eax
00001311  660f70c900        pshufd     xmm1, xmm1, 0
00001316  8d3409            lea        esi, [rcx + rcx]
00001319  81e6f87f0000      and        esi, 0x7ff8
0000131f  31ff              xor        edi, edi
00001321  660f6f1587f7ffff  movdqa     xmm2, xmmword ptr [rip - 0x879]
00001329  660f6f1d8ff7ffff  movdqa     xmm3, xmmword ptr [rip - 0x871]
00001331  6666666666662e0f1f840000000000  nop        word ptr cs:[rax + rax]
00001340  660f3823ac3dd0fffeff  pmovsxwd   xmm5, qword ptr [rbp + rdi - 0x10030]
0000134a  660f6fe0          movdqa     xmm4, xmm0
0000134e  660f3840e5        pmulld     xmm4, xmm5
00001353  660f70f4f5        pshufd     xmm6, xmm4, 0xf5
00001358  660f3828f2        pmuldq     xmm6, xmm2
0000135d  660f3828e2        pmuldq     xmm4, xmm2
00001362  660f70e4f5        pshufd     xmm4, xmm4, 0xf5
00001367  660f3a0ee6cc      pblendw    xmm4, xmm6, 0xcc
0000136d  660f6ff4          movdqa     xmm6, xmm4
00001371  660f72d61f        psrld      xmm6, 0x1f
00001376  660f72d405        psrld      xmm4, 5
0000137b  660ffee6          paddd      xmm4, xmm6
0000137f  660f3840e9        pmulld     xmm5, xmm1
00001384  660f70f5f5        pshufd     xmm6, xmm5, 0xf5
00001389  660f3828f2        pmuldq     xmm6, xmm2
0000138e  660f3828ea        pmuldq     xmm5, xmm2
00001393  660f70edf5        pshufd     xmm5, xmm5, 0xf5
00001398  660f3a0eeecc      pblendw    xmm5, xmm6, 0xcc
0000139e  660f6ff5          movdqa     xmm6, xmm5
000013a2  660f72d61f        psrld      xmm6, 0x1f
000013a7  660f72d505        psrld      xmm5, 5
000013ac  660ffeee          paddd      xmm5, xmm6
000013b0  660f3800eb        pshufb     xmm5, xmm3
000013b5  660f3800e3        pshufb     xmm4, xmm3
000013ba  660f61e5          punpcklwd  xmm4, xmm5
000013be  660ffda47dd07fffff  paddw      xmm4, xmmword ptr [rbp + rdi*2 - 0x8030]
000013c7  660f7fa47dd07fffff  movdqa     xmmword ptr [rbp + rdi*2 - 0x8030], xmm4
000013d0  4883c708          add        rdi, 8
000013d4  4839fe            cmp        rsi, rdi
000013d7  0f8563ffffff      jne        0x1340
000013dd  4839ca            cmp        rdx, rcx
000013e0  7467              je         0x1449
000013e2  66666666662e0f1f840000000000  nop        word ptr cs:[rax + rax]
000013f0  0fbfb455d0fffeff  movsx      esi, word ptr [rbp + rdx*2 - 0x10030]
000013f8  89f7              mov        edi, esi
000013fa  410faffd          imul       edi, r13d
000013fe  4863ff            movsxd     rdi, edi
00001401  4869ff1f85eb51    imul       rdi, rdi, 0x51eb851f
00001408  4989f8            mov        r8, rdi
0000140b  49c1e83f          shr        r8, 0x3f
0000140f  48c1ef25          shr        rdi, 0x25
00001413  4401c7            add        edi, r8d
00001416  0faff0            imul       esi, eax
00001419  4863f6            movsxd     rsi, esi
0000141c  4869f61f85eb51    imul       rsi, rsi, 0x51eb851f
00001423  4989f0            mov        r8, rsi
00001426  49c1e83f          shr        r8, 0x3f
0000142a  48c1ee25          shr        rsi, 0x25
0000142e  6601bc95d07fffff  add        word ptr [rbp + rdx*4 - 0x8030], di
00001436  4401c6            add        esi, r8d
00001439  6601b495d27fffff  add        word ptr [rbp + rdx*4 - 0x802e], si
00001441  48ffc2            inc        rdx
00001444  4839d1            cmp        rcx, rdx
00001447  75a7              jne        0x13f0
00001449  438d1424          lea        edx, [r12 + r12]
0000144d  488d3dcc9b0000    lea        rdi, [rip + 0x9bcc]
00001454  488db5d07fffff    lea        rsi, [rbp - 0x8030]
0000145b  b900800000        mov        ecx, 0x8000
00001460  e8db180000        call       0x2d40
00001465  448925b41b0100    mov        dword ptr [rip + 0x11bb4], r12d
0000146c  443925b11b0100    cmp        dword ptr [rip + 0x11bb1], r12d
00001473  7c0a              jl         0x147f
00001475  c705a51b010000000000  mov        dword ptr [rip + 0x11ba5], 0
0000147f  48833dc91b090000  cmp        qword ptr [rip + 0x91bc9], 0
00001487  488b9db8fffeff    mov        rbx, qword ptr [rbp - 0x10048]
0000148e  7473              je         0x1503
00001490  833d9d1b010000    cmp        dword ptr [rip + 0x11b9d], 0
00001497  786a              js         0x1503
00001499  8b05951b0100      mov        eax, dword ptr [rip + 0x11b95]
0000149f  803d961b010000    cmp        byte ptr [rip + 0x11b96], 0
000014a6  b910000000        mov        ecx, 0x10
000014ab  ba04000000        mov        edx, 4
000014b0  0f45d1            cmovne     edx, ecx
000014b3  39d0              cmp        eax, edx
000014b5  7d4c              jge        0x1503
000014b7  486305761b0100    movsxd     rax, dword ptr [rip + 0x11b76]
000014be  48c1e00f          shl        rax, 0xf
000014c2  488d35971b0900    lea        rsi, [rip + 0x91b97]
000014c9  4801c6            add        rsi, rax
000014cc  4489e2            mov        edx, r12d
000014cf  81e2fe7f0000      and        edx, 0x7ffe
000014d5  4c8dbdd0fffeff    lea        r15, [rbp - 0x10030]
000014dc  b900800000        mov        ecx, 0x8000
000014e1  4c89ff            mov        rdi, r15
000014e4  e857180000        call       0x2d40
000014e9  498b06            mov        rax, qword ptr [r14]
000014ec  4c89f7            mov        rdi, r14
000014ef  4889de            mov        rsi, rbx
000014f2  31d2              xor        edx, edx
000014f4  8b8dccfffeff      mov        ecx, dword ptr [rbp - 0x10034]
000014fa  4d89f8            mov        r8, r15
000014fd  ff9090060000      call       qword ptr [rax + 0x690]
00001503  498b06            mov        rax, qword ptr [r14]
00001506  4c8d85d07fffff    lea        r8, [rbp - 0x8030]
0000150d  4c89f7            mov        rdi, r14
00001510  488bb5c0fffeff    mov        rsi, qword ptr [rbp - 0x10040]
00001517  31d2              xor        edx, edx
00001519  4489e1            mov        ecx, r12d
0000151c  ff9090060000      call       qword ptr [rax + 0x690]
00001522  64488b042528000000  mov        rax, qword ptr fs:[0x28]
0000152b  483b45d0          cmp        rax, qword ptr [rbp - 0x30]
0000152f  7512              jne        0x1543
00001531  4881c428000100    add        rsp, 0x10028
00001538  5b                pop        rbx
00001539  415c              pop        r12
0000153b  415d              pop        r13
0000153d  415e              pop        r14
0000153f  415f              pop        r15
00001541  5d                pop        rbp
00001542  c3                ret        
00001543  e8e8170000        call       0x2d30
