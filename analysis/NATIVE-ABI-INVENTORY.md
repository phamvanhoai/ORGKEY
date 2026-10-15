# Phân bố thư viện native theo ABI

APK chứa 58 file `.so`:

| ABI | Số file |
| --- | ---: |
| `arm64-v8a` | 18 |
| `armeabi-v7a` | 18 |
| `x86` | 11 |
| `x86_64` | 11 |

Các symbol JNI đã lập bảng thuộc hai thư viện âm thanh `libnmusic.so` và `libnopensl.so` trên ARM64. Các file còn lại là thư viện xử lý hiệu ứng, codec hoặc SDK bên thứ ba; tên file không đủ để gán chắc chức năng nếu chưa disassemble từng ABI. Vì vậy inventory này xác nhận độ rộng ABI của APK, còn bằng chứng thuật toán native hiện tập trung ở ARM64.
