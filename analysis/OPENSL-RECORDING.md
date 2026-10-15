# Đường thu âm OpenSL và microphone mix

Phân tích này nối `Music.InDeviceStart/Stop`, `ProcessIn`, `NOpenSL.RecordStart/Stop`, hai callback output và `MixPut/MixWrite`. Đây là phân tích tĩnh của đúng APK và ELF ARM64 đã ghi trong [TONG-QUAN.md](TONG-QUAN.md).

## Khởi động input từ Java

Khi cả `fIsOpenSL` và `fIsOpenSLRecord` bật, `Music.InDeviceStart(inputSource, channels)`:

1. dừng backend input cũ qua `fPlatform.d()`;
2. gọi `NOpenSL.RecordStart()`;
3. đặt sample rate của chuỗi FX microphone bằng `fOutSampleRate`;
4. đặt `fInSampleRate = fOutSampleRate` và `fInBufferSize = fOutBufferSize`;
5. cấp phát `short[fInBufferSize]`, điền 0 và bật `fInActive`.

Nhánh này không tạo `fInThread`. Nếu OpenSL record không khả dụng, Java dò sample rate qua 48.000, 44.100, 32.000 xuống 8.000 Hz, sau đó thử từng bước 100 Hz; backend fallback dùng thread input riêng.

Bằng chứng: [`in-device-start.json`](in-device-start.json), [`in-device-stop.json`](in-device-stop.json), mã trích tại [`in-device-start.java`](source/in-device-start.java).

## `RecordStart` và `RecordStop`

`Java_sofeh_audio_NOpenSL_RecordStart`, `0x2ad4..0x2bc0`:

- yêu cầu cả `SLRecordItf` và `SLAndroidSimpleBufferQueueItf` đã được tạo bởi `NOpenSL.Start`;
- xóa 4 hoặc 16 slot capture, mỗi slot cách nhau `0x8000` byte;
- gọi slot 0 của `SLRecordItf` với trạng thái `3`, tức `SL_RECORDSTATE_RECORDING`;
- đặt chỉ số capture bằng `outputSlot - 1`, có wrap về 3 hoặc 15;
- bật cờ recording tại `0x112fbc` và trả về `true`.

`RecordStop`, `0x2bc0..0x2be8`, xóa cờ recording rồi gọi cùng slot với trạng thái `1`, tức `SL_RECORDSTATE_STOPPED`.

## Recorder dùng nhịp callback output

Không có một callback recorder độc lập được đăng ký trong `NOpenSL.Start`. Cả callback `0x2574` và `0x28c8`, ngay sau khi enqueue output, đều kiểm tra cờ recording và interface recorder queue. Nếu hợp lệ, chúng:

1. chọn slot capture hiện tại trong vùng bắt đầu tại `0x92f78`;
2. gọi `SLAndroidSimpleBufferQueueItf.Enqueue` với `bufferFrames * 2` byte;
3. tăng chỉ số capture và wrap theo 4 hoặc 16 slot.

Do đó nhịp hoàn tất output cũng cấp buffer trống cho input. Điều này giữ kích thước và sample rate của hai phía đồng nhất trong chế độ OpenSL record.

## `MixPut` và `MixWrite`

Hai hàm nhận `short[] output`, `short[] input`, số phần tử output và gain trái/phải. Trình tự quan sát được:

1. đọc nội dung hiện có của hai mảng Java;
2. trộn mẫu mono trong `input` vào hai kênh xen kẽ của `output`, với gain phần trăm riêng;
3. đưa output vào đường vòng OpenSL tương ứng;
4. lấy slot capture đã hoàn tất từ vùng `0x92f78`;
5. ghi dữ liệu capture mới trở lại mảng Java `input` bằng JNI `SetShortArrayRegion` tại offset `0x690`.

Vì Java gọi `ProcessIn(fInBufferSize)` trước `MixPut/MixWrite`, FX microphone và `RecordWave` xử lý buffer đã nhận ở chu kỳ trước. Cuối lời gọi native, `fInBuffer` mới được thay bằng capture của chu kỳ kế tiếp. Buffer đầu tiên được Java khởi tạo bằng 0, nên đường xử lý có một chu kỳ đệm rõ ràng trong cấu trúc tĩnh.

`ProcessIn` áp dụng theo thứ tự:

- gain microphone `fFXWaveMic.e + 100` nếu khác 0;
- ghi wave vào track khi `fPlayMode == 4`;
- chuỗi effect trong `fFXWaveMic.l` cho từng mẫu;
- fade toàn cục nếu `fFadeVolume != MaxFade`.

Bằng chứng Java: [`music-ProcessIn.json`](music-ProcessIn.json), [`music-ProcessIn.java`](source/music-ProcessIn.java), [`music-Process.java`](source/music-Process.java), [`out-device-start.java`](source/out-device-start.java). Bằng chứng native: [`Java_sofeh_audio_NOpenSL_RecordStart.asm`](native/Java_sofeh_audio_NOpenSL_RecordStart.asm), [`Java_sofeh_audio_NOpenSL_RecordStop.asm`](native/Java_sofeh_audio_NOpenSL_RecordStop.asm), [`Java_sofeh_audio_NOpenSL_MixPut.asm`](native/Java_sofeh_audio_NOpenSL_MixPut.asm) và [`Java_sofeh_audio_NOpenSL_MixWrite.asm`](native/Java_sofeh_audio_NOpenSL_MixWrite.asm).

## Giới hạn

Một chu kỳ đệm được suy ra từ thứ tự gọi và thao tác JNI; thời lượng thực tế phụ thuộc `bufferFrames/sampleRate`. Chưa đo clock drift, overrun, underrun, mức gain thực tế hoặc độ trễ microphone trên thiết bị.
