# APK member: lib/arm64-v8a/libnmusic.so
# Library SHA-256: 282be49ee24cb3822cc941fb9003d9259c93cd6ea56238426f4f24b018d78156
# Function: Java_sofeh_music_NMusic_Mix2; address=0x235c; bytes=108
# Decoder mode: default; decoded_bytes=108
0000235c  0590321e          fmov       s5, #-5.00000000
00002360  46cc029e          scvtf      s6, x2, #0xd
00002364  6700221e          scvtf      s7, w3
00002368  1010201e          fmov       s16, #2.00000000
0000236c  1110221e          fmov       s17, #4.00000000
00002370  1210211e          fmov       s18, #3.00000000
00002374  4508251e          fmul       s5, s2, s5
00002378  c638271e          fsub       s6, s6, s7
0000237c  6738211e          fsub       s7, s3, s1
00002380  2514101f          fmadd      s5, s1, s16, s5
00002384  4184321f          fnmsub     s1, s2, s18, s1
00002388  e708261e          fmul       s7, s7, s6
0000238c  6514111f          fmadd      s5, s3, s17, s5
00002390  1110311e          fmov       s17, #-3.00000000
00002394  421c101f          fmadd      s2, s2, s16, s7
00002398  c708261e          fmul       s7, s6, s6
0000239c  6104111f          fmadd      s1, s3, s17, s1
000023a0  a538241e          fsub       s5, s5, s4
000023a4  c308271e          fmul       s3, s6, s7
000023a8  2128241e          fadd       s1, s1, s4
000023ac  a208071f          fmadd      s2, s5, s7, s2
000023b0  2108031f          fmadd      s1, s1, s3, s2
000023b4  02102c1e          fmov       s2, #0.50000000
000023b8  2108221e          fmul       s1, s1, s2
000023bc  2008201e          fmul       s0, s1, s0
000023c0  0000381e          fcvtzs     w0, s0
000023c4  c0035fd6          ret        
