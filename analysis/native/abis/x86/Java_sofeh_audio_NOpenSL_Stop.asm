# APK member: lib/x86/libnopensl.so
# Library SHA-256: 81e6220973b454100123e9259f13717edc34b683f704a7e2f342ff6424ed7706
# Function: Java_sofeh_audio_NOpenSL_Stop; address=0x28d0; bytes=165
# Decoder mode: default; decoded_bytes=165
000028d0  55                push       ebp
000028d1  89e5              mov        ebp, esp
000028d3  53                push       ebx
000028d4  56                push       esi
000028d5  83e4f0            and        esp, 0xfffffff0
000028d8  83ec10            sub        esp, 0x10
000028db  e800000000        call       0x28e0
000028e0  5b                pop        ebx
000028e1  81c3bc140000      add        ebx, 0x14bc
000028e7  8db368901000      lea        esi, [ebx + 0x109068]
000028ed  893424            mov        dword ptr [esp], esi
000028f0  e86b030000        call       0x2c60
000028f5  c7835c90000003000000  mov        dword ptr [ebx + 0x905c], 3
000028ff  c6838490100000    mov        byte ptr [ebx + 0x109084], 0
00002906  8b8388901000      mov        eax, dword ptr [ebx + 0x109088]
0000290c  85c0              test       eax, eax
0000290e  740f              je         0x291f
00002910  8b08              mov        ecx, dword ptr [eax]
00002912  890424            mov        dword ptr [esp], eax
00002915  c744240401000000  mov        dword ptr [esp + 4], 1
0000291d  ff11              call       dword ptr [ecx]
0000291f  8b838c901000      mov        eax, dword ptr [ebx + 0x10908c]
00002925  85c0              test       eax, eax
00002927  7408              je         0x2931
00002929  8b08              mov        ecx, dword ptr [eax]
0000292b  890424            mov        dword ptr [esp], eax
0000292e  ff5104            call       dword ptr [ecx + 4]
00002931  8b8390901000      mov        eax, dword ptr [ebx + 0x109090]
00002937  85c0              test       eax, eax
00002939  740f              je         0x294a
0000293b  8b08              mov        ecx, dword ptr [eax]
0000293d  890424            mov        dword ptr [esp], eax
00002940  c744240401000000  mov        dword ptr [esp + 4], 1
00002948  ff11              call       dword ptr [ecx]
0000294a  8b8364900800      mov        eax, dword ptr [ebx + 0x89064]
00002950  85c0              test       eax, eax
00002952  7408              je         0x295c
00002954  8b08              mov        ecx, dword ptr [eax]
00002956  890424            mov        dword ptr [esp], eax
00002959  ff5104            call       dword ptr [ecx + 4]
0000295c  c7835c90000000000000  mov        dword ptr [ebx + 0x905c], 0
00002966  893424            mov        dword ptr [esp], esi
00002969  e802030000        call       0x2c70
0000296e  8d65f8            lea        esp, [ebp - 8]
00002971  5e                pop        esi
00002972  5b                pop        ebx
00002973  5d                pop        ebp
00002974  c3                ret        
