# Dữ liệu Chord, System và Model trong `assets/data.zip`

## Kết quả chính

`assets/data.zip` chứa 169 entry ZIP, gồm 18 thư mục và 151 file. Script
[`parse_data_zip.py`](parse_data_zip.py) đọc trực tiếp ZIP lồng trong APK, kiểm tra
cấu trúc từng nhóm và ghi kết quả đầy đủ vào
[`data-zip-inventory.json`](data-zip-inventory.json).

| Loại | Số file | Kết quả |
| --- | ---: | --- |
| Chord | 33 | Mỗi file có đúng 12 mặt nạ 12 bit |
| System | 36 | Giải mã đủ 36 file đến đúng EOF |
| Model | 6 | Cấu hình bàn phím 25, 32, 49, 61, 76 và 88 phím |
| Script | 24 | Văn bản nguồn, đã thống kê kích thước và số dòng |
| Skin | 10 | Văn bản cấu hình skin |
| PNG | 36 | Header IHDR hợp lệ, đã lấy kích thước và loại màu |
| WAV | 2 | PCM mono 16 bit, header đọc được hoàn chỉnh |

## Chord: mặt nạ cao độ 12 bit

Mỗi dòng trong `.Chord` là một số nguyên từ 0 đến 4095. Bit `n` biểu diễn cao
độ `n` trong quãng tám. Ví dụ `Major.Chord` bắt đầu bằng `145`, tức
`145 = 1 + 16 + 128`, đặt các bit `[0, 4, 7]`: hợp âm trưởng gồm nốt gốc,
quãng ba trưởng và quãng năm đúng. `Minor.Chord` bắt đầu bằng `137`, tương ứng
`[0, 3, 7]`.

Mỗi file có 12 dòng cho 12 nốt gốc. Với 31/33 file, dòng `i` đúng bằng mặt nạ
dòng đầu quay vòng `i` bit trong không gian 12 bit. Hai file `Customized Major`
và `Customized Minor` cố ý thay đổi một số dòng nên không phải phép chuyển giọng
thuần túy; inventory ghi rõ các chỉ số lệch.

DEX của `sofeh.music.Music.ChordTable(String)` xác nhận cùng mô hình: phương thức
nhận tên và các khoảng cao độ, sau đó dựng `fChordTable[(chord * 12) + root]`
bằng biểu thức `1 << ((interval + root) % 12)`. Bằng chứng REA nằm trong
[`chord-table-loader.json`](chord-table-loader.json).

## System: layout nhị phân đã kiểm tra EOF

Tất cả 36 file dùng layout little-endian sau:

| Trường | Kích thước |
| --- | ---: |
| Header cố định `Music Studio System` | 32 byte |
| Độ dài metadata UTF-8 | `int32` |
| Metadata `name\rversion\rdescription` | biến đổi |
| Group | `int32` |
| Start key | 1 byte |
| Selected keys | mặt nạ `int32` |
| Reserved | 28 byte |
| Tuning | 24 số `double`, tổng 192 byte |

Parser dừng đúng EOF ở cả 36 file. Toàn bộ `group` và 28 byte reserved đều bằng
0. Các phiên bản gồm 30 file không ghi version, 3 file `1.10` và 3 file `1.2.4`.
Giá trị tuning quan sát được chỉ gồm `-1.0`, `-0.5`, `0`, `0.5`, `1.0`; số lần
tương ứng là 67, 123, 666, 2 và 6. Đây là giá trị lưu trong file; ý nghĩa đơn vị
âm nhạc chưa được xác minh bằng chạy ứng dụng.

## Model và WAV đi kèm

Sáu `.Model` là văn bản cấu hình. Trường `Key` lần lượt là:

| Model | Count | Start | Chord |
| --- | ---: | ---: | ---: |
| Standard 25 Key | 25 | 48 | 11 |
| Standard 32 Key | 32 | 53 | 12 |
| Standard 49 Key | 49 | 48 | 17 |
| Standard 61 Key | 61 | 36 | 17 |
| Standard 76 Key | 76 | 28 | 19 |
| Standard 88 Key | 88 | 21 | 20 |

Hai WAV là `Pad/Loop/Tomba.wav` (32 kHz, 37.969 frame, khoảng 1,187 giây) và
`Pad/Sound/Chap.wav` (44,1 kHz, 8.816 frame, khoảng 0,200 giây). Cả hai đều mono,
PCM 16 bit. SHA-256 và thông số chính xác được lưu trong inventory.

## Chạy lại

```powershell
py -3 analysis\parse_data_zip.py
```

Phân tích này là phân tích tĩnh trên đúng APK có SHA-256
`9512e006f4c1780683aa19510dad701c415c98f3d87d0adbd7559088813e8855`;
ứng dụng không được thực thi.
