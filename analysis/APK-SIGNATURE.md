# Chữ ký APK

Đã kiểm tra cấu trúc ZIP/APK:

- `jarsigner` không tìm thấy chữ ký JAR/v1 (`jar is unsigned`).
- Ngay trước Central Directory có magic `APK Sig Block 42`.
- Signing Block chứa ID v2 `0x7109871a`.
- Không tìm thấy ID v3 `0xf05368c0` trong Signing Block.

Androguard 4.1.4 xác nhận `v1=False`, `v2=True`, `v3=False` và đọc được một certificate v2:

- Subject/issuer: `CN=Ramin Jafari,O=Sofeh Sunrise` (tự ký).
- Hiệu lực: 2013-11-26 20:00:25 UTC đến 2038-11-20 20:00:25 UTC.
- SHA-256 certificate: `ea29e47d6696ca0d6df9f0d455f580e4fd54f11a5c7a71abd5edacae937c02ea`.

Androguard cũng báo APK được validate thành công. Đây là xác minh parser/signature scheme và certificate; `apksigner` chính thức của Android vẫn không có trong môi trường để tạo báo cáo xác minh độc lập thứ hai.
