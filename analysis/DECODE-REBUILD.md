# Giải mã APK và dựng lại ORGX

## Bản xuất đầy đủ

Chạy `analysis/decode-apk.ps1` để xuất toàn bộ nguồn Java/tài nguyên bằng JADX đã cài sẵn. Kết quả cục bộ ở `analysis/decoded-original/`, không trộn vào mã ORGX và không đưa hàng chục nghìn file thư viện vào Git.

Lần chạy hiện tại nạp 27.265 class, 155.293 method, 6.047.111 instruction, gồm cả DEX trong assets; xuất 22.173 file Java. Số file Java không bằng số class vì inner class gộp chung file. JADX báo 655 lỗi giải mã. Những số này mô tả phạm vi đầu vào/đầu ra, không chứng minh mọi method đã phục hồi chính xác.

Chạy `py -3 analysis/audit_decoded_apk.py` để tạo `DECODED-APK-INVENTORY.json`: hash APK, toàn bộ archive entry, danh sách nguồn namespace sofeh, vị trí marker lỗi giải mã. Các file `.so` vẫn là binary; xem phân tích riêng trong `analysis/native/`.

## Dựng lại vào project

Project có thể build vẫn ở `orgx/`. Không thể coi việc chép Java decompile vào Gradle là phục hồi project gốc: có tên bị obfuscate, method thiếu, resource ID và liên kết JNI cần giữ tương thích. Việc đổi thương hiệu ORGX/hovait cũng là khác biệt chủ ý.

Thứ tự đối chiếu bắt buộc trước khi kết luận một phần hoàn thành:

1. `sofeh/music/Music`, `Instrument`, `InstSnd`, `InstSec`, `Track`, `TrackGroup`, `Channels`: đọc SET và biến đổi dữ liệu hiệu lực sau đọc.
2. `Tracks.GroupPlayNext`, `GroupPlayStart`, `_PlayGroup`, `Track.PlayNext`: chuyển phần/style, loại nhạc cụ theo TrackKind, ánh xạ hợp âm, điều kiện kết thúc.
3. `Channels.OpenKey`, `Process`, mode/mix và FX: chọn sound/layer, gain/pan/pitch, ADSR và DSP.
4. `sofeh/android/MainActivity`: trạng thái màn hình và thao tác từng nút; đối chiếu thiết bị, không chỉ ảnh hoặc build.
5. So sánh cùng SET và cùng chuỗi thao tác/âm thanh giữa APK gốc và ORGX. Kiểm thử unit không thay thế bước này.

## Sửa dựa trên lần xuất này

`Tracks.GroupPlayNext` gọi `Instrument.SetType`: kind 1 đổi melodic thành type 2; kind >1 đổi type 2 về melodic. `SetType` đồng thời đặt PitchMode của tất cả sound thành 2 hoặc 0. ORGX hiện áp dụng cả hai biến đổi trên bản sao nhạc cụ theo track, không sửa SET và không gây đột biến ngân hàng dùng chung.

## Chưa hoàn thành

Đây là bản xuất để phân tích và bước sửa engine có bằng chứng, chưa phải bản dựng lại APK gốc 100%. Mode/layer/DNC, toàn bộ FX, STS/system, GroupSection và toàn bộ giao diện gốc còn phải đối chiếu và thực thi. Không lấy “export xong” hoặc “build đạt” làm kết luận các phần này đã đúng.

## Mode và layer đã bổ sung

- Bộ đọc giữ tên mode, Sound, Aftertouch, 8 Mix slot và MixVol/MixPan có dấu; không còn skip toàn bộ 16/336 byte này.
- Theo `Channels.OpenKey` wrapper: phát primary sound của mode và từng Mix slot bật, kể cả sound lặp; MixVol dùng `(100 + value)%`, MixPan cộng vào pan. Style dùng mode mặc định; bộ chọn voice bổ sung mục `MODE` cho các mode hỗ trợ, giữ lựa chọn raw sound riêng.
- Mỗi layer có playback/envelope độc lập, dùng chung đường phát cho bàn phím và track style. Giới hạn PCM tính cả các layer được chọn.
- Kiểm thử Organ DNC so sánh tổng PCM từng layer, kiểm thử hai envelope có thời điểm auto-release khác nhau và pan lệch; không ép output mode nhiều layer thành mono chỉ để đạt kiểm thử cũ.

Phần mới chưa thực thi aftertouch chuyển sound, mode Script, module FX hoặc toàn bộ thuật toán DNC. Các trường Aftertouch được giữ lại để dựng phần tiếp theo; “đã đọc” không đồng nghĩa “đã phát tương đương”.

## System / tuning table


Đã đọc `System_.ReadFromStream`: tên, StartKey, SelectedKeys và 24 double little-endian thay vì skip 225 byte/system. Track giữ `fSystem`; cache sample phân biệt cùng instrument/type nhưng khác system. Melodic sử dụng công thức `Music.KeyToPitchEx`: cộng table[key % 24] vào khoảng cao độ, cộng Tune/1000 rồi dùng cơ số 1.059463094 và bước cố định 8192. Percussion bỏ qua bảng. Voice SET trên bàn phím dùng system 0; các track style dùng system được lưu riêng.

Kiểm thử gồm chỉnh nửa cung tại một slot, lặp lại theo 24 phím, PCM melodic khác trong khi percussion giữ nguyên, và báo rõ track tham chiếu system thiếu. Chưa có giao diện sửa SelectedKeys/StartKey hoặc đổi system bàn phím trực tiếp; việc giữ hai trường này không được tính là đã thực thi giao diện tương ứng.


## Tham số FX header của kênh

### Module Bass Booster của track

Đã giữ cờ enable block, enable module, realtime và payload module trên channel từng track. `ImportedFxChain` dựng type 20 từ `defpackage.p01`: smoothing = 1/(frequency+1), state = (state*frequency+input)*smoothing, output = input+state*volume/16; trạng thái trái/phải độc lập, reset khi reset player. Tính trên PCM chuẩn hóa float, chưa sao chép lượng tử hóa long nội bộ của APK gốc. Kiểm thử impulse/đuôi, stereo, bypass và tích hợp track.

Chỉ type 20 được xử lý trong chain này. Các type khác được giữ payload trong index track nhưng chưa chạy DSP; FX ở mode/global/group vẫn chưa nối toàn bộ. Không suy từ một module sang tuyên bố hỗ trợ đầy đủ reverb/flanger/equalizer.

### Echo Delay type 6

Đã bổ sung type 6 theo `s01.a/e/c`: delay = min(48000, frequency * sampleRate / 100), output = input + delayedOutput * volume / 100, buffer trái/phải riêng. Delay lưu 0 dùng một ô nhớ vì bản gốc luôn reset cursor về 0 trong trường hợp này. Chain giữ thứ tự các stage đã hỗ trợ (6/20) theo dữ liệu. Reset xóa buffer và cursor. Kiểm thử impulse tại đúng frame trễ, giảm feedback, stereo độc lập và delay 0. Tính PCM chuẩn hóa không mô phỏng lượng tử hóa long của gốc; type 4 stereo modulation, reverb và các module còn lại chưa dựng.

Đã giữ volume, pan, tune, transpose, octave và release-percent từ header `z01.j`. Track áp dụng tune theo đơn vị 1/1000 semitone, transpose/octave trước chọn sample (sau ánh xạ hợp âm), và nhân release theo `Channels.OpenKey`, chặn trên 100. Release-percent lưu 0 được đổi thành 100 như bộ đọc gốc. Percussion không nhận tune/transpose/octave melodic. Cache phân biệt các thiết lập kênh để không dùng sai sample/envelope giữa các track. Module DSP bên trong FX block vẫn chưa được dựng; header FX không đại diện cho toàn bộ FX.
