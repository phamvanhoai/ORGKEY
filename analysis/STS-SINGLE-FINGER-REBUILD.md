# STS ChordSingles: hợp âm một ngón

Nguồn đối chiếu: `Tracks.ChordGet()` duyệt phím MIDI từ 0 tới 127; khi Music.fChordType=1 trả Music.ChordSingles[key % 12] của phím đầu tiên đang giữ. Track.Play nhánh áp ChordType sao chép 12 mục ChordSingle từ STS khi ChordType khác 0. Đây là mask 12 pitch class, không phải chỉ số trong danh sách hợp âm.

ORGX áp bảng ChordSingle khi `AudioEngine.applyPreparedSts` thành công và ChordType=1. `KeyboardChordInput` dùng mask của phím thấp nhất đang giữ để tìm hợp âm trong thư viện đang mở. Đổi STS nhận lại hợp âm từ phím đang giữ; nhả phím thấp nhất nhận phím tiếp theo; nhả hết giữ hợp âm cuối. Mode khác 1 dùng đường nhận hợp âm nhiều ngón hiện tại. Việc nhận này hoạt động khi LEFT CHORD bật và AUTO không ghi đè lựa chọn hợp âm.

Đổi SET xóa cấu hình một ngón của SET cũ. Bảng được sao chép để tránh thay đổi dữ liệu bên ngoài làm biến đổi cấu hình đang dùng. Loader kiểm tra bảng có 12 mask 0..4095 trước khi chuẩn bị snapshot; lỗi không thay thế voice hiện tại. Không chỉnh file SET.

Kiểm chứng: KeyboardChordInputTest gồm các ca bảng tùy biến, octave, nhiều phím, release, đổi snapshot khi đang giữ phím, trở về nhiều ngón và bảng lỗi; cùng ImportedStsLoaderTest và ImportedStsIntegrationTest đều đạt. AssembleDebug thành công, cài và mở bản mới trên thiết bị 88d75c04.

Giới hạn: ChordType=2 dùng ChordDuals gốc chưa dựng; không khẳng định tương đương chế độ hai ngón. Chưa hoàn thiện fSTSChanges và STSPut đa Track. Mask không tìm thấy trong thư viện giữ hợp âm cuối; chưa port toàn bộ ChordFind fallback của APK. Chưa thử thao tác một ngón với SET thật trên điện thoại hoặc đối chiếu âm thanh APK.
