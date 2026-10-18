# Tổng quan APK ORG 24

Đã kiểm tra JNI thật qua player: chuyển đuôi Reverb sang player mới rồi đóng player cũ vẫn giữ đuôi vang. Wrapper Reverb dùng buffer stereo cấp phát một lần, bỏ Pair mỗi frame. Đây chưa phải kiểm tra toàn bộ chuyển style hoặc đo latency/GC của engine.

Đã nối Reverb native (FX 29) vào chuỗi FX SET, gồm cấp phát khi có tín hiệu, ownership handle, flush/close và khóa cặp lệnh stereo. Dùng binary gốc có sẵn trong project; chưa khôi phục mã nguồn thuật toán và chưa xác nhận tương đương toàn bộ SET. Xem [DECODE-REBUILD.md](DECODE-REBUILD.md).

Đã bổ sung Band Move (FX 2): gain hai nhóm band đối nghịch, delay, speed, loop đổi chiều và ngưỡng tín hiệu theo `sofeh.audio.c`/native bandMove. Giữ history khi đổi gain/reset; phạm vi 48 kHz. Chưa xác nhận bằng binary native hoặc bản thu. Chi tiết: [DECODE-REBUILD.md](DECODE-REBUILD.md).

Đã dựng Equalizer (FX 7) stereo từ assembly native: tám nhánh lọc, gain từng band, input delay và reset giữ history. Phạm vi 48 kHz; chưa đối chiếu binary native/bản thu để xác nhận tương đương âm thanh. Xem [DECODE-REBUILD.md](DECODE-REBUILD.md).

Đã dựng đường stereo Flanger/Phaser (FX 8) từ assembly ARM64/x86: chuỗi all-pass, feedback, modulation mỗi 20 frame và dry/wet. Đã sửa reset giữ history/phase theo `n01.c` và nhận Ratio tới 256; fresh chain vẫn tạo state sạch. Chưa xác nhận bằng bản thu. Xem [DECODE-REBUILD.md](DECODE-REBUILD.md).

Đã bổ sung Distortion (FX 23) theo đường stereo `r01.e`: gain, soft/hard clip, CutOff và history final output riêng hai kênh. Giữ payload boolean Soft và thứ tự module; file SET không thay đổi. Giới hạn đối chiếu PCM/bản thu: [DECODE-REBUILD.md](DECODE-REBUILD.md).

Đã bổ sung Chorus (FX 21) theo `q01`: delay nội suy, feedback và độ lệch pha stereo; reset giữ pha, xóa buffer. Chưa xác nhận tương đương bản thu/lượng tử PCM gốc. Xem [DECODE-REBUILD.md](DECODE-REBUILD.md).

Đã bổ sung Band pass (FX type 1) theo `o01/v01`, gồm history riêng hai kênh, reset, cờ enable và kiểm tra hệ số không hữu hạn. Đây là bộ lọc Band pass; Band Move native vẫn cần dựng riêng. Chi tiết: [DECODE-REBUILD.md](DECODE-REBUILD.md).

Đã bổ sung FX Robot (16) theo `x01`: gain cosine dùng pha integer, bộ đếm chung hai kênh, reset và vòng một giây. Áp dụng trong chain FX dùng chung, giữ enable/thứ tự. Chưa đối chiếu bản thu hoặc lượng tử long nội bộ; xem [DECODE-REBUILD.md](DECODE-REBUILD.md).

Đã bổ sung bộ lọc FX High pass (9) và Low pass (10) theo `u01/v01`, với history riêng hai kênh, reset và cờ enable. Giữ công thức/thang tham số trong mã gốc, gồm phép chia integer của High pass. Band Move/Flanger/Reverb native vẫn chưa dựng đầy đủ; xem [DECODE-REBUILD.md](DECODE-REBUILD.md).

Đã bổ sung DSP type 17 “3D Sound (Stereo)” theo `y01` của APK gốc, áp dụng qua chuỗi FX dùng chung cho track/group/voice MODE/Music. Giữ cờ bật/tắt và thứ tự module; không thay đổi file SET. Chưa xác nhận tương đương lượng tử PCM/bản thu gốc; các module DSP còn thiếu được ghi trong [DECODE-REBUILD.md](DECODE-REBUILD.md).

Đã sửa tra bảng tuning khi transpose theo `Music.KeyToPitchEx`: trừ transpose khỏi key trước khi chọn slot 24 phím, giữ octave; áp dụng cho raw voice, MODE và channel style. Đây là sửa cao độ theo mã gốc, chưa phải xác nhận âm thanh tương đương toàn bộ APK.

ORGX đã nối Music tune/transpose/release cho voice SET và style; octave bàn phím lấy từ Music, octave style lấy từ Rhythm theo `Channels.OpenKey`. Release nhân đủ ba yếu tố trước khi làm tròn. Music/Rhythm balance chốt vào nốt mới trước FX, không đổi mức đuôi echo đang ngân. Music FX (module 4/6/20) chạy sau mixer voice SET, volume Music áp lên tổng đầu ra theo `Music.ProcessOut`. Chưa hoàn chỉnh chỉnh pitch trực tiếp khi nốt ngân, Music FX/balance cho voice factory/MIDI, Wave FX và các module còn thiếu; xem [DECODE-REBUILD.md](DECODE-REBUILD.md).

Mục lục toàn bộ báo cáo: [INDEX.md](INDEX.md). Manifest kiểm chứng 491 artifact, kích thước và SHA-256 nằm tại [ANALYSIS-MANIFEST.md](ANALYSIS-MANIFEST.md).

Đã dựng [luồng khởi động ứng dụng](APP-STARTUP.md): `MainApplication → SplashActivity → MainActivity`, tạo 13 thư mục, nhập URI `VIEW/SEND`, xử lý extras; preference `safe_mode` bật launcher riêng `SafeActivity → MainActivity` sau 750 ms.

Đã giải mã [catalog nội dung tải xuống `download.en`](DOWNLOAD-CATALOG.md): 101 nhóm tác giả, 431 gói ZIP, 21 preview, timestamp và các trường metadata được parse hết; ghi nhận năm record dùng tên archive trùng.

Đã nối [luồng MIDI vào với dispatcher Model](MIDI-CONTROL-FLOW.md): Note On/Off, Program Change, keyboard mapping, wildcard lookup, UI-thread dispatch và command chọn Instrument 1–10.

Đã giải mã [MIDI Model và ánh xạ điều khiển](MIDI-MODEL.md): parser/writer dạng text, 70 command được hỗ trợ, quy tắc wildcard và 60 ánh xạ Program Change trong sáu model mặc định.

Đã nối [24 Script nhạc cụ với luồng thực thi](SCRIPT-ENGINE.md): thống kê event, lệnh phát/timer/effect/cài đặt và xác nhận chuỗi `Music → Track → InstMode → interpreter` bằng REA.

Đã giải mã [Chord, System và Model trong `assets/data.zip`](DATA-ZIP-CHORD-SYSTEM.md): 33 bảng hợp âm dạng mặt nạ 12 bit, 36 System đọc đúng EOF, 6 cấu hình bàn phím cùng inventory cho Script, Skin, PNG và WAV.

Đã giải mã [Constant.Instrument và Sound.Instrument](INSTRUMENT-ASSETS.md) đến đúng EOF: 15 section, 3.433.536 byte PCM nội tuyến; đã xuất WAV kiểm chứng từ mỗi asset.

Đã [đối chiếu PCM và xuất WAV](XUAT-PCM-WAV.md): toàn bộ 2.001 section khớp chính xác với 1.251 wave; thêm công cụ xuất PCM nội tuyến hoặc wave dùng chung và bốn file WAV kiểm chứng.

Đã xác định [vòng đời event nốt](EVENT-NOTE-LIFECYCLE.md): type 2 mở nốt, type 1 release/đóng theo cùng ID; nối được logic ghi Length, scheduler và connection effect với số liệu trong Template.

Đã giải mã [empty.Template 1.2.4](EMPTY-TEMPLATE-INVENTORY.md) đến đúng EOF: 2 Instrument, 3 section chứa 297.880 byte PCM nội tuyến, 1 System, 14 Track và 1 TrackGroup.

Đã xuất [inventory thực của Default.Template](DEFAULT-TEMPLATE-INVENTORY.md): parser đi đúng đến EOF, tìm được 92 Instrument, 11 System, 2.648 Track, 158.018 event, 610 TrackGroup và 62.086.248 byte PCM dùng chung.

Đã đọc [STS, Channels và Effect](STS-CHANNELS-EFFECT.md): payload STS, record Effect 40 byte, 8 slot cố định, ba layout Channels lịch sử và bảng kích thước của đủ 16 module FX.

Đã nối [collection, System, Track và Event](COLLECTION-TRACK-EVENT.md): cách lưu count/object, hai kiểu chuỗi, bảng tuning, metadata MIDI/STS, `KeyData`, event 101 nhúng sample và phần TrackGroup theo phiên bản.

Đã bóc tách [định dạng Template/SET và Instrument](DINH-DANG-SET-INSTRUMENT.md): header chung, nhánh phiên bản, kho wave dùng chung, mode/sound/section và cách lưu PCM. Có script đối chiếu trực tiếp bốn asset đi kèm APK.

Đã đọc [callback SoundPool và writer WAV](SOUNDPOOL-VA-WAV-WRITER.md): PCM little-endian, header cập nhật khi đóng; callback không dùng status và có điểm bất thường nối thêm `.wav` vào đường dẫn đã có đuôi này.

Đã nối [vòng đời WAV cho mode 4](VONG-DOI-WAV.md): nơi gọi chuẩn bị lại, nốt gốc được render, thư mục `.temp` và tác vụ dọn file cũ hơn 24 giờ khi khởi tạo.

Đã [xác minh bằng DEX](XAC-MINH-DEX.md): Activity khởi tạo mode 1, 44100 Hz, buffer Auto; sửa điều kiện SoundPool bị đảo trong Java suy dịch — nhánh này bỏ qua mode <= 2.

Đã đọc [cơ chế chuẩn bị âm bổ sung và nốt ghi sẵn](CHE-DO-AM-BO-SUNG.md) cho mode 3/4: tài nguyên SoundPool và WAV xuất từ nhạc cụ. Điều kiện phát trong mã suy dịch còn cần đối chiếu DEX.

Đã xác định [các cài đặt âm thanh và giá trị lưu](CAI-DAT-AM-THANH.md): khóa `output_low_latency`, năm lựa chọn chế độ, các mức buffer/chất lượng và đường nạp vào Music.

Đã xác định [fallback AudioTrack](FALLBACK-AUDIOTRACK.md): tạo thiết bị, gọi cùng bộ trộn rồi ghi buffer `short[]`; đã nối đủ ba đường đầu ra chính. Nơi nạp lựa chọn chế độ và hành vi runtime còn chưa xác minh.

Đã nối [callback phát âm và hai chế độ OpenSL](CALLBACK-AUDIO.md), đồng thời dựng lại [toàn bộ chuỗi `NOpenSL.Start`](NOPENSL-START-CALLBACKS.md): tạo engine/output mix/player, lấy Play/BufferQueue/Volume, đăng ký hai callback, enqueue buffer đầu và tạo recorder tùy chọn. Mode 1 gọi `Music.ProcessOut` từ callback; mode 2 dùng thread Java cung cấp dữ liệu.

Đã nối [đường thu âm OpenSL](OPENSL-RECORDING.md): `RecordStart/Stop`, queue capture đồng bộ theo callback output, vòng buffer 4/16 slot, `ProcessIn`, FX microphone và dữ liệu capture trả về Java qua `MixPut/MixWrite`.

Đã nối [đường thu âm fallback `AudioRecord`](FALLBACK-AUDIORECORD.md): dò sample rate, tạo recorder và monitor `AudioTrack`, thread đọc khoảng 10 ms, `ProcessIn`, phát monitor, rồi dừng và giải phóng cả hai thiết bị.

Đã bổ sung [phân tích mã native âm thanh ARM64](NATIVE-AUDIO.md): disassembly năm hàm, các mode trộn mẫu, cơ chế chép/trộn buffer và giới hạn chưa kiểm chứng runtime.

Đã giải mã cấu trúc [Skin/Part](SKIN-FORMAT.md): 24 tài nguyên giao diện dạng văn bản, 5 họ skin, 1.107 bản ghi và parser `us3.x` với các trường header, tọa độ, giá trị mặc định, mã thành phần đặc biệt và quy tắc co giãn theo kích thước màn hình.

Đã trích thêm `us3.w` vào [skin-render-w.java](source/skin-render-w.java), lập danh sách các nhóm mã được dispatch khi render và nối chắc chắn nhánh 30/31 với vùng điều khiển đặc biệt từ parser.

Đã kiểm kê [tài nguyên menu/trợ giúp](MENU-ASSETS.md): 33 tệp UTF-8, gồm menu chính, FAQ, hướng dẫn hiệu năng, thông báo và chuỗi mua hàng đa ngôn ngữ; đã ghi lại quy ước cặp nhãn–hành động và các liên kết giữa menu.

Đã rà các tài nguyên phụ trong [REMAINING-ASSETS.md](REMAINING-ASSETS.md): `download.en` là catalog, `dic` đã được giải mã thành ELF Pangle/PGL, còn `PublicSuffixDatabase.list`/MRAID là dữ liệu SDK bên thứ ba.

Đã parse [assets/Chord](CHORD-ASSET.md): 37 định nghĩa hợp âm dạng tên + khoảng cách bán âm, từ hợp âm ba nốt đến các mẫu bảy nốt; script và inventory lưu lại toàn bộ dòng thực tế.

Đã nối tệp Chord với `sofeh.music.Music.ChordTable`: mỗi mẫu được mở rộng thành 12 bitmask theo nốt gốc trong `fChordTable`; bằng chứng mã nằm ở [chord-table-loader.java](source/chord-table-loader.java).

Đã phát hiện và parse thêm 33 file `data.zip!/Chord/*.Chord`, mỗi file chứa sẵn 12 bitmask theo nốt gốc; chi tiết ở [NESTED-CHORD-ASSETS.md](NESTED-CHORD-ASSETS.md).

Đã đối chiếu hai lớp hợp âm: 31/33 file nested trùng chính xác với mẫu chuẩn; chỉ `Customized Major` và `Customized Minor` là bảng tùy biến không có dòng tên tương ứng trong `assets/Chord`.

Đã lập [bảng coverage tài nguyên](ASSET-COVERAGE.md), phân biệt các định dạng ứng dụng đã parse đến EOF, tài nguyên văn bản đã kiểm kê và dữ liệu SDK không thuộc logic riêng của ORG.

Đã bổ sung [coverage native](NATIVE-COVERAGE.md): xác nhận 32 JNI symbol của `libnmusic.so` và `libnopensl.so` trên ARM64, trong đó 25 symbol được đánh dấu defined; toàn bộ 75 JNI lõi sau đó đã được tách trên cả bốn ABI.

Đã lập [inventory ABI native](NATIVE-ABI-INVENTORY.md): 58 thư viện `.so` phân bố trên `arm64-v8a`, `armeabi-v7a`, `x86` và `x86_64`; [disassembly JNI toàn ABI](NATIVE-ALL-ABI-DISASSEMBLY.md) có 300 file cho 75 JNI trên mỗi kiến trúc.

Đã lập [danh sách symbol JNI native](NATIVE-JNI-SYMBOLS.md), gồm 16 hàm channel/mix/bộ nhớ của `libnmusic.so` và 9 hàm phát/ghi OpenSL của `libnopensl.so`, kèm các symbol OpenSL xuất khẩu.

Đã quét [symbol inventory ARM64](NATIVE-ARM64-SYMBOL-INVENTORY.md): 18 thư viện, 3.888 dynamic symbol và 100 symbol JNI; các thư viện âm thanh ORG đã được tách khỏi nhóm cần đối chiếu SDK.

Đã lập [JNI của các hiệu ứng âm thanh](NATIVE-EFFECT-JNI.md): BandBooster, Equalizer, Flanger, GVerb và PitchShift đều có vòng đời native riêng và entry point xử lý trái/phải hoặc stereo.

Đã xếp hạng [kích thước JNI hiệu ứng](NATIVE-EFFECT-SIZES.md): ưu tiên tiếp theo là Equalizer `process*`, NGVerb `processStereo`/setter và Flanger `process*`.

Đã disassemble nhóm ưu tiên Equalizer và GVerb bằng [dump_effects.py](dump_effects.py); các bản ARM64 đầy đủ đã nằm trong `analysis/native/`.

Đã disassemble thêm Flanger và BandBooster; các entry point `build/process/processLeft/processRight` lớn của năm module hiệu ứng chính hiện đã được bao phủ trên ARM64.

Đã lập [JNI SF2 và LAME](NSF2-LAME-JNI.md): `libnsf2` đọc preset/voice/sample loop của SoundFont; `libnlame` cung cấp vòng đời mã hóa MP3; `libnms` không xuất JNI tên tĩnh.

Đã disassemble `NSF2_Voice/Open/PresetName` và toàn bộ bốn wrapper `Lame` trên ARM64; assembly nằm trong `analysis/native/`.

Đã [nhận dạng libnms.so](LIBNMS-IDENTITY.md) là thư viện Pangle/PGL qua `JNI_OnLoad` và lớp `com/pgl/ssdk/ces/a`, sửa việc xếp nhầm nó vào nhóm âm thanh ORG.

Đã lập [bảng sở hữu native](NATIVE-OWNERSHIP.md): 9 thư viện có JNI `Java_sofeh_*` thuộc đường âm thanh ORG; phần còn lại được tách sang AndroidX, AppLovin và Pangle/ByteDance.

Đã kiểm kê [toàn bộ native SDK bên thứ ba](THIRD-PARTY-NATIVE.md): 58/58 ELF đã có nhóm sở hữu, SHA-256, ABI, symbol, JNI, import và `DT_NEEDED`; bảy thư viện Pangle chỉ có bản ARM, còn AppLovin và AndroidX có đủ bốn ABI.

Đã thực hiện [kiểm chứng runtime đúng APK 2026](RUNTIME-RESULT-2026.md): hash bản cài khớp file phân tích; xác nhận nạp `libnmusic/libnopensl`, OpenSL fast track stereo 48 kHz, buffer 960 frame, latency 41 ms, 0 underrun ở track ứng dụng và không crash. Sau khi cấp quyền micro, `AudioRecord` mono 48 kHz thu thành công 92.160 frame với input latency khoảng 30,17 ms; output monitor mono cũng xuất hiện. UID ứng dụng có lưu lượng mạng trong khoảng đo.

Người dùng đã xác nhận xuất MP3 thành công trên đúng bản 2026; pipeline LAME đã được nối đầy đủ ở mức tĩnh. Không có thiết bị MIDI phần cứng trong môi trường kiểm thử, nên MIDI được ghi là không áp dụng thay vì coi là hạng mục chưa hoàn tất.

Đã lập [bảng khoảng trống JNI ARM64](NATIVE-JNI-COVERAGE-GAPS.md): LAME và PitchShift đã có assembly cho toàn bộ JNI; các module khác còn chủ yếu wrapper nhỏ/getter/setter, riêng NMusic còn 15 wrapper chưa tách file riêng.

Đã chạy [dump_all_sofeh_jni.py](dump_all_sofeh_jni.py), tạo thêm 44 bản assembly; hiện toàn bộ JNI `Java_sofeh_*` của 9 thư viện âm thanh ARM64 đều có file riêng trong `analysis/native/`.

Đã [so sánh JNI giữa bốn ABI](NATIVE-ABI-JNI-COMPARISON.md): ARM64, ARM32, x86 và x86_64 đều có cùng 9 thư viện `sofeh` và cùng 75 tên JNI; coverage ARM64 bao phủ toàn bộ bề mặt API native.

Đã tách [assembly JNI trên toàn bộ ABI](NATIVE-ALL-ABI-DISASSEMBLY.md): 300 thân symbol, 82.942 byte và 21.899 lệnh. [Phần cuối năm symbol ARM32](ARM32-TAIL-CLASSIFICATION.md) gồm 118 byte pool hằng số/literal, dữ liệu hoặc padding đã được giữ nguyên và phân loại riêng.

Đã kiểm tra [cấu trúc chữ ký APK](APK-SIGNATURE.md): không có JAR/v1, có `APK Sig Block 42` với scheme v2, không thấy ID v3; xác minh mật mã signer vẫn cần `apksigner`.

Đã đọc certificate v2 bằng Androguard: signer tự ký `CN=Ramin Jafari,O=Sofeh Sunrise`, SHA-256 `ea29e47d6696ca0d6df9f0d455f580e4fd54f11a5c7a71abd5edacae937c02ea`, hiệu lực 2013-11-26 đến 2038-11-20 UTC.

Đã [giải mã assets/dic](DIC-ASSET.md): DEX xref dẫn tới `com.pgl.ssdk.af`, phép XOR khóa lặp `dic` tạo ELF x86 32-bit 4.016 byte của SDK Pangle/PGL; ứng dụng ghi file vào filesDir, chmod và chạy nó.

Đã disassemble toàn bộ `.text` của ELF `dic`: 756 byte/245 lệnh x86, entry `0x550`; PLT chỉ có `__libc_init`, `__cxa_atexit`, `printf` và stack-check failure.

Đã chạy [kiểm chứng tái lập](VALIDATION.md): các script Python đều hợp lệ; các parser Skin, Menu, Chord, native inventory, so sánh ABI và disassembler chạy lại không lỗi.

Đã xếp hạng [symbol native theo kích thước](NATIVE-SYMBOL-SIZE.md); ba hàm lớn nhất `NMusic_ChannelProcess`, `NOpenSL_Start` và `NOpenSL_MixWrite` hiện đều đã có disassembly đầy đủ. Ba helper nội bộ kế tiếp `NOpenSL_Start` cũng đã được tách riêng.

Đã chạy disassembler ARM64 cho năm hàm ưu tiên; các file `.asm` đầy đủ nằm trong `analysis/native/`, với kiểm tra số byte khớp symbol ELF.

Đã trích [call graph native](NATIVE-CALL-GRAPH.md): `ChannelProcess` gọi `smbPitchShift`, `NOpenSL_Start` khởi tạo `slCreateEngine`, còn các hàm Put/Write dùng memcpy và sleep đồng bộ.

Đã lập [danh sách import native](NATIVE-IMPORTS.md): `libnopensl.so` chỉ import OpenSL, mutex, thao tác buffer, `usleep` và runtime C/C++; không có import mạng hoặc I/O file trực tiếp.

Đã lập [import của libnmusic](NMUSIC-IMPORTS.md): thư viện này dùng `smbPitchShift`, FFT và các hàm toán học, không import OpenSL; ranh giới xử lý âm thanh và thiết bị phát/ghi đã rõ hơn.

Đã ghi nhận [chuỗi trạng thái trong libnmusic](NMUSIC-STRINGS.md): vị trí, volume trái/phải và pitch change xuất hiện trong `.rodata`, phù hợp với vai trò mixer/pitch-shift.

Đã lập [hồ sơ module pitch-shift](PITCH-SHIFT-MODULE.md): nhóm symbol `smbPitchShift*`, `smbFft`, `smbAtan2` và hai lời gọi từ `ChannelProcess`; chưa gán nguồn upstream vì APK không chứa metadata đủ chắc chắn.

Đã đọc [hai call site pitch-shift](PITCH-SHIFT-CALLSITE.md): cả hai xử lý block `0x400` mẫu, truyền buffer/trạng thái riêng và hệ số pitch đã tính vào `smbPitchShift`.

Đã xác nhận implementation `smbPitchShift*` nằm trong [libnpitchshift.so](NPITCHSHIFT-COVERAGE.md): `smbPitchShift` dài 1.468 byte/367 lệnh ARM64 và có JNI wrapper `NPitchShift`; `libnmusic.so` chỉ gọi module qua PLT.

Đã disassemble trực tiếp `smbPitchShift` và bốn wrapper `NPitchShift` vào `analysis/native/`, với script tái lập [dump_npitch.py](dump_npitch.py).

Đã mở rộng assembly cho `smbPitchShiftSample`, `smbFft`, `smbPitchShiftNew`, `smbPitchShiftFlush` và `smbPitchShiftCopy`; các entry point chính của module pitch-shift/FFT hiện đã có bản ARM64.

Đã kiểm tra lại [địa chỉ PLT của libnpitchshift](NPITCHSHIFT-HELPERS.md): vùng `0x1e50–0x1ec0` là stub import dùng chung, không phải helper static như nhận định ban đầu.

Đã lập [call map import của libnpitchshift](NPITCHSHIFT-IMPORT-CALLS.md): `smbPitchShift` và `smbPitchShiftSample` gọi FFT, toán học và giải phóng buffer; `smbFft` gọi ngược `smbPitchShiftSample` qua PLT.

Đã đọc [JNI wrapper NPitchShift](NPITCHSHIFT-WRAPPERS.md): block 1.024 mẫu, hai vùng trái/phải, offset phải `0xb010`, hệ số chuẩn hóa `100.0f` và kiểm tra null trước khi gọi routine chung.

Đã bổ sung [vòng đời NPitchShift](NPITCHSHIFT-LIFECYCLE.md): `open` cấp phát block trạng thái `0x16020` và khởi tạo các vùng offset cố định; `close` chỉ là wrapper ngắn, chưa tự giải phóng trong thân JNI.

Đã ghi nhận [các lời gọi của smbPitchShift](NPITCHSHIFT-CALLS.md) và ánh xạ chúng qua PLT tới `smbFft`, `atan2`, `sincos`, `log`, `cos`, `free` và `smbPitchShiftSample`.

Đã bổ sung [phân tích luồng bấm phím → phát âm thanh](LUONG-PHAT-AM.md), kèm bằng chứng REA và các giới hạn chưa xác minh.

Đã đọc tiếp cơ chế mode/aftertouch/layer, cấp phát và release voice, nhận diện phím trắng/đen; đã đối chiếu các điểm JNI với bảng ký hiệu ELF ARM64. Chi tiết và file bằng chứng nằm trong báo cáo luồng phát âm.

Phân tích tĩnh bằng REA 6.5.0 và jadx-headless-mcp 0.7.1. Không chạy ứng dụng.

## Thông tin xác nhận từ APK

- Package: `com.sofeh.android.musicstudio3`
- Phiên bản: `2026.10.01`, version code `274`.
- SDK tối thiểu: 24; SDK mục tiêu: 36.
- SHA-256: `9512e006f4c1780683aa19510dad701c415c98f3d87d0adbd7559088813e8855`.
- JADX báo 22.322 lớp và 1.349 tài nguyên; số lớp bao gồm các SDK bên thứ ba.
- 3 DEX ở gốc APK; thêm một DEX tại `assets/audience_network/classes.dex`.
- 58 file thư viện native, phân bố trên ARM 32/64 bit và x86 32/64 bit.

## Các thành phần

| Nhóm | Bằng chứng | Nhận xét |
| --- | --- | --- |
| Khởi động và giao diện | `SplashActivity`, `SafeActivity`, `MainActivity`; application `sofeh.android.MainApplication` | Đã nối thứ tự intent tĩnh và hai đường vào MainActivity trong [APP-STARTUP.md](APP-STARTUP.md). |
| Cài đặt | `sofeh.android.setting.SettingActivity`, `SettingFragment0` đến `SettingFragment6` | Có các lớp riêng cho cài đặt. |
| Mô hình âm nhạc | `sofeh.music.Instrument`, `Channels`, `Track`, `Tracks`, `TrackEvent`, `WaveData`, `Effect` | Đã đọc parser/writer, vòng đời event, track, channel và effect; các báo cáo chuyên đề giữ chi tiết. |
| Âm thanh native | `sofeh.audio.NOpenSL`, `NEqualizer`, `NFlanger`, `NGVerb`, `NPitchShift`, `Lame`; các file `.so` tương ứng | Đã nối JNI và có assembly riêng cho toàn bộ 75 JNI `Java_sofeh_*` trên cả bốn ABI. |
| Nhạc cụ và dữ liệu | `Default.Template` (67.848.221 byte), `Sound.Instrument`, `Constant.Instrument`, `Chord`, `data.zip` | Đã giải mã cấu trúc và kiểm tra parser đến EOF cho các template chính. |
| Giao diện tùy chỉnh | `assets/skin/*.Skin`, `*.Part`, `assets/menu/*` | Có tài nguyên skin và menu nhiều ngôn ngữ. |
| Quảng cáo và dịch vụ | Các trường Google Ads, AppBrain và FirebaseAnalytics trong MainActivity; thư viện AppLovin và các thành phần quảng cáo trong manifest | Một phần mã APK phục vụ SDK bên thứ ba. |

## Quyền khai báo

Có các quyền micro, điều chỉnh âm thanh, Bluetooth, vị trí, bộ nhớ, Internet, thông báo và thanh toán. Quyền khai báo không chứng minh ứng dụng đã sử dụng quyền hoặc truyền dữ liệu khi chạy. Danh sách đầy đủ nằm trong `package.json` và `AndroidManifest.xml`.

## Dùng lại REA

Từ thư mục dự án, chạy trong PowerShell:

```powershell
.\run-rea.ps1 inspect-android-package '.\ORG+24_+Your+Music_2026.10.01_APKPure.apk' --format json
.\run-rea.ps1 search-android-classes '.\ORG+24_+Your+Music_2026.10.01_APKPure.apk' 'sofeh.music' --format json
```

Script chọn JDK và JAR trong `%LOCALAPPDATA%\rea\android`, dùng REA phiên bản 6.5.0 qua npx. Không cần thay đổi PATH toàn hệ thống.

## Bằng chứng và giới hạn

- `package.json`: kết quả REA đầy đủ và manifest.
- `AndroidManifest.xml`: manifest đã giải mã.
- `classes-sofeh.json`: 66 tên lớp khớp `sofeh`; không đại diện toàn bộ mã riêng vì một số tên đã bị rút gọn.
- `main-activity.json`: danh mục phương thức và trường của MainActivity, chưa phải mã nguồn phương thức.
- `archive-inventory.csv`: danh mục file và kích thước trong APK.

Đã xác minh cấu trúc chữ ký v2 và certificate của Sofeh Sunrise; chưa xác minh nguồn gốc chuỗi phân phối từ APKPure. Đã phân tích mã máy cho toàn bộ bề mặt JNI `sofeh` trên bốn ABI, phân loại riêng 118 byte dữ liệu/padding ARM32, kiểm kê mọi ELF bên thứ ba và chạy đúng APK trên thiết bị. Runtime đã xác nhận khởi động, output OpenSL, thu microphone và monitor output; nội dung/đích mạng, từng chế độ độ trễ và toàn bộ hàm nội bộ SDK bên thứ ba chưa được phân tích động đầy đủ.

## Trạng thái tái dựng ORGX

Bản dựng lại nằm trong thư mục `../orgx` và hiện phát hành ORGX 1.23.1. Các phần đã triển khai gồm bàn phím cảm ứng, 10 nhạc cụ, rhythm/chord/sequencer/pad, mixer và FX, WAV/MIDI/MP3, project/SET, COUNT-IN, sustain, pitch bend, CC7/CC10/CC11, Program Change GM, safety controller và GM drums channel 10.

Đã kiểm chứng trên thiết bị thật: khởi động, thu WAV/MIDI, COUNT-IN, tự lưu khi chuyển nền, pitch bend bằng tần số PCM, volume/expression bằng RMS, pan stereo, AudioTrack lifecycle và 44 unit test. APK là bản tái dựng chức năng; native engine, sample/template và skin của APK gốc chưa được đóng gói lại toàn bộ.

Khi tiếp tục phát triển, ưu tiên còn lại là thay engine Kotlin/AudioTrack bằng lớp native tương thích, dùng chính inventory sample/template đã phân tích và đối chiếu từng màn hình với skin gốc. Không nên gọi bản dựng hiện tại là bản sao 100% APK gốc.

Bản 1.23.1 đã nối AudioEngine với AudioTrackBackend qua AudioBackend; APK đã build, cài và khởi chạy lại trên thiết bị thật, không ghi nhận lỗi AudioTrack.


Checkpoint phục hồi hiện tại: orgx-piano-sample-0.1.3 và orgx-metronome-sample-0.1.4. Source đã được commit theo từng bước; bản 0.1.4 đã thu thử WAV/MIDI trên thiết bị.


Bản hiện tại của nhánh phục hồi là ORGX 0.1.5 (versionCode 40), đã có piano sample và metronome accent sample; các checkpoint nằm trong git tag orgx-piano-sample-0.1.3, orgx-metronome-sample-0.1.4 và orgx-metronome-accent-0.1.5.


Bản sample hiện có kiểm thử SamplePcmDecoder cho PCM16 hợp lệ và WAV bị cắt; erify-release.ps1 -Build xác nhận versionCode 40 và checksum APK 0.1.5.
Bổ sung cập nhật 2026-10-17: ORGX hiện là bản 0.1.6 (versionCode 41). APK đã cài và chạy trên thiết bị thật; thư viện native tham chiếu nạp được. Wrapper `sofeh.audio.NOpenSL` và `NOpenSLBackend` đã có trong mã nguồn nhưng chưa được chọn mặc định; đường phát chính vẫn là AudioTrack để giữ fallback an toàn. Vì vậy chưa thể gọi ORGX là bản sao 100% APK gốc.

Cập nhật xử lý SET: 41 nhóm Drum tương thích trong SET thử đã được render kiểm thử bằng sample của SET. Đây chỉ là phần Drum, chưa chứng minh toàn bộ Bass/accompaniment và voice của SET tương thích. Đầu ra ORGX hiện có limiter stereo chung, trần 0,98, giảm gain tức thời và hồi phục dần để tránh cắt ngọn khi nhiều track cộng lại. Limiter là xử lý bổ sung của ORGX, chưa đối chiếu với DSP gốc. Build debug và unit test đã đạt; APK đã cài lên thiết bị. Chất lượng nghe thực tế của thay đổi limiter chưa được xác nhận.

Màn hình SET có thêm lựa chọn `Preview Drum + accompaniment`. Chế độ này dùng sample và các nốt gốc của mọi track không mute trong nhóm; không chuyển hợp âm, chưa áp dụng DNC hay đầy đủ envelope/FX gốc. Trong SET thử, 32/46 nhóm mục 16–35 tương thích với chế độ preview; 14 nhóm còn tham số sample chưa hỗ trợ. Đã render hết độ dài 32 nhóm, kiểm tra tín hiệu hữu hạn, có âm thanh và giới hạn đỉnh stereo; có kiểm thử riêng chứng minh track nhạc đệm được cộng vào đầu ra. Chế độ Drum riêng vẫn có thể chọn. Parser giữ trường tune của event; track cần tune chưa triển khai sẽ bị chặn có thông báo. Tổng PCM nguồn mỗi nhóm giới hạn 64 MB trước khi giải mã. APK đã build, cài và khởi chạy trên thiết bị; chưa xác nhận bằng nghe trực tiếp hoặc đối chiếu âm thanh với APK gốc.

Sửa đường trộn nhạc đệm SET: khi chọn một style nhập, ORGX không cộng hợp âm tổng hợp mặc định vào đầu ra nữa (kể cả chế độ chỉ Drum). Track nhạc cụ type 1 đi qua mức Drums; track type 0 đi qua mức Chord. Hai mức chỉnh độc lập trên mixer, áp dụng cho cả stereo trước FX/master/limiter; đặt cả hai về 0 sẽ tắt phần style nhập, không ảnh hưởng bàn phím/pad. Kiểm thử sample riêng xác nhận tắt từng nhóm, phối mức khác nhau và reset. Đây là sửa mixer; chưa bổ sung chuyển hợp âm cho SET.

Cập nhật chuyển hợp âm thủ công: đã trích `Tracks._PlayGroup`, `Track.PlayNext`, `Track.CalcChordKey` và `Track.ReadFromStream` vào `analysis/source`. Parser SET giữ `fChordRoot`/`fChordType` của từng track, thay phần skip tương ứng. `ReferenceChordTransposer` chuyển bậc theo hai hàng trong bảng `Chord`, điều chỉnh root và đưa nốt về 21–108 theo hàm gốc. Có port các control 0–4; đường phát ORGX hiện dùng control 0, chưa nạp lựa chọn control/octave gốc.

Nhận diện phím trái: màn hình Chord có `Left keyboard chords`, lưu lựa chọn qua lần mở app. Khi bật, quãng tám bên trái của bàn phím hai quãng tám dùng để nhận hợp âm (không phát voice bàn phím), bên phải giữ giai điệu; vạch giữa phân vùng. `KeyboardChordInput` so khớp chính xác pitch-class với thư viện Chord khi có ít nhất ba pitch-class, ưu tiên root trùng nốt bass khi mask có nhiều cách gọi. Nhả phím hoặc tổ hợp không khớp giữ hợp âm cuối. Đây là thuật toán ORGX, chưa đối chiếu tương đương `Music.ChordFind` gốc. Chọn hợp âm thủ công/AUTO sẽ tắt chế độ phím trái. SET cần nạp với `Follow selected chord`; nốt mới của nhạc đệm dùng kết quả nhận diện trực tiếp, trống giữ nguyên. Chưa có single-finger, debounce hoặc chuyển nốt đang ngân. Kiểm thử trưởng/thứ/bảy, đảo thế, octave doubling, mask mơ hồ, release/hold và reset đã đạt. Bản debug đã cài, khởi chạy và xác minh checkbox chuyển bật trên thiết bị; chưa kiểm chứng nghe bằng thao tác đa chạm thực tế.

AUTO cho SET: khi style nạp với `Follow selected chord`, ba preset Pop (C–Am–F–G), Ballad (C–G–Am–F), Dance (Am–F–C–G) nay cung cấp hợp âm đích cho cùng bộ chuyển nốt như chọn tay/phím trái. `AutoChordProgression` tìm index qua mask pitch-class, tránh nhầm index của danh sách dựng sẵn với thư viện Chord nhiều hàng. Đồng hồ harmony tích lũy theo tempo, không tính lại vị trí ô nhịp từ toàn bộ số frame bằng BPM mới; STOP rồi PLAY khởi động lại vòng. Preset dùng 4/4, mỗi hợp âm một ô nhịp và đổi ở note-on mới; đây là vòng ORGX, chưa chứng minh giống vòng hoặc time signature của APK/SET gốc. Thiếu hợp âm tương ứng trong thư viện thì callback trả null và track phát nốt gốc. Có kiểm thử vòng bốn ô, thứ tự thư viện bị đảo, thiếu hợp âm, đổi tempo giữa ô, reset và note-on thực tế qua timeline/transposer ở ranh giới ô nhịp.

Sửa lỗi không thấy/chọn được style SET: luồng IMPORT trước đây chỉ đọc instruments, còn index style chỉ tạo khi nhấn OPEN lần nữa; index lại nằm trong state cục bộ mất khi đóng dialog. Đã dùng `ImportedSetBank.open` để đọc một lần cả instruments/styles/tracks trước khi kích hoạt, cache index cùng SET đang mở và khôi phục bank theo tham chiếu file đã lưu. Màn hình SET mặc định mở tab STYLES, có tab VOICES riêng và tìm kiếm tên style; lỗi đọc/nạp nằm ở đầu màn hình. Mixer có nút SET STYLES ở đầu thay cho nút style bị khóa.

Nguyên nhân trực tiếp khóa toàn bộ style hợp lệ đã xác định: biểu thức `index?.let { reason(...) } ?: "SET index unavailable"` biến kết quả null (nghĩa là được hỗ trợ) thành lỗi. Đã chuyển xử lý index thiếu vào `ImportedStyleSupport.reason`, giao diện dùng trực tiếp kết quả nullable, không bọc Elvis lần nữa. Có regression test phân biệt index thiếu, style hợp lệ và style không có track. Các kiểm thử render trước đó không kiểm tra điều kiện enabled của giao diện, nên không chứng minh được người dùng chọn style thành công.

Đối chiếu UI trên điện thoại sau bản sửa: SET mở ra có STYLES (46), VOICES (92); mục không có track bị khóa có lý do, mục tương thích chọn được. Đã chọn DJ NEW CLUB / VARIATION 1, màn hình chính hiện đúng tên và 141 BPM; nhấn PLAY chuyển nút sang STOP rồi dừng lại. Đóng/mở lại SET vẫn có 46 mục style. Log AndroidRuntime gần nhất không có lỗi. Ghi nhận trong `set-style-ui-check.json`. Chưa lặp thao tác nhập qua document picker trong kiểm tra này, chưa đo/nghe xác nhận audio và chưa tự khôi phục style đang phát sau khi khởi động lại ứng dụng.

**Thay thế cách hiển thị 46 mục rời ở trên:** `ImportedStyleCatalog` gom theo parent ID, mỗi style gồm INTRO 1–3 (16–18), MAIN 1–4 (32–35), FILL 1–4 (48–51), BREAK (64), ENDING 1–3 (80–82). Nhóm không có Main không xuất hiện như style độc lập; các phần mồ côi vẫn giữ trong index, không tự gán sang style khác. SET thử có 7 style, 86 phần trong các style đó; cả 7 style có ít nhất một Main tương thích ở chế độ Drum. Một phần vẫn có thể không hỗ trợ; không có nghĩa là cả 86 phần đã tương thích đầy đủ.

Chọn một tên style trong SET sẽ nạp các phần tương thích, quay về màn hình chính và hiện hàng nút phần có thể cuộn ngang. `ImportedStylePlayer` chia sẻ PCM giữa các phần, áp dụng ngân sách 64 MB cho toàn style trước giải mã. `ImportedStyleSession` giữ Main đã chọn: lúc dừng chọn phần rồi PLAY; lúc đang chạy xếp yêu cầu tới cuối phần hiện tại, hiển thị QUEUED. Intro/Fill/Break xong trở về Main cuối đã chọn, Ending xong dừng. Chuyển phần giữ BPM chung hiện tại; chưa triển khai mọi chế độ chuyển theo phách/ô nhịp hay tempo riêng từng phần của APK gốc. Phần không tương thích bị khóa, không dùng âm mặc định thay thế. Main 1 của DJ new club chưa có Drum tương thích, nên style này khởi đầu ở Main 2 trong chế độ Drum.

149 kiểm thử đã đạt, gồm gom theo parent, bỏ Pad và phần mồ côi khỏi danh sách style, chờ ranh giới phần, Intro trở về Main, Fill/Break trở về Main cuối, Ending dừng và chọn phần lúc dừng. Đã nạp cả 7 style từ SET thật trong kiểm thử; UI trên điện thoại xác nhận chọn DJ new club hiện các nút INTRO/MAIN/FILL và trạng thái QUEUED, Main 1 bị khóa còn các Main tương thích chọn được. Các chuyển tiếp tự động được kiểm chứng bằng unit test; chưa có bản thu audio đối chiếu toàn chuỗi với APK gốc.

Bản cuối đã cài và đối chiếu trên điện thoại: STYLES (7), chọn DJ AAA (12/12 phần tương thích ở chế độ Drum) hiển thị DJ AAA / MAIN 1. Cuộn hàng nút thấy MAIN 1–4, FILL 1–4, BREAK, ENDING 2; các Intro nằm đầu hàng. Không tạo nút cho phần không có trong SET. Log AndroidRuntime gần nhất không có lỗi.

Sửa thao tác phải nhấn PLAY thêm lần nữa: các nút phần trên màn hình chính nay gọi `AudioEngine.requestStyleSection`. Nếu đang dừng, nút phần gửi yêu cầu immediate và bật rhythm; audio thread chọn/reset đúng phần trước khi render, kể cả sau khi Ending kết thúc. Nếu đang phát, giữ quy tắc xếp hàng tới cuối phần hiện tại. Pending command dùng AtomicReference để chuyển yêu cầu giữa UI và audio thread. UI đồng bộ PLAY/STOP theo trạng thái engine ở cả hai chiều. Đây là sửa khởi phát theo nút, chưa khẳng định đầy đủ quy tắc chuyển phần/auto-fill của APK gốc.

151 kiểm thử đã đạt, bổ sung khởi phát ngay từng loại phần không cần reset/PLAY riêng và phát lại sau Ending. Kiểm tra điện thoại: DJ AAA ở trạng thái PLAY (đang dừng), chỉ bấm INTRO 1; quan sát tiếp theo có STOP và DJ AAA / MAIN 1 sau Intro, không có thao tác nhấn PLAY trong chuỗi kiểm tra. AndroidRuntime gần nhất không ghi lỗi. Chưa đo âm thanh hoặc đối chiếu chính xác thời điểm chuyển với APK gốc.

**Đính chính về độ tương đương hành vi:** đối chiếu mới trong `STYLE-BUTTON-AUDIT.md` xác nhận bộ chuyển phần ORGX hiện là mô hình đơn giản hóa, chưa port đầy đủ APK gốc. Mã gốc dùng List/Jump/LastLoop/JumpGroup, MeterX/Y, Limit, rhythmScale và fGroupPlayChangeMethod. Parser ORGX đang bỏ qua một số metadata này, session lại quyết định chủ yếu theo nhãn phần và hết độ dài. Vì vậy các con số unit test và thao tác UI trên đây không chứng minh Intro/Main/Fill/Break/Ending hoạt động giống APK gốc. Lượt audit bổ sung mã gốc và ghi rõ khoảng thiếu; chưa đổi hoặc cài lại engine style.

Sau audit đã thay bộ chuyển phần dựa trên mã gốc, chi tiết tại `STYLE-BUTTON-AUDIT.md`: giữ JumpGroup/Limit/Type/meter khi đọc SET, tính rhythmScale và căn phase, hàng đợi nhiều lệnh, Jump ưu tiên, LastLoop và tempo khi chuyển. Không suy Ending luôn dừng hay Intro luôn về Main: đọc đích trong dữ liệu. Có AUTO FILL theo MainActivity.y1 (Rhythm Fill tương ứng → RhythmJump Main), mặc định tắt. Nút đang dừng vẫn tự khởi phát. Chưa port đầy đủ các cài đặt UI, nhấn đôi tempLoop, fallback/biến thể GroupSection hoặc chuyển style đang chạy theo B1. Đích nhảy không hỗ trợ báo lỗi thay vì dùng phần mặc định. SET gốc không thay đổi.

Kiểm chứng bản chuyển phần mới: build debug và 162 unit test đạt (0 failure), cả 7 style của SET thử vẫn nạp được ở chế độ Drum. Có kiểm thử Main 4 giây chuyển ở rhythmScale 1 giây, hàng đợi nhiều lệnh, bỏ Main loop trùng, Auto Fill rồi Jump Main, JumpGroup dương/âm/0, đích thiếu báo lỗi, tempo lock, meter 3/4, mode 0–3 và căn phase khi LimitStart khác 0. APK đã cài và mở trên thiết bị, tiến trình chạy và log AndroidRuntime gần nhất không có lỗi. Chưa so sánh audio hoặc đầy đủ chuỗi nút giữa APK gốc và ORGX trên thiết bị.

Sửa tiếp phản hồi Intro tự chạy nối các Intro: đường nút `press` đặt Jump về LastLoop/Main khi chọn Intro 16–18, ưu tiên hơn JumpGroup lưu trong SET. Đây là bổ sung đích trở về cho thao tác nút; đường `request` mức thấp vẫn giữ nguyên hành vi đọc JumpGroup. Idle reset giữ Jump đi kèm yêu cầu khởi phát immediate để tránh mất lệnh ở ranh giới UI/audio thread. Regression test dùng Intro 1 có JumpGroup trỏ Intro 2: bấm Intro khi dừng hoặc đang chạy trở về Main đã chọn; yêu cầu playback thô vẫn nhảy Intro 2. Không thay đổi file SET. Chưa đối chiếu đầy đủ các trạng thái g2/f2 và chuỗi Intro khi đổi Main trong lúc Intro đang chạy của APK gốc.

Sửa giao diện biểu diễn: bỏ hai panel lớn gây cắt nút BPM/octave; đưa Style/Voice/Chord thành nút truy cập trực tiếp. Play/Stop, BPM ±1, octave, sustain và Auto Fill ở một hàng cố định. `StyleSectionControls` hiện 15 vị trí phần trên hai hàng, không cuộn ngang; vị trí Intro/Main/Fill/Break/Ending ổn định qua các style, phần không có/không tương thích khóa, phần hiện tại/chờ có nhãn riêng. Files/Project/MIDI/Pad/Ghi âm/Thông tin chuyển vào menu ORGX để dành chiều cao cho bàn phím. Nút phần cao 48dp và cách nhau 8dp. Màn hình SET gộp phần nhập/đổi file và tùy chọn nhạc đệm vào các mục mở rộng, tách STYLES/VOICES như trước. Build debug đạt và APK đã cài, mở trên thiết bị; ảnh trước/sau đã kiểm tra bố cục và phát hiện/sửa lỗi cắt nút, hàng điều khiển giấu Break/Ending. Đây là cải thiện thao tác giao diện, chưa phải bản sao skin APK gốc; chưa kiểm tra mọi cỡ màn hình/font scale.

Thay giao diện phẳng ở trên theo phản hồi: đã mở APK gốc trên thiết bị và xem ảnh thực tế, kiểm tra inventory skin. `InstrumentConsole` dựng mặt đàn Canvas theo bố cục tham chiếu: nền than có vân, màn hình thông tin trung tâm, nút kim loại và đèn, cụm tempo bên phải, cụm style ngay trên bàn phím. Tỉ lệ panel/phím 55/45; phím có gradient và viền thay màu phẳng. Đổi thương hiệu ORGX/hovait. Style/Voice trên LCD mở trình chọn, Menu giữ các chức năng phụ, tempo/octave/sustain/start/stop/auto-fill và các phần nối vào callback hiện có. Núm bên trái là lối mở Mixer, không phải núm xoay volume liên tục; núm tempo hiện mang tính hình ảnh, chỉnh bằng ±. Không thêm các nút chức năng gốc chưa có engine. Hộp SET/Voice/Chord vẫn dùng giao diện cũ, chưa sao chép skin gốc; Canvas chưa có ngữ nghĩa trợ năng riêng cho từng nút. Build debug đạt, cài và mở trên thiết bị, ảnh màn hình thực tế đã xem; kiểm tra chạm vùng Style trên LCD mở SET memory. Chưa thể gọi giao diện này giống 100% APK gốc.

Chọn style trên mặt đàn: vùng Style và nút SET nay mở trang STYLE SELECT trong chính LCD, không mở modal. Sáu ô mỗi trang, PREV/NEXT và EXIT; bàn phím/cụm phần không bị che. Dùng các nhóm style của bank SET đang mở, nạp trên IO và báo LOADING/lỗi trong LCD, nạp xong trở về màn hình chính. Không có bank SET thì dùng danh sách drum dựng sẵn. Đường chọn nhanh SET hiện dùng chế độ Drum; chọn đầy đủ nhạc đệm/chuyển hợp âm vẫn ở phần quản lý SET cũ. Import/quản lý file SET tách vào MENU. Nút SET STYLES trong Mixer cũng chuyển sang trang LCD. Đã build, cài và xem ảnh trang LCD trên thiết bị, thấy danh sách DJ new club/DJ AAA… trong LCD, không có nền modal. Các hộp Voice/Chord/Mixer chưa chuyển sang trang LCD; chuyển style khi đang phát hiện vẫn dừng rhythm, chưa giữ chuyển tiếp liên tục như mọi chế độ gốc.

Kiểm kê toàn file SET: xem `KIEM-KE-SET.md`, `SET-AUDIT-SUMMARY.json` và công cụ chỉ đọc `audit_set.py`. File 294.227.939 byte phiên bản 1.2.8 đã được đi qua hết cấu trúc, không có byte đuôi; hash trước/sau không đổi. Có 154 instrument, 158 sound, 4.102 vùng, 31 mode, 1 system, 370 track/23.204 event, 112 STS, 105 group, 516 FX block/149 module. PCM tổng 292.970.840 byte: 26.552.898 wave-bank và 266.417.942 inline. Cả 348 bank wave đều có tham chiếu. 461.865 byte khối/field còn bỏ qua được ghi vị trí trong báo cáo chi tiết tại `local-set-check/full-set-audit.json`; đi qua toàn file không đồng nghĩa hiểu/dùng mọi byte. Báo cáo phân biệt dữ liệu ORGX đã dùng, chỉ đọc, bỏ qua, chưa hỗ trợ và reserved. Một đích nhảy dương chưa tìm thấy group cần đối chiếu fallback/UI gốc. Lượt kiểm kê không sửa SET và không thay engine; ORGX chưa tương thích đầy đủ các mode/DNC/FX/system/channel/STS của file.

Sửa engine sau kiểm kê: sound SET có `ImportedEnvelope`, cấp state riêng cho mỗi voice bàn phím/style, áp dụng ADS/release/auto-release thay envelope bàn phím mặc định và cắt release track cố định 10 ms. Parser legacy ADS chuẩn hóa 0/0/100 theo Instrument.ReadFromStream trước 1.3.1. Kiểm thử attack/decay/sustain, nhả giữa attack, auto-release và release track vượt 10 ms. Chỉ mô phỏng nhánh pedal mode 0, chưa đầy đủ ForceLoop/tick/DNC. Start khác 0 vẫn chặn, chưa đoán thành vị trí sample. LCD chọn style có MODE: DRUM / FULL ORIGINAL NOTES / FULL FOLLOW CHORD, lưu lựa chọn và áp dụng cùng bộ kiểm tra tương thích trước nạp; không còn buộc chọn nhanh luôn chỉ trống. FULL nghĩa là toàn bộ track tương thích trong phần, không phải tương thích toàn bộ cấu hình SET gốc.

Build debug và 169 unit test đã đạt cho bản envelope/MODE, gồm kiểm thử timeline track nhả kéo dài đúng cấu hình thay vì bị cắt sau 10 ms. APK đã cài, mở trên thiết bị và không có lỗi AndroidRuntime gần nhất. Chưa thu/nghe đối chiếu tiếng của bản envelope với APK gốc. Các dữ liệu mode/layer/DNC, FX chain, system tuning và STS vẫn chưa áp dụng đầy đủ; lượt sửa không thay file SET.

Trong SET, bật `Preview Drum + accompaniment` rồi `Follow selected chord`, nhấn PLAY và chọn hợp âm thủ công trong thư viện Chord. Chỉ note-on mới của track type 0 được đổi; track Drum và ID note-off giữ nguyên. Khi AUTO bật hoặc thiếu thư viện hợp âm, phát nốt gốc. Chế độ này nạp toàn bộ vùng của sound đầu để nốt chuyển chọn đúng sample, nên điều kiện tương thích nghiêm hơn preview nốt gốc; ngân sách PCM toàn nhóm vẫn 64 MB. Bank melodic exact-note chưa hỗ trợ chuyển hợp âm sẽ bị chặn để tránh mất tiếng. Trong SET thử có 26 nhóm qua điều kiện và render thử một giây/nhóm, đổi C trưởng sang C thứ giữa lượt; có unit test chuyển bậc/root, control/octave, giới hạn nốt, thay hợp âm giữa các note-on và release theo ID. Chưa xác nhận nghe/so sánh với APK gốc, nhận diện hợp âm từ phím trái, đổi nốt đang ngân, AUTO và DNC vẫn chưa đầy đủ.
# Sửa khôi phục SET khi khởi động

## Xuất toàn bộ APK để đối chiếu

Đã nối keyboard MODE: transpose/octave melodic trước chọn sample, channel volume/pan và DSP 4/6/20 sau cộng note/layer. Echo bus tiếp tục sau khi thả hết phím; style không bị áp MODE lần thứ hai. Imported keyboard dùng tổng tuyến tính, không trung bình theo polyphony như đường cũ; limiter cuối vẫn giữ. Build/kiểm thử đạt, chưa so sánh loudness/âm thanh bản thu với APK gốc. Aftertouch/script/mode Effects và FX Music toàn cục còn thiếu.

Đã giữ FX header/module của mode và dùng tune/release khi nạp voice MODE trực tiếp. Đối chiếu thứ tự `Track.ReadFromStream`: mode được chọn trước rồi channel đã lưu ghi đè, nên style không tự dùng FX mặc định mode thay cấu hình track. Kiểm thử PCM stereo/ưu tiên cấu hình và release đạt. Chưa nối đầy đủ DSP/gain/pan/transpose mode cho tổng voice bàn phím, aftertouch/script còn thiếu.

Đã dựng fallback section còn thiếu về group 0 theo caller gốc, áp dụng vào catalog và loader thật; không thay section riêng bằng fallback. Giữ ChordType/ChordBase của TrackGroup. Kiểm thử nạp/request PCM fallback và không nhân bản/không lấy nhầm style đạt. Chưa dựng ChordVars variant ở định dạng SET mới; bản 1.2.8/1.4.6 hiện hỗ trợ không lưu trường này.

Đã dựng option nhấn lại section để đặt `tempLoop` theo `MainActivity.y1` và event 204, mặc định tắt. Settings có “Repeat section button to loop”, LCD hiện TEMP LOOP khi đang lặp tạm. Intro/Ending giữ lặp, chọn Main khác thoát được; option tắt giữ one-shot. Toàn bộ kiểm thử đạt trước cập nhật polling LCD; bản cuối build và kiểm thử session/tempLoop đạt. Chưa hoàn thiện GroupSection/control-mode khác.

Đã sửa restart cùng section theo `GroupPlayNext`: không gọi đóng nốt như khi đổi group; giữ voice/sample age và FX track khi reset timeline. Khởi tạo lại timeline không xóa channel lần thứ hai. Stop/reset vẫn xóa. Kiểm thử riêng phân biệt nốt còn vang với sự kiện phát lại sau seek. Chưa dựng nhấn đôi tempLoop và đầy đủ control-mode.

Đã giữ state FX từng track trong release tail khi đổi phần, kể cả echo còn ngân sau khi voice kết thúc. Source có chain mới để reset/reuse không làm mất đuôi đã chuyển. Kiểm thử impulse sau chuyển/reset source đạt; Stop destination xóa đuôi. Giới hạn module 4/6/20, ngưỡng im lặng và 32 nhóm tail; chưa hoàn chỉnh Ending hoặc restart cùng player.

Đã bổ sung nhả voice section/style cũ khi chuyển sang player khác, thay vì bỏ ngay tiếng đang vang. Giữ envelope/sample age, routing drum/chord và mang tiếp tail khi chuyển nhanh; Stop/reset xóa. Kiểm thử release/routing/chuyển liên tiếp đạt. Chưa giữ state FX riêng từng track trong tail, chưa xử lý đầy đủ đuôi sau Ending hoặc restart cùng player.

Đã giữ buffer group/rhythm FX khi chuyển section/style có cùng cấu hình; chọn lại section có thể giữ bus, Stop/reset xóa. Kiểm thử impulse và đuôi sau chuyển đạt, chưa đối chiếu bản thu. Đổi tham số/module và voice release của track cũ chưa mô phỏng đầy đủ theo mixer gốc; xem [DECODE-REBUILD.md](DECODE-REBUILD.md).

Đã nối global rhythm FX fallback và volume/balance vào output style, giữ ba block Music/Wave/Rhythm từ SET. SET cục bộ có rhythm volume 100/balance 2; module 20 bật riêng nhưng block FX tắt nên không tự bật DSP. Toàn bộ 201 kiểm thử đạt, build/cài APK thành công. DSP Music/Wave, các module còn thiếu và đuôi xuyên section chưa hoàn chỉnh.

Đã giữ FX block của group (format từ 1.4.2), dựng kế thừa phần → cha → group 0 và chạy DSP đã hỗ trợ sau mixer drum/chord. Kiểm thử fallback/chu kỳ/PCM đạt. SET 1.2.8 không lưu group FX ở layout này; chưa hoàn thành FX global rhythm và giữ đuôi xuyên section. Chi tiết tại [DECODE-REBUILD.md](DECODE-REBUILD.md).

Đã dựng Stereo Echo type 4 theo `t01`: phase tam giác điều biến output hai kênh, feedback đầy đủ và độc lập. Delay 0 báo lỗi thay vì chia cho 0. Build và toàn bộ kiểm thử đạt; chưa so sánh bản thu/độ lượng tử với APK gốc. Reverb và chuỗi FX mode/global/group vẫn chưa hoàn chỉnh.

Đã bổ sung Echo Delay type 6 vào chain FX track theo `s01`: delay/feedback từ SET, stereo độc lập, reset buffer, giữ thứ tự stage 6/20. Kiểm thử độ trễ và feedback đạt; toàn bộ build/kiểm thử đạt. Chưa dựng stereo modulation type 4, reverb và toàn bộ FX mode/global/group.

Đã dựng DSP Bass Booster type 20 cho track SET theo `p01`, giữ cờ bật/tắt và payload module FX trong index track. Kiểm thử impulse/stereo/bypass/tích hợp player đạt; toàn bộ 191 kiểm thử đạt, đã cài APK. Các module FX khác và chuỗi FX global/mode/group chưa hoàn chỉnh; xem [DECODE-REBUILD.md](DECODE-REBUILD.md).

Đã bổ sung đọc và áp dụng tune/transpose/octave/release-percent của channel SET theo `Channels.OpenKey`. Track melodic chọn sample sau transpose/octave; percussion giữ nốt và tune cũ. Thiết lập kênh tham gia khóa cache để các track không dùng chung sai envelope/sample. Header được dựng từng trường có bằng chứng, chưa phải toàn bộ module FX/DSP.

Đã bổ sung đọc system và bảng tuning 24 phím theo `System_.ReadFromStream`/`Music.KeyToPitchEx`: style dùng system riêng của từng track, voice SET dùng system 0, percussion không chịu tuning melodic. Giữ StartKey/SelectedKeys nhưng chưa có giao diện chỉnh hai trường này. Build và toàn bộ kiểm thử đạt; kiểm thử riêng xác nhận bảng thay đổi PCM melodic nhưng không đổi percussion và phát hiện tham chiếu system thiếu. Xem [DECODE-REBUILD.md](DECODE-REBUILD.md) để biết phần còn chưa dựng.

Đã bổ sung default mode và Mix layer vào engine bàn phím/style: đọc 31 mode / 3 Mix slot bật trong SET cục bộ, chọn primary sound theo mode, trộn gain/pan từng layer và dùng envelope độc lập. Bộ chọn Voice có thêm mục MODE, lựa chọn được lưu/khôi phục qua cơ chế sound selection hiện tại. Build/toàn bộ kiểm thử và lượt kiểm tra lại mode/SET đều đạt; đã cài bản mới. Chưa hoàn thành aftertouch, script và DSP/DNC trong mode.

Đã xuất toàn bộ nguồn/tài nguyên bằng JADX vào `analysis/decoded-original/`: 22.173 file Java, đầu vào 27.265 class gồm DEX trong assets. Có 655 lỗi giải mã, nên đây là tài liệu đối chiếu, chưa phải project gốc có thể build. Xem [DECODE-REBUILD.md](DECODE-REBUILD.md) về cách tái tạo, ranh giới Java/native và thứ tự dựng lại vào `orgx/`.

## Tương thích style SET bổ sung

Đã sửa Type 2/PitchMode 1, bỏ chặn trường Start theo đường phát PCM gốc và hỗ trợ Ending không bật Limit. Bộ kiểm tra SET cục bộ xác nhận 7 style / 86 phần đều nạp được ở chế độ follow chord; 175 kiểm thử đạt, build APK thành công. Chi tiết và giới hạn còn lại: [SET-STYLE-COMPATIBILITY.md](SET-STYLE-COMPATIBILITY.md). Kết quả này xác nhận nạp và chạy frame âm thanh, chưa xác nhận âm sắc/DNC/FX giống bản gốc 100%.

- Giao diện theo dõi index SET được khôi phục bất đồng bộ, không cần mở lại bộ chọn style để cập nhật.
- Lưu ID style cùng tên file SET và chế độ phát tại cả hai nơi chọn style; lần mở sau khôi phục session nhưng không tự bật phát.
- Trong khi khôi phục hiển thị `LOADING SAVED SET...`, chặn Play/style để tránh phát nhầm mặc định. Với dữ liệu cũ chưa lưu ID style, cần chọn style một lần; sau đó ứng dụng nhớ lựa chọn.
- Giữ nguyên file SET nhập vào.

