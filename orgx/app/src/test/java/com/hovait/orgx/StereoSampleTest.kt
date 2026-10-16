package com.hovait.orgx

import org.junit.Assert.*
import org.junit.Test

class StereoSampleTest {
    @Test fun preservesOppositeChannelsInsteadOfCancellingThem() {
        val sample=StereoSample(SamplePcm(48000,2,shortArrayOf(0),shortArrayOf(12000,-12000)),48000)
        assertEquals(12000.0/32768,StereoSample.left(sample.frame(0)),1e-9)
        assertEquals(-12000.0/32768,StereoSample.right(sample.frame(0)),1e-9)
        assertEquals(0L,sample.frame(1))
    }
    @Test fun monoFeedsBothChannelsAfterResampling() {
        val sample=StereoSample(SamplePcm(24000,1,shortArrayOf(0,10000)),48000)
        assertEquals(4,sample.size)
        assertEquals(5000.0/32768,StereoSample.left(sample.frame(1)),1e-9)
        assertEquals(StereoSample.left(sample.frame(1)),StereoSample.right(sample.frame(1)),0.0)
    }
}
