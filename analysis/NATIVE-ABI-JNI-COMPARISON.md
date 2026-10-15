# So sánh JNI giữa các ABI

Đã quét `.dynsym` của bốn kiến trúc bằng [compare_native_abis.py](compare_native_abis.py). Kết quả đầy đủ ở [native-abi-jni-comparison.json](native-abi-jni-comparison.json).

| ABI | Thư viện `sofeh` | JNI `Java_sofeh_*` | Khớp ARM64 |
| --- | ---: | ---: | --- |
| `arm64-v8a` | 9 | 75 | Có |
| `armeabi-v7a` | 9 | 75 | Có |
| `x86` | 9 | 75 | Có |
| `x86_64` | 9 | 75 | Có |

Danh sách tên JNI giống hệt giữa bốn ABI. Phần triển khai lệnh máy của cả 75 hàm trên từng ABI đã được tách riêng trong [báo cáo disassembly toàn ABI](NATIVE-ALL-ABI-DISASSEMBLY.md).
