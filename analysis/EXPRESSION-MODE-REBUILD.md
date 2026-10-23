# Effect Mode và tốc độ biểu cảm

Đối chiếu `decoded-original/sources/sofeh/music/Effect.java`, hàm `getSpeed()` trả 0 nếu Mode bằng 1 hoặc 2; các Mode khác trả Speed đã lưu. Không dùng mặt nạ low nibble: Mode 17 vẫn trả Speed.

ORGX bổ sung `ImportedExpressionEffect.playbackSpeed`, dùng trong Pitch Shake và Volume Shake cho cả voice mode/STS lẫn Track style. Giá trị Speed gốc và toàn bộ raw bytes được giữ nguyên; không sửa file SET. Đổi Mode không tạo lại voice hoặc xóa phase. Khôi phục Mode thông thường tiếp tục phase với Speed đã lưu.

Pitch Shake dùng counter/product int32, phép chia số nguyên cho drift và giá trị sin float theo `channels-process.java`. Trước đây drift dùng phép chia Double nên có thêm phần lẻ không tồn tại ở phép tính gốc. ORGX vẫn dùng mixer nội suy Double: chưa mô phỏng đầy đủ phép lượng tử hóa pitch tổng, smoothing và PCM của mọi mixer gốc.

Kiểm chứng: các lớp ImportedExpressionModeTest, ImportedPitchShakeTest, ImportedVolumeShakeTest, StylePitchShakeIntegrationTest và StyleVolumeShakeTest đều đạt; assembleDebug thành công. Đã cài APK lên thiết bị 88d75c04. Chưa đối chiếu bản thu âm APK cho những Mode này.

Chưa mở automation parameter 7: Track.Play gốc kết hợp Mode với `fKeyConnectionMethod * 16`; cần dựng cấu hình đó trước khi nhận sự kiện này. Key Connection và các chế độ Legato/Mono vẫn chưa được triển khai đầy đủ.

ORGX đã nối nhánh Key Connection có bit mode cao (`mode > 15`) của `Channels._Connection` vào voice MODE/STS. Khi có voice cùng part và MIDI channel còn mở, phím mới tiếp quản voice đó, giữ vị trí sample, chuyển định danh note-off sang phím mới và đổi cao độ theo `StartDelay`/`Speed`. Điều kiện `ByPedal` được giữ; pedal chưa nhấn thì mở voice riêng như gốc.

Đã nối tiếp nhánh mode thấp: voice đích được mở riêng nhưng bắt đầu từ cao độ phím trước; mode lớn hơn 1 bắt đầu tại loop point của section. Trong lúc phát, `ByPedal` điều khiển cả bộ đếm StartDelay lẫn bước chuyển pitch, còn `StopByRelease` ngừng chuyển pitch khi voice đã release.

Đã dựng Key Repeat slot 0 theo nhịp điều khiển 10 ms và công thức chu kỳ `5000 / (Speed * 10) + 1` của `Channels.Process`. Sau StartDelay, voice gốc mở thêm voice lặp AutoRelease; voice lặp tắt EffectAllow nên không tự sinh lặp hoặc chạy lại Pitch/Volume Shake. ByPedal tạm dừng bộ đếm delay và StopByRelease/release ngăn mở voice mới.

Automation event 65 parameter 7 đã được mở cho Pitch Shake và Volume Shake. Nó thay `Mode` nhưng giữ nguyên `Speed` đã lưu; Mode 1/2 làm playback speed bằng 0, và khi trở về mode thường thì phase tiếp tục với Speed cũ. Thay đổi được cấu hình vào cả voice đang ngân và state dùng cho voice mở sau đó.

Automation slot 0/1 đã được nối vào style player. Event thay đổi Key Repeat giữ nguyên bộ đếm 10 ms của voice hiện hành; event Key Connection cập nhật chuyển pitch đang chạy và cấu hình dùng khi nốt kế tiếp mở. Mode cao có thể chuyển quyền điều khiển sang voice hiện hành, mode thấp mở voice mới từ cao độ phím trước.

Đã nối cấu hình toàn cục `fKeyConnectionMethod` theo APK gốc: Method 1 là 0, Method 2 là 1, lưu bằng khóa `key_connection`. Event 65 parameter 7 của slot 1 ghép phương thức vào bit cao (`lowMode | method * 16`); các slot khác không bị ghép. Giá trị được chuyển vào mọi style load/preview/restore và có thể đổi trong trang Cài đặt. Loader giữ phiên bản nguồn; với SET trước `1.4.3`, nó cũng ghép Method vào Key Connection tĩnh của channel và mode nhạc cụ như `Channels.ReadFromStream` và `Instrument.ReadFromStream`, chỉ trong bộ nhớ và không sửa file SET.

Các ngoại lệ AutoRelease/restart theo từng low nibble và chuyển ngay session đang phát khi đổi phương thức vẫn cần dựng tiếp trước khi coi Key Connection/Legato/Mono hoàn chỉnh.
Đã bổ sung ngoại lệ restart theo `Channels._Connection`: tốc độ 0 với low mode 1 luôn về đầu sample; low mode 3 chỉ restart khi sound AutoRelease hoặc sample không loop; low mode 0 giữ vị trí. Khi mở voice low mode lớn hơn 1, loop point vẫn được dùng khi có tốc độ, còn tốc độ 0 đưa vị trí về đầu sample. Test PCM đã kiểm tra từng nhánh.
