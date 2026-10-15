# APK member: lib/x86_64/libnequalizer.so
# Library SHA-256: 5bb0dc8806216597eac5b39540e6e15563919ae6c029617a4d93d22790a8596c
# Function: Java_sofeh_audio_NEqualizer_bandMove; address=0xa30; bytes=60
# Decoder mode: default; decoded_bytes=60
00000a30  4885d2            test       rdx, rdx
00000a33  7436              je         0xa6b
00000a35  f30f100d37fdffff  movss      xmm1, dword ptr [rip - 0x2c9]
00000a3d  0f28d1            movaps     xmm2, xmm1
00000a40  f30f5cd0          subss      xmm2, xmm0
00000a44  f30f2cc2          cvttss2si  eax, xmm2
00000a48  f30f58c1          addss      xmm0, xmm1
00000a4c  f30f2cc8          cvttss2si  ecx, xmm0
00000a50  660f6ec0          movd       xmm0, eax
00000a54  660f70c000        pshufd     xmm0, xmm0, 0
00000a59  f30f7f02          movdqu     xmmword ptr [rdx], xmm0
00000a5d  660f6ec1          movd       xmm0, ecx
00000a61  660f70c000        pshufd     xmm0, xmm0, 0
00000a66  f30f7f4210        movdqu     xmmword ptr [rdx + 0x10], xmm0
00000a6b  c3                ret        
