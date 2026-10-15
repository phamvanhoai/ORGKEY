# APK member: lib/x86/libnbandbooster.so
# Library SHA-256: 159fe53c87c1728336b9ffd53d5f471b91d6cddbc89effb302ce8435a0f64a40
# Function: Java_sofeh_audio_NBandBooster_build; address=0x780; bytes=224
# Decoder mode: default; decoded_bytes=224
00000780  55                push       ebp
00000781  89e5              mov        ebp, esp
00000783  53                push       ebx
00000784  56                push       esi
00000785  83e4f0            and        esp, 0xfffffff0
00000788  83ec20            sub        esp, 0x20
0000078b  e800000000        call       0x790
00000790  5b                pop        ebx
00000791  81c3a4130000      add        ebx, 0x13a4
00000797  8b7510            mov        esi, dword ptr [ebp + 0x10]
0000079a  8b4514            mov        eax, dword ptr [ebp + 0x14]
0000079d  09f0              or         eax, esi
0000079f  0f84b4000000      je         0x859
000007a5  8b4524            mov        eax, dword ptr [ebp + 0x24]
000007a8  8b4d20            mov        ecx, dword ptr [ebp + 0x20]
000007ab  f30f10451c        movss      xmm0, dword ptr [ebp + 0x1c]
000007b0  f30f104d18        movss      xmm1, dword ptr [ebp + 0x18]
000007b5  0f14c8            unpcklps   xmm1, xmm0
000007b8  0f5bc1            cvtdq2ps   xmm0, xmm1
000007bb  0f59833ce9ffff    mulps      xmm0, xmmword ptr [ebx - 0x16c4]
000007c2  0f58834ce9ffff    addps      xmm0, xmmword ptr [ebx - 0x16b4]
000007c9  0f134628          movlps     qword ptr [esi + 0x28], xmm0
000007cd  0f57c0            xorps      xmm0, xmm0
000007d0  f30f2ac1          cvtsi2ss   xmm0, ecx
000007d4  f30f598378e9ffff  mulss      xmm0, dword ptr [ebx - 0x1688]
000007dc  f30f108b6ce9ffff  movss      xmm1, dword ptr [ebx - 0x1694]
000007e4  f30f58c1          addss      xmm0, xmm1
000007e8  f30f114630        movss      dword ptr [esi + 0x30], xmm0
000007ed  0f57c0            xorps      xmm0, xmm0
000007f0  f30f2ac0          cvtsi2ss   xmm0, eax
000007f4  f30f5ec8          divss      xmm1, xmm0
000007f8  f30f114c2414      movss      dword ptr [esp + 0x14], xmm1
000007fe  f30f108370e9ffff  movss      xmm0, dword ptr [ebx - 0x1690]
00000806  f30f59c1          mulss      xmm0, xmm1
0000080a  f30f5ac0          cvtss2sd   xmm0, xmm0
0000080e  f20f110424        movsd      qword ptr [esp], xmm0
00000813  e838020000        call       0xa50
00000818  d95c2418          fstp       dword ptr [esp + 0x18]
0000081c  f30f10442418      movss      xmm0, dword ptr [esp + 0x18]
00000822  f30f58c0          addss      xmm0, xmm0
00000826  f30f1106          movss      dword ptr [esi], xmm0
0000082a  f30f10442414      movss      xmm0, dword ptr [esp + 0x14]
00000830  f30f598374e9ffff  mulss      xmm0, dword ptr [ebx - 0x168c]
00000838  f30f5ac0          cvtss2sd   xmm0, xmm0
0000083c  f20f110424        movsd      qword ptr [esp], xmm0
00000841  e80a020000        call       0xa50
00000846  d95c241c          fstp       dword ptr [esp + 0x1c]
0000084a  f30f1044241c      movss      xmm0, dword ptr [esp + 0x1c]
00000850  f30f58c0          addss      xmm0, xmm0
00000854  f30f114614        movss      dword ptr [esi + 0x14], xmm0
00000859  8d65f8            lea        esp, [ebp - 8]
0000085c  5e                pop        esi
0000085d  5b                pop        ebx
0000085e  5d                pop        ebp
0000085f  c3                ret        
