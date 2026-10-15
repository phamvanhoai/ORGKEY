# APK member: lib/arm64-v8a/libnopensl.so
# Library SHA-256: dc900f9de170ec71471d8b5d2ccecc14d0bbb8c1d2346bd6e149467c888b38cf
# Function: Java_sofeh_audio_NOpenSL_Write; address=0xfc4; bytes=636
# Decoder mode: default; decoded_bytes=636
00000fc4  fd7bbaa9          stp        x29, x30, [sp, #-0x60]!
00000fc8  fc6f01a9          stp        x28, x27, [sp, #0x10]
00000fcc  fa6702a9          stp        x26, x25, [sp, #0x20]
00000fd0  f85f03a9          stp        x24, x23, [sp, #0x30]
00000fd4  f65704a9          stp        x22, x21, [sp, #0x40]
00000fd8  f44f05a9          stp        x20, x19, [sp, #0x50]
00000fdc  fd030091          mov        x29, sp
00000fe0  ff2340d1          sub        sp, sp, #8, lsl #12
00000fe4  ff8300d1          sub        sp, sp, #0x20
00000fe8  58d03bd5          mrs        x24, tpidr_el0
00000fec  081740f9          ldr        x8, [x24, #0x28]
00000ff0  a8031ff8          stur       x8, [x29, #-0x10]
00000ff4  a21000b4          cbz        x2, #0x1208
00000ff8  970000d0          adrp       x23, #0x12000
00000ffc  e8aa47f9          ldr        x8, [x23, #0xf50]
00001000  481000b4          cbz        x8, #0x1208
00001004  880000b0          adrp       x8, #0x12000
00001008  08594fb9          ldr        w8, [x8, #0xf58]
0000100c  1f050071          cmp        w8, #1
00001010  cb0f0054          b.lt       #0x1208
00001014  080040f9          ldr        x8, [x0]
00001018  e10302aa          mov        x1, x2
0000101c  f303032a          mov        w19, w3
00001020  f40302aa          mov        x20, x2
00001024  f50300aa          mov        x21, x0
00001028  08ad42f9          ldr        x8, [x8, #0x558]
0000102c  00013fd6          blr        x8
00001030  1f00136b          cmp        w0, w19
00001034  08008852          mov        w8, #0x4000
00001038  09b0931a          csel       w9, w0, w19, lt
0000103c  3f114071          cmp        w9, #4, lsl #12
00001040  33b1881a          csel       w19, w9, w8, lt
00001044  3f050071          cmp        w9, #1
00001048  0b0e0054          b.lt       #0x1208
0000104c  a80240f9          ldr        x8, [x21]
00001050  e4430091          add        x4, sp, #0x10
00001054  e00315aa          mov        x0, x21
00001058  e10314aa          mov        x1, x20
0000105c  e2031f2a          mov        w2, wzr
00001060  e303132a          mov        w3, w19
00001064  082943f9          ldr        x8, [x8, #0x650]
00001068  00013fd6          blr        x8
0000106c  950000b0          adrp       x21, #0x12000
00001070  9a0000b0          adrp       x26, #0x12000
00001074  990000b0          adrp       x25, #0x12000
00001078  94008052          mov        w20, #4
0000107c  16028052          mov        w22, #0x10
00001080  f80700f9          str        x24, [sp, #8]
00001084  a85e4fb9          ldr        w8, [x21, #0xf5c]
00001088  49634fb9          ldr        w9, [x26, #0xf60]
0000108c  1f01096b          cmp        w8, w9
00001090  81000054          b.ne       #0x10a0
00001094  fcaa47f9          ldr        x28, [x23, #0xf50]
00001098  7c0100b5          cbnz       x28, #0x10c4
0000109c  53000014          b          #0x11e8
000010a0  890000b0          adrp       x9, #0x12000
000010a4  a85e4fb9          ldr        w8, [x21, #0xf5c]
000010a8  fcaa47f9          ldr        x28, [x23, #0xf50]
000010ac  29917d39          ldrb       w9, [x9, #0xf64]
000010b0  3f010071          cmp        w9, #0
000010b4  c912941a          csel       w9, w22, w20, ne
000010b8  1f01096b          cmp        w8, w9
000010bc  8b010054          b.lt       #0x10ec
000010c0  7c0100b4          cbz        x28, #0x10ec
000010c4  286b4fb9          ldr        w8, [x25, #0xf68]
000010c8  1f090071          cmp        w8, #2
000010cc  81000054          b.ne       #0x10dc
000010d0  40018052          mov        w0, #0xa
000010d4  db060094          bl         #0x2c40
000010d8  ebffff17          b          #0x1084
000010dc  880000b0          adrp       x8, #0x12000
000010e0  5b634fb9          ldr        w27, [x26, #0xf60]
000010e4  1a6d4fb9          ldr        w26, [x8, #0xf6c]
000010e8  05000014          b          #0x10fc
000010ec  880000b0          adrp       x8, #0x12000
000010f0  5b634fb9          ldr        w27, [x26, #0xf60]
000010f4  1a6d4fb9          ldr        w26, [x8, #0xf6c]
000010f8  fc0700b4          cbz        x28, #0x11f4
000010fc  f6031f2a          mov        w22, wzr
00001100  14028052          mov        w20, #0x10
00001104  286b4fb9          ldr        w8, [x25, #0xf68]
00001108  1f090071          cmp        w8, #2
0000110c  41070054          b.ne       #0x11f4
00001110  880000b0          adrp       x8, #0x12000
00001114  08594fb9          ldr        w8, [x8, #0xf58]
00001118  5f03086b          cmp        w26, w8
0000111c  0b040054          b.lt       #0x119c
00001120  980000b0          adrp       x24, #0x12000
00001124  9a008052          mov        w26, #4
00001128  08937d39          ldrb       w8, [x24, #0xf64]
0000112c  1f010071          cmp        w8, #0
00001130  68070011          add        w8, w27, #1
00001134  89129a1a          csel       w9, w20, w26, ne
00001138  1f01096b          cmp        w8, w9
0000113c  fba79b1a          csinc      w27, wzr, w27, ge
00001140  a85e4fb9          ldr        w8, [x21, #0xf5c]
00001144  1f011b6b          cmp        w8, w27
00001148  81000054          b.ne       #0x1158
0000114c  fcaa47f9          ldr        x28, [x23, #0xf50]
00001150  5c0100b5          cbnz       x28, #0x1178
00001154  0f000014          b          #0x1190
00001158  a85e4fb9          ldr        w8, [x21, #0xf5c]
0000115c  09937d39          ldrb       w9, [x24, #0xf64]
00001160  fcaa47f9          ldr        x28, [x23, #0xf50]
00001164  3f010071          cmp        w9, #0
00001168  89129a1a          csel       w9, w20, w26, ne
0000116c  1f01096b          cmp        w8, w9
00001170  84ab40fa          ccmp       x28, #0, #4, ge
00001174  e0000054          b.eq       #0x1190
00001178  286b4fb9          ldr        w8, [x25, #0xf68]
0000117c  1f090071          cmp        w8, #2
00001180  81000054          b.ne       #0x1190
00001184  40018052          mov        w0, #0xa
00001188  ae060094          bl         #0x2c40
0000118c  edffff17          b          #0x1140
00001190  880000b0          adrp       x8, #0x12000
00001194  fa031f2a          mov        w26, wzr
00001198  08594fb9          ldr        w8, [x8, #0xf58]
0000119c  697f4093          sxtw       x9, w27
000011a0  08011a4b          sub        w8, w8, w26
000011a4  1f2003d5          nop        
000011a8  4aee0810          adr        x10, #0x12f70
000011ac  7f02086b          cmp        w19, w8
000011b0  493d098b          add        x9, x10, x9, lsl #15
000011b4  78b2881a          csel       w24, w19, w8, lt
000011b8  e8430091          add        x8, sp, #0x10
000011bc  01c5368b          add        x1, x8, w22, sxtw #1
000011c0  027f7f93          sbfiz      x2, x24, #1, #0x20
000011c4  20c53a8b          add        x0, x9, w26, sxtw #1
000011c8  a2060094          bl         #0x2c50
000011cc  7302184b          sub        w19, w19, w24
000011d0  1a031a0b          add        w26, w24, w26
000011d4  7f060071          cmp        w19, #1
000011d8  eb000054          b.lt       #0x11f4
000011dc  1603160b          add        w22, w24, w22
000011e0  3cf9ffb5          cbnz       x28, #0x1104
000011e4  04000014          b          #0x11f4
000011e8  880000b0          adrp       x8, #0x12000
000011ec  5b634fb9          ldr        w27, [x26, #0xf60]
000011f0  1a6d4fb9          ldr        w26, [x8, #0xf6c]
000011f4  880000b0          adrp       x8, #0x12000
000011f8  1b610fb9          str        w27, [x8, #0xf60]
000011fc  880000b0          adrp       x8, #0x12000
00001200  1a6d0fb9          str        w26, [x8, #0xf6c]
00001204  f80740f9          ldr        x24, [sp, #8]
00001208  081740f9          ldr        x8, [x24, #0x28]
0000120c  a9035ff8          ldur       x9, [x29, #-0x10]
00001210  1f0109eb          cmp        x8, x9
00001214  41010054          b.ne       #0x123c
00001218  ff234091          add        sp, sp, #8, lsl #12
0000121c  ff830091          add        sp, sp, #0x20
00001220  f44f45a9          ldp        x20, x19, [sp, #0x50]
00001224  f65744a9          ldp        x22, x21, [sp, #0x40]
00001228  f85f43a9          ldp        x24, x23, [sp, #0x30]
0000122c  fa6742a9          ldp        x26, x25, [sp, #0x20]
00001230  fc6f41a9          ldp        x28, x27, [sp, #0x10]
00001234  fd7bc6a8          ldp        x29, x30, [sp], #0x60
00001238  c0035fd6          ret        
0000123c  89060094          bl         #0x2c60
