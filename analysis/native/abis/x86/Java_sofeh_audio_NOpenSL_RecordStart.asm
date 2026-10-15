# APK member: lib/x86/libnopensl.so
# Library SHA-256: 81e6220973b454100123e9259f13717edc34b683f704a7e2f342ff6424ed7706
# Function: Java_sofeh_audio_NOpenSL_RecordStart; address=0x29a0; bytes=481
# Decoder mode: default; decoded_bytes=481
000029a0  55                push       ebp
000029a1  89e5              mov        ebp, esp
000029a3  53                push       ebx
000029a4  57                push       edi
000029a5  56                push       esi
000029a6  83e4f0            and        esp, 0xfffffff0
000029a9  83ec20            sub        esp, 0x20
000029ac  e800000000        call       0x29b1
000029b1  5b                pop        ebx
000029b2  81c3eb130000      add        ebx, 0x13eb
000029b8  8b8b90901000      mov        ecx, dword ptr [ebx + 0x109090]
000029be  31c0              xor        eax, eax
000029c0  85c9              test       ecx, ecx
000029c2  0f84b1010000      je         0x2b79
000029c8  83bb6490080000    cmp        dword ptr [ebx + 0x89064], 0
000029cf  0f84a4010000      je         0x2b79
000029d5  894c2410          mov        dword ptr [esp + 0x10], ecx
000029d9  8bbb4c900000      mov        edi, dword ptr [ebx + 0x904c]
000029df  01ff              add        edi, edi
000029e1  31d2              xor        edx, edx
000029e3  80bb5890000000    cmp        byte ptr [ebx + 0x9058], 0
000029ea  b810000000        mov        eax, 0x10
000029ef  b904000000        mov        ecx, 4
000029f4  0f45c8            cmovne     ecx, eax
000029f7  894c240c          mov        dword ptr [esp + 0xc], ecx
000029fb  0f84d9000000      je         0x2ada
00002a01  8b44240c          mov        eax, dword ptr [esp + 0xc]
00002a05  83e0f0            and        eax, 0xfffffff0
00002a08  89442414          mov        dword ptr [esp + 0x14], eax
00002a0c  31d2              xor        edx, edx
00002a0e  8db368100c00      lea        esi, [ebx + 0xc1068]
00002a14  90                nop        
00002a15  90                nop        
00002a16  90                nop        
00002a17  90                nop        
00002a18  90                nop        
00002a19  90                nop        
00002a1a  90                nop        
00002a1b  90                nop        
00002a1c  90                nop        
00002a1d  90                nop        
00002a1e  90                nop        
00002a1f  90                nop        
00002a20  89542408          mov        dword ptr [esp + 8], edx
00002a24  8d860080fcff      lea        eax, [esi - 0x38000]
00002a2a  83ec04            sub        esp, 4
00002a2d  57                push       edi
00002a2e  6a00              push       0
00002a30  50                push       eax
00002a31  e84a020000        call       0x2c80
00002a36  83c410            add        esp, 0x10
00002a39  8d860000fdff      lea        eax, [esi - 0x30000]
00002a3f  83ec04            sub        esp, 4
00002a42  57                push       edi
00002a43  6a00              push       0
00002a45  50                push       eax
00002a46  e835020000        call       0x2c80
00002a4b  83c410            add        esp, 0x10
00002a4e  8d860080fdff      lea        eax, [esi - 0x28000]
00002a54  83ec04            sub        esp, 4
00002a57  57                push       edi
00002a58  6a00              push       0
00002a5a  50                push       eax
00002a5b  e820020000        call       0x2c80
00002a60  83c410            add        esp, 0x10
00002a63  8d860000feff      lea        eax, [esi - 0x20000]
00002a69  83ec04            sub        esp, 4
00002a6c  57                push       edi
00002a6d  6a00              push       0
00002a6f  50                push       eax
00002a70  e80b020000        call       0x2c80
00002a75  83c410            add        esp, 0x10
00002a78  8d860080feff      lea        eax, [esi - 0x18000]
00002a7e  83ec04            sub        esp, 4
00002a81  57                push       edi
00002a82  6a00              push       0
00002a84  50                push       eax
00002a85  e8f6010000        call       0x2c80
00002a8a  83c410            add        esp, 0x10
00002a8d  8d860000ffff      lea        eax, [esi - 0x10000]
00002a93  83ec04            sub        esp, 4
00002a96  57                push       edi
00002a97  6a00              push       0
00002a99  50                push       eax
00002a9a  e8e1010000        call       0x2c80
00002a9f  83c410            add        esp, 0x10
00002aa2  8d860080ffff      lea        eax, [esi - 0x8000]
00002aa8  83ec04            sub        esp, 4
00002aab  57                push       edi
00002aac  6a00              push       0
00002aae  50                push       eax
00002aaf  e8cc010000        call       0x2c80
00002ab4  83c40c            add        esp, 0xc
00002ab7  57                push       edi
00002ab8  6a00              push       0
00002aba  56                push       esi
00002abb  e8c0010000        call       0x2c80
00002ac0  8b542418          mov        edx, dword ptr [esp + 0x18]
00002ac4  83c410            add        esp, 0x10
00002ac7  83c208            add        edx, 8
00002aca  81c600000400      add        esi, 0x40000
00002ad0  39542414          cmp        dword ptr [esp + 0x14], edx
00002ad4  0f8546ffffff      jne        0x2a20
00002ada  8b4c240c          mov        ecx, dword ptr [esp + 0xc]
00002ade  f6c104            test       cl, 4
00002ae1  7444              je         0x2b27
00002ae3  c1e20f            shl        edx, 0xf
00002ae6  8d0413            lea        eax, [ebx + edx]
00002ae9  0568900800        add        eax, 0x89068
00002aee  89442408          mov        dword ptr [esp + 8], eax
00002af2  83e104            and        ecx, 4
00002af5  c1e10f            shl        ecx, 0xf
00002af8  31f6              xor        esi, esi
00002afa  897c2418          mov        dword ptr [esp + 0x18], edi
00002afe  90                nop        
00002aff  90                nop        
00002b00  8b442408          mov        eax, dword ptr [esp + 8]
00002b04  01f0              add        eax, esi
00002b06  83ec04            sub        esp, 4
00002b09  57                push       edi
00002b0a  6a00              push       0
00002b0c  50                push       eax
00002b0d  89cf              mov        edi, ecx
00002b0f  e86c010000        call       0x2c80
00002b14  89f9              mov        ecx, edi
00002b16  8b7c2428          mov        edi, dword ptr [esp + 0x28]
00002b1a  83c410            add        esp, 0x10
00002b1d  81c600800000      add        esi, 0x8000
00002b23  39f1              cmp        ecx, esi
00002b25  75d9              jne        0x2b00
00002b27  8b4c2410          mov        ecx, dword ptr [esp + 0x10]
00002b2b  8b01              mov        eax, dword ptr [ecx]
00002b2d  83ec08            sub        esp, 8
00002b30  6a03              push       3
00002b32  51                push       ecx
00002b33  ff10              call       dword ptr [eax]
00002b35  83c410            add        esp, 0x10
00002b38  85c0              test       eax, eax
00002b3a  7404              je         0x2b40
00002b3c  31c0              xor        eax, eax
00002b3e  eb39              jmp        0x2b79
00002b40  8b8350900000      mov        eax, dword ptr [ebx + 0x9050]
00002b46  48                dec        eax
00002b47  8983ac901000      mov        dword ptr [ebx + 0x1090ac], eax
00002b4d  83bbac90100000    cmp        dword ptr [ebx + 0x1090ac], 0
00002b54  791a              jns        0x2b70
00002b56  80bb5890000000    cmp        byte ptr [ebx + 0x9058], 0
00002b5d  b80f000000        mov        eax, 0xf
00002b62  b903000000        mov        ecx, 3
00002b67  0f45c8            cmovne     ecx, eax
00002b6a  898bac901000      mov        dword ptr [ebx + 0x1090ac], ecx
00002b70  c6838490100001    mov        byte ptr [ebx + 0x109084], 1
00002b77  b001              mov        al, 1
00002b79  8d65f4            lea        esp, [ebp - 0xc]
00002b7c  5e                pop        esi
00002b7d  5f                pop        edi
00002b7e  5b                pop        ebx
00002b7f  5d                pop        ebp
00002b80  c3                ret        
