# Bộ lập lệnh STSPut

ImportedStsPlan dựng thứ tự lệnh theo Music.STSPut từ nguồn APK: Master Tempo 222, System 223, STSFX 224, ChordType 225; từng track đổi Record, Instrument 62, System 63, Pedal 11/12, Mode 61 và STSFX 64. Track.id ở đây là vị trí track trong fTracksMain gốc (1 trở lên), không phải group ID hoặc instrument ID.

Giữ mặc định 21 cờ fSTSChanges theo Music constructor. Các cờ instrument/system chỉ phát lệnh khi tham chiếu thay đổi; Mode/Instrument có ID -1 để giữ cấu hình theo điều kiện gốc. STSFX track chỉ cho kind 0/1 và bốn track đầu. FX Master vẫn phát khi các cờ khác tắt, vì các cờ FX được xét ở handler 224. Master null đại diện slot Master không Enable và trả kế hoạch rỗng.

Kiểm thử ImportedStsPlanTest xác nhận thứ tự mặc định, event ID đối chiếu TrackEvent.java, cờ toàn cục, nhánh giữ cấu hình, track không phải keyboard và thiếu Master. Test này cùng ImportedStsIntegrationTest đạt, assembleDebug đạt.

Đây là bộ lập lệnh đã có mã và kiểm thử, CHƯA nối vào audio engine. Chưa có executor đa Track, chưa đổi cơ chế chọn STS đang áp một voice, chưa có UI mặt nạ. Vì vậy chưa tuyên bố STSPut đa Track hoạt động trong ứng dụng. Bước tích hợp phải chuẩn bị tất cả PCM trước rồi commit cả nhóm, không áp tuần tự để tránh trạng thái nửa cũ nửa mới.

## Chuẩn bị nhóm STS

ImportedStsBatch.resolve đối chiếu Tracks.GroupAdd: fTracksMain là danh sách Group 0. Giữ cả vị trí trong main list và index gốc trong SET, loại track rhythm thuộc group khác; yêu cầu Master là track index 0 và slot bật/không mơ hồ trên từng track. Bộ lập lệnh nhận đúng vị trí main thay vì index toàn SET.

prepareFull chuẩn bị tiếng cho tối đa bốn track đầu kind 0/1 qua ImportedStsLoader, trả một kết quả nhóm duy nhất sau khi nạp xong. Chưa thay bất cứ engine state nào trong quá trình chuẩn bị. Các track khác vẫn có trong selection/actions. Đây là chuẩn bị snapshot đầy đủ, chưa phải áp change-mask dựa trên state hiện tại.

ImportedStsBatchTest xác nhận rhythm track xen giữa không làm lệch vị trí main, hai voice nạp PCM đúng, và thiếu slot ở track cuối khiến toàn bộ preparation thất bại. Test batch/plan và assembleDebug đạt. CHƯA có commit nhóm vào mixer; không cài bản này vì chưa thay hành vi ứng dụng. Cần thêm ngân sách bộ nhớ chung, mixer multi-part, xử lý mặt nạ với state hiện hành và commit nhóm trước khi tích hợp UI.

## Mixer nhiều part

ImportedKeyboardParts quản lý tối đa bốn part riêng: mỗi note giữ Playback, sound, gain velocity và pedal của part. renderInto đưa từng voice vào ImportedKeyboardMixer chung để gain/pan/FX theo sound và Music FX chỉ được xử lý một lần bởi caller. Thay part chỉ ảnh hưởng note-on tiếp theo, giữ nguyên Playback/FX bus của voice đang ngân. Note-off giữ riêng theo pedal, pedal-off nhả nốt đang đợi. Giới hạn 512 voice và kiểm tra part ID trùng/ngoài 1..4 trước thay cấu hình. reset xóa nốt, caller vẫn quản lý reset FX mixer.

Prepared.keyboardParts chuyển kết quả batch sang part đúng vị trí với pedal từ snapshot. Test xác nhận tổng gain hai part, sustain độc lập, thay tiếng giữ nốt cũ, batch lỗi không thay cấu hình và reset. Test parts/mixer/batch và assembleDebug đạt. Đây là module đã nối được với kết quả prepare, CHƯA nối AudioEngine note routing/audio-loop/UI. Chưa cài bản vì chưa thay hành vi sản phẩm. Pressure/bend đa part, vùng phím, commit qua audio-thread, mặt nạ và ngân sách bộ nhớ chung vẫn cần tích hợp.

## Đường lệnh audio-thread

ImportedKeyboardPartQueue nối vào AudioEngine.renderBlock trước finishFrame của mixer chung. applyPreparedStsBatch kiểm tra file/index hiện hành, đưa toàn bộ part đã nạp vào một lệnh replace; khi đường đa part được bật, keyboardNoteOn/Off gửi lệnh vào hàng đợi. Chỉ audio thread thay Playback và danh sách part. Chọn STS một voice hoặc đổi SET xóa đường đa part. Lệnh replace giữ live voice cũ theo chính sách của ImportedKeyboardParts, clear xóa nốt và part. Đây là đường full snapshot, chưa thực thi Action plan hay change masks.

Test queue xác nhận thứ tự replace → note-on → replace → note-on, nhả nốt, clear, sao chép list và từ chối part lỗi trước enqueue. Test queue/parts/mixer/batch và assembleDebug đạt. CHƯA bật qua giao diện nên ứng dụng vẫn chọn STS một voice; chưa cài bản này. Vẫn thiếu pedal live, pressure/bend/MIDI recording đa part, vùng phím, tránh state cũ khi chọn voice thường, ngân sách PCM chung và commit đồng bộ Master/FX/chord trước khi có thể công bố STS đa Track dùng được.
