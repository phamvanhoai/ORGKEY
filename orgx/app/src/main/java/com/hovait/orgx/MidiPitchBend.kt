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
