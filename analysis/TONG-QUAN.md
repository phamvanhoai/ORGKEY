# Tổng quan APK ORG 24

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

Trong SET, bật `Preview Drum + accompaniment` rồi `Follow selected chord`, nhấn PLAY và chọn hợp âm thủ công trong thư viện Chord. Chỉ note-on mới của track type 0 được đổi; track Drum và ID note-off giữ nguyên. Khi AUTO bật hoặc thiếu thư viện hợp âm, phát nốt gốc. Chế độ này nạp toàn bộ vùng của sound đầu để nốt chuyển chọn đúng sample, nên điều kiện tương thích nghiêm hơn preview nốt gốc; ngân sách PCM toàn nhóm vẫn 64 MB. Bank melodic exact-note chưa hỗ trợ chuyển hợp âm sẽ bị chặn để tránh mất tiếng. Trong SET thử có 26 nhóm qua điều kiện và render thử một giây/nhóm, đổi C trưởng sang C thứ giữa lượt; có unit test chuyển bậc/root, control/octave, giới hạn nốt, thay hợp âm giữa các note-on và release theo ID. Chưa xác nhận nghe/so sánh với APK gốc, nhận diện hợp âm từ phím trái, đổi nốt đang ngân, AUTO và DNC vẫn chưa đầy đủ.
