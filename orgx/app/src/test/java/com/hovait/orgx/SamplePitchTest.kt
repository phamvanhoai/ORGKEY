package com.hovait.orgx

import org.junit.Assert.*
import org.junit.Test

class SamplePitchTest {
    @Test fun octavesAndSourceRateAreIncluded() {
        assertEquals(1.0,SamplePitch.step(60,60,48000,48000),0.0)
        assertEquals(2.0,SamplePitch.step(72,60,48000,48000),0.0)
        assertEquals(0.5,SamplePitch.step(48,60,48000,48000),0.0)
        assertEquals(7526.0/8192,SamplePitch.step(60,60,44100,48000),0.0)
    }
    @Test fun everyMidiStepUsesIntegral8192Units() {
        repeat(128){note->val step=SamplePitch.step(note,61,44100,48000)
            assertEquals(Math.rint(step*8192),step*8192,0.0)
            assertTrue(step>0)}
    }
}
