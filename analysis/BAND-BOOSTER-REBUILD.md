# Band Booster: lõi DSP

Mở rộng kiểm chứng native arm64: 144.000 sample, ba bộ gain tại 32.000/44.100/48.000/96.000 Hz. Tất cả PASS, sai khác lớn nhất mỗi rate đều là 1 đơn vị PCM nguyên. Đã cài bản dựng và test APK lên thiết bị, chạy instrumentation thực rồi mở lại MainActivity thành công. Kết quả này không thay thế kiểm chứng mọi input/ABI hoặc style SET end-to-end.

Kiểm chứng Android arm64 trên thiết bị 88d75c04: NativeBandBoosterSmoke chạy JNI libnbandbooster.so gốc và scalar trên cùng 36.000 sample (ba cấu hình gain, tín hiệu giả ngẫu nhiên rồi silence). PASS, sai khác lớn nhất 1 đơn vị PCM nguyên. Handle luôn close trong finally. Đây là đối chiếu thuật toán ở 48 kHz trên ABI hiện tại, chưa bao phủ mọi input/rate/ABI hoặc SET end-to-end.

`ReferenceBandBooster` dựng lại đường scalar từ `Java_sofeh_audio_NBandBooster_build/process` trong assembly arm64 và x86_64 đã lưu.

- Hai filter cascade bốn tầng; hệ số `2*sin(2764.602f/rate)` và `2*sin(15707.96f/rate)`.
- Gain từng dải = `1 + parameter * 0.01f`.
- Low = filter thấp; mid = filter cao trừ low; high = input trễ ba sample trừ filter cao.
- State float riêng, đầu vào/ra Long như JNI, reset toàn bộ filter/delay.
- Test unity bands trả input trễ ba sample (dung sai lượng tử một đơn vị), impulse có tail và reset lặp lại đúng. Build và test đạt.

Đã đọc trực tiếp DEX lớp `Lsofeh/audio/b;`: reader g đọc byte signed vào g[2],g[1],g[0], bỏ byte cuối; build truyền đúng thứ tự g[2],g[1],g[0]. Payload tương ứng Low/Mid/High, giới hạn setter -100..100. Hàm e xử lý trái rồi phải trên cùng handle. ImportedFxChain đã nối FX 31 theo thứ tự này; test signed payload, state xen kẽ và reset đạt. Tail hiện giữ 100 ms ở 48 kHz. Chưa xác minh sai khác FMA/float giữa ABI bằng chạy so sánh với native; chưa đối chiếu SET thực chứa FX 31.
