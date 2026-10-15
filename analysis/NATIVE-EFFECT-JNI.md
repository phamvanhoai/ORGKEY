# JNI của các hiệu ứng âm thanh

Inventory ARM64 xác nhận các thư viện hiệu ứng riêng của ORG có JNI export:

- `libnbandbooster.so`: 4 hàm (`open`, `build`, `process`, `close`).
- `libnequalizer.so`: 8 hàm, gồm `band`, `bandMove`, `build`, `process`, `processLeft/Right`, `open`, `close`.
- `libnflanger.so`: 6 hàm, gồm `open`, `build`, `process`, `processLeft/Right`, `close`.
- `libngverb.so`: 14 hàm, gồm `open`, `processLeft`, `processStereoLeft`, `flush` và các biến thể xử lý khác.
- `libnpitchshift.so`: 5 hàm JNI, đã disassemble riêng.

Các nhóm này xác nhận kiến trúc hiệu ứng: mỗi module có vòng đời mở/xây dựng/xử lý/đóng, còn xử lý trái/phải hoặc stereo được tách thành entry point riêng.
