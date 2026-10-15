# Function: Java_sofeh_audio_Lame_flush; bytes=148
00035ffc  fd7bbda9  stp      x29, x30, [sp, #-0x30]!
00036000  f65701a9  stp      x22, x21, [sp, #0x10]
00036004  f44f02a9  stp      x20, x19, [sp, #0x20]
00036008  fd030091  mov      x29, sp
0003600c  080040f9  ldr      x8, [x0]
00036010  e10302aa  mov      x1, x2
00036014  f30300aa  mov      x19, x0
00036018  f40302aa  mov      x20, x2
0003601c  08ad42f9  ldr      x8, [x8, #0x558]
00036020  00013fd6  blr      x8
00036024  680240f9  ldr      x8, [x19]
00036028  f503002a  mov      w21, w0
0003602c  e00313aa  mov      x0, x19
00036030  e10314aa  mov      x1, x20
00036034  e2031faa  mov      x2, xzr
00036038  08e142f9  ldr      x8, [x8, #0x5c0]
0003603c  00013fd6  blr      x8
00036040  c80000d0  adrp     x8, #0x50000
00036044  f60300aa  mov      x22, x0
00036048  e203152a  mov      w2, w21
0003604c  089d42f9  ldr      x8, [x8, #0x538]
00036050  e10316aa  mov      x1, x22
00036054  e00308aa  mov      x0, x8
00036058  7e020094  bl       #0x36a50
0003605c  680240f9  ldr      x8, [x19]
00036060  f503002a  mov      w21, w0
00036064  e00313aa  mov      x0, x19
00036068  e10314aa  mov      x1, x20
0003606c  e20316aa  mov      x2, x22
00036070  e3031f2a  mov      w3, wzr
00036074  080143f9  ldr      x8, [x8, #0x600]
00036078  00013fd6  blr      x8
0003607c  e003152a  mov      w0, w21
00036080  f44f42a9  ldp      x20, x19, [sp, #0x20]
00036084  f65741a9  ldp      x22, x21, [sp, #0x10]
00036088  fd7bc3a8  ldp      x29, x30, [sp], #0x30
0003608c  c0035fd6  ret      
