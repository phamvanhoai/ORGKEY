# Constant.Instrument và Sound.Instrument

Parser chung đã được mở rộng cho Instrument độc lập phiên bản 1.4.2. Cả hai asset đi kèm APK đều được đọc đến đúng EOF; tất cả section mang PCM nội tuyến và có thể xuất trực tiếp thành WAV.

## Constant.Instrument

File dài 469.845 byte, tên `Constant`, type 1, family 0, không có mode bổ sung và có một sound `Primary`.

| Note index | Section | Định dạng | PCM |
| ---: | --- | --- | ---: |
| 0 | Metronome Long | mono 48 kHz, 17.800 frame | 35.600 byte |
| 1 | Metronome Short | mono 48 kHz, 12.031 frame | 24.062 byte |
| 12 | Syncro Crash | mono 44,1 kHz, 204.847 frame | 409.694 byte |

Tổng PCM là 469.356 byte. Hai metronome giống từng byte với section tương ứng trong `empty.Template`: SHA-256 PCM của bản Long là `9adf4d1ac81360aa8f0d82ea0b1748bc005bad1caa8f419121a80f96a364cf6e`, bản Short là `fc37a91f4bb71152be522f169594b93c2bdf029495dc9dd5b0e2a7fd9e8299c3` ở cả hai nguồn.

## Sound.Instrument

File dài 2.965.539 byte, tên `Sound`, type 1, family 0, không có mode bổ sung và có một sound `Primary` với 12 section.

| Note | Tên | Sample rate | Kênh | Frame | PCM byte | Loop |
| ---: | --- | ---: | ---: | ---: | ---: | ---: |
| 24 | Brrr | 32.000 | 1 | 27.202 | 54.404 | 0 |
| 25 | Eehh | 32.000 | 1 | 39.444 | 78.888 | 0 |
| 26 | Heyy | 32.000 | 1 | 9.915 | 19.830 | 0 |
| 27 | Scream | 32.000 | 1 | 26.778 | 53.556 | 0 |
| 28 | Kel | 44.100 | 2 | 114.980 | 459.920 | 0 |
| 29 | Sea | 44.100 | 2 | 384.000 | 1.536.000 | 3 |
| 30 | Dance | 44.100 | 2 | 74.684 | 298.736 | 0 |
| 31 | Haa | 48.000 | 1 | 17.026 | 34.052 | 0 |
| 32 | Gangnam | 44.100 | 2 | 65.008 | 260.032 | 0 |
| 33 | Joonom | 44.115 | 1 | 24.879 | 49.758 | 0 |
| 34 | Yashasa | 47.633 | 1 | 30.454 | 60.908 | 0 |
| 35 | Yaainn | 48.000 | 1 | 29.048 | 58.096 | 0 |

Tổng PCM là 2.964.180 byte. Sample rate 44.115 và 47.633 là giá trị thực lưu trong asset; parser giữ nguyên thay vì chuẩn hóa về 44,1/48 kHz.

## File kiểm chứng

- `constant-instrument-inventory.json` và `sound-instrument-inventory.json` chứa offset/metadata từng section.
- `extracted-audio/constant-syncro-crash.wav`: mono 44,1 kHz, 204.847 frame.
- `extracted-audio/sound-sea.wav`: stereo 44,1 kHz, 384.000 frame; loop mode 3 nằm trong JSON metadata, chưa được nhúng thành chunk WAV `smpl`.

Python `wave` đọc lại hai WAV đúng số kênh, sample rate, frame và byte PCM. Parser cũng được chạy hồi quy lại trên hai Template 1.2.4/1.4.6 sau khi thêm hỗ trợ Instrument 1.4.2.
