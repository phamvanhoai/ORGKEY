# ORGX

Ứng dụng music workstation Android do **hovait** thiết kế. Dự án tái hiện các luồng chức năng đã nghiên cứu từ ORG 24 bằng mã nguồn, giao diện và thương hiệu mới.

## Phiên bản 0.1.0 hiện có

- Giao diện ngang tối ưu cho màn hình cảm ứng.
- Bàn phím cảm ứng đa điểm hai quãng tám, nhận đúng phím trắng/phím đen và cho phép rê ngón giữa các nốt.
- Touch velocity theo vị trí chạm và phản hồi màu riêng cho từng phím đang giữ.
- Ba âm sắc tổng hợp: ORGX Grand, ORGX Organ và ORGX Lead; phát bằng `AudioTrack` 48 kHz.
- Voice engine có attack/release envelope, decay cho Grand, sustain nhả mềm và giới hạn 24 voice để tránh click/quá tải.
- Bộ tổng hợp nhiều nốt, đổi octave, sustain và chỉnh âm lượng tổng.
- Ba style rhythm 8 bước Pop, Rock và Dance phát kick, snare, hi-hat; chạy/dừng bằng PLAY và bám theo tempo.
- Thư viện 12 hợp âm C, Dm, Em, F, G, Am, Bdim, C7, D7, E7, Fmaj7 và G7; phần đệm đổi ngay và đồng bộ rhythm.
- Song sequencer tự chuyển hợp âm mỗi ô nhịp với ba progression Pop, Ballad và Dance; chế độ AUTO được lưu qua lần mở sau.
- Thu trực tiếp toàn bộ tiếng đàn và rhythm ra WAV stereo 48 kHz/16-bit bằng nút REC.
- Thư viện FILES liệt kê bản thu, hiển thị thời lượng, nghe lại và chia sẻ WAV qua Android.
- Điều khiển tempo, trạng thái engine và hộp thông tin “Designed by hovait”.
- Tự lưu tempo, octave, nhạc cụ, hợp âm và style giữa các lần mở ứng dụng.
- Ba ô nhớ SET để lưu/gọi lại toàn bộ cấu hình biểu diễn, gồm cả âm lượng tổng.
- Mixer bốn kênh độc lập cho Keys, Chord, Drums và Master; mức kênh được lưu tự động và trong từng SET.
- FX thời gian thực gồm Delay và Reverb, có mức wet riêng; FX được đưa vào WAV và lưu cùng SET.
- Tám performance pad tổng hợp: Kick, Snare, Hi-hat, Clap, Low Tom, High Tom, Crash và Perc; đi qua kênh Drums/FX và bộ ghi WAV.
- Mỗi lần thu tạo WAV stereo 48 kHz và MIDI Type 0 chứa nốt bàn phím, velocity và tempo; cả hai có thể chia sẻ từ FILES.
- PROJECT lưu, mở lại và chia sẻ toàn bộ cấu hình buổi diễn bằng tệp `.orgx`, gồm nhạc cụ, tempo, hợp âm tự động, rhythm, mixer và FX.
- Nhập và phát tệp MIDI Type 0/1 qua trình chọn tệp Android bằng bộ tiếng ORGX; hỗ trợ note on/off, velocity, running status và tempo.

## Chưa triển khai

- Thư viện tiếng nhạc cụ và các bộ style/rhythm hoàn chỉnh.
- Sequencer, hợp âm tự động, pad và mixer nhiều kênh.
- Xuất MP3.
- Audio engine native có độ trễ thấp tương đương ứng dụng tham chiếu.

## Build

```powershell
.\gradlew.bat :app:assembleDebug
```

APK đầu ra: `app/build/outputs/apk/debug/app-debug.apk`.

## Kiểm tra gần nhất

- Build debug thành công với JDK 17 và Android SDK 35.
- Cài đặt và khởi chạy thành công trên thiết bị Android thật.
- Đã thử thao tác bàn phím; không có lỗi `AndroidRuntime`.
- Đã thu và kiểm tra tệp WAV RIFF stereo 48 kHz/16-bit trên thiết bị thật.
