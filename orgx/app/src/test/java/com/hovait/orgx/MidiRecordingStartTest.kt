package com.hovait.orgx

import org.junit.Assert.*
import org.junit.Test

class MidiRecordingStartTest {
    @Test fun heldKeysStartWithNoteOnAndPedalHeldKeysAlsoGetAnImmediateNoteOff(){
        val held=MidiRecordingStart.noteMessages(60,80,2,false)
        assertEquals(1,held.size);assertArrayEquals(byteArrayOf(0x92.toByte(),60,80),held.single())
        val sustained=MidiRecordingStart.noteMessages(60,80,2,true)
        assertEquals(2,sustained.size)
        assertArrayEquals(byteArrayOf(0x82.toByte(),60,0),sustained[1])
        val events=listOf(MidiNoteEvent(0,0,127,false,2,controller=64))+
            sustained.map{MidiNoteEvent(0,60,it[2].toInt(),it[0].toInt() and 0xf0==0x90,2)}+
            MidiNoteEvent(10,0,0,false,2,controller=64)
        assertEquals(listOf(0L,10L),MidiSustain.resolve(events).map{it.tick})
    }
}
