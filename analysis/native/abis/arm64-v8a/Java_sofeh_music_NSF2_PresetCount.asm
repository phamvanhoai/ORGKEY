# APK member: lib/arm64-v8a/libnsf2.so
# Library SHA-256: 8ea5a2f1833868953a9b4a52525e2544eefc6e94c98e1e9a8c344040d51139df
# Function: Java_sofeh_music_NSF2_PresetCount; address=0x77b4; bytes=28
# Decoder mode: default; decoded_bytes=28
000077b4  480000b0          adrp       x8, #0x10000
000077b8  084d43f9          ldr        x8, [x8, #0x698]
000077bc  680000b4          cbz        x8, #0x77c8
000077c0  002140b9          ldr        w0, [x8, #0x20]
000077c4  c0035fd6          ret        
000077c8  e0031f2a          mov        w0, wzr
000077cc  c0035fd6          ret        
