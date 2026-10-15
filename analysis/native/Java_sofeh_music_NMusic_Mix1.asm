# Library: libnmusic.so
# Function: Java_sofeh_music_NMusic_Mix1; bytes=32
0000233c  43cc029e  scvtf    s3, x2, #0xd
00002340  6400221e  scvtf    s4, w3
00002344  4238211e  fsub     s2, s2, s1
00002348  6338241e  fsub     s3, s3, s4
0000234c  6104021f  fmadd    s1, s3, s2, s1
00002350  2008201e  fmul     s0, s1, s0
00002354  0000381e  fcvtzs   w0, s0
00002358  c0035fd6  ret      
