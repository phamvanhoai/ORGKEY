# Catalog nội dung tải xuống `download.en`

## Cấu trúc đã giải mã

[`inspect_download_catalog.py`](inspect_download_catalog.py) đọc trực tiếp
`assets/data.zip!/download.en` và xuất
[`download-catalog-inventory.json`](download-catalog-inventory.json). File dài
49.796 byte, 3.192 dòng và được parse hết mà không còn record lỗi.

Mỗi nhóm bắt đầu bằng năm dòng thông tin người đóng góp:

```text
by Tên tác giả
email hoặc -
kênh/video hoặc -
website hoặc -
flag
```

Sau dấu phân cách là các record sáu dòng:

```text
tiêu đề
mô tả
tên archive ZIP
tên preview MP3 hoặc -
timestamp Unix millisecond
--------
```

Catalog có 101 nhóm tác giả và 431 record nội dung. Một nhóm `by Mahdi Ebrahimi`
không có item và dùng `--------` ở vị trí dấu phân cách nhóm; đây là biến thể có
thật trong asset, không phải lỗi parser. Field `flag` bằng `0` ở cả 101 nhóm.

## Thống kê nội dung

Tất cả 431 file tải chính đều có đuôi `.zip`; 21 record có preview MP3. Tên file
cho thấy các họ nội dung sau:

| Prefix | Số record |
| --- | ---: |
| `set-` | 162 |
| `ins-` | 88 |
| `pad-` | 70 |
| `skn-` | 52 |
| `sty-` | 44 |
| `sys-` | 13 |
| khác | 2 |

Các prefix phù hợp với SET, Instrument, Pad, Skin, Style và System được ứng dụng
hỗ trợ, nhưng đây là quy ước tên file của catalog; ZIP có thể chứa nhiều loại
tài nguyên và chưa được tải để kiểm tra payload.

Cả 431 timestamp đều chuyển đổi được. Khoảng ngày trong catalog là từ
2014-12-20 đến 2023-07-30 theo UTC. Trong 101 nhóm, 66 có email, 28 có đường dẫn
kênh/video và 15 có website thay vì dấu `-`.

## Tên archive trùng

Có hai tên archive bị lặp:

- `set-hgn.zip` xuất hiện ba lần với tiêu đề Techno SET, Persian SET và Kurdish SET.
- `ins-my.zip` xuất hiện hai lần với tiêu đề Mix Self-Play Instruments và Kurdish
  Systems.

Inventory giữ nguyên cả năm record và ngữ cảnh tác giả. Việc trùng tên có thể là
các mục dùng chung URL hoặc lỗi catalog; chưa có dữ liệu mạng/runtime để kết luận.

## Ý nghĩa vận hành

`download.en` là manifest nội dung, không chứa các ZIP được liệt kê. Các trường
archive/preview đều chỉ là tên tương đối; base URL không nằm trong file này.
Thông tin liên hệ và liên kết tác giả là dữ liệu hiển thị đi kèm từng nhóm.

Không có class mang tên Download thuộc namespace riêng của ứng dụng trong kết
quả tìm tên class; phần lớn kết quả `Download` thuộc SDK quảng cáo. Reader mạng
của ứng dụng có thể nằm trong class đã rút gọn, nên lượt này chưa gán base URL
hoặc cơ chế HTTP nếu chưa có bằng chứng gọi cụ thể.

## Chạy lại

```powershell
py -3 analysis\inspect_download_catalog.py
```

Phân tích là tĩnh trên asset đóng gói trong APK; không gửi request và không tải
bất kỳ archive bên ngoài nào.
