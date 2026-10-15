# Khởi tạo OpenSL và hai callback phát âm thanh

Phân tích tĩnh `lib/arm64-v8a/libnopensl.so`, SHA-256 `dc900f9de170ec71471d8b5d2ccecc14d0bbb8c1d2346bd6e149467c888b38cf`. Bằng chứng chính là [`Java_sofeh_audio_NOpenSL_Start.asm`](native/Java_sofeh_audio_NOpenSL_Start.asm) và ba vùng nội bộ vừa được tách bằng [`dump_nopensl_helpers.py`](dump_nopensl_helpers.py).

## Tham số từ Java

`Music.OutDeviceStart()` gọi:

```java
NOpenSL.Start(
    fLowLatencyMethod == 1 ? this : null,
    fOutSampleRate,
    bufferFrames,
    bufferCount,
    fIsOpenSLRecord
)
```

Vì vậy, việc truyền `Music` hay `null` chủ động chọn một trong hai callback. `bufferFrames` bị giới hạn trong khoảng 64–4096 ở Java; `bufferCount` được giới hạn 3–8 trước lời gọi JNI, rồi native chặn tối đa 16.

## Chuỗi tạo đối tượng

Các offset vtable và IID relocation cho phép dựng lại chuỗi OpenSL ES sau:

1. `slCreateEngine`, `SLObjectItf.Realize`, rồi `GetInterface(SL_IID_ENGINE)`.
2. `SLEngineItf.CreateOutputMix`, sau đó `Realize` output mix.
3. `SLEngineItf.CreateAudioPlayer` với PCM stereo 16-bit và Android simple buffer queue.
4. `Realize` player; lấy `SL_IID_PLAY`, `SL_IID_BUFFERQUEUE` và `SL_IID_VOLUME`.
5. Đăng ký callback cho queue, enqueue trước `bufferCount` buffer im lặng, rồi gọi `SLPlayItf.SetPlayState(SL_PLAYSTATE_PLAYING)`.
6. Nếu tham số ghi âm bật, tạo thêm `AudioRecorder`, `Realize`, rồi lấy `SL_IID_RECORD` và `SL_IID_ANDROIDSIMPLEBUFFERQUEUE`.

Việc gán IID dựa trên relocation GOT: `0x6e98=VOLUME`, `0x6ea0=BUFFERQUEUE`, `0x6ea8=PLAY`, `0x6eb0=ENGINE`, `0x6eb8=ANDROIDSIMPLEBUFFERQUEUE`, `0x6ec0=RECORD`.

## Hai callback đầu ra

Địa chỉ callback được chọn tại `0x20ac`:

| Điều kiện | Callback | Hành vi |
|---|---:|---|
| Có `Music`, global reference và method ID | `0x2574` | Gọi `Music.ProcessOut()` qua JNI, chép phần dữ liệu còn thiếu nếu có, điền 0 cho phần cuối, rồi enqueue buffer kế tiếp. |
| Không truyền `Music` | `0x28c8` | Enqueue trực tiếp buffer vòng đã được luồng Java điền qua `Write`/`MixWrite`. |

Trong callback `0x2574`, lời gọi qua JNI table offset `0x1e8` nhận global object và method ID đã lưu. Offset này là họ `CallVoidMethod`; ngữ cảnh từ `OutDeviceStart()` xác định đích là `Music.ProcessOut(false)`. Callback quản lý vòng 4 hoặc 16 slot tùy cờ native và mỗi slot cách nhau `0x8000` byte.

Callback `0x28c8` không gọi ngược Java để dựng âm thanh. Nó chỉ lấy slot vòng hiện tại, enqueue `2 * bufferFrames` byte, cập nhật chỉ số slot, và nếu nhánh ghi âm đang bật thì enqueue cả buffer recorder tương ứng.

Điều này xác nhận ý nghĩa hai chế độ OpenSL trong Java:

- `fLowLatencyMethod == 1`: native callback gọi `Music.ProcessOut`; Java không khởi động `fOutThread` sau khi `Start` thành công.
- `fLowLatencyMethod == 2`: `Start` nhận `null`; `fOutThread` chạy `ProcessOut` rồi `NOpenSL.Write` hoặc `MixWrite`, còn callback native tiêu thụ các buffer vòng.

## Dọn tài nguyên tại `0x2448`

Vùng [`nopensl-teardown-2448.asm`](native/nopensl-teardown-2448.asm) không phải lời gọi sang `NMusic_ChannelCopy`. Đây là helper nội bộ liền ngay sau `NOpenSL_Start`, không có dynamic symbol. Nó gọi `SLObjectItf.Destroy` ở offset vtable `0x30` cho player, recorder, output mix và engine; sau đó xóa các global reference JNI, con trỏ interface và trạng thái.

Nhận định cũ trong call graph về đích `0x2448` vì vậy được thay bằng bằng chứng byte-range trong chính `libnopensl.so`.

## Artifact tái lập

- [`nopensl-teardown-2448.asm`](native/nopensl-teardown-2448.asm): 300 byte, 75 lệnh.
- [`nopensl-buffer-callback-2574.asm`](native/nopensl-buffer-callback-2574.asm): 852 byte, 213 lệnh.
- [`nopensl-buffer-callback-28c8.asm`](native/nopensl-buffer-callback-28c8.asm): 332 byte, 83 lệnh.

Đây là phân tích tĩnh ARM64. Trình tự và các nhánh lỗi đã được thấy trong mã máy, nhưng thời gian callback, underrun và độ trễ thực tế vẫn cần đo trên thiết bị.
