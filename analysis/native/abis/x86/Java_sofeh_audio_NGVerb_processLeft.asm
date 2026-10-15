# APK member: lib/x86/libngverb.so
# Library SHA-256: 8412ca981b3be45bccdf4d07aff9e3554c36594cb309f6bb73e3c87705155f48
# Function: Java_sofeh_audio_NGVerb_processLeft; address=0x2190; bytes=165
# Decoder mode: default; decoded_bytes=165
00002190  55                push       ebp
00002191  89e5              mov        ebp, esp
00002193  53                push       ebx
00002194  83e4f0            and        esp, 0xfffffff0
00002197  83ec40            sub        esp, 0x40
0000219a  e800000000        call       0x219f
0000219f  5b                pop        ebx
000021a0  81c3bd210000      add        ebx, 0x21bd
000021a6  8b4514            mov        eax, dword ptr [ebp + 0x14]
000021a9  8b4d10            mov        ecx, dword ptr [ebp + 0x10]
000021ac  658b1514000000    mov        edx, dword ptr gs:[0x14]
000021b3  89542430          mov        dword ptr [esp + 0x30], edx
000021b7  09c8              or         eax, ecx
000021b9  745e              je         0x2219
000021bb  f30f104518        movss      xmm0, dword ptr [ebp + 0x18]
000021c0  f30f104d1c        movss      xmm1, dword ptr [ebp + 0x1c]
000021c5  0f14c1            unpcklps   xmm0, xmm1
000021c8  0f13442420        movlps     qword ptr [esp + 0x20], xmm0
000021cd  df6c2420          fild       qword ptr [esp + 0x20]
000021d1  d95c241c          fstp       dword ptr [esp + 0x1c]
000021d5  f30f1044241c      movss      xmm0, dword ptr [esp + 0x1c]
000021db  f30f59835cc8ffff  mulss      xmm0, dword ptr [ebx - 0x37a4]
000021e3  8d442428          lea        eax, [esp + 0x28]
000021e7  890424            mov        dword ptr [esp], eax
000021ea  8d54242c          lea        edx, [esp + 0x2c]
000021ee  e84d000000        call       0x2240
000021f3  f30f1044242c      movss      xmm0, dword ptr [esp + 0x2c]
000021f9  f30f598384c8ffff  mulss      xmm0, dword ptr [ebx - 0x377c]
00002201  f30f11442410      movss      dword ptr [esp + 0x10], xmm0
00002207  d9442410          fld        dword ptr [esp + 0x10]
0000220b  dd4c2410          fisttp     qword ptr [esp + 0x10]
0000220f  8b442410          mov        eax, dword ptr [esp + 0x10]
00002213  8b542414          mov        edx, dword ptr [esp + 0x14]
00002217  eb04              jmp        0x221d
00002219  31c0              xor        eax, eax
0000221b  31d2              xor        edx, edx
0000221d  658b0d14000000    mov        ecx, dword ptr gs:[0x14]
00002224  3b4c2430          cmp        ecx, dword ptr [esp + 0x30]
00002228  7506              jne        0x2230
0000222a  8d65fc            lea        esp, [ebp - 4]
0000222d  5b                pop        ebx
0000222e  5d                pop        ebp
0000222f  c3                ret        
00002230  e82b100000        call       0x3260
