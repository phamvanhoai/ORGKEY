# APK member: lib/arm64-v8a/libnflanger.so
# Library SHA-256: 0367d94dbc3607e1d98b5787c916e98cbc88d73a8aaa5f5653a4955aa92158b2
# Function: Java_sofeh_audio_NFlanger_processLeft; address=0x9d8; bytes=404
# Decoder mode: default; decoded_bytes=404
000009d8  820500b4          cbz        x2, #0xa88
000009dc  e80f1dfc          str        d8, [sp, #-0x30]!
000009e0  fdfb00a9          stp        x29, x30, [sp, #8]
000009e4  f50f00f9          str        x21, [sp, #0x18]
000009e8  f44f02a9          stp        x20, x19, [sp, #0x20]
000009ec  fd230091          add        x29, sp, #8
000009f0  48e19a52          mov        w8, #0xd70a
000009f4  402048bd          ldr        s0, [x2, #0x820]
000009f8  410040bd          ldr        s1, [x2]
000009fc  6884a772          movk       w8, #0x3c23, lsl #16
00000a00  6200229e          scvtf      s2, x3
00000a04  531840b9          ldr        w19, [x2, #0x18]
00000a08  0301271e          fmov       s3, w8
00000a0c  21d8215e          scvtf      s1, s1
00000a10  a8999952          mov        w8, #0xcccd
00000a14  8899b972          movk       w8, #0xcccc, lsl #16
00000a18  89999952          mov        w9, #0xcccc
00000a1c  687e081b          mul        w8, w19, w8
00000a20  8999a172          movk       w9, #0xccc, lsl #16
00000a24  0008231e          fmul       s0, s0, s3
00000a28  08098813          ror        w8, w8, #2
00000a2c  0808011f          fmadd      s8, s0, s1, s2
00000a30  1f01096b          cmp        w8, w9
00000a34  482808bd          str        s8, [x2, #0x828]
00000a38  c9020054          b.ls       #0xa90
00000a3c  480c40b9          ldr        w8, [x2, #0xc]
00000a40  69060011          add        w9, w19, #1
00000a44  491800b9          str        w9, [x2, #0x18]
00000a48  1f050071          cmp        w8, #1
00000a4c  8b060054          b.lt       #0xb1c
00000a50  401448bd          ldr        s0, [x2, #0x814]
00000a54  49700091          add        x9, x2, #0x1c
00000a58  ea0308aa          mov        x10, x8
00000a5c  210140bd          ldr        s1, [x9]
00000a60  4a0500f1          subs       x10, x10, #1
00000a64  2220001f          fmadd      s2, s1, s0, s8
00000a68  411808bd          str        s1, [x2, #0x818]
00000a6c  224500bc          str        s2, [x9], #4
00000a70  411848bd          ldr        s1, [x2, #0x818]
00000a74  401448bd          ldr        s0, [x2, #0x814]
00000a78  0884021f          fmsub      s8, s0, s2, s1
00000a7c  482808bd          str        s8, [x2, #0x828]
00000a80  e1feff54          b.ne       #0xa5c
00000a84  27000014          b          #0xb20
00000a88  e0031faa          mov        x0, xzr
00000a8c  c0035fd6          ret        
00000a90  400440bd          ldr        s0, [x2, #4]
00000a94  6102231e          ucvtf      s1, w19
00000a98  421c48bd          ldr        s2, [x2, #0x81c]
00000a9c  f40302aa          mov        x20, x2
00000aa0  f50303aa          mov        x21, x3
00000aa4  00d8215e          scvtf      s0, s0
00000aa8  4000011f          fmadd      s0, s2, s1, s0
00000aac  00c0221e          fcvt       d0, s0
00000ab0  e4000094          bl         #0xe40
00000ab4  0040621e          fcvt       s0, d0
00000ab8  0110201e          fmov       s1, #2.00000000
00000abc  0028201e          fadd       s0, s0, s0
00000ac0  0028211e          fadd       s0, s0, s1
00000ac4  00c0221e          fcvt       d0, s0
00000ac8  e2000094          bl         #0xe50
00000acc  810a40bd          ldr        s1, [x20, #8]
00000ad0  28109052          mov        w8, #0x8081
00000ad4  0040621e          fcvt       s0, d0
00000ad8  0870b772          movk       w8, #0xbb80, lsl #16
00000adc  e30315aa          mov        x3, x21
00000ae0  e20314aa          mov        x2, x20
00000ae4  21d8215e          scvtf      s1, s1
00000ae8  0201271e          fmov       s2, w8
00000aec  2108221e          fmul       s1, s1, s2
00000af0  0084211f          fnmsub     s0, s0, s1, s1
00000af4  813248bd          ldr        s1, [x20, #0x830]
00000af8  0018211e          fdiv       s0, s0, s1
00000afc  01102e1e          fmov       s1, #1.00000000
00000b00  0028211e          fadd       s0, s0, s1
00000b04  801608bd          str        s0, [x20, #0x814]
00000b08  880e40b9          ldr        w8, [x20, #0xc]
00000b0c  69060011          add        w9, w19, #1
00000b10  891a00b9          str        w9, [x20, #0x18]
00000b14  1f050071          cmp        w8, #1
00000b18  caf9ff54          b.ge       #0xa50
00000b1c  e8031f2a          mov        w8, wzr
00000b20  491080b9          ldrsw      x9, [x2, #0x10]
00000b24  ea1f8052          mov        w10, #0xff
00000b28  481400b9          str        w8, [x2, #0x14]
00000b2c  4a0109cb          sub        x10, x10, x9
00000b30  2001221e          scvtf      s0, w9
00000b34  29109052          mov        w9, #0x8081
00000b38  4a7d039b          mul        x10, x10, x3
00000b3c  0970a772          movk       w9, #0x3b80, lsl #16
00000b40  4101229e          scvtf      s1, x10
00000b44  0005001f          fmadd      s0, s8, s0, s1
00000b48  2101271e          fmov       s1, w9
00000b4c  0008211e          fmul       s0, s0, s1
00000b50  0000389e          fcvtzs     x0, s0
00000b54  482008bd          str        s8, [x2, #0x820]
00000b58  f44f42a9          ldp        x20, x19, [sp, #0x20]
00000b5c  f50f40f9          ldr        x21, [sp, #0x18]
00000b60  fdfb40a9          ldp        x29, x30, [sp, #8]
00000b64  e80743fc          ldr        d8, [sp], #0x30
00000b68  c0035fd6          ret        
