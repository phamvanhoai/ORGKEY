# Phân tuyến Effect của Track

`Tracks.ReadFromStream` trong nguồn giải mã gốc đặt Channels.fType=1 cho Master (track index 0), track mất group, group Type 1 và track Type 3. Track Type 2 còn lại dùng Channels.fType=2; các track còn lại giữ kiểu mặc định 0.

`Channels.Process` chỉ chạy khối Effect khi Channel.EffectAllow=true và Channels.fType là 0 hoặc 2. Vì vậy không được suy luận rằng instrument melodic trong một rhythm group sẽ nhận Pitch Shake/Volume Shake.

ORGX đã nối quy tắc này tại ImportedStyleSupport.expressionAllowed và ImportedStylePlayer. ReferenceRhythmPlayer vẫn lưu trạng thái automation event 65 nhưng chỉ chuyển cấu hình Effect vào voice khi tuyến cho phép. Cả note-on ban đầu, voice đang ngân, note-on sau automation và reset đều dùng cùng chính sách. FX module DSP vẫn là đường xử lý riêng.

Hai kiểm thử tích hợp style trước đây kỳ vọng Shake trên group Type 1 đã được sửa thành kiểm tra bypass; đó là kỳ vọng sai so với nguồn gốc. Các kiểm thử trực tiếp của kênh cho phép vẫn xác nhận Shake hoạt động. ImportedExpressionRoutingTest kiểm tra Master, group thiếu, group Type 1 và Track Type 3.

Giới hạn: ORGX hiện chỉ nhận style group Type 1 trong ImportedStyleSupport.reason, vì vậy Effect biểu cảm trên style được bypass; voice mode/STS bàn phím vẫn có thể dùng Shake. Chưa dựng Key Repeat/Key Connection, chưa nối mọi điều kiện EffectAllow riêng của note và chưa kiểm chứng bằng bản thu từ APK.

Kiểm chứng: hồi quy toàn bộ 346 unit test đạt, không fail/error/skip. Sau khi tách hàm phân tuyến, chạy lại ImportedExpressionRoutingTest và hai lớp tích hợp style đều đạt; assembleDebug thành công, cài và mở bản cuối trên thiết bị 88d75c04.
