# Kết quả runtime APK 2026.10.01

Thiết bị `2510DRK44C` chạy `com.sofeh.android.musicstudio3` phiên bản `2026.10.01`, code `274`, target SDK 36. APK kéo ngược từ `/data/app` có SHA-256 `9512E006F4C1780683AA19510DAD701C415C98F3D87D0ADBD7559088813E8855`, khớp tuyệt đối file APK đã dùng cho toàn bộ phân tích tĩnh. Đây là kiểm chứng trên đúng artifact.

## Khởi động và ổn định

- Launcher mở ứng dụng và `MainActivity` trở thành `topResumedActivity`.
- Tiến trình PID 31956/UID 10580 còn sống sau khoảng quan sát 30 giây.
- Không có `FATAL EXCEPTION`, fatal signal hoặc ANR trong log theo PID.
- Có cảnh báo từ MIUI, Google Play Services, Glide và SDK quảng cáo nhưng không làm ứng dụng dừng.

## Đường âm thanh thực tế

- Runtime nạp thành công `libnmusic.so` và `libnopensl.so` ARM64 từ chính APK.
- OpenSL ES yêu cầu PCM stereo 48.000 Hz và tạo `AudioTrack` với `AUDIO_OUTPUT_FLAG_FAST|RAW` (`0x104`).
- Hệ thống chấp nhận FAST flag và chọn frame count 960. Tại 48 kHz, một buffer tương đương 20 ms mẫu.
- AudioFlinger cho thấy track của PID/UID ứng dụng đang active, 960/960 frame sẵn sàng, **0 underrun** và latency 41 ms.
- AudioFlinger có bộ đếm FastMixer toàn hệ thống lớn hơn 0; đây là số tích lũy của mixer, không phải trường `Underruns` của track ORG. Không dùng số đó để quy lỗi cho ứng dụng.

## Thu âm sau khi cấp quyền micro

Một lượt đo riêng đã cấp `android.permission.RECORD_AUDIO`; AppOps chuyển sang `foreground/allow`. Runtime sau đó tạo và khởi động thành công `AudioRecord` với:

- input source `1` (microphone), PCM format `0x1`;
- mono channel mask `0x10`;
- sample rate 48.000 Hz;
- buffer 2.880 frame;
- session 73105, port 8056.

Recorder chạy khoảng 1,94 giây rồi dừng sạch. AudioFlinger ghi server frame count `0x16800`, tức 92.160 frame, phù hợp khoảng 1,92 giây ở 48 kHz; input latency được báo khoảng 30,17 ms. Cùng thời điểm có một output mono 48 kHz của chính UID ứng dụng được tạo rồi dừng, phù hợp đường monitor/fallback `AudioTrack` đã nối từ mã tĩnh. Không có crash trong lượt thu.

## Hoạt động mạng trong khoảng đo

Hai snapshot netstats trước/sau cho UID 10580, foreground, tag 0 tăng:

- nhận: từ 1.221.814 lên 2.272.209 byte, chênh **1.050.395 byte**;
- gửi: từ 199.798 lên 497.482 byte, chênh **297.684 byte**;
- packet nhận: tăng 1.127; packet gửi: tăng 847.

Các số này xác nhận UID ứng dụng có truyền dữ liệu trong khoảng quan sát, nhưng netstats không cung cấp hostname hoặc nội dung. SDK quảng cáo, analytics và tài nguyên mạng đều dùng chung UID nên chưa thể gán lượng dữ liệu cho một SDK cụ thể.

## Giới hạn tự động hóa giao diện

UI Automator chỉ nhìn thấy hai `ImageView` lớn (`imgControl` và `imgKeyboard`); các nút, phím và menu được vẽ trong canvas nên không có resource ID hoặc accessibility node riêng. Một phép thử tọa độ đã mở trình chọn Style do ảnh xem trong IDE bị scale; thay đổi thử nghiệm được bỏ bằng lựa chọn `NO` ở hộp lưu và ứng dụng được đưa lại về trang `MAIN`. Vì vậy chưa tự động đổi ba mode độ trễ bằng tọa độ mù. Việc kiểm tra từng mode nên thực hiện với thao tác người dùng có quan sát, trong khi script chỉ thu log ở nền.

## Xuất MP3 và MIDI

Người dùng đã thực hiện và xác nhận thao tác xuất MP3 thành công trên đúng bản 2026 trong phiên kiểm thử. Logcat không ghi đường dẫn đầu ra và truy vấn MediaStore không thấy mục mới trong một giờ gần nhất, nên lượt này chưa kiểm tra độc lập header, bitrate, sample rate hoặc thời lượng file đã xuất. Kết quả giao diện phù hợp pipeline tĩnh đã xác định: writer mode 2 gọi `Lame.init → encode → flush → close`.

Không có thiết bị MIDI phần cứng để thử. Mục MIDI được đánh dấu **không áp dụng cho môi trường kiểm thử**, không tính là khoảng trống bắt buộc của phân tích APK.

Bằng chứng output/mạng nằm ở `analysis/runtime/20261016-035214/`; lượt có quyền micro nằm ở `analysis/runtime/20261016-035546/`. Bản APK kéo từ thiết bị nằm ở `analysis/runtime-installed/ORG-installed-2026.10.01.apk`. Các thư mục này được loại khỏi Git và manifest vì chứa dữ liệu thiết bị và binary lớn.
