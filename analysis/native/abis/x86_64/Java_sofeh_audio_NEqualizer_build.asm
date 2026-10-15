# APK member: lib/x86_64/libnequalizer.so
# Library SHA-256: 5bb0dc8806216597eac5b39540e6e15563919ae6c029617a4d93d22790a8596c
# Function: Java_sofeh_audio_NEqualizer_build; address=0xa70; bytes=871
# Decoder mode: default; decoded_bytes=871
00000a70  4885d2            test       rdx, rdx
00000a73  0f845d030000      je         0xdd6
00000a79  55                push       rbp
00000a7a  4889e5            mov        rbp, rsp
00000a7d  53                push       rbx
00000a7e  50                push       rax
00000a7f  4889d3            mov        rbx, rdx
00000a82  f30f2ac1          cvtsi2ss   xmm0, ecx
00000a86  f30f100dc2fcffff  movss      xmm1, dword ptr [rip - 0x33e]
00000a8e  f30f5ec8          divss      xmm1, xmm0
00000a92  f30f114df4        movss      dword ptr [rbp - 0xc], xmm1
00000a97  f30f1005d1fcffff  movss      xmm0, dword ptr [rip - 0x32f]
00000a9f  f30f59c1          mulss      xmm0, xmm1
00000aa3  f30f5ac0          cvtss2sd   xmm0, xmm0
00000aa7  f20f5905e1fcffff  mulsd      xmm0, qword ptr [rip - 0x31f]
00000aaf  e8dc140000        call       0x1f90
00000ab4  f20f58c0          addsd      xmm0, xmm0
00000ab8  f20f5ac0          cvtsd2ss   xmm0, xmm0
00000abc  f30f114320        movss      dword ptr [rbx + 0x20], xmm0
00000ac1  c7434401000000    mov        dword ptr [rbx + 0x44], 1
00000ac8  0f57c9            xorps      xmm1, xmm1
00000acb  f30f2a0b          cvtsi2ss   xmm1, dword ptr [rbx]
00000acf  f30f590d8dfcffff  mulss      xmm1, dword ptr [rip - 0x373]
00000ad7  f30f114b40        movss      dword ptr [rbx + 0x40], xmm1
00000adc  f30f118360010000  movss      dword ptr [rbx + 0x160], xmm0
00000ae4  c7838401000001000000  mov        dword ptr [rbx + 0x184], 1
00000aee  f30f118b80010000  movss      dword ptr [rbx + 0x180], xmm1
00000af6  f30f10057efcffff  movss      xmm0, dword ptr [rip - 0x382]
00000afe  f30f5945f4        mulss      xmm0, dword ptr [rbp - 0xc]
00000b03  f30f5ac0          cvtss2sd   xmm0, xmm0
00000b07  f20f590571fcffff  mulsd      xmm0, qword ptr [rip - 0x38f]
00000b0f  e87c140000        call       0x1f90
00000b14  f20f58c0          addsd      xmm0, xmm0
00000b18  f20f5ac0          cvtsd2ss   xmm0, xmm0
00000b1c  f30f114348        movss      dword ptr [rbx + 0x48], xmm0
00000b21  c7436c01000000    mov        dword ptr [rbx + 0x6c], 1
00000b28  0f57c9            xorps      xmm1, xmm1
00000b2b  f30f2a4b04        cvtsi2ss   xmm1, dword ptr [rbx + 4]
00000b30  f30f590d2cfcffff  mulss      xmm1, dword ptr [rip - 0x3d4]
00000b38  f30f114b68        movss      dword ptr [rbx + 0x68], xmm1
00000b3d  f30f118388010000  movss      dword ptr [rbx + 0x188], xmm0
00000b45  c783ac01000001000000  mov        dword ptr [rbx + 0x1ac], 1
00000b4f  f30f118ba8010000  movss      dword ptr [rbx + 0x1a8], xmm1
00000b57  f30f1005f9fbffff  movss      xmm0, dword ptr [rip - 0x407]
00000b5f  f30f5945f4        mulss      xmm0, dword ptr [rbp - 0xc]
00000b64  f30f5ac0          cvtss2sd   xmm0, xmm0
00000b68  f20f590518fcffff  mulsd      xmm0, qword ptr [rip - 0x3e8]
00000b70  e81b140000        call       0x1f90
00000b75  f20f58c0          addsd      xmm0, xmm0
00000b79  f20f5ac0          cvtsd2ss   xmm0, xmm0
00000b7d  f30f114370        movss      dword ptr [rbx + 0x70], xmm0
00000b82  c7839400000001000000  mov        dword ptr [rbx + 0x94], 1
00000b8c  0f57c9            xorps      xmm1, xmm1
00000b8f  f30f2a4b08        cvtsi2ss   xmm1, dword ptr [rbx + 8]
00000b94  f30f590dc8fbffff  mulss      xmm1, dword ptr [rip - 0x438]
00000b9c  f30f118b90000000  movss      dword ptr [rbx + 0x90], xmm1
00000ba4  f30f1183b0010000  movss      dword ptr [rbx + 0x1b0], xmm0
00000bac  c783d401000001000000  mov        dword ptr [rbx + 0x1d4], 1
00000bb6  f30f118bd0010000  movss      dword ptr [rbx + 0x1d0], xmm1
00000bbe  f30f1005a2fbffff  movss      xmm0, dword ptr [rip - 0x45e]
00000bc6  f30f5945f4        mulss      xmm0, dword ptr [rbp - 0xc]
00000bcb  f30f5ac0          cvtss2sd   xmm0, xmm0
00000bcf  f20f5905b1fbffff  mulsd      xmm0, qword ptr [rip - 0x44f]
00000bd7  e8b4130000        call       0x1f90
00000bdc  f20f58c0          addsd      xmm0, xmm0
00000be0  f20f5ac0          cvtsd2ss   xmm0, xmm0
00000be4  f30f118398000000  movss      dword ptr [rbx + 0x98], xmm0
00000bec  c783bc00000001000000  mov        dword ptr [rbx + 0xbc], 1
00000bf6  0f57c9            xorps      xmm1, xmm1
00000bf9  f30f2a4b0c        cvtsi2ss   xmm1, dword ptr [rbx + 0xc]
00000bfe  f30f590d5efbffff  mulss      xmm1, dword ptr [rip - 0x4a2]
00000c06  f30f118bb8000000  movss      dword ptr [rbx + 0xb8], xmm1
00000c0e  f30f1183d8010000  movss      dword ptr [rbx + 0x1d8], xmm0
00000c16  c783fc01000001000000  mov        dword ptr [rbx + 0x1fc], 1
00000c20  f30f118bf8010000  movss      dword ptr [rbx + 0x1f8], xmm1
00000c28  f30f10053cfbffff  movss      xmm0, dword ptr [rip - 0x4c4]
00000c30  f30f5945f4        mulss      xmm0, dword ptr [rbp - 0xc]
00000c35  f30f5ac0          cvtss2sd   xmm0, xmm0
00000c39  f20f590557fbffff  mulsd      xmm0, qword ptr [rip - 0x4a9]
00000c41  e84a130000        call       0x1f90
00000c46  f20f58c0          addsd      xmm0, xmm0
00000c4a  f20f5ac0          cvtsd2ss   xmm0, xmm0
00000c4e  f30f1183c0000000  movss      dword ptr [rbx + 0xc0], xmm0
00000c56  c783e400000001000000  mov        dword ptr [rbx + 0xe4], 1
00000c60  0f57c9            xorps      xmm1, xmm1
00000c63  f30f2a4b10        cvtsi2ss   xmm1, dword ptr [rbx + 0x10]
00000c68  f30f590df4faffff  mulss      xmm1, dword ptr [rip - 0x50c]
00000c70  f30f118be0000000  movss      dword ptr [rbx + 0xe0], xmm1
00000c78  f30f118300020000  movss      dword ptr [rbx + 0x200], xmm0
00000c80  c7832402000001000000  mov        dword ptr [rbx + 0x224], 1
00000c8a  f30f118b20020000  movss      dword ptr [rbx + 0x220], xmm1
00000c92  f30f1005c2faffff  movss      xmm0, dword ptr [rip - 0x53e]
00000c9a  f30f5945f4        mulss      xmm0, dword ptr [rbp - 0xc]
00000c9f  f30f5ac0          cvtss2sd   xmm0, xmm0
00000ca3  f20f5905edfaffff  mulsd      xmm0, qword ptr [rip - 0x513]
00000cab  e8e0120000        call       0x1f90
00000cb0  f20f58c0          addsd      xmm0, xmm0
00000cb4  f20f5ac0          cvtsd2ss   xmm0, xmm0
00000cb8  f30f1183e8000000  movss      dword ptr [rbx + 0xe8], xmm0
00000cc0  c7830c01000001000000  mov        dword ptr [rbx + 0x10c], 1
00000cca  0f57c9            xorps      xmm1, xmm1
00000ccd  f30f2a4b14        cvtsi2ss   xmm1, dword ptr [rbx + 0x14]
00000cd2  f30f590d8afaffff  mulss      xmm1, dword ptr [rip - 0x576]
00000cda  f30f118b08010000  movss      dword ptr [rbx + 0x108], xmm1
00000ce2  f30f118328020000  movss      dword ptr [rbx + 0x228], xmm0
00000cea  c7834c02000001000000  mov        dword ptr [rbx + 0x24c], 1
00000cf4  f30f118b48020000  movss      dword ptr [rbx + 0x248], xmm1
00000cfc  f30f10055cfaffff  movss      xmm0, dword ptr [rip - 0x5a4]
00000d04  f30f5945f4        mulss      xmm0, dword ptr [rbp - 0xc]
00000d09  f30f5ac0          cvtss2sd   xmm0, xmm0
00000d0d  f20f590583faffff  mulsd      xmm0, qword ptr [rip - 0x57d]
00000d15  e876120000        call       0x1f90
00000d1a  f20f58c0          addsd      xmm0, xmm0
00000d1e  f20f5ac0          cvtsd2ss   xmm0, xmm0
00000d22  0f57c9            xorps      xmm1, xmm1
00000d25  f30f2a4b18        cvtsi2ss   xmm1, dword ptr [rbx + 0x18]
00000d2a  f30f118310010000  movss      dword ptr [rbx + 0x110], xmm0
00000d32  c7833401000001000000  mov        dword ptr [rbx + 0x134], 1
00000d3c  f30f590d20faffff  mulss      xmm1, dword ptr [rip - 0x5e0]
00000d44  f30f118b30010000  movss      dword ptr [rbx + 0x130], xmm1
00000d4c  f30f118350020000  movss      dword ptr [rbx + 0x250], xmm0
00000d54  c7837402000001000000  mov        dword ptr [rbx + 0x274], 1
00000d5e  f30f118b70020000  movss      dword ptr [rbx + 0x270], xmm1
00000d66  f30f1045f4        movss      xmm0, dword ptr [rbp - 0xc]
00000d6b  f30f590505faffff  mulss      xmm0, dword ptr [rip - 0x5fb]
00000d73  f30f5ac0          cvtss2sd   xmm0, xmm0
00000d77  f20f590519faffff  mulsd      xmm0, qword ptr [rip - 0x5e7]
00000d7f  e80c120000        call       0x1f90
00000d84  f20f58c0          addsd      xmm0, xmm0
00000d88  f20f5ac0          cvtsd2ss   xmm0, xmm0
00000d8c  0f57c9            xorps      xmm1, xmm1
00000d8f  f30f2a4b1c        cvtsi2ss   xmm1, dword ptr [rbx + 0x1c]
00000d94  f30f118338010000  movss      dword ptr [rbx + 0x138], xmm0
00000d9c  c7835c01000001000000  mov        dword ptr [rbx + 0x15c], 1
00000da6  f30f590db6f9ffff  mulss      xmm1, dword ptr [rip - 0x64a]
00000dae  f30f118b58010000  movss      dword ptr [rbx + 0x158], xmm1
00000db6  f30f118378020000  movss      dword ptr [rbx + 0x278], xmm0
00000dbe  c7839c02000001000000  mov        dword ptr [rbx + 0x29c], 1
00000dc8  f30f118b98020000  movss      dword ptr [rbx + 0x298], xmm1
00000dd0  4883c408          add        rsp, 8
00000dd4  5b                pop        rbx
00000dd5  5d                pop        rbp
00000dd6  c3                ret        
