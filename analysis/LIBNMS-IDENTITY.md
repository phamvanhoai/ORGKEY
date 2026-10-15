# Nhận dạng `libnms.so`

`libnms.so` không phải module âm nhạc của ORG dù tên dễ gây nhầm. ELF không xuất JNI `Java_sofeh_*`; entry point duy nhất là `JNI_OnLoad` (456 byte), cho thấy JNI được đăng ký động. `.rodata` chứa tên lớp `com/pgl/ssdk/ces/a`, nối thư viện với SDK Pangle/PGL.

Dependency gồm `liblog.so`, `libm.so`, `libdl.so`, `libc.so`. Import có API system property, filesystem, environment, process/thread và syscall (`__system_property_*`, `stat/lstat`, `opendir`, `getenv`, `uname`, `getppid`, `syscall`), phù hợp với thư viện SDK kiểm tra môi trường hơn là DSP âm thanh.

Vì vậy `libnms.so` được chuyển sang nhóm SDK bên thứ ba và không tính vào coverage thuật toán âm thanh riêng của ORG.
