# Library: libngverb.so
# Function: Java_sofeh_audio_NGVerb_setinputbandwidth; bytes=28
00002bd4  c20000b4  cbz      x2, #0x2bec
00002bd8  01102e1e  fmov     s1, #1.00000000
00002bdc  480840f9  ldr      x8, [x2, #0x10]
00002be0  400400bd  str      s0, [x2, #4]
00002be4  2138201e  fsub     s1, s1, s0
00002be8  010100bd  str      s1, [x8]
00002bec  c0035fd6  ret      
