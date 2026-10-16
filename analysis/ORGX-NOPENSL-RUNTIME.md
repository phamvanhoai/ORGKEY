# ORGX NOpenSL — kiểm tra 2026-10-17

Đã nối NOpenSLBackend vào AudioEngine qua AudioOutputLoop. Chế độ thử chỉ
khả dụng trong bản debuggable, bằng extra `orgx.testNativeOutput=true` khi khởi động activity mới.
Khởi động thông thường vẫn dùng AudioTrack.

Trên thiết bị `88d75c04`:

- `am start -W -n com.hovait.orgx/.MainActivity --ez orgx.testNativeOutput true`: Status ok.
- Log PID 30889: `ORGX-Audio: Backend: NOpenSL`.
- AudioFlinger ghi nhận track active của PID 30889, sample rate 48000, session 83953.
- Kiểm tra lại sau khi chạy unit test: process vẫn sống, không thấy lỗi ORGX-Audio/Fatal signal trong log gần nhất.

StartupFallbackBackend giải phóng backend khởi tạo lỗi và chuyển sang AudioTrack
khi gặp Exception hoặc LinkageError. Hai kiểm thử xác nhận chọn native thành công
và fallback khi thiếu JNI. Lỗi ghi dừng output qua AudioOutputLoop;
fallback này không phục hồi native crash hoặc lời gọi JNI bị treo.

Đây là bằng chứng khởi tạo và output track hoạt động, chưa phải phép đo âm thanh
nghe được, độ trễ, underrun hay độ tương đồng với âm sắc APK gốc.
