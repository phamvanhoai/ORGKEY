# Thiết lập hợp âm hai ngón

Trang Chord có ba chế độ FINGERED / ONE KEY / TWO KEYS. EDIT TWO-KEY CHORDS cho chọn hai pitch class khác nhau, rồi ASSIGN một hợp âm từ thư viện. CLEAR THIS PAIR chỉ xóa cặp đang chọn. Đây là nội dung trong trang LCD InstrumentPage hiện có, không thêm modal.

ChordDualSettings lưu các cặp mask → chord mask vào preferences orgx-settings/chordDualMappings, riêng với file SET. Engine sao chép bảng, cập nhật nhận hợp âm ngay, giữ bảng khi đổi mode hoặc áp dụng STS. Khởi động đọc bảng trước khi khôi phục STS; STS được khôi phục thành công quyết định ChordType, nếu không có/khôi phục thất bại thì dùng chế độ người dùng đã lưu. STS được chọn sau đó vẫn có quyền đổi chế độ như snapshot gốc. ONE KEY dùng ChordSingles của STS gần nhất hoặc bảng mặc định gốc.

Kiểm thử ChordDualSettingsTest xác nhận serialize → restore → hai phím nhận hợp âm, xóa cặp, bỏ qua preference hỏng và chặn mask không hợp lệ. ReferenceChordInputTest, KeyboardChordInputTest, ImportedStsLoaderTest, ImportedStsIntegrationTest và build debug đạt. Đã cài và mở bản mới trên thiết bị 88d75c04. Chưa thực hiện toàn bộ thao tác gán/đóng/mở lại qua giao diện thiết bị; không coi đây là kiểm chứng UI hoặc âm thanh tương đương APK.

Giới hạn: định dạng preference riêng ORGX, chưa nhập preference user_chord_dual từ APK. Chưa có import/export cấu hình người dùng, chưa hoàn thiện STS change masks/đa Track. Không sửa file SET.

Kiểm tra STS trước commit: ImportedStsChordSettings xác nhận ChordType 0..2 và bảng 12 mask hợp lệ cho cả chế độ 1 lẫn 2. Chế độ 0 giữ bảng hiện hành; chế độ khác 0 sao chép bảng từ STS theo nhánh gốc. Cấu hình được kiểm tra trong loader và trước khi thay voice/pedal ở applyPreparedSts, tránh bảng dual lỗi làm hỏng lần chọn ONE KEY sau đó. Test riêng, loader, STS trên SET cục bộ và bàn phím đạt; build/cài bản mới thành công. ChordType ngoài phạm vi hỗ trợ bị báo lỗi thay vì ngầm dùng fingered.
