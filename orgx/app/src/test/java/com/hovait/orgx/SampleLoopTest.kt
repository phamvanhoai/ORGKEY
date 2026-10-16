package com.hovait.orgx

import org.junit.Assert.*
import org.junit.Test

class SampleLoopTest {
    @Test fun attackPlaysOnceAndLoopWrapsAtExclusiveEnd() {
        assertEquals(1.5,SampleLoop.position(1.5,2,6),0.0)
        assertEquals(5.5,SampleLoop.position(5.5,2,6),0.0)
        assertEquals(2.0,SampleLoop.position(6.0,2,6),0.0)
        assertEquals(2.5,SampleLoop.position(10.5,2,6),0.0)
    }
    @Test fun stereoLoopKeepsBothChannelsAtBoundary() {
        val sample=StereoSample(SamplePcm(48000,2,shortArrayOf(0,0,0),shortArrayOf(100,-100,200,-200,300,-300)),48000,1,3)
        assertEquals(sample.frame(1),sample.frame(3))
        assertEquals(sample.frame(2),sample.frame(4))
        assertTrue(sample.looping)
    }
}
