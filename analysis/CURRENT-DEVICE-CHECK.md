# Kiểm tra bản dựng trên thiết bị

- ADB nằm tại `tools/android-sdk/platform-tools/adb.exe` theo `orgx/local.properties`.
- Thiết bị `88d75c04` kết nối và được cấp quyền ADB.
- Cài đè APK debug thành công; package `com.hovait.orgx`, versionName `0.1.6`.
- Mở MainActivity thành công; tiến trình chạy. Không có lỗi trong truy vấn log gần nhất với tag AndroidRuntime:E và ORGX-Audio:E.
- Screenshot xác nhận giao diện đàn đang hiển thị, style `DJ VINAHOUSE 3 / MAIN 1`, voice `Dance 9 KBT / Primary`, tempo 142. Màn hình đang READY.
- Meter đang hiện `0/0`, cần đối chiếu metadata/default gốc trước sửa.

Phạm vi: xác nhận cài/chạy/hiển thị. Chưa nhấn Start, chưa xác nhận tiếng phát ra, bản ghi MIDI hoặc đối chiếu âm thanh với APK gốc. UIAutomator chỉ thấy Compose root, vì vậy ảnh chụp được dùng để kiểm tra hiển thị.
