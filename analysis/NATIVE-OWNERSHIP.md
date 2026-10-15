# Phân loại sở hữu thư viện ARM64

## Logic âm thanh của ORG

Chín thư viện có JNI `Java_sofeh_*`: `libnbandbooster`, `libnequalizer`, `libnflanger`, `libngverb`, `libnlame`, `libnmusic`, `libnopensl`, `libnpitchshift`, `libnsf2`.

## AndroidX/hạ tầng

- `libdatastore_shared_counter.so`: JNI `androidx.datastore.core.NativeSharedCounter`.

## SDK quảng cáo/đo lường

- `libapplovin-native-crash-reporter.so`: AppLovin.
- `libbuffer_pgl.so`, `libfile_lock_pgl.so`, `libpglarmor.so`, `libnms.so`: Pangle/PGL/ByteDance.
- `libapminsighta.so`, `libapminsightb.so`, `libtt_ugen_layout.so`: nhóm phụ trợ ByteDance/Pangle theo tên và cụm đóng gói; không có JNI `sofeh`.

Phân loại ưu tiên namespace JNI và chuỗi lớp hơn tên file. Các thư viện không có `Java_sofeh_*` không được tính vào thuật toán âm thanh của ứng dụng nếu chưa có bằng chứng gọi trực tiếp.
