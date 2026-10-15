# Phân loại phần cuối symbol ARM32

[Disassembler toàn ABI](NATIVE-ALL-ABI-DISASSEMBLY.md) dừng ở 118 byte cuối của năm symbol Thumb. Script [analyze_arm32_tails.py](analyze_arm32_tails.py) trích nguyên byte, địa chỉ, các word little-endian và cách diễn giải float vào [arm32-undecoded-tail-analysis.json](arm32-undecoded-tail-analysis.json).

| Symbol | Byte | Phân loại | Bằng chứng chính |
|---|---:|---|---|
| `NEqualizer_build` | 20 | pool hằng số dấu phẩy động | Năm word lần lượt giải thành `8750`, `6250`, `7500`, `10000` và xấp xỉ `0,01`. |
| `NGVerb_setroomsize` | 56 | pool hằng số dấu phẩy động/literal | Các word chứa `0,41`, `0,30`, `0,155`, `0`, `0,00294`, `0,63245`, `0,7071`, `0,81649`, `0,96`, `-0,96` và bốn literal lặp. |
| `NMusic_code` | 2 | padding/sentinel | Toàn vùng chỉ là `ffff`, nằm sau nhánh quay vòng và epilogue trả về. |
| `NOpenSL_MixWrite` | 24 | pool số nguyên/địa chỉ | Nằm sau epilogue `pop ... pc` và một nhánh tới thunk; các word còn lại có dạng giới hạn và offset. |
| `NSF2_Voice` | 16 | pool số nguyên/địa chỉ | Nằm sau epilogue `pop ... pc`; bốn word có dạng địa chỉ/offset ELF. |

Các vùng trên thuộc kích thước symbol do ELF khai báo nhưng không phải một luồng lệnh Thumb tuyến tính. Vì vậy giữ chúng dưới dạng `Raw hex` chính xác hơn việc bật chế độ skip-data rồi đếm các byte dữ liệu thành lệnh. Toàn bộ byte của 75 JNI ARM32 hiện đã được bảo toàn: 15.430 byte lệnh Capstone và 118 byte dữ liệu/padding đã phân loại.
