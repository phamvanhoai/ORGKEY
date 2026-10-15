# Library: libnequalizer.so
# Function: Java_sofeh_audio_NEqualizer_open; bytes=48
00000a38  fd7bbfa9  stp      x29, x30, [sp, #-0x10]!
00000a3c  fd030091  mov      x29, sp
00000a40  620000b4  cbz      x2, #0xa4c
00000a44  e00302aa  mov      x0, x2
00000a48  e2030094  bl       #0x19d0
00000a4c  20008052  mov      w0, #1
00000a50  01548052  mov      w1, #0x2a0
00000a54  e3030094  bl       #0x19e0
00000a58  8004034f  movi     v0.4s, #0x64
00000a5c  000000ad  stp      q0, q0, [x0]
00000a60  fd7bc1a8  ldp      x29, x30, [sp], #0x10
00000a64  c0035fd6  ret      
