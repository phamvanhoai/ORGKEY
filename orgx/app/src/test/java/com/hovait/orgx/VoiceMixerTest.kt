package com.hovait.orgx

import org.junit.Assert.assertEquals
import org.junit.Test

class VoiceMixerTest {
    @Test fun midiChannelControlsApplyVolumeExpressionAndPanIndependently(){
        val mix=MidiChannelMix()
        assertEquals(100/127.0,mix.gain(0,false),0.0)
        mix.controller(0,7,127);mix.controller(0,11,64);mix.controller(0,10,0)
        assertEquals(64/127.0,mix.gain(0,false),0.0)
        assertEquals(0.0,mix.gain(0,true),0.0)
        assertEquals(100/127.0,mix.gain(1,true),0.0)
        mix.controller(0,10,127)
        assertEquals(0.0,mix.gain(0,false),0.0)
        assertEquals(64/127.0,mix.gain(0,true),0.0)
        mix.reset();assertEquals(100/127.0,mix.gain(0,true),0.0)
    }
    @Test fun mutingKeysDoesNotMuteDrums() {
        assertEquals(0.25,VoiceMixer.mix(2.0,4,0.5,0f,0.5f),0.0)
    }
    @Test fun mutingDrumsDoesNotMuteKeys() {
        assertEquals(0.25,VoiceMixer.mix(2.0,4,0.5,0.5f,0f),0.0)
    }
    @Test fun polyphonyDoesNotReduceDrumLevel() {
        assertEquals(VoiceMixer.mix(0.0,1,0.5,1f,1f),VoiceMixer.mix(0.0,24,0.5,1f,1f),0.0)
        assertEquals(0.5,VoiceMixer.mix(0.0,0,0.5,1f,1f),0.0)
    }
}
