# Native SDK bên thứ ba

[inventory_native_all.py](inventory_native_all.py) quét trực tiếp toàn bộ 58 ELF trong APK. Kết quả chi tiết gồm hash, ABI, kiến trúc, symbol, import, export, JNI và `DT_NEEDED` nằm ở [native-all-library-inventory.json](native-all-library-inventory.json); bảng gọn nằm ở [native-all-library-summary.csv](native-all-library-summary.csv).

| Nhóm | Bản ELF | Thư viện duy nhất | ABI |
|---|---:|---:|---|
| Âm thanh ORG/Sofeh | 36 | 9 | ARM64, ARM32, x86, x86_64 |
| Pangle/ByteDance | 14 | 7 | ARM64, ARM32 |
| AppLovin | 4 | 1 | ARM64, ARM32, x86, x86_64 |
| AndroidX DataStore | 4 | 1 | ARM64, ARM32, x86, x86_64 |

Không còn ELF chưa phân loại. Bảy thư viện Pangle/ByteDance chỉ được đóng gói cho ARM; đây là khác biệt của SDK quảng cáo, trong khi chín thư viện âm thanh ORG có đủ bốn ABI.

## JNI và chức năng quan sát được

- `libapplovin-native-crash-reporter.so` xuất ba JNI `enable`, `disable` và `updateAdInfo` thuộc `com.applovin.impl.sdk.NativeCrashReporter`.
- `libbuffer_pgl.so` xuất 11 JNI cho `MemoryBuffer`, `MMapBuffer` và `MReMapBuffer` của GeckoX.
- `libfile_lock_pgl.so` xuất sáu JNI khóa file, khóa đoạn, lấy file descriptor và giải phóng khóa của GeckoX.
- `libpglarmor.so` xuất `PglCryptUtils.bc` và phụ thuộc thêm `libz.so`.
- `libdatastore_shared_counter.so` xuất bốn JNI tạo, đọc, tăng và truncate bộ đếm chia sẻ AndroidX.
- `libapminsighta`, `libapminsightb`, `libnms` và `libtt_ugen_layout` không xuất tên `Java_*`; việc nối native có thể đi qua `JNI_OnLoad`/`RegisterNatives` hoặc entry point C/C++ riêng.

Toàn bộ 58 ELF chỉ khai báo phụ thuộc hệ thống trong tập `libandroid`, `libc`, `libdl`, `liblog`, `libm`, `libOpenSLES` và `libz`. `libOpenSLES` thuộc đường âm thanh ORG; `libandroid` xuất hiện ở crash reporter AppLovin. Inventory không thấy một thư viện ứng dụng bí danh khác liên kết trực tiếp vào chín thư viện `sofeh` qua `DT_NEEDED`.

Phân loại trên chứng minh ranh giới đóng gói và bề mặt native tĩnh. Nó không chứng minh dữ liệu nào được SDK gửi qua mạng; kết luận đó cần quan sát runtime hoặc phân tích lớp Java gọi SDK.
