# JNI của SF2 và LAME

`libnsf2.so` xuất 9 hàm JNI cho `sofeh.music.NSF2`: mở/đóng SoundFont, đếm và đọc tên preset, lấy voice, sample rate và điểm loop. Hàm lớn nhất là `NSF2_Voice` (764 byte), tiếp theo `Open` (176) và `PresetName` (72). Điều này xác nhận thư viện chịu trách nhiệm đọc preset/voice từ SF2.

`libnlame.so` xuất 4 JNI cho `sofeh.audio.Lame`: `init` (148 byte), `encode` (308), `flush` (148), `close` (40). Đây là wrapper mã hóa MP3 theo vòng đời chuẩn.

`libnms.so` không có symbol JNI `Java_*`; kiểm tra tiếp đã xác định `JNI_OnLoad` và chuỗi lớp `com/pgl/ssdk/ces/a`, nên đây là thư viện SDK Pangle/PGL, không phải module âm nhạc. Chi tiết ở [LIBNMS-IDENTITY.md](LIBNMS-IDENTITY.md).

Đã disassemble ARM64 bằng [dump_effects.py](dump_effects.py): `NSF2_Voice` 191 lệnh, `Open` 44, `PresetName` 18; `Lame_encode` 77 lệnh, `init` 37, `flush` 37 và `close` 10. Các file `.asm` nằm trong `analysis/native/`.
