# APK member: lib/x86/libnsf2.so
# Library SHA-256: 452e13279ac07ceb7c0366072a52f65dedf77b845331b27d58ba4be9865cb7a5
# Function: Java_sofeh_music_NSF2_Open; address=0x93e0; bytes=173
# Decoder mode: default; decoded_bytes=173
000093e0  55                push       ebp
000093e1  89e5              mov        ebp, esp
000093e3  53                push       ebx
000093e4  57                push       edi
000093e5  56                push       esi
000093e6  83e4f0            and        esp, 0xfffffff0
000093e9  83ec20            sub        esp, 0x20
000093ec  e800000000        call       0x93f1
000093f1  5b                pop        ebx
000093f2  81c3cb1e0000      add        ebx, 0x1ecb
000093f8  8b4510            mov        eax, dword ptr [ebp + 0x10]
000093fb  8b4d08            mov        ecx, dword ptr [ebp + 8]
000093fe  658b1514000000    mov        edx, dword ptr gs:[0x14]
00009405  89542418          mov        dword ptr [esp + 0x18], edx
00009409  8b11              mov        edx, dword ptr [ecx]
0000940b  89442404          mov        dword ptr [esp + 4], eax
0000940f  890c24            mov        dword ptr [esp], ecx
00009412  c744240800000000  mov        dword ptr [esp + 8], 0
0000941a  ff92a4020000      call       dword ptr [edx + 0x2a4]
00009420  8b8b20ffffff      mov        ecx, dword ptr [ebx - 0xe0]
00009426  894c2414          mov        dword ptr [esp + 0x14], ecx
0000942a  f20f108318ffffff  movsd      xmm0, qword ptr [ebx - 0xe8]
00009432  f20f1144240c      movsd      qword ptr [esp + 0xc], xmm0
00009438  8d8b5861ffff      lea        ecx, [ebx - 0x9ea8]
0000943e  894c2404          mov        dword ptr [esp + 4], ecx
00009442  890424            mov        dword ptr [esp], eax
00009445  e8c60b0000        call       0xa010
0000944a  85c0              test       eax, eax
0000944c  741e              je         0x946c
0000944e  8944240c          mov        dword ptr [esp + 0xc], eax
00009452  8d4c240c          lea        ecx, [esp + 0xc]
00009456  890c24            mov        dword ptr [esp], ecx
00009459  89c7              mov        edi, eax
0000945b  e8c00b0000        call       0xa020
00009460  89c6              mov        esi, eax
00009462  893c24            mov        dword ptr [esp], edi
00009465  e8c60b0000        call       0xa030
0000946a  eb02              jmp        0x946e
0000946c  31f6              xor        esi, esi
0000946e  89b388100000      mov        dword ptr [ebx + 0x1088], esi
00009474  65a114000000      mov        eax, dword ptr gs:[0x14]
0000947a  3b442418          cmp        eax, dword ptr [esp + 0x18]
0000947e  7508              jne        0x9488
00009480  8d65f4            lea        esp, [ebp - 0xc]
00009483  5e                pop        esi
00009484  5f                pop        edi
00009485  5b                pop        ebx
00009486  5d                pop        ebp
00009487  c3                ret        
00009488  e8b30b0000        call       0xa040
