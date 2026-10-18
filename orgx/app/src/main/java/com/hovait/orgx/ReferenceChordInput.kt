package com.hovait.orgx

/** Tracks.ChordGet pitch-class assembly, including octave-doubling shortcuts. */
object ReferenceChordInput {
    private val doubled=mapOf(0 to (129 to 145),2 to (516 to 548),
        4 to (2064 to 2192),5 to (33 to 289),7 to (132 to 2180),
        9 to (528 to 529),11 to (2080 to 2084))
    fun mask(notes:Collection<Int>):Int {
        var mask=0
        // Original scans the MIDI key-state array, rather than press order.
        notes.filter{it in 0..127}.distinct().sorted().forEach{note->
            val pitch=note%12
            val bit=1 shl pitch
            if(mask and bit!=0)doubled[pitch]?.let{(before,after)->if(mask==before)mask=after}
            mask=mask or bit
        }
        return mask
    }
}
