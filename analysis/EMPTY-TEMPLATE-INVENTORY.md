# Inventory thực của empty.Template

Parser đã được mở rộng cho định dạng 1.2.4 và đọc toàn bộ `assets/empty.Template` đến đúng EOF tại byte 313.042. Kết quả cho thấy tên `empty` chỉ có nghĩa đây là dự án nền ít nội dung; file vẫn chứa metronome, một mẫu piano, System và 14 Track mặc định.

## Tổng số

| Thành phần | Số lượng |
| --- | ---: |
| Kho wave dùng chung | 0 |
| Instrument | 2 |
| Section | 3 |
| PCM nội tuyến | 297.880 byte |
| System | 1 |
| Track | 14 |
| Event | 0 |
| TrackGroup | 1 |

Hai Instrument là:

| Tên | Type | Section | Kích thước object |
| --- | ---: | ---: | ---: |
| `-` | 1 | 2 | 60.405 byte |
| `Piano (LOW)` | 0 | 1 | 238.470 byte |

Instrument `-` chứa `Metronome Long` (mono 48 kHz, 17.800 sample, 35.600 byte) và `Metronome Short` (mono 48 kHz, 12.031 sample, 24.062 byte). `Piano (LOW)` chứa section `EB2` ở note index 63, mono 44,1 kHz, 119.109 sample, 238.218 byte. Tổng ba payload PCM là 297.880 byte.

Ba section này đã được xuất thành WAV và đọc kiểm tra lại; xem `XUAT-PCM-WAV.md`.

System duy nhất có tên `Normal`. 14 Track đều thuộc group 0, bắt đầu bằng `Master`, `Up 1`, `Up 2`, `Up 3`, `Low`, `Rhythm`, `Wave`, `Bass`, rồi các accompaniment. Mỗi Track được reader cũ chuẩn hóa thành 13 slot STS dù giá trị count lưu trong file khác; chỉ một slot được bật. Không Track nào chứa event.

TrackGroup duy nhất là `Music`, bắt đầu tại offset 312.970 và dài 72 byte. Nó kết thúc đúng tại EOF, xác nhận nhánh 1.2.4 không có bốn STS index và FX block của các phiên bản mới.

## Khác biệt quan trọng với Default.Template 1.4.6

| Đặc điểm | empty 1.2.4 | Default 1.4.6 |
| --- | ---: | ---: |
| Kích thước | 313.042 | 67.848.221 |
| Kho wave dùng chung | không | 1.251 wave |
| Cách lưu PCM Instrument | nội tuyến | tham chiếu `WaveIndex` |
| Instrument | 2 | 92 |
| Track | 14 | 2.648 |
| Event | 0 | 158.018 |
| TrackGroup | 1 | 610 |

Các nhánh tương thích cần thiết để parse 1.2.4 gồm ADS chung 16 sound, tên mode/sound đầu bị lược khỏi stream, STS count ép thành 13, STS áp dụng cả với track ngoài group 0, và TrackGroup chưa có các trường mở rộng 1.4.2/1.4.6.

## File và cách chạy

- Inventory: `empty-template-inventory.json`.
- Parser chung: `parse_default_template.py`.
- Lệnh: `py -3 analysis\parse_default_template.py --asset assets/empty.Template --compact --output analysis\empty-template-inventory.json`.

Parser chỉ hỗ trợ chính xác hai phiên bản asset đã kiểm tra là 1.2.4 và 1.4.6, không thực thi ứng dụng và không phát PCM.
