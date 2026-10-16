package com.hovait.orgx

import org.junit.Assert.*
import org.junit.Test

class VoiceEnvelopeTest {
    @Test fun drumTransientIsNotFadedIn() {
        assertEquals(1f,VoiceEnvelope.next(0f,false,true,48000),0f)
        assertEquals(1f,VoiceEnvelope.next(1f,false,true,48000),0f)
    }
    @Test fun keyboardKeepsGradualAttack() {
        val first=VoiceEnvelope.next(0f,false,false,48000)
        assertTrue(first>0f && first<0.01f)
        var gain=0f
        repeat(385){gain=VoiceEnvelope.next(gain,false,false,48000)}
        assertEquals(1f,gain,0f)
    }
    @Test fun releaseReachesSilenceWithoutNegativeGain() {
        var gain=1f
        repeat(6000){gain=VoiceEnvelope.next(gain,true,true,48000)}
        assertEquals(0f,gain,0f)
        assertEquals(0f,VoiceEnvelope.next(0f,true,false,48000),0f)
    }
}
