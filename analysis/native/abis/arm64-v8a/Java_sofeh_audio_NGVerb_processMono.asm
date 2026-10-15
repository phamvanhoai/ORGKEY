# APK member: lib/arm64-v8a/libngverb.so
# Library SHA-256: f7967cfd6f4c70e596e7fa92e524bebcca0029509265993c59ff91bba9a05620
# Function: Java_sofeh_audio_NGVerb_processMono; address=0x2824; bytes=160
# Decoder mode: default; decoded_bytes=160
00002824  c20400b4          cbz        x2, #0x28bc
00002828  fd7bbda9          stp        x29, x30, [sp, #-0x30]!
0000282c  f65701a9          stp        x22, x21, [sp, #0x10]
00002830  f44f02a9          stp        x20, x19, [sp, #0x20]
00002834  fd030091          mov        x29, sp
00002838  8000221e          scvtf      s0, w4
0000283c  0859a852          mov        w8, #0x42c80000
00002840  35000090          adrp       x21, #0x6000
00002844  0101271e          fmov       s1, w8
00002848  36000090          adrp       x22, #0x6000
0000284c  b5ae47f9          ldr        x21, [x21, #0xf58]
00002850  d6b647f9          ldr        x22, [x22, #0xf68]
00002854  33000090          adrp       x19, #0x6000
00002858  34000090          adrp       x20, #0x6000
0000285c  e00302aa          mov        x0, x2
00002860  0118211e          fdiv       s1, s0, s1
00002864  60c4029e          scvtf      s0, x3, #0xf
00002868  a00200bd          str        s0, [x21]
0000286c  c10200bd          str        s1, [x22]
00002870  73ba47f9          ldr        x19, [x19, #0xf70]
00002874  94be47f9          ldr        x20, [x20, #0xf78]
00002878  e10313aa          mov        x1, x19
0000287c  e20314aa          mov        x2, x20
00002880  2cfeff97          bl         #0x2130
00002884  600240bd          ldr        s0, [x19]
00002888  810240bd          ldr        s1, [x20]
0000288c  c20240bd          ldr        s2, [x22]
00002890  0028211e          fadd       s0, s0, s1
00002894  01102e1e          fmov       s1, #1.00000000
00002898  2138221e          fsub       s1, s1, s2
0000289c  4008201e          fmul       s0, s2, s0
000028a0  a20240bd          ldr        s2, [x21]
000028a4  4000011f          fmadd      s0, s2, s1, s0
000028a8  00c4189e          fcvtzs     x0, s0, #0xf
000028ac  f44f42a9          ldp        x20, x19, [sp, #0x20]
000028b0  f65741a9          ldp        x22, x21, [sp, #0x10]
000028b4  fd7bc3a8          ldp        x29, x30, [sp], #0x30
000028b8  c0035fd6          ret        
000028bc  e0031faa          mov        x0, xzr
000028c0  c0035fd6          ret        
