package com.hovait.orgx

import org.junit.Assert.*
import org.junit.Test

class VoiceVelocityTest {
    @Test fun sampledKeyboardKeepsTheSameVelocityRangeAsStsParts(){
        for(velocity in 0..127){
            val value=velocity/127f
            assertEquals(value,VoiceVelocity.gain(value,false,sampled=true),0f)
        }
        assertEquals(1f,VoiceVelocity.gain(2f,false,sampled=true),0f)
    }
    @Test fun everyMidiVelocityPreservesItsGain() {
        for(velocity in 1..127)assertEquals(velocity/127f,VoiceVelocity.gain(velocity/127f,true),0f)
    }
    @Test fun quietMidiNotesAreNotRaisedToTouchKeyboardMinimum() {
        assertEquals(1f/127f,VoiceVelocity.gain(1f/127f,true),0f)
        assertEquals(0.15f,VoiceVelocity.gain(1f/127f,false),0f)
        assertEquals(1f,VoiceVelocity.gain(2f,true),0f)
    }
    @Test(expected=IllegalArgumentException::class)
    fun rejectsInvalidGain(){VoiceVelocity.gain(Float.NaN,true)}
}
