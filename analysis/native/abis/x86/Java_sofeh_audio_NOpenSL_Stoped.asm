# APK member: lib/x86/libnopensl.so
# Library SHA-256: 81e6220973b454100123e9259f13717edc34b683f704a7e2f342ff6424ed7706
# Function: Java_sofeh_audio_NOpenSL_Stoped; address=0x2980; bytes=32
# Decoder mode: default; decoded_bytes=32
00002980  55                push       ebp
00002981  89e5              mov        ebp, esp
00002983  83e4fc            and        esp, 0xfffffffc
00002986  e800000000        call       0x298b
0000298b  58                pop        eax
0000298c  81c011140000      add        eax, 0x1411
00002992  83b85c90000000    cmp        dword ptr [eax + 0x905c], 0
00002999  0f94c0            sete       al
0000299c  89ec              mov        esp, ebp
0000299e  5d                pop        ebp
0000299f  c3                ret        
