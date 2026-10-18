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

## Pedal và biểu cảm live

Đã nối setSustainEnabled vào pedalAll qua queue, áp cho cả part hiện hành lẫn voice thuộc part cũ còn ngân. Pitch bend live kênh 0 đi qua queue, cập nhật nốt hiện hành và lưu cho note-on sau; clear trả bend về unity. Pressure từ keyboard surface gọi aftertouch từng Playback đa part (chưa thay gain velocity theo pressure). Setter importedInstrument xóa đường đa part khi chọn tiếng thường. Test pedal-off sau thay nhóm và ramp PCM pitch bend/clear đạt; test queue/parts/mixer/batch và assembleDebug đạt.

Chưa kiểm thử aftertouch đa part bằng PCM alternate, chưa nối poly/channel pressure MIDI hoặc ghi MIDI note của đường đa part. Vẫn chưa bật giao diện, chưa cài bản nội bộ này; vùng phím, change masks, Master và ngân sách PCM chung còn thiếu.

## Vùng phím và Record routing

Đối chiếu Tracks.RecordMusic: note-on (2/4) chỉ vào track có fRecord và phím gốc nằm trong [fKeyAreaFromKey, fKeyAreaToKey], bao gồm hai đầu. Note-off (1) bỏ qua điều kiện Record/vùng phím. ImportedKeyboardParts.Part nay giữ enabled/fromKey/toKey và lọc trước keyboardNote transpose, không lọc note-off. Đổi part tắt/vùng mới không làm nốt cũ kẹt.

Prepared.keyboardParts lấy enabled từ STS.Record (đường full snapshot) và vùng phím từ Track trong SET, không suy từ instrument range hoặc ChordSingles. Kiểm thử biên chung hai vùng, part tắt, ngoài vùng, đổi vùng rồi nhả nốt và truyền dữ liệu batch đều đạt; test queue/parts/batch và assembleDebug đạt. Các lượt STS change-mask bỏ qua Record vẫn chưa được hỗ trợ bởi prepareFull; không tuyên bố executor hoàn chỉnh. Vẫn chưa bật đường này qua UI và chưa cài bản nội bộ.

## Trộn thông số theo change mask

ImportedStsChanges.channel đối chiếu Track.Play(64) → z01.e: cờ 13 volume, 14 pan, 15 transpose, 16 octave, 17 release, 18 FX enable/modules. Tune z01.d không được e() chép nên giữ giá trị hiện hành. Trường z01.e được chép cùng volume nhưng parser hiện chưa giữ trường đó; chưa tuyên bố toàn bộ header tương đương. Track merge áp cờ 8 Record, 9 instrument, 10 pedal, 11 mode, 12 system, 19 expression; không đổi ChordType/MIDI vì các trường này có đường xử lý khác.

prepareMasked nhận snapshot hiện hành theo vị trí part, merge trước global pitch/PCM load; thiếu current part báo lỗi, Record tắt trong mask được giữ đúng. Hiện chỉ nhận instrument/mode không đổi hoặc bị mask giữ lại, vì handler 61/62 còn reset/reload riêng chưa dựng. Chưa xử lý cờ Master 0..7/20 tại commit engine. Full snapshot API vẫn riêng, không giả làm mask đầy đủ.

Test từng cờ channel, Tune giữ nguyên, track/chord/MIDI giữ đúng và batch PCM giữ hai volume/Record hiện hành đều đạt; test changes/batch/plan và assembleDebug đạt. Chưa bật UI, chưa cài bản nội bộ.

## Handler instrument/mode trong prepareMasked

ImportedStsChanges.transition thực hiện các action 62/61 theo thứ tự trước track merge. Event 62 đổi instrument hợp lệ (>0 theo Track.Play), ID khác 0 gọi ModeSet(0). Event 61 ID=0 chỉ gán mode, ID khác 0 nạp FX/Effect từ mode. ModeSet không tìm thấy mode trả -1 với channel mặc định và Effect tắt (ORGX biểu diễn bằng list rỗng). Sau đó mới áp các cờ channel/Effect của STS. prepareMasked đã bỏ chặn chung đổi instrument/mode và dùng resolver này.

Test xác nhận ModeSet lấy volume/Tune mode, gán chỉ số trực tiếp giữ channel cũ, mask volume chép STS sau ModeSet nhưng không chép Tune, đổi instrument dùng mode mặc định mới và mode thiếu reset về channel mặc định. Test changes/batch/plan và assembleDebug đạt. Chưa port callback ScriptEvent, toàn bộ z01 header/reserved, hoặc những Effect mặc định tắt dạng record đầy đủ. Direct selection tới mode không tồn tại vẫn được loader từ chối thay vì âm thầm nạp tiếng khác. Chưa bật UI hay commit Master đa part, chưa cài bản nội bộ.

## Chuẩn bị Master trước PCM

ImportedStsMaster.prepare giải quyết event 224 với cờ 2..7 tương ứng volume/pan/transpose/octave/release/FX, giữ Tune hiện hành theo z01.e. Event 223 sao chép name/table từ system được chọn vào system 0, giữ StartKey/SelectedKeys theo handler gốc; system không hợp lệ bỏ qua. Event 222/225 trả tempo/chord riêng khi cờ 0/20 bật, null nghĩa giữ giá trị live. Chord được kiểm tra trước nạp; tempo bật phải dương.

prepareMasked tạo loadIndex riêng từ Music settings và systems hiệu lực để tiếng mới được giải mã theo global pitch/scale đã chọn. Prepared.index vẫn là index gốc để kiểm tra danh tính SET khi commit; từng voice Prepared bên trong giữ loadIndex hiệu lực. Không dùng từng voice này qua applyPreparedSts một voice, vì kiểm tra index identity sẽ từ chối.

Test Master xác nhận mask, Tune giữ, scale copy chỉ name/table, dữ liệu SET không bị đổi, tempo/chord null khi không chọn và system lỗi bỏ qua. Test master/changes/batch và assembleDebug đạt. Chưa commit Music bus/tempo/chord cùng cả nhóm trong audio thread; UI đa part vẫn tắt, chưa cài bản nội bộ. prepareFull vẫn là đường full-voice snapshot riêng và không tự áp Master.

## Chuyển nhóm ở biên frame

ImportedKeyboardPartQueue.commit nhận một lệnh kiểm tra/trước-replace; AudioEngine drain đúng đầu frame rồi configure Music mixer và render. Không drain lần hai sau configureMusic, tránh nhóm mới dùng Music settings cũ trong cùng frame. applyPreparedStsBatch cập nhật tempo, chord mode/table và Music override trước thay part trong cùng lệnh. Scale đã được bake vào PCM ở prepareMasked. Generation + file/index identity chặn kết quả cũ sau đổi SET/chọn voice thường. Clear xóa Music override trên audio thread.

Test queue kiểm tra Master/part đổi ở frame render, không đổi ngay lúc enqueue, commit bị từ chối giữ part cũ và clear callback chạy đúng thứ tự; test queue/master/batch và assembleDebug đạt. Chưa bật UI, chưa cài bản nội bộ. Đây là đồng bộ thứ tự trên audio thread, không phải transaction rollback khi callback/DSP phát sinh lỗi. Chưa có ngân sách PCM chung, cập nhật current snapshot sau commit và toàn bộ MIDI multi-part; không tuyên bố luồng STS hoàn chỉnh.

## Ngân sách PCM nhóm

ImportedStsMemory kiểm tra trước giải mã ở cả prepareFull và prepareMasked, giới hạn 64 MiB cho nhóm mới. Mono giữ frames*2 byte, stereo giữ stereo + mono = frames*6 byte; cộng raw buffer đọc lớn nhất. Chọn source dùng chung ImportedStsLoader.source để thống nhất mode đã chọn; các layer/Mix/Aftertouch trùng trong cùng part được distinct như loader, part riêng tính riêng vì hiện giải mã riêng. Không chỉ cộng kích thước file PCM stereo.

Test tính mono/stereo/hai part và nhóm lớn thất bại trước mở file không tồn tại đều đạt, cùng test batch/loader/STS cục bộ; assembleDebug đạt. Giới hạn chỉ cho PCM nhóm mới + raw scratch, chưa tính object overhead, garbage chờ GC, nhóm cũ còn ngân, FX/native buffers hoặc PCM style. Chưa phải cap RAM toàn app. Chưa bật UI, chưa cài bản nội bộ.

## Giao diện nạp nhóm tiếng

ImportedStsRequest phân tuyến track index 0 (Master) vào prepareFull, track riêng vào loader một voice. Trang STS hiển thị MASTER / ALL VOICES cho Master; chọn mục này nạp tối đa bốn voice Group 0, theo Record và vùng phím đã lưu. Chọn STS track riêng quay về đường một voice. Restore khi mở app và link Main dùng cùng resolver nên giữ kiểu nhóm đã chọn. Tên engine hiển thị tên STS / số part. Note-on multi-part được ghi MIDI kênh 0 một lần cho thao tác phím; đây không phải export cấu trúc từng part/MIDI channel gốc.

Luồng UI này cố ý dùng snapshot tiếng đầy đủ prepareFull: CHƯA thực thi default fSTSChanges/STSPut toàn bộ, CHƯA áp Master tempo/chord/Music qua lựa chọn UI. prepareMasked/Master commit vẫn là API nội bộ. Không coi MASTER / ALL VOICES là parity STSPut. Nạp thiếu slot/PCM quá ngân sách báo lỗi trước thay nhóm.

Test phân tuyến Master vs track riêng, batch/queue/memory/loader và assembleDebug đạt. Đã cài APK và xác nhận MainActivity chạy trên 88d75c04. Chưa trực tiếp chọn/thử nghe nhóm SET thật hoặc đóng/mở lại trên điện thoại; các lượt restore/link mới được build, không tuyên bố đã kiểm chứng UI đầy đủ.
