# Script gốc dùng đối chiếu

24 file được trích từ `assets/data.zip` trong APK 2026.10.01 tại workspace. `inventory.json` ghi độ dài và SHA-256 byte gốc; không chuẩn hóa xuống dòng khi trích.

Đăng ký event dùng `on(touch) { ... }`, không phải từ khóa đứng riêng. Script mẫu dùng lệnh có tham số đặt tên như `play(octavemove:1)`, biểu thức, điều kiện, biến lưu trạng thái giữa các lần gọi và `return(true)` để chặn fallback. `main()` khởi tạo trạng thái.

`ImportedModeScript` hiện chỉ lập danh mục event và tên biến, bỏ comment/chuỗi trước khi dò. Nó chưa phân tích biểu thức, chưa chạy lệnh và chưa chứng minh DNC tương đương APK. Không dùng `handles` để chặn fallback âm thanh cho tới khi có thực thi và giá trị trả về thật.
