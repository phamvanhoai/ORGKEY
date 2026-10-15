# Library: libnbandbooster.so
# Function: Java_sofeh_audio_NBandBooster_open; bytes=36
00000800  c20000b4  cbz      x2, #0x818
00000804  fd7bbfa9  stp      x29, x30, [sp, #-0x10]!
00000808  fd030091  mov      x29, sp
0000080c  e00302aa  mov      x0, x2
00000810  7c000094  bl       #0xa00
00000814  fd7bc1a8  ldp      x29, x30, [sp], #0x10
00000818  20008052  mov      w0, #1
0000081c  010b8052  mov      w1, #0x58
00000820  7c000014  b        #0xa10
