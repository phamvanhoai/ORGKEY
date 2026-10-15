# Coverage `libnpitchshift.so`

Đã xác nhận thư viện ARM64 `lib/arm64-v8a/libnpitchshift.so` (49.896 byte) chứa trực tiếp module pitch-shift, không chỉ là import:

- `smbPitchShift`: 1.468 byte, 367 lệnh ARM64.
- `Java_sofeh_audio_NPitchShift_open`: 100 byte.
- `Java_sofeh_audio_NPitchShift_process`: 68 byte.
- `Java_sofeh_audio_NPitchShift_processLeft`: 52 byte.
- `Java_sofeh_audio_NPitchShift_processRight`: 56 byte.
- Ngoài ra có `smbPitchShiftNew/Free`, stereo, flush, copy, sample, FFT và atan2.

Điều này sửa lại giới hạn trước đó: `libnmusic.so` chỉ gọi module qua PLT, còn implementation được đóng gói trong `libnpitchshift.so`.

Đã trích assembly đầy đủ bằng [dump_npitch.py](dump_npitch.py) vào `analysis/native/`: `smbPitchShift.asm` (367 lệnh), cùng bốn wrapper JNI `NPitchShift_*.asm`. Các file bao phủ đủ kích thước symbol ELF ARM64.

Đã mở rộng disassembly cho `smbPitchShiftSample` (304 lệnh), `smbFft` (126), `smbPitchShiftNew` (10), `smbPitchShiftFlush` (44) và `smbPitchShiftCopy` (45). Như vậy các entry point chính của module pitch-shift/FFT đã có assembly ARM64 trong thư mục `analysis/native/`.
