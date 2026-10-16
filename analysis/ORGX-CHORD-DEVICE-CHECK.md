# Kiểm tra lưu chord trên thiết bị — 2026-10-17

- Cài APK debug từ mã nguồn hiện tại: thành công.
- Thiết bị: `88d75c04`, package `com.hovait.orgx`.
- Mở Chord library và chạm `D M`.
- Đọc preferences bằng `run-as`: `chordName=D M`, `chord=2`.
- Force-stop rồi mở lại MainActivity.
- UI Automator xác nhận cả nút `CHORD D M` và trạng thái `D M`.

Kết quả: chọn hợp âm và giữ lựa chọn sau khi khởi động lại đạt trên thiết bị.
Chưa kiểm chứng trong lượt đầu: lưu/nạp SET và project bằng UI, âm thanh thực tế,
âm sắc, cách xếp nốt và độ tương đồng với APK gốc.

## Lưu/nạp SET bằng UI

- Xác nhận SET 2 chưa có dữ liệu (nút SAVE, LOAD chưa bật).
- Lưu D M, tempo 120 vào SET 2; preferences ghi `set2_chordName=D M`, `set2_chord=2`.
- Chuyển chord hiện tại sang C M và tempo sang 125.
- Chạm LOAD của SET 2: UI trở lại `CHORD D M` và `120 BPM`.
- Preferences hiện tại xác nhận `chordName=D M`, `bpm=120`.
- Giữ lại SET 2 vừa tạo; không ghi đè SET 1.

Kết quả: luồng lưu/nạp SET 2 khôi phục đúng chord và tempo trên thiết bị.
Project và tín hiệu âm thanh vẫn chưa được xác nhận trong lượt này.

## Lưu/nạp project bằng UI

- Lưu project mới `ORGX-20261017-053342.orgx` (360 byte) từ D M, tempo 120.
- Giữ nguyên project cũ trong danh sách.
- Chuyển sang C M, tempo 125; preferences xác nhận trạng thái đã đổi.
- Mở PROJECT và LOAD project mới.
- UI Automator xác nhận `CHORD D M`, `D M`, `120 BPM` sau khi nạp.

Kết quả: project mới khôi phục đúng chord và tempo qua UI trên thiết bị.
Chưa kiểm chứng âm thanh, toàn bộ trường mixer và tương thích định dạng project APK gốc.
