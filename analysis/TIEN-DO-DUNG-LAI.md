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

Aftertouch fallback đã được nối end-to-end theo `Track` gốc: áp lực dương đầu tiên chọn sound Aftertouch, giữ velocity ban đầu và không khởi động lại ở các pressure tiếp theo. ScriptText của mode nay được giữ lại khi đọc SET; DNC và runtime script tùy biến vẫn cần bộ thực thi lệnh.
Key Connection đã bổ sung điều kiện restart AutoRelease/loop theo low nibble của `Channels._Connection`; test PCM kiểm tra mode 0/1/3 và low mode mở voice từ loop point.

## Script mode: phạm vi thực thi hiện tại

Đã trích 24 script tham chiếu từ `assets/data.zip`. Bộ thực thi hiện xử lý touch dạng chuỗi lệnh `play`/`return`, số nguyên và biến cộng/trừ hằng số. Playback dùng chung cho voice trực tiếp và STS, hỗ trợ `key`, `keymove`, `octavemove`, `volume`, `pan`, `tune`, `pitchmove`, `connectkey`, `connectspeed`. Bốn script Octave/Legato Lower/Upper gốc đã được đưa qua test PCM; pitchmove giữ hiệu lực khi bend live thay đổi.

Đã truyền context velocity cho voice trực tiếp, STS, Mix layer và sound Aftertouch. Lệnh play có velocity dùng tỷ lệ so với lực đánh đầu vào vì mixer đã áp lực đánh ở ngoài Playback; test giữ nguyên velocity và override giá trị đều đạt. Chưa đối chiếu lượng tử hóa lực đánh với bản thu APK.

Script play đã nối autorelease và attack/decay/sustain/release xuống envelope từng Mix layer; giá trị không chỉ định giữ envelope gốc. Test hai voice cùng instrument xác nhận release một voice không đổi state voice kia. Bộ kiểm thử đầy đủ 437 test đạt, build/cài APK thành công; chưa đối chiếu bản thu envelope APK gốc.

Chưa có biến trạng thái/main, if/else, timer, script aftertouch/release và exit đổi mode. Lựa chọn sound tùy lệnh và fallback khi lỗi vẫn cần đối chiếu/hoàn thiện. Không coi toàn bộ DNC hoặc script Legato đã tương đương APK. Những script ngoài tập con hỗ trợ hiện dùng fallback.

Cập nhật session: đã nối main khởi tạo một lần, biến trạng thái giữa các touch, phép gán, so sánh và điều kiện một lệnh với &&/||/!. Trạng thái chỉ commit khi kế hoạch âm thanh hợp lệ. Chưa có block if/else, timer, effect assignment hoặc release exit đổi mode; ghi chú trước về thiếu main/trạng thái đã được thay thế ở phạm vi này. Kiểm thử toàn bộ 447 test đạt; APK đã build, cài và mở thành công.

Cập nhật event: release cập nhật biến một lần khi note-off; aftertouch nhận velocity áp lực và keyvelocity ban đầu, xử lý script trước fallback. Return tính biểu thức và coi mọi kết quả khác 0 là handled. Release chưa hỗ trợ chặn nhả/phát nốt/exit; aftertouch chưa hỗ trợ play hoặc effect assignment. Toàn bộ 450 test đạt, APK mới đã build/cài và nhận lệnh mở MainActivity thành công. Chưa xác minh nghe/so bản thu APK gốc.

Cập nhật điều kiện: đã có block if lồng nhau và if-block/else-block, return trong block kết thúc event. Test PCM xác nhận velocity chọn nhánh gain khác nhau trong voice thật. Chưa có đầy đủ if đơn lồng nhau/else-if, timer và effect assignment. Toàn bộ 453 test đạt; APK đã build/cài và mở thành công.

## Modulation và script: cập nhật phạm vi

Đã thay bộ tách câu lệnh để hỗ trợ điều kiện đơn lồng nhau và else; nhánh touch của Legato from Last gốc đã được kiểm tra. Dynamic Legato (Velocity) và Aftertouch Shake gốc đã chạy qua voice/PCM; script ghi và đọc thuộc tính effect 0..3 ở touch, cập nhật Pitch/Volume Shake trong aftertouch/release. Chưa có timer hoặc exit đổi mode. Time hiện là mili giây từ lúc tạo session, khác epoch double của APK.

Modulation Shake gốc đã chạy qua API modulate. MIDI file giữ CC1, engine gửi theo kênh; STS và voice mới nhận mức đã chọn. CC121 và kết thúc/STOP playback reset modulation. Ghi MIDI giữ mức CC1 trước REC và ghi reset ở cuối file. Bộ nhận controller MIDI phần cứng chưa được nối, chưa thử MIDI thật; không coi API là kết nối thiết bị hoàn chỉnh.

Trang Cài đặt đã có điều khiển DNC từ khai báo slider của tập con script hỗ trợ. Thay đổi qua audio queue, kiểm tra sound hiện hành và giới hạn; chưa xác minh tương đương giao diện gốc hoặc đầy đủ nhóm tiếng STS. Các ghi chú lịch sử phía trên về thiếu gán effect/slider được thay thế trong phạm vi đã nêu ở đây.

## Vòng đời điều khiển DNC

Pedal cập nhật session khi im lặng và truyền hiệu ứng cho nốt mở dưới pedal đang giữ. Các voice dùng chung session không chạy lặp phép gán pedal/modulation cho cùng trạng thái; kết quả dùng lại đọc giá trị Effect hiện hành để tránh ghi đè thay đổi từ aftertouch. Việc chia sẻ session giữa part/kênh vẫn cần đối chiếu với Track/Mode của APK gốc.

Event pitchbend đã nối trước bend mặc định: script xử lý thành công và trả khác 0 thì chặn fallback. Giá trị MIDI gốc trừ 8192 truyền tới nốt đang ngân và nốt mới của STS/voice trực tiếp; trạng thái live và playback được giữ riêng theo kênh. Chưa kiểm chứng controller vật lý hoặc toàn bộ thứ tự event khi nhiều nốt dùng chung session.

Script quá 262144 ký tự hoặc ngoặc lồng quá 64 cấp dùng fallback để tránh tràn stack; đây là giới hạn ORGX. Runtime vẫn dùng số nguyên và elapsed time, chưa tương đương double/epoch của APK. Timer, lịch thực thi, exit đổi mode và các lệnh phát âm trong aftertouch/release chưa hoàn chỉnh. Các bản build/test không chứng minh tương đương 100%.

Chuyển sang sound Aftertouch đã truyền raw MIDI bend, modulation đang giữ và pedal đang giữ; các cập nhật modulation/pedal tiếp theo cũng đến voice thay thế. Test PCM/session kiểm tra giá trị giữ trước chuyển sound, reset sau chuyển và chống chạy lặp pedal. Chưa kiểm chứng controller vật lý hoặc so âm thanh với APK gốc; đây là hoàn thiện vòng đời điều khiển, không phải dựng xong toàn DNC.

Khởi tạo script: loader nạp Effect trước khi tạo session để main đọc được thông số SET; các phép gán Effect trong main được giữ khi cấu hình mode được đồng bộ. Test kiểm tra đọc Speed 65 và gán lại 75 từ biểu thức, cùng trường hợp gán hằng số qua hai thứ tự cấu hình. Main chưa hỗ trợ các lệnh phát âm, timer hoặc đổi mode; không coi các nhánh đó đã dựng.

Legato tái sử dụng: khi StopByRelease tắt, voice còn đuôi release có thể làm nguồn nối. Với sound không AutoRelease, kết nối hủy release cũ trong envelope và giữ vị trí PCM; quyền release script chuyển sang phím mới. Test PCM xác nhận giữ vị trí mẫu và tiếp tục ngân sau thời gian release cũ. Chưa đối chiếu đường cong gain/ADS bằng bản thu APK; timer và exit đổi mode vẫn thiếu.
