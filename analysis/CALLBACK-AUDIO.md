# Callback và đường phát OpenSL ES

Đã đọc tiếp [fallback AudioTrack](FALLBACK-AUDIOTRACK.md), nối các lớp `defpackage.fx2/zd1` đến `AudioTrack.play/write`.

Đã nối tiếp phân tích ARM64 từ buffer do `Put` chuẩn bị đến lời gọi enqueue. Đây là bằng chứng tĩnh; chưa đo hoặc chạy ứng dụng trên Android.

## Hai chế độ từ Java

`Music.OutDeviceStart()` cho thấy:

| Điều kiện | Cách chạy |
| --- | --- |
| `fLowLatencyMethod == 1`, OpenSL khởi động thành công | Gọi `NOpenSL.Start(this, ...)`. Sau khi nền tảng báo sẵn sàng, hàm trả về mà không khởi chạy `fOutThread`. |
| `fLowLatencyMethod == 2`, OpenSL khởi động thành công | Gọi `NOpenSL.Start(null, ...)`, chạy thread Java; mỗi vòng gọi `ProcessOut(false)` rồi `Write` hoặc `MixWrite`. |
| Không dùng OpenSL hoặc Start thất bại | Tạo cấu hình nền tảng qua `fx2`; thread đi qua dispatcher với `zd1`. Chưa đọc thân hai lớp này để xác định đầy đủ backend fallback. |

Hai chế độ OpenSL phụ thuộc cả các chuỗi thông số nền tảng `fPlatform.b/c`; giá trị mode đơn lẻ không bảo đảm OpenSL được chọn. Có ghi trực tiếp ra file nếu `fDirectRecordWriter` khác null.

Bằng chứng: `out-device-start.json`, `source/out-device-start.java`.

## Native đăng ký callback nào?

Trong `NOpenSL.Start`:

- `0x1c18..0x1cb4`: khi đối tượng Music được truyền vào, lấy JavaVM, giữ global reference và tìm method tên `Process`, chữ ký `()V`. Tên và chữ ký được đọc từ `.rodata` tại `0xadc` và `0xad8`.
- `0x2068..0x20b0`: chọn địa chỉ callback `0x2574` nếu các reference/method/VM cần thiết có mặt; ngược lại chọn `0x28c8`.
- Lời gọi tại `0x20b0` dùng slot `0x18` của buffer-queue interface để đăng ký callback, context null.

Đối chiếu cấu trúc `SLBufferQueueItf_` trong [header Android OpenSL ES](https://android.googlesource.com/platform/frameworks/wilhelm/+/refs/heads/main/include/SLES/OpenSLES.h): trên ABI 64 bit, slot `0` là `Enqueue`, `8` là `Clear`, `0x10` là `GetState`, `0x18` là `RegisterCallback`. Bản header tham chiếu được lưu tại `native/OpenSLES-reference.h`; đây là header tham khảo hiện tại, không khẳng định đúng phiên bản NDK đã biên dịch APK.

## Callback 0x2574: gọi Java tạo thêm âm rồi enqueue

Các bước quan sát trong `native/libnopensl.so-text.asm`:

1. Kiểm tra trạng thái chạy, interface queue và kích thước buffer.
2. Nếu buffer `0xaf48` còn mẫu chưa dùng, chép phần còn lại vào slot đầu ra.
3. Lấy JNIEnv qua JavaVM; có nhánh attach thread nếu chưa có môi trường JNI.
4. Khi chưa đủ dữ liệu, tại `0x2728` gọi JNI `CallVoidMethod` (offset `0x1e8`) với đối tượng/method đã lưu. Kết hợp bước đăng ký cho thấy đích là `Music.Process()`.
5. Java `Process()` trộn âm và gọi `NOpenSL.Put/MixPut`; callback đọc số lượng mới, chép từ `0xaf48` vào phần còn thiếu của slot. Có vòng lặp để lấy thêm dữ liệu khi cần.
6. Nếu vẫn thiếu, `0x27a0..0x27c4` điền phần thiếu bằng 0.
7. `0x27dc..0x2804` gọi slot `0` của queue, tức `Enqueue`, truyền địa chỉ slot và số byte bằng hai lần số phần tử short.
8. Tăng chỉ số slot, quay vòng theo cờ cấu hình chọn 4 hoặc 16 slot. Đây là giới hạn vòng chỉ số trong callback, không đồng nhất với số buffer thực tế đã enqueue hoặc độ trễ nghe được.

Điểm mấu chốt: đường mode 1 là **OpenSL callback → Java Music.Process → native Put → callback chép buffer → OpenSL Enqueue**. Việc callback gọi lại Java đã được nối bằng các địa chỉ lưu method, không chỉ suy đoán từ tên hàm.

## Callback 0x28c8: queue dữ liệu đã được chuẩn bị

Callback này ngắn hơn: kiểm tra trạng thái, enqueue slot hiện tại, tăng chỉ số và quay vòng. Không thấy lời gọi JNI để tạo âm trong phạm vi `0x28c8..0x2a14`. Điều này phù hợp đường mode 2 dùng thread Java gọi `Write/MixWrite` để cung cấp dữ liệu. Chưa đối chiếu hết trạng thái giữa producer và consumer ở mọi trường hợp dừng/khởi động lại.

## Kết luận thực dụng và giới hạn

Đã xác định nơi buffer đi vào OpenSL queue cho cả hai callback. Enqueue thành công không tự chứng minh lúc nào mẫu đến DAC/loa. Cần kiểm chứng runtime để đo latency, underrun và mức sử dụng CPU; chưa có số đo cho các yếu tố đó. Các nhánh thu âm và callback của recorder chưa được phân tích đầy đủ.

Bước tiếp theo độc lập với thiết bị: đọc `fx2/zd1` để nối fallback AudioTrack, và kiểm tra cấu hình Java đặt `fLowLatencyMethod`. Bước kiểm chứng runtime cần điện thoại hoặc emulator thực sự kết nối.
