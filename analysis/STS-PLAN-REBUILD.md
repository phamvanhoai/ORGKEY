# Bộ lập lệnh STSPut

ImportedStsPlan dựng thứ tự lệnh theo Music.STSPut từ nguồn APK: Master Tempo 222, System 223, STSFX 224, ChordType 225; từng track đổi Record, Instrument 62, System 63, Pedal 11/12, Mode 61 và STSFX 64. Track.id ở đây là vị trí track trong fTracksMain gốc (1 trở lên), không phải group ID hoặc instrument ID.

Giữ mặc định 21 cờ fSTSChanges theo Music constructor. Các cờ instrument/system chỉ phát lệnh khi tham chiếu thay đổi; Mode/Instrument có ID -1 để giữ cấu hình theo điều kiện gốc. STSFX track chỉ cho kind 0/1 và bốn track đầu. FX Master vẫn phát khi các cờ khác tắt, vì các cờ FX được xét ở handler 224. Master null đại diện slot Master không Enable và trả kế hoạch rỗng.

Kiểm thử ImportedStsPlanTest xác nhận thứ tự mặc định, event ID đối chiếu TrackEvent.java, cờ toàn cục, nhánh giữ cấu hình, track không phải keyboard và thiếu Master. Test này cùng ImportedStsIntegrationTest đạt, assembleDebug đạt.

Đây là bộ lập lệnh đã có mã và kiểm thử, CHƯA nối vào audio engine. Chưa có executor đa Track, chưa đổi cơ chế chọn STS đang áp một voice, chưa có UI mặt nạ. Vì vậy chưa tuyên bố STSPut đa Track hoạt động trong ứng dụng. Bước tích hợp phải chuẩn bị tất cả PCM trước rồi commit cả nhóm, không áp tuần tự để tránh trạng thái nửa cũ nửa mới.
