# APK member: lib/armeabi-v7a/libngverb.so
# Library SHA-256: f4be624ae221f556beaa4e0e3c9dd6b359e44e97d630f47aa9e74fe9a3990748
# Function: Java_sofeh_audio_NGVerb_processRight; address=0x1760; bytes=116
# Decoder mode: thumb; decoded_bytes=116
00001760  dfb5              push       {r0, r1, r2, r3, r4, r6, r7, lr}
00001762  06af              add        r7, sp, #0x18
00001764  1948              ldr        r0, [pc, #0x64]
00001766  7844              add        r0, pc
00001768  0068              ldr        r0, [r0]
0000176a  0068              ldr        r0, [r0]
0000176c  0390              str        r0, [sp, #0xc]
0000176e  52ea0300          orrs.w     r0, r2, r3
00001772  1ad0              beq        #0x17aa
00001774  d7e90201          ldrd       r0, r1, [r7, #8]
00001778  1446              mov        r4, r2
0000177a  00f0feea          blx        #0x1d78
0000177e  00ee100a          vmov       s0, r0
00001782  9fed101a          vldr       s2, [pc, #0x40]
00001786  01a9              add        r1, sp, #4
00001788  02aa              add        r2, sp, #8
0000178a  20ee010a          vmul.f32   s0, s0, s2
0000178e  2046              mov        r0, r4
00001790  fff7c8fe          bl         #0x1524
00001794  9fed0c0a          vldr       s0, [pc, #0x30]
00001798  9ded021a          vldr       s2, [sp, #8]
0000179c  21ee000a          vmul.f32   s0, s2, s0
000017a0  10ee100a          vmov       r0, s0
000017a4  00f0a6ea          blx        #0x1cf4
000017a8  01e0              b          #0x17ae
000017aa  0020              movs       r0, #0
000017ac  0021              movs       r1, #0
000017ae  039a              ldr        r2, [sp, #0xc]
000017b0  074b              ldr        r3, [pc, #0x1c]
000017b2  7b44              add        r3, pc
000017b4  1b68              ldr        r3, [r3]
000017b6  1b68              ldr        r3, [r3]
000017b8  9342              cmp        r3, r2
000017ba  04bf              itt        eq
000017bc  04b0              addeq      sp, #0x10
000017be  d0bd              popeq      {r4, r6, r7, pc}
000017c0  00f06eed          blx        #0x22a0
000017c4  0000              movs       r0, r0
000017c6  0038              subs       r0, #0
000017c8  0000              movs       r0, r0
000017ca  0047              bx         r0
000017cc  261c              adds       r6, r4, #0
000017ce  0000              movs       r0, r0
000017d0  da1b              subs       r2, r3, r7
000017d2  0000              movs       r0, r0
