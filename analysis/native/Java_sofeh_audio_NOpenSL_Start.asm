# APK member: lib/arm64-v8a/libnopensl.so
# Library SHA-256: dc900f9de170ec71471d8b5d2ccecc14d0bbb8c1d2346bd6e149467c888b38cf
# Function: Java_sofeh_audio_NOpenSL_Start; address=0x1a00; bytes=2632
# ELF virtual addresses, not runtime absolute addresses. Indirect calls unresolved.
00001a00  ff8305d1  sub      sp, sp, #0x160
00001a04  fd7b10a9  stp      x29, x30, [sp, #0x100]
00001a08  fc6f11a9  stp      x28, x27, [sp, #0x110]
00001a0c  fa6712a9  stp      x26, x25, [sp, #0x120]
00001a10  f85f13a9  stp      x24, x23, [sp, #0x130]
00001a14  f65714a9  stp      x22, x21, [sp, #0x140]
00001a18  f44f15a9  stp      x20, x19, [sp, #0x150]
00001a1c  fd030491  add      x29, sp, #0x100
00001a20  5bd03bd5  mrs      x27, tpidr_el0
00001a24  f30300aa  mov      x19, x0
00001a28  800800b0  adrp     x0, #0x112000
00001a2c  00e03d91  add      x0, x0, #0xf78
00001a30  681740f9  ldr      x8, [x27, #0x28]
00001a34  fc03062a  mov      w28, w6
00001a38  f603052a  mov      w22, w5
00001a3c  f703042a  mov      w23, w4
00001a40  f503032a  mov      w21, w3
00001a44  f80302aa  mov      x24, x2
00001a48  a8031ff8  stur     x8, [x29, #-0x10]
00001a4c  91040094  bl       #0x2c90 ; pthread_mutex_lock@plt
00001a50  910000b0  adrp     x17, #0x12000
00001a54  34008052  mov      w20, #1
00001a58  880800b0  adrp     x8, #0x112000
00001a5c  346a0fb9  str      w20, [x17, #0xf68]
00001a60  1f0300f1  cmp      x24, #0
00001a64  8d0800b0  adrp     x13, #0x112000
00001a68  08d147f9  ldr      x8, [x8, #0xfa0]
00001a6c  e9179f1a  cset     w9, eq
00001a70  8e0800b0  adrp     x14, #0x112000
00001a74  8c0800b0  adrp     x12, #0x112000
00001a78  8a0800b0  adrp     x10, #0x112000
00001a7c  8b0800b0  adrp     x11, #0x112000
00001a80  480200b4  cbz      x8, #0x1ac8
00001a84  afa17e39  ldrb     w15, [x13, #0xfa8]
00001a88  ff01096b  cmp      w15, w9
00001a8c  e1010054  b.ne     #0x1ac8
00001a90  cfad4fb9  ldr      w15, [x14, #0xfac]
00001a94  ff01156b  cmp      w15, w21
00001a98  81010054  b.ne     #0x1ac8
00001a9c  8fb14fb9  ldr      w15, [x12, #0xfb0]
00001aa0  ff01176b  cmp      w15, w23
00001aa4  21010054  b.ne     #0x1ac8
00001aa8  4fb54fb9  ldr      w15, [x10, #0xfb4]
00001aac  ff01166b  cmp      w15, w22
00001ab0  c1000054  b.ne     #0x1ac8
00001ab4  9f1f0072  tst      w28, #0xff
00001ab8  6fb94fb9  ldr      w15, [x11, #0xfb8]
00001abc  f0079f1a  cset     w16, ne
00001ac0  ff01106b  cmp      w15, w16
00001ac4  f4079f1a  cset     w20, ne
00001ac8  9f1f0072  tst      w28, #0xff
00001acc  a9a13e39  strb     w9, [x13, #0xfa8]
00001ad0  e9079f1a  cset     w9, ne
00001ad4  d5ad0fb9  str      w21, [x14, #0xfac]
00001ad8  97b10fb9  str      w23, [x12, #0xfb0]
00001adc  56b50fb9  str      w22, [x10, #0xfb4]
00001ae0  69b90fb9  str      w9, [x11, #0xfb8]
00001ae4  e80600b4  cbz      x8, #0x1bc0
00001ae8  d4060034  cbz      w20, #0x1bc0
00001aec  68008052  mov      w8, #3
00001af0  890800b0  adrp     x9, #0x112000
00001af4  286a0fb9  str      w8, [x17, #0xf68]
00001af8  880800b0  adrp     x8, #0x112000
00001afc  3ff13e39  strb     wzr, [x9, #0xfbc]
00001b00  00e147f9  ldr      x0, [x8, #0xfc0]
00001b04  a00000b4  cbz      x0, #0x1b18
00001b08  080040f9  ldr      x8, [x0]
00001b0c  21008052  mov      w1, #1
00001b10  080140f9  ldr      x8, [x8]
00001b14  00013fd6  blr      x8
00001b18  880800b0  adrp     x8, #0x112000
00001b1c  00e547f9  ldr      x0, [x8, #0xfc8]
00001b20  800000b4  cbz      x0, #0x1b30
00001b24  080040f9  ldr      x8, [x0]
00001b28  080540f9  ldr      x8, [x8, #8]
00001b2c  00013fd6  blr      x8
00001b30  880800b0  adrp     x8, #0x112000
00001b34  00e947f9  ldr      x0, [x8, #0xfd0]
00001b38  a00000b4  cbz      x0, #0x1b4c
00001b3c  080040f9  ldr      x8, [x0]
00001b40  21008052  mov      w1, #1
00001b44  080140f9  ldr      x8, [x8]
00001b48  00013fd6  blr      x8
00001b4c  880400b0  adrp     x8, #0x92000
00001b50  00b947f9  ldr      x0, [x8, #0xf70]
00001b54  800000b4  cbz      x0, #0x1b64
00001b58  080040f9  ldr      x8, [x0]
00001b5c  080540f9  ldr      x8, [x8, #8]
00001b60  00013fd6  blr      x8
00001b64  800800b0  adrp     x0, #0x112000
00001b68  00e03d91  add      x0, x0, #0xf78
00001b6c  4d040094  bl       #0x2ca0 ; pthread_mutex_unlock@plt
00001b70  990800b0  adrp     x25, #0x112000
00001b74  28db4fb9  ldr      w8, [x25, #0xfd8]
00001b78  1f050071  cmp      w8, #1
00001b7c  4b010054  b.lt     #0x1ba4
00001b80  fa031f2a  mov      w26, wzr
00001b84  007d8052  mov      w0, #0x3e8
00001b88  2e040094  bl       #0x2c40 ; usleep@plt
00001b8c  28db4fb9  ldr      w8, [x25, #0xfd8]
00001b90  1f050071  cmp      w8, #1
00001b94  8b000054  b.lt     #0x1ba4
00001b98  5f9f0f71  cmp      w26, #0x3e7
00001b9c  5a070011  add      w26, w26, #1
00001ba0  23ffff54  b.lo     #0x1b84
00001ba4  800800b0  adrp     x0, #0x112000
00001ba8  00e03d91  add      x0, x0, #0xf78
00001bac  39040094  bl       #0x2c90 ; pthread_mutex_lock@plt
00001bb0  e00313aa  mov      x0, x19
00001bb4  25020094  bl       #0x2448
00001bb8  880800b0  adrp     x8, #0x112000
00001bbc  1ff107f9  str      xzr, [x8, #0xfe0]
00001bc0  9a0800b0  adrp     x26, #0x112000
00001bc4  41f747f9  ldr      x1, [x26, #0xfe8]
00001bc8  c10000b4  cbz      x1, #0x1be0
00001bcc  680240f9  ldr      x8, [x19]
00001bd0  e00313aa  mov      x0, x19
00001bd4  085940f9  ldr      x8, [x8, #0xb0]
00001bd8  00013fd6  blr      x8
00001bdc  5ff707f9  str      xzr, [x26, #0xfe8]
00001be0  990800b0  adrp     x25, #0x112000
00001be4  21fb47f9  ldr      x1, [x25, #0xff0]
00001be8  c10000b4  cbz      x1, #0x1c00
00001bec  680240f9  ldr      x8, [x19]
00001bf0  e00313aa  mov      x0, x19
00001bf4  085940f9  ldr      x8, [x8, #0xb0]
00001bf8  00013fd6  blr      x8
00001bfc  3ffb07f9  str      xzr, [x25, #0xff0]
00001c00  fb0700f9  str      x27, [sp, #8]
00001c04  9b0800b0  adrp     x27, #0x112000
00001c08  880800d0  adrp     x8, #0x113000
00001c0c  fc0700b9  str      w28, [sp, #4]
00001c10  7fff07f9  str      xzr, [x27, #0xff8]
00001c14  1f0100f9  str      xzr, [x8]
00001c18  180500b4  cbz      x24, #0x1cb8
00001c1c  680240f9  ldr      x8, [x19]
00001c20  810800d0  adrp     x1, #0x113000
00001c24  21000091  add      x1, x1, #0
00001c28  e00313aa  mov      x0, x19
00001c2c  086d43f9  ldr      x8, [x8, #0x6d8]
00001c30  00013fd6  blr      x8
00001c34  680240f9  ldr      x8, [x19]
00001c38  e00313aa  mov      x0, x19
00001c3c  e10318aa  mov      x1, x24
00001c40  085540f9  ldr      x8, [x8, #0xa8]
00001c44  00013fd6  blr      x8
00001c48  40f707f9  str      x0, [x26, #0xfe8]
00001c4c  e00313aa  mov      x0, x19
00001c50  e10318aa  mov      x1, x24
00001c54  680240f9  ldr      x8, [x19]
00001c58  087d40f9  ldr      x8, [x8, #0xf8]
00001c5c  00013fd6  blr      x8
00001c60  680240f9  ldr      x8, [x19]
00001c64  f80300aa  mov      x24, x0
00001c68  e00313aa  mov      x0, x19
00001c6c  e10318aa  mov      x1, x24
00001c70  085540f9  ldr      x8, [x8, #0xa8]
00001c74  00013fd6  blr      x8
00001c78  20fb07f9  str      x0, [x25, #0xff0]
00001c7c  e00313aa  mov      x0, x19
00001c80  e10318aa  mov      x1, x24
00001c84  680240f9  ldr      x8, [x19]
00001c88  085d40f9  ldr      x8, [x8, #0xb8]
00001c8c  00013fd6  blr      x8
00001c90  680240f9  ldr      x8, [x19]
00001c94  21fb47f9  ldr      x1, [x25, #0xff0]
00001c98  1f2003d5  nop      
00001c9c  0272ff10  adr      x2, #0xadc
00001ca0  e3fffff0  adrp     x3, #0
00001ca4  63602b91  add      x3, x3, #0xad8
00001ca8  088540f9  ldr      x8, [x8, #0x108]
00001cac  e00313aa  mov      x0, x19
00001cb0  00013fd6  blr      x8
00001cb4  60ff07f9  str      x0, [x27, #0xff8]
00001cb8  880000b0  adrp     x8, #0x12000
00001cbc  890000b0  adrp     x9, #0x12000
00001cc0  ea7a1f53  lsl      w10, w23, #1
00001cc4  1f4d0fb9  str      wzr, [x8, #0xf4c]
00001cc8  880000b0  adrp     x8, #0x12000
00001ccc  8b0800d0  adrp     x11, #0x113000
00001cd0  3f490fb9  str      wzr, [x9, #0xf48]
00001cd4  890000b0  adrp     x9, #0x12000
00001cd8  5f114071  cmp      w10, #4, lsl #12
00001cdc  1f5d0fb9  str      wzr, [x8, #0xf5c]
00001ce0  880000b0  adrp     x8, #0x12000
00001ce4  1f2003d5  nop      
00001ce8  57940810  adr      x23, #0x12f70
00001cec  3f610fb9  str      wzr, [x9, #0xf60]
00001cf0  09008852  mov      w9, #0x4000
00001cf4  1f6d0fb9  str      wzr, [x8, #0xf6c]
00001cf8  48b1891a  csel     w8, w10, w9, lt
00001cfc  df0e0071  cmp      w22, #3
00001d00  69008052  mov      w9, #3
00001d04  7f0900b9  str      wzr, [x11, #8]
00001d08  8a0000b0  adrp     x10, #0x12000
00001d0c  c9c2891a  csel     w9, w22, w9, gt
00001d10  2b008052  mov      w11, #1
00001d14  167d7f93  sbfiz    x22, x8, #1, #0x20
00001d18  4b913d39  strb     w11, [x10, #0xf64]
00001d1c  3f410071  cmp      w9, #0x10
00001d20  0a028052  mov      w10, #0x10
00001d24  9c0000b0  adrp     x28, #0x12000
00001d28  39318a1a  csel     w25, w9, w10, lo
00001d2c  5b0000b0  adrp     x27, #0xa000
00001d30  e00317aa  mov      x0, x23
00001d34  e1031f2a  mov      w1, wzr
00001d38  e20316aa  mov      x2, x22
00001d3c  885b0fb9  str      w8, [x28, #0xf58]
00001d40  79430fb9  str      w25, [x27, #0xf40]
00001d44  db030094  bl       #0x2cb0 ; memset@plt
00001d48  1f2003d5  nop      
00001d4c  78914810  adr      x24, #0x92f78
00001d50  e1031f2a  mov      w1, wzr
00001d54  e00318aa  mov      x0, x24
00001d58  e20316aa  mov      x2, x22
00001d5c  d5030094  bl       #0x2cb0 ; memset@plt
00001d60  e0224091  add      x0, x23, #8, lsl #12
00001d64  e1031f2a  mov      w1, wzr
00001d68  e20316aa  mov      x2, x22
00001d6c  d1030094  bl       #0x2cb0 ; memset@plt
00001d70  00234091  add      x0, x24, #8, lsl #12
00001d74  e1031f2a  mov      w1, wzr
00001d78  e20316aa  mov      x2, x22
00001d7c  cd030094  bl       #0x2cb0 ; memset@plt
00001d80  e0424091  add      x0, x23, #0x10, lsl #12
00001d84  e1031f2a  mov      w1, wzr
00001d88  e20316aa  mov      x2, x22
00001d8c  c9030094  bl       #0x2cb0 ; memset@plt
00001d90  00434091  add      x0, x24, #0x10, lsl #12
00001d94  e1031f2a  mov      w1, wzr
00001d98  e20316aa  mov      x2, x22
00001d9c  c5030094  bl       #0x2cb0 ; memset@plt
00001da0  e0624091  add      x0, x23, #0x18, lsl #12
00001da4  e1031f2a  mov      w1, wzr
00001da8  e20316aa  mov      x2, x22
00001dac  c1030094  bl       #0x2cb0 ; memset@plt
00001db0  00634091  add      x0, x24, #0x18, lsl #12
00001db4  e1031f2a  mov      w1, wzr
00001db8  e20316aa  mov      x2, x22
00001dbc  bd030094  bl       #0x2cb0 ; memset@plt
00001dc0  e0824091  add      x0, x23, #0x20, lsl #12
00001dc4  e1031f2a  mov      w1, wzr
00001dc8  e20316aa  mov      x2, x22
00001dcc  b9030094  bl       #0x2cb0 ; memset@plt
00001dd0  00834091  add      x0, x24, #0x20, lsl #12
00001dd4  e1031f2a  mov      w1, wzr
00001dd8  e20316aa  mov      x2, x22
00001ddc  b5030094  bl       #0x2cb0 ; memset@plt
00001de0  e0a24091  add      x0, x23, #0x28, lsl #12
00001de4  e1031f2a  mov      w1, wzr
00001de8  e20316aa  mov      x2, x22
00001dec  b1030094  bl       #0x2cb0 ; memset@plt
00001df0  00a34091  add      x0, x24, #0x28, lsl #12
00001df4  e1031f2a  mov      w1, wzr
00001df8  e20316aa  mov      x2, x22
00001dfc  ad030094  bl       #0x2cb0 ; memset@plt
00001e00  e0c24091  add      x0, x23, #0x30, lsl #12
00001e04  e1031f2a  mov      w1, wzr
00001e08  e20316aa  mov      x2, x22
00001e0c  a9030094  bl       #0x2cb0 ; memset@plt
00001e10  00c34091  add      x0, x24, #0x30, lsl #12
00001e14  e1031f2a  mov      w1, wzr
00001e18  e20316aa  mov      x2, x22
00001e1c  a5030094  bl       #0x2cb0 ; memset@plt
00001e20  e0e24091  add      x0, x23, #0x38, lsl #12
00001e24  e1031f2a  mov      w1, wzr
00001e28  e20316aa  mov      x2, x22
00001e2c  a1030094  bl       #0x2cb0 ; memset@plt
00001e30  00e34091  add      x0, x24, #0x38, lsl #12
00001e34  e1031f2a  mov      w1, wzr
00001e38  e20316aa  mov      x2, x22
00001e3c  9d030094  bl       #0x2cb0 ; memset@plt
00001e40  e0024191  add      x0, x23, #0x40, lsl #12
00001e44  e1031f2a  mov      w1, wzr
00001e48  e20316aa  mov      x2, x22
00001e4c  99030094  bl       #0x2cb0 ; memset@plt
00001e50  00034191  add      x0, x24, #0x40, lsl #12
00001e54  e1031f2a  mov      w1, wzr
00001e58  e20316aa  mov      x2, x22
00001e5c  95030094  bl       #0x2cb0 ; memset@plt
00001e60  e0224191  add      x0, x23, #0x48, lsl #12
00001e64  e1031f2a  mov      w1, wzr
00001e68  e20316aa  mov      x2, x22
00001e6c  91030094  bl       #0x2cb0 ; memset@plt
00001e70  00234191  add      x0, x24, #0x48, lsl #12
00001e74  e1031f2a  mov      w1, wzr
00001e78  e20316aa  mov      x2, x22
00001e7c  8d030094  bl       #0x2cb0 ; memset@plt
00001e80  e0424191  add      x0, x23, #0x50, lsl #12
00001e84  e1031f2a  mov      w1, wzr
00001e88  e20316aa  mov      x2, x22
00001e8c  89030094  bl       #0x2cb0 ; memset@plt
00001e90  00434191  add      x0, x24, #0x50, lsl #12
00001e94  e1031f2a  mov      w1, wzr
00001e98  e20316aa  mov      x2, x22
00001e9c  85030094  bl       #0x2cb0 ; memset@plt
00001ea0  e0624191  add      x0, x23, #0x58, lsl #12
00001ea4  e1031f2a  mov      w1, wzr
00001ea8  e20316aa  mov      x2, x22
00001eac  81030094  bl       #0x2cb0 ; memset@plt
00001eb0  00634191  add      x0, x24, #0x58, lsl #12
00001eb4  e1031f2a  mov      w1, wzr
00001eb8  e20316aa  mov      x2, x22
00001ebc  7d030094  bl       #0x2cb0 ; memset@plt
00001ec0  e0824191  add      x0, x23, #0x60, lsl #12
00001ec4  e1031f2a  mov      w1, wzr
00001ec8  e20316aa  mov      x2, x22
00001ecc  79030094  bl       #0x2cb0 ; memset@plt
00001ed0  00834191  add      x0, x24, #0x60, lsl #12
00001ed4  e1031f2a  mov      w1, wzr
00001ed8  e20316aa  mov      x2, x22
00001edc  75030094  bl       #0x2cb0 ; memset@plt
00001ee0  e0a24191  add      x0, x23, #0x68, lsl #12
00001ee4  e1031f2a  mov      w1, wzr
00001ee8  e20316aa  mov      x2, x22
00001eec  71030094  bl       #0x2cb0 ; memset@plt
00001ef0  00a34191  add      x0, x24, #0x68, lsl #12
00001ef4  e1031f2a  mov      w1, wzr
00001ef8  e20316aa  mov      x2, x22
00001efc  6d030094  bl       #0x2cb0 ; memset@plt
00001f00  e0c24191  add      x0, x23, #0x70, lsl #12
00001f04  e1031f2a  mov      w1, wzr
00001f08  e20316aa  mov      x2, x22
00001f0c  69030094  bl       #0x2cb0 ; memset@plt
00001f10  00c34191  add      x0, x24, #0x70, lsl #12
00001f14  e1031f2a  mov      w1, wzr
00001f18  e20316aa  mov      x2, x22
00001f1c  65030094  bl       #0x2cb0 ; memset@plt
00001f20  e0e24191  add      x0, x23, #0x78, lsl #12
00001f24  e1031f2a  mov      w1, wzr
00001f28  e20316aa  mov      x2, x22
00001f2c  61030094  bl       #0x2cb0 ; memset@plt
00001f30  00e34191  add      x0, x24, #0x78, lsl #12
00001f34  e1031f2a  mov      w1, wzr
00001f38  e20316aa  mov      x2, x22
00001f3c  5d030094  bl       #0x2cb0 ; memset@plt
00001f40  960800b0  adrp     x22, #0x112000
00001f44  c8d247f9  ldr      x8, [x22, #0xfa0]
00001f48  1f0100f1  cmp      x8, #0
00001f4c  89169f1a  csinc    w9, w20, wzr, ne
00001f50  e90f0036  tbz      w9, #0, #0x214c
00001f54  081500b4  cbz      x8, #0x21f4
00001f58  4004000f  movi     v0.2s, #2
00001f5c  a8f300d1  sub      x8, x29, #0x3c
00001f60  a96301d1  sub      x9, x29, #0x58
00001f64  8a0800d0  adrp     x10, #0x113000
00001f68  b4f78052  mov      w20, #0x7bd
00001f6c  a8a739a9  stp      x8, x9, [x29, #-0x68]
00001f70  480d40f9  ldr      x8, [x10, #0x18]
00001f74  1400b072  movk     w20, #0x8000, lsl #16
00001f78  2a0000b0  adrp     x10, #0x6000
00001f7c  b4e73829  stp      w20, w25, [x29, #-0x3c]
00001f80  a9e301d1  sub      x9, x29, #0x78
00001f84  990800d0  adrp     x25, #0x113000
00001f88  a0831afc  stur     d0, [x29, #-0x58]
00001f8c  1f2003d5  nop      
00001f90  017e0810  adr      x1, #0x12f50
00001f94  a80319f8  stur     x8, [x29, #-0x70]
00001f98  280000b0  adrp     x8, #0x6000
00001f9c  a2a301d1  sub      x2, x29, #0x68
00001fa0  085147f9  ldr      x8, [x8, #0xea0]
00001fa4  4a4d47f9  ldr      x10, [x10, #0xe98]
00001fa8  e9ff07a9  stp      x9, xzr, [sp, #0x78]
00001fac  e90300b2  mov      x9, #0x100000001
00001fb0  200b40f9  ldr      x0, [x25, #0x10]
00001fb4  e3e30191  add      x3, sp, #0x78
00001fb8  170140f9  ldr      x23, [x8]
00001fbc  560140f9  ldr      x22, [x10]
00001fc0  a9831df8  stur     x9, [x29, #-0x28]
00001fc4  087d8052  mov      w8, #0x3e8
00001fc8  89008052  mov      w9, #4
00001fcc  a58300d1  sub      x5, x29, #0x20
00001fd0  b75b3ea9  stp      x23, x22, [x29, #-0x20]
00001fd4  b87e081b  mul      w24, w21, w8
00001fd8  e8fffff0  adrp     x8, #0
00001fdc  0a0040f9  ldr      x10, [x0]
00001fe0  00b1c23d  ldr      q0, [x8, #0xac0]
00001fe4  a6a300d1  sub      x6, x29, #0x28
00001fe8  44008052  mov      w4, #2
00001fec  a98318b8  stur     w9, [x29, #-0x78]
00001ff0  480940f9  ldr      x8, [x10, #0x10]
00001ff4  b8031bb8  stur     w24, [x29, #-0x50]
00001ff8  a0439b3c  stur     q0, [x29, #-0x4c]
00001ffc  00013fd6  blr      x8
00002000  000a0035  cbnz     w0, #0x2140
00002004  95000090  adrp     x21, #0x12000
00002008  e1031f2a  mov      w1, wzr
0000200c  a0aa47f9  ldr      x0, [x21, #0xf50]
00002010  080040f9  ldr      x8, [x0]
00002014  080140f9  ldr      x8, [x8]
00002018  00013fd6  blr      x8
0000201c  20090035  cbnz     w0, #0x2140
00002020  a0aa47f9  ldr      x0, [x21, #0xf50]
00002024  29000090  adrp     x9, #0x6000
00002028  82080090  adrp     x2, #0x112000
0000202c  42003f91  add      x2, x2, #0xfc0
00002030  080040f9  ldr      x8, [x0]
00002034  295547f9  ldr      x9, [x9, #0xea8]
00002038  080d40f9  ldr      x8, [x8, #0x18]
0000203c  210140f9  ldr      x1, [x9]
00002040  00013fd6  blr      x8
00002044  e0070035  cbnz     w0, #0x2140
00002048  a0aa47f9  ldr      x0, [x21, #0xf50]
0000204c  82080090  adrp     x2, #0x112000
00002050  42203f91  add      x2, x2, #0xfc8
00002054  e10317aa  mov      x1, x23
00002058  080040f9  ldr      x8, [x0]
0000205c  080d40f9  ldr      x8, [x8, #0x18]
00002060  00013fd6  blr      x8
00002064  e0060035  cbnz     w0, #0x2140
00002068  88080090  adrp     x8, #0x112000
0000206c  97080090  adrp     x23, #0x112000
00002070  49f747f9  ldr      x9, [x26, #0xfe8]
00002074  08fd47f9  ldr      x8, [x8, #0xff8]
00002078  e0e647f9  ldr      x0, [x23, #0xfc8]
0000207c  8a0800b0  adrp     x10, #0x113000
00002080  4a0140f9  ldr      x10, [x10]
00002084  e2031faa  mov      x2, xzr
00002088  1f0100f1  cmp      x8, #0
0000208c  080040f9  ldr      x8, [x0]
00002090  241940fa  ccmp     x9, #0, #4, ne
00002094  1f2003d5  nop      
00002098  89410010  adr      x9, #0x28c8
0000209c  441940fa  ccmp     x10, #0, #4, ne
000020a0  080d40f9  ldr      x8, [x8, #0x18]
000020a4  1f2003d5  nop      
000020a8  6a260010  adr      x10, #0x2574
000020ac  4111899a  csel     x1, x10, x9, ne
000020b0  00013fd6  blr      x8
000020b4  60040035  cbnz     w0, #0x2140
000020b8  a0aa47f9  ldr      x0, [x21, #0xf50]
000020bc  820800b0  adrp     x2, #0x113000
000020c0  42800091  add      x2, x2, #0x20
000020c4  e10316aa  mov      x1, x22
000020c8  080040f9  ldr      x8, [x0]
000020cc  080d40f9  ldr      x8, [x8, #0x18]
000020d0  00013fd6  blr      x8
000020d4  60030035  cbnz     w0, #0x2140
000020d8  68434fb9  ldr      w8, [x27, #0xf40]
000020dc  1f050071  cmp      w8, #1
000020e0  2b020054  b.lt     #0x2124
000020e4  f6031faa  mov      x22, xzr
000020e8  1f2003d5  nop      
000020ec  35740810  adr      x21, #0x12f70
000020f0  e0e647f9  ldr      x0, [x23, #0xfc8]
000020f4  895b4fb9  ldr      w9, [x28, #0xf58]
000020f8  e10315aa  mov      x1, x21
000020fc  080040f9  ldr      x8, [x0]
00002100  22791f53  lsl      w2, w9, #1
00002104  080140f9  ldr      x8, [x8]
00002108  00013fd6  blr      x8
0000210c  a0010035  cbnz     w0, #0x2140
00002110  68438fb9  ldrsw    x8, [x27, #0xf40]
00002114  d6060091  add      x22, x22, #1
00002118  b5224091  add      x21, x21, #8, lsl #12
0000211c  df0208eb  cmp      x22, x8
00002120  8bfeff54  b.lt     #0x20f0
00002124  88080090  adrp     x8, #0x112000
00002128  61008052  mov      w1, #3
0000212c  00e147f9  ldr      x0, [x8, #0xfc0]
00002130  080040f9  ldr      x8, [x0]
00002134  080140f9  ldr      x8, [x8]
00002138  00013fd6  blr      x8
0000213c  c00e0034  cbz      w0, #0x2314
00002140  e00313aa  mov      x0, x19
00002144  c1000094  bl       #0x2448
00002148  58000014  b        #0x22a8
0000214c  94080090  adrp     x20, #0x112000
00002150  80e647f9  ldr      x0, [x20, #0xfc8]
00002154  600a00b4  cbz      x0, #0x22a0
00002158  95080090  adrp     x21, #0x112000
0000215c  a8e247f9  ldr      x8, [x21, #0xfc0]
00002160  080a00b4  cbz      x8, #0x22a0
00002164  080040f9  ldr      x8, [x0]
00002168  080540f9  ldr      x8, [x8, #8]
0000216c  00013fd6  blr      x8
00002170  68434fb9  ldr      w8, [x27, #0xf40]
00002174  1f050071  cmp      w8, #1
00002178  2b020054  b.lt     #0x21bc
0000217c  f6031faa  mov      x22, xzr
00002180  1f2003d5  nop      
00002184  736f0810  adr      x19, #0x12f70
00002188  80e647f9  ldr      x0, [x20, #0xfc8]
0000218c  895b4fb9  ldr      w9, [x28, #0xf58]
00002190  e10313aa  mov      x1, x19
00002194  080040f9  ldr      x8, [x0]
00002198  22791f53  lsl      w2, w9, #1
0000219c  080140f9  ldr      x8, [x8]
000021a0  00013fd6  blr      x8
000021a4  e0070035  cbnz     w0, #0x22a0
000021a8  68438fb9  ldrsw    x8, [x27, #0xf40]
000021ac  d6060091  add      x22, x22, #1
000021b0  73224091  add      x19, x19, #8, lsl #12
000021b4  df0208eb  cmp      x22, x8
000021b8  8bfeff54  b.lt     #0x2188
000021bc  a0e247f9  ldr      x0, [x21, #0xfc0]
000021c0  61008052  mov      w1, #3
000021c4  080040f9  ldr      x8, [x0]
000021c8  080140f9  ldr      x8, [x8]
000021cc  00013fd6  blr      x8
000021d0  f30740f9  ldr      x19, [sp, #8]
000021d4  c0080034  cbz      w0, #0x22ec
000021d8  88000090  adrp     x8, #0x12000
000021dc  80080090  adrp     x0, #0x112000
000021e0  00e03d91  add      x0, x0, #0xf78
000021e4  1f690fb9  str      wzr, [x8, #0xf68]
000021e8  ae020094  bl       #0x2ca0 ; pthread_mutex_unlock@plt
000021ec  e0031f2a  mov      w0, wzr
000021f0  33000014  b        #0x22bc
000021f4  80080090  adrp     x0, #0x112000
000021f8  00803e91  add      x0, x0, #0xfa0
000021fc  e1031f2a  mov      w1, wzr
00002200  e2031faa  mov      x2, xzr
00002204  e3031f2a  mov      w3, wzr
00002208  e4031faa  mov      x4, xzr
0000220c  e5031faa  mov      x5, xzr
00002210  ac020094  bl       #0x2cc0 ; slCreateEngine@plt
00002214  60040035  cbnz     w0, #0x22a0
00002218  c0d247f9  ldr      x0, [x22, #0xfa0]
0000221c  e1031f2a  mov      w1, wzr
00002220  080040f9  ldr      x8, [x0]
00002224  080140f9  ldr      x8, [x8]
00002228  00013fd6  blr      x8
0000222c  a0030035  cbnz     w0, #0x22a0
00002230  c0d247f9  ldr      x0, [x22, #0xfa0]
00002234  29000090  adrp     x9, #0x6000
00002238  820800b0  adrp     x2, #0x113000
0000223c  42400091  add      x2, x2, #0x10
00002240  080040f9  ldr      x8, [x0]
00002244  295947f9  ldr      x9, [x9, #0xeb0]
00002248  080d40f9  ldr      x8, [x8, #0x18]
0000224c  210140f9  ldr      x1, [x9]
00002250  00013fd6  blr      x8
00002254  60020035  cbnz     w0, #0x22a0
00002258  880800b0  adrp     x8, #0x113000
0000225c  810800b0  adrp     x1, #0x113000
00002260  21600091  add      x1, x1, #0x18
00002264  000940f9  ldr      x0, [x8, #0x10]
00002268  e2031f2a  mov      w2, wzr
0000226c  e3031faa  mov      x3, xzr
00002270  e4031faa  mov      x4, xzr
00002274  080040f9  ldr      x8, [x0]
00002278  081d40f9  ldr      x8, [x8, #0x38]
0000227c  00013fd6  blr      x8
00002280  00010035  cbnz     w0, #0x22a0
00002284  880800b0  adrp     x8, #0x113000
00002288  e1031f2a  mov      w1, wzr
0000228c  000d40f9  ldr      x0, [x8, #0x18]
00002290  080040f9  ldr      x8, [x0]
00002294  080140f9  ldr      x8, [x8]
00002298  00013fd6  blr      x8
0000229c  80030034  cbz      w0, #0x230c
000022a0  88000090  adrp     x8, #0x12000
000022a4  1f690fb9  str      wzr, [x8, #0xf68]
000022a8  80080090  adrp     x0, #0x112000
000022ac  00e03d91  add      x0, x0, #0xf78
000022b0  7c020094  bl       #0x2ca0 ; pthread_mutex_unlock@plt
000022b4  e0031f2a  mov      w0, wzr
000022b8  f30740f9  ldr      x19, [sp, #8]
000022bc  681640f9  ldr      x8, [x19, #0x28]
000022c0  a9035ff8  ldur     x9, [x29, #-0x10]
000022c4  1f0109eb  cmp      x8, x9
000022c8  e10b0054  b.ne     #0x2444
000022cc  f44f55a9  ldp      x20, x19, [sp, #0x150]
000022d0  f65754a9  ldp      x22, x21, [sp, #0x140]
000022d4  f85f53a9  ldp      x24, x23, [sp, #0x130]
000022d8  fa6752a9  ldp      x26, x25, [sp, #0x120]
000022dc  fc6f51a9  ldp      x28, x27, [sp, #0x110]
000022e0  fd7b50a9  ldp      x29, x30, [sp, #0x100]
000022e4  ff830591  add      sp, sp, #0x160
000022e8  c0035fd6  ret      
000022ec  48008052  mov      w8, #2
000022f0  89000090  adrp     x9, #0x12000
000022f4  80080090  adrp     x0, #0x112000
000022f8  00e03d91  add      x0, x0, #0xf78
000022fc  28690fb9  str      w8, [x9, #0xf68]
00002300  68020094  bl       #0x2ca0 ; pthread_mutex_unlock@plt
00002304  20008052  mov      w0, #1
00002308  edffff17  b        #0x22bc
0000230c  79434fb9  ldr      w25, [x27, #0xf40]
00002310  12ffff17  b        #0x1f58
00002314  e80740b9  ldr      w8, [sp, #4]
00002318  1f1d0072  tst      w8, #0xff
0000231c  00080054  b.eq     #0x241c
00002320  68434fb9  ldr      w8, [x27, #0xf40]
00002324  1f2003d5  nop      
00002328  0a3eff10  adr      x10, #0xae8
0000232c  4001c03d  ldr      q0, [x10]
00002330  e9c30191  add      x9, sp, #0x70
00002334  810800b0  adrp     x1, #0x113000
00002338  21a00091  add      x1, x1, #0x28
0000233c  f4230e29  stp      w20, w8, [sp, #0x70]
00002340  e8430191  add      x8, sp, #0x50
00002344  e92304a9  stp      x9, x8, [sp, #0x40]
00002348  29000090  adrp     x9, #0x6000
0000234c  480940f9  ldr      x8, [x10, #0x10]
00002350  e00b803d  str      q0, [sp, #0x20]
00002354  eaffffd0  adrp     x10, #0
00002358  e2430091  add      x2, sp, #0x10
0000235c  295d47f9  ldr      x9, [x9, #0xeb8]
00002360  e81b00f9  str      x8, [sp, #0x30]
00002364  e8830091  add      x8, sp, #0x20
00002368  406945fd  ldr      d0, [x10, #0xad0]
0000236c  200b40f9  ldr      x0, [x25, #0x10]
00002370  e87f01a9  stp      x8, xzr, [sp, #0x10]
00002374  340140f9  ldr      x20, [x9]
00002378  e8ffffd0  adrp     x8, #0
0000237c  29008052  mov      w9, #1
00002380  e02b00fd  str      d0, [sp, #0x50]
00002384  00adc23d  ldr      q0, [x8, #0xab0]
00002388  e3030191  add      x3, sp, #0x40
0000238c  b4031df8  stur     x20, [x29, #-0x30]
00002390  a5c300d1  sub      x5, x29, #0x30
00002394  a6d300d1  sub      x6, x29, #0x34
00002398  080040f9  ldr      x8, [x0]
0000239c  24008052  mov      w4, #1
000023a0  f85b00b9  str      w24, [sp, #0x58]
000023a4  e0c3853c  stur     q0, [sp, #0x5c]
000023a8  080d40f9  ldr      x8, [x8, #0x18]
000023ac  a9c31cb8  stur     w9, [x29, #-0x34]
000023b0  00013fd6  blr      x8
000023b4  60ecff35  cbnz     w0, #0x2140
000023b8  950800b0  adrp     x21, #0x113000
000023bc  e1031f2a  mov      w1, wzr
000023c0  a01640f9  ldr      x0, [x21, #0x28]
000023c4  080040f9  ldr      x8, [x0]
000023c8  080140f9  ldr      x8, [x8]
000023cc  00013fd6  blr      x8
000023d0  80ebff35  cbnz     w0, #0x2140
000023d4  a01640f9  ldr      x0, [x21, #0x28]
000023d8  29000090  adrp     x9, #0x6000
000023dc  82080090  adrp     x2, #0x112000
000023e0  42403f91  add      x2, x2, #0xfd0
000023e4  080040f9  ldr      x8, [x0]
000023e8  296147f9  ldr      x9, [x9, #0xec0]
000023ec  080d40f9  ldr      x8, [x8, #0x18]
000023f0  210140f9  ldr      x1, [x9]
000023f4  00013fd6  blr      x8
000023f8  40eaff35  cbnz     w0, #0x2140
000023fc  a01640f9  ldr      x0, [x21, #0x28]
00002400  1f2003d5  nop      
00002404  625b4810  adr      x2, #0x92f70
00002408  e10314aa  mov      x1, x20
0000240c  080040f9  ldr      x8, [x0]
00002410  080d40f9  ldr      x8, [x8, #0x18]
00002414  00013fd6  blr      x8
00002418  40e9ff35  cbnz     w0, #0x2140
0000241c  48008052  mov      w8, #2
00002420  89080090  adrp     x9, #0x112000
00002424  8a000090  adrp     x10, #0x12000
00002428  80080090  adrp     x0, #0x112000
0000242c  00e03d91  add      x0, x0, #0xf78
00002430  48690fb9  str      w8, [x10, #0xf68]
00002434  3fd90fb9  str      wzr, [x9, #0xfd8]
00002438  1a020094  bl       #0x2ca0 ; pthread_mutex_unlock@plt
0000243c  20008052  mov      w0, #1
00002440  9effff17  b        #0x22b8
00002444  07020094  bl       #0x2c60 ; __stack_chk_fail@plt
