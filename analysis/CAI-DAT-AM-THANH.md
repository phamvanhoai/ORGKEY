# Cài đặt âm thanh và giá trị lưu

Cập nhật bằng [DEX](XAC-MINH-DEX.md): field Activity mặc định mode 1, 44100 Hz, buffer Auto; Keyboard/Microphone Balanced, Style High Performance. Đây là fallback field khi chưa có preference, chưa phải xác nhận cấu hình thực tế trên thiết bị.

Đọc tiếp: [chuẩn bị âm bổ sung mode 3/4 và cơ chế bổ sung khóa preference](CHE-DO-AM-BO-SUNG.md).

Đã nối mã Java bằng REA và đọc trực tiếp binary XML `res/tN.xml` cùng cấu hình mặc định của `resources.arsc`. Nhãn dưới đây là chuỗi tiếng Anh trong cấu hình tài nguyên mặc định, không bảo đảm giống ngôn ngữ hiện trên điện thoại.

## Low-latency method

Khóa lưu: `output_low_latency`.

| Giá trị | Nhãn trong APK | Đường xử lý đã xác định |
| --- | --- | --- |
| `0` | Disabled | Không chọn OpenSL bằng điều kiện mode 1/2 của `OutDeviceStart`; có đường fallback AudioTrack. |
| `1` | Real (OpenSL Method 1) | Nếu OpenSL khởi động thành công: callback gọi Java `Music.Process`, rồi Put và enqueue. |
| `2` | Real (OpenSL Method 2) | Nếu OpenSL khởi động thành công: thread Java trộn và gọi Write/MixWrite. |
| `3` | Play an additional sound | Nhãn tài nguyên xác nhận chế độ âm bổ sung; chưa nối trọn mọi nhánh triển khai. Không phải mode OpenSL trong OutDeviceStart. |
| `4` | Play recorded note | Nhãn tài nguyên xác nhận chế độ nốt ghi sẵn; chưa nối trọn mọi nhánh triển khai. Không phải mode OpenSL trong OutDeviceStart. |

Không được hiểu chữ “Real” trong nhãn là số đo latency. Hiệu quả thực tế còn phụ thuộc thiết bị, cấu hình và việc OpenSL có khởi động được hay không.

## Các cài đặt liên quan

| Khóa | Nhãn | Ánh xạ |
| --- | --- | --- |
| `output_buf` | Touch performance | `0` Auto; `1` Very Fast; `2` Fast; `3` Normal; `4` Slow; `5` Very Slow. |
| `output_samplerate` | Output wave type | 48000, 44100, 32000, 24000, 22050, 16000, 12000, 11025 hoặc 8000 Hz; các nhãn đều ghi 16 bits, stereo. |
| `output_music` | Keyboard | `0` High Performance; `1` Balanced; `2` High Quality (Powerful devices); `3` Best Quality (Very powerful devices). |
| `output_wave` | Microphone | Cùng bốn giá trị chất lượng trên. |
| `output_rhythm` | Style | Cùng bốn giá trị chất lượng trên. |

Các mức chất lượng tương ứng biến mixer đã phân tích ở native: mode 0 đọc mẫu trực tiếp, mode 1 tuyến tính, mode 2/3 có nhánh bốn điểm. Chưa chứng minh mọi khác biệt giữa mode 2 và 3.

## Nơi nạp trong mã

`MainActivity.onCreate` gọi `I()`, và `I()` gọi `O0()`. Trong `O0`:

```text
P1 = r1(P1, "output_samplerate")
Q1 = r1(Q1, "output_buf")
R1 = r1(R1, "output_low_latency")
...
music.OutFormatSet(P1, 16, 2, Q1)  // trong một nhánh của hàm
...
music.fLowLatencyMethod = R1
```

Hàm còn gán `fMixerMusic`, `fMixerWave`, `fMixerRhythm` từ các giá trị đã đọc. `r1(default, key)` gọi `Integer.parseInt(s1(key, Integer.toString(default)))`. Chưa đọc trọn `s1` hoặc các field khởi tạo, nên chưa kết luận giá trị mặc định khi cài mới, cơ chế migration hay hành vi khi dữ liệu lưu không hợp lệ.

Đây là phép gán field trực tiếp, giải thích vì sao danh mục tham chiếu đến setter `set_fLowLatencyMethod` trả 0. Kết quả 0 của setter không được dùng để kết luận chế độ không có nơi cấu hình.

## Bằng chứng và cách đọc lại

- `load-settings-O0.json`, `main-init-I.json`, `main-oncreate.json`, `read-int-setting.json`: bằng chứng REA.
- `audio-preferences-xml.json`: thuộc tính XML với resource ID gốc.
- `audio-preferences-resolved.json`: nhãn và entryValues đã giải tham chiếu ở cấu hình mặc định.
- `inspect_setting_xml.py`: đọc XML và resource table trực tiếp từ APK, không chạy mã ứng dụng; hỗ trợ bảng entry thông thường, từ chối sparse/offset16 thay vì diễn giải sai. Không phải parser Android resource tổng quát.
- `find_dex_field_candidates.py` và `latency-field-candidates.json`: bước tìm ứng viên truy cập field bằng quét code unit. Có thể có false positive; kết luận về `O0` đã được kiểm tra lại qua thân hàm REA, không dựa riêng vào phép quét này.

Chạy lại:

```powershell
py -3 analysis\inspect_setting_xml.py '.\ORG+24_+Your+Music_2026.10.01_APKPure.apk'
```

Chưa đọc đầy đủ đường ghi preference từ UI hoặc xác nhận cài đặt đang lưu trên thiết bị. Không thay đổi APK hoặc cài đặt điện thoại trong quá trình phân tích.
