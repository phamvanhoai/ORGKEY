# APK member: lib/x86_64/libnsf2.so
# Library SHA-256: b145c2ce82b69909417b8510a31da8408b7f0696fd62ab62f1ed49c7c76ec3b8
# Function: Java_sofeh_music_NSF2_PresetCount; address=0x8a80; bytes=19
# Decoder mode: default; decoded_bytes=19
00008a80  488b05d98e0000    mov        rax, qword ptr [rip + 0x8ed9]
00008a87  4885c0            test       rax, rax
00008a8a  7404              je         0x8a90
00008a8c  8b4020            mov        eax, dword ptr [rax + 0x20]
00008a8f  c3                ret        
00008a90  31c0              xor        eax, eax
00008a92  c3                ret        
