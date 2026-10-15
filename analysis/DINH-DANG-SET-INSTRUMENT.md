# Định dạng Template/SET và Instrument

Phần này mô tả kết quả phân tích tĩnh các file mẫu đi kèm APK. Đây là sơ đồ đọc dữ liệu của phiên bản ứng dụng đang xét, chưa phải đặc tả hoàn chỉnh để ghi lại mọi file tương thích.

## Kết quả chắc chắn từ file thật

| File trong APK | Kích thước | Chữ ký | Tên | Phiên bản | Vị trí payload |
| --- | ---: | --- | --- | --- | ---: |
| `assets/Default.Template` | 67.848.221 | `Music Studio Music` | rỗng | `1.4.6` | `0x2f` |
| `assets/empty.Template` | 313.042 | `Music Studio Music` | rỗng | `1.2.4` | `0x2f` |
| `assets/Constant.Instrument` | 469.845 | `Music Studio Instrument` | `Constant` | `1.4.2` | `0x37` |
| `assets/Sound.Instrument` | 2.965.539 | `Music Studio Instrument` | `Sound` | `1.4.2` | `0x34` |

Script `inspect_music_assets.py` đọc trực tiếp bốn entry từ APK. Kết quả cố định nằm trong `music-asset-common-metadata.json`.

## Header chung

`MusicObject.LoadFromFile` đọc trước 32 byte và chỉ kiểm tra phần đầu, không yêu cầu toàn bộ vùng này phải bằng nhau:

```text
offset  kích thước  nội dung
0x00    32          vùng chữ ký, bắt đầu bằng "Music Studio " + Signature()
0x20    4           độ dài N, int32 little-endian
0x24    N           chuỗi: name + CR + version + CR + description
0x24+N  4           group, int32 little-endian
...                 payload riêng của Music hoặc Instrument
```

`al0.b()` là `Integer.reverseBytes(readInt())`, vì vậy các số 32 bit do phương thức này đọc có thứ tự byte little-endian. `al0.e()` đọc chiều dài bằng chính phương thức đó rồi dựng chuỗi từ N byte. Mã không chỉ định charset; các chuỗi metadata đã kiểm tra trong bốn asset đều là ASCII.

Ứng dụng tách chuỗi metadata bằng ký tự CR (`0x0d`) thành tên, phiên bản và mô tả. Việc chọn nhánh tương thích dùng `String.compareToIgnoreCase`, tức so sánh chuỗi theo thứ tự từ điển chứ không phân tích semantic version.

## Payload Music/Template

Sau header chung, `Music.ReadFromStream` đọc theo thứ tự:

1. Album, loại và copyright dưới dạng chuỗi có tiền tố độ dài.
2. Một khối thông tin mật khẩu/người dùng. Từ phiên bản 1.3.2, dữ liệu có khóa ngắn và phép XOR; đây là biến đổi định dạng, chưa đủ bằng chứng để coi là mã hóa an toàn.
3. Cờ/giá trị chung gồm reserved, stereo mode, tempo, record counter, tempo lock và vùng dự trữ.
4. Từ phiên bản 1.2.7, một kho mẫu âm dùng chung: số slot, số entry có dữ liệu, rồi mỗi entry có chỉ số, số byte và PCM.
5. Ba khối hiệu ứng cho music, wave và rhythm (các bản trước 1.2.1 dùng nhánh tương thích khác).
6. Lần lượt các collection `Instruments`, `Systems`, `Tracks`.
7. Từ phiên bản 1.4.7 có thêm chuỗi tên các họ nhạc cụ.

Điều này giải thích chênh lệch lớn giữa hai Template: `Default.Template` chứa kho wave và cấu trúc dự án đầy đủ, còn `empty.Template` vẫn có phần khung dự án nên không phải file rỗng theo nghĩa byte.

## Payload Instrument

`Instrument.ReadFromStream` đọc 52 byte selection map, một vùng tham số thay đổi theo phiên bản, volume, pan, type, family, priority và số mode. Mỗi mode có thể chứa:

- tên, sound được chọn và aftertouch;
- bảng mix, volume/pan của mix;
- bộ hiệu ứng;
- script từ phiên bản 1.4.0;
- danh sách thiết lập script từ phiên bản 1.4.4.

Sau mode là danh sách sound. Mỗi sound có tên, ADSR, pitch mode, auto-release và các section ánh xạ theo nốt. Các bản trước 1.4.1 đặt một phần dữ liệu của sound đầu tiên ở vị trí khác, còn các bản trước 1.4.0 dùng bảng ADS chung 16 phần tử. Vì vậy không thể giải mã mọi `.Instrument` bằng một layout byte cố định.

## Section và PCM

Mỗi `InstSec` chứa tên, sample rate cũ, bits per sample, số kênh, sample rate hiện tại, chỉ số wave dùng chung, độ dài mẫu, volume, pan, tune, start, loop, loop start/end và bốn số dự trữ.

Khi đọc, ứng dụng chuẩn hóa mẫu thành 16-bit và chỉ chấp nhận mono hoặc stereo. Nếu `WaveIndex` hợp lệ, section tham chiếu PCM trong kho wave của Template. Nếu không, PCM little-endian nằm trực tiếp sau metadata section. Ở chế độ bỏ qua dữ liệu (`z=true`), reader nhảy qua đúng `ByteLength` rồi xóa wave khỏi đối tượng đã nạp.

`LoopEnd` bị chặn ở `Length - 1`, còn `LoopStart` bị chặn không vượt quá `LoopEnd`. Với stereo, dữ liệu được giữ dạng sample xen kẽ theo cách `WaveData` sử dụng stride/offset bằng 1.

## Điểm còn chưa giải mã

- Khung collection cùng payload `System`, `Track`, `TrackGroup` và `KeyData` nằm trong `COLLECTION-TRACK-EVENT.md`; STS, Channels, effect và bảng đầy đủ 16 loại module FX được nối tiếp trong `STS-CHANNELS-EFFECT.md`.
- Cả `Default.Template` 1.4.6 và `empty.Template` 1.2.4 đã được parser đi xuyên đến đúng EOF; inventory nằm trong `DEFAULT-TEMPLATE-INVENTORY.md` và `EMPTY-TEMPLATE-INVENTORY.md`.
- Hai Instrument độc lập 1.4.2 cũng đã được parse đến EOF; xem `INSTRUMENT-ASSETS.md`.
- Chưa xác minh writer có tái tạo byte giống hệt reader cho mọi phiên bản cũ.
- Chưa chạy ứng dụng để kiểm tra file được tạo thực tế hoặc các file SET do người dùng nhập.

## Bằng chứng

- `music-read-stream.json`: thứ tự payload của Music/Template.
- `instrument-read-stream.json`: nhánh phiên bản và cấu trúc Instrument.
- `section-read-stream.json`: metadata section, tham chiếu wave và PCM.
- `object-load-file.json`, `object-read-stream.json`: xác thực chữ ký và header chung.
- `stream-read-number.json`, `stream-read-string.json`: cách đọc số và chuỗi.
- `asset-headers.json`: 64 byte đầu và kích thước các asset gốc.

Các kết luận trên đến từ APK SHA-256 `9512e006f4c1780683aa19510dad701c415c98f3d87d0adbd7559088813e8855`, phân tích bằng REA/JADX và đọc trực tiếp ZIP; ứng dụng không được thực thi.
