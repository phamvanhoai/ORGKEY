# Tài nguyên phụ còn lại

`assets/download.en` là catalog văn bản 3.192 dòng, bắt đầu bằng thông tin Sofeh Sunrise, email, URL và sau đó là các bản ghi tên gói tải xuống. Nội dung này đã được phân tích theo cấu trúc catalog trong [DOWNLOAD-CATALOG.md](DOWNLOAD-CATALOG.md); không tạo bản sao parser khác.

`assets/dic` đã được giải mã: lớp `com.pgl.ssdk.af` XOR dữ liệu với khóa lặp `dic`, ghi vào filesDir và chạy nó. Kết quả là ELF x86 32-bit của SDK Pangle/PGL. Chi tiết ở [DIC-ASSET.md](DIC-ASSET.md).

Kiểm tra byte ban đầu thấy `c`, `d`, `i` xuất hiện dày đặc vì chính chúng là khóa XOR lặp; DEX xref đã xác nhận thay vì suy đoán từ tần suất.

Đã thử các signature nén chuẩn zlib, gzip, bzip2 và LZMA; cả bốn đều thất bại. Quét XOR một byte cũng không tạo ra dữ liệu có tỷ lệ ký tự in được cao. Vì vậy `dic` không phải archive nén chuẩn hoặc XOR một byte đơn giản theo các kiểm tra này.

`assets/PublicSuffixDatabase.list` là cơ sở dữ liệu public suffix của thư viện mạng, có header nhị phân và danh sách hậu tố; đây là dữ liệu thư viện bên thứ ba, không phải định dạng riêng của ORG. Các template quảng cáo HTML và tệp MRAID nằm trong nhóm SDK quảng cáo.
