# Inventory symbol native ARM64

Đã quét 18 thư viện `.so` trong `lib/arm64-v8a/` bằng ELF parser. Tổng cộng có 3.888 dynamic symbol, trong đó 100 symbol JNI có tên `Java_*`. Kết quả đầy đủ nằm ở [native-arm64-symbol-inventory.json](native-arm64-symbol-inventory.json), script tái lập ở [inventory_native_arm64.py](inventory_native_arm64.py).

Các thư viện âm thanh của ORG (`libnmusic`, `libnopensl`, `libnpitchshift`, `libnbandbooster`, `libnequalizer`, `libnflanger`, `libngverb`, `libnlame`, `libnsf2`) xuất hiện cùng nhóm JNI/âm thanh. `libnms` đã được xác định là thành phần Pangle/PGL qua `JNI_OnLoad` và tên lớp `com/pgl/ssdk/ces/a`. [Inventory toàn bộ ELF](THIRD-PARTY-NATIVE.md) đã phân loại các thư viện còn lại thành AndroidX DataStore, AppLovin và Pangle/ByteDance.
