# Manifest kết quả phân tích

[`analysis-manifest.json`](analysis-manifest.json) là danh mục có thể kiểm chứng của các artifact trong thư mục `analysis/`. Mỗi mục lưu đường dẫn tương đối, kích thước byte và SHA-256 của nội dung.

Manifest hiện bao phủ 486 file, tổng cộng 16.197.204 byte:

| Loại | Số file |
|---|---:|
| JSON | 218 |
| ASM | 87 |
| Java | 82 |
| Markdown | 62 |
| Python | 26 |
| TXT | 5 |
| CSV | 4 |
| XML | 1 |
| BIN | 1 |

Phạm vi gồm các báo cáo, dữ liệu parser, mã Java trích xuất, disassembly native, script phân tích, manifest Android và ELF `dic` đã giải mã. Các file tạm, cache Python và chính `analysis-manifest.json` được bỏ qua; việc loại manifest khỏi chính nó tránh vòng lặp hash.

## Tái tạo

Chạy từ thư mục gốc dự án:

```powershell
py -3 analysis/build_analysis_manifest.py
```

Script [`build_analysis_manifest.py`](build_analysis_manifest.py) quét đệ quy các phần mở rộng được hỗ trợ, sắp xếp đường dẫn ổn định rồi ghi lại [`analysis-manifest.json`](analysis-manifest.json). Chạy lại lệnh sau khi thêm hoặc sửa artifact để cập nhật số lượng, kích thước và hash.

Để kiểm tra một file riêng trên PowerShell:

```powershell
Get-FileHash analysis/native/smbFft.asm -Algorithm SHA256
```

Giá trị trả về phải trùng trường `sha256` của đường dẫn tương ứng trong manifest.
