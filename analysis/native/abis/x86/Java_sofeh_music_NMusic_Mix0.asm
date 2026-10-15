# APK member: lib/x86/libnmusic.so
# Library SHA-256: 051762c43873d3b1e8d0eb96eb91cd82ffbc3e9ca211006f6977da7afa2b80a2
# Function: Java_sofeh_music_NMusic_Mix0; address=0x2850; bytes=18
# Decoder mode: default; decoded_bytes=18
00002850  55                push       ebp
00002851  89e5              mov        ebp, esp
00002853  83e4fc            and        esp, 0xfffffffc
00002856  0fbf4514          movsx      eax, word ptr [ebp + 0x14]
0000285a  0faf4510          imul       eax, dword ptr [ebp + 0x10]
0000285e  89ec              mov        esp, ebp
00002860  5d                pop        ebp
00002861  c3                ret        
