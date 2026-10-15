# Địa chỉ PLT dùng chung của module pitch-shift

Các entry point `Flush`, `Sample` và `Fft` nhảy tới các địa chỉ `0x1e50–0x1ec0`. Kiểm tra ELF xác nhận đây là section `.plt`, tức stub cho import, không phải helper tĩnh. Báo cáo cũ đã gán chúng là helper nội bộ; cách gán đó đã được sửa.

Bảng relocation xác định các slot: `0x1e50=free`, `0x1e70=cos`, `0x1e80=smbFft`, `0x1e90=atan2`, `0x1ea0=sincos`, `0x1eb0=log`, `0x1ec0=smbPitchShiftSample`. Như vậy FFT và các hàm toán học được gọi trực tiếp qua PLT.
