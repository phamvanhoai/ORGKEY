# APK member: lib/x86/libnequalizer.so
# Library SHA-256: 0c7dfba2ccd6a4c88a74500c5b3ecac4f88ff3e4abb681dafc117af7ee94b44b
# Function: Java_sofeh_audio_NEqualizer_build; address=0xa00; bytes=1027
# Decoder mode: default; decoded_bytes=1027
00000a00  55                push       ebp
00000a01  89e5              mov        ebp, esp
00000a03  53                push       ebx
00000a04  56                push       esi
00000a05  83e4f0            and        esp, 0xfffffff0
00000a08  83ec60            sub        esp, 0x60
00000a0b  e800000000        call       0xa10
00000a10  5b                pop        ebx
00000a11  81c3e42d0000      add        ebx, 0x2de4
00000a17  8b7510            mov        esi, dword ptr [ebp + 0x10]
00000a1a  8b4514            mov        eax, dword ptr [ebp + 0x14]
00000a1d  09f0              or         eax, esi
00000a1f  0f84d7030000      je         0xdfc
00000a25  f30f2a4518        cvtsi2ss   xmm0, dword ptr [ebp + 0x18]
00000a2a  f30f108b9ccdffff  movss      xmm1, dword ptr [ebx - 0x3264]
00000a32  f30f5ec8          divss      xmm1, xmm0
00000a36  f30f114c2414      movss      dword ptr [esp + 0x14], xmm1
00000a3c  f30f1083b8cdffff  movss      xmm0, dword ptr [ebx - 0x3248]
00000a44  f30f59c1          mulss      xmm0, xmm1
00000a48  f30f5ac0          cvtss2sd   xmm0, xmm0
00000a4c  f20f5983dccdffff  mulsd      xmm0, qword ptr [ebx - 0x3224]
00000a54  f20f110424        movsd      qword ptr [esp], xmm0
00000a59  e8b21c0000        call       0x2710
00000a5e  dd5c2420          fstp       qword ptr [esp + 0x20]
00000a62  f20f10442420      movsd      xmm0, qword ptr [esp + 0x20]
00000a68  f20f58c0          addsd      xmm0, xmm0
00000a6c  f20f5ac0          cvtsd2ss   xmm0, xmm0
00000a70  f30f114620        movss      dword ptr [esi + 0x20], xmm0
00000a75  c7464401000000    mov        dword ptr [esi + 0x44], 1
00000a7c  0f57c9            xorps      xmm1, xmm1
00000a7f  f30f2a0e          cvtsi2ss   xmm1, dword ptr [esi]
00000a83  f30f1093accdffff  movss      xmm2, dword ptr [ebx - 0x3254]
00000a8b  f30f11542410      movss      dword ptr [esp + 0x10], xmm2
00000a91  f30f59ca          mulss      xmm1, xmm2
00000a95  f30f114e40        movss      dword ptr [esi + 0x40], xmm1
00000a9a  f30f118660010000  movss      dword ptr [esi + 0x160], xmm0
00000aa2  c7868401000001000000  mov        dword ptr [esi + 0x184], 1
00000aac  f30f118e80010000  movss      dword ptr [esi + 0x180], xmm1
00000ab4  f30f1083c4cdffff  movss      xmm0, dword ptr [ebx - 0x323c]
00000abc  f30f59442414      mulss      xmm0, dword ptr [esp + 0x14]
00000ac2  f30f5ac0          cvtss2sd   xmm0, xmm0
00000ac6  f20f5983cccdffff  mulsd      xmm0, qword ptr [ebx - 0x3234]
00000ace  f20f110424        movsd      qword ptr [esp], xmm0
00000ad3  e8381c0000        call       0x2710
00000ad8  dd5c2428          fstp       qword ptr [esp + 0x28]
00000adc  f20f10442428      movsd      xmm0, qword ptr [esp + 0x28]
00000ae2  f20f58c0          addsd      xmm0, xmm0
00000ae6  f20f5ac0          cvtsd2ss   xmm0, xmm0
00000aea  f30f114648        movss      dword ptr [esi + 0x48], xmm0
00000aef  c7466c01000000    mov        dword ptr [esi + 0x6c], 1
00000af6  0f57c9            xorps      xmm1, xmm1
00000af9  f30f2a4e04        cvtsi2ss   xmm1, dword ptr [esi + 4]
00000afe  f30f594c2410      mulss      xmm1, dword ptr [esp + 0x10]
00000b04  f30f114e68        movss      dword ptr [esi + 0x68], xmm1
00000b09  f30f118688010000  movss      dword ptr [esi + 0x188], xmm0
00000b11  c786ac01000001000000  mov        dword ptr [esi + 0x1ac], 1
00000b1b  f30f118ea8010000  movss      dword ptr [esi + 0x1a8], xmm1
00000b23  f30f1083a0cdffff  movss      xmm0, dword ptr [ebx - 0x3260]
00000b2b  f30f59442414      mulss      xmm0, dword ptr [esp + 0x14]
00000b31  f30f5ac0          cvtss2sd   xmm0, xmm0
00000b35  f20f108bd4cdffff  movsd      xmm1, qword ptr [ebx - 0x322c]
00000b3d  f20f114c2418      movsd      qword ptr [esp + 0x18], xmm1
00000b43  f20f59c1          mulsd      xmm0, xmm1
00000b47  f20f110424        movsd      qword ptr [esp], xmm0
00000b4c  e8bf1b0000        call       0x2710
00000b51  dd5c2430          fstp       qword ptr [esp + 0x30]
00000b55  f20f10442430      movsd      xmm0, qword ptr [esp + 0x30]
00000b5b  f20f58c0          addsd      xmm0, xmm0
00000b5f  f20f5ac0          cvtsd2ss   xmm0, xmm0
00000b63  f30f114670        movss      dword ptr [esi + 0x70], xmm0
00000b68  c7869400000001000000  mov        dword ptr [esi + 0x94], 1
00000b72  0f57c9            xorps      xmm1, xmm1
00000b75  f30f2a4e08        cvtsi2ss   xmm1, dword ptr [esi + 8]
00000b7a  f30f594c2410      mulss      xmm1, dword ptr [esp + 0x10]
00000b80  f30f118e90000000  movss      dword ptr [esi + 0x90], xmm1
00000b88  f30f1186b0010000  movss      dword ptr [esi + 0x1b0], xmm0
00000b90  c786d401000001000000  mov        dword ptr [esi + 0x1d4], 1
00000b9a  f30f118ed0010000  movss      dword ptr [esi + 0x1d0], xmm1
00000ba2  f30f1083b0cdffff  movss      xmm0, dword ptr [ebx - 0x3250]
00000baa  f30f59442414      mulss      xmm0, dword ptr [esp + 0x14]
00000bb0  f30f5ac0          cvtss2sd   xmm0, xmm0
00000bb4  f20f59442418      mulsd      xmm0, qword ptr [esp + 0x18]
00000bba  f20f110424        movsd      qword ptr [esp], xmm0
00000bbf  e84c1b0000        call       0x2710
00000bc4  dd5c2438          fstp       qword ptr [esp + 0x38]
00000bc8  f20f10442438      movsd      xmm0, qword ptr [esp + 0x38]
00000bce  f20f58c0          addsd      xmm0, xmm0
00000bd2  f20f5ac0          cvtsd2ss   xmm0, xmm0
00000bd6  f30f118698000000  movss      dword ptr [esi + 0x98], xmm0
00000bde  c786bc00000001000000  mov        dword ptr [esi + 0xbc], 1
00000be8  0f57c9            xorps      xmm1, xmm1
00000beb  f30f2a4e0c        cvtsi2ss   xmm1, dword ptr [esi + 0xc]
00000bf0  f30f594c2410      mulss      xmm1, dword ptr [esp + 0x10]
00000bf6  f30f118eb8000000  movss      dword ptr [esi + 0xb8], xmm1
00000bfe  f30f1186d8010000  movss      dword ptr [esi + 0x1d8], xmm0
00000c06  c786fc01000001000000  mov        dword ptr [esi + 0x1fc], 1
00000c10  f30f118ef8010000  movss      dword ptr [esi + 0x1f8], xmm1
00000c18  f30f1083b4cdffff  movss      xmm0, dword ptr [ebx - 0x324c]
00000c20  f30f59442414      mulss      xmm0, dword ptr [esp + 0x14]
00000c26  f30f5ac0          cvtss2sd   xmm0, xmm0
00000c2a  f20f108be4cdffff  movsd      xmm1, qword ptr [ebx - 0x321c]
00000c32  f20f114c2418      movsd      qword ptr [esp + 0x18], xmm1
00000c38  f20f59c1          mulsd      xmm0, xmm1
00000c3c  f20f110424        movsd      qword ptr [esp], xmm0
00000c41  e8ca1a0000        call       0x2710
00000c46  dd5c2440          fstp       qword ptr [esp + 0x40]
00000c4a  f20f10442440      movsd      xmm0, qword ptr [esp + 0x40]
00000c50  f20f58c0          addsd      xmm0, xmm0
00000c54  f20f5ac0          cvtsd2ss   xmm0, xmm0
00000c58  f30f1186c0000000  movss      dword ptr [esi + 0xc0], xmm0
00000c60  c786e400000001000000  mov        dword ptr [esi + 0xe4], 1
00000c6a  0f57c9            xorps      xmm1, xmm1
00000c6d  f30f2a4e10        cvtsi2ss   xmm1, dword ptr [esi + 0x10]
00000c72  f30f594c2410      mulss      xmm1, dword ptr [esp + 0x10]
00000c78  f30f118ee0000000  movss      dword ptr [esi + 0xe0], xmm1
00000c80  f30f118600020000  movss      dword ptr [esi + 0x200], xmm0
00000c88  c7862402000001000000  mov        dword ptr [esi + 0x224], 1
00000c92  f30f118e20020000  movss      dword ptr [esi + 0x220], xmm1
00000c9a  f30f1083a4cdffff  movss      xmm0, dword ptr [ebx - 0x325c]
00000ca2  f30f59442414      mulss      xmm0, dword ptr [esp + 0x14]
00000ca8  f30f5ac0          cvtss2sd   xmm0, xmm0
00000cac  f20f59442418      mulsd      xmm0, qword ptr [esp + 0x18]
00000cb2  f20f110424        movsd      qword ptr [esp], xmm0
00000cb7  e8541a0000        call       0x2710
00000cbc  dd5c2448          fstp       qword ptr [esp + 0x48]
00000cc0  f20f10442448      movsd      xmm0, qword ptr [esp + 0x48]
00000cc6  f20f58c0          addsd      xmm0, xmm0
00000cca  f20f5ac0          cvtsd2ss   xmm0, xmm0
00000cce  f30f1186e8000000  movss      dword ptr [esi + 0xe8], xmm0
00000cd6  c7860c01000001000000  mov        dword ptr [esi + 0x10c], 1
00000ce0  0f57c9            xorps      xmm1, xmm1
00000ce3  f30f2a4e14        cvtsi2ss   xmm1, dword ptr [esi + 0x14]
00000ce8  f30f594c2410      mulss      xmm1, dword ptr [esp + 0x10]
00000cee  f30f118e08010000  movss      dword ptr [esi + 0x108], xmm1
00000cf6  f30f118628020000  movss      dword ptr [esi + 0x228], xmm0
00000cfe  c7864c02000001000000  mov        dword ptr [esi + 0x24c], 1
00000d08  f30f118e48020000  movss      dword ptr [esi + 0x248], xmm1
00000d10  f30f1083a8cdffff  movss      xmm0, dword ptr [ebx - 0x3258]
00000d18  f30f59442414      mulss      xmm0, dword ptr [esp + 0x14]
00000d1e  f30f5ac0          cvtss2sd   xmm0, xmm0
00000d22  f20f59442418      mulsd      xmm0, qword ptr [esp + 0x18]
00000d28  f20f110424        movsd      qword ptr [esp], xmm0
00000d2d  e8de190000        call       0x2710
00000d32  dd5c2450          fstp       qword ptr [esp + 0x50]
00000d36  f20f10442450      movsd      xmm0, qword ptr [esp + 0x50]
00000d3c  f20f58c0          addsd      xmm0, xmm0
00000d40  f20f5ac0          cvtsd2ss   xmm0, xmm0
00000d44  f30f118610010000  movss      dword ptr [esi + 0x110], xmm0
00000d4c  c7863401000001000000  mov        dword ptr [esi + 0x134], 1
00000d56  0f57c9            xorps      xmm1, xmm1
00000d59  f30f2a4e18        cvtsi2ss   xmm1, dword ptr [esi + 0x18]
00000d5e  f30f594c2410      mulss      xmm1, dword ptr [esp + 0x10]
00000d64  f30f118e30010000  movss      dword ptr [esi + 0x130], xmm1
00000d6c  f30f118650020000  movss      dword ptr [esi + 0x250], xmm0
00000d74  c7867402000001000000  mov        dword ptr [esi + 0x274], 1
00000d7e  f30f118e70020000  movss      dword ptr [esi + 0x270], xmm1
00000d86  f30f10442414      movss      xmm0, dword ptr [esp + 0x14]
00000d8c  f30f5983c0cdffff  mulss      xmm0, dword ptr [ebx - 0x3240]
00000d94  f30f5ac0          cvtss2sd   xmm0, xmm0
00000d98  f20f59442418      mulsd      xmm0, qword ptr [esp + 0x18]
00000d9e  f20f110424        movsd      qword ptr [esp], xmm0
00000da3  e868190000        call       0x2710
00000da8  dd5c2458          fstp       qword ptr [esp + 0x58]
00000dac  f20f10442458      movsd      xmm0, qword ptr [esp + 0x58]
00000db2  f20f58c0          addsd      xmm0, xmm0
00000db6  f20f5ac0          cvtsd2ss   xmm0, xmm0
00000dba  f30f118638010000  movss      dword ptr [esi + 0x138], xmm0
00000dc2  c7865c01000001000000  mov        dword ptr [esi + 0x15c], 1
00000dcc  0f57c9            xorps      xmm1, xmm1
00000dcf  f30f2a4e1c        cvtsi2ss   xmm1, dword ptr [esi + 0x1c]
00000dd4  f30f594c2410      mulss      xmm1, dword ptr [esp + 0x10]
00000dda  f30f118e58010000  movss      dword ptr [esi + 0x158], xmm1
00000de2  f30f118678020000  movss      dword ptr [esi + 0x278], xmm0
00000dea  c7869c02000001000000  mov        dword ptr [esi + 0x29c], 1
00000df4  f30f118e98020000  movss      dword ptr [esi + 0x298], xmm1
00000dfc  8d65f8            lea        esp, [ebp - 8]
00000dff  5e                pop        esi
00000e00  5b                pop        ebx
00000e01  5d                pop        ebp
00000e02  c3                ret        
