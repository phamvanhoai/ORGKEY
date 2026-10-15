# Vòng đời WAV cho chế độ nốt ghi sẵn

Đã phân tích tiếp [callback nạp SoundPool và writer](SOUNDPOOL-VA-WAV-WRITER.md), gồm thao tác xóa sau callback và điểm bất thường đuôi file kép.

Phân tích tĩnh APK hiện tại, không tạo hoặc xóa file của ứng dụng Android. Đã đối chiếu các điểm gọi bằng DEX và đọc thân hàm qua REA.

## Khi nào chuẩn bị lại âm?

`Q0` có bốn caller trong danh mục tham chiếu REA: `G1`, `v0`, `v1`, `z1`. Danh sách này là quan hệ tĩnh, không phải tần suất gọi runtime.

- `G1(String, boolean)` thay đổi trạng thái `TrackRecordSet` của các track bàn phím, cập nhật vùng phím bằng `H1(false)`, rồi gọi `Q0`.
- `z1(int)` xử lý lựa chọn section của Style. Trong một nhánh áp dụng `STSPut`, nó gọi `H1(false)`, `Q0`, rồi cập nhật giao diện. Không phải mọi lần chọn Style đều chạy Q0.
- DEX ghi nhận hai điểm gọi trong `v0` và hai điểm trong `v1`; chưa phân loại hết các nhánh của hai hàm lớn này.

Tên file `select-inst-z1.json` là tên artifact nghiên cứu ban đầu, không phải bằng chứng rằng `z1` chỉ xử lý chọn nhạc cụ; thân hàm cho thấy xử lý Style/STS như trên.

## WAV chứa gì?

Mode 4 gọi `ExportKeyToAudioFile` bảy lần, với octave 3..9 và pitch class 0. Hàm mở nốt `(octave * 12) + pitchClass`, velocity 100, cho mỗi ID nhạc cụ dương trong mảng được Q0 chuẩn bị. Vì vậy các nốt gốc được yêu cầu là 36, 48, 60, 72, 84, 96 và 108; đây là mã nốt, không suy ra tần số tuyệt đối khi tuning khác nhau.

`ExportKeyToAudioFile`:

1. Đặt `fOutBufferSize = fOutSampleRate / 100` theo phép chia nguyên.
2. Tạo `Channels` riêng, `fType = 6`, mixer 1, các bus trái/phải riêng và mode được truyền vào.
3. Mở các voice và giới hạn `Release` trên một số slot được truy cập xuống tối đa 5 theo đơn vị nội bộ; chưa quy đổi thành giây.
4. Mỗi vòng xóa bus, chạy `Channels.Process`, chia mức âm cho 200, áp dụng công thức phi tuyến đã thấy ở mixer, rồi ghi buffer stereo short qua `sofeh.audio.a.c`.
5. Kết thúc khi `channels.isEmpty()`, đóng writer bằng `a()`.

DEX xác nhận tại byte `0x1c8`: `if-ge oldCounter, 4` nhảy qua vòng Close. Do đó trong bốn vòng đầu (counter 0..3) nó gửi Close cho các ID nhạc cụ hợp lệ; sau đó tiếp tục render đến khi voice hết. Không diễn giải đây là giữ phím 40 ms: Close đã bắt đầu sau block đầu tiên, còn đuôi âm phụ thuộc release và xử lý voice.

Hàm ghi vào field `fOutBufferSize` của đối tượng Music, dù dùng Channels/bus riêng. Chưa chứng minh có đồng bộ với thread phát hoặc phục hồi kích thước ở mọi đường chạy; đây là điểm cần kiểm chứng, chưa phải kết luận có lỗi cạnh tranh.

Bằng chứng: `export-note.json`, `source/export-note.java`, `dex/Music-ExportKeyToAudioFile-8.dexasm`.

## Lưu ở đâu và dọn khi nào?

Trong `MainActivity.onCreate`, `W3` lấy từ `xs1.a(context)`; `f4 = W3 + separator + ".temp"`. Đây là đường WAV mode 4 sử dụng. Nó khác với thư mục `V3` chứa bộ SET/Instrument, có thể đổi bằng `root_folder`; không được mặc định đặt WAV trong thư mục ORG người dùng chọn.

Đã đọc `xs1.a`: ưu tiên `getExternalFilesDir(null)` nếu khác null và ghi được, nếu không dùng `getFilesDir()`. Vậy `.temp` nằm dưới vùng file riêng của ứng dụng ở bộ nhớ ngoài hoặc vùng file nội bộ fallback, không phải Android cache directory theo API. Không có thiết bị để xác nhận đường tuyệt đối. Bằng chứng: `app-storage-root.json`.

Sau khi chuẩn bị thư mục `.temp`, onCreate gửi tác vụ `k82(file10, 0)` vào executor. Nhánh 0 gọi:

```text
bp3.p(tempDirectory, true, currentTimeMillis - 86400000)
```

`bp3.p` duyệt đệ quy: file có `lastModified` nhỏ hơn mốc sẽ được yêu cầu xóa; thư mục con rỗng được yêu cầu xóa; thư mục gốc được giữ do đối số true. Không lọc đuôi WAV, nên áp dụng cho mọi file cũ dưới `.temp`. Kết quả boolean của `File.delete()` không được kiểm tra, exception bị bỏ qua. Vì vậy chỉ kết luận có **cố gắng dọn file cũ hơn 24 giờ khi khởi tạo**, không bảo đảm xóa thành công, không có bằng chứng dọn định kỳ đúng mỗi 24 giờ.

Không thấy `File.delete` trong `r03.g` khi unload/thay sound. Việc thay một slot SoundPool không tự chứng minh WAV cũ bị xóa ngay. Còn cần khảo sát các hàm quản lý file khác để kết luận toàn bộ chính sách dọn.

Bằng chứng: `main-oncreate.json`, `temp-cleanup-k82.json`, `temp-cleanup-helper.json`, `soundpool-load-recorded.json`.

## Artifact kiểm tra lại

`dex-lifecycle/index.json` liệt kê vị trí lệnh thực sự đọc/ghi `f4`, gọi Q0 và các lời gọi SoundPool đã chọn. Các file `method-*.dexasm` giữ cả thân hàm. Script `find_wav_lifecycle.py` quét instruction đã giải mã, không chạy code APK; hiện giới hạn ở `classes.dex` và `classes3.dex` và các tiêu chí chỉ định, không phải khảo sát mọi thao tác filesystem trong ứng dụng.

Phần tiếp theo cần xác minh: callback load hoàn tất SoundPool, đồng bộ khi đổi nhạc cụ nhiều lần và nội dung writer WAV. Chưa có phép thử âm thanh hoặc kiểm tra thư mục trên điện thoại.
