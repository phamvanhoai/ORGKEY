# Tài nguyên menu và trợ giúp

APK chứa 33 tệp văn bản UTF-8 trong `assets/menu/`, tổng cộng 17.877 byte. Bộ kiểm kê [menu-assets-inventory.json](menu-assets-inventory.json) được tạo bằng [inspect_menu_assets.py](inspect_menu_assets.py); tất cả tệp đều đọc được, không có lỗi mã hóa.

Các nhóm nội dung:

- `menu*.txt`: menu chính, 10 bản ngôn ngữ/biến thể. Dòng đầu là tiêu đề, sau đó là cặp văn bản và hành động; ví dụ `Quick Guide ...` đi với `.g|ic_faq`, mua tính năng đi với `.purchase|dl_buy`, tải dữ liệu đi với `.data|dl_download`, và liên kết YouTube đi với URL cùng mã `lg_video`.
- `menu-faq*.txt`: 4 tệp câu hỏi thường gặp. Mỗi mục gồm câu hỏi, URL hoặc tên menu đích, kết thúc bằng `return...` và `menu|ic_default`.
- `menu-speed*.txt`: 8 tệp hướng dẫn điều chỉnh hiệu năng theo ngôn ngữ.
- `pm*.txt`: 6 tệp nội dung ngắn liên quan thông báo/quyền riêng tư hoặc thông điệp menu.
- `sku*.txt`: 9 tệp chuỗi giao diện mua hàng theo ngôn ngữ.

## Định dạng quan sát được

Đây là dữ liệu dòng, không phải serialized binary. BOM UTF-8 xuất hiện ở một số tệp; parser nên dùng `utf-8-sig`. Các dấu `|` phân cách nhãn hiển thị và mã hành động, còn dòng bắt đầu bằng dấu chấm là mã nội bộ (`.purchase`, `.data`, `.market`, `.about`). Một số dòng là URL hoặc tên tệp menu khác, cho phép menu liên kết sang FAQ/hướng dẫn mà không cần mã hóa URL trong Java.

Phân tích này xác nhận nội dung và quy ước dữ liệu được đóng gói. Chưa gán được lớp Java cụ thể đọc từng nhóm vì tên menu được tạo động và các chuỗi nằm rải trong nhiều nhánh Activity; không kết luận thứ tự hiển thị runtime chỉ từ tài nguyên.
