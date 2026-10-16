# Kiểm tra vòng đời output — 2026-10-17

Thiết bị `88d75c04`, mã nguồn tại commit `4e01cc2`.

- Cài APK debug thành công.
- Khởi động lạnh qua `am start -W`: Status ok, 638 ms.
- Ba chu kỳ gửi Back và mở lại: hai lượt WARM (133/151 ms), một lượt chuyển intent tới activity hiện có.
- Không thấy FATAL EXCEPTION hay lỗi ORGX-Audio trong 1500 dòng log gần nhất sau chuỗi thao tác.
- `ps -T -p 30451` sau kiểm tra có đúng một worker `ORGX-Audio` và một thread `AudioTrack`.
- MainActivity đang resumed sau kiểm tra.

Giới hạn: Back có thể chỉ đưa task xuống nền trên Android; không chứng minh mọi lượt đều chạy onDestroy.
Chưa đo độ trễ, underrun hoặc chất lượng âm thanh, chưa kiểm tra NOpenSL.
