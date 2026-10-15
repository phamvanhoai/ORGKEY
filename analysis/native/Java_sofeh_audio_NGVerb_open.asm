# Library: libngverb.so
# Function: Java_sofeh_audio_NGVerb_open; bytes=140
00002044  ef3bba6d  stp      d15, d14, [sp, #-0x60]!
00002048  ed33016d  stp      d13, d12, [sp, #0x10]
0000204c  eb2b026d  stp      d11, d10, [sp, #0x20]
00002050  e923036d  stp      d9, d8, [sp, #0x30]
00002054  fd7b04a9  stp      x29, x30, [sp, #0x40]
00002058  f32b00f9  str      x19, [sp, #0x50]
0000205c  fd030191  add      x29, sp, #0x40
00002060  f303032a  mov      w19, w3
00002064  620200b4  cbz      x2, #0x20b0
00002068  e00302aa  mov      x0, x2
0000206c  e840201e  fmov     s8, s7
00002070  cc40201e  fmov     s12, s6
00002074  a940201e  fmov     s9, s5
00002078  8d40201e  fmov     s13, s4
0000207c  6a40201e  fmov     s10, s3
00002080  4e40201e  fmov     s14, s2
00002084  2b40201e  fmov     s11, s1
00002088  0f40201e  fmov     s15, s0
0000208c  1d030094  bl       #0x2d00
00002090  e041201e  fmov     s0, s15
00002094  6141201e  fmov     s1, s11
00002098  c241201e  fmov     s2, s14
0000209c  4341201e  fmov     s3, s10
000020a0  a441201e  fmov     s4, s13
000020a4  2541201e  fmov     s5, s9
000020a8  8641201e  fmov     s6, s12
000020ac  0741201e  fmov     s7, s8
000020b0  e003132a  mov      w0, w19
000020b4  fd7b44a9  ldp      x29, x30, [sp, #0x40]
000020b8  f32b40f9  ldr      x19, [sp, #0x50]
000020bc  e923436d  ldp      d9, d8, [sp, #0x30]
000020c0  eb2b426d  ldp      d11, d10, [sp, #0x20]
000020c4  ed33416d  ldp      d13, d12, [sp, #0x10]
000020c8  ef3bc66c  ldp      d15, d14, [sp], #0x60
000020cc  ed020014  b        #0x2c80
