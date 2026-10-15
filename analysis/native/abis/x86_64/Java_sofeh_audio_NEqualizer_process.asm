# APK member: lib/x86_64/libnequalizer.so
# Library SHA-256: 5bb0dc8806216597eac5b39540e6e15563919ae6c029617a4d93d22790a8596c
# Function: Java_sofeh_audio_NEqualizer_process; address=0x1980; bytes=1426
# Decoder mode: default; decoded_bytes=1426
00001980  4885d2            test       rdx, rdx
00001983  0f8486050000      je         0x1f0f
00001989  0fbfc1            movsx      eax, cx
0000198c  f30f2ac8          cvtsi2ss   xmm1, eax
00001990  f30f105a20        movss      xmm3, dword ptr [rdx + 0x20]
00001995  f30f105224        movss      xmm2, dword ptr [rdx + 0x24]
0000199a  0f28e1            movaps     xmm4, xmm1
0000199d  f30f5ce2          subss      xmm4, xmm2
000019a1  f30f59e3          mulss      xmm4, xmm3
000019a5  f30f1005a7edffff  movss      xmm0, dword ptr [rip - 0x1259]
000019ad  f30f58d0          addss      xmm2, xmm0
000019b1  f30f58d4          addss      xmm2, xmm4
000019b5  f30f115224        movss      dword ptr [rdx + 0x24], xmm2
000019ba  f30f106228        movss      xmm4, dword ptr [rdx + 0x28]
000019bf  f30f5cd4          subss      xmm2, xmm4
000019c3  f30f59d3          mulss      xmm2, xmm3
000019c7  f30f58d4          addss      xmm2, xmm4
000019cb  f30f115228        movss      dword ptr [rdx + 0x28], xmm2
000019d0  f30f10622c        movss      xmm4, dword ptr [rdx + 0x2c]
000019d5  f30f5cd4          subss      xmm2, xmm4
000019d9  f30f59d3          mulss      xmm2, xmm3
000019dd  f30f58d4          addss      xmm2, xmm4
000019e1  f30f11522c        movss      dword ptr [rdx + 0x2c], xmm2
000019e6  f30f106230        movss      xmm4, dword ptr [rdx + 0x30]
000019eb  f30f5cd4          subss      xmm2, xmm4
000019ef  f30f59d3          mulss      xmm2, xmm3
000019f3  f30f58d4          addss      xmm2, xmm4
000019f7  f30f115230        movss      dword ptr [rdx + 0x30], xmm2
000019fc  f20f105a34        movsd      xmm3, qword ptr [rdx + 0x34]
00001a01  f20f115a38        movsd      qword ptr [rdx + 0x38], xmm3
00001a06  f30f114a34        movss      dword ptr [rdx + 0x34], xmm1
00001a0b  837a4401          cmp        dword ptr [rdx + 0x44], 1
00001a0f  750b              jne        0x1a1c
00001a11  f30f16db          movshdup   xmm3, xmm3
00001a15  f30f5cda          subss      xmm3, xmm2
00001a19  0f28d3            movaps     xmm2, xmm3
00001a1c  f30f595240        mulss      xmm2, dword ptr [rdx + 0x40]
00001a21  f3480f2cca        cvttss2si  rcx, xmm2
00001a26  f30f105a48        movss      xmm3, dword ptr [rdx + 0x48]
00001a2b  f30f10524c        movss      xmm2, dword ptr [rdx + 0x4c]
00001a30  0f28e1            movaps     xmm4, xmm1
00001a33  f30f5ce2          subss      xmm4, xmm2
00001a37  f30f59e3          mulss      xmm4, xmm3
00001a3b  f30f58d0          addss      xmm2, xmm0
00001a3f  f30f58d4          addss      xmm2, xmm4
00001a43  f30f11524c        movss      dword ptr [rdx + 0x4c], xmm2
00001a48  f30f106250        movss      xmm4, dword ptr [rdx + 0x50]
00001a4d  f30f5cd4          subss      xmm2, xmm4
00001a51  f30f59d3          mulss      xmm2, xmm3
00001a55  f30f58d4          addss      xmm2, xmm4
00001a59  f30f115250        movss      dword ptr [rdx + 0x50], xmm2
00001a5e  f30f106254        movss      xmm4, dword ptr [rdx + 0x54]
00001a63  f30f5cd4          subss      xmm2, xmm4
00001a67  f30f59d3          mulss      xmm2, xmm3
00001a6b  f30f58d4          addss      xmm2, xmm4
00001a6f  f30f115254        movss      dword ptr [rdx + 0x54], xmm2
00001a74  f30f106258        movss      xmm4, dword ptr [rdx + 0x58]
00001a79  f30f5cd4          subss      xmm2, xmm4
00001a7d  f30f59d3          mulss      xmm2, xmm3
00001a81  f30f58d4          addss      xmm2, xmm4
00001a85  f30f115258        movss      dword ptr [rdx + 0x58], xmm2
00001a8a  f20f105a5c        movsd      xmm3, qword ptr [rdx + 0x5c]
00001a8f  f20f115a60        movsd      qword ptr [rdx + 0x60], xmm3
00001a94  f30f114a5c        movss      dword ptr [rdx + 0x5c], xmm1
00001a99  837a6c01          cmp        dword ptr [rdx + 0x6c], 1
00001a9d  750b              jne        0x1aaa
00001a9f  f30f16db          movshdup   xmm3, xmm3
00001aa3  f30f5cda          subss      xmm3, xmm2
00001aa7  0f28d3            movaps     xmm2, xmm3
00001aaa  f30f595268        mulss      xmm2, dword ptr [rdx + 0x68]
00001aaf  f3480f2cc2        cvttss2si  rax, xmm2
00001ab4  01c8              add        eax, ecx
00001ab6  f30f105a70        movss      xmm3, dword ptr [rdx + 0x70]
00001abb  f30f105274        movss      xmm2, dword ptr [rdx + 0x74]
00001ac0  0f28e1            movaps     xmm4, xmm1
00001ac3  f30f5ce2          subss      xmm4, xmm2
00001ac7  f30f59e3          mulss      xmm4, xmm3
00001acb  f30f58d0          addss      xmm2, xmm0
00001acf  f30f58d4          addss      xmm2, xmm4
00001ad3  f30f115274        movss      dword ptr [rdx + 0x74], xmm2
00001ad8  f30f106278        movss      xmm4, dword ptr [rdx + 0x78]
00001add  f30f5cd4          subss      xmm2, xmm4
00001ae1  f30f59d3          mulss      xmm2, xmm3
00001ae5  f30f58d4          addss      xmm2, xmm4
00001ae9  f30f115278        movss      dword ptr [rdx + 0x78], xmm2
00001aee  f30f10627c        movss      xmm4, dword ptr [rdx + 0x7c]
00001af3  f30f5cd4          subss      xmm2, xmm4
00001af7  f30f59d3          mulss      xmm2, xmm3
00001afb  f30f58d4          addss      xmm2, xmm4
00001aff  f30f11527c        movss      dword ptr [rdx + 0x7c], xmm2
00001b04  f30f10a280000000  movss      xmm4, dword ptr [rdx + 0x80]
00001b0c  f30f5cd4          subss      xmm2, xmm4
00001b10  f30f59d3          mulss      xmm2, xmm3
00001b14  f30f58d4          addss      xmm2, xmm4
00001b18  f30f119280000000  movss      dword ptr [rdx + 0x80], xmm2
00001b20  f20f109a84000000  movsd      xmm3, qword ptr [rdx + 0x84]
00001b28  f20f119a88000000  movsd      qword ptr [rdx + 0x88], xmm3
00001b30  f30f118a84000000  movss      dword ptr [rdx + 0x84], xmm1
00001b38  83ba9400000001    cmp        dword ptr [rdx + 0x94], 1
00001b3f  750b              jne        0x1b4c
00001b41  f30f16db          movshdup   xmm3, xmm3
00001b45  f30f5cda          subss      xmm3, xmm2
00001b49  0f28d3            movaps     xmm2, xmm3
00001b4c  f30f599290000000  mulss      xmm2, dword ptr [rdx + 0x90]
00001b54  f3480f2cca        cvttss2si  rcx, xmm2
00001b59  01c8              add        eax, ecx
00001b5b  f30f109a98000000  movss      xmm3, dword ptr [rdx + 0x98]
00001b63  f30f10929c000000  movss      xmm2, dword ptr [rdx + 0x9c]
00001b6b  0f28e1            movaps     xmm4, xmm1
00001b6e  f30f5ce2          subss      xmm4, xmm2
00001b72  f30f59e3          mulss      xmm4, xmm3
00001b76  f30f58d0          addss      xmm2, xmm0
00001b7a  f30f58d4          addss      xmm2, xmm4
00001b7e  f30f11929c000000  movss      dword ptr [rdx + 0x9c], xmm2
00001b86  f30f10a2a0000000  movss      xmm4, dword ptr [rdx + 0xa0]
00001b8e  f30f5cd4          subss      xmm2, xmm4
00001b92  f30f59d3          mulss      xmm2, xmm3
00001b96  f30f58d4          addss      xmm2, xmm4
00001b9a  f30f1192a0000000  movss      dword ptr [rdx + 0xa0], xmm2
00001ba2  f30f10a2a4000000  movss      xmm4, dword ptr [rdx + 0xa4]
00001baa  f30f5cd4          subss      xmm2, xmm4
00001bae  f30f59d3          mulss      xmm2, xmm3
00001bb2  f30f58d4          addss      xmm2, xmm4
00001bb6  f30f1192a4000000  movss      dword ptr [rdx + 0xa4], xmm2
00001bbe  f30f10a2a8000000  movss      xmm4, dword ptr [rdx + 0xa8]
00001bc6  f30f5cd4          subss      xmm2, xmm4
00001bca  f30f59d3          mulss      xmm2, xmm3
00001bce  f30f58d4          addss      xmm2, xmm4
00001bd2  f30f1192a8000000  movss      dword ptr [rdx + 0xa8], xmm2
00001bda  f20f109aac000000  movsd      xmm3, qword ptr [rdx + 0xac]
00001be2  f20f119ab0000000  movsd      qword ptr [rdx + 0xb0], xmm3
00001bea  f30f118aac000000  movss      dword ptr [rdx + 0xac], xmm1
00001bf2  83babc00000001    cmp        dword ptr [rdx + 0xbc], 1
00001bf9  750b              jne        0x1c06
00001bfb  f30f16db          movshdup   xmm3, xmm3
00001bff  f30f5cda          subss      xmm3, xmm2
00001c03  0f28d3            movaps     xmm2, xmm3
00001c06  f30f5992b8000000  mulss      xmm2, dword ptr [rdx + 0xb8]
00001c0e  f3480f2cca        cvttss2si  rcx, xmm2
00001c13  01c8              add        eax, ecx
00001c15  f30f109ac0000000  movss      xmm3, dword ptr [rdx + 0xc0]
00001c1d  f30f1092c4000000  movss      xmm2, dword ptr [rdx + 0xc4]
00001c25  0f28e1            movaps     xmm4, xmm1
00001c28  f30f5ce2          subss      xmm4, xmm2
00001c2c  f30f59e3          mulss      xmm4, xmm3
00001c30  f30f58d0          addss      xmm2, xmm0
00001c34  f30f58d4          addss      xmm2, xmm4
00001c38  f30f1192c4000000  movss      dword ptr [rdx + 0xc4], xmm2
00001c40  f30f10a2c8000000  movss      xmm4, dword ptr [rdx + 0xc8]
00001c48  f30f5cd4          subss      xmm2, xmm4
00001c4c  f30f59d3          mulss      xmm2, xmm3
00001c50  f30f58d4          addss      xmm2, xmm4
00001c54  f30f1192c8000000  movss      dword ptr [rdx + 0xc8], xmm2
00001c5c  f30f10a2cc000000  movss      xmm4, dword ptr [rdx + 0xcc]
00001c64  f30f5cd4          subss      xmm2, xmm4
00001c68  f30f59d3          mulss      xmm2, xmm3
00001c6c  f30f58d4          addss      xmm2, xmm4
00001c70  f30f1192cc000000  movss      dword ptr [rdx + 0xcc], xmm2
00001c78  f30f10a2d0000000  movss      xmm4, dword ptr [rdx + 0xd0]
00001c80  f30f5cd4          subss      xmm2, xmm4
00001c84  f30f59d3          mulss      xmm2, xmm3
00001c88  f30f58d4          addss      xmm2, xmm4
00001c8c  f30f1192d0000000  movss      dword ptr [rdx + 0xd0], xmm2
00001c94  f20f109ad4000000  movsd      xmm3, qword ptr [rdx + 0xd4]
00001c9c  f20f119ad8000000  movsd      qword ptr [rdx + 0xd8], xmm3
00001ca4  f30f118ad4000000  movss      dword ptr [rdx + 0xd4], xmm1
00001cac  83bae400000001    cmp        dword ptr [rdx + 0xe4], 1
00001cb3  750b              jne        0x1cc0
00001cb5  f30f16db          movshdup   xmm3, xmm3
00001cb9  f30f5cda          subss      xmm3, xmm2
00001cbd  0f28d3            movaps     xmm2, xmm3
00001cc0  f30f5992e0000000  mulss      xmm2, dword ptr [rdx + 0xe0]
00001cc8  f3480f2cca        cvttss2si  rcx, xmm2
00001ccd  01c8              add        eax, ecx
00001ccf  f30f109ae8000000  movss      xmm3, dword ptr [rdx + 0xe8]
00001cd7  f30f1092ec000000  movss      xmm2, dword ptr [rdx + 0xec]
00001cdf  0f28e1            movaps     xmm4, xmm1
00001ce2  f30f5ce2          subss      xmm4, xmm2
00001ce6  f30f59e3          mulss      xmm4, xmm3
00001cea  f30f58d0          addss      xmm2, xmm0
00001cee  f30f58d4          addss      xmm2, xmm4
00001cf2  f30f1192ec000000  movss      dword ptr [rdx + 0xec], xmm2
00001cfa  f30f10a2f0000000  movss      xmm4, dword ptr [rdx + 0xf0]
00001d02  f30f5cd4          subss      xmm2, xmm4
00001d06  f30f59d3          mulss      xmm2, xmm3
00001d0a  f30f58d4          addss      xmm2, xmm4
00001d0e  f30f1192f0000000  movss      dword ptr [rdx + 0xf0], xmm2
00001d16  f30f10a2f4000000  movss      xmm4, dword ptr [rdx + 0xf4]
00001d1e  f30f5cd4          subss      xmm2, xmm4
00001d22  f30f59d3          mulss      xmm2, xmm3
00001d26  f30f58d4          addss      xmm2, xmm4
00001d2a  f30f1192f4000000  movss      dword ptr [rdx + 0xf4], xmm2
00001d32  f30f10a2f8000000  movss      xmm4, dword ptr [rdx + 0xf8]
00001d3a  f30f5cd4          subss      xmm2, xmm4
00001d3e  f30f59d3          mulss      xmm2, xmm3
00001d42  f30f58d4          addss      xmm2, xmm4
00001d46  f30f1192f8000000  movss      dword ptr [rdx + 0xf8], xmm2
00001d4e  f20f109afc000000  movsd      xmm3, qword ptr [rdx + 0xfc]
00001d56  f20f119a00010000  movsd      qword ptr [rdx + 0x100], xmm3
00001d5e  f30f118afc000000  movss      dword ptr [rdx + 0xfc], xmm1
00001d66  83ba0c01000001    cmp        dword ptr [rdx + 0x10c], 1
00001d6d  750b              jne        0x1d7a
00001d6f  f30f16db          movshdup   xmm3, xmm3
00001d73  f30f5cda          subss      xmm3, xmm2
00001d77  0f28d3            movaps     xmm2, xmm3
00001d7a  f30f599208010000  mulss      xmm2, dword ptr [rdx + 0x108]
00001d82  f3480f2cca        cvttss2si  rcx, xmm2
00001d87  01c8              add        eax, ecx
00001d89  f30f109a10010000  movss      xmm3, dword ptr [rdx + 0x110]
00001d91  f30f109214010000  movss      xmm2, dword ptr [rdx + 0x114]
00001d99  0f28e1            movaps     xmm4, xmm1
00001d9c  f30f5ce2          subss      xmm4, xmm2
00001da0  f30f59e3          mulss      xmm4, xmm3
00001da4  f30f58d0          addss      xmm2, xmm0
00001da8  f30f58d4          addss      xmm2, xmm4
00001dac  f30f119214010000  movss      dword ptr [rdx + 0x114], xmm2
00001db4  f30f10a218010000  movss      xmm4, dword ptr [rdx + 0x118]
00001dbc  f30f5cd4          subss      xmm2, xmm4
00001dc0  f30f59d3          mulss      xmm2, xmm3
00001dc4  f30f58d4          addss      xmm2, xmm4
00001dc8  f30f119218010000  movss      dword ptr [rdx + 0x118], xmm2
00001dd0  f30f10a21c010000  movss      xmm4, dword ptr [rdx + 0x11c]
00001dd8  f30f5cd4          subss      xmm2, xmm4
00001ddc  f30f59d3          mulss      xmm2, xmm3
00001de0  f30f58d4          addss      xmm2, xmm4
00001de4  f30f11921c010000  movss      dword ptr [rdx + 0x11c], xmm2
00001dec  f30f10a220010000  movss      xmm4, dword ptr [rdx + 0x120]
00001df4  f30f5cd4          subss      xmm2, xmm4
00001df8  f30f59d3          mulss      xmm2, xmm3
00001dfc  f30f58d4          addss      xmm2, xmm4
00001e00  f30f119220010000  movss      dword ptr [rdx + 0x120], xmm2
00001e08  f20f109a24010000  movsd      xmm3, qword ptr [rdx + 0x124]
00001e10  f20f119a28010000  movsd      qword ptr [rdx + 0x128], xmm3
00001e18  f30f118a24010000  movss      dword ptr [rdx + 0x124], xmm1
00001e20  83ba3401000001    cmp        dword ptr [rdx + 0x134], 1
00001e27  750b              jne        0x1e34
00001e29  f30f16db          movshdup   xmm3, xmm3
00001e2d  f30f5cda          subss      xmm3, xmm2
00001e31  0f28d3            movaps     xmm2, xmm3
00001e34  f30f599230010000  mulss      xmm2, dword ptr [rdx + 0x130]
00001e3c  f3480f2cca        cvttss2si  rcx, xmm2
00001e41  01c8              add        eax, ecx
00001e43  f30f109238010000  movss      xmm2, dword ptr [rdx + 0x138]
00001e4b  f30f109a3c010000  movss      xmm3, dword ptr [rdx + 0x13c]
00001e53  0f28e1            movaps     xmm4, xmm1
00001e56  f30f5ce3          subss      xmm4, xmm3
00001e5a  f30f59e2          mulss      xmm4, xmm2
00001e5e  f30f58c3          addss      xmm0, xmm3
00001e62  f30f58c4          addss      xmm0, xmm4
00001e66  f30f11823c010000  movss      dword ptr [rdx + 0x13c], xmm0
00001e6e  f30f109a40010000  movss      xmm3, dword ptr [rdx + 0x140]
00001e76  f30f5cc3          subss      xmm0, xmm3
00001e7a  f30f59c2          mulss      xmm0, xmm2
00001e7e  f30f58c3          addss      xmm0, xmm3
00001e82  f30f118240010000  movss      dword ptr [rdx + 0x140], xmm0
00001e8a  f30f109a44010000  movss      xmm3, dword ptr [rdx + 0x144]
00001e92  f30f5cc3          subss      xmm0, xmm3
00001e96  f30f59c2          mulss      xmm0, xmm2
00001e9a  f30f58c3          addss      xmm0, xmm3
00001e9e  f30f118244010000  movss      dword ptr [rdx + 0x144], xmm0
00001ea6  f30f109a48010000  movss      xmm3, dword ptr [rdx + 0x148]
00001eae  f30f5cc3          subss      xmm0, xmm3
00001eb2  f30f59c2          mulss      xmm0, xmm2
00001eb6  f30f58c3          addss      xmm0, xmm3
00001eba  f30f118248010000  movss      dword ptr [rdx + 0x148], xmm0
00001ec2  f20f10924c010000  movsd      xmm2, qword ptr [rdx + 0x14c]
00001eca  f20f119250010000  movsd      qword ptr [rdx + 0x150], xmm2
00001ed2  f30f118a4c010000  movss      dword ptr [rdx + 0x14c], xmm1
00001eda  83ba5c01000001    cmp        dword ptr [rdx + 0x15c], 1
00001ee1  750b              jne        0x1eee
00001ee3  f30f16ca          movshdup   xmm1, xmm2
00001ee7  f30f5cc8          subss      xmm1, xmm0
00001eeb  0f28c1            movaps     xmm0, xmm1
00001eee  f30f598258010000  mulss      xmm0, dword ptr [rdx + 0x158]
00001ef6  f3480f2cc8        cvttss2si  rcx, xmm0
00001efb  89c2              mov        edx, eax
00001efd  01ca              add        edx, ecx
00001eff  01c8              add        eax, ecx
00001f01  83c003            add        eax, 3
00001f04  6685d2            test       dx, dx
00001f07  0f49c2            cmovns     eax, edx
00001f0a  98                cwde       
00001f0b  c1e802            shr        eax, 2
00001f0e  c3                ret        
00001f0f  31c0              xor        eax, eax
00001f11  c3                ret        
