# Kiểm tra lưu chord trên thiết bị — 2026-10-17

- Cài APK debug từ mã nguồn hiện tại: thành công.
- Thiết bị: `88d75c04`, package `com.hovait.orgx`.
- Mở Chord library và chạm `D M`.
- Đọc preferences bằng `run-as`: `chordName=D M`, `chord=2`.
- Force-stop rồi mở lại MainActivity.
- UI Automator xác nhận cả nút `CHORD D M` và trạng thái `D M`.

Kết quả: chọn hợp âm và giữ lựa chọn sau khi khởi động lại đạt trên thiết bị.
Chưa kiểm chứng trong lượt này: lưu/nạp SET và project bằng UI, âm thanh thực tế,
âm sắc, cách xếp nốt và độ tương đồng với APK gốc.
