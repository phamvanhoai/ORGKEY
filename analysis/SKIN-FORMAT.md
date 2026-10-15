# Định dạng Skin/Part

Đã kiểm kê trực tiếp các tài nguyên `assets/skin/` trong APK `ORG 24`. Có 24 tệp văn bản UTF-8: 5 tệp nền `.Skin` và 19 tệp biến thể `.Part`, thuộc 5 họ `skin_4`, `skin_5`, `skin_9`, `skin_m5`, `skin_m9`. Tổng cộng có 1.107 bản ghi thành phần, 112 mã thành phần khác nhau; cả 24 tệp đều vượt qua kiểm tra chữ ký, số trường và số nguyên, không có lỗi phân tích. Kết quả máy đọc nằm trong [skin-assets-inventory.json](skin-assets-inventory.json), mã kiểm kê ở [inspect_skin_assets.py](inspect_skin_assets.py).

## Luồng nạp được xác minh

REA cho thấy `defpackage.us3.t(MainActivity,String,int[],boolean)` nhận tên skin, lưu các drawable liên quan rồi gọi `s(...)`. Phương thức `s` tải bitmap nền qua `xs1.d(...)`, tính kích thước hiển thị (giới hạn chiều rộng 2.048 pixel), sau đó gọi `w(...)`. Phương thức `w` chuyển chuỗi skin vào `x(width,height,str)` để phân tích và dựng các thành phần. Các bản trích mã tương ứng: [skin-loader-t.java](source/skin-loader-t.java), [skin-loader-s.java](source/skin-loader-s.java), [skin-parser-x.java](source/skin-parser-x.java).

## Cấu trúc tệp

Dòng đầu tiên phải là `; ORG Skin file`. Dòng thứ hai là header số nguyên. Bảy trường đầu luôn có mặt; các trường sau là tùy chọn và được mã Java gán mặc định khi thiếu:

| Vị trí | Ý nghĩa theo mã phân tích | Mặc định nếu thiếu |
| ---: | --- | ---: |
| 0 | kích thước logic theo chiều ngang | bắt buộc |
| 1 | chiều cao phần đầu | bắt buộc |
| 2 | chiều cao một hàng | bắt buộc |
| 3 | số hàng | bắt buộc |
| 4–7 | bốn giá trị mặc định cho thành phần | trường 7 mặc định `10` |
| 8–10 | ba màu mặc định | `-49152`, `-16715536`, `-13619152` |
| 11–13 | cờ/giá trị phụ và màu thứ tư | `0`, `1`, `-6250336` |

Mỗi dòng sau header tạo một đối tượng `vs3`. Chín trường đầu bắt buộc gồm mã thành phần, bốn tham số, rồi bốn tọa độ `left, top, right, bottom`. Tối đa chín trường sau ghi đè tùy chọn cho biến thể và kiểu hiển thị; khi thiếu, parser kế thừa các giá trị header. Tọa độ và kích thước được nhân với hệ số `width/logical_width` và `height/(row_count*row_height+header_height)` trước khi vẽ.

Một số mã có xử lý bố cục riêng trong `x`: 30/31 tạo thêm thành phần 32; 35, 60, 70, 121/130, 135/136 và 179 được giữ vào các khe điều khiển đặc biệt. Nếu `row_height` bằng 0, mã 35 được dùng để suy ra lại hàng; nếu có nhiều hàng, parser thêm một thành phần nền tổng hợp.

## Phân bố tài nguyên

| Họ | Tệp nền | Part có trong APK | Số bản ghi |
| --- | --- | --- | ---: |
| `skin_4` | `skin_4.Skin` | 1, 2, 3, 4 | 228 |
| `skin_5` | `skin_5.Skin` | 1, 2, 3, 4 | 245 |
| `skin_9` | `skin_9.Skin` | 2, 3, 4 | 231 |
| `skin_m5` | `skin_m5.Skin` | 1, 2, 3, 4 | 207 |
| `skin_m9` | `skin_m9.Skin` | 1, 2, 3, 4 | 196 |

Mã xuất hiện nhiều nhất là 15 (85 lần), 201 (54), 200 (31), tiếp theo là 56 (27). Đây là thống kê mã số từ dữ liệu, chưa gán tên giao diện cho từng mã; việc gán tên cần đọc tiếp các nhánh render trong `us3.w` và các lớp điều khiển gọi chúng.

Phân tích này là tĩnh: nó xác nhận parser và dữ liệu được đóng gói, không xác nhận thứ tự skin được chọn khi chạy ứng dụng.

## Nhánh render `us3.w`

Đã lưu toàn bộ thân phương thức render trong [skin-render-w.java](source/skin-render-w.java). Mã thành phần được dispatch theo nhiều tầng `switch` và các nhóm kiểm tra trực tiếp. Các mã được xử lý riêng trong bản decompile gồm:

`30, 31, 50, 51, 55, 56, 110, 111, 150, 151, 6, 180, 106, 120, 139, 174, 190, 196, 198, 170, 62–71, 100–103, 160–162, 164–166, 8–12, 16–18, 40–46, 73–75, 77–79, 91–95`.

Nhánh `30/31` được xác nhận là vùng phím/điều khiển đặc biệt vì `x` cũng tạo thành phần tổng hợp 32 từ nó; nhánh `170` lặp theo số lượng `vs3.e`. Các nhóm còn lại gọi các hàm vẽ nội bộ khác nhau (`C`, `D`, `E`, `F`, `G`, `h`, `i`, `j`, `k`, `l`, `m`, `n`), nhưng tên lớp đã bị rút gọn và decompiler không giữ nhãn nghiệp vụ. Vì vậy báo cáo chỉ gán chắc chắn những vai trò có bằng chứng trực tiếp, không đặt tên như “nút đàn” hay “thanh âm lượng” cho các mã chưa được nối với xử lý chạm.
