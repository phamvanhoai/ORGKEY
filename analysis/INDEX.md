# Mục lục phân tích ORG 24

## Bắt đầu

- [Tổng quan](TONG-QUAN.md)
- [Luồng khởi động ứng dụng](APP-STARTUP.md)
- [Manifest kết quả và SHA-256](ANALYSIS-MANIFEST.md)
- [Coverage tài nguyên](ASSET-COVERAGE.md)
- [Kiểm chứng tái lập](VALIDATION.md)
- [Chữ ký APK](APK-SIGNATURE.md)

## Định dạng dữ liệu âm nhạc

- [SET/Template và Instrument](DINH-DANG-SET-INSTRUMENT.md)
- [Default.Template](DEFAULT-TEMPLATE-INVENTORY.md)
- [empty.Template](EMPTY-TEMPLATE-INVENTORY.md)
- [Instrument assets](INSTRUMENT-ASSETS.md)
- [Collection, Track và Event](COLLECTION-TRACK-EVENT.md)
- [STS, Channels và Effect](STS-CHANNELS-EFFECT.md)
- [Vòng đời event nốt](EVENT-NOTE-LIFECYCLE.md)
- [data.zip: Chord/System](DATA-ZIP-CHORD-SYSTEM.md)
- [Script engine](SCRIPT-ENGINE.md)

## Hợp âm và MIDI

- [Bảng Chord](CHORD-ASSET.md)
- [Chord nested](NESTED-CHORD-ASSETS.md)
- [MIDI Model](MIDI-MODEL.md)
- [Luồng điều khiển MIDI](MIDI-CONTROL-FLOW.md)

## Audio Java và luồng phát

- [Luồng bấm phím đến phát âm](LUONG-PHAT-AM.md)
- [Cài đặt âm thanh](CAI-DAT-AM-THANH.md)
- [Chế độ âm bổ sung](CHE-DO-AM-BO-SUNG.md)
- [Callback audio](CALLBACK-AUDIO.md)
- [Khởi tạo OpenSL và hai callback](NOPENSL-START-CALLBACKS.md)
- [Thu âm OpenSL và microphone mix](OPENSL-RECORDING.md)
- [Thu âm fallback bằng AudioRecord](FALLBACK-AUDIORECORD.md)
- [Fallback AudioTrack](FALLBACK-AUDIOTRACK.md)
- [SoundPool và WAV writer](SOUNDPOOL-VA-WAV-WRITER.md)
- [Vòng đời WAV](VONG-DOI-WAV.md)
- [Xuất PCM/WAV](XUAT-PCM-WAV.md)
- [Xác minh DEX](XAC-MINH-DEX.md)

## Native audio

- [Phân tích native audio](NATIVE-AUDIO.md)
- [Coverage native](NATIVE-COVERAGE.md)
- [Sở hữu thư viện](NATIVE-OWNERSHIP.md)
- [ABI inventory](NATIVE-ABI-INVENTORY.md)
- [So sánh JNI giữa ABI](NATIVE-ABI-JNI-COMPARISON.md)
- [Symbol ARM64](NATIVE-ARM64-SYMBOL-INVENTORY.md)
- [Danh sách JNI](NATIVE-JNI-SYMBOLS.md)
- [Coverage JNI](NATIVE-JNI-COVERAGE-GAPS.md)
- [Import OpenSL](NATIVE-IMPORTS.md)
- [Import NMusic](NMUSIC-IMPORTS.md)
- [Call graph](NATIVE-CALL-GRAPH.md)
- [Hiệu ứng native](NATIVE-EFFECT-JNI.md)
- [Kích thước hàm hiệu ứng](NATIVE-EFFECT-SIZES.md)
- [SF2 và LAME](NSF2-LAME-JNI.md)

## Pitch shift

- [Module pitch shift](PITCH-SHIFT-MODULE.md)
- [Call site từ NMusic](PITCH-SHIFT-CALLSITE.md)
- [Coverage libnpitchshift](NPITCHSHIFT-COVERAGE.md)
- [JNI wrapper](NPITCHSHIFT-WRAPPERS.md)
- [Vòng đời](NPITCHSHIFT-LIFECYCLE.md)
- [PLT và lời gọi](NPITCHSHIFT-CALLS.md)
- [Call map import](NPITCHSHIFT-IMPORT-CALLS.md)

## Giao diện và catalog

- [Skin/Part](SKIN-FORMAT.md)
- [Menu/FAQ](MENU-ASSETS.md)
- [Download catalog](DOWNLOAD-CATALOG.md)
- [Tài nguyên phụ](REMAINING-ASSETS.md)

## SDK bên thứ ba

- [Giải mã assets/dic](DIC-ASSET.md)
- [Nhận dạng libnms](LIBNMS-IDENTITY.md)
