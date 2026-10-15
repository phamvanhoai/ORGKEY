# APK member: lib/x86_64/libnflanger.so
# Library SHA-256: d2a7cec8840993d6dc944a3f0999a7fe2667decd0004b0e78fc4f98b33ea053e
# Function: Java_sofeh_audio_NFlanger_build; address=0x9a0; bytes=93
# Decoder mode: default; decoded_bytes=93
000009a0  4885d2            test       rdx, rdx
000009a3  7457              je         0x9fc
000009a5  8b442420          mov        eax, dword ptr [rsp + 0x20]
000009a9  8b742418          mov        esi, dword ptr [rsp + 0x18]
000009ad  8b7c2410          mov        edi, dword ptr [rsp + 0x10]
000009b1  448b542408        mov        r10d, dword ptr [rsp + 8]
000009b6  890a              mov        dword ptr [rdx], ecx
000009b8  44894204          mov        dword ptr [rdx + 4], r8d
000009bc  44894a08          mov        dword ptr [rdx + 8], r9d
000009c0  4181faff000000    cmp        r10d, 0xff
000009c7  b9ff000000        mov        ecx, 0xff
000009cc  f30f2ac6          cvtsi2ss   xmm0, esi
000009d0  410f4cca          cmovl      ecx, r10d
000009d4  f30f590518fdffff  mulss      xmm0, dword ptr [rip - 0x2e8]
000009dc  894a0c            mov        dword ptr [rdx + 0xc], ecx
000009df  f30f2ac8          cvtsi2ss   xmm1, eax
000009e3  897a10            mov        dword ptr [rdx + 0x10], edi
000009e6  f30f5ec1          divss      xmm0, xmm1
000009ea  f30f11821c080000  movss      dword ptr [rdx + 0x81c], xmm0
000009f2  c7823008000081645642  mov        dword ptr [rdx + 0x830], 0x42566481
000009fc  c3                ret        
