# Giải mã `assets/dic`

Đã tìm được consumer chính xác bằng DEX xref: `com.pgl.ssdk.af.a(Context)` trong `classes2.dex`. Đây là mã SDK Pangle/PGL, không thuộc logic âm nhạc ORG.

Luồng xử lý:

1. Mở `assets/dic`.
2. Đọc từng block tối đa 8.192 byte.
3. XOR từng byte với khóa UTF-8 lặp lại `dic`.
4. Ghi kết quả vào `<filesDir>/dic`.
5. Đặt quyền `chmod 777`, chạy file, rồi trả về `chmod 600`.
6. Nếu lần chạy đầu không trả kết quả, ghi byte `2` tại offset 16 rồi thử lại.

Vì asset chỉ dài 4.016 byte, toàn bộ file được giải bằng một block. Kết quả [dic-decoded.bin](dic-decoded.bin) là ELF 32-bit x86, loại `ET_DYN`, entry point `0x550`, dùng interpreter `/system/bin/linker`. Nó import `printf`, `__libc_init`, `__cxa_atexit`, `__stack_chk_fail`; chuỗi build cho thấy Android clang 14.0.7/LLD 14.0.7.

Mã biến đổi REA xác nhận chính xác phép XOR:

```java
bArr[i] = (byte) (bArr[i] ^ bArr2[i % bArr2.length]);
```

Bằng chứng lưu tại [dic-reader.json](dic-reader.json) và [dic-transform.json](dic-transform.json). Như vậy `dic` đã được giải mã về định dạng thực; chưa thực thi ELF này trong quá trình phân tích.

Đã disassemble toàn bộ section `.text` bằng [disassemble_dic.py](disassemble_dic.py): 756 byte, 245 lệnh x86, bắt đầu đúng tại entry point `0x550`. Assembly nằm ở [dic-x86-text.asm](native/dic-x86-text.asm). PLT gồm `0x860=__libc_init`, `0x870=__cxa_atexit`, `0x880=printf`, `0x890=__stack_chk_fail`; các call site trong `.text` đã được đối chiếu với bốn stub này.
