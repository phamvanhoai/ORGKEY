# APK member: lib/arm64-v8a/libnequalizer.so
# Library SHA-256: 8ea1d1bb9a47bdb7f785ccbca268dd7b2758ab07e14e89c818f91f4d0a1581cb
# Function: Java_sofeh_audio_NEqualizer_build; address=0xaa0; bytes=684
# Decoder mode: default; decoded_bytes=684
00000aa0  421500b4          cbz        x2, #0xd48
00000aa4  ea0f1cfc          str        d10, [sp, #-0x40]!
00000aa8  e923016d          stp        d9, d8, [sp, #0x10]
00000aac  fd7b02a9          stp        x29, x30, [sp, #0x20]
00000ab0  f44f03a9          stp        x20, x19, [sp, #0x30]
00000ab4  fd830091          add        x29, sp, #0x20
00000ab8  6000221e          scvtf      s0, w3
00000abc  01102e1e          fmov       s1, #1.00000000
00000ac0  08008852          mov        w8, #0x4000
00000ac4  8893a872          movk       w8, #0x449c, lsl #16
00000ac8  f30302aa          mov        x19, x2
00000acc  2818201e          fdiv       s8, s1, s0
00000ad0  0001271e          fmov       s0, w8
00000ad4  08000090          adrp       x8, #0
00000ad8  01a543fd          ldr        d1, [x8, #0x748]
00000adc  0009201e          fmul       s0, s8, s0
00000ae0  00c0221e          fcvt       d0, s0
00000ae4  0008611e          fmul       d0, d0, d1
00000ae8  c2030094          bl         #0x19f0
00000aec  08008852          mov        w8, #0x4000
00000af0  0028601e          fadd       d0, d0, d0
00000af4  610240bd          ldr        s1, [x19]
00000af8  88a3a872          movk       w8, #0x451c, lsl #16
00000afc  34008052          mov        w20, #1
00000b00  0201271e          fmov       s2, w8
00000b04  48e19a52          mov        w8, #0xd70a
00000b08  21d8215e          scvtf      s1, s1
00000b0c  6884a772          movk       w8, #0x3c23, lsl #16
00000b10  744600b9          str        w20, [x19, #0x44]
00000b14  0040621e          fcvt       s0, d0
00000b18  0901271e          fmov       s9, w8
00000b1c  08000090          adrp       x8, #0
00000b20  0209221e          fmul       s2, s8, s2
00000b24  039d43fd          ldr        d3, [x8, #0x738]
00000b28  748601b9          str        w20, [x19, #0x184]
00000b2c  2108291e          fmul       s1, s1, s9
00000b30  602200bd          str        s0, [x19, #0x20]
00000b34  42c0221e          fcvt       d2, s2
00000b38  606201bd          str        s0, [x19, #0x160]
00000b3c  614200bd          str        s1, [x19, #0x40]
00000b40  618201bd          str        s1, [x19, #0x180]
00000b44  4008631e          fmul       d0, d2, d3
00000b48  aa030094          bl         #0x19f0
00000b4c  08008c52          mov        w8, #0x6000
00000b50  0028601e          fadd       d0, d0, d0
00000b54  610640bd          ldr        s1, [x19, #4]
00000b58  48ada872          movk       w8, #0x456a, lsl #16
00000b5c  746e00b9          str        w20, [x19, #0x6c]
00000b60  0201271e          fmov       s2, w8
00000b64  21d8215e          scvtf      s1, s1
00000b68  08000090          adrp       x8, #0
00000b6c  0aa143fd          ldr        d10, [x8, #0x740]
00000b70  74ae01b9          str        w20, [x19, #0x1ac]
00000b74  0040621e          fcvt       s0, d0
00000b78  0209221e          fmul       s2, s8, s2
00000b7c  2108291e          fmul       s1, s1, s9
00000b80  604a00bd          str        s0, [x19, #0x48]
00000b84  42c0221e          fcvt       d2, s2
00000b88  608a01bd          str        s0, [x19, #0x188]
00000b8c  616a00bd          str        s1, [x19, #0x68]
00000b90  61aa01bd          str        s1, [x19, #0x1a8]
00000b94  40086a1e          fmul       d0, d2, d10
00000b98  96030094          bl         #0x19f0
00000b9c  08008852          mov        w8, #0x4000
00000ba0  0028601e          fadd       d0, d0, d0
00000ba4  610a40bd          ldr        s1, [x19, #8]
00000ba8  88b3a872          movk       w8, #0x459c, lsl #16
00000bac  749600b9          str        w20, [x19, #0x94]
00000bb0  0201271e          fmov       s2, w8
00000bb4  21d8215e          scvtf      s1, s1
00000bb8  74d601b9          str        w20, [x19, #0x1d4]
00000bbc  0340621e          fcvt       s3, d0
00000bc0  0209221e          fmul       s2, s8, s2
00000bc4  2108291e          fmul       s1, s1, s9
00000bc8  637200bd          str        s3, [x19, #0x70]
00000bcc  40c0221e          fcvt       d0, s2
00000bd0  63b201bd          str        s3, [x19, #0x1b0]
00000bd4  619200bd          str        s1, [x19, #0x90]
00000bd8  61d201bd          str        s1, [x19, #0x1d0]
00000bdc  00086a1e          fmul       d0, d0, d10
00000be0  84030094          bl         #0x19f0
00000be4  08008a52          mov        w8, #0x5000
00000be8  0028601e          fadd       d0, d0, d0
00000bec  610e40bd          ldr        s1, [x19, #0xc]
00000bf0  68b8a872          movk       w8, #0x45c3, lsl #16
00000bf4  74be00b9          str        w20, [x19, #0xbc]
00000bf8  0201271e          fmov       s2, w8
00000bfc  21d8215e          scvtf      s1, s1
00000c00  08000090          adrp       x8, #0
00000c04  0aa943fd          ldr        d10, [x8, #0x750]
00000c08  74fe01b9          str        w20, [x19, #0x1fc]
00000c0c  0040621e          fcvt       s0, d0
00000c10  0209221e          fmul       s2, s8, s2
00000c14  2108291e          fmul       s1, s1, s9
00000c18  609a00bd          str        s0, [x19, #0x98]
00000c1c  42c0221e          fcvt       d2, s2
00000c20  60da01bd          str        s0, [x19, #0x1d8]
00000c24  61ba00bd          str        s1, [x19, #0xb8]
00000c28  61fa01bd          str        s1, [x19, #0x1f8]
00000c2c  40086a1e          fmul       d0, d2, d10
00000c30  70030094          bl         #0x19f0
00000c34  08008c52          mov        w8, #0x6000
00000c38  0028601e          fadd       d0, d0, d0
00000c3c  611240bd          ldr        s1, [x19, #0x10]
00000c40  48bda872          movk       w8, #0x45ea, lsl #16
00000c44  74e600b9          str        w20, [x19, #0xe4]
00000c48  0201271e          fmov       s2, w8
00000c4c  21d8215e          scvtf      s1, s1
00000c50  742602b9          str        w20, [x19, #0x224]
00000c54  0040621e          fcvt       s0, d0
00000c58  0209221e          fmul       s2, s8, s2
00000c5c  2108291e          fmul       s1, s1, s9
00000c60  60c200bd          str        s0, [x19, #0xc0]
00000c64  42c0221e          fcvt       d2, s2
00000c68  600202bd          str        s0, [x19, #0x200]
00000c6c  61e200bd          str        s1, [x19, #0xe0]
00000c70  612202bd          str        s1, [x19, #0x220]
00000c74  40086a1e          fmul       d0, d2, d10
00000c78  5e030094          bl         #0x19f0
00000c7c  08009752          mov        w8, #0xb800
00000c80  0028601e          fadd       d0, d0, d0
00000c84  611640bd          ldr        s1, [x19, #0x14]
00000c88  08c1a872          movk       w8, #0x4608, lsl #16
00000c8c  740e01b9          str        w20, [x19, #0x10c]
00000c90  0201271e          fmov       s2, w8
00000c94  21d8215e          scvtf      s1, s1
00000c98  744e02b9          str        w20, [x19, #0x24c]
00000c9c  0040621e          fcvt       s0, d0
00000ca0  0209221e          fmul       s2, s8, s2
00000ca4  2108291e          fmul       s1, s1, s9
00000ca8  60ea00bd          str        s0, [x19, #0xe8]
00000cac  42c0221e          fcvt       d2, s2
00000cb0  602a02bd          str        s0, [x19, #0x228]
00000cb4  610a01bd          str        s1, [x19, #0x108]
00000cb8  614a02bd          str        s1, [x19, #0x248]
00000cbc  40086a1e          fmul       d0, d2, d10
00000cc0  4c030094          bl         #0x19f0
00000cc4  08008852          mov        w8, #0x4000
00000cc8  0028601e          fadd       d0, d0, d0
00000ccc  611a40bd          ldr        s1, [x19, #0x18]
00000cd0  88c3a872          movk       w8, #0x461c, lsl #16
00000cd4  743601b9          str        w20, [x19, #0x134]
00000cd8  0201271e          fmov       s2, w8
00000cdc  21d8215e          scvtf      s1, s1
00000ce0  747602b9          str        w20, [x19, #0x274]
00000ce4  0340621e          fcvt       s3, d0
00000ce8  0209221e          fmul       s2, s8, s2
00000cec  2108291e          fmul       s1, s1, s9
00000cf0  631201bd          str        s3, [x19, #0x110]
00000cf4  40c0221e          fcvt       d0, s2
00000cf8  635202bd          str        s3, [x19, #0x250]
00000cfc  613201bd          str        s1, [x19, #0x130]
00000d00  617202bd          str        s1, [x19, #0x270]
00000d04  00086a1e          fmul       d0, d0, d10
00000d08  3a030094          bl         #0x19f0
00000d0c  611e40bd          ldr        s1, [x19, #0x1c]
00000d10  0028601e          fadd       d0, d0, d0
00000d14  745e01b9          str        w20, [x19, #0x15c]
00000d18  749e02b9          str        w20, [x19, #0x29c]
00000d1c  21d8215e          scvtf      s1, s1
00000d20  0040621e          fcvt       s0, d0
00000d24  2108291e          fmul       s1, s1, s9
00000d28  603a01bd          str        s0, [x19, #0x138]
00000d2c  607a02bd          str        s0, [x19, #0x278]
00000d30  615a01bd          str        s1, [x19, #0x158]
00000d34  619a02bd          str        s1, [x19, #0x298]
00000d38  f44f43a9          ldp        x20, x19, [sp, #0x30]
00000d3c  fd7b42a9          ldp        x29, x30, [sp, #0x20]
00000d40  e923416d          ldp        d9, d8, [sp, #0x10]
00000d44  ea0744fc          ldr        d10, [sp], #0x40
00000d48  c0035fd6          ret        
