# Kiểm tra bản dựng trên thiết bị

- ADB nằm tại `tools/android-sdk/platform-tools/adb.exe` theo `orgx/local.properties`.
- Thiết bị `88d75c04` kết nối và được cấp quyền ADB.
- Cài đè APK debug thành công; package `com.hovait.orgx`, versionName `0.1.6`.
- Mở MainActivity thành công; tiến trình chạy. Không có lỗi trong truy vấn log gần nhất với tag AndroidRuntime:E và ORGX-Audio:E.
- Screenshot xác nhận giao diện đàn đang hiển thị, style `DJ VINAHOUSE 3 / MAIN 1`, voice `Dance 9 KBT / Primary`, tempo 142. Màn hình đang READY.
- Meter đang hiện `0/0`, cần đối chiếu metadata/default gốc trước sửa.

## Lượt kiểm tra sau sửa meter và liên kết Main–STS

- Màn hình xác nhận meter đã hiện 4/4 trên style DJ VINAHOUSE 3.
- Nhấn START chuyển READY sang RUNNING.
- Khi đang chạy, chọn MAIN 2 với AUTO FILL đang bật: ảnh đầu hiện NEXT FILL 2; ảnh sau hiện MAIN 2 và vẫn RUNNING, không cần nhấn START lại.
- STOP trả về READY. Chọn MAIN 1 khi dừng tự khởi chạy theo cơ chế hiện có; sau đó đã STOP lần nữa, để thiết bị ở MAIN 1 / READY như ban đầu.
- Truy vấn AndroidRuntime:E/ORGX-Audio:E gần nhất không trả lỗi.
- Chưa xác nhận bằng nghe hoặc thu âm, chưa bật liên kết STS trong lượt này. Đây là kiểm tra thao tác và trạng thái màn hình, không phải đối chiếu âm thanh APK gốc.

Phạm vi: xác nhận cài/chạy/hiển thị. Chưa nhấn Start, chưa xác nhận tiếng phát ra, bản ghi MIDI hoặc đối chiếu âm thanh với APK gốc. UIAutomator chỉ thấy Compose root, vì vậy ảnh chụp được dùng để kiểm tra hiển thị.
