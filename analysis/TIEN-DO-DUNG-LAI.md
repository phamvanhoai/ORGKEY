# ORGX: phạm vi đã dựng và phần còn thiếu

Đây là bảng đối chiếu mã hiện tại, không phải chứng nhận tương đương APK gốc. Không quy đổi số kiểm thử hoặc số file sang phần trăm hoàn thành.

## TrackMode61 áp dụng lại mode cố định, 26-10-2026

- Đối chiếu Track.Play61: ID0 chỉ gán fMode, ID khác0 gọi ModeSet kể cả khi cùng mode; đổi mode khác còn gọi ScriptEvent rỗng. Dựng trường hợp một mode0 không script, không tự hỗ trợ đổi sound động.
- Player với fixedMode nhận61 Key0: ID0 giữ channel đang automation, ID khác0 áp dụng liveMode hiện tại (bao gồm FX từng sửa qua66), Tune và expression. Dùng chung applyLiveMode với66; mask ModeSet tạo một lần thay vì mỗi event.
- Bộ tương thích nhận61 chỉ khi đúng một mode trống script và Key0; các trường hợp còn lại báo dynamic mode selection chưa hỗ trợ trước khi phát.
- assembleDebug và ReferenceRhythmPlayerTest, ImportedSectionFallbackTest, ImportedFxChainTest đạt. Test volume80/pan0 giữ nguyên sau61 ID0, rồi về mode volume40/pan100 sau61 ID1.
- Giới hạn: chưa nhiều mode, script đổi mode, mode-1 và sound routing tương ứng; chưa thu âm đối chiếu thiết bị. Không sửa SET hoặc tạo file test/log mới.
## Tune của mode sau TrackFXParam66 áp dụng cho nốt mới, 26-10-2026

- Channels.ModeSet dùng z01.d copy Tune, khác event64 z01.e không copy Tune. Nhánh fixed mode FX nay cập nhật currentChannel.tune sau khi áp dụng snapshot mode.
- Nốt melodic mới nhận delta Tune so với initialChannel đã gộp trong sample loader, cộng vào event tune một lần. Voice giữ tune hiệu lực để các lần mở lại theo chord/repeat giữ đường phát hiện có. Reset currentChannel khôi phục tune ban đầu.
- assembleDebug và ReferenceRhythmPlayerTest, ImportedSectionFallbackTest, ImportedFxChainTest đạt. So sánh mẫu ramp khi mode Tune12000 áp dụng sau66 với event note Tune12000, hai chuỗi output trùng nhau.
- Giới hạn: mới fixed mode không script; chưa live tune toàn cục Music hoặc re-tune nốt đang phát, chưa kiểm thử âm thanh thiết bị. Không sửa SET hoặc tạo file test/log mới.
## TrackFXParam66 qua một mode cố định không script, 26-10-2026

- Đối chiếu Track.Play66 sửa FX mode rồi Channels.ModeSet sao chép cấu hình và expression về channel. Loader nhận trường hợp instrument có một mode script trống; nhiều mode/script vẫn báo dynamic mode FX chưa hỗ trợ.
- Player giữ snapshot mode riêng, sửa module trong snapshot rồi áp dụng volume/pan/transpose/octave/release/modules/expression về channel với mask riêng, không phụ thuộc tùy chọn STS. Reset trả snapshot mode ban đầu. Bộ kiểm tra event lấy module từ mode thay vì track settings.
- assembleDebug và 102 kiểm thử ReferenceRhythmPlayerTest, ImportedSectionFallbackTest, ImportedFxChainTest đạt. Kiểm tra track volume automation80 bị trả về mode40 khi bật Bass module, output0.6 theo gain module1.5.
- Giới hạn: chưa mode động/ModeSet event61, tune mode vẫn latched trong sample loader, chưa đồng bộ thay đổi mode giữa nhiều track dùng chung instrument như APK. FX vẫn reset chain. Không sửa SET hoặc tạo file test/log mới; chưa đối chiếu audio thực.
## TrackFXParam66 cho Band Move, 26-10-2026

- Đối chiếu sofeh.audio.c: logical params from,to,boolean,delay,speed có min-100/-100/0/1/1 và max100/100/0/5000/2000. Serialize khác thứ tự: from+100,to+100,delay,speed,boolean.
- Helper nhận payload17 byte type2, ánh xạ đúng min/max và offset; Key3 ghi byte16, Key4/5 ghi int8/12, Key1/2 cộng bias100. Theo setter gốc boolean min/max0 nên lệnh normalized đặt vềfalse.
- assembleDebug, ImportedFxChainTest, ReferenceRhythmPlayerTest, ImportedSectionFallbackTest đạt. Kiểm tra tất cả5 điều khiển, kích thước17 byte và output finite khi chạy cấu hình cập nhật.
- Giới hạn: mode FX, cập nhật riêng stage và tính liên tục native chưa hoàn thiện; whole-chain vẫn reset khi đổi Band Move. Không sửa SET hoặc tạo file test/log mới.
## TrackFXParam66 cho EQ từng dải và Reverb, 26-10-2026

- Đối chiếu sofeh.audio.d và w01: EQ8 dải ánh xạ-100..100 nhưng serialize int+100; Reverb7 tham số có min1/max100, lưu mỗi tham số một byte trong payload8 byte.
- Helper nhận type7 theo số dải thực có trong payload tối đa8, ghi giá trị bias100; type29 Key1..7 ghi đúng byte và giữ byte8. Bộ tương thích/player dùng đường66 hiện có.
- assembleDebug và ImportedFxChainTest, ReferenceRhythmPlayerTest, ImportedSectionFallbackTest đạt. Test từng dải EQ không đổi hàng xóm, mức-75 lưu25; Reverb normalized0 lưu51, giữ byte cuối77 và từ chối Key8.
- Giới hạn: chưa EQ control tổng hợp Key9, Reverb control thứ8 có mảng giới hạn không tương ứng trong source nên chưa nhận. Đổi tham số vẫn reset whole-chain; mode FX và đối chiếu tail native trên thiết bị còn thiếu. Không sửa SET hoặc tạo file test/log mới.
## TrackFXParam66 cho sáu tham số Flanger, 26-10-2026

- Đối chiếu sofeh.audio.e.i/g/h: sáu tham số min1, max300/90/100/255/16/256. Payload28 byte chứa một int reserved sau tham số đầu; Key2..6 phải bỏ qua offset4.
- Helper automation nhận type8 payload28 byte, ánh xạ Touch-100..100 và ghi đúng offset0/8/12/16/20/24. Giữ reserved và các tham số khác; đường loader/player hiện có áp dụng cho non-mode track.
- assembleDebug, ImportedFxChainTest, ReferenceRhythmPlayerTest, ImportedSectionFallbackTest đạt. Kiểm tra từng tham số ở min/trung điểm/max, reserved123456 không bị đổi và output DSP hữu hạn.
- Giới hạn: vẫn tái tạo whole-chain khi sửa Flanger, khác khả năng giữ native state của APK; mode FX chưa dựng. Không sửa SET hoặc tạo file test/log mới; chưa thu âm đối chiếu trên thiết bị.
## TrackFXParam66 cho sáu tham số Chorus, 26-10-2026

- Đối chiếu q01.h/i và setter i: sáu khoảng5..500,10..2000,5..100,-95..95,0..100,0..180. Xác minh hằng SDK dùng cho max tham số đầu thực sự là500. Payload24 byte gồm sáu int little-endian.
- Helper nhận type21 Key1..6 và ánh xạ Touch-100..100 theo min/max riêng, không dùng chung min0. Theo đường player/loader hiện có cho track không dùng mode.
- assembleDebug, ImportedFxChainTest, ReferenceRhythmPlayerTest, ImportedSectionFallbackTest đạt. Kiểm tra từng tham số ở ba mức chuẩn hóa, giữ năm tham số bên cạnh và output DSP hữu hạn ở cấu hình nhận được.
- Giới hạn: thay đổi Chorus vẫn tái tạo chain nên có thể khác native về phase/tail; mode FX chưa dựng. Không sửa SET hoặc tạo file test/log mới; chưa đối chiếu audio thực trên thiết bị.
## Pitch Shift đổi tham số tại chỗ, giữ chain và native handle, 26-10-2026

- Đối chiếu sofeh.audio.f: i thay g0, a chỉ open nếu handle0; mỗi process truyền g0 hiện tại tới JNI. Native wrapper nay có setShift kiểm tra50..200, không đóng/open khi đổi giá trị.
- ImportedFxChain.updatePitchShift cập nhật stage đã có và cấu hình dùng cho fresh, giữ các stage khác và tail. Module bị disable cũng cập nhật cấu hình nhưng không tạo native handle. Không áp dụng fast path khi Enable/realtime/type đổi.
- Track event66 Key1 dùng fast path khi module là PitchShift; các nhánh khác giữ cơ chế trước. Build debug, ImportedFxChainTest, ReferenceRhythmPlayerTest, ImportedSectionFallbackTest đạt. Test có PitchShift disabled và Echo active xác nhận đổi Shift không làm mất echo đang chờ.
- Giới hạn: test host chưa chứng minh giữ phase native khi PitchShift active; cần thu âm/kiểm tra JNI trên thiết bị. Module khác và mode FX chưa cập nhật tại chỗ. Không sửa SET hoặc tạo file test/log mới.
## TrackFXParam66 cho Pitch Shift và Band Booster, 26-10-2026

- Xác minh đúng class sofeh.audio.f được import từ z01, không phải defpackage.f crypto. Shift có min50/max200; normalized0 thành125, không phải100. Band Booster sofeh.audio.b có min-100/max100 cho ba tham số, serialize theo g2,g1,g0,reserved.
- Helper nhận payload4 byte type15/31, dùng min phù hợp và ghi byte band đảo thứ tự, giữ nguyên byte reserved. Loader/player hiện có nhận qua cùng helper, mode vẫn chưa hỗ trợ.
- assembleDebug và 96 kiểm thử ImportedFxChainTest, ReferenceRhythmPlayerTest, ImportedSectionFallbackTest đạt. Kiểm tra shift50/125/200 và band payload100,25,156,reserved77. Host DSP kiểm tra finite không chứng minh native audio chính xác hay stereo isolation.
- Giới hạn: PitchShift gốc thay g0 trên native handle hiện tại, ORGX tái tạo chain nên chưa giữ phase/buffer liên tục; cần sửa vòng đời module riêng. Không sửa SET hoặc tạo file test/log mới.
## TrackFXParam66 cho Distortion, 26-10-2026

- Đối chiếu r01.i và mảng h/i: tham số gain/cutoff có min1 max100, tham số soft có min/max0. Touch dùng b44.f và Math.round như các setter khác.
- Helper hỗ trợ payload9 byte của type23: gain int tại0, soft byte tại4, cutoff int tại5. Nhánh tham số2 chỉ ghi byte4, không ghi đè cutoff; Key1/3 dùng min1 thay vì0.
- assembleDebug và ImportedFxChainTest, ReferenceRhythmPlayerTest, ImportedSectionFallbackTest đạt. Kiểm tra gain normalized-100 thành1, cutoff normalized0 thành51, soft normalized100 thành0 theo setter gốc; output DSP hữu hạn, không rò kênh phải.
- PitchShift chưa triển khai automation ở lượt này: class f decompile trùng alias với class crypto, cần xác minh đúng bytecode trước khi lấy giới hạn. Mode FX và cập nhật riêng stage chưa đầy đủ. Không sửa SET hoặc tạo file test/log mới.
## TrackFXParam66 cho High/Low Pass, Robot và Stereo Width, 26-10-2026

- Đối chiếu u01 kế thừa v01.i, x01.i và y01.i: High/Low Pass type9/10 tham số0 ánh xạ0..150000, Robot16 ánh xạ0..1000, Stereo Width17 ánh xạ0..50000. Touch chuẩn hóa-100..100, Key1 chọn tham số0.
- Helper automation nhận payload4 byte cho bốn module thay vì bắt buộc8 byte; ghi integer little-endian và giữ nguyên Enable. Setter không tồn tại (Key2 trở lên) vẫn chưa được nhận. Loader và player dùng cùng helper hiện có.
- assembleDebug, ImportedFxChainTest, ReferenceRhythmPlayerTest, ImportedSectionFallbackTest đạt. Kiểm tra biên và trung điểm normalized, kích thước payload, từ chối tham số thứ hai; chạy DSP từng cấu hình để xác nhận output hữu hạn.
- Giới hạn: chưa mode FX và cập nhật riêng stage; whole-chain vẫn reset khi sửa tham số. Chưa thu âm so sánh native APK trên thiết bị. Không sửa SET hoặc tạo file test/log mới.
## TrackFXParam66 cho Bass Booster và Band Pass, 26-10-2026

- Đối chiếu p01.i và v01.i cùng mảng min/max: Bass Booster type20 tham số0/1 đều0..100; Band Pass type1 tần số0..150000, width0..25000. Event Key1/2 chọn tham số0/1, Touch-100..100 ánh xạ Math.round như b44.f.
- Mở rộng helper automation hiện có, ghi payload int little-endian và giữ nguyên tham số/module enable khác. Bộ tương thích dùng cùng helper và thử chain payload mới trước khi nhận event. Không đổi nhánh ModeSet hoặc Type0.
- assembleDebug và ImportedFxChainTest, ReferenceRhythmPlayerTest, ImportedSectionFallbackTest đạt. Kiểm tra tần số normalized0 thành75000,width100 thành25000; DSP filter hữu hạn và không rò stereo. Timeline Bass từ gain0 sang100 rồi0 tác động output mà không cần event bật riêng.
- Giới hạn: vẫn dựng lại whole-chain, chưa cập nhật riêng native stage; mode FX và các module khác chưa đầy đủ. Không sửa SET hoặc tạo file test/log mới; chưa thu âm so sánh APK trên thiết bị.
## TrackFXParam66 đổi tham số Echo và Stereo Delay, 26-10-2026

- Đối chiếu s01.i và b44.f: Touch chuẩn hóa -100..100 được Math.round ánh xạ vào min/max tham số; mức Echo0..99, delay0..100. t01 Stereo Delay kế thừa setter này. Key1/2 tương ứng tham số0/1.
- ImportedTrackFxAutomation sửa đúng integer little-endian trong payload của module4/6, giữ các tham số khác và Enable. RhythmPlayer dùng helper cho enable hoặc tham số; loader kiểm tra payload mới trước khi chấp nhận. Type0 vẫn bỏ qua, mode FX vẫn báo chưa hỗ trợ.
- assembleDebug và ImportedFxChainTest, ReferenceRhythmPlayerTest, ImportedSectionFallbackTest đạt. Kiểm tra normalized0 thành mức50,100 thành delay100,-100 thành mức0 và FX không sinh echo khi feedback0.
- Giới hạn: tái tạo toàn chain thay vì cập nhật native module tại chỗ; chưa mode FX, module khác hoặc giá trị chuẩn hóa ngoài -100..100; delay0 của Stereo vẫn bị từ chối theo giới hạn DSP hiện có. Không sửa SET hoặc tạo file test/log mới.
## TrackFXParam66: bật/tắt module FX channel, 26-10-2026

- Đối chiếu Track.Play66: Type0 bỏ qua, ID là index module, Key0 đổi Enable theo Touch!=0 rồi z01.c; Key>0 gọi setter riêng theo loại module. Dựng nhánh Key0 cho track không dùng mode; không tự suy đoán payload tham số.
- Player cập nhật module trong currentChannel, dựng lại chain và giữ trạng thái mới cho các lần merge STS sau. Loader lọc66 của track Type0 theo APK, từ chối mode FX automation và parameter chưa hỗ trợ với lý do rõ; module được yêu cầu bật được kiểm tra trước trên cấu hình ban đầu.
- assembleDebug, ReferenceRhythmPlayerTest và ImportedSectionFallbackTest đạt. Kiểm tra module20 ban đầu tắt, event66 bật tăng output từ1 lên1.5, rồi tắt về1 trong khi note tiếp tục chạy.
- Giới hạn: chưa setter tham số Key>0, chưa ModeSet sau FX edit; tái tạo whole-chain có thể reset module khác khác native. Module đổi bởi STS trước event66 chưa được kiểm tra toàn bộ đường trạng thái. Không sửa SET hoặc tạo file test/log mới.
## Hiển thị lỗi điều khiển style/PAD thay vì giữ nội bộ, 26-10-2026

- Loader giữ reason của section/linked group không chuẩn bị được, đưa vào transitionError của Rhythm/Jump thay cho thông báo chung. Không thay cơ chế hỗ trợ của group.
- PadBank công bố playbackError từ sequence kèm slot PAD; clear khi nhận thay đổi PAD/close. TempoError được UI đọc cùng playbackError.
- MainActivity theo dõi lỗi của session đang phát và PAD, hiển thị Toast khi lỗi thay đổi, không lặp mỗi lần polling. Đổi session đặt lại dấu vết để lỗi của session mới có thể được báo.
- assembleDebug và ImportedStyleSessionTest, ImportedSectionFallbackTest, ImportedTempLoopTest đạt. Không tạo thêm file test/log; chưa kiểm tra trực quan Toast với SET gây lỗi trên điện thoại.
- Đây là cải thiện chẩn đoán, không tuyên bố dựng thêm engine hoặc làm group unsupported phát được. Thông báo kỹ thuật hiện còn tiếng Anh theo reason hiện có. Không sửa SET.
## RhythmJump phân biệt target thiếu và target chưa phát được, 26-10-2026

- Bản trước203 dùng id.takeIf(in players), làm target có trong SET nhưng chưa có player bị biến thành null và xóa Jump đang chờ. Session nay giữ Jump trước đó và ghi transitionError cho target known nhưng chưa playable; chỉ target không tồn tại mới clear Jump theo Group(null).
- PadSequence có transitionError tương ứng khi metadata group có nhưng player thiếu; start mới xóa chẩn đoán cũ. Target hợp lệ tiếp tục bỏ tempLoop và thay Jump.
- assembleDebug và ImportedStyleSessionTest, ImportedTempLoopTest, ImportedSectionFallbackTest đạt. Test Intro nhận JumpMain rồi Jump tới group chưa playable: lỗi được ghi, JumpMain vẫn chuyển sau khi Intro kết thúc.
- Đây là xử lý lỗi của ORGX khi chưa hỗ trợ target, không phải khẳng định APK gốc có cùng fallback. Chưa có UI riêng hiển thị lỗi PadSequence và chưa giải quyết mọi target không hỗ trợ. Không sửa SET hoặc tạo file test/log mới.
## Không để đồng bộ UI xóa hợp âm của timeline, 26-10-2026

- Phát hiện setter AudioEngine.chordIndex luôn clearChord, trong khi MainActivity polling detectedKeyboardChord mỗi40ms và các SideEffect gán lại chordIndex. Vì thế event202 vừa áp dụng có thể mất do UI, không phải thao tác chọn mới.
- chordIndex nay là giá trị fallback đồng bộ bình thường; selectChord là thao tác explicit xóa override. Panel chọn hợp âm và nạp project gọi selectChord; bàn phím vùng chord và đổi SET vẫn chủ động clear như trước.
- Poll UI hiển thị selectedSetChord đang hiệu lực, không chỉ hợp âm bàn phím đã nhớ; trạng thái noHeldChord dùng cùng kết quả.
- assembleDebug và các suite hiện có ReferenceRhythmPlayerTest, KeyboardChordInputTest, ImportedSectionFallbackTest đạt. Chưa có kiểm tra UI/thiết bị chứng minh event202 giữ xuyên polling; không thêm test phản chiếu setter. Không sửa SET hay tạo file test/log mới.
- Nhánh Music.Stop/lastTempo đã được khảo sát nhưng chưa thay đổi vì phải phân biệt dừng arranger với playback Music đầy đủ.
## PAD chạy riêng áp dụng Tempo221/222, 26-10-2026

- PadBank có handler tempo cục bộ khi không gắn style owner, nhận tempo/lock từ player và cả sequence; AudioEngine tiêu thụ thay đổi sau bước render PAD. Đồng hồ riêng PAD dùng tempo mới ngay khi lệnh đã dispatch.
- Khi có style owner, lệnh vẫn chuyển tới session; AudioEngine nhận thông báo ngay sau render PAD thay vì đợi lần rhythmSample tiếp theo. Đổi owner/đóng bank xóa thông báo chưa dùng để không áp dụng lại tempo cũ.
- assembleDebug và ImportedTempLoopTest, ImportedStyleSessionTest, ImportedSectionFallbackTest đạt. Kiểm tra PAD riêng đổi160 và khóa, tiêu thụ một lần; có owner gửi180 tới owner, tháo owner chuyển lại đường cục bộ.
- Giới hạn: phạm vi40..240 BPM, chưa khôi phục lastTempo theo toàn bộ Music.Stop; chưa chứng minh đồng bộ từng mẫu giữa nhiều PAD trong cùng frame. Không sửa SET hoặc tạo file test/log mới.
## Timeline Tempo221/222 điều khiển tốc độ và khóa tempo, 26-10-2026

- Đối chiếu Track.Play221 đặt TempoLock theo Key>0; 222 đặt Tempo trực tiếp, không kiểm tra lock. Player dispatch hai event tới owner; bộ tương thích giữ cả control-only track.
- StyleSession thay lock/tempo và trả thay đổi cho AudioEngine; engine cập nhật BPM và trạng thái styleTempoLock. Đổi section khi lock giữ nguyên tốc độ đang chạy. PAD/chuỗi PAD gửi cùng lệnh về style khi đồng bộ; handler được tháo khi style không chạy.
- Reset/stop xóa thông báo lock chưa tiêu thụ để không phát lại trạng thái cũ. Build debug và ImportedStyleSessionTest, ImportedSectionFallbackTest, ImportedTempLoopTest đạt, gồm khóa rồi đổi tempo160 và chuyển sang section tempo90 không ghi đè.
- Giới hạn: ORGX chỉ nhận tempo40..240 và báo lỗi ngoài khoảng này, trong khi APK gốc gán trực tiếp; tempo từ PAD được engine tiêu thụ lượt xử lý kế tiếp. PAD chạy riêng/GroupPlay nhiều slot và khôi phục lastTempo sau Music.Stop chưa đầy đủ. Không sửa SET hoặc tạo file test/log mới.
## Nạp trước target Rhythm từ PAD của style, 26-10-2026

- Catalog chọn PAD theo parent style rồi fallback parent0, lần theo các group liên quan và thu target201 của track trong chuỗi. Target cùng các nhánh Jump/TempLoop tiếp theo được đưa vào control graph của session chính, không thêm vào danh sách nút section.
- loadStyle nạp player/PCM của target trước lúc phát; lệnh từ PAD có thể vào hàng đợi style ngay mà không cần chuyển ownership hoặc đọc file trên audio thread. Hợp nhất theo ID tránh nạp trùng. Giữ giới hạn graph64 group và ngân sách PCM64MB hiện có.
- assembleDebug và ImportedSectionFallbackTest, ImportedStyleSessionTest, ImportedTempLoopTest đạt. Integration dùng PAD fallback group0 có JumpGroup sang continuation chứa201 tới group ngoài style, xác nhận target nạp sẵn và PadBank chuyển style đến đó không có transitionError.
- Giới hạn: graph lớn hoặc vượt PCM budget sẽ bị từ chối, chưa nạp động; group Section0 chạy song song và Touch chọn slot khác chưa dựng. Chưa đo âm thanh PAD thực với APK gốc. Không sửa SET hoặc tạo file test/log mới.
## PAD gửi Rhythm201 về style chính, phân biệt target thiếu và chưa nạp, 26-10-2026

- StyleSession gắn handler Rhythm cho PadBank khi render đồng bộ; handler truyền tới cả player hiện tại, sequence JumpGroup và PAD nhận từ mailbox. Khi style không chạy, AudioEngine tháo handler như đường mute để không gọi session cũ.
- Session nhận danh sách group ID từ index SET. Target có trong SET nhưng không có player không còn được coi như Group(null): ghi transitionError và giữ style chạy. Target thực sự không tồn tại vẫn xóa queue/dừng theo nhánh đã dựng.
- Build debug và ImportedStyleSessionTest, ImportedSectionFallbackTest, ImportedTempLoopTest đạt. Integration PadBank gửi201 ở100, style giữ group cũ trước cửa sổ1000 rồi chuyển group2. Kiểm tra target có ID nhưng chưa có player không làm dừng.
- Giới hạn: target từ PAD phải đã có player trong session chính; chưa chuyển ownership PCM từ PAD vào style hoặc nạp động. Touch chọn nhiều GroupPlay slot và Section0 song song vẫn chưa dựng. Không sửa SET hoặc tạo file test/log mới.
## Rhythm201 điều khiển hàng đợi style chính, 26-10-2026

- Đối chiếu Track.Play201: CloseAll(true) trên track gửi lệnh; group hợp lệ đi GroupPlayAdd, group null xóa queue và đóng group hiện tại. Player nay dispatch Rhythm command, bộ tương thích giữ event và control-only track.
- StyleSession dùng queue request theo cửa sổ nhịp, không đợi section hoàn tất như Jump203. Target ngoài player dừng session, xóa queue/Jump và release group. Catalog nạp trước target201 cùng target203/204, không thêm nút console ngoài style.
- assembleDebug và ImportedStyleSessionTest, ImportedSectionFallbackTest, ImportedTempLoopTest đạt. Kiểm tra Intro dài4000 có201 ở100 vẫn chờ window1000 rồi chuyển Main, cùng target999 dừng session.
- Giới hạn: mới hỗ trợ owner style chính; Touch chọn slot GroupPlay khác, group Section0 chạy song song và lệnh201 từ PAD chưa dựng đầy đủ. Target tồn tại nhưng bị loại vì không hỗ trợ hiện cũng dừng thay vì phân biệt null; cần cải thiện chẩn đoán. Không sửa SET hay tạo file test/log mới.
## Timeline SET thực thi mute hợp âm/trống 205/206, 26-10-2026

- Track.Play205/206 gọi GroupPlayAddChordMute/DrumMute, thêm marker vào queue GroupPlay0. ORGX nay dispatch hai lệnh vào hàng đợi style, toggle ở cửa sổ rhythm thay vì mute ngay lúc nhận event. Command từ dữ liệu không dùng giới hạn queue của nút Rhythm.
- Bộ tương thích nhận hai lệnh và giữ track chỉ chứa điều khiển. PAD đang chạy cùng style gửi mute tới owner style; khi không có style đang chạy thì tháo handler để không giữ session cũ.
- Build debug và ImportedStyleSessionTest, ImportedTempLoopTest, ImportedSectionFallbackTest đạt. Test nhận205 ở100 rồi206 ở1100 xác nhận trạng thái chưa đổi trước window, chord/drum đổi độc lập và không đổi section/dừng transport.
- Giới hạn: khi chỉ PAD chạy chưa có GroupPlay0 độc lập để giữ mute; callback standalone không có owner vẫn không áp dụng. Rhythm201 và nhiều slot GroupPlay chưa hoàn tất. Không sửa SET hoặc tạo file test/log mới.
## RhythmJump 203 thực thi target sau section và nạp trước group liên quan, 26-10-2026

- Đối chiếu Track.Play203 và Tracks.GroupPlayJump/Next: cắt channel của track gửi lệnh, đặt Jump chờ section không Loop/tempLoop kết thúc. Không chuyển ngay tại thời điểm event.
- ReferenceRhythmPlayer gửi command đến session/sequence. StyleSession ghi jumpId, target không có trong player sẽ xóa Jump như Group(null); PAD giữ override Jump và awaitingContinuation để không bị dọn trước lúc chuyển. Target được bỏ cờ temporary loop.
- Catalog theo cả target event203/204 trong track của group, không chỉ metadata JumpGroup; seen-set ngăn chu trình. Loader chuẩn bị PCM trước khi chạy, target ngoài style không xuất hiện thêm như nút trên console.
- assembleDebug và ImportedSectionFallbackTest, ImportedTempLoopTest, ImportedStyleSessionTest đạt. Integration loadStyle có Intro chứa203 tới Ending ngoài style: target được nạp, sau nửa section vẫn Intro, kết thúc mới chuyển target.
- Giới hạn: mới nối Jump cho luồng style/PAD hiện hành, chưa hỗ trợ nhiều GroupPlay slot của event Touch; Rhythm201, mute205/206 trong timeline chưa dựng. Player standalone không có owner chưa đổi group. Không sửa SET hoặc tạo file test/log mới.
## EndTime gốc cho lệnh lặp tạm của group không giới hạn, 26-10-2026

- Đối chiếu Track.EndTime và Tracks.EndTime: lấy Time + Length - 1 của event cuối từng track, rồi lấy max trong group, giá trị tối thiểu 0; không lấy nốt dài nhất và không loại track mute.
- Loader tính map thời điểm kết thúc từ tất cả track của SET, truyền cho StyleSession và PadSequence. Điều kiện event204 dùng group.limit hoặc EndTime>10; không còn dựa group.end metadata khi group không giới hạn. Constructor tạo thủ công vẫn có fallback tính từ các player đã nạp.
- assembleDebug và ImportedTempLoopTest, ImportedStyleSessionTest, ImportedSectionFallbackTest đạt. Kiểm tra biên EndTime10/11 với metadata end9999 và track mute có event cuối muộn hơn, cùng trường hợp nốt dài trước event cuối không quyết định EndTime.
- Chưa dùng EndTime này để thay đổi toàn bộ transport, chưa nạp mọi target event204 ngoài nhóm đã chuẩn bị; không coi vòng đời group hoàn tất. Không sửa SET hoặc tạo file test/log mới.
## Thực thi RhythmTempLoop 204 từ timeline SET, 26-10-2026

- Đối chiếu Track.Play(204): đánh dấu tempLoop của group một lần phát, không đổi Loop gốc. ReferenceRhythmPlayer phát command tới chủ sở hữu session/sequence thay vì bỏ qua; bộ tương thích và chọn track giữ cả track chỉ chứa lệnh 204.
- StyleSession cập nhật temporaryLoops, dùng cơ chế quay section hiện có; chuỗi PAD giữ tập temporaryLoops và quay group hiện tại thay vì đi JumpGroup. awaitingContinuation giữ PAD sống qua frame kết thúc để vòng tiếp theo được khởi động. Start chuỗi mới xóa trạng thái tạm.
- Build debug và ImportedTempLoopTest, ImportedStyleSessionTest, ImportedSectionFallbackTest đạt. Kiểm tra event204 giữ Intro qua nhiều vòng, người chơi chuyển Main vẫn thoát được, và PAD không đi JumpGroup khi đang temp loop.
- Giới hạn: target phải thuộc nhóm đã nạp; điều kiện group không giới hạn mới xét end metadata, chưa tính đầy đủ Tracks.EndTime từ toàn event cho group không giới hạn. Player standalone không có session/sequence chưa thực hiện chuyển vòng theo command. Không sửa SET hoặc tạo file test/log mới.
## Lệnh bật FX lặp khởi tạo lại bus, 26-10-2026

- Track.Play 241–246 luôn gọi z01.c, không kiểm tra enable cũ; z01.c gọi khởi tạo/đóng module tương ứng. ORGX trước chỉ thay chain khi boolean đổi, nên lệnh bật lặp không có tác dụng với echo đang chờ.
- Music state giữ revision riêng Music/Wave/Rhythm; mỗi event tăng đúng revision của bus. Mixer Music, Wave chain và Rhythm group tái tạo chain khi revision đổi, kể cả enable giữ nguyên. Không thay chain khi chỉ quan sát lại cùng revision qua các frame.
- Chuyển section nhận cả revision của live Rhythm chain để không làm mới đuôi âm thanh ngoài ý muốn sau khi chuyển ownership.
- assembleDebug và ImportedKeyboardMixerTest, ImportedGroupFxTest, ImportedStyleSessionTest, ReferenceRhythmPlayerTest đạt. Kiểm tra echo đã nhận xung bị xóa khi enable lặp, sau đó nhận xung mới vẫn trả echo đúng delay; revision Wave không đổi revision Rhythm/Music.
- Giới hạn: ORGX tái tạo toàn chain, chưa đối chiếu từng native module có a/c là no-op hoặc giữ bộ nhớ khác nhau; chưa sửa toàn bộ vòng đời Music.Play/Stop. Không sửa SET hoặc tạo file test/log mới.
## Khôi phục metadata hợp âm của SET cũ theo group, 26-10-2026

- Đối chiếu cuối Tracks.ReadFromStream: Music version <1.4.8 ghi đè fChordRoot/fChordType của mỗi track bằng ChordBase/ChordType của group chứa track. ORGX đã đọc cả hai nguồn nhưng chưa thực hiện bước nâng cấp này.
- Sau khi đọc group, parser áp dụng chuyển đổi cho track có group tương ứng; track không có group giữ nguyên. Dùng version container Music theo APK, không lấy version riêng của Track. So sánh version theo thành phần số để 1.4.10 không bị coi là cũ hơn 1.4.8.
- Điều này sửa dữ liệu nguồn của CalcChordKey cho style cũ, không sửa bảng nốt hoặc ghi ngược SET. Metadata kind/type, sample selection và sourceVersion vẫn giữ riêng.
- assembleDebug, ImportedSectionFallbackTest, parser legacyStsReservedByteDoesNotSkipMidiOrInstrumentFields và ReferenceChordTransposerTest đạt. Kiểm tra group có nguồn khác track, orphan, container cũ chứa track mới và biên 1.4.8/1.4.10/2.0.0.
- Giới hạn: chưa thu âm đối chiếu các style bị ảnh hưởng trên thiết bị; không coi đây là hoàn tất toàn bộ dữ liệu SET. Không sửa SET hay tạo file test/log mới.
## Sửa bộ đọc SET nhầm Type và Kind của Track, 26-10-2026

- Track.ReadFromStream ghi hai byte liên tiếp fType rồi fKind. ORGX trước bỏ byte đầu và đọc byte sau vào kind; các nhánh STS/channel lại dùng kind như Type. Nay ImportedTrack giữ riêng type (byte đầu có dấu), kind vẫn là byte thứ hai có dấu.
- STS batch chọn tiếng keyboard/chord, tạo STSPlan và điều kiện expression routing dùng type; chuyển kiểu nhạc cụ ở Tracks.GroupPlayNext vẫn dùng kind. Chord part được xác định bằng type3, không phải kind3.
- Đối chiếu Tracks.RecordMusic: type3 chỉ phát nốt keyboard trực tiếp khi kind=-1. Part có acceptsKeys tương ứng để chord-only không phát thêm tiếng nốt thường. Record/mute vẫn áp dụng; direct chord giữ đường riêng.
- Kiểm thử binary parser legacy chứa Type3/Kind-1 xác nhận hai byte không bị gộp hoặc mất dấu; kiểm tra keyboard chord-only không nhận noteOn nhưng vẫn phát mask. Các fixture expression/STSs được sửa để mô tả đúng trường Type.
- Kiểm tra: assembleDebug và các kiểm thử parser legacy, ImportedKeyboardPartsTest, ImportedStsBatchTest, ImportedExpressionRoutingTest, stylePickerDistinguishesMissingIndexFromSupportedStyle đạt. Lượt chạy toàn ImportedStylePlayerTest phát hiện assertion cũ coi group rỗng là lỗi; cập nhật theo cơ chế silent ending đã có và chạy lại ca đó đạt.
- Đây là sửa lỗi nền của parser và routing; các ghi chú trước dùng chữ Kind để chỉ Type ở STS không còn chính xác. Không sửa SET hay tạo file test/log mới; chưa đối chiếu toàn bộ kiểu track của SET thực với APK trên thiết bị.
## Chọn đúng chord part theo Track Type 3, 26-10-2026

- Đối chiếu Tracks.RecordChord: chỉ track Record và fType=3 nhận event Chord. Bản direct nhiều part trước đã áp dụng cho cả tiếng keyboard thường; sửa bằng cờ chordPart từ ImportedTrack.kind, không phát direct ở part 0/1.
- Tìm thấy loader STS chỉ giữ kind0/1, khiến chord part chưa bao giờ được nạp dù bộ phát đã có. Mở rộng chuẩn bị STS đầy đủ, basis snapshot và chuyển STS để giữ cả kind3 trong bốn part được hỗ trợ. Không tự đổi STSPlan event61/64 vốn có điều kiện type riêng của APK.
- enabled của part kiểm tra cả snapshot Record và track mute. Đổi loại part từ chord sang keyboard cắt direct voice cũ; mask0/part bị bỏ vẫn cắt như trước. Bỏ tạo danh sách filter part trên mỗi mẫu audio.
- Build debug và ImportedKeyboardPartsTest, ImportedStsBatchTest, ImportedKeyboardPartQueueTest, ImportedStsPlanTest đạt. Integration STS xác nhận Type3 được chuẩn bị thành chordPart; kiểm tra part thường không phát hợp âm, part chord tắt Record im lặng và đổi loại part cắt tiếng.
- Giới hạn: còn phạm vi tối đa bốn part, chưa phục hồi đầy đủ trường fKind của Track cho nhánh keyboard type3 đặc biệt; chưa đo audio thực với APK gốc. Không sửa SET hoặc tạo file test/log mới.
## Hợp âm trực tiếp qua nhiều part STS, 26-10-2026

- ImportedKeyboardParts quản lý direct chord riêng theo part, dùng sound/mode, inputGain, enabled/mute, pedal và pedalMode của part. Nguồn Music release/pitch được gắn như đường bàn phím. OpenKey trực tiếp không phát Touch script.
- AudioEngine bỏ điều kiện chặn STS parts cho nhánh direct khi Left Chord bật, Sync Start tắt và style/PAD không chạy; chọn đường sound đơn hoặc part STS để không phát trùng. Dữ liệu part vẫn thuộc audio thread qua queue.
- Mask0, part bị bỏ, retire/reset/Cut dọn voice; khi điều kiện direct không còn, không giữ tiếng chord cũ. Không tạo danh sách lọc part tạm ở mỗi mẫu; khi mask0 và không có chord thì thoát ngay.
- Build debug và 50 kiểm thử ImportedKeyboardMixerTest, ImportedKeyboardPartQueueTest, ImportedKeyboardPartsTest, KeyboardChordInputTest đạt. Kiểm tra mute/gain riêng hai part, xóa part cũ, mask0 và retire. Không sửa SET hoặc tạo file test/log mới.
- Giới hạn: đây là nối fallback OpenKey đã xác minh vào kiến trúc STS hiện có; chưa đối chiếu việc chọn chord-track riêng trong APK gốc, octave/tuning đặc thù hoặc audio nhiều part trên thiết bị. Nhánh event202 ngoài transport vẫn chưa đầy đủ.
## Hợp âm trực tiếp khi không chạy group: đối chiếu bytecode và nối tiếng SET đơn, 26-10-2026

- Disassemble Track từ classes3/classes dex của APK bằng baksmali SDK vào thư mục TEMP, chỉ đọc class Track. Bytecode GroupPlayState=0 và SyncStart/type3 xác nhận nhánh CloseAll(true), duyệt pitch 0..11, mở key=36+pitch, velocity100, gọi Channels.OpenKey trực tiếp; không phải Track._scriptTouch. Không thêm bản dump vào project.
- ImportedDirectChord trong file keyboard parts quản lý voice riêng trên audio thread, dùng sound SET đang chọn; thay mask cắt voice cũ, mask0/dừng group cắt tiếng. Không kích hoạt Touch DNC; tiếng vẫn qua mode/mix và mixer đã có.
- AudioEngine nối từ mask đã xử lý single/dual chord input khi Left Chord bật, Sync Start tắt, không có rhythm/PAD chạy và không dùng nhiều part STS. Khi group bắt đầu hoặc điều kiện không còn phù hợp, chord trực tiếp được cắt; Cut/Fade/close cũng dọn voice.
- Build debug và ImportedKeyboardPartsTest, KeyboardChordInputTest, ImportedKeyboardMixerTest đạt. Kiểm tra ba nốt của mask, đổi sang hai nốt không cộng chồng, mask0 và Cut; sound có Touch handled vẫn phát vì gọi OpenKey trực tiếp.
- Giới hạn: chỉ nối tiếng SET đơn, chưa chọn chord-track instrument riêng hoặc nhóm nhiều part STS; chưa mở nhánh trực tiếp từ event202 ngoài transport. Chưa đối chiếu audio trên thiết bị, chưa coi toàn bộ fallback Track.Play202 hoàn thành. Không sửa SET hoặc tạo file test/log mới.
## Style dùng hợp âm đã lưu mà không cần callback Follow Chord, 26-10-2026

- Bổ sung ReferenceChordTransposer.find để nhận mask bằng cùng scale/table dùng chuyển nốt. Loader tự bật mapping hợp âm lưu khi có event 202, accompaniment được nạp và có bảng transposer, kể cả khi targetChord callback không được truyền.
- Các track đọc mask/octave từ Music state hiện tại, ưu tiên hợp âm lưu rồi mới tới callback input. Dùng chung trạng thái giữa mọi section và nhóm JumpGroup đã nạp; AudioEngine vẫn có thể gắn trạng thái Music chung của ứng dụng.
- Build debug và kiểm thử ReferenceChordTransposerTest, ImportedSectionFallbackTest, ReferenceRhythmPlayerTest, ImportedStyleSessionTest đạt. Integration loadStyle chứa track điều khiển mask D và track nốt C, xác nhận chuyển sang mẫu D mà không có callback Follow Chord.
- Nhánh phát hợp âm trực tiếp khi không có group chạy vẫn chưa dựng: Track.Play đoạn này có biến boolean/int decompile không rõ, cần đối chiếu bytecode trước khi chọn cách mở tiếng. Không coi phần đó đã hoàn thành. Cần bảng transposer và nạp accompaniment; chế độ chỉ trống không tự thêm tiếng đệm. Không sửa SET hoặc tạo file test/log mới.
## Chord event 202 cập nhật hòa âm dùng chung, 26-10-2026

- Đối chiếu Track.Play case Chord và Tracks.GroupPlayChord: Key là mask 12 nốt, Tune là octave move; GroupPlayChord dùng ChordFind để tìm số hợp âm. Bỏ nhánh no-op trước đây; cho event qua kiểm tra tương thích và giữ cả track chỉ chứa lệnh hợp âm.
- ImportedMusicState lưu mask/octave; AudioEngine.selectedSetChord nhận dạng qua ReferenceChordMatcher và bảng referenceChords. Các style/PAD đã bật Follow Chord dùng target chung này, bao gồm mở lại nốt đang giữ qua cơ chế refreshChord hiện hành. Mask không nhận dạng trả -1. Đổi SET, chọn hợp âm trên UI hoặc nhấn phím vùng hợp âm xóa override từ event.
- Build debug và 93 kiểm thử thuộc ImportedSectionFallbackTest, ImportedStyleSessionTest, ReferenceChordMatcherTest, ReferenceRhythmPlayerTest đạt. Kiểm tra C sang Dm bằng mask, octave +/-1, mask rỗng và trả quyền điều khiển về input. Không sửa SET hoặc tạo file test/log mới.
- Giới hạn: chưa dựng nhánh mở hợp âm trực tiếp khi không có group đang chạy trong Track.Play(202); vẫn cần bật Follow Chord để style/PAD chuyển theo target này. Chưa đo đối chiếu audio trên thiết bị, chưa bảo đảm thứ tự cập nhật cùng mẫu giữa mọi track/group như APK gốc.
## Bật/tắt FX bus theo event 241–246, 26-10-2026

- Đối chiếu Track.Play và z01.c: 241/242 tắt/bật Music, 243/244 Wave (APK gốc gồm cả WaveMic), 245/246 Rhythm. Trước đây player bỏ qua tất cả sáu lệnh và bộ tương thích vẫn từ chối chúng.
- ImportedMusicState giữ riêng trạng thái bus. Music mixer lấy trạng thái sau khi track/PAD đã xử lý điều khiển trong khung âm thanh; Rhythm group thay enable của bus chung và giữ cấu hình gốc để trở lại hoặc chuyển section đúng. FX của từng tiếng/channel không bị tắt theo Rhythm.
- Wave DSP nhận âm thanh hiện có từ SampleBank/factory PAD, dùng block Wave trong SET; thay module hoặc enable sẽ khởi tạo chain mới, bỏ tail cũ khi tắt. Wave chain được reset khi Cut và đóng khi AudioEngine đóng. Không dựng lại chain hoặc copy settings ở mỗi mẫu khi trạng thái không đổi.
- Build debug và 104 kiểm thử thuộc ImportedGroupFxTest, ImportedKeyboardMixerTest, ImportedSectionFallbackTest, ImportedStyleSessionTest, ReferenceRhythmPlayerTest đạt. Kiểm tra Rhythm đang phát qua cả hai trạng thái và Music echo không quay lại sau khi bật lại. Cài debug lên thiết bị thành công.
- Giới hạn: Wave nhúng event 101 và microphone/WaveMic chưa được dựng; bus Rhythm vẫn xử lý theo player/section/PAD của ORGX, chưa chứng minh tương đương một bus tổng của APK gốc. Chưa đo âm thanh Wave trên thiết bị; lặp lại cùng lệnh enable khi bus đã cùng trạng thái chưa mô phỏng toàn bộ vòng đời native. Không sửa SET hoặc tạo file test/log mới.
## Music KeyMove/OctaveMove có tác dụng khi phát, 26-10-2026

- Đối chiếu Track.Play 231/232 và Channels.OpenKey: Music transpose cộng vào nhạc cụ melodic; Music octave dùng cho channel bàn phím, còn rhythm dùng Rhythm octave. Trước đây hai event bị bỏ qua ở player và còn bị bộ tương thích từ chối.
- ImportedMusicState dùng chung giữa các track/section, bàn phím, part STS và PAD. Event 231/232 cập nhật trạng thái này; nốt mới lấy pitch mới. Bàn phím trừ phần Music pitch đã gộp lúc nạp để không cộng lặp; chỉ số bảng tuning cũng bù Music transpose hiện tại.
- Giữ track chỉ chứa 231/232 để điều khiển track khác. Khi SET có KeyMove, loader giữ vùng mẫu melodic cần cho việc đổi pitch trong ngân sách PCM hiện hành, thay vì cắt theo nốt ban đầu rồi phát thiếu mẫu.
- Build debug và 133 kiểm thử thuộc ImportedKeyboardPartsTest, ImportedSectionFallbackTest, ImportedStyleSessionTest, ReferenceRhythmPlayerTest, SampledInstrumentTest đạt. Đường loadStyle kiểm tra điều khiển ở track riêng chuyển note 60 sang sample exact 72. Đã cài debug lên thiết bị và gửi mở MainActivity thành công.
- Giới hạn: chưa đối chiếu toàn bộ script DNC đặt key tuyệt đối, cơ chế lưu/khôi phục lastFX của Music.Stop và các nút bật/tắt bus FX. Chưa chứng minh tương đương âm thanh tất cả SET. Không sửa SET hoặc tạo file test/log mới.
## Music release cập nhật động cho lần mở nốt kế tiếp, 26-10-2026

- AudioEngine cung cấp Music release hiện tại từ STS Music đang áp dụng, hoặc cấu hình SET nếu không có STS Music. Các nốt bàn phím, part STS, style session và PAD/chuỗi JumpGroup dùng cùng nguồn trạng thái này.
- SampledInstrument truyền nguồn Music release tới các mode, layer, Aftertouch và sound script. Playback chỉ đọc khi xây envelope mới; envelope của nốt đang giữ/nhả không bị sửa. copySampleSource giữ liên kết Music cùng release gốc để phục hồi được từ mức 0 khi nạp.
- Nốt Repeat hoặc mở lại theo hợp âm đọc mức mới khi mở; script release override nhân với tỷ lệ channel và Music một lần theo Channels.OpenKey. Không sửa SET hay nạp lại PCM để đổi release.
- assembleDebug và 212 kiểm thử thuộc SampledInstrumentTest, ImportedKeyboardPartsTest, ImportedKeyboardPartQueueTest, ReferenceRhythmPlayerTest, ImportedScriptPlaybackTest, ImportedSectionFallbackTest đạt. Kiểm tra giữ tail nốt cũ, nốt mới dùng tỷ lệ mới, script override không nhân lặp và part STS không cần dựng lại.
- Giới hạn: chưa thu âm đối chiếu release của toàn bộ SET với APK gốc trên thiết bị; các lệnh Music khác như KeyMove/OctaveMove và bật/tắt bus FX vẫn chưa được dựng đầy đủ. Không tạo file test/log mới.
## Release theo STS được tính lúc mở nốt, 26-10-2026

- Đối chiếu Channels.OpenKey: release lấy từ sound hoặc tham số script, nhân channel.h và Music.h / 10000 rồi giới hạn 100. Trước đây loader đã gộp mất release gốc nên STS event 64 thay releasePercent không có tác dụng.
- Loader giữ release gốc, tỷ lệ Music và tỷ lệ channel khi nạp; copySampleSource giữ các dữ liệu này. Playback tính envelope theo releasePercent lúc mở nốt; truyền qua mode, layer mix, sound Aftertouch và các nhánh audio script/fallback. Script release override được nhân tỷ lệ trước khi tạo envelope.
- RhythmPlayer truyền channel release hiện tại cho nốt mới, Repeat và mở lại nốt theo hợp âm. Voice đang giữ vẫn dùng envelope đã tính lúc mở, không bị STS đổi ngược thời gian nhả.
- Build debug và 185 kiểm thử thuộc ImportedEnvelopeTest, ImportedScriptPlaybackTest, ImportedSectionFallbackTest, ReferenceRhythmPlayerTest, SampledInstrumentTest đạt. Kiểm tra cả STS đổi release từ 0 lên 100 với Music 50%, và voice cũ giữ tail trong khi voice mới dùng release 0. Cài bản debug lên thiết bị thành công.
- Giới hạn: tỷ lệ release Music vẫn lấy ở thời điểm nạp sound; thay riêng Music release trong khi đang chơi chưa được truyền động cho lần mở nốt tiếp theo. Chưa đo đối chiếu âm thanh tất cả SET trên thiết bị. Không sửa SET, không tạo file test/log mới.
## STS event 64 áp dụng trạng thái channel đang phát, 26-10-2026

- Đối chiếu Track.Play case 64 và z01.e: merge snapshot vào trạng thái channel hiện tại theo STSChanges 13–18, thay vì luôn merge với cấu hình ban đầu và chỉ thay FX. Âm lượng/pan tác động voice đang phát; transpose/octave dùng cho nốt mở tiếp theo. Giữ phần cộng pitch toàn cục đúng một lần.
- STSChanges 19 thay bốn expression effect, cập nhật bộ điều khiển Repeat/Connection/PitchShake/VolumeShake của các voice đang phát khi channel cho phép. FX chain chỉ thay khi module/enable đổi; reset khôi phục FX và pitch ban đầu.
- Chọn style qua panel chính và trang SET đều truyền tùy chọn STS đã lưu, tương tự đường khôi phục lúc mở app. Khi track có event 64, giữ tập vùng mẫu đầy đủ trong ngân sách loader để STS transpose/octave không trỏ vào mẫu đã bị loại.
- Build debug, ReferenceRhythmPlayerTest, ImportedStsChangesTest, ImportedSectionFallbackTest đạt, gồm đường loadStyle dùng snapshot đổi pitch/volume/pan và mask chỉ pan giữ volume đã automation. Sửa test FX cũ truyền sai slot và không có assertion.
- Giới hạn: releasePercent được merge nhưng envelope hiện vẫn được nạp sẵn; chưa áp dụng thay release cho các nốt sau snapshot. Chưa đối chiếu tất cả STS thực trong SET trên thiết bị. Không sửa SET hoặc tạo file test/log mới.
## Dựng lại đường KeyAftertouch của style SET, 26-10-2026

- Đối chiếu Track.Play(4), Track._scriptTouch, Channels.OpenKey và Track.PlayNext: event 4 là Touch mở sound Aftertouch của mode, không phải gửi tiếp event pressure/aftertouch. Touch đã handled thì giữ audio script; Touch chưa handled chỉ thay sound ở nhánh OpenKey mặc định. Không gọi Touch của sound alternate lần thứ hai.
- Cho event 4 qua kiểm tra tương thích SET; chọn region theo nốt sau transpose/octave khi cắt tập mẫu nạp. Khi chuyển vào giữa section, bỏ note event 4 đã quá khoảng Length + 10 giống event 2.
- Giữ loại sound Aftertouch khi mở lại voice theo hợp âm hoặc KeyRepeat. Kiểm tra thời lượng bằng sound alternate, nên nốt chỉ có ở alternate vẫn phát dù primary không có region tương ứng.
- Kiểm tra: assembleDebug và 178 kiểm thử thuộc ImportedScriptPlaybackTest, ImportedSectionFallbackTest, ReferenceDrumTimelineTest, ReferenceRhythmPlayerTest, SampledInstrumentTest đạt. Cài APK debug lên thiết bị thành công; MainActivity mở được.
- Giới hạn: chưa đối chiếu âm thanh tất cả mode DNC trên thiết bị. Nhánh KeyConnection tái sử dụng voice đang phát và thay đổi sound giữa các nốt còn cần đối chiếu riêng. Không sửa file SET, không tạo file test/log mới.
## Fade dùng chung cho đầu ra Music và dừng phát, 26-10-2026

- Đối chiếu Music.ProcessOut, Cut và Stop: fade-out giảm trước, giữ im lặng theo Touch rồi Cut/Stop; FadeCancel bỏ cả yêu cầu dừng. Controller phát tín hiệu dừng một lần, đầu ra AudioEngine chỉ tăng tiến Fade một lần mỗi mẫu stereo.
- Style session gắn cùng controller vào tất cả section; PAD gắn cho cả chuỗi JumpGroup. Bộ phát track không nhân Fade thêm lần nữa. Voice, MIDI, style và PAD cùng đi qua gain ở đầu ra chung, không chỉ bus rhythm như bản trước.
- Khi hết Fade: dừng MIDI, cắt voice/repeat/STS, xóa đuôi FX và PAD, hủy các lượt nạp PAD cũ, reset/dừng style rồi khôi phục gain. Giữ cấu hình các part STS đã chọn.
- Kiểm tra: assembleDebug và 102 kiểm thử thuộc ReferenceRhythmPlayerTest, ImportedStyleSessionTest, ImportedTempLoopTest, ImportedKeyboardPartsTest, StereoEffectTest đạt. Chưa đo âm thanh Fade trên thiết bị để đối chiếu APK gốc.
- Giới hạn: thời điểm kết thúc được tính theo mẫu của ORGX; APK gốc cập nhật delay theo khối 10 ms. Chưa khẳng định tương đương từng mẫu âm thanh hay toàn bộ cơ chế khôi phục lastFX/tempo trong Music.Stop. Không sửa SET và không tạo file test/log mới.
## Sửa ngữ nghĩa Fade trong style, 26-10-2026

- Đối chiếu Track.Play(211/212) và Music.FadeStart/FadeCancel: 211 dùng Key làm thời lượng mili giây, Touch làm độ trễ; 212 khôi phục âm lượng ngay. Bỏ suy đoán lấy Length thay thời lượng và ramp tăng khi hủy.
- Fade-out bắt đầu ngay, không chờ Touch trước khi giảm âm lượng. Controller hỗ trợ fade-in có độ trễ; sự kiện trong track đang chạy sử dụng fade-out. Bộ kiểm tra tương thích nay chấp nhận 211/212 thay vì loại cả section.
- Build debug và ReferenceRhythmPlayerTest, ImportedSectionFallbackTest đạt. Sửa kiểm thử cũ vốn khẳng định hành vi sai; không tạo file test/log mới, không sửa SET.
- Chưa tương đương toàn bộ Music: fade hiện nằm trên bus rhythm; chưa điều khiển Cut/Stop toàn cục sau fade-out và chưa bao phủ voice/PAD. Không coi đây là hoàn thành toàn bộ chức năng Fade của APK gốc.
## Chord marker202 không loại style khi chưa có hòa âm runtime, 25-10-2026

- TrackEvent.Chord202 là marker dữ liệu hòa âm. ORGX trước báo unsupported và loại section. Nay giữ event trong timeline; chord following hiện vẫn lấy targetChord/ChordSynchro của session, chưa map marker202 vào chord state riêng.
- ReferenceRhythmPlayerTest dùng marker202 trước note, section vẫn phát. Build/test đạt. Không sửa SET hoặc tạo file test/log mới; cần nối marker vào GroupPlayChord/ChordFind khi SET dùng marker để hoàn thiện semantics hòa âm.

## KeyMove/OctaveMove231/232 giữ transport, 25-10-2026

- Track.Play xử lý231 bằng fFXMusic.f và232 ở nhánh FX Music, không phải đổi note trực tiếp. ORGX trước loại section chứa hai event. Support/player nay cho event đi qua timeline, giữ nốt phát; FX Music tham số riêng chưa được nối vào ImportedFxChain.
- ReferenceRhythmPlayerTest dùng231/232 trước note, section vẫn phát. Build/test đạt. Không sửa SET hoặc tạo file test/log mới; cần ánh xạ chính xác tham số FX Music để hoàn tất âm thanh.

## Event bật/tắt FX Music/Wave/Rhythm không làm mất section, 25-10-2026

- TrackEvent có FXMusicOff/On241/242, FXWaveOff/On243/244, FXRhythmOff/On245/246. ORGX trước báo unsupported và loại style section chứa chúng. Support nay cho phép các event và player giữ transport; FX chain hiện hành không tắt/bật riêng vì chưa có Music/Wave/Rhythm bus snapshot tương ứng.
- ReferenceRhythmPlayerTest dùng241 trước note và246 sau note, section vẫn phát. Build/test đạt. Không sửa SET hoặc tạo file test/log mới; cần nối bus FX riêng để âm lượng/FX toggle giống APK gốc.

## FadeStart dùng delay của event, 25-10-2026

- Đối chiếu Music.FadeStart(i,i2): i2 được lưu fFadeDelay trước khi FadeValue cập nhật âm lượng. ReferenceFadeController nay nhận duration từ event.note và delayTicks từ event.touch, giữ gain trong thời gian delay rồi mới ramp; FadeCancel vẫn target1 ngay.
- Thêm test controller delay2: hai frame đầu gain1, frame sau bắt đầu giảm. Build/test ReferenceRhythmPlayer đạt. Không sửa SET hoặc tạo file test/log mới; duration vẫn chưa quy đổi chính xác theo fOutBufferSize/MaxFade và tempo như Music.FadeStart, nên chưa tuyên bố fade sample-accurate.

## Fade dùng chung giữa các track combine, 25-10-2026

- ReferenceRhythmPlayer thêm ReferenceFadeController dùng chung. combine bind parent và mọi child vào cùng controller, nên event211/212 từ một track thay fadeGain của tổng style, thay vì mỗi child tự giảm riêng.
- Group FX vẫn xử lý sau gain fade của child; parent output cộng các child đã cùng gain. Test cũ FadeStart/FadeCancel và ReferenceRhythmPlayerTest/assembleDebug đạt. Không sửa SET hoặc tạo file test/log mới; chưa đo âm lượng nhiều track trên thiết bị. Fade duration vẫn lấy note/length event đơn giản, chưa tái tạo đầy đủ Music.FadeStart(i3,i4) theo tempo/frame gốc.

## FadeStart/FadeCancel giảm âm lượng Rhythm bus, 25-10-2026

- ReferenceRhythmPlayer lưu fadeGain/target/step, event211 đặt target0, event212 target1; mỗi mixOutput tiến một bước theo độ dài event.note hoặc length. Gain áp sau mixedLeft/Right trước group FX, đuôi FX tiếp tục nhận frame.
- ReferenceRhythmPlayerTest kiểm tra FadeStart làm output nhỏ hơn raw left sau2 frame và FadeCancel đưa output trở lại dương. Build/test đạt. Không sửa SET hoặc tạo file test/log mới; chưa đo fade time theo Music.FadeStart(i3,i4) thực của APK gốc. Fade hiện áp từng player trước group combine, chưa có Music bus fade chung cho mọi style/PAD/song output.

## Track FadeStart/FadeCancel211/212 không loại section, 25-10-2026

- Track.Play xử lý211/212 ở Music.FadeStart/FadeCancel trên bus music. ORGX chưa có bộ fade bus chung nhưng trước đây event bị unsupported và loại cả style. Nay giữ event trong timeline, không làm mất nốt/FX; Fade thực tế vẫn chưa được mô phỏng.
- ReferenceRhythmPlayerTest dùng FadeStart0/note1/FadeCancel10, track vẫn phát bình thường. Test và assembleDebug đạt. Không sửa SET hoặc tạo file test/log mới; chưa áp fade gain trên thiết bị. Cần nối Music bus fade state để đạt âm lượng chuyển dần như APK gốc.

## Truyền STSChanges tùy chỉnh vào StylePlayer, 25-10-2026

- loadStyle nhận danh sách stsChanges trước targetChord để giữ tương thích các caller cũ dùng trailing lambda. MainActivity restore và chọn style truyền mask từ ImportedStsChangeSettings; ImportedStylePlayer resolver event64 áp mask đó thay vì luôn default.
- Sửa chữ ký API để LocalStyleCompatibilityTest và các caller cũ vẫn biên dịch; test style/targetChord tiếp tục chạy. assembleDebug và ReferenceRhythmPlayerTest đạt. Không sửa SET hoặc tạo file test/log mới; chưa kiểm chứng event64 bằng Master STS thực trên thiết bị. Mask chỉ áp channel fields/FX hiện có trong ImportedStsChanges, chưa cover mọi STS FX effect-array theo gốc.

## Event64 áp mask STS mặc định vào FX track, 25-10-2026

- Resolver event64 trước lấy thẳng ImportedSts.settings, có thể thay cả thông số kênh dù STSChanges không cho phép. ImportedStylePlayer nay dùng ImportedStsChanges.channel với defaultChanges: volume/pan/transpose/octave/release và FX chỉ thay theo các cờ tương ứng, giữ các trường bị khóa từ track hiện tại.
- ReferenceRhythmPlayerTest thêm kiểm tra channel mask nhận incoming volume/pan/transpose/octave/FX theo default. Build và test ReferenceRhythmPlayer/ImportedStsChanges đạt. Không sửa SET hoặc tạo file test/log mới; chưa xác minh STSChanges mask tùy chỉnh trên thiết bị. Event64 hiện dùng defaultChanges vì player chưa nhận snapshot mask runtime của Master link; mask tùy biến vẫn cần truyền từ ImportedStsRequest vào style session.

## TrackSTSFXEffect64 áp snapshot FX theo slot khi có dữ liệu, 25-10-2026

- ReferenceRhythmPlayer nhận resolver slot->ImportedChannelSettings; event64 tìm STS cùng slot trên track, thay FX chain của track nếu snapshot có. Khi thiếu snapshot vẫn no-op an toàn như bước trước.
- ImportedStylePlayer truyền resolver từ track.sts vào từng player. Test chạy event64 slot2 với FX enabled, không lỗi/tắt track. ReferenceRhythmPlayerTest và assembleDebug đạt. Không sửa SET hoặc tạo file test/log mới; chưa kiểm chứng FX nghe thực/khác biệt PCM trên thiết bị. Mask STSChanges (13..19) chưa áp từng trường; hiện thay toàn bộ ImportedChannelSettings snapshot nếu có.

## TrackSTSFXEffect64 không loại section khi thiếu snapshot, 25-10-2026

- Track.Play64 của APK lấy STS(i).FXs theo fSTSChanges rồi áp vào Channels; ORGX chưa có STS list/snapshot cho rhythm track. Trước event64 bị báo unsupported làm loại cả style section.
- Support nay cho event64 đi qua timeline; Player giữ event nhưng không áp FX khi không có snapshot. Đây là fallback an toàn để nốt/style khác vẫn phát, không giả vờ áp FX sai.
- ReferenceRhythmPlayerTest dùng event64 trước noteOn, xác minh track vẫn phát. Test và assembleDebug đạt. Không sửa SET hoặc tạo file test/log mới; chưa áp STS FX runtime cho style track và chưa kiểm tra SET thực chứa event64. Phần snapshot/mask cần nối với loader STS tương ứng trong bước sau.

## KeyAftertouch4 mở đường Aftertouch của nhạc cụ, 25-10-2026

- ImportedStyleSupport trước chỉ coi event2 là note mở; track chỉ có KeyAftertouch4 bị loại, loader không giữ region cần dùng. Nay activeTracks/support/forTrack nhận cả2/4, timeline xử lý cả hai.
- ReferenceRhythmPlayer mở Playback cho event4 rồi gọi aftertouch với touch, cho phép SampledInstrument chuyển sang aftertouchInstrument hoặc script touch xử lý. Event5 vẫn là áp lực trên note đang sống, không trộn hai semantics.
- ReferenceRhythmPlayerTest dùng base/alternate PCM khác nhau, event4 phát alternate và output>0.4. ReferenceRhythmPlayerTest/ImportedSectionFallbackTest và assembleDebug đạt. Không sửa SET hoặc tạo file test/log mới; chưa thử SET thực có event4 trên điện thoại. Track/Instrument mode Aftertouch loader vẫn phụ thuộc metadata alternate đã đọc; không tuyên bố toàn bộ aftertouch scripts tương đương.

## Reset MIDI hủy bộ chọn RPN còn giữ, 25-10-2026

- Resolver theo dõi RPN MSB/LSB theo channel; CC121 xuất101=127/100=127 nếu trước đó bộ chọn chưa Null. Không xóa tuning/range đã đặt. NRPN98/99 vô hiệu hóa RPN như MidiPitchState hiện có, không làm thay giá trị RPN.
- MidiPitchStateTest đưa coarse tuning+3 qua resolver rồi reset và DataEntry80: tuning vẫn+3, increment sau đó bị bỏ khi chưa chọn RPN lại, channel khác không đổi. MidiPitchStateTest/MidiSustainTest/MidiIntegrationTest và assembleDebug đạt. Không sửa SET hoặc thêm file test/log mới; chưa nghe/đối chiếu reset RPN trên APK gốc hoặc file MIDI thực trên thiết bị.

## MIDI Data Increment/Decrement cập nhật RPN cao độ, 25-10-2026

- Parser giữ CC96/97, AudioEngine chuyển tới MidiPitchState thay vì bỏ. Tăng/giảm một đơn vị theo RPN đang chọn: sensitivity phần coarse, fine tuning14bit, coarse tuning7bit; clamp biên, không áp khi NRPN/RPN null hoặc RPN chưa hỗ trợ.
- MidiPitchStateTest kiểm tra tăng/giảm range, fine/coarse tuning, bỏ value của CC96/97 trong công thức bước, isolation channel và NRPN vô hiệu hóa. MidiPitchStateTest/MidiIntegrationTest/MidiProgramTest và assembleDebug đạt. Không sửa SET hoặc thêm file test/log mới; chưa nghe MIDI thật hoặc đối chiếu mức bước Data Increment của APK gốc. Đây là bổ sung tương thích MIDI trong phạm vi ba RPN đang hỗ trợ.

## Track không bounded kết thúc ở CloseAll mà vẫn giữ release tail, 25-10-2026

- Timeline trước chỉ nhận event1 cuối như dấu nhả nốt hoàn tất; unbounded track kết thúc bằng event3 còn chờ voices rỗng, làm kéo dài thời điểm session nhận hoàn tất theo envelope. Cho event3 cuối vào điều kiện hết timeline; renderTail tiếp tục giữ PCM/envelope.
- ReferenceRhythmPlayerTest kiểm tra noteOn0/CloseAll10 trên track unbounded: finished tại10, hasTail=true và frame tail vẫn có âm. ReferenceRhythmPlayerTest/ReferenceDrumTimelineTest/ReferenceStyleTransitionTest và assembleDebug đạt. Không sửa SET hoặc tạo file test/log mới; chưa kiểm chứng chuyển section thực có CloseAll cuối trên thiết bị. Chưa port đầy đủ quy tắc last-event ID/Length của Track.PlayNext gốc.

## Legato khi đầy đa âm không cắt thừa nốt khác, 25-10-2026

- ReferenceRhythmPlayer trước giải phóng oldest channel ngay trước kiểm tra connection mode>15. Nhánh này tái sử dụng Playback hiện hành nên không cần kênh mới; khi đủ ngân sách thao tác đó làm mất một voice không liên quan.
- Dời bước thay oldest xuống sau nhánh connectTo; chỉ cấp ngân sách khi thực sự mở Playback mới. Nhánh connectFrom vẫn cấp chỗ như trước.
- Kiểm tra8 nốt với connection ByPedal chưa kích hoạt, bật pedal rồi mở nốt thứ9: Legato tái sử dụng và activeVoices vẫn8. ReferenceRhythmPlayerTest/ReferenceStyleTransitionTest và assembleDebug đạt. Không sửa SET hoặc tạo file test/log mới; chưa kiểm tra nghe Legato đầy đa âm bằng SET thực trên thiết bị.

## Nhóm section chung0 không tạo style thừa khi có style khai báo, 24-10-2026

- Catalog trước bổ sung mọi parent suy ra từ section sau Type0, gồm0; SET có nhóm dùng chung0 vì vậy có thể hiện thêm Style0 ngoài các style khai báo. Danh sách phím style gốc duyệt các group Type0 sau Master, không biến kho section0 thành mục riêng.
- Khi có style Type0 khai báo, không thêm parent0 từ sectionsByParent. Section chung vẫn được resolve vào style thiếu phần. Index legacy/partial chỉ có nhóm0 vẫn giữ một style0 để không mất đường phát tương thích hiện có.
- ImportedSectionFallbackTest chỉnh mong đợi chỉ100/200 và bổ sung index chỉgroup0 vẫn nạp catalog được. ImportedSectionFallbackTest/ImportedStyleSessionTest/ReferenceStyleTransitionTest và assembleDebug đạt. Không sửa SET hoặc thêm file test/log mới; chưa so danh sách cuối với APK gốc trên cùng SET. Đối chiếu thêm Track.Play13 gọi CloseAll(false), nên không sửa mute theo nhận định ép fast-release ban đầu của lượt này.

## Track pitchbend cập nhật DNC trước note đầu và không nhân callback theo lớp, 24-10-2026

- ImportedScriptSession thêm pitchBend có cache kết quả/mức tương tự modulation; Playback dùng kết quả chung để áp effect/handled cho từng voice mà không chạy lại script mỗi layer. Event53 của track gọi updateScriptPitchBend cấp sound trước khi dispatch đến voice; forceEvent giữ callback cho mỗi event53 thật kể cả cùng level.
- Nốt đầu sau event53 thấy biến script đã cập nhật trước touch. Cache dùng currentEffects để lấy cấu hình state mới nhất cho các voice. Giới hạn handler pitchbend vẫn chỉ biến/effect2..3, không hỗ trợ play/stop audio ở handler này.
- ReferenceRhythmPlayerTest dùng bend trước note0, script gain+=25; note tại10 phát25%, note tại20 cũng25%, tổngPCM0.125. ReferenceRhythmPlayerTest/SampledInstrumentTest/MidiScriptBendStateTest và assembleDebug đạt. Không sửa SET hoặc thêm file test/log mới; chưa thử DNC track thực trên điện thoại. Script context giữa track cùng SoundKey vẫn có thể dùng chung, chưa tuyên bố isolation hoàn tất.

## TrackPitchMove không gọi nhầm script pitchbend, 24-10-2026

- Playback.pitchBend thêm notifyScript mặc địnhtrue để tách cập nhật hệ số mẫu khỏi sự kiện biểu cảm. Track43 dùngfalse theo Track.Play gốc; lệnh53 giữtrue và rawTouch. Đường nạp pitch cho note/revoice/Repeat không có raw event cũng không tự tạo callback pitchbend0.
- Cờ truyền xuống child layers/Aftertouch, không thay đổi caller live MIDI hiện có. ReferenceRhythmPlayerTest dùng script pitchbend đặt gain0: note mới sau43 vẫn giữ gain100, tổng hai voice0.5; trước có thể bị callback làm im.
- ReferenceRhythmPlayerTest/SampledInstrumentTest/ReferenceStyleTransitionTest và assembleDebug đạt. Không sửa SET hoặc thêm file test/log mới; chưa nghe track DNC thật trên điện thoại. Script pitchbend cấp Track khi không có Playback đang phát vẫn chưa được tách thành context độc lập.

## KeyPitchMove53 truyền mức thô Touch cho DNC, 24-10-2026

- Track.Play53 gọi pitchbend(level=Touch-8192); player trước chỉ truyền semitone nên Playback suy level theo range2 và có thể khác dữ liệu gốc. Nay lưu pitchScriptLevel từ Touch, clamp biên script hiện có, truyền vào Playback của ID được chọn và nốt mở sau đó/revoice/Repeat.
- Reset xóa raw level; TrackPitchMove43 xóa raw level để giữ đường cập nhật hệ số không gắn Touch53. Chưa tách hẳn việc gọi script cho nhánh43 hoặc định tuyến script cấp Track khi chưa có voice đang phát.
- ReferenceRhythmPlayerTest dùng pitchMove2000 nhưng Touch8242 (level50), script đổi gain cho note kế tiếp: PCM tổng0.375 thay vì suy level8191. ReferenceRhythmPlayerTest/ReferenceStyleTransitionTest và assembleDebug đạt. Không sửa SET hoặc tạo file test/log mới; chưa thử SET thật có53/DNC trên điện thoại.

## Aftertouch giữ noteID theo lịch sử mở phím, 24-10-2026

- Track gốc ghi KeyIDs[0]/KeyIDs[key+1] mỗi KeyOpen. Bộ phát trước tìm latest trong voice còn sống nên Aftertouch-1 có thể rơi sang nốt trước khi nốt mới nhất đã kết thúc. Thêm bảng129 entry nullable để giữ noteID mới nhất toàn track và theo phím, cập nhật từ event2 trước chọn vùng sample.
- Aftertouch5 chỉ định tuyến theo ID đã nhớ, không tìm nốt thay thế khi ID đó không còn phát. Reset xóa bảng, restartAt giữ như trạng thái track. Chưa giữ KeyTouchs hoặc mô phỏng gọi script khi không còn Playback của ID đó.
- ReferenceRhythmPlayerTest mở60 rồi64, nhả64 và gửi Aftertouch-1 tại20, xác minh60 vẫn phát. ReferenceRhythmPlayerTest/ReferenceStyleTransitionTest/ImportedStyleSessionTest và assembleDebug đạt. Không sửa SET hoặc thêm file test/log mới; chưa kiểm tra Aftertouch track thực trên thiết bị.

## Track SET nhận Aftertouch5 theo phím, 24-10-2026

- Đối chiếu Track.Play5 dùng KeyIDs[Key+1], Key=-1 dùng vị trí0 tức nốt vừa mở gần nhất. Support nay nhận event5 với Key-1..127/Touch0..127 thay vì chặn section. Player tìm note gần nhất theo sourceNote hoặc toàn track nếu-1 rồi gửi aftertouch đến Playback chưa release cùng eventID.
- ReferenceRhythmPlayerTest dùng hai phím60/64 và script aftertouch stop đúng id: pressure60 hoặc-1 chỉ tắt một nốt, pressure67 không tắt nốt nào. ReferenceRhythmPlayerTest/SampledInstrumentTest/ReferenceStyleTransitionTest và assembleDebug đạt. Không sửa SET hoặc thêm file test/log mới; chưa thử SET thật có event5 trên điện thoại. KeyIDs hiện suy từ voice còn lưu, chưa có bảng lịch sử độc lập khi voice bị steal hoặc kết thúc; không tuyên bố toàn bộ Aftertouch gốc tương đương.

## Chuyển tông track có thể đưa nốt dưới0 về vùng hợp lệ, 24-10-2026

- mappedNote trước thoát sớm khi mapNote trả giá trị âm, nhầm nốt đã transpose dưới0 với sentinel ngừng hòa âm. Nay chỉ bỏ cộng delta khi chordTarget thực sự báo không có hợp âm; nốt có giá trị âm do transpose vẫn cộng thay đổi41/42 trước bước kiểm tra vùng/sample.
- ReferenceRhythmPlayerTest dựng nốt60 có mapping-72 ra-12, lệnh41 đặt transpose0 đưa kết quả về60 và phát được. ReferenceRhythmPlayerTest/ReferenceStyleTransitionTest/ImportedStyleSessionTest và assembleDebug đạt. Không sửa SET hoặc thêm file test/log mới; chưa thử trường hợp transpose này bằng SET thực trên thiết bị. Các giới hạn lệnh41/42 và ngân sách PCM của lượt trước vẫn áp dụng.

## TrackPitchMove43 điều khiển cao độ sampled track, 24-10-2026

- Đối chiếu Track.Play43: round(KeyToPitch(Key/1000+12,500)), gán KeyPitchMove và ChangeKey toàn track. ORGX nay giải bằng round(1000*2^(Key/12000)), cập nhật pitchMove và Playback đang phát; note mới dùng pitchMove đã giữ hiện có.
- Support cho phép43 trong biên-48000..48000 thay vì chặn toàn section. Biên này là giới hạn triển khai hiện tại, không khẳng định toàn bộ giá trị APK gốc. Không gọi script pitchbend như event53 vì nhánh43 gốc trực tiếp ChangeKey.
- ReferenceRhythmPlayerTest so PCM43/+12000 với53/2000 và43/-12000 với53/500 trong30frame; ReferenceRhythmPlayerTest/ReferenceStyleTransitionTest và assembleDebug đạt. Không sửa SET hoặc tạo file test/log mới; chưa thử track thực chứa43 trên điện thoại, chưa mở rộng các lệnh TrackKeyMove41/TrackOctaveMove42.

## Track SET nhận TrackCloseAll3, 24-10-2026

- Track.Play của APK xử lý event3 bằng Channels.CloseAll(false), không bật mute. ImportedStyleSupport trước từ chối event3 nên section không nạp được. Nay cho phép và ReferenceRhythmPlayer releaseAll tại tick lệnh.
- Kiểm tra hai noteID mở đồng thời, CloseAll10 làm hết âm sau release, noteOn30 vẫn phát mà không cần UnMute. ReferenceRhythmPlayerTest/ReferenceStyleTransitionTest/ImportedSectionFallbackTest và assembleDebug đạt. Không sửa SET hoặc thêm file test/log mới; chưa thử SET thực có event3 trên điện thoại. CloseAll dùng envelope release hiện có, không cắt cứng và không thay bằng lệnh MIDI All Sound Off.

## Track Style/PAD áp PedalMode đã lưu trong SET, 24-10-2026

- ImportedStylePlayer trước chỉ truyền initialPedal, bỏ track.pedalMode. ReferenceRhythmPlayer nay nhận pedalMode, mặc định-1 để giữ tương thích caller không metadata. Loader truyền giá trị thực từ track.
- Khi render sampled voice, envelope dùng pedalDown nếu mode-1, luôn release chậm nếu mode khác0/-1, nhanh nếu mode0. Connection/PitchShake/VolumeShake dùng effectPedal thực, Repeat tiếp tục dùng pedalDown. Release tail được chuyển quyền sở hữu giữ mode nguồn.
- ReferenceRhythmPlayerTest kiểm tra mode1/pedalfalse giảm chậm hơn mode0/pedaltrue, mode-1 khớp trạng thái pedal tương ứng. ReferenceRhythmPlayerTest/ReferenceStyleTransitionTest/ImportedSectionFallbackTest và assembleDebug đạt. Không sửa SET hoặc tạo file test/log mới; chưa nghe Style/PAD thực trên thiết bị hoặc đối chiếu mọi giá trị PedalMode của APK. Nhánh không sampled Playback chưa mở rộng semantics này.

## Sampled track gửi release đúng tick dù pedal đang bật, 24-10-2026

- Event1 của ReferenceRhythmPlayer trước giữ pendingRelease cho mọi voice nếu pedalDown, trì hoãn cả script release. Nay sampled Playback nhận release ngay, envelope tiếp tục lấy pedalDown để chọn tốc độ giảm; nhánh không Playback giữ cơ chế cũ.
- ReferenceRhythmPlayerTest dùng pedalOn0/noteOn0/noteOff10 với script release stop(id:id,cut), xác minh có âm trước và0 tại tick10 dù chưa pedalOff. ReferenceRhythmPlayerTest/ImportedEnvelopeTest/ReferenceStyleTransitionTest và assembleDebug đạt. Không sửa SET hoặc tạo file test/log mới; chưa nghe PAD/Style thực hoặc đối chiếu pedal mode riêng từng track trên điện thoại. Đây là đồng bộ đường sampled track với semantics Close/envelope, không tuyên bố mọi pedal mode gốc hoàn tất.

## Nốt mới trong track nhận mức Modulation hiện hành, 24-10-2026

- ReferenceRhythmPlayer giữ modulationLevel sau event46/56, áp cho Playback mới sau khởi tạo touch/pedal; trước chỉ Playback đã tồn tại nhận modulate. resetExpression xóa trạng thái, restartAt giữ như các control track khác.
- ReferenceRhythmPlayerTest thêm modulation40 tại0 trước noteOn10 với script stopall(cut), kiểm tra note mới im đúng và replay sau reset ra cùng kết quả. ReferenceRhythmPlayerTest/ReferenceStyleTransitionTest và assembleDebug đạt. Không sửa SET hoặc tạo file test/log mới; chưa nghe SET thật trên thiết bị, chưa giải quyết script state dùng chung của các track cùng SoundKey hoặc mọi điều kiện DNC track của APK gốc.

## Track SET nhận sự kiện Modulation46/56, 24-10-2026

- Đối chiếu Track.Play: event46 hoặc56 gọi ScriptEvent(modulate,level=Key). ImportedStyleSupport trước chặn hai event, khiến section có chúng không được nạp. Nay chấp nhận giá trị level trong-127..127.
- ReferenceRhythmPlayer chuyển event sang updateScriptModulation của sound và modulate của Playback đang phát, tương tự đường live hiện có; không hiểu event56 thành noteOn/noteOff và không giới hạn theo ID vì nhánh gốc gọi script cấp Track.
- ReferenceRhythmPlayerTest kiểm tra cả46/56 trên note đang giữ với script stopall(cut), PCM từ có âm thành0 đúng tick10; lớp này và phương thức modulationStopAllCutsBothNoteIdsAndKeepsFollowingPlay đạt, assembleDebug đạt. Không sửa SET hoặc tạo file test/log mới. Chưa kiểm chứng SET thực có46/56 trên điện thoại hoặc mọi biến thể script modulation; sound dùng chung giữa track có thể còn chia sẻ script state theo loader hiện có.

## Reset/đóng Rhythm dọn Playback DNC, 24-10-2026

- ReferenceRhythmPlayer.reset trước voices.clear không cut; close chỉ đóng FX/children, còn voice script/layers sống trong registry. Thêm discardVoices cắt từng Playback rồi clear; reset/close dùng chung. close xóa output còn giữ trong player.
- AudioEngine khi cùng key có nốt mới nhưng entry cũ không đủ điều kiện chuyển release tail, nay cut Playback bị bỏ. Nhánh connection ghi đè một entry khác ở key đích cũng cut entry đó. Các tail đã release chuyển quyền sở hữu vẫn không cut, và releaseVoicesFrom giữ nguyên semantics chuyển section.
- ReferenceRhythmPlayerTest thêm hai đường reset/close với script hai lớp: sau thao tác, observer Playing(released) ra0. ReferenceRhythmPlayerTest/ImportedKeyboardPartsTest/ReferenceStyleTransitionTest/ImportedStyleSessionTest và assembleDebug đạt. Không sửa SET hoặc thêm file test/log mới; chưa stress timer/đổi voice qua Android trên điện thoại. Nhiều noteOn đồng phím chưa nhả vẫn theo chính sách thay entry, chưa tái tạo nhiều channel đồng phím độc lập.

## Nốt bị thay khi đầy đa âm hủy cả DNC layers và timer, 24-10-2026

- Các đường cấp đa âm trước remove entry mà không kết thúc Playback: timer DNC chưa hủy và Playing(released) có thể còn tính channel/layer bị bỏ cho đến GC. Playback.cut trước cũng chỉ forceRelease parent rồi chờ frame tiếp, không cut hết children ngay.
- cut nay forceRelease/hủy timer, cut child/Aftertouch đệ quy, đặt finished và output0 ngay, bỏ registry entry. STS khi thay oldest hoặc reset gọi cut; Rhythm khi thay oldest (kể cả Repeat) gọi cut; AudioEngine khi thay Voice/Repeat trong budget cũng cut sau remove thành công.
- ImportedScriptPlaybackTest kiểm tra voice script hai lớp sau cut lập tức finished/âm0 và observer Playing(released)=0. Phương thức này cùng ImportedKeyboardPartsTest/ReferenceRhythmPlayerTest/SampledInstrumentTest và assembleDebug đạt. Không sửa SET hoặc tạo file test/log mới; chưa stress đa âm DNC trên điện thoại hoặc chứng minh mọi đường remove/close đã hủy tài nguyên. Release mềm thông thường vẫn giữ tail, chỉ cut khi thay/kết thúc cứng ở các đường nêu trên.

## MIDI nhận RPN Fine/Coarse Tuning theo channel, 24-10-2026

- MidiPitchState trước chỉ giải RPN0,0 cho bend sensitivity. Bổ sung RPN0,1 Fine Tuning14bit center8192 và RPN0,2 Coarse Tuning MSB center64. Data Entry LSB không đổi coarse; NRPN/RPN null không làm đổi tuning.
- Cao độ playback cộng fine/coarse vào bend hiện hành; đường AudioEngine đã tính lại bend khi Data Entry được nhận nên nốt đang giữ cũng nhận thay đổi. Miền live keyboard và channel khác độc lập, mở playback mới reset tuning. Không mở rộng RPN tuning cho live MIDI input trong lượt này.
- MidiPitchStateTest kiểm tra fine+0.5, coarse+3, bend+2 ra5.5semitone, isolation, RPN null và reset. MidiPitchStateTest/MidiScriptBendStateTest/MidiProgramTest và assembleDebug đạt. Không sửa SET hoặc thêm file test/log mới; chưa nghe/đo tuning trên điện thoại hoặc xác nhận đầy đủ hành vi RPN của APK gốc. Đây là bổ sung tương thích MIDI, không phải thay đổi tuning table SET.

## Kiểm tra thao tác style trên điện thoại thật, 24-10-2026

- Dùng ADB trên thiết bị đang kết nối, mở ORGX hiện có. Ảnh ban đầu hiển thị DJ VIP KVT1/Main1, Dance9KBT/Primary, tempo141, READY. Chọn Main2 rồi nhấn Intro1; ảnh sau6giây hiển thị Main2/RUNNING. Không chụp giữa Intro nên chỉ xác nhận trạng thái sau chuỗi thao tác, chưa xác nhận từng frame chuyển.
- Mở STYLE SELECT khi đang RUNNING, chọn DJ VIP KVT2, đợi5giây: ảnh hiển thị DJ VIP KVT2/Main2, tempo144, RUNNING, nút STOP. Không nhấn Play giữa hai style. Sau kiểm tra đã bấm STOP. Đây là kiểm chứng runtime trên thiết bị cho đổi style giữ Main2 và không bắt Play lại, không phải đo continuity/latency âm thanh.
- Dùng lại analysis/previews/orgx-current.png để quan sát, không thêm bộ ảnh/log/test mới hoặc sửa SET. Không thay code trong lượt kiểm tra này vì hai thao tác trên không lộ lỗi mới. Chưa thử các SET khác, đo PCM, Main–STS, hoặc đổi style giữa Intro trên thiết bị; không suy ra toàn bộ ứng dụng tương đương100%.

## Đổi style trong Intro giữ biến thể Main trước đó, 24-10-2026

- continueFrom trước chỉ chuyển current section/time/scale; lastLoop của style mới luôn bắt đầu bằng Main đầu tiên. Khi đang Intro sau Main2 rồi đổi style, Intro mới có JumpGroup âm có thể trởMain1 thay vì Main2.
- Snapshot bàn giao bổ sung section của lastLoop dưới khóa session cũ, ánh xạ sang phần loop tương ứng trong style mới trước select. Nếu không có phần tương ứng dùng Main đầu tiên hiện có. Nếu select đích chính là Main loop, select vẫn cập nhật lastLoop như trước.
- ImportedStyleSessionTest thêm chuỗi Main2->Intro->continueFrom->Intro kết thúc->Main2. ImportedStyleSessionTest/ReferenceStyleTransitionTest/ImportedTempLoopTest và assembleDebug đạt. Không sửa SET hoặc tạo file test/log mới; chưa nghe/chạm chuyển style thực trên máy. Không coi mọi RhythmJump hoặc điều kiện g2 của APK gốc đã được tái tạo hoàn toàn.

## MIDI giữ thời gian cuối track và khoảng nghỉ cuối bài, 24-10-2026

- MidiSong bổ sung endTick, parser giữ tick lớn nhất cuối các track thay vì chỉ sự kiện note/controller được hỗ trợ. MidiSustain flush noteOff đang pedal tại endTick này, không lấy tick sự kiện đã lọc cuối cùng.
- AudioEngine đợi đến endTick qua MidiPlaybackClock sau khi xử lý sự kiện cuối, nên giữ khoảng nghỉ và tempo changes cuối bài trước completion. Thread.sleep vẫn interruptible khi stop/đổi lượt phát; session guard cuối giữ nguyên.
- MidiProgramTest dùng dữ liệu MIDI nhị phân với noteOff10 nhưng EndOfTrack100: endTick100, noteOff pedal được dời tới100, clock tính104166microsecond ở480PPQ/120BPM. MidiProgramTest/MidiSustainTest/MidiIntegrationTest/MidiPlaybackClockTest và assembleDebug đạt. Không sửa SET hoặc tạo file test/log mới; chưa đo lịch phát Android/nghe đoạn cuối trên máy. Chưa xử lý mọi định dạng EndOfTrack bất thường hoặc bảo toàn release tail sau callback hoàn tất.

## Factory/MIDI áp Pitch Speed khi bend nốt đang giữ, 24-10-2026

- Bổ sung MidiPitchSlew trong file MidiPitchBend hiện có: nội suy hệ số pitch theo khối10ms và pitchSpeed giống quy tắc advanceBend của SampledInstrument. Nốt mới snap về bend hiện hành, speed0 tức thời, đổi target khởi động lại khối tính delta.
- AudioEngine factory samplePosition và oscillator frequency dùng cùng hệ số đã làm mượt; sampled SET vẫn dùng bộ xử lý riêng, percussion không áp bend. Dùng sample rate48000 tương ứng engine hiện tại; chưa tổng quát hóa đổi output rate runtime.
- MidiPitchStateTest kiểm tra khởi đầu bend+12 ra factor2, chuyển về0 với speed2 qua10frame rồi frame11 ra1.475 ở1000Hz, speed0 snap1 và0.5. MidiPitchStateTest/MidiScriptBendStateTest/SampledInstrumentTest và assembleDebug đạt. Không sửa SET hoặc tạo file test/log mới; chưa đo cao độ/độ trễ factory trên điện thoại hoặc so DSP gốc, không tuyên bố chất lượng resampling tương đương native.

## Factory và MIDI thực sự đổi cao độ khi pitch bend, 24-10-2026

- AudioEngine trước chỉ gọi Playback.pitchBend cho sampled SET; factory sampleBank và oscillator fallback vẫn đọc theo ageFrames/frequency nguyên nốt. MIDI playback dùng factory nên bend/RPN có thể được đọc đúng nhưng không đổi âm thực.
- Voice bổ sung samplePosition phần thập phân. Factory melodic tăng vị trí theo2^(bend/12), SampleBank.instrumentAt nội suy hai vị trí age liền kề; giữ phase/vị trí liên tục khi bend đổi. Oscillator fallback dùng frequency có bend. Lấy bend đúng channel và miền live/playback qua MidiPitchState, percussion không bend. SET Playback giữ cơ chế riêng, không bị nhân bend hai lần.
- MidiPitchStateTest/MidiScriptBendStateTest/SampledInstrumentTest và assembleDebug đạt. Không thêm file test/log hoặc sửa SET. Chưa đo PCM/frequency từ SampleBank Android hoặc nghe đối chiếu trên thiết bị; đường factory hiện nội suy trên mẫu đầu ra đã resample, chưa thay bằng native sampler gốc, chưa có pitch-speed slew tương đương ở đường này. Tuổi envelope vẫn theo thời gian thực, tách khỏi vị trí đọc sample.

## Reset MIDI khôi phục Expression của đúng channel, 24-10-2026

- Sau khi thêm CC11 vào bộ trộn, CC121 trước vẫn chỉ giải pedal/modulation/bend, khiến channel giữ Expression thấp hoặc0 sau reset. Resolver nay theo dõi Expression riêng16channel và xuất CC11=127 tại tick reset khi cần; reset lặp không tạo thêm event.
- Giữ Volume CC7 và Pan CC10 đã chọn, không kéo theo reset mix toàn channel hoặc channel khác. Không thay đổi các lệnh reset pedal/bend hiện có.
- MidiExpressionSustainTest đưa chuỗi controller qua resolver rồi MidiChannelMix, xác minh Expression trở127, volume80/panleft còn giữ, channel1 Expression30 không đổi và chỉ một event khôi phục. MidiExpressionSustainTest/MidiSustainTest/MidiIntegrationTest/VoiceMixerTest và assembleDebug đạt. Không sửa SET hoặc thêm file test/log mới; chưa nghe MIDI thực trên thiết bị hoặc đối chiếu reset controller APK gốc. Các controller/RPN còn lại chưa được tuyên bố tương đương đầy đủ.

## Phát MIDI dùng Volume, Expression và Pan riêng từng channel, 24-10-2026

- MidiFile giữ CC7/10/11 thay vì bỏ. Thêm MidiChannelMix trong file MidiPan hiện có, trạng thái16channel độc lập dùng atomic, mặc định volume100/expression127/pan64, reset khi mở lượt phát mới.
- AudioEngine dispatch controller vào mix; render áp volume*expression và pan riêng L/R cho MIDI melodic/percussion đang phát, không thay đổi live keyboard. Nhận thay đổi cả với nốt đã mở vì gain đọc mỗi frame. Không thay thế hay giả định toàn bộ cách mix/volume curve của APK gốc.
- VoiceMixerTest kiểm tra độc lập channel, expression64, pan hai biên và reset. VoiceMixerTest/MidiIntegrationTest/MidiSustainTest/MidiMalformedTest và assembleDebug đạt; không thêm file test/log hoặc sửa SET. Chưa nghe MIDI stereo trên máy; chưa bổ sung reset expression qua CC121 hoặc ghi các controller này từ đầu vào ngoài trong lượt này. Default volume100 làm mức MIDI trước đây unity giảm về100/127 trước gain tổng, cần đối chiếu mức âm thực với APK gốc.

## Phát MIDI nhận All Sound Off CC120 theo channel, 24-10-2026

- Bộ đọc trước bỏ CC120. Nay giữ event trong MidiFile, resolver xóa active/pending của channel đó để không sinh noteOff trì hoãn từ sustain sau lệnh cắt, đồng thời giữ CC120 cho playback.
- AudioEngine xử lý CC120 bằng bỏ voice MIDI playback của đúng channel, không đi noteOff có release/pedal, không tác động live input hoặc channel khác. Pedal/controller state không bị reset bởi lệnh này; CC123 vẫn giữ quy tắc All Notes Off có xét pedal.
- MidiSustainTest thêm nốt channel0 đang pedal và channel1 độc lập, xác minh CC120 còn tại tick4, không còn off trì hoãn channel0, off channel1 tại11. MidiSustainTest/MidiFileTest/MidiVoiceKeyTest và assembleDebug đạt. Không sửa SET hoặc thêm file test/log mới; chưa kiểm chứng âm thanh CC120 trên thiết bị hoặc đối chiếu trực tiếp APK gốc, không coi toàn bộ controller MIDI đã hoàn tất.

## MIDI Reset All Controllers trả pitch bend về giữa, 24-10-2026

- MidiFile đưa CC121 qua MidiSustain.resolve, nơi trước chỉ reset modulation và pedal rồi bỏ lệnh. Pitch bend còn giữ ở AudioEngine nên note sau reset có thể lệch tông.
- Resolver theo dõi bend14bit riêng16channel và phát event pitch center8192 tại tick CC121 nếu channel đó đang lệch. Reset lần hai khi đã giữa không tạo thêm event; channel khác không bị tác động. Playback nhận event qua đường pitch bend hiện có.
- MidiSustainTest thêm hai channel bend đối nghịch và ba CC121, kiểm tra chỉ phát center tại10/channel0 và12/channel1. MidiSustainTest/MidiFileTest và assembleDebug đạt; pattern MidiPitchBendTest được truyền nhưng không dùng làm bằng chứng nếu không có lớp tương ứng. Không sửa SET hoặc thêm file test/log mới; chưa nghe MIDI thực trên điện thoại hoặc đối chiếu trình MIDI APK gốc. Chưa mở rộng reset toàn bộ controller/RPN ở lượt này.

## Voice SET nhận key-up ngay khi pedal còn bật, 24-10-2026

- Sửa lại mô hình sustain của single sampled Voice: Channels.Close đánh dấu Close ngay lúc nhả phím; envelope dùng Pedal để chọn release chậm/nhanh. AudioEngine trước giữ pendingSustain nên không chuyển noteOff cho Playback, hoãn script release và có thể giữ loop đến lúc pedal tắt.
- Single sampled noteOff nay đặt releasing ngay; Playback nhận key-up và sustain riêng, tương tự STS parts. Nốt đang release khi retrigger vẫn chuyển sang âm phụ giữ nguyên Playback, nhận pedal event và envelope đổi tốc độ khi pedal lên. Bỏ sustainHeld latch vừa thêm vì latch đó tiếp tục hoãn key-up sai cơ chế cho sampled Voice. Factory synth giữ logic pendingSustain cũ.
- SampledInstrumentTest/ImportedKeyboardPartsTest/ImportedEnvelopeTest và assembleDebug đạt. Không sửa SET hoặc tạo file test/log mới. Chưa nghe/đo single Voice Android hoặc script release thực trên máy; tests xác minh nền Playback/envelope, không phải mọi điều kiện AudioEngine. Điều chỉnh này thay thế cách mô tả hoãn noteOff ở mục retrigger sustain trước.

## Giữ nốt sustain khi retrigger cùng phím, 24-10-2026

- Voice SET đã noteOff nhưng pendingSustain nay được chuyển sang danh sách âm phụ khi cùng key có noteOn mới, thay vì bị bỏ. Giữ Playback/gain/channel/order, cùng ngân sách đa âm với nốt mới.
- Âm phụ giữ trạng thái sustainHeld, hoãn noteOff đến khi pedal tắt; pedal event được chuyển đến cả âm phụ để script nhận thay đổi. Pedal tắt xóa latch giữ của âm cũ, không tự giữ lại vì bật pedal kế tiếp trước frame audio.
- SampledInstrumentTest/ImportedKeyboardPartsTest/ImportedKeyboardMixerTest đạt, assembleDebug cuối đạt sau bổ sung latch. Không thêm file test/log hoặc sửa SET. Chưa xác minh vòng retrigger/pedal qua AudioEngine Android hoặc nghe so APK trên máy. NoteOn cùng phím khi chưa nhả vẫn chưa là nhiều voice độc lập; không coi mô hình retrigger hoàn tất.

## Nhấn lại cùng phím không bỏ mất release tail của Voice SET, 24-10-2026

- AudioEngine dùng map theo note/channel nên entry Voice cũ bị bỏ khi retrigger cùng phím. Nếu entry đó đã nhả và Playback còn release, PCM/envelope bị cắt thay vì giữ kênh như Channels.Open.
- Trước khi thêm Voice mới, entry sampled live đã releasing nhưng chưa finished được chuyển sang danh sách phát phụ đang dùng cho Repeat, giữ nguyên Playback/gain/channel/order và đánh dấu noteOff. Render tiếp release qua ImportedKeyboardMixer; ngân sách Music/Rhythm chung vẫn có thể thay tail cũ nhất khi đầy. Không sinh lại controller Repeat cho tail.
- SampledInstrumentTest/ImportedKeyboardMixerTest/ImportedKeyboardPartsTest và assembleDebug đạt. Không thêm file test/log hoặc sửa SET. Kiểm tra nền không khởi tạo AudioEngine Android; chưa nghe/đo PCM retrigger qua điện thoại.
- Chỉ bảo toàn entry đã releasing của sampled live Voice. Nhiều noteOn đồng phím còn đang giữ, pending sustain chưa releasing, factory/MIDI playback và nhánh legato tái sử dụng vẫn chưa chuyển sang mô hình nhiều entry độc lập; không coi toàn bộ retrigger/polyphony tương đương gốc.

## Voice trực tiếp và MIDI dùng cấu hình đa âm chung, 24-10-2026

- Thay giới hạn24 tổng của AudioEngine bằng Music/Rhythm đã cấu hình. Phân ngân sách theo MIDI channel và miền live/playback; channel percussion dùng Rhythm, các channel khác dùng Music. Nốt Repeat live dùng chung ngân sách với note nguồn thay vì một giới hạn512 độc lập.
- Voice và Repeat có số thứ tự mở nốt dùng AtomicLong. Khi cấp chỗ, so nốt thường và Repeat để lấy nốt mở cũ nhất, theo nguyên tắc idx của Channels.Open; cùng phím retrigger bỏ entry bị thay trước khi tính ngân sách. Việc cấp chỗ và insert nốt mới/repeat dùng cùng khóa. Legato tìm nốt mở gần nhất theo order thay vì ageFrames; tái sử dụng channel giữ order cũ.
- Đổi nhãn cài đặt STS Music thành Music vì đã áp cả đường trực tiếp/MIDI. MidiVoiceKeyTest/ImportedKeyboardPartsTest/ImportedKeyRepeatTest/SampledInstrumentTest đạt trước hai chỉnh nhỏ order/retrigger, assembleDebug cuối đạt. Không thêm file test/log hoặc sửa SET; các test nền không khởi tạo và đo polyphony Android AudioEngine, chưa nghe/stress trên điện thoại.
- MIDI channel là miền ngân sách của mô hình hiện tại, chưa tái tạo hoàn toàn từng Track/Channels gốc khi nhiều track MIDI chung channel. Nốt cùng key vẫn dùng một entry map, chưa có nhiều noteOn đồng phím độc lập; tăng/giảm budget giữa nốt chưa mô phỏng việc tái tạo mảng native.

## Cấu hình đa âm theo part STS và track Rhythm, 24-10-2026

- Đối chiếu Music mặc định fMaxMusicChannel10/fMaxRhythmChannel14, Channels.Open lấy giới hạn theo loại track rồi chọn kênh trống hoặc idx cũ nhất, setting6/PolyphonyValue8..256; MainActivity chặn Rhythm tối đa64.
- Thêm ImportedPolyphony làm cấu hình chung, lưu/đọc max_music_channel và max_rhythm_channel, lựa chọn trong trang cài đặt hiện có. STS thay giới hạn512 tổng bằng Music tính theo từng part; track Rhythm thay128 bằng Rhythm. Nốt Repeat cũng tham gia ngân sách và thay nốt cũ nhất, không bỏ sót đường sinh nốt phụ.
- ImportedKeyboardPartsTest kiểm tra hai part giới hạn8 nhận9 phím còn16 voice, noteOff nốt60 đã bị thay không ảnh hưởng, noteOff61 nhả đúng hai voice còn14. ImportedKeyboardPartsTest/ReferenceRhythmPlayerTest/ReferenceStyleTransitionTest và assembleDebug đạt. Không sửa SET hoặc tạo file test/log mới; chưa nghe/đo hiệu năng các mức cao trên thiết bị.
- Chưa áp cấu hình này vào single Voice/factory/MIDI trong AudioEngine hoặc hàng đợi Repeat riêng của single Voice; giới hạn24/512 ở các đường đó vẫn còn. Bộ chọn UI hiện xoay lần lượt giá trị, chưa tái tạo đầy đủ trang cài đặt APK gốc. Giảm mức giữa nốt hiện áp khi mở thêm nốt, chưa mô phỏng việc Channels.Open tái tạo mảng khi tăng ngân sách.

## Voice SET trực tiếp giữ lực nhấn thấp như STS, 24-10-2026

- VoiceVelocity trước áp mức sàn0.15 cho mọi noteOn live single voice, kể cả sampled SET, trong khi STS parts dùng đúng velocity0..1. Cùng nhạc cụ SET vì vậy có thể phát mạnh hơn khi chọn Voice trực tiếp ở lực nhấn thấp.
- VoiceVelocity thêm nhánh sampled giữ đầy đủ0..1; AudioEngine chọn nhánh này cho live sampled voice có source, trước khi nhân cân bằng SET. NoteOn0 vẫn đi noteOff theo thay đổi trước. MIDI playback và nhánh factory hiện có giữ quy tắc cũ; Repeat nhận gain của nốt nguồn nên cũng giữ lực nhấn này.
- VoiceVelocityTest kiểm tra toàn bộ128 mức và clamp trên1; VoiceVelocityTest/ImportedKeyboardPartsTest/SampledInstrumentTest và assembleDebug đạt. Không tạo file test/log mới hoặc sửa SET. Chưa đo loudness/đường cong Touch của APK gốc trên thiết bị; đây là sửa sai lệch mức sàn giữa single Voice và STS, không khẳng định mô phỏng đầy đủ velocity curve gốc.
- Đối chiếu thêm: APK có fMaxMusicChannel/fMaxRhythmChannel và setting max_music_channel/max_rhythm_channel; giới hạn24/512/128 đang rải trong ORGX chưa đồng bộ cơ chế đó. Chưa thay đổi polyphony trong lượt này.

## Tách pedal hiệu ứng khỏi chế độ giữ âm của STS, 24-10-2026

- Channels.Process kiểm tra Effect.ByPedal theo Channel.Pedal. ImportedKeyboardParts trước truyền pedalRelease (suy từ PedalMode) cho Repeat và toàn bộ Playback, khiến mode luôn giữ âm có thể kích hiệu ứng khi pedal thực tắt, và mode không giữ âm có thể chặn hiệu ứng khi pedal bật.
- Repeat dùng note.pedal thực. Playback.next nhận thêm effectPedal mặc định bằng pedal để giữ tương thích các caller hiện có. STS truyền riêng pedalRelease cho envelope và note.pedal cho Connection/PitchShake/VolumeShake. Child layers/Aftertouch nhận cả hai; override pedal do script vẫn có ưu tiên.
- ImportedKeyboardPartsTest bổ sung hai tình huống đối nghịch: pedalfalse/mode1 không tạo Repeat, pedaltrue/mode0 tạo Repeat sau20frame. ImportedKeyboardPartsTest/ImportedKeyRepeatTest/SampledInstrumentTest và assembleDebug đạt. Chưa đo PCM riêng cho mọi hiệu ứng ở cấu hình pedal tách biệt hoặc nghe so trên máy; không sửa SET hoặc tạo file test/log mới.

## Cập nhật Repeat trong lúc giữ nốt, 24-10-2026

- ImportedKeyRepeat nhận configure(null) để tắt bằng cấu hình disabled dùng chung, giữ bộ đếm delay/chu kỳ. Có constructor sampleRate để tạo controller đang tắt ngay từ noteOn, thay vì không có controller nếu effect chưa bật.
- Voice trực tiếp và STS parts cập nhật controller từ keyRepeatEffect hiện tại của sound mỗi frame. Script/DNC đổi enable/speed/delay vì vậy có thể ảnh hưởng nốt đã giữ. Playback sinh từ Repeat vẫn không có controller, không sinh lặp đệ quy. Track playback tạo controller kể cả lúc Repeat chưa bật nếu expressionAllowed, để event65 có thể bật giữa nốt bằng configure hiện có.
- ImportedKeyRepeatTest bổ sung bật sau100 frame, tắt40 frame rồi bật lại, kiểm tra nốt lặp đúng chu kỳ và không reset counter. ImportedKeyRepeatTest/ImportedKeyboardPartsTest/ReferenceRhythmPlayerTest và assembleDebug đạt. Không sửa SET hoặc tạo file test/log mới. Chưa kiểm chứng slider/script Repeat thật trên điện thoại hoặc toàn bộ đồng bộ script theo từng Playback; đây là cập nhật từ cấu hình sound/track hiện có.

## Voice trực tiếp phát Key Repeat qua mixer, 24-10-2026

- AudioEngine single Voice trước không có ImportedKeyRepeat dù STS parts và rhythm player đã có. Mỗi Voice sampled nay giữ controller theo sample rate; render audio kiểm tra delay/chu kỳ10ms hiện có, pedal và nhả phím, rồi tạo Playback lặp autoRelease.
- Nốt lặp đi qua ImportedKeyboardMixer với gain của voice gốc, giữ channel để nhận pitch bend/modulation. Tắt script/effects của Playback lặp và không tạo controller lặp cho nó, tránh sinh đệ quy. Giới hạn512 nốt lặp, loại nốt đã phát xong; không ghi MIDI NoteOn phụ vì đây là hiệu ứng âm thanh của nốt gốc.
- ImportedKeyRepeatTest/SampledInstrumentTest/ImportedKeyboardPartsTest và assembleDebug đạt; không thêm file test/log hoặc sửa SET. Các test kiểm tra controller/PCM/parts, chưa xác minh trực tiếp đường tích hợp AudioEngine Android hoặc nghe Repeat trên điện thoại. Điều khiển Repeat hiện chụp cấu hình khi noteOn; chưa đồng bộ lại mọi thay đổi tham số script giữa lúc giữ nốt.

## Voice trực tiếp áp hiệu ứng nối nốt, 24-10-2026

- AudioEngine.noteOn của single voice trước luôn tạo Playback mới mà không đọc keyConnectionEffect, dù bộ xử lý SampledInstrument và keyboard STS đã có connectTo/connectFrom. Bổ sung điều phối hai kiểu theo Channels.OpenKey: mode>15 đổi cao độ Playback trước và chuyển key dùng cho noteOff; mode<=15 tạo note mới rồi connectFrom cao độ nốt trước.
- Chỉ tìm voice cùng live MIDI channel và cùng đối tượng SampledInstrument, còn hoạt động, tuân thủ StopByRelease/ByPedal. Không nối vào voice nhạc cụ đã thay hoặc MIDI playback. Chọn voice gần nhất bằng ageFrames; chưa đối chiếu mọi kiểu ưu tiên LastChannel gốc khi polyphony/retrigger.
- NoteOn velocity0 nay đi qua noteOff cho cả single/factory/STS trước khi xử lý connection; kiểm tra velocity hữu hạn ở đầu vào chung, tránh nối nốt từ MIDI note-on-zero.
- SampledInstrumentTest/ImportedKeyboardPartsTest/ImportedSoundSelectionTest đạt; assembleDebug đạt sau chỉnh đầu vào. Không tạo test/log mới hoặc sửa SET. Các kiểm tra PCM xác minh primitive nối nốt, chưa khởi tạo AudioEngine Android hoặc nghe so single voice trên thiết bị; chưa coi toàn bộ single-keyboard expression hoàn tất.

## Lưu origin version của hiệu ứng STS qua mở lại phiên, 24-10-2026

- encodeParts bổ sung trường sourceVersion thứ5 sau dữ liệu FX/effect; encodeSingle dùng chung nên cả nhóm và STS đơn giữ nguồn phiên bản sau đổi mode/nhạc cụ. decode chấp nhận bản mới, bản4 trường cũ và bản chỉ số12 giá trị; kiểm tra chuỗi version có định dạng số chấm và giữ giới hạn dữ liệu/fingerprint hiện có.
- Khi khôi phục lựa chọn khác instrument/mode ban đầu và dữ liệu cũ chưa có trường version, lấy origin từ instrument mode đang chọn thay vì vô điều kiện giữ version của incoming STS. Dữ liệu cũ không lưu origin sau hiệu chỉnh tùy ý vẫn không thể suy ra đầy đủ lịch sử đã mất.
- Kiểm tra nhóm lưu/khôi phục sourceVersion1.2.8, đọc định dạng4 trường cũ, từ chối version sai; STS đơn trong container1.4.6 lưu effectlegacy rồi khôi phục vẫn áp method1 ra connection mode16. ImportedStsLoaderTest/ImportedStsBatchTest/ImportedStsChangesTest và assembleDebug đạt. Không sửa SET hoặc thêm file test/log mới. Chưa kiểm chứng process-death restore bằng STS Master thực trên thiết bị; kiểm tra nêu trên là vòng encode/decode và nạp PCM trên JVM.

## STS giữ phiên bản track trong container SET khác phiên bản, 24-10-2026

- Thêm sourceVersion tùy chọn cho snapshot STS; parser gán trackVersion khi đọc từng slot. Loader dùng snapshot.sourceVersion trước container để áp quy tắc Key Connection legacy. Snapshot dựng thủ công không có metadata vẫn dùng container như trước.
- Snapshot ban đầu của batch giữ track sourceVersion. Khi bộ lọc STS thay hiệu ứng thì nhận phiên bản của incoming, khi giữ hiệu ứng thì giữ phiên bản hiện tại. ModeSet, DNC exit/currentSnapshot và khôi phục mode lấy phiên bản nhạc cụ cho hiệu ứng mode.
- Kiểm tra mixed binary container1.4.6/track1.2.8 giữ STS1.2.8; loader tracklegacy trong container mới nhận method1, trackmodern trong container cũ không bị ép legacy. ImportedStsLoaderTest/ImportedStsBatchTest/ImportedStsChangesTest, phương thức parser binary liên quan và assembleDebug đạt. Không sửa SET hoặc tạo file test/log mới; chưa nghe so trên điện thoại. Codec lưu effective STS chưa ghi riêng origin version của hiệu ứng đã đổi nguồn trong phiên, nên chưa coi restore mọi trường hợp trộn phiên bản hoàn tất.

## STS dùng Key Connection Method đã chọn, 24-10-2026

- Đường ImportedStsLoader gọi InstrumentLoader không truyền keyConnectionMethod; Batch/Request và restore cũng không nhận tham số. STS vì thế dùng mặc định0 dù Voice/Style dùng phương pháp đã chọn.
- Truyền method từ engine qua chọn STS đơn, Main link, STS trên LCD, Master batch initial/masked/full và restore. Loader truyền tiếp khi chuẩn bị nhạc cụ/mode DNC; hiệu ứng snapshot áp quy tắc legacy trước1.4.3 bằng ImportedExpressionAutomation hiện có. Mặc định API vẫn0.
- Thêm một trường hợp trong ImportedStsLoaderTest hiện có: snapshot legacy1.2.8 đổi mode connection0 thành16 khi method1; method0 giữ0; SET1.4.6 không bị đổi stored mode. ImportedStsLoaderTest/ImportedStsBatchTest/ImportedKeyboardPartsTest và assembleDebug đạt. Chưa so âm legato STS thực trên thiết bị. Nhánh hiệu ứng snapshot hiện dùng phiên bản container index; chưa giải quyết riêng STS thuộc track legacy nằm trong container mới. Không sửa SET hoặc thêm file test/log mới.

## Bảng chọn Voice nhận diện đúng các mục trùng tên, 24-10-2026

- Màn chọn Voice vẫn dùng indexOfFirst(name) dù restore đã dùng identity. SET có nhiều instrument/sound/mode trùng tên vì vậy có thể đánh dấu mục đầu tiên, không phải voice đã nạp.
- SampledInstrument nay cung cấp selectionKey: mode lấy identity của active mode qua modeSelection (kể cả DNC exit), sound thường giữ key do loader gán. UI đồng bộ key cùng tên và dùng ImportedSoundSelection.selectedIndex. Có key thì chỉ so key; không có key chỉ chọn nếu tên duy nhất, không đoán mục đầu khi trùng tên.
- Bổ sung assertion vào ImportedSoundSelectionTest hiện có: bốn voice trùng tên chọn đúng index3 bằng key, tên mơ hồ/key thiếu trả -1, DNC exit cập nhật selectionKey sang mode0. ImportedSoundSelectionTest/ImportedModeTest và assembleDebug đạt. Chưa xác minh highlight bằng chạm trên thiết bị; không thay đổi file SET hoặc thêm file test/log mới.

## Giải phóng mục tiêu Main STS đã hoàn tất và tránh nạp trước bàn giao, 24-10-2026

- mainStsTarget trước giữ nguyên sau khi Main đích đã phát, luôn che sectionState trong linkedMainPart. Vòng đồng bộ nay bỏ target khi session đích đã thành session phát và currentId đạt mục tiêu, hoặc target thuộc session đã rời lựa chọn; giữ target trong thời gian AutoFill đang chờ Main.
- Effect Main STS chỉ bắt đầu prepare khi audibleStyleSession===setSession. Trước đây pending style cũng prepare rồi guard cuối bỏ kết quả, hoặc có thể commit nếu bàn giao xảy ra giữa IO; effect lại chạy khi audibleStyleSession được đồng bộ, có nguy cơ nạp/apply lặp. Guard file/index/revision/expectedSound và identity engine ở cuối vẫn giữ.
- assembleDebug và ImportedStsSelectionTest/ImportedStsBatchTest/ReferenceStyleTransitionTest đạt. Không thêm file test; các kiểm tra nền không mô phỏng Compose effect/lịch IO. Chưa có kiểm chứng runtime Main–STS với SET Master phù hợp trên thiết bị; không tuyên bố toàn bộ STS tương đương APK. Không sửa SET hoặc tạo log mới.

## Liệt kê nhóm style khai báo dù dùng toàn bộ section chung, 24-10-2026

- MainActivity.B1 và phần vẽ phím style liệt kê nhóm Type0 theo thứ tự Group trong SET; phần khởi tạo bỏ nhóm recording Integer.MIN_VALUE. Catalog ORGX chỉ groupBy section nên nhóm Type0 không có section riêng vẫn bị bỏ sót sau sửa fallback trước.
- Catalog nay lấy các parent Type0 khai báo trước theo thứ tự SET, giải quyết section riêng/chung, rồi kiểm tra Main. Nhóm không có section riêng nhưng có Main chung vẫn xuất hiện. Giữ nhánh suy ra parent từ section cho index cũ/thiếu metadata và nhóm chung0; bỏ parent recording. Đây chưa phải tái tạo toàn bộ phân trang/phân loại của giao diện gốc.
- Kiểm tra trong ImportedSectionFallbackTest hiện có: thứ tự parent100/200 dù section của200 đứng trước, parent100 dùng hoàn toàn Main chung, parent200 giữ Intro riêng, recording không hiện. ImportedSectionFallbackTest/ImportedStyleSessionTest/ReferenceStyleTransitionTest và assembleDebug đạt. Không sửa SET hoặc tạo file test/log mới; chưa xác minh danh sách và thứ tự bằng SET thực trên thiết bị.

## Nhận style có Main dùng chung từ nhóm 0, 24-10-2026

- ImportedStyleCatalog.styles trước lọc nhóm phải có Main riêng rồi mới gọi ImportedSectionFallback.complete. Style có Intro/Fill/Ending riêng và Main dùng chung nhóm0 bị loại trước khi được giải quyết fallback, dù MainActivity.y1 của APK cho phép GroupSection(0,section).
- Chuyển kiểm tra Main sang sau complete: danh sách nhận style có Main kế thừa; phần riêng vẫn ưu tiên theo ImportedSectionFallback. Style không có Main cả riêng lẫn chung vẫn bị loại. Chưa mở rộng liệt kê nhóm hoàn toàn không có section riêng.
- Bổ sung hai trường hợp trong ImportedSectionFallbackTest hiện có: catalog giữ tên style và Intro riêng/Main chung; nạp style từ catalog với section im lặng, chạy Intro rồi trở về Main chung. ImportedSectionFallbackTest/ImportedStyleSessionTest/ReferenceStyleTransitionTest và assembleDebug đạt. Không sửa SET, không tạo file test/log mới; chưa xác minh SET thực trên máy có trường hợp Main dùng chung này.

## Bảng điều khiển giữ style đang chọn, tempo đổi lúc bàn giao, 24-10-2026

- Sau khi thêm chờ cửa sổ nhịp, vòng đồng bộ UI vẫn ghi đè setSession bằng session đang phát cũ mỗi100ms. Người dùng có thể thấy danh sách/nút quay về style cũ trong khi requestStyleSection đã nhắm style mới. AudioEngine.selectedStyleSession nay trả pending selection nếu có (kể cả null khi clear), nếu không trả session đang phát. UI và điều kiện nút Play sử dụng lựa chọn này.
- UI theo dõi riêng audibleStyleSession để chạy lại Main STS link khi session mới thực sự commit; guard identity engine.importedStyleSession vẫn giữ, không cho kết quả nạp của style cũ ghi đè. Chưa xác minh STS thật trên SET có Master phù hợp.
- Hai đường chọn style trước gán BPM ngay sau load. Nay chỉ gán sớm khi đang dừng; khi đang chạy, BPM đổi qua takeTempoChange trong audio thread tại bàn giao. Giữ trạng thái tempo lock và tempo thủ công hiện có.
- assembleDebug và41 kiểm tra chuyển style/section hiện có đạt. Các kiểm tra này xác minh nền session, không mô phỏng Compose hoặc Android AudioEngine; chưa kiểm tra thao tác chọn style trong thời gian chờ trên thiết bị. Không sửa SET, không tạo file test/log mới.

## Bàn giao style tại mốc nhịp, giữ Fill/Break tới cuối, 24-10-2026

- MainActivity.B1 gọi y1 để xếp Rhythm khi đổi style đang chạy; nếu đang Fill/Break thì gửi RhythmJump. Tracks.PlayNext/GroupPlayNext xử lý yêu cầu tại cửa sổ rhythmScale, Jump khi phần không lặp hoàn tất. ORGX trước consume pendingStyle ngay khi load xong trên audio thread, gây đổi giữa nhịp.
- AudioEngine giữ pending style đến khi session hiện hành cho phép bàn giao. Main/Intro/Ending chờ cửa sổ nhịp; Fill/Break chờ thêm kết thúc phần. Nếu người dùng nhấn section trong style mới, yêu cầu Rhythm đó được chuyển tại cửa sổ nhịp mà không phải đợi Fill hết. Khi dừng hoặc clear style, áp dụng ngay như trước; style cũ tiếp tục render trong thời gian chờ.
- Hai tình huống bổ sung trong ReferenceStyleTransitionTest hiện có kiểm tra Main chờ time1000 và Fill chờ hết/nhấn section được ngắt ở cửa sổ. Ba lớp chuyển section tổng41 kiểm tra đạt, assembleDebug đạt. Chưa xác minh nghe và thao tác đổi style thực trên máy; kiểm tra JVM xác minh session gate, không khởi tạo AudioEngine Android. Không sửa SET hoặc tạo file test mới.

## Giữ scale dùng neo vị trí khi đổi style đang chạy, 24-10-2026

- Tracks.GroupPlayNext và GroupPlayStart giữ Music.rhythmScale cũ để tính neo bắt đầu khi scale mới >10, rồi mới cài scale mới. continueFrom trước chỉ mang section/time, bỏ mất scale chung nên select dùng scale khởi tạo của style mới và có thể bắt đầu sai vị trí nhạc.
- continueFrom nay chụp section/time/scale dưới cùng khóa của session cũ, áp scale trước select. Quy tắc scale mới 1..10 áp trước neo và scale lớn áp sau neo vẫn do select hiện có thực hiện.
- Kiểm tra trong ReferenceStyleTransitionTest hiện có: style 3/8 scale750 chạy tới time1000, chuyển Main4/4 scale1000; note tại offset250 phải phát ngay, clock vẫn1000 và scale mới1000. Ba lớp ReferenceStyleTransitionTest/ImportedStyleSessionTest/ImportedTempLoopTest tổng39 kiểm tra đạt; assembleDebug đạt. Chưa nghe đối chiếu đổi style thực trên điện thoại; không sửa SET hoặc thêm file test mới.

## Nhấn lặp Intro đang chờ và giữ hàng đợi Main, 24-10-2026

- Đối chiếu MainActivity.y1 và Music.GroupPlayIsLastGroup: chế độ nhấn lặp chấp nhận group đang phát hoặc group cuối hàng đợi, trước khi gửi yêu cầu Rhythm mới. ORGX từng xóa queue Intro trước kiểm tra, và chỉ xét cuối queue nếu queue không rỗng.
- ImportedStyleSession.press kiểm tra hai vị trí trước nhánh xử lý Intro. Nhấn Intro hai lần khi Main đang chạy nay đánh dấu vòng tạm cho Intro đang chờ. Nhấn lại Intro đang phát trong khi Main đã chờ không xóa yêu cầu Main.
- Bổ sung hai tình huống vào ImportedTempLoopTest hiện có. ImportedTempLoopTest, ImportedStyleSessionTest, ReferenceStyleTransitionTest và assembleDebug đạt. Chưa đối chiếu thao tác/nghe trên APK gốc; không sửa SET hoặc tạo file test mới. Các cơ chế chuyển section khác chưa được coi là tương đương toàn bộ.

## Hiển thị trạng thái không giữ hợp âm, 24-10-2026

- Khi tắt Chord Memory và nhả hết phím hợp âm, engine đã trả -1 để ngừng phần hòa âm nhưng LCD vẫn hiện hợp âm cũ. LCD nay lấy thêm trạng thái này từ engine và hiển thị dấu —; không thay đổi lựa chọn hợp âm thủ công hoặc Auto Chord.
- Sửa setter đặt nhầm: chordControl kiểm tra 0..4, chordQuality kiểm tra 0..2, tương ứng các giá trị engine hỗ trợ.
- assembleDebug và KeyboardChordInputTest/ReferenceRhythmPlayerTest đã đạt; APK đã cài lên thiết bị. Chưa xác minh bằng thao tác chạm và nghe so với APK gốc cho thay đổi LCD này. Không sửa file SET và không tạo file test mới.

## Track giữ phiên bản riêng khi đọc STS, 24-10-2026

- Track.ReadFromStream gốc dùng fVersion của track để quyết định STSCount13 trước1.2.9, có STS hay không và cách đọc tên snapshot. ORGX trước dùng musicVersion của container. Parser nay lấy metadata version của track, fallback SET version nếu rỗng và lưu ImportedTrack.sourceVersion.
- parseSts nhận trackVersion cho tên legacy/new; sốflags và điều kiện lưu STS dùng trackVersion. Style loader dùng version track cho legacy KeyConnection effects, không dùng version instrument hay container khi metadata track có sẵn. Không mở rộng hỗ trợ toàn bộ schema mới ngoài các nhánh hiện có.
- Kiểm tra binary Track1.2.8 giữ nguyên, đổi header Music sang1.4.6: STS/instrument/MIDI/mode/keyarea đọc giống trước, version track còn1.2.8. Kiểm tra SET mẫu có sẵn/template92instrument và ImportedModeTest, build đạt; cài APK thành công.
- Chưa thống kê mixed-version trên SET điện thoại hoặc so thao tác/âm thanh APK. Một số layout channel/event rất cũ vẫn chưa hỗ trợ đầy đủ; không tuyên bố parser mọi version. Không sửa SET hoặc thêm file test mới.

## Key Connection không tái dùng sample của part cũ, 24-10-2026

- ImportedKeyboardParts.noteOn trước tìm previous theo partID/MIDI channel, có thể connectTo vào playback của nguồn âm trước sau khi replace part; low mode cũng lấy source key từ nguồn không còn hiện hành. Nay yêu cầu sound identity trùng và note chưa retired trước khi nối.
- Nốt đang ngân của bộ trước vẫn render theo cơ chế tail hiện có; nốt mới mở đúng SampledInstrument của bộ mới. Retire rồi chọn lại cùng sound cũng không hồi sinh note đã retired. Các nối nốt trong cùng nguồn/kênh vẫn dùng luật cũ.
- Kiểm tra source0.25 đổi source0.5 cùng part/mode16: nốt mới tạo playback riêng tổng0.75, noteoff cũ còn0.5. Retire/chọn lại cùng sound có2voice rồi tail cũ hết còn1. ImportedKeyboardPartsTest/ImportedKeyboardPartQueueTest và build đạt; cài APK thành công.
- Chưa thử thao tác giữ phím đổi STS thực trên điện thoại/so bản thu APK. Sound identity là ranh giới prepared context ORGX, chưa mô phỏng tái sử dụng native channel qua mọi instrument-mode change. Không sửa SET hoặc thêm file test mới.

## Thoát STS cho nốt cũ nhả tiếng thay vì xóa ngay, 24-10-2026

- clearStsParts trước gọi queue.clear, xóa notes khi đổi voice/SET. Thêm ImportedKeyboardParts.retire và queue.retire: xóa định tuyến part cho nốt mới, forceRelease voice cũ và giữ render vào mixer hiện có cho tới khi finished. Engine thoát STS dùng retire; clear/reset hoàn toàn vẫn giữ cho caller cần hard reset.
- Note retired không chạy KeyRepeat để không tiếp tục tạo note mới sau khi đã rời KeyboardSet. Queue xử lý trên audio thread; generation/commit guard từ lượt trước giữ nguyên. Không thay SET.
- Kiểm tra PCM envelope0.25 trước retire, frame kế vẫn>0 và<0.25, sau release về0; noteOn sau retire không phát tiếng cũ, replace nhóm mới lại phát0.25. ImportedKeyboardPartQueueTest/ImportedKeyboardPartsTest/StsCommitWaitersTest và build đạt; cài APK thành công.
- Chưa nghe so STS->voice thực trên thiết bị/APK. Music FX tổng có thể đổi cấu hình khi clear stsMusic, nên chưa bảo toàn mọi đuôi Master DSP khác cấu hình. Không coi mọi kiểu channel transition tương đương100%.

## Chọn factory voice thoát Master STS dù single voice đã null, 24-10-2026

- Master STS thay nhóm keyboard parts mà có thể giữ importedInstrument=null. Setter cũ chỉ clearStsParts khi field!==value; UI chọn factory gánnull lại không clear, nên noteOn vẫn định tuyến vào parts STS.
- Setter nay clear khi voice khác hoặc stsPartsEnabled/currentStsGroup còn hoạt động. clearStsParts tăng generation, xóa currentGroup/single và enqueue clear trên audio thread, ngăn pending batch cũ commit sau chọn voice. Gán lại cùng single voice khi không có batch giữ hành vi cũ.
- assembleDebug và ImportedKeyboardPartQueueTest/ImportedStsBatchTest/StsCommitWaitersTest đạt; cài APK thành công. Các test này kiểm tra queue/batch nền, không phải runtime AudioEngine setter trên thiết bị. Không thêm test phản chiếu điều kiện setter.
- Chưa nghe xác minh chuyển STS->factory trên thiết bị vì SET hiện tại không có Master slot phù hợp theo index. Không coi đây là đối chiếu mọi quy tắc release tail/channel switch của APK gốc. Không sửa SET.

## Chặn tiếng mặc định lúc restore và kết quả voice nạp cũ, 24-10-2026

- PianoKeyboard/PerformanceWheels trước chỉ dựa keyboardActive của lifecycle, vẫn nhận chạm khi restoringSet=true và engine còn voice factory. Nay enabled yêu cầu đã hoàn tất restore; pointer cancellation/finally hiện có xử lý nhả khi đổi trạng thái.
- Hai nơi chọn SET voice chụp file/index/expectedSound trước IO, dùng snapshot index cho tuning/music/version; trước apply kiểm tra cả ba còn hiệu lực. Nếu người dùng đổi SET/voice khi đang nạp, trả trạng thái selection changed thay vì gán tiếng cũ và ghi prefs cũ. Cuối nhánh vẫn chạy finally giải phóng busy flag.
- assembleDebug đạt, đã cài APK; force-stop rồi mở Activity COLD thành công trên88d75c04. Không thêm test cho gate UI đơn giản; chưa kiểm chứng âm thanh trong thời gian restore hoặc chủ động tái hiện race đổi SET trên điện thoại. Không sửa SET.
- Chặn restore chỉ áp input cảm ứng của màn hình; MIDI/controller ngoài chưa có gate chung ở đây. Check identity không phải generation chung cho mọi nguồn chọn voice, nên chưa tuyên bố loại bỏ toàn bộ race.

## Phân trang Keyboard Set trên LCD, 24-10-2026

- ImportedStsSelection.consoleSlots giữ bốn vị trí Main/STSIndex ở đầu, rồi bổ sung tất cả slot Master đang bật chưa nằm trong bốn vị trí đó, sorted/distinct. Theo Music.STSExists, không dùng slot chỉ có ở member track làm KeyboardSet toàn nhóm.
- Thêm STS< / STS> khi có hơn4 mục, hiển thị bốn nút mỗi trang. Trang sau ghi số slot thực, giữ active/loading và cơ chế nạp/hủy của directSTS; chuyển trang không dừng style hoặc tự phát STS. Đổi SET/style reset trang0. Vùng trạng thái NEXT chuyển thấp xuống khi có nút trang để không đè nhau.
- Kiểm tra Master slot0/1/5/8/12 với styleoverride8: trangđầu8/null/null/null, phầncòn0/1/5/12; tập slot hiển thị đủ và không gồm member-only. ImportedStsSelectionTest/ImportedStsBatchTest và build đạt; cài APK thành công.
- Chưa kiểm tra bố trí/tap paging trên thiết bị với SET có nhiều Master STS; chưa có KeyboardSet name browsing/phân loại như toàn bộ app gốc. Không sửa SET hoặc tạo file test mới; không tuyên bố UI giống100%.

## DNC exit giữa mode trùng tên vẫn cập nhật identity, 24-10-2026

- changedMode nhận savedKey, so cả display name và key instrument/mode; mode khác index nhưng cùng tên vẫn trả tín hiệu lưu. Nhạc cụ có identity nhưng chưa lưu key cũng được migration khi chạy; không làm mất fallback nhạc cụ dựng bằng code không có identity.
- Vòng lưu direct voice truyền key hiện tại; MainActivity.onPause bổ sung lưu direct mode mới nhất khi không có selectedStsSet, tránh chỉ lưu STS mà bỏ sót exit voice ngay trước khi chuyển nền. Không sửa SET.
- Kiểm tra script release exit mode1->0 cùng tên: trước exit khôngchanged, sau exit name giữ nhưng key thành instrument5:mode0, lưu xong khôngchanged. Kiểm tra script Move gốc và ImportedSoundSelectionTest, build đạt; cài APK thành công.
- Chưa kiểm tra đóng/mở process thực với mode trùng tên hoặc chụp snapshot nguyên tử lúc script đổi mode. Không lưu phase voice/DSP hoặc toàn bộ DNC biến runtime; chưa coi restore100%.

## Voice trùng tên khôi phục bằng định danh, 24-10-2026

- Thêm selectedSound giữ số sound gốc trên lựa chọn raw sound; mode giữ selectedMode. ImportedSoundSelection.key kết hợp sourceIdentity của nhạc cụ với sound/mode index. Hai nơi chọn voice lưu importedSetSoundKey cùng file/name; mode exit có đổi tên lưu key mới qua đường changedMode hiện có.
- Resolve dùng key khi có, tên chỉ fallback cho dữ liệu lưu cũ. Key thiếu trong SET không tự chọn voice khác dù tên khớp. Restore chụp key trước IO và kiểm tra key/file/name chưa đổi trước apply để tránh ghi đè lựa chọn mới trùng tên.
- Kiểm tra4voice cùng tên thuộc2nhạc cụ/2sound, keyinstrument2:sound1 chọn đúng object; legacy tên mơ hồ và key không tồn tại bị từ chối. ImportedSoundSelectionTest/ImportedModeTest và build đạt; cài APK thành công.
- Chưa kiểm tra restart thiết bị với SET trùng tên. Các mode có cùng display name và script exit về mode trùng tên có thể chưa kích hoạt changedMode; chưa chuyển mọi lựa chọn sang một identity bus chung. Không sửa SET hoặc tuyên bố100% parity.

## Hủy yêu cầu STS cũ giữa nút trực tiếp và Main-link, 24-10-2026

- Hai đường nạp trước dùng job riêng nên có thể tranh commit cùng expected group. Thêm directStsOverride/revision: bấm STS trực tiếp làm Main effect mất hiệu lực và bị cancel; bấm Main khi link bật cancel direct job, tăng generation UI và tạo yêu cầu Main mới. Link tắt không hủy direct khi chỉ chuyển Main.
- Override kết thúc khi chọn Main, đổi session hoặc thay preference link; không tự bật/tắt preference. Main effect kiểm tra override/revision trước await commit, tiếp tục dùng cancellation gate hiện có của STS. Không thay dữ liệu SET hoặc tạo test phản chiếu Compose.
- StsCommitWaitersTest/ImportedStsBatchTest và assembleDebug đạt; cài APK thành công. Chưa kiểm chứng thao tác đua trên thiết bị với SET có Master STS; chưa có bằng chứng hành vi cạnh tranh IO này giống timing gốc100%.

## Legato tính pitch theo region đang phát, 24-10-2026

- Channels._Connection tính key mới với lastChannel.BaseKey và sample rate của region đang dùng. Playback.connectTo trước gọi pitchStep(targetNote) chọn region theo target, dù PCM vẫn là region cũ; connectFrom cũng chọn source region sai khi voice mới dùng target sample.
- pitchStep nhận region tùy chọn; connectTo dùng selected[note] của voice cũ, connectFrom tính pitch source theo selected[note] của voice mới. PitchMode1 dùng root của region thực tế. Không đổi mapping của note-on thông thường hoặc tự thay PCM khi legato giữ voice.
- Kiểm tra hai region root60/rate1000 và root72/rate2000: nối60->72 trên PCM cũ tiến bước2, nối về60 bước1; connectFrom trên PCM mới khởi động theo source key đúng. SampledInstrumentTest/ReferenceRhythmPlayerTest và build đạt; cài APK thành công.
- Chưa thu âm nhạc cụ đa-region trên SET thực/so APK. Chưa đối chiếu mọi tuning/pitchMode/legato speed integer hoặc ForceLoop; không tuyên bố hoàn thành toàn bộ nối nốt. Không tạo file test mới hoặc sửa SET.

## Style/PAD truyền Key Connection Method tới mode loader, 24-10-2026

- loadSections đã nhận keyConnectionMethod cho expression track nhưng bỏ sót khi gọi ImportedInstrumentLoader.load. Nay truyền tham số này để các mode context/fallback exit được chuẩn bị cùng phương thức engine, thay vì mặc định0. Đường style và PAD cùng dùng loadSections.
- Kiểm tra loader có Track channel override, nhạc cụ1.4.2/mode tạm chứa release exit: sau noteoff về mode0, KeyConnection mode2 được áp method1 thành18. ImportedModeTest/ReferenceRhythmPlayerTest và assembleDebug đạt; cài APK thành công.
- STS.ReadFromStream gốc không áp cùng conversion cho Effects snapshot; không tự thay đổi nhánh đó. Chưa thu âm DNC exit của style thực hoặc so APK; không xác nhận mọi mode/script giống100%. Không thêm file test hoặc sửa SET.

## Key Connection mode dùng phiên bản nhạc cụ riêng, 24-10-2026

- Instrument.ReadFromStream áp quy tắc mode KeyConnection trước1.4.3 theo fVersion riêng. Parser ORGX nay giữ metadata version trên ImportedInstrument; loader mode dùng sourceVersion nhạc cụ khi có, fallback tham số container cho nhạc cụ dựng bằng code không có metadata.
- Nhạc cụ1.4.2 trong SET1.4.6 nay nhận bit keyConnectionMethod16, nhạc cụ1.4.6 không bị áp compatibility dù container cũ. Các bản copy/layer/exitmode giữ metadata nhờ data class copy. Không sửa expression effects của track dựa trên phiên bản nhạc cụ; track vẫn dùng version SET.
- Kiểm tra loader mode2/method1: old instrument ra18, modern ra2, metadata rỗng fallback old ra18; object mode gốc giữ2. ImportedModeTest và index template92nhạc cụ, build đạt; cài APK thành công.
- Chưa tìm/thu âm nhạc cụ mixed-version trong SET thực, chưa đối chiếu toàn bộ điều khiển nối nốt. Nhánh STS dùng effects snapshot riêng không thay đổi trong lượt này. Không tuyên bố tương đương100%.

## LCD dùng metadata group đang phát và giữ thông báo NEXT, 24-10-2026

- Session bổ sung lookup section(id) trên cả button sections và linked JumpGroup targets. LCD meter dùng metadata này thay vì fallback4/4 khi group nối tiếp nằm ngoài danh sách nút; Main link cũng nhận diện linked Main qua lookup chung. Không thêm group nối tiếp vào danh sách style/nút.
- Thông báo NEXT của section đang chờ hiển thị ở vùng trạng thái trái, vẫn thấy khi LCD có hàng STS1..4. Trước hàng STS làm nhánh thông báo phải bị ẩn và không còn nơi báo pending.
- Kiểm tra loader group nối tiếp3/4 tra được meter đúng, group không liên quan không tra được; hồi quy ReferenceStyleTransitionTest và build đạt. Cài APK thành công. Không thêm file test hoặc sửa SET.
- Chưa kiểm tra hình ảnh/tap trên điện thoại ở lượt này hoặc so pixel với APK. Meter fallback khi group không lưu meter vẫn dùng quy tắc hiện có, chưa nối metronome meter tùy chỉnh toàn cục.

## Byte dự phòng STS cũ không phải độ dài tên, 24-10-2026

- STS.ReadFromStream trước1.4.5 chỉ readByte và đặt Name rỗng; parser ORGX từng đọc byte này thành legacyLength rồi skip tương ứng, lệch trường nếu reserved khác0. Đã bỏ skip; nhánh tên length-prefixed1.4.5+ giữ nguyên.
- Kiểm tra binary SET1.2.8 tối thiểu, slot1 bật/13flags, reserved7 không có tên: đọc đúng MIDI513/instrument27/system3/mode2/tempo142/data2=9, keyarea24..100 và groupcount cuối file. Kiểm tra đọc SET mẫu hiện có và ImportedStsBatchTest, assembleDebug đạt; cài APK thành công. Không sửa SET hoặc thêm file test mới.
- STSExists gốc xác định bằng Master.Enable; không tự tạo STS cho slot tắt. Chưa chứng minh reserved byte của SET hiện tại khác0 hoặc đây là nguyên nhân nút STS disabled; chưa thao tác/thu âm so APK, không tuyên bố mọi STS thiếu đã được sửa.

## STS1–4 trực tiếp trên LCD mặt đàn, 24-10-2026

- Thêm hàng STS1..4 dưới PAD trên InstrumentConsole; slot dùng liên kết STSIndex của style hoặc1..4 qua resolver đã đối chiếu MainActivity. Hiển thị active theo currentStsGroup và dấu... khi nạp. SET thiếu slot giữ nút disabled, không tự tạo snapshot.
- Bấm gọi ImportedStsRequest.prepare track0 với SET CONTROL, nạp PCM trên IO rồi await commit gate; không gửi lệnh chuyển style/Play/Stop. Lưu selectedSET/track0/slot để restore hiện có tiếp nhận. Job mới hủy request cũ; revision giữ trạng thái nạp đúng cả khi bấm lại cùng slot, kiểm tra SET/expectedSound/currentGroup trước apply.
- ImportedStsSelectionTest/ImportedStsBatchTest và build đạt, đã cài APK. Xem màn hình thiết bị sau restore: SET DJ KHA BANH theo resolver hiện tại không có Master slots tương ứng, nên chưa kiểm chứng gọi tiếng bằng nút STS trên SET này. Không sửa SET hoặc thêm file test/UI skill.
- Hàng nút là bổ sung thao tác trực tiếp theo cơ chế KeyboardSet của gốc, không tuyên bố vị trí/kích thước giống100%. Chưa có thử nghiệm thao tác đồng thời Main-link và direct STS/đổi SET hoặc thu âm; chưa hỗ trợ phân trang toàn bộ KeyboardSet trên mặt đàn.

## Bỏ timing Pair allocation trong callback PAD, 24-10-2026

- AudioEngine từng gọi session.padTiming mỗi sample, tạo Pair và boxing thời gian Long để truyền sang PAD. Thêm nextAt dùng primitive Long/Int và renderPads của session đọc clock/scale, render bank, nhận scale phản hồi dưới một session lock. Engine không gọi padTiming trong callback nữa; đường next với Pair vẫn giữ cho caller/test cũ.
- Khi không có style chạy, nextAt dùng clock bank như trước; không thay sample generation/nhịp/Synchro/STOP. Không tuyên bố toàn callback không allocation: các stage/player khác vẫn có phần việc cần tối ưu.
- Kiểm tra so PCM stereo/masks/scale qua4000frame, đổi tempo120->173 và STOP giữa lượt khớp đường timing cũ. Kiểm tra renderPads truyền scale1500 về style nhưng không tiến clock, Main chờ chuyển đúng cửa sổ1500. ReferenceRhythmPlayerTest/ReferenceStyleTransitionTest, assembleDebug đạt; cài APK thành công.
- Chưa profile allocation/GC/underrun hoặc đo latency trên thiết bị; loại bỏ source allocation không chứng minh hết giật tiếng. Không thêm file test mới hoặc sửa SET.

## Cộng pan sample/nhạc cụ/Mix trước khi giới hạn kênh, 24-10-2026

- Channels.OpenKey cộng notePan+Instrument.fPan+InstSec.Pan rồi tính min100; Channels.Process chặn iVolumeL/R âm về0. ORGX từng từ chối tổng pan instrument+section vượt100 và samplePosition có thể tạo panGain âm khi cộng notePan.
- Loader kiểm tra từng metadata pan nhạc cụ/sample trong-100..100 thay vì từ chối tổng; SampledInstrument cho region lưu tổng pan, dùng Long khi cộng notePan và giới hạn gain từng kênh0..100 để không đảo dấu PCM.
- Tách layerPan cho Mix khỏi metadata pan nhạc cụ, cộng vào region khi load; không clamp sớm từng tổng trung gian nên Mix có thể bù pan nhạc cụ/sample đúng. Gain layer độc lập từ lượt trước giữ nguyên. Không sửa SET.
- Kiểm tra PCM pan±100 ở cả instrument+section cho kênh đối diện0 và kênh còn lại đầy đủ; instrument75+section75+Mix-100 cho left50%/right100%. ImportedModeTest/SampledInstrumentTest và kiểm tra loader mục tiêu, assembleDebug đạt; cài APK thành công.
- Chưa đối chiếu bản thu APK, native integer rounding hoặc mọi channelPan/TouchBalance route. Chưa thay đổi cấu trúc track FX/mixer tổng; không coi stereo routing hoàn chỉnh100%.

## Volume nhạc cụ/sample bằng0 theo OpenKey, 24-10-2026

- Hai nhánh Channels.OpenKey chỉ nhân Instrument.fVolume và InstSec.Volume khi cả hai khác0; ORGX trước nhân trực tiếp gây im lặng khi metadata một trong hai bằng0. regionGain nay trả1 khi có0, còn cả hai khác0 giữ product/10000.
- Tách ImportedInstrument.layerGain để hệ số MixVol tác động độc lập, theo MixVol thay Touch trước OpenKey. Không gộp gain mix vào stored instrument volume nữa: MixVol-100 vẫn im lặng, không bị zero-sentinel biến thành unity. Volume track/mixer0 vẫn mute như trước.
- Kiểm tra PCM các tổ hợp0/100/50 cho instrument/section,50x50 ra0.25, silent mix-100 và trackvolume0 vẫn0. SampledInstrumentTest/ReferenceRhythmPlayerTest ở lần đầu; sau tách gain, kiểm tra mục tiêu/SampledInstrumentTest và ImportedModeTest đạt; assembleDebug và cài APK thành công. Test mode cũ được đổi từ kiểm tra volume nội bộ50 sang volume100/layerGain0.5; kiểm tra PCM/stereo/envelope mode vẫn đạt.
- Chưa đối chiếu bản thu SET thực/APK; chưa tái tạo lượng tử integer Touch khi MixVol nhân trước gain nhạc cụ. Không suy ra toàn bộ gain/mixer tương đương100%; SET không bị sửa.

## Nối nốt mở lại voice đã bắt đầu release, 24-10-2026

- Channels._Connection gán Close=false khi nhận key mới. Playback.connectTo đã reconnect envelope nhưng chưa xóa forcedRelease hoặc loopExited của Loop1, khiến voice nối vẫn tự đóng/đọc tail khi next.
- connectTo nay xóa forcedRelease và loopExited cùng scriptReleased/releaseHandled. Điều kiện lựa chọn voice và envelope reconnect hiện có không đổi; chỉ mở lại khi caller thực sự thực hiện connection. Không reset PCM phase hoặc tự hồi sinh voice đã bị xóa khỏi mixer.
- Kiểm tra Loop1 voice đang giữ, forceRelease3frame rồi connect mode2/stopByReleasefalse: giữ50frame trong loop với gain đầy đủ và chưafinished; forceRelease sau đó vẫn kết thúc. SampledInstrumentTest/ReferenceRhythmPlayerTest và build đạt; cài APK thành công. Không tạo file test mới hoặc sửa SET.
- Chưa thu âm hoặc đối chiếu thao tác keyconnection thực trên APK. Chưa xử lý toàn bộ ForceLoop, dynamic connection speed và mọi nhánh script/autorelease; không coi nhạc cụ biểu cảm hoàn chỉnh100%.

## Sample Loop1 giữ phím rồi đọc tail khi nhả, 24-10-2026

- Đối chiếu Channels.Process nhánh InstSec.Loop: Loop>1 luôn lặp, Loop1 chỉ lặp khi channel chưa Close; khi không lặp dùng Wave.Length. Loader trước từ chối1, làm nhạc cụ/style có region này không được hỗ trợ.
- Thêm InstrumentRegion.loopUntilRelease, loader map Loop1 riêng. Playback khi released lần đầu quy vị trí tích lũy về phase vật lý trong loop, khóa thoát loop cho voice đó và đọc tới cuối PCM qua envelope; Loop2/3 giữ hành vi cũ. Đường sample đọc trực tiếp không có noteoff vẫn coi là đang giữ.
- Kiểm tra PCM giữ10frame cho Loop1 giống Loop2, sau nhả Loop1 đọc frame4/tail rồi finished trong khi Loop2 vẫn lặp. Kiểm tra ImportedStyleSupport/InstrumentLoader chấp nhận Loop1 có bounds hợp lệ. SampledInstrumentTest/ReferenceRhythmPlayerTest và kiểm tra style mục tiêu, assembleDebug đạt; cài APK thành công.
- Chưa tìm/so bản thu Loop1 trong SET thực với APK. Chưa đối chiếu đầy đủ ForceLoop/keyconnection nối lại voice đã thoát loop hoặc native interpolation/boundary Length-1. Không suy ra toàn bộ tiếng nhạc cụ tương đương100%. File SET giữ nguyên.

## PAD Loop không giới hạn tiếp tục sau hết sự kiện, 24-10-2026

- Đối chiếu Tracks._PlayGroup: khi Track.PlayNext hết dữ liệu gọi GroupPlayStart; GroupPlayNext giữ group hiện tại nếu Loop/tempLoop. ImportedPadSequence trước chuyển JumpGroup dương khi player.finished kể cả Loop, hoặc bank chuyển sang tail rồi đóng slot khi Loop không có jump.
- Nhánh player.finished && group.loop nay restartAt với startOffset của clock chung, giữ channel controls/nhả voice/selective FX như đường Track.Start. Chỉ group khôngLoop mới theo JumpGroup dương. Bank nhận awaitingContinuation để không đóng slot Loop vừa hết dữ liệu.
- Kiểm tra PCM unbounded PAD có NoteOn/NoteOff và JumpGroup tới player giá trị100: slot vẫn chạy qua nhiều frame/cycle, có năng lượng sau lượt đầu, không chuyển group2; STOP hết release về0. ReferenceRhythmPlayerTest/ReferenceStyleTransitionTest và build đạt; cài APK thành công. Không tạo file test mới hoặc sửa SET.
- Chưa đối chiếu PAD unbounded trong SET thực/thu âm APK. Empty group vẫn có thể restart mỗi frame như đường hết sự kiện; chưa gom scheduler style/PAD hoặc tái tạo LastLoop/tempLoop của PAD. Không coi mọi loại PAD hoàn chỉnh100%.

## Lưu Master lấy tempo/chord live thay vì apply plan, 24-10-2026

- Prepared Master dùng tempo/chord null để biểu thị không thay đổi khi áp STS. Lưu trực tiếp object này từng mất giá trị hiệu lực sau đổi BPM/chord thủ công hoặc sau STS có cờ tempo/chord tắt.
- Thêm ImportedStsMaster.capture và AudioEngine.stsMasterForPersistence: lấy BPM live, chordInputMode/chordSingles live, music bus hiện có và hệ tuning hiệu lực. Kiểm tra current group/SET trước và sau snapshot; vòng lưu định kỳ và onPause dùng snapshot này. Không đổi kế hoạch apply hoặc mutate SET.
- Kiểm tra plan có tempo/chordnull được lưu thành BPM173/chordmode2/table273 nhưng plan gốc cònnull; trường hợp chưa có Master Prepared vẫn capture music77/BPM99/mode0. ImportedStsMasterTest/ImportedStsBatchTest/ImportedStsChordSettingsTest và build đạt; cài APK thành công.
- Chưa kiểm tra process restart với STS chọn trên thiết bị. Chưa bảo đảm snapshot nguyên tử của mọi field live khi audio commit đồng thời; tuning lấy từ current Prepared, không bao quát editor tuning chưa tích hợp. Dual chord mapping riêng vẫn theo preferences hiện có, không nằm trong Master codec. Chưa xác nhận tương đương APK100%.

## Restore STS một track giữ hiệu lực channel/FX, 24-10-2026

- encodeSingle chuyển sang payload v2 dùng codec part state chung, giữ record/pedal/system/nhạc cụ/mode/channel numeric/FX modules/expression thay vì chỉ mode. Prefix track/instrument và file fingerprint/slot vẫn chặn restore nhầm. restoreSingle đọc cả v2 và định dạng cũ.
- decodeParts nhận baseline snapshots tùy chọn, để single dùng đúng snapshot track riêng mà không giả lập Group0. Cả vòng lưu và onPause hiện có tự dùng encodeSingle mới; restore hiện có tự nhận đầy đủ state.
- Kiểm tra loader giữ volume37/pan-12, Echo và expression depth29, pedal1/recordfalse; payload hỏng fallback snapshot, định dạng cũ đọc được. ImportedStsLoaderTest/ImportedStsBatchTest và build đạt; cài APK thành công. Không tạo file test mới hoặc sửa SET.
- Chưa kiểm tra đóng/mở với STS được chọn trên thiết bị. Không lưu phase PCM/FX, không tái tạo mọi điều khiển live ngoài Prepared snapshot; các giới hạn lưu định kỳ và Master thủ công vẫn còn. Đây là cải thiện khôi phục ORGX, chưa có chứng cứ định dạng lưu preference giống APK gốc.

## Lưu STS khi chuyển Activity xuống nền, 24-10-2026

- Thêm persistStsState trong MainActivity.onPause trước super: lấy snapshot mới nhất của batch, lưu modes/partFX/Master hoặc single mode theo selection tương ứng. Kiểm tra active SET index/file và slot/track để không ghi đè bằng batch cũ trong lúc đổi SET. Vẫn dùng SharedPreferences.apply, không chặn UI bằng commit/đọc PCM.
- Bổ sung cho vòng lưu100ms hiện có, giảm cửa sổ mất thay đổi khi coroutine UI ngừng. Không bảo đảm trường hợp process bị kill đột ngột không đi qua onPause hoặc state thay đổi sau pause.
- Thiết bị88d75c04: force-stop/mở lạnh thành công, xem màn hình đã phục hồi style DJ KHA BANH/Main1, voice Dance9KBT/Primary và tempo141. Sau build/cài mới, đưa HOME rồi mở lại Activity thành công. Không có selectedStsSet/Track/Slot trong prefs thiết bị, nên không coi đây là kiểm chứng khôi phục STS hoặc nhánh ghi snapshot với STS đang chọn.
- assembleDebug đạt; không thêm test/file mới. Các giới hạn live Master thay đổi thủ công, phase DSP và restore single đầy đủ vẫn còn. File SET không bị sửa.

## Restore Master STS hiệu lực, 24-10-2026

- Thêm lưu stsEffectiveMaster gắn file/length/mtime/slot: channel music đầy đủ numeric + FX modules, các system tuning, optional tempo và chord type/single mapping. Codec binary Base64 có giới hạn kích thước/count và từ chối dữ liệu sai, thừa byte, khácSET/slot hoặc số hệ tuning không khớp.
- prepareFull nhận restoredMaster, dùng music/system hiệu lực để chuẩn bị part PCM trước, trả batch kèm Master để engine commit chung. Nhánh không có Master hiệu lực xóa preference cũ, tránh phục hồi nhầm trạng thái trước. Không sửa SET.
- Kiểm tra roundtrip Master volume73/pan-21/Echo, bảng tuning0.25,tempo142,chord; sai slot, dữ liệu hỏng, số tuning khác hoặc file đổi trảnull. ImportedStsMasterTest/ImportedStsBatchTest và build đạt; cài APK thành công.
- Chưa kiểm tra process restart trên điện thoại. Giá trị lưu là Master Prepared của lần STS đã commit, chưa theo mọi thay đổi tempo/chord/tuning thủ công sau đó; các giá trị optional null vẫn để trạng thái engine hiện có. Không lưu phase FX/voice và chưa coi restore tương đương toàn bộ APK gốc.

## Restore FX và expression effects của part STS, 24-10-2026

- Mở rộng stsEffectiveParts lưu fxEnabled, thứ tự module DSP, type/enabled/realtime/payload và đầy đủ trường effect biểu cảm cùng raw bytes. Decode không thay FX bằng mode mặc định khi đã có trạng thái lưu mới; vẫn đọc bản cũ chỉ có12 trường numeric.
- Kiểm tra số trường/boolean/byte payload, giới hạn kích thước262144 ký tự, module1000, effect8 và slot effect không trùng; lỗi trả rỗng để fallback. Dữ liệu vẫn gắn đúng SET/slot; không thay SET trên đĩa.
- Roundtrip qua prepareFull giữ Echo realtime và PitchShake tùy chỉnh cùng nhạc cụ/channel levels; kiểm tra định dạng cũ và flag hỏng. ImportedStsBatchTest/ImportedFxChainTest cùng build đạt; cài APK thành công. Không tạo file test mới.
- Chưa kiểm tra đóng/mở process trên điện thoại; không lưu phase DSP/voice đang phát. Master music/tuning/tempo/chord hiệu lực chưa được lưu riêng, và vòng lưu định kỳ có thể mất thay đổi cuối khi app bị đóng đột ngột. Chưa coi restore hoàn chỉnh100%.

## Restore part STS hiệu lực sau SET CONTROL, 24-10-2026

- Trước chỉ lưu mode theo part rồi nạp lại snapshot gốc; mất instrument/record/pedal/system và channel levels được giữ bởi SET CONTROL. Thêm stsEffectiveParts lưu các trường này cùng volume/pan/tune/transpose/octave/release, gắn filename/length/mtime/slot.
- Restore xác thực part1..4, instrument/mode/system tồn tại, slot duy nhất và không trùng part; dữ liệu lỗi hoặc khác file/slot trả rỗng, fallback restore cũ. prepareFull nhận restoredParts trước restoredModes rồi kiểm tra budget/nạp PCM như trước. Không sửa SET.
- Kiểm tra roundtrip qua prepareFull giữ instrument0 thay vì slot instrument1, record/pedal và volume43/pan-28/transpose3/octave-1; sai slot, part trùng, file đổi bị từ chối. ImportedStsBatchTest/ImportedStsSelectionTest và build đạt; cài APK thành công.
- Chưa lưu toàn bộ FX modules/expression effects hiệu lực hoặc Master music/tuning. Khi instrument/mode khác snapshot, FX/effects lấy từ mode tương ứng. Chưa kiểm tra đóng/mở process thực trên điện thoại; vòng lưu UI hiện lấy mẫu định kỳ nên đóng đột ngột vẫn có thể mất thay đổi cuối. Chưa coi restore STS hoàn chỉnh.

## SET CONTROL áp dụng ngay lần Master STS đầu tiên, 24-10-2026

- ImportedStsRequest trước gọi prepareFull khi chưa có current group, bỏ qua mask người dùng. Thêm prepareInitial dựng basis cho các main track1..4 kind0/1 từ metadata track của SET: record, instrument/mode, system, pedal, settings volume/pan và expression effects; sau đó dùng chung prepareMasked/ImportedStsChanges/ImportedStsMaster.
- Nhánh chọn Master lần đầu nay giữ dữ liệu track ở các cờ tắt và chỉ chuyển các cờ bật. Khi đã có current group vẫn lấy effective current snapshots và kiểm tra basis như trước. Đường restore snapshot đầy đủ bằng prepareFull không bị đổi.
- Kiểm tra request thực tế với toàn bộ cờfalse giữ instrument0/recordtrue/pedal0/volume40/pan-25/transpose2/octave-1 và Master volume65; bật volume/record/pedal đổi đúng sang90/false/1, pan giữ-25; bật instrument riêng đổi sang1 và nạp default mode trước theo plan hiện có. SET metadata giữ nguyên.
- ImportedStsBatchTest/ImportedStsChangesTest/ImportedStsMasterTest/ImportedStsPlanTest và assembleDebug đạt; cài APK thành công. Không tạo file test mới. Sửa tên nhóm kiểm tra ở hai mục trước: không có lớp ImportedStsRequestTest; hành vi request được kiểm tra trong ImportedStsBatchTest, các pattern Gradle không khớp không được tính thành kiểm tra đã chạy.
- Basis lần đầu lấy từ SET, chưa chụp mọi sửa đổi bàn phím thủ công trước khi có batch STS; không tuyên bố giữ toàn bộ live state đó. Restore theo slot còn dùng full snapshot và chưa lưu mọi trường masked hiệu lực qua đóng app. Chưa đối chiếu thao tác/thu âm với APK gốc.

## Main button yêu cầu STS trước cửa sổ chuyển nhịp, 24-10-2026

- Tiếp tục đối chiếu MainActivity.z1: STSPut chạy trước y1 khi bấm Main, không đợi đoạn Main thành Current. UI ORGX nay ghi nhận mainStsTarget/session và revision ngay trong onPart rồi gửi lệnh rhythm như trước.
- LaunchedEffect của Main link dùng Main được bấm, không chỉ currentId. Có thể chuẩn bị/apply STS khi Main còn pending hoặc Auto Fill đang chạy; chuyển Fill->Main không tự tạo lại cùng yêu cầu STS. Bấm lại cùng Main tăng revision để chủ động gọi lại STS theo thao tác gốc.
- Yêu cầu gắn với session; đổi style khiến target cũ mất hiệu lực. Bấm Main khác hủy effect trước, kiểm tra revision/target/SET/session/âm sắc trước awaitStsRequest, dùng cơ chế cancellation/commit gate hiện có để hạn chế apply kết quả cũ. Nếu chưa có thao tác nút, vẫn giữ đường liên kết theo current section khi restore/bật tùy chọn.
- assembleDebug và hồi quy ImportedStsSelectionTest/ReferenceStyleTransitionTest đạt; cài APK thành công. Không thêm file hoặc test UI phản chiếu implementation. Chưa thao tác timing hoặc thu âm trên thiết bị trong lượt này.
- Nạp PCM vẫn bất đồng bộ trên IO: bắt đầu yêu cầu lúc bấm không đồng nghĩa tiếng đổi ngay trong cùng frame. Đây là đường nút Main trên console; các input điều khiển ngoài chưa được gom chung. Giới hạn SET CONTROL ở lần đầu chưa có basis group vẫn còn.

## Main link chọn STS toàn nhóm bàn phím, 24-10-2026

- Đối chiếu MainActivity.z1: khi Y1 bật và section Main, tính slot từ variation/GroupOf(style).STSIndex rồi gọi Music.STSPut(slot); STSPut áp Master và các main track, không chỉ track editor đang chọn.
- Sửa Main link trong ORGX: bỏ điều kiện đã chọn STS thủ công từ đúng SET và bỏ selectedStsTrack khỏi cách chọn đích. Dùng ImportedStsRequest.prepare track0 để chuẩn bị cả group; chỉ apply nếu session/section/SET/âm sắc vẫn đúng sau nạp IO. Lưu selectedStsTrack0/SET/slot sau apply thành công để restore theo batch.
- linkedMainSlot kiểm tra slot tồn tại duy nhất trên Master group0; slot vắng không đổi âm sắc. Nếu metadata group của style vắng, dùng slot1..4 thay vì lấy STSIndex của section con. Nhãn UI đổi thành Đổi toàn bộ STS theo Main1–4.
- ImportedStsSelectionTest/ImportedStsBatchTest và assembleDebug đạt; cài APK thành công. Có kiểm tra Main override8, Master thiếuslot, chỉ member cóslot, thiếuMaster và fallbackdefault. Không tạo file test mới hoặc sửa SET.
- Chưa thao tác/thu âm đối chiếu trên thiết bị. Main link vẫn kích hoạt theo section đang phát, chưa theo thời điểm bấm Main trước cửa sổ chuyển nhịp như z1. ImportedStsRequest hiện áp full snapshot ở lần đầu chưa có current group; SET CONTROL mask áp cho các chuyển tiếp có basis group, chưa bảo toàn mọi trạng thái bàn phím trước lần đầu.

## STOP giữ release tail của style, 24-10-2026

- Đường setRhythmEnabled(false) từng xóa naturalStyleTail rồi reset player ở frame dừng, khiến nốt và FX đang ngân bị mất. Theo cơ chế đóng kênh CloseAll(false) của Tracks.GroupPlayNext, chuyển sang release thay vì xóa voice/buffer.
- AudioEngine gửi pendingRhythmStop qua atomic mailbox; audio thread gọi session.stopPlayback rồi render tail khi transport đã dừng. Mixer vẫn nhận importedRhythm tới khi hết tail; chỉ sau đó StoppedStyleReset đặt lại player. Không gọi DSP từ UI thread.
- stopPlayback hủy queue, jump và temporary loop, ngừng clock/scheduling, giữ player để nhả tiếng. Khi STOP rồi START/Intro trước khi mailbox được xử lý, giữ yêu cầu immediate mới và preparePlay xử lý lại; các yêu cầu cũ không chạy muộn. STOP performance vẫn dừng PAD qua bank riêng hiện có.
- Kiểm tra manual stop: voice0.1->0.09 rồi hết, clock không tiến, Main2 đang chờ bị hủy; preparePlay phát lại. STOP rồi Intro tức thời vẫn chạy Intro và về Main. ReferenceStyleTransitionTest/ReferenceRhythmPlayerTest/StoppedStyleResetTest và assembleDebug đạt; đã cài APK.
- Chưa thu âm/so thao tác STOP trên APK gốc. AudioEngine chỉ được build, kiểm tra JVM nêu trên áp vào session/player; chưa có test runtime thiết bị cho toàn bộ mixer. Chưa tái tạo thời điểm xử lý lệnh STOP của RecordRhythm theo mọi mode hoặc giữ tail khi đổi SET giữa lúc đang nhả.

## Khởi động lại track giữ channel state, 24-10-2026

- Đối chiếu Track.Start: đặt lại DataPos/TimeStart, Channels.CloseAll(false), gọi z01.f(true); không khôi phục volume/pan/pedal/KeyTouch/KeyBalance/KeyPitchMove từ metadata. ORGX từng reset toàn bộ player khi quay lại section, còn nhánh preserveVoices lại không nhả các note cũ.
- Thêm restartAt giữ trạng thái điều khiển và các release tail, đặt lại timeline/clock, đưa note đang mở sang release. ImportedStyleSession và chuyển JumpGroup PAD dùng đường này; full reset vẫn đặt lại trạng thái ban đầu. Nhánh startAt(preserveVoices=true) nay giữ voice trong release thay vì để ngân vô hạn.
- ImportedFxChain giữ danh sách module cùng thứ tự stage, thêm resetRealtime chỉ reset stage có realtime=true theo z01.f(true). Gọi khi Track.Start/restart và khi qua ranh giới loop; FX không realtime không bị xóa buffer chỉ vì khởi động lại track.
- Kiểm tra PCM: đổi volume50/pan100 rồi restart giữ đúng phía/gain, old/new note tổng1 rồi0.95 và ổn định0.5; full reset trả hai kênh1. Kiểm tra Main1->Main2->Main1 giữ volume50 trước sự kiện volume kế tiếp. Echo thường còn0.5 sau selective reset, Echo realtime về0; module disabled không làm lệch ánh xạ.
- ReferenceRhythmPlayerTest/ReferenceStyleTransitionTest/ImportedFxChainTest và assembleDebug đạt; kiểm tra Main reentry mới đạt riêng; cài APK thành công. Không tạo file test mới hoặc sửa SET.
- Chưa đối chiếu bản thu APK. resetRealtime dùng Stage.reset hiện có, chưa mô phỏng riêng mọi module c()/a() hoặc phase native. Full Stop/reset, mọi kiểu pedal và group FX khác cấu hình vẫn cần đối chiếu thêm; chưa coi transport/audio tương đương100%.

## Không áp nhầm Solo của editor vào style/PAD, 24-10-2026

- Đối chiếu Tracks.PlayNext ở playmode0 đặt _solo=false trước _PlayGroup. Bộ lọc activeTracks của ORGX từng tự kích hoạt solo nếu bất kỳ track có fSolo, làm mất các bè khác trong group type1. Nay playback group type1 không lọc theo metadata Solo; Mute và lựa chọn drum-only vẫn được giữ. Metadata SET không bị thay đổi.
- Kiểm tra qua loader PCM ở cả section32 và96: lưu Solo ở Bass vẫn có cả drum/chord bus, tổng stereo bằng bản không có Solo; track Mute vẫn không được đưa vào danh sách phát. Đã sửa kỳ vọng Solo cũ vốn phản ánh cách triển khai ORGX thay vì playmode thường của gốc.
- Sửa tương thích chữ ký load/loadStyle: callback targetChord giữ vị trí cuối để lời gọi Kotlin trailing lambda cũ không bị gán thành chordQuality sau khi thêm tùy chọn ở lượt trước. Callback có thể trảnull vẫn được loader gọi đúng; các lời gọi app dùng named arguments giữ nguyên.
- Build và các kiểm tra Solo PCM/callback cùng ReferenceRhythmPlayerTest đạt; đã cài APK. Lượt chạy cả ImportedStylePlayerTest được dừng vì gồm render SET dài; kết quả báo đạt ở đây chỉ thuộc các kiểm tra mục tiêu nêu trên. Không tạo file test mới.
- Báo cáo SET thiết bị hiện có ghi solo=false cho các track nên chưa có bằng chứng lỗi Solo ảnh hưởng SET đó; sửa này áp dụng SET có cờ Solo. Chưa đối chiếu thu âm APK, chưa dựng chế độ Solo editor/recording hoặc toàn bộ tương tác fMute của gốc.

## Nạp group nối tiếp ngoài danh sách nút style, 24-10-2026

- Tracks.GroupPlayNext gọi Group(JumpGroup), tìm trên toàn SET. loadStyle trước chỉ chuẩn bị các section của style/fallback; group đích có trong SET nhưng ngoài danh sách này vẫn gây transition unavailable.
- Thêm duyệt JumpGroup dương của group không Loop trên toàn index; theo ID đã thấy để kết thúc khi gặp chu trình. loadStyle chuẩn bị các group đích được hỗ trợ bằng cùng loader/sample budget hiện có. ImportedStyleSession nhận linkedGroups riêng, không chèn vào style.sections hoặc tạo thêm style/nút trên UI.
- PAD dùng lại cùng bộ duyệt, vẫn giữ kiểm tra target thiếu và giới hạn64 group. Group Loop không tự đi theo JumpGroup; jump âm/0 vẫn giữ cơ chế hiện có. Không sửa file SET.
- Kiểm tra qua loadStyle: Ending của parent100 nối tới group parent200 rồi kết thúc, không lỗi transition; group parent300 không liên quan không được nạp; danh sách nút giữ1/2. Kiểm tra chu trình, đích thiếu và Loop không duyệt jump; hồi quy ReferenceRhythmPlayerTest/ReferenceStyleTransitionTest và build đạt. Cài APK thành công.
- Chưa đối chiếu bản thu SET thực/APK. Group type khác1 hoặc dữ liệu target chưa hỗ trợ vẫn không được phát và báo unavailable khi chuyển; không thêm hỗ trợ ChordVars hoặc toàn bộ schema SET mới. Tổng PCM của chuỗi vẫn chịu giới hạn64MB.

## Chord quality trong đường phát SET, 24-10-2026

- Đối chiếu Tracks._PlayGroup và arrays.ChordQuality: Minimum0 đánh dấu trước melodic instrument7/8/9, Half1 đánh dấu8; các mức dưới Full chỉ xử lý track đầu tiên của cùng melodic instrument0..10 trong group. Percussion và instrument>10 không bị lọc. Music mặc địnhFull2.
- Thêm ImportedChordQuality giữ mask riêng cho mỗi group, reset mỗi lượt render group; gate nằm trước timeline.advance thay vì giảm volume. Nốt/FX đang phát vẫn được render; khi mở lại, timeline bắt kịp theo quy tắc bỏ nốt hết hạn sẵn có. Track đang event-muted không chiếm slot của track khác.
- Nối callback engine.chordQuality vào restore/đổi/preview style và cả hai đường PAD. Trang CHORD có Minimum/Half/Full, lưu chord_qual và phục hồi trước SET. Không dùng skill UI hoặc sửa file SET.
- Kiểm tra PCM7 track với slot7/8/9, slot1 trùng, percussion và slot11: tổngFull127, Half109, Minimum104. Kiểm tra đổi mức giữ voice hiện tại, chặn nốt mới và bắt kịp khi bật lại đạt cùng ReferenceRhythmPlayerTest/ReferenceStyleTransitionTest. Build/cài APK thành công; không tạo file test mới.
- Chưa so thu âm trên APK hoặc toàn bộ tương tác Solo/fMute/recording/unbounded group. Gate hiện dùng eventMuted của player, chưa chứng minh tương đương mọi nhánh Track.fMute của gốc. Chưa coi toàn bộ accompaniment hoàn chỉnh100%.

## Nhận diện section SET cũ qua phím chức năng, 24-10-2026

- Đối chiếu TrackGroup.ReadFromStream: khi Section==0, ShortCutKeyDevice113..123 lần lượt đổi thành Intro16/17, Main32..35, Fill48/49, Ending80/81, Break64 và màu tương ứng. ORGX từng skip6 byte nên bỏ mất cơ chế này; parser nay đọc shortcutDevice/shortcutMidi và Color, chuẩn hóa section theo bảng gốc.
- Section đã khác0 giữ nguyên cả section lẫn màu dù shortcut trùng; shortcut không được nhận diện vẫn giữ Section0. Không đoán thêm Intro3/Fill3/Fill4/Ending3 từ mã ngoài bảng. Dữ liệu gốc trên đĩa không bị sửa.
- Các section sau chuẩn hóa đi qua ImportedStyleCatalog/SectionFallback/loader hiện có nên xuất hiện trong style và được phát như các section rõ ràng. Không thêm layout hoặc skill UI.
- Kiểm tra binary SET tối thiểu qua parser cho đủ11 mã, mã lạ và explicit section; kiểm tra catalog nhận các Main. Nhóm ReferenceStyleTransitionTest, đọc SET mẫu có sẵn và92 nhạc cụ template đạt; build/cài APK thành công. Không tạo file test mới.
- Chưa xác định SET đang dùng trên thiết bị có section thuộc nhánh này hay không; không tuyên bố đã sửa mọi style lỗi. Chưa thêm điều khiển ngoài bằng shortcut MIDI/device hoặc hỗ trợ ChordVars ở layout1.4.9+.

## Nhịp chuyển đoạn dùng chung PAD/style, 24-10-2026

- Đối chiếu Tracks.CalcRhythmScale và GroupPlayNext: PAD Auto trả-1 giữ scale trước; các method khác tính scale theo meter/LimitEnd-LimitStart. Giá trị<=10 áp trước GroupPlayStart, giá trị dương còn lại áp sau.
- ImportedPadBank giữ rhythmScale khi phát riêng, thay cho hằng1000 mỗi frame. Bắt đầu PAD hoặc chuyển JumpGroup cập nhật scale theo quy tắc trên; các slot sau trong cùng frame dùng giá trị mới. ImportedPadSequence giữ phase bắt đầu theo đúng thứ tự cập nhật scale.
- AudioEngine chuyển thay đổi scale của PAD vào ImportedStyleSession đang chạy, dùng cho cửa sổ chuyển style tiếp theo, không reset clock, không xóa yêu cầu đang chờ. Không thay BPM khi chỉ chọn PAD.
- Kiểm tra: PAD3/4 mode Wait cycle đặt scale1500, slot khác không khởi động ở1000 mà đợi1500; Auto giữ scale500 từ style. Yêu cầu chuyển Main đang chờ vẫn giữ clock100 và chuyển tại1500 sau khi PAD đổi scale. ReferenceRhythmPlayerTest/ReferenceStyleTransitionTest/ReferenceSectionTimingTest và build đạt; cài APK thành công.
- Chưa so thao tác/thu âm trên APK gốc. Style được xử lý trước PAD trong mỗi audio frame nên phản hồi scale về style từ frame tiếp theo; chưa gộp hai scheduler thành một như Tracks gốc, chưa chuyển LastLoop/chord variant PAD. Không xác nhận timing tương đương100%.

## Chuỗi JumpGroup của PAD, 24-10-2026

- Theo Tracks.GroupPlayNext, group không Loop có JumpGroup>0 chuyển tới group chỉ định thay vì luôn dừng. Thêm loadPad nạp các group nối tiếp theo ID trên IO; phát dùng ImportedPadSequence và không đọc file trong audio callback. Cả LCD và trang PAD dùng đường mới.
- Chuỗi được duyệt theo ID duy nhất để không nạp vô hạn khi có vòng, tối đa64 group và dùng chung giới hạn64MB PCM của loadSections. Target thiếu báo lỗi cụ thể; không tự thay bằng PAD/style mặc định. Không sửa file SET.
- Chuyển tại cửa sổ ReferenceSectionTiming.inWindow, đặt phase theo startOffset và method hiện tại. Chuyển khác player mang theo voice release/bus FX phù hợp; self jump giữ voice hiện tại. STOP/đổi SET/token nạp cũ đóng hoặc hủy toàn bộ chuỗi; khi chạy chỉ hiển thị một slot.
- Kiểm tra PCM chuỗi one-shot->loop cho tiếng cũ+mới ở boundary, ổn định sau release và stop về0; token hoàn tất sau STOP không phát. ReferenceRhythmPlayerTest/ReferenceStyleTransitionTest và assembleDebug đạt; cài APK thành công.
- Chưa so bản thu APK/SET thực. Chưa triển khai LastLoop ngoài chuỗi, variant GroupSection theo chord, cập nhật rhythmScale toàn cục bởi PAD trong mọi method, hoặc toàn bộ group DSP tail khác cấu hình. Chưa coi PAD hoàn chỉnh100%.

## Nhả tiếng khi PAD kết thúc hoặc thay player, 24-10-2026

- Đối chiếu Tracks.GroupPlayNext: khi đổi group, các track cũ gọi Channels.CloseAll(false). ReferenceRhythmPlayer không còn voices.clear tại cuối đoạn không lặp; đánh dấu finished, nhả nốt và tiếp tục render tail nếu caller gọi next. Không lập lịch vòng tiếp theo.
- ImportedPadBank khi nhận player thay thế khởi tạo vị trí mới trước, chuyển bus FX cùng cấu hình và các voice/tail cũ sang player mới bằng cơ chế releaseVoicesFrom đang dùng cho style, rồi mới đóng player cũ. Giữ giới hạn32 tail hiện có. Không sửa file SET.
- Kiểm tra PCM: cuối one-shot còn tiếng1.0 rồi0.9 và về0; thay PAD giá trị1 bằng2 cho tổng3 rồi2.9, cuối còn2 và slot vẫn chạy. Nhóm ReferenceRhythmPlayerTest/ReferenceStyleTransitionTest và assembleDebug đạt; đã cài APK lên thiết bị. Không tạo file test mới.
- Chưa so bản thu PAD trên APK gốc. Nhánh chuyển group FX khác cấu hình vẫn chưa giữ toàn bộ đuôi group DSP cũ; chưa bổ sung JumpGroup/LastLoop hoặc biến thể hợp âm PAD. Đây là sửa lỗi cắt tiếng, không xác nhận PAD tương đương hoàn toàn.

## Chọn PAD theo style, 24-10-2026

- Sửa trang PAD từng gom mọi group Section96..99 của SET rồi chia trang và gán slot theo thứ tự danh sách. Nay dùng cùng resolver với LCD: Section96+slot của style đang chọn, thiếu mới tìm parent0, theo MainActivity.y1 của APK.
- Giữ bốn vị trí PAD1..4; slot thiếu hiện EMPTY và vô hiệu hóa. Bỏ phân trang từng khiến PAD của style khác vào nút hiện tại. Không sửa dữ liệu SET.
- Build debug và nhóm ReferenceRhythmPlayerTest đạt, có kiểm tra group đảo thứ tự, slot khuyết, ưu tiên local/shared và không lấy style khác. Đã cài APK lên thiết bị. Chưa đối chiếu âm thanh PAD thực với APK; chưa bổ sung assignment tùy chọn hay chord variant PAD.

## Kết quả mới nhất — sample Loop Tempo, 24-10-2026

- Đối chiếu Channels.Process: fLoopTempo && InstSec.Loop==3 nhân pitch theo fTempo/120 và đặt _pitchSpeed0. ImportedInstrumentLoader nay giữ riêng tempoSync cho region Loop3, thay vì chỉ gộp chung cờ looping.
- Playback nhân bước đọc sample Loop3 với tempo/120 khi bật Loop Tempo; đổi BPM hoặc bật/tắt hiệu lực ngay tại vị trí đang phát, không reset phase. Region khác không nhân tempo. Nhánh Loop3 bỏ bend slew khi sync bật, giữ pitch-speed của các region thường.
- Engine đồng bộ tempo sample khi BPM thay đổi từ UI/style/MIDI/project; Cài đặt có Loop tempo và lưu rhythm_loop_tempo, mặc địnhtrue theo Music gốc. Global nội bộ mặc địnhfalse cho thư viện/tests không có Music context.
- Kiểm tra PCM đổi120->240: Loop3 tiến sample gấp đôi, loop thường giữ bước1; tắt sync tiếp tục ở phase hiện có với bước1. Các nhóm SampledInstrumentTest/ReferenceRhythmPlayerTest và kiểm tra loader loop3 cũ khi sync tắt đạt; build/cài APK thành công, không tạo file test mới.
- Chưa đối chiếu lượng tử pitch integer/tempo trên native hoặc bản thu SET thực; nhánh sample() đọc trực tiếp ngoài Playback không nhân tempo. Đây là thay đổi tốc độ/cao độ theo gốc, không phải time-stretch giữ pitch. Chưa kết luận toàn bộ Loop3 tương đương100%.

## Pitch Speed, 24-10-2026

- Đối chiếu Channels.Process: fPitchSpeed lấy từ Music, iPitch là đích, mỗi khối tính dPitchChangeValue=(target-current)/(size*speed). Mảng gốc có1/2/3/4/5/7/10/15/20/30/40/60/100 tương ứng Fast,20..600ms,Slow; Music mặc định2.
- Sampled Playback nay giữ bendFactor hiện tại và đích riêng, nội suy theo khối10ms và speed trước bước đọc sample. Nốt mới nhận bend ban đầu ngay; nốt đang ngân đi dần khi đổi hoặc nhả bend. Connection cập nhật base step mà vẫn dùng bendFactor đang chuyển. Scope cấu hình dùng chung sample channels như Music.
- Cài đặt có Pitch Speed, lưu pitchspeed và restore mặc định2. Giá trị0 chỉ dùng đường nội bộ/tests để giữ phát tức thời; UI dùng các giá trị gốc1..100.
- Kiểm tra PCM held-note ramp nằm giữa pitch cũ/mới, fresh note nhận bend ngay, trở center hữu hạn đạt cùng SampledInstrumentTest/ImportedScriptPlaybackTest/ReferenceRhythmPlayerTest; build/cài APK thành công. Không tạo file test mới.
- Khối10ms hiện quy đổi từ outputRate, chưa chứng minh khớp kích thước buffer/quantization/mixer branch của native APK trên từng thiết bị. Chưa áp fLoopTempo/Loop3 bypass hoặc smoothing toàn bộ pitchmove/keyconnection giống gốc; chưa có bản thu đối chiếu. Các mức ms là nhãn tham chiếu, không cam kết tuyến tính tới đích sau đúng số ms.

## MIDI bend sensitivity, 24-10-2026

- Khép giới hạn export bend range ở lượt trước: khi REC bắt đầu, ghi RPN0,0 + Data Entry MSB range/LSB cents0 cho16 kênh live, rồi null RPN và ghi raw bend đang giữ trước các nốt. Đổi range trong REC cũng capture bộ controller này.
- MidiFile giữ CC6/38/100/101 và NRPN98/99 theo thứ tự sự kiện; MidiSustain chuyển tiếp các sự kiện expression này. Playback có sensitivity riêng từng kênh (semitone+cents), reset mặc định2 mỗi lần mở song. Data Entry chỉ đổi range khi RPN0,0 đang được chọn; null RPN/NRPN không làm đổi nhầm range. Đổi range playback áp lại raw bend cho tiếng đang ngân, không thay trạng thái live.
- Kiểm tra export bằng MidiRecording rồi đọc MidiFile: RPN±7 được phục hồi trước note bent đầu tiên; kiểm tra range12.5, per-channel, null RPN và reset playback đạt cùng hồi quy MidiModulation/MidiIntegration/MidiSustain. Build/cài APK thành công; không tạo file test mới.
- Chưa phát/so file trên thiết bị MIDI hoặc DAW ngoài; không triển khai mọi RPN, increment/decrement Data Entry hay NRPN tùy hãng. Chưa có chứng cứ exporter APK gốc ghi RPN theo cùng cách; đây là bảo toàn thông tin range của ORGX, không tuyên bố format/event export giống100% gốc.

## Bend range và lock, 24-10-2026

- Đối chiếu mảng PitchbendRange/Value gốc1..12 và MainActivity.v1 kiểm tra f1 khi nhả pointer. Thêm Pitch bend ±1..±12 và Pitch bend lock trong Cài đặt, dùng preference pitchbend_range/pitchbend_lock.
- MidiPitchState giữ range live riêng; engine áp range mới cho channel live đang có tiếng và cho note-on sau đó. Raw scriptLevel vẫn là bend14bit-8192, trạng thái/range file playback giữ độc lập mặc định±2.
- Bánh BEND giữ vị trí sau nhả bình thường khi lock=true; RESET vẫn trả giữa. Khi cancel gesture/Activity pause/rời composition, vẫn trả giữa để tránh treo pitch (khác chính sách lock toàn cục gốc, cần đối chiếu thêm).
- Kiểm tra range12 cho raw max/min -> ±12, range1 -> +1, center ->0, scriptLevel giữ8191 và playback vẫn+2 đạt cùng PitchBendPolicyTest; build/cài APK thành công, không tạo file test mới.
- Chưa có pitchspeed/smoothing/range riêng controller; ghi MIDI chưa xuất RPN bend sensitivity nên phát lại file ngoài engine mặc định±2 không tái hiện range live khác2. Chưa có bản thu hoặc thao tác lock/range so APK gốc.

## Điều khiển biểu cảm cảm ứng, 24-10-2026

- Thêm PerformanceWheels mã ứng dụng cạnh bàn phím: BEND và MOD kéo dọc, giữ riêng pointer của mỗi điều khiển để không lấy thao tác phím đàn. Dùng midiPitchBend/midiModulation hiện có (channel0 live), đi qua đường voice/STS/script và MIDI recording của engine.
- BEND gửi giá trị14bit quanh8192 và trả giữa khi nhả, khi cancel gesture, khi Activity không còn cho nhập bàn phím hoặc khi rời composition. MOD gửi CC1 0..127, giữ mức sau nhả; RESET đưa bend/CC1 về giữa/0. Không nạp sample trong gesture.
- Bố trí cạnh trái keyboard, không dùng skill UI; build/cài APK đạt. Đã kéo hai điều khiển trên thiết bị và xem trạng thái BEND trả giữa, MOD giữ mức, RESET thao tác được. Không thêm test cho bố trí cảm ứng.
- Đối chiếu gốc có pitchbend_range/lock/pitchspeed, b1 hiển thị raw pitchbend quanh8192 và gesture RecordMusic53 reset1000; ORGX hiện dùng MIDI bend range mặc định±2 semitone, chưa nối các tùy chọn range/lock/speed/shape của APK. Chưa có bản thu chứng minh âm thanh/độ nhạy tương đương, chưa kiểm chứng đa chạm hai tay cùng lúc hoặc controller ngoài. Không coi giao diện biểu cảm này giống100% gốc.

## Chord Memory, 24-10-2026

- Đối chiếu Tracks.RecordKey: khi không nhận ra hợp âm và !fChordMemory, RecordChordStop -> GroupPlayChord(0); GroupPlayChord đóng channel melodic, Tracks bỏ Track.PlayNext melodic khi chord mask0. Music mặc định memory=true.
- Thêm Chord Memory trong trang CHORD và preference, phục hồi trước SET. Với bàn phím trái và memory=false, selectedSetChord trả trạng thái không hợp âm (-1) khi pressedChord rỗng thay vì dùng chord-memory/chordIndex cũ; AUTO và hợp âm chọn thủ công ngoài chế độ tay trái không đổi.
- Nhánh chord-follow của style/PAD nhận trạng thái này: đưa voice melodic vào release (cả note ngắn), giữ timeline đến khi hợp âm trở lại; không ảnh hưởng percussion. Loader không đưa -1 vào transposer hoặc cộng transpose làm thành nốt khác.
- Kiểm tra PCM: melodic tắt sau release, drums tiếp tục tới note thứ2, khi hợp âm trở lại timeline catch-up nốt dài còn hiệu lực; các nhóm ReferenceChordTransposerTest/ReferenceRhythmPlayerTest/KeyboardChordInputTest đạt. Build và cài APK thành công, không tạo file test mới.
- Chưa có bản thu đối chiếu gốc hoặc kiểm chứng pedal/controller ngoài, chưa tái tạo mọi nhánh ChordQuality/standalone chord voice. Chord label hiện vẫn có thể giữ tên đã nhận dù melodic ngừng; không coi toàn bộ cơ chế hợp âm hoàn thành.

## PAD trên mặt đàn, 24-10-2026

- Thêm PAD1..4 vào LCD màn hình chơi, giữ bàn phím và nút chuyển style truy cập được; không cần mở trang PAD để phát/dừng. Theo y1/GroupSection, chọn Section96+slot của parent style hiện tại trước, rồi parent0 nếu thiếu. Không tự lấy PAD của style khác làm mặc định.
- Mỗi nút nạp sample bằng IO rồi gửi prepared player tới bank đã có; bấm lần nữa khi chạy/chờ/nạp sẽ stop/cancel. Hiển thị PAD... khi nạp/chờ, tắt nút khi SET/style không có PAD tương ứng; lỗi nạp hiện thông báo.
- Bổ sung loadingMask dùng chung cho LCD/STOP: STOP có thể hủy cả nạp chưa tới mailbox. finishLoad chỉ xóa trạng thái nếu generation còn đúng, tránh completion cũ xóa trạng thái request mới. Trang PAD cũng gọi finishLoad cả khi nạp lỗi.
- Build/ReferenceRhythmPlayerTest đạt, cài APK thành công; đã xem màn hình trên điện thoại xác nhận bốn nút nằm trong LCD và không che phím đàn. Chưa kiểm chứng tiếng PAD SET thực/so bản thu APK trong lượt này. Không tạo file test mới.
- Assignment tùy chọn từ trang PAD chưa được giữ theo style hoặc lưu qua đóng app; chọn lại nút LCD vẫn dùng group của style/fallback. Các giới hạn đồng bộ/chord variants/recording đã ghi ở các mục trước vẫn còn.

## Dừng PAD, 24-10-2026

- Đối chiếu MainActivity.D1 -> RecordRhythmStop và k92 SyncStop gọi D1(v1?-1:0), v1 lưu chord_synchro_stop_pads, mặc định false. Thêm tùy chọn Synchro Stop: stop PADs trong CHORD; Sync Stop dừng PAD chỉ khi bật tùy chọn.
- Nút STOP biểu diễn trên console khi style hoặc PAD chạy/chờ; thao tác dừng performance đóng style và dừng tất cả PAD, chạy release tails thay vì cắt mixer. PAD chờ cửa sổ cũng bị hủy.
- Thêm token nạp theo slot: bắt đầu nạp lấy generation, stop/stop-all/đổi SET tăng generation. Player chuẩn bị xong sau STOP bị đóng và từ chối, không tự phát lại muộn. UI nạp IO chỉ chuyển player nếu token còn đúng.
- Kiểm tra token cũ sau stop-all bị từ chối và im lặng, token mới phát bình thường đạt cùng nhóm ReferenceRhythmPlayerTest/KeyboardChordInputTest; build/cài APK thành công. Không tạo file test mới.
- Chưa đối chiếu thao tác PAD SET thực hoặc toàn bộ RecordRhythmStop/playmode/pad tail với APK. Chưa có synchro trigger sound và recording PAD; không kết luận transport/PAD tương đương100%.

## Đồng bộ PAD/style, 24-10-2026

- Nối PAD SET vào clock/rhythmScale của session style đang chạy. Prepared PAD chờ cửa sổ ReferenceSectionTiming.inWindow, bắt đầu theo startOffset của GroupPlayStart và Rhythm Change Method hiện hành; không còn luôn phát từ đầu ngay lúc chọn.
- Khi không có style đang chạy, PAD dùng clock chung của bank; clock reset sau khi không còn slot chạy/chờ. Mỗi slot giữ riêng lệnh chờ; thay lệnh/dừng/đổi SET/đóng bank thu hồi cả player chờ, tránh phát muộn sau Cancel.
- Trang PAD hiển thị WAIT / CANCEL khi đã nạp và chờ nhịp. Chạm hủy được PAD chưa phát, không dừng slot khác.
- Kiểm tra timeline/PCM: request ở reference250 với scale1000 chưa phát; cửa sổ1000/method2 bắt đầu offset1000 của PAD dài2000, bỏ hit mở đầu đã hết hạn và phát đúng nốt61; cancel slot1 trước cửa sổ không phát muộn. Nhóm ReferenceRhythmPlayerTest/ReferenceSectionTimingTest/ReferenceStyleTransitionTest và build đạt; đã cài APK mới.
- Chưa kiểm chứng PAD SET thực và bản thu với APK gốc. PAD vẫn chưa thay đổi rhythmScale toàn cục như GroupPlayNext trong mọi method, chưa chuyển biến thể chord group, chưa lưu assignment, chưa tái tạo mọi quy tắc stop/pad temp-loop/recording. Không coi PAD hoàn thành100%.

## Dữ liệu PAD của SET, 24-10-2026

- Trước đây trang PAD chỉ có factory drum hits/effects. Nay liệt kê các group Section96..99 của SET đang mở, theo cùng quy ước MainActivity.y1 (section high nibble6), và nạp bằng ImportedStylePlayer với sample, track/group FX, tuning, script mode và chord-follow.
- Thêm ImportedPadBank mã ứng dụng: bốn slot player độc lập, chuyển player đã chuẩn bị qua atomic mailbox; nạp PCM trên IO, audio callback chỉ nhận player/render. Dừng PAD chạy release/FX tail, không dừng style chính hay slot khác; đổi SET dừng tất cả PAD. Đóng engine thu hồi cả player đang chạy lẫn đang chờ.
- Trang PAD có PLAY/STOP từng slot, STOP ALL và trang tiếp/trước khi nhiều group; factory hits/effects vẫn nằm dưới. Lỗi nạp hiện ở trang PAD. Chưa lưu assignment PAD qua mở app.
- Kiểm tra PCM hai slot cùng chạy tổng gain3, dừng slot0 còn gain2, stop-all hết tail về0 đạt cùng ReferenceRhythmPlayerTest; build/cài APK thành công. Không tạo file test mới. Chưa xác minh PAD trên SET thực/so bản thu APK trong lượt này.
- Giới hạn: chưa đồng bộ điểm bắt đầu với rhythmScale/style clock như GroupPlayAdd/Start; chưa dùng cơ chế GroupPlayChord chuyển biến thể PAD, temp-loop/stop pads/synchro sound và MIDI recording. Đây là đường phát PAD SET độc lập ban đầu, chưa tương đương toàn bộ PAD gốc.

## Mở mute và sự kiện bị lỡ, 24-10-2026

- Sửa giới hạn của lượt Chord/Drum Mute: đối chiếu Tracks điều kiện không gọi Track.PlayNext khi nhóm muted. ORGX nay giữ nguyên event cursor trong lúc performance mute, tiếp tục audio clock, tiếng đang ngân và FX.
- Khi unmute, ReferenceDrumTimeline xử lý các event overdue: lệnh volume/pan/pedal/note-off được thực thi, note dài còn trong khoảng hiệu lực được mở, note ngắn đã quá Length+10 bị bỏ theo Track.PlayNext. Không tự reset pattern hoặc đổi volume mixer.
- Kiểm tra PCM mute từ đầu: hit Length5 bị bỏ sau 41ms, volume25 được phục hồi trước nốt dài Length1000, chỉ một nốt phát gain .25; note-off tới trong mute không xử lý sớm nhưng được xử lý khi mở mute. Hồi quy ReferenceRhythmPlayerTest và ReferenceStyleTransitionTest/build đạt; APK đã cài.
- Chưa có bản thu đối chiếu native, chưa kiểm chứng đầy đủ khác biệt GroupPlayNext khi toàn bộ track mute và section không bounded. Các giới hạn factory rhythm/PAD/controller/recording của mute vẫn còn. Không tạo file test mới.

## Chord/Drum Mute, 24-10-2026

- Đối chiếu Tracks.GroupPlayAddChordMute/DrumMute và GroupPlayNext: lệnh toggle dùng hàng chờ group, thực thi ở cửa sổ rhythmScale. Thêm CHORD MUTE/DRUM MUTE trên console cho style SET; khi dừng toggle áp dụng ngay, khi chạy nằm trong cùng queue chuyển phần.
- Bộ phát phân nhóm theo melodic/percussion, chặn note-on mới của nhóm đã mute, không thay volume và không hard-cut tiếng đang ngân. Trạng thái giữ qua đổi phần/session style; thao tác vào session đang chờ nạp được lưu để áp sau khi chuyển.
- Kiểm tra queue chỉ toggle tại cửa sổ nhịp, không đổi current section; PCM xác nhận chord mute không chặn drum và existing voices vẫn ngân. ReferenceStyleTransitionTest/ReferenceRhythmPlayerTest và build đạt; APK đã cài. Không tạo file test mới.
- Giới hạn: native bỏ qua Track.PlayNext khi mute trong khi ORGX vẫn tiến timeline và bỏ note-on; khi mở mute, các event điều khiển/nốt dài bị bỏ lỡ có thể khác APK. Nút hiện chỉ dùng với style SET; chưa áp factory rhythm, recording event và controller ngoài. Chưa nghe/so bản thu gốc cho nhánh này.

## Synchro Start/Stop, 24-10-2026

- Đối chiếu vòng điều khiển k92: Sync Start khi group trống, có hợp âm đang nhấn hợp lệ và đủ ngưỡng phím; Sync Stop chỉ dừng sau khi đã có hợp âm đang nhấn rồi mất hợp âm đó. Mặc định Start=true/Stop=false theo Music.
- Thêm hai nút SYNC START/SYNC STOP trực tiếp trên console, lưu sync_start/sync_stop. Trang CHORD có ngưỡng Auto/2 keys/3 keys theo ChordSynchro gốc. Luồng này nhận phím tay trái, không dùng riêng tên hợp âm được nhớ làm tín hiệu start.
- KeyboardChordInput giữ pressedChord và heldKeyCount riêng với chord-memory. Engine nối note-on/off và thay đổi cấu hình nhận diện vào transport, chọn đúng session hiện tại hoặc đang chờ nạp; không phát factory rhythm khi có SET nhưng chưa chọn style.
- Kiểm tra ngưỡng ba phím, nhả còn hợp âm hợp lệ không dừng, nhả hết dừng một lần, chord-memory không tự start và Sync Stop không dừng playback thủ công trước khi có hợp âm đều đạt cùng hồi quy chuyển phần/chord PCM. Build cuối/cài thành công, không tạo file test mới.
- Đã thao tác trên thiết bị: đang READY, chạm phím tay trái ở ONE KEY với Sync Start bật -> RUNNING; bật Sync Stop rồi nhấn/nhả hợp âm -> READY, chord-memory vẫn hiện C M.
- Giới hạn: xử lý theo sự kiện bàn phím ORGX, chưa tái tạo timer polling k92 hoặc kiểm chứng MIDI controller ngoài. Chưa có tiếng synchro u1, chế độ dừng pads, recording/playmode exceptions và toàn bộ tương tác sync với PAD như gốc.

## Nhớ tempo từng style, 24-10-2026

- Hoàn thiện giới hạn của lượt chỉnh tempo trước: bộ nhớ tempo gắn với đối tượng index của SET đang mở và ID style, không gắn riêng session. Rời style/chọn lại hoặc nạp lại session đổi chế độ nhạc đệm vẫn lấy tempo đã chỉnh; mở index SET khác xóa bộ nhớ này.
- Chỉ ghi tempo style khi chưa khóa. Session mới phục hồi override kể cả lúc Tempo Lock bật, để khi mở khóa vẫn dùng tempo đã chỉnh trước đó. selectedTempo được dùng ở cả đường restore, LCD và trang SET, tránh UI ghi đè lại tempo gốc sau engine chọn session.
- Lệnh chỉnh tempo nhắm session đang chờ chọn nếu có; không sửa nhầm session cũ đang chờ bị thay. Cấu hình timing/lock được áp ngay khi đưa session vào hàng chờ.
- Build/các kiểm tra ReferenceStyleTransitionTest và ImportedStyleSessionTest đạt. Đã cài APK và kiểm tra trên điện thoại: DJ VIP KVT 1 chỉnh 141 -> 142 BPM, chọn DJ VIP KVT 2 rồi chọn lại DJ VIP KVT 1 vẫn hiện 142 BPM. SET gốc không bị sửa, không tạo file test mới.
- Bộ nhớ theo phiên mở SET, không lưu override của mọi style qua đóng ứng dụng; không triển khai chức năng ghi SET đã chỉnh. Không kết luận toàn bộ app đã tương đương APK gốc.

## Chỉnh tempo thủ công, 24-10-2026

- Đối chiếu MainActivity.a2: RecordMusic(Tempo), bật fTempoLock nếu rhythm_auto_tempolock, nếu chưa khóa thì cập nhật tempo các group Parent==style đang chọn.
- Thêm Auto tempo lock (mặc định false như r1 gốc). Nút +/- dùng BPM hiện tại của engine, đi qua setManualTempo; tự khóa được áp dụng trước khi chỉnh session. Lưu BPM và trạng thái khóa vào preferences.
- Session lưu tempo override trong bộ nhớ, chỉ áp dụng cho các phần thuộc style đang chọn (không đè group fallback Parent=0); giữ qua reset và chuyển Main/Fill. Khi khóa, chỉnh BPM không viết override lên tempo của phần. Không thay byte SET hoặc đối tượng ImportedStyleDefinition gốc.
- Xóa tempo-change cũ khi chỉnh thủ công; audio nhận tempo-change dưới cùng khóa với thao tác chỉnh để tránh ghi đè BPM bằng kết quả đã lấy từ trước. Các kiểm tra ReferenceStyleTransitionTest/ImportedStyleSessionTest đạt; build cuối và cài APK thành công. Không tạo file test mới.
- Giới hạn: tempo override thuộc session hiện tại, chưa giữ override riêng cho từng style khi bỏ chọn rồi nạp lại; cơ chế gốc sửa các group trong Music đang mở nên phạm vi lưu nhớ rộng hơn. Chưa đối chiếu thao tác/bản thu với APK gốc.

## Tempo và chuyển phần, 24-10-2026

- Nối Tempo Lock và Rhythm Change Method vào cài đặt, preference và engine. Bốn tên lựa chọn lấy từ mảng gốc: Auto/Don't wait/Wait for a cycle/Wait for a section; công thức ReferenceSectionTiming đã có trước nay được dùng theo lựa chọn thực.
- Cấu hình cập nhật session đang phát lẫn session chờ chọn dưới cùng khóa chuyển style. Đổi method không xóa queue, không reset đồng hồ; scale được tính lại. Khóa tempo xóa tempo-change còn chờ để tránh BPM nhảy muộn.
- Áp dụng cấu hình trước continueFrom khi audio nhận style mới. Các đường restore SET, chọn ở LCD, chọn ở trang SET và chọn factory style đều tôn trọng khóa tempo thay vì ghi đè BPM vô điều kiện.
- ReferenceStyleTransitionTest, ReferenceSectionTimingTest và ImportedStyleSessionTest đạt, gồm đổi sang chờ hết section khi đang có request và lock/unlock tempo. Build cuối/cài APK thành công. Chưa đo thời điểm chuyển bằng bản thu đối chiếu APK; chức năng này không có nghĩa toàn bộ transport đã tương đương gốc. Không triển khai Auto Tempo Lock trong lượt này.

## Chord Control, 24-10-2026

- Đối chiếu `Track.CalcChordKey`, `Tracks.RecordChord` và mảng `ChordControl`/`ChordControlValue` gốc. Nối bốn lựa chọn Manual/Auto/by Chord 1/by Chord 2 vào trang CHORD, lưu preference và phục hồi trước khi nạp SET/style. Giá trị nội bộ 4 vẫn được engine nhận theo nhánh mã gốc; giao diện chỉ hiện 0..3 như APK.
- Đường nạp style khi restore, chọn ở LCD và chọn trong trang SET đều truyền live ChordControl và octave-move vào transposer. Các nốt dài được cập nhật cả khi chỉ đổi Control hoặc quãng tám tay trái mà tên hợp âm giữ nguyên; original-notes và percussion không áp dụng.
- Octave-move lấy quãng tám của phím MIDI cao nhất trong bộ phím hợp âm đã nhận diện, theo RecordChord. Giữ cùng chord-memory sau nhả hết phím; reset xóa cả chord và octave. Với chọn hợp âm thủ công/AUTO hiện dùng octave-move 0.
- Kiểm tra PCM sample C4/C5 xác nhận by Chord 1 dùng octave lẻ, by Chord 2 dùng octave chẵn; đổi Control không đổi tên hợp âm vẫn có hiệu lực. Kiểm tra single-finger giữ/cập nhật octave theo phím cao nhất đạt cùng các nhóm nhận diện chord/rhythm và silent SET regression. AssembleDebug/cài/khởi động đạt; trên thiết bị đã mở trang CHORD, thấy đủ bốn lựa chọn, chọn Auto lưu chordControl=1 rồi trả Manual.
- ChordVars chỉ có trong layout >=1.4.9 theo TrackGroup.ReadFromStream; hai layout đang hỗ trợ 1.2.8/1.4.6 không chứa trường này. Chưa mở rộng parser cho layout mới hoặc tuyên bố đã dựng biến thể chord group. Chưa đối chiếu bản thu với APK gốc.

## Hợp âm đang ngân, 24-10-2026

- Đối chiếu `Tracks.GroupPlayChord`: khi hợp âm đổi, các channel melodic chưa nhả và Length >= 1000 được mở lại theo `Track.CalcChordKey`. Trước đó ORGX chỉ map note-on mới.
- ReferenceRhythmPlayer giữ key/length/touch của note-on gốc; khi targetChord đổi ở chế độ FULL/FOLLOW CHORD, map lại nốt dài đang giữ, mở Playback với sample đúng cao độ và cùng ID, tune, balance, pitchMove, pedal, expression. Không chạy lại script touch vì đường gốc gọi Channels trực tiếp. Voice cũ bị cắt để tránh chồng tiếng; note-off vẫn tìm theo ID gốc. Key Repeat không được coi là note-on dài của pattern.
- Nốt ngắn, percussion, nốt đang nhả và release-tail của phần trước không được đổi theo đường này. Chế độ original-notes không cài callback đổi hợp âm.
- Kiểm tra âm thanh mẫu xác nhận C major E chuyển thành C minor Eb (.75 -> .5 trên sample khác), chuyển về C major phục hồi sample, drum giữ nguyên; kiểm tra nốt dài + ngắn và note-off ID. Các nhóm ReferenceChordTransposerTest, ReferenceRhythmPlayerTest, ReferenceStyleTransitionTest đạt; assembleDebug/cài/khởi động thiết bị thành công. Không tạo file test mới.
- Chưa so bản thu với APK gốc: reset phase/envelope khi mở lại, lựa chọn biến thể group theo chord và ChordControl/octave-move vẫn cần đối chiếu đầy đủ. Không coi đây là hoàn thành toàn bộ cơ chế hợp âm gốc.

## SET/style, 24-10-2026

- Sửa lỗi khóa cả style khi một sound không có sample hoặc track không có vùng sample cho nốt yêu cầu. Đối chiếu `Channels.OpenKey`: i26=-1 không mở channel; các track khác vẫn phát. ORGX giữ sound rỗng ở trạng thái im lặng, không dùng sample mặc định thay thế. Loop/pitch/envelope không hợp lệ vẫn bị từ chối, nay có thông báo nêu track và tham số cụ thể.
- Giữ phần style không có track phát nốt (kể cả Ending im lặng) cùng timing/JumpGroup; không loại bỏ nút chỉ vì không có note-on.
- Đối chiếu `Tracks.PlayNext` và `GroupPlayNext`: lệnh chuyển phần đang chờ được nhận ở cửa sổ rhythmScale kế tiếp, kể cả Intro/Fill/Ending chưa hết. Không bắt chờ hết one-shot.
- Thao tác phần ngay sau chọn style được giữ cho session mới và áp dụng sau chuyển session; tránh gửi ID vào style cũ hoặc mất lệnh khi continueFrom xóa queue.
- Nút MODE trên LCD nạp lại style đang chọn, giữ trạng thái phát; chỉ lưu chế độ sau khi nạp thành công. Đồng bộ bankMode với trang SET và đánh dấu style được chọn trong danh sách.
- Kiểm chứng SET đang lưu trên thiết bị, SHA-256/tên ngân hàng `9d61373254d69b86801d127ff0bede20fc1cb68779ca852022510b732db84740`: 16 style, 200 phần, toàn bộ nạp và render 4800 frame/phần ở chế độ full/follow chord qua kiểm tra có sẵn. Backend Reverb JVM chỉ là adapter, không phải đối chiếu âm thanh native.
- Đã build/cài APK; thao tác trên thiết bị: chuyển DRUM → FULL → FOLLOW CHORD khi đang chạy, chọn DJ VIP KVT 2, chạy Fill 2, đổi sang DJ VIP KVT 1 vẫn RUNNING, Ending 1 kết thúc về READY. Các kiểm tra hồi quy chuyển phần, silent sound/Ending và sample mapping đạt. Không tạo file test mới.
- Còn cần đối chiếu bản thu/độ trễ và hợp âm với APK gốc; không kết luận 200 phần có âm thanh giống 100%. Bản sao SET tạm và log của lượt sửa đã được dọn; SET gốc không bị sửa.

| Cụm chức năng | Đã có trong ORGX | Còn thiếu / cần kiểm chứng |
|---|---|---|
| Đọc SET | Layout Music 1.2.8/1.4.6, instrument 1.2.8/1.4.2/1.4.6; PCM, group/track, mode/mix, system, header FX | Layout khác, STS thực thi, script/DNC; đọc đủ byte không đồng nghĩa dùng đủ dữ liệu |
| Style | Nhóm Intro/Main/Fill/Break/Ending, queue/jump, Auto Fill, tiếp tục khi đổi style, loop/release, đuôi Ending | So cùng thao tác với APK gốc; biến thể hợp âm ở layout mới, đầy đủ event điều khiển |
| Voice/sample | Sample mapping, pitch modes, ADS/release, mode layers, tune, tuning 24 phím, pan note | DNC connection/articulation, aftertouch đổi sound, pedal modes, thay pitch lúc nốt ngân |
| FX | DSP 1/2/4/6/7/8/9/10/16/17/20/21/23; module 29 dùng native gốc | 15 Pitch Shift, 31 Band Booster; lượng tử PCM, gain/threshold và bản thu đối chiếu |
| Sự kiện track | 1/2 note-off/on, 13/14 mute/unmute, 44/45 channel volume/pan | Các event khác vẫn bị từ chối; 101 embedded wave chưa có |
| Giao diện | Console và các trang chọn style/voice trong LCD | Chưa có bằng chứng từng màn hình/thao tác giống 100%; không coi screenshot/build là kiểm chứng chức năng |
| Ghi/xuất/MIDI/project | Có lớp triển khai trong project | Cần kiểm tra end-to-end và bảng khác biệt với gốc; không mặc định hoàn thành |
| Độ ổn định | Kiểm thử JVM, một số instrumentation native Reverb, cài/khởi động thiết bị | Chưa đo polyphony/latency/CPU/bộ nhớ dài hạn, chưa so toàn bộ bản thu |

## Cụm tiếp theo: voice biểu cảm

Ưu tiên đọc trọn đường `Channels.OpenKey`, `ChangeKey`, DNC Effects và mode Aftertouch trước khi thêm hành vi. Tách rõ:

1. Áp aftertouch lên nốt đang ngân và lựa chọn sound/layer theo mode.
2. DNC connection, articulation, pedal và các điều kiện kích hoạt.
3. Script runtime: ngôn ngữ, biến/event, giới hạn thực thi và tác động lên engine.

Một cụm chỉ được ghi hoàn thành khi có đường phát/UI sử dụng được, kiểm thử hành vi và đối chiếu APK gốc. Nếu mới dựng adapter hoặc đọc trường dữ liệu thì ghi đúng mức đó. Những lỗi tái hiện được vẫn phải sửa, nhưng không dùng các bản vá nhỏ để thay cho tiến độ cụm này.

## Cách kiểm tra lại danh sách đã triển khai

Chạy `py -3 analysis/rebuild_status.py` để tạo [REBUILD-STATUS.json](REBUILD-STATUS.json) từ khai báo support trong mã. Script dừng nếu không còn nhận diện được khai báo; không tự suy đoán hỗ trợ. Báo cáo này không xác minh DSP hay hành vi trên thiết bị.

Aftertouch fallback đã được nối end-to-end theo `Track` gốc: áp lực dương đầu tiên chọn sound Aftertouch, giữ velocity ban đầu và không khởi động lại ở các pressure tiếp theo. ScriptText của mode nay được giữ lại khi đọc SET; DNC và runtime script tùy biến vẫn cần bộ thực thi lệnh.
Key Connection đã bổ sung điều kiện restart AutoRelease/loop theo low nibble của `Channels._Connection`; test PCM kiểm tra mode 0/1/3 và low mode mở voice từ loop point.

## Script mode: phạm vi thực thi hiện tại

Đã trích 24 script tham chiếu từ `assets/data.zip`. Bộ thực thi hiện xử lý touch dạng chuỗi lệnh `play`/`return`, số nguyên và biến cộng/trừ hằng số. Playback dùng chung cho voice trực tiếp và STS, hỗ trợ `key`, `keymove`, `octavemove`, `volume`, `pan`, `tune`, `pitchmove`, `connectkey`, `connectspeed`. Bốn script Octave/Legato Lower/Upper gốc đã được đưa qua test PCM; pitchmove giữ hiệu lực khi bend live thay đổi.

Đã truyền context velocity cho voice trực tiếp, STS, Mix layer và sound Aftertouch. Lệnh play có velocity dùng tỷ lệ so với lực đánh đầu vào vì mixer đã áp lực đánh ở ngoài Playback; test giữ nguyên velocity và override giá trị đều đạt. Chưa đối chiếu lượng tử hóa lực đánh với bản thu APK.

Script play đã nối autorelease và attack/decay/sustain/release xuống envelope từng Mix layer; giá trị không chỉ định giữ envelope gốc. Test hai voice cùng instrument xác nhận release một voice không đổi state voice kia. Bộ kiểm thử đầy đủ 437 test đạt, build/cài APK thành công; chưa đối chiếu bản thu envelope APK gốc.

Chưa có biến trạng thái/main, if/else, timer, script aftertouch/release và exit đổi mode. Lựa chọn sound tùy lệnh và fallback khi lỗi vẫn cần đối chiếu/hoàn thiện. Không coi toàn bộ DNC hoặc script Legato đã tương đương APK. Những script ngoài tập con hỗ trợ hiện dùng fallback.

Cập nhật session: đã nối main khởi tạo một lần, biến trạng thái giữa các touch, phép gán, so sánh và điều kiện một lệnh với &&/||/!. Trạng thái chỉ commit khi kế hoạch âm thanh hợp lệ. Chưa có block if/else, timer, effect assignment hoặc release exit đổi mode; ghi chú trước về thiếu main/trạng thái đã được thay thế ở phạm vi này. Kiểm thử toàn bộ 447 test đạt; APK đã build, cài và mở thành công.

Cập nhật event: release cập nhật biến một lần khi note-off; aftertouch nhận velocity áp lực và keyvelocity ban đầu, xử lý script trước fallback. Return tính biểu thức và coi mọi kết quả khác 0 là handled. Release chưa hỗ trợ chặn nhả/phát nốt/exit; aftertouch chưa hỗ trợ play hoặc effect assignment. Toàn bộ 450 test đạt, APK mới đã build/cài và nhận lệnh mở MainActivity thành công. Chưa xác minh nghe/so bản thu APK gốc.

Cập nhật điều kiện: đã có block if lồng nhau và if-block/else-block, return trong block kết thúc event. Test PCM xác nhận velocity chọn nhánh gain khác nhau trong voice thật. Chưa có đầy đủ if đơn lồng nhau/else-if, timer và effect assignment. Toàn bộ 453 test đạt; APK đã build/cài và mở thành công.

## Modulation và script: cập nhật phạm vi

Đã thay bộ tách câu lệnh để hỗ trợ điều kiện đơn lồng nhau và else; nhánh touch của Legato from Last gốc đã được kiểm tra. Dynamic Legato (Velocity) và Aftertouch Shake gốc đã chạy qua voice/PCM; script ghi và đọc thuộc tính effect 0..3 ở touch, cập nhật Pitch/Volume Shake trong aftertouch/release. Chưa có timer hoặc exit đổi mode. Time hiện là mili giây từ lúc tạo session, khác epoch double của APK.

Modulation Shake gốc đã chạy qua API modulate. MIDI file giữ CC1, engine gửi theo kênh; STS và voice mới nhận mức đã chọn. CC121 và kết thúc/STOP playback reset modulation. Ghi MIDI giữ mức CC1 trước REC và ghi reset ở cuối file. Bộ nhận controller MIDI phần cứng chưa được nối, chưa thử MIDI thật; không coi API là kết nối thiết bị hoàn chỉnh.

Trang Cài đặt đã có điều khiển DNC từ khai báo slider của tập con script hỗ trợ. Thay đổi qua audio queue, kiểm tra sound hiện hành và giới hạn; chưa xác minh tương đương giao diện gốc hoặc đầy đủ nhóm tiếng STS. Các ghi chú lịch sử phía trên về thiếu gán effect/slider được thay thế trong phạm vi đã nêu ở đây.

## Vòng đời điều khiển DNC

Pedal cập nhật session khi im lặng và truyền hiệu ứng cho nốt mở dưới pedal đang giữ. Các voice dùng chung session không chạy lặp phép gán pedal/modulation cho cùng trạng thái; kết quả dùng lại đọc giá trị Effect hiện hành để tránh ghi đè thay đổi từ aftertouch. Việc chia sẻ session giữa part/kênh vẫn cần đối chiếu với Track/Mode của APK gốc.

Event pitchbend đã nối trước bend mặc định: script xử lý thành công và trả khác 0 thì chặn fallback. Giá trị MIDI gốc trừ 8192 truyền tới nốt đang ngân và nốt mới của STS/voice trực tiếp; trạng thái live và playback được giữ riêng theo kênh. Chưa kiểm chứng controller vật lý hoặc toàn bộ thứ tự event khi nhiều nốt dùng chung session.

Script quá 262144 ký tự hoặc ngoặc lồng quá 64 cấp dùng fallback để tránh tràn stack; đây là giới hạn ORGX. Runtime vẫn dùng số nguyên và elapsed time, chưa tương đương double/epoch của APK. Timer, lịch thực thi, exit đổi mode và các lệnh phát âm trong aftertouch/release chưa hoàn chỉnh. Các bản build/test không chứng minh tương đương 100%.

Chuyển sang sound Aftertouch đã truyền raw MIDI bend, modulation đang giữ và pedal đang giữ; các cập nhật modulation/pedal tiếp theo cũng đến voice thay thế. Test PCM/session kiểm tra giá trị giữ trước chuyển sound, reset sau chuyển và chống chạy lặp pedal. Chưa kiểm chứng controller vật lý hoặc so âm thanh với APK gốc; đây là hoàn thiện vòng đời điều khiển, không phải dựng xong toàn DNC.

Khởi tạo script: loader nạp Effect trước khi tạo session để main đọc được thông số SET; các phép gán Effect trong main được giữ khi cấu hình mode được đồng bộ. Test kiểm tra đọc Speed 65 và gán lại 75 từ biểu thức, cùng trường hợp gán hằng số qua hai thứ tự cấu hình. Main chưa hỗ trợ các lệnh phát âm, timer hoặc đổi mode; không coi các nhánh đó đã dựng.

Legato tái sử dụng: khi StopByRelease tắt, voice còn đuôi release có thể làm nguồn nối. Với sound không AutoRelease, kết nối hủy release cũ trong envelope và giữ vị trí PCM; quyền release script chuyển sang phím mới. Test PCM xác nhận giữ vị trí mẫu và tiếp tục ngân sau thời gian release cũ. Chưa đối chiếu đường cong gain/ADS bằng bản thu APK; timer và exit đổi mode vẫn thiếu.

Cập nhật vòng đời voice: context pedal ban đầu được truyền tới STS, voice trực tiếp, Mix và sound Aftertouch. Sau chuyển Legato, sound Aftertouch và key trong event release/aftertouch theo phím hiện hành. Chuyển Aftertouch giữ các lớp octave/gain và pitchmove của script. Key Repeat nội bộ bỏ qua script touch, cả trong Mix layer, tránh tăng biến/sinh thêm lớp ngoài ý muốn. Toàn bộ 506 test đạt; APK đã build/cài và khởi động lạnh thành công. Đây chưa là đối chiếu âm thanh APK gốc; timer và exit đổi mode vẫn chưa được dựng xong.

Bộ đọc thiết lập DNC: đối chiếu nhánh setting trong InstMode.ScriptEvent của APK gốc (tham số được tra theo tên). ORGX nhận text/slider/toggle với name/title/reset/from/to đảo thứ tự và khoảng trắng; giữ thứ tự khai báo các điều khiển, nhận dấu phẩy trong tiêu đề, kiểm tra tham số trùng và phạm vi. Build APK và các nhóm ImportedScriptSettingsTest, ImportedSimpleScriptTest, ImportedScriptPlaybackTest đạt. APK đã cài lên thiết bị. Chưa hỗ trợ đủ setting volume, biểu thức/double trong tham số, timer hoặc exit; chưa xác nhận tương đương toàn bộ DNC trên thiết bị.
Thiết lập DNC volume và thứ tự giao diện: đối chiếu InstMode.java nhánh setting và vb2.java/tb2.java của APK gốc. volume và slider dùng thanh kéo; mặc định phạm vi khi không khai báo là 0..100. ORGX đã hỗ trợ volume, giữ tiêu đề xen kẽ điều khiển, dùng thanh kéo và nút RESET cho cả thanh kéo/công tắc. Giá trị vẫn chuyển qua hàng đợi audio và chỉ cập nhật sau khi được chấp nhận. Build và ba nhóm kiểm tra settings/interpreter/playback đạt; APK đã cài. Chưa kiểm chứng trực quan trang DNC với SET thực trên thiết bị; chưa hỗ trợ toàn bộ tham số biểu thức/double, timer và exit.
Khôi phục thiết lập DNC lưu trong SET: phát hiện bộ đọc trước đây bỏ cả hai mảng ScriptSetting của Instrument.ReadFromStream (layout 1.4.6). Đã giữ cặp tên/giá trị trong ImportedMode, chuyển qua ImportedInstrumentLoader vào session trước main; metadata bắt đầu # không trở thành biến thực thi. Giá trị lưu được dùng cho script và giao diện, còn RESET lấy mặc định khai báo. Kiểm tra session/loader xác nhận giá trị lưu 35.0, toggle false, effect main và RESET 80; các nhóm mode/settings/interpreter/playback/SET đạt, assembleDebug thành công. Chưa lưu thay đổi DNC của người dùng qua lần đóng ứng dụng, chưa hoàn thiện timer/exit và biểu thức double; không coi đây là tương đương APK 100%.
Lưu thiết lập DNC của voice được chọn: thêm SharedPreferences orgx-dnc, khóa SHA-256 tách theo đường dẫn/phiên bản file SET, voice, mode, tham chiếu sound và script. Ba đường chọn/khôi phục voice trực tiếp đọc giá trị người dùng, ưu tiên hơn ScriptSetting trong SET; RESET vẫn theo script. Chỉ lưu sau khi audio queue chấp nhận, ghi preferences từ coroutine UI (apply ghi đĩa bất đồng bộ), không ghi vào SET. Kiểm tra loader xác nhận ưu tiên giá trị 42 thay vì 35 trong SET, RESET 80 và tách khóa giữa mode; build và nhóm mode/settings/playback đạt. Giới hạn: chưa nối override cục bộ vào đường STS/style, chưa kiểm chứng thao tác DNC rồi khởi động lại trên thiết bị; timer/exit vẫn thiếu.
Nối DNC override vào STS/style: provider đọc thiết lập cục bộ đi qua STS đơn, batch đầy đủ, batch có change mask và các đường chọn/khôi phục style. Instrument được gắn sourceIdentity theo vị trí trong SET để alias hiển thị VOICE / MODE và tên gốc trong STS/style dùng chung khóa. Đọc tương thích khóa tên hiển thị của phiên bản trước. Kiểm tra bổ sung xác nhận STS nhận 42 thay vì 35 từ SET, RESET 80; style nhận level 50 làm mức PCM bằng một nửa mặc định. Chưa xác nhận giao diện chỉnh từng part STS hoặc vòng đời lưu qua khởi động lại bằng thao tác trên thiết bị; timer/exit chưa hoàn thiện.
Kết quả: 46 kiểm tra STS/style/mode đạt (0 lỗi), gồm render SET mẫu; sau điều chỉnh đọc khóa cũ, build lại và hai kiểm tra tích hợp DNC mới tiếp tục đạt. Đã cài APK mới lên thiết bị.

DNC Play chọn Sound: đối chiếu InstMode.ScriptCallback (play mặc định InstMode.Sound, tham số sound ghi đè; Channels.OpenKey mở sound cụ thể). Loader chuẩn bị các sound dùng cho script trước playback, giữ giới hạn 64 MB; nếu script có tham số sound động thì chuẩn bị các sound hỗ trợ trong instrument. Playback định tuyến từng lệnh play tới PCM riêng, mặc định dùng primary thay vì nhân cả Mix. Mix vẫn áp dụng cho fallback/phím thường. Script sound không tồn tại bị từ chối trước khi commit biến/event. Kiểm tra PCM xác nhận sound 1 = 0.5, primary = 0.25, Mix thường = 0.75; thay slider chọn sound có tác dụng, sound 99 báo fallback. Build và các nhóm script playback/mode/STS loader cùng DNC style đạt. Chưa hoàn thiện timer, exit và chọn instrument/system trong Play; các script Move có timer chưa chạy trọn. Nạp sound script có thể tăng bộ nhớ và vẫn chịu giới hạn ORGX 64 MB; chưa so âm thanh với bản thu APK gốc.
Timer DNC đã nối tới phát âm: đối chiếu il3 case timer/break, d34 đăng ký/hủy và Music.ScriptTimer. Timer mới thay timer cũ theo session; giữ tham số touch, chia sẻ biến script, clamp khoảng lặp 0..5000 ms, break kết thúc. ORGX dùng thời gian theo frame audio của Playback sở hữu timer (0 ms được chạy tối thiểu 1 ms), mỗi lần poll chạy nhiều nhất một callback, không bù dồn. Callback tạo các voice Play thật, giữ bend/balance, áp dụng effect, giữ Playback rỗng sống trong lúc chờ; voice cũ không chiếm timer mới. Giới hạn ORGX 128 voice con, callback lỗi dừng timer và không commit biến. Forced release hủy timer; release script chưa hỗ trợ sẽ fallback và hủy timer. Kiểm tra script nguyên bản Move from Upper xác nhận ba lần phát sau 25/50/75 ms khi giữ phím, dừng vòng lặp bằng break, cùng thay timer và giữ tham số touch. 103 kiểm tra script/playback/mode/settings đạt, assembleDebug đạt. Chưa tái dựng exit chuyển mode, nên nhả phím của Move chưa tương đương APK. Timer ở main/aftertouch/modulation chưa hỗ trợ; lịch frame khác bộ hẹn giờ wall clock APK và chưa so trên thiết bị với bản thu gốc.
DNC exit về mode 0: đối chiếu InstMode.ScriptCallback exit, Track.Play case 61 và Channels.ModeSet. exit được tạo thành yêu cầu chuyển mode, không cắt việc chạy các câu lệnh tiếp theo; mode không có trong context bị từ chối trước commit biến. Instrument giữ defaultMode/selectedMode qua chọn VOICE / MODE và STS. Khi mode khác 0 có exit, loader chuẩn bị thêm mode 0 trước playback (tổng ngân sách PCM bảo thủ 64 MB), không đọc file trong callback. Nốt mới, keyboard mapping, sample API, DNC controls và mixer chuyển sang context mode 0; nốt cũ giữ Playback riêng. UI/queue chặn sửa slider đã thuộc mode cũ. Kiểm tra dùng script Move from Upper nguyên bản xác nhận release chuyển mode 0, nốt tiếp theo dùng sound đích, transpose +12 và mixer volume 50; exit vẫn thực hiện flag=false, đích không có không commit flag=99. Giới hạn: mới chuẩn bị đích mặc định 0 và mode hiện tại, chưa chuyển tự do sang mọi mode; override DNC cục bộ của mode đích chưa được nạp khi chuẩn bị exit (vẫn dùng giá trị SET). Thứ tự hiệu ứng ngay trong một callback và FX tail khi đổi mode chưa xác minh với APK; timer vẫn chạy theo frame của Playback, chưa phải bộ hẹn giờ toàn Music.
Kết quả: assembleDebug đạt, toàn bộ 521 kiểm tra đạt (0 lỗi), gồm kiểm tra render SET mẫu. Đã cài APK mới. Chưa kiểm chứng thao tác Move/exit trên thiết bị so với APK; tên voice hiển thị/lựa chọn khôi phục sau mở lại chưa đồng bộ theo exit nội bộ.

Đồng bộ lựa chọn sau exit: giữ sourceName của instrument qua alias VOICE / MODE, gắn Mode selection vào từng context SampledInstrument. UI quan sát mode đang phát và cập nhật tên voice/lựa chọn lưu khi đó là voice trực tiếp cùng file SET; không ghi đè đường khôi phục STS. Mixer đọc lại tên đang chọn. Loader nhận provider thiết lập cho mode đích, dùng override cục bộ khi chuẩn bị mode 0; các đường voice/STS/style đều chuyển provider. Kiểm tra Move release xác nhận tên Voice / MODE Default khớp mục trong catalog để khôi phục, không đồng bộ khi khác SET hoặc không có lựa chọn voice trực tiếp, DNC level 40 và RESET 100 của mode đích, PCM 0.2 và mixer 0.1. Build cùng nhóm script playback/mode/STS loader/DNC style đạt. Chưa kiểm chứng UI và khôi phục sau force-stop bằng thao tác trên thiết bị; STS vẫn khôi phục snapshot gốc, chưa lưu mode nội bộ từng part. Vẫn chỉ chuẩn bị exit về mode 0 hoặc giữ mode hiện tại.
STS giữ trạng thái mode thực sau DNC exit: ImportedStsLoader.Prepared.currentSnapshot đọc modeSelection hiện hành thay cho snapshot ban đầu khi mode đã đổi, lấy raw channel settings/effects của mode đích (tránh cộng global pitch lần hai). ImportedStsRequest dùng snapshot hiện hành từng part khi chuẩn bị STS với change mask. Batch giữ basisSnapshots và kiểm tra lại trước enqueue/commit audio; nếu script đổi mode trong lúc đọc PCM thì từ chối batch cũ. Kiểm tra hai part xác nhận Right exit về mode 0/volume 45, Left giữ mode 1, mask giữ mode không đưa Right lại mode 1, batch chuẩn bị trước exit không còn hợp lệ. AssembleDebug và nhóm ImportedSts*Test cùng ImportedScriptPlaybackTest đạt. Chưa lưu trạng thái mode từng part qua đóng/mở ứng dụng; đây là sửa chuyển STS trong phiên hiện tại. Chưa so thao tác trên thiết bị với APK gốc.
Khôi phục mode từng part STS qua phiên ứng dụng: thêm ImportedStsModeState mã hóa map vị trí part -> mode, gắn file SET (tên, kích thước, mtime) và slot. Khi STS Master/group hiện hành trùng lựa chọn lưu, UI lưu map thay đổi vào preferences riêng; khôi phục lúc khởi động chuẩn bị PCM đầy đủ với mode đã lưu. Nếu mode khác snapshot gốc, lấy settings/effects raw của mode đích; giữ nguyên định nghĩa STS trong SET. Dữ liệu sai slot/phiên bản file/trùng part/mode không có fallback về snapshot. Kiểm tra encode/decode + loader xác nhận Right mode 0/volume 45, Left mode 1, slot khác/trùng part/SET thay đổi bị bỏ, đích 99 fallback mode 1. Build và các nhóm STS/DNC playback đạt; đã cài APK mới. Giới hạn: hiện chỉ STS Master nhiều part; STS đơn vẫn khôi phục snapshot gốc. Lưu theo chu kỳ UI 100 ms và SharedPreferences.apply; tắt tiến trình ngay sau exit có thể chưa lưu. Chưa xác nhận thao tác thực trên điện thoại rồi force-stop/khôi phục, và chưa lưu mọi thay đổi channel/FX giữa phiên.
Khôi phục mode STS đơn: AudioEngine giữ Prepared của STS đơn đã áp dụng thành công, xóa khi đổi sound/SET hoặc chuyển sang STS group. UI lưu currentSnapshot mode kèm track/instrument/file revision/slot vào khóa riêng; startup chỉ khôi phục nếu các định danh trùng, nạp settings/effects của mode đích trước load. Kiểm tra chọn STS mode Move, release exit, encode/decode và nạp lại mode 0/volume 45 đạt; dữ liệu khác track, instrument, slot hoặc SET thay đổi giữ snapshot gốc. AssembleDebug và nhóm STS cùng DNC playback đạt. Chưa kiểm chứng quy trình thao tác rồi đóng/mở trên thiết bị; lưu vẫn theo chu kỳ UI 100 ms và preferences.apply nên dừng tiến trình ngay lập tức có thể mất thay đổi mới nhất. Không ghi vào SET nguồn.
Context DNC của nốt style: đối chiếu Track._scriptTouch lấy velocity từ Touch & 65535 và pedal từ Track. ReferenceRhythmPlayer trước đây mở Playback với scriptVelocity mặc định 100 và initialScriptPedal false; đã truyền velocity đóng gói thực và pedalDown trước touch, đồng bộ pedal session/voice qua event 12/11. Sửa tác động các nhánh DNC theo velocity/pedal trong style; không thay đổi dữ liệu SET. Kiểm tra PCM velocity 50 -> 0.0625, velocity 120 -> 0.3; pedal callback bật đúng một lần, tắt cập nhật biến dùng cho touch sau đó. AssembleDebug và nhóm ReferenceRhythmPlayerTest/ImportedScriptPlaybackTest/StylePitchShakeIntegrationTest đạt. Đã xem screenshot trực tiếp thiết bị (analysis/previews/orgx-current.png): giao diện keyboard, style DJ SAY / MAIN 1, voice Soprano Choir DNC hiển thị. Chưa xác nhận âm thanh thực hoặc so bản thu với APK. Event context còn thiếu id/instrument/system/balance và một số lệnh DNC; không coi DNC đã tương đương hoàn toàn.
Context ID/balance DNC: đối chiếu Track._scriptTouch truyền id và balance=Touch>>16, release/aftertouch giữ id. Thêm scriptId cho Playback, style truyền event.id thay vì suy ra từ cao độ; touch nhận balance là pan per-note đã giải mã. ID được giữ qua layer, script child, fallback, timer và sound aftertouch; release/aftertouch có cùng ID. Với keyboard trực tiếp chưa có nguồn ID riêng, mặc định là số phím trong context. Kiểm tra style id 37/balance -20 chọn đúng nhánh và stereo PCM 0.125/0.1; timer phát theo id 37 và release return(id==37) hoạt động. AssembleDebug và nhóm rhythm/script playback/mode đạt. Chưa hỗ trợ play(id), stop(id), biến dotted/switch của script Modulation Octave, nên bổ sung context này chưa làm script đó chạy đầy đủ. Các context instrument/system/volume/pan/tune/pitchmove vẫn chưa đầy đủ như APK.
DNC switch và biến dotted: đối chiếu il3 case switch (giá trị, danh sách case, khoảng mở/đóng) và script gốc Octave/Modulation Octave.Script. Bộ đọc hỗ trợ switch/case/default, nhiều giá trị cách dấu phẩy, khoảng a..b/a../..b, fallthrough và break dừng switch gần nhất; break trong switch nằm trong timer không dừng timer. Gán/đọc biến dạng touch.id/touch.key/touch.count đã hoạt động; sửa nhận dạng keyword để tên có dấu chấm/underscore không bị coi là if/timer/switch. Kiểm tra script nguyên bản (thêm event quan sát) xác nhận main khởi tạo, touch cập nhật key/count 2 rồi release xuống 1/0. Kiểm tra biên level 10/9/0/-9/-10/127 và break/fallthrough đạt. AssembleDebug cùng nhóm interpreter/playback/mode đạt; APK đã cài. Chưa hỗ trợ stop(id), play(id) và thực thi lệnh audio trong modulate; vì vậy Modulation Octave chưa đổi octave đầy đủ bằng controller. Chưa kiểm chứng trực tiếp controller vật lý trên thiết bị.
Modulation Octave có đường audio: ImportedSimpleScript tạo danh sách lệnh audio có thứ tự, nhận Play(id) và Stop(id[,cut]) trong modulate. Playback nhận plan modulation dùng chung session nhưng chỉ áp dụng lệnh cho ID thuộc nốt của nó; stop release/cut voice con, play tạo child mới, giữ bend/balance và gain/velocity; không chạy lại khi cùng level được gửi lặp. Validate lại kết quả modulation cached trước khi áp dụng; updateScriptModulation cấp sound không commit plan có audio để Playback xử lý. Touch/timer Play tới ID khác bị từ chối (chưa có channel scheduler tổng quát); Stop ngoài modulate chưa hỗ trợ. Kiểm tra file Octave/Modulation Octave.Script nguyên bản với 2 nốt: chỉ nốt mới nhất ID38 restart ở octave +1, ID37 giữ pitch/position; trả level0 phục hồi octave, gửi level lặp không nhân voice. Kiểm tra Play rồi Stop(cut) giữ đúng thứ tự và kết thúc tiếng. AssembleDebug, nhóm script interpreter/playback/mode và keyboard/STS loader/MIDI modulation/rhythm đạt. Chưa kiểm chứng controller vật lý; không có Play tạo nốt mới khi không còn Playback sở hữu ID, stopall/change/playing và audio ở event khác chưa hỗ trợ. Chưa chứng minh tương đương mọi kịch bản polyphony với APK.
Playing trong DNC: đối chiếu InstMode callback playing và Channels.Count(boolean): Playing() loại channel đã Close, Playing(released) gồm tiếng còn đuôi. Thêm registry weak Playback dùng chung qua layer/script sound/aftertouch/các mode exit, đếm leaf phát sample thay vì wrapper, loại tiếng finished/cut. Cung cấp count thực cho event/timer qua session, bộ biểu thức nhận Playing()/Playing(released). Kiểm tra Dynamic Legato (Near).Script nguyên bản: không có tiếng thì fallback, có tiếng và 600ms/khoảng 4 phím thì Play(connectkey:60), hết tiếng thì không nối. Kiểm tra PCM đếm 2 layer riêng và tiếng release còn ngân. AssembleDebug, nhóm interpreter/playback/mode và keyboard/STS loader/rhythm đạt; APK đã cài. Giới hạn: registry thuộc context SampledInstrument, các Track dùng chung instance có thể dùng chung count trong khi APK đếm theo Channels; trạng thái released cập nhật theo lần render; chưa kiểm chứng thực tế Dynamic Legato trên điện thoại với APK. Không coi toàn bộ script gốc đã hỗ trợ (Dynamic Legato Speed còn biểu thức đơn = và runtime double/epoch chưa tương đương).
Stereo DNC Play: đối chiếu InstMode Play callback (balance mặc định0 được pack cùng velocity, pan mặc định Track.KeyBalance) và Channels.OpenKey cộng per-note balance vào pan. Interpreter nhận play(balance). Script child dùng balance riêng thay vì lấy pan nốt chạm cộng với play.pan; play.pan được giữ thành override của child, không bị channelBalance ghi đè sau tạo, còn Play không ghi pan vẫn theo channel pan. Override được truyền qua Mix child và aftertouch nhận giá trị pan hiệu lực. Kiểm tra PCM nốt chạm balance80 + Play(balance20,pan-40) cho L/R .25/.2, cập nhật pan70/-100 không đổi override; Play() mặc định centered và theo channel -40/+40 cho .25/.15 rồi .15/.25. Kiểm tra style ID/balance đổi thành play(balance:balance) để bảo toàn balance event một cách tường minh như callback gốc. AssembleDebug và các nhóm script interpreter/playback/rhythm/keyboard parts đạt; APK đã cài. Chưa so stereo với bản thu APK thực, giới hạn clamp -100..100 và một số context Play còn khác APK; không coi toàn bộ routing DSP tương đương.
DNC Play pedal: đối chiếu InstMode Play callback pedal mặc định1, Channels.OpenKey/Open truyền Pedal và Channels.Pedal cập nhật các channel đang mở. Interpreter nhận play(pedal:...), script child giữ trạng thái pedal ban đầu (mặc định1) và cập nhật khi scriptPedal gọi sau đó; truyền qua Mix layer. Envelope, connection và shake dùng trạng thái hiệu lực của child. Kiểm tra explicit pedal1 và mặc định giữ release dài, pedal0 release nhanh, pedal-off chuyển sang release nhanh. Các kiểm tra ADS cần release nhanh đã ghi rõ pedal0 để không dựa vào mặc định sai trước đây. Build và nhóm script playback/interpreter/keyboard parts/rhythm đạt. Đang kiểm tra toàn bộ suite; chưa so đường cong release bằng bản thu APK thực. Pedal mode của Track và callback điều khiển vật lý vẫn cần kiểm chứng trên thiết bị.
Kết quả cuối: toàn bộ 537 kiểm tra đạt (0 lỗi), gồm render style SET mẫu; assembleDebug đạt, APK đã cài lại lên thiết bị.

DNC Play velocity mặc định: đối chiếu InstMode callback Play dùng velocity mặc định100, không tự kế thừa lực touch. Đã sửa gain script child: velocity yêu cầu (mặc định100) chia velocity input đã được áp dụng bởi mixer ngoài, tránh nhân lực đánh hai lần. play(velocity:velocity) vẫn bảo toàn input; play()/play(velocity:100) khớp nhau. Kiểm tra octave trong keyboard có input127 cập nhật mức theo100/127; các test nhánh điều kiện velocity và aftertouch muốn giữ input đã ghi velocity:velocity rõ ràng. AssembleDebug và nhóm script playback/rhythm/keyboard parts/STS loader đạt. Chưa đối chiếu audio thiết bị với APK; context velocity0, volume Track.KeyTouch và chuẩn hóa MIDI live127 so đơn vị style100 còn cần kiểm chứng.
DNC stopall ở modulation: đối chiếu InstMode callback stopall -> Channels.CloseAll(cut). Interpreter nhận stopall()/stopall(cut), giữ thứ tự với Play/Stop trong audio plan; playback modulation áp dụng cho toàn bộ voice con của mỗi note owner được controller dispatch tới. Không cut dùng forceRelease (giữ ADS release), cut xóa âm ngay; Play sau StopAll còn phát đúng. Kiểm tra hai ID37/38 đều cắt tiếng cũ, chỉ ID38 phát child volume25 sau stopall; kiểm tra không cut giữ đuôi rồi kết thúc. AssembleDebug và nhóm script playback/interpreter/keyboard parts/MIDI modulation đạt. Giới hạn: chỉ event modulate, dispatch dựa các Playback hiện hành, chưa là scheduler toàn Channels; retired/released owner không nhận modulation từ mọi đường gọi hiện tại nên chưa tương đương CloseAll toàn kênh trong mọi tình huống. Không áp dụng stopall từ touch/timer/aftertouch/release.
Modulation tới release tail STS: ImportedKeyboardParts trước đây bỏ notes.released trong dispatch modulation; đã gửi tới mọi Playback chưa finished của đúng MIDI channel, gồm voice retired sau thay part. Cho phép StopAll(cut) của modulation dừng đuôi âm như Channels.CloseAll, thay vì chỉ tiếng còn giữ phím. Kiểm tra 2 MIDI channel: cả hai đã release nhưng còn ngân, modulation channel1 chỉ cắt channel1, channel2 tiếp tục tới khi nhận modulation riêng. Kiểm tra thay part rồi StopAll vẫn cắt voice release của part cũ. AssembleDebug và nhóm keyboard parts/script playback/MIDI modulation đạt. Chưa tương đương toàn Channels: audio command ở touch/timer/release còn giới hạn; cached modulation theo level và session dùng chung giữa channel còn cần đối chiếu APK.
DNC KeyToPitch: đối chiếu InstMode callback KeyToPitch và Music.KeyToPitch: exp(log(1.059463094)*(key+12))*500-1000. Bộ biểu thức nhận KeyToPitch(key:expression) và mặc định key0; dùng đúng hằng số gốc, trả Int trunc do giới hạn runtime ORGX, không giả định kết quả octave chính xác1000. Kiểm tra key -12/0/12/24 -> -500/0/999/2999, tham số key-key60, mặc định0, overflow không commit biến; PCM chứng minh pitchmove999 tiến sample1.999 thay vì1. AssembleDebug và các nhóm interpreter/playback/mode đạt; APK đã cài. Chưa hỗ trợ key số thực hoặc giữ Double qua phép toán như APK, chưa so pitch bằng bản thu thực; đây là hỗ trợ phần integer của callback.
DNC Stop trong release: interpreter nhận stop(id[,cut]) ở event release; Playback validate chỉ ID của nốt sở hữu, áp dụng release/cut trên child ngay cả khi return(true) chặn noteOff mặc định. Cross-ID Stop/StopAll hoặc Play ở release vẫn từ chối trước commit trạng thái. Kiểm tra 2 note xác nhận stop(id:id,cut) kết thúc riêng note nhả, note khác tiếp tục; Stop không cut giữ ADS tail và kết thúc; cross-ID99 không commit level25, note tiếp theo giữ level100. AssembleDebug và nhóm script playback/interpreter/keyboard parts đạt. Chưa là Channels scheduler đầy đủ nên không tuyên bố hỗ trợ dừng mọi ID từ release; chưa kiểm chứng thao tác thực trên thiết bị với SET dùng lệnh này.
Audio trong DNC aftertouch: Play/Stop của callback gốc được nối vào Playback aftertouch cho ID nốt sở hữu. Dùng chung hàm thực thi audio có thứ tự với modulation; validate source sound, pitch, voice limit128 và ID trước commit state. Cho phép stop(id:id[,cut]) rồi play(id:id,sound:...) thay tiếng, return(true) chặn fallback InstMode.Aftertouch. Cross-ID và StopAll ở aftertouch chưa hỗ trợ, từ chối thay vì âm thầm ảnh hưởng note khác. Kiểm tra 2 note: pressure note37 thay sample .25 thành .5, note38 vẫn .25, alternate lưu .75 không bị mở vì handled; forceRelease đóng voice mới. Cross-ID99 không commit level25, note sau vẫn level100. AssembleDebug và nhóm script playback/interpreter/keyboard parts/rhythm đạt; APK đã cài. Chưa kiểm chứng pressure/controller thật, chưa hỗ trợ scheduler Channels toàn cục hoặc phát khi owner đã bị loại.
Bàn phím chống kẹt nốt khi hủy gesture: PianoKeyboard trước đây chỉ cleanup pointerNotes sau vòng awaitPointerEvent, nên CancellationException khi octave/leftChord thay đổi hoặc Canvas rời composition bỏ qua noteOff và xóa trạng thái phím. Đã đặt cleanup trong finally, trừ đúng số reference của gesture theo từng note (nhiều ngón cùng phím), chỉ noteOff khi reference còn lại về0 và clear pointer map. AssembleDebug và diff check đạt. Không thêm unit test mô phỏng Compose pointer cancellation; chưa xác nhận đa chạm + đổi octave thực trên thiết bị, không khẳng định đã xử lý mọi lifecycle background hoặc controller MIDI.
Lifecycle bàn phím: Activity onPause/onResume cập nhật keyboardActive vào PianoKeyboard; đổi key của pointerInput khi pause làm hủy gesture và chạy finally nhả các nốt đang giữ. Trong pause không nhận chạm, resume bật lại. Đối chiếu APK gốc dispatchTouchEvent xử lý ACTION_CANCEL cùng note-up và MainActivity.onPause có xử lý theo trạng thái nền. Không suy ra chính sách dừng style/output từ bản gốc vì còn phụ thuộc q0; thay đổi chỉ áp dụng gesture bàn phím. AssembleDebug đạt, APK cài và smoke chuyển HOME rồi resume thành công. Chưa xác minh âm thanh nốt đang giữ qua đa chạm/background trên thiết bị; lifecycle Compose được cập nhật bất đồng bộ theo recomposition nên chưa coi mọi race đã xử lý.
### 2026-10-24 — DNC timer dừng và phát nốt theo thứ tự
- ImportedSimpleScript cho phép Stop trong callback timer; SampledInstrument xác thực ID trước khi ghi trạng thái và dùng cùng bộ áp dụng lệnh có thứ tự với modulation/aftertouch.
- Stop(id:id,cut) rồi Play không cộng dồn tiếng cũ; Play rồi Stop(cut) trong cùng callback tạo im lặng. Nốt timer mới kế thừa bend, balance và modulation đang giữ.
- Đã build assembleDebug và chạy ImportedScriptPlaybackTest, ImportedSimpleScriptTest, ReferenceRhythmPlayerTest thành công; thêm hai kiểm tra PCM cho thứ tự Stop/Play. Đã cài APK và mở MainActivity trên thiết bị thành công.
- Giới hạn: timer chỉ điều khiển ID của nốt sở hữu; StopAll và Stop sang ID khác chưa hỗ trợ. Chưa xác nhận tương đương toàn bộ APK gốc.
### 2026-10-24 — Phát tiếng từ sự kiện nhả phím DNC
- Đối chiếu InstMode callback play -> Channels.OpenKey: callback không giới hạn Play ở touch. ORGX nay nhận Play trong release cho ID của nốt sở hữu, thay vì luôn báo unsupported.
- Release xác thực sound/pitch, ID, giới hạn voice và effect trước commit state; áp dụng Stop/Play theo thứ tự qua applyOrderedAudio, kế thừa bend/balance/modulation. return(true) giữ quyền xử lý nhả phím cho script; release chỉ chạy một lần dù audio tiếp tục nhận noteOff.
- Kiểm tra PCM: đổi sample khi nhả riêng ID37 trong khi ID38 tiếp tục, không nhân tiếng khi gọi next(true) nhiều lần; Play rồi Stop(cut) im lặng; sound99 không tồn tại rollback biến và dùng release mặc định.
- assembleDebug thành công; 145 kiểm tra trong ImportedScriptPlaybackTest (85), ImportedSimpleScriptTest (31), ImportedKeyboardPartsTest (17), ReferenceRhythmPlayerTest (12) đạt. Đã cài và mở APK trên thiết bị 88d75c04.
- Còn giới hạn: Play/Stop ở release chỉ dùng ID sở hữu, chưa có scheduler toàn Channels cho cross-ID/StopAll. Chưa kiểm chứng trên SET thực có release script tương ứng; không kết luận giống toàn bộ APK.
