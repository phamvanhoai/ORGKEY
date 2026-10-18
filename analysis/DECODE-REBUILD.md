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

### Reset khi transport dừng

Sửa AudioEngine.rhythmSample trước đây reset player ở mỗi frame khi !rhythmPlaying; với Reverb native đã nối, đường này flush lặp 48000 lần/giây và reset session rồi reset lại cùng player. Nay StoppedStyleReset chỉ cho reset lần đầu của khoảng dừng, sau playing→stopped, hoặc khi thay session trong trạng thái dừng. Session reset đã bao gồm player nên không reset player lần hai. Chọn phần immediate trong session vẫn được xử lý khi session.next chạy lúc Play.

Kiểm thử gate qua 48000 frame dừng, chuyển play/stop và thay selection ở cả trạng thái dừng/phát. Chưa đo mức CPU/latency trên thiết bị; thay đổi giảm số lần reset theo luồng điều khiển, không khẳng định mức cải thiện hiệu năng cụ thể.

Xác minh: build debug thành công, 264 kiểm thử JVM không lỗi, gồm kiểm tra SET cục bộ. Đã cài APK mới và mở ứng dụng trên điện thoại; giữ nguyên SET đầu vào.

### Quay lại section sau khi chuyển bus FX

Sửa lỗi `continueGroupFxFrom`: sau khi chuyển live group chain sang player đích, player nguồn từng bị để groupFx=null nên khi chọn lại section sẽ phát thiếu FX. Nay nguồn giữ `live.fresh()` (Reverb vẫn chưa cấp phát handle cho tới khi có tín hiệu). Đích nhận đúng live chain và đuôi đang ngân; nguồn có bus sạch khi phát lại. Không sao chép cùng native handle cho hai owner.

Kiểm thử JVM chuyển bus hai lần rồi chọn lại nguồn, xác nhận Bass Booster vẫn áp dụng. Bổ sung instrumentation JNI thật: chuyển group Reverb tail, phát nguồn lại và xác nhận cả đuôi chuyển lẫn đuôi mới đều có output. Chưa coi là đối chiếu đầy đủ mọi chuỗi nút style với APK gốc.

Xác minh: build debug/test APK thành công, 262 kiểm thử JVM không lỗi. Instrumentation native trên điện thoại PASS, gồm assert group transfer/revisit. Đã cài cập nhật, mở ứng dụng thành công; SET giữ nguyên.

### Reverb native đã nối vào chain SET

Đã bỏ Pair/boxed Long khỏi kết quả mỗi frame: wrapper dùng LongArray hai phần tử cấp phát một lần, native ghi trực tiếp dưới khóa stereo. Test backend kiểm tra cùng buffer được tái sử dụng qua nhiều frame. Đây là giảm cấp phát trong wrapper, chưa phải phép đo latency/GC toàn engine.

Instrumentation bổ sung đi qua ReferenceRhythmPlayer và ImportedFxChain thật: phát impulse, chuyển track FX tail sang player mới, đóng player cũ, render thêm 48000 frame; xác nhận đuôi không mất và output hữu hạn, đóng player mới hai lần không lỗi. JNI thật trên thiết bị báo PASS với transferred tail peak 0.142822265625. Chưa so toàn bộ thao tác style/âm sắc với APK gốc.

Đã thêm khai báo JNI `sofeh.audio.NGVerb` cho libngverb đã có trong jniLibs và `ImportedNativeReverb` sở hữu handle tường minh. Tham số open theo `w01.e`: maxRoom 100, room/time từ payload, damping/bandwidth/early/tail chia 100, spread .5; mix lấy byte 6. JNI stereo quy đổi input/output theo 32768 như assembly, không dùng 6553600 của các module Java khác.

Cặp processStereoLeft/processStereoRight được khóa toàn cục vì output phải nằm trong biến global của thư viện. process/reset/close khóa instance để không dùng handle đã giải phóng; close idempotent, reset flush native. Module 29 đã nối vào ImportedFxChain ở đúng thứ tự; chỉ cấp phát handle khi có input. Thay style đóng session cũ sau khi chuyển các tail/chain cần giữ; bỏ tail/bus, thay Music FX và audio owner kết thúc đều đóng chain. Module chưa phát không cấp phát native.

Kiểm thử JVM dùng backend giả tường minh để kiểm tra đường nối/ownership, không xác nhận DSP. Kiểm tra Android riêng gọi ELF thật cho impulse/tail/flush/close. Thư viện native là binary gốc đã có trong project, chưa phải mã C/C++ được dựng lại. Giới hạn: thời gian giữ bus Reverb không còn note hiện dùng 2*revtime+1 giây, chưa đối chiếu chính xác ngưỡng cắt tail gốc; chưa đo thời gian xử lý nhiều track hoặc so bản thu toàn bộ SET. Reset/close có thể cắt tail theo lifecycle hiện tại của session.

Xác minh: build debug và Android test APK thành công; 261 kiểm thử JVM không lỗi. Instrumentation `com.hovait.orgx.NativeReverbSmoke` trên thiết bị 88d75c04 chạy JNI thật: PASS impulse/tail/flush/close, peak wet tail 0.0714111328125, đóng/mở thêm tám lần thành công. APK mới đã cài; file SET đầu vào giữ nguyên. Chưa phải so sánh end-to-end với bản thu APK gốc.

### Band Move, type 2

Đã nối `sofeh.audio.c` vào Equalizer đã dựng: payload 17 byte = From+100, To+100, Delay, Speed (int32) và Loop boolean. `NEqualizer_bandMove` cắt về int `100-position` cho bốn band đầu và `100+position` cho bốn band sau. Chuyển động giữ Float, step = Speed*1e-6*sampleRate/48000; delay tăng 1000/sampleRate ms trên frame có input vượt ngưỡng. Chỉ tiến khi elapsed > Delay, gần đích trong một step thì dừng hoặc đổi chiều/đặt elapsed 0 nếu Loop bật, không tự snap đến đích. Reset giữ position/delay/EQ history theo n01.c.

Ngưỡng native abs(input)>1000 được quy đổi theo full scale 6553600 trong đường PCM chuẩn hóa hiện tại; chưa xác nhận loudness bằng bản thu. Equalizer cập nhật gain mà giữ history, không tạo filter mới mỗi frame. Phạm vi 48 kHz. Kiểm thử endpoint cố định so với EQ, delay trước chuyển động, thay đổi đáp ứng, stereo và reset giữa loop. Chưa đối chiếu binary native/bản thu, chưa mô phỏng cast long của từng nhánh EQ. Reverb và DNC/aftertouch/script vẫn chưa hoàn chỉnh.

Xác minh: build debug thành công, 257 kiểm thử không lỗi (gồm kiểm tra style SET cục bộ). Đã cài APK và mở trên điện thoại; giữ nguyên file SET đầu vào.

### Equalizer, type 7

Đã dựng đường stereo từ `NEqualizer_build/processLeft` ARM64: tám nhánh song song, mỗi nhánh bốn tầng low-pass recurrence Float/FMA; nhánh output lấy input trễ ba frame trừ tầng thứ tư, nhân band gain. Cộng tám nhánh rồi chia bốn. Gain lưu trong SET 0..200 được nhân .01; band thiếu giữ mặc định 100 theo facade `sofeh.audio.d`. Payload count được parser tách riêng; chain nhận tối đa tám int32.

Hệ số native dùng `2*sin((frequency/sampleRate)*angle)`, frequency `[1250,2500,3750,5000,6250,7500,8750,10000]`, angle `[pi/8,pi/4,pi/2,pi/2,pi,pi,pi,pi]`. Các hằng double được đọc từ rodata ELF, PLT xác nhận sin. Giữ bias 2^-32 trên tầng đầu như assembly. Reset giữ native history (facade không override n01.c), fresh tạo instance mới. Phạm vi nhận hiện tại 48000 Hz, đúng engine ORGX; chưa hỗ trợ tùy chọn sample rate khác.

Kiểm thử gain 0/50/100, stereo cùng pha, reset giữ history, count vượt tám và band mặc định. Chưa có kiểm tra độc lập bằng chạy binary native hoặc bản thu; đây là bản dựng theo assembly, chưa xác nhận tương đương. Chưa sao chép cast long từng nhánh và chia integer cuối. Band Move, Reverb và DNC/aftertouch/script vẫn còn thiếu.

Xác minh: build debug thành công, 254 kiểm thử không lỗi, gồm kiểm tra style SET cục bộ. Đã cài APK và mở thành công trên điện thoại. File SET giữ nguyên.

### Flanger / Phaser, type 8

Đã dựng đường stereo từ assembly `Java_sofeh_audio_NFlanger_processLeft/Right/build` ARM64 và đối chiếu x86. Relocation PLT của ELF xác nhận hai lời gọi toán học là cos và exp. Mỗi 20 frame, coefficient = `1+(exp(2*cos(StartPhase+frame*increment)+2)-1)*(-Depth/255)/53.598148345947266`, increment = `Frequency*0.15707963705062866/sampleRate`. StartPhase giữ giá trị native, không tự đổi sang radians.

Input mỗi kênh cộng previousWet*Feedback/100, rồi đi qua Stages all-pass: nextState = oldState*coefficient+input; output = oldState-coefficient*nextState. Trái/phải giữ state và feedback riêng, cùng coefficient. Trộn `(wet*Ratio+dry*(255-Ratio))/255`. Payload 28 byte giữ trường reserved sau Frequency. Java facade giới hạn Stages 1..16, Ratio 1..256; ORGX nhận đầy đủ miền này, không chặn Ratio 256 thành 255. Dùng FMA Float cho recurrence ARM64; chưa mô phỏng cast long ở output.

Đối chiếu `z01.f/c` gọi `n01.c`; `sofeh.audio.e` không override `c`, nên reset Flanger giữ state native và phase. ORGX đã sửa Stage.reset thành no-op, chỉ fresh chain mới tạo trạng thái sạch. Kiểm thử so nhiều frame sau reset với một chain chạy liên tục và xác nhận fresh khởi tạo sạch; kiểm thử Ratio 256 giữ dry âm theo mẫu số 255 của native. Chưa đối chiếu vòng đời thay module/SET trên APK gốc.

Xác minh bổ sung: build debug thành công, 251 kiểm thử không lỗi. Đã cài cập nhật và mở trên điện thoại; giữ nguyên SET đầu vào.

Kiểm thử impulse theo coefficient native, stereo độc lập/cùng pha, reset, bypass và output hữu hạn. Chưa có đối chiếu bản thu hoặc chạy song song native để kết luận tương đương. Reverb/Band Move/Equalizer native và DNC/aftertouch/script vẫn chưa hoàn chỉnh.

Xác minh: build debug thành công, 249 kiểm thử không lỗi (gồm style SET cục bộ có module 8). Đã cài cập nhật và mở trên điện thoại; không sửa SET đầu vào.

### Distortion, type 23

Đã dựng theo đường stereo `r01.e`: nhân Gain, soft clip bằng tanh hoặc hard clip tại full scale, rồi `output=(previousOutput*(1-k)+clipped*k)*level`, với `k=1f/CutOff`, `level=float(1/max(1,sqrt(Gain/CutOff)))`. History lưu final output riêng hai kênh. Payload 9 byte: Gain int32, Soft boolean, CutOff int32; Gain/CutOff 1..100. Reset/fresh xóa state; active giữ bus khi còn đuôi lọc.

Kiểm thử hard/soft clipping, bù mức, recurrence qua frame, stereo độc lập, reset/bypass và cutoff 0. PCM chuẩn hóa coi full scale tương ứng mốc 6553600 trong `r01.e`; chưa sao chép lượng tử long/cast Float của từng phép nhân nội bộ và chưa xác nhận loudness bằng bản thu. Không sử dụng đường `r01.f(short[])` khác cách cập nhật history. Các phần native và DNC/aftertouch/script vẫn chưa hoàn chỉnh.

Xác minh: build debug thành công, 247 kiểm thử không lỗi. Đã cài cập nhật và mở ứng dụng trên điện thoại; giữ nguyên SET đầu vào.

### Chorus, type 21

Đã dựng theo `q01`: ring cố định 10082 frame mỗi kênh, delay phân số nội suy tuyến tính, sine LFO với stereo spread, feedback riêng hai kênh và dry/wet mix. Payload giữ đủ 6 int32: Rate, Depth, Delay, Feedback, Mix, Stereo Spread. Reset xóa rings/cursor nhưng giữ phase như `q01.c`; fresh tạo phase 0. Kiểm thử impulse có delay phân số, stereo không crossfeed, dry mix và reset đuôi feedback.

PCM chuẩn hóa giữ Float buffer và coefficients nhưng không sao chép chia/nhân 6553600 và lượng tử Math.round long của bản gốc. Chưa đối chiếu bản thu; native Flanger/Reverb/Band Move vẫn cần dựng riêng.

Xác minh: build debug thành công, 244 kiểm thử không lỗi. Đã cài cập nhật và mở trên điện thoại; SET giữ nguyên. Miền Rate 5..500 đã đối chiếu hằng số `Sdk.SDKError.Reason.BANNER_VIEW_INVALID_SIZE_VALUE` dùng trong `q01`.

### Band pass, type 1

Đã dựng bộ lọc bậc hai theo `o01.a/v01.e`, giữ hệ số/history Float và thứ tự cộng feedforward rồi feedback. Payload gồm frequency (0..150000), bandwidth (0..25000), hai int32 little-endian. Radius = `1-3*bandwidth/sampleRate`; cosine = `2*cos(2*pi*frequency/sampleRate)`. Hai kênh có ba mẫu input và hai mẫu output history độc lập; reset xóa lịch sử, active giữ bus khi còn tín hiệu trong history.

Tham số sinh hệ số NaN/Infinity (ví dụ frequency 0 làm mẫu số `2-cosine` bằng 0) bị từ chối rõ ràng khi module bật. Không tự thay bằng bộ lọc khác. Kiểm thử impulse nhiều frame, stereo, reset, bypass và hệ số suy biến; chưa mô phỏng lượng tử long của APK gốc hoặc đối chiếu bản thu. Native Band Move/Flanger/Reverb vẫn chưa hoàn chỉnh.

Xác minh: build debug thành công, 242 kiểm thử không lỗi. Đã cài APK cập nhật và gửi lệnh mở ứng dụng thành công trên điện thoại; giữ nguyên file SET.

### Robot, type 16

Đã dựng theo `x01.e/c`: payload frequency int32 little-endian miền 0..1000; gain = `cos((frequency * frame) / sampleRate)` với phép chia integer. Hai kênh dùng chung gain và bộ đếm; bộ đếm quay về 0 sau sampleRate frame, reset/fresh bắt đầu từ 0. Không tự thay công thức bằng oscillator có 2*pi. Stage không có đuôi tín hiệu, giữ cờ enable và thứ tự chain.

Kiểm thử pha integer, stereo cùng pha, chu kỳ một giây, reset/fresh, frequency 0 và block disabled. Tính trên PCM chuẩn hóa, chưa mô phỏng Math.round lên long nội bộ hoặc xác nhận bản thu gốc. Module này mở rộng chuỗi FX, không hoàn tất Band Move/Flanger/Reverb native hay aftertouch/DNC/script.

Xác minh: build debug thành công, 240 kiểm thử không lỗi. Đã cài cập nhật và mở trên điện thoại; file SET giữ nguyên.

### Bộ lọc High pass / Low pass, type 9 và 10

Đã dựng recurrence IIR theo `u01.a` và `v01.e`: `y = b0*x + b1*xPrevious + a*yPrevious`, trạng thái Float độc lập trái/phải. High pass dùng `a=exp(-2*pi*(frequency/sampleRate))`, phép chia hai Int như Java decompile; `b0=(1+a)/2`, `b1=-b0`. Low pass dùng `a=exp(-2*pi*((frequency*1000f/150000f)/sampleRate))`, `b0=1-a`, `b1=0`. Payload mỗi module là int32 frequency, miền 0..150000. Giữ nguyên tỷ lệ và phép chia của mã đối chiếu, không tự thay bằng cutoff Hz thông thường.

Chain áp dụng type 9/10 cùng cờ enable và thứ tự hiện có; reset xóa input/output history, active giữ bus khi filter còn đuôi. Kiểm thử impulse, stereo độc lập, reset, low pass frequency 0, high pass qua ngưỡng phép chia integer và module disabled. Tính trên PCM chuẩn hóa, chưa mô phỏng lượng tử long sau mỗi frame của gốc, chưa có so sánh bản thu. Band Move (2), Flanger (8) và Reverb (29) gọi native nên vẫn cần dựng riêng.

Xác minh: build debug thành công, 237 kiểm thử không lỗi (gồm kiểm tra style SET cục bộ). Đã cài APK cập nhật và mở thành công trên điện thoại. File SET đầu vào giữ nguyên.

### FX 3D Sound (Stereo), type 17

Đã dựng module 17 theo `defpackage.y01`: payload là volume int32 little-endian, miền 0..50000; width = `(volume/10000f)*6.5536f`. Với centre = `(L+R)/2`, output mỗi kênh = `((input-centre)*width + centre + input)/2`. Module giữ mono centre, có thể đảo dấu kênh còn lại khi mở rộng stereo, không có buffer/đuôi. Volume 0 vẫn giữ một nửa side, không đồng nghĩa bypass.

Module dùng chung chain track, group, keyboard MODE và Music; giữ thứ tự với stage khác và cờ enable block/module. Kiểm thử mono, stereo lệch một phía, volume 0/max, bypass, payload sai và volume vượt miền. Tính trên PCM chuẩn hóa; chưa sao chép lượng tử long của APK gốc hoặc xác nhận bằng bản thu. Các module 2/8/29 và những module chưa triển khai vẫn chưa được tính là hỗ trợ.

Xác minh: build debug thành công, 234 kiểm thử không lỗi, gồm kiểm tra style SET cục bộ. Đã cài cập nhật và mở ứng dụng trên điện thoại; không sửa SET.

### Tuning table khi transpose

`Music.KeyToPitchEx` tra `table[(key - transpose) % 24]`, sửa chỉ số âm trước khi tra; phép tính cao độ vẫn dùng key đã dịch và root sample đã chọn. ORGX truyền transpose hiệu lực của channel vào `SampledInstrument` và trừ riêng transpose khi chọn slot tuning. Octave không bị trừ. Nhánh PitchMode 1 dùng key sau ánh xạ lặp như `Channels.OpenKey`.

Loader MODE hợp nhất transpose channel/Music trước khi nạp layer, không cộng Music transpose lần nữa ở metadata bàn phím. Loader raw sound cũng giữ transpose Music cho tuning; style dùng settings đã hợp nhất theo track. Kiểm thử so PCM giữa MODE/raw/style channel, slot 24 phím, giữ octave và vòng chỉ số âm. Chưa dựng thay đổi tuning/pitch của voice đang ngân hoặc đầy đủ DNC; kiểm thử không thay thế so bản thu APK gốc.

Xác minh: build debug thành công, 230 kiểm thử không lỗi (gồm style SET cục bộ). Đã cài APK và mở thành công trên điện thoại. Không sửa file SET.

### Music/Rhythm balance tại OpenKey

Đối chiếu `Channels.OpenKey` (đoạn `iMin`): keyboard nhận `min(100,100+Music.pan)/100`; rhythm nhận `min(100,100-Music.pan)/100 * Rhythm.volume/100`, sau đó melodic nhân `min(100,100-Rhythm.pan)/100`, percussion nhân `min(100,100+Rhythm.pan)/100`. Đây là cân bằng giữa nguồn âm, không phải pan stereo. Music.volume vẫn là master sau khi cộng các bus.

ORGX chốt hệ số này vào gain của nốt mới, trước channel FX. Bỏ phép nhân Rhythm volume/balance ở `mixOutput` để không áp hai lần. Cấu hình truyền xuống từng child track, phân loại theo instrument hiệu lực. Đuôi voice/echo khi chuyển phần giữ gain đã chốt. Bàn phím voice SET chốt Music balance tại noteOn; voice factory/MIDI vẫn chưa dùng đường balance này. Hệ số dùng PCM chuẩn hóa Double, chưa mô phỏng từng phép chia integer của Touch trong APK gốc.

Kiểm thử hai chiều Music balance, Rhythm cân bằng chord/drum mà giữ stereo, thay đổi cấu hình không đổi nốt đang ngân và không xóa/giảm lại đuôi echo. Chưa có so sánh bản thu để kết luận tương đương âm thanh.

Xác minh: build debug thành công, 228 kiểm thử không lỗi (gồm style SET cục bộ); đã cài APK cập nhật và mở thành công trên điện thoại. Không sửa SET.

### Music pitch và release

Theo `Channels.OpenKey`, tune của melodic bằng section + channel + Music; transpose bằng channel + Music. Octave bàn phím cộng Music octave, còn style cộng Rhythm octave. ORGX áp transpose/octave trước chọn region, bao gồm nhánh trim sample của style, và truyền tune vào bước phát PCM. Percussion giữ key/tune theo quy tắc cũ. Các đường chọn voice và khôi phục voice đã lưu đều truyền Music settings của SET vào loader; MODE nhiều layer chỉ nhận mỗi yếu tố một lần.

Release được tính `soundRelease * channelRelease * musicRelease / 10000`, chặn 0..100 sau phép chia; không làm tròn phần trăm channel/Music trước. Phép tính dùng Long. Giữ nguyên volume/pan/module của channel khi hợp nhất pitch; FX Music và master volume vẫn xử lý ở bus đầu ra. Chưa dựng thay đổi pitch Music trực tiếp khi nốt đang ngân hoặc tempOctaveMove. Tuning khi transpose đã được bổ sung như phần trên.

Xác minh: build debug thành công, 226 kiểm thử không lỗi, gồm kiểm tra style SET cục bộ. Đã cài cập nhật và khởi động trên điện thoại; chưa có đối chiếu bản thu với APK gốc. File SET giữ nguyên.

### Music FX và volume đầu ra

Đối chiếu `Music.ProcessOut`: `fFXMusic.h` xử lý bus Music trước khi cộng bus Wave/Rhythm; `fFXMusic.b` nhân tổng đầu ra sau phép cộng. ORGX nối các module đã hỗ trợ (4/6/20) của Music sau tổng các bus voice SET và trước khi cộng style. Volume Music nhân đầu ra chung trước limiter, không nhân riêng từng voice. Không dùng Music pan như stereo pan; cơ chế phân phối Music/Rhythm đã được nối tại đầu nốt như phần trên.

Đuôi Music echo tiếp tục chạy khi không còn bus voice. Đổi volume không dựng lại chuỗi DSP; đổi enable/modules dựng lại chuỗi, reset xóa đuôi. Kiểm thử tổng nhiều channel, đuôi sau thả phím, đổi volume/pan, bypass và reset. Phạm vi hiện tại là voice SET nhập; chưa nối Music FX cho voice factory/MIDI, Wave FX và các module DSP còn thiếu. Chưa có bản thu so sánh để kết luận âm thanh giống APK gốc.

Xác minh: build debug thành công, 223 kiểm thử không lỗi (gồm kiểm tra style SET cục bộ); đã cài cập nhật lên thiết bị ADB. Không sửa file SET đầu vào.

### Keyboard MODE channel mixer

Voice MODE nạp trực tiếp giữ cấu hình channel hiệu lực trên sample đã load; bàn phím melodic áp transpose/octave trước chọn region, percussion giữ key. Mixer dùng bus theo identity của sound đã load, cộng các nốt/layer trước rồi áp volume/pan và DSP đã hỗ trợ (4/6/20) một lần trên tổng stereo. Không nhân lại gain của từng layer. Echo buffer tiếp tục chạy với input 0 khi hết nốt. Voice cũ giữ bus của sound cũ khi đổi voice; bus im và hết note được loại.

Voice SET nhập được cộng tuyến tính thay vì trung bình theo số nốt; limiter cuối vẫn giới hạn peak. Kiểm thử gain/pan hai note, echo sau thả phím/reset và transpose melodic/percussion. Chưa đối chiếu loudness bản thu; DSP Music global, module còn thiếu, aftertouch/script và mode Effects vẫn chưa hoàn chỉnh. Không áp cấu hình bàn phím lần nữa lên style vì loader style truyền channel settings tường minh.

### Cấu hình mode và thứ tự khôi phục channel

Đã giữ FX header/module của InstMode thay vì bỏ qua. `Track.ReadFromStream` gọi InstrumentChange/ModeSet trước, rồi Channels.ReadFromStream đọc cấu hình channel đã lưu đè lên. Vì vậy track style tiếp tục dùng cấu hình channel của track, không tự đổi gain/pan/FX sang mặc định mode. Caller có cấu hình channel tường minh thì cấu hình đó ưu tiên. Voice MODE khi load trực tiếp dùng tune/release của mode cho từng layer.

Kiểm thử resolver/envelope release và PCM stereo xác nhận track volume 20/pan -100 vẫn ưu tiên trước mode volume 50/pan 100. Chưa dựng DSP mode dùng chung trên tổng voice bàn phím, gain/pan/transpose mode cho keyboard và mode Effects/script/aftertouch; không coi việc giữ payload là đã phát toàn bộ FX.

### Section fallback về group 0

Đã dựng bước fallback `MainActivity.y1`: khi style không có section được yêu cầu, lấy section cùng mã từ parent/group 0. Bộ chọn và session cùng dùng danh sách đã bổ sung, player fallback được nạp PCM thật và có thể request/auto-fill. Section riêng của style luôn ưu tiên, kể cả khi không hỗ trợ (không âm thầm thay vì lỗi engine). Không lấy section từ parent style khác. Hàm bổ sung idempotent để gọi từ catalog và loader không nhân bản.

Bộ đọc cũng giữ ChordType/ChordBase của TrackGroup thay vì skip hai byte. Các version SET 1.2.8/1.4.6 hiện hỗ trợ không lưu ChordVars; chưa thêm hỗ trợ định dạng mới hoặc chọn variant theo mask. Không gọi fallback này là đã dựng đầy đủ GroupSection hợp âm.

### RhythmTempLoop / nhấn lại để lặp tạm

Đối chiếu `MainActivity.y1`, field `q1` (không phải method q1 đặt password), `Track.Play` event 204 và `GroupPlayAdd/Jump`. Option `rhythm_loop_double` mặc định false. Khi đang chạy và target là group cuối queue hoặc current, nhấn lại đặt tempLoop=true nếu group không Loop và có Limit hoặc độ dài >10. Đây không phải toggle double-tap theo timeout. Request/Jump group đó xóa tempLoop; chọn group khác có thể thoát ở cửa sổ chuyển phần.

ORGX có setting lưu `styleRepeatToLoop` và hiển thị TEMP LOOP qua state polling. Engine lặp phần một lần khi hết vòng, ưu tiên queue nếu có phần đích, giữ Jump để dùng khi hết one-shot sau lần chọn lại. Kiểm thử Intro/Ending lặp và chuyển Main, option tắt Ending vẫn kết thúc. Chưa port GroupSection hợp âm và mọi event record/control của gốc.

### Restart cùng section / channel continuity

Đối chiếu `GroupPlayNext`: chỉ gọi `CloseAll(false)` khi group cũ khác group mới. Restart cùng group gọi `GroupPlayStart`/`Track.Start`, không đóng toàn bộ channel. `startAt(preserveVoices=true)` hiện reset timeline/clock nhưng giữ voice, sample age, FX track và tail đang chạy; bus group giữ khi yêu cầu. Khởi tạo timeline sau đó không reset channel lần nữa. Stop/reset vẫn xóa toàn bộ. Kiểm thử nốt có interval đã bỏ lỡ sau seek: không phát lại sự kiện, voice cũ tiếp tục hơn thời gian release, reset xóa tiếng.

Chưa dựng nhấn đôi tempLoop hoặc toàn bộ control-mode của bản gốc; đây là sửa cách reset channel khi tái khởi động cùng player.

### FX track trong release tail

Đã chuyển ownership chain FX từng track cùng voice sang release tail; cả khi voice hết nhưng buffer DSP còn năng lượng cũng được chuyển. Source nhận chain mới cùng cấu hình để có thể reset/reuse độc lập. Tail chạy input 0 sau khi voice hết cho tới trạng thái DSP dưới ngưỡng (echo energy 1e-18, Bass state 1e-9), hoặc Stop/reset xóa. Kiểm thử tạo impulse, voice dài 1 frame, chuyển phần, reset source, xác nhận echo xuất hiện đúng frame ở destination và bị xóa khi Stop.

Giới hạn: ngưỡng im lặng là lựa chọn ORGX trên PCM chuẩn hóa, chưa chứng minh giống phép lượng tử long của gốc; chỉ các module 4/6/20 đang dựng được theo dõi. Tail vẫn giới hạn 32 nhóm; Ending/đổi tham số module và restart cùng player chưa hoàn chỉnh.

### Nhả voice của section/style cũ

Đã bổ sung giữ voice đang vang khi chuyển sang player khác, theo ý nghĩa `GroupPlayNext` gọi `CloseAll(false)` cho track cũ: ngừng schedule cũ, đánh dấu note-off, tiếp tục playback/envelope hiện tại. Nhóm release giữ routing drum/chord và gain của nguồn; chuyển nhanh mang tiếp các release đang tồn tại. Stop/reset xóa. Giới hạn 32 nhóm tail để tránh tích lũy vô hạn. Voice dùng sample riêng của nguồn, không phát lại sample từ đầu.

Chưa chuyển state FX từng track sang nhóm release, chưa giữ voice khi restart cùng player, và Ending dừng transport vẫn có thể cắt đuôi. Các giới hạn này cần đối chiếu riêng trước khi kết luận mixer tương đương bản gốc.

### Giữ bus FX khi chuyển phần

Đã chuyển ownership chain group/rhythm đang chạy sang player đích khi cấu hình FX bằng nhau, sau khi player đích chuẩn bị timeline. Áp dụng khi đổi section và đổi style đang chạy. Chọn lại cùng section có thể giữ bus; reset/Stop vẫn xóa. Không giữ hai player cùng tiến buffer, không reset chain sau khi đã chuyển ownership. Kiểm thử impulse echo trước đổi phần và đuôi tại frame 480 sau đổi phần, cấu hình khác không dùng buffer cũ, reset xóa đuôi.

Đây là tiếp nối state cho cấu hình không đổi, chưa mô phỏng cơ chế copy/update từng module khi tham số khác nhau của `z01.e`; chưa bảo toàn đuôi khi module đổi hoặc track cũ còn voice release. Không kết luận tương đương toàn bộ mixer gốc.

### Global rhythm FX từ SET

Đã giữ ba FX block Music/Wave/Rhythm theo thứ tự `Music.ReadFromStream`; `ImportedGroupFx` khi tới group 0 dùng global rhythm như mã gốc. Nếu không có block group phù hợp, global rhythm là fallback thay cho cấu hình trống. Các module đã dựng (4/6/20) của block này chạy trên stereo output style.

Đã áp dụng volume và balance của global rhythm: balance dương giảm chord, âm giảm drum; không coi là stereo pan (`Channels.OpenKey`). Kiểm thử +/-100 và volume 50 xác nhận hai kênh giữ cân bằng stereo. Music/Wave block được giữ nhưng chưa nối đầy đủ DSP của chúng. Các header tune/octave/release global, module chưa hỗ trợ và đuôi xuyên section vẫn cần dựng tiếp. Kiểm thử unit chưa thay thế so sánh bản thu APK gốc.

### Group/style FX inheritance

Đã giữ FX block trong TrackGroup với version từ 1.4.2, và dựng cách tìm `Tracks.GroupFXs`: ưu tiên block của phần nếu enabled và có module; nếu không lần theo cha, cuối cùng về group 0. Có chống chu kỳ cha lỗi. Output style trộn drum/chord gain trước rồi chạy module đã hỗ trợ một lần cho cả stereo. Không áp dụng các header volume/pan/tune của group vì `GroupPlayNext` chỉ copy module với các cờ header false.

Kiểm thử ưu tiên/fallback, block có module disabled vẫn chặn kế thừa như gốc, chu kỳ cha và PCM tổng sau gain. Giới hạn: phiên bản SET 1.2.8 không có FX block tại TrackGroup; các module chưa dựng không tự trở thành được hỗ trợ. Chain hiện sở hữu theo player section và reset khi section reset; chưa giữ đuôi xuyên phần như engine FX rhythm dùng chung của gốc, và fallback global rhythm chưa hoàn chỉnh.

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

### Stereo Echo type 4

Đã dựng `t01.e`: hai buffer feedback độc lập; output echo trái nhân phase/delay, phải nhân (delay-phase)/delay; phase chạy tam giác 0..delay. Feedback lưu mức đầy đủ, không dùng mức output đã điều biến. Reset buffer/cursor giữ phase như `s01.c` kế thừa; tạo chain mới khởi tạo phase 0/direction 1. Delay 0 bị từ chối rõ ràng vì công thức gốc chia cho delay; không tự thay bằng một hiệu ứng khác. Kiểm thử quét phase, feedback, không crossfeed, xóa buffer. Chưa đối chiếu lượng tử hóa long hoặc bản thu APK gốc.
