package com.hovait.orgx

import org.junit.Assert.*
import org.junit.Test

class PitchBendPolicyTest {
    @Test fun bendRangeHasStableCenterAndTwoSemitoneEdges(){
        assertEquals(0.0,MidiPitchBend.semitones(8192),0.0)
        assertEquals(2.0,MidiPitchBend.semitones(16383),1e-12)
        assertEquals(-2.0,MidiPitchBend.semitones(0),1e-12)
    }
    @Test fun samplePlaybackBendChangesStepWithoutRecreatingVoice(){
        val sound=SampledInstrument(listOf(InstrumentRegion(60,SamplePcm(48000,1,shortArrayOf(0,8192,16384,24576),null),0,0,looping=false)),48000)
        val voice=sound.voice(60);voice.next(false);assertEquals(0.0,voice.left,0.0)
        voice.pitchBend(12.0);voice.next(false);assertEquals(.25,voice.left,0.0)
        voice.pitchBend(-12.0);voice.next(false);assertEquals(.75,voice.left,0.0)
    }
}
