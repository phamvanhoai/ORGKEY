# APK member: lib/x86_64/libnpitchshift.so
# Library SHA-256: 2b07803806c26b8fb41dfd381a8c34b4f05d5cd476aabd180d5113a56462a43a
# Function: Java_sofeh_audio_NPitchShift_open; address=0x2000; bytes=89
# Decoder mode: default; decoded_bytes=89
00002000  55                push       rbp
00002001  4889e5            mov        rbp, rsp
00002004  4156              push       r14
00002006  53                push       rbx
00002007  4885d2            test       rdx, rdx
0000200a  7408              je         0x2014
0000200c  4889d7            mov        rdi, rdx
0000200f  e86c010000        call       0x2180
00002014  bf20600100        mov        edi, 0x16020
00002019  e842010000        call       0x2160
0000201e  4889c3            mov        rbx, rax
00002021  4c8db008b00000    lea        r14, [rax + 0xb008]
00002028  48c7801860010000000000  mov        qword ptr [rax + 0x16018], 0
00002033  ba08900000        mov        edx, 0x9008
00002038  4889c7            mov        rdi, rax
0000203b  31f6              xor        esi, esi
0000203d  e82e010000        call       0x2170
00002042  ba10900000        mov        edx, 0x9010
00002047  4c89f7            mov        rdi, r14
0000204a  31f6              xor        esi, esi
0000204c  e81f010000        call       0x2170
00002051  4889d8            mov        rax, rbx
00002054  5b                pop        rbx
00002055  415e              pop        r14
00002057  5d                pop        rbp
00002058  c3                ret        
