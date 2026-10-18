package com.hovait.orgx

import org.junit.Assert.*
import org.junit.Test

class MidiPitchStateTest {
    @Test fun retainedBendIsPerChannelAndSeparateFromFilePlayback(){
        val state=MidiPitchState()
        assertEquals(0.0,state.semitones(0),0.0)
        state.set(16383,0)
        assertEquals(2.0,state.semitones(0),0.0)
        assertEquals(0.0,state.semitones(1),0.0)
        assertEquals(0.0,state.semitones(0,true),0.0)
        state.set(0,0,true)
        assertEquals(-2.0,state.semitones(0,true),0.0)
        assertEquals(2.0,state.semitones(0),0.0)
        state.set(MidiPitchBend.CENTER,0)
        assertEquals(0.0,state.semitones(0),0.0)
    }
}
