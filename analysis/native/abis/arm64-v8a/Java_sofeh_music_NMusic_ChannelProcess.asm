# APK member: lib/arm64-v8a/libnmusic.so
# Library SHA-256: 282be49ee24cb3822cc941fb9003d9259c93cd6ea56238426f4f24b018d78156
# Function: Java_sofeh_music_NMusic_ChannelProcess; address=0x248c; bytes=2816
# Decoder mode: default; decoded_bytes=2816
0000248c  ff0304d1          sub        sp, sp, #0x100
00002490  ed33076d          stp        d13, d12, [sp, #0x70]
00002494  eb2b086d          stp        d11, d10, [sp, #0x80]
00002498  e923096d          stp        d9, d8, [sp, #0x90]
0000249c  fd7b0aa9          stp        x29, x30, [sp, #0xa0]
000024a0  fc6f0ba9          stp        x28, x27, [sp, #0xb0]
000024a4  fa670ca9          stp        x26, x25, [sp, #0xc0]
000024a8  f85f0da9          stp        x24, x23, [sp, #0xd0]
000024ac  f6570ea9          stp        x22, x21, [sp, #0xe0]
000024b0  f44f0fa9          stp        x20, x19, [sp, #0xf0]
000024b4  fd830291          add        x29, sp, #0xa0
000024b8  a98340b9          ldr        w9, [x29, #0x80]
000024bc  a88b40b9          ldr        w8, [x29, #0x88]
000024c0  e10304aa          mov        x1, x4
000024c4  b86b40b9          ldr        w24, [x29, #0x68]
000024c8  f90307aa          mov        x25, x7
000024cc  fc0306aa          mov        x28, x6
000024d0  e92f00b9          str        w9, [sp, #0x2c]
000024d4  a9e34139          ldrb       w9, [x29, #0x78]
000024d8  f70305aa          mov        x23, x5
000024dc  e83f00b9          str        w8, [sp, #0x3c]
000024e0  080040f9          ldr        x8, [x0]
000024e4  f40300aa          mov        x20, x0
000024e8  e93b00b9          str        w9, [sp, #0x38]
000024ec  a97340b9          ldr        w9, [x29, #0x70]
000024f0  f30304aa          mov        x19, x4
000024f4  087d40f9          ldr        x8, [x8, #0xf8]
000024f8  f603032a          mov        w22, w3
000024fc  e20700f9          str        x2, [sp, #8]
00002500  e91b00f9          str        x9, [sp, #0x30]
00002504  a96340b9          ldr        w9, [x29, #0x60]
00002508  e92300f9          str        x9, [sp, #0x40]
0000250c  00013fd6          blr        x8
00002510  880240f9          ldr        x8, [x20]
00002514  f50300aa          mov        x21, x0
00002518  1f2003d5          nop        
0000251c  a242ff10          adr        x2, #0xd70
00002520  e3ffffd0          adrp       x3, #0
00002524  63303791          add        x3, x3, #0xdcc
00002528  087941f9          ldr        x8, [x8, #0x2f0]
0000252c  e00314aa          mov        x0, x20
00002530  e10315aa          mov        x1, x21
00002534  00013fd6          blr        x8
00002538  880240f9          ldr        x8, [x20]
0000253c  e20300aa          mov        x2, x0
00002540  e00314aa          mov        x0, x20
00002544  e10313aa          mov        x1, x19
00002548  e22700f9          str        x2, [sp, #0x48]
0000254c  089541f9          ldr        x8, [x8, #0x328]
00002550  00013fd6          blr        x8
00002554  880240f9          ldr        x8, [x20]
00002558  fa0300aa          mov        x26, x0
0000255c  e00314aa          mov        x0, x20
00002560  e10317aa          mov        x1, x23
00002564  089943f9          ldr        x8, [x8, #0x730]
00002568  00013fd6          blr        x8
0000256c  880240f9          ldr        x8, [x20]
00002570  fb0300aa          mov        x27, x0
00002574  e00314aa          mov        x0, x20
00002578  e1031caa          mov        x1, x28
0000257c  e2031faa          mov        x2, xzr
00002580  fc2b00f9          str        x28, [sp, #0x50]
00002584  08f142f9          ldr        x8, [x8, #0x5e0]
00002588  00013fd6          blr        x8
0000258c  880240f9          ldr        x8, [x20]
00002590  b9833ba9          stp        x25, x0, [x29, #-0x48]
00002594  e00314aa          mov        x0, x20
00002598  e10319aa          mov        x1, x25
0000259c  e2031faa          mov        x2, xzr
000025a0  08f142f9          ldr        x8, [x8, #0x5e0]
000025a4  00013fd6          blr        x8
000025a8  880240f9          ldr        x8, [x20]
000025ac  f90300aa          mov        x25, x0
000025b0  087941f9          ldr        x8, [x8, #0x2f0]
000025b4  360e0034          cbz        w22, #0x2778
000025b8  f7ffffd0          adrp       x23, #0
000025bc  f7b23691          add        x23, x23, #0xdac
000025c0  e2ffffd0          adrp       x2, #0
000025c4  42443691          add        x2, x2, #0xd91
000025c8  e00314aa          mov        x0, x20
000025cc  e10315aa          mov        x1, x21
000025d0  e30317aa          mov        x3, x23
000025d4  fc03162a          mov        w28, w22
000025d8  00013fd6          blr        x8
000025dc  880240f9          ldr        x8, [x20]
000025e0  e20300aa          mov        x2, x0
000025e4  e00314aa          mov        x0, x20
000025e8  e10313aa          mov        x1, x19
000025ec  e21300f9          str        x2, [sp, #0x20]
000025f0  089d41f9          ldr        x8, [x8, #0x338]
000025f4  00013fd6          blr        x8
000025f8  880240f9          ldr        x8, [x20]
000025fc  e2ffffd0          adrp       x2, #0
00002600  42e83691          add        x2, x2, #0xdba
00002604  e00314aa          mov        x0, x20
00002608  e10315aa          mov        x1, x21
0000260c  e30317aa          mov        x3, x23
00002610  087941f9          ldr        x8, [x8, #0x2f0]
00002614  0840601e          fmov       d8, d0
00002618  00013fd6          blr        x8
0000261c  880240f9          ldr        x8, [x20]
00002620  e20300aa          mov        x2, x0
00002624  e00314aa          mov        x0, x20
00002628  e10313aa          mov        x1, x19
0000262c  089d41f9          ldr        x8, [x8, #0x338]
00002630  00013fd6          blr        x8
00002634  880240f9          ldr        x8, [x20]
00002638  f7ffffd0          adrp       x23, #0
0000263c  f7e23691          add        x23, x23, #0xdb8
00002640  e2ffffd0          adrp       x2, #0
00002644  42b83691          add        x2, x2, #0xdae
00002648  e00314aa          mov        x0, x20
0000264c  087941f9          ldr        x8, [x8, #0x2f0]
00002650  e10315aa          mov        x1, x21
00002654  e30317aa          mov        x3, x23
00002658  0940601e          fmov       d9, d0
0000265c  00013fd6          blr        x8
00002660  880240f9          ldr        x8, [x20]
00002664  e20300aa          mov        x2, x0
00002668  e00314aa          mov        x0, x20
0000266c  e10313aa          mov        x1, x19
00002670  e20f00f9          str        x2, [sp, #0x18]
00002674  089941f9          ldr        x8, [x8, #0x330]
00002678  00013fd6          blr        x8
0000267c  880240f9          ldr        x8, [x20]
00002680  e2ffffd0          adrp       x2, #0
00002684  42643691          add        x2, x2, #0xd99
00002688  e00314aa          mov        x0, x20
0000268c  e10315aa          mov        x1, x21
00002690  e30317aa          mov        x3, x23
00002694  087941f9          ldr        x8, [x8, #0x2f0]
00002698  0a40201e          fmov       s10, s0
0000269c  00013fd6          blr        x8
000026a0  880240f9          ldr        x8, [x20]
000026a4  e20300aa          mov        x2, x0
000026a8  e00314aa          mov        x0, x20
000026ac  e10313aa          mov        x1, x19
000026b0  e20b00f9          str        x2, [sp, #0x10]
000026b4  089941f9          ldr        x8, [x8, #0x330]
000026b8  00013fd6          blr        x8
000026bc  880240f9          ldr        x8, [x20]
000026c0  e2ffffd0          adrp       x2, #0
000026c4  42e43591          add        x2, x2, #0xd79
000026c8  e00314aa          mov        x0, x20
000026cc  e10315aa          mov        x1, x21
000026d0  e30317aa          mov        x3, x23
000026d4  087941f9          ldr        x8, [x8, #0x2f0]
000026d8  0d40201e          fmov       s13, s0
000026dc  00013fd6          blr        x8
000026e0  880240f9          ldr        x8, [x20]
000026e4  e20300aa          mov        x2, x0
000026e8  e00314aa          mov        x0, x20
000026ec  e10313aa          mov        x1, x19
000026f0  089941f9          ldr        x8, [x8, #0x330]
000026f4  00013fd6          blr        x8
000026f8  880240f9          ldr        x8, [x20]
000026fc  e2ffffd0          adrp       x2, #0
00002700  42143691          add        x2, x2, #0xd85
00002704  e00314aa          mov        x0, x20
00002708  e10315aa          mov        x1, x21
0000270c  e30317aa          mov        x3, x23
00002710  087941f9          ldr        x8, [x8, #0x2f0]
00002714  0c40201e          fmov       s12, s0
00002718  00013fd6          blr        x8
0000271c  880240f9          ldr        x8, [x20]
00002720  e20300aa          mov        x2, x0
00002724  e00314aa          mov        x0, x20
00002728  e10313aa          mov        x1, x19
0000272c  089941f9          ldr        x8, [x8, #0x330]
00002730  00013fd6          blr        x8
00002734  01e4002f          movi       d1, #0000000000000000
00002738  4821201e          fcmp       s10, #0.0
0000273c  ec0313aa          mov        x12, x19
00002740  2b4c2a1e          fcsel      s11, s1, s10, mi
00002744  a821201e          fcmp       s13, #0.0
00002748  2a4c2d1e          fcsel      s10, s1, s13, mi
0000274c  6821201e          fcmp       s11, #0.0
00002750  4c0e0054          b.gt       #0x2918
00002754  4821201e          fcmp       s10, #0.0
00002758  0c0e0054          b.gt       #0x2918
0000275c  b6d77ba9          ldp        x22, x21, [x29, #-0x48]
00002760  f3031f2a          mov        w19, wzr
00002764  f8df44a9          ldp        x24, x23, [sp, #0x48]
00002768  e21340f9          ldr        x2, [sp, #0x20]
0000276c  fb0f40f9          ldr        x27, [sp, #0x18]
00002770  fc030caa          mov        x28, x12
00002774  d1010014          b          #0x2eb8
00002778  f7ffffd0          adrp       x23, #0
0000277c  f77a3791          add        x23, x23, #0xdde
00002780  e2ffffd0          adrp       x2, #0
00002784  42383791          add        x2, x2, #0xdce
00002788  e00314aa          mov        x0, x20
0000278c  e10315aa          mov        x1, x21
00002790  e30317aa          mov        x3, x23
00002794  00013fd6          blr        x8
00002798  880240f9          ldr        x8, [x20]
0000279c  e20300aa          mov        x2, x0
000027a0  e00314aa          mov        x0, x20
000027a4  e10313aa          mov        x1, x19
000027a8  089141f9          ldr        x8, [x8, #0x320]
000027ac  00013fd6          blr        x8
000027b0  880240f9          ldr        x8, [x20]
000027b4  e00f00f9          str        x0, [sp, #0x18]
000027b8  e2ffffd0          adrp       x2, #0
000027bc  428c3691          add        x2, x2, #0xda3
000027c0  e00314aa          mov        x0, x20
000027c4  e10315aa          mov        x1, x21
000027c8  087941f9          ldr        x8, [x8, #0x2f0]
000027cc  e30317aa          mov        x3, x23
000027d0  00013fd6          blr        x8
000027d4  880240f9          ldr        x8, [x20]
000027d8  f60300aa          mov        x22, x0
000027dc  e00314aa          mov        x0, x20
000027e0  e10313aa          mov        x1, x19
000027e4  e20316aa          mov        x2, x22
000027e8  089141f9          ldr        x8, [x8, #0x320]
000027ec  00013fd6          blr        x8
000027f0  880240f9          ldr        x8, [x20]
000027f4  fc03002a          mov        w28, w0
000027f8  e2ffffd0          adrp       x2, #0
000027fc  42543791          add        x2, x2, #0xdd5
00002800  e00314aa          mov        x0, x20
00002804  e10315aa          mov        x1, x21
00002808  087941f9          ldr        x8, [x8, #0x2f0]
0000280c  e30317aa          mov        x3, x23
00002810  00013fd6          blr        x8
00002814  880240f9          ldr        x8, [x20]
00002818  e20300aa          mov        x2, x0
0000281c  e00314aa          mov        x0, x20
00002820  e10313aa          mov        x1, x19
00002824  f50313aa          mov        x21, x19
00002828  e21300f9          str        x2, [sp, #0x20]
0000282c  089141f9          ldr        x8, [x8, #0x320]
00002830  00013fd6          blr        x8
00002834  837fbc0a          bic        w3, w28, w28, asr #31
00002838  1f000071          cmp        w0, #0
0000283c  177ca00a          bic        w23, w0, w0, asr #31
00002840  80db407a          ccmp       w28, #0, #0, le
00002844  f3031f2a          mov        w19, wzr
00002848  ed110054          b.le       #0x2a84
0000284c  e02340f9          ldr        x0, [sp, #0x40]
00002850  1f040071          cmp        w0, #1
00002854  8b110054          b.lt       #0x2a84
00002858  ea0f40f9          ldr        x10, [sp, #0x18]
0000285c  ec1b40f9          ldr        x12, [sp, #0x30]
00002860  eb03382a          mvn        w11, w24
00002864  e2074729          ldp        w2, w1, [sp, #0x38]
00002868  4a7d4093          sxtw       x10, w10
0000286c  fc0315aa          mov        x28, x21
00002870  b5035cf8          ldur       x21, [x29, #-0x40]
00002874  e42f40b9          ldr        w4, [sp, #0x2c]
00002878  e8031faa          mov        x8, xzr
0000287c  e9031f2a          mov        w9, wzr
00002880  6b010c0b          add        w11, w11, w12
00002884  ecff8352          mov        w12, #0x1fff
00002888  5f1c0072          tst        w2, #0xff
0000288c  4e030c8b          add        x14, x26, x12
00002890  ed079f1a          cset       w13, ne
00002894  5f0300f1          cmp        x26, #0
00002898  ceb19a9a          csel       x14, x14, x26, lt
0000289c  cefd4dd3          lsr        x14, x14, #0xd
000028a0  df01186b          cmp        w14, w24
000028a4  70010e0b          add        w16, w11, w14
000028a8  efd79f1a          cset       w15, gt
000028ac  117e7393          sbfiz      x17, x16, #0xd, #0x20
000028b0  bf010f6a          tst        w13, w15
000028b4  0d128e1a          csel       w13, w16, w14, ne
000028b8  2e129a9a          csel       x14, x17, x26, ne
000028bc  10f17dd3          lsl        x16, x8, #3
000028c0  bf01186b          cmp        w13, w24
000028c4  08050091          add        x8, x8, #1
000028c8  da010a8b          add        x26, x14, x10
000028cc  adb1981a          csel       w13, w13, w24, lt
000028d0  b16a70f8          ldr        x17, [x21, x16]
000028d4  29d59f1a          csinc      w9, w9, wzr, le
000028d8  ad21c41a          lsl        w13, w13, w4
000028dc  1f0100eb          cmp        x8, x0
000028e0  33010012          and        w19, w9, #1
000028e4  6fdbad78          ldrsh      x15, [x27, w13, sxtw #1]
000028e8  ad01010b          add        w13, w13, w1
000028ec  6ddbad78          ldrsh      x13, [x27, w13, sxtw #1]
000028f0  1f2003d5          nop        
000028f4  ef45239b          smaddl     x15, w15, w3, x17
000028f8  af6a30f8          str        x15, [x21, x16]
000028fc  2f6b70f8          ldr        x15, [x25, x16]
00002900  1f2003d5          nop        
00002904  ad3d379b          smaddl     x13, w13, w23, x15
00002908  2d6b30f8          str        x13, [x25, x16]
0000290c  020c0054          b.hs       #0x2a8c
00002910  d3fbff34          cbz        w19, #0x2888
00002914  5e000014          b          #0x2a8c
00002918  b5035cf8          ldur       x21, [x29, #-0x40]
0000291c  9f070071          cmp        w28, #1
00002920  610d0054          b.ne       #0x2acc
00002924  f12340f9          ldr        x17, [sp, #0x40]
00002928  e21340f9          ldr        x2, [sp, #0x20]
0000292c  eb1b40f9          ldr        x11, [sp, #0x30]
00002930  e1034729          ldp        w1, w0, [sp, #0x38]
00002934  3f060071          cmp        w17, #1
00002938  eb250054          b.lt       #0x2df4
0000293c  ea03382a          mvn        w10, w24
00002940  e32f40b9          ldr        w3, [sp, #0x2c]
00002944  e8031faa          mov        x8, xzr
00002948  e9031f2a          mov        w9, wzr
0000294c  4a010b0b          add        w10, w10, w11
00002950  ebff8352          mov        w11, #0x1fff
00002954  fc030caa          mov        x28, x12
00002958  4c030b8b          add        x12, x26, x11
0000295c  5f0300f1          cmp        x26, #0
00002960  2d1c0012          and        w13, w1, #0xff
00002964  8cb19a9a          csel       x12, x12, x26, lt
00002968  8cfd4dd3          lsr        x12, x12, #0xd
0000296c  ad010034          cbz        w13, #0x29a0
00002970  9f01186b          cmp        w12, w24
00002974  cd000054          b.le       #0x298c
00002978  4821201e          fcmp       s10, #0.0
0000297c  4c010c0b          add        w12, w10, w12
00002980  9a7d7393          sbfiz      x26, x12, #0xd, #0x20
00002984  6295211e          fccmp      s11, s1, #2, ls
00002988  29859f1a          csinc      w9, w9, wzr, hi
0000298c  9f01186b          cmp        w12, w24
00002990  edb38a1a          csel       w13, wzr, w10, lt
00002994  8d010d0b          add        w13, w12, w13
00002998  ad050011          add        w13, w13, #1
0000299c  02000014          b          #0x29a4
000029a0  8d050011          add        w13, w12, #1
000029a4  9f01186b          cmp        w12, w24
000029a8  42cf029e          scvtf      s2, x26, #0xd
000029ac  8cb1981a          csel       w12, w12, w24, lt
000029b0  29d59f1a          csinc      w9, w9, wzr, le
000029b4  bf01186b          cmp        w13, w24
000029b8  8301221e          scvtf      s3, w12
000029bc  8dc18d1a          csel       w13, w12, w13, gt
000029c0  6821201e          fcmp       s11, #0.0
000029c4  8e21c31a          lsl        w14, w12, w3
000029c8  ad21c31a          lsl        w13, w13, w3
000029cc  0cf17dd3          lsl        x12, x8, #3
000029d0  4238231e          fsub       s2, s2, s3
000029d4  ad010054          b.le       #0x2a08
000029d8  6fdbee78          ldrsh      w15, [x27, w14, sxtw #1]
000029dc  70dbed78          ldrsh      w16, [x27, w13, sxtw #1]
000029e0  e301221e          scvtf      s3, w15
000029e4  0402221e          scvtf      s4, w16
000029e8  af6a6cf8          ldr        x15, [x21, x12]
000029ec  8438231e          fsub       s4, s4, s3
000029f0  430c041f          fmadd      s3, s2, s4, s3
000029f4  e401229e          scvtf      s4, x15
000029f8  63100b1f          fmadd      s3, s3, s11, s4
000029fc  8b292b1e          fadd       s11, s12, s11
00002a00  6f00389e          fcvtzs     x15, s3
00002a04  af6a2cf8          str        x15, [x21, x12]
00002a08  4821201e          fcmp       s10, #0.0
00002a0c  ed010054          b.le       #0x2a48
00002a10  ce01000b          add        w14, w14, w0
00002a14  ad01000b          add        w13, w13, w0
00002a18  6edbee78          ldrsh      w14, [x27, w14, sxtw #1]
00002a1c  6ddbed78          ldrsh      w13, [x27, w13, sxtw #1]
00002a20  c301221e          scvtf      s3, w14
00002a24  a401221e          scvtf      s4, w13
00002a28  2d6b6cf8          ldr        x13, [x25, x12]
00002a2c  8438231e          fsub       s4, s4, s3
00002a30  420c041f          fmadd      s2, s2, s4, s3
00002a34  a301229e          scvtf      s3, x13
00002a38  420c0a1f          fmadd      s2, s2, s10, s3
00002a3c  0a282a1e          fadd       s10, s0, s10
00002a40  4d00389e          fcvtzs     x13, s2
00002a44  2d6b2cf8          str        x13, [x25, x12]
00002a48  2829681e          fadd       d8, d9, d8
00002a4c  4203629e          scvtf      d2, x26
00002a50  08050091          add        x8, x8, #1
00002a54  33010012          and        w19, w9, #1
00002a58  0229621e          fadd       d2, d8, d2
00002a5c  0821601e          fcmp       d8, #0.0
00002a60  4c00789e          fcvtzs     x12, d2
00002a64  9ac19a9a          csel       x26, x12, x26, gt
00002a68  1f0111eb          cmp        x8, x17
00002a6c  42000054          b.hs       #0x2a74
00002a70  53f7ff34          cbz        w19, #0x2958
00002a74  f8df44a9          ldp        x24, x23, [sp, #0x48]
00002a78  b6835bf8          ldur       x22, [x29, #-0x48]
00002a7c  fb0f40f9          ldr        x27, [sp, #0x18]
00002a80  0e010014          b          #0x2eb8
00002a84  fc0315aa          mov        x28, x21
00002a88  b5035cf8          ldur       x21, [x29, #-0x40]
00002a8c  880240f9          ldr        x8, [x20]
00002a90  e00314aa          mov        x0, x20
00002a94  e1031caa          mov        x1, x28
00002a98  e20316aa          mov        x2, x22
00002a9c  08b541f9          ldr        x8, [x8, #0x368]
00002aa0  00013fd6          blr        x8
00002aa4  880240f9          ldr        x8, [x20]
00002aa8  e21340f9          ldr        x2, [sp, #0x20]
00002aac  e00314aa          mov        x0, x20
00002ab0  e1031caa          mov        x1, x28
00002ab4  e303172a          mov        w3, w23
00002ab8  08b541f9          ldr        x8, [x8, #0x368]
00002abc  00013fd6          blr        x8
00002ac0  f8df44a9          ldp        x24, x23, [sp, #0x48]
00002ac4  b6835bf8          ldur       x22, [x29, #-0x48]
00002ac8  10010014          b          #0x2f08
00002acc  e30b4729          ldp        w3, w2, [sp, #0x38]
00002ad0  887b1f12          and        w8, w28, #0xfffffffe
00002ad4  e12340f9          ldr        x1, [sp, #0x40]
00002ad8  e41b40f9          ldr        x4, [sp, #0x30]
00002adc  1f090071          cmp        w8, #2
00002ae0  a1110054          b.ne       #0x2d14
00002ae4  e52f40b9          ldr        w5, [sp, #0x2c]
00002ae8  3f040071          cmp        w1, #1
00002aec  fc030caa          mov        x28, x12
00002af0  ab180054          b.lt       #0x2e04
00002af4  04e4002f          movi       d4, #0000000000000000
00002af8  0110201e          fmov       s1, #2.00000000
00002afc  ea03382a          mvn        w10, w24
00002b00  0290321e          fmov       s2, #-5.00000000
00002b04  0310221e          fmov       s3, #4.00000000
00002b08  e8031faa          mov        x8, xzr
00002b0c  0510211e          fmov       s5, #3.00000000
00002b10  0610311e          fmov       s6, #-3.00000000
00002b14  e9031f2a          mov        w9, wzr
00002b18  07102c1e          fmov       s7, #0.50000000
00002b1c  4a01040b          add        w10, w10, w4
00002b20  ebff8352          mov        w11, #0x1fff
00002b24  4c030b8b          add        x12, x26, x11
00002b28  5f0300f1          cmp        x26, #0
00002b2c  6d1c0012          and        w13, w3, #0xff
00002b30  8cb19a9a          csel       x12, x12, x26, lt
00002b34  8cfd4dd3          lsr        x12, x12, #0xd
00002b38  ed020034          cbz        w13, #0x2b94
00002b3c  4821201e          fcmp       s10, #0.0
00002b40  4d010c0b          add        w13, w10, w12
00002b44  ae7d7393          sbfiz      x14, x13, #0xd, #0x20
00002b48  6295241e          fccmp      s11, s4, #2, ls
00002b4c  2f859f1a          csinc      w15, w9, wzr, hi
00002b50  9f01186b          cmp        w12, w24
00002b54  8cd18d1a          csel       w12, w12, w13, le
00002b58  5ad38e9a          csel       x26, x26, x14, le
00002b5c  29d18f1a          csel       w9, w9, w15, le
00002b60  9f01186b          cmp        w12, w24
00002b64  8d090011          add        w13, w12, #2
00002b68  8f0d0011          add        w15, w12, #3
00002b6c  eeb38a1a          csel       w14, wzr, w10, lt
00002b70  bf01186b          cmp        w13, w24
00002b74  50c19f1a          csel       w16, w10, wzr, gt
00002b78  ff01186b          cmp        w15, w24
00002b7c  8e010e0b          add        w14, w12, w14
00002b80  51c19f1a          csel       w17, w10, wzr, gt
00002b84  ad01100b          add        w13, w13, w16
00002b88  ce050011          add        w14, w14, #1
00002b8c  ef01110b          add        w15, w15, w17
00002b90  04000014          b          #0x2ba0
00002b94  8e050011          add        w14, w12, #1
00002b98  8d090011          add        w13, w12, #2
00002b9c  8f0d0011          add        w15, w12, #3
00002ba0  9f01186b          cmp        w12, w24
00002ba4  50cf029e          scvtf      s16, x26, #0xd
00002ba8  8cb1981a          csel       w12, w12, w24, lt
00002bac  29d59f1a          csinc      w9, w9, wzr, le
00002bb0  df01186b          cmp        w14, w24
00002bb4  9101221e          scvtf      s17, w12
00002bb8  90c18e1a          csel       w16, w12, w14, gt
00002bbc  bf01186b          cmp        w13, w24
00002bc0  0dc28d1a          csel       w13, w16, w13, gt
00002bc4  ff01186b          cmp        w15, w24
00002bc8  8e21c51a          lsl        w14, w12, w5
00002bcc  b1c18f1a          csel       w17, w13, w15, gt
00002bd0  6821201e          fcmp       s11, #0.0
00002bd4  1022c51a          lsl        w16, w16, w5
00002bd8  af21c51a          lsl        w15, w13, w5
00002bdc  2d22c51a          lsl        w13, w17, w5
00002be0  0cf17dd3          lsl        x12, x8, #3
00002be4  123a311e          fsub       s18, s16, s17
00002be8  500a321e          fmul       s16, s18, s18
00002bec  510a301e          fmul       s17, s18, s16
00002bf0  8d030054          b.le       #0x2c60
00002bf4  71dbf078          ldrsh      w17, [x27, w16, sxtw #1]
00002bf8  60dbef78          ldrsh      w0, [x27, w15, sxtw #1]
00002bfc  3302221e          scvtf      s19, w17
00002c00  71dbee78          ldrsh      w17, [x27, w14, sxtw #1]
00002c04  1500221e          scvtf      s21, w0
00002c08  3402221e          scvtf      s20, w17
00002c0c  71dbed78          ldrsh      w17, [x27, w13, sxtw #1]
00002c10  760a221e          fmul       s22, s19, s2
00002c14  3802221e          scvtf      s24, w17
00002c18  b16a6cf8          ldr        x17, [x21, x12]
00002c1c  b73a341e          fsub       s23, s21, s20
00002c20  965a011f          fmadd      s22, s20, s1, s22
00002c24  74d2251f          fnmsub     s20, s19, s5, s20
00002c28  570a371e          fmul       s23, s18, s23
00002c2c  b65a031f          fmadd      s22, s21, s3, s22
00002c30  b452061f          fmadd      s20, s21, s6, s20
00002c34  735e011f          fmadd      s19, s19, s1, s23
00002c38  d63a381e          fsub       s22, s22, s24
00002c3c  942a381e          fadd       s20, s20, s24
00002c40  d34e101f          fmadd      s19, s22, s16, s19
00002c44  934e111f          fmadd      s19, s20, s17, s19
00002c48  3402229e          scvtf      s20, x17
00002c4c  730a271e          fmul       s19, s19, s7
00002c50  73520b1f          fmadd      s19, s19, s11, s20
00002c54  8b292b1e          fadd       s11, s12, s11
00002c58  7102389e          fcvtzs     x17, s19
00002c5c  b16a2cf8          str        x17, [x21, x12]
00002c60  4821201e          fcmp       s10, #0.0
00002c64  0d040054          b.le       #0x2ce4
00002c68  1002020b          add        w16, w16, w2
00002c6c  ce01020b          add        w14, w14, w2
00002c70  ef01020b          add        w15, w15, w2
00002c74  70dbf078          ldrsh      w16, [x27, w16, sxtw #1]
00002c78  6edbee78          ldrsh      w14, [x27, w14, sxtw #1]
00002c7c  6fdbef78          ldrsh      w15, [x27, w15, sxtw #1]
00002c80  ad01020b          add        w13, w13, w2
00002c84  1302221e          scvtf      s19, w16
00002c88  d401221e          scvtf      s20, w14
00002c8c  f501221e          scvtf      s21, w15
00002c90  6ddbed78          ldrsh      w13, [x27, w13, sxtw #1]
00002c94  760a221e          fmul       s22, s19, s2
00002c98  b73a341e          fsub       s23, s21, s20
00002c9c  965a011f          fmadd      s22, s20, s1, s22
00002ca0  520a371e          fmul       s18, s18, s23
00002ca4  b701221e          scvtf      s23, w13
00002ca8  74d2251f          fnmsub     s20, s19, s5, s20
00002cac  2d6b6cf8          ldr        x13, [x25, x12]
00002cb0  b65a031f          fmadd      s22, s21, s3, s22
00002cb4  724a011f          fmadd      s18, s19, s1, s18
00002cb8  b452061f          fmadd      s20, s21, s6, s20
00002cbc  d33a371e          fsub       s19, s22, s23
00002cc0  704a101f          fmadd      s16, s19, s16, s18
00002cc4  922a371e          fadd       s18, s20, s23
00002cc8  5042111f          fmadd      s16, s18, s17, s16
00002ccc  b101229e          scvtf      s17, x13
00002cd0  100a271e          fmul       s16, s16, s7
00002cd4  10460a1f          fmadd      s16, s16, s10, s17
00002cd8  0a282a1e          fadd       s10, s0, s10
00002cdc  0d02389e          fcvtzs     x13, s16
00002ce0  2d6b2cf8          str        x13, [x25, x12]
00002ce4  2829681e          fadd       d8, d9, d8
00002ce8  5003629e          scvtf      d16, x26
00002cec  08050091          add        x8, x8, #1
00002cf0  33010012          and        w19, w9, #1
00002cf4  1029701e          fadd       d16, d8, d16
00002cf8  0821601e          fcmp       d8, #0.0
00002cfc  0c02789e          fcvtzs     x12, d16
00002d00  9ac19a9a          csel       x26, x12, x26, gt
00002d04  1f0101eb          cmp        x8, x1
00002d08  02080054          b.hs       #0x2e08
00002d0c  d3f0ff34          cbz        w19, #0x2b24
00002d10  3e000014          b          #0x2e08
00002d14  e52f40b9          ldr        w5, [sp, #0x2c]
00002d18  3f040071          cmp        w1, #1
00002d1c  fc030caa          mov        x28, x12
00002d20  cb070054          b.lt       #0x2e18
00002d24  897c7393          sbfiz      x9, x4, #0xd, #0x20
00002d28  e8031faa          mov        x8, xzr
00002d2c  1f2003d5          nop        
00002d30  0a440410          adr        x10, #0xb5b0
00002d34  ebff8352          mov        w11, #0x1fff
00002d38  1f2003d5          nop        
00002d3c  ac430810          adr        x12, #0x135b0
00002d40  09000014          b          #0x2d64
00002d44  ef01102a          orr        w15, w15, w16
00002d48  08050091          add        x8, x8, #1
00002d4c  da094091          add        x26, x14, #2, lsl #12
00002d50  b3010f0a          and        w19, w13, w15
00002d54  1f0101eb          cmp        x8, x1
00002d58  6d369f1a          csinc      w13, w19, wzr, lo
00002d5c  bf050071          cmp        w13, #1
00002d60  e0050054          b.eq       #0x2e1c
00002d64  4d030b8b          add        x13, x26, x11
00002d68  5f0300f1          cmp        x26, #0
00002d6c  adb19a9a          csel       x13, x13, x26, lt
00002d70  aefd4dd3          lsr        x14, x13, #0xd
00002d74  df01186b          cmp        w14, w24
00002d78  edd79f1a          cset       w13, gt
00002d7c  f0c79f1a          cset       w16, le
00002d80  d1b1981a          csel       w17, w14, w24, lt
00002d84  7f1c0072          tst        w3, #0xff
00002d88  ef179f1a          cset       w15, eq
00002d8c  0e169f1a          csinc      w14, w16, wzr, ne
00002d90  4821201e          fcmp       s10, #0.0
00002d94  6295211e          fccmp      s11, s1, #2, ls
00002d98  f0879f1a          cset       w16, ls
00002d9c  df010071          cmp        w14, #0
00002da0  4e13899a          csel       x14, x26, x9, ne
00002da4  3112841a          csel       w17, w17, w4, ne
00002da8  6821201e          fcmp       s11, #0.0
00002dac  3122c51a          lsl        w17, w17, w5
00002db0  ed000054          b.le       #0x2dcc
00002db4  60dbf178          ldrsh      w0, [x27, w17, sxtw #1]
00002db8  0200221e          scvtf      s2, w0
00002dbc  6209221e          fmul       s2, s11, s2
00002dc0  8b292b1e          fadd       s11, s12, s11
00002dc4  4000389e          fcvtzs     x0, s2
00002dc8  407928f8          str        x0, [x10, x8, lsl #3]
00002dcc  4821201e          fcmp       s10, #0.0
00002dd0  adfbff54          b.le       #0x2d44
00002dd4  3102020b          add        w17, w17, w2
00002dd8  71dbf178          ldrsh      w17, [x27, w17, sxtw #1]
00002ddc  2202221e          scvtf      s2, w17
00002de0  4209221e          fmul       s2, s10, s2
00002de4  0a282a1e          fadd       s10, s0, s10
00002de8  5100389e          fcvtzs     x17, s2
00002dec  917928f8          str        x17, [x12, x8, lsl #3]
00002df0  d5ffff17          b          #0x2d44
00002df4  f8df44a9          ldp        x24, x23, [sp, #0x48]
00002df8  f3031f2a          mov        w19, wzr
00002dfc  b6835bf8          ldur       x22, [x29, #-0x48]
00002e00  5bfeff17          b          #0x276c
00002e04  f3031f2a          mov        w19, wzr
00002e08  f8df44a9          ldp        x24, x23, [sp, #0x48]
00002e0c  b6835bf8          ldur       x22, [x29, #-0x48]
00002e10  fb8b41a9          ldp        x27, x2, [sp, #0x18]
00002e14  29000014          b          #0x2eb8
00002e18  f3031f2a          mov        w19, wzr
00002e1c  f8df44a9          ldp        x24, x23, [sp, #0x48]
00002e20  b6835bf8          ldur       x22, [x29, #-0x48]
00002e24  fb0f40f9          ldr        x27, [sp, #0x18]
00002e28  e00740f9          ldr        x0, [sp, #8]
00002e2c  000400b4          cbz        x0, #0x2eac
00002e30  08e4e7d2          mov        x8, #0x3f20000000000000
00002e34  357c4093          sxtw       x21, w1
00002e38  a5035cf8          ldur       x5, [x29, #-0x40]
00002e3c  0001679e          fmov       d0, x8
00002e40  08009052          mov        w8, #0x8000
00002e44  1f2003d5          nop        
00002e48  443b0410          adr        x4, #0xb5b0
00002e4c  68e7a872          movk       w8, #0x473b, lsl #16
00002e50  e10315aa          mov        x1, x21
00002e54  0d01271e          fmov       s13, w8
00002e58  02808052          mov        w2, #0x400
00002e5c  83008052          mov        w3, #4
00002e60  0009601e          fmul       d0, d8, d0
00002e64  a141201e          fmov       s1, s13
00002e68  0c40621e          fcvt       s12, d0
00002e6c  8041201e          fmov       s0, s12
00002e70  24010094          bl         #0x3300
00002e74  8041201e          fmov       s0, s12
00002e78  a141201e          fmov       s1, s13
00002e7c  e90740f9          ldr        x9, [sp, #8]
00002e80  08029652          mov        w8, #0xb010
00002e84  1f2003d5          nop        
00002e88  44390810          adr        x4, #0x135b0
00002e8c  2001088b          add        x0, x9, x8
00002e90  e10315aa          mov        x1, x21
00002e94  b5035cf8          ldur       x21, [x29, #-0x40]
00002e98  02808052          mov        w2, #0x400
00002e9c  83008052          mov        w3, #4
00002ea0  e50319aa          mov        x5, x25
00002ea4  17010094          bl         #0x3300
00002ea8  e12340f9          ldr        x1, [sp, #0x40]
00002eac  2000621e          scvtf      d0, w1
00002eb0  e21340f9          ldr        x2, [sp, #0x20]
00002eb4  2821401f          fmadd      d8, d9, d0, d8
00002eb8  880240f9          ldr        x8, [x20]
00002ebc  0041601e          fmov       d0, d8
00002ec0  e00314aa          mov        x0, x20
00002ec4  e1031caa          mov        x1, x28
00002ec8  08c141f9          ldr        x8, [x8, #0x380]
00002ecc  00013fd6          blr        x8
00002ed0  880240f9          ldr        x8, [x20]
00002ed4  6041201e          fmov       s0, s11
00002ed8  e00314aa          mov        x0, x20
00002edc  e1031caa          mov        x1, x28
00002ee0  e2031baa          mov        x2, x27
00002ee4  08bd41f9          ldr        x8, [x8, #0x378]
00002ee8  00013fd6          blr        x8
00002eec  880240f9          ldr        x8, [x20]
00002ef0  4041201e          fmov       s0, s10
00002ef4  e20b40f9          ldr        x2, [sp, #0x10]
00002ef8  e00314aa          mov        x0, x20
00002efc  e1031caa          mov        x1, x28
00002f00  08bd41f9          ldr        x8, [x8, #0x378]
00002f04  00013fd6          blr        x8
00002f08  880240f9          ldr        x8, [x20]
00002f0c  e00314aa          mov        x0, x20
00002f10  e1031caa          mov        x1, x28
00002f14  e20318aa          mov        x2, x24
00002f18  e3031aaa          mov        x3, x26
00002f1c  08b941f9          ldr        x8, [x8, #0x370]
00002f20  00013fd6          blr        x8
00002f24  880240f9          ldr        x8, [x20]
00002f28  e00314aa          mov        x0, x20
00002f2c  e10317aa          mov        x1, x23
00002f30  e20315aa          mov        x2, x21
00002f34  e3031f2a          mov        w3, wzr
00002f38  081143f9          ldr        x8, [x8, #0x620]
00002f3c  00013fd6          blr        x8
00002f40  880240f9          ldr        x8, [x20]
00002f44  e00314aa          mov        x0, x20
00002f48  e10316aa          mov        x1, x22
00002f4c  e20319aa          mov        x2, x25
00002f50  e3031f2a          mov        w3, wzr
00002f54  081143f9          ldr        x8, [x8, #0x620]
00002f58  00013fd6          blr        x8
00002f5c  e003132a          mov        w0, w19
00002f60  f44f4fa9          ldp        x20, x19, [sp, #0xf0]
00002f64  f6574ea9          ldp        x22, x21, [sp, #0xe0]
00002f68  f85f4da9          ldp        x24, x23, [sp, #0xd0]
00002f6c  fa674ca9          ldp        x26, x25, [sp, #0xc0]
00002f70  fc6f4ba9          ldp        x28, x27, [sp, #0xb0]
00002f74  fd7b4aa9          ldp        x29, x30, [sp, #0xa0]
00002f78  e923496d          ldp        d9, d8, [sp, #0x90]
00002f7c  eb2b486d          ldp        d11, d10, [sp, #0x80]
00002f80  ed33476d          ldp        d13, d12, [sp, #0x70]
00002f84  ff030491          add        sp, sp, #0x100
00002f88  c0035fd6          ret        
