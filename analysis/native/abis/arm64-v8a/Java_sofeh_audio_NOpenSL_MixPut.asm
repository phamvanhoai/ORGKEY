# APK member: lib/arm64-v8a/libnopensl.so
# Library SHA-256: dc900f9de170ec71471d8b5d2ccecc14d0bbb8c1d2346bd6e149467c888b38cf
# Function: Java_sofeh_audio_NOpenSL_MixPut; address=0x1240; bytes=792
# Decoder mode: default; decoded_bytes=792
00001240  fd7bbaa9          stp        x29, x30, [sp, #-0x60]!
00001244  fc6f01a9          stp        x28, x27, [sp, #0x10]
00001248  fa6702a9          stp        x26, x25, [sp, #0x20]
0000124c  f85f03a9          stp        x24, x23, [sp, #0x30]
00001250  f65704a9          stp        x22, x21, [sp, #0x40]
00001254  f44f05a9          stp        x20, x19, [sp, #0x50]
00001258  fd030091          mov        x29, sp
0000125c  ff4340d1          sub        sp, sp, #0x10, lsl #12
00001260  ff4300d1          sub        sp, sp, #0x10
00001264  5ad03bd5          mrs        x26, tpidr_el0
00001268  481740f9          ldr        x8, [x26, #0x28]
0000126c  a8831ff8          stur       x8, [x29, #-8]
00001270  821500b4          cbz        x2, #0x1520
00001274  f50303aa          mov        x21, x3
00001278  431500b4          cbz        x3, #0x1520
0000127c  080040f9          ldr        x8, [x0]
00001280  e10302aa          mov        x1, x2
00001284  f803062a          mov        w24, w6
00001288  f903052a          mov        w25, w5
0000128c  f603042a          mov        w22, w4
00001290  f30302aa          mov        x19, x2
00001294  08ad42f9          ldr        x8, [x8, #0x558]
00001298  f40300aa          mov        x20, x0
0000129c  00013fd6          blr        x8
000012a0  880240f9          ldr        x8, [x20]
000012a4  f703002a          mov        w23, w0
000012a8  e00314aa          mov        x0, x20
000012ac  e10315aa          mov        x1, x21
000012b0  08ad42f9          ldr        x8, [x8, #0x558]
000012b4  00013fd6          blr        x8
000012b8  ff02166b          cmp        w23, w22
000012bc  0a781f53          lsl        w10, w0, #1
000012c0  e8b2961a          csel       w8, w23, w22, lt
000012c4  1f010071          cmp        w8, #0
000012c8  09a5881a          cinc       w9, w8, lt
000012cc  1f04896b          cmp        w0, w9, asr #1
000012d0  5bb1881a          csel       w27, w10, w8, lt
000012d4  08008852          mov        w8, #0x4000
000012d8  7f134071          cmp        w27, #4, lsl #12
000012dc  76b3881a          csel       w22, w27, w8, lt
000012e0  7f070071          cmp        w27, #1
000012e4  eb110054          b.lt       #0x1520
000012e8  880240f9          ldr        x8, [x20]
000012ec  e4234091          add        x4, sp, #8, lsl #12
000012f0  e00314aa          mov        x0, x20
000012f4  84200091          add        x4, x4, #8
000012f8  e10313aa          mov        x1, x19
000012fc  e2031f2a          mov        w2, wzr
00001300  082943f9          ldr        x8, [x8, #0x650]
00001304  e303162a          mov        w3, w22
00001308  00013fd6          blr        x8
0000130c  880240f9          ldr        x8, [x20]
00001310  d77e0153          lsr        w23, w22, #1
00001314  e4230091          add        x4, sp, #8
00001318  e00314aa          mov        x0, x20
0000131c  e10315aa          mov        x1, x21
00001320  e2031f2a          mov        w2, wzr
00001324  082943f9          ldr        x8, [x8, #0x650]
00001328  e303172a          mov        w3, w23
0000132c  00013fd6          blr        x8
00001330  7f070071          cmp        w27, #1
00001334  a0080054          b.eq       #0x1448
00001338  ff060071          cmp        w23, #1
0000133c  e8869f1a          csinc      w8, w23, wzr, hi
00001340  7f230071          cmp        w27, #8
00001344  6a000054          b.ge       #0x1350
00001348  e9031faa          mov        x9, xzr
0000134c  23000014          b          #0x13d8
00001350  e9a39052          mov        w9, #0x851f
00001354  200f044e          dup        v0.4s, w25
00001358  010f044e          dup        v1.4s, w24
0000135c  693daa72          movk       w9, #0x51eb, lsl #16
00001360  ea234091          add        x10, sp, #8, lsl #12
00001364  eb230091          add        x11, sp, #8
00001368  220d044e          dup        v2.4s, w9
0000136c  092d7e92          and        x9, x8, #0x3ffc
00001370  4a210091          add        x10, x10, #8
00001374  ec0309aa          mov        x12, x9
00001378  638540fc          ldr        d3, [x11], #8
0000137c  8c1100f1          subs       x12, x12, #4
00001380  63a4100f          sshll      v3.4s, v3.4h, #0
00001384  049ca34e          mul        v4.4s, v0.4s, v3.4s
00001388  239ca34e          mul        v3.4s, v1.4s, v3.4s
0000138c  85c0a24e          smull2     v5.2d, v4.4s, v2.4s
00001390  84c0a20e          smull      v4.2d, v4.2s, v2.2s
00001394  66c0a24e          smull2     v6.2d, v3.4s, v2.4s
00001398  63c0a20e          smull      v3.2d, v3.2s, v2.2s
0000139c  8458854e          uzp2       v4.4s, v4.4s, v5.4s
000013a0  6358864e          uzp2       v3.4s, v3.4s, v6.4s
000013a4  85043b4f          sshr       v5.4s, v4.4s, #5
000013a8  66043b4f          sshr       v6.4s, v3.4s, #5
000013ac  8514216f          usra       v5.4s, v4.4s, #0x1f
000013b0  6614216f          usra       v6.4s, v3.4s, #0x1f
000013b4  4385400c          ld2        {v3.4h, v4.4h}, [x10]
000013b8  a528610e          xtn        v5.4h, v5.4s
000013bc  c628610e          xtn        v6.4h, v6.4s
000013c0  7084650e          add        v16.4h, v3.4h, v5.4h
000013c4  9184660e          add        v17.4h, v4.4h, v6.4h
000013c8  50859f0c          st2        {v16.4h, v17.4h}, [x10], #16
000013cc  61fdff54          b.ne       #0x1378
000013d0  3f0108eb          cmp        x9, x8
000013d4  a0030054          b.eq       #0x1448
000013d8  ea234091          add        x10, sp, #8, lsl #12
000013dc  080109cb          sub        x8, x8, x9
000013e0  4a210091          add        x10, x10, #8
000013e4  4b09098b          add        x11, x10, x9, lsl #2
000013e8  ea230091          add        x10, sp, #8
000013ec  4a05098b          add        x10, x10, x9, lsl #1
000013f0  e9a39052          mov        w9, #0x851f
000013f4  6b090091          add        x11, x11, #2
000013f8  693daa72          movk       w9, #0x51eb, lsl #16
000013fc  4c25c078          ldrsh      w12, [x10], #2
00001400  6fe15f78          ldurh      w15, [x11, #-2]
00001404  080500f1          subs       x8, x8, #1
00001408  8d7d191b          mul        w13, w12, w25
0000140c  8c7d181b          mul        w12, w12, w24
00001410  ad7d299b          smull      x13, w13, w9
00001414  8c7d299b          smull      x12, w12, w9
00001418  aefd7fd3          lsr        x14, x13, #0x3f
0000141c  adfd65d3          lsr        x13, x13, #0x25
00001420  90fd7fd3          lsr        x16, x12, #0x3f
00001424  8cfd65d3          lsr        x12, x12, #0x25
00001428  ad010e0b          add        w13, w13, w14
0000142c  6e014079          ldrh       w14, [x11]
00001430  8c01100b          add        w12, w12, w16
00001434  ed010d0b          add        w13, w15, w13
00001438  cc010c0b          add        w12, w14, w12
0000143c  6de11f78          sturh      w13, [x11, #-2]
00001440  6c450078          strh       w12, [x11], #4
00001444  c1fdff54          b.ne       #0x13fc
00001448  c27a1f53          lsl        w2, w22, #1
0000144c  e1234091          add        x1, sp, #8, lsl #12
00001450  1f2003d5          nop        
00001454  a0d70410          adr        x0, #0xaf48
00001458  21200091          add        x1, x1, #8
0000145c  03009052          mov        w3, #0x8000
00001460  04060094          bl         #0x2c70
00001464  880000b0          adrp       x8, #0x12000
00001468  8a0000b0          adrp       x10, #0x12000
0000146c  094d4fb9          ldr        w9, [x8, #0xf4c]
00001470  56490fb9          str        w22, [x10, #0xf48]
00001474  3f01166b          cmp        w9, w22
00001478  4b000054          b.lt       #0x1480
0000147c  1f4d0fb9          str        wzr, [x8, #0xf4c]
00001480  880400b0          adrp       x8, #0x92000
00001484  08b947f9          ldr        x8, [x8, #0xf70]
00001488  a80300b4          cbz        x8, #0x14fc
0000148c  880000b0          adrp       x8, #0x12000
00001490  095d4fb9          ldr        w9, [x8, #0xf5c]
00001494  4903f837          tbnz       w9, #0x1f, #0x14fc
00001498  890000b0          adrp       x9, #0x12000
0000149c  0a5d4fb9          ldr        w10, [x8, #0xf5c]
000014a0  0b028052          mov        w11, #0x10
000014a4  29917d39          ldrb       w9, [x9, #0xf64]
000014a8  3f010071          cmp        w9, #0
000014ac  89008052          mov        w9, #4
000014b0  6911891a          csel       w9, w11, w9, ne
000014b4  5f01096b          cmp        w10, w9
000014b8  2a020054          b.ge       #0x14fc
000014bc  085d8fb9          ldrsw      x8, [x8, #0xf5c]
000014c0  1f2003d5          nop        
000014c4  a9d54810          adr        x9, #0x92f78
000014c8  c2361f12          and        w2, w22, #0x7ffe
000014cc  e0230091          add        x0, sp, #8
000014d0  03009052          mov        w3, #0x8000
000014d4  213d088b          add        x1, x9, x8, lsl #15
000014d8  e6050094          bl         #0x2c70
000014dc  880240f9          ldr        x8, [x20]
000014e0  e4230091          add        x4, sp, #8
000014e4  e00314aa          mov        x0, x20
000014e8  e10315aa          mov        x1, x21
000014ec  e2031f2a          mov        w2, wzr
000014f0  e303172a          mov        w3, w23
000014f4  084943f9          ldr        x8, [x8, #0x690]
000014f8  00013fd6          blr        x8
000014fc  880240f9          ldr        x8, [x20]
00001500  e4234091          add        x4, sp, #8, lsl #12
00001504  e00314aa          mov        x0, x20
00001508  84200091          add        x4, x4, #8
0000150c  e10313aa          mov        x1, x19
00001510  e2031f2a          mov        w2, wzr
00001514  084943f9          ldr        x8, [x8, #0x690]
00001518  e303162a          mov        w3, w22
0000151c  00013fd6          blr        x8
00001520  481740f9          ldr        x8, [x26, #0x28]
00001524  a9835ff8          ldur       x9, [x29, #-8]
00001528  1f0109eb          cmp        x8, x9
0000152c  41010054          b.ne       #0x1554
00001530  ff434091          add        sp, sp, #0x10, lsl #12
00001534  ff430091          add        sp, sp, #0x10
00001538  f44f45a9          ldp        x20, x19, [sp, #0x50]
0000153c  f65744a9          ldp        x22, x21, [sp, #0x40]
00001540  f85f43a9          ldp        x24, x23, [sp, #0x30]
00001544  fa6742a9          ldp        x26, x25, [sp, #0x20]
00001548  fc6f41a9          ldp        x28, x27, [sp, #0x10]
0000154c  fd7bc6a8          ldp        x29, x30, [sp], #0x60
00001550  c0035fd6          ret        
00001554  c3050094          bl         #0x2c60
