# Luồng MIDI vào và dispatch Model

## Chuỗi xử lý đã xác nhận

Luồng điều khiển MIDI dùng bảng operation của `.Model` theo chuỗi:

```text
MIDI event
  → MainActivity.a1/Z0/g1
  → tp2.b(event, channel, value1, value2)
  → operation sp2
  → x92 chạy trên UI thread
  → h1(...) hoặc u0(commandCode, index)
  → v0(...) dispatch hành động giao diện/âm nhạc
```

REA liệt kê đúng bốn caller của `tp2.b`: `Z0`, `a1`, `f1`, `g1`. `a1` xử lý
Note On, `Z0` xử lý Note Off, `f1` xử lý tổ hợp keyboard và `g1` là đường
event chung dùng tên event/channel/value.

## Program Change và sáu Model mặc định

`g1(event, channel, value1, value2)` lưu event gần nhất, gọi lookup Model rồi:

- nếu tìm thấy operation, đưa `sp2` sang `x92` bằng `runOnUiThread`;
- nếu không tìm thấy và event là `program`, gọi fallback `u0(240, value1 + 1)`;
- trả `true` khi event được nhận để caller có thể bỏ xử lý tiếp.

Trong sáu Model mặc định, lookup luôn có thể khớp Program 0–9 vì mỗi Model
chứa:

```text
Instrument: 1 = Program: -1, 0
...
Instrument: 10 = Program: -1, 9
```

Operation khớp có command code 240 (`0xF0`) và index 1–10. Runnable `x92` xử lý
phần lớn command chuyên biệt ngay tại đó; nhánh mặc định gọi
`u0(sp2.commandCode, sp2.index)`. `u0` chuyển tiếp sang dispatcher lớn `v0`.
Static table của Model đặt tên code 240 là `Instrument`, nên cả đường có mapping
và fallback Program Change đều hội tụ về cùng command chọn Instrument.

## Note On và Note Off

`a1(channel, key, velocity)` chỉ xử lý khi các cờ MIDI input cho phép. Key được
chuẩn hóa bằng `% 128`.

- Velocity ngoài miền phát (`<= 0` hoặc `>= 128`) đi theo đường đóng nốt nếu
  Model không bắt event.
- Với velocity 1–127, `g1("NoteOn", channel, key, -1)` được thử trước. Khi Model
  bắt event, phát nốt mặc định bị bỏ qua.
- Nếu không bị bắt, key có thể được cộng offset theo channel, velocity được scale
  theo cấu hình, rồi đi vào hàm phát/ghi nốt `m(...)`.

`Z0(channel, key, velocity)` là đường Note Off. Mã quan sát được lookup chuỗi
`"NoteOn"` với cùng channel/key trước khi gọi hàm đóng nốt `o(...)`. Đây là hành
vi thực trong Java suy dịch; chưa chạy thiết bị để xác định dụng ý hoặc ảnh hưởng
của cách dùng cùng tên event cho cả hai chiều.

## Keyboard mapping

`f1(key, z1, z2, z3, z4)` đóng gói bốn cờ boolean thành bitmask 1/2/4/8 rồi tra:

```text
tp2.b("keyboard", -1, key, bitmask)
```

Nếu khớp, operation cũng được dispatch trên UI thread. Lookup riêng
`tp2.a(code, index, keyboard)` có cờ để chỉ nhận event `keyboard` hoặc loại nó,
cho thấy mapping phím điều khiển được tách khỏi MIDI event thông thường.

## Thread và chế độ học mapping

`g1` và `f1` kiểm tra trạng thái `T5` trước khi dispatch. Khi dialog mapping đang
hiện, chúng cập nhật dialog bằng event vừa nhận thay vì thực thi operation. Khi
thực thi, `x92` luôn được gửi qua `runOnUiThread`, phù hợp với việc nhiều command
thay đổi giao diện, track, volume và trạng thái phát.

## Bằng chứng

- [`midi-model-find-callers.json`](midi-model-find-callers.json): bốn caller đầy đủ.
- [`midi-control-a1.json`](midi-control-a1.json): Note On.
- [`midi-control-Z0.json`](midi-control-Z0.json): Note Off.
- [`midi-control-g1.json`](midi-control-g1.json): lookup/fallback Program.
- [`midi-control-f1.json`](midi-control-f1.json): keyboard bitmask.
- [`midi-control-dispatch-runnable.json`](midi-control-dispatch-runnable.json):
  dispatch operation trên UI thread.
- [`midi-control-command-u0.json`](midi-control-command-u0.json): cầu nối đến `v0`.

Phân tích này là tĩnh. Chưa kết nối thiết bị USB MIDI/BLE MIDI và chưa quan sát
event trong runtime.
