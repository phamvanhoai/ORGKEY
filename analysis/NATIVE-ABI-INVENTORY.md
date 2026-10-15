# Phân bố thư viện native theo ABI

APK chứa 58 file `.so`:

| ABI | Số file |
| --- | ---: |
| `arm64-v8a` | 18 |
| `armeabi-v7a` | 18 |
| `x86` | 11 |
| `x86_64` | 11 |

Chín thư viện âm thanh ORG có đủ cả bốn ABI. AppLovin và AndroidX DataStore cũng có đủ bốn; bảy thư viện Pangle/ByteDance chỉ có ARM64 và ARM32. Toàn bộ 58 ELF đã được phân loại và kiểm kê symbol/import trong [báo cáo native bên thứ ba](THIRD-PARTY-NATIVE.md); 75 JNI `sofeh` đã được tách assembly riêng trên mỗi ABI.
