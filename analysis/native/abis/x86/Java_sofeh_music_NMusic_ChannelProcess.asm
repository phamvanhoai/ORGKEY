# APK member: lib/x86/libnmusic.so
# Library SHA-256: 051762c43873d3b1e8d0eb96eb91cd82ffbc3e9ca211006f6977da7afa2b80a2
# Function: Java_sofeh_music_NMusic_ChannelProcess; address=0x2ad0; bytes=4176
# Decoder mode: default; decoded_bytes=4176
00002ad0  55                push       ebp
00002ad1  89e5              mov        ebp, esp
00002ad3  53                push       ebx
00002ad4  57                push       edi
00002ad5  56                push       esi
00002ad6  83e4f0            and        esp, 0xfffffff0
00002ad9  81ec60010000      sub        esp, 0x160
00002adf  e800000000        call       0x2ae4
00002ae4  5b                pop        ebx
00002ae5  81c3c0240000      add        ebx, 0x24c0
00002aeb  8b7508            mov        esi, dword ptr [ebp + 8]
00002aee  8b06              mov        eax, dword ptr [esi]
00002af0  83ec08            sub        esp, 8
00002af3  ff751c            push       dword ptr [ebp + 0x1c]
00002af6  56                push       esi
00002af7  ff507c            call       dword ptr [eax + 0x7c]
00002afa  83c410            add        esp, 0x10
00002afd  8b3e              mov        edi, dword ptr [esi]
00002aff  8d8b20bbffff      lea        ecx, [ebx - 0x44e0]
00002b05  8d93c4baffff      lea        edx, [ebx - 0x453c]
00002b0b  51                push       ecx
00002b0c  52                push       edx
00002b0d  89442414          mov        dword ptr [esp + 0x14], eax
00002b11  50                push       eax
00002b12  56                push       esi
00002b13  ff9778010000      call       dword ptr [edi + 0x178]
00002b19  83c410            add        esp, 0x10
00002b1c  8b0e              mov        ecx, dword ptr [esi]
00002b1e  83ec04            sub        esp, 4
00002b21  898424d0000000    mov        dword ptr [esp + 0xd0], eax
00002b28  50                push       eax
00002b29  ff751c            push       dword ptr [ebp + 0x1c]
00002b2c  56                push       esi
00002b2d  ff9194010000      call       dword ptr [ecx + 0x194]
00002b33  83c410            add        esp, 0x10
00002b36  89442414          mov        dword ptr [esp + 0x14], eax
00002b3a  89542408          mov        dword ptr [esp + 8], edx
00002b3e  8b06              mov        eax, dword ptr [esi]
00002b40  83ec08            sub        esp, 8
00002b43  ff7520            push       dword ptr [ebp + 0x20]
00002b46  56                push       esi
00002b47  ff9098030000      call       dword ptr [eax + 0x398]
00002b4d  83c410            add        esp, 0x10
00002b50  8944241c          mov        dword ptr [esp + 0x1c], eax
00002b54  8b06              mov        eax, dword ptr [esi]
00002b56  83ec04            sub        esp, 4
00002b59  6a00              push       0
00002b5b  ff7524            push       dword ptr [ebp + 0x24]
00002b5e  56                push       esi
00002b5f  ff90f0020000      call       dword ptr [eax + 0x2f0]
00002b65  83c410            add        esp, 0x10
00002b68  89442444          mov        dword ptr [esp + 0x44], eax
00002b6c  8b06              mov        eax, dword ptr [esi]
00002b6e  83ec04            sub        esp, 4
00002b71  6a00              push       0
00002b73  ff7528            push       dword ptr [ebp + 0x28]
00002b76  56                push       esi
00002b77  ff90f0020000      call       dword ptr [eax + 0x2f0]
00002b7d  83c410            add        esp, 0x10
00002b80  89442440          mov        dword ptr [esp + 0x40], eax
00002b84  8b06              mov        eax, dword ptr [esi]
00002b86  8b8078010000      mov        eax, dword ptr [eax + 0x178]
00002b8c  837d1800          cmp        dword ptr [ebp + 0x18], 0
00002b90  895c2434          mov        dword ptr [esp + 0x34], ebx
00002b94  0f84cf010000      je         0x2d69
00002b9a  8dbb00bbffff      lea        edi, [ebx - 0x4500]
00002ba0  8d8be5baffff      lea        ecx, [ebx - 0x451b]
00002ba6  57                push       edi
00002ba7  51                push       ecx
00002ba8  ff742414          push       dword ptr [esp + 0x14]
00002bac  56                push       esi
00002bad  ffd0              call       eax
00002baf  83c410            add        esp, 0x10
00002bb2  8b0e              mov        ecx, dword ptr [esi]
00002bb4  83ec04            sub        esp, 4
00002bb7  898424c4000000    mov        dword ptr [esp + 0xc4], eax
00002bbe  50                push       eax
00002bbf  8b451c            mov        eax, dword ptr [ebp + 0x1c]
00002bc2  50                push       eax
00002bc3  56                push       esi
00002bc4  ff919c010000      call       dword ptr [ecx + 0x19c]
00002bca  83c410            add        esp, 0x10
00002bcd  dd9c2450010000    fstp       qword ptr [esp + 0x150]
00002bd4  f20f10842450010000  movsd      xmm0, qword ptr [esp + 0x150]
00002bdd  f20f11442448      movsd      qword ptr [esp + 0x48], xmm0
00002be3  8b06              mov        eax, dword ptr [esi]
00002be5  8d8b0ebbffff      lea        ecx, [ebx - 0x44f2]
00002beb  57                push       edi
00002bec  51                push       ecx
00002bed  8b7c2414          mov        edi, dword ptr [esp + 0x14]
00002bf1  57                push       edi
00002bf2  56                push       esi
00002bf3  ff9078010000      call       dword ptr [eax + 0x178]
00002bf9  83c410            add        esp, 0x10
00002bfc  8b0e              mov        ecx, dword ptr [esi]
00002bfe  83ec04            sub        esp, 4
00002c01  50                push       eax
00002c02  ff751c            push       dword ptr [ebp + 0x1c]
00002c05  56                push       esi
00002c06  ff919c010000      call       dword ptr [ecx + 0x19c]
00002c0c  83c410            add        esp, 0x10
00002c0f  dd9c2448010000    fstp       qword ptr [esp + 0x148]
00002c16  f20f10842448010000  movsd      xmm0, qword ptr [esp + 0x148]
00002c1f  f20f11442460      movsd      qword ptr [esp + 0x60], xmm0
00002c25  8b06              mov        eax, dword ptr [esi]
00002c27  8d930cbbffff      lea        edx, [ebx - 0x44f4]
00002c2d  89542420          mov        dword ptr [esp + 0x20], edx
00002c31  8d8b02bbffff      lea        ecx, [ebx - 0x44fe]
00002c37  52                push       edx
00002c38  51                push       ecx
00002c39  57                push       edi
00002c3a  56                push       esi
00002c3b  ff9078010000      call       dword ptr [eax + 0x178]
00002c41  83c410            add        esp, 0x10
00002c44  8b0e              mov        ecx, dword ptr [esi]
00002c46  83ec04            sub        esp, 4
00002c49  898424c0000000    mov        dword ptr [esp + 0xc0], eax
00002c50  50                push       eax
00002c51  8b451c            mov        eax, dword ptr [ebp + 0x1c]
00002c54  50                push       eax
00002c55  56                push       esi
00002c56  ff9198010000      call       dword ptr [ecx + 0x198]
00002c5c  83c410            add        esp, 0x10
00002c5f  d99c24fc000000    fstp       dword ptr [esp + 0xfc]
00002c66  8b06              mov        eax, dword ptr [esi]
00002c68  8d8bedbaffff      lea        ecx, [ebx - 0x4513]
00002c6e  8b7c2420          mov        edi, dword ptr [esp + 0x20]
00002c72  57                push       edi
00002c73  51                push       ecx
00002c74  ff742414          push       dword ptr [esp + 0x14]
00002c78  56                push       esi
00002c79  ff9078010000      call       dword ptr [eax + 0x178]
00002c7f  83c410            add        esp, 0x10
00002c82  8b0e              mov        ecx, dword ptr [esi]
00002c84  83ec04            sub        esp, 4
00002c87  898424bc000000    mov        dword ptr [esp + 0xbc], eax
00002c8e  50                push       eax
00002c8f  ff751c            push       dword ptr [ebp + 0x1c]
00002c92  56                push       esi
00002c93  ff9198010000      call       dword ptr [ecx + 0x198]
00002c99  83c410            add        esp, 0x10
00002c9c  d99c24f8000000    fstp       dword ptr [esp + 0xf8]
00002ca3  8b06              mov        eax, dword ptr [esi]
00002ca5  8d8bcdbaffff      lea        ecx, [ebx - 0x4533]
00002cab  57                push       edi
00002cac  51                push       ecx
00002cad  8b7c2414          mov        edi, dword ptr [esp + 0x14]
00002cb1  57                push       edi
00002cb2  56                push       esi
00002cb3  ff9078010000      call       dword ptr [eax + 0x178]
00002cb9  83c410            add        esp, 0x10
00002cbc  8b0e              mov        ecx, dword ptr [esi]
00002cbe  83ec04            sub        esp, 4
00002cc1  50                push       eax
00002cc2  ff751c            push       dword ptr [ebp + 0x1c]
00002cc5  56                push       esi
00002cc6  ff9198010000      call       dword ptr [ecx + 0x198]
00002ccc  83c410            add        esp, 0x10
00002ccf  d99c24f4000000    fstp       dword ptr [esp + 0xf4]
00002cd6  f30f108424f4000000  movss      xmm0, dword ptr [esp + 0xf4]
00002cdf  f30f1144243c      movss      dword ptr [esp + 0x3c], xmm0
00002ce5  8b06              mov        eax, dword ptr [esi]
00002ce7  8d8bd9baffff      lea        ecx, [ebx - 0x4527]
00002ced  ff742420          push       dword ptr [esp + 0x20]
00002cf1  51                push       ecx
00002cf2  57                push       edi
00002cf3  56                push       esi
00002cf4  ff9078010000      call       dword ptr [eax + 0x178]
00002cfa  83c410            add        esp, 0x10
00002cfd  8b0e              mov        ecx, dword ptr [esi]
00002cff  83ec04            sub        esp, 4
00002d02  50                push       eax
00002d03  ff751c            push       dword ptr [ebp + 0x1c]
00002d06  56                push       esi
00002d07  ff9198010000      call       dword ptr [ecx + 0x198]
00002d0d  83c410            add        esp, 0x10
00002d10  d99c24f0000000    fstp       dword ptr [esp + 0xf0]
00002d17  f30f108424f0000000  movss      xmm0, dword ptr [esp + 0xf0]
00002d20  f30f11442438      movss      dword ptr [esp + 0x38], xmm0
00002d26  0f57c0            xorps      xmm0, xmm0
00002d29  0f57db            xorps      xmm3, xmm3
00002d2c  f30f5f9c24fc000000  maxss      xmm3, dword ptr [esp + 0xfc]
00002d35  0f57f6            xorps      xmm6, xmm6
00002d38  f30f5fb424f8000000  maxss      xmm6, dword ptr [esp + 0xf8]
00002d41  0f2ed8            ucomiss    xmm3, xmm0
00002d44  0f87f2010000      ja         0x2f3c
00002d4a  0f2ef0            ucomiss    xmm6, xmm0
00002d4d  0f87e9010000      ja         0x2f3c
00002d53  0f295c2420        movaps     xmmword ptr [esp + 0x20], xmm3
00002d58  31db              xor        ebx, ebx
00002d5a  8b542408          mov        edx, dword ptr [esp + 8]
00002d5e  f20f107c2448      movsd      xmm7, qword ptr [esp + 0x48]
00002d64  e9d00c0000        jmp        0x3a39
00002d69  8dbb32bbffff      lea        edi, [ebx - 0x44ce]
00002d6f  8d8b22bbffff      lea        ecx, [ebx - 0x44de]
00002d75  57                push       edi
00002d76  897c2424          mov        dword ptr [esp + 0x24], edi
00002d7a  51                push       ecx
00002d7b  ff742414          push       dword ptr [esp + 0x14]
00002d7f  56                push       esi
00002d80  ffd0              call       eax
00002d82  83c410            add        esp, 0x10
00002d85  8b0e              mov        ecx, dword ptr [esi]
00002d87  83ec04            sub        esp, 4
00002d8a  50                push       eax
00002d8b  8b451c            mov        eax, dword ptr [ebp + 0x1c]
00002d8e  50                push       eax
00002d8f  56                push       esi
00002d90  ff9190010000      call       dword ptr [ecx + 0x190]
00002d96  83c410            add        esp, 0x10
00002d99  89442450          mov        dword ptr [esp + 0x50], eax
00002d9d  8b06              mov        eax, dword ptr [esi]
00002d9f  8d8bf7baffff      lea        ecx, [ebx - 0x4509]
00002da5  57                push       edi
00002da6  51                push       ecx
00002da7  ff742414          push       dword ptr [esp + 0x14]
00002dab  56                push       esi
00002dac  ff9078010000      call       dword ptr [eax + 0x178]
00002db2  83c410            add        esp, 0x10
00002db5  8b0e              mov        ecx, dword ptr [esi]
00002db7  83ec04            sub        esp, 4
00002dba  8944243c          mov        dword ptr [esp + 0x3c], eax
00002dbe  50                push       eax
00002dbf  ff751c            push       dword ptr [ebp + 0x1c]
00002dc2  56                push       esi
00002dc3  ff9190010000      call       dword ptr [ecx + 0x190]
00002dc9  83c410            add        esp, 0x10
00002dcc  89c7              mov        edi, eax
00002dce  8b06              mov        eax, dword ptr [esi]
00002dd0  8d8b29bbffff      lea        ecx, [ebx - 0x44d7]
00002dd6  ff742420          push       dword ptr [esp + 0x20]
00002dda  51                push       ecx
00002ddb  ff742414          push       dword ptr [esp + 0x14]
00002ddf  56                push       esi
00002de0  ff9078010000      call       dword ptr [eax + 0x178]
00002de6  83c410            add        esp, 0x10
00002de9  8b0e              mov        ecx, dword ptr [esi]
00002deb  83ec04            sub        esp, 4
00002dee  8944241c          mov        dword ptr [esp + 0x1c], eax
00002df2  50                push       eax
00002df3  ff751c            push       dword ptr [ebp + 0x1c]
00002df6  56                push       esi
00002df7  ff9190010000      call       dword ptr [ecx + 0x190]
00002dfd  83c410            add        esp, 0x10
00002e00  31c9              xor        ecx, ecx
00002e02  85ff              test       edi, edi
00002e04  0f4ef9            cmovle     edi, ecx
00002e07  897c2410          mov        dword ptr [esp + 0x10], edi
00002e0b  0f9ec2            setle      dl
00002e0e  85c0              test       eax, eax
00002e10  0f4ec1            cmovle     eax, ecx
00002e13  89442460          mov        dword ptr [esp + 0x60], eax
00002e17  0f9ec1            setle      cl
00002e1a  20d1              and        cl, dl
00002e1c  837d2c00          cmp        dword ptr [ebp + 0x2c], 0
00002e20  0f9ec0            setle      al
00002e23  08c8              or         al, cl
00002e25  0f8526040000      jne        0x3251
00002e2b  8b4530            mov        eax, dword ptr [ebp + 0x30]
00002e2e  f7d0              not        eax
00002e30  034534            add        eax, dword ptr [ebp + 0x34]
00002e33  89442448          mov        dword ptr [esp + 0x48], eax
00002e37  31d2              xor        edx, edx
00002e39  8b442450          mov        eax, dword ptr [esp + 0x50]
00002e3d  c1f81f            sar        eax, 0x1f
00002e40  8944243c          mov        dword ptr [esp + 0x3c], eax
00002e44  c744242001000000  mov        dword ptr [esp + 0x20], 1
00002e4c  8b7c2408          mov        edi, dword ptr [esp + 8]
00002e50  897c2408          mov        dword ptr [esp + 8], edi
00002e54  807d3800          cmp        byte ptr [ebp + 0x38], 0
00002e58  0f95c1            setne      cl
00002e5b  8b7c2408          mov        edi, dword ptr [esp + 8]
00002e5f  c1ff1f            sar        edi, 0x1f
00002e62  c1ef13            shr        edi, 0x13
00002e65  037c2414          add        edi, dword ptr [esp + 0x14]
00002e69  8b742408          mov        esi, dword ptr [esp + 8]
00002e6d  83d600            adc        esi, 0
00002e70  0fa4fe13          shld       esi, edi, 0x13
00002e74  8b4530            mov        eax, dword ptr [ebp + 0x30]
00002e77  39c6              cmp        esi, eax
00002e79  0f9fc5            setg       ch
00002e7c  8b442448          mov        eax, dword ptr [esp + 0x48]
00002e80  8d3c30            lea        edi, [eax + esi]
00002e83  89f8              mov        eax, edi
00002e85  c1f81f            sar        eax, 0x1f
00002e88  0fa4f80d          shld       eax, edi, 0xd
00002e8c  89fb              mov        ebx, edi
00002e8e  c1e30d            shl        ebx, 0xd
00002e91  84e9              test       cl, ch
00002e93  0f44fe            cmove      edi, esi
00002e96  8b4c2408          mov        ecx, dword ptr [esp + 8]
00002e9a  0f45c8            cmovne     ecx, eax
00002e9d  894c2408          mov        dword ptr [esp + 8], ecx
00002ea1  8b442414          mov        eax, dword ptr [esp + 0x14]
00002ea5  0f45c3            cmovne     eax, ebx
00002ea8  89442414          mov        dword ptr [esp + 0x14], eax
00002eac  8b4530            mov        eax, dword ptr [ebp + 0x30]
00002eaf  39c7              cmp        edi, eax
00002eb1  0f4df8            cmovge     edi, eax
00002eb4  0fb6d2            movzx      edx, dl
00002eb7  b801000000        mov        eax, 1
00002ebc  0f4fd0            cmovg      edx, eax
00002ebf  8954240c          mov        dword ptr [esp + 0xc], edx
00002ec3  8b4d3c            mov        ecx, dword ptr [ebp + 0x3c]
00002ec6  d3e7              shl        edi, cl
00002ec8  8b5c241c          mov        ebx, dword ptr [esp + 0x1c]
00002ecc  0fbf047b          movsx      eax, word ptr [ebx + edi*2]
00002ed0  0faf442410        imul       eax, dword ptr [esp + 0x10]
00002ed5  89c1              mov        ecx, eax
00002ed7  c1f91f            sar        ecx, 0x1f
00002eda  8b742444          mov        esi, dword ptr [esp + 0x44]
00002ede  8b542420          mov        edx, dword ptr [esp + 0x20]
00002ee2  0144d6f8          add        dword ptr [esi + edx*8 - 8], eax
00002ee6  114cd6fc          adc        dword ptr [esi + edx*8 - 4], ecx
00002eea  8d047b            lea        eax, [ebx + edi*2]
00002eed  8b7c2408          mov        edi, dword ptr [esp + 8]
00002ef1  8b4d40            mov        ecx, dword ptr [ebp + 0x40]
00002ef4  0fbf0448          movsx      eax, word ptr [eax + ecx*2]
00002ef8  0faf442460        imul       eax, dword ptr [esp + 0x60]
00002efd  89c1              mov        ecx, eax
00002eff  c1f91f            sar        ecx, 0x1f
00002f02  8b742440          mov        esi, dword ptr [esp + 0x40]
00002f06  0144d6f8          add        dword ptr [esi + edx*8 - 8], eax
00002f0a  114cd6fc          adc        dword ptr [esi + edx*8 - 4], ecx
00002f0e  8b4c2450          mov        ecx, dword ptr [esp + 0x50]
00002f12  014c2414          add        dword ptr [esp + 0x14], ecx
00002f16  137c243c          adc        edi, dword ptr [esp + 0x3c]
00002f1a  3b552c            cmp        edx, dword ptr [ebp + 0x2c]
00002f1d  0f8d75080000      jge        0x3798
00002f23  8b54240c          mov        edx, dword ptr [esp + 0xc]
00002f27  89d0              mov        eax, edx
00002f29  2401              and        al, 1
00002f2b  ff442420          inc        dword ptr [esp + 0x20]
00002f2f  84c0              test       al, al
00002f31  0f8419ffffff      je         0x2e50
00002f37  e960080000        jmp        0x379c
00002f3c  8b5518            mov        edx, dword ptr [ebp + 0x18]
00002f3f  83fa01            cmp        edx, 1
00002f42  0f8514030000      jne        0x325c
00002f48  0f295c2420        movaps     xmmword ptr [esp + 0x20], xmm3
00002f4d  837d2c00          cmp        dword ptr [ebp + 0x2c], 0
00002f51  8b442434          mov        eax, dword ptr [esp + 0x34]
00002f55  8b542408          mov        edx, dword ptr [esp + 8]
00002f59  8b4d30            mov        ecx, dword ptr [ebp + 0x30]
00002f5c  f20f107c2448      movsd      xmm7, qword ptr [esp + 0x48]
00002f62  0f8e72080000      jle        0x37da
00002f68  89d6              mov        esi, edx
00002f6a  8b54241c          mov        edx, dword ptr [esp + 0x1c]
00002f6e  8b7d40            mov        edi, dword ptr [ebp + 0x40]
00002f71  8d147a            lea        edx, [edx + edi*2]
00002f74  89542418          mov        dword ptr [esp + 0x18], edx
00002f78  89f2              mov        edx, esi
00002f7a  f7d1              not        ecx
00002f7c  034d34            add        ecx, dword ptr [ebp + 0x34]
00002f7f  894c2450          mov        dword ptr [esp + 0x50], ecx
00002f83  f30f108880baffff  movss      xmm1, dword ptr [eax - 0x4580]
00002f8b  0f57d2            xorps      xmm2, xmm2
00002f8e  bf01000000        mov        edi, 1
00002f93  31db              xor        ebx, ebx
00002f95  90                nop        
00002f96  90                nop        
00002f97  90                nop        
00002f98  90                nop        
00002f99  90                nop        
00002f9a  90                nop        
00002f9b  90                nop        
00002f9c  90                nop        
00002f9d  90                nop        
00002f9e  90                nop        
00002f9f  90                nop        
00002fa0  897c2410          mov        dword ptr [esp + 0x10], edi
00002fa4  89df              mov        edi, ebx
00002fa6  89d0              mov        eax, edx
00002fa8  c1f81f            sar        eax, 0x1f
00002fab  c1e813            shr        eax, 0x13
00002fae  03442414          add        eax, dword ptr [esp + 0x14]
00002fb2  89d3              mov        ebx, edx
00002fb4  83d300            adc        ebx, 0
00002fb7  0fa4c313          shld       ebx, eax, 0x13
00002fbb  807d3800          cmp        byte ptr [ebp + 0x38], 0
00002fbf  744f              je         0x3010
00002fc1  8b4d30            mov        ecx, dword ptr [ebp + 0x30]
00002fc4  39cb              cmp        ebx, ecx
00002fc6  0f285c2420        movaps     xmm3, xmmword ptr [esp + 0x20]
00002fcb  7e53              jle        0x3020
00002fcd  8b742450          mov        esi, dword ptr [esp + 0x50]
00002fd1  01f3              add        ebx, esi
00002fd3  89da              mov        edx, ebx
00002fd5  c1fa1f            sar        edx, 0x1f
00002fd8  0fa4da0d          shld       edx, ebx, 0xd
00002fdc  89d8              mov        eax, ebx
00002fde  c1e00d            shl        eax, 0xd
00002fe1  89442414          mov        dword ptr [esp + 0x14], eax
00002fe5  0f28c6            movaps     xmm0, xmm6
00002fe8  0fc2c202          cmpleps    xmm0, xmm2
00002fec  0f28e3            movaps     xmm4, xmm3
00002fef  0fc2e202          cmpleps    xmm4, xmm2
00002ff3  0f54e0            andps      xmm4, xmm0
00002ff6  660f7ee0          movd       eax, xmm4
00002ffa  a801              test       al, 1
00002ffc  89f8              mov        eax, edi
00002ffe  0fb6c0            movzx      eax, al
00003001  bf01000000        mov        edi, 1
00003006  0f45c7            cmovne     eax, edi
00003009  89c7              mov        edi, eax
0000300b  89f0              mov        eax, esi
0000300d  eb15              jmp        0x3024
0000300f  90                nop        
00003010  89d6              mov        esi, edx
00003012  8d4301            lea        eax, [ebx + 1]
00003015  8b4d30            mov        ecx, dword ptr [ebp + 0x30]
00003018  0f285c2420        movaps     xmm3, xmmword ptr [esp + 0x20]
0000301d  eb14              jmp        0x3033
0000301f  90                nop        
00003020  8b442450          mov        eax, dword ptr [esp + 0x50]
00003024  89d6              mov        esi, edx
00003026  39cb              cmp        ebx, ecx
00003028  ba00000000        mov        edx, 0
0000302d  0f4cc2            cmovl      eax, edx
00003030  01d8              add        eax, ebx
00003032  40                inc        eax
00003033  39cb              cmp        ebx, ecx
00003035  0f4dd9            cmovge     ebx, ecx
00003038  89fa              mov        edx, edi
0000303a  0fb6d2            movzx      edx, dl
0000303d  bf01000000        mov        edi, 1
00003042  0f4fd7            cmovg      edx, edi
00003045  8954240c          mov        dword ptr [esp + 0xc], edx
00003049  39c8              cmp        eax, ecx
0000304b  0f4fc3            cmovg      eax, ebx
0000304e  0f57c0            xorps      xmm0, xmm0
00003051  f30f2ac3          cvtsi2ss   xmm0, ebx
00003055  8b4d3c            mov        ecx, dword ptr [ebp + 0x3c]
00003058  d3e3              shl        ebx, cl
0000305a  d3e0              shl        eax, cl
0000305c  660f6e642414      movd       xmm4, dword ptr [esp + 0x14]
00003062  660f6eee          movd       xmm5, esi
00003066  660f62e5          punpckldq  xmm4, xmm5
0000306a  660fd6a42428010000  movq       qword ptr [esp + 0x128], xmm4
00003073  dfac2428010000    fild       qword ptr [esp + 0x128]
0000307a  d99c24e0000000    fstp       dword ptr [esp + 0xe0]
00003081  f30f10ac24e0000000  movss      xmm5, dword ptr [esp + 0xe0]
0000308a  f30f59e9          mulss      xmm5, xmm1
0000308e  f30f5ce8          subss      xmm5, xmm0
00003092  0f2eda            ucomiss    xmm3, xmm2
00003095  89742408          mov        dword ptr [esp + 8], esi
00003099  0f8691000000      jbe        0x3130
0000309f  8b74241c          mov        esi, dword ptr [esp + 0x1c]
000030a3  0fbf0c5e          movsx      ecx, word ptr [esi + ebx*2]
000030a7  0f57c0            xorps      xmm0, xmm0
000030aa  f30f2ac1          cvtsi2ss   xmm0, ecx
000030ae  0fbf0c46          movsx      ecx, word ptr [esi + eax*2]
000030b2  0f28de            movaps     xmm3, xmm6
000030b5  f30f2af1          cvtsi2ss   xmm6, ecx
000030b9  f30f5cf0          subss      xmm6, xmm0
000030bd  f30f59f5          mulss      xmm6, xmm5
000030c1  f30f58f0          addss      xmm6, xmm0
000030c5  8b7c2444          mov        edi, dword ptr [esp + 0x44]
000030c9  8b542410          mov        edx, dword ptr [esp + 0x10]
000030cd  df6cd7f8          fild       qword ptr [edi + edx*8 - 8]
000030d1  d99c24dc000000    fstp       dword ptr [esp + 0xdc]
000030d8  f30f59742420      mulss      xmm6, dword ptr [esp + 0x20]
000030de  f30f58b424dc000000  addss      xmm6, dword ptr [esp + 0xdc]
000030e7  f30f11b42498000000  movss      dword ptr [esp + 0x98], xmm6
000030f0  0f28f3            movaps     xmm6, xmm3
000030f3  0f285c2420        movaps     xmm3, xmmword ptr [esp + 0x20]
000030f8  d9842498000000    fld        dword ptr [esp + 0x98]
000030ff  dd8c2498000000    fisttp     qword ptr [esp + 0x98]
00003106  8b8c2498000000    mov        ecx, dword ptr [esp + 0x98]
0000310d  8bb4249c000000    mov        esi, dword ptr [esp + 0x9c]
00003114  8974d7fc          mov        dword ptr [edi + edx*8 - 4], esi
00003118  8b7508            mov        esi, dword ptr [ebp + 8]
0000311b  894cd7f8          mov        dword ptr [edi + edx*8 - 8], ecx
0000311f  89d7              mov        edi, edx
00003121  f30f585c243c      addss      xmm3, dword ptr [esp + 0x3c]
00003127  eb0e              jmp        0x3137
00003129  90                nop        
0000312a  90                nop        
0000312b  90                nop        
0000312c  90                nop        
0000312d  90                nop        
0000312e  90                nop        
0000312f  90                nop        
00003130  8b7508            mov        esi, dword ptr [ebp + 8]
00003133  8b7c2410          mov        edi, dword ptr [esp + 0x10]
00003137  0f2ef2            ucomiss    xmm6, xmm2
0000313a  0f295c2420        movaps     xmmword ptr [esp + 0x20], xmm3
0000313f  0f868b000000      jbe        0x31d0
00003145  8b542418          mov        edx, dword ptr [esp + 0x18]
00003149  0fbf0c5a          movsx      ecx, word ptr [edx + ebx*2]
0000314d  0f57c0            xorps      xmm0, xmm0
00003150  f30f2ac1          cvtsi2ss   xmm0, ecx
00003154  0fbf0442          movsx      eax, word ptr [edx + eax*2]
00003158  0f28de            movaps     xmm3, xmm6
0000315b  0f57f6            xorps      xmm6, xmm6
0000315e  f30f2af0          cvtsi2ss   xmm6, eax
00003162  f30f5cf0          subss      xmm6, xmm0
00003166  f30f59ee          mulss      xmm5, xmm6
0000316a  0f28f3            movaps     xmm6, xmm3
0000316d  f30f58e8          addss      xmm5, xmm0
00003171  8b7c2440          mov        edi, dword ptr [esp + 0x40]
00003175  8b442410          mov        eax, dword ptr [esp + 0x10]
00003179  df6cc7f8          fild       qword ptr [edi + eax*8 - 8]
0000317d  d99c24d8000000    fstp       dword ptr [esp + 0xd8]
00003184  f30f59eb          mulss      xmm5, xmm3
00003188  f30f58ac24d8000000  addss      xmm5, dword ptr [esp + 0xd8]
00003191  f30f11ac2490000000  movss      dword ptr [esp + 0x90], xmm5
0000319a  d9842490000000    fld        dword ptr [esp + 0x90]
000031a1  dd8c2490000000    fisttp     qword ptr [esp + 0x90]
000031a8  8b842490000000    mov        eax, dword ptr [esp + 0x90]
000031af  8b8c2494000000    mov        ecx, dword ptr [esp + 0x94]
000031b6  8b542410          mov        edx, dword ptr [esp + 0x10]
000031ba  894cd7fc          mov        dword ptr [edi + edx*8 - 4], ecx
000031be  8b4c2410          mov        ecx, dword ptr [esp + 0x10]
000031c2  8944cff8          mov        dword ptr [edi + ecx*8 - 8], eax
000031c6  8b7c2410          mov        edi, dword ptr [esp + 0x10]
000031ca  f30f58742438      addss      xmm6, dword ptr [esp + 0x38]
000031d0  8b542408          mov        edx, dword ptr [esp + 8]
000031d4  8b4d2c            mov        ecx, dword ptr [ebp + 0x2c]
000031d7  8b5c240c          mov        ebx, dword ptr [esp + 0xc]
000031db  f20f587c2460      addsd      xmm7, qword ptr [esp + 0x60]
000031e1  0f57c0            xorps      xmm0, xmm0
000031e4  660f2ef8          ucomisd    xmm7, xmm0
000031e8  660fd6a42420010000  movq       qword ptr [esp + 0x120], xmm4
000031f1  dfac2420010000    fild       qword ptr [esp + 0x120]
000031f8  dd9c2418010000    fstp       qword ptr [esp + 0x118]
000031ff  f20f10842418010000  movsd      xmm0, qword ptr [esp + 0x118]
00003208  f20f58c7          addsd      xmm0, xmm7
0000320c  f20f11842488000000  movsd      qword ptr [esp + 0x88], xmm0
00003215  dd842488000000    fld        qword ptr [esp + 0x88]
0000321c  dd8c2488000000    fisttp     qword ptr [esp + 0x88]
00003223  7612              jbe        0x3237
00003225  8b94248c000000    mov        edx, dword ptr [esp + 0x8c]
0000322c  8b842488000000    mov        eax, dword ptr [esp + 0x88]
00003233  89442414          mov        dword ptr [esp + 0x14], eax
00003237  39cf              cmp        edi, ecx
00003239  0f8dfa070000      jge        0x3a39
0000323f  89d8              mov        eax, ebx
00003241  2401              and        al, 1
00003243  47                inc        edi
00003244  84c0              test       al, al
00003246  0f8454fdffff      je         0x2fa0
0000324c  e9e8070000        jmp        0x3a39
00003251  8b7c2408          mov        edi, dword ptr [esp + 8]
00003255  31d2              xor        edx, edx
00003257  e940050000        jmp        0x379c
0000325c  83e2fe            and        edx, 0xfffffffe
0000325f  8b44241c          mov        eax, dword ptr [esp + 0x1c]
00003263  8b4d40            mov        ecx, dword ptr [ebp + 0x40]
00003266  8d0448            lea        eax, [eax + ecx*2]
00003269  89842480000000    mov        dword ptr [esp + 0x80], eax
00003270  83fa02            cmp        edx, 2
00003273  8b442434          mov        eax, dword ptr [esp + 0x34]
00003277  8b5d2c            mov        ebx, dword ptr [ebp + 0x2c]
0000327a  8b4d30            mov        ecx, dword ptr [ebp + 0x30]
0000327d  f20f107c2448      movsd      xmm7, qword ptr [esp + 0x48]
00003283  0f8558050000      jne        0x37e1
00003289  0f295c2420        movaps     xmmword ptr [esp + 0x20], xmm3
0000328e  85db              test       ebx, ebx
00003290  8b542408          mov        edx, dword ptr [esp + 8]
00003294  0f8e40050000      jle        0x37da
0000329a  f7d1              not        ecx
0000329c  034d34            add        ecx, dword ptr [ebp + 0x34]
0000329f  894c2468          mov        dword ptr [esp + 0x68], ecx
000032a3  f30f108080baffff  movss      xmm0, dword ptr [eax - 0x4580]
000032ab  f30f118424c8000000  movss      dword ptr [esp + 0xc8], xmm0
000032b4  f30f108084baffff  movss      xmm0, dword ptr [eax - 0x457c]
000032bc  f30f1144247c      movss      dword ptr [esp + 0x7c], xmm0
000032c2  f30f108090baffff  movss      xmm0, dword ptr [eax - 0x4570]
000032ca  f30f11442478      movss      dword ptr [esp + 0x78], xmm0
000032d0  f30f108098baffff  movss      xmm0, dword ptr [eax - 0x4568]
000032d8  f30f11442474      movss      dword ptr [esp + 0x74], xmm0
000032de  f30f108094baffff  movss      xmm0, dword ptr [eax - 0x456c]
000032e6  f30f11442470      movss      dword ptr [esp + 0x70], xmm0
000032ec  f30f10808cbaffff  movss      xmm0, dword ptr [eax - 0x4574]
000032f4  f30f1144246c      movss      dword ptr [esp + 0x6c], xmm0
000032fa  bf01000000        mov        edi, 1
000032ff  31db              xor        ebx, ebx
00003301  90                nop        
00003302  90                nop        
00003303  90                nop        
00003304  90                nop        
00003305  90                nop        
00003306  90                nop        
00003307  90                nop        
00003308  90                nop        
00003309  90                nop        
0000330a  90                nop        
0000330b  90                nop        
0000330c  90                nop        
0000330d  90                nop        
0000330e  90                nop        
0000330f  90                nop        
00003310  89d0              mov        eax, edx
00003312  c1f81f            sar        eax, 0x1f
00003315  c1e813            shr        eax, 0x13
00003318  03442414          add        eax, dword ptr [esp + 0x14]
0000331c  89d1              mov        ecx, edx
0000331e  83d100            adc        ecx, 0
00003321  0fa4c113          shld       ecx, eax, 0x13
00003325  807d3800          cmp        byte ptr [ebp + 0x38], 0
00003329  897c2418          mov        dword ptr [esp + 0x18], edi
0000332d  7451              je         0x3380
0000332f  3b4d30            cmp        ecx, dword ptr [ebp + 0x30]
00003332  7e7c              jle        0x33b0
00003334  8b542468          mov        edx, dword ptr [esp + 0x68]
00003338  01d1              add        ecx, edx
0000333a  89c8              mov        eax, ecx
0000333c  c1f81f            sar        eax, 0x1f
0000333f  0fa4c80d          shld       eax, ecx, 0xd
00003343  89442408          mov        dword ptr [esp + 8], eax
00003347  89c8              mov        eax, ecx
00003349  c1e00d            shl        eax, 0xd
0000334c  89442414          mov        dword ptr [esp + 0x14], eax
00003350  0f28c6            movaps     xmm0, xmm6
00003353  0f57d2            xorps      xmm2, xmm2
00003356  0fc2c202          cmpleps    xmm0, xmm2
0000335a  0f284c2420        movaps     xmm1, xmmword ptr [esp + 0x20]
0000335f  0fc2ca02          cmpleps    xmm1, xmm2
00003363  0f54c8            andps      xmm1, xmm0
00003366  660f7ec8          movd       eax, xmm1
0000336a  a801              test       al, 1
0000336c  0fb6db            movzx      ebx, bl
0000336f  b801000000        mov        eax, 1
00003374  0f45d8            cmovne     ebx, eax
00003377  895c240c          mov        dword ptr [esp + 0xc], ebx
0000337b  eb3f              jmp        0x33bc
0000337d  90                nop        
0000337e  90                nop        
0000337f  90                nop        
00003380  89542408          mov        dword ptr [esp + 8], edx
00003384  895c240c          mov        dword ptr [esp + 0xc], ebx
00003388  8d4101            lea        eax, [ecx + 1]
0000338b  89842400010000    mov        dword ptr [esp + 0x100], eax
00003392  8d4102            lea        eax, [ecx + 2]
00003395  89442410          mov        dword ptr [esp + 0x10], eax
00003399  8d4103            lea        eax, [ecx + 3]
0000339c  8b7d30            mov        edi, dword ptr [ebp + 0x30]
0000339f  eb62              jmp        0x3403
000033a1  90                nop        
000033a2  90                nop        
000033a3  90                nop        
000033a4  90                nop        
000033a5  90                nop        
000033a6  90                nop        
000033a7  90                nop        
000033a8  90                nop        
000033a9  90                nop        
000033aa  90                nop        
000033ab  90                nop        
000033ac  90                nop        
000033ad  90                nop        
000033ae  90                nop        
000033af  90                nop        
000033b0  89542408          mov        dword ptr [esp + 8], edx
000033b4  895c240c          mov        dword ptr [esp + 0xc], ebx
000033b8  8b542468          mov        edx, dword ptr [esp + 0x68]
000033bc  8d4102            lea        eax, [ecx + 2]
000033bf  8b7d30            mov        edi, dword ptr [ebp + 0x30]
000033c2  39f9              cmp        ecx, edi
000033c4  89d3              mov        ebx, edx
000033c6  be00000000        mov        esi, 0
000033cb  0f4cd6            cmovl      edx, esi
000033ce  8d7103            lea        esi, [ecx + 3]
000033d1  01ca              add        edx, ecx
000033d3  42                inc        edx
000033d4  89942400010000    mov        dword ptr [esp + 0x100], edx
000033db  39f8              cmp        eax, edi
000033dd  b800000000        mov        eax, 0
000033e2  0f4fc3            cmovg      eax, ebx
000033e5  89da              mov        edx, ebx
000033e7  01c8              add        eax, ecx
000033e9  83c002            add        eax, 2
000033ec  89442410          mov        dword ptr [esp + 0x10], eax
000033f0  39fe              cmp        esi, edi
000033f2  bb00000000        mov        ebx, 0
000033f7  0f4fda            cmovg      ebx, edx
000033fa  8d0419            lea        eax, [ecx + ebx]
000033fd  83c003            add        eax, 3
00003400  8b7508            mov        esi, dword ptr [ebp + 8]
00003403  39f9              cmp        ecx, edi
00003405  0f4dcf            cmovge     ecx, edi
00003408  8b5c240c          mov        ebx, dword ptr [esp + 0xc]
0000340c  0fb6db            movzx      ebx, bl
0000340f  ba01000000        mov        edx, 1
00003414  0f4fda            cmovg      ebx, edx
00003417  895c240c          mov        dword ptr [esp + 0xc], ebx
0000341b  8b9c2400010000    mov        ebx, dword ptr [esp + 0x100]
00003422  39fb              cmp        ebx, edi
00003424  0f4fd9            cmovg      ebx, ecx
00003427  8b542410          mov        edx, dword ptr [esp + 0x10]
0000342b  39fa              cmp        edx, edi
0000342d  0f4fd3            cmovg      edx, ebx
00003430  39f8              cmp        eax, edi
00003432  0f4fc2            cmovg      eax, edx
00003435  0f57c0            xorps      xmm0, xmm0
00003438  f30f2ac1          cvtsi2ss   xmm0, ecx
0000343c  89cf              mov        edi, ecx
0000343e  8b4d3c            mov        ecx, dword ptr [ebp + 0x3c]
00003441  d3e7              shl        edi, cl
00003443  d3e3              shl        ebx, cl
00003445  d3e2              shl        edx, cl
00003447  d3e0              shl        eax, cl
00003449  660f6e5c2414      movd       xmm3, dword ptr [esp + 0x14]
0000344f  8b4c2408          mov        ecx, dword ptr [esp + 8]
00003453  660f6ec9          movd       xmm1, ecx
00003457  660f62d9          punpckldq  xmm3, xmm1
0000345b  660f7f9c2400010000  movdqa     xmmword ptr [esp + 0x100], xmm3
00003464  660fd69c2440010000  movq       qword ptr [esp + 0x140], xmm3
0000346d  dfac2440010000    fild       qword ptr [esp + 0x140]
00003474  d99c24ec000000    fstp       dword ptr [esp + 0xec]
0000347b  f30f10a424ec000000  movss      xmm4, dword ptr [esp + 0xec]
00003484  f30f59a424c8000000  mulss      xmm4, dword ptr [esp + 0xc8]
0000348d  f30f5ce0          subss      xmm4, xmm0
00003491  0f28ec            movaps     xmm5, xmm4
00003494  f30f59ec          mulss      xmm5, xmm4
00003498  0f28c4            movaps     xmm0, xmm4
0000349b  f30f59c5          mulss      xmm0, xmm5
0000349f  0f285c2420        movaps     xmm3, xmmword ptr [esp + 0x20]
000034a4  660fefc9          pxor       xmm1, xmm1
000034a8  0f2ed9            ucomiss    xmm3, xmm1
000034ab  f20f117c2448      movsd      qword ptr [esp + 0x48], xmm7
000034b1  0f29742450        movaps     xmmword ptr [esp + 0x50], xmm6
000034b6  898424d4000000    mov        dword ptr [esp + 0xd4], eax
000034bd  89542410          mov        dword ptr [esp + 0x10], edx
000034c1  f30f11842484000000  movss      dword ptr [esp + 0x84], xmm0
000034ca  89bc24d0000000    mov        dword ptr [esp + 0xd0], edi
000034d1  0f8619010000      jbe        0x35f0
000034d7  8b74241c          mov        esi, dword ptr [esp + 0x1c]
000034db  0fbf0c7e          movsx      ecx, word ptr [esi + edi*2]
000034df  f30f2ad1          cvtsi2ss   xmm2, ecx
000034e3  0fbf0c5e          movsx      ecx, word ptr [esi + ebx*2]
000034e7  f30f2af9          cvtsi2ss   xmm7, ecx
000034eb  0fbf0c56          movsx      ecx, word ptr [esi + edx*2]
000034ef  f30f2af1          cvtsi2ss   xmm6, ecx
000034f3  0fbf0c46          movsx      ecx, word ptr [esi + eax*2]
000034f7  0f57c9            xorps      xmm1, xmm1
000034fa  f30f2ac9          cvtsi2ss   xmm1, ecx
000034fe  f30f118c24c4000000  movss      dword ptr [esp + 0xc4], xmm1
00003507  0f28ce            movaps     xmm1, xmm6
0000350a  f30f5cca          subss      xmm1, xmm2
0000350e  f30f59cc          mulss      xmm1, xmm4
00003512  0f28c7            movaps     xmm0, xmm7
00003515  f30f58c7          addss      xmm0, xmm7
00003519  f30f58c1          addss      xmm0, xmm1
0000351d  0f28cf            movaps     xmm1, xmm7
00003520  f30f594c247c      mulss      xmm1, dword ptr [esp + 0x7c]
00003526  f30f597c2474      mulss      xmm7, dword ptr [esp + 0x74]
0000352c  f30f5cfa          subss      xmm7, xmm2
00003530  f30f58d2          addss      xmm2, xmm2
00003534  f30f58d1          addss      xmm2, xmm1
00003538  0f28ce            movaps     xmm1, xmm6
0000353b  f30f594c2478      mulss      xmm1, dword ptr [esp + 0x78]
00003541  f30f58ca          addss      xmm1, xmm2
00003545  f30f109424c4000000  movss      xmm2, dword ptr [esp + 0xc4]
0000354e  f30f5cca          subss      xmm1, xmm2
00003552  f30f59cd          mulss      xmm1, xmm5
00003556  f30f58c8          addss      xmm1, xmm0
0000355a  f30f10842484000000  movss      xmm0, dword ptr [esp + 0x84]
00003563  f30f59742470      mulss      xmm6, dword ptr [esp + 0x70]
00003569  f30f58f7          addss      xmm6, xmm7
0000356d  f30f58f2          addss      xmm6, xmm2
00003571  f30f59f0          mulss      xmm6, xmm0
00003575  f30f58f1          addss      xmm6, xmm1
00003579  f30f5974246c      mulss      xmm6, dword ptr [esp + 0x6c]
0000357f  8b7c2444          mov        edi, dword ptr [esp + 0x44]
00003583  8b542418          mov        edx, dword ptr [esp + 0x18]
00003587  df6cd7f8          fild       qword ptr [edi + edx*8 - 8]
0000358b  d99c24e8000000    fstp       dword ptr [esp + 0xe8]
00003592  f30f59f3          mulss      xmm6, xmm3
00003596  f30f58b424e8000000  addss      xmm6, dword ptr [esp + 0xe8]
0000359f  f30f11b424b0000000  movss      dword ptr [esp + 0xb0], xmm6
000035a8  0f28742450        movaps     xmm6, xmmword ptr [esp + 0x50]
000035ad  d98424b0000000    fld        dword ptr [esp + 0xb0]
000035b4  dd8c24b0000000    fisttp     qword ptr [esp + 0xb0]
000035bb  8b8c24b0000000    mov        ecx, dword ptr [esp + 0xb0]
000035c2  8bb424b4000000    mov        esi, dword ptr [esp + 0xb4]
000035c9  8974d7fc          mov        dword ptr [edi + edx*8 - 4], esi
000035cd  8b7508            mov        esi, dword ptr [ebp + 8]
000035d0  894cd7f8          mov        dword ptr [edi + edx*8 - 8], ecx
000035d4  89d7              mov        edi, edx
000035d6  f30f585c243c      addss      xmm3, dword ptr [esp + 0x3c]
000035dc  0f295c2420        movaps     xmmword ptr [esp + 0x20], xmm3
000035e1  eb11              jmp        0x35f4
000035e3  90                nop        
000035e4  90                nop        
000035e5  90                nop        
000035e6  90                nop        
000035e7  90                nop        
000035e8  90                nop        
000035e9  90                nop        
000035ea  90                nop        
000035eb  90                nop        
000035ec  90                nop        
000035ed  90                nop        
000035ee  90                nop        
000035ef  90                nop        
000035f0  8b7c2418          mov        edi, dword ptr [esp + 0x18]
000035f4  0f57c9            xorps      xmm1, xmm1
000035f7  0f2ef1            ucomiss    xmm6, xmm1
000035fa  0f860a010000      jbe        0x370a
00003600  89fa              mov        edx, edi
00003602  8bbc2480000000    mov        edi, dword ptr [esp + 0x80]
00003609  8b8424d0000000    mov        eax, dword ptr [esp + 0xd0]
00003610  0fbf0c47          movsx      ecx, word ptr [edi + eax*2]
00003614  0f57d2            xorps      xmm2, xmm2
00003617  f30f2ad1          cvtsi2ss   xmm2, ecx
0000361b  0fbf0c5f          movsx      ecx, word ptr [edi + ebx*2]
0000361f  0f57ff            xorps      xmm7, xmm7
00003622  f30f2af9          cvtsi2ss   xmm7, ecx
00003626  8b442410          mov        eax, dword ptr [esp + 0x10]
0000362a  0fbf0447          movsx      eax, word ptr [edi + eax*2]
0000362e  0f57f6            xorps      xmm6, xmm6
00003631  f30f2af0          cvtsi2ss   xmm6, eax
00003635  8b8424d4000000    mov        eax, dword ptr [esp + 0xd4]
0000363c  0fbf0447          movsx      eax, word ptr [edi + eax*2]
00003640  89d7              mov        edi, edx
00003642  0f57db            xorps      xmm3, xmm3
00003645  f30f2ad8          cvtsi2ss   xmm3, eax
00003649  0f28c6            movaps     xmm0, xmm6
0000364c  f30f5cc2          subss      xmm0, xmm2
00003650  f30f59e0          mulss      xmm4, xmm0
00003654  0f28c7            movaps     xmm0, xmm7
00003657  f30f58c7          addss      xmm0, xmm7
0000365b  f30f58c4          addss      xmm0, xmm4
0000365f  0f28cf            movaps     xmm1, xmm7
00003662  f30f594c247c      mulss      xmm1, dword ptr [esp + 0x7c]
00003668  f30f597c2474      mulss      xmm7, dword ptr [esp + 0x74]
0000366e  f30f5cfa          subss      xmm7, xmm2
00003672  f30f58d2          addss      xmm2, xmm2
00003676  f30f58d1          addss      xmm2, xmm1
0000367a  0f28ce            movaps     xmm1, xmm6
0000367d  f30f594c2478      mulss      xmm1, dword ptr [esp + 0x78]
00003683  f30f58ca          addss      xmm1, xmm2
00003687  f30f5ccb          subss      xmm1, xmm3
0000368b  f30f59cd          mulss      xmm1, xmm5
0000368f  f30f58c8          addss      xmm1, xmm0
00003693  f30f59742470      mulss      xmm6, dword ptr [esp + 0x70]
00003699  f30f58f7          addss      xmm6, xmm7
0000369d  f30f58f3          addss      xmm6, xmm3
000036a1  f30f59b42484000000  mulss      xmm6, dword ptr [esp + 0x84]
000036aa  f30f58f1          addss      xmm6, xmm1
000036ae  f30f5974246c      mulss      xmm6, dword ptr [esp + 0x6c]
000036b4  8b542440          mov        edx, dword ptr [esp + 0x40]
000036b8  df6cfaf8          fild       qword ptr [edx + edi*8 - 8]
000036bc  d99c24e4000000    fstp       dword ptr [esp + 0xe4]
000036c3  f30f59742450      mulss      xmm6, dword ptr [esp + 0x50]
000036c9  f30f58b424e4000000  addss      xmm6, dword ptr [esp + 0xe4]
000036d2  f30f11b424a8000000  movss      dword ptr [esp + 0xa8], xmm6
000036db  0f28742450        movaps     xmm6, xmmword ptr [esp + 0x50]
000036e0  d98424a8000000    fld        dword ptr [esp + 0xa8]
000036e7  dd8c24a8000000    fisttp     qword ptr [esp + 0xa8]
000036ee  8b8424a8000000    mov        eax, dword ptr [esp + 0xa8]
000036f5  8b8c24ac000000    mov        ecx, dword ptr [esp + 0xac]
000036fc  894cfafc          mov        dword ptr [edx + edi*8 - 4], ecx
00003700  8944faf8          mov        dword ptr [edx + edi*8 - 8], eax
00003704  f30f58742438      addss      xmm6, dword ptr [esp + 0x38]
0000370a  8b4d2c            mov        ecx, dword ptr [ebp + 0x2c]
0000370d  f20f107c2448      movsd      xmm7, qword ptr [esp + 0x48]
00003713  8b5c240c          mov        ebx, dword ptr [esp + 0xc]
00003717  8b542408          mov        edx, dword ptr [esp + 8]
0000371b  f20f587c2460      addsd      xmm7, qword ptr [esp + 0x60]
00003721  0f57c0            xorps      xmm0, xmm0
00003724  660f2ef8          ucomisd    xmm7, xmm0
00003728  0f28842400010000  movaps     xmm0, xmmword ptr [esp + 0x100]
00003730  0f13842438010000  movlps     qword ptr [esp + 0x138], xmm0
00003738  dfac2438010000    fild       qword ptr [esp + 0x138]
0000373f  dd9c2430010000    fstp       qword ptr [esp + 0x130]
00003746  f20f10842430010000  movsd      xmm0, qword ptr [esp + 0x130]
0000374f  f20f58c7          addsd      xmm0, xmm7
00003753  f20f118424a0000000  movsd      qword ptr [esp + 0xa0], xmm0
0000375c  dd8424a0000000    fld        qword ptr [esp + 0xa0]
00003763  dd8c24a0000000    fisttp     qword ptr [esp + 0xa0]
0000376a  7612              jbe        0x377e
0000376c  8b9424a4000000    mov        edx, dword ptr [esp + 0xa4]
00003773  8b8424a0000000    mov        eax, dword ptr [esp + 0xa0]
0000377a  89442414          mov        dword ptr [esp + 0x14], eax
0000377e  39cf              cmp        edi, ecx
00003780  0f8db3020000      jge        0x3a39
00003786  89d8              mov        eax, ebx
00003788  2401              and        al, 1
0000378a  47                inc        edi
0000378b  84c0              test       al, al
0000378d  0f847dfbffff      je         0x3310
00003793  e9a1020000        jmp        0x3a39
00003798  8b54240c          mov        edx, dword ptr [esp + 0xc]
0000379c  897c2408          mov        dword ptr [esp + 8], edi
000037a0  8954240c          mov        dword ptr [esp + 0xc], edx
000037a4  8b7508            mov        esi, dword ptr [ebp + 8]
000037a7  8b06              mov        eax, dword ptr [esi]
000037a9  8b5c2434          mov        ebx, dword ptr [esp + 0x34]
000037ad  ff742410          push       dword ptr [esp + 0x10]
000037b1  ff74243c          push       dword ptr [esp + 0x3c]
000037b5  8b7d1c            mov        edi, dword ptr [ebp + 0x1c]
000037b8  57                push       edi
000037b9  56                push       esi
000037ba  ff90b4010000      call       dword ptr [eax + 0x1b4]
000037c0  83c410            add        esp, 0x10
000037c3  8b06              mov        eax, dword ptr [esi]
000037c5  ff742460          push       dword ptr [esp + 0x60]
000037c9  ff74241c          push       dword ptr [esp + 0x1c]
000037cd  57                push       edi
000037ce  56                push       esi
000037cf  ff90b4010000      call       dword ptr [eax + 0x1b4]
000037d5  e9ec020000        jmp        0x3ac6
000037da  31db              xor        ebx, ebx
000037dc  e958020000        jmp        0x3a39
000037e1  8b4d14            mov        ecx, dword ptr [ebp + 0x14]
000037e4  898c2400010000    mov        dword ptr [esp + 0x100], ecx
000037eb  85db              test       ebx, ebx
000037ed  8b542408          mov        edx, dword ptr [esp + 8]
000037f1  0f8e3a010000      jle        0x3931
000037f7  8b4d34            mov        ecx, dword ptr [ebp + 0x34]
000037fa  89ce              mov        esi, ecx
000037fc  c1fe1f            sar        esi, 0x1f
000037ff  0fa4ce0d          shld       esi, ecx, 0xd
00003803  89742450          mov        dword ptr [esp + 0x50], esi
00003807  c1e10d            shl        ecx, 0xd
0000380a  894c2418          mov        dword ptr [esp + 0x18], ecx
0000380e  8db04c500000      lea        esi, [eax + 0x504c]
00003814  8d804c100000      lea        eax, [eax + 0x104c]
0000381a  89442410          mov        dword ptr [esp + 0x10], eax
0000381e  0f57c9            xorps      xmm1, xmm1
00003821  31db              xor        ebx, ebx
00003823  b801000000        mov        eax, 1
00003828  90                nop        
00003829  90                nop        
0000382a  90                nop        
0000382b  90                nop        
0000382c  90                nop        
0000382d  90                nop        
0000382e  90                nop        
0000382f  90                nop        
00003830  89742420          mov        dword ptr [esp + 0x20], esi
00003834  89d1              mov        ecx, edx
00003836  c1f91f            sar        ecx, 0x1f
00003839  c1e913            shr        ecx, 0x13
0000383c  034c2414          add        ecx, dword ptr [esp + 0x14]
00003840  89542408          mov        dword ptr [esp + 8], edx
00003844  89d7              mov        edi, edx
00003846  83d700            adc        edi, 0
00003849  0fa4cf13          shld       edi, ecx, 0x13
0000384d  0f28c6            movaps     xmm0, xmm6
00003850  0fc2c102          cmpleps    xmm0, xmm1
00003854  0f28d3            movaps     xmm2, xmm3
00003857  0fc2d102          cmpleps    xmm2, xmm1
0000385b  0f54d0            andps      xmm2, xmm0
0000385e  660f7ed1          movd       ecx, xmm2
00003862  f6c101            test       cl, 1
00003865  0fb6cb            movzx      ecx, bl
00003868  89ca              mov        edx, ecx
0000386a  be01000000        mov        esi, 1
0000386f  0f45d6            cmovne     edx, esi
00003872  807d3800          cmp        byte ptr [ebp + 0x38], 0
00003876  0f44d6            cmove      edx, esi
00003879  0f94c3            sete       bl
0000387c  8b7530            mov        esi, dword ptr [ebp + 0x30]
0000387f  39f7              cmp        edi, esi
00003881  0f4ed1            cmovle     edx, ecx
00003884  8954240c          mov        dword ptr [esp + 0xc], edx
00003888  0f9ec1            setle      cl
0000388b  0f4dfe            cmovge     edi, esi
0000388e  08d9              or         cl, bl
00003890  7513              jne        0x38a5
00003892  8b4c2418          mov        ecx, dword ptr [esp + 0x18]
00003896  894c2414          mov        dword ptr [esp + 0x14], ecx
0000389a  8b4c2450          mov        ecx, dword ptr [esp + 0x50]
0000389e  894c2408          mov        dword ptr [esp + 8], ecx
000038a2  8b7d34            mov        edi, dword ptr [ebp + 0x34]
000038a5  8b4d3c            mov        ecx, dword ptr [ebp + 0x3c]
000038a8  d3e7              shl        edi, cl
000038aa  0f2ed9            ucomiss    xmm3, xmm1
000038ad  7623              jbe        0x38d2
000038af  8b4c241c          mov        ecx, dword ptr [esp + 0x1c]
000038b3  0fbf0c79          movsx      ecx, word ptr [ecx + edi*2]
000038b7  0f57c0            xorps      xmm0, xmm0
000038ba  f30f2ac1          cvtsi2ss   xmm0, ecx
000038be  f30f59c3          mulss      xmm0, xmm3
000038c2  f30f2cc8          cvttss2si  ecx, xmm0
000038c6  8b542410          mov        edx, dword ptr [esp + 0x10]
000038ca  890a              mov        dword ptr [edx], ecx
000038cc  f30f585c243c      addss      xmm3, dword ptr [esp + 0x3c]
000038d2  8b5d2c            mov        ebx, dword ptr [ebp + 0x2c]
000038d5  8b742420          mov        esi, dword ptr [esp + 0x20]
000038d9  0f2ef1            ucomiss    xmm6, xmm1
000038dc  7622              jbe        0x3900
000038de  8b8c2480000000    mov        ecx, dword ptr [esp + 0x80]
000038e5  0fbf0c79          movsx      ecx, word ptr [ecx + edi*2]
000038e9  0f57c0            xorps      xmm0, xmm0
000038ec  f30f2ac1          cvtsi2ss   xmm0, ecx
000038f0  f30f59c6          mulss      xmm0, xmm6
000038f4  f30f2cc8          cvttss2si  ecx, xmm0
000038f8  890e              mov        dword ptr [esi], ecx
000038fa  f30f58742438      addss      xmm6, dword ptr [esp + 0x38]
00003900  8144241400200000  add        dword ptr [esp + 0x14], 0x2000
00003908  8b542408          mov        edx, dword ptr [esp + 8]
0000390c  83d200            adc        edx, 0
0000390f  89df              mov        edi, ebx
00003911  39d8              cmp        eax, ebx
00003913  7d22              jge        0x3937
00003915  8b5c240c          mov        ebx, dword ptr [esp + 0xc]
00003919  89d9              mov        ecx, ebx
0000391b  80e101            and        cl, 1
0000391e  40                inc        eax
0000391f  83c604            add        esi, 4
00003922  8344241004        add        dword ptr [esp + 0x10], 4
00003927  84c9              test       cl, cl
00003929  0f8401ffffff      je         0x3830
0000392f  eb0a              jmp        0x393b
00003931  89df              mov        edi, ebx
00003933  31db              xor        ebx, ebx
00003935  eb04              jmp        0x393b
00003937  8b5c240c          mov        ebx, dword ptr [esp + 0xc]
0000393b  895c240c          mov        dword ptr [esp + 0xc], ebx
0000393f  0f295c2420        movaps     xmmword ptr [esp + 0x20], xmm3
00003944  8b4d10            mov        ecx, dword ptr [ebp + 0x10]
00003947  098c2400010000    or         dword ptr [esp + 0x100], ecx
0000394e  0f84c9000000      je         0x3a1d
00003954  8b5c2434          mov        ebx, dword ptr [esp + 0x34]
00003958  f20f108374baffff  movsd      xmm0, qword ptr [ebx - 0x458c]
00003960  f20f59c7          mulsd      xmm0, xmm7
00003964  f20f5ac0          cvtsd2ss   xmm0, xmm0
00003968  f30f11442408      movss      dword ptr [esp + 8], xmm0
0000396e  83ec20            sub        esp, 0x20
00003971  8b442464          mov        eax, dword ptr [esp + 0x64]
00003975  8944241c          mov        dword ptr [esp + 0x1c], eax
00003979  8d834c100000      lea        eax, [ebx + 0x104c]
0000397f  89442418          mov        dword ptr [esp + 0x18], eax
00003983  8b452c            mov        eax, dword ptr [ebp + 0x2c]
00003986  89442408          mov        dword ptr [esp + 8], eax
0000398a  890c24            mov        dword ptr [esp], ecx
0000398d  f30f11442404      movss      dword ptr [esp + 4], xmm0
00003993  c744241400803b47  mov        dword ptr [esp + 0x14], 0x473b8000
0000399b  c744241004000000  mov        dword ptr [esp + 0x10], 4
000039a3  c744240c00040000  mov        dword ptr [esp + 0xc], 0x400
000039ab  0f29742470        movaps     xmmword ptr [esp + 0x70], xmm6
000039b0  89d6              mov        esi, edx
000039b2  89cf              mov        edi, ecx
000039b4  e8a7040000        call       0x3e60
000039b9  83c420            add        esp, 0x20
000039bc  81c70cb00000      add        edi, 0xb00c
000039c2  83ec20            sub        esp, 0x20
000039c5  8b442460          mov        eax, dword ptr [esp + 0x60]
000039c9  8944241c          mov        dword ptr [esp + 0x1c], eax
000039cd  8d834c500000      lea        eax, [ebx + 0x504c]
000039d3  89442418          mov        dword ptr [esp + 0x18], eax
000039d7  8b452c            mov        eax, dword ptr [ebp + 0x2c]
000039da  89442408          mov        dword ptr [esp + 8], eax
000039de  f30f10442428      movss      xmm0, dword ptr [esp + 0x28]
000039e4  f30f11442404      movss      dword ptr [esp + 4], xmm0
000039ea  893c24            mov        dword ptr [esp], edi
000039ed  c744241400803b47  mov        dword ptr [esp + 0x14], 0x473b8000
000039f5  c744241004000000  mov        dword ptr [esp + 0x10], 4
000039fd  c744240c00040000  mov        dword ptr [esp + 0xc], 0x400
00003a05  e856040000        call       0x3e60
00003a0a  89f2              mov        edx, esi
00003a0c  0f28742470        movaps     xmm6, xmmword ptr [esp + 0x70]
00003a11  f20f107c2468      movsd      xmm7, qword ptr [esp + 0x68]
00003a17  8b7d2c            mov        edi, dword ptr [ebp + 0x2c]
00003a1a  83c420            add        esp, 0x20
00003a1d  0f57c0            xorps      xmm0, xmm0
00003a20  f20f2ac7          cvtsi2sd   xmm0, edi
00003a24  f20f104c2460      movsd      xmm1, qword ptr [esp + 0x60]
00003a2a  f20f59c8          mulsd      xmm1, xmm0
00003a2e  f20f58f9          addsd      xmm7, xmm1
00003a32  8b7508            mov        esi, dword ptr [ebp + 8]
00003a35  8b5c240c          mov        ebx, dword ptr [esp + 0xc]
00003a39  0f29742450        movaps     xmmword ptr [esp + 0x50], xmm6
00003a3e  89542408          mov        dword ptr [esp + 8], edx
00003a42  895c240c          mov        dword ptr [esp + 0xc], ebx
00003a46  8b06              mov        eax, dword ptr [esi]
00003a48  83ec20            sub        esp, 0x20
00003a4b  f20f117c240c      movsd      qword ptr [esp + 0xc], xmm7
00003a51  8b8c24e0000000    mov        ecx, dword ptr [esp + 0xe0]
00003a58  894c2408          mov        dword ptr [esp + 8], ecx
00003a5c  8b7d1c            mov        edi, dword ptr [ebp + 0x1c]
00003a5f  897c2404          mov        dword ptr [esp + 4], edi
00003a63  893424            mov        dword ptr [esp], esi
00003a66  8b5c2454          mov        ebx, dword ptr [esp + 0x54]
00003a6a  ff90c0010000      call       dword ptr [eax + 0x1c0]
00003a70  83c420            add        esp, 0x20
00003a73  8b06              mov        eax, dword ptr [esi]
00003a75  83ec10            sub        esp, 0x10
00003a78  0f28442430        movaps     xmm0, xmmword ptr [esp + 0x30]
00003a7d  f30f1144240c      movss      dword ptr [esp + 0xc], xmm0
00003a83  8b8c24cc000000    mov        ecx, dword ptr [esp + 0xcc]
00003a8a  894c2408          mov        dword ptr [esp + 8], ecx
00003a8e  897c2404          mov        dword ptr [esp + 4], edi
00003a92  893424            mov        dword ptr [esp], esi
00003a95  ff90bc010000      call       dword ptr [eax + 0x1bc]
00003a9b  83c410            add        esp, 0x10
00003a9e  8b06              mov        eax, dword ptr [esi]
00003aa0  83ec10            sub        esp, 0x10
00003aa3  0f28442460        movaps     xmm0, xmmword ptr [esp + 0x60]
00003aa8  f30f1144240c      movss      dword ptr [esp + 0xc], xmm0
00003aae  8b8c24c8000000    mov        ecx, dword ptr [esp + 0xc8]
00003ab5  894c2408          mov        dword ptr [esp + 8], ecx
00003ab9  897c2404          mov        dword ptr [esp + 4], edi
00003abd  893424            mov        dword ptr [esp], esi
00003ac0  ff90bc010000      call       dword ptr [eax + 0x1bc]
00003ac6  83c410            add        esp, 0x10
00003ac9  8b06              mov        eax, dword ptr [esi]
00003acb  83ec0c            sub        esp, 0xc
00003ace  ff742414          push       dword ptr [esp + 0x14]
00003ad2  ff742424          push       dword ptr [esp + 0x24]
00003ad6  ffb424e0000000    push       dword ptr [esp + 0xe0]
00003add  57                push       edi
00003ade  56                push       esi
00003adf  ff90b8010000      call       dword ptr [eax + 0x1b8]
00003ae5  83c420            add        esp, 0x20
00003ae8  8b06              mov        eax, dword ptr [esi]
00003aea  6a00              push       0
00003aec  ff742448          push       dword ptr [esp + 0x48]
00003af0  ff7524            push       dword ptr [ebp + 0x24]
00003af3  56                push       esi
00003af4  ff9010030000      call       dword ptr [eax + 0x310]
00003afa  83c410            add        esp, 0x10
00003afd  8b06              mov        eax, dword ptr [esi]
00003aff  6a00              push       0
00003b01  ff742444          push       dword ptr [esp + 0x44]
00003b05  ff7528            push       dword ptr [ebp + 0x28]
00003b08  56                push       esi
00003b09  ff9010030000      call       dword ptr [eax + 0x310]
00003b0f  83c410            add        esp, 0x10
00003b12  8b44240c          mov        eax, dword ptr [esp + 0xc]
00003b16  2401              and        al, 1
00003b18  8d65f4            lea        esp, [ebp - 0xc]
00003b1b  5e                pop        esi
00003b1c  5f                pop        edi
00003b1d  5b                pop        ebx
00003b1e  5d                pop        ebp
00003b1f  c3                ret        
