# Kế hoạch thay AudioTrack bằng native engine

## Tình trạng

APK gốc có `libnopensl.so` trên bốn ABI và các JNI `NOpenSL_*` được thống kê trong `analysis/native/`. Thư mục ORGX hiện chỉ đóng gói `libnlame.so`; không có binary `libnopensl.so` để tích hợp trực tiếp. Các file `.asm` là disassembly, không phải mã nguồn có thể link.

## JNI cần tái tạo

- `NOpenSL.Start`, `Stop`, `Stoped`: vòng đời output và callback buffer.
- `NOpenSL.Put`, `Write`, `MixPut`, `MixWrite`: đưa PCM mono/stereo vào mixer.
- `NOpenSL.RecordStart`, `RecordStop`: đường thu và đồng bộ file.
- `NEqualizer`, `NFlanger`, `NGVerb`, `NPitchShift`: FX native tương ứng.

## Đường chuyển đổi an toàn

1. Giữ `AudioTrack` làm backend mặc định.
2. Tạo interface `AudioBackend` dùng block PCM16 stereo và trạng thái start/stop.
3. Viết backend C++/Oboe riêng, không chép binary/disassembly của APK gốc.
4. So sánh latency, underrun, RMS, pan, FX và teardown với backend hiện tại.
5. Chỉ bật native backend khi kiểm thử lifecycle trên cả ABI đạt.

Không nên đưa các file disassembly vào APK như thư viện native: chúng thiếu relocation, symbol build context, header và mã nguồn phụ thuộc.

## Tiến độ

Đã thêm orgx/.../AudioBackend.kt và AudioTrackBackend.kt. AudioOutputLoop vẫn quản lý lifecycle và adapter hiện chưa đổi backend mặc định; 46 unit test đạt. Bước tiếp theo là viết adapter C++/Oboe theo đúng contract PCM16 interleaved stereo rồi so sánh latency và teardown.


AudioOutputLoop đã có đường nhận AudioBackend; constructor callback cũ vẫn tương thích. Điều này cho phép thử backend native độc lập trước khi thay backend production.


Đã thêm AudioBackendLoopIntegrationTest: backend giả lập kiểm tra start/close và partial write; tổng số unit test của ORGX là 48.


AudioEngine hiện đã dùng AudioTrackBackend qua AudioOutputLoop(backend=...); backend native chỉ cần triển khai cùng contract. Ứng dụng đã cài và khởi chạy lại trên thiết bị sau thay đổi.
