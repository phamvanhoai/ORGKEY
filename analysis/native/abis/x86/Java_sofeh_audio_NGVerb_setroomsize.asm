# APK member: lib/x86/libngverb.so
# Library SHA-256: 8412ca981b3be45bccdf4d07aff9e3554c36594cb309f6bb73e3c87705155f48
# Function: Java_sofeh_audio_NGVerb_setroomsize; address=0x2be0; bytes=661
# Decoder mode: default; decoded_bytes=661
00002be0  55                push       ebp
00002be1  89e5              mov        ebp, esp
00002be3  53                push       ebx
00002be4  57                push       edi
00002be5  56                push       esi
00002be6  83e4f0            and        esp, 0xfffffff0
00002be9  81ec90000000      sub        esp, 0x90
00002bef  e800000000        call       0x2bf4
00002bf4  5b                pop        ebx
00002bf5  81c368170000      add        ebx, 0x1768
00002bfb  8b7510            mov        esi, dword ptr [ebp + 0x10]
00002bfe  8b4514            mov        eax, dword ptr [ebp + 0x14]
00002c01  09f0              or         eax, esi
00002c03  0f8464020000      je         0x2e6d
00002c09  f30f104d18        movss      xmm1, dword ptr [ebp + 0x18]
00002c0e  f30f108354c8ffff  movss      xmm0, dword ptr [ebx - 0x37ac]
00002c16  0f28d1            movaps     xmm2, xmm1
00002c19  f30fc2d103        cmpunordss xmm2, xmm1
00002c1e  0f28da            movaps     xmm3, xmm2
00002c21  0f54d8            andps      xmm3, xmm0
00002c24  0f55d1            andnps     xmm2, xmm1
00002c27  0f56d3            orps       xmm2, xmm3
00002c2a  f30fc2c802        cmpless    xmm1, xmm0
00002c2f  0f28d9            movaps     xmm3, xmm1
00002c32  0f55da            andnps     xmm3, xmm2
00002c35  0f54c8            andps      xmm1, xmm0
00002c38  0f56cb            orps       xmm1, xmm3
00002c3b  f30f114e18        movss      dword ptr [esi + 0x18], xmm1
00002c40  0f57c0            xorps      xmm0, xmm0
00002c43  f30f2a06          cvtsi2ss   xmm0, dword ptr [esi]
00002c47  f30f59c1          mulss      xmm0, xmm1
00002c4b  8b4630            mov        eax, dword ptr [esi + 0x30]
00002c4e  f30f598364c8ffff  mulss      xmm0, dword ptr [ebx - 0x379c]
00002c56  f30f114624        movss      dword ptr [esi + 0x24], xmm0
00002c5b  f30f108b80c8ffff  movss      xmm1, dword ptr [ebx - 0x3780]
00002c63  f30f59c8          mulss      xmm1, xmm0
00002c67  f30f12d0          movsldup   xmm2, xmm0
00002c6b  0f599384c7ffff    mulps      xmm2, xmmword ptr [ebx - 0x387c]
00002c72  0f14c1            unpcklps   xmm0, xmm1
00002c75  0f16c2            movlhps    xmm0, xmm2
00002c78  0f288b04c8ffff    movaps     xmm1, xmmword ptr [ebx - 0x37fc]
00002c7f  0f294c2460        movaps     xmmword ptr [esp + 0x60], xmm1
00002c84  0f58c1            addps      xmm0, xmm1
00002c87  660ffe8374c7ffff  paddd      xmm0, xmmword ptr [ebx - 0x388c]
00002c8f  f30f7f00          movdqu     xmmword ptr [eax], xmm0
00002c93  f20f104e5c        movsd      xmm1, qword ptr [esi + 0x5c]
00002c98  f20f5ac9          cvtsd2ss   xmm1, xmm1
00002c9c  f30f114c240c      movss      dword ptr [esp + 0xc], xmm1
00002ca2  8b7e2c            mov        edi, dword ptr [esi + 0x2c]
00002ca5  0f5bd0            cvtdq2ps   xmm2, xmm0
00002ca8  0f29542430        movaps     xmmword ptr [esp + 0x30], xmm2
00002cad  f30f16c2          movshdup   xmm0, xmm2
00002cb1  f30f11442404      movss      dword ptr [esp + 4], xmm0
00002cb7  f30f110c24        movss      dword ptr [esp], xmm1
00002cbc  0f12d2            movhlps    xmm2, xmm2
00002cbf  0f29542450        movaps     xmmword ptr [esp + 0x50], xmm2
00002cc4  e8d7040000        call       0x31a0
00002cc9  db7c2444          fstp       xword ptr [esp + 0x44]
00002ccd  0f28442450        movaps     xmm0, xmmword ptr [esp + 0x50]
00002cd2  f30f11442404      movss      dword ptr [esp + 4], xmm0
00002cd8  f30f1044240c      movss      xmm0, dword ptr [esp + 0xc]
00002cde  f30f110424        movss      dword ptr [esp], xmm0
00002ce3  0f28442430        movaps     xmm0, xmmword ptr [esp + 0x30]
00002ce8  0fc6c0ff          shufps     xmm0, xmm0, 0xff
00002cec  0f29442470        movaps     xmmword ptr [esp + 0x70], xmm0
00002cf1  e8aa040000        call       0x31a0
00002cf6  db7c2450          fstp       xword ptr [esp + 0x50]
00002cfa  0f28442470        movaps     xmm0, xmmword ptr [esp + 0x70]
00002cff  f30f11442404      movss      dword ptr [esp + 4], xmm0
00002d05  f30f1044240c      movss      xmm0, dword ptr [esp + 0xc]
00002d0b  f30f110424        movss      dword ptr [esp], xmm0
00002d10  e88b040000        call       0x31a0
00002d15  0f28442430        movaps     xmm0, xmmword ptr [esp + 0x30]
00002d1a  f30f11442404      movss      dword ptr [esp + 4], xmm0
00002d20  f30f1044240c      movss      xmm0, dword ptr [esp + 0xc]
00002d26  f30f110424        movss      dword ptr [esp], xmm0
00002d2b  d95c2410          fstp       dword ptr [esp + 0x10]
00002d2f  db6c2450          fld        xword ptr [esp + 0x50]
00002d33  d95c2414          fstp       dword ptr [esp + 0x14]
00002d37  db6c2444          fld        xword ptr [esp + 0x44]
00002d3b  d95c2418          fstp       dword ptr [esp + 0x18]
00002d3f  e85c040000        call       0x31a0
00002d44  d95c241c          fstp       dword ptr [esp + 0x1c]
00002d48  f30f10442410      movss      xmm0, dword ptr [esp + 0x10]
00002d4e  f30f104c2414      movss      xmm1, dword ptr [esp + 0x14]
00002d54  0f14c8            unpcklps   xmm1, xmm0
00002d57  f30f10442418      movss      xmm0, dword ptr [esp + 0x18]
00002d5d  f30f1054241c      movss      xmm2, dword ptr [esp + 0x1c]
00002d63  0f14d0            unpcklps   xmm2, xmm0
00002d66  0f16d1            movlhps    xmm2, xmm1
00002d69  0f288324c8ffff    movaps     xmm0, xmmword ptr [ebx - 0x37dc]
00002d70  0f57c2            xorps      xmm0, xmm2
00002d73  0fc293a4c7ffff01  cmpltps    xmm2, xmmword ptr [ebx - 0x385c]
00002d7b  0f54c2            andps      xmm0, xmm2
00002d7e  0f559344c8ffff    andnps     xmm2, xmmword ptr [ebx - 0x37bc]
00002d85  0f56d0            orps       xmm2, xmm0
00002d88  0f1117            movups     xmmword ptr [edi], xmm2
00002d8b  f30f104624        movss      xmm0, dword ptr [esi + 0x24]
00002d90  0fc6c000          shufps     xmm0, xmm0, 0
00002d94  0f5983f4c7ffff    mulps      xmm0, xmmword ptr [ebx - 0x380c]
00002d9b  0f58442460        addps      xmm0, xmmword ptr [esp + 0x60]
00002da0  660ffe83d4c7ffff  paddd      xmm0, xmmword ptr [ebx - 0x382c]
00002da8  8b4648            mov        eax, dword ptr [esi + 0x48]
00002dab  f30f7f00          movdqu     xmmword ptr [eax], xmm0
00002daf  8b764c            mov        esi, dword ptr [esi + 0x4c]
00002db2  0f5bc8            cvtdq2ps   xmm1, xmm0
00002db5  0f294c2430        movaps     xmmword ptr [esp + 0x30], xmm1
00002dba  f30f1044240c      movss      xmm0, dword ptr [esp + 0xc]
00002dc0  f30f110424        movss      dword ptr [esp], xmm0
00002dc5  f30f114c2404      movss      dword ptr [esp + 4], xmm1
00002dcb  e8d0030000        call       0x31a0
00002dd0  db7c2460          fstp       xword ptr [esp + 0x60]
00002dd4  f30f1044240c      movss      xmm0, dword ptr [esp + 0xc]
00002dda  f30f110424        movss      dword ptr [esp], xmm0
00002ddf  0f28442430        movaps     xmm0, xmmword ptr [esp + 0x30]
00002de4  0f12c0            movhlps    xmm0, xmm0
00002de7  f30f11442404      movss      dword ptr [esp + 4], xmm0
00002ded  e8ae030000        call       0x31a0
00002df2  db7c2444          fstp       xword ptr [esp + 0x44]
00002df6  f30f1044240c      movss      xmm0, dword ptr [esp + 0xc]
00002dfc  f30f110424        movss      dword ptr [esp], xmm0
00002e01  0f28442430        movaps     xmm0, xmmword ptr [esp + 0x30]
00002e06  0fc6c0ff          shufps     xmm0, xmm0, 0xff
00002e0a  f30f11442404      movss      dword ptr [esp + 4], xmm0
00002e10  e88b030000        call       0x31a0
00002e15  f30f1044240c      movss      xmm0, dword ptr [esp + 0xc]
00002e1b  f30f110424        movss      dword ptr [esp], xmm0
00002e20  f30f16442430      movshdup   xmm0, xmmword ptr [esp + 0x30]
00002e26  f30f11442404      movss      dword ptr [esp + 4], xmm0
00002e2c  d95c2420          fstp       dword ptr [esp + 0x20]
00002e30  db6c2444          fld        xword ptr [esp + 0x44]
00002e34  d95c2424          fstp       dword ptr [esp + 0x24]
00002e38  db6c2460          fld        xword ptr [esp + 0x60]
00002e3c  d95c242c          fstp       dword ptr [esp + 0x2c]
00002e40  e85b030000        call       0x31a0
00002e45  d95c2428          fstp       dword ptr [esp + 0x28]
00002e49  f30f10442420      movss      xmm0, dword ptr [esp + 0x20]
00002e4f  f30f104c2424      movss      xmm1, dword ptr [esp + 0x24]
00002e55  0f14c8            unpcklps   xmm1, xmm0
00002e58  f30f1044242c      movss      xmm0, dword ptr [esp + 0x2c]
00002e5e  f30f10542428      movss      xmm2, dword ptr [esp + 0x28]
00002e64  0f14c2            unpcklps   xmm0, xmm2
00002e67  0f16c1            movlhps    xmm0, xmm1
00002e6a  0f1106            movups     xmmword ptr [esi], xmm0
00002e6d  8d65f4            lea        esp, [ebp - 0xc]
00002e70  5e                pop        esi
00002e71  5f                pop        edi
00002e72  5b                pop        ebx
00002e73  5d                pop        ebp
00002e74  c3                ret        
