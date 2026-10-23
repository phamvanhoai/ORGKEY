package com.hovait.orgx

import java.io.ByteArrayOutputStream
import org.junit.Assert.*
import org.junit.Test

class MidiModulationTest {
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
