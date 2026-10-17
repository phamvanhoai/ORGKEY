# Đối chiếu hành vi nút style với APK gốc

Ngày đối chiếu: 2026-10-17. Đây là kiểm tra mã tĩnh của APK đã lưu trong dự án, chưa phải xác nhận thao tác chạy trên APK gốc.

## Kết luận

Các nút ORGX hiện chưa tương đương APK gốc. Quy tắc chờ hết phần, Intro/Fill/Break trở về Main và Ending luôn dừng là logic đã tự đơn giản hóa trong `ImportedStyleSession`, không phải bản port đầy đủ. Kiểm thử của session hiện xác nhận logic đó, không phải kiểm thử đối chiếu với bộ điều khiển gốc.

## Bằng chứng từ mã gốc

- `source/GroupPlayNext.java`: chỉ xử lý chuyển ở cửa sổ `time % rhythmScale <= tempo * 10 / 120`. Đọc hàng đợi `List`; nếu phần không loop kết thúc và hàng đợi rỗng thì ưu tiên `Jump`, sau đó xét `JumpGroup`: âm trở về `LastLoop`, dương tìm group tương ứng, bằng 0 dừng. Vì vậy không thể suy hành vi chỉ từ nhãn Intro/Fill/Ending.
- `source/group-play-add.java`: xếp hàng phần, bỏ yêu cầu lặp lại phần loop giống phần cuối hàng đợi/đang chạy; hàng đợi có giới hạn. Khác với một biến pending duy nhất của ORGX.
- `source/group-play-jump.java`: đặt đích Jump riêng, không phải thêm vào hàng đợi thông thường.
- `source/CalcRhythmScale.java`: tính nhịp chuyển từ MeterX/MeterY, cờ Limit, độ dài LimitEnd–LimitStart và thiết lập fGroupPlayChangeMethod. Chế độ mặc định 0 không đồng nghĩa chờ hết phần. Với Limit 4/4 dài chia hết 2000 reference units, nhịp chuyển mặc định có thể là 1000 reference units. Không được đồng nhất nhịp chuyển với BPM hoặc số frame của cả phần.
- `source/GroupPlayStart.java`: căn vị trí bắt đầu theo rhythmScale và fGroupPlayChangeMethod, có điều chỉnh LimitStart. Đổi phần không phải lúc nào cũng bắt đầu lại từ sample/nốt đầu.
- `source/group-read-complete.java`: TrackGroup có Type, Loop, Limit, LimitStart/End, JumpGroup, MeterX/Y. Phiên bản trước 1.4.8 lấy nhịp từ hai nibble của byte MeterY. Parser ORGX hiện bỏ qua Type/Limit/JumpGroup/meter.
- `source/tracks-play-group.java`: kết thúc track và cửa sổ rhythmScale đều có thể dẫn tới GroupPlayNext; còn phụ thuộc TrackGroup.Type và hàng đợi.
- `source/GroupPlayChord.java`: thay đổi hợp âm có thể cập nhật cả voice đang ngân đủ dài và chọn lại biến thể GroupSection. ORGX mới đổi note-on tiếp theo.

## Những phần chưa được chứng minh

Chưa lần xong caller UI của từng nút để xác định nút nào phát event Add, Jump, tempLoop, hoặc tổ hợp sự kiện; chưa chứng minh quy tắc bấm lại Main/Auto Fill, thao tác nhấn/nhả, chế độ đồng bộ và các lựa chọn cài đặt. Không được suy những hành vi này chỉ từ GroupPlayNext. Tham chiếu caller đang lưu trong `style-button-callers.json`.

## Yêu cầu trước khi coi là sửa đúng

1. Giữ đầy đủ metadata chuyển phần khi đọc SET, không sửa file SET.
2. Lần theo callback UI đến event 201/203/204 và các caller có liên quan; ghi lại chuỗi lệnh cho từng nút và trạng thái.
3. Port riêng hàng đợi, Jump, LastLoop, JumpGroup, nhịp chuyển và căn phase. Tôn trọng thiết lập gốc thay vì một quy tắc cố định.
4. Viết kiểm thử đối chiếu các chuỗi sự kiện đã xác minh, gồm bấm lại Main, Main sang Main, Fill vào Main đích, Break, Intro, Ending và STOP/START.
5. Chạy cùng chuỗi thao tác trên APK gốc và ORGX; so thời điểm và trạng thái chuyển phần trước khi khẳng định tương đương.

Trong lượt đối chiếu này chưa thay đổi hay cài lại mã phát style của ORGX. Không có thay đổi nội dung SET.
