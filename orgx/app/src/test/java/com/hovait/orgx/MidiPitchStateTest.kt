package com.hovait.orgx

import org.junit.Assert.*
import org.junit.Test

class MidiPitchStateTest {
    @Test fun recordingStartsWithOnlyNonCenteredLiveChannelBends(){
        val state=MidiPitchState()
        assertTrue(state.recordingMessages().isEmpty())
        state.set(16383,2);state.set(0,5);state.set(9000,1,true)
        val messages=state.recordingMessages()
        assertEquals(2,messages.size)
        assertArrayEquals(byteArrayOf(0xe2.toByte(),127,127),messages[0])
        assertArrayEquals(byteArrayOf(0xe5.toByte(),0,0),messages[1])
        state.set(8192,2)
        assertEquals(1,state.recordingMessages().size)
    }
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
