# APK member: lib/arm64-v8a/libnbandbooster.so
# Library SHA-256: 800679fa5286d3bf8f6117dc7cd74b413b657f6f9aa4703eaeb7c7b3501e509d
# Function: Java_sofeh_audio_NBandBooster_process; address=0x8d0; bytes=208
# Decoder mode: default; decoded_bytes=208
000008d0  420600b4          cbz        x2, #0x998
000008d4  6000229e          scvtf      s0, x3
000008d8  08f0a552          mov        w8, #0x2f800000
000008dc  4404402d          ldp        s4, s1, [x2]
000008e0  0301271e          fmov       s3, w8
000008e4  4218432d          ldp        s2, s6, [x2, #0x18]
000008e8  482840f9          ldr        x8, [x2, #0x50]
000008ec  2528231e          fadd       s5, s1, s3
000008f0  0138211e          fsub       s1, s0, s1
000008f4  0038221e          fsub       s0, s0, s2
000008f8  4228231e          fadd       s2, s2, s3
000008fc  431440bd          ldr        s3, [x2, #0x14]
00000900  8114011f          fmadd      s1, s4, s1, s5
00000904  6008001f          fmadd      s0, s3, s0, s2
00000908  4214412d          ldp        s2, s5, [x2, #8]
0000090c  2738221e          fsub       s7, s1, s2
00000910  1038261e          fsub       s16, s0, s6
00000914  e208041f          fmadd      s2, s7, s4, s2
00000918  4744442d          ldp        s7, s17, [x2, #0x20]
0000091c  061a031f          fmadd      s6, s16, s3, s6
00000920  5038251e          fsub       s16, s2, s5
00000924  4188002d          stp        s1, s2, [x2, #4]
00000928  d238271e          fsub       s18, s6, s7
0000092c  4018032d          stp        s0, s6, [x2, #0x18]
00000930  0516041f          fmadd      s5, s16, s4, s5
00000934  501040bd          ldr        s16, [x2, #0x10]
00000938  471e031f          fmadd      s7, s18, s3, s7
0000093c  b238301e          fsub       s18, s5, s16
00000940  f338311e          fsub       s19, s7, s17
00000944  4442041f          fmadd      s4, s18, s4, s16
00000948  1001229e          scvtf      s16, x8
0000094c  6346031f          fmadd      s3, s19, s3, s17
00000950  524c452d          ldp        s18, s19, [x2, #0x28]
00000954  520a241e          fmul       s18, s18, s4
00000958  4590012d          stp        s5, s4, [x2, #0xc]
0000095c  7138241e          fsub       s17, s3, s4
00000960  103a231e          fsub       s16, s16, s3
00000964  470c042d          stp        s7, s3, [x2, #0x20]
00000968  310a331e          fmul       s17, s17, s19
0000096c  533040bd          ldr        s19, [x2, #0x30]
00000970  610a301e          fmul       s1, s19, s16
00000974  222a321e          fadd       s2, s17, s18
00000978  52c4062d          stp        s18, s17, [x2, #0x34]
0000097c  413c00bd          str        s1, [x2, #0x3c]
00000980  4028211e          fadd       s0, s2, s1
00000984  4210c03d          ldr        q2, [x2, #0x40]
00000988  432000f9          str        x3, [x2, #0x40]
0000098c  4280843c          stur       q2, [x2, #0x48]
00000990  0000389e          fcvtzs     x0, s0
00000994  c0035fd6          ret        
00000998  e0031faa          mov        x0, xzr
0000099c  c0035fd6          ret        
