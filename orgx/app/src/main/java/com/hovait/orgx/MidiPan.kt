package com.hovait.orgx

object MidiPan {
    const val CENTER=64
    fun left(value:Int):Double=if(value<=CENTER)1.0 else (127-value.coerceIn(0,127))/63.0
    fun right(value:Int):Double=if(value>=CENTER)1.0 else value.coerceIn(0,127)/64.0
}

/** MIDI file channel controls, independent of the live keyboard mixer. */
class MidiChannelMix {
    private val volume=java.util.concurrent.atomic.AtomicIntegerArray(16)
    private val expression=java.util.concurrent.atomic.AtomicIntegerArray(16)
    private val pan=java.util.concurrent.atomic.AtomicIntegerArray(16)
    init{reset()}
    fun reset(){for(channel in 0..15){volume.set(channel,100);expression.set(channel,127);pan.set(channel,64)}}
    fun controller(channel:Int,number:Int,value:Int){
        require(channel in 0..15 && value in 0..127)
        when(number){7->volume.set(channel,value);10->pan.set(channel,value);11->expression.set(channel,value)}
    }
    fun gain(channel:Int,right:Boolean):Double=volume.get(channel)/127.0*expression.get(channel)/127.0*
        (if(right)MidiPan.right(pan.get(channel)) else MidiPan.left(pan.get(channel)))
}
