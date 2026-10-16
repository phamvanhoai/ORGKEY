package com.hovait.orgx

import android.media.AudioTrack

class AudioTrackBackend(private val track:AudioTrack,override val sampleRate:Int,override val channels:Int=2):AudioBackend {
    override fun start(){track.play()}
    override fun write(samples:ShortArray,offset:Int,count:Int)=track.write(samples,offset,count,AudioTrack.WRITE_BLOCKING)
    override fun stop(){if(track.playState==AudioTrack.PLAYSTATE_PLAYING)track.stop()}
    override fun close(){runCatching{stop()};track.release()}
}
