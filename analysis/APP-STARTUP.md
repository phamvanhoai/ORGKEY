# Luồng khởi động ứng dụng

Phân tích tĩnh từ manifest và DEX của APK. Luồng launcher chính đã được nối từ `Application` qua `SplashActivity` đến `MainActivity`; `SafeActivity` là launcher Safe Mode tùy chọn, bị tắt mặc định và được điều khiển bởi preference.

## Thành phần và điểm vào

Manifest đặt `sofeh.android.MainApplication` làm application. `SplashActivity` có `MAIN` + `LAUNCHER` và được bật mặc định. Vì vậy khởi động bình thường từ biểu tượng chính đi theo:

```text
MainApplication.onCreate
        ↓
SplashActivity.onCreate
        ↓ thread chuẩn bị
SplashActivity.a
        ↓ main Handler
MainActivity
```

`MainApplication.onCreate()` chỉ cấu hình hậu tố thư mục WebView khi tiến trình hiện tại khác package chính và Android API >= 28. Không thấy nó khởi tạo engine nhạc trong phương thức này.

## `SplashActivity.onCreate`

Activity nạp `activity_splash`, tìm progress bar, rồi tạo hai tác vụ:

- main-thread handler hiện progress bar sau 1.000 ms;
- background thread chạy nhánh 14 của `fb2`, gọi `SplashActivity.a()` để chuẩn bị thư mục và intent import;
- khi công việc kết thúc, nhánh 1 của `fu3` gỡ callback progress, ẩn progress bar, mở `MainActivity`, xử lý extras rồi `finish()` splash.

Mã rút gọn nằm tại [`splash-oncreate.java`](source/splash-oncreate.java). Bằng chứng đầy đủ: [`splash-oncreate.json`](splash-oncreate.json), [`fb2-run.json`](fb2-run.json), [`fu3-run.json`](fu3-run.json).

`onDestroy()` đặt cờ hủy và interrupt background thread. `onWindowFocusChanged(true)` áp dụng chế độ trang trí cửa sổ qua `ys1.k`.

## Chuẩn bị thư mục và file được chia sẻ

`SplashActivity.a()` tạo 13 đường dẫn dưới storage root của ứng dụng:

```text
<root>
<root>/.temp
<root>/ORG
<root>/ORG/Instrument
<root>/ORG/System
<root>/ORG/Style
<root>/ORG/Pad
<root>/ORG/Skin
<root>/ORG/Model
<root>/ORG/Chord
<root>/ORG/Import
<root>/ORG/Script
<root>/ORG/KeyboardSet
```

Với intent `VIEW` hoặc `SEND`:

- URI `file:` đã nằm dưới app root được giữ trực tiếp trong `MainApplication.a`;
- URI nội dung được đặt tên từ `_display_name`, kiểm tra `_size` và `last_modified`, làm sạch ký tự không hợp lệ rồi chép vào `ORG/Import`;
- đường dẫn file import cuối cùng được lưu trong `MainApplication.a` để màn hình chính sử dụng.

Decompiler tạo một số nhánh lặp và điều kiện khó đọc trong hàm lớn này. Các kết luận trên chỉ dùng các khối có lời gọi API và dữ liệu đích rõ ràng; không coi Java suy dịch là bản tương đương nguồn gốc ở mọi đường exception.

## Mở `MainActivity`

Nhánh hoàn tất splash gọi `startActivity(Intent(SplashActivity, MainActivity.class))`. Sau đó nó duyệt extras:

- `browse`: chuyển chuỗi cho `ys1.W`;
- `message`: chuyển chuỗi cho `ys1.i`;
- `newdata`: đặt `MainApplication.b = "newdata"`;
- `newversion`: có nhánh nhận dạng khóa nhưng thân suy dịch không cho thấy thao tác bổ sung.

Splash cuối cùng gọi `finish()`, nên không nằm lại trong back stack của luồng này.

## Launcher `SafeActivity`

`SafeActivity` cũng có `MAIN` + `LAUNCHER`, nhãn `ORG - Safe Mode`, icon `ic_launcher_safe`, task affinity riêng và `android:enabled="false"` trong manifest. Nó không xuất hiện trong launcher ở trạng thái mặc định.

Subclass `com.sofeh.android.musicstudio3.MainActivity.v()` trả về `SafeActivity.class`. Trong `sofeh.android.MainActivity.O0()`, ứng dụng tạo `ComponentName(this, v())` rồi đọc preference boolean `safe_mode`:

```text
safe_mode == true  → COMPONENT_ENABLED_STATE_ENABLED
safe_mode == false → COMPONENT_ENABLED_STATE_DISABLED
```

Lời gọi dùng flag `DONT_KILL_APP`, nên thay đổi trạng thái component không yêu cầu giết tiến trình tại chính điểm gọi. Đây là cơ chế làm hiện hoặc ẩn launcher Safe Mode.

Khi được mở, nó:

1. đặt `MainApplication.c = Boolean.TRUE`;
2. nạp `activity_safe`;
3. chờ 750 ms;
4. nhánh mặc định của `r92.run()` mở `MainActivity` và gọi `finish()`.

Bằng chứng: [`safe-oncreate.json`](safe-oncreate.json), [`r92-run.json`](r92-run.json), [`safe-activity-references.json`](safe-activity-references.json), [`main-safe-target-v.json`](main-safe-target-v.json), [`base-main-v-callers.json`](base-main-v-callers.json), [`load-settings-O0.java`](source/load-settings-O0.java), mã rút gọn [`safe-oncreate.java`](source/safe-oncreate.java).

Cờ `MainApplication.c` được đặt khi người dùng khởi động qua icon Safe Mode. `MainActivity.onResume()` chép cờ này vào `l4`, rồi lập tức đặt `MainApplication.c = false`, nên tín hiệu chỉ được tiêu thụ một lần cho lượt resume đó.

Runnable khởi tạo trong `s2.run()` đọc `l4`. Nếu cờ bật, nó không mở menu thường mà hiện dialog bắt buộc với ba lựa chọn:

- mở bản gốc;
- mở SET;
- mở trạng thái trống.

Sau khi dựng dialog, nó đặt `l4 = false`. Nếu cờ không bật, nhánh tương ứng mở menu bình thường qua `N1("menu", true)`. Như vậy Safe Mode là đường khởi động yêu cầu người dùng chọn nguồn dữ liệu ban đầu, thay vì tự đi vào menu chuẩn.

Bằng chứng bổ sung: [`main-onresume-safe.json`](main-onresume-safe.json), [`main-onresume-safe.java`](source/main-onresume-safe.java), [`s2-run.java`](source/s2-run.java).

## Kết quả

Luồng bình thường là `MainApplication → SplashActivity → MainActivity`. Khi preference `safe_mode` bật, hệ thống có thêm launcher `SafeActivity → MainActivity` với delay 750 ms và cờ Safe Mode. Việc hiển thị khung hình thực tế, thời gian I/O import và hành vi khi Android hủy tiến trình vẫn cần quan sát runtime.
