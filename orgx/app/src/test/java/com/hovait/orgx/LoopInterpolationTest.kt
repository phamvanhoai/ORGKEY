package com.hovait.orgx

import org.junit.Assert.assertEquals
import org.junit.Test

class LoopInterpolationTest {
    @Test fun lastFractionUsesLoopStartInsteadOfTail() {
        val pcm=SamplePcm(48000,1,shortArrayOf(0,2000,6000,30000))
        assertEquals(4000.0/32768,SampleResampler.looped(pcm,2.5,0,1,3),1e-9)
        assertEquals(4000.0/32768,SampleResampler.looped(pcm,4.5,0,1,3),1e-9)
        assertEquals(1000.0/32768,SampleResampler.looped(pcm,0.5,0,1,3),1e-9)
    }
    @Test fun stereoBoundaryDoesNotMixChannels() {
        val pcm=SamplePcm(48000,2,shortArrayOf(0,0,0),shortArrayOf(0,0,2000,-2000,6000,-6000))
        assertEquals(4000.0/32768,SampleResampler.looped(pcm,2.5,0,1,3),1e-9)
        assertEquals(-4000.0/32768,SampleResampler.looped(pcm,2.5,1,1,3),1e-9)
    }
}
