# APK member: lib/x86/libngverb.so
# Library SHA-256: 8412ca981b3be45bccdf4d07aff9e3554c36594cb309f6bb73e3c87705155f48
# Function: Java_sofeh_audio_NGVerb_setrevtime; address=0x2e80; bytes=340
# Decoder mode: default; decoded_bytes=340
00002e80  55                push       ebp
00002e81  89e5              mov        ebp, esp
00002e83  53                push       ebx
00002e84  57                push       edi
00002e85  56                push       esi
00002e86  83e4f0            and        esp, 0xfffffff0
00002e89  83ec50            sub        esp, 0x50
00002e8c  e800000000        call       0x2e91
00002e91  5b                pop        ebx
00002e92  81c3cb140000      add        ebx, 0x14cb
00002e98  8b7d10            mov        edi, dword ptr [ebp + 0x10]
00002e9b  8b4514            mov        eax, dword ptr [ebp + 0x14]
00002e9e  09f8              or         eax, edi
00002ea0  0f8426010000      je         0x2fcc
00002ea6  f30f104518        movss      xmm0, dword ptr [ebp + 0x18]
00002eab  f30f11471c        movss      dword ptr [edi + 0x1c], xmm0
00002eb0  f30f2a0f          cvtsi2ss   xmm1, dword ptr [edi]
00002eb4  f30f59c8          mulss      xmm1, xmm0
00002eb8  f30f108354c8ffff  movss      xmm0, dword ptr [ebx - 0x37ac]
00002ec0  f30f5ec1          divss      xmm0, xmm1
00002ec4  f30f108b8cc8ffff  movss      xmm1, dword ptr [ebx - 0x3774]
00002ecc  f30f110c24        movss      dword ptr [esp], xmm1
00002ed1  f30f11442404      movss      dword ptr [esp + 4], xmm0
00002ed7  e8c4020000        call       0x31a0
00002edc  d954240c          fst        dword ptr [esp + 0xc]
00002ee0  f30f104c240c      movss      xmm1, dword ptr [esp + 0xc]
00002ee6  f30f114c2408      movss      dword ptr [esp + 8], xmm1
00002eec  dd5f5c            fstp       qword ptr [edi + 0x5c]
00002eef  8b772c            mov        esi, dword ptr [edi + 0x2c]
00002ef2  8b4730            mov        eax, dword ptr [edi + 0x30]
00002ef5  0f1000            movups     xmm0, xmmword ptr [eax]
00002ef8  0f5bc0            cvtdq2ps   xmm0, xmm0
00002efb  0f29442420        movaps     xmmword ptr [esp + 0x20], xmm0
00002f00  f30f110c24        movss      dword ptr [esp], xmm1
00002f05  f30f11442404      movss      dword ptr [esp + 4], xmm0
00002f0b  e890020000        call       0x31a0
00002f10  db7c2440          fstp       xword ptr [esp + 0x40]
00002f14  f30f10442408      movss      xmm0, dword ptr [esp + 8]
00002f1a  f30f110424        movss      dword ptr [esp], xmm0
00002f1f  0f28442420        movaps     xmm0, xmmword ptr [esp + 0x20]
00002f24  0f12c0            movhlps    xmm0, xmm0
00002f27  f30f11442404      movss      dword ptr [esp + 4], xmm0
00002f2d  e86e020000        call       0x31a0
00002f32  db7c2434          fstp       xword ptr [esp + 0x34]
00002f36  f30f10442408      movss      xmm0, dword ptr [esp + 8]
00002f3c  f30f110424        movss      dword ptr [esp], xmm0
00002f41  0f28442420        movaps     xmm0, xmmword ptr [esp + 0x20]
00002f46  0fc6c0ff          shufps     xmm0, xmm0, 0xff
00002f4a  f30f11442404      movss      dword ptr [esp + 4], xmm0
00002f50  e84b020000        call       0x31a0
00002f55  f30f10442408      movss      xmm0, dword ptr [esp + 8]
00002f5b  f30f110424        movss      dword ptr [esp], xmm0
00002f60  f30f16442420      movshdup   xmm0, xmmword ptr [esp + 0x20]
00002f66  f30f11442404      movss      dword ptr [esp + 4], xmm0
00002f6c  d95c2410          fstp       dword ptr [esp + 0x10]
00002f70  db6c2434          fld        xword ptr [esp + 0x34]
00002f74  d95c2414          fstp       dword ptr [esp + 0x14]
00002f78  db6c2440          fld        xword ptr [esp + 0x40]
00002f7c  d95c241c          fstp       dword ptr [esp + 0x1c]
00002f80  e81b020000        call       0x31a0
00002f85  d95c2418          fstp       dword ptr [esp + 0x18]
00002f89  f30f10442410      movss      xmm0, dword ptr [esp + 0x10]
00002f8f  f30f104c2414      movss      xmm1, dword ptr [esp + 0x14]
00002f95  0f14c8            unpcklps   xmm1, xmm0
00002f98  f30f1044241c      movss      xmm0, dword ptr [esp + 0x1c]
00002f9e  f30f10542418      movss      xmm2, dword ptr [esp + 0x18]
00002fa4  0f14c2            unpcklps   xmm0, xmm2
00002fa7  0f16c1            movlhps    xmm0, xmm1
00002faa  0f288b24c8ffff    movaps     xmm1, xmmword ptr [ebx - 0x37dc]
00002fb1  0f57c8            xorps      xmm1, xmm0
00002fb4  0fc283a4c7ffff01  cmpltps    xmm0, xmmword ptr [ebx - 0x385c]
00002fbc  0f54c8            andps      xmm1, xmm0
00002fbf  0f558344c8ffff    andnps     xmm0, xmmword ptr [ebx - 0x37bc]
00002fc6  0f56c1            orps       xmm0, xmm1
00002fc9  0f1106            movups     xmmword ptr [esi], xmm0
00002fcc  8d65f4            lea        esp, [ebp - 0xc]
00002fcf  5e                pop        esi
00002fd0  5f                pop        edi
00002fd1  5b                pop        ebx
00002fd2  5d                pop        ebp
00002fd3  c3                ret        
