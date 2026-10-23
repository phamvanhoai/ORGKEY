package com.hovait.orgx

import java.util.concurrent.atomic.AtomicIntegerArray

/** Channel state persists across note-ons; live input and file playback are independent. */
class MidiPitchState {
    private val rpnMsb=IntArray(16){127}
    private val rpnLsb=IntArray(16){127}
    private val playbackCoarse=IntArray(16){2}
    private val playbackCents=IntArray(16)
    fun resetPlayback(){for(channel in 0..15){rpnMsb[channel]=127;rpnLsb[channel]=127;playbackCoarse[channel]=2;playbackCents[channel]=0;set(8192,channel,true)}}
    /** Pitch bend sensitivity is RPN 0,0; unrelated Data Entry must be ignored. */
    fun playbackController(channel:Int,controller:Int,value:Int):Boolean{
        require(channel in 0..15 && value in 0..127)
        when(controller){
            98,99->{rpnMsb[channel]=127;rpnLsb[channel]=127}
            101->rpnMsb[channel]=value
            100->rpnLsb[channel]=value
            6->if(rpnMsb[channel]==0 && rpnLsb[channel]==0){playbackCoarse[channel]=value;return true}
            38->if(rpnMsb[channel]==0 && rpnLsb[channel]==0){playbackCents[channel]=value.coerceAtMost(99);return true}
        }
        return false
    }
    fun sensitivityMessages():List<ByteArray> = (0..15).flatMap{channel->
        listOf(101 to 0,100 to 0,6 to liveRange,38 to 0,101 to 127,100 to 127).map{(cc,value)->MidiChannelMessage.bytes(0xb0 or channel,cc,value)}
    }
    @Volatile var liveRange=2
        set(value){require(value in 1..12);field=value}
    private val values=AtomicIntegerArray(32).apply{for(i in 0 until length())set(i,MidiPitchBend.CENTER)}
    private fun index(channel:Int,playback:Boolean):Int {
        require(channel in 0..15)
        return channel+if(playback)16 else 0
    }
    fun set(value:Int,channel:Int,playback:Boolean=false){values.set(index(channel,playback),value.coerceIn(0,16383))}
    /** Seed a new recording before its first note, excluding file playback state. */
    fun recordingMessages():List<ByteArray> = (0..15).mapNotNull{channel->
        val bend=values.get(index(channel,false))
        if(bend==MidiPitchBend.CENTER)null else MidiChannelMessage.bytes(0xe0 or channel,bend and 127,bend shr 7)
    }
    fun semitones(channel:Int,playback:Boolean=false)=MidiPitchBend.semitones(values.get(index(channel,playback)),1)*
        (if(playback)playbackCoarse[channel]+playbackCents[channel]/100.0 else liveRange.toDouble())
    fun scriptLevel(channel:Int,playback:Boolean=false)=values.get(index(channel,playback))-MidiPitchBend.CENTER
}
