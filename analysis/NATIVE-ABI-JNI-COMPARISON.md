# So sánh JNI giữa các ABI

Đã quét `.dynsym` của bốn kiến trúc bằng [compare_native_abis.py](compare_native_abis.py). Kết quả đầy đủ ở [native-abi-jni-comparison.json](native-abi-jni-comparison.json).

| ABI | Thư viện `sofeh` | JNI `Java_sofeh_*` | Khớp ARM64 |
| --- | ---: | ---: | --- |
| `arm64-v8a` | 9 | 75 | Có |
| `armeabi-v7a` | 9 | 75 | Có |
| `x86` | 9 | 75 | Có |
| `x86_64` | 9 | 75 | Có |

Danh sách tên JNI giống hệt giữa bốn ABI. Do đó coverage ARM64 bao phủ đầy đủ bề mặt API JNI của các kiến trúc còn lại, dù phần triển khai lệnh máy có thể khác và chưa được disassemble riêng trên ARM32/x86/x86_64.
