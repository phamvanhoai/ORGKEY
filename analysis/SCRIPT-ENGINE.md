# Script nhạc cụ và luồng thực thi

## Phạm vi

`assets/data.zip` chứa 24 file `.Script`: 8 Legato, 2 Move, 7 Octave,
6 Shake và một script rỗng. Công cụ
[`inspect_script_assets.py`](inspect_script_assets.py) đọc trực tiếp ZIP lồng trong
APK và xuất [`script-assets-inventory.json`](script-assets-inventory.json).

Đây là kiểm tra cú pháp tĩnh theo các mẫu đi kèm ứng dụng, không phải một parser
hoàn chỉnh của ngôn ngữ script.

## Sự kiện và thao tác quan sát được

| Event `on(...)` | Số script |
| --- | ---: |
| `touch` | 17 |
| `release` | 9 |
| `modulate` | 5 |
| `aftertouch` | 4 |

Các script gọi `play` 18 lần, `timer` 5, `stop` 2 và `setting` 18 lần. Tham số
quan sát được của `play` gồm `connectkey`, `connectspeed`, `octavemove`,
`autorelease`, `key`, `sound`, `volume`, `id` và `velocity`.

Hai slot effect được thao tác trực tiếp:

- `effect[1]`: `enable`, `Mode`, `Speed`, được nhóm Legato sử dụng.
- `effect[2]`: `enable`, `Depth`, `Speed`, được nhóm Shake sử dụng.

Có 18 khai báo giao diện cài đặt: 12 slider, 5 tiêu đề text và 1 toggle.
Inventory lưu tên biến, tiêu đề, giá trị reset và miền `from`/`to` cho từng khai
báo. Cả 24 file cân bằng dấu ngoặc nhọn; một file `Empty.Script` có nội dung rỗng.

## Luồng từ Music đến trình thông dịch

REA xác nhận ba tầng gọi:

1. `Music.ScriptEvent(name, names, values)` duyệt toàn bộ `fTracksMain`. Chỉ Track
   có `fRecord` mới nhận event; kết quả trả về là OR của các Track đã xử lý.
2. `Track.ScriptEvent(...)` kiểm tra mode hiện tại, lấy `InstMode` của Instrument
   đang chọn rồi chuyển event xuống mode. Khi tên event rỗng, nó hủy timer script,
   reset trình thông dịch và `ScriptLastKeys`, sau đó đổi event thành `mode`.
3. `InstMode.ScriptEvent(...)` khởi tạo `ScriptSetting` khi cần, biên dịch/nạp
   `ScriptText` theo kiểu lazy ở lần dùng đầu tiên, dựng bảng tham số tên–giá trị,
   rồi gọi trình thông dịch. Nếu script trả một số khác 0, hàm Java trả `true`.

Điều này giải thích `return(true)` trong 13 script mẫu: handler báo rằng event đã
được xử lý. Các luồng gọi hiện có dùng kết quả này để có thể chặn xử lý mặc định;
ví dụ dispatcher chỉ áp dụng pedal mặc định khi `ScriptEvent("pedaloff", ...)`
trả `false`.

`InstMode.ScriptEvent` bắt mọi `Exception` và trả `false` mà không phát lại lỗi.
Vì vậy lỗi script quan sát từ luồng Java này có thể biểu hiện như handler không
xử lý event thay vì làm dừng luồng phát.

## Ví dụ hành vi từ asset

- **Dynamic Legato** dùng `connectkey`/`connectspeed` để nối nốt mới với nốt cũ.
- **Move** dùng `timer(25/30)` để phát chuỗi nốt di chuyển và dừng theo trạng thái
  touch/release.
- **Repeat Octave** đổi `octavemove` theo timer và hỗ trợ ngưỡng bàn phím.
- **Modulation Octave** giữ `id`, key và velocity, dừng rồi phát lại cùng voice ID
  khi mức modulation đổi vùng.
- **Shake** ánh xạ aftertouch hoặc modulation vào Depth/Speed của effect slot 2.

## Bằng chứng

- [`script-event.json`](script-event.json): `Music.ScriptEvent`.
- [`track-script-event.json`](track-script-event.json): `Track.ScriptEvent`.
- [`instmode-script-event.json`](instmode-script-event.json): nạp và gọi trình
  thông dịch ở `InstMode`.
- [`instmode-class.json`](instmode-class.json): các field script của mode.

Phân tích được thực hiện tĩnh trên APK SHA-256
`9512e006f4c1780683aa19510dad701c415c98f3d87d0adbd7559088813e8855`;
không chạy script hoặc ứng dụng.
