# Nhận hợp âm nhiều ngón và hai ngón

Đối chiếu `Tracks.ChordGet`, `Tracks.ChordFind`, `Music` constructor và `MainActivity.O0` trong nguồn giải mã APK:

- Phím được duyệt theo thứ tự MIDI tăng dần, không theo thứ tự nhấn.
- Nốt lặp ở octave có bảy quy tắc đặc biệt. Ví dụ C–G–C có mask 129 trước C thứ hai, chuyển thành 145 (C trưởng). ReferenceChordInput giữ đủ bảy cặp mask trước/sau của nguồn.
- ChordFind dùng hợp âm con nhiều nốt nhất và thứ tự bảng khi hòa điểm, không ưu tiên bass. ORGX nay dùng quy tắc này cả khi nhận nhiều ngón, thay matcher exact/bass cũ.
- ChordType=2 chỉ tra ChordDuals khi mask sau xử lý có đúng hai pitch class và hợp âm đích được ChordFind nhận. Bảng mặc định được Music constructor xóa về 0; MainActivity nạp lại từ thiết lập người dùng. Đây không phải bảng 12 ChordSingles của SET.

KeyboardChordInput nhận ChordType đầy đủ từ STS, có đường cấu hình bảng dual mask và sao chép dữ liệu riêng. Nhả phím xét lại các phím còn giữ; nếu không nhận được thì giữ hợp âm cuối. Kiểm thử xác nhận bảy shortcut, thứ tự nhấn đảo, nốt MIDI trùng không thành octave, dual hợp lệ/không hợp lệ, bảng mặc định rỗng và nhả nốt C7 về C trưởng.

Kiểm chứng: ReferenceChordInputTest, ReferenceChordMatcherTest, KeyboardChordInputTest, ImportedStsLoaderTest và ImportedStsIntegrationTest đạt; assembleDebug thành công. Đã cài/mở bản mới trên thiết bị 88d75c04.

Giới hạn: chưa có giao diện chỉnh/lưu ChordDuals, chưa nhập thiết lập ChordDuals riêng từ APK. Vì vậy STS ChordType=2 hiện dùng bảng dual rỗng như mặc định gốc; hai nốt thường không tự suy thành hợp âm. Các octave shortcut vẫn chạy theo ChordGet. Chưa đối chiếu thao tác/âm thanh APK trên thiết bị; chưa hoàn thiện STS change masks, STSPut đa Track và chord-control trong playback.
