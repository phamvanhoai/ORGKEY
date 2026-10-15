# Nhánh thu âm fallback bằng AudioRecord

Nhánh này hoạt động khi `Music.InDeviceStart()` không dùng được OpenSL record. Bằng chứng lấy từ REA/JADX trên APK, không chạy microphone thật.

## Chọn sample rate và kích thước buffer

Java thử sample rate đầu ra hiện tại trước. Nếu nền tảng từ chối, nó thử lần lượt `48000, 44100, 32000, 24000, 22050, 16000, 12000, 11025, 8000`, rồi quét 8.000–48.000 Hz với bước 100 Hz.

Khi tìm được cấu hình:

- chuỗi FX microphone nhận sample rate đó;
- `fInBufferSize = fInSampleRate / 100`, tương ứng khoảng 10 ms mẫu;
- `fInBuffer` là `short[]` có cùng số phần tử;
- dispatcher nhận `q13(Music, sampleRate, inputSource, channels, minBufferBytes)`;
- một thread lặp gửi `qc1((byte)17)` trong khi `fInActive` còn bật.

## Khởi tạo `AudioRecord` và monitor

`defpackage.q13.g(Object)` lưu `Music` tại `b60.g` rồi tạo:

```text
AudioRecord(inputSource, sampleRate,
            channels == 1 ? CHANNEL_IN_MONO : CHANNEL_IN_STEREO,
            ENCODING_PCM_16BIT, minBufferBytes)
```

Nó đồng thời tạo `AudioTrack` monitor tại `b60.e`. Output mask là mono khi input có một kênh, stereo khi có nhiều kênh. Mã thử constructor dùng `AudioAttributes/AudioFormat`, sau đó `AudioTrack.Builder`, cuối cùng constructor cũ. Khi có đối tượng, nó gọi `AudioRecord.startRecording()` và `AudioTrack.play()`.

Mã rút gọn nằm tại [`fallback-input-create.java`](source/fallback-input-create.java); bằng chứng đầy đủ tại [`q13-input-dispatch.json`](q13-input-dispatch.json).

## Vòng đọc và phát monitor

`defpackage.qc1.g(Object)`, nhánh discriminator 17, thực hiện:

```text
count = AudioRecord.read(fInBuffer, 0, fInBufferSize)
ProcessIn(count)
AudioTrack.write(fInBuffer, 0, count)
```

Vì vậy fallback microphone là một đường monitor trực tiếp sau xử lý. `ProcessIn` áp dụng gain, ghi wave khi play mode 4, chuỗi effect microphone và fade trước khi mẫu được phát lại. Giá trị trả về âm của `AudioRecord.read` không được kiểm tra riêng; toàn bộ khối nằm trong `try/catch`, nên phân tích tĩnh chưa xác định hành vi chi tiết khi read trả mã lỗi.

Mã rút gọn: [`fallback-input-read.java`](source/fallback-input-read.java). Bằng chứng: [`qc1-g.json`](qc1-g.json), [`music-ProcessIn.json`](music-ProcessIn.json).

`ProcessInSafe(int)` chỉ bọc `ProcessIn` bằng khóa `fTracks.fProcessList`; callback `qc1` quan sát được gọi trực tiếp `ProcessIn`, không gọi wrapper này. Bằng chứng nằm tại [`music-ProcessInSafe.json`](music-ProcessInSafe.json).

## Dừng và giải phóng

`Music.InDeviceStop()` xóa `fInActive`. Với fallback, thread kết thúc rồi gọi `fPlatform.d()`. `x13.d()` duyệt các target và:

- `stop()` + `release()` `AudioRecord`, sau đó đặt `b60.f = null`;
- `stop()` + `release()` monitor `AudioTrack`, sau đó đặt `b60.e = null`.

Bằng chứng: [`in-device-stop.json`](in-device-stop.json), [`x13-input-stop.json`](x13-input-stop.json), mã rút gọn [`fallback-input-stop.java`](source/fallback-input-stop.java).

## Quan hệ với các backend khác

| Backend | Nơi tạo mẫu | Đưa input vào Java | Output microphone |
|---|---|---|---|
| OpenSL mode 1 | callback native gọi `Music.Process` | `MixPut` ghi capture mới vào `fInBuffer` | trộn vào output stereo OpenSL |
| OpenSL mode 2 | thread Java gọi `ProcessOut` | `MixWrite` ghi capture mới vào `fInBuffer` | trộn vào output stereo OpenSL |
| AudioRecord fallback | thread input Java | `AudioRecord.read` ghi thẳng `fInBuffer` | `AudioTrack.write` phát buffer sau `ProcessIn` |

Nhánh fallback đã được nối đầy đủ ở mức mã tĩnh. Độ trễ monitor, echo, khả năng thiết bị chấp nhận constructor và mã lỗi `read/write` vẫn cần đo runtime.
