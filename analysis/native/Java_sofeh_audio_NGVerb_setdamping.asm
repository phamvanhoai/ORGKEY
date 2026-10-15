# Library: libngverb.so
# Function: Java_sofeh_audio_NGVerb_setdamping; bytes=44
00002ba8  420100b4  cbz      x2, #0x2bd0
00002bac  482440f9  ldr      x8, [x2, #0x48]
00002bb0  405000bd  str      s0, [x2, #0x50]
00002bb4  090140f9  ldr      x9, [x8]
00002bb8  0a0540f9  ldr      x10, [x8, #8]
00002bbc  200100bd  str      s0, [x9]
00002bc0  092141a9  ldp      x9, x8, [x8, #0x10]
00002bc4  400100bd  str      s0, [x10]
00002bc8  200100bd  str      s0, [x9]
00002bcc  000100bd  str      s0, [x8]
00002bd0  c0035fd6  ret      
