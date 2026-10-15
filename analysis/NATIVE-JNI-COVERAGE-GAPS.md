# Khoảng trống coverage JNI ARM64

So sánh JNI export với các file `analysis/native/Java_*.asm`:

| Thư viện | JNI tổng | Chưa có file assembly riêng |
| --- | ---: | ---: |
| BandBooster | 4 | 2 |
| Equalizer | 8 | 4 |
| Flanger | 6 | 2 |
| GVerb | 14 | 10 |
| LAME | 4 | 0 |
| NMusic | 16 | 15 |
| NOpenSL | 9 | 5 |
| PitchShift | 5 | 0 |
| NSF2 | 9 | 6 |

Đã chạy [dump_all_sofeh_jni.py](dump_all_sofeh_jni.py) và tạo thêm 44 file assembly còn thiếu. Hiện toàn bộ JNI `Java_sofeh_*` của chín thư viện âm thanh ARM64 đều có file `.asm` riêng trong `analysis/native/`; bảng phía trên ghi trạng thái trước lần chạy hoàn tất này.
