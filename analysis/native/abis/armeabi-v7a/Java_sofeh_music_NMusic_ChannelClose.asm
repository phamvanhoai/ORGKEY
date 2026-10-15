# APK member: lib/armeabi-v7a/libnmusic.so
# Library SHA-256: bd2a4a0e17aaa14c558d5f96ba1ac387097bd96c2bdd771c287261dd8fdcc77e
# Function: Java_sofeh_music_NMusic_ChannelClose; address=0x1a3c; bytes=14
# Decoder mode: thumb; decoded_bytes=14
00001a3c  52ea0300          orrs.w     r0, r2, r3
00001a40  1cbf              itt        ne
00001a42  1046              movne      r0, r2
00001a44  01f01ab8          bne.w      #0x2a7c
00001a48  7047              bx         lr
