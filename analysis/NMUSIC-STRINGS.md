# Chuỗi và nhãn dữ liệu trong `libnmusic.so`

Ngoài tên JNI và import, `.rodata` của `libnmusic.so` có các nhãn `Position`, `VolumeMoveL`, `VolumeMoveR`, `diPitch`, `diVolumeR`, `iVolumeL`, `diVolumeL`, `dPitchChangeValue`, `iPitch` và `iVolumeR`. Đây là dấu hiệu tĩnh cho thấy native giữ trạng thái vị trí, âm lượng trái/phải và thay đổi pitch trong cùng thư viện trộn.

Các chuỗi này không đủ để suy ra công thức xử lý; disassembly mới là bằng chứng cho lời gọi `smbPitchShift`.
