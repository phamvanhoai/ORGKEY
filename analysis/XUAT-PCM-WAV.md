# Đối chiếu kho PCM và xuất WAV

Parser đã đối chiếu toàn bộ 2.001 section trong `Default.Template` với 1.251 entry của kho wave. Kết quả:

- 2.001/2.001 section ánh xạ được bằng `storedIndex = WaveIndex - 1`;
- 2.001/2.001 kích thước khớp chính xác `Length × Channels × 2`;
- cả 1.251 entry wave đều được ít nhất một section tham chiếu;
- không có wave không được dùng và không có section trỏ ra ngoài kho.

Điều này xác nhận các entry là PCM 16-bit little-endian, `WaveIndex` trong section là 1-based, còn `index` trong kho wave là 0-based. Một entry có thể được nhiều section dùng lại với metadata khác; ví dụ cùng dữ liệu trống/bộ gõ có thể xuất hiện với sample rate khác trong section.

## Công cụ xuất

`export_template_wav.py` nhận asset, chỉ số Instrument, chỉ số sound và note index. Nó hỗ trợ cả hai cách lưu:

- PCM nội tuyến của `empty.Template` 1.2.4;
- PCM trong kho wave chung của `Default.Template` 1.4.6.
- PCM nội tuyến của `Constant.Instrument` và `Sound.Instrument` 1.4.2.

Ví dụ:

```powershell
py -3 analysis\export_template_wav.py `
  --asset assets/Default.Template `
  --instrument 1 --sound 0 --note 61 `
  --output analysis\extracted-audio\piano-dnc-csharp4.wav
```

Script kiểm tra kích thước PCM trước khi ghi, sau đó tạo WAV PCM 16-bit với channel count và sample rate của section. Loop metadata được xuất vào file JSON kèm theo khi dùng `--metadata`; WAV hiện chỉ có header PCM chuẩn và chưa ghi chunk `smpl`, nên trình phát WAV không tự lặp đoạn loop.

## Bốn file kiểm chứng đã xuất

| File | Nguồn | Định dạng | Thời lượng | SHA-256 WAV |
| --- | --- | --- | ---: | --- |
| `metronome-long.wav` | empty, note 0 | mono 48 kHz, 17.800 frame | 0,3708 s | `a335f9f7337ebb27ebbdded40a13bc1b24ec816ece8794098b78c700b84fe85f` |
| `metronome-short.wav` | empty, note 1 | mono 48 kHz, 12.031 frame | 0,2506 s | `334b915a6c68589f28ae3e71d12a073aa89adea8f97c8ca61c48037ad0e8deb7` |
| `piano-low-eb2.wav` | empty, Instrument 1/note 63 | mono 44,1 kHz, 119.109 frame | 2,7009 s | `9aef20ce5f7c06929bb9e979260bb7cecddbeaa79d62bf5272ef71ad987f85a8` |
| `piano-dnc-csharp4.wav` | Default, Instrument 1/note 61 | mono 44,1 kHz, 77.309 frame | 1,7530 s | `466ed25b1a0eb4f6a7a30d5c5353494fc38d57b0d9232504c3a32c9d8dfdc06a` |

Các WAV và metadata tương ứng nằm trong `analysis/extracted-audio/`. Python `wave` đã đọc lại đúng channel, sample rate, số frame và số byte PCM của cả bốn file.

Việc xuất WAV chỉ chuyển container, không áp dụng volume, pan, tune, ADSR, loop, FX hoặc pitch mapping của engine. Âm thanh khi nghe file WAV thô vì vậy có thể khác âm thanh phát trong ứng dụng.
