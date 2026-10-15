# Module pitch-shift trong native

ELF của `libnmusic.so` xuất hiện nhóm symbol `smbPitchShiftNew`, `smbPitchShiftFree`, `smbPitchShiftStereoNew`, `smbPitchShiftStereoFree`, `smbPitchShiftFlush`, `smbPitchShiftCopy`, `smbPitchShift`, `smbPitchShiftSample`, `smbFft` và `smbAtan2`. `NMusic_ChannelProcess` gọi `smbPitchShift@plt` hai lần.

Tên `smb*` cho thấy một module pitch-shift/FFT riêng được liên kết vào `libnmusic.so`. APK không cung cấp metadata license hoặc tên upstream đủ chắc chắn trong phần đã trích, nên báo cáo chỉ xác nhận symbol và luồng gọi, không gán nguồn thư viện cụ thể.

Các thân hàm `smbPitchShift*` không nằm trong `libnmusic.so` mà nằm trong thư viện riêng `libnpitchshift.so`. ELF ARM64 của thư viện này có `smbPitchShift` kích thước 1.468 byte (367 lệnh), cùng các JNI wrapper `NPitchShift_open`, `process`, `processLeft`, `processRight`, `close`. Vì vậy phần triển khai pitch-shift thực sự vẫn nằm trong APK và có thể disassemble độc lập.
