# APK member: lib/armeabi-v7a/libngverb.so
# Library SHA-256: f4be624ae221f556beaa4e0e3c9dd6b359e44e97d630f47aa9e74fe9a3990748
# Function: Java_sofeh_audio_NGVerb_setdamping; address=0x1bf4; bytes=36
# Decoder mode: thumb; decoded_bytes=36
00001bf4  52ea0300          orrs.w     r0, r2, r3
00001bf8  0dd0              beq        #0x1c16
00001bfa  506b              ldr        r0, [r2, #0x34]
00001bfc  0021              movs       r1, #0
00001bfe  9ded000a          vldr       s0, [sp]
00001c02  82ed0e0a          vstr       s0, [r2, #0x38]
00001c06  0429              cmp        r1, #4
00001c08  05d0              beq        #0x1c16
00001c0a  50f82120          ldr.w      r2, [r0, r1, lsl #2]
00001c0e  0131              adds       r1, #1
00001c10  82ed000a          vstr       s0, [r2]
00001c14  f7e7              b          #0x1c06
00001c16  7047              bx         lr
