# Native reference inventory extracted from the reference APK.

| Library family | ABIs | Purpose inferred from JNI/analysis |
| --- | --- | --- |
| `libnopensl.so` | arm64-v8a, armeabi-v7a, x86, x86_64 | OpenSL output, mix/write and record lifecycle |
| `libnequalizer.so` | four ABIs | Equalizer FX |
| `libnflanger.so` | four ABIs | Flanger FX |
| `libngverb.so` | four ABIs | Reverb FX |
| `libnpitchshift.so` | four ABIs | Pitch shift FX |
| `libnbandbooster.so` | four ABIs | Band booster FX |
| `libnmusic.so` | four ABIs | Native music/render helpers |
| `libnms.so` | four ABIs | Music/sample support |
| `libnsf2.so` | four ABIs | SoundFont 2 support |

The binaries are under `analysis/native/reference-libs/` for inspection only. They are not copied into ORGX: their JNI package/signatures and native dependencies belong to the reference application, and loading them without a matching wrapper is unsafe.
