# Kết quả runtime trên bản 2023.1.0.4

Thiết bị `2510DRK44C` đã chạy package `com.sofeh.android.musicstudio3` trong 30 giây. APK cài trên thiết bị là phiên bản `2023.1.0.4`, code `202`, target SDK 31 và SHA-256 `0E918DD9F94C2F9D1DCF3BA1DDEE3C2102691091048238CF70EBDC1597E6A550`. Đây không phải APK 2026.10.01/code 274 đang được phân tích: Android từ chối `adb install -r` với `INSTALL_FAILED_UPDATE_INCOMPATIBLE` vì chữ ký khác. Vì vậy các kết quả dưới đây xác nhận kiến trúc âm thanh chung của bản 2023, không được dùng để khẳng định tuyệt đối hành vi bản 2026.

## Khởi động

- Launcher mở `SplashActivity`, sau đó chuyển tới `MainActivity`.
- Tiến trình PID 28991/UID 10514 vẫn sống sau khoảng quan sát và `MainActivity` là `topResumedActivity`.
- Không có `FATAL EXCEPTION`, fatal signal hoặc ANR trong log theo PID.
- Có lỗi khởi tạo Google Ads Lite do thiếu lớp `LiteSdkInfo`, nhưng lỗi được bắt và không làm ứng dụng dừng.

## Đường âm thanh quan sát được

- Runtime nạp thành công `libnmusic.so` và `libnopensl.so` từ ABI ARM64.
- OpenSL ES tạo `AudioTrack` stereo PCM tại 48.000 Hz với `AUDIO_OUTPUT_FLAG_FAST|RAW` (`0x104`).
- Hệ thống chấp nhận fast track, đổi frame count từ 0 thành 960 frame. Ở 48 kHz, 960 frame tương đương 20 ms dữ liệu trong một buffer.
- AudioFlinger ghi track đang active, buffer 960/960, không có underrun và báo latency 41 ms tại thời điểm dump.
- Loa máy là output device được chọn.
- Ngoài track OpenSL chính còn có các `AudioTrack` 44.100 Hz, frame count 22.050; chúng phù hợp các mẫu/đường phát phụ nhưng log này chưa gán chắc từng track cho SoundPool hay MediaCodec.

## Thu âm và fallback

OpenSL thử tạo `AudioRecord` mono 48 kHz nhưng `Realize` trả `SL_RESULT_CONTENT_UNSUPPORTED`. Quyền `RECORD_AUDIO` của ứng dụng đang chưa được cấp, nên lần chạy này không kiểm chứng được recorder thành công. Ngay sau thất bại có một `AudioTrack` thường 48 kHz được tạo và phát 42.720 frame trước khi dừng; điều này là bằng chứng runtime cho nhánh output fallback, nhưng chưa đủ để xác định toàn bộ điều kiện chọn nhánh.

## Quyền và mạng

Trong khoảng chạy, AppOps ghi nhận `WAKE_LOCK` và `CONTROL_AUDIO`/`CONTROL_AUDIO_PARTIAL`. Không có thay đổi cấp quyền nhạy cảm. Netstats có mục cho UID 10514 nhưng bản thu đầu tiên chỉ lấy snapshot sau khi chạy, nên đây là số tích lũy và không chứng minh lượng dữ liệu phát sinh trong 30 giây. Script đã được sửa để các lần sau giữ cả `netstats-before.txt` và `netstats-after.txt`.

Bằng chứng thô nằm tại `analysis/runtime/20261016-034902/` và APK đã kéo từ thiết bị nằm trong `analysis/runtime-installed/`; hai thư mục bị loại khỏi Git vì chứa dữ liệu thiết bị và binary lớn.
