# Inventory thực của Default.Template

`parse_default_template.py` đã giải mã tuần tự toàn bộ `assets/Default.Template` phiên bản 1.4.6 và kết thúc đúng tại byte 67.848.221, bằng đúng kích thước file. Đây là kiểm chứng mạnh hơn việc tìm chuỗi hoặc phỏng đoán offset: mọi khối biến độ dài ở trước đều phải được tiêu thụ đúng thì parser mới tới EOF chính xác.

## Tổng số

| Thành phần | Số lượng |
| --- | ---: |
| Slot wave được khai báo | 4.889 |
| Wave có dữ liệu | 1.251 |
| Tổng PCM trong kho wave | 62.086.248 byte |
| Instrument | 92 |
| Section của Instrument | 2.001 |
| System | 11 |
| Track | 2.648 |
| Event | 158.018 |
| TrackGroup | 610 |

Toàn bộ 2.001 section của các Instrument tham chiếu kho wave chung bằng `WaveIndex`; không section nào trong asset này mang PCM nội tuyến. Không event nào có type 101, nên Template mặc định cũng không có sample nhúng trong event. Hai type event xuất hiện là `1` (79.192 lần) và `2` (78.826 lần); ý nghĩa open/release được xác nhận trong `EVENT-NOTE-LIFECYCLE.md`.

Đối chiếu đầy đủ WaveIndex, kích thước PCM và công cụ xuất WAV nằm trong `XUAT-PCM-WAV.md`.

## Ranh giới đã xác nhận

| Khối | Offset bắt đầu | Offset kết thúc |
| --- | ---: | ---: |
| Header/Music và kho wave | 0 | 62.096.457 |
| Ba FX block cấp Music | 62.096.457 | 62.096.689 |
| Header collection Instruments | 62.096.689 | 62.096.708 |
| 92 Instrument | 62.096.708 | 62.434.776 |
| Header collection Systems | 62.434.776 | 62.434.795 |
| 11 System | 62.434.795 | 62.437.497 |
| Header collection Tracks | 62.437.497 | 62.437.516 |
| 2.648 Track | 62.437.516 | 67.762.805 |
| Count và 610 TrackGroup | 67.762.805 | 67.848.221 (EOF) |

## Nội dung quan sát được

92 Instrument gồm 51 type 0, 2 type 1 và 39 type 2. Một số tên đầu danh sách là `-`, `Piano DNC`, `Soprano Choir DNC`, `Jazz Flute DNC`, `Finger Bass`, `Strings`, `Violin DNC`, `Saw Muff DNC`, `Organ DNC` và `General Drumkit`. Inventory JSON chứa đủ tên, mode, sound, số section, offset và kích thước từng Instrument.

11 System có tên: hai entry `Normal`, rồi `Shoor`, `Homaun`, `Nava`, `Mahoor`, `3Gah`, `4Gah`, `Rast5Gah`, `Arabic` và `Kurdish`. Mỗi entry kèm nguyên 24 số tuning.

Trong 2.648 Track, có 2.646 track type 1, một track type 2 (`Wave`) và một track type 3 (`Rhythm`). Các track đầu của group 0 gồm `Master`, `Up 1`, `Up 2`, `Up 3`, `Low`, `Rhythm`, `Wave` và `Bass`. Phần còn lại chủ yếu thuộc các group style/rhythm bằng ID 32-bit.

Các TrackGroup đầu gồm `Music`, ba intro, break, ba ending, `Global` và các variation. Cuối file có các group pad. Quan hệ parent/group được giữ nguyên trong JSON để có thể dựng cây ở bước sau.

## File tạo ra

- `parse_default_template.py`: parser đọc trực tiếp entry trong APK, có kiểm tra biên và yêu cầu kết thúc đúng EOF.
- `default-template-inventory.json`: inventory compact, 892.169 byte; chứa đầy đủ 92 Instrument, 11 System, 2.648 Track và 610 TrackGroup nhưng không bung toàn bộ 158.018 event.
- Chạy lại bằng `py -3 analysis\parse_default_template.py --compact --output analysis\default-template-inventory.json`.
- Bỏ `--compact` để xuất cấu trúc chi tiết, gồm từng wave, STS và event; kết quả sẽ lớn đáng kể.

## Giới hạn

Parser hiện hỗ trợ hai Template đi kèm APK: 1.4.6 và nhánh cũ 1.2.4 được mô tả trong `EMPTY-TEMPLATE-INVENTORY.md`. Nó chưa diễn giải ý nghĩa nghiệp vụ của event type 1/2 và không giải mã PCM thành WAV. Việc parser tới đúng EOF xác nhận layout của hai asset này, không chứng minh mọi file Template ngoài ứng dụng đều hợp lệ hoặc an toàn.

Nguồn APK có SHA-256 `9512e006f4c1780683aa19510dad701c415c98f3d87d0adbd7559088813e8855`; ứng dụng không được thực thi.
