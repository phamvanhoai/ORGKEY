package com.hovait.orgx

import org.junit.Assert.*
import org.junit.Test

class MidiRecordingEndTest {
    @Test fun stoppingRecordingReleasesHeldAndAlreadySustainedNotes(){
        val note=MidiNoteEvent(0,60,100,true,2)
        val pedal=MidiNoteEvent(1,0,127,false,2,controller=64)
        val off=note.copy(tick=2,on=false,velocity=0)
        val endings=MidiRecordingEnd.messages(listOf(2,2)).map{bytes->
            MidiNoteEvent(10,0,bytes[2].toInt() and 127,false,bytes[0].toInt() and 15,controller=bytes[1].toInt() and 127)
        }
        assertEquals(listOf(64,123),endings.map{it.controller})
        for(events in listOf(listOf(note,pedal),listOf(note,pedal,off))){
            val resolved=MidiSustain.resolve(events+endings)
            assertEquals(listOf(note,note.copy(tick=10,on=false,velocity=0)),resolved)
        }
        assertTrue(MidiRecordingEnd.messages(emptyList()).isEmpty())
    }
}
