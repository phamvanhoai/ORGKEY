# Pitch Shift FX 15

Kiểm chứng thêm qua ImportedFxChain trên điện thoại: payload 200 little-endian, sine stereo với kênh phải im, reset xóa active/state, render lại tái lập năng lượng (sai khác <1e-9), xả 96.000 frame silence kết thúc active tail. PASS. Giới hạn tail hai giây là chính sách hiện tại, chưa đo đuôi tối thiểu/latency gốc.

Đo thêm trên arm64: sine đầu vào 440 Hz tại 48 kHz, một giây mỗi cấu hình, đếm zero-crossing dương ở nửa giây cuối sau warm-up. Shift 50 đo 220 Hz, Shift 100 đo 440 Hz, Shift 200 đo 880 Hz. PASS với dung sai 8%; kết quả thực tế đúng tần số mục tiêu trong độ phân giải phép đo 2 Hz. Đây là phép đo đơn âm, chưa đánh giá chất lượng đa âm hoặc latency.

DEX `Lsofeh/audio/f;` reader g lưu int32 Shift; setter giới hạn 50–200, mặc định 100. Wrapper e gọi NPitchShift.processLeft/Right(handle,Shift,sampleRate,input) theo từng sample. Đã nối ImportedFxChain với thư viện libnpitchshift.so gốc, cấp phát khi có tín hiệu, reset đóng handle và tạo lại khi cần, close tường minh. Tail hiện giữ tối đa hai giây; chưa đo latency/tail chính xác.

NativePitchShiftSmoke trên thiết bị arm64 88d75c04: chạy sine 440 Hz tại 48 kHz, Shift 50/100/200, kiểm tra tín hiệu hữu hạn, phải im khi đầu vào phải im, reset và close. PASS, peak 0.375640869140625. Chưa đo tần số đầu ra hoặc so sánh bản thu APK, chưa thử SET thực chứa FX 15. Đây là tái sử dụng native gốc, chưa tái dựng thuật toán phase vocoder bằng mã nguồn ORGX.
