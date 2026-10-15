# Vòng đời `NPitchShift`

`NPitchShift_open` nhận con trỏ tùy chọn, gọi một import xử lý chuỗi/buffer nếu con trỏ khác null, cấp phát block trạng thái kích thước `0x16020`, rồi khởi tạo các vùng tại offset `0x6018`, `0x6020`, `0x9008`, `0x9010` và buffer phải tại `0xb008`.

Các wrapper xử lý dùng chung state đã mở; `processRight` truy cập vùng lệch `0xb010`. `NPitchShift_close` là wrapper 4 lệnh, trả về trực tiếp giá trị được truyền, nên việc giải phóng đầy đủ có thể nằm trong routine gọi hoặc vtable state chứ không nằm trong wrapper này. Các địa chỉ PLT ở `open` chỉ được ghi theo target ELF; không gán tên malloc/memset nếu chưa đối chiếu đầy đủ slot relocation của đúng binary.
