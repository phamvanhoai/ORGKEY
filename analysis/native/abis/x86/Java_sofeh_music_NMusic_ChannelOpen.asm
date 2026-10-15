# APK member: lib/x86/libnmusic.so
# Library SHA-256: 051762c43873d3b1e8d0eb96eb91cd82ffbc3e9ca211006f6977da7afa2b80a2
# Function: Java_sofeh_music_NMusic_ChannelOpen; address=0x29b0; bytes=70
# Decoder mode: default; decoded_bytes=70
000029b0  55                push       ebp
000029b1  89e5              mov        ebp, esp
000029b3  53                push       ebx
000029b4  56                push       esi
000029b5  83e4f0            and        esp, 0xfffffff0
000029b8  83ec10            sub        esp, 0x10
000029bb  e800000000        call       0x29c0
000029c0  5b                pop        ebx
000029c1  81c3e4250000      add        ebx, 0x25e4
000029c7  c7042418600100    mov        dword ptr [esp], 0x16018
000029ce  e82d140000        call       0x3e00
000029d3  89c6              mov        esi, eax
000029d5  890424            mov        dword ptr [esp], eax
000029d8  e853140000        call       0x3e30
000029dd  8d860cb00000      lea        eax, [esi + 0xb00c]
000029e3  890424            mov        dword ptr [esp], eax
000029e6  e845140000        call       0x3e30
000029eb  89f0              mov        eax, esi
000029ed  31d2              xor        edx, edx
000029ef  8d65f8            lea        esp, [ebp - 8]
000029f2  5e                pop        esi
000029f3  5b                pop        ebx
000029f4  5d                pop        ebp
000029f5  c3                ret        
