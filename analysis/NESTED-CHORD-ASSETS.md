# Các file `.Chord` trong `data.zip`

Ngoài bảng tên `assets/Chord`, `assets/data.zip` chứa 33 file `Chord/*.Chord`. Mỗi file có đúng 12 số nguyên CRLF, tương ứng trực tiếp với 12 bitmask nốt gốc; toàn bộ 33 file (396 mask) được parse thành công bằng [inspect_nested_chords.py](inspect_nested_chords.py), kết quả ở [nested-chord-inventory.json](nested-chord-inventory.json).

Điều này xác nhận hai lớp dữ liệu:

- `assets/Chord`: mẫu tên + khoảng cách bán âm, được `Music.ChordTable` chuyển thành bitmask khi khởi tạo.
- `data.zip!/Chord/*.Chord`: bitmask đã tính sẵn, đặt tên đầy đủ như `Major.Chord`, `Diminished-7th.Chord`, `Thirteenth-Flat-Ninth-Flat-Fifth.Chord`.

33 file nested là tập con được đặt tên đầy đủ của 37 dòng bảng gốc; các dòng còn lại trong bảng gốc không có file `.Chord` riêng tương ứng. Vì mỗi file nested đã chứa đúng 12 mask, loader có thể dùng trực tiếp cho tra cứu mà không cần tính lại khoảng cách.

Đối chiếu chính xác từng vector 12 mask cho thấy 31/33 file nested trùng hoàn toàn với một dòng trong `assets/Chord`. Hai ngoại lệ là `Customized Major.Chord` và `Customized Minor.Chord`; chúng là các bảng tùy biến riêng, không có dòng tên tương ứng trong bảng 37 mẫu chuẩn. Đây là khác biệt dữ liệu có chủ ý, không phải lỗi parse.
Hai bảng tùy biến vẫn bắt đầu bằng mask major `[0,4,7]`, nhưng các gốc sau không phải phép xoay đơn giản của một mẫu duy nhất; một số gốc có thêm hoặc bớt cao độ, ví dụ `Customized Minor` tại gốc 1 là `[1,4,8]`. Điều này phù hợp với preset hợp âm tùy chỉnh theo từng phím, thay vì chỉ là bí danh của `M` hoặc `m`.
