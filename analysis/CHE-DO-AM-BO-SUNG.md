# Chuẩn bị âm bổ sung và nốt ghi sẵn

Cập nhật: [đối chiếu DEX đã giải quyết điều kiện mâu thuẫn và xác định mặc định Activity](XAC-MINH-DEX.md). Các ghi chú “chưa xác định” dưới đây ghi lại giới hạn của bước đọc Java trước đó; kết quả mới nằm trong báo cáo DEX.

Phân tích tĩnh, không tạo WAV hoặc chạy SoundPool thực tế. Bằng chứng: `latency-instruments.json`, `fallback-write.json` (nhánh mặc định của `zd1.g`), `soundpool-load-recorded.json`, `read-setting.json`, `s2-run.json`.

## Mode 3: Play an additional sound

`Music.lowLatencyInstruments(...)` kiểm tra `fLowLatencyMethod`. Với giá trị `3`, nó gửi `new zd1((byte)17)` qua dispatcher nền tảng.

Trong nhánh tương ứng của `defpackage.zd1.g`, mã duyệt chỉ số `2..8`, dùng `SoundPool.load(context, (index - 2) + R.raw.c3, 1)` để nạp bảy tài nguyên âm. Nếu slot đã tồn tại, nó unload sound cũ trước khi thay. Đây là bằng chứng chuẩn bị bộ âm tài nguyên riêng, không phải mode callback OpenSL.

Chưa đọc raw resource ID để chứng minh nội dung và cao độ của từng file âm; tên `c3` chỉ là tên tài nguyên quan sát được.

## Mode 4: Play recorded note

Với giá trị `4`, `lowLatencyInstruments` khởi động thread Java, duyệt `i3 = 3..9`. Mỗi lượt:

1. Tạo tên số ngẫu nhiên và đường dẫn `.wav` trong thư mục caller truyền vào.
2. Gọi `ExportKeyToAudioFile(path, 1, i, 0, i3, instrumentArray, 0, 1)`.
3. Gửi đường dẫn và chỉ số `i3 - 3` bằng đối tượng `r03` qua dispatcher.

`defpackage.r03.g` cộng thêm 2 vào chỉ số, rồi gọi `SoundPool.load(path, 1)`. Nếu slot đã có âm, nó unload và thay ID, đồng thời lưu đường dẫn trong danh sách. Như vậy slot đầu ra cũng là `2..8`, phù hợp nhóm slot mode 3 sử dụng.

Chưa đọc đầy đủ `ExportKeyToAudioFile` để ánh xạ chính xác từng tham số và độ dài nốt. Chưa xác minh callback báo load xong, xử lý đổi nhạc cụ đồng thời hoặc xóa file WAV; không được suy ra file tạm đã được dọn chỉ từ đoạn này.

## Từ âm đã nạp đến thao tác nhấn

Trong `Channels.OpenKey` có lời gọi `SoundPool.play` với gain trái/phải và playback rate tính từ `KeyToPitchEx`. Tuy nhiên Java do JADX tạo có điều kiện `i24 > 2` bao quanh nhánh lại kiểm tra `i24 == 4`, cùng các cảnh báo suy luận kiểu và khối lặp. Không dùng điều kiện suy dịch đó để kết luận mode 3/4 có phát hoặc không phát.

Đã xác nhận đường chuẩn bị và điểm gọi play tồn tại. Còn cần đối chiếu smali/raw DEX tại các nhánh này để nối điều kiện chính xác. Đây cũng là lý do chưa thể coi cơ chế bổ sung là bản phát thay thế hoàn toàn cho voice của mixer.

## Cài đặt thiếu khóa và giá trị mặc định

`MainActivity.r1(default, key)` gọi `s1` rồi parse chuỗi thành int. `s1`:

- Nếu kho preference chưa có khóa, đưa chuỗi default vào editor và đặt `W4 = true`.
- Trả về `k0.getString(key, default)`.
- Cuối `O0`, nếu `W4` bật thì gọi `m0.commit()`, rồi tắt cờ.

Vì vậy đã xác nhận cơ chế lưu bổ sung khóa chưa có, nhưng chưa xác nhận default số cụ thể: nó được truyền từ trạng thái field hiện tại. REA trả `body_status` không có thân mã cho constructor MainActivity, không đủ cơ sở kết luận constructor là native/abstract hoặc hoàn toàn không khởi tạo field. Cần kiểm tra DEX thay vì diễn giải câu chú thích tự động của decompiler.

## Khởi động lại OpenSL

Trong `defpackage.s2.run` có hai nhánh kiểm tra ứng dụng còn hoạt động, mode là `1/2`, âm thanh đang active nhưng `fIsOpenSL == false`, rồi dừng, chờ và gọi lại `OutDeviceStart`. Đây là các đường thử khởi động lại quan sát được; chưa xác định tần suất kích hoạt hoặc chứng minh hệ thống sẽ phục hồi được trên mọi thiết bị.

## Công cụ

`run-rea.ps1` đã đổi từ gọi `npx.cmd` sang chạy `npx-cli.js` qua Node để tránh ký tự `<init>` bị CMD hiểu thành chuyển hướng. Các lệnh thông thường và constructor Music đã chạy hoàn tất; kết quả constructor không được coi là bằng chứng đầy đủ cho giá trị khởi tạo.

Phần cần làm tiếp: đọc raw DEX để xác minh default của `R1` và điều kiện play SoundPool; tìm nơi gọi `lowLatencyInstruments` để xác định khi nào bộ âm được chuẩn bị lại.
