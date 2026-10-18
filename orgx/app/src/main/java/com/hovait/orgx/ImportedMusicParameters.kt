package com.hovait.orgx

/** Channels.OpenKey: Music transpose/tune/release, with octave chosen by channel type. */
object ImportedMusicParameters {
    fun pitch(channel:ImportedChannelSettings,music:ImportedChannelSettings,octave:Int)=channel.copy(
        transpose=channel.transpose+music.transpose,octave=channel.octave+octave)
    fun release(value:Int,channel:ImportedChannelSettings,music:ImportedChannelSettings)=
        (value.toLong()*channel.releasePercent*music.releasePercent/10000).coerceIn(0,100).toInt()
}
