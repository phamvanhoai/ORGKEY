# Kích thước JNI của các module hiệu ứng

Theo dynamic symbol ARM64, các entry point xử lý lớn nhất còn lại là:

- `NEqualizer_process`: 1.068 byte; `processRight`: 1.060; `processLeft`: 968; `build`: 684.
- `NGVerb_processStereoLeft`: 212 byte; `processMono`: 160; `open`: 140; `setrevtime`: 256; `setroomsize`: 484.
- `NFlanger_processLeft`: 404 byte; `process`: 400; `processRight`: 184.
- `NBandBooster_process`: 208 byte; `build`: 172.

Ưu tiên disassembly tiếp theo là Equalizer process trái/phải và NGVerb setters/processStereo, vì đây là các hàm lớn hoặc chứa logic hiệu ứng đáng kể.

Đã disassemble xong nhóm ưu tiên bằng [dump_effects.py](dump_effects.py): Equalizer `process` (267 lệnh), `processLeft` (242), `processRight` (265), `build` (171); GVerb `processStereoLeft` (53), `processMono` (40), `setroomsize` (121) và `setrevtime` (64). Assembly nằm trong `analysis/native/`.

Đã bổ sung Flanger `process` (100 lệnh), `processLeft` (101), `processRight` (46), `build` (21) và BandBooster `process` (52), `build` (43). Như vậy các entry point xử lý/build lớn của năm module hiệu ứng chính đều đã có assembly ARM64.
