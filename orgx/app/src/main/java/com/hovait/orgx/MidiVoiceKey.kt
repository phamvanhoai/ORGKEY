package com.hovait.orgx

object MidiVoiceKey {
    fun key(note:Int,channel:Int):Int {
        require(note in 0..127 && channel in 0..15)
        return channel*128+note
    }
    fun percussion(channel:Int)=channel==9
}
