# Tài nguyên hợp âm

`assets/Chord` là tệp văn bản CRLF 826 byte, gồm 37 định nghĩa hợp âm. Mỗi dòng có tên hiển thị rồi các khoảng cách bán âm tương đối từ nốt gốc; ví dụ `M 0 4 7`, `m 0 3 7`, `7th 0 4 7 10` và `13th 0 4 7 10 2 5 9`. Bộ kiểm kê [chord-asset-inventory.json](chord-asset-inventory.json) được tạo bởi [inspect_chord_asset.py](inspect_chord_asset.py) và phân tích đúng 37/37 dòng.

Số nốt mỗi định nghĩa nằm trong khoảng 1–7. Tệp có các nhóm major/minor/diminished/augmented, sus, seventh, ninth, eleventh và thirteenth; các hậu tố `flt`, `shp`, `alt`, `No3`, `No5` chỉ ra biến thể nốt. Các khoảng cách có thể xuất hiện ngoài thứ tự tăng dần (ví dụ nốt 9 dùng `2` sau quãng 10), cho thấy đây là danh sách lớp cao độ chứ không phải dãy MIDI tuyệt đối.

Phân tích này mô tả dữ liệu đóng gói; việc lớp Java nào chọn tên hợp âm và chuyển các khoảng cách thành nốt cụ thể vẫn cần nối với luồng `Chord`/`System` đã phân tích trong `data.zip`.

## Nối với mã Java

REA xác nhận `sofeh.music.Music.ChordTable(String)` đọc đúng định dạng này: giới hạn số dòng theo `MaxChannelCount`, lưu tên vào `fChordName`, nạp tối đa 12 khoảng cách vào `fChordScale` và điền `-1` cho ô thiếu. Sau đó, với từng nốt gốc từ 0 đến 11, phương thức tạo bitmask 12 bit trong `fChordTable`; mỗi khoảng cách `s` bật bit `(s + root) % 12`. Như vậy tệp `Chord` là bảng mẫu cao độ tương đối, còn `fChordTable` là bảng tra cứu hợp âm theo cả loại và nốt gốc. Mã gốc đã trích ở [chord-table-loader.java](source/chord-table-loader.java).

Inventory cũng đã tính sẵn 12 mask cho mỗi hợp âm trong trường `root_masks` của [chord-asset-inventory.json](chord-asset-inventory.json). Ví dụ `M` có mask gốc `145` (các bit cho 0, 4, 7); các mask tiếp theo là phép xoay theo từng nốt gốc.

Toàn bộ 444 trường hợp (37 mẫu × 12 nốt gốc) tạo ra 373 mask khác nhau, với 61 mask bị trùng. Trùng hợp có thể có chủ ý theo lý thuyết âm nhạc: hợp âm `aug` đối xứng theo chu kỳ 4 bán âm, `dim7` đối xứng theo chu kỳ 3, còn `sus4` và `sus2` có thể cho cùng tập cao độ khi đổi nốt gốc. Vì `ChordTable` chỉ lưu mask theo chỉ số dòng và nốt gốc, việc chọn tên khi nhiều dòng cùng mask phụ thuộc vào mã gọi tra cứu bên ngoài bảng này.
