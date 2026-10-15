# Vòng đời event nốt trong Template

Hai type xuất hiện trong 158.018 `KeyData` của `Default.Template` đã được nối với luồng ghi và phát:

| Event | Số lượng | Ý nghĩa xác nhận từ mã |
| ---: | ---: | --- |
| 1 | 79.192 | nhả/đóng nốt |
| 2 | 78.826 | mở nốt thông thường |

## Event 2: mở nốt

`Track.Play` với event 2 lưu ID/key/touch vào trạng thái hiện tại, gọi `_scriptTouch`, rồi gọi `Channels.OpenKey` nếu script không xử lý event. Các tham số chính được ánh xạ như sau:

```text
KeyData.ID      -> voice/note ID
KeyData.Key     -> key
KeyData.Touch   -> touch truyền vào OpenKey
KeyData.Length  -> chiều dài dự kiến
KeyData.Tune    -> tham số tune của Play
```

Nếu connection effect ở slot 1 đang bật, Track còn đưa ID, key, touch, event và length vào năm mảng connection của `Channels`. Các mảng này có 10 phần tử và được dùng để nối/chuyển voice giữa các nốt.

## Event 1: nhả nốt

Với event 1, Track tìm và xóa ID tương ứng khỏi danh sách connection. Sau đó nó gọi `_scriptRelease`; nếu script không nhận xử lý thì gọi `Channels.Close(ID)`. Khi connection effect yêu cầu giữ chuỗi nốt, code có thể mở lại voice connection cuối trước khi đóng ID hiện tại.

Trong asset thực, toàn bộ event 1 có `Length = -1`; phần length hữu ích nằm ở event mở. Touch của event 1 gần như luôn `-1`, phù hợp vai trò release thay vì touch mở nốt.

## Cách ghi cặp event

`Track.RecordMusic` ghi event mở trước. Khi nhận event 1, nó duyệt ngược danh sách để tìm event 2 hoặc 4 có cùng ID, cập nhật:

```text
open.Length = (releaseTime - open.Time) + 1
```

sau đó ghi thêm event 1 tại thời điểm release. Vì vậy ID là khóa nối giữa open/release, còn `Length` trên record mở cho phép scheduler biết thời lượng ngay cả trước khi gặp record đóng.

`PlayNext` chỉ phát event khi thời gian hiện tại đạt `KeyData.Time`. Với event 2/4/101 có length dương, nó cũng bỏ qua event quá cũ nếu vị trí phát đã vượt quá `Time + Length + 10`; điều này tránh mở một nốt đã kết thúc khi bắt đầu phát giữa timeline.

## Đối chiếu dữ liệu Default.Template

Phân tích theo từng Track và ghép ID theo thứ tự tìm ngược cho thấy 78.247 cặp open/release có thể ghép trực tiếp. Còn 579 event mở và 945 event đóng không có đối tác trong cùng Track theo phép ghép đơn giản. Điều này không nhất thiết là dữ liệu hỏng: style có giới hạn, loop, điểm bắt đầu/kết thúc và ID có thể lặp, trong khi logic runtime còn xét group và trạng thái channel.

Trong các cặp ghép được, key của open và release giống nhau gần như toàn bộ; chỉ hai trường hợp lệch theo phép ghép tĩnh. 66.381 cặp có `release.Time - open.Time == open.Length`; 10.539 cặp lệch `-1`, phù hợp với công thức ghi có cộng một. Các độ lệch khác cần được hiểu cùng loop/biên section, nên không được dùng để kết luận file lỗi.

## Các event liên quan nhưng không xuất hiện trong asset này

- Event 4 đi cùng nhánh mở nốt như event 2 nhưng truyền cờ riêng vào `OpenKey`.
- Event 3 đóng tất cả channel.
- Event 101 mở một `InstSec` nhúng và đặt vị trí sample theo thời gian hiện tại.
- Các event 11/12 điều khiển pedal; 13/14 mute; 51–53 thay đổi touch/balance/pitch; còn nhiều mã điều khiển FX, mode, STS và chord trong dispatcher.

## Bằng chứng

- `track-play-dispatch.json` và `source/track-play-dispatch.java`: dispatch event sang Channels/script.
- `track-record-music-event.json`: cách ghi event mở/đóng và cập nhật Length.
- `track-play-next.json`: scheduler theo Time/Length.
- `track-write-event.json`: ánh xạ tham số vào KeyData.
- `default-template-inventory.json`: số lượng type event thực tế.

Đây là phân tích tĩnh; chưa phát timeline trên Android để đo thời điểm âm thanh thực tế.
