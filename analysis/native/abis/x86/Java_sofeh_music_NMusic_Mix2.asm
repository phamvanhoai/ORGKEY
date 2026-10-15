# APK member: lib/x86/libnmusic.so
# Library SHA-256: 051762c43873d3b1e8d0eb96eb91cd82ffbc3e9ca211006f6977da7afa2b80a2
# Function: Java_sofeh_music_NMusic_Mix2; address=0x28d0; bytes=212
# Decoder mode: default; decoded_bytes=212
000028d0  55                push       ebp
000028d1  89e5              mov        ebp, esp
000028d3  83e4fc            and        esp, 0xfffffffc
000028d6  83ec08            sub        esp, 8
000028d9  e800000000        call       0x28de
000028de  58                pop        eax
000028df  81c0c6260000      add        eax, 0x26c6
000028e5  f30f104d18        movss      xmm1, dword ptr [ebp + 0x18]
000028ea  df6d24            fild       qword ptr [ebp + 0x24]
000028ed  d95c2404          fstp       dword ptr [esp + 4]
000028f1  f30f10542404      movss      xmm2, dword ptr [esp + 4]
000028f7  f30f599080baffff  mulss      xmm2, dword ptr [eax - 0x4580]
000028ff  f30f105d14        movss      xmm3, dword ptr [ebp + 0x14]
00002904  f30f2a652c        cvtsi2ss   xmm4, dword ptr [ebp + 0x2c]
00002909  f30f10451c        movss      xmm0, dword ptr [ebp + 0x1c]
0000290e  f30f5cd4          subss      xmm2, xmm4
00002912  0f28e8            movaps     xmm5, xmm0
00002915  f30f5ceb          subss      xmm5, xmm3
00002919  f30f59ea          mulss      xmm5, xmm2
0000291d  0f28e1            movaps     xmm4, xmm1
00002920  f30f58e1          addss      xmm4, xmm1
00002924  f30f58e5          addss      xmm4, xmm5
00002928  f30f10a884baffff  movss      xmm5, dword ptr [eax - 0x457c]
00002930  f30f59e9          mulss      xmm5, xmm1
00002934  f30f598898baffff  mulss      xmm1, dword ptr [eax - 0x4568]
0000293c  f30f107520        movss      xmm6, dword ptr [ebp + 0x20]
00002941  f30f5ccb          subss      xmm1, xmm3
00002945  0f28fb            movaps     xmm7, xmm3
00002948  f30f58fb          addss      xmm7, xmm3
0000294c  f30f58fd          addss      xmm7, xmm5
00002950  f30f109890baffff  movss      xmm3, dword ptr [eax - 0x4570]
00002958  f30f59d8          mulss      xmm3, xmm0
0000295c  f30f58df          addss      xmm3, xmm7
00002960  0f28ea            movaps     xmm5, xmm2
00002963  f30f59ea          mulss      xmm5, xmm2
00002967  f30f59d5          mulss      xmm2, xmm5
0000296b  f30f5cde          subss      xmm3, xmm6
0000296f  f30f59dd          mulss      xmm3, xmm5
00002973  f30f58dc          addss      xmm3, xmm4
00002977  f30f598094baffff  mulss      xmm0, dword ptr [eax - 0x456c]
0000297f  f30f58c1          addss      xmm0, xmm1
00002983  f30f58c6          addss      xmm0, xmm6
00002987  f30f59c2          mulss      xmm0, xmm2
0000298b  f30f58c3          addss      xmm0, xmm3
0000298f  f30f59808cbaffff  mulss      xmm0, dword ptr [eax - 0x4574]
00002997  f30f594510        mulss      xmm0, dword ptr [ebp + 0x10]
0000299c  f30f2cc0          cvttss2si  eax, xmm0
000029a0  89ec              mov        esp, ebp
000029a2  5d                pop        ebp
000029a3  c3                ret        
