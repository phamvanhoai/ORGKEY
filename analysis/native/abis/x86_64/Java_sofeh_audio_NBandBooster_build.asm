# APK member: lib/x86_64/libnbandbooster.so
# Library SHA-256: d26716a4c20ffc01c78a57e125ea92d8c7d3caf71b1c31026ce42124b3dc20f2
# Function: Java_sofeh_audio_NBandBooster_build; address=0x870; bytes=177
# Decoder mode: default; decoded_bytes=177
00000870  4885d2            test       rdx, rdx
00000873  0f84a7000000      je         0x920
00000879  55                push       rbp
0000087a  4889e5            mov        rbp, rsp
0000087d  53                push       rbx
0000087e  50                push       rax
0000087f  8b4510            mov        eax, dword ptr [rbp + 0x10]
00000882  660f6ec1          movd       xmm0, ecx
00000886  66410f3a22c001    pinsrd     xmm0, r8d, 1
0000088d  0f5bc0            cvtdq2ps   xmm0, xmm0
00000890  0f590599fdffff    mulps      xmm0, xmmword ptr [rip - 0x267]
00000897  0f5805a2fdffff    addps      xmm0, xmmword ptr [rip - 0x25e]
0000089e  0f134228          movlps     qword ptr [rdx + 0x28], xmm0
000008a2  0f57c0            xorps      xmm0, xmm0
000008a5  f3410f2ac1        cvtsi2ss   xmm0, r9d
000008aa  f30f5905aefdffff  mulss      xmm0, dword ptr [rip - 0x252]
000008b2  f30f100d96fdffff  movss      xmm1, dword ptr [rip - 0x26a]
000008ba  f30f58c1          addss      xmm0, xmm1
000008be  f30f114230        movss      dword ptr [rdx + 0x30], xmm0
000008c3  0f57c0            xorps      xmm0, xmm0
000008c6  f30f2ac0          cvtsi2ss   xmm0, eax
000008ca  f30f5ec8          divss      xmm1, xmm0
000008ce  f30f114df4        movss      dword ptr [rbp - 0xc], xmm1
000008d3  f30f10057dfdffff  movss      xmm0, dword ptr [rip - 0x283]
000008db  f30f59c1          mulss      xmm0, xmm1
000008df  f30f5ac0          cvtss2sd   xmm0, xmm0
000008e3  4889d3            mov        rbx, rdx
000008e6  e8e5010000        call       0xad0
000008eb  f20f5ac0          cvtsd2ss   xmm0, xmm0
000008ef  f30f58c0          addss      xmm0, xmm0
000008f3  f30f1103          movss      dword ptr [rbx], xmm0
000008f7  f30f1045f4        movss      xmm0, dword ptr [rbp - 0xc]
000008fc  f30f590558fdffff  mulss      xmm0, dword ptr [rip - 0x2a8]
00000904  f30f5ac0          cvtss2sd   xmm0, xmm0
00000908  e8c3010000        call       0xad0
0000090d  f20f5ac0          cvtsd2ss   xmm0, xmm0
00000911  f30f58c0          addss      xmm0, xmm0
00000915  f30f114314        movss      dword ptr [rbx + 0x14], xmm0
0000091a  4883c408          add        rsp, 8
0000091e  5b                pop        rbx
0000091f  5d                pop        rbp
00000920  c3                ret        
