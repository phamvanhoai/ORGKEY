package com.hovait.orgx

import org.junit.Assert.*
import org.junit.Test

class MidiExpressionSustainTest {
    @Test fun pedalDoesNotDelayExpressionOrRemoveItsMatchingActiveNote(){
        val note=MidiNoteEvent(0,60,100,true)
        val bend=MidiNoteEvent(2,60,70,false,controller=-2)
        val pressure=MidiNoteEvent(3,60,80,false,controller=-3)
        val result=MidiSustain.resolve(listOf(note,
            MidiNoteEvent(1,0,127,false,controller=64),bend,pressure,
            MidiNoteEvent(4,0,0,false,controller=123),
            MidiNoteEvent(5,0,0,false,controller=64)))
        assertEquals(listOf(note,bend,pressure,note.copy(tick=5,on=false,velocity=0)),result)
    }
}
