# Call site `smbPitchShift` trong `ChannelProcess`

Tại hai call site `0x2e70` và `0x2ea4`, native chuẩn bị cùng kích thước khối `w2 = 0x400` (1.024 mẫu) và `w3 = 4`, truyền hai buffer qua `x0/x4`, cùng một số trạng thái qua `x5`. Thanh ghi `s0/s1` được nạp từ giá trị pitch đã tính và hằng số scale trước khi gọi `smbPitchShift`.

Đây là bằng chứng cho xử lý theo block và hai đường buffer (phù hợp trái/phải hoặc hai kênh xử lý). Ý nghĩa chính xác của `w3 = 4`, con trỏ trạng thái và hệ số pitch vẫn cần prototype của `smbPitchShift`; không gán chắc chắn đó là số kênh chỉ từ lệnh gọi.
