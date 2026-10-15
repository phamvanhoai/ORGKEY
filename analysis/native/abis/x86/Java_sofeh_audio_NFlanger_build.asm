# APK member: lib/x86/libnflanger.so
# Library SHA-256: 358a7d5d8ff8336f0dff81448c92ca52cbb50537d20bc26689308d57c3842482
# Function: Java_sofeh_audio_NFlanger_build; address=0x880; bytes=122
# Decoder mode: default; decoded_bytes=122
00000880  55                push       ebp
00000881  89e5              mov        ebp, esp
00000883  56                push       esi
00000884  83e4fc            and        esp, 0xfffffffc
00000887  e800000000        call       0x88c
0000088c  59                pop        ecx
0000088d  81c1d8180000      add        ecx, 0x18d8
00000893  8b4510            mov        eax, dword ptr [ebp + 0x10]
00000896  8b5514            mov        edx, dword ptr [ebp + 0x14]
00000899  09c2              or         edx, eax
0000089b  7457              je         0x8f4
0000089d  8b5524            mov        edx, dword ptr [ebp + 0x24]
000008a0  8b7518            mov        esi, dword ptr [ebp + 0x18]
000008a3  8930              mov        dword ptr [eax], esi
000008a5  8b751c            mov        esi, dword ptr [ebp + 0x1c]
000008a8  897004            mov        dword ptr [eax + 4], esi
000008ab  8b7520            mov        esi, dword ptr [ebp + 0x20]
000008ae  897008            mov        dword ptr [eax + 8], esi
000008b1  81faff000000      cmp        edx, 0xff
000008b7  beff000000        mov        esi, 0xff
000008bc  0f4cf2            cmovl      esi, edx
000008bf  8b552c            mov        edx, dword ptr [ebp + 0x2c]
000008c2  f30f2ac2          cvtsi2ss   xmm0, edx
000008c6  89700c            mov        dword ptr [eax + 0xc], esi
000008c9  f30f5981b0e3ffff  mulss      xmm0, dword ptr [ecx - 0x1c50]
000008d1  8b4d30            mov        ecx, dword ptr [ebp + 0x30]
000008d4  f30f2ac9          cvtsi2ss   xmm1, ecx
000008d8  8b4d28            mov        ecx, dword ptr [ebp + 0x28]
000008db  894810            mov        dword ptr [eax + 0x10], ecx
000008de  f30f5ec1          divss      xmm0, xmm1
000008e2  f30f11801c080000  movss      dword ptr [eax + 0x81c], xmm0
000008ea  c7803008000081645642  mov        dword ptr [eax + 0x830], 0x42566481
000008f4  8d65fc            lea        esp, [ebp - 4]
000008f7  5e                pop        esi
000008f8  5d                pop        ebp
000008f9  c3                ret        
