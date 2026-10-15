# APK member: lib/x86_64/libnequalizer.so
# Library SHA-256: 5bb0dc8806216597eac5b39540e6e15563919ae6c029617a4d93d22790a8596c
# Function: Java_sofeh_audio_NEqualizer_processLeft; address=0xde0; bytes=1430
# Decoder mode: default; decoded_bytes=1430
00000de0  4885d2            test       rdx, rdx
00000de3  0f848a050000      je         0x1373
00000de9  f3480f2ac9        cvtsi2ss   xmm1, rcx
00000dee  f30f105a20        movss      xmm3, dword ptr [rdx + 0x20]
00000df3  f30f105224        movss      xmm2, dword ptr [rdx + 0x24]
00000df8  0f28e1            movaps     xmm4, xmm1
00000dfb  f30f5ce2          subss      xmm4, xmm2
00000dff  f30f59e3          mulss      xmm4, xmm3
00000e03  f30f100549f9ffff  movss      xmm0, dword ptr [rip - 0x6b7]
00000e0b  f30f58d0          addss      xmm2, xmm0
00000e0f  f30f58d4          addss      xmm2, xmm4
00000e13  f30f115224        movss      dword ptr [rdx + 0x24], xmm2
00000e18  f30f106228        movss      xmm4, dword ptr [rdx + 0x28]
00000e1d  f30f5cd4          subss      xmm2, xmm4
00000e21  f30f59d3          mulss      xmm2, xmm3
00000e25  f30f58d4          addss      xmm2, xmm4
00000e29  f30f115228        movss      dword ptr [rdx + 0x28], xmm2
00000e2e  f30f10622c        movss      xmm4, dword ptr [rdx + 0x2c]
00000e33  f30f5cd4          subss      xmm2, xmm4
00000e37  f30f59d3          mulss      xmm2, xmm3
00000e3b  f30f58d4          addss      xmm2, xmm4
00000e3f  f30f11522c        movss      dword ptr [rdx + 0x2c], xmm2
00000e44  f30f106230        movss      xmm4, dword ptr [rdx + 0x30]
00000e49  f30f5cd4          subss      xmm2, xmm4
00000e4d  f30f59d3          mulss      xmm2, xmm3
00000e51  f30f58d4          addss      xmm2, xmm4
00000e55  f30f115230        movss      dword ptr [rdx + 0x30], xmm2
00000e5a  f20f105a34        movsd      xmm3, qword ptr [rdx + 0x34]
00000e5f  f20f115a38        movsd      qword ptr [rdx + 0x38], xmm3
00000e64  f30f114a34        movss      dword ptr [rdx + 0x34], xmm1
00000e69  837a4401          cmp        dword ptr [rdx + 0x44], 1
00000e6d  750b              jne        0xe7a
00000e6f  f30f16db          movshdup   xmm3, xmm3
00000e73  f30f5cda          subss      xmm3, xmm2
00000e77  0f28d3            movaps     xmm2, xmm3
00000e7a  f30f595240        mulss      xmm2, dword ptr [rdx + 0x40]
00000e7f  f3480f2cca        cvttss2si  rcx, xmm2
00000e84  f30f105a48        movss      xmm3, dword ptr [rdx + 0x48]
00000e89  f30f10524c        movss      xmm2, dword ptr [rdx + 0x4c]
00000e8e  0f28e1            movaps     xmm4, xmm1
00000e91  f30f5ce2          subss      xmm4, xmm2
00000e95  f30f59e3          mulss      xmm4, xmm3
00000e99  f30f58d0          addss      xmm2, xmm0
00000e9d  f30f58d4          addss      xmm2, xmm4
00000ea1  f30f11524c        movss      dword ptr [rdx + 0x4c], xmm2
00000ea6  f30f106250        movss      xmm4, dword ptr [rdx + 0x50]
00000eab  f30f5cd4          subss      xmm2, xmm4
00000eaf  f30f59d3          mulss      xmm2, xmm3
00000eb3  f30f58d4          addss      xmm2, xmm4
00000eb7  f30f115250        movss      dword ptr [rdx + 0x50], xmm2
00000ebc  f30f106254        movss      xmm4, dword ptr [rdx + 0x54]
00000ec1  f30f5cd4          subss      xmm2, xmm4
00000ec5  f30f59d3          mulss      xmm2, xmm3
00000ec9  f30f58d4          addss      xmm2, xmm4
00000ecd  f30f115254        movss      dword ptr [rdx + 0x54], xmm2
00000ed2  f30f106258        movss      xmm4, dword ptr [rdx + 0x58]
00000ed7  f30f5cd4          subss      xmm2, xmm4
00000edb  f30f59d3          mulss      xmm2, xmm3
00000edf  f30f58d4          addss      xmm2, xmm4
00000ee3  f30f115258        movss      dword ptr [rdx + 0x58], xmm2
00000ee8  f20f105a5c        movsd      xmm3, qword ptr [rdx + 0x5c]
00000eed  f20f115a60        movsd      qword ptr [rdx + 0x60], xmm3
00000ef2  f30f114a5c        movss      dword ptr [rdx + 0x5c], xmm1
00000ef7  837a6c01          cmp        dword ptr [rdx + 0x6c], 1
00000efb  750b              jne        0xf08
00000efd  f30f16db          movshdup   xmm3, xmm3
00000f01  f30f5cda          subss      xmm3, xmm2
00000f05  0f28d3            movaps     xmm2, xmm3
00000f08  f30f595268        mulss      xmm2, dword ptr [rdx + 0x68]
00000f0d  f3480f2cc2        cvttss2si  rax, xmm2
00000f12  4801c8            add        rax, rcx
00000f15  f30f105a70        movss      xmm3, dword ptr [rdx + 0x70]
00000f1a  f30f105274        movss      xmm2, dword ptr [rdx + 0x74]
00000f1f  0f28e1            movaps     xmm4, xmm1
00000f22  f30f5ce2          subss      xmm4, xmm2
00000f26  f30f59e3          mulss      xmm4, xmm3
00000f2a  f30f58d0          addss      xmm2, xmm0
00000f2e  f30f58d4          addss      xmm2, xmm4
00000f32  f30f115274        movss      dword ptr [rdx + 0x74], xmm2
00000f37  f30f106278        movss      xmm4, dword ptr [rdx + 0x78]
00000f3c  f30f5cd4          subss      xmm2, xmm4
00000f40  f30f59d3          mulss      xmm2, xmm3
00000f44  f30f58d4          addss      xmm2, xmm4
00000f48  f30f115278        movss      dword ptr [rdx + 0x78], xmm2
00000f4d  f30f10627c        movss      xmm4, dword ptr [rdx + 0x7c]
00000f52  f30f5cd4          subss      xmm2, xmm4
00000f56  f30f59d3          mulss      xmm2, xmm3
00000f5a  f30f58d4          addss      xmm2, xmm4
00000f5e  f30f11527c        movss      dword ptr [rdx + 0x7c], xmm2
00000f63  f30f10a280000000  movss      xmm4, dword ptr [rdx + 0x80]
00000f6b  f30f5cd4          subss      xmm2, xmm4
00000f6f  f30f59d3          mulss      xmm2, xmm3
00000f73  f30f58d4          addss      xmm2, xmm4
00000f77  f30f119280000000  movss      dword ptr [rdx + 0x80], xmm2
00000f7f  f20f109a84000000  movsd      xmm3, qword ptr [rdx + 0x84]
00000f87  f20f119a88000000  movsd      qword ptr [rdx + 0x88], xmm3
00000f8f  f30f118a84000000  movss      dword ptr [rdx + 0x84], xmm1
00000f97  83ba9400000001    cmp        dword ptr [rdx + 0x94], 1
00000f9e  750b              jne        0xfab
00000fa0  f30f16db          movshdup   xmm3, xmm3
00000fa4  f30f5cda          subss      xmm3, xmm2
00000fa8  0f28d3            movaps     xmm2, xmm3
00000fab  f30f599290000000  mulss      xmm2, dword ptr [rdx + 0x90]
00000fb3  f3480f2cca        cvttss2si  rcx, xmm2
00000fb8  4801c8            add        rax, rcx
00000fbb  f30f109a98000000  movss      xmm3, dword ptr [rdx + 0x98]
00000fc3  f30f10929c000000  movss      xmm2, dword ptr [rdx + 0x9c]
00000fcb  0f28e1            movaps     xmm4, xmm1
00000fce  f30f5ce2          subss      xmm4, xmm2
00000fd2  f30f59e3          mulss      xmm4, xmm3
00000fd6  f30f58d0          addss      xmm2, xmm0
00000fda  f30f58d4          addss      xmm2, xmm4
00000fde  f30f11929c000000  movss      dword ptr [rdx + 0x9c], xmm2
00000fe6  f30f10a2a0000000  movss      xmm4, dword ptr [rdx + 0xa0]
00000fee  f30f5cd4          subss      xmm2, xmm4
00000ff2  f30f59d3          mulss      xmm2, xmm3
00000ff6  f30f58d4          addss      xmm2, xmm4
00000ffa  f30f1192a0000000  movss      dword ptr [rdx + 0xa0], xmm2
00001002  f30f10a2a4000000  movss      xmm4, dword ptr [rdx + 0xa4]
0000100a  f30f5cd4          subss      xmm2, xmm4
0000100e  f30f59d3          mulss      xmm2, xmm3
00001012  f30f58d4          addss      xmm2, xmm4
00001016  f30f1192a4000000  movss      dword ptr [rdx + 0xa4], xmm2
0000101e  f30f10a2a8000000  movss      xmm4, dword ptr [rdx + 0xa8]
00001026  f30f5cd4          subss      xmm2, xmm4
0000102a  f30f59d3          mulss      xmm2, xmm3
0000102e  f30f58d4          addss      xmm2, xmm4
00001032  f30f1192a8000000  movss      dword ptr [rdx + 0xa8], xmm2
0000103a  f20f109aac000000  movsd      xmm3, qword ptr [rdx + 0xac]
00001042  f20f119ab0000000  movsd      qword ptr [rdx + 0xb0], xmm3
0000104a  f30f118aac000000  movss      dword ptr [rdx + 0xac], xmm1
00001052  83babc00000001    cmp        dword ptr [rdx + 0xbc], 1
00001059  750b              jne        0x1066
0000105b  f30f16db          movshdup   xmm3, xmm3
0000105f  f30f5cda          subss      xmm3, xmm2
00001063  0f28d3            movaps     xmm2, xmm3
00001066  f30f5992b8000000  mulss      xmm2, dword ptr [rdx + 0xb8]
0000106e  f3480f2cca        cvttss2si  rcx, xmm2
00001073  4801c8            add        rax, rcx
00001076  f30f109ac0000000  movss      xmm3, dword ptr [rdx + 0xc0]
0000107e  f30f1092c4000000  movss      xmm2, dword ptr [rdx + 0xc4]
00001086  0f28e1            movaps     xmm4, xmm1
00001089  f30f5ce2          subss      xmm4, xmm2
0000108d  f30f59e3          mulss      xmm4, xmm3
00001091  f30f58d0          addss      xmm2, xmm0
00001095  f30f58d4          addss      xmm2, xmm4
00001099  f30f1192c4000000  movss      dword ptr [rdx + 0xc4], xmm2
000010a1  f30f10a2c8000000  movss      xmm4, dword ptr [rdx + 0xc8]
000010a9  f30f5cd4          subss      xmm2, xmm4
000010ad  f30f59d3          mulss      xmm2, xmm3
000010b1  f30f58d4          addss      xmm2, xmm4
000010b5  f30f1192c8000000  movss      dword ptr [rdx + 0xc8], xmm2
000010bd  f30f10a2cc000000  movss      xmm4, dword ptr [rdx + 0xcc]
000010c5  f30f5cd4          subss      xmm2, xmm4
000010c9  f30f59d3          mulss      xmm2, xmm3
000010cd  f30f58d4          addss      xmm2, xmm4
000010d1  f30f1192cc000000  movss      dword ptr [rdx + 0xcc], xmm2
000010d9  f30f10a2d0000000  movss      xmm4, dword ptr [rdx + 0xd0]
000010e1  f30f5cd4          subss      xmm2, xmm4
000010e5  f30f59d3          mulss      xmm2, xmm3
000010e9  f30f58d4          addss      xmm2, xmm4
000010ed  f30f1192d0000000  movss      dword ptr [rdx + 0xd0], xmm2
000010f5  f20f109ad4000000  movsd      xmm3, qword ptr [rdx + 0xd4]
000010fd  f20f119ad8000000  movsd      qword ptr [rdx + 0xd8], xmm3
00001105  f30f118ad4000000  movss      dword ptr [rdx + 0xd4], xmm1
0000110d  83bae400000001    cmp        dword ptr [rdx + 0xe4], 1
00001114  750b              jne        0x1121
00001116  f30f16db          movshdup   xmm3, xmm3
0000111a  f30f5cda          subss      xmm3, xmm2
0000111e  0f28d3            movaps     xmm2, xmm3
00001121  f30f5992e0000000  mulss      xmm2, dword ptr [rdx + 0xe0]
00001129  f3480f2cca        cvttss2si  rcx, xmm2
0000112e  4801c8            add        rax, rcx
00001131  f30f109ae8000000  movss      xmm3, dword ptr [rdx + 0xe8]
00001139  f30f1092ec000000  movss      xmm2, dword ptr [rdx + 0xec]
00001141  0f28e1            movaps     xmm4, xmm1
00001144  f30f5ce2          subss      xmm4, xmm2
00001148  f30f59e3          mulss      xmm4, xmm3
0000114c  f30f58d0          addss      xmm2, xmm0
00001150  f30f58d4          addss      xmm2, xmm4
00001154  f30f1192ec000000  movss      dword ptr [rdx + 0xec], xmm2
0000115c  f30f10a2f0000000  movss      xmm4, dword ptr [rdx + 0xf0]
00001164  f30f5cd4          subss      xmm2, xmm4
00001168  f30f59d3          mulss      xmm2, xmm3
0000116c  f30f58d4          addss      xmm2, xmm4
00001170  f30f1192f0000000  movss      dword ptr [rdx + 0xf0], xmm2
00001178  f30f10a2f4000000  movss      xmm4, dword ptr [rdx + 0xf4]
00001180  f30f5cd4          subss      xmm2, xmm4
00001184  f30f59d3          mulss      xmm2, xmm3
00001188  f30f58d4          addss      xmm2, xmm4
0000118c  f30f1192f4000000  movss      dword ptr [rdx + 0xf4], xmm2
00001194  f30f10a2f8000000  movss      xmm4, dword ptr [rdx + 0xf8]
0000119c  f30f5cd4          subss      xmm2, xmm4
000011a0  f30f59d3          mulss      xmm2, xmm3
000011a4  f30f58d4          addss      xmm2, xmm4
000011a8  f30f1192f8000000  movss      dword ptr [rdx + 0xf8], xmm2
000011b0  f20f109afc000000  movsd      xmm3, qword ptr [rdx + 0xfc]
000011b8  f20f119a00010000  movsd      qword ptr [rdx + 0x100], xmm3
000011c0  f30f118afc000000  movss      dword ptr [rdx + 0xfc], xmm1
000011c8  83ba0c01000001    cmp        dword ptr [rdx + 0x10c], 1
000011cf  750b              jne        0x11dc
000011d1  f30f16db          movshdup   xmm3, xmm3
000011d5  f30f5cda          subss      xmm3, xmm2
000011d9  0f28d3            movaps     xmm2, xmm3
000011dc  f30f599208010000  mulss      xmm2, dword ptr [rdx + 0x108]
000011e4  f3480f2cca        cvttss2si  rcx, xmm2
000011e9  4801c8            add        rax, rcx
000011ec  f30f109a10010000  movss      xmm3, dword ptr [rdx + 0x110]
000011f4  f30f109214010000  movss      xmm2, dword ptr [rdx + 0x114]
000011fc  0f28e1            movaps     xmm4, xmm1
000011ff  f30f5ce2          subss      xmm4, xmm2
00001203  f30f59e3          mulss      xmm4, xmm3
00001207  f30f58d0          addss      xmm2, xmm0
0000120b  f30f58d4          addss      xmm2, xmm4
0000120f  f30f119214010000  movss      dword ptr [rdx + 0x114], xmm2
00001217  f30f10a218010000  movss      xmm4, dword ptr [rdx + 0x118]
0000121f  f30f5cd4          subss      xmm2, xmm4
00001223  f30f59d3          mulss      xmm2, xmm3
00001227  f30f58d4          addss      xmm2, xmm4
0000122b  f30f119218010000  movss      dword ptr [rdx + 0x118], xmm2
00001233  f30f10a21c010000  movss      xmm4, dword ptr [rdx + 0x11c]
0000123b  f30f5cd4          subss      xmm2, xmm4
0000123f  f30f59d3          mulss      xmm2, xmm3
00001243  f30f58d4          addss      xmm2, xmm4
00001247  f30f11921c010000  movss      dword ptr [rdx + 0x11c], xmm2
0000124f  f30f10a220010000  movss      xmm4, dword ptr [rdx + 0x120]
00001257  f30f5cd4          subss      xmm2, xmm4
0000125b  f30f59d3          mulss      xmm2, xmm3
0000125f  f30f58d4          addss      xmm2, xmm4
00001263  f30f119220010000  movss      dword ptr [rdx + 0x120], xmm2
0000126b  f20f109a24010000  movsd      xmm3, qword ptr [rdx + 0x124]
00001273  f20f119a28010000  movsd      qword ptr [rdx + 0x128], xmm3
0000127b  f30f118a24010000  movss      dword ptr [rdx + 0x124], xmm1
00001283  83ba3401000001    cmp        dword ptr [rdx + 0x134], 1
0000128a  750b              jne        0x1297
0000128c  f30f16db          movshdup   xmm3, xmm3
00001290  f30f5cda          subss      xmm3, xmm2
00001294  0f28d3            movaps     xmm2, xmm3
00001297  f30f599230010000  mulss      xmm2, dword ptr [rdx + 0x130]
0000129f  f3480f2cca        cvttss2si  rcx, xmm2
000012a4  4801c8            add        rax, rcx
000012a7  f30f109238010000  movss      xmm2, dword ptr [rdx + 0x138]
000012af  f30f109a3c010000  movss      xmm3, dword ptr [rdx + 0x13c]
000012b7  0f28e1            movaps     xmm4, xmm1
000012ba  f30f5ce3          subss      xmm4, xmm3
000012be  f30f59e2          mulss      xmm4, xmm2
000012c2  f30f58c3          addss      xmm0, xmm3
000012c6  f30f58c4          addss      xmm0, xmm4
000012ca  f30f11823c010000  movss      dword ptr [rdx + 0x13c], xmm0
000012d2  f30f109a40010000  movss      xmm3, dword ptr [rdx + 0x140]
000012da  f30f5cc3          subss      xmm0, xmm3
000012de  f30f59c2          mulss      xmm0, xmm2
000012e2  f30f58c3          addss      xmm0, xmm3
000012e6  f30f118240010000  movss      dword ptr [rdx + 0x140], xmm0
000012ee  f30f109a44010000  movss      xmm3, dword ptr [rdx + 0x144]
000012f6  f30f5cc3          subss      xmm0, xmm3
000012fa  f30f59c2          mulss      xmm0, xmm2
000012fe  f30f58c3          addss      xmm0, xmm3
00001302  f30f118244010000  movss      dword ptr [rdx + 0x144], xmm0
0000130a  f30f109a48010000  movss      xmm3, dword ptr [rdx + 0x148]
00001312  f30f5cc3          subss      xmm0, xmm3
00001316  f30f59c2          mulss      xmm0, xmm2
0000131a  f30f58c3          addss      xmm0, xmm3
0000131e  f30f118248010000  movss      dword ptr [rdx + 0x148], xmm0
00001326  f20f10924c010000  movsd      xmm2, qword ptr [rdx + 0x14c]
0000132e  f20f119250010000  movsd      qword ptr [rdx + 0x150], xmm2
00001336  f30f118a4c010000  movss      dword ptr [rdx + 0x14c], xmm1
0000133e  83ba5c01000001    cmp        dword ptr [rdx + 0x15c], 1
00001345  750b              jne        0x1352
00001347  f30f16ca          movshdup   xmm1, xmm2
0000134b  f30f5cc8          subss      xmm1, xmm0
0000134f  0f28c1            movaps     xmm0, xmm1
00001352  f30f598258010000  mulss      xmm0, dword ptr [rdx + 0x158]
0000135a  f3480f2cc8        cvttss2si  rcx, xmm0
0000135f  4889c2            mov        rdx, rax
00001362  4801ca            add        rdx, rcx
00001365  488d440103        lea        rax, [rcx + rax + 3]
0000136a  480f49c2          cmovns     rax, rdx
0000136e  48c1f802          sar        rax, 2
00001372  c3                ret        
00001373  31c0              xor        eax, eax
00001375  c3                ret        
