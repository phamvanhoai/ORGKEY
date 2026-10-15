# Import native của `libnopensl.so`

PLT của thư viện chỉ có 13 import:

- OpenSL: `slCreateEngine`.
- Đồng bộ: `pthread_mutex_lock`, `pthread_mutex_unlock`.
- Bộ nhớ: `memcpy`, `__memcpy_chk`, `memmove`, `memset`.
- Thời gian: `usleep`.
- Runtime/compiler: `__cxa_finalize`, `__cxa_atexit`, `__register_atfork`, `__stack_chk_fail`.

Không thấy import trực tiếp của file, socket, Java I/O hoặc thư viện mạng. Điều này phù hợp với một thư viện native tập trung vào khởi tạo OpenSL, sao chép buffer và đồng bộ callback.
