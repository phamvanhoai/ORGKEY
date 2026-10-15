# Disassembly JNI `sofeh` trên toàn bộ ABI

Bộ sinh [dump_all_sofeh_abis.py](dump_all_sofeh_abis.py) đọc trực tiếp ELF trong APK, lấy mọi dynamic symbol `Java_sofeh_*` có thân hàm và tạo assembly tại `native/abis/<ABI>/`. Dữ liệu máy đọc được nằm trong [native-sofeh-all-abi-disassembly.json](native-sofeh-all-abi-disassembly.json).

| ABI | Hàm JNI | Byte symbol | Byte Capstone giải mã | Lệnh | Hàm giải mã hết byte |
|---|---:|---:|---:|---:|---:|
| arm64-v8a | 75 | 18.836 | 18.836 | 4.709 | 75 |
| armeabi-v7a | 75 | 15.548 | 15.430 | 5.212 | 70 |
| x86 | 75 | 27.044 | 27.044 | 7.087 | 75 |
| x86_64 | 75 | 21.514 | 21.514 | 4.891 | 75 |
| **Tổng** | **300** | **82.942** | **82.824** | **21.899** | **295** |

ARM32 còn 118 byte ở cuối năm symbol mà giải mã tuyến tính không nhận là lệnh hợp lệ: `NEqualizer_build` (20 byte), `NGVerb_setroomsize` (56 byte), `NMusic_code` (2 byte), `NOpenSL_MixWrite` (24 byte) và `NSF2_Voice` (16 byte). Script giữ nguyên các byte này dưới dạng `Raw hex` trong từng file `.asm`. [Báo cáo phân loại phần cuối ARM32](ARM32-TAIL-CLASSIFICATION.md) xác định chúng là pool hằng số/literal, bảng dữ liệu hoặc padding và không tính chúng là byte lệnh.

Kết quả này hoàn tất việc tách riêng thân của toàn bộ 75 JNI lõi ORG trên cả bốn kiến trúc có trong APK. Phạm vi chưa bao gồm mọi hàm nội bộ không xuất khẩu qua JNI và các thư viện SDK bên thứ ba.
