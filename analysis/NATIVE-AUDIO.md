# Bộ trộn và đầu ra âm thanh native ARM64

Đã phân tích tiếp [callback và đường enqueue OpenSL](CALLBACK-AUDIO.md), nối buffer `0xaf48` đến queue và xác định hai chế độ Java/native.

Phân tích tĩnh APK `2026.10.01`. REA đã cung cấp luồng Java/JNI; lượt này dùng Capstone 5.0.9 và pyelftools 0.33 để đọc trực tiếp ELF trong APK. Chưa chạy ứng dụng, chưa giả lập native và chưa đo âm thanh.

## Bằng chứng có thể kiểm tra lại

`native/*.asm` chứa địa chỉ ELF, byte lệnh và disassembly của năm hàm. Script `disassemble_audio.py` đối chiếu độ dài giải mã với kích thước hàm trong bảng ký hiệu; cả năm hàm được giải mã đủ 7.032 byte (1.758 lệnh ARM64). Điều này chứng minh độ phủ byte trong các phạm vi đã chọn, không chứng minh mọi lệnh đều có thể chạy hoặc cách diễn giải thuật toán hoàn toàn chính xác.

| Thư viện | SHA-256 |
| --- | --- |
| `lib/arm64-v8a/libnmusic.so` | `282be49ee24cb3822cc941fb9003d9259c93cd6ea56238426f4f24b018d78156` |
| `lib/arm64-v8a/libnopensl.so` | `dc900f9de170ec71471d8b5d2ccecc14d0bbb8c1d2346bd6e149467c888b38cf` |

Script ánh xạ lời gọi PLT bằng địa chỉ GOT và ELF relocation; các tên `@plt` trong assembly có nguồn từ bảng ký hiệu, không đoán theo hình dạng hàm. Các lời gọi gián tiếp JNI/OpenSL chưa được chú thích tự động.

## 1. Put: chép buffer, chưa phải thao tác enqueue

Hàm `Java_sofeh_audio_NOpenSL_Put`, `0xf28..0xfc4`:

- Nếu mảng đầu vào null thì trả về.
- Đọc độ dài mảng Java qua JNI offset `0x558`.
- Lấy số phần tử nhỏ hơn giữa độ dài mảng và số yêu cầu; nếu không dương thì dừng; giới hạn tối đa `0x4000` = 16.384 phần tử.
- Gọi JNI offset `0x650` để chép vùng `short[]` từ phần tử 0 vào buffer native tại địa chỉ ELF `0xaf48`.
- Ghi số phần tử hợp lệ vào biến toàn cục `0x12f48`; có nhánh đặt lại bộ đếm tại `0x12f4c`.

Hai offset tương ứng `GetArrayLength` (index 171) và `GetShortArrayRegion` (index 202) trong bảng JNI 64 bit, đã đối chiếu `jni.h` cục bộ và [đặc tả JNI của Oracle](https://docs.oracle.com/en/java/javase/21/docs/specs/jni/functions.html). Bảng đối chiếu nằm trong `native/jni-table.csv`.

Thân hàm này không gọi trực tiếp một API enqueue OpenSL. Vì vậy mô tả đúng hơn là **chuẩn bị buffer cho tầng phát native**, chưa đủ bằng chứng để khẳng định âm thanh đã ra loa ngay khi `Put` trả về. Consumer/callback của buffer còn cần đọc tiếp.

Giới hạn 16.384 là số phần tử 16 bit. Nếu dữ liệu stereo xen kẽ và số phần tử chẵn, nó tương ứng tối đa 8.192 frame; không suy ra sample rate hoặc độ trễ thực tế từ giới hạn này.

## 2. MixPut: trộn mono vào stereo

Hàm `Java_sofeh_audio_NOpenSL_MixPut`, `0x1240..0x1558`, đọc hai mảng Java. Với số lượng hợp lệ thông thường, nó giới hạn theo chiều dài mảng stereo, số lượng yêu cầu, hai lần chiều dài mảng mono và trần 16.384 phần tử.

Ở vòng lặp scalar `0x13fc..0x1444`, một mẫu mono được nhân với hai giá trị gain riêng cho trái/phải. Phép nhân với hằng `0x51eb851f` và dịch bit là cách mã máy thực hiện phép chia cho 100. Diễn giải cho dữ liệu và gain trong miền thông thường:

```text
L[k] = low16(L[k] + trunc(mono[k] * gainLeft / 100))
R[k] = low16(R[k] + trunc(mono[k] * gainRight / 100))
```

Có vòng lặp NEON xử lý theo nhóm và vòng lặp scalar xử lý phần dư. Phép ghi `strh` và cộng vector 16 bit giữ 16 bit thấp; không thấy phép cộng bão hòa trong các vòng lặp này. Chưa kiểm thử mọi trường hợp tràn số, số lượng lẻ hoặc tham số bất thường, nên công thức trên chưa phải bản tương đương byte cho mọi đầu vào.

Sau trộn, hàm chép dữ liệu đến buffer native `0xaf48`, cập nhật số lượng và có các nhánh ghi trả mảng Java bằng `SetShortArrayRegion` (offset `0x690`, index 210). Nó còn xử lý một vùng buffer phụ theo trạng thái toàn cục; chưa đặt tên chắc chắn cho cơ chế đó.

## 3. ChannelProcess: đọc mẫu và tích lũy vào bus

Hàm `Java_sofeh_music_NMusic_ChannelProcess`, `0x248c..0x2f8c`, có 704 lệnh.

Phần đầu lấy các field từ đối tượng Channel qua JNI. Tên field được giữ trong `.rodata`: `Position`, `iPitch`, `diPitch`, `dPitchChangeValue`, `iVolumeL/R`, `diVolumeL/R`, `VolumeMoveL/R`. Hàm lấy địa chỉ direct buffer chứa mẫu bằng `GetDirectBufferAddress` và truy cập các bus `long[]` bằng `GetLongArrayElements`, sau đó release với mode 0 để ghi nhận thay đổi.

Các nhánh đã nhận diện:

| Giá trị mixer | Quan sát từ lệnh máy |
| --- | --- |
| `0` | Chuyển vị trí chia cho 8.192 sang chỉ số mẫu, đọc mẫu signed 16 bit, nhân volume nguyên rồi cộng vào bus 64 bit. |
| `1` | Đọc hai mẫu, tính phần lẻ của vị trí và nội suy tuyến tính bằng công thức `p0 + t * (p1 - p0)`, nhân gain và cộng vào bus. |
| `2` hoặc `3` | Nhánh kiểm tra `(mixer & ~1) == 2`; dùng bốn mẫu và đa thức bậc ba có các hệ số `2, -5, 4, 3, -3, 0.5`. Chưa gán tên thuật toán chính thức hoặc chứng minh tương đương với một thư viện cụ thể. |

Nhánh linear và bốn điểm tăng gain theo `VolumeMoveL/R`, cập nhật pitch dần theo `dPitchChangeValue`, xử lý biên/loop và ghi lại trạng thái về Channel. Các phép dịch 13 bit phù hợp thang vị trí 8.192 đã thấy trong Java. Chưa khôi phục đầy đủ quy tắc tại mọi biên mẫu.

Ở `0x2e70` và `0x2ea4`, có hai lời gọi PLT đến `smbPitchShift`, một cho từng bus, trong nhánh phụ thuộc handle khác 0. Tên đích đã xác định qua relocation. Chưa đọc thân thuật toán đó; không kết luận về nguồn gốc, giấy phép, chất lượng hoặc cấu hình tối ưu chỉ từ tên hàm.

## 4. Start và Write: phần đã xác nhận

`NOpenSL.Start` có mutex, khởi tạo nhiều vùng buffer bằng `memset` và lời gọi `slCreateEngine` tại `0x2210`. Các lời gọi interface đã được ánh xạ sang chuỗi tạo engine/output mix/player, lấy Play/BufferQueue/Volume, đăng ký callback và tạo recorder tùy chọn trong [NOPENSL-START-CALLBACKS.md](NOPENSL-START-CALLBACKS.md).

`NOpenSL.Write` kiểm tra trạng thái, chép dữ liệu Java và có các vòng chờ gọi `usleep` (`0x10d4`, `0x1188`) cùng thao tác `memcpy`. Không thể coi đây là đường ghi không chặn. Chưa xác nhận nhánh nào được app chọn trong mỗi cấu hình; không suy ra thời gian chờ hay độ trễ chỉ từ sự hiện diện của `usleep`.

## Chạy lại phép đọc

Trong PowerShell tại gốc dự án:

```powershell
& "$env:LOCALAPPDATA\rea\native-python\Scripts\python.exe" analysis\disassemble_audio.py '.\ORG+24_+Your+Music_2026.10.01_APKPure.apk'
```

Các dependency nằm trong môi trường Python riêng tại `%LOCALAPPDATA%\rea\native-python`. Script chỉ đọc ZIP/ELF và viết kết quả dưới `analysis/native/`.

## Phần còn thiếu

Consumer/callback của buffer `0xaf48`, các interface OpenSL và nhánh Java chọn đường callback so với `Write` đã được ánh xạ trong [NOPENSL-START-CALLBACKS.md](NOPENSL-START-CALLBACKS.md). Phần còn thiếu là kiểm chứng trên Android bằng một nốt đã biết, đo underrun và bản ghi âm đầu ra. Hiện chưa có thiết bị/runtime được xác nhận kết nối; mọi kết luận ở đây là phân tích tĩnh trên biến thể ARM64 của đúng APK đã nêu.
