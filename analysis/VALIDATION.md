# Kiểm chứng khả năng tái lập

Đã kiểm tra cú pháp toàn bộ 26 script Python trong `analysis/`: không có lỗi cú pháp.

Các parser/inventory nhẹ được chạy lại thành công:

- Skin: 24 file, 1.107 record, 0 lỗi.
- Menu: 33 file, 0 lỗi.
- Chord chuẩn: 37 mẫu, 0 lỗi.
- Chord nested: 33 file, 396 mask, 0 lỗi.
- Native ARM64: 18 thư viện, 3.888 dynamic symbol, 100 JNI tổng cộng.
- JNI `sofeh`: 9 thư viện và 75 tên giống hệt trên cả bốn ABI.
- ELF `dic`: `.text` 756 byte, 245 lệnh x86, entry `0x550`.
- Helper OpenSL nội bộ: 3 vùng, 1.484 byte, 371 lệnh ARM64; giải mã bao phủ đủ byte.
- Bằng chứng REA mới cho `ProcessIn`, `InDeviceStart` và `InDeviceStop` đều là JSON hợp lệ.
- Manifest: 491 artifact, đường dẫn duy nhất và 0 sai lệch kích thước/SHA-256.

Các kết quả này được tạo lại trực tiếp từ APK hoặc artifact giải mã có hash đã ghi trong báo cáo. Phân tích runtime không nằm trong phạm vi kiểm chứng này.
