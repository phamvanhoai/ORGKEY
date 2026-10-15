# APK member: lib/arm64-v8a/libnbandbooster.so
# Library SHA-256: 800679fa5286d3bf8f6117dc7cd74b413b657f6f9aa4703eaeb7c7b3501e509d
# Function: Java_sofeh_audio_NBandBooster_build; address=0x824; bytes=172
# Decoder mode: default; decoded_bytes=172
00000824  420500b4          cbz        x2, #0x8cc
00000828  e80f1efc          str        d8, [sp, #-0x20]!
0000082c  fdfb00a9          stp        x29, x30, [sp, #8]
00000830  f30f00f9          str        x19, [sp, #0x18]
00000834  fd230091          add        x29, sp, #8
00000838  08349952          mov        w8, #0xc9a0
0000083c  c800221e          scvtf      s8, w6
00000840  6100271e          fmov       s1, w3
00000844  88a5a872          movk       w8, #0x452c, lsl #16
00000848  03f6030f          fmov       v3.2s, #1.00000000
0000084c  a400221e          scvtf      s4, w5
00000850  0001271e          fmov       s0, w8
00000854  48e19a52          mov        w8, #0xd70a
00000858  05102e1e          fmov       s5, #1.00000000
0000085c  811c0c4e          mov        v1.s[1], w4
00000860  6884a772          movk       w8, #0x3c23, lsl #16
00000864  f30302aa          mov        x19, x2
00000868  020d040e          dup        v2.2s, w8
0000086c  0018281e          fdiv       s0, s0, s8
00000870  21d8210e          scvtf      v1.2s, v1.2s
00000874  23cc220e          fmla       v3.2s, v1.2s, v2.2s
00000878  0101271e          fmov       s1, w8
0000087c  8114011f          fmadd      s1, s4, s1, s5
00000880  431400fd          str        d3, [x2, #0x28]
00000884  00c0221e          fcvt       d0, s0
00000888  413000bd          str        s1, [x2, #0x30]
0000088c  65000094          bl         #0xa20
00000890  68fb8d52          mov        w8, #0x6fdb
00000894  0040621e          fcvt       s0, d0
00000898  a8cea872          movk       w8, #0x4675, lsl #16
0000089c  0101271e          fmov       s1, w8
000008a0  0228201e          fadd       s2, s0, s0
000008a4  2118281e          fdiv       s1, s1, s8
000008a8  620200bd          str        s2, [x19]
000008ac  20c0221e          fcvt       d0, s1
000008b0  5c000094          bl         #0xa20
000008b4  0040621e          fcvt       s0, d0
000008b8  0028201e          fadd       s0, s0, s0
000008bc  601600bd          str        s0, [x19, #0x14]
000008c0  fdfb40a9          ldp        x29, x30, [sp, #8]
000008c4  f30f40f9          ldr        x19, [sp, #0x18]
000008c8  e80742fc          ldr        d8, [sp], #0x20
000008cc  c0035fd6          ret        
