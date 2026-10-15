# APK member: lib/x86_64/libnmusic.so
# Library SHA-256: 792e4ca002affd7fa432bd2a8dbf94dc31b829ef32123a5954dd6e376627e35c
# Function: Java_sofeh_music_NMusic_Mix1; address=0x2620; bytes=42
# Decoder mode: default; decoded_bytes=42
00002620  f3480f2ada        cvtsi2ss   xmm3, rdx
00002625  f30f591d3fe7ffff  mulss      xmm3, dword ptr [rip - 0x18c1]
0000262d  f30f2ae1          cvtsi2ss   xmm4, ecx
00002631  f30f5cdc          subss      xmm3, xmm4
00002635  f30f5cd1          subss      xmm2, xmm1
00002639  f30f59d3          mulss      xmm2, xmm3
0000263d  f30f58d1          addss      xmm2, xmm1
00002641  f30f59d0          mulss      xmm2, xmm0
00002645  f30f2cc2          cvttss2si  eax, xmm2
00002649  c3                ret        
