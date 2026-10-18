# Pitch Shift FX 15

DEX `Lsofeh/audio/f;` reader g lưu int32 Shift; setter giới hạn 50–200, mặc định 100. Wrapper e gọi NPitchShift.processLeft/Right(handle,Shift,sampleRate,input) theo từng sample. Đã nối ImportedFxChain với thư viện libnpitchshift.so gốc, cấp phát khi có tín hiệu, reset đóng handle và tạo lại khi cần, close tường minh. Tail hiện giữ tối đa hai giây; chưa đo latency/tail chính xác.

NativePitchShiftSmoke trên thiết bị arm64 88d75c04: chạy sine 440 Hz tại 48 kHz, Shift 50/100/200, kiểm tra tín hiệu hữu hạn, phải im khi đầu vào phải im, reset và close. PASS, peak 0.375640869140625. Chưa đo tần số đầu ra hoặc so sánh bản thu APK, chưa thử SET thực chứa FX 15. Đây là tái sử dụng native gốc, chưa tái dựng thuật toán phase vocoder bằng mã nguồn ORGX.
