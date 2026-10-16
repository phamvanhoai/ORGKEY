# ORGX

Ứng dụng music workstation Android do **hovait** thiết kế. Dự án tái hiện các luồng chức năng đã nghiên cứu từ ORG 24 bằng mã nguồn, giao diện và thương hiệu mới.

## Phiên bản 1.24.0 hiện có

- Giao diện ngang tối ưu cho màn hình cảm ứng.
- Bàn phím cảm ứng đa điểm hai quãng tám, nhận đúng phím trắng/phím đen và cho phép rê ngón giữa các nốt.
- Touch velocity theo vị trí chạm và phản hồi màu riêng cho từng phím đang giữ.
- Mười âm sắc tổng hợp: ORGX Grand, Organ, Lead, Electric Piano, Synth Bass, Strings, Guitar, Brass, Flute và Synth Pad; phát bằng `AudioTrack` 48 kHz với harmonic/envelope riêng.
- Voice engine có attack/release envelope, decay cho Grand, sustain nhả mềm và giới hạn 24 voice để tránh click/quá tải.
- Bộ tổng hợp nhiều nốt, đổi octave, sustain và chỉnh âm lượng tổng.
- Tám style rhythm 16 bước Pop, Rock, Dance, Custom, Ballad, Funk, Latin và Reggae phát kick, snare, hi-hat; chạy/dừng bằng PLAY và bám theo tempo.
- Thư viện 12 hợp âm C, Dm, Em, F, G, Am, Bdim, C7, D7, E7, Fmaj7 và G7; phần đệm đổi ngay và đồng bộ rhythm.
- Song sequencer tự chuyển hợp âm mỗi ô nhịp với ba progression Pop, Ballad và Dance; chế độ AUTO được lưu qua lần mở sau.
- Trình tự hợp âm Custom gồm 8 ô nhịp chỉnh độc lập từ thư viện 12 hợp âm; mẫu được tự lưu và đóng gói trong PROJECT.
- COUNT-IN dùng đồng hồ sample trên luồng âm thanh, tiếng đếm đi trực tiếp ra master và không phụ thuộc MUTE/SOLO kênh Drums. Phần đếm không nằm trong WAV. Trước khi thu: OFF, 1 hoặc 2 ô nhịp trong SEQ; bấm REC lần nữa để hủy đếm. Lưu trong SET và PROJECT.
- Thu trực tiếp toàn bộ tiếng đàn và rhythm ra WAV stereo 48 kHz/16-bit bằng nút REC.
- Thư viện FILES liệt kê bản thu, hiển thị thời lượng, nghe lại và chia sẻ WAV qua Android.
- FILES nhập WAV PCM 16-bit mono/stereo từ bộ nhớ Android, kiểm tra cấu trúc, sample rate, kích thước dữ liệu và giới hạn 500 MB trước khi lưu.
- Bộ đọc RIFF duyệt từng chunk, hỗ trợ WAV có metadata `LIST`, `JUNK` hoặc chunk phụ trước `data`; import, thời lượng và MP3 dùng chung cùng kết quả phân tích.
- FILES hỗ trợ phát lặp và xóa an toàn trọn nhóm WAV/MIDI/MP3; PROJECT có xác nhận trước khi xóa tệp dự án.
- Danh sách FILES cuộn qua toàn bộ bản thu và đổi tên đồng bộ nhóm WAV/MIDI/MP3, có kiểm tra tên trống hoặc trùng.
- Điều khiển tempo, trạng thái engine và hộp thông tin “Designed by hovait”.
- TAP TEMPO đo nhịp từ các lần chạm, lấy trung bình tối đa bốn khoảng gần nhất và tự cập nhật BPM trong phạm vi 40–240.
- Tự lưu tempo, octave, nhạc cụ, hợp âm và style giữa các lần mở ứng dụng.
- Ba ô nhớ SET để lưu/gọi lại toàn bộ cấu hình biểu diễn, gồm cả âm lượng tổng.
- Mixer bốn kênh độc lập cho Keys, Chord, Drums và Master; mức kênh được lưu tự động và trong từng SET.
- Mixer có MUTE/SOLO độc lập cho Keys, Chord và Drums; trạng thái được tự lưu, áp dụng vào bản thu và đóng gói trong PROJECT.
- PAN stereo độc lập cho Keys, Chord và Drums, đi qua Delay/Reverb stereo và được giữ trong SET, PROJECT cùng bản thu WAV.
- FX thời gian thực gồm Delay và Reverb, có mức wet riêng; FX được đưa vào WAV và lưu cùng SET.
- Tám performance pad tổng hợp: Kick, Snare, Hi-hat, Clap, Low Tom, High Tom, Crash và Perc; đi qua kênh Drums/FX và bộ ghi WAV.
- Mỗi lần thu tạo WAV stereo 48 kHz và MIDI Type 0 chứa nốt bàn phím, velocity và tempo; cả hai có thể chia sẻ từ FILES.
- PROJECT lưu, mở lại và chia sẻ toàn bộ cấu hình buổi diễn bằng tệp `.orgx`, gồm nhạc cụ, tempo, hợp âm tự động, rhythm, mixer và FX.
- PROJECT nhập tệp `.orgx` từ bộ nhớ Android, kiểm tra định dạng, phiên bản, kích thước và dữ liệu bắt buộc trước khi đưa vào thư viện.
- Thư viện PROJECT cuộn qua toàn bộ tệp và hỗ trợ đổi tên an toàn, có kiểm tra tên trống, ký tự không hợp lệ và trùng tên.
- Nhập và phát tệp MIDI Type 0/1 qua trình chọn tệp Android bằng bộ tiếng ORGX; hỗ trợ note on/off, velocity, running status và tempo.
- Xuất bản thu WAV sang MP3 stereo 192 kbps bằng LAME và chia sẻ trực tiếp từ FILES.
- Bộ xuất MP3 nhận cả WAV mono và stereo PCM 16-bit; nguồn mono được nhân sang hai kênh để tạo MP3 stereo tương thích rộng.
- Step sequencer trống 16 bước cho Kick, Snare và Hi-hat; pattern Custom chạy theo tempo, có xóa nhanh và tự lưu.
- Swing 50–75% cho toàn bộ rhythm và Custom pattern; giữ nguyên tempo tổng thể và được lưu trong SET cùng PROJECT.
- Metronome theo phách, transpose ±12 bán âm và pitch bend thời gian thực ±2 bán âm; transpose/metronome được lưu trong thiết lập và PROJECT.
- Split Keyboard biến bảy phím trắng bên trái thành vùng chọn hợp âm C, Dm, Em, F, G, Am và Bdim; nửa phải tiếp tục chơi giai điệu.

- MIDI Type 0/1 áp dụng bản đồ tempo xuyên track, hỗ trợ running status và phát theo lịch tuyệt đối; kiểm tra độ dài track, data byte, tempo và giới hạn tệp 8 MB.

- Trình phát MIDI quản lý nốt theo từng phiên; STOP/đổi bài nhả nốt thuộc phiên cũ, không nhả toàn bộ phím đang chơi. Giao diện theo dõi trạng thái thực của trình phát.

- Nốt bàn phím và MIDI được giữ riêng; MIDI giữ số kênh khi đọc/phát/thu lại, nên note-off ở kênh này không nhả cùng cao độ ở kênh khác.

- MIDI hỗ trợ pedal sustain CC64 riêng từng kênh: giữ nốt sau Note Off, nhả khi pedal về dưới 64 và dọn nốt khi STOP/đổi bài.

- Bản thu MIDI ghi SUSTAIN bàn phím (CC64), chụp các nốt đang giữ khi bắt đầu thu và tự nhả nốt/pedal ở cuối bản thu theo độ dài WAV.

- Thu âm giới hạn 5 phút: tự lưu WAV/MIDI khi đầy, hiện SAVING và báo đã lưu. Ghi tệp chạy ở luồng nền sau khi tách dữ liệu khỏi luồng âm thanh.

- Khi màn hình ORGX chuyển nền hoặc đóng, tự dừng và lưu bản thu WAV/MIDI; hủy COUNT-IN chưa hoàn tất. Tác vụ lưu tách khỏi vòng đời giao diện và hiển thị đúng trạng thái khi quay lại.

- Luồng âm thanh tự quản lý khởi động và giải phóng AudioTrack, hoàn tất lần ghi đang chạy trước khi đóng; xử lý trường hợp ghi trả về một phần dữ liệu hoặc báo lỗi.

- Lưu WAV/MIDI qua thư mục tạm: đồng bộ dữ liệu xuống tệp, đưa WAV vào FILES sau khi MIDI hoàn tất, dọn tệp dở khi gặp lỗi và tránh ghi đè tên đã có.

- Bảng trạng thái hiển thị đồng hồ thu mm:ss / 05:00, thời gian còn lại và chấm đỏ khi đang ghi; thời gian tính theo số frame PCM đã ghi, không bao gồm COUNT-IN.

- PERFORMANCE mở rộng vùng điều khiển, hiển thị đủ tên nút và tách nhãn OCTAVE/TEMPO/TRANS khỏi giá trị; nút tăng giảm có vùng chạm 48 dp, CHORD hiển thị hai dòng.

- PANIC nhả toàn bộ nốt, dừng MIDI/rhythm, tắt sustain/metronome, xóa pad và đuôi FX; không dừng bản thu. Bàn phím dọn nốt khi thao tác bị hủy do đổi octave/split hoặc PANIC.

- Phát MIDI hỗ trợ Pitch Bend 14-bit riêng từng kênh với biên độ mặc định ±2 bán âm; STOP/đổi bài trả bend về giữa. Thu lại MIDI giữ lệnh bend và thêm reset ở cuối tệp. Chưa xử lý RPN thay đổi biên độ bend.

- MIDI CC7 Volume và CC11 Expression điều chỉnh âm lượng riêng từng kênh, kể cả nốt đang giữ. Khi STOP/đổi bài trả CC7 về 100 và CC11 về 127; thu lại MIDI giữ các lệnh này.

- MIDI Pan (CC10) điều khiển vị trí stereo riêng từng kênh: trái, giữa, phải; trả về giữa khi STOP/đổi bài và lưu khi thu lại MIDI.

- MIDI hỗ trợ All Notes Off (CC123), All Sound Off (CC120) và Reset Controllers (CC121) theo từng kênh để dọn nốt/pedal và khôi phục Volume, Pan, Expression khi chuyển đoạn.

- MIDI Program Change chọn nhạc cụ ORGX riêng từng kênh (0–9) trước khi phát nốt; bàn phím vẫn dùng nhạc cụ đang chọn trên MIXER.

- Program Change ánh xạ nhóm GM 0–127 vào 10 nhạc cụ ORGX hợp lý: piano, electric piano, organ, guitar, bass, strings, brass, reeds, lead và pad.

- MIDI channel 10 (GM drums) ánh xạ nốt 35–57 vào 8 performance pad ORGX: kick, snare, hi-hat, clap, tom, crash và percussion; nốt ngoài danh sách dùng pad percussion dự phòng.

- ORGX 1.23.1 đã nối AudioEngine với AudioTrackBackend qua contract AudioBackend; bản thu WAV/MIDI thực tế sau thay đổi đã thành công.

- Đã đóng gói 6 WAV đã giải mã từ APK tham chiếu trong es/raw và catalog SampleAssetCatalog; lớp sample playback sẽ dùng chúng ở bước tiếp theo.

## Giới hạn hiện tại

- Thư viện tiếng nhạc cụ và các bộ style/rhythm hoàn chỉnh.
- Trình biên tập sequencer đầy đủ và thư viện SoundFont/style mở rộng.
- Audio engine hiện là Kotlin/AudioTrack tái dựng, chưa dùng đúng native engine riêng của APK tham chiếu.

## Build

```powershell
.\gradlew.bat :app:assembleDebug
```

APK đầu ra: `app/build/outputs/apk/debug/app-debug.apk`.

Xem [THIRD-PARTY-NOTICES.md](THIRD-PARTY-NOTICES.md) để biết thành phần mã hóa MP3.

## Kiểm tra gần nhất

- Build debug thành công với JDK 17 và Android SDK 35.
- Cài đặt và khởi chạy thành công trên thiết bị Android thật.
- Đã thử thao tác bàn phím; không có lỗi `AndroidRuntime`.
- Đã thu và kiểm tra tệp WAV RIFF stereo 48 kHz/16-bit trên thiết bị thật.

- Kiểm tra 1.7.1: 6 unit test đạt, gồm 30 lượt thay phiên MIDI liên tiếp; build và khởi chạy trên thiết bị thật thành công.

- Kiểm tra 1.9.0: 11 unit test đạt, gồm đọc/thời gian CC64, giữ nốt đến pedal-up và nhả nốt sustain khi STOP.

- Kiểm tra 1.10.0: 15 unit test đạt; thu trên điện thoại tạo MIDI có CC64 ON/OFF và Note On/Off, không thấy lỗi AndroidRuntime.

- Kiểm tra 1.11.0: 18 unit test đạt. Bản kiểm thử giới hạn 2 giây trên điện thoại tự lưu WAV đúng 2 giây và trả nút về REC; APK chính khôi phục giới hạn 300 giây.

- Kiểm tra 1.12.0: 18 unit test đạt; trên điện thoại đã xác nhận HOME và BACK khi đang thu tạo WAV/MIDI, quay lại hiển thị REC, không thấy crash.

- Kiểm tra 1.12.1: 21 unit test đạt; tái tạo màn hình 6 lần khi rhythm chạy trên điện thoại, không thấy crash hoặc lỗi ORGX-Audio.

- Kiểm tra 1.13.0: 25 unit test đạt, gồm giả lập ghi MIDI thất bại và công bố WAV thất bại; thu thử trên điện thoại tạo đủ cặp WAV/MIDI, không còn thư mục tạm sau khi lưu.

- Kiểm tra 1.14.0: 25 unit test đạt; đã kiểm tra ảnh màn hình và trạng thái RECORDING/đồng hồ/thời gian còn lại trên điện thoại, thu và lưu WAV/MIDI thành công.

- Kiểm tra 1.15.0: 25 unit test đạt; đã xem ảnh bố cục trên điện thoại, thử OCTAVE 4→5 và TEMPO 120→125 rồi khôi phục, kiểm tra đồng hồ khi thu.

- Kiểm tra 1.16.0: 25 unit test đạt; thử rhythm+sustain+phím khi thu trên điện thoại, PANIC trả về PLAY nhưng giữ STOP REC; WAV có tín hiệu trước PANIC và 0 byte PCM khác 0 trong 0,5 giây cuối sau PANIC.

- Kiểm tra 1.17.0: 29 unit test đạt, gồm pitch bend 14-bit/running status, biên độ ±2, reset khi STOP và xuất/đọc lại bend; đã cài và thu WAV/MIDI trên điện thoại. Đã đo WAV do engine kết xuất trên điện thoại: xem analysis/pitch-bend-verification.json.

## Kiểm chứng pitch bend trên điện thoại

- Bản kiểm chứng: ORGX 1.17.0, ORGX Grand, transpose +1, FX/rhythm/metronome tắt.
- Tệp `tools/fixtures/pitch-bend.mid` phát ba đoạn: bend giữa, +2 và -2 bán âm; mỗi đoạn 10 giây.
- WAV thu từ ORGX trên điện thoại đo được 466,164 Hz, 523,251 Hz và 415,305 Hz, khớp các mức dự kiến. Có 9–10 cửa sổ đo hợp lệ cho mỗi mức.
- MIDI thu lại giữ bend 16383, 0 và reset 8192 (có thêm một reset giữa khi phiên phát kết thúc).
- Phép đo dùng zero-crossing với ORGX Grand và kiểm tra PCM kết xuất, không đo loa/micro. Công cụ này không dùng để đánh giá mọi âm sắc.

```powershell
py -3 tools/verify_midi_pitch.py --generate pitch-bend.mid
py -3 tools/verify_midi_pitch.py --wav ban-thu.wav --transpose 1 --report pitch-report.json
```
- Kiểm tra 1.18.0: 33 unit test đạt. WAV thu trên điện thoại với fixture tools/fixtures/volume-expression.mid đo tỷ lệ RMS 0,503974 (CC7=64, CC11=127) và 0,253734 (CC7=64, CC11=64), so với dự kiến 0,503937 và 0,253953. Báo cáo: ../analysis/midi-volume-verification.json.

- Kiểm tra 1.19.0: 37 unit test đạt. WAV thu trên điện thoại với pan trái/giữa/phải đo đúng: trái 4163,76/0 RMS, giữa 4163,76/4163,76, phải 0/4163,76. Báo cáo: ../analysis/midi-pan-verification.json.

- Kiểm tra 1.20.0: 37 unit test đạt; build, cài và khởi chạy trên thiết bị thật thành công.

- Kiểm tra 1.21.0: 39 unit test đạt; build, cài và khởi chạy trên thiết bị thật thành công.

- Kiểm tra 1.22.0: 42 unit test đạt; build, cài và khởi chạy trên thiết bị thật thành công.

- Kiểm tra 1.23.0: 44 unit test đạt; build, cài và khởi chạy trên thiết bị thật thành công.

- Kiểm tra kênh trống GM: fixture MIDI channel 10 đã được tạo và ánh xạ qua MidiDrumMap; kiểm thử UI picker trên thiết bị bị Android giữ ở màn hình chọn tệp nên chưa dùng kết quả WAV cho báo cáo.

- Trạng thái hiện tại: ORGX 1.23.1, 44 unit test đạt, APK debug đã cài trên thiết bị thật.

- ORGX 0.1.4: metronome dùng sample WAV gốc với con trỏ phát riêng; Crash pad và metronome không chia sẻ trạng thái sample.
