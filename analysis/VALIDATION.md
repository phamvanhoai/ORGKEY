# Kiểm chứng khả năng tái lập

Đã kiểm tra cú pháp toàn bộ 29 script Python trong `analysis/`: không có lỗi cú pháp.

Đã kiểm tra cú pháp script PowerShell thu runtime bằng parser của PowerShell: không có lỗi cú pháp.

ADB đã xác nhận một thiết bị và đúng APK 2026.10.01/code 274. Hash APK kéo từ `/data/app` khớp file phân tích; ứng dụng mở thành công, không crash trong 30 giây và sinh track OpenSL FAST 48 kHz với 0 underrun tại thời điểm dump.

Sau khi cấp quyền micro, runtime tạo `AudioRecord` mono 48 kHz, thu 92.160 frame trong khoảng 1,92 giây và dừng sạch; AudioFlinger báo input latency khoảng 30,17 ms.

UI Automator xác nhận giao diện chính chỉ công bố hai `ImageView` canvas, không có accessibility node riêng cho nút/menu; không dùng tọa độ mù để tự động thay đổi các mode âm thanh.

Người dùng xác nhận xuất MP3 thành công trên đúng APK 2026. Log không công bố đường dẫn file để kiểm tra metadata độc lập; MIDI phần cứng không có trong môi trường kiểm thử và được đánh dấu không áp dụng.

Các parser/inventory nhẹ được chạy lại thành công:

- Skin: 24 file, 1.107 record, 0 lỗi.
- Menu: 33 file, 0 lỗi.
- Chord chuẩn: 37 mẫu, 0 lỗi.
- Chord nested: 33 file, 396 mask, 0 lỗi.
- Native ARM64: 18 thư viện, 3.888 dynamic symbol, 100 JNI tổng cộng.
- JNI `sofeh`: 9 thư viện và 75 tên giống hệt trên cả bốn ABI.
- Disassembly JNI toàn ABI: 300 hàm, 82.942 byte symbol; 295 hàm được Capstone giải mã hết byte và 118 byte cuối của năm hàm ARM32 được giữ nguyên dạng hex.
- Phân loại ARM32: năm vùng/118 byte; xác nhận pool hằng số Equalizer và GVerb, padding NMusic cùng literal pool của OpenSL/NSF2.
- Inventory native toàn APK: 58 ELF được phân loại đầy đủ; 36 bản ORG, 14 Pangle/ByteDance, 4 AppLovin và 4 AndroidX DataStore.
- JSON: toàn bộ artifact `.json` đọc được bằng parser nghiêm ngặt; artifact constructor rỗng cũ đã được thay bằng metadata trạng thái hợp lệ.
- ELF `dic`: `.text` 756 byte, 245 lệnh x86, entry `0x550`.
- Helper OpenSL nội bộ: 3 vùng, 1.484 byte, 371 lệnh ARM64; giải mã bao phủ đủ byte.
- Bằng chứng REA mới cho `ProcessIn`, `InDeviceStart` và `InDeviceStop` đều là JSON hợp lệ.
- Manifest: 805 artifact, đường dẫn duy nhất và 0 sai lệch kích thước/SHA-256.

Các kết quả này được tạo lại trực tiếp từ APK hoặc artifact giải mã có hash đã ghi trong báo cáo. Phân tích runtime không nằm trong phạm vi kiểm chứng này.
