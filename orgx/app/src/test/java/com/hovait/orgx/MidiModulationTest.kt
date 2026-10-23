package com.hovait.orgx

import java.io.ByteArrayOutputStream
import org.junit.Assert.*
import org.junit.Test

class MidiModulationTest {
    @Test fun resetControllersClearsModulationOnOnlyItsOwnChannel(){
        val events=listOf(MidiNoteEvent(0,60,100,true,3),
            MidiNoteEvent(1,0,80,false,3,controller=1),
            MidiNoteEvent(2,0,90,false,4,controller=1),
            MidiNoteEvent(3,0,0,false,3,controller=121),
            MidiNoteEvent(4,60,0,false,3))
        val modulation=MidiSustain.resolve(events).filter{it.controller==1}
        assertEquals(listOf(80,90,0),modulation.map{it.velocity})
        assertEquals(listOf(3,4,3),modulation.map{it.channel})
        assertEquals(3L,modulation.last().tick)
    }
    @Test fun recordedModulationKeepsChannelAndResetsAtEnd(){
        val output=ByteArrayOutputStream()
        MidiRecording.write(output,listOf(RecordedMidiEvent(0,0x93,60,100),
            RecordedMidiEvent(100_000_000,0xb3,1,80)),120,1_000_000_000)
        val song=MidiFile.read(output.toByteArray().inputStream())
        val controls=song.events.filter{it.controller==1}
        assertEquals(listOf(80,0),controls.map{it.velocity})
        assertTrue(controls.all{it.channel==3})
        assertTrue(controls.last().tick>controls.first().tick)
    }
}
