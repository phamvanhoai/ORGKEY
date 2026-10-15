# APK member: lib/x86_64/libnmusic.so
# Library SHA-256: 792e4ca002affd7fa432bd2a8dbf94dc31b829ef32123a5954dd6e376627e35c
# Function: Java_sofeh_music_NMusic_Mix2; address=0x2650; bytes=169
# Decoder mode: default; decoded_bytes=169
00002650  f3480f2aea        cvtsi2ss   xmm5, rdx
00002655  f30f592d0fe7ffff  mulss      xmm5, dword ptr [rip - 0x18f1]
0000265d  f30f2af1          cvtsi2ss   xmm6, ecx
00002661  f30f5cee          subss      xmm5, xmm6
00002665  0f28f5            movaps     xmm6, xmm5
00002668  f30f59f5          mulss      xmm6, xmm5
0000266c  440f28c3          movaps     xmm8, xmm3
00002670  f3440f5cc1        subss      xmm8, xmm1
00002675  f3440f59c5        mulss      xmm8, xmm5
0000267a  f30f59ee          mulss      xmm5, xmm6
0000267e  0f28fa            movaps     xmm7, xmm2
00002681  f30f58fa          addss      xmm7, xmm2
00002685  f3410f58f8        addss      xmm7, xmm8
0000268a  f3440f1005dde6ffff  movss      xmm8, dword ptr [rip - 0x1923]
00002693  f3440f59c2        mulss      xmm8, xmm2
00002698  f30f5915e8e6ffff  mulss      xmm2, dword ptr [rip - 0x1918]
000026a0  f30f5cd1          subss      xmm2, xmm1
000026a4  f30f58c9          addss      xmm1, xmm1
000026a8  f3410f58c8        addss      xmm1, xmm8
000026ad  f3440f1005c6e6ffff  movss      xmm8, dword ptr [rip - 0x193a]
000026b6  f3440f59c3        mulss      xmm8, xmm3
000026bb  f3440f58c1        addss      xmm8, xmm1
000026c0  f3440f5cc4        subss      xmm8, xmm4
000026c5  f3440f59c6        mulss      xmm8, xmm6
000026ca  f3440f58c7        addss      xmm8, xmm7
000026cf  f30f591da9e6ffff  mulss      xmm3, dword ptr [rip - 0x1957]
000026d7  f30f58da          addss      xmm3, xmm2
000026db  f30f58dc          addss      xmm3, xmm4
000026df  f30f59dd          mulss      xmm3, xmm5
000026e3  f3410f58d8        addss      xmm3, xmm8
000026e8  f30f591d88e6ffff  mulss      xmm3, dword ptr [rip - 0x1978]
000026f0  f30f59d8          mulss      xmm3, xmm0
000026f4  f30f2cc3          cvttss2si  eax, xmm3
000026f8  c3                ret        
