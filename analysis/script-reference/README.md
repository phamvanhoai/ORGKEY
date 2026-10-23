# Script gốc dùng đối chiếu

24 file được trích từ `assets/data.zip` trong APK 2026.10.01 tại workspace. `inventory.json` ghi độ dài và SHA-256 byte gốc; không chuẩn hóa xuống dòng khi trích.

Đăng ký event dùng `on(touch) { ... }`, không phải từ khóa đứng riêng. Script mẫu dùng lệnh có tham số đặt tên như `play(octavemove:1)`, biểu thức, điều kiện, biến lưu trạng thái giữa các lần gọi và `return(true)` để chặn fallback. `main()` khởi tạo trạng thái.

`ImportedModeScript` hiện chỉ lập danh mục event và tên biến, bỏ comment/chuỗi trước khi dò. Nó chưa phân tích biểu thức, chưa chạy lệnh và chưa chứng minh DNC tương đương APK. Không dùng `handles` để chặn fallback âm thanh cho tới khi có thực thi và giá trị trả về thật.

Cập nhật thực thi: `ImportedSimpleScript` xử lý chuỗi lệnh `play`/`return`, số nguyên và biến cộng/trừ hằng số. Playback hiện nhận `octavemove`, `volume`, `connectkey`, `connectspeed` trong touch. Test đọc trực tiếp bốn file Add Upper Octave, Add Upper & Lower Octave, Legato from Lower/Upper từ thư mục này và kiểm tra PCM: tổng mix, giữ fallback khi không return true, hướng pitch và release. Đây là mẫu PCM tổng hợp, chưa đối chiếu bản thu APK. Script timer, trạng thái, điều kiện và release `exit` đổi mode vẫn chưa thực thi.
