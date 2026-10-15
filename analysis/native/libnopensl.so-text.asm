00000ec8  5f2403d5  bti      c
00000ecc  1f2003d5  nop      
00000ed0  00f00210  adr      x0, #0x6cd0
00000ed4  4f070014  b        #0x2c10 ; __cxa_finalize@plt
00000ed8  5f2403d5  bti      c
00000edc  c0035fd6  ret      
00000ee0  5f2403d5  bti      c
00000ee4  fdffff17  b        #0xed8
00000ee8  5f2403d5  bti      c
00000eec  600000b4  cbz      x0, #0xef8
00000ef0  f00300aa  mov      x16, x0
00000ef4  00021fd6  br       x16
00000ef8  c0035fd6  ret      
00000efc  5f2403d5  bti      c
00000f00  e10300aa  mov      x1, x0
00000f04  1f2003d5  nop      
00000f08  00ffff10  adr      x0, #0xee8
00000f0c  1f2003d5  nop      
00000f10  02ee0210  adr      x2, #0x6cd0
00000f14  43070014  b        #0x2c20 ; __cxa_atexit@plt
00000f18  5f2403d5  bti      c
00000f1c  1f2003d5  nop      
00000f20  83ed0210  adr      x3, #0x6cd0
00000f24  43070014  b        #0x2c30 ; __register_atfork@plt
00000f28  c20400b4  cbz      x2, #0xfc0
00000f2c  fd7bbda9  stp      x29, x30, [sp, #-0x30]!
00000f30  f50b00f9  str      x21, [sp, #0x10]
00000f34  f44f02a9  stp      x20, x19, [sp, #0x20]
00000f38  fd030091  mov      x29, sp
00000f3c  080040f9  ldr      x8, [x0]
00000f40  e10302aa  mov      x1, x2
00000f44  f503032a  mov      w21, w3
00000f48  f30302aa  mov      x19, x2
00000f4c  f40300aa  mov      x20, x0
00000f50  08ad42f9  ldr      x8, [x8, #0x558]
00000f54  00013fd6  blr      x8
00000f58  1f00156b  cmp      w0, w21
00000f5c  08b0951a  csel     w8, w0, w21, lt
00000f60  1f050071  cmp      w8, #1
00000f64  8b020054  b.lt     #0xfb4
00000f68  890240f9  ldr      x9, [x20]
00000f6c  1f114071  cmp      w8, #4, lsl #12
00000f70  0a008852  mov      w10, #0x4000
00000f74  15318a1a  csel     w21, w8, w10, lo
00000f78  1f2003d5  nop      
00000f7c  64fe0410  adr      x4, #0xaf48
00000f80  292943f9  ldr      x9, [x9, #0x650]
00000f84  e00314aa  mov      x0, x20
00000f88  e10313aa  mov      x1, x19
00000f8c  e2031f2a  mov      w2, wzr
00000f90  e303152a  mov      w3, w21
00000f94  20013fd6  blr      x9
00000f98  880000d0  adrp     x8, #0x12000
00000f9c  8a0000d0  adrp     x10, #0x12000
00000fa0  094d4fb9  ldr      w9, [x8, #0xf4c]
00000fa4  55490fb9  str      w21, [x10, #0xf48]
00000fa8  3f01156b  cmp      w9, w21
00000fac  4b000054  b.lt     #0xfb4
00000fb0  1f4d0fb9  str      wzr, [x8, #0xf4c]
00000fb4  f44f42a9  ldp      x20, x19, [sp, #0x20]
00000fb8  f50b40f9  ldr      x21, [sp, #0x10]
00000fbc  fd7bc3a8  ldp      x29, x30, [sp], #0x30
00000fc0  c0035fd6  ret      
00000fc4  fd7bbaa9  stp      x29, x30, [sp, #-0x60]!
00000fc8  fc6f01a9  stp      x28, x27, [sp, #0x10]
00000fcc  fa6702a9  stp      x26, x25, [sp, #0x20]
00000fd0  f85f03a9  stp      x24, x23, [sp, #0x30]
00000fd4  f65704a9  stp      x22, x21, [sp, #0x40]
00000fd8  f44f05a9  stp      x20, x19, [sp, #0x50]
00000fdc  fd030091  mov      x29, sp
00000fe0  ff2340d1  sub      sp, sp, #8, lsl #12
00000fe4  ff8300d1  sub      sp, sp, #0x20
00000fe8  58d03bd5  mrs      x24, tpidr_el0
00000fec  081740f9  ldr      x8, [x24, #0x28]
00000ff0  a8031ff8  stur     x8, [x29, #-0x10]
00000ff4  a21000b4  cbz      x2, #0x1208
00000ff8  970000d0  adrp     x23, #0x12000
00000ffc  e8aa47f9  ldr      x8, [x23, #0xf50]
00001000  481000b4  cbz      x8, #0x1208
00001004  880000b0  adrp     x8, #0x12000
00001008  08594fb9  ldr      w8, [x8, #0xf58]
0000100c  1f050071  cmp      w8, #1
00001010  cb0f0054  b.lt     #0x1208
00001014  080040f9  ldr      x8, [x0]
00001018  e10302aa  mov      x1, x2
0000101c  f303032a  mov      w19, w3
00001020  f40302aa  mov      x20, x2
00001024  f50300aa  mov      x21, x0
00001028  08ad42f9  ldr      x8, [x8, #0x558]
0000102c  00013fd6  blr      x8
00001030  1f00136b  cmp      w0, w19
00001034  08008852  mov      w8, #0x4000
00001038  09b0931a  csel     w9, w0, w19, lt
0000103c  3f114071  cmp      w9, #4, lsl #12
00001040  33b1881a  csel     w19, w9, w8, lt
00001044  3f050071  cmp      w9, #1
00001048  0b0e0054  b.lt     #0x1208
0000104c  a80240f9  ldr      x8, [x21]
00001050  e4430091  add      x4, sp, #0x10
00001054  e00315aa  mov      x0, x21
00001058  e10314aa  mov      x1, x20
0000105c  e2031f2a  mov      w2, wzr
00001060  e303132a  mov      w3, w19
00001064  082943f9  ldr      x8, [x8, #0x650]
00001068  00013fd6  blr      x8
0000106c  950000b0  adrp     x21, #0x12000
00001070  9a0000b0  adrp     x26, #0x12000
00001074  990000b0  adrp     x25, #0x12000
00001078  94008052  mov      w20, #4
0000107c  16028052  mov      w22, #0x10
00001080  f80700f9  str      x24, [sp, #8]
00001084  a85e4fb9  ldr      w8, [x21, #0xf5c]
00001088  49634fb9  ldr      w9, [x26, #0xf60]
0000108c  1f01096b  cmp      w8, w9
00001090  81000054  b.ne     #0x10a0
00001094  fcaa47f9  ldr      x28, [x23, #0xf50]
00001098  7c0100b5  cbnz     x28, #0x10c4
0000109c  53000014  b        #0x11e8
000010a0  890000b0  adrp     x9, #0x12000
000010a4  a85e4fb9  ldr      w8, [x21, #0xf5c]
000010a8  fcaa47f9  ldr      x28, [x23, #0xf50]
000010ac  29917d39  ldrb     w9, [x9, #0xf64]
000010b0  3f010071  cmp      w9, #0
000010b4  c912941a  csel     w9, w22, w20, ne
000010b8  1f01096b  cmp      w8, w9
000010bc  8b010054  b.lt     #0x10ec
000010c0  7c0100b4  cbz      x28, #0x10ec
000010c4  286b4fb9  ldr      w8, [x25, #0xf68]
000010c8  1f090071  cmp      w8, #2
000010cc  81000054  b.ne     #0x10dc
000010d0  40018052  mov      w0, #0xa
000010d4  db060094  bl       #0x2c40 ; usleep@plt
000010d8  ebffff17  b        #0x1084
000010dc  880000b0  adrp     x8, #0x12000
000010e0  5b634fb9  ldr      w27, [x26, #0xf60]
000010e4  1a6d4fb9  ldr      w26, [x8, #0xf6c]
000010e8  05000014  b        #0x10fc
000010ec  880000b0  adrp     x8, #0x12000
000010f0  5b634fb9  ldr      w27, [x26, #0xf60]
000010f4  1a6d4fb9  ldr      w26, [x8, #0xf6c]
000010f8  fc0700b4  cbz      x28, #0x11f4
000010fc  f6031f2a  mov      w22, wzr
00001100  14028052  mov      w20, #0x10
00001104  286b4fb9  ldr      w8, [x25, #0xf68]
00001108  1f090071  cmp      w8, #2
0000110c  41070054  b.ne     #0x11f4
00001110  880000b0  adrp     x8, #0x12000
00001114  08594fb9  ldr      w8, [x8, #0xf58]
00001118  5f03086b  cmp      w26, w8
0000111c  0b040054  b.lt     #0x119c
00001120  980000b0  adrp     x24, #0x12000
00001124  9a008052  mov      w26, #4
00001128  08937d39  ldrb     w8, [x24, #0xf64]
0000112c  1f010071  cmp      w8, #0
00001130  68070011  add      w8, w27, #1
00001134  89129a1a  csel     w9, w20, w26, ne
00001138  1f01096b  cmp      w8, w9
0000113c  fba79b1a  csinc    w27, wzr, w27, ge
00001140  a85e4fb9  ldr      w8, [x21, #0xf5c]
00001144  1f011b6b  cmp      w8, w27
00001148  81000054  b.ne     #0x1158
0000114c  fcaa47f9  ldr      x28, [x23, #0xf50]
00001150  5c0100b5  cbnz     x28, #0x1178
00001154  0f000014  b        #0x1190
00001158  a85e4fb9  ldr      w8, [x21, #0xf5c]
0000115c  09937d39  ldrb     w9, [x24, #0xf64]
00001160  fcaa47f9  ldr      x28, [x23, #0xf50]
00001164  3f010071  cmp      w9, #0
00001168  89129a1a  csel     w9, w20, w26, ne
0000116c  1f01096b  cmp      w8, w9
00001170  84ab40fa  ccmp     x28, #0, #4, ge
00001174  e0000054  b.eq     #0x1190
00001178  286b4fb9  ldr      w8, [x25, #0xf68]
0000117c  1f090071  cmp      w8, #2
00001180  81000054  b.ne     #0x1190
00001184  40018052  mov      w0, #0xa
00001188  ae060094  bl       #0x2c40 ; usleep@plt
0000118c  edffff17  b        #0x1140
00001190  880000b0  adrp     x8, #0x12000
00001194  fa031f2a  mov      w26, wzr
00001198  08594fb9  ldr      w8, [x8, #0xf58]
0000119c  697f4093  sxtw     x9, w27
000011a0  08011a4b  sub      w8, w8, w26
000011a4  1f2003d5  nop      
000011a8  4aee0810  adr      x10, #0x12f70
000011ac  7f02086b  cmp      w19, w8
000011b0  493d098b  add      x9, x10, x9, lsl #15
000011b4  78b2881a  csel     w24, w19, w8, lt
000011b8  e8430091  add      x8, sp, #0x10
000011bc  01c5368b  add      x1, x8, w22, sxtw #1
000011c0  027f7f93  sbfiz    x2, x24, #1, #0x20
000011c4  20c53a8b  add      x0, x9, w26, sxtw #1
000011c8  a2060094  bl       #0x2c50 ; memcpy@plt
000011cc  7302184b  sub      w19, w19, w24
000011d0  1a031a0b  add      w26, w24, w26
000011d4  7f060071  cmp      w19, #1
000011d8  eb000054  b.lt     #0x11f4
000011dc  1603160b  add      w22, w24, w22
000011e0  3cf9ffb5  cbnz     x28, #0x1104
000011e4  04000014  b        #0x11f4
000011e8  880000b0  adrp     x8, #0x12000
000011ec  5b634fb9  ldr      w27, [x26, #0xf60]
000011f0  1a6d4fb9  ldr      w26, [x8, #0xf6c]
000011f4  880000b0  adrp     x8, #0x12000
000011f8  1b610fb9  str      w27, [x8, #0xf60]
000011fc  880000b0  adrp     x8, #0x12000
00001200  1a6d0fb9  str      w26, [x8, #0xf6c]
00001204  f80740f9  ldr      x24, [sp, #8]
00001208  081740f9  ldr      x8, [x24, #0x28]
0000120c  a9035ff8  ldur     x9, [x29, #-0x10]
00001210  1f0109eb  cmp      x8, x9
00001214  41010054  b.ne     #0x123c
00001218  ff234091  add      sp, sp, #8, lsl #12
0000121c  ff830091  add      sp, sp, #0x20
00001220  f44f45a9  ldp      x20, x19, [sp, #0x50]
00001224  f65744a9  ldp      x22, x21, [sp, #0x40]
00001228  f85f43a9  ldp      x24, x23, [sp, #0x30]
0000122c  fa6742a9  ldp      x26, x25, [sp, #0x20]
00001230  fc6f41a9  ldp      x28, x27, [sp, #0x10]
00001234  fd7bc6a8  ldp      x29, x30, [sp], #0x60
00001238  c0035fd6  ret      
0000123c  89060094  bl       #0x2c60 ; __stack_chk_fail@plt
00001240  fd7bbaa9  stp      x29, x30, [sp, #-0x60]!
00001244  fc6f01a9  stp      x28, x27, [sp, #0x10]
00001248  fa6702a9  stp      x26, x25, [sp, #0x20]
0000124c  f85f03a9  stp      x24, x23, [sp, #0x30]
00001250  f65704a9  stp      x22, x21, [sp, #0x40]
00001254  f44f05a9  stp      x20, x19, [sp, #0x50]
00001258  fd030091  mov      x29, sp
0000125c  ff4340d1  sub      sp, sp, #0x10, lsl #12
00001260  ff4300d1  sub      sp, sp, #0x10
00001264  5ad03bd5  mrs      x26, tpidr_el0
00001268  481740f9  ldr      x8, [x26, #0x28]
0000126c  a8831ff8  stur     x8, [x29, #-8]
00001270  821500b4  cbz      x2, #0x1520
00001274  f50303aa  mov      x21, x3
00001278  431500b4  cbz      x3, #0x1520
0000127c  080040f9  ldr      x8, [x0]
00001280  e10302aa  mov      x1, x2
00001284  f803062a  mov      w24, w6
00001288  f903052a  mov      w25, w5
0000128c  f603042a  mov      w22, w4
00001290  f30302aa  mov      x19, x2
00001294  08ad42f9  ldr      x8, [x8, #0x558]
00001298  f40300aa  mov      x20, x0
0000129c  00013fd6  blr      x8
000012a0  880240f9  ldr      x8, [x20]
000012a4  f703002a  mov      w23, w0
000012a8  e00314aa  mov      x0, x20
000012ac  e10315aa  mov      x1, x21
000012b0  08ad42f9  ldr      x8, [x8, #0x558]
000012b4  00013fd6  blr      x8
000012b8  ff02166b  cmp      w23, w22
000012bc  0a781f53  lsl      w10, w0, #1
000012c0  e8b2961a  csel     w8, w23, w22, lt
000012c4  1f010071  cmp      w8, #0
000012c8  09a5881a  cinc     w9, w8, lt
000012cc  1f04896b  cmp      w0, w9, asr #1
000012d0  5bb1881a  csel     w27, w10, w8, lt
000012d4  08008852  mov      w8, #0x4000
000012d8  7f134071  cmp      w27, #4, lsl #12
000012dc  76b3881a  csel     w22, w27, w8, lt
000012e0  7f070071  cmp      w27, #1
000012e4  eb110054  b.lt     #0x1520
000012e8  880240f9  ldr      x8, [x20]
000012ec  e4234091  add      x4, sp, #8, lsl #12
000012f0  e00314aa  mov      x0, x20
000012f4  84200091  add      x4, x4, #8
000012f8  e10313aa  mov      x1, x19
000012fc  e2031f2a  mov      w2, wzr
00001300  082943f9  ldr      x8, [x8, #0x650]
00001304  e303162a  mov      w3, w22
00001308  00013fd6  blr      x8
0000130c  880240f9  ldr      x8, [x20]
00001310  d77e0153  lsr      w23, w22, #1
00001314  e4230091  add      x4, sp, #8
00001318  e00314aa  mov      x0, x20
0000131c  e10315aa  mov      x1, x21
00001320  e2031f2a  mov      w2, wzr
00001324  082943f9  ldr      x8, [x8, #0x650]
00001328  e303172a  mov      w3, w23
0000132c  00013fd6  blr      x8
00001330  7f070071  cmp      w27, #1
00001334  a0080054  b.eq     #0x1448
00001338  ff060071  cmp      w23, #1
0000133c  e8869f1a  csinc    w8, w23, wzr, hi
00001340  7f230071  cmp      w27, #8
00001344  6a000054  b.ge     #0x1350
00001348  e9031faa  mov      x9, xzr
0000134c  23000014  b        #0x13d8
00001350  e9a39052  mov      w9, #0x851f
00001354  200f044e  dup      v0.4s, w25
00001358  010f044e  dup      v1.4s, w24
0000135c  693daa72  movk     w9, #0x51eb, lsl #16
00001360  ea234091  add      x10, sp, #8, lsl #12
00001364  eb230091  add      x11, sp, #8
00001368  220d044e  dup      v2.4s, w9
0000136c  092d7e92  and      x9, x8, #0x3ffc
00001370  4a210091  add      x10, x10, #8
00001374  ec0309aa  mov      x12, x9
00001378  638540fc  ldr      d3, [x11], #8
0000137c  8c1100f1  subs     x12, x12, #4
00001380  63a4100f  sshll    v3.4s, v3.4h, #0
00001384  049ca34e  mul      v4.4s, v0.4s, v3.4s
00001388  239ca34e  mul      v3.4s, v1.4s, v3.4s
0000138c  85c0a24e  smull2   v5.2d, v4.4s, v2.4s
00001390  84c0a20e  smull    v4.2d, v4.2s, v2.2s
00001394  66c0a24e  smull2   v6.2d, v3.4s, v2.4s
00001398  63c0a20e  smull    v3.2d, v3.2s, v2.2s
0000139c  8458854e  uzp2     v4.4s, v4.4s, v5.4s
000013a0  6358864e  uzp2     v3.4s, v3.4s, v6.4s
000013a4  85043b4f  sshr     v5.4s, v4.4s, #5
000013a8  66043b4f  sshr     v6.4s, v3.4s, #5
000013ac  8514216f  usra     v5.4s, v4.4s, #0x1f
000013b0  6614216f  usra     v6.4s, v3.4s, #0x1f
000013b4  4385400c  ld2      {v3.4h, v4.4h}, [x10]
000013b8  a528610e  xtn      v5.4h, v5.4s
000013bc  c628610e  xtn      v6.4h, v6.4s
000013c0  7084650e  add      v16.4h, v3.4h, v5.4h
000013c4  9184660e  add      v17.4h, v4.4h, v6.4h
000013c8  50859f0c  st2      {v16.4h, v17.4h}, [x10], #16
000013cc  61fdff54  b.ne     #0x1378
000013d0  3f0108eb  cmp      x9, x8
000013d4  a0030054  b.eq     #0x1448
000013d8  ea234091  add      x10, sp, #8, lsl #12
000013dc  080109cb  sub      x8, x8, x9
000013e0  4a210091  add      x10, x10, #8
000013e4  4b09098b  add      x11, x10, x9, lsl #2
000013e8  ea230091  add      x10, sp, #8
000013ec  4a05098b  add      x10, x10, x9, lsl #1
000013f0  e9a39052  mov      w9, #0x851f
000013f4  6b090091  add      x11, x11, #2
000013f8  693daa72  movk     w9, #0x51eb, lsl #16
000013fc  4c25c078  ldrsh    w12, [x10], #2
00001400  6fe15f78  ldurh    w15, [x11, #-2]
00001404  080500f1  subs     x8, x8, #1
00001408  8d7d191b  mul      w13, w12, w25
0000140c  8c7d181b  mul      w12, w12, w24
00001410  ad7d299b  smull    x13, w13, w9
00001414  8c7d299b  smull    x12, w12, w9
00001418  aefd7fd3  lsr      x14, x13, #0x3f
0000141c  adfd65d3  lsr      x13, x13, #0x25
00001420  90fd7fd3  lsr      x16, x12, #0x3f
00001424  8cfd65d3  lsr      x12, x12, #0x25
00001428  ad010e0b  add      w13, w13, w14
0000142c  6e014079  ldrh     w14, [x11]
00001430  8c01100b  add      w12, w12, w16
00001434  ed010d0b  add      w13, w15, w13
00001438  cc010c0b  add      w12, w14, w12
0000143c  6de11f78  sturh    w13, [x11, #-2]
00001440  6c450078  strh     w12, [x11], #4
00001444  c1fdff54  b.ne     #0x13fc
00001448  c27a1f53  lsl      w2, w22, #1
0000144c  e1234091  add      x1, sp, #8, lsl #12
00001450  1f2003d5  nop      
00001454  a0d70410  adr      x0, #0xaf48
00001458  21200091  add      x1, x1, #8
0000145c  03009052  mov      w3, #0x8000
00001460  04060094  bl       #0x2c70 ; __memcpy_chk@plt
00001464  880000b0  adrp     x8, #0x12000
00001468  8a0000b0  adrp     x10, #0x12000
0000146c  094d4fb9  ldr      w9, [x8, #0xf4c]
00001470  56490fb9  str      w22, [x10, #0xf48]
00001474  3f01166b  cmp      w9, w22
00001478  4b000054  b.lt     #0x1480
0000147c  1f4d0fb9  str      wzr, [x8, #0xf4c]
00001480  880400b0  adrp     x8, #0x92000
00001484  08b947f9  ldr      x8, [x8, #0xf70]
00001488  a80300b4  cbz      x8, #0x14fc
0000148c  880000b0  adrp     x8, #0x12000
00001490  095d4fb9  ldr      w9, [x8, #0xf5c]
00001494  4903f837  tbnz     w9, #0x1f, #0x14fc
00001498  890000b0  adrp     x9, #0x12000
0000149c  0a5d4fb9  ldr      w10, [x8, #0xf5c]
000014a0  0b028052  mov      w11, #0x10
000014a4  29917d39  ldrb     w9, [x9, #0xf64]
000014a8  3f010071  cmp      w9, #0
000014ac  89008052  mov      w9, #4
000014b0  6911891a  csel     w9, w11, w9, ne
000014b4  5f01096b  cmp      w10, w9
000014b8  2a020054  b.ge     #0x14fc
000014bc  085d8fb9  ldrsw    x8, [x8, #0xf5c]
000014c0  1f2003d5  nop      
000014c4  a9d54810  adr      x9, #0x92f78
000014c8  c2361f12  and      w2, w22, #0x7ffe
000014cc  e0230091  add      x0, sp, #8
000014d0  03009052  mov      w3, #0x8000
000014d4  213d088b  add      x1, x9, x8, lsl #15
000014d8  e6050094  bl       #0x2c70 ; __memcpy_chk@plt
000014dc  880240f9  ldr      x8, [x20]
000014e0  e4230091  add      x4, sp, #8
000014e4  e00314aa  mov      x0, x20
000014e8  e10315aa  mov      x1, x21
000014ec  e2031f2a  mov      w2, wzr
000014f0  e303172a  mov      w3, w23
000014f4  084943f9  ldr      x8, [x8, #0x690]
000014f8  00013fd6  blr      x8
000014fc  880240f9  ldr      x8, [x20]
00001500  e4234091  add      x4, sp, #8, lsl #12
00001504  e00314aa  mov      x0, x20
00001508  84200091  add      x4, x4, #8
0000150c  e10313aa  mov      x1, x19
00001510  e2031f2a  mov      w2, wzr
00001514  084943f9  ldr      x8, [x8, #0x690]
00001518  e303162a  mov      w3, w22
0000151c  00013fd6  blr      x8
00001520  481740f9  ldr      x8, [x26, #0x28]
00001524  a9835ff8  ldur     x9, [x29, #-8]
00001528  1f0109eb  cmp      x8, x9
0000152c  41010054  b.ne     #0x1554
00001530  ff434091  add      sp, sp, #0x10, lsl #12
00001534  ff430091  add      sp, sp, #0x10
00001538  f44f45a9  ldp      x20, x19, [sp, #0x50]
0000153c  f65744a9  ldp      x22, x21, [sp, #0x40]
00001540  f85f43a9  ldp      x24, x23, [sp, #0x30]
00001544  fa6742a9  ldp      x26, x25, [sp, #0x20]
00001548  fc6f41a9  ldp      x28, x27, [sp, #0x10]
0000154c  fd7bc6a8  ldp      x29, x30, [sp], #0x60
00001550  c0035fd6  ret      
00001554  c3050094  bl       #0x2c60 ; __stack_chk_fail@plt
00001558  fd7bbaa9  stp      x29, x30, [sp, #-0x60]!
0000155c  fc6f01a9  stp      x28, x27, [sp, #0x10]
00001560  fa6702a9  stp      x26, x25, [sp, #0x20]
00001564  f85f03a9  stp      x24, x23, [sp, #0x30]
00001568  f65704a9  stp      x22, x21, [sp, #0x40]
0000156c  f44f05a9  stp      x20, x19, [sp, #0x50]
00001570  fd030091  mov      x29, sp
00001574  ff4340d1  sub      sp, sp, #0x10, lsl #12
00001578  ff0301d1  sub      sp, sp, #0x40
0000157c  55d03bd5  mrs      x21, tpidr_el0
00001580  a81640f9  ldr      x8, [x21, #0x28]
00001584  a8031ff8  stur     x8, [x29, #-0x10]
00001588  022200b4  cbz      x2, #0x19c8
0000158c  fa0303aa  mov      x26, x3
00001590  c32100b4  cbz      x3, #0x19c8
00001594  880000b0  adrp     x8, #0x12000
00001598  08a947f9  ldr      x8, [x8, #0xf50]
0000159c  682100b4  cbz      x8, #0x19c8
000015a0  880000b0  adrp     x8, #0x12000
000015a4  08594fb9  ldr      w8, [x8, #0xf58]
000015a8  1f050071  cmp      w8, #1
000015ac  eb200054  b.lt     #0x19c8
000015b0  080040f9  ldr      x8, [x0]
000015b4  e10302aa  mov      x1, x2
000015b8  f803062a  mov      w24, w6
000015bc  f903052a  mov      w25, w5
000015c0  f603042a  mov      w22, w4
000015c4  fb0302aa  mov      x27, x2
000015c8  08ad42f9  ldr      x8, [x8, #0x558]
000015cc  f40300aa  mov      x20, x0
000015d0  00013fd6  blr      x8
000015d4  880240f9  ldr      x8, [x20]
000015d8  f703002a  mov      w23, w0
000015dc  e00314aa  mov      x0, x20
000015e0  e1031aaa  mov      x1, x26
000015e4  08ad42f9  ldr      x8, [x8, #0x558]
000015e8  00013fd6  blr      x8
000015ec  ff02166b  cmp      w23, w22
000015f0  0a781f53  lsl      w10, w0, #1
000015f4  e8b2961a  csel     w8, w23, w22, lt
000015f8  1f010071  cmp      w8, #0
000015fc  09a5881a  cinc     w9, w8, lt
00001600  1f04896b  cmp      w0, w9, asr #1
00001604  53b1881a  csel     w19, w10, w8, lt
00001608  08008852  mov      w8, #0x4000
0000160c  7f124071  cmp      w19, #4, lsl #12
00001610  76b2881a  csel     w22, w19, w8, lt
00001614  7f060071  cmp      w19, #1
00001618  8b1d0054  b.lt     #0x19c8
0000161c  880240f9  ldr      x8, [x20]
00001620  e4234091  add      x4, sp, #8, lsl #12
00001624  e00314aa  mov      x0, x20
00001628  84c00091  add      x4, x4, #0x30
0000162c  e1031baa  mov      x1, x27
00001630  e2031f2a  mov      w2, wzr
00001634  082943f9  ldr      x8, [x8, #0x650]
00001638  e303162a  mov      w3, w22
0000163c  00013fd6  blr      x8
00001640  880240f9  ldr      x8, [x20]
00001644  d77e0153  lsr      w23, w22, #1
00001648  e4c30091  add      x4, sp, #0x30
0000164c  e00314aa  mov      x0, x20
00001650  e1031aaa  mov      x1, x26
00001654  e2031f2a  mov      w2, wzr
00001658  082943f9  ldr      x8, [x8, #0x650]
0000165c  e303172a  mov      w3, w23
00001660  00013fd6  blr      x8
00001664  7f060071  cmp      w19, #1
00001668  a0080054  b.eq     #0x177c
0000166c  ff060071  cmp      w23, #1
00001670  e8869f1a  csinc    w8, w23, wzr, hi
00001674  7f220071  cmp      w19, #8
00001678  6a000054  b.ge     #0x1684
0000167c  e9031faa  mov      x9, xzr
00001680  23000014  b        #0x170c
00001684  e9a39052  mov      w9, #0x851f
00001688  200f044e  dup      v0.4s, w25
0000168c  010f044e  dup      v1.4s, w24
00001690  693daa72  movk     w9, #0x51eb, lsl #16
00001694  ea234091  add      x10, sp, #8, lsl #12
00001698  ebc30091  add      x11, sp, #0x30
0000169c  220d044e  dup      v2.4s, w9
000016a0  092d7e92  and      x9, x8, #0x3ffc
000016a4  4ac10091  add      x10, x10, #0x30
000016a8  ec0309aa  mov      x12, x9
000016ac  638540fc  ldr      d3, [x11], #8
000016b0  8c1100f1  subs     x12, x12, #4
000016b4  63a4100f  sshll    v3.4s, v3.4h, #0
000016b8  049ca34e  mul      v4.4s, v0.4s, v3.4s
000016bc  239ca34e  mul      v3.4s, v1.4s, v3.4s
000016c0  85c0a24e  smull2   v5.2d, v4.4s, v2.4s
000016c4  84c0a20e  smull    v4.2d, v4.2s, v2.2s
000016c8  66c0a24e  smull2   v6.2d, v3.4s, v2.4s
000016cc  63c0a20e  smull    v3.2d, v3.2s, v2.2s
000016d0  8458854e  uzp2     v4.4s, v4.4s, v5.4s
000016d4  6358864e  uzp2     v3.4s, v3.4s, v6.4s
000016d8  85043b4f  sshr     v5.4s, v4.4s, #5
000016dc  66043b4f  sshr     v6.4s, v3.4s, #5
000016e0  8514216f  usra     v5.4s, v4.4s, #0x1f
000016e4  6614216f  usra     v6.4s, v3.4s, #0x1f
000016e8  4385400c  ld2      {v3.4h, v4.4h}, [x10]
000016ec  a528610e  xtn      v5.4h, v5.4s
000016f0  c628610e  xtn      v6.4h, v6.4s
000016f4  7084650e  add      v16.4h, v3.4h, v5.4h
000016f8  9184660e  add      v17.4h, v4.4h, v6.4h
000016fc  50859f0c  st2      {v16.4h, v17.4h}, [x10], #16
00001700  61fdff54  b.ne     #0x16ac
00001704  3f0108eb  cmp      x9, x8
00001708  a0030054  b.eq     #0x177c
0000170c  ea234091  add      x10, sp, #8, lsl #12
00001710  080109cb  sub      x8, x8, x9
00001714  4ac10091  add      x10, x10, #0x30
00001718  4b09098b  add      x11, x10, x9, lsl #2
0000171c  eac30091  add      x10, sp, #0x30
00001720  4a05098b  add      x10, x10, x9, lsl #1
00001724  e9a39052  mov      w9, #0x851f
00001728  6b090091  add      x11, x11, #2
0000172c  693daa72  movk     w9, #0x51eb, lsl #16
00001730  4c25c078  ldrsh    w12, [x10], #2
00001734  6fe15f78  ldurh    w15, [x11, #-2]
00001738  080500f1  subs     x8, x8, #1
0000173c  8d7d191b  mul      w13, w12, w25
00001740  8c7d181b  mul      w12, w12, w24
00001744  ad7d299b  smull    x13, w13, w9
00001748  8c7d299b  smull    x12, w12, w9
0000174c  aefd7fd3  lsr      x14, x13, #0x3f
00001750  adfd65d3  lsr      x13, x13, #0x25
00001754  90fd7fd3  lsr      x16, x12, #0x3f
00001758  8cfd65d3  lsr      x12, x12, #0x25
0000175c  ad010e0b  add      w13, w13, w14
00001760  6e014079  ldrh     w14, [x11]
00001764  8c01100b  add      w12, w12, w16
00001768  ed010d0b  add      w13, w15, w13
0000176c  cc010c0b  add      w12, w14, w12
00001770  6de11f78  sturh    w13, [x11, #-2]
00001774  6c450078  strh     w12, [x11], #4
00001778  c1fdff54  b.ne     #0x1730
0000177c  f70700b9  str      w23, [sp, #4]
00001780  990000b0  adrp     x25, #0x12000
00001784  980000b0  adrp     x24, #0x12000
00001788  fbd300a9  stp      x27, x20, [sp, #8]
0000178c  970000b0  adrp     x23, #0x12000
00001790  93008052  mov      w19, #4
00001794  14028052  mov      w20, #0x10
00001798  9b0000b0  adrp     x27, #0x12000
0000179c  fad701a9  stp      x26, x21, [sp, #0x18]
000017a0  f62f00b9  str      w22, [sp, #0x2c]
000017a4  285f4fb9  ldr      w8, [x25, #0xf5c]
000017a8  09634fb9  ldr      w9, [x24, #0xf60]
000017ac  1f01096b  cmp      w8, w9
000017b0  81000054  b.ne     #0x17c0
000017b4  68ab47f9  ldr      x8, [x27, #0xf50]
000017b8  680100b5  cbnz     x8, #0x17e4
000017bc  6a000014  b        #0x1964
000017c0  880000b0  adrp     x8, #0x12000
000017c4  295f4fb9  ldr      w9, [x25, #0xf5c]
000017c8  08917d39  ldrb     w8, [x8, #0xf64]
000017cc  1f010071  cmp      w8, #0
000017d0  68ab47f9  ldr      x8, [x27, #0xf50]
000017d4  8a12931a  csel     w10, w20, w19, ne
000017d8  3f010a6b  cmp      w9, w10
000017dc  8b010054  b.lt     #0x180c
000017e0  680100b4  cbz      x8, #0x180c
000017e4  e86a4fb9  ldr      w8, [x23, #0xf68]
000017e8  1f090071  cmp      w8, #2
000017ec  81000054  b.ne     #0x17fc
000017f0  40018052  mov      w0, #0xa
000017f4  13050094  bl       #0x2c40 ; usleep@plt
000017f8  ebffff17  b        #0x17a4
000017fc  880000b0  adrp     x8, #0x12000
00001800  1a634fb9  ldr      w26, [x24, #0xf60]
00001804  186d4fb9  ldr      w24, [x8, #0xf6c]
00001808  05000014  b        #0x181c
0000180c  890000b0  adrp     x9, #0x12000
00001810  1a634fb9  ldr      w26, [x24, #0xf60]
00001814  386d4fb9  ldr      w24, [x9, #0xf6c]
00001818  c80a00b4  cbz      x8, #0x1970
0000181c  f42f40b9  ldr      w20, [sp, #0x2c]
00001820  f3031f2a  mov      w19, wzr
00001824  95008052  mov      w21, #4
00001828  1c028052  mov      w28, #0x10
0000182c  e86a4fb9  ldr      w8, [x23, #0xf68]
00001830  1f090071  cmp      w8, #2
00001834  e1090054  b.ne     #0x1970
00001838  880000b0  adrp     x8, #0x12000
0000183c  08594fb9  ldr      w8, [x8, #0xf58]
00001840  1f03086b  cmp      w24, w8
00001844  eb030054  b.lt     #0x18c0
00001848  960000b0  adrp     x22, #0x12000
0000184c  c8927d39  ldrb     w8, [x22, #0xf64]
00001850  1f010071  cmp      w8, #0
00001854  48070011  add      w8, w26, #1
00001858  8913951a  csel     w9, w28, w21, ne
0000185c  1f01096b  cmp      w8, w9
00001860  faa79a1a  csinc    w26, wzr, w26, ge
00001864  285f4fb9  ldr      w8, [x25, #0xf5c]
00001868  1f011a6b  cmp      w8, w26
0000186c  81000054  b.ne     #0x187c
00001870  68ab47f9  ldr      x8, [x27, #0xf50]
00001874  480100b5  cbnz     x8, #0x189c
00001878  0f000014  b        #0x18b4
0000187c  285f4fb9  ldr      w8, [x25, #0xf5c]
00001880  c9927d39  ldrb     w9, [x22, #0xf64]
00001884  6aab47f9  ldr      x10, [x27, #0xf50]
00001888  3f010071  cmp      w9, #0
0000188c  8913951a  csel     w9, w28, w21, ne
00001890  1f01096b  cmp      w8, w9
00001894  44a940fa  ccmp     x10, #0, #4, ge
00001898  e0000054  b.eq     #0x18b4
0000189c  e86a4fb9  ldr      w8, [x23, #0xf68]
000018a0  1f090071  cmp      w8, #2
000018a4  81000054  b.ne     #0x18b4
000018a8  40018052  mov      w0, #0xa
000018ac  e5040094  bl       #0x2c40 ; usleep@plt
000018b0  edffff17  b        #0x1864
000018b4  880000b0  adrp     x8, #0x12000
000018b8  f8031f2a  mov      w24, wzr
000018bc  08594fb9  ldr      w8, [x8, #0xf58]
000018c0  5b7f4093  sxtw     x27, w26
000018c4  0801184b  sub      w8, w8, w24
000018c8  1f2003d5  nop      
000018cc  29b50810  adr      x9, #0x12f70
000018d0  9f02086b  cmp      w20, w8
000018d4  293d1b8b  add      x9, x9, x27, lsl #15
000018d8  96b2881a  csel     w22, w20, w8, lt
000018dc  e8234091  add      x8, sp, #8, lsl #12
000018e0  08c10091  add      x8, x8, #0x30
000018e4  c27e7f93  sbfiz    x2, x22, #1, #0x20
000018e8  20c5388b  add      x0, x9, w24, sxtw #1
000018ec  01c5338b  add      x1, x8, w19, sxtw #1
000018f0  e4040094  bl       #0x2c80 ; memmove@plt
000018f4  df060071  cmp      w22, #1
000018f8  4d020054  b.le     #0x1940
000018fc  880400b0  adrp     x8, #0x92000
00001900  08b947f9  ldr      x8, [x8, #0xf70]
00001904  e80100b4  cbz      x8, #0x1940
00001908  7f020071  cmp      w19, #0
0000190c  1f2003d5  nop      
00001910  4ab34810  adr      x10, #0x92f78
00001914  68a6931a  cinc     w8, w19, lt
00001918  1f030071  cmp      w24, #0
0000191c  4a3d1b8b  add      x10, x10, x27, lsl #15
00001920  09a7981a  cinc     w9, w24, lt
00001924  087d0113  asr      w8, w8, #1
00001928  ebc30091  add      x11, sp, #0x30
0000192c  297d0113  asr      w9, w9, #1
00001930  c2761f12  and      w2, w22, #0x7ffffffe
00001934  60c5288b  add      x0, x11, w8, sxtw #1
00001938  41c5298b  add      x1, x10, w9, sxtw #1
0000193c  d1040094  bl       #0x2c80 ; memmove@plt
00001940  9402164b  sub      w20, w20, w22
00001944  d802180b  add      w24, w22, w24
00001948  9f060071  cmp      w20, #1
0000194c  2b010054  b.lt     #0x1970
00001950  9b0000b0  adrp     x27, #0x12000
00001954  d302130b  add      w19, w22, w19
00001958  68ab47f9  ldr      x8, [x27, #0xf50]
0000195c  88f6ffb5  cbnz     x8, #0x182c
00001960  04000014  b        #0x1970
00001964  880000b0  adrp     x8, #0x12000
00001968  1a634fb9  ldr      w26, [x24, #0xf60]
0000196c  186d4fb9  ldr      w24, [x8, #0xf6c]
00001970  880000b0  adrp     x8, #0x12000
00001974  e4234091  add      x4, sp, #8, lsl #12
00001978  e2031f2a  mov      w2, wzr
0000197c  1a610fb9  str      w26, [x8, #0xf60]
00001980  880000b0  adrp     x8, #0x12000
00001984  84c00091  add      x4, x4, #0x30
00001988  186d0fb9  str      w24, [x8, #0xf6c]
0000198c  e1cf40a9  ldp      x1, x19, [sp, #8]
00001990  e32f40b9  ldr      w3, [sp, #0x2c]
00001994  680240f9  ldr      x8, [x19]
00001998  e00313aa  mov      x0, x19
0000199c  084943f9  ldr      x8, [x8, #0x690]
000019a0  00013fd6  blr      x8
000019a4  680240f9  ldr      x8, [x19]
000019a8  e10f40f9  ldr      x1, [sp, #0x18]
000019ac  e4c30091  add      x4, sp, #0x30
000019b0  e30740b9  ldr      w3, [sp, #4]
000019b4  e00313aa  mov      x0, x19
000019b8  e2031f2a  mov      w2, wzr
000019bc  084943f9  ldr      x8, [x8, #0x690]
000019c0  00013fd6  blr      x8
000019c4  f51340f9  ldr      x21, [sp, #0x20]
000019c8  a81640f9  ldr      x8, [x21, #0x28]
000019cc  a9035ff8  ldur     x9, [x29, #-0x10]
000019d0  1f0109eb  cmp      x8, x9
000019d4  41010054  b.ne     #0x19fc
000019d8  ff434091  add      sp, sp, #0x10, lsl #12
000019dc  ff030191  add      sp, sp, #0x40
000019e0  f44f45a9  ldp      x20, x19, [sp, #0x50]
000019e4  f65744a9  ldp      x22, x21, [sp, #0x40]
000019e8  f85f43a9  ldp      x24, x23, [sp, #0x30]
000019ec  fa6742a9  ldp      x26, x25, [sp, #0x20]
000019f0  fc6f41a9  ldp      x28, x27, [sp, #0x10]
000019f4  fd7bc6a8  ldp      x29, x30, [sp], #0x60
000019f8  c0035fd6  ret      
000019fc  99040094  bl       #0x2c60 ; __stack_chk_fail@plt
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
00002448  fd7bbda9  stp      x29, x30, [sp, #-0x30]!
0000244c  f50b00f9  str      x21, [sp, #0x10]
00002450  f44f02a9  stp      x20, x19, [sp, #0x20]
00002454  fd030091  mov      x29, sp
00002458  94000090  adrp     x20, #0x12000
0000245c  f30300aa  mov      x19, x0
00002460  88aa47f9  ldr      x8, [x20, #0xf50]
00002464  880100b4  cbz      x8, #0x2494
00002468  090140f9  ldr      x9, [x8]
0000246c  e00308aa  mov      x0, x8
00002470  291940f9  ldr      x9, [x9, #0x30]
00002474  20013fd6  blr      x9
00002478  88080090  adrp     x8, #0x112000
0000247c  89080090  adrp     x9, #0x112000
00002480  9faa07f9  str      xzr, [x20, #0xf50]
00002484  1fe107f9  str      xzr, [x8, #0xfc0]
00002488  880800b0  adrp     x8, #0x113000
0000248c  3fe507f9  str      xzr, [x9, #0xfc8]
00002490  1f1100f9  str      xzr, [x8, #0x20]
00002494  940800b0  adrp     x20, #0x113000
00002498  801640f9  ldr      x0, [x20, #0x28]
0000249c  200100b4  cbz      x0, #0x24c0
000024a0  080040f9  ldr      x8, [x0]
000024a4  081940f9  ldr      x8, [x8, #0x30]
000024a8  00013fd6  blr      x8
000024ac  88080090  adrp     x8, #0x112000
000024b0  89040090  adrp     x9, #0x92000
000024b4  9f1600f9  str      xzr, [x20, #0x28]
000024b8  1fe907f9  str      xzr, [x8, #0xfd0]
000024bc  3fb907f9  str      xzr, [x9, #0xf70]
000024c0  940800b0  adrp     x20, #0x113000
000024c4  800e40f9  ldr      x0, [x20, #0x18]
000024c8  a00000b4  cbz      x0, #0x24dc
000024cc  080040f9  ldr      x8, [x0]
000024d0  081940f9  ldr      x8, [x8, #0x30]
000024d4  00013fd6  blr      x8
000024d8  9f0e00f9  str      xzr, [x20, #0x18]
000024dc  94080090  adrp     x20, #0x112000
000024e0  80d247f9  ldr      x0, [x20, #0xfa0]
000024e4  e00000b4  cbz      x0, #0x2500
000024e8  080040f9  ldr      x8, [x0]
000024ec  081940f9  ldr      x8, [x8, #0x30]
000024f0  00013fd6  blr      x8
000024f4  880800b0  adrp     x8, #0x113000
000024f8  9fd207f9  str      xzr, [x20, #0xfa0]
000024fc  1f0900f9  str      xzr, [x8, #0x10]
00002500  95080090  adrp     x21, #0x112000
00002504  94080090  adrp     x20, #0x112000
00002508  b30100b4  cbz      x19, #0x253c
0000250c  a1f647f9  ldr      x1, [x21, #0xfe8]
00002510  a10000b4  cbz      x1, #0x2524
00002514  680240f9  ldr      x8, [x19]
00002518  e00313aa  mov      x0, x19
0000251c  085940f9  ldr      x8, [x8, #0xb0]
00002520  00013fd6  blr      x8
00002524  81fa47f9  ldr      x1, [x20, #0xff0]
00002528  a10000b4  cbz      x1, #0x253c
0000252c  680240f9  ldr      x8, [x19]
00002530  e00313aa  mov      x0, x19
00002534  085940f9  ldr      x8, [x8, #0xb0]
00002538  00013fd6  blr      x8
0000253c  880800b0  adrp     x8, #0x113000
00002540  bff607f9  str      xzr, [x21, #0xfe8]
00002544  1f0100f9  str      xzr, [x8]
00002548  88080090  adrp     x8, #0x112000
0000254c  1ffd07f9  str      xzr, [x8, #0xff8]
00002550  88080090  adrp     x8, #0x112000
00002554  9ffa07f9  str      xzr, [x20, #0xff0]
00002558  1fd90fb9  str      wzr, [x8, #0xfd8]
0000255c  88000090  adrp     x8, #0x12000
00002560  1f690fb9  str      wzr, [x8, #0xf68]
00002564  f44f42a9  ldp      x20, x19, [sp, #0x20]
00002568  f50b40f9  ldr      x21, [sp, #0x10]
0000256c  fd7bc3a8  ldp      x29, x30, [sp], #0x30
00002570  c0035fd6  ret      
00002574  fd7bbaa9  stp      x29, x30, [sp, #-0x60]!
00002578  fc6f01a9  stp      x28, x27, [sp, #0x10]
0000257c  fa6702a9  stp      x26, x25, [sp, #0x20]
00002580  f85f03a9  stp      x24, x23, [sp, #0x30]
00002584  f65704a9  stp      x22, x21, [sp, #0x40]
00002588  f44f05a9  stp      x20, x19, [sp, #0x50]
0000258c  fd030091  mov      x29, sp
00002590  96000090  adrp     x22, #0x12000
00002594  88080090  adrp     x8, #0x112000
00002598  c96a4fb9  ldr      w9, [x22, #0xf68]
0000259c  0ae547f9  ldr      x10, [x8, #0xfc8]
000025a0  88000090  adrp     x8, #0x12000
000025a4  08594fb9  ldr      w8, [x8, #0xf58]
000025a8  3f090071  cmp      w9, #2
000025ac  440940fa  ccmp     x10, #0, #4, eq
000025b0  0819417a  ccmp     w8, #1, #8, ne
000025b4  cb170054  b.lt     #0x28ac
000025b8  8a080090  adrp     x10, #0x112000
000025bc  49d94fb9  ldr      w9, [x10, #0xfd8]
000025c0  29050011  add      w9, w9, #1
000025c4  49d90fb9  str      w9, [x10, #0xfd8]
000025c8  89000090  adrp     x9, #0x12000
000025cc  385d4fb9  ldr      w24, [x9, #0xf5c]
000025d0  89000090  adrp     x9, #0x12000
000025d4  1801f837  tbnz     w24, #0x1f, #0x25f4
000025d8  29917d39  ldrb     w9, [x9, #0xf64]
000025dc  0a028052  mov      w10, #0x10
000025e0  3f010071  cmp      w9, #0
000025e4  89008052  mov      w9, #4
000025e8  4911891a  csel     w9, w10, w9, ne
000025ec  1f03096b  cmp      w24, w9
000025f0  43000054  b.lo     #0x25f8
000025f4  f8031f2a  mov      w24, wzr
000025f8  89000090  adrp     x9, #0x12000
000025fc  fb031f2a  mov      w27, wzr
00002600  95000090  adrp     x21, #0x12000
00002604  33494fb9  ldr      w19, [x9, #0xf48]
00002608  7f060071  cmp      w19, #1
0000260c  cb020054  b.lt     #0x2664
00002610  b44e4fb9  ldr      w20, [x21, #0xf4c]
00002614  9f060071  cmp      w20, #1
00002618  6b020054  b.lt     #0x2664
0000261c  6902144b  sub      w9, w19, w20
00002620  297da90a  bic      w9, w9, w9, asr #31
00002624  3f01086b  cmp      w9, w8
00002628  3b31881a  csel     w27, w9, w8, lo
0000262c  db010034  cbz      w27, #0x2664
00002630  1f2003d5  nop      
00002634  e8490810  adr      x8, #0x12f70
00002638  e903182a  mov      w9, w24
0000263c  1f2003d5  nop      
00002640  4a480410  adr      x10, #0xaf48
00002644  003d098b  add      x0, x8, x9, lsl #15
00002648  4105148b  add      x1, x10, x20, lsl #1
0000264c  627b1f53  lsl      w2, w27, #1
00002650  80010094  bl       #0x2c50 ; memcpy@plt
00002654  6803140b  add      w8, w27, w20
00002658  1f01136b  cmp      w8, w19
0000265c  08319f1a  csel     w8, w8, wzr, lo
00002660  a84e0fb9  str      w8, [x21, #0xf4c]
00002664  94080090  adrp     x20, #0x112000
00002668  88f247f9  ldr      x8, [x20, #0xfe0]
0000266c  e80200b5  cbnz     x8, #0x26c8
00002670  930800b0  adrp     x19, #0x113000
00002674  600240f9  ldr      x0, [x19]
00002678  800200b4  cbz      x0, #0x26c8
0000267c  080040f9  ldr      x8, [x0]
00002680  c2008052  mov      w2, #6
00002684  81080090  adrp     x1, #0x112000
00002688  21803f91  add      x1, x1, #0xfe0
0000268c  2200a072  movk     w2, #1, lsl #16
00002690  081940f9  ldr      x8, [x8, #0x30]
00002694  00013fd6  blr      x8
00002698  80010034  cbz      w0, #0x26c8
0000269c  1f080031  cmn      w0, #2
000026a0  21010054  b.ne     #0x26c4
000026a4  600240f9  ldr      x0, [x19]
000026a8  81080090  adrp     x1, #0x112000
000026ac  21803f91  add      x1, x1, #0xfe0
000026b0  e2031faa  mov      x2, xzr
000026b4  080040f9  ldr      x8, [x0]
000026b8  081140f9  ldr      x8, [x8, #0x20]
000026bc  00013fd6  blr      x8
000026c0  40000034  cbz      w0, #0x26c8
000026c4  9ff207f9  str      xzr, [x20, #0xfe0]
000026c8  89000090  adrp     x9, #0x12000
000026cc  88f247f9  ldr      x8, [x20, #0xfe0]
000026d0  37594fb9  ldr      w23, [x9, #0xf58]
000026d4  680600b4  cbz      x8, #0x27a0
000026d8  99080090  adrp     x25, #0x112000
000026dc  28f747f9  ldr      x8, [x25, #0xfe8]
000026e0  080600b4  cbz      x8, #0x27a0
000026e4  9c080090  adrp     x28, #0x112000
000026e8  88ff47f9  ldr      x8, [x28, #0xff8]
000026ec  a80500b4  cbz      x8, #0x27a0
000026f0  7f03176b  cmp      w27, w23
000026f4  6a050054  b.ge     #0x27a0
000026f8  c86a4fb9  ldr      w8, [x22, #0xf68]
000026fc  1f090071  cmp      w8, #2
00002700  01050054  b.ne     #0x27a0
00002704  e803182a  mov      w8, w24
00002708  1f2003d5  nop      
0000270c  29430810  adr      x9, #0x12f70
00002710  353d088b  add      x21, x9, x8, lsl #15
00002714  80f247f9  ldr      x0, [x20, #0xfe0]
00002718  21f747f9  ldr      x1, [x25, #0xfe8]
0000271c  82ff47f9  ldr      x2, [x28, #0xff8]
00002720  080040f9  ldr      x8, [x0]
00002724  08f540f9  ldr      x8, [x8, #0x1e8]
00002728  00013fd6  blr      x8
0000272c  88000090  adrp     x8, #0x12000
00002730  1a494fb9  ldr      w26, [x8, #0xf48]
00002734  88000090  adrp     x8, #0x12000
00002738  17594fb9  ldr      w23, [x8, #0xf58]
0000273c  5f070071  cmp      w26, #1
00002740  0b030054  b.lt     #0x27a0
00002744  e8021b4b  sub      w8, w23, w27
00002748  1f050071  cmp      w8, #1
0000274c  ab020054  b.lt     #0x27a0
00002750  5f03086b  cmp      w26, w8
00002754  a0463b8b  add      x0, x21, w27, uxtw #1
00002758  1f2003d5  nop      
0000275c  613f0410  adr      x1, #0xaf48
00002760  5333881a  csel     w19, w26, w8, lo
00002764  627a1f53  lsl      w2, w19, #1
00002768  3a010094  bl       #0x2c50 ; memcpy@plt
0000276c  8a000090  adrp     x10, #0x12000
00002770  7b021b0b  add      w27, w19, w27
00002774  484d4fb9  ldr      w8, [x10, #0xf4c]
00002778  0801130b  add      w8, w8, w19
0000277c  1f011a6b  cmp      w8, w26
00002780  e9b39a1a  csel     w9, wzr, w26, lt
00002784  7f03176b  cmp      w27, w23
00002788  0801094b  sub      w8, w8, w9
0000278c  484d0fb9  str      w8, [x10, #0xf4c]
00002790  8a000054  b.ge     #0x27a0
00002794  c86a4fb9  ldr      w8, [x22, #0xf68]
00002798  1f090071  cmp      w8, #2
0000279c  c0fbff54  b.eq     #0x2714
000027a0  e8021b6b  subs     w8, w23, w27
000027a4  2d010054  b.le     #0x27c8
000027a8  e903182a  mov      w9, w24
000027ac  1f2003d5  nop      
000027b0  0a3e0810  adr      x10, #0x12f70
000027b4  493d098b  add      x9, x10, x9, lsl #15
000027b8  027d7f93  sbfiz    x2, x8, #1, #0x20
000027bc  e1031f2a  mov      w1, wzr
000027c0  20c53b8b  add      x0, x9, w27, sxtw #1
000027c4  3b010094  bl       #0x2cb0 ; memset@plt
000027c8  c86a4fb9  ldr      w8, [x22, #0xf68]
000027cc  94080090  adrp     x20, #0x112000
000027d0  95000090  adrp     x21, #0x12000
000027d4  1f090071  cmp      w8, #2
000027d8  41060054  b.ne     #0x28a0
000027dc  88080090  adrp     x8, #0x112000
000027e0  00e547f9  ldr      x0, [x8, #0xfc8]
000027e4  e00500b4  cbz      x0, #0x28a0
000027e8  080040f9  ldr      x8, [x0]
000027ec  e903182a  mov      w9, w24
000027f0  1f2003d5  nop      
000027f4  ea3b0810  adr      x10, #0x12f70
000027f8  e27a1f53  lsl      w2, w23, #1
000027fc  080140f9  ldr      x8, [x8]
00002800  413d098b  add      x1, x10, x9, lsl #15
00002804  00013fd6  blr      x8
00002808  a8927d39  ldrb     w8, [x21, #0xf64]
0000280c  09028052  mov      w9, #0x10
00002810  0a070011  add      w10, w24, #1
00002814  1f010071  cmp      w8, #0
00002818  88008052  mov      w8, #4
0000281c  2811881a  csel     w8, w9, w8, ne
00002820  89000090  adrp     x9, #0x12000
00002824  5f01086b  cmp      w10, w8
00002828  e8a7981a  csinc    w8, wzr, w24, ge
0000282c  285d0fb9  str      w8, [x9, #0xf5c]
00002830  88080090  adrp     x8, #0x112000
00002834  08f17e39  ldrb     w8, [x8, #0xfbc]
00002838  48030034  cbz      w8, #0x28a0
0000283c  88040090  adrp     x8, #0x92000
00002840  00b947f9  ldr      x0, [x8, #0xf70]
00002844  e00200b4  cbz      x0, #0x28a0
00002848  080040f9  ldr      x8, [x0]
0000284c  930800b0  adrp     x19, #0x113000
00002850  1f2003d5  nop      
00002854  2a394810  adr      x10, #0x92f78
00002858  080140f9  ldr      x8, [x8]
0000285c  690a80b9  ldrsw    x9, [x19, #8]
00002860  413d098b  add      x1, x10, x9, lsl #15
00002864  89000090  adrp     x9, #0x12000
00002868  22594fb9  ldr      w2, [x9, #0xf58]
0000286c  00013fd6  blr      x8
00002870  680a40b9  ldr      w8, [x19, #8]
00002874  0a028052  mov      w10, #0x10
00002878  08050011  add      w8, w8, #1
0000287c  680a00b9  str      w8, [x19, #8]
00002880  680a40b9  ldr      w8, [x19, #8]
00002884  a9927d39  ldrb     w9, [x21, #0xf64]
00002888  3f010071  cmp      w9, #0
0000288c  89008052  mov      w9, #4
00002890  4911891a  csel     w9, w10, w9, ne
00002894  1f01096b  cmp      w8, w9
00002898  4b000054  b.lt     #0x28a0
0000289c  7f0a00b9  str      wzr, [x19, #8]
000028a0  88da4fb9  ldr      w8, [x20, #0xfd8]
000028a4  08050051  sub      w8, w8, #1
000028a8  88da0fb9  str      w8, [x20, #0xfd8]
000028ac  f44f45a9  ldp      x20, x19, [sp, #0x50]
000028b0  f65744a9  ldp      x22, x21, [sp, #0x40]
000028b4  f85f43a9  ldp      x24, x23, [sp, #0x30]
000028b8  fa6742a9  ldp      x26, x25, [sp, #0x20]
000028bc  fc6f41a9  ldp      x28, x27, [sp, #0x10]
000028c0  fd7bc6a8  ldp      x29, x30, [sp], #0x60
000028c4  c0035fd6  ret      
000028c8  fd7bbca9  stp      x29, x30, [sp, #-0x40]!
000028cc  f70b00f9  str      x23, [sp, #0x10]
000028d0  f65702a9  stp      x22, x21, [sp, #0x20]
000028d4  f44f03a9  stp      x20, x19, [sp, #0x30]
000028d8  fd030091  mov      x29, sp
000028dc  88000090  adrp     x8, #0x12000
000028e0  94000090  adrp     x20, #0x12000
000028e4  09694fb9  ldr      w9, [x8, #0xf68]
000028e8  88080090  adrp     x8, #0x112000
000028ec  00e547f9  ldr      x0, [x8, #0xfc8]
000028f0  885a4fb9  ldr      w8, [x20, #0xf58]
000028f4  3f090071  cmp      w9, #2
000028f8  040840fa  ccmp     x0, #0, #4, eq
000028fc  0819417a  ccmp     w8, #1, #8, ne
00002900  0b080054  b.lt     #0x2a00
00002904  93080090  adrp     x19, #0x112000
00002908  96000090  adrp     x22, #0x12000
0000290c  95000090  adrp     x21, #0x12000
00002910  69da4fb9  ldr      w9, [x19, #0xfd8]
00002914  29050011  add      w9, w9, #1
00002918  69da0fb9  str      w9, [x19, #0xfd8]
0000291c  d75e4fb9  ldr      w23, [x22, #0xf5c]
00002920  1701f837  tbnz     w23, #0x1f, #0x2940
00002924  a9927d39  ldrb     w9, [x21, #0xf64]
00002928  0a028052  mov      w10, #0x10
0000292c  3f010071  cmp      w9, #0
00002930  89008052  mov      w9, #4
00002934  4911891a  csel     w9, w10, w9, ne
00002938  ff02096b  cmp      w23, w9
0000293c  43000054  b.lo     #0x2944
00002940  f7031f2a  mov      w23, wzr
00002944  090040f9  ldr      x9, [x0]
00002948  ea03172a  mov      w10, w23
0000294c  1f2003d5  nop      
00002950  0b310810  adr      x11, #0x12f70
00002954  02791f53  lsl      w2, w8, #1
00002958  290140f9  ldr      x9, [x9]
0000295c  613d0a8b  add      x1, x11, x10, lsl #15
00002960  20013fd6  blr      x9
00002964  a8927d39  ldrb     w8, [x21, #0xf64]
00002968  09028052  mov      w9, #0x10
0000296c  ea060011  add      w10, w23, #1
00002970  1f010071  cmp      w8, #0
00002974  88008052  mov      w8, #4
00002978  2811881a  csel     w8, w9, w8, ne
0000297c  5f01086b  cmp      w10, w8
00002980  e827971a  csinc    w8, wzr, w23, hs
00002984  c85e0fb9  str      w8, [x22, #0xf5c]
00002988  88080090  adrp     x8, #0x112000
0000298c  08f17e39  ldrb     w8, [x8, #0xfbc]
00002990  28030034  cbz      w8, #0x29f4
00002994  88040090  adrp     x8, #0x92000
00002998  00b947f9  ldr      x0, [x8, #0xf70]
0000299c  c00200b4  cbz      x0, #0x29f4
000029a0  080040f9  ldr      x8, [x0]
000029a4  960800b0  adrp     x22, #0x113000
000029a8  1f2003d5  nop      
000029ac  6a2e4810  adr      x10, #0x92f78
000029b0  080140f9  ldr      x8, [x8]
000029b4  c90a80b9  ldrsw    x9, [x22, #8]
000029b8  825a4fb9  ldr      w2, [x20, #0xf58]
000029bc  413d098b  add      x1, x10, x9, lsl #15
000029c0  00013fd6  blr      x8
000029c4  c80a40b9  ldr      w8, [x22, #8]
000029c8  0a028052  mov      w10, #0x10
000029cc  08050011  add      w8, w8, #1
000029d0  c80a00b9  str      w8, [x22, #8]
000029d4  c80a40b9  ldr      w8, [x22, #8]
000029d8  a9927d39  ldrb     w9, [x21, #0xf64]
000029dc  3f010071  cmp      w9, #0
000029e0  89008052  mov      w9, #4
000029e4  4911891a  csel     w9, w10, w9, ne
000029e8  1f01096b  cmp      w8, w9
000029ec  4b000054  b.lt     #0x29f4
000029f0  df0a00b9  str      wzr, [x22, #8]
000029f4  68da4fb9  ldr      w8, [x19, #0xfd8]
000029f8  08050051  sub      w8, w8, #1
000029fc  68da0fb9  str      w8, [x19, #0xfd8]
00002a00  f44f43a9  ldp      x20, x19, [sp, #0x30]
00002a04  f70b40f9  ldr      x23, [sp, #0x10]
00002a08  f65742a9  ldp      x22, x21, [sp, #0x20]
00002a0c  fd7bc4a8  ldp      x29, x30, [sp], #0x40
00002a10  c0035fd6  ret      
00002a14  fd7bbea9  stp      x29, x30, [sp, #-0x20]!
00002a18  f30b00f9  str      x19, [sp, #0x10]
00002a1c  fd030091  mov      x29, sp
00002a20  80080090  adrp     x0, #0x112000
00002a24  00e03d91  add      x0, x0, #0xf78
00002a28  9a000094  bl       #0x2c90 ; pthread_mutex_lock@plt
00002a2c  93000090  adrp     x19, #0x12000
00002a30  68008052  mov      w8, #3
00002a34  89080090  adrp     x9, #0x112000
00002a38  686a0fb9  str      w8, [x19, #0xf68]
00002a3c  88080090  adrp     x8, #0x112000
00002a40  3ff13e39  strb     wzr, [x9, #0xfbc]
00002a44  00e147f9  ldr      x0, [x8, #0xfc0]
00002a48  a00000b4  cbz      x0, #0x2a5c
00002a4c  080040f9  ldr      x8, [x0]
00002a50  21008052  mov      w1, #1
00002a54  080140f9  ldr      x8, [x8]
00002a58  00013fd6  blr      x8
00002a5c  88080090  adrp     x8, #0x112000
00002a60  00e547f9  ldr      x0, [x8, #0xfc8]
00002a64  800000b4  cbz      x0, #0x2a74
00002a68  080040f9  ldr      x8, [x0]
00002a6c  080540f9  ldr      x8, [x8, #8]
00002a70  00013fd6  blr      x8
00002a74  88080090  adrp     x8, #0x112000
00002a78  00e947f9  ldr      x0, [x8, #0xfd0]
00002a7c  a00000b4  cbz      x0, #0x2a90
00002a80  080040f9  ldr      x8, [x0]
00002a84  21008052  mov      w1, #1
00002a88  080140f9  ldr      x8, [x8]
00002a8c  00013fd6  blr      x8
00002a90  88040090  adrp     x8, #0x92000
00002a94  00b947f9  ldr      x0, [x8, #0xf70]
00002a98  800000b4  cbz      x0, #0x2aa8
00002a9c  080040f9  ldr      x8, [x0]
00002aa0  080540f9  ldr      x8, [x8, #8]
00002aa4  00013fd6  blr      x8
00002aa8  80080090  adrp     x0, #0x112000
00002aac  00e03d91  add      x0, x0, #0xf78
00002ab0  7f6a0fb9  str      wzr, [x19, #0xf68]
00002ab4  f30b40f9  ldr      x19, [sp, #0x10]
00002ab8  fd7bc2a8  ldp      x29, x30, [sp], #0x20
00002abc  79000014  b        #0x2ca0 ; pthread_mutex_unlock@plt
00002ac0  88000090  adrp     x8, #0x12000
00002ac4  08694fb9  ldr      w8, [x8, #0xf68]
00002ac8  1f010071  cmp      w8, #0
00002acc  e0179f1a  cset     w0, eq
00002ad0  c0035fd6  ret      
00002ad4  fd7bbca9  stp      x29, x30, [sp, #-0x40]!
00002ad8  f70b00f9  str      x23, [sp, #0x10]
00002adc  f65702a9  stp      x22, x21, [sp, #0x20]
00002ae0  f44f03a9  stp      x20, x19, [sp, #0x30]
00002ae4  fd030091  mov      x29, sp
00002ae8  88080090  adrp     x8, #0x112000
00002aec  e0031f2a  mov      w0, wzr
00002af0  13e947f9  ldr      x19, [x8, #0xfd0]
00002af4  d30500b4  cbz      x19, #0x2bac
00002af8  88040090  adrp     x8, #0x92000
00002afc  08b947f9  ldr      x8, [x8, #0xf70]
00002b00  680500b4  cbz      x8, #0x2bac
00002b04  88000090  adrp     x8, #0x12000
00002b08  96000090  adrp     x22, #0x12000
00002b0c  1f2003d5  nop      
00002b10  55234810  adr      x21, #0x92f78
00002b14  08598fb9  ldrsw    x8, [x8, #0xf58]
00002b18  c9927d39  ldrb     w9, [x22, #0xf64]
00002b1c  14f97fd3  lsl      x20, x8, #1
00002b20  3f010071  cmp      w9, #0
00002b24  88008052  mov      w8, #4
00002b28  09028052  mov      w9, #0x10
00002b2c  3711889a  csel     x23, x9, x8, ne
00002b30  e00315aa  mov      x0, x21
00002b34  e1031f2a  mov      w1, wzr
00002b38  e20314aa  mov      x2, x20
00002b3c  5d000094  bl       #0x2cb0 ; memset@plt
00002b40  f70600f1  subs     x23, x23, #1
00002b44  b5224091  add      x21, x21, #8, lsl #12
00002b48  41ffff54  b.ne     #0x2b30
00002b4c  680240f9  ldr      x8, [x19]
00002b50  e00313aa  mov      x0, x19
00002b54  61008052  mov      w1, #3
00002b58  080140f9  ldr      x8, [x8]
00002b5c  00013fd6  blr      x8
00002b60  60000034  cbz      w0, #0x2b6c
00002b64  e0031f2a  mov      w0, wzr
00002b68  11000014  b        #0x2bac
00002b6c  88000090  adrp     x8, #0x12000
00002b70  085d4fb9  ldr      w8, [x8, #0xf5c]
00002b74  09050051  sub      w9, w8, #1
00002b78  880800b0  adrp     x8, #0x113000
00002b7c  090900b9  str      w9, [x8, #8]
00002b80  090940b9  ldr      w9, [x8, #8]
00002b84  e900f836  tbz      w9, #0x1f, #0x2ba0
00002b88  c9927d39  ldrb     w9, [x22, #0xf64]
00002b8c  ea018052  mov      w10, #0xf
00002b90  3f010071  cmp      w9, #0
00002b94  69008052  mov      w9, #3
00002b98  4911891a  csel     w9, w10, w9, ne
00002b9c  090900b9  str      w9, [x8, #8]
00002ba0  88080090  adrp     x8, #0x112000
00002ba4  20008052  mov      w0, #1
00002ba8  00f13e39  strb     w0, [x8, #0xfbc]
00002bac  f44f43a9  ldp      x20, x19, [sp, #0x30]
00002bb0  f70b40f9  ldr      x23, [sp, #0x10]
00002bb4  f65742a9  ldp      x22, x21, [sp, #0x20]
00002bb8  fd7bc4a8  ldp      x29, x30, [sp], #0x40
00002bbc  c0035fd6  ret      
00002bc0  88080090  adrp     x8, #0x112000
00002bc4  1ff13e39  strb     wzr, [x8, #0xfbc]
00002bc8  88080090  adrp     x8, #0x112000
00002bcc  00e947f9  ldr      x0, [x8, #0xfd0]
00002bd0  a00000b4  cbz      x0, #0x2be4
00002bd4  080040f9  ldr      x8, [x0]
00002bd8  21008052  mov      w1, #1
00002bdc  020140f9  ldr      x2, [x8]
00002be0  40001fd6  br       x2
00002be4  c0035fd6  ret      
