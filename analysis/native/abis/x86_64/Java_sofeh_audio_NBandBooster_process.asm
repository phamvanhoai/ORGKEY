# APK member: lib/x86_64/libnbandbooster.so
# Library SHA-256: d26716a4c20ffc01c78a57e125ea92d8c7d3caf71b1c31026ce42124b3dc20f2
# Function: Java_sofeh_audio_NBandBooster_process; address=0x930; bytes=296
# Decoder mode: default; decoded_bytes=296
00000930  4885d2            test       rdx, rdx
00000933  0f841c010000      je         0xa55
00000939  f30f100a          movss      xmm1, dword ptr [rdx]
0000093d  f30f104204        movss      xmm0, dword ptr [rdx + 4]
00000942  f3480f2ae1        cvtsi2ss   xmm4, rcx
00000947  0f28d4            movaps     xmm2, xmm4
0000094a  f30f5cd0          subss      xmm2, xmm0
0000094e  f30f59d1          mulss      xmm2, xmm1
00000952  f30f102dfafcffff  movss      xmm5, dword ptr [rip - 0x306]
0000095a  f30f58c5          addss      xmm0, xmm5
0000095e  f30f58c2          addss      xmm0, xmm2
00000962  f30f114204        movss      dword ptr [rdx + 4], xmm0
00000967  f30f105208        movss      xmm2, dword ptr [rdx + 8]
0000096c  f30f5cc2          subss      xmm0, xmm2
00000970  f30f59c1          mulss      xmm0, xmm1
00000974  f30f58c2          addss      xmm0, xmm2
00000978  f30f114208        movss      dword ptr [rdx + 8], xmm0
0000097d  f30f10520c        movss      xmm2, dword ptr [rdx + 0xc]
00000982  f30f5cc2          subss      xmm0, xmm2
00000986  f30f59c1          mulss      xmm0, xmm1
0000098a  f30f58c2          addss      xmm0, xmm2
0000098e  f30f11420c        movss      dword ptr [rdx + 0xc], xmm0
00000993  f30f105a10        movss      xmm3, dword ptr [rdx + 0x10]
00000998  f30f5cc3          subss      xmm0, xmm3
0000099c  f30f105218        movss      xmm2, dword ptr [rdx + 0x18]
000009a1  f30f5ce2          subss      xmm4, xmm2
000009a5  f30f58d5          addss      xmm2, xmm5
000009a9  f30f106a14        movss      xmm5, dword ptr [rdx + 0x14]
000009ae  f30f59e5          mulss      xmm4, xmm5
000009b2  f30f58d4          addss      xmm2, xmm4
000009b6  f30f115218        movss      dword ptr [rdx + 0x18], xmm2
000009bb  f30f10621c        movss      xmm4, dword ptr [rdx + 0x1c]
000009c0  f30f5cd4          subss      xmm2, xmm4
000009c4  f30f59d5          mulss      xmm2, xmm5
000009c8  f30f58d4          addss      xmm2, xmm4
000009cc  f30f11521c        movss      dword ptr [rdx + 0x1c], xmm2
000009d1  f30f106220        movss      xmm4, dword ptr [rdx + 0x20]
000009d6  f30f5cd4          subss      xmm2, xmm4
000009da  f30f59d5          mulss      xmm2, xmm5
000009de  f30f58d4          addss      xmm2, xmm4
000009e2  f30f115220        movss      dword ptr [rdx + 0x20], xmm2
000009e7  f30f106224        movss      xmm4, dword ptr [rdx + 0x24]
000009ec  f30f5cd4          subss      xmm2, xmm4
000009f0  f30f59d5          mulss      xmm2, xmm5
000009f4  f30f58d4          addss      xmm2, xmm4
000009f8  f30f115224        movss      dword ptr [rdx + 0x24], xmm2
000009fd  0f57e4            xorps      xmm4, xmm4
00000a00  f3480f2a6250      cvtsi2ss   xmm4, qword ptr [rdx + 0x50]
00000a06  f30f59c1          mulss      xmm0, xmm1
00000a0a  f30f5ce2          subss      xmm4, xmm2
00000a0e  f30f58c3          addss      xmm0, xmm3
00000a12  f30f114210        movss      dword ptr [rdx + 0x10], xmm0
00000a17  f30f5cd0          subss      xmm2, xmm0
00000a1b  f20f104a28        movsd      xmm1, qword ptr [rdx + 0x28]
00000a20  660f3a21c210      insertps   xmm0, xmm2, 0x10
00000a26  0f59c1            mulps      xmm0, xmm1
00000a29  0f134234          movlps     qword ptr [rdx + 0x34], xmm0
00000a2d  f30f596230        mulss      xmm4, dword ptr [rdx + 0x30]
00000a32  f30f11623c        movss      dword ptr [rdx + 0x3c], xmm4
00000a37  0f104a40          movups     xmm1, xmmword ptr [rdx + 0x40]
00000a3b  0f114a48          movups     xmmword ptr [rdx + 0x48], xmm1
00000a3f  48894a40          mov        qword ptr [rdx + 0x40], rcx
00000a43  f30f16c8          movshdup   xmm1, xmm0
00000a47  f30f58c8          addss      xmm1, xmm0
00000a4b  f30f58cc          addss      xmm1, xmm4
00000a4f  f3480f2cc1        cvttss2si  rax, xmm1
00000a54  c3                ret        
00000a55  31c0              xor        eax, eax
00000a57  c3                ret        
