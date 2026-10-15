# Các lời gọi trong native ARM64 đã disassemble

Từ các file `.asm` trong `analysis/native/`:

- `NMusic_ChannelProcess` gọi `smbPitchShift@plt` 2 lần; đây là bằng chứng trực tiếp rằng đường xử lý channel có bước pitch-shift bên ngoài thân JNI.
- `NOpenSL_Start` gọi `slCreateEngine@plt`, `pthread_mutex_lock/unlock`, `usleep` và nhiều lần `memset`; hai lời gọi nội bộ tới `0x2448` đi vào helper dọn OpenSL/JNI được xác minh trong [NOPENSL-START-CALLBACKS.md](NOPENSL-START-CALLBACKS.md).
- `NOpenSL_MixPut` gọi `__memcpy_chk@plt` 2 lần.
- `NOpenSL_Write` gọi `memcpy@plt` và `usleep@plt` 2 lần.
- `NOpenSL_Put` không có lời gọi `bl` ngoài, phù hợp với một wrapper ngắn.

Đây là call graph tĩnh ở mức lệnh `bl`; các nhánh gián tiếp, callback function pointer và địa chỉ nội bộ chưa được tự động gán tên.

Vùng `0x2448..0x2574` trong chính `.text` của `libnopensl.so` đã được disassemble riêng. Nó gọi `SLObjectItf.Destroy` cho player, recorder, output mix và engine, xóa global reference JNI cùng toàn bộ con trỏ trạng thái. Địa chỉ trùng `NMusic_ChannelCopy` trong một ELF khác hoàn toàn không mang ý nghĩa liên kết chéo thư viện.
