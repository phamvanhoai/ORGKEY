# Pitch Shift FX 15

## C?p nh?t 2026-10-19: x? b? ??m theo c?a s? FFT

ORGX d?ng gi?i h?n 4.096 frame thay cho hai gi?y ?? x? Pitch Shift, sau ?? ??ng/reset b? ??m tr??c n?t ti?p theo. T?i 48 kHz t??ng ???ng kho?ng 85 ms thay v? 2.000 ms; ??y l? th?i gian duy tr? x? l? sau ??u v?o, kh?ng ph?i ?? tr? nghe c?a hi?u ?ng.

?? build v? c?i b?n debug l?n thi?t b? arm64 88d75c04. NativePitchShiftSmoke PASS tr?n 604 c?u h?nh: m?i m?c Shift nguy?n 50?200 t?i 32.000 / 44.100 / 48.000 / 96.000 Hz, ??u v?o burst sine 440 Hz d?i 4.096 frame. M?u kh?c zero cu?i c?ng xu?t hi?n mu?n nh?t 1.786 frame sau burst, n?m trong ng?n s?ch 4.096 frame. Ki?m tra th?m qua ImportedFxChain: h?t ng?n s?ch th? active=false; ph?t l?i sau idle t?i l?p n?ng l??ng ban ??u, kh?ng mang b? ??m n?t c?. T?n s? ??u ra 220/440/880 Hz cho Shift 50/100/200 v?n PASS.

Assembly JNI ??t FFT size 1.024 v? overlap 4. C?c s? first-nonzero 274?311 trong m?t s? c?u h?nh l? th?i ?i?m ??u ra v??t l??ng t? PCM c?a t?n hi?u th?, kh?ng ph?i ph?p ?o group delay. Gi?i h?n 4.096 l? ch?nh s?ch b?o th? c?a ORGX ???c ki?m tra v?i burst tr?n; ch?a ch?ng minh cho m?i d?ng t?n hi?u, ch?a ??i chi?u b?n thu ?a ?m t? APK. C?c ?o?n ghi ?hai gi?y? b?n d??i l? l?ch s? tr??c c?p nh?t n?y.


Kiểm chứng thêm qua ImportedFxChain trên điện thoại: payload 200 little-endian, sine stereo với kênh phải im, reset xóa active/state, render lại tái lập năng lượng (sai khác <1e-9), xả 96.000 frame silence kết thúc active tail. PASS. Giới hạn tail hai giây là chính sách hiện tại, chưa đo đuôi tối thiểu/latency gốc.

Đo thêm trên arm64: sine đầu vào 440 Hz tại 48 kHz, một giây mỗi cấu hình, đếm zero-crossing dương ở nửa giây cuối sau warm-up. Shift 50 đo 220 Hz, Shift 100 đo 440 Hz, Shift 200 đo 880 Hz. PASS với dung sai 8%; kết quả thực tế đúng tần số mục tiêu trong độ phân giải phép đo 2 Hz. Đây là phép đo đơn âm, chưa đánh giá chất lượng đa âm hoặc latency.

DEX `Lsofeh/audio/f;` reader g lưu int32 Shift; setter giới hạn 50–200, mặc định 100. Wrapper e gọi NPitchShift.processLeft/Right(handle,Shift,sampleRate,input) theo từng sample. Đã nối ImportedFxChain với thư viện libnpitchshift.so gốc, cấp phát khi có tín hiệu, reset đóng handle và tạo lại khi cần, close tường minh. Tail hiện giữ tối đa hai giây; chưa đo latency/tail chính xác.

NativePitchShiftSmoke trên thiết bị arm64 88d75c04: chạy sine 440 Hz tại 48 kHz, Shift 50/100/200, kiểm tra tín hiệu hữu hạn, phải im khi đầu vào phải im, reset và close. PASS, peak 0.375640869140625. Chưa đo tần số đầu ra hoặc so sánh bản thu APK, chưa thử SET thực chứa FX 15. Đây là tái sử dụng native gốc, chưa tái dựng thuật toán phase vocoder bằng mã nguồn ORGX.
