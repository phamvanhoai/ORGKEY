package com.hovait.orgx

object MidiVoiceKey {
    fun key(note:Int,channel:Int,playback:Boolean=false):Int {
        require(note in 0..127 && channel in 0..15)
        return (if(playback)2048 else 0)+channel*128+note
    }
    fun percussion(channel:Int)=channel==9
}
