# APK member: lib/armeabi-v7a/libnopensl.so
# Library SHA-256: 84027a5e2c443984c6607df964e90cb12f7218ac735e77863d230b3995694b1f
# Function: Java_sofeh_audio_NOpenSL_Start; address=0xf80; bytes=1416
# Decoder mode: thumb; decoded_bytes=1416
00000f80  f0b5              push       {r4, r5, r6, r7, lr}
00000f82  03af              add        r7, sp, #0xc
00000f84  2de9000f          push.w     {r8, sb, sl, fp}
00000f88  afb0              sub        sp, #0xbc
00000f8a  8246              mov        sl, r0
00000f8c  dff83c05          ldr.w      r0, [pc, #0x53c]
00000f90  1c46              mov        r4, r3
00000f92  1546              mov        r5, r2
00000f94  7844              add        r0, pc
00000f96  0068              ldr        r0, [r0]
00000f98  0068              ldr        r0, [r0]
00000f9a  2e90              str        r0, [sp, #0xb8]
00000f9c  da4e              ldr        r6, [pc, #0x368]
00000f9e  7e44              add        r6, pc
00000fa0  06f12809          add.w      sb, r6, #0x28
00000fa4  4846              mov        r0, sb
00000fa6  00f00cee          blx        #0x1bc0
00000faa  0123              movs       r3, #1
00000fac  b5fa85f0          clz        r0, r5
00000fb0  f361              str        r3, [r6, #0x1c]
00000fb2  ae46              mov        lr, r5
00000fb4  4109              lsrs       r1, r0, #5
00000fb6  f06a              ldr        r0, [r6, #0x2c]
00000fb8  3d69              ldr        r5, [r7, #0x10]
00000fba  d7e902c8          ldrd       ip, r8, [r7, #8]
00000fbe  98b1              cbz        r0, #0xfe8
00000fc0  7278              ldrb       r2, [r6, #1]
00000fc2  8a42              cmp        r2, r1
00000fc4  04bf              itt        eq
00000fc6  326b              ldreq      r2, [r6, #0x30]
00000fc8  a242              cmpeq      r2, r4
00000fca  0dd1              bne        #0xfe8
00000fcc  726b              ldr        r2, [r6, #0x34]
00000fce  6245              cmp        r2, ip
00000fd0  04bf              itt        eq
00000fd2  b26b              ldreq      r2, [r6, #0x38]
00000fd4  4245              cmpeq      r2, r8
00000fd6  07d1              bne        #0xfe8
00000fd8  2a46              mov        r2, r5
00000fda  002d              cmp        r5, #0
00000fdc  18bf              it         ne
00000fde  0122              movne      r2, #1
00000fe0  f36b              ldr        r3, [r6, #0x3c]
00000fe2  9b1a              subs       r3, r3, r2
00000fe4  18bf              it         ne
00000fe6  0123              movne      r3, #1
00000fe8  7170              strb       r1, [r6, #1]
00000fea  002d              cmp        r5, #0
00000fec  2946              mov        r1, r5
00000fee  0793              str        r3, [sp, #0x1c]
00000ff0  18bf              it         ne
00000ff2  0121              movne      r1, #1
00000ff4  0494              str        r4, [sp, #0x10]
00000ff6  c6e90c4c          strd       r4, ip, [r6, #0x30]
00000ffa  b346              mov        fp, r6
00000ffc  7446              mov        r4, lr
00000ffe  c6e90e81          strd       r8, r1, [r6, #0x38]
00001002  b8b3              cbz        r0, #0x1074
00001004  0798              ldr        r0, [sp, #0x1c]
00001006  a8b3              cbz        r0, #0x1074
00001008  0320              movs       r0, #3
0000100a  f061              str        r0, [r6, #0x1c]
0000100c  0020              movs       r0, #0
0000100e  b070              strb       r0, [r6, #2]
00001010  306c              ldr        r0, [r6, #0x40]
00001012  18b1              cbz        r0, #0x101c
00001014  0168              ldr        r1, [r0]
00001016  0a68              ldr        r2, [r1]
00001018  0121              movs       r1, #1
0000101a  9047              blx        r2
0000101c  706c              ldr        r0, [r6, #0x44]
0000101e  10b1              cbz        r0, #0x1026
00001020  0168              ldr        r1, [r0]
00001022  4968              ldr        r1, [r1, #4]
00001024  8847              blx        r1
00001026  b06c              ldr        r0, [r6, #0x48]
00001028  18b1              cbz        r0, #0x1032
0000102a  0168              ldr        r1, [r0]
0000102c  0a68              ldr        r2, [r1]
0000102e  0121              movs       r1, #1
00001030  9047              blx        r2
00001032  706a              ldr        r0, [r6, #0x24]
00001034  10b1              cbz        r0, #0x103c
00001036  0168              ldr        r1, [r0]
00001038  4968              ldr        r1, [r1, #4]
0000103a  8847              blx        r1
0000103c  4846              mov        r0, sb
0000103e  00f0c8ed          blx        #0x1bd0
00001042  0026              movs       r6, #0
00001044  5846              mov        r0, fp
00001046  dbf84c00          ldr.w      r0, [fp, #0x4c]
0000104a  0128              cmp        r0, #1
0000104c  08db              blt        #0x1060
0000104e  f008              lsrs       r0, r6, #3
00001050  7c28              cmp        r0, #0x7c
00001052  05d8              bhi        #0x1060
00001054  4ff47a70          mov.w      r0, #0x3e8
00001058  00f08aed          blx        #0x1b70
0000105c  0136              adds       r6, #1
0000105e  f1e7              b          #0x1044
00001060  4846              mov        r0, sb
00001062  00f0aeed          blx        #0x1bc0
00001066  5046              mov        r0, sl
00001068  00f04efa          bl         #0x1508
0000106c  0020              movs       r0, #0
0000106e  5e46              mov        r6, fp
00001070  cbf85000          str.w      r0, [fp, #0x50]
00001074  716d              ldr        r1, [r6, #0x54]
00001076  31b1              cbz        r1, #0x1086
00001078  daf80000          ldr.w      r0, [sl]
0000107c  826d              ldr        r2, [r0, #0x58]
0000107e  5046              mov        r0, sl
00001080  9047              blx        r2
00001082  0020              movs       r0, #0
00001084  7065              str        r0, [r6, #0x54]
00001086  b16d              ldr        r1, [r6, #0x58]
00001088  cdf81890          str.w      sb, [sp, #0x18]
0000108c  31b1              cbz        r1, #0x109c
0000108e  daf80000          ldr.w      r0, [sl]
00001092  826d              ldr        r2, [r0, #0x58]
00001094  5046              mov        r0, sl
00001096  9047              blx        r2
00001098  0020              movs       r0, #0
0000109a  b065              str        r0, [r6, #0x58]
0000109c  4ff00009          mov.w      sb, #0
000010a0  cdf814a0          str.w      sl, [sp, #0x14]
000010a4  c6e91799          strd       sb, sb, [r6, #0x5c]
000010a8  4cb3              cbz        r4, #0x10fe
000010aa  059d              ldr        r5, [sp, #0x14]
000010ac  06f16001          add.w      r1, r6, #0x60
000010b0  2868              ldr        r0, [r5]
000010b2  d0f86c23          ldr.w      r2, [r0, #0x36c]
000010b6  2846              mov        r0, r5
000010b8  9047              blx        r2
000010ba  2868              ldr        r0, [r5]
000010bc  2146              mov        r1, r4
000010be  426d              ldr        r2, [r0, #0x54]
000010c0  2846              mov        r0, r5
000010c2  9047              blx        r2
000010c4  7065              str        r0, [r6, #0x54]
000010c6  2146              mov        r1, r4
000010c8  2868              ldr        r0, [r5]
000010ca  c26f              ldr        r2, [r0, #0x7c]
000010cc  2846              mov        r0, r5
000010ce  9047              blx        r2
000010d0  0446              mov        r4, r0
000010d2  2868              ldr        r0, [r5]
000010d4  2146              mov        r1, r4
000010d6  426d              ldr        r2, [r0, #0x54]
000010d8  2846              mov        r0, r5
000010da  9047              blx        r2
000010dc  b065              str        r0, [r6, #0x58]
000010de  2146              mov        r1, r4
000010e0  2868              ldr        r0, [r5]
000010e2  c26d              ldr        r2, [r0, #0x5c]
000010e4  2846              mov        r0, r5
000010e6  9047              blx        r2
000010e8  2868              ldr        r0, [r5]
000010ea  b16d              ldr        r1, [r6, #0x58]
000010ec  d0f88440          ldr.w      r4, [r0, #0x84]
000010f0  2846              mov        r0, r5
000010f2  864a              ldr        r2, [pc, #0x218]
000010f4  864b              ldr        r3, [pc, #0x218]
000010f6  7a44              add        r2, pc
000010f8  7b44              add        r3, pc
000010fa  a047              blx        r4
000010fc  f065              str        r0, [r6, #0x5c]
000010fe  ba68              ldr        r2, [r7, #8]
00001100  0120              movs       r0, #1
00001102  c6f81490          str.w      sb, [r6, #0x14]
00001106  4ff48041          mov.w      r1, #0x4000
0000110a  c6f81890          str.w      sb, [r6, #0x18]
0000110e  c6f82090          str.w      sb, [r6, #0x20]
00001112  3070              strb       r0, [r6]
00001114  5000              lsls       r0, r2, #1
00001116  b0f5804f          cmp.w      r0, #0x4000
0000111a  c6e90199          strd       sb, sb, [r6, #4]
0000111e  b8bf              it         lt
00001120  5100              lsllt      r1, r2, #1
00001122  3161              str        r1, [r6, #0x10]
00001124  b8f1030f          cmp.w      r8, #3
00001128  d8bf              it         le
0000112a  4ff00308          movle.w    r8, #3
0000112e  b8f1100f          cmp.w      r8, #0x10
00001132  28bf              it         hs
00001134  4ff01008          movhs.w    r8, #0x10
00001138  da48              ldr        r0, [pc, #0x368]
0000113a  4c00              lsls       r4, r1, #1
0000113c  7844              add        r0, pc
0000113e  c0f80080          str.w      r8, [r0]
00001142  8246              mov        sl, r0
00001144  c6f86490          str.w      sb, [r6, #0x64]
00001148  e14d              ldr        r5, [pc, #0x384]
0000114a  e24e              ldr        r6, [pc, #0x388]
0000114c  7d44              add        r5, pc
0000114e  7e44              add        r6, pc
00001150  b9f5002f          cmp.w      sb, #0x80000
00001154  0cd0              beq        #0x1170
00001156  05eb0900          add.w      r0, r5, sb
0000115a  2146              mov        r1, r4
0000115c  00f040ed          blx        #0x1be0
00001160  06eb0900          add.w      r0, r6, sb
00001164  2146              mov        r1, r4
00001166  00f03ced          blx        #0x1be0
0000116a  09f50049          add.w      sb, sb, #0x8000
0000116e  efe7              b          #0x1150
00001170  dbf82c00          ldr.w      r0, [fp, #0x2c]
00001174  5e46              mov        r6, fp
00001176  079a              ldr        r2, [sp, #0x1c]
00001178  b0fa80f1          clz        r1, r0
0000117c  4909              lsrs       r1, r1, #5
0000117e  1143              orrs       r1, r2
00001180  00f08580          beq.w      #0x128e
00001184  0028              cmp        r0, #0
00001186  00f0a480          beq.w      #0x12d2
0000118a  cf48              ldr        r0, [pc, #0x33c]
0000118c  3546              mov        r5, r6
0000118e  2690              str        r0, [sp, #0x98]
00001190  c5a0              adr        r0, #0x314
00001192  cdf89c80          str.w      r8, [sp, #0x9c]
00001196  60f9cf0a          vld1.64    {d16, d17}, [r0]
0000119a  1fa8              add        r0, sp, #0x7c
0000119c  00f10c01          add.w      r1, r0, #0xc
000011a0  049a              ldr        r2, [sp, #0x10]
000011a2  41f98f0a          vst1.32    {d16, d17}, [r1]
000011a6  4ff47a71          mov.w      r1, #0x3e8
000011aa  02fb01f8          mul        r8, r2, r1
000011ae  0221              movs       r1, #2
000011b0  2091              str        r1, [sp, #0x80]
000011b2  cde91e01          strd       r0, r1, [sp, #0x78]
000011b6  cdf88480          str.w      r8, [sp, #0x84]
000011ba  26a8              add        r0, sp, #0x98
000011bc  1d90              str        r0, [sp, #0x74]
000011be  0420              movs       r0, #4
000011c0  1b90              str        r0, [sp, #0x6c]
000011c2  f06e              ldr        r0, [r6, #0x6c]
000011c4  1c90              str        r0, [sp, #0x70]
000011c6  0020              movs       r0, #0
000011c8  1a90              str        r0, [sp, #0x68]
000011ca  1ba8              add        r0, sp, #0x6c
000011cc  1990              str        r0, [sp, #0x64]
000011ce  c448              ldr        r0, [pc, #0x310]
000011d0  7844              add        r0, pc
000011d2  0068              ldr        r0, [r0]
000011d4  d0f80090          ldr.w      sb, [r0]
000011d8  cdf8b490          str.w      sb, [sp, #0xb4]
000011dc  c148              ldr        r0, [pc, #0x304]
000011de  7844              add        r0, pc
000011e0  0068              ldr        r0, [r0]
000011e2  0468              ldr        r4, [r0]
000011e4  0120              movs       r0, #1
000011e6  cde92a00          strd       r0, r0, [sp, #0xa8]
000011ea  b06e              ldr        r0, [r6, #0x68]
000011ec  2c94              str        r4, [sp, #0xb0]
000011ee  0268              ldr        r2, [r0]
000011f0  9668              ldr        r6, [r2, #8]
000011f2  2aaa              add        r2, sp, #0xa8
000011f4  2cab              add        r3, sp, #0xb0
000011f6  0292              str        r2, [sp, #8]
000011f8  cde90013          strd       r1, r3, [sp]
000011fc  1daa              add        r2, sp, #0x74
000011fe  05f10c01          add.w      r1, r5, #0xc
00001202  19ab              add        r3, sp, #0x64
00001204  b047              blx        r6
00001206  d0bb              cbnz       r0, #0x127e
00001208  e868              ldr        r0, [r5, #0xc]
0000120a  0168              ldr        r1, [r0]
0000120c  0a68              ldr        r2, [r1]
0000120e  0021              movs       r1, #0
00001210  9047              blx        r2
00001212  a0bb              cbnz       r0, #0x127e
00001214  e868              ldr        r0, [r5, #0xc]
00001216  05f14002          add.w      r2, r5, #0x40
0000121a  2e46              mov        r6, r5
0000121c  0168              ldr        r1, [r0]
0000121e  cb68              ldr        r3, [r1, #0xc]
00001220  b149              ldr        r1, [pc, #0x2c4]
00001222  7944              add        r1, pc
00001224  0968              ldr        r1, [r1]
00001226  0968              ldr        r1, [r1]
00001228  9847              blx        r3
0000122a  40bb              cbnz       r0, #0x127e
0000122c  f068              ldr        r0, [r6, #0xc]
0000122e  06f14402          add.w      r2, r6, #0x44
00001232  0168              ldr        r1, [r0]
00001234  cb68              ldr        r3, [r1, #0xc]
00001236  2146              mov        r1, r4
00001238  9847              blx        r3
0000123a  00bb              cbnz       r0, #0x127e
0000123c  d6e91734          ldrd       r3, r4, [r6, #0x5c]
00001240  3546              mov        r5, r6
00001242  706c              ldr        r0, [r6, #0x44]
00001244  726d              ldr        r2, [r6, #0x54]
00001246  002b              cmp        r3, #0
00001248  a849              ldr        r1, [pc, #0x2a0]
0000124a  a94e              ldr        r6, [pc, #0x2a4]
0000124c  7944              add        r1, pc
0000124e  7e44              add        r6, pc
00001250  08bf              it         eq
00001252  3146              moveq      r1, r6
00001254  002a              cmp        r2, #0
00001256  08bf              it         eq
00001258  3146              moveq      r1, r6
0000125a  002c              cmp        r4, #0
0000125c  08bf              it         eq
0000125e  3146              moveq      r1, r6
00001260  0268              ldr        r2, [r0]
00001262  d368              ldr        r3, [r2, #0xc]
00001264  0022              movs       r2, #0
00001266  9847              blx        r3
00001268  48b9              cbnz       r0, #0x127e
0000126a  e868              ldr        r0, [r5, #0xc]
0000126c  05f17002          add.w      r2, r5, #0x70
00001270  0168              ldr        r1, [r0]
00001272  cb68              ldr        r3, [r1, #0xc]
00001274  4946              mov        r1, sb
00001276  9847              blx        r3
00001278  0028              cmp        r0, #0
0000127a  00f08c80          beq.w      #0x1396
0000127e  0598              ldr        r0, [sp, #0x14]
00001280  00f042f9          bl         #0x1508
00001284  0698              ldr        r0, [sp, #0x18]
00001286  00f0a4ec          blx        #0x1bd0
0000128a  0024              movs       r4, #0
0000128c  69e0              b          #0x1362
0000128e  706c              ldr        r0, [r6, #0x44]
00001290  e8b1              cbz        r0, #0x12ce
00001292  316c              ldr        r1, [r6, #0x40]
00001294  d9b1              cbz        r1, #0x12ce
00001296  0168              ldr        r1, [r0]
00001298  4968              ldr        r1, [r1, #4]
0000129a  8847              blx        r1
0000129c  8e49              ldr        r1, [pc, #0x238]
0000129e  4ff0ff34          mov.w      r4, #-1
000012a2  069e              ldr        r6, [sp, #0x18]
000012a4  7944              add        r1, pc
000012a6  5046              mov        r0, sl
000012a8  0134              adds       r4, #1
000012aa  daf80000          ldr.w      r0, [sl]
000012ae  8442              cmp        r4, r0
000012b0  1dda              bge        #0x12ee
000012b2  dbf84400          ldr.w      r0, [fp, #0x44]
000012b6  01f50045          add.w      r5, r1, #0x8000
000012ba  dbf81020          ldr.w      r2, [fp, #0x10]
000012be  0368              ldr        r3, [r0]
000012c0  5200              lsls       r2, r2, #1
000012c2  1b68              ldr        r3, [r3]
000012c4  9847              blx        r3
000012c6  0028              cmp        r0, #0
000012c8  2946              mov        r1, r5
000012ca  ecd0              beq        #0x12a6
000012cc  17e0              b          #0x12fe
000012ce  0024              movs       r4, #0
000012d0  0ae0              b          #0x12e8
000012d2  06f12c00          add.w      r0, r6, #0x2c
000012d6  0024              movs       r4, #0
000012d8  0021              movs       r1, #0
000012da  0022              movs       r2, #0
000012dc  0023              movs       r3, #0
000012de  cde90044          strd       r4, r4, [sp]
000012e2  00f086ec          blx        #0x1bf0
000012e6  a8b1              cbz        r0, #0x1314
000012e8  f461              str        r4, [r6, #0x1c]
000012ea  0698              ldr        r0, [sp, #0x18]
000012ec  37e0              b          #0x135e
000012ee  dbf84000          ldr.w      r0, [fp, #0x40]
000012f2  0168              ldr        r1, [r0]
000012f4  0a68              ldr        r2, [r1]
000012f6  0321              movs       r1, #3
000012f8  9047              blx        r2
000012fa  0028              cmp        r0, #0
000012fc  3fd0              beq        #0x137e
000012fe  0024              movs       r4, #0
00001300  3046              mov        r0, r6
00001302  cbf81c40          str.w      r4, [fp, #0x1c]
00001306  2ae0              b          #0x135e
00001308  9ead              add        r5, sp, #0x278
0000130a  1000              movs       r0, r2
0000130c  faf7ffff          bl         #0xffffc30e
00001310  f4f7ffff          bl         #0xffff6312
00001314  f06a              ldr        r0, [r6, #0x2c]
00001316  0168              ldr        r1, [r0]
00001318  0a68              ldr        r2, [r1]
0000131a  0021              movs       r1, #0
0000131c  9047              blx        r2
0000131e  069d              ldr        r5, [sp, #0x18]
00001320  d8b9              cbnz       r0, #0x135a
00001322  f06a              ldr        r0, [r6, #0x2c]
00001324  06f16802          add.w      r2, r6, #0x68
00001328  0168              ldr        r1, [r0]
0000132a  cb68              ldr        r3, [r1, #0xc]
0000132c  6b49              ldr        r1, [pc, #0x1ac]
0000132e  7944              add        r1, pc
00001330  0968              ldr        r1, [r1]
00001332  0968              ldr        r1, [r1]
00001334  9847              blx        r3
00001336  80b9              cbnz       r0, #0x135a
00001338  b06e              ldr        r0, [r6, #0x68]
0000133a  0022              movs       r2, #0
0000133c  0023              movs       r3, #0
0000133e  0168              ldr        r1, [r0]
00001340  d1f81cc0          ldr.w      ip, [r1, #0x1c]
00001344  06f16c01          add.w      r1, r6, #0x6c
00001348  0094              str        r4, [sp]
0000134a  e047              blx        ip
0000134c  28b9              cbnz       r0, #0x135a
0000134e  f06e              ldr        r0, [r6, #0x6c]
00001350  0168              ldr        r1, [r0]
00001352  0a68              ldr        r2, [r1]
00001354  0021              movs       r1, #0
00001356  9047              blx        r2
00001358  c8b1              cbz        r0, #0x138e
0000135a  2846              mov        r0, r5
0000135c  f461              str        r4, [r6, #0x1c]
0000135e  00f038ec          blx        #0x1bd0
00001362  2e98              ldr        r0, [sp, #0xb8]
00001364  6749              ldr        r1, [pc, #0x19c]
00001366  7944              add        r1, pc
00001368  0968              ldr        r1, [r1]
0000136a  0968              ldr        r1, [r1]
0000136c  8142              cmp        r1, r0
0000136e  01bf              itttt      eq
00001370  2046              moveq      r0, r4
00001372  2fb0              addeq      sp, #0xbc
00001374  bde8000f          popeq.w    {r8, sb, sl, fp}
00001378  f0bd              popeq      {r4, r5, r6, r7, pc}
0000137a  00f00aec          blx        #0x1b90
0000137e  0220              movs       r0, #2
00001380  cbf81c00          str.w      r0, [fp, #0x1c]
00001384  3046              mov        r0, r6
00001386  00f024ec          blx        #0x1bd0
0000138a  0124              movs       r4, #1
0000138c  e9e7              b          #0x1362
0000138e  daf80080          ldr.w      r8, [sl]
00001392  5046              mov        r0, sl
00001394  f9e6              b          #0x118a
00001396  5749              ldr        r1, [pc, #0x15c]
00001398  4ff0ff34          mov.w      r4, #-1
0000139c  dde90559          ldrd       r5, sb, [sp, #0x14]
000013a0  7944              add        r1, pc
000013a2  5046              mov        r0, sl
000013a4  0134              adds       r4, #1
000013a6  daf80000          ldr.w      r0, [sl]
000013aa  8442              cmp        r4, r0
000013ac  0dda              bge        #0x13ca
000013ae  dbf84400          ldr.w      r0, [fp, #0x44]
000013b2  01f50046          add.w      r6, r1, #0x8000
000013b6  dbf81020          ldr.w      r2, [fp, #0x10]
000013ba  0368              ldr        r3, [r0]
000013bc  5200              lsls       r2, r2, #1
000013be  1b68              ldr        r3, [r3]
000013c0  9847              blx        r3
000013c2  0028              cmp        r0, #0
000013c4  3146              mov        r1, r6
000013c6  ecd0              beq        #0x13a2
000013c8  06e0              b          #0x13d8
000013ca  dbf84000          ldr.w      r0, [fp, #0x40]
000013ce  0168              ldr        r1, [r0]
000013d0  0a68              ldr        r2, [r1]
000013d2  0321              movs       r1, #3
000013d4  9047              blx        r2
000013d6  20b1              cbz        r0, #0x13e2
000013d8  2846              mov        r0, r5
000013da  00f095f8          bl         #0x1508
000013de  4846              mov        r0, sb
000013e0  51e7              b          #0x1286
000013e2  3869              ldr        r0, [r7, #0x10]
000013e4  0028              cmp        r0, #0
000013e6  54d0              beq        #0x1492
000013e8  3748              ldr        r0, [pc, #0xdc]
000013ea  0222              movs       r2, #2
000013ec  1790              str        r0, [sp, #0x5c]
000013ee  daf80000          ldr.w      r0, [sl]
000013f2  1890              str        r0, [sp, #0x60]
000013f4  30a0              adr        r0, #0xc0
000013f6  60f9cf0a          vld1.64    {d16, d17}, [r0]
000013fa  10a8              add        r0, sp, #0x40
000013fc  00f10c01          add.w      r1, r0, #0xc
00001400  cde90f02          strd       r0, r2, [sp, #0x3c]
00001404  0022              movs       r2, #0
00001406  41f98f0a          vst1.32    {d16, d17}, [r1]
0000140a  0121              movs       r1, #1
0000140c  cdf84880          str.w      r8, [sp, #0x48]
00001410  1191              str        r1, [sp, #0x44]
00001412  17a8              add        r0, sp, #0x5c
00001414  0e90              str        r0, [sp, #0x38]
00001416  3848              ldr        r0, [pc, #0xe0]
00001418  7844              add        r0, pc
0000141a  60f9cf0a          vld1.64    {d16, d17}, [r0]
0000141e  0aa8              add        r0, sp, #0x28
00001420  40f9cf0a          vst1.64    {d16, d17}, [r0]
00001424  cde90802          strd       r0, r2, [sp, #0x20]
00001428  3448              ldr        r0, [pc, #0xd0]
0000142a  7844              add        r0, pc
0000142c  0068              ldr        r0, [r0]
0000142e  0468              ldr        r4, [r0]
00001430  dbf86800          ldr.w      r0, [fp, #0x68]
00001434  cde92814          strd       r1, r4, [sp, #0xa0]
00001438  0268              ldr        r2, [r0]
0000143a  d668              ldr        r6, [r2, #0xc]
0000143c  28aa              add        r2, sp, #0xa0
0000143e  29ab              add        r3, sp, #0xa4
00001440  0292              str        r2, [sp, #8]
00001442  cde90013          strd       r1, r3, [sp]
00001446  08aa              add        r2, sp, #0x20
00001448  0bf17401          add.w      r1, fp, #0x74
0000144c  0eab              add        r3, sp, #0x38
0000144e  b047              blx        r6
00001450  0028              cmp        r0, #0
00001452  c1d1              bne        #0x13d8
00001454  dbf87400          ldr.w      r0, [fp, #0x74]
00001458  0168              ldr        r1, [r0]
0000145a  0a68              ldr        r2, [r1]
0000145c  0021              movs       r1, #0
0000145e  9047              blx        r2
00001460  0028              cmp        r0, #0
00001462  b9d1              bne        #0x13d8
00001464  dbf87400          ldr.w      r0, [fp, #0x74]
00001468  0bf14802          add.w      r2, fp, #0x48
0000146c  0168              ldr        r1, [r0]
0000146e  cb68              ldr        r3, [r1, #0xc]
00001470  2349              ldr        r1, [pc, #0x8c]
00001472  7944              add        r1, pc
00001474  0968              ldr        r1, [r1]
00001476  0968              ldr        r1, [r1]
00001478  9847              blx        r3
0000147a  0028              cmp        r0, #0
0000147c  acd1              bne        #0x13d8
0000147e  dbf87400          ldr.w      r0, [fp, #0x74]
00001482  0bf12402          add.w      r2, fp, #0x24
00001486  0168              ldr        r1, [r0]
00001488  cb68              ldr        r3, [r1, #0xc]
0000148a  2146              mov        r1, r4
0000148c  9847              blx        r3
0000148e  0028              cmp        r0, #0
00001490  a2d1              bne        #0x13d8
00001492  0220              movs       r0, #2
00001494  cbf81c00          str.w      r0, [fp, #0x1c]
00001498  0020              movs       r0, #0
0000149a  cbf84c00          str.w      r0, [fp, #0x4c]
0000149e  4846              mov        r0, sb
000014a0  71e7              b          #0x1386
000014a2  00bf              nop        
000014a4  fc2b              cmp        r3, #0xfc
000014a6  0000              movs       r0, r0
000014a8  1000              movs       r0, r2
000014aa  0000              movs       r0, r0
000014ac  1000              movs       r0, r2
000014ae  0000              movs       r0, r0
000014b0  0300              movs       r3, r0
000014b2  0000              movs       r0, r0
000014b4  0200              movs       r2, r0
000014b6  0000              movs       r0, r0
000014b8  1000              movs       r0, r2
000014ba  0000              movs       r0, r0
000014bc  1000              movs       r0, r2
000014be  0000              movs       r0, r0
000014c0  0400              movs       r4, r0
000014c2  0000              movs       r0, r0
000014c4  0200              movs       r2, r0
000014c6  0000              movs       r0, r0
000014c8  bd07              lsls       r5, r7, #0x1e
000014ca  0080              strh       r0, [r0]
000014cc  4c1d              adds       r4, r1, #5
000014ce  0000              movs       r0, r0
000014d0  f0ab              add        r3, sp, #0x3c0
000014d2  0000              movs       r0, r0
000014d4  eeab              add        r3, sp, #0x3b8
000014d6  0800              movs       r0, r1
000014d8  98aa              add        r2, sp, #0x260
000014da  0000              movs       r0, r0
000014dc  b619              adds       r6, r6, r6
000014de  0000              movs       r0, r0
000014e0  181b              subs       r0, r3, r4
000014e2  0000              movs       r0, r0
000014e4  0e1b              subs       r6, r1, r4
000014e6  0000              movs       r0, r0
000014e8  ce1a              subs       r6, r1, r3
000014ea  0000              movs       r0, r0
000014ec  3503              lsls       r5, r6, #0xc
000014ee  0000              movs       r0, r0
000014f0  fb04              lsls       r3, r7, #0x13
000014f2  0000              movs       r0, r0
000014f4  9ca9              add        r1, sp, #0x270
000014f6  0000              movs       r0, r0
000014f8  e4f4ffff          bl         #0xffce64fa
000014fc  ca18              adds       r2, r1, r3
000014fe  0000              movs       r0, r0
00001500  8618              adds       r6, r0, r2
00001502  0000              movs       r0, r0
00001504  7a19              adds       r2, r7, r5
00001506  0000              movs       r0, r0
