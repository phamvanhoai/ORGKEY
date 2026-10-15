# APK member: lib/x86_64/libnequalizer.so
# Library SHA-256: 5bb0dc8806216597eac5b39540e6e15563919ae6c029617a4d93d22790a8596c
# Function: Java_sofeh_audio_NEqualizer_processRight; address=0x1380; bytes=1535
# Decoder mode: default; decoded_bytes=1535
00001380  4885d2            test       rdx, rdx
00001383  0f84f3050000      je         0x197c
00001389  f3480f2ac9        cvtsi2ss   xmm1, rcx
0000138e  f30f109a60010000  movss      xmm3, dword ptr [rdx + 0x160]
00001396  f30f109264010000  movss      xmm2, dword ptr [rdx + 0x164]
0000139e  0f28e1            movaps     xmm4, xmm1
000013a1  f30f5ce2          subss      xmm4, xmm2
000013a5  f30f59e3          mulss      xmm4, xmm3
000013a9  f30f1005a3f3ffff  movss      xmm0, dword ptr [rip - 0xc5d]
000013b1  f30f58d0          addss      xmm2, xmm0
000013b5  f30f58d4          addss      xmm2, xmm4
000013b9  f30f119264010000  movss      dword ptr [rdx + 0x164], xmm2
000013c1  f30f10a268010000  movss      xmm4, dword ptr [rdx + 0x168]
000013c9  f30f5cd4          subss      xmm2, xmm4
000013cd  f30f59d3          mulss      xmm2, xmm3
000013d1  f30f58d4          addss      xmm2, xmm4
000013d5  f30f119268010000  movss      dword ptr [rdx + 0x168], xmm2
000013dd  f30f10a26c010000  movss      xmm4, dword ptr [rdx + 0x16c]
000013e5  f30f5cd4          subss      xmm2, xmm4
000013e9  f30f59d3          mulss      xmm2, xmm3
000013ed  f30f58d4          addss      xmm2, xmm4
000013f1  f30f11926c010000  movss      dword ptr [rdx + 0x16c], xmm2
000013f9  f30f10a270010000  movss      xmm4, dword ptr [rdx + 0x170]
00001401  f30f5cd4          subss      xmm2, xmm4
00001405  f30f59d3          mulss      xmm2, xmm3
00001409  f30f58d4          addss      xmm2, xmm4
0000140d  f30f119270010000  movss      dword ptr [rdx + 0x170], xmm2
00001415  f20f109a74010000  movsd      xmm3, qword ptr [rdx + 0x174]
0000141d  f20f119a78010000  movsd      qword ptr [rdx + 0x178], xmm3
00001425  f30f118a74010000  movss      dword ptr [rdx + 0x174], xmm1
0000142d  83ba8401000001    cmp        dword ptr [rdx + 0x184], 1
00001434  750b              jne        0x1441
00001436  f30f16db          movshdup   xmm3, xmm3
0000143a  f30f5cda          subss      xmm3, xmm2
0000143e  0f28d3            movaps     xmm2, xmm3
00001441  f30f599280010000  mulss      xmm2, dword ptr [rdx + 0x180]
00001449  f3480f2cca        cvttss2si  rcx, xmm2
0000144e  f30f109a88010000  movss      xmm3, dword ptr [rdx + 0x188]
00001456  f30f10928c010000  movss      xmm2, dword ptr [rdx + 0x18c]
0000145e  0f28e1            movaps     xmm4, xmm1
00001461  f30f5ce2          subss      xmm4, xmm2
00001465  f30f59e3          mulss      xmm4, xmm3
00001469  f30f58d0          addss      xmm2, xmm0
0000146d  f30f58d4          addss      xmm2, xmm4
00001471  f30f11928c010000  movss      dword ptr [rdx + 0x18c], xmm2
00001479  f30f10a290010000  movss      xmm4, dword ptr [rdx + 0x190]
00001481  f30f5cd4          subss      xmm2, xmm4
00001485  f30f59d3          mulss      xmm2, xmm3
00001489  f30f58d4          addss      xmm2, xmm4
0000148d  f30f119290010000  movss      dword ptr [rdx + 0x190], xmm2
00001495  f30f10a294010000  movss      xmm4, dword ptr [rdx + 0x194]
0000149d  f30f5cd4          subss      xmm2, xmm4
000014a1  f30f59d3          mulss      xmm2, xmm3
000014a5  f30f58d4          addss      xmm2, xmm4
000014a9  f30f119294010000  movss      dword ptr [rdx + 0x194], xmm2
000014b1  f30f10a298010000  movss      xmm4, dword ptr [rdx + 0x198]
000014b9  f30f5cd4          subss      xmm2, xmm4
000014bd  f30f59d3          mulss      xmm2, xmm3
000014c1  f30f58d4          addss      xmm2, xmm4
000014c5  f30f119298010000  movss      dword ptr [rdx + 0x198], xmm2
000014cd  f20f109a9c010000  movsd      xmm3, qword ptr [rdx + 0x19c]
000014d5  f20f119aa0010000  movsd      qword ptr [rdx + 0x1a0], xmm3
000014dd  f30f118a9c010000  movss      dword ptr [rdx + 0x19c], xmm1
000014e5  83baac01000001    cmp        dword ptr [rdx + 0x1ac], 1
000014ec  750b              jne        0x14f9
000014ee  f30f16db          movshdup   xmm3, xmm3
000014f2  f30f5cda          subss      xmm3, xmm2
000014f6  0f28d3            movaps     xmm2, xmm3
000014f9  f30f5992a8010000  mulss      xmm2, dword ptr [rdx + 0x1a8]
00001501  f3480f2cc2        cvttss2si  rax, xmm2
00001506  4801c8            add        rax, rcx
00001509  f30f109ab0010000  movss      xmm3, dword ptr [rdx + 0x1b0]
00001511  f30f1092b4010000  movss      xmm2, dword ptr [rdx + 0x1b4]
00001519  0f28e1            movaps     xmm4, xmm1
0000151c  f30f5ce2          subss      xmm4, xmm2
00001520  f30f59e3          mulss      xmm4, xmm3
00001524  f30f58d0          addss      xmm2, xmm0
00001528  f30f58d4          addss      xmm2, xmm4
0000152c  f30f1192b4010000  movss      dword ptr [rdx + 0x1b4], xmm2
00001534  f30f10a2b8010000  movss      xmm4, dword ptr [rdx + 0x1b8]
0000153c  f30f5cd4          subss      xmm2, xmm4
00001540  f30f59d3          mulss      xmm2, xmm3
00001544  f30f58d4          addss      xmm2, xmm4
00001548  f30f1192b8010000  movss      dword ptr [rdx + 0x1b8], xmm2
00001550  f30f10a2bc010000  movss      xmm4, dword ptr [rdx + 0x1bc]
00001558  f30f5cd4          subss      xmm2, xmm4
0000155c  f30f59d3          mulss      xmm2, xmm3
00001560  f30f58d4          addss      xmm2, xmm4
00001564  f30f1192bc010000  movss      dword ptr [rdx + 0x1bc], xmm2
0000156c  f30f10a2c0010000  movss      xmm4, dword ptr [rdx + 0x1c0]
00001574  f30f5cd4          subss      xmm2, xmm4
00001578  f30f59d3          mulss      xmm2, xmm3
0000157c  f30f58d4          addss      xmm2, xmm4
00001580  f30f1192c0010000  movss      dword ptr [rdx + 0x1c0], xmm2
00001588  f20f109ac4010000  movsd      xmm3, qword ptr [rdx + 0x1c4]
00001590  f20f119ac8010000  movsd      qword ptr [rdx + 0x1c8], xmm3
00001598  f30f118ac4010000  movss      dword ptr [rdx + 0x1c4], xmm1
000015a0  83bad401000001    cmp        dword ptr [rdx + 0x1d4], 1
000015a7  750b              jne        0x15b4
000015a9  f30f16db          movshdup   xmm3, xmm3
000015ad  f30f5cda          subss      xmm3, xmm2
000015b1  0f28d3            movaps     xmm2, xmm3
000015b4  f30f5992d0010000  mulss      xmm2, dword ptr [rdx + 0x1d0]
000015bc  f3480f2cca        cvttss2si  rcx, xmm2
000015c1  4801c8            add        rax, rcx
000015c4  f30f109ad8010000  movss      xmm3, dword ptr [rdx + 0x1d8]
000015cc  f30f1092dc010000  movss      xmm2, dword ptr [rdx + 0x1dc]
000015d4  0f28e1            movaps     xmm4, xmm1
000015d7  f30f5ce2          subss      xmm4, xmm2
000015db  f30f59e3          mulss      xmm4, xmm3
000015df  f30f58d0          addss      xmm2, xmm0
000015e3  f30f58d4          addss      xmm2, xmm4
000015e7  f30f1192dc010000  movss      dword ptr [rdx + 0x1dc], xmm2
000015ef  f30f10a2e0010000  movss      xmm4, dword ptr [rdx + 0x1e0]
000015f7  f30f5cd4          subss      xmm2, xmm4
000015fb  f30f59d3          mulss      xmm2, xmm3
000015ff  f30f58d4          addss      xmm2, xmm4
00001603  f30f1192e0010000  movss      dword ptr [rdx + 0x1e0], xmm2
0000160b  f30f10a2e4010000  movss      xmm4, dword ptr [rdx + 0x1e4]
00001613  f30f5cd4          subss      xmm2, xmm4
00001617  f30f59d3          mulss      xmm2, xmm3
0000161b  f30f58d4          addss      xmm2, xmm4
0000161f  f30f1192e4010000  movss      dword ptr [rdx + 0x1e4], xmm2
00001627  f30f10a2e8010000  movss      xmm4, dword ptr [rdx + 0x1e8]
0000162f  f30f5cd4          subss      xmm2, xmm4
00001633  f30f59d3          mulss      xmm2, xmm3
00001637  f30f58d4          addss      xmm2, xmm4
0000163b  f30f1192e8010000  movss      dword ptr [rdx + 0x1e8], xmm2
00001643  f20f109aec010000  movsd      xmm3, qword ptr [rdx + 0x1ec]
0000164b  f20f119af0010000  movsd      qword ptr [rdx + 0x1f0], xmm3
00001653  f30f118aec010000  movss      dword ptr [rdx + 0x1ec], xmm1
0000165b  83bafc01000001    cmp        dword ptr [rdx + 0x1fc], 1
00001662  750b              jne        0x166f
00001664  f30f16db          movshdup   xmm3, xmm3
00001668  f30f5cda          subss      xmm3, xmm2
0000166c  0f28d3            movaps     xmm2, xmm3
0000166f  f30f5992f8010000  mulss      xmm2, dword ptr [rdx + 0x1f8]
00001677  f3480f2cca        cvttss2si  rcx, xmm2
0000167c  4801c8            add        rax, rcx
0000167f  f30f109a00020000  movss      xmm3, dword ptr [rdx + 0x200]
00001687  f30f109204020000  movss      xmm2, dword ptr [rdx + 0x204]
0000168f  0f28e1            movaps     xmm4, xmm1
00001692  f30f5ce2          subss      xmm4, xmm2
00001696  f30f59e3          mulss      xmm4, xmm3
0000169a  f30f58d0          addss      xmm2, xmm0
0000169e  f30f58d4          addss      xmm2, xmm4
000016a2  f30f119204020000  movss      dword ptr [rdx + 0x204], xmm2
000016aa  f30f10a208020000  movss      xmm4, dword ptr [rdx + 0x208]
000016b2  f30f5cd4          subss      xmm2, xmm4
000016b6  f30f59d3          mulss      xmm2, xmm3
000016ba  f30f58d4          addss      xmm2, xmm4
000016be  f30f119208020000  movss      dword ptr [rdx + 0x208], xmm2
000016c6  f30f10a20c020000  movss      xmm4, dword ptr [rdx + 0x20c]
000016ce  f30f5cd4          subss      xmm2, xmm4
000016d2  f30f59d3          mulss      xmm2, xmm3
000016d6  f30f58d4          addss      xmm2, xmm4
000016da  f30f11920c020000  movss      dword ptr [rdx + 0x20c], xmm2
000016e2  f30f10a210020000  movss      xmm4, dword ptr [rdx + 0x210]
000016ea  f30f5cd4          subss      xmm2, xmm4
000016ee  f30f59d3          mulss      xmm2, xmm3
000016f2  f30f58d4          addss      xmm2, xmm4
000016f6  f30f119210020000  movss      dword ptr [rdx + 0x210], xmm2
000016fe  f20f109a14020000  movsd      xmm3, qword ptr [rdx + 0x214]
00001706  f20f119a18020000  movsd      qword ptr [rdx + 0x218], xmm3
0000170e  f30f118a14020000  movss      dword ptr [rdx + 0x214], xmm1
00001716  83ba2402000001    cmp        dword ptr [rdx + 0x224], 1
0000171d  750b              jne        0x172a
0000171f  f30f16db          movshdup   xmm3, xmm3
00001723  f30f5cda          subss      xmm3, xmm2
00001727  0f28d3            movaps     xmm2, xmm3
0000172a  f30f599220020000  mulss      xmm2, dword ptr [rdx + 0x220]
00001732  f3480f2cca        cvttss2si  rcx, xmm2
00001737  4801c8            add        rax, rcx
0000173a  f30f109a28020000  movss      xmm3, dword ptr [rdx + 0x228]
00001742  f30f10922c020000  movss      xmm2, dword ptr [rdx + 0x22c]
0000174a  0f28e1            movaps     xmm4, xmm1
0000174d  f30f5ce2          subss      xmm4, xmm2
00001751  f30f59e3          mulss      xmm4, xmm3
00001755  f30f58d0          addss      xmm2, xmm0
00001759  f30f58d4          addss      xmm2, xmm4
0000175d  f30f11922c020000  movss      dword ptr [rdx + 0x22c], xmm2
00001765  f30f10a230020000  movss      xmm4, dword ptr [rdx + 0x230]
0000176d  f30f5cd4          subss      xmm2, xmm4
00001771  f30f59d3          mulss      xmm2, xmm3
00001775  f30f58d4          addss      xmm2, xmm4
00001779  f30f119230020000  movss      dword ptr [rdx + 0x230], xmm2
00001781  f30f10a234020000  movss      xmm4, dword ptr [rdx + 0x234]
00001789  f30f5cd4          subss      xmm2, xmm4
0000178d  f30f59d3          mulss      xmm2, xmm3
00001791  f30f58d4          addss      xmm2, xmm4
00001795  f30f119234020000  movss      dword ptr [rdx + 0x234], xmm2
0000179d  f30f10a238020000  movss      xmm4, dword ptr [rdx + 0x238]
000017a5  f30f5cd4          subss      xmm2, xmm4
000017a9  f30f59d3          mulss      xmm2, xmm3
000017ad  f30f58d4          addss      xmm2, xmm4
000017b1  f30f119238020000  movss      dword ptr [rdx + 0x238], xmm2
000017b9  f20f109a3c020000  movsd      xmm3, qword ptr [rdx + 0x23c]
000017c1  f20f119a40020000  movsd      qword ptr [rdx + 0x240], xmm3
000017c9  f30f118a3c020000  movss      dword ptr [rdx + 0x23c], xmm1
000017d1  83ba4c02000001    cmp        dword ptr [rdx + 0x24c], 1
000017d8  750b              jne        0x17e5
000017da  f30f16db          movshdup   xmm3, xmm3
000017de  f30f5cda          subss      xmm3, xmm2
000017e2  0f28d3            movaps     xmm2, xmm3
000017e5  f30f599248020000  mulss      xmm2, dword ptr [rdx + 0x248]
000017ed  f3480f2cca        cvttss2si  rcx, xmm2
000017f2  4801c8            add        rax, rcx
000017f5  f30f109a50020000  movss      xmm3, dword ptr [rdx + 0x250]
000017fd  f30f109254020000  movss      xmm2, dword ptr [rdx + 0x254]
00001805  0f28e1            movaps     xmm4, xmm1
00001808  f30f5ce2          subss      xmm4, xmm2
0000180c  f30f59e3          mulss      xmm4, xmm3
00001810  f30f58d0          addss      xmm2, xmm0
00001814  f30f58d4          addss      xmm2, xmm4
00001818  f30f119254020000  movss      dword ptr [rdx + 0x254], xmm2
00001820  f30f10a258020000  movss      xmm4, dword ptr [rdx + 0x258]
00001828  f30f5cd4          subss      xmm2, xmm4
0000182c  f30f59d3          mulss      xmm2, xmm3
00001830  f30f58d4          addss      xmm2, xmm4
00001834  f30f119258020000  movss      dword ptr [rdx + 0x258], xmm2
0000183c  f30f10a25c020000  movss      xmm4, dword ptr [rdx + 0x25c]
00001844  f30f5cd4          subss      xmm2, xmm4
00001848  f30f59d3          mulss      xmm2, xmm3
0000184c  f30f58d4          addss      xmm2, xmm4
00001850  f30f11925c020000  movss      dword ptr [rdx + 0x25c], xmm2
00001858  f30f10a260020000  movss      xmm4, dword ptr [rdx + 0x260]
00001860  f30f5cd4          subss      xmm2, xmm4
00001864  f30f59d3          mulss      xmm2, xmm3
00001868  f30f58d4          addss      xmm2, xmm4
0000186c  f30f119260020000  movss      dword ptr [rdx + 0x260], xmm2
00001874  f20f109a64020000  movsd      xmm3, qword ptr [rdx + 0x264]
0000187c  f20f119a68020000  movsd      qword ptr [rdx + 0x268], xmm3
00001884  f30f118a64020000  movss      dword ptr [rdx + 0x264], xmm1
0000188c  83ba7402000001    cmp        dword ptr [rdx + 0x274], 1
00001893  750b              jne        0x18a0
00001895  f30f16db          movshdup   xmm3, xmm3
00001899  f30f5cda          subss      xmm3, xmm2
0000189d  0f28d3            movaps     xmm2, xmm3
000018a0  f30f599270020000  mulss      xmm2, dword ptr [rdx + 0x270]
000018a8  f3480f2cca        cvttss2si  rcx, xmm2
000018ad  4801c8            add        rax, rcx
000018b0  f30f109278020000  movss      xmm2, dword ptr [rdx + 0x278]
000018b8  f30f109a7c020000  movss      xmm3, dword ptr [rdx + 0x27c]
000018c0  0f28e1            movaps     xmm4, xmm1
000018c3  f30f5ce3          subss      xmm4, xmm3
000018c7  f30f59e2          mulss      xmm4, xmm2
000018cb  f30f58c3          addss      xmm0, xmm3
000018cf  f30f58c4          addss      xmm0, xmm4
000018d3  f30f11827c020000  movss      dword ptr [rdx + 0x27c], xmm0
000018db  f30f109a80020000  movss      xmm3, dword ptr [rdx + 0x280]
000018e3  f30f5cc3          subss      xmm0, xmm3
000018e7  f30f59c2          mulss      xmm0, xmm2
000018eb  f30f58c3          addss      xmm0, xmm3
000018ef  f30f118280020000  movss      dword ptr [rdx + 0x280], xmm0
000018f7  f30f109a84020000  movss      xmm3, dword ptr [rdx + 0x284]
000018ff  f30f5cc3          subss      xmm0, xmm3
00001903  f30f59c2          mulss      xmm0, xmm2
00001907  f30f58c3          addss      xmm0, xmm3
0000190b  f30f118284020000  movss      dword ptr [rdx + 0x284], xmm0
00001913  f30f109a88020000  movss      xmm3, dword ptr [rdx + 0x288]
0000191b  f30f5cc3          subss      xmm0, xmm3
0000191f  f30f59c2          mulss      xmm0, xmm2
00001923  f30f58c3          addss      xmm0, xmm3
00001927  f30f118288020000  movss      dword ptr [rdx + 0x288], xmm0
0000192f  f20f10928c020000  movsd      xmm2, qword ptr [rdx + 0x28c]
00001937  f20f119290020000  movsd      qword ptr [rdx + 0x290], xmm2
0000193f  f30f118a8c020000  movss      dword ptr [rdx + 0x28c], xmm1
00001947  83ba9c02000001    cmp        dword ptr [rdx + 0x29c], 1
0000194e  750b              jne        0x195b
00001950  f30f16ca          movshdup   xmm1, xmm2
00001954  f30f5cc8          subss      xmm1, xmm0
00001958  0f28c1            movaps     xmm0, xmm1
0000195b  f30f598298020000  mulss      xmm0, dword ptr [rdx + 0x298]
00001963  f3480f2cc8        cvttss2si  rcx, xmm0
00001968  4889c2            mov        rdx, rax
0000196b  4801ca            add        rdx, rcx
0000196e  488d440103        lea        rax, [rcx + rax + 3]
00001973  480f49c2          cmovns     rax, rdx
00001977  48c1f802          sar        rax, 2
0000197b  c3                ret        
0000197c  31c0              xor        eax, eax
0000197e  c3                ret        
