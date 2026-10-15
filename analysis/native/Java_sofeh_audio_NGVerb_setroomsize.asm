# Function: Java_sofeh_audio_NGVerb_setroomsize; bytes=484
000028c4  020f00b4  cbz      x2, #0x2aa4
000028c8  ff0302d1  sub      sp, sp, #0x80
000028cc  ea2300fd  str      d10, [sp, #0x40]
000028d0  e923056d  stp      d9, d8, [sp, #0x50]
000028d4  fd7b06a9  stp      x29, x30, [sp, #0x60]
000028d8  f44f07a9  stp      x20, x19, [sp, #0x70]
000028dc  fd830191  add      x29, sp, #0x60
000028e0  0020201e  fcmp     s0, s0
000028e4  01102e1e  fmov     s1, #1.00000000
000028e8  420040bd  ldr      s2, [x2]
000028ec  88a09552  mov      w8, #0xad04
000028f0  f30302aa  mov      x19, x2
000028f4  42d8215e  scvtf    s2, s2
000028f8  0868a772  movk     w8, #0x3b40, lsl #16
000028fc  0074211e  fccmp    s0, s1, #0, vc
00002900  209c201e  fcsel    s0, s1, s0, ls
00002904  0108221e  fmul     s1, s0, s2
00002908  0201271e  fmov     s2, w8
0000290c  a8af8052  mov      w8, #0x57d
00002910  28eaa772  movk     w8, #0x3f51, lsl #16
00002914  401c00bd  str      s0, [x2, #0x1c]
00002918  2108221e  fmul     s1, s1, s2
0000291c  0201271e  fmov     s2, w8
00002920  e8ffffd0  adrp     x8, #0
00002924  039147fd  ldr      d3, [x8, #0xf20]
00002928  0868a952  mov      w8, #0x4b400000
0000292c  2208221e  fmul     s2, s1, s2
00002930  412800bd  str      s1, [x2, #0x28]
00002934  6390810f  fmul     v3.2s, v3.2s, v1.s[0]
00002938  41040c6e  mov      v1.s[1], v2.s[0]
0000293c  020d044e  dup      v2.4s, w8
00002940  0898b652  mov      w8, #-0x4b400000
00002944  e20f803d  str      q2, [sp, #0x30]
00002948  6104186e  mov      v1.d[1], v3.d[0]
0000294c  21d4224e  fadd     v1.4s, v1.4s, v2.4s
00002950  020d044e  dup      v2.4s, w8
00002954  54a043a9  ldp      x20, x8, [x2, #0x38]
00002958  2284a24e  add      v2.4s, v1.4s, v2.4s
0000295c  414c40fd  ldr      d1, [x2, #0x98]
00002960  2840621e  fcvt     s8, d1
00002964  43d8210e  scvtf    v3.2s, v2.2s
00002968  0201803d  str      q2, [x8]
0000296c  0041201e  fmov     s0, s8
00002970  61040c5e  mov      s1, v3.s[1]
00002974  e38b00ad  stp      q3, q2, [sp, #0x10]
00002978  da000094  bl       #0x2ce0
0000297c  e003803d  str      q0, [sp]
00002980  e107c03d  ldr      q1, [sp, #0x10]
00002984  0041201e  fmov     s0, s8
00002988  d6000094  bl       #0x2ce0
0000298c  e8519852  mov      w8, #0xc28f
00002990  e103c03d  ldr      q1, [sp]
00002994  a8eea772  movk     w8, #0x3f75, lsl #16
00002998  20040c6e  mov      v0.s[1], v1.s[0]
0000299c  090d040e  dup      v9.2s, w8
000029a0  e10bc03d  ldr      q1, [sp, #0x20]
000029a4  e8519852  mov      w8, #0xc28f
000029a8  a8eeb772  movk     w8, #0xbf75, lsl #16
000029ac  2140016e  ext      v1.16b, v1.16b, v1.16b, #8
000029b0  0a0d040e  dup      v10.2s, w8
000029b4  02f8a02e  fneg     v2.2s, v0.2s
000029b8  20e5a02e  fcmgt    v0.2s, v9.2s, v0.2s
000029bc  21d8210e  scvtf    v1.2s, v1.2s
000029c0  401c6a2e  bsl      v0.8b, v2.8b, v10.8b
000029c4  e107803d  str      q1, [sp, #0x10]
000029c8  21040c5e  mov      s1, v1.s[1]
000029cc  800200fd  str      d0, [x20]
000029d0  0041201e  fmov     s0, s8
000029d4  c3000094  bl       #0x2ce0
000029d8  e00b803d  str      q0, [sp, #0x20]
000029dc  e107c03d  ldr      q1, [sp, #0x10]
000029e0  0041201e  fmov     s0, s8
000029e4  bf000094  bl       #0x2ce0
000029e8  e10bc03d  ldr      q1, [sp, #0x20]
000029ec  e8ffffd0  adrp     x8, #0
000029f0  20040c6e  mov      v0.s[1], v1.s[0]
000029f4  01f8a02e  fneg     v1.2s, v0.2s
000029f8  20e5a02e  fcmgt    v0.2s, v9.2s, v0.2s
000029fc  201c6a2e  bsl      v0.8b, v1.8b, v10.8b
00002a00  01d1c33d  ldr      q1, [x8, #0xf40]
00002a04  a8008052  mov      w8, #5
00002a08  0898b672  movk     w8, #0xb4c0, lsl #16
00002a0c  800600fd  str      d0, [x20, #8]
00002a10  602a40bd  ldr      s0, [x19, #0x28]
00002a14  2090804f  fmul     v0.4s, v1.4s, v0.s[0]
00002a18  e10fc03d  ldr      q1, [sp, #0x30]
00002a1c  00d4214e  fadd     v0.4s, v0.4s, v1.4s
00002a20  010d044e  dup      v1.4s, w8
00002a24  0084a14e  add      v0.4s, v0.4s, v1.4s
00002a28  0800261e  fmov     w8, s0
00002a2c  e00f803d  str      q0, [sp, #0x30]
00002a30  0101221e  scvtf    s1, w8
00002a34  684e47a9  ldp      x8, x19, [x19, #0x70]
00002a38  0001803d  str      q0, [x8]
00002a3c  0041201e  fmov     s0, s8
00002a40  a8000094  bl       #0x2ce0
00002a44  e10fc03d  ldr      q1, [sp, #0x30]
00002a48  600200bd  str      s0, [x19]
00002a4c  0041201e  fmov     s0, s8
00002a50  283c0c0e  mov      w8, v1.s[1]
00002a54  0101221e  scvtf    s1, w8
00002a58  a2000094  bl       #0x2ce0
00002a5c  e10fc03d  ldr      q1, [sp, #0x30]
00002a60  600600bd  str      s0, [x19, #4]
00002a64  0041201e  fmov     s0, s8
00002a68  283c140e  mov      w8, v1.s[2]
00002a6c  0101221e  scvtf    s1, w8
00002a70  9c000094  bl       #0x2ce0
00002a74  e10fc03d  ldr      q1, [sp, #0x30]
00002a78  600a00bd  str      s0, [x19, #8]
00002a7c  0041201e  fmov     s0, s8
00002a80  283c1c0e  mov      w8, v1.s[3]
00002a84  0101221e  scvtf    s1, w8
00002a88  96000094  bl       #0x2ce0
00002a8c  600e00bd  str      s0, [x19, #0xc]
00002a90  f44f47a9  ldp      x20, x19, [sp, #0x70]
00002a94  ea2340fd  ldr      d10, [sp, #0x40]
00002a98  fd7b46a9  ldp      x29, x30, [sp, #0x60]
00002a9c  e923456d  ldp      d9, d8, [sp, #0x50]
00002aa0  ff030291  add      sp, sp, #0x80
00002aa4  c0035fd6  ret      
