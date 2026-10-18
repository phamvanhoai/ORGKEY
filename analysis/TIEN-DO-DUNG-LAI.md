# ORGX: phạm vi đã dựng và phần còn thiếu

Đây là bảng đối chiếu mã hiện tại, không phải chứng nhận tương đương APK gốc. Không quy đổi số kiểm thử hoặc số file sang phần trăm hoàn thành.

| Cụm chức năng | Đã có trong ORGX | Còn thiếu / cần kiểm chứng |
|---|---|---|
| Đọc SET | Layout Music 1.2.8/1.4.6, instrument 1.2.8/1.4.2/1.4.6; PCM, group/track, mode/mix, system, header FX | Layout khác, STS thực thi, script/DNC; đọc đủ byte không đồng nghĩa dùng đủ dữ liệu |
| Style | Nhóm Intro/Main/Fill/Break/Ending, queue/jump, Auto Fill, tiếp tục khi đổi style, loop/release, đuôi Ending | So cùng thao tác với APK gốc; biến thể hợp âm ở layout mới, đầy đủ event điều khiển |
| Voice/sample | Sample mapping, pitch modes, ADS/release, mode layers, tune, tuning 24 phím, pan note | DNC connection/articulation, aftertouch đổi sound, pedal modes, thay pitch lúc nốt ngân |
| FX | DSP 1/2/4/6/7/8/9/10/16/17/20/21/23; module 29 dùng native gốc | 15 Pitch Shift, 31 Band Booster; lượng tử PCM, gain/threshold và bản thu đối chiếu |
| Sự kiện track | 1/2 note-off/on, 13/14 mute/unmute, 44/45 channel volume/pan | Các event khác vẫn bị từ chối; 101 embedded wave chưa có |
| Giao diện | Console và các trang chọn style/voice trong LCD | Chưa có bằng chứng từng màn hình/thao tác giống 100%; không coi screenshot/build là kiểm chứng chức năng |
| Ghi/xuất/MIDI/project | Có lớp triển khai trong project | Cần kiểm tra end-to-end và bảng khác biệt với gốc; không mặc định hoàn thành |
| Độ ổn định | Kiểm thử JVM, một số instrumentation native Reverb, cài/khởi động thiết bị | Chưa đo polyphony/latency/CPU/bộ nhớ dài hạn, chưa so toàn bộ bản thu |

## Cụm tiếp theo: voice biểu cảm

Ưu tiên đọc trọn đường `Channels.OpenKey`, `ChangeKey`, DNC Effects và mode Aftertouch trước khi thêm hành vi. Tách rõ:

1. Áp aftertouch lên nốt đang ngân và lựa chọn sound/layer theo mode.
2. DNC connection, articulation, pedal và các điều kiện kích hoạt.
3. Script runtime: ngôn ngữ, biến/event, giới hạn thực thi và tác động lên engine.

Một cụm chỉ được ghi hoàn thành khi có đường phát/UI sử dụng được, kiểm thử hành vi và đối chiếu APK gốc. Nếu mới dựng adapter hoặc đọc trường dữ liệu thì ghi đúng mức đó. Những lỗi tái hiện được vẫn phải sửa, nhưng không dùng các bản vá nhỏ để thay cho tiến độ cụm này.

## Cách kiểm tra lại danh sách đã triển khai

Chạy `py -3 analysis/rebuild_status.py` để tạo [REBUILD-STATUS.json](REBUILD-STATUS.json) từ khai báo support trong mã. Script dừng nếu không còn nhận diện được khai báo; không tự suy đoán hỗ trợ. Báo cáo này không xác minh DSP hay hành vi trên thiết bị.

Lượt rà soát này chưa thêm DNC/aftertouch/script vào ứng dụng và không cần cài APK chỉ vì cập nhật tài liệu.
