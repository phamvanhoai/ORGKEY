# Sửa tương thích style SET

Kiểm tra trực tiếp bằng bộ đọc ORGX trên SET cục bộ, không chỉnh sửa dữ liệu SET.

Các nguyên nhân trước khi sửa:

- Fill/Break DJ new club và Main 1 DJ AAA dùng instrument Type 2, PitchMode 1, trước đây bị bộ lọc từ chối.
- NHAC TRE, Cha Cha TTV V1 và DJ DDD có sample chứa Start khác 0, trước đây bị chặn toàn bộ phần nhạc.
- Một số Ending không bật Limit, trước đây bị từ chối dù APK gốc hỗ trợ.

Đối chiếu mã gốc:

- `source/open-key.java`: PitchMode 1 lặp dải từ root thấp nhất đến cao nhất; khoảng trống không có section giữ im lặng. Key phát đổi thành key đã ánh xạ, không chuyển cao độ nhiều octave như nearest mapping. Giữ toàn bộ dải khi nạp, tránh việc cắt bớt region làm đổi chu kỳ.
- `source/channel-open.java`: khởi tạo Position = 0. `source/channels-process.java` lấy Position và LoopStart/LoopEnd để đọc PCM; trường InstSec.Start không được dùng làm offset trong đường phát này. ORGX giữ nguyên trường đã đọc và bỏ điều kiện từ chối Start khác 0, không tự đoán offset.
- `source/track-play-next-chord.java`: nếu bật Limit, kết thúc ở LimitEnd; nếu không, chờ hết sự kiện và trạng thái channel cuối. ORGX dùng trạng thái timeline/voice cho phần không bật Limit, thay vì đoán độ dài từ LimitEnd.

`LocalStyleCompatibilityTest` kiểm tra từng phần của từng style có thể nạp session đầy đủ ở chế độ follow chord và chạy các frame âm thanh hữu hạn. Báo cáo chi tiết nằm ở `local-set-check/style-compatibility.txt` (không đưa SET riêng vào Git).

Đây là sửa các đường phát sample/style đã xác định; DNC, FX, STS và biến thể GroupSection vẫn chưa tương đương đầy đủ bản gốc.

## Dữ liệu trộn từng track

Đã đọc `fKind`, `fSolo` thay vì bỏ qua trong `Track.ReadFromStream`; đọc hai trường đầu `b` (volume) và `c` (pan) của FX channel thay vì skip toàn bộ header. Theo `channels-process.java`, pan dương giảm trái, pan âm giảm phải, sau đó nhân volume. ORGX áp dụng hệ số này cho từng track trước khi cộng stereo. Trạng thái Solo chỉ chọn các track solo trong group và vẫn tôn trọng Mute. Kiểm thử PCM riêng xác nhận volume 50/pan 100 giữ nửa mức bên phải và im bên trái; kiểm thử Solo xác nhận không sửa dữ liệu Mute đã đọc.

Việc đọc được `fKind` chưa đồng nghĩa đã mô phỏng mọi chuyển loại nhạc cụ theo kind. Module FX/DNC/STS vẫn còn công việc riêng; không đổi tên giới hạn thành “hỗ trợ đầy đủ”.
