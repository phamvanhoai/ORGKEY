# APK member: lib/arm64-v8a/libnsf2.so
# Library SHA-256: 8ea5a2f1833868953a9b4a52525e2544eefc6e94c98e1e9a8c344040d51139df
# Function: Java_sofeh_music_NSF2_Voice; address=0x7838; bytes=764
# Decoder mode: default; decoded_bytes=764
00007838  fd7bbaa9          stp        x29, x30, [sp, #-0x60]!
0000783c  fc6f01a9          stp        x28, x27, [sp, #0x10]
00007840  fa6702a9          stp        x26, x25, [sp, #0x20]
00007844  f85f03a9          stp        x24, x23, [sp, #0x30]
00007848  f65704a9          stp        x22, x21, [sp, #0x40]
0000784c  f44f05a9          stp        x20, x19, [sp, #0x50]
00007850  fd030091          mov        x29, sp
00007854  ff4322d1          sub        sp, sp, #0x890
00007858  4ad03bd5          mrs        x10, tpidr_el0
0000785c  69640051          sub        w9, w3, #0x19
00007860  f6031f2a          mov        w22, wzr
00007864  481540f9          ldr        x8, [x10, #0x28]
00007868  3f190171          cmp        w9, #0x46
0000786c  a8031ff8          stur       x8, [x29, #-0x10]
00007870  68140054          b.hi       #0x7afc
00007874  550000b0          adrp       x21, #0x10000
00007878  a84e43f9          ldr        x8, [x21, #0x698]
0000787c  081400b4          cbz        x8, #0x7afc
00007880  e01300f9          str        x0, [sp, #0x20]
00007884  e00308aa          mov        x0, x8
00007888  f303032a          mov        w19, w3
0000788c  f403022a          mov        w20, w2
00007890  e42b01a9          stp        x4, x10, [sp, #0x10]
00007894  8b020094          bl         #0x82c0
00007898  c8ffffd0          adrp       x8, #0x1000
0000789c  a04e43f9          ldr        x0, [x21, #0x698]
000078a0  e103142a          mov        w1, w20
000078a4  00c142fd          ldr        d0, [x8, #0x580]
000078a8  e203132a          mov        w2, w19
000078ac  f44f0129          stp        w20, w19, [sp, #8]
000078b0  1f3000b9          str        wzr, [x0, #0x30]
000078b4  004003fc          stur       d0, [x0, #0x34]
000078b8  00102e1e          fmov       s0, #1.00000000
000078bc  91020094          bl         #0x8300
000078c0  aa4e43f9          ldr        x10, [x21, #0x698]
000078c4  1ce09f52          mov        w28, #0xff00
000078c8  f92f8052          mov        w25, #0x17f
000078cc  f30f8052          mov        w19, #0x7f
000078d0  fcdfa872          movk       w28, #0x46ff, lsl #16
000078d4  19f0b772          movk       w25, #0xbf80, lsl #16
000078d8  ea3b00f9          str        x10, [sp, #0x70]
000078dc  4a3140b9          ldr        w10, [x10, #0x30]
000078e0  13f0a772          movk       w19, #0x3f80, lsl #16
000078e4  eb030291          add        x11, sp, #0x80
000078e8  350f044e          dup        v21.4s, w25
000078ec  760e044e          dup        v22.4s, w19
000078f0  970f044e          dup        v23.4s, w28
000078f4  08408052          mov        w8, #0x200
000078f8  09208052          mov        w9, #0x100
000078fc  5f090071          cmp        w10, #2
00007900  6a810091          add        x10, x11, #0x20
00007904  14c09d52          mov        w20, #0xee00
00007908  1f2003d5          nop        
0000790c  b86c0410          adr        x24, #0x106a0
00007910  ea1700f9          str        x10, [sp, #0x28]
00007914  ea079f1a          cset       w10, ne
00007918  2811881a          csel       w8, w9, w8, ne
0000791c  5400a072          movk       w20, #2, lsl #16
00007920  fbff8f52          mov        w27, #0x7fff
00007924  faff8f12          mov        w26, #-0x8000
00007928  e82b0d29          stp        w8, w10, [sp, #0x68]
0000792c  f65702ad          stp        q22, q21, [sp, #0x40]
00007930  f70f803d          str        q23, [sp, #0x30]
00007934  05000014          b          #0x7948
00007938  e87f40b9          ldr        w8, [sp, #0x7c]
0000793c  f80316aa          mov        x24, x22
00007940  9402086b          subs       w20, w20, w8
00007944  2d0a0054          b.le       #0x7a88
00007948  e9234d29          ldp        w9, w8, [sp, #0x68]
0000794c  e03b40f9          ldr        x0, [sp, #0x70]
00007950  e1030291          add        x1, sp, #0x80
00007954  e3031f2a          mov        w3, wzr
00007958  f5030291          add        x21, sp, #0x80
0000795c  9f02096b          cmp        w20, w9
00007960  82b2891a          csel       w2, w20, w9, lt
00007964  5720c81a          lsl        w23, w2, w8
00007968  e27f00b9          str        w2, [sp, #0x7c]
0000796c  1647378b          add        x22, x24, w23, uxtw #1
00007970  78020094          bl         #0x8350
00007974  e80600d1          sub        x8, x23, #1
00007978  08f94092          and        x8, x8, #0x7fffffffffffffff
0000797c  1f3d00f1          cmp        x8, #0xf
00007980  62000054          b.hs       #0x798c
00007984  e80318aa          mov        x8, x24
00007988  32000014          b          #0x7a50
0000798c  09050091          add        x9, x8, #1
00007990  14a4044f          movi       v20.8h, #0x80, lsl #8
00007994  eb030291          add        x11, sp, #0x80
00007998  2aed7c92          and        x10, x9, #0xfffffffffffffff0
0000799c  f65742ad          ldp        q22, q21, [sp, #0x40]
000079a0  08070a8b          add        x8, x24, x10, lsl #1
000079a4  75090a8b          add        x21, x11, x10, lsl #2
000079a8  ed1740f9          ldr        x13, [sp, #0x28]
000079ac  f70fc03d          ldr        q23, [sp, #0x30]
000079b0  0b430091          add        x11, x24, #0x10
000079b4  ec030aaa          mov        x12, x10
000079b8  a0057fad          ldp        q0, q1, [x13, #-0x20]
000079bc  8c4100f1          subs       x12, x12, #0x10
000079c0  a20dc2ac          ldp        q2, q3, [x13], #0x40
000079c4  04dc376e          fmul       v4.4s, v0.4s, v23.4s
000079c8  25dc376e          fmul       v5.4s, v1.4s, v23.4s
000079cc  30e4b66e          fcmgt      v16.4s, v1.4s, v22.4s
000079d0  46dc376e          fmul       v6.4s, v2.4s, v23.4s
000079d4  67dc376e          fmul       v7.4s, v3.4s, v23.4s
000079d8  11e4b66e          fcmgt      v17.4s, v0.4s, v22.4s
000079dc  72e4b66e          fcmgt      v18.4s, v3.4s, v22.4s
000079e0  53e4b66e          fcmgt      v19.4s, v2.4s, v22.4s
000079e4  a1e6a16e          fcmgt      v1.4s, v21.4s, v1.4s
000079e8  a0e6a06e          fcmgt      v0.4s, v21.4s, v0.4s
000079ec  a3e6a36e          fcmgt      v3.4s, v21.4s, v3.4s
000079f0  a2e6a26e          fcmgt      v2.4s, v21.4s, v2.4s
000079f4  a5b8a14e          fcvtzs     v5.4s, v5.4s
000079f8  84b8a14e          fcvtzs     v4.4s, v4.4s
000079fc  e7b8a14e          fcvtzs     v7.4s, v7.4s
00007a00  c6b8a14e          fcvtzs     v6.4s, v6.4s
00007a04  301a504e          uzp1       v16.8h, v17.8h, v16.8h
00007a08  0018414e          uzp1       v0.8h, v0.8h, v1.8h
00007a0c  4118434e          uzp1       v1.8h, v2.8h, v3.8h
00007a10  8418454e          uzp1       v4.8h, v4.8h, v5.8h
00007a14  651a524e          uzp1       v5.8h, v19.8h, v18.8h
00007a18  c618474e          uzp1       v6.8h, v6.8h, v7.8h
00007a1c  841c704e          bic        v4.16b, v4.16b, v16.16b
00007a20  10b4046f          bic        v16.8h, #0x80, lsl #8
00007a24  c61c654e          bic        v6.16b, v6.16b, v5.16b
00007a28  05b4046f          bic        v5.8h, #0x80, lsl #8
00007a2c  021ea44e          orr        v2.16b, v16.16b, v4.16b
00007a30  a31ca64e          orr        v3.16b, v5.16b, v6.16b
00007a34  801e626e          bsl        v0.16b, v20.16b, v2.16b
00007a38  811e636e          bsl        v1.16b, v20.16b, v3.16b
00007a3c  60853fad          stp        q0, q1, [x11, #-0x10]
00007a40  6b810091          add        x11, x11, #0x20
00007a44  a1fbff54          b.ne       #0x79b8
00007a48  3f010aeb          cmp        x9, x10
00007a4c  60f7ff54          b.eq       #0x7938
00007a50  8103271e          fmov       s1, w28
00007a54  a04640bc          ldr        s0, [x21], #4
00007a58  0108211e          fmul       s1, s0, s1
00007a5c  2900381e          fcvtzs     w9, s1
00007a60  6102271e          fmov       s1, w19
00007a64  0020211e          fcmp       s0, s1
00007a68  2103271e          fmov       s1, w25
00007a6c  69c3891a          csel       w9, w27, w9, gt
00007a70  0020211e          fcmp       s0, s1
00007a74  4943891a          csel       w9, w26, w9, mi
00007a78  09250078          strh       w9, [x8], #2
00007a7c  1f0116eb          cmp        x8, x22
00007a80  81feff54          b.ne       #0x7a50
00007a84  adffff17          b          #0x7938
00007a88  480000b0          adrp       x8, #0x10000
00007a8c  e10b4129          ldp        w1, w2, [sp, #8]
00007a90  004d43f9          ldr        x0, [x8, #0x698]
00007a94  1f020094          bl         #0x8310
00007a98  0a809b52          mov        w10, #0xdc00
00007a9c  ebbf9d92          mov        x11, #-0xee00
00007aa0  28060090          adrp       x8, #0xcb000
00007aa4  08793a91          add        x8, x8, #0xe9e
00007aa8  aa00a072          movk       w10, #5, lsl #16
00007aac  abffbff2          movk       x11, #0xfffd, lsl #16
00007ab0  e9030baa          mov        x9, x11
00007ab4  e3030a2a          mov        w3, w10
00007ab8  eb0000b4          cbz        x11, #0x7ad4
00007abc  0ae15f78          ldurh      w10, [x8, #-2]
00007ac0  aa000035          cbnz       w10, #0x7ad4
00007ac4  0cc55f78          ldrh       w12, [x8], #0xfffffffffffffffc
00007ac8  6a080051          sub        w10, w3, #2
00007acc  2b050091          add        x11, x9, #1
00007ad0  0cffff34          cbz        w12, #0x7ab0
00007ad4  e01340f9          ldr        x0, [sp, #0x20]
00007ad8  e10b40f9          ldr        x1, [sp, #0x10]
00007adc  1f2003d5          nop        
00007ae0  045e0410          adr        x4, #0x106a0
00007ae4  e2031f2a          mov        w2, wzr
00007ae8  f603094b          neg        w22, w9
00007aec  080040f9          ldr        x8, [x0]
00007af0  084943f9          ldr        x8, [x8, #0x690]
00007af4  00013fd6          blr        x8
00007af8  ea0f40f9          ldr        x10, [sp, #0x18]
00007afc  481540f9          ldr        x8, [x10, #0x28]
00007b00  a9035ff8          ldur       x9, [x29, #-0x10]
00007b04  1f0109eb          cmp        x8, x9
00007b08  41010054          b.ne       #0x7b30
00007b0c  e003162a          mov        w0, w22
00007b10  ff432291          add        sp, sp, #0x890
00007b14  f44f45a9          ldp        x20, x19, [sp, #0x50]
00007b18  f65744a9          ldp        x22, x21, [sp, #0x40]
00007b1c  f85f43a9          ldp        x24, x23, [sp, #0x30]
00007b20  fa6742a9          ldp        x26, x25, [sp, #0x20]
00007b24  fc6f41a9          ldp        x28, x27, [sp, #0x10]
00007b28  fd7bc6a8          ldp        x29, x30, [sp], #0x60
00007b2c  c0035fd6          ret        
00007b30  bc010094          bl         #0x8220
