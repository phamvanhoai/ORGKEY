# Library: libnsf2.so
# Function: Java_sofeh_music_NSF2_Close; bytes=20
000077a0  480000b0  adrp     x8, #0x10000
000077a4  004d43f9  ldr      x0, [x8, #0x698]
000077a8  400000b4  cbz      x0, #0x77b0
000077ac  c1020014  b        #0x82b0
000077b0  c0035fd6  ret      
