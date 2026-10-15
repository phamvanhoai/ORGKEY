# APK member: lib/x86_64/libngverb.so
# Library SHA-256: 8fd68c89616a094decd0ad7f229e0d8e89656b29446477be056c19051fc5f443
# Function: Java_sofeh_audio_NGVerb_open; address=0x21a0; bytes=117
# Decoder mode: default; decoded_bytes=117
000021a0  55                push       rbp
000021a1  4889e5            mov        rbp, rsp
000021a4  53                push       rbx
000021a5  4883ec28          sub        rsp, 0x28
000021a9  89cb              mov        ebx, ecx
000021ab  4885d2            test       rdx, rdx
000021ae  7458              je         0x2208
000021b0  4889d7            mov        rdi, rdx
000021b3  f30f117dd8        movss      dword ptr [rbp - 0x28], xmm7
000021b8  f30f1175dc        movss      dword ptr [rbp - 0x24], xmm6
000021bd  f30f116de0        movss      dword ptr [rbp - 0x20], xmm5
000021c2  f30f1165e4        movss      dword ptr [rbp - 0x1c], xmm4
000021c7  f30f115de8        movss      dword ptr [rbp - 0x18], xmm3
000021cc  f30f1155ec        movss      dword ptr [rbp - 0x14], xmm2
000021d1  f30f114df0        movss      dword ptr [rbp - 0x10], xmm1
000021d6  f30f1145f4        movss      dword ptr [rbp - 0xc], xmm0
000021db  e8c00d0000        call       0x2fa0
000021e0  f30f1045f4        movss      xmm0, dword ptr [rbp - 0xc]
000021e5  f30f104df0        movss      xmm1, dword ptr [rbp - 0x10]
000021ea  f30f1055ec        movss      xmm2, dword ptr [rbp - 0x14]
000021ef  f30f105de8        movss      xmm3, dword ptr [rbp - 0x18]
000021f4  f30f1065e4        movss      xmm4, dword ptr [rbp - 0x1c]
000021f9  f30f106de0        movss      xmm5, dword ptr [rbp - 0x20]
000021fe  f30f1075dc        movss      xmm6, dword ptr [rbp - 0x24]
00002203  f30f107dd8        movss      xmm7, dword ptr [rbp - 0x28]
00002208  89df              mov        edi, ebx
0000220a  4883c428          add        rsp, 0x28
0000220e  5b                pop        rbx
0000220f  5d                pop        rbp
00002210  e90b0d0000        jmp        0x2f20
