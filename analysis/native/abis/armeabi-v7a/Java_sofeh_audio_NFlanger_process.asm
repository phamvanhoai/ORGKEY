# APK member: lib/armeabi-v7a/libnflanger.so
# Library SHA-256: 4d4fabc33a8e9d24417f86d86f0e51c250d4ce0cc77fc1056a4f23b59e289534
# Function: Java_sofeh_audio_NFlanger_process; address=0x948; bytes=400
# Decoder mode: thumb; decoded_bytes=400
00000948  52ea0300          orrs.w     r0, r2, r3
0000094c  04bf              itt        eq
0000094e  0020              moveq      r0, #0
00000950  7047              bxeq       lr
00000952  f0b5              push       {r4, r5, r6, r7, lr}
00000954  03af              add        r7, sp, #0xc
00000956  2de9000b          push.w     {r8, sb, fp}
0000095a  2ded048b          vpush      {d8, d9}
0000095e  02f50269          add.w      sb, r2, #0x820
00000962  92ed001a          vldr       s2, [r2]
00000966  9fed590a          vldr       s0, [pc, #0x164]
0000096a  4cf6cd40          movw       r0, #0xcccd
0000096e  99ed002a          vldr       s4, [sb]
00000972  b8eec11a          vcvt.f32.s32 s2, s2
00000976  d7f80880          ldr.w      r8, [r7, #8]
0000097a  ccf6cc40          movt       r0, #0xcccc
0000097e  22ee000a          vmul.f32   s0, s4, s0
00000982  9569              ldr        r5, [r2, #0x18]
00000984  4cf6cc41          movw       r1, #0xcccc
00000988  02f62806          addw       r6, r2, #0x828
0000098c  02ee108a          vmov       s4, r8
00000990  c0f6cc41          movt       r1, #0xccc
00000994  6843              muls       r0, r5, r0
00000996  b8eec22a          vcvt.f32.s32 s4, s4
0000099a  20ee010a          vmul.f32   s0, s0, s2
0000099e  4feab000          ror.w      r0, r0, #2
000009a2  8842              cmp        r0, r1
000009a4  30ee028a          vadd.f32   s16, s0, s4
000009a8  86ed008a          vstr       s16, [r6]
000009ac  47d8              bhi        #0xa3e
000009ae  00ee105a          vmov       s0, r5
000009b2  02f61c00          addw       r0, r2, #0x81c
000009b6  92ed011a          vldr       s2, [r2, #4]
000009ba  1446              mov        r4, r2
000009bc  b8ee400a          vcvt.f32.u32 s0, s0
000009c0  90ed002a          vldr       s4, [r0]
000009c4  b8eec11a          vcvt.f32.s32 s2, s2
000009c8  92ed029a          vldr       s18, [r2, #8]
000009cc  22ee000a          vmul.f32   s0, s4, s0
000009d0  30ee010a          vadd.f32   s0, s0, s2
000009d4  f7eec00a          vcvt.f64.f32 d16, s0
000009d8  51ec300b          vmov       r0, r1, d16
000009dc  00f058e9          blx        #0xc90
000009e0  41ec300b          vmov       d16, r0, r1
000009e4  b0ee001a          vmov.f32   s2, #2.000000e+00
000009e8  b7eee00b          vcvt.f32.f64 s0, d16
000009ec  30ee000a          vadd.f32   s0, s0, s0
000009f0  30ee010a          vadd.f32   s0, s0, s2
000009f4  f7eec00a          vcvt.f64.f32 d16, s0
000009f8  51ec300b          vmov       r0, r1, d16
000009fc  00f050e9          blx        #0xca0
00000a00  41ec300b          vmov       d16, r0, r1
00000a04  9fed323a          vldr       s6, [pc, #0xc8]
00000a08  b8eec90a          vcvt.f32.s32 s0, s18
00000a0c  04f50360          add.w      r0, r4, #0x830
00000a10  bfee001a          vmov.f32   s2, #-1.000000e+00
00000a14  2246              mov        r2, r4
00000a16  b7eee02b          vcvt.f32.f64 s4, d16
00000a1a  20ee030a          vmul.f32   s0, s0, s6
00000a1e  32ee011a          vadd.f32   s2, s4, s2
00000a22  20ee010a          vmul.f32   s0, s0, s2
00000a26  90ed001a          vldr       s2, [r0]
00000a2a  04f61400          addw       r0, r4, #0x814
00000a2e  80ee010a          vdiv.f32   s0, s0, s2
00000a32  b7ee001a          vmov.f32   s2, #1.000000e+00
00000a36  30ee010a          vadd.f32   s0, s0, s2
00000a3a  80ed000a          vstr       s0, [r0]
00000a3e  d468              ldr        r4, [r2, #0xc]
00000a40  681c              adds       r0, r5, #1
00000a42  9061              str        r0, [r2, #0x18]
00000a44  012c              cmp        r4, #1
00000a46  1fdb              blt        #0xa88
00000a48  02f61403          addw       r3, r2, #0x814
00000a4c  02f61801          addw       r1, r2, #0x818
00000a50  02f11c05          add.w      r5, r2, #0x1c
00000a54  2046              mov        r0, r4
00000a56  93ed000a          vldr       s0, [r3]
00000a5a  95ed001a          vldr       s2, [r5]
00000a5e  0138              subs       r0, #1
00000a60  21ee000a          vmul.f32   s0, s2, s0
00000a64  81ed001a          vstr       s2, [r1]
00000a68  30ee082a          vadd.f32   s4, s0, s16
00000a6c  a5ec012a          vstmia     r5!, {s4}
00000a70  93ed000a          vldr       s0, [r3]
00000a74  20ee021a          vmul.f32   s2, s0, s4
00000a78  91ed002a          vldr       s4, [r1]
00000a7c  32ee418a          vsub.f32   s16, s4, s2
00000a80  86ed008a          vstr       s16, [r6]
00000a84  e9d1              bne        #0xa5a
00000a86  00e0              b          #0xa8a
00000a88  0024              movs       r4, #0
00000a8a  1069              ldr        r0, [r2, #0x10]
00000a8c  89ed008a          vstr       s16, [sb]
00000a90  5461              str        r4, [r2, #0x14]
00000a92  00ee100a          vmov       s0, r0
00000a96  c0f1ff00          rsb.w      r0, r0, #0xff
00000a9a  00fb08f0          mul        r0, r0, r8
00000a9e  b8eec00a          vcvt.f32.s32 s0, s0
00000aa2  01ee100a          vmov       s2, r0
00000aa6  28ee000a          vmul.f32   s0, s16, s0
00000aaa  b8eec11a          vcvt.f32.s32 s2, s2
00000aae  30ee010a          vadd.f32   s0, s0, s2
00000ab2  9fed081a          vldr       s2, [pc, #0x20]
00000ab6  20ee010a          vmul.f32   s0, s0, s2
00000aba  bdeec00a          vcvt.s32.f32 s0, s0
00000abe  10ee100a          vmov       r0, s0
00000ac2  bdec048b          vpop       {d8, d9}
00000ac6  bde8000b          pop.w      {r8, sb, fp}
00000aca  f0bd              pop        {r4, r5, r6, r7, pc}
00000acc  0ad7              bvc        #0xae4
00000ace  233c              subs       r4, #0x23
00000ad0  8180              strh       r1, [r0, #4]
00000ad2  80bb              cbnz       r0, #0xb36
00000ad4  8180              strh       r1, [r0, #4]
00000ad6  803b              subs       r3, #0x80
