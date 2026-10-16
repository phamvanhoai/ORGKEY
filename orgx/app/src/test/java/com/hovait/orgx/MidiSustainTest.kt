package com.hovait.orgx

import org.junit.Assert.*
import org.junit.Test

class MidiSustainTest {
    @Test fun allNotesOffRespectsPedalUntilControllerResetAndKeepsOtherChannel() {
        val notes=MidiSustain.resolve(listOf(pedal(0,127),MidiNoteEvent(1,60,100,true),
            MidiNoteEvent(1,65,100,true,channel=1),
            MidiNoteEvent(2,0,0,false,controller=123),
            MidiNoteEvent(10,0,0,false,controller=121)))
        val releases=notes.filter{!it.on}
        assertEquals(1,releases.size)
        assertEquals(60,releases.single().note)
        assertEquals(10L,releases.single().tick)
        assertEquals(0,releases.single().channel)
    }
    @Test fun allNotesOffWithoutPedalEmitsReleaseAtCommandTick() {
        val notes=MidiSustain.resolve(listOf(MidiNoteEvent(0,60,100,true),
            MidiNoteEvent(20,0,0,false,controller=123)))
        assertEquals(listOf(0L,20L),notes.map{it.tick})
        assertEquals(false,notes.last().on)
    }
    private fun pedal(tick:Long,value:Int)=MidiNoteEvent(tick,0,value,false,controller=64)
    @Test fun pedalDefersOnlyItsOwnChannelAndDoesNotChangeNotes() {
        val notes=MidiSustain.resolve(listOf(pedal(0,127),MidiNoteEvent(1,60,100,true),
            MidiNoteEvent(2,60,0,false),MidiNoteEvent(3,60,0,false,channel=1),pedal(10,0)))
        assertEquals(listOf(1L,3L,10L),notes.map{it.tick})
        assertEquals(listOf(true,false,false),notes.map{it.on})
    }
    @Test fun repeatedNoteClosesHeldVoiceBeforeRetrigger() {
        val notes=MidiSustain.resolve(listOf(pedal(0,127),MidiNoteEvent(1,60,100,true),
            MidiNoteEvent(2,60,0,false),MidiNoteEvent(3,60,100,true),pedal(10,0)))
        assertEquals(listOf(1L,3L,3L),notes.map{it.tick})
        assertEquals(listOf(true,false,true),notes.map{it.on})
    }
    @Test fun drumsIgnorePedalAndEndFlushesHeldRelease() {
        val notes=MidiSustain.resolve(listOf(pedal(0,127),MidiNoteEvent(2,60,0,false),
            MidiNoteEvent(3,36,0,false,channel=9),MidiNoteEvent(10,62,100,true)))
        assertEquals(3L,notes.first{it.channel==9}.tick)
        assertEquals(10L,notes.first{it.note==60}.tick)
    }
}
