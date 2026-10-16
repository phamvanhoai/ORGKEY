package com.hovait.orgx

import org.junit.Assert.assertEquals
import org.junit.Test

class VoiceMixerTest {
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
