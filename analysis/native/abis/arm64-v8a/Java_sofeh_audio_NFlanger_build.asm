# APK member: lib/arm64-v8a/libnflanger.so
# Library SHA-256: 0367d94dbc3607e1d98b5787c916e98cbc88d73a8aaa5f5653a4955aa92158b2
# Function: Java_sofeh_audio_NFlanger_build; address=0x984; bytes=84
# Decoder mode: default; decoded_bytes=84
00000984  820200b4          cbz        x2, #0x9d4
00000988  e80340b9          ldr        w8, [sp]
0000098c  892f9b52          mov        w9, #0xd97c
00000990  dffc0371          cmp        w6, #0xff
00000994  09c4a772          movk       w9, #0x3e20, lsl #16
00000998  43100029          stp        w3, w4, [x2]
0000099c  0001221e          scvtf      s0, w8
000009a0  2101271e          fmov       s1, w9
000009a4  e80b40b9          ldr        w8, [sp, #8]
000009a8  450800b9          str        w5, [x2, #8]
000009ac  0008211e          fmul       s0, s0, s1
000009b0  0101221e          scvtf      s1, w8
000009b4  e81f8052          mov        w8, #0xff
000009b8  c8b0881a          csel       w8, w6, w8, lt
000009bc  489c0129          stp        w8, w7, [x2, #0xc]
000009c0  28908c52          mov        w8, #0x6481
000009c4  c84aa872          movk       w8, #0x4256, lsl #16
000009c8  0018211e          fdiv       s0, s0, s1
000009cc  483008b9          str        w8, [x2, #0x830]
000009d0  401c08bd          str        s0, [x2, #0x81c]
000009d4  c0035fd6          ret        
