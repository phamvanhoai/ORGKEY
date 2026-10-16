package com.hovait.orgx

object MidiPitchBend {
    const val CENTER=8192
    // Default MIDI bend range: two semitones in either direction.
    fun semitones(value:Int):Double {
        val delta=value.coerceIn(0,16383)-CENTER
        return delta*2.0/(if(delta>=0)8191 else 8192)
    }
}
