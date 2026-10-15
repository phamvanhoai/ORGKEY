# Xác minh điều kiện phát và mặc định bằng DEX

Đọc trực tiếp `classes3.dex` bằng Androguard 4.1.4, không chạy ứng dụng. SHA-256 DEX: `94b5c8e042e4e3bda38c3682eafa88375e06644e6f887dd6e77a4e30b005d38e`.

Các file `dex/*.dexasm` chứa byte gốc, lệnh và địa chỉ byte của từng method; branch delta được decoder in theo code unit 2 byte. Script `dump_audio_dex.py` giải mã theo ranh giới lệnh, khác với script quét ứng viên field trước đây.

## 1. Giá trị khởi tạo Activity đã xác định

Trong `MainActivity.<init>()V`, lần theo các thanh ghi không bị ghi lại trước các phép gán:

| Field | Giá trị | Địa chỉ phép gán | Ý nghĩa đã nối với preference |
| --- | --- | --- | --- |
| `P1` | `44100` | `0x254` | `output_samplerate`: 44100 Hz |
| `Q1` | `0` | `0x258` | `output_buf`: Auto |
| `R1` | `1` | `0x25c` | `output_low_latency`: Real (OpenSL Method 1) |
| `J1` | `1` | `0x238` | Keyboard: Balanced |
| `K1` | `1` | `0x23c` | Microphone: Balanced |
| `L1` | `0` | `0x240` | Style: High Performance |

Nguồn hằng: `v1 = 1` tại `0xa`, `v3 = 0` tại `0x38`, `v15 = 44100` tại `0x214`. Các giá trị này là mặc định field của Activity và là fallback được `O0` truyền cho `r1` khi chưa có preference. Chưa kiểm tra mọi đường migration hoặc ghi đè trước khi nạp; không khẳng định mọi thiết bị cài mới chắc chắn phát bằng OpenSL, vì còn kiểm tra khả năng nền tảng và kết quả Start.

Constructor `Music` tự đặt `fLowLatencyMethod = 0`; sau đó `MainActivity.O0` gán từ `R1`/preference. Vì vậy mặc định của riêng lớp Music khác mặc định Activity, không được trộn hai giá trị.

Bằng chứng: `dex/MainActivity-init-0.dexasm`, `dex/Music-init-4.dexasm`, `dex/MainActivity-O0-1.dexasm`.

## 2. Sửa diễn giải nhánh SoundPool

Trong overload OpenKey 25 tham số, incoming registers bắt đầu ở `v31` (this), tham số mode cuối cùng ở `v56`. Đoạn lệnh:

```text
0x594  move/from16 v1, v56
0x5a2  const/4 v6, 2
0x5a4  if-le v1, v6, +0xd5 code units
```

Đích nhảy = `0x5a4 + 2 * 0xd5 = 0x74e`, bỏ qua khối SoundPool. Do đó **mode <= 2 bỏ qua**, còn mode > 2 đi tiếp. Mã Java suy dịch đã đặt điều kiện ngược ở đoạn này; kết luận mới dựa trên DEX.

Tiếp theo mã tính `note / 12` vào `v4`, bỏ qua nếu nhỏ hơn 3 hoặc lớn hơn 9. Khi mode bằng 4 và `lastChannel()` khác null, nó đặt `dVolumeStarter = 0.0f` tại `0x5ce`. Khối sau tính gain/pitch, lấy sound ID và gọi `SoundPool.play` tại `0x740`.

Như vậy, tại nhánh đã kiểm tra: mode 3/4 có thể phát sound bổ sung khi nốt thuộc các nhóm octave 3..9 và các điều kiện nhạc cụ/section trước đó cho phép. Mode 4 còn thay đổi mức khởi đầu của voice mixer. Chưa đo âm để kết luận nó triệt tiêu hoặc thay thế hoàn toàn voice chính; `Channels.Process` có logic tăng dần `dVolumeStarter`.

Nhánh này gọi `Channels.Open` trước khi đến SoundPool. Cơ chế không đơn giản là chọn độc quyền một trong hai bộ phát. Trạng thái nạp sound, số lượng slot và kết quả `play` vẫn cần kiểm chứng runtime.

## 3. Nơi chuẩn bị bộ âm

Danh mục tham chiếu REA đến `Music.lowLatencyInstruments` trả về caller `MainActivity.Q0`. Bằng chứng gốc: `latency-instruments-callers.json`; thân hàm được lưu riêng trong `prepare-low-latency-Q0.json`.

Đã đọc `Q0`: ngoài mode 4, nó truyền `(null, 0, f4)` cho hàm chuẩn bị. Với mode 4, nó tạo mảng bốn ID nhạc cụ, lấy ID từ các track có `fGroup == 0`, `fType == 1`, `fRecord == true`, tìm mode đầu tiên trong 1..3 có `ModeState > 0`, rồi truyền mảng, mode và thư mục `f4` xuống. Chưa xác minh giới hạn số track hợp lệ hoặc nơi gán `f4`; không suy ra mọi bộ nhạc cụ đều được đưa vào bốn slot này.

## Chạy lại

```powershell
& "$env:LOCALAPPDATA\rea\native-python\Scripts\python.exe" analysis\dump_audio_dex.py '.\ORG+24_+Your+Music_2026.10.01_APKPure.apk'
```

Phụ thuộc bổ sung: `androguard==4.1.4`. Các method được chọn được xuất đầy đủ; chưa xây toàn bộ control-flow graph hoặc chứng minh tương đương hành vi cho mọi đường chạy.
