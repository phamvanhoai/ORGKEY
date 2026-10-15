# Function: Java_sofeh_audio_NGVerb_setrevtime; bytes=256
00002aa8  e20700b4  cbz      x2, #0x2ba4
00002aac  ea0f1cfc  str      d10, [sp, #-0x40]!
00002ab0  e923016d  stp      d9, d8, [sp, #0x10]
00002ab4  fd7b02a9  stp      x29, x30, [sp, #0x20]
00002ab8  f44f03a9  stp      x20, x19, [sp, #0x30]
00002abc  fd830091  add      x29, sp, #0x20
00002ac0  410040bd  ldr      s1, [x2]
00002ac4  02102e1e  fmov     s2, #1.00000000
00002ac8  e84d8252  mov      w8, #0x126f
00002acc  6850a772  movk     w8, #0x3a83, lsl #16
00002ad0  402000bd  str      s0, [x2, #0x20]
00002ad4  f30302aa  mov      x19, x2
00002ad8  21d8215e  scvtf    s1, s1
00002adc  2108201e  fmul     s1, s1, s0
00002ae0  0001271e  fmov     s0, w8
00002ae4  4118211e  fdiv     s1, s2, s1
00002ae8  7e000094  bl       #0x2ce0
00002aec  0840201e  fmov     s8, s0
00002af0  00c0221e  fcvt     d0, s0
00002af4  604e00fd  str      d0, [x19, #0x98]
00002af8  73d243a9  ldp      x19, x20, [x19, #0x38]
00002afc  800240bd  ldr      s0, [x20]
00002b00  01d8215e  scvtf    s1, s0
00002b04  0041201e  fmov     s0, s8
00002b08  76000094  bl       #0x2ce0
00002b0c  e8519852  mov      w8, #0xc28f
00002b10  0140211e  fneg     s1, s0
00002b14  a8eea772  movk     w8, #0x3f75, lsl #16
00002b18  0901271e  fmov     s9, w8
00002b1c  e8519852  mov      w8, #0xc28f
00002b20  a8eeb772  movk     w8, #0xbf75, lsl #16
00002b24  0a01271e  fmov     s10, w8
00002b28  0020291e  fcmp     s0, s9
00002b2c  204c2a1e  fcsel    s0, s1, s10, mi
00002b30  600200bd  str      s0, [x19]
00002b34  800640bd  ldr      s0, [x20, #4]
00002b38  01d8215e  scvtf    s1, s0
00002b3c  0041201e  fmov     s0, s8
00002b40  68000094  bl       #0x2ce0
00002b44  0140211e  fneg     s1, s0
00002b48  0020291e  fcmp     s0, s9
00002b4c  204c2a1e  fcsel    s0, s1, s10, mi
00002b50  600600bd  str      s0, [x19, #4]
00002b54  800a40bd  ldr      s0, [x20, #8]
00002b58  01d8215e  scvtf    s1, s0
00002b5c  0041201e  fmov     s0, s8
00002b60  60000094  bl       #0x2ce0
00002b64  0140211e  fneg     s1, s0
00002b68  0020291e  fcmp     s0, s9
00002b6c  204c2a1e  fcsel    s0, s1, s10, mi
00002b70  600a00bd  str      s0, [x19, #8]
00002b74  800e40bd  ldr      s0, [x20, #0xc]
00002b78  01d8215e  scvtf    s1, s0
00002b7c  0041201e  fmov     s0, s8
00002b80  58000094  bl       #0x2ce0
00002b84  0140211e  fneg     s1, s0
00002b88  0020291e  fcmp     s0, s9
00002b8c  204c2a1e  fcsel    s0, s1, s10, mi
00002b90  600e00bd  str      s0, [x19, #0xc]
00002b94  f44f43a9  ldp      x20, x19, [sp, #0x30]
00002b98  fd7b42a9  ldp      x29, x30, [sp, #0x20]
00002b9c  e923416d  ldp      d9, d8, [sp, #0x10]
00002ba0  ea0744fc  ldr      d10, [sp], #0x40
00002ba4  c0035fd6  ret      
