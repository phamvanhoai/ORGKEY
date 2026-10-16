package com.hovait.orgx

/** Assets decoded from the reference APK and packaged for the sample playback layer. */
data class SampleAsset(val resourceName:String,val use:String,val source:String)
object SampleAssetCatalog {
    val referenceAssets=listOf(
        SampleAsset("orgx_piano_low_eb2","piano low register","analysis/extracted-audio/piano-low-eb2.wav"),
        SampleAsset("orgx_piano_dnc_csharp4","piano mid register","analysis/extracted-audio/piano-dnc-csharp4.wav"),
        SampleAsset("orgx_metronome_short","metronome short","analysis/extracted-audio/metronome-short.wav"),
        SampleAsset("orgx_metronome_long","metronome long","analysis/extracted-audio/metronome-long.wav"),
        SampleAsset("orgx_crash","crash pad","analysis/extracted-audio/constant-syncro-crash.wav"),
        SampleAsset("orgx_sound_sea","ambient sound","analysis/extracted-audio/sound-sea.wav")
    )
}
