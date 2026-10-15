# Ưu tiên disassembly native theo kích thước

Theo bảng symbol ARM64, các hàm lớn nhất là:

| Thư viện | Hàm | Kích thước |
| --- | --- | ---: |
| `libnmusic.so` | `NMusic_ChannelProcess` | 2.816 byte |
| `libnopensl.so` | `NOpenSL_Start` | 2.632 byte |
| `libnopensl.so` | `NOpenSL_MixWrite` | 1.192 byte |
| `libnopensl.so` | `NOpenSL_MixPut` | 792 byte |
| `libnopensl.so` | `NOpenSL_Write` | 636 byte |
| `libnopensl.so` | `NOpenSL_RecordStart` | 236 byte |

Các mục ưu tiên trong bảng hiện đã được disassemble đầy đủ. `ChannelProcess` chứa xử lý/trộn mẫu; `NOpenSL_Start` khởi tạo đường phát và hai callback OpenSL; `MixWrite`, `MixPut` và `Write` bao phủ đường truyền buffer. Phân tích chi tiết callback và helper nội bộ nằm tại [NOPENSL-START-CALLBACKS.md](NOPENSL-START-CALLBACKS.md).
