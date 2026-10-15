# Call map chính xác của `libnpitchshift`

Sau khi đối chiếu PLT relocation:

- `smbPitchShift`: gọi `smbFft` 3 lần, `atan2` 3 lần, `free` 6 lần, `sincos` 1 lần, `log` 1 lần và `cos` 2 lần.
- `smbPitchShiftSample`: gọi `smbFft` 2 lần, `atan2` 2 lần, `sincos` 1 lần, `log` 1 lần, `free` 3 lần và `cos` 2 lần.
- `smbFft`: gọi `smbPitchShiftSample` 1 lần và `log` 1 lần.

Các số đếm là số lệnh `bl` tĩnh trong bản ARM64, không phải số lần chạy runtime. Chúng cho thấy FFT và hàm sample được dùng lẫn nhau trong pipeline pitch-shift.
