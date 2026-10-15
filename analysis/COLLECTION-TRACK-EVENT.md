# Collection, System, Track và Event trong Template

Tài liệu này nối tiếp `DINH-DANG-SET-INSTRUMENT.md`, tập trung vào phần sau kho wave và effect của một Music/Template.

## Khung collection chung

`Instruments`, `Systems` và `Tracks` đều kế thừa `MusicCollection`. Reader và writer ở lớp cơ sở đối xứng:

```text
MusicObject header của collection
int32-le count
object[0]
object[1]
...
object[count - 1]
```

Khi đọc, mỗi phần tử được tạo bằng `Add(-1)` rồi tự đọc payload của nó. Vì mỗi object lại bắt đầu bằng header `MusicObject`, collection không có bảng offset riêng. Muốn đến phần tử sau phải hiểu và bỏ qua đúng payload của phần tử trước.

Trong `Music.ReadFromStream`, thứ tự ba collection là:

```text
Instruments -> Systems -> Tracks
```

## Hai kiểu chuỗi

Reader có ít nhất hai cách lưu chuỗi:

| Hàm | Bố cục |
| --- | --- |
| `al0.e()` | `int32-le length` rồi đúng `length` byte |
| `al0.a()` | bỏ 1 byte đầu, đọc vùng cố định 31 byte, dừng tại NUL |

Header `MusicObject`, tên mode/sound và script dùng chuỗi có độ dài. Tên `InstSec` và `TrackGroup` dùng chuỗi cố định 32 byte. Cả hai đều gọi `new String(...)` mà không nêu charset.

## System

Mỗi `System_` có header chung, sau đó:

```text
byte       startKey
int32-le   selectedKeys
byte[]     reserved (kích thước lấy từ đối tượng)
24 * f64   bảng tuning, mỗi số đảo byte trước khi đổi sang double
```

Bảng 24 số thực cho thấy System lưu bảng điều chỉnh cao độ/tuning riêng. Phân tích tĩnh chưa xác định đơn vị hoặc ý nghĩa từng vị trí.

## Track

Sau header chung, `Track.ReadFromStream` đọc:

- type, kind, MIDI channel output;
- system và instrument index;
- MIDI instrument, solo, mute, record và MIDI channel input;
- hai MIDI bank, chord root/type và color;
- 25 số 32-bit dự trữ;
- số STS và payload các STS đang bật;
- pedal mode, pedal, giới hạn vùng phím;
- khối `Channels`;
- `int32-le` số KeyData rồi từng event liên tiếp.

Với phiên bản trước 1.2.9, số STS bị ép thành 13. Việc đọc payload STS phụ thuộc group và phiên bản. Khi nạp từng KeyData của group 0, reader cập nhật `Tracks.fEndTime` theo `Time + Length - 1`.

## KeyData/event

Một bản ghi cơ bản có bố cục:

```text
int16-le Event
int16-le Tune
int32-le ID
int32-le Key
int32-le Touch
int32-le Time
int32-le Length
```

Nếu `Event == 101`, ngay sau các trường trên có thêm một `InstSec` hoàn chỉnh, gồm metadata section và PCM/tham chiếu wave như đã mô tả trong báo cáo Instrument. Do đó độ dài event là biến đổi; không thể coi danh sách event là mảng record có kích thước cố định.

Event 2 mở nốt và event 1 nhả/đóng nốt; cách ghép ID, cập nhật Length và dispatch sang `Channels` được phân tích trong `EVENT-NOTE-LIFECYCLE.md`.

## TrackGroup và phần mở rộng của Tracks

Sau collection Track cơ sở, `Tracks` đọc thêm:

```text
int32-le groupCount
TrackGroup[groupCount]
```

Mỗi TrackGroup có group ID, parent ID, tên cố định 32 byte, section/type, loop/limit, khoảng giới hạn, jump group, color, chord, tempo, phím tắt và meter. Các nhánh phiên bản đáng chú ý:

- trước 1.3.0: tempo được đổi theo tỉ lệ `120/100`;
- trước 1.4.8: hai giá trị meter được tách từ hai nibble của một byte;
- từ 1.4.6: có bốn STS index;
- từ 1.4.2: có payload effect;
- từ 1.4.9: có `ChordVars` 64-bit little-endian.

Sau khi nạp, `Tracks` bảo đảm track đầu là `Master`, gắn track vào danh sách group tương ứng, đặt kiểu channels và áp dụng nhánh tương thích chord cho phiên bản cũ. Đây là bước chuẩn hóa trong bộ nhớ, không phải toàn bộ các giá trị đều được lấy nguyên trạng từ file.

## Hệ quả khi viết parser độc lập

Parser đầy đủ cần dispatch theo cả kiểu object và phiên bản. Quy trình tối thiểu là đọc Music, bỏ/giữ kho wave, giải mã từng Instrument, System, Track, KeyData, rồi mới đọc TrackGroup. Chỉ quét byte để tìm chuỗi chữ ký sẽ không xác định đáng tin cậy ranh giới các phần tử vì object lồng nhau có payload biến đổi.

Layout STS, Channels và effect được nối tiếp trong `STS-CHANNELS-EFFECT.md`, gồm kích thước cố định 40 byte của Effect và phần module động trong FX block.

## Bằng chứng

- `music-collection-read.json`, `music-collection-write.json`: count và vòng lặp object chung.
- `system-read-stream.json`: bảng tuning của System.
- `track-read-stream.json`: metadata Track, STS, Channels và danh sách KeyData.
- `keydata-read-stream.json`: các trường event và event 101.
- `tracks-read-stream.json`, `tracks-write-stream.json`: phần mở rộng TrackGroup.
- `track-group-read-stream.json`: layout và các nhánh phiên bản của TrackGroup.
- `stream-read-short-string.json`: chuỗi cố định 32 byte.

Kết quả đến từ phân tích tĩnh APK SHA-256 `9512e006f4c1780683aa19510dad701c415c98f3d87d0adbd7559088813e8855`; chưa chạy ứng dụng.
