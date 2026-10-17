# Kiểm kê SET và mức sử dụng trong ORGX

Kiểm tra ngày 2026-10-17 trên bản SET thử trong `local-set-check/sample.Set`. Công cụ mở file chỉ đọc. SHA-256 trước/sau đều là `bcfb82bcac2aa5547aec4efd16409b0b1b074a4f28e4ae37c2bfa949dc7f928f`. Không sửa SET.

## Kết quả cấu trúc

| Khối | Số lượng / kích thước |
|---|---:|
| File, phiên bản | 294.227.939 byte, 1.2.8 |
| Nhạc cụ | 154 |
| Sound / vùng sample | 158 / 4.102 |
| Mode | 31 |
| System | 1 |
| Track / event | 370 / 23.204 |
| STS được lưu | 112 |
| Group tổng, gồm cả nhóm cha và Pad | 105 |
| FX block / module có mặt | 516 / 149 |
| Wave-bank entries | 348 trên 11.444 slot |
| PCM trong wave bank | 26.552.898 byte |
| PCM inline trong vùng nhạc cụ | 266.417.942 byte |
| Tổng PCM lưu trong file | 292.970.840 byte |

Cả 348 wave-bank entry đều có tham chiếu từ vùng nhạc cụ. Điều này không có nghĩa tất cả phát cùng lúc hoặc mọi nhạc cụ đều được style hiện tại sử dụng. PCM inline không nằm trong bộ đếm wave-bank; thống kê chỉ 26,55 MB trước đây chưa thể hiện toàn bộ âm thanh của SET.

Đã kiểm tra các đoạn byte liên tục, không chồng lấn hoặc bỏ hổng: 795.226 byte đọc theo cấu trúc, 461.865 byte trường/khối bỏ qua có đánh dấu vị trí, 8 byte metadata bảo vệ chỉ tính độ dài, 292.970.840 byte PCM định vị nhưng không giải mã từng mẫu trong lượt audit này. Điểm kết thúc parser bằng kích thước file: **0 byte đuôi chưa phân loại**. Đây là kiểm kê cấu trúc, không phải chứng nhận đã hiểu ý nghĩa hoặc sử dụng mọi byte.

## Đối chiếu với ứng dụng

| Dữ liệu SET | Trạng thái ORGX hiện tại | Hệ quả |
|---|---|---|
| PCM mono/stereo, root note, rate, gain/pan/tune vùng | Đọc; áp dụng cho vùng sample được loader chấp nhận | Không phải mọi sound đều nạp được |
| 223 vùng có Start khác 0 | Đọc nhưng loader chặn | Mất tương thích với các sound/track cần vùng đó |
| PitchMode | 116 sound mode 0, 26 mode 2, 16 mode 1; mới hỗ trợ 0/2 | Sound dùng mode 1 chưa tương thích |
| Instrument type | 108 type 0, 29 type 1, 17 type 2; loader chỉ hỗ trợ 0/1 | Type 2 chưa phát như engine gốc |
| Loop vùng | 3.411 loop 0, 690 loop 2, 1 loop 3 | Đã dùng ở mức sample; chưa chứng minh mọi hành vi envelope/release gốc |
| Envelope attack/decay/sustain/release, auto-release | Đã nối vào voice bàn phím và track SET trong bản sửa sau audit; ADS legacy chuẩn hóa theo reader gốc | Nội suy theo sample; chưa tái tạo mọi block tick, pedal mode, ForceLoop và DNC gốc |
| Mode, mix layer, aftertouch, effect, script DNC | Parser đi qua nhưng không giữ đầy đủ model/ngữ nghĩa; playback style dùng sound đầu | Thiếu articulation, layer và điều kiện chọn sound |
| 516 FX block, 149 module | Đọc cấu trúc để bỏ qua; không tái tạo chuỗi FX của SET | FX Kotlin của ORGX không thay thế tương đương FX trong SET |
| System/tuning | 1 object kiểm kê, payload hiện bỏ qua | Track có tham chiếu system nhưng chưa áp dụng tuning/system gốc |
| Track instrument, mute, chord source, note on/off | Đọc và dùng trong các đường style tương thích | Các track ngoài chế độ chọn có thể không phát |
| Track kind, MIDI routing, bank/program, solo/record | Audit đọc thêm; model/runtime ORGX chưa giữ và áp dụng đủ | Không thể coi đây là phần không cần thiết |
| Channel state, mix, effect, pedal, key range | Nhiều khối bỏ qua theo layout | Gain/pan/FX/phạm vi và đáp ứng track có thể sai |
| 112 STS | Chỉ đi qua layout, không áp dụng khi chuyển/chọn | Chọn style không khôi phục đầy đủ keyboard set và tham số |
| Group Type/Loop/Limit/JumpGroup/meter/tempo | Đọc; dùng trong session với giới hạn đã ghi ở STYLE-BUTTON-AUDIT | Có chuyển nhịp/đích nhưng chưa đầy đủ state machine gốc |
| Group chord variants, color, shortcut, STSIndex, group FX | Một số audit được, app bỏ qua/chưa áp dụng | Chưa hoàn chỉnh biến thể hợp âm, preset và điều khiển |
| Pad groups 96–99, nhóm mồ côi | Có trong index, không nằm trong danh sách 7 style | Không được bỏ khỏi kế hoạch hỗ trợ SET chỉ vì không hiện ở trang style |
| Metadata bảo vệ | Giữ nguyên bytes trong bản SET, không xuất nội dung | Không phải dữ liệu audio; không suy thành lỗi password khi không cần |
| Trường reserved | Định vị theo reader gốc; không tự diễn giải | Không khẳng định mọi byte reserved đều là chức năng đang dùng |

149 FX module gồm type 6:46, 29:50, 20:1, 8:35, 2:14, 17:2, 7:1. Tên/chức năng từng type cần đối chiếu lớp FX gốc; báo cáo không tự gán tên dựa trên số ID.

Các event thực tế có 11.736 note-off (1) và 11.468 note-on (2); không event tune khác 0 trong file thử. Không thể suy từ đó rằng mọi SET khác cũng không cần controller/tune hoặc embedded-wave events.

Toàn bộ 105 group có meter lưu 0/0. Mã gốc dùng meter fallback của Music trong trường hợp này; không được hiển thị 0/0 như một nhịp có nghĩa hoặc mặc định mọi SET là 4/4. Đích nhảy trong toàn bộ group: 53 bằng 0, 37 âm, 15 dương; phải xét cả lệnh từ UI, không chỉ JumpGroup.

Trong 15 đích dương có một đích `1065724091` từ phần section 17 không xuất hiện trong danh sách group của file. Đây là tham chiếu chưa giải quyết trong lượt audit, không tự kết luận SET bị hỏng: đích có thể bị ghi đè bởi Jump từ UI hoặc có đường fallback trong engine gốc. Cả 370 tham chiếu instrument của track đều nằm trong khoảng 154 instrument; tất cả track tham chiếu system index 0.

## Bằng chứng và cách chạy lại

- `audit_set.py`: parser audit độc lập, bám layout ORGX và đối chiếu reader gốc; có kiểm tra biên, wave references và byte accounting.
- `SET-AUDIT-SUMMARY.json`: tổng số liệu và hash, không chứa âm thanh hay nội dung script/password.
- `local-set-check/full-set-audit.json`: chi tiết instrument/sound/region/mode, track/STS/FX, group và vị trí những vùng chưa triển khai. Báo cáo riêng tại máy, không đưa binary SET vào git.
- `source/music-read-stream.java`, `source/instrument-read-stream.java`, `source/track-read-stream.java`, `source/group-read-complete.java`, `source/fx-block-read.java`, `source/fx-module-read.java`: reader tham chiếu đã trích từ APK.

```powershell
py -3 analysis/audit_set.py analysis/local-set-check/sample.Set --output analysis/local-set-check/full-set-audit.json
```

Đã chạy trên toàn file thật, kiểm tra hash không thay đổi; thử file cắt 4.096 byte bị từ chối ngay ở wave-bank thay vì coi là import hợp lệ. Chưa audit byte-level tất cả file SET khác hoặc toàn bộ phiên bản định dạng.

## Kết luận triển khai

SET không phải chỉ là danh sách tên style và voice. Muốn tái tạo đúng cần giữ các cấu hình gắn với nó và áp dụng vào engine theo đường đọc gốc. ORGX hiện chưa dùng đủ dữ liệu trong SET. Thứ tự sửa nên là model đầy đủ cho mode/channel/STS/system/FX, sau đó port cách áp dụng và đối chiếu âm thanh/tác vụ; không tiếp tục dùng sample đầu hoặc hiệu ứng mặc định để tuyên bố tương thích toàn SET.

Sửa sau audit: thêm `ImportedEnvelope` cho sound SET và dùng trên keyboard/style; attack/decay và auto-release tính theo đơn vị 100 ms từ Channels.Process, release note-off theo nhánh pedal mode 0 (10 ms mỗi đơn vị, giới hạn 20), release 0 dùng 0,1 theo Channel.Open. Legacy 1.2.8 bỏ ADS lưu và dùng 0/0/100 như Instrument.ReadFromStream gốc. Chưa port các pedal mode khác hoặc ForceLoop; quá trình gain nội suy theo sample thay vì tái tạo tick 10 ms chính xác. Không nới điều kiện Start khác 0 vì chưa chứng minh trường này là sample offset trong đường phát gốc.
