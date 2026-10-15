# Tổng quan APK ORG 24

Đã giải mã [Constant.Instrument và Sound.Instrument](INSTRUMENT-ASSETS.md) đến đúng EOF: 15 section, 3.433.536 byte PCM nội tuyến; đã xuất WAV kiểm chứng từ mỗi asset.

Đã [đối chiếu PCM và xuất WAV](XUAT-PCM-WAV.md): toàn bộ 2.001 section khớp chính xác với 1.251 wave; thêm công cụ xuất PCM nội tuyến hoặc wave dùng chung và bốn file WAV kiểm chứng.

Đã xác định [vòng đời event nốt](EVENT-NOTE-LIFECYCLE.md): type 2 mở nốt, type 1 release/đóng theo cùng ID; nối được logic ghi Length, scheduler và connection effect với số liệu trong Template.

Đã giải mã [empty.Template 1.2.4](EMPTY-TEMPLATE-INVENTORY.md) đến đúng EOF: 2 Instrument, 3 section chứa 297.880 byte PCM nội tuyến, 1 System, 14 Track và 1 TrackGroup.

Đã xuất [inventory thực của Default.Template](DEFAULT-TEMPLATE-INVENTORY.md): parser đi đúng đến EOF, tìm được 92 Instrument, 11 System, 2.648 Track, 158.018 event, 610 TrackGroup và 62.086.248 byte PCM dùng chung.

Đã đọc [STS, Channels và Effect](STS-CHANNELS-EFFECT.md): payload STS, record Effect 40 byte, 8 slot cố định, ba layout Channels lịch sử và bảng kích thước của đủ 16 module FX.

Đã nối [collection, System, Track và Event](COLLECTION-TRACK-EVENT.md): cách lưu count/object, hai kiểu chuỗi, bảng tuning, metadata MIDI/STS, `KeyData`, event 101 nhúng sample và phần TrackGroup theo phiên bản.

Đã bóc tách [định dạng Template/SET và Instrument](DINH-DANG-SET-INSTRUMENT.md): header chung, nhánh phiên bản, kho wave dùng chung, mode/sound/section và cách lưu PCM. Có script đối chiếu trực tiếp bốn asset đi kèm APK.

Đã đọc [callback SoundPool và writer WAV](SOUNDPOOL-VA-WAV-WRITER.md): PCM little-endian, header cập nhật khi đóng; callback không dùng status và có điểm bất thường nối thêm `.wav` vào đường dẫn đã có đuôi này.

Đã nối [vòng đời WAV cho mode 4](VONG-DOI-WAV.md): nơi gọi chuẩn bị lại, nốt gốc được render, thư mục `.temp` và tác vụ dọn file cũ hơn 24 giờ khi khởi tạo.

Đã [xác minh bằng DEX](XAC-MINH-DEX.md): Activity khởi tạo mode 1, 44100 Hz, buffer Auto; sửa điều kiện SoundPool bị đảo trong Java suy dịch — nhánh này bỏ qua mode <= 2.

Đã đọc [cơ chế chuẩn bị âm bổ sung và nốt ghi sẵn](CHE-DO-AM-BO-SUNG.md) cho mode 3/4: tài nguyên SoundPool và WAV xuất từ nhạc cụ. Điều kiện phát trong mã suy dịch còn cần đối chiếu DEX.

Đã xác định [các cài đặt âm thanh và giá trị lưu](CAI-DAT-AM-THANH.md): khóa `output_low_latency`, năm lựa chọn chế độ, các mức buffer/chất lượng và đường nạp vào Music.

Đã xác định [fallback AudioTrack](FALLBACK-AUDIOTRACK.md): tạo thiết bị, gọi cùng bộ trộn rồi ghi buffer `short[]`; đã nối đủ ba đường đầu ra chính. Nơi nạp lựa chọn chế độ và hành vi runtime còn chưa xác minh.

Đã nối [callback phát âm và hai chế độ OpenSL](CALLBACK-AUDIO.md): mode 1 gọi Java từ callback; mode 2 dùng thread Java cung cấp dữ liệu. Cả hai callback đều có điểm enqueue xác định trong mã ARM64.

Đã bổ sung [phân tích mã native âm thanh ARM64](NATIVE-AUDIO.md): disassembly năm hàm, các mode trộn mẫu, cơ chế chép/trộn buffer và giới hạn chưa kiểm chứng runtime.

Đã bổ sung [phân tích luồng bấm phím → phát âm thanh](LUONG-PHAT-AM.md), kèm bằng chứng REA và các giới hạn chưa xác minh.

Đã đọc tiếp cơ chế mode/aftertouch/layer, cấp phát và release voice, nhận diện phím trắng/đen; đã đối chiếu các điểm JNI với bảng ký hiệu ELF ARM64. Chi tiết và file bằng chứng nằm trong báo cáo luồng phát âm.

Phân tích tĩnh bằng REA 6.5.0 và jadx-headless-mcp 0.7.1. Không chạy ứng dụng.

## Thông tin xác nhận từ APK

- Package: `com.sofeh.android.musicstudio3`
- Phiên bản: `2026.10.01`, version code `274`.
- SDK tối thiểu: 24; SDK mục tiêu: 36.
- SHA-256: `9512e006f4c1780683aa19510dad701c415c98f3d87d0adbd7559088813e8855`.
- JADX báo 22.322 lớp và 1.349 tài nguyên; số lớp bao gồm các SDK bên thứ ba.
- 3 DEX ở gốc APK; thêm một DEX tại `assets/audience_network/classes.dex`.
- 58 file thư viện native, phân bố trên ARM 32/64 bit và x86 32/64 bit.

## Các thành phần

| Nhóm | Bằng chứng | Nhận xét |
| --- | --- | --- |
| Khởi động và giao diện | `SplashActivity`, `SafeActivity`, `MainActivity`; application `sofeh.android.MainApplication` | Manifest khai báo các thành phần này; chưa xác nhận thứ tự gọi khi chạy. |
| Cài đặt | `sofeh.android.setting.SettingActivity`, `SettingFragment0` đến `SettingFragment6` | Có các lớp riêng cho cài đặt. |
| Mô hình âm nhạc | `sofeh.music.Instrument`, `Channels`, `Track`, `Tracks`, `TrackEvent`, `WaveData`, `Effect` | Tên lớp gợi ý cách tổ chức nhạc cụ, kênh, track, sự kiện và hiệu ứng; chưa đọc chi tiết triển khai. |
| Âm thanh native | `sofeh.audio.NOpenSL`, `NEqualizer`, `NFlanger`, `NGVerb`, `NPitchShift`, `Lame`; các file `.so` tương ứng | Có lớp và thư viện mang tên các bộ xử lý âm thanh; quan hệ JNI cụ thể chưa được kiểm tra. |
| Nhạc cụ và dữ liệu | `Default.Template` (67.848.221 byte), `Sound.Instrument`, `Constant.Instrument`, `Chord`, `data.zip` | Quan sát tên và kích thước file; chưa giải mã định dạng riêng. |
| Giao diện tùy chỉnh | `assets/skin/*.Skin`, `*.Part`, `assets/menu/*` | Có tài nguyên skin và menu nhiều ngôn ngữ. |
| Quảng cáo và dịch vụ | Các trường Google Ads, AppBrain và FirebaseAnalytics trong MainActivity; thư viện AppLovin và các thành phần quảng cáo trong manifest | Một phần mã APK phục vụ SDK bên thứ ba. |

## Quyền khai báo

Có các quyền micro, điều chỉnh âm thanh, Bluetooth, vị trí, bộ nhớ, Internet, thông báo và thanh toán. Quyền khai báo không chứng minh ứng dụng đã sử dụng quyền hoặc truyền dữ liệu khi chạy. Danh sách đầy đủ nằm trong `package.json` và `AndroidManifest.xml`.

## Dùng lại REA

Từ thư mục dự án, chạy trong PowerShell:

```powershell
.\run-rea.ps1 inspect-android-package '.\ORG+24_+Your+Music_2026.10.01_APKPure.apk' --format json
.\run-rea.ps1 search-android-classes '.\ORG+24_+Your+Music_2026.10.01_APKPure.apk' 'sofeh.music' --format json
```

Script chọn JDK và JAR trong `%LOCALAPPDATA%\rea\android`, dùng REA phiên bản 6.5.0 qua npx. Không cần thay đổi PATH toàn hệ thống.

## Bằng chứng và giới hạn

- `package.json`: kết quả REA đầy đủ và manifest.
- `AndroidManifest.xml`: manifest đã giải mã.
- `classes-sofeh.json`: 66 tên lớp khớp `sofeh`; không đại diện toàn bộ mã riêng vì một số tên đã bị rút gọn.
- `main-activity.json`: danh mục phương thức và trường của MainActivity, chưa phải mã nguồn phương thức.
- `archive-inventory.csv`: danh mục file và kích thước trong APK.

Chưa xác minh chữ ký APK hoặc nguồn gốc bản tải APKPure. Chưa phân tích mã máy trong `.so`, chưa quan sát hành vi thực tế và chưa xác nhận luồng MIDI/âm thanh. Tên lớp và thư viện chỉ hỗ trợ giả thuyết về vai trò, không đủ để kết luận thuật toán.
