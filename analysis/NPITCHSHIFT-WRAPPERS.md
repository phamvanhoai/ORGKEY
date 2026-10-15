# JNI wrapper của `NPitchShift`

Các wrapper ARM64 cho thấy giao diện native:

- `processLeft` và `processRight` nhận buffer khác nhau, đặt block `0x400` mẫu, truyền tham số `4`, chuẩn hóa tham số đầu vào bằng hằng số `100.0f` (`0x42c80000`) và chuyển tham số thứ hai thành `float`.
- `processRight` cộng offset `0xb010` vào con trỏ buffer trước khi gọi routine chung, cho thấy vùng phải nằm sau vùng trái trong một buffer lớn.
- `process` dùng cùng block/stride nhưng gọi routine chung qua `bl #0x1ed0`; `processLeft/Right` dùng nhánh `b #0x1ed0`.
- Khi con trỏ buffer bằng null, wrapper trả về ngay mà không gọi native.

Đây là bằng chứng trực tiếp về block size, hai vùng buffer và chuẩn hóa pitch/scale ở ranh giới JNI.
