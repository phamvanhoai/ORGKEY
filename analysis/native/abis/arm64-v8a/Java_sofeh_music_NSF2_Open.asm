# APK member: lib/arm64-v8a/libnsf2.so
# Library SHA-256: 8ea5a2f1833868953a9b4a52525e2544eefc6e94c98e1e9a8c344040d51139df
# Function: Java_sofeh_music_NSF2_Open; address=0x76f0; bytes=176
# Decoder mode: default; decoded_bytes=176
000076f0  ff4301d1          sub        sp, sp, #0x50
000076f4  fd7b02a9          stp        x29, x30, [sp, #0x20]
000076f8  f51b00f9          str        x21, [sp, #0x30]
000076fc  f44f04a9          stp        x20, x19, [sp, #0x40]
00007700  fd830091          add        x29, sp, #0x20
00007704  55d03bd5          mrs        x21, tpidr_el0
00007708  e10302aa          mov        x1, x2
0000770c  e2031faa          mov        x2, xzr
00007710  a81640f9          ldr        x8, [x21, #0x28]
00007714  a8831ff8          stur       x8, [x29, #-8]
00007718  080040f9          ldr        x8, [x0]
0000771c  08a542f9          ldr        x8, [x8, #0x548]
00007720  00013fd6          blr        x8
00007724  1f2003d5          nop        
00007728  88640210          adr        x8, #0xc3b8
0000772c  1f2003d5          nop        
00007730  41fcfc10          adr        x1, #0x16b8
00007734  0001c03d          ldr        q0, [x8]
00007738  080940f9          ldr        x8, [x8, #0x10]
0000773c  e003803d          str        q0, [sp]
00007740  e80b00f9          str        x8, [sp, #0x10]
00007744  ab020094          bl         #0x81f0
00007748  200100b4          cbz        x0, #0x776c
0000774c  e00300f9          str        x0, [sp]
00007750  f40300aa          mov        x20, x0
00007754  e0030091          mov        x0, sp
00007758  aa020094          bl         #0x8200
0000775c  f30300aa          mov        x19, x0
00007760  e00314aa          mov        x0, x20
00007764  ab020094          bl         #0x8210
00007768  02000014          b          #0x7770
0000776c  f3031faa          mov        x19, xzr
00007770  480000b0          adrp       x8, #0x10000
00007774  134d03f9          str        x19, [x8, #0x698]
00007778  a81640f9          ldr        x8, [x21, #0x28]
0000777c  a9835ff8          ldur       x9, [x29, #-8]
00007780  1f0109eb          cmp        x8, x9
00007784  c1000054          b.ne       #0x779c
00007788  f44f44a9          ldp        x20, x19, [sp, #0x40]
0000778c  f51b40f9          ldr        x21, [sp, #0x30]
00007790  fd7b42a9          ldp        x29, x30, [sp, #0x20]
00007794  ff430191          add        sp, sp, #0x50
00007798  c0035fd6          ret        
0000779c  a1020094          bl         #0x8220
