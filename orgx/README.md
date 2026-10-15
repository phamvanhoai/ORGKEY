# ORGX

Ứng dụng music workstation Android do **hovait** thiết kế. Dự án tái hiện các luồng chức năng đã nghiên cứu từ ORG 24 bằng mã nguồn, giao diện và thương hiệu mới.

## Phiên bản 0.1.0 hiện có

- Giao diện ngang tối ưu cho màn hình cảm ứng.
- Bàn phím cảm ứng đa điểm hai quãng tám, nhận đúng phím trắng/phím đen và cho phép rê ngón giữa các nốt.
- Ba âm sắc tổng hợp: ORGX Grand, ORGX Organ và ORGX Lead; phát bằng `AudioTrack` 48 kHz.
- Bộ tổng hợp nhiều nốt, đổi octave, sustain và chỉnh âm lượng tổng.
- Rhythm 8 bước phát kick, snare và hi-hat tổng hợp, chạy/dừng bằng PLAY và bám theo tempo.
- Phần đệm hòa âm tự động đồng bộ rhythm, chuyển trực tiếp giữa C, Am, F và G.
- Thu trực tiếp toàn bộ tiếng đàn và rhythm ra WAV stereo 48 kHz/16-bit bằng nút REC.
- Điều khiển tempo, trạng thái engine và hộp thông tin “Designed by hovait”.

## Chưa triển khai

- Thư viện tiếng nhạc cụ và các bộ style/rhythm hoàn chỉnh.
- Sequencer, hợp âm tự động, pad và mixer nhiều kênh.
- Lưu dự án, nhập MIDI và xuất MP3.
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
