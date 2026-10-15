# APK member: lib/arm64-v8a/libnpitchshift.so
# Library SHA-256: 25dff5430d9ff9db374fd01ab3fc879c7215eca59ad5e181fa54ca3dbd3b5cc0
# Function: smbPitchShiftNew; address=0xe30; bytes=40
00000e30  fd7bbfa9  stp      x29, x30, [sp, #-0x10]!
00000e34  fd030091  mov      x29, sp
00000e38  00029652  mov      w0, #0xb010
00000e3c  01040094  bl       #0x1e40
00000e40  08019652  mov      w8, #0xb008
00000e44  e1031f2a  mov      w1, wzr
00000e48  02019252  mov      w2, #0x9008
00000e4c  1f6828f8  str      xzr, [x0, x8]
00000e50  fd7bc1a8  ldp      x29, x30, [sp], #0x10
00000e54  ff030014  b        #0x1e50
