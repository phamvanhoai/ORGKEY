# ORGX 1.23.1

## APK

- File: `app/build/outputs/apk/debug/app-debug.apk`
- Package: `com.hovait.orgx`
- Version code: `37`
- SHA-256: `32B2844E9DEE8CD15B57345D9087F9E0F0C659E4B91A241B561B0C1C32A0EE11`

## Thay đổi chính

- `AudioEngine` chạy qua contract `AudioBackend` và adapter `AudioTrackBackend`.
- MIDI: Program Change GM, GM drums channel 10, sustain, pitch bend, CC7/CC10/CC11 và safety controllers.
- Thu âm: COUNT-IN, đồng hồ 5 phút, tự lưu khi nền/đóng, staging WAV/MIDI và PANIC.
- Giao diện: vùng chạm 48 dp, nhãn PERFORMANCE đầy đủ và trạng thái RECORDING/SAVING.

## Kiểm tra

- 48 unit/integration test đạt.
- Build debug thành công với JDK 17/Android SDK 35.
- APK đã cài và khởi chạy trên thiết bị Android thật.
- Log runtime không có `AndroidRuntime`, `Audio output failed` hoặc underrun trong lần kiểm tra cuối.

## Giới hạn

Đây là bản tái dựng chức năng. Native engine, sample/template và skin gốc của APK tham chiếu chưa được thay thế toàn bộ.
