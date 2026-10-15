# Import native của `libnmusic.so`

`libnmusic.so` có 16 JNI symbol của `sofeh.music.NMusic` và các import xử lý pitch-shift/FFT:

- Pitch-shift: `smbPitchShift`, `smbPitchShiftFlush`, `smbPitchShiftCopy`.
- Toán học/FFT: `smbFft`, `cos`, `atan2`, `sincos`, `log`.
- Bộ nhớ/chuỗi: `malloc`, `free`, `memset`, `memmove`, `strlen`.
- Runtime C/C++: `__cxa_finalize`, `__cxa_atexit`, `__register_atfork`.

Không có import OpenSL trong thư viện này. Ranh giới tĩnh hiện rõ: `libnmusic.so` xử lý channel, trộn và pitch-shift; `libnopensl.so` đảm nhiệm thiết bị phát/ghi OpenSL.
