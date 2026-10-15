# Định dạng MIDI Model và ánh xạ điều khiển

## Kết quả trên sáu Model đi kèm

[`inspect_midi_models.py`](inspect_midi_models.py) đọc trực tiếp sáu file `.Model`
trong `assets/data.zip` và xuất
[`midi-model-inventory.json`](midi-model-inventory.json). Toàn bộ dòng dữ liệu
đều được nhận dạng.

| Model | Phạm vi nốt MIDI | Chord | Operation |
| --- | ---: | ---: | ---: |
| Standard 25 Key | 48–72 | 11 | 10 |
| Standard 32 Key | 53–84 | 12 | 10 |
| Standard 49 Key | 48–96 | 17 | 10 |
| Standard 61 Key | 36–96 | 17 | 10 |
| Standard 76 Key | 28–103 | 19 | 10 |
| Standard 88 Key | 21–108 | 20 | 10 |

Phạm vi trên được tính từ `Start` đến `Start + Count - 1`. Trường `Chord` được
ứng dụng dùng như một offset của vùng chord/split; mã giao diện có đọc trực tiếp
field này khi tính điểm chia bàn phím.

## Cú pháp

Reader `defpackage.tp2.c(String)`:

1. tách văn bản theo dòng;
2. bỏ dòng rỗng và dòng có ký tự đầu sau trim là `/`;
3. tách trường bằng một trong bốn ký tự `:`, `=`, `,`, `/`;
4. so sánh tên field/command không phân biệt hoa thường.

Ba field đầu có dạng:

```text
Name: Standard 61 Key
Key: 61, 36, 17
Skin:
```

Operation được lưu theo mẫu:

```text
Command: Index = Event: Channel, Value1, Value2
```

Object operation `sp2` có sáu trường tương ứng: mã command nội bộ, index, tên
event, channel, value1 và value2. Khi thiếu hoặc không đọc được số, index mặc định
0; channel/value mặc định `-1`.

## Các ánh xạ thực tế

Cả sáu model chứa cùng mười operation:

```text
Instrument: 1 = Program: -1, 0
...
Instrument: 10 = Program: -1, 9
```

Vì vậy Program Change 0–9 chọn Instrument 1–10. `-1` ở channel là wildcard:
`tp2.b(event, channel, value1, value2)` chấp nhận operation nếu channel/value
bằng tham số đầu vào hoặc bằng `-1`. Tên event được chuyển về chữ thường trước
khi so sánh.

Mã command nội bộ của `Instrument` là `0xF0` (240). Static initializer của
`tp2` khai báo tổng cộng 70 command, bao phủ điều khiển style, pad, chord,
Sound/Loop, FX, tempo, octave, transpose, track, LiveStyle, slider, danh sách
Instrument/System/Keyboard Set và các chức năng khác. Sáu asset mặc định chỉ sử
dụng một command trong số đó.

Một lookup khác, `tp2.a(code, index, keyboard)`, tìm operation theo mã command và
index, đồng thời tách event tên `keyboard` khỏi các event còn lại bằng cờ boolean.

## Đọc và ghi file

`tp2.c` là reader từ chuỗi. `tp2.d(path)` tạo lại header `Name`, `Key`, `Skin`,
sau đó tuần tự ghi từng operation theo đúng sáu trường và lưu bằng
`FileOutputStream`. Writer bắt exception và không phát lại lỗi ở tầng này.

## Bằng chứng

- [`midi-model-parse.json`](midi-model-parse.json): reader.
- [`midi-model-load.json`](midi-model-load.json): writer (tên bị rút gọn là `d`).
- [`midi-model-static.json`](midi-model-static.json): 70 tên và mã command.
- [`midi-model-find-operation.json`](midi-model-find-operation.json): wildcard
  channel/value.
- [`midi-model-method-a.json`](midi-model-method-a.json): lookup code/index.
- [`midi-model-operation-class.json`](midi-model-operation-class.json): layout
  object operation.

Đây là phân tích tĩnh trên đúng APK đã ghi trong inventory; ứng dụng và thiết bị
MIDI không được chạy.
