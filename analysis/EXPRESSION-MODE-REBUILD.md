# Effect Mode và tốc độ biểu cảm

Đối chiếu `decoded-original/sources/sofeh/music/Effect.java`, hàm `getSpeed()` trả 0 nếu Mode bằng 1 hoặc 2; các Mode khác trả Speed đã lưu. Không dùng mặt nạ low nibble: Mode 17 vẫn trả Speed.

ORGX bổ sung `ImportedExpressionEffect.playbackSpeed`, dùng trong Pitch Shake và Volume Shake cho cả voice mode/STS lẫn Track style. Giá trị Speed gốc và toàn bộ raw bytes được giữ nguyên; không sửa file SET. Đổi Mode không tạo lại voice hoặc xóa phase. Khôi phục Mode thông thường tiếp tục phase với Speed đã lưu.

Pitch Shake dùng counter/product int32, phép chia số nguyên cho drift và giá trị sin float theo `channels-process.java`. Trước đây drift dùng phép chia Double nên có thêm phần lẻ không tồn tại ở phép tính gốc. ORGX vẫn dùng mixer nội suy Double: chưa mô phỏng đầy đủ phép lượng tử hóa pitch tổng, smoothing và PCM của mọi mixer gốc.

Kiểm chứng: các lớp ImportedExpressionModeTest, ImportedPitchShakeTest, ImportedVolumeShakeTest, StylePitchShakeIntegrationTest và StyleVolumeShakeTest đều đạt; assembleDebug thành công. Đã cài APK lên thiết bị 88d75c04. Chưa đối chiếu bản thu âm APK cho những Mode này.

Chưa mở automation parameter 7: Track.Play gốc kết hợp Mode với `fKeyConnectionMethod * 16`; cần dựng cấu hình đó trước khi nhận sự kiện này. Key Connection và các chế độ Legato/Mono vẫn chưa được triển khai đầy đủ.
