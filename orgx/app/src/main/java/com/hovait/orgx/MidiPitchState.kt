package com.hovait.orgx

import java.util.concurrent.atomic.AtomicIntegerArray

/** Channel state persists across note-ons; live input and file playback are independent. */
class MidiPitchState {
    private val values=AtomicIntegerArray(32).apply{for(i in 0 until length())set(i,MidiPitchBend.CENTER)}
    private fun index(channel:Int,playback:Boolean):Int {
        require(channel in 0..15)
        return channel+if(playback)16 else 0
    }
    fun set(value:Int,channel:Int,playback:Boolean=false){values.set(index(channel,playback),value.coerceIn(0,16383))}
    fun semitones(channel:Int,playback:Boolean=false)=MidiPitchBend.semitones(values.get(index(channel,playback)))
}
