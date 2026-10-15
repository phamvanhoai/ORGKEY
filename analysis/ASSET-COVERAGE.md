# Coverage tài nguyên APK

Sau các vòng phân tích, các tài nguyên có ý nghĩa ứng dụng đã được phân loại như sau:

| Nhóm | Trạng thái |
| --- | --- |
| `Default.Template`, `empty.Template` | Đã parse tuần tự đến EOF, có inventory Instrument/System/Track/Event/PCM. |
| `Sound.Instrument`, `Constant.Instrument` | Đã xác định header, nhánh phiên bản và payload Instrument/wave. |
| `data.zip` | Đã phân tích Chord/System/Model/Script và quan hệ tải dữ liệu. |
| `assets/Chord` | Đã parse 37 định nghĩa hợp âm. |
| `assets/skin/*.Skin`, `*.Part` | Đã parse 24 file, 1.107 bản ghi và parser/render `us3`. |
| `assets/menu/*.txt` | Đã kiểm kê 33 file đa ngôn ngữ và quy ước liên kết. |
| `download.en` trong catalog | Đã kiểm kê 431 ZIP và 21 preview MP3. |
| `assets/dic` | Đã giải mã XOR khóa `dic`; kết quả là ELF x86 32-bit của Pangle/PGL. |
| Public suffix, MRAID, HTML quảng cáo | Đã nhận dạng là dữ liệu SDK bên thứ ba. |

Các phần chưa thể kết luận từ static analysis gồm thứ tự skin được chọn ở runtime và hành vi thực tế của Activity/audio trên thiết bị. Những giới hạn này đã được ghi trong các báo cáo chuyên đề.
