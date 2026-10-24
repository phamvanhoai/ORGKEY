package com.hovait.orgx

object MidiPitchBend {
    const val CENTER=8192
    // Default MIDI bend range: two semitones in either direction.
    fun semitones(value:Int,range:Int=2):Double {
        require(range in 1..12)
        val delta=value.coerceIn(0,16383)-CENTER
        return delta*range.toDouble()/(if(delta>=0)8191 else 8192)
    }
}

/** Same 10 ms factor interpolation used by sampled channels. */
class MidiPitchSlew(private val sampleRate:Int){
    private var factor=1.0
    private var target=Double.NaN
    private var frames=0
    private var delta=0.0
    private var started=false
    init{require(sampleRate>0)}
    fun next(semitones:Double,speed:Int):Double{
        require(semitones.isFinite() && speed in 0..100)
        val wanted=Math.pow(2.0,semitones/12.0)
        if(!started || speed==0){factor=wanted;frames=0;started=true}
        else{
            if(wanted!=target)frames=0
            if(frames<=0){frames=(sampleRate/100).coerceAtLeast(1);delta=(wanted-factor)/(frames.toDouble()*speed)}
            factor+=delta;frames--
            if(kotlin.math.abs(wanted-factor)<1e-10)factor=wanted
        }
        target=wanted
        return factor
    }
}
