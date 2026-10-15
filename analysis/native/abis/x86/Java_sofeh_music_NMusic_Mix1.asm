# APK member: lib/x86/libnmusic.so
# Library SHA-256: 051762c43873d3b1e8d0eb96eb91cd82ffbc3e9ca211006f6977da7afa2b80a2
# Function: Java_sofeh_music_NMusic_Mix1; address=0x2870; bytes=86
# Decoder mode: default; decoded_bytes=86
00002870  55                push       ebp
00002871  89e5              mov        ebp, esp
00002873  83e4fc            and        esp, 0xfffffffc
00002876  83ec08            sub        esp, 8
00002879  e800000000        call       0x287e
0000287e  58                pop        eax
0000287f  81c026270000      add        eax, 0x2726
00002885  f30f104d14        movss      xmm1, dword ptr [ebp + 0x14]
0000288a  f30f104518        movss      xmm0, dword ptr [ebp + 0x18]
0000288f  df6d1c            fild       qword ptr [ebp + 0x1c]
00002892  d95c2404          fstp       dword ptr [esp + 4]
00002896  f30f10542404      movss      xmm2, dword ptr [esp + 4]
0000289c  f30f2a5d24        cvtsi2ss   xmm3, dword ptr [ebp + 0x24]
000028a1  f30f599080baffff  mulss      xmm2, dword ptr [eax - 0x4580]
000028a9  f30f5cd3          subss      xmm2, xmm3
000028ad  f30f5cc1          subss      xmm0, xmm1
000028b1  f30f59c2          mulss      xmm0, xmm2
000028b5  f30f58c1          addss      xmm0, xmm1
000028b9  f30f594510        mulss      xmm0, dword ptr [ebp + 0x10]
000028be  f30f2cc0          cvttss2si  eax, xmm0
000028c2  89ec              mov        esp, ebp
000028c4  5d                pop        ebp
000028c5  c3                ret        
