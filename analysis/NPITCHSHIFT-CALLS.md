# Lời gọi trong `libnpitchshift`

Các entry point có nhiều lệnh `bl` tới vùng địa chỉ `0x1e50–0x1ec0`. Đối chiếu section ELF cho thấy vùng này nằm trong `.plt`, không phải helper static trong `.text`; đây là các stub gọi import.

Đã lập bảng relocation/PLT chính xác: `0x1e50=free`, `0x1e70=cos`, `0x1e80=smbFft`, `0x1e90=atan2`, `0x1ea0=sincos`, `0x1eb0=log`, `0x1ec0=smbPitchShiftSample`. Vì vậy các lệnh `bl` trước đó là lời gọi import cụ thể, không phải helper ẩn danh.

`.symtab` vẫn giữ các hàm chính: `smbPitchShiftNew` (40 byte), `smbPitchShiftFlush` (176), `smbPitchShiftCopy` (180), `smbPitchShift` (1.468), `smbFft` (504), `smbPitchShiftSample` (1.216), `smbAtan2` (56) và các wrapper JNI.
