# APK member: lib/arm64-v8a/libnflanger.so
# Library SHA-256: 0367d94dbc3607e1d98b5787c916e98cbc88d73a8aaa5f5653a4955aa92158b2
# Function: Java_sofeh_audio_NFlanger_process; address=0xc24; bytes=400
# Decoder mode: default; decoded_bytes=400
00000c24  a20500b4          cbz        x2, #0xcd8
00000c28  e80f1dfc          str        d8, [sp, #-0x30]!
00000c2c  fdfb00a9          stp        x29, x30, [sp, #8]
00000c30  f50f00f9          str        x21, [sp, #0x18]
00000c34  f44f02a9          stp        x20, x19, [sp, #0x20]
00000c38  fd230091          add        x29, sp, #8
00000c3c  48e19a52          mov        w8, #0xd70a
00000c40  733c0013          sxth       w19, w3
00000c44  402048bd          ldr        s0, [x2, #0x820]
00000c48  6884a772          movk       w8, #0x3c23, lsl #16
00000c4c  410040bd          ldr        s1, [x2]
00000c50  541840b9          ldr        w20, [x2, #0x18]
00000c54  0201271e          fmov       s2, w8
00000c58  6302221e          scvtf      s3, w19
00000c5c  a8999952          mov        w8, #0xcccd
00000c60  21d8215e          scvtf      s1, s1
00000c64  8899b972          movk       w8, #0xcccc, lsl #16
00000c68  89999952          mov        w9, #0xcccc
00000c6c  887e081b          mul        w8, w20, w8
00000c70  8999a172          movk       w9, #0xccc, lsl #16
00000c74  0008221e          fmul       s0, s0, s2
00000c78  08098813          ror        w8, w8, #2
00000c7c  080c011f          fmadd      s8, s0, s1, s3
00000c80  1f01096b          cmp        w8, w9
00000c84  482808bd          str        s8, [x2, #0x828]
00000c88  c9020054          b.ls       #0xce0
00000c8c  480c40b9          ldr        w8, [x2, #0xc]
00000c90  89060011          add        w9, w20, #1
00000c94  491800b9          str        w9, [x2, #0x18]
00000c98  1f050071          cmp        w8, #1
00000c9c  4b060054          b.lt       #0xd64
00000ca0  401448bd          ldr        s0, [x2, #0x814]
00000ca4  49700091          add        x9, x2, #0x1c
00000ca8  ea0308aa          mov        x10, x8
00000cac  210140bd          ldr        s1, [x9]
00000cb0  4a0500f1          subs       x10, x10, #1
00000cb4  2220001f          fmadd      s2, s1, s0, s8
00000cb8  411808bd          str        s1, [x2, #0x818]
00000cbc  224500bc          str        s2, [x9], #4
00000cc0  411848bd          ldr        s1, [x2, #0x818]
00000cc4  401448bd          ldr        s0, [x2, #0x814]
00000cc8  0884021f          fmsub      s8, s0, s2, s1
00000ccc  482808bd          str        s8, [x2, #0x828]
00000cd0  e1feff54          b.ne       #0xcac
00000cd4  25000014          b          #0xd68
00000cd8  e0031f2a          mov        w0, wzr
00000cdc  c0035fd6          ret        
00000ce0  400440bd          ldr        s0, [x2, #4]
00000ce4  8102231e          ucvtf      s1, w20
00000ce8  421c48bd          ldr        s2, [x2, #0x81c]
00000cec  f50302aa          mov        x21, x2
00000cf0  00d8215e          scvtf      s0, s0
00000cf4  4000011f          fmadd      s0, s2, s1, s0
00000cf8  00c0221e          fcvt       d0, s0
00000cfc  51000094          bl         #0xe40
00000d00  0040621e          fcvt       s0, d0
00000d04  0110201e          fmov       s1, #2.00000000
00000d08  0028201e          fadd       s0, s0, s0
00000d0c  0028211e          fadd       s0, s0, s1
00000d10  00c0221e          fcvt       d0, s0
00000d14  4f000094          bl         #0xe50
00000d18  a10a40bd          ldr        s1, [x21, #8]
00000d1c  28109052          mov        w8, #0x8081
00000d20  0040621e          fcvt       s0, d0
00000d24  0870b772          movk       w8, #0xbb80, lsl #16
00000d28  e20315aa          mov        x2, x21
00000d2c  21d8215e          scvtf      s1, s1
00000d30  0201271e          fmov       s2, w8
00000d34  2108221e          fmul       s1, s1, s2
00000d38  0084211f          fnmsub     s0, s0, s1, s1
00000d3c  a13248bd          ldr        s1, [x21, #0x830]
00000d40  0018211e          fdiv       s0, s0, s1
00000d44  01102e1e          fmov       s1, #1.00000000
00000d48  0028211e          fadd       s0, s0, s1
00000d4c  a01608bd          str        s0, [x21, #0x814]
00000d50  a80e40b9          ldr        w8, [x21, #0xc]
00000d54  89060011          add        w9, w20, #1
00000d58  a91a00b9          str        w9, [x21, #0x18]
00000d5c  1f050071          cmp        w8, #1
00000d60  0afaff54          b.ge       #0xca0
00000d64  e8031f2a          mov        w8, wzr
00000d68  491040b9          ldr        w9, [x2, #0x10]
00000d6c  ea1f8052          mov        w10, #0xff
00000d70  481400b9          str        w8, [x2, #0x14]
00000d74  4a01094b          sub        w10, w10, w9
00000d78  2001221e          scvtf      s0, w9
00000d7c  29109052          mov        w9, #0x8081
00000d80  4a7d131b          mul        w10, w10, w19
00000d84  0970a772          movk       w9, #0x3b80, lsl #16
00000d88  4101221e          scvtf      s1, w10
00000d8c  0005001f          fmadd      s0, s8, s0, s1
00000d90  2101271e          fmov       s1, w9
00000d94  0008211e          fmul       s0, s0, s1
00000d98  0000381e          fcvtzs     w0, s0
00000d9c  482008bd          str        s8, [x2, #0x820]
00000da0  f44f42a9          ldp        x20, x19, [sp, #0x20]
00000da4  f50f40f9          ldr        x21, [sp, #0x18]
00000da8  fdfb40a9          ldp        x29, x30, [sp, #8]
00000dac  e80743fc          ldr        d8, [sp], #0x30
00000db0  c0035fd6          ret        
