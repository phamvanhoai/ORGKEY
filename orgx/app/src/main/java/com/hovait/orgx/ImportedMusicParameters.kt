package com.hovait.orgx

/** Channels.OpenKey: Music transpose/tune/release, with octave chosen by channel type. */
object ImportedMusicParameters {
    fun keyboardGain(music:ImportedChannelSettings)=minOf(100,100+music.pan)/100.0
    fun rhythmGain(music:ImportedChannelSettings,rhythm:ImportedChannelSettings,melodic:Boolean)=
        minOf(100,100-music.pan)/100.0 * rhythm.volume/100.0 *
            minOf(100,if(melodic)100-rhythm.pan else 100+rhythm.pan)/100.0
    fun pitch(channel:ImportedChannelSettings,music:ImportedChannelSettings,octave:Int)=channel.copy(
        transpose=channel.transpose+music.transpose,octave=channel.octave+octave)
    fun release(value:Int,channel:ImportedChannelSettings,music:ImportedChannelSettings)=
        (value.toLong()*channel.releasePercent*music.releasePercent/10000).coerceIn(0,100).toInt()
}
