# APK member: lib/x86_64/libnmusic.so
# Library SHA-256: 792e4ca002affd7fa432bd2a8dbf94dc31b829ef32123a5954dd6e376627e35c
# Function: Java_sofeh_music_NMusic_code; address=0x34b0; bytes=186
# Decoder mode: default; decoded_bytes=186
000034b0  55                push       rbp
000034b1  4889e5            mov        rbp, rsp
000034b4  4157              push       r15
000034b6  4156              push       r14
000034b8  4155              push       r13
000034ba  4154              push       r12
000034bc  53                push       rbx
000034bd  50                push       rax
000034be  89cb              mov        ebx, ecx
000034c0  4989d6            mov        r14, rdx
000034c3  4989ff            mov        r15, rdi
000034c6  488b07            mov        rax, qword ptr [rdi]
000034c9  4531ed            xor        r13d, r13d
000034cc  4889d6            mov        rsi, rdx
000034cf  31d2              xor        edx, edx
000034d1  ff9048050000      call       qword ptr [rax + 0x548]
000034d7  4989c4            mov        r12, rax
000034da  803800            cmp        byte ptr [rax], 0
000034dd  7424              je         0x3503
000034df  4c89e7            mov        rdi, r12
000034e2  e839030000        call       0x3820
000034e7  31c9              xor        ecx, ecx
000034e9  4531ed            xor        r13d, r13d
000034ec  0f1f4000          nop        dword ptr [rax]
000034f0  410fbe140c        movsx      edx, byte ptr [r12 + rcx]
000034f5  48ffc1            inc        rcx
000034f8  0fafd1            imul       edx, ecx
000034fb  4101d5            add        r13d, edx
000034fe  4839c8            cmp        rax, rcx
00003501  77ed              ja         0x34f0
00003503  498b07            mov        rax, qword ptr [r15]
00003506  4c89ff            mov        rdi, r15
00003509  4c89f6            mov        rsi, r14
0000350c  4c89e2            mov        rdx, r12
0000350f  ff9050050000      call       qword ptr [rax + 0x550]
00003515  4863c3            movsxd     rax, ebx
00003518  488d0d81d8ffff    lea        rcx, [rip - 0x277f]
0000351f  8b0c81            mov        ecx, dword ptr [rcx + rax*4]
00003522  4489e8            mov        eax, r13d
00003525  c1e004            shl        eax, 4
00003528  99                cdq        
00003529  f7f9              idiv       ecx
0000352b  3d10270000        cmp        eax, 0x2710
00003530  7c29              jl         0x355b
00003532  66666666662e0f1f840000000000  nop        word ptr cs:[rax + rax]
00003540  4489e8            mov        eax, r13d
00003543  c1e81f            shr        eax, 0x1f
00003546  4401e8            add        eax, r13d
00003549  d1f8              sar        eax, 1
0000354b  4189c5            mov        r13d, eax
0000354e  c1e004            shl        eax, 4
00003551  99                cdq        
00003552  f7f9              idiv       ecx
00003554  3d0f270000        cmp        eax, 0x270f
00003559  7fe5              jg         0x3540
0000355b  4883c408          add        rsp, 8
0000355f  5b                pop        rbx
00003560  415c              pop        r12
00003562  415d              pop        r13
00003564  415e              pop        r14
00003566  415f              pop        r15
00003568  5d                pop        rbp
00003569  c3                ret        
