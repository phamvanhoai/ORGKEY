package com.hovait.orgx

object MidiVoiceKey {
    fun key(note:Int,channel:Int,playback:Boolean=false):Int {
        require(note in 0..127 && channel in 0..15)
        return (if(playback)2048 else 0)+channel*128+note
    }
    fun percussion(channel:Int)=channel==9
    fun isLiveChannel(key:Int,channel:Int):Boolean = channel in 0..15 && key in 0..2047 && key/128==channel
    fun isChannel(key:Int,channel:Int,playback:Boolean):Boolean =
        isLiveChannel(key-(if(playback)2048 else 0),channel)
}
