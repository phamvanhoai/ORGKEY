# Library: libnsf2.so
# Function: Java_sofeh_music_NSF2_PresetCount; bytes=28
000077b4  480000b0  adrp     x8, #0x10000
000077b8  084d43f9  ldr      x8, [x8, #0x698]
000077bc  680000b4  cbz      x8, #0x77c8
000077c0  002140b9  ldr      w0, [x8, #0x20]
000077c4  c0035fd6  ret      
000077c8  e0031f2a  mov      w0, wzr
000077cc  c0035fd6  ret      
