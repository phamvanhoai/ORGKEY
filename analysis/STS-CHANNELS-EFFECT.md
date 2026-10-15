# STS, Channels và Effect trong Template

Tài liệu này hoàn thiện thêm các payload nằm trong Track, Instrument mode và TrackGroup. Kết quả dựa trên reader, writer và cấu trúc mảng xác nhận từ DEX.

## STS

Track lưu một mảng STS. Trước payload, Track ghi cờ `Enable` cho từng slot; chỉ slot bật mới có payload `STS.ReadFromStream`.

Mỗi STS có các trường theo thứ tự:

```text
bool        Record
byte        nameLength (từ phiên bản 1.4.5; bản cũ chỉ bỏ qua byte này)
byte[N]     Name
byte[2]     MIDIChOut
int32-le    Instrument
int32-le    System
int16-le    Pedal
int16-le    Mode
int16-le    Data1
byte        Data2
byte        ChordType
int16-le[12] ChordSingle, chỉ có khi version >= 1.2.5 và ChordType != 0
FX block
Effect[8]
```

DEX constructor xác nhận `MIDIChOut` có 2 phần tử, `ChordSingle` có 12 phần tử và `Effects` có 8 phần tử. Reader tên STS dùng một byte độ dài rồi đọc đúng số byte; mã suy dịch không cho thấy xử lý rõ ràng nếu byte này âm, nên parser độc lập phải kiểm tra biên trước khi cấp phát.

Trong luồng phát Track đã đọc trước đó, khi chọn STS đang bật, ứng dụng có thể sao chép FX, từng Effect, chord type và 12 giá trị `ChordSingle` sang trạng thái phát. Việc trường nào được áp dụng còn phụ thuộc mảng cài đặt `fSTSChanges`.

## Effect cố định

Mỗi `Effect` dài đúng 40 byte:

```text
offset  size  nội dung
0       1     Enable
1       3     boolean dự trữ
4       16    byte dự trữ
20      4     StartDelay, int32-le
24      1     ByPedal
25      1     StopByRelease
26      1     Mode
27      1     boolean dự trữ
28      4     Speed, int32-le
32      4     Depth, int32-le
36      4     Gain, int32-le
```

Reader và writer đối xứng. Constructor nhận chỉ số slot làm `effectType`, nhưng chỉ số loại này không được ghi trong chính record 40 byte. Ý nghĩa record phụ thuộc vị trí trong mảng 8 slot.

## FX block động (`z01`)

Khối FX tổng hợp bắt đầu bằng:

```text
6 * int32-le  b..g
int32-le      h; giá trị 0 được chuẩn hóa thành 100
bool          i
byte[18]      reserved
bool          j
int32-le      moduleCount
module[moduleCount]
```

Phần cố định trước module dài 52 byte. Mỗi module bắt đầu bằng `int32-le type`; reader nhận các mã:

```text
1, 2, 4, 6, 7, 8, 9, 10, 15, 16, 17, 20, 21, 23, 29, 31
```

Sau mã loại, lớp cơ sở đọc một boolean, `byte[2]` và một boolean cuối. Constructor `n01` cấp phát mảng này với đúng 2 byte. Phần cơ sở dài 8 byte tính cả type. Các lớp DSP override reader và đọc thêm tham số, nên kích thước record phụ thuộc type.

| Type | Tên trong constructor | Lớp DEX | Tổng byte |
| ---: | --- | --- | ---: |
| 1 | Band pass | `Lo01;` | 16 |
| 2 | Band Move | `Lsofeh/audio/c;` | 25 |
| 4 | Echo delay (Stereo) | `Lt01;` | 16 |
| 6 | Echo delay | `Ls01;` | 16 |
| 7 | Equalizer | `Lsofeh/audio/d;` | `12 + 4 × bandCount` |
| 8 | Flanger (Phaser) | `Lsofeh/audio/e;` | 36 |
| 9 | High pass | `Lu01;` | 12 |
| 10 | Low pass (SP) | `Lu01;` | 12 |
| 15 | Pitch Shift | `Lsofeh/audio/f;` | 12 |
| 16 | Robot | `Lx01;` | 12 |
| 17 | 3D Sound (Stereo) | `Ly01;` | 12 |
| 20 | Bass Booster | `Lp01;` | 16 |
| 21 | Chorus | `Lq01;` | 32 |
| 23 | Distortion | `Lr01;` | 17 |
| 29 | Reverb | `Lw01;` | 16 |
| 31 | Band Booster | `Lsofeh/audio/b;` | 12 |

Bảng tương đương dạng máy đọc được nằm trong `fx-module-table.json`. Equalizer là loại duy nhất trong bảng có chiều dài nội tại biến đổi: sau phần base là `bandCount` 32-bit và đúng số lượng gain 32-bit đó. Hai byte trạng thái base được giữ tên trung tính vì ý nghĩa của chúng không lộ rõ trong reader.

Nếu gặp type không thuộc bảng trên, mã hiện tại để object module bằng `null` và không gọi reader payload. Đây là điểm dễ làm mất đồng bộ nếu file chứa type mới; ứng dụng đã có kiểm tra phiên bản tổng thể ở `MusicObject`, nhưng phân tích tĩnh chưa chứng minh mọi file lạ đều bị từ chối trước nhánh này.

## Channels

`Channels.ReadFromStream` có ba layout lịch sử:

- version trước 1.2.0: đọc nguyên 1.792 byte thành 448 số `int32-le`, rồi lấy các vị trí 6–10 để dựng một phần FX;
- version 1.2.0 đến trước 1.2.1: đọc hai FX block, giữ block đầu và bỏ object của block thứ hai;
- từ 1.2.1: đọc một FX block.

Sau đó reader luôn đọc 8 record `Effect` (8 × 40 = 320 byte). Với version trước 1.2.4, dữ liệu Effect vẫn được tiêu thụ khỏi stream nhưng từng slot sau đó bị đặt lại về mặc định. Với version trước 1.4.3, mode của effect slot 1 được ghép thêm `fKeyConnectionMethod` vào nibble cao.

DEX constructor xác nhận `Channels.fEffects` có đúng 8 slot. Các mảng kết nối runtime `fConnectID`, key, touch, event và length đều có 10 phần tử, nhưng chúng không được đọc trong `Channels.ReadFromStream`; chúng là trạng thái phục vụ phát âm.

## Nơi các khối xuất hiện

| Vị trí | FX block | Effect[8] |
| --- | --- | --- |
| `Channels` của Track | có | có |
| mỗi STS bật | có | có |
| mỗi mode của Instrument | có | có |
| TrackGroup từ version 1.4.2 | có | không thấy mảng Effect riêng trong reader |
| Music cấp cao | ba FX block: music, wave, rhythm | không nằm trực tiếp tại đây |

## Bằng chứng

- `sts-read-stream.json`, `sts-constructor.json`, `sts-class.json`.
- `channels-read-stream.json`, `channels-constructor.json`.
- `effect-read-stream.json`, `effect-write-stream.json`, `effect-constructor.json`.
- `fx-block-read.json`: header FX và dispatch module.
- `fx-module-read.json`, `fx-module-base-class.json`: payload cơ sở của module.
- `fx-module-base-constructor.json`, `fx-module-table.json`: kích thước state và bảng type/lớp/tên.
- `track-read-stream.json`, `track-play-dispatch.json`: vị trí STS trong Track và cách áp dụng lúc phát.

Phân tích tĩnh trên APK SHA-256 `9512e006f4c1780683aa19510dad701c415c98f3d87d0adbd7559088813e8855`; chưa đo đầu ra effect khi chạy.
