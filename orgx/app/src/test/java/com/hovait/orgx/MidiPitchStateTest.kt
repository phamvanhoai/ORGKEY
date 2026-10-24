package com.hovait.orgx

import org.junit.Assert.*
import org.junit.Test

class MidiPitchStateTest {
    @Test fun factoryBendStartsAtCurrentPitchAndSmoothsHeldChangesOnTenMillisecondClock(){
        val slew=MidiPitchSlew(1000)
        assertEquals(2.0,slew.next(12.0,2),0.0)
        repeat(10){slew.next(0.0,2)}
        assertEquals(1.475,slew.next(0.0,2),1e-12)
        assertEquals(1.0,slew.next(0.0,0),0.0)
        assertEquals(.5,slew.next(-12.0,0),0.0)
    }
    @Test fun sensitivitySurvivesWritingAndReadingMidiBeforeFirstBentNote(){
        val live=MidiPitchState();live.liveRange=7
        val initial=live.sensitivityMessages().map{message->RecordedMidiEvent(0,message[0].toInt() and 255,
            message[1].toInt() and 127,message[2].toInt() and 127)}
        val output=java.io.ByteArrayOutputStream()
        MidiRecording.write(output,initial+listOf(RecordedMidiEvent(0,0xe3,127,127),RecordedMidiEvent(0,0x93,60,100)),120,1_000_000_000)
        val song=MidiFile.read(output.toByteArray().inputStream())
        val playback=MidiPitchState()
        for(event in song.events){
            when(event.controller){
                6,38,100,101->playback.playbackController(event.channel,event.controller!!,event.velocity)
                -2->playback.set(event.note or (event.velocity shl 7),event.channel,true)
                null->if(event.on){assertEquals(7.0,playback.semitones(3,true),0.0);return}
            }
        }
        fail("Recorded note missing")
    }
    @Test fun recordedSensitivityRestoresRangeAndRpnNullPreventsUnrelatedDataEntry(){
        val recorded=MidiPitchState();recorded.liveRange=12
        val playback=MidiPitchState()
        recorded.sensitivityMessages().forEach{message->playback.playbackController(message[0].toInt() and 15,
            message[1].toInt() and 127,message[2].toInt() and 127)}
        playback.set(16383,3,true);assertEquals(12.0,playback.semitones(3,true),0.0)
        assertFalse(playback.playbackController(3,6,7))
        assertEquals(12.0,playback.semitones(3,true),0.0)
        playback.playbackController(3,101,0);playback.playbackController(3,100,0)
        assertTrue(playback.playbackController(3,38,50))
        assertEquals(12.5,playback.semitones(3,true),0.0)
        playback.set(16383,4,true);assertEquals(12.0,playback.semitones(4,true),0.0)
        playback.resetPlayback();playback.set(16383,3,true)
        assertEquals(2.0,playback.semitones(3,true),0.0)
    }
    @org.junit.Test fun liveRangeChangesPreserveRawBendAndPlaybackRange(){
        val state=MidiPitchState()
        state.set(16383,0);state.set(0,1);state.set(16383,0,true)
        state.liveRange=12
        org.junit.Assert.assertEquals(12.0,state.semitones(0),0.0)
        org.junit.Assert.assertEquals(-12.0,state.semitones(1),0.0)
        org.junit.Assert.assertEquals(2.0,state.semitones(0,true),0.0)
        org.junit.Assert.assertEquals(8191,state.scriptLevel(0))
        state.liveRange=1
        org.junit.Assert.assertEquals(1.0,state.semitones(0),0.0)
        state.set(8192,0);org.junit.Assert.assertEquals(0.0,state.semitones(0),0.0)
    }
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
