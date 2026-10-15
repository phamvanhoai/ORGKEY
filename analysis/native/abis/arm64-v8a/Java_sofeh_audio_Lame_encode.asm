# APK member: lib/arm64-v8a/libnlame.so
# Library SHA-256: 8fdbc059e6be58988d86df4e875f5c17f5ba3b93153afe7ef9c20bbfd409c9f9
# Function: Java_sofeh_audio_Lame_encode; address=0x35ec8; bytes=308
# Decoder mode: default; decoded_bytes=308
00035ec8  fd7bbaa9          stp        x29, x30, [sp, #-0x60]!
00035ecc  fb0b00f9          str        x27, [sp, #0x10]
00035ed0  fa6702a9          stp        x26, x25, [sp, #0x20]
00035ed4  f85f03a9          stp        x24, x23, [sp, #0x30]
00035ed8  f65704a9          stp        x22, x21, [sp, #0x40]
00035edc  f44f05a9          stp        x20, x19, [sp, #0x50]
00035ee0  fd030091          mov        x29, sp
00035ee4  080040f9          ldr        x8, [x0]
00035ee8  f70302aa          mov        x23, x2
00035eec  e10302aa          mov        x1, x2
00035ef0  e2031faa          mov        x2, xzr
00035ef4  f30305aa          mov        x19, x5
00035ef8  f603042a          mov        w22, w4
00035efc  08e942f9          ldr        x8, [x8, #0x5d0]
00035f00  f50303aa          mov        x21, x3
00035f04  f40300aa          mov        x20, x0
00035f08  00013fd6          blr        x8
00035f0c  880240f9          ldr        x8, [x20]
00035f10  f80300aa          mov        x24, x0
00035f14  e00314aa          mov        x0, x20
00035f18  e10315aa          mov        x1, x21
00035f1c  e2031faa          mov        x2, xzr
00035f20  08e942f9          ldr        x8, [x8, #0x5d0]
00035f24  00013fd6          blr        x8
00035f28  880240f9          ldr        x8, [x20]
00035f2c  f90300aa          mov        x25, x0
00035f30  e00314aa          mov        x0, x20
00035f34  e10313aa          mov        x1, x19
00035f38  08ad42f9          ldr        x8, [x8, #0x558]
00035f3c  00013fd6          blr        x8
00035f40  880240f9          ldr        x8, [x20]
00035f44  fa03002a          mov        w26, w0
00035f48  e00314aa          mov        x0, x20
00035f4c  e10313aa          mov        x1, x19
00035f50  e2031faa          mov        x2, xzr
00035f54  08e142f9          ldr        x8, [x8, #0x5c0]
00035f58  00013fd6          blr        x8
00035f5c  c80000f0          adrp       x8, #0x50000
00035f60  fb0300aa          mov        x27, x0
00035f64  e10318aa          mov        x1, x24
00035f68  089d42f9          ldr        x8, [x8, #0x538]
00035f6c  e20319aa          mov        x2, x25
00035f70  e303162a          mov        w3, w22
00035f74  e4031baa          mov        x4, x27
00035f78  e5031a2a          mov        w5, w26
00035f7c  e00308aa          mov        x0, x8
00035f80  b0020094          bl         #0x36a40
00035f84  880240f9          ldr        x8, [x20]
00035f88  f603002a          mov        w22, w0
00035f8c  e00314aa          mov        x0, x20
00035f90  e10317aa          mov        x1, x23
00035f94  e20318aa          mov        x2, x24
00035f98  e3031f2a          mov        w3, wzr
00035f9c  080943f9          ldr        x8, [x8, #0x610]
00035fa0  00013fd6          blr        x8
00035fa4  880240f9          ldr        x8, [x20]
00035fa8  e00314aa          mov        x0, x20
00035fac  e10315aa          mov        x1, x21
00035fb0  e20319aa          mov        x2, x25
00035fb4  e3031f2a          mov        w3, wzr
00035fb8  080943f9          ldr        x8, [x8, #0x610]
00035fbc  00013fd6          blr        x8
00035fc0  880240f9          ldr        x8, [x20]
00035fc4  e00314aa          mov        x0, x20
00035fc8  e10313aa          mov        x1, x19
00035fcc  e2031baa          mov        x2, x27
00035fd0  e3031f2a          mov        w3, wzr
00035fd4  080143f9          ldr        x8, [x8, #0x600]
00035fd8  00013fd6          blr        x8
00035fdc  e003162a          mov        w0, w22
00035fe0  f44f45a9          ldp        x20, x19, [sp, #0x50]
00035fe4  fb0b40f9          ldr        x27, [sp, #0x10]
00035fe8  f65744a9          ldp        x22, x21, [sp, #0x40]
00035fec  f85f43a9          ldp        x24, x23, [sp, #0x30]
00035ff0  fa6742a9          ldp        x26, x25, [sp, #0x20]
00035ff4  fd7bc6a8          ldp        x29, x30, [sp], #0x60
00035ff8  c0035fd6          ret        
