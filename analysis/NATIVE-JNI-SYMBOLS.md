# Symbol native chính

## `libnmusic.so`

16 symbol JNI đã được nhận dạng:

`ChannelOpen`, `ChannelClose`, `ChannelReset`, `ChannelCopy`, `ChannelProcess`, `Mix0`, `Mix1`, `Mix2`, `GetMem`, `FreeMem`, `MemFromByteArray`, `MemFromShortArray`, `MemToByteArray`, `MemToShortArray`, `SysBits` và `code`.

Tên hàm nối trực tiếp với các lớp `sofeh.music.NMusic`; disassembly trước đó đã bao phủ nhóm channel/process và trộn mẫu.

## `libnopensl.so`

Có 9 symbol JNI của `sofeh.audio.NOpenSL`: `Start`, `Stop`, `Stoped`, `Put`, `Write`, `MixPut`, `MixWrite`, `RecordStart`, `RecordStop`. Thư viện cũng xuất các symbol OpenSL như `slCreateEngine`, `SL_IID_ENGINE`, `SL_IID_PLAY`, `SL_IID_RECORD`, `SL_IID_BUFFERQUEUE`, `SL_IID_ANDROIDSIMPLEBUFFERQUEUE` và `SL_IID_VOLUME`.

Danh sách này là inventory symbol từ ELF, không tự chứng minh mọi hàm đều được gọi trong runtime; mối nối Java/JNI đã được xác nhận cho các hàm mang tiền tố `Java_sofeh_`.
