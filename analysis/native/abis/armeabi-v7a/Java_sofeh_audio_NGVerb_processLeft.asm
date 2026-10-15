# APK member: lib/armeabi-v7a/libngverb.so
# Library SHA-256: f4be624ae221f556beaa4e0e3c9dd6b359e44e97d630f47aa9e74fe9a3990748
# Function: Java_sofeh_audio_NGVerb_processLeft; address=0x14b0; bytes=116
# Decoder mode: thumb; decoded_bytes=116
000014b0  dfb5              push       {r0, r1, r2, r3, r4, r6, r7, lr}
000014b2  06af              add        r7, sp, #0x18
000014b4  1948              ldr        r0, [pc, #0x64]
000014b6  7844              add        r0, pc
000014b8  0068              ldr        r0, [r0]
000014ba  0068              ldr        r0, [r0]
000014bc  0390              str        r0, [sp, #0xc]
000014be  52ea0300          orrs.w     r0, r2, r3
000014c2  1ad0              beq        #0x14fa
000014c4  d7e90201          ldrd       r0, r1, [r7, #8]
000014c8  1446              mov        r4, r2
000014ca  00f056ec          blx        #0x1d78
000014ce  00ee100a          vmov       s0, r0
000014d2  9fed101a          vldr       s2, [pc, #0x40]
000014d6  02a9              add        r1, sp, #8
000014d8  01aa              add        r2, sp, #4
000014da  20ee010a          vmul.f32   s0, s0, s2
000014de  2046              mov        r0, r4
000014e0  00f020f8          bl         #0x1524
000014e4  9fed0c0a          vldr       s0, [pc, #0x30]
000014e8  9ded021a          vldr       s2, [sp, #8]
000014ec  21ee000a          vmul.f32   s0, s2, s0
000014f0  10ee100a          vmov       r0, s0
000014f4  00f0feeb          blx        #0x1cf4
000014f8  01e0              b          #0x14fe
000014fa  0020              movs       r0, #0
000014fc  0021              movs       r1, #0
000014fe  039a              ldr        r2, [sp, #0xc]
00001500  074b              ldr        r3, [pc, #0x1c]
00001502  7b44              add        r3, pc
00001504  1b68              ldr        r3, [r3]
00001506  1b68              ldr        r3, [r3]
00001508  9342              cmp        r3, r2
0000150a  04bf              itt        eq
0000150c  04b0              addeq      sp, #0x10
0000150e  d0bd              popeq      {r4, r6, r7, pc}
00001510  00f0c6ee          blx        #0x22a0
00001514  0000              movs       r0, r0
00001516  0038              subs       r0, #0
00001518  0000              movs       r0, r0
0000151a  0047              bx         r0
0000151c  d61e              subs       r6, r2, #3
0000151e  0000              movs       r0, r0
00001520  8a1e              subs       r2, r1, #2
00001522  0000              movs       r0, r0
