# Library: libnopensl.so
# Function: Java_sofeh_audio_NOpenSL_MixWrite; bytes=1192
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
000017f4  13050094  bl       #0x2c40
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
000018ac  e5040094  bl       #0x2c40
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
000018f0  e4040094  bl       #0x2c80
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
0000193c  d1040094  bl       #0x2c80
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
000019fc  99040094  bl       #0x2c60
