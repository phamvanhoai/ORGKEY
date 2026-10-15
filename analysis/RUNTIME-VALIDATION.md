# Kiểm chứng runtime

Android Platform Tools 37.0.1 đã được tải từ máy chủ chính thức của Google vào `tools/platform-tools/`; SHA-256 của gói ZIP là `45F4D63113E895EBDE0C90F194099A4676B6AC653BD28D54314A9E022BBC1A99`. Thư mục công cụ và kết quả runtime được loại khỏi Git vì chứa binary tải ngoài và dữ liệu riêng của thiết bị.

Thiết bị đã được kết nối và đúng APK 2026.10.01 đã được quan sát; xem [kết quả runtime 2026](RUNTIME-RESULT-2026.md). [Kết quả bản 2023](RUNTIME-RESULT-2023.md) được giữ riêng để không trộn bằng chứng giữa hai phiên bản. Để thu lại khi có đúng một điện thoại hoặc emulator ở trạng thái `device`, chạy từ thư mục dự án:

```powershell
powershell -ExecutionPolicy Bypass -File analysis/capture_runtime.ps1 -Install -DurationSeconds 30
```

Script cài lại APK khi có `-Install`, mở launcher của `com.sofeh.android.musicstudio3`, chờ trong khoảng đã chọn và thu:

- thông tin thiết bị và package;
- quyền/AppOps trước và sau khi mở;
- logcat toàn hệ thống và log theo PID ứng dụng;
- Activity đang chạy;
- trạng thái audio và AudioFlinger;
- thống kê mạng trước và sau khoảng quan sát.

Đầu ra nằm trong `analysis/runtime/<thời-gian>/`. Trong thời gian script chờ, có thể bấm phím đàn, bật thu âm hoặc đổi chế độ độ trễ để log phản ánh đúng đường cần kiểm tra. Script không root thiết bị, không sửa APK và không giải mã TLS.
