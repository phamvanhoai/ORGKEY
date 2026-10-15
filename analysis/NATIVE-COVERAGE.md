# Coverage thư viện native

Bảng [native-dynamic-symbols.csv](native-dynamic-symbols.csv) hiện có 32 JNI/dynamic symbol thuộc hai thư viện riêng của ORG trên ARM64:

- `libnmusic.so`: 16 symbol, gồm mở/đóng/reset/process channel, trộn `Mix0/1/2`, cấp phát bộ nhớ và chuyển đổi byte/short array.
- `libnopensl.so`: 16 symbol tương ứng với đường OpenSL và callback âm thanh.

Trong 32 dòng có 25 symbol được đánh dấu defined. Các bản disassembly và báo cáo hiện đã bao phủ các hàm trộn mẫu, channel process và callback chính; chưa dịch ngược toàn bộ thân mọi symbol trên tất cả ABI (ARM32, x86, x86_64). Vì vậy phần native đã được lập bản đồ và nối JNI, nhưng chưa thể gọi là giải mã hoàn toàn từng lệnh máy.

Đã chạy bộ disassembler ARM64 sau khi bổ sung `capstone` và `pyelftools`. Đầu ra hiện có trong thư mục `analysis/native/`: `NMusic_ChannelProcess` 704 lệnh, `NOpenSL_Start` 658 lệnh, `NOpenSL_MixPut` 198 lệnh, `NOpenSL_Write` 159 lệnh và `NOpenSL_Put` 39 lệnh. Mỗi file `.asm` bao phủ đủ số byte của symbol ELF; địa chỉ là địa chỉ ảo ELF, không phải địa chỉ runtime.
