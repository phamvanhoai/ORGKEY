# APK member: lib/arm64-v8a/libnopensl.so
# Library SHA-256: dc900f9de170ec71471d8b5d2ccecc14d0bbb8c1d2346bd6e149467c888b38cf
# Function: Java_sofeh_audio_NOpenSL_RecordStart; address=0x2ad4; bytes=236
# Decoder mode: default; decoded_bytes=236
00002ad4  fd7bbca9          stp        x29, x30, [sp, #-0x40]!
00002ad8  f70b00f9          str        x23, [sp, #0x10]
00002adc  f65702a9          stp        x22, x21, [sp, #0x20]
00002ae0  f44f03a9          stp        x20, x19, [sp, #0x30]
00002ae4  fd030091          mov        x29, sp
00002ae8  88080090          adrp       x8, #0x112000
00002aec  e0031f2a          mov        w0, wzr
00002af0  13e947f9          ldr        x19, [x8, #0xfd0]
00002af4  d30500b4          cbz        x19, #0x2bac
00002af8  88040090          adrp       x8, #0x92000
00002afc  08b947f9          ldr        x8, [x8, #0xf70]
00002b00  680500b4          cbz        x8, #0x2bac
00002b04  88000090          adrp       x8, #0x12000
00002b08  96000090          adrp       x22, #0x12000
00002b0c  1f2003d5          nop        
00002b10  55234810          adr        x21, #0x92f78
00002b14  08598fb9          ldrsw      x8, [x8, #0xf58]
00002b18  c9927d39          ldrb       w9, [x22, #0xf64]
00002b1c  14f97fd3          lsl        x20, x8, #1
00002b20  3f010071          cmp        w9, #0
00002b24  88008052          mov        w8, #4
00002b28  09028052          mov        w9, #0x10
00002b2c  3711889a          csel       x23, x9, x8, ne
00002b30  e00315aa          mov        x0, x21
00002b34  e1031f2a          mov        w1, wzr
00002b38  e20314aa          mov        x2, x20
00002b3c  5d000094          bl         #0x2cb0
00002b40  f70600f1          subs       x23, x23, #1
00002b44  b5224091          add        x21, x21, #8, lsl #12
00002b48  41ffff54          b.ne       #0x2b30
00002b4c  680240f9          ldr        x8, [x19]
00002b50  e00313aa          mov        x0, x19
00002b54  61008052          mov        w1, #3
00002b58  080140f9          ldr        x8, [x8]
00002b5c  00013fd6          blr        x8
00002b60  60000034          cbz        w0, #0x2b6c
00002b64  e0031f2a          mov        w0, wzr
00002b68  11000014          b          #0x2bac
00002b6c  88000090          adrp       x8, #0x12000
00002b70  085d4fb9          ldr        w8, [x8, #0xf5c]
00002b74  09050051          sub        w9, w8, #1
00002b78  880800b0          adrp       x8, #0x113000
00002b7c  090900b9          str        w9, [x8, #8]
00002b80  090940b9          ldr        w9, [x8, #8]
00002b84  e900f836          tbz        w9, #0x1f, #0x2ba0
00002b88  c9927d39          ldrb       w9, [x22, #0xf64]
00002b8c  ea018052          mov        w10, #0xf
00002b90  3f010071          cmp        w9, #0
00002b94  69008052          mov        w9, #3
00002b98  4911891a          csel       w9, w10, w9, ne
00002b9c  090900b9          str        w9, [x8, #8]
00002ba0  88080090          adrp       x8, #0x112000
00002ba4  20008052          mov        w0, #1
00002ba8  00f13e39          strb       w0, [x8, #0xfbc]
00002bac  f44f43a9          ldp        x20, x19, [sp, #0x30]
00002bb0  f70b40f9          ldr        x23, [sp, #0x10]
00002bb4  f65742a9          ldp        x22, x21, [sp, #0x20]
00002bb8  fd7bc4a8          ldp        x29, x30, [sp], #0x40
00002bbc  c0035fd6          ret        
