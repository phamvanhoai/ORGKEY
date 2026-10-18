# Band Booster: lõi DSP

`ReferenceBandBooster` dựng lại đường scalar từ `Java_sofeh_audio_NBandBooster_build/process` trong assembly arm64 và x86_64 đã lưu.

- Hai filter cascade bốn tầng; hệ số `2*sin(2764.602f/rate)` và `2*sin(15707.96f/rate)`.
- Gain từng dải = `1 + parameter * 0.01f`.
- Low = filter thấp; mid = filter cao trừ low; high = input trễ ba sample trừ filter cao.
- State float riêng, đầu vào/ra Long như JNI, reset toàn bộ filter/delay.
- Test unity bands trả input trễ ba sample (dung sai lượng tử một đơn vị), impulse có tail và reset lặp lại đúng. Build và test đạt.

Chưa ánh xạ payload FX 31 bốn byte sang ba tham số build của wrapper Java; chưa nối vào ImportedFxChain. Chưa xác minh sai khác FMA/float giữa ABI bằng chạy so sánh với native. Không đánh dấu FX 31 là đã hỗ trợ phát SET.
