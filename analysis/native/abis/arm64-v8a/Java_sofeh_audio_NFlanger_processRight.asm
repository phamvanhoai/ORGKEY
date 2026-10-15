# APK member: lib/arm64-v8a/libnflanger.so
# Library SHA-256: 0367d94dbc3607e1d98b5787c916e98cbc88d73a8aaa5f5653a4955aa92158b2
# Function: Java_sofeh_audio_NFlanger_processRight; address=0xb6c; bytes=184
# Decoder mode: default; decoded_bytes=184
00000b6c  820300b4          cbz        x2, #0xbdc
00000b70  48e19a52          mov        w8, #0xd70a
00000b74  402448bd          ldr        s0, [x2, #0x824]
00000b78  410040bd          ldr        s1, [x2]
00000b7c  6884a772          movk       w8, #0x3c23, lsl #16
00000b80  6300229e          scvtf      s3, x3
00000b84  0201271e          fmov       s2, w8
00000b88  21d8215e          scvtf      s1, s1
00000b8c  480c40b9          ldr        w8, [x2, #0xc]
00000b90  1f050071          cmp        w8, #1
00000b94  0008221e          fmul       s0, s0, s2
00000b98  000c011f          fmadd      s0, s0, s1, s3
00000b9c  402c08bd          str        s0, [x2, #0x82c]
00000ba0  2b020054          b.lt       #0xbe4
00000ba4  411448bd          ldr        s1, [x2, #0x814]
00000ba8  49601091          add        x9, x2, #0x418
00000bac  ea0308aa          mov        x10, x8
00000bb0  220140bd          ldr        s2, [x9]
00000bb4  4a0500f1          subs       x10, x10, #1
00000bb8  4000011f          fmadd      s0, s2, s1, s0
00000bbc  421808bd          str        s2, [x2, #0x818]
00000bc0  204500bc          str        s0, [x9], #4
00000bc4  421848bd          ldr        s2, [x2, #0x818]
00000bc8  411448bd          ldr        s1, [x2, #0x814]
00000bcc  2088001f          fmsub      s0, s1, s0, s2
00000bd0  402c08bd          str        s0, [x2, #0x82c]
00000bd4  e1feff54          b.ne       #0xbb0
00000bd8  04000014          b          #0xbe8
00000bdc  e0031faa          mov        x0, xzr
00000be0  c0035fd6          ret        
00000be4  e8031f2a          mov        w8, wzr
00000be8  491080b9          ldrsw      x9, [x2, #0x10]
00000bec  ea1f8052          mov        w10, #0xff
00000bf0  481400b9          str        w8, [x2, #0x14]
00000bf4  402408bd          str        s0, [x2, #0x824]
00000bf8  4a0109cb          sub        x10, x10, x9
00000bfc  2101221e          scvtf      s1, w9
00000c00  29109052          mov        w9, #0x8081
00000c04  4a7d039b          mul        x10, x10, x3
00000c08  0970a772          movk       w9, #0x3b80, lsl #16
00000c0c  4201229e          scvtf      s2, x10
00000c10  0108011f          fmadd      s1, s0, s1, s2
00000c14  2201271e          fmov       s2, w9
00000c18  2108221e          fmul       s1, s1, s2
00000c1c  2000389e          fcvtzs     x0, s1
00000c20  c0035fd6          ret        
