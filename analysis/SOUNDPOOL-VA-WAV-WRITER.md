# Callback SoundPool và writer WAV

Đã tìm callback trong `classes.dex`, không phải `classes3.dex`. Các quan sát dưới đây là tĩnh; chưa tạo WAV bằng ứng dụng hoặc kiểm tra phát âm trên Android.

## Tạo SoundPool

`defpackage.yd1.g` xóa hai danh sách sound ID/đường dẫn, thử `new SoundPool(9, 3, 0)`. Nếu không tạo được, thử Builder với usage 14 và content type 4; nhánh Builder không gọi `setMaxStreams` trong đoạn đã đọc. Sau đó đăng ký `b23` làm `OnLoadCompleteListener`, nạp hai sound metronome vào đầu danh sách. Các âm bổ sung nằm sau hai slot này.

Bằng chứng: `soundpool-create.json`; DEX trong `dex-lifecycle/` và index do script quét tạo. Không thấy xử lý riêng trường hợp cả hai cách tạo đều thất bại trước khi gọi `setOnLoadCompleteListener` trong thân hàm này.

## Callback có kiểm tra ready không?

`b23.onLoadComplete(SoundPool, sampleId, status)`:

1. Tìm `sampleId` trong danh sách sound ID.
2. Nếu không thấy thì trả về.
3. Nếu chuỗi đường dẫn ở slot khác rỗng, gọi `bp3.e(path + ".wav")`.
4. Đổi chuỗi ở slot thành `"."`.

DEX xác nhận tham số status không được dùng trong thân callback này; không thấy field boolean ready hoặc tín hiệu chờ load hoàn tất được ghi ở đây. Đây là giới hạn của callback đã đọc, không chứng minh mọi tầng khác đều bỏ qua trạng thái nạp.

`bp3.e` tạo `File`, kiểm tra tồn tại rồi gọi `delete`, không kiểm tra boolean trả về.

### Đường dẫn có đuôi kép

Trong mode 4, `lowLatencyInstruments` tạo đường dẫn dạng `.../<number>.wav`; `r03.g` lưu nguyên đường dẫn đó vào danh sách. Callback lại nối thêm `.wav` trước khi gọi xóa. Chuỗi đích theo các hàm đã nối là `.../<number>.wav.wav`, trong khi file xuất ban đầu có một đuôi `.wav`.

Đây là bất thường tĩnh đáng kiểm tra. Chưa quan sát filesystem để kết luận mỗi lần đều để lại file gốc; vẫn có tác vụ dọn file cũ trong `.temp` khi khởi tạo. Với nhãn `"."` đã ghi lại, callback lặp lại còn có thể tạo đường dẫn khác; chưa quan sát tình huống callback lặp.

`SoundPool.play` ở nhánh đã đọc lấy ID từ danh sách, không đợi một cờ ready do callback này đặt. Kết quả load/play và thao tác đổi sound liên tiếp cần được kiểm chứng trên thiết bị.

Bằng chứng: `dex/soundpool-onLoadComplete.dexasm`, `soundpool-load-recorded.json`, `latency-instruments.json`, `callback-file-helper.json`; callback xuất lại được bằng script `find_wav_lifecycle.py` qua index.

## Định dạng file xuất

`sofeh.audio.a` nhận mode ở đối số thứ hai:

| Mode | Quan sát |
| --- | --- |
| 1 | Mở file truy cập ngẫu nhiên `rw`, ghi header RIFF/WAVE và dữ liệu PCM. |
| 2 | Mở buffered output stream, gọi LAME init/encode/flush để tạo MP3. |

Mode 4 low-latency gọi writer với mode file 1, nên đây là WAV chứ không phải MP3 đổi đuôi.

Header WAV do `b()` ghi có 44 byte: `RIFF`, size `length - 8`, `WAVE`, `fmt ` size 16, format tag 1, channel count, sample rate, byte rate, block align, bits per sample, `data`, size `length - 44`. Số nhiều byte được đảo sang little-endian. Sample rate, channels và bits lấy từ cấu hình Music truyền vào writer, không hard-code 44100 trong hàm header.

`c(short[], frameCount)` tính số byte bằng `(bits / 8) * channels * frameCount`, tạo ByteBuffer little-endian, đưa short array vào rồi ghi byte. Luồng mode 4 đã quan sát dùng stereo 16 bit. Không suy ra hàm xử lý an toàn mọi bit depth chỉ vì header có field bits.

`a()` lúc đóng WAV gọi lại `b()` để cập nhật size rồi đóng file. Lần ghi header ban đầu khi file chưa có dữ liệu chưa phải header cuối cùng; cần file đã đóng để kiểm tra kích thước. Constructor gọi `bp3.e(path)` trước khi mở, tức cố gắng xóa file cũ cùng tên.

Bằng chứng: `wav-writer-init.json`, `wav-writer-init-full.json`, `wav-writer-write.json`, `wav-header.json`, `wav-writer-close.json`. Các bản Java tương ứng trong `source/`.

## Phần còn cần kiểm chứng

- Load thất bại, phát trước khi load xong và đổi nhạc cụ liên tiếp.
- Có còn WAV một đuôi sau callback hay không, so với cơ chế dọn 24 giờ.
- File WAV thực tế có khớp header, số frame và âm thanh mong đợi không.

Không sửa hoặc rebuild APK trong quá trình này. Kết luận về bất thường không đồng nghĩa đã tái hiện lỗi runtime.
